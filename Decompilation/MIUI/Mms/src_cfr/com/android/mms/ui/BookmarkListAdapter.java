/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.AsyncQueryHandler
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteException
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.Handler
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Sms
 *  android.util.Log
 *  android.util.LruCache
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.CursorAdapter
 *  com.google.android.mms.MmsException
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  miui.provider.ExtraTelephony
 *  miui.provider.ExtraTelephony$MmsSms
 */
package com.android.mms.ui;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.Handler;
import android.provider.Telephony;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.ui.MessageItem;
import com.android.mms.ui.MessageListAdapter;
import com.android.mms.ui.MessageListItem;
import com.google.android.mms.MmsException;
import miui.provider.ExtraTelephony;

public class BookmarkListAdapter
extends CursorAdapter {
    private static final Uri CONTENT_ALL_LOCKED_URI = ExtraTelephony.MmsSms.CONTENT_ALL_LOCKED_URI.buildUpon().appendQueryParameter("privacy_flag", "0").build();
    static final String[] PROJECTION = MessageListAdapter.PROJECTION;
    private BackgroundQueryHandler mBackgroundQueryHandler;
    private MessageListAdapter.ColumnsMap mColumnsMap;
    private Context mContext;
    private Handler mHandler = new Handler();
    private LayoutInflater mInflater;
    private boolean mIsStoped = false;
    private final LruCache<Long, MessageItem> mMessageItemCache;
    private float mTextSize;

    public BookmarkListAdapter(Context context) {
        super(context, null);
        this.mContext = context;
        this.mBackgroundQueryHandler = new BackgroundQueryHandler(context.getContentResolver());
        this.mColumnsMap = new MessageListAdapter.ColumnsMap();
        this.mInflater = (LayoutInflater)context.getSystemService("layout_inflater");
        this.mMessageItemCache = new LruCache(500);
    }

    private Contact getContact(MessageItem object) {
        if (object.isMms() && object.getAddress() == null && (object = Conversation.get(object.getThreadId())) != null && (object = object.getRecipients()) != null && object.size() > 0) {
            return (Contact)object.get(0);
        }
        return null;
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

    public void bindView(View view, Context object, Cursor cursor) {
        if (view instanceof MessageListItem) {
            object = this.getCachedMessageItem(cursor);
            ((MessageListItem)view).rebind((MessageItem)object, false, false, null, null);
            ((MessageListItem)view).setBodyTextSize(this.mTextSize);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void deleteBookmark(MessageItem messageItem) {
        Uri uri = messageItem.isSms() ? Telephony.Sms.CONTENT_URI : Telephony.Mms.CONTENT_URI;
        messageItem = ContentUris.withAppendedId((Uri)uri, (long)messageItem.getMsgId());
        uri = new ContentValues(1);
        uri.put("locked", Integer.valueOf((int)0));
        this.mBackgroundQueryHandler.startUpdate(1000, (Object)null, (Uri)messageItem, (ContentValues)uri, null, null);
        this.load();
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
        MessageItem messageItem = (MessageItem)this.mMessageItemCache.get((Object)BookmarkListAdapter.getKey((String)object, l));
        if (messageItem != null) return messageItem;
        if (cursor == null) return messageItem;
        if (!this.isCursorValid(cursor)) return messageItem;
        try {
            object = new MessageItem(this.mContext, (String)object, cursor, this.mColumnsMap, true);
        }
        catch (MmsException var1_2) {}
        try {
            if (object.isMms() && object.getAddress() == null) {
                object.setContact(this.getContact((MessageItem)object));
            }
            this.mMessageItemCache.put((Object)BookmarkListAdapter.getKey(object.getType(), object.getMsgId()), object);
            return object;
        }
        catch (MmsException var1_4) {}
        {
            String string2;
            string2 = string2.getMessage() == null ? "Caught MmsException" : string2.getMessage();
            Log.e((String)"BookmarkListAdapter", (String)string2);
            return null;
        }
    }

    public void load() {
        this.mIsStoped = false;
        this.mBackgroundQueryHandler.cancelOperation(1001);
        try {
            this.mBackgroundQueryHandler.startQuery(1001, (Object)null, CONTENT_ALL_LOCKED_URI, PROJECTION, null, null, null);
            return;
        }
        catch (SQLiteException var1_1) {
            SqliteWrapper.checkSQLiteException((Context)this.mContext, (SQLiteException)var1_1);
            return;
        }
    }

    public View newView(Context object, Cursor object2, ViewGroup viewGroup) {
        object = this.getCachedMessageItem((Cursor)object2);
        object2 = (MessageListItem)this.mInflater.inflate(2130968589, viewGroup, false);
        object2.bind((MessageItem)object);
        object2.setMsgListItemHandler(this.mHandler);
        return object2;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.mMessageItemCache.evictAll();
    }

    public void setTextSize(float f2) {
        this.mTextSize = f2;
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
            if (BookmarkListAdapter.this.mIsStoped) {
                if (cursor != null) {
                    cursor.close();
                }
                Log.v((String)"BookmarkListAdapter", (String)"query cursor close for stop");
                return;
            }
            BookmarkListAdapter.this.changeCursor(cursor);
        }
    }

}

