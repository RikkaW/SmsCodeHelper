/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.AsyncQueryHandler
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteException
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.Handler
 *  android.util.Log
 *  android.util.SparseBooleanArray
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.CheckBox
 *  android.widget.CursorAdapter
 *  android.widget.TextView
 *  com.google.android.mms.MmsException
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Map$Entry
 *  miui.provider.ExtraTelephony
 *  miui.provider.ExtraTelephony$MmsSms
 */
package com.android.mms.ui;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.ui.MessageItem;
import com.android.mms.ui.MessageListAdapter;
import com.android.mms.ui.MessageListItem;
import com.android.mms.ui.MessageUtils;
import com.google.android.mms.MmsException;
import java.util.LinkedHashMap;
import java.util.Map;
import miui.provider.ExtraTelephony;

public class ExpiredTimedMessageListAdapter
extends CursorAdapter {
    public static final Uri NO_PRIVATE_EXPIRED_URI;
    public static final Uri PRIVATE_EXPIRED_URI;
    static final String[] PROJECTION;
    private BackgroundQueryHandler mBackgroundQueryHandler;
    private SparseBooleanArray mCheckStates;
    private MessageListAdapter.ColumnsMap mColumnsMap;
    private Context mContext;
    private Handler mHandler = new Handler();
    private LayoutInflater mInflater;
    private boolean mIsStoped = false;
    private final LinkedHashMap<Long, MessageItem> mMessageItemCache;

    static {
        PROJECTION = MessageListAdapter.PROJECTION;
        NO_PRIVATE_EXPIRED_URI = ExtraTelephony.MmsSms.CONTENT_EXPIRED_URI.buildUpon().appendQueryParameter("privacy_flag", "0").build();
        PRIVATE_EXPIRED_URI = ExtraTelephony.MmsSms.CONTENT_EXPIRED_URI.buildUpon().appendQueryParameter("privacy_flag", "1").build();
    }

    public ExpiredTimedMessageListAdapter(Context context) {
        super(context, null);
        this.mContext = context;
        this.mBackgroundQueryHandler = new BackgroundQueryHandler(context.getContentResolver());
        this.mColumnsMap = new MessageListAdapter.ColumnsMap();
        this.mInflater = (LayoutInflater)context.getSystemService("layout_inflater");
        this.mMessageItemCache = new LinkedHashMap<Long, MessageItem>(10, 1.0f, true){

            @Override
            protected boolean removeEldestEntry(Map.Entry entry) {
                if (this.size() > 50) {
                    return true;
                }
                return false;
            }
        };
    }

    public static long getKey(String string2, long l) {
        long l2 = l;
        if ("mms".equals((Object)string2)) {
            l2 = - l;
        }
        return l2;
    }

    private boolean isCursorValid(Cursor cursor) {
        if (cursor.isClosed() || cursor.isBeforeFirst() || cursor.isAfterLast()) {
            return false;
        }
        return true;
    }

    private boolean isItemChecked(int n) {
        if (this.mCheckStates != null) {
            return this.mCheckStates.get(n);
        }
        return false;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        if (view instanceof MessageListItem) {
            Object object = this.getCachedMessageItem(cursor);
            ((MessageListItem)view).rebind((MessageItem)object, false, false, null, null);
            TextView textView = (TextView)view.findViewById(2131820632);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(MessageUtils.getPreciseTimeStamp(object.getDate(), true));
            stringBuilder.append(" ");
            stringBuilder.append(context.getString(2131362159));
            stringBuilder.append(" ");
            object = Conversation.get(object.getThreadId());
            object.loadRecipients(false, false);
            object = object.getRecipients();
            for (int i = 0; i < object.size() && i < 3; ++i) {
                if (i != 0) {
                    stringBuilder.append(",");
                }
                stringBuilder.append(((Contact)object.get(i)).getName());
            }
            if (object.size() > 3) {
                stringBuilder.append(context.getString(2131362160, new Object[]{object.size()}));
            }
            textView.setText((CharSequence)stringBuilder.toString());
            ((CheckBox)view.findViewById(2131820631)).setChecked(this.isItemChecked(cursor.getPosition()));
        }
    }

    public MessageItem getCachedMessageItem(int n) {
        Cursor cursor = (Cursor)this.getItem(n);
        if (cursor == null) {
            return null;
        }
        return this.getCachedMessageItem(cursor);
    }

    public MessageItem getCachedMessageItem(Cursor cursor) {
        return this.getCachedMessageItem(cursor.getString(this.mColumnsMap.mColumnMsgType), cursor.getLong(this.mColumnsMap.mColumnMsgId), cursor);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public MessageItem getCachedMessageItem(String object, long l, Cursor cursor) {
        MessageItem messageItem = this.mMessageItemCache.get(ExpiredTimedMessageListAdapter.getKey((String)object, l));
        if (messageItem != null) return messageItem;
        if (cursor == null) return messageItem;
        if (!this.isCursorValid(cursor)) return messageItem;
        try {
            object = new MessageItem(this.mContext, (String)object, cursor, this.mColumnsMap, true);
        }
        catch (MmsException var1_2) {}
        try {
            this.mMessageItemCache.put((Object)ExpiredTimedMessageListAdapter.getKey(object.getType(), object.getMsgId()), object);
            return object;
        }
        catch (MmsException var1_4) {}
        {
            String string2;
            string2 = string2.getMessage() == null ? "Caught MmsException" : string2.getMessage();
            Log.e((String)"ExpiredTimedMessageListAdapter", (String)string2);
            return null;
        }
    }

    public void load() {
        this.mIsStoped = false;
        this.mBackgroundQueryHandler.cancelOperation(1001);
        try {
            Uri uri = Uri.withAppendedPath((Uri)NO_PRIVATE_EXPIRED_URI, (String)Long.toString((long)MmsApp.getStartupTime()));
            this.mBackgroundQueryHandler.startQuery(1001, (Object)null, uri, PROJECTION, null, null, null);
            return;
        }
        catch (SQLiteException var1_2) {
            SqliteWrapper.checkSQLiteException((Context)this.mContext, (SQLiteException)var1_2);
            return;
        }
    }

    public View newView(Context object, Cursor object2, ViewGroup viewGroup) {
        object = this.getCachedMessageItem((Cursor)object2);
        object2 = (MessageListItem)this.mInflater.inflate(2130968605, viewGroup, false);
        object2.bind((MessageItem)object);
        object2.setMsgListItemHandler(this.mHandler);
        return object2;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.mMessageItemCache.clear();
    }

    public void setListViewCheckStates(SparseBooleanArray sparseBooleanArray) {
        this.mCheckStates = sparseBooleanArray;
    }

    public void stop() {
        this.mIsStoped = true;
    }

    private final class BackgroundQueryHandler
    extends AsyncQueryHandler {
        public BackgroundQueryHandler(ContentResolver contentResolver) {
            super(contentResolver);
        }

        protected void onQueryComplete(int n, Object object, Cursor cursor) {
            switch (n) {
                default: {
                    return;
                }
                case 1001: 
            }
            if (ExpiredTimedMessageListAdapter.this.mIsStoped) {
                if (cursor != null) {
                    cursor.close();
                }
                Log.v((String)"ExpiredTimedMessageListAdapter", (String)"query cursor close for stop");
                return;
            }
            ExpiredTimedMessageListAdapter.this.changeCursor(cursor);
        }
    }

}

