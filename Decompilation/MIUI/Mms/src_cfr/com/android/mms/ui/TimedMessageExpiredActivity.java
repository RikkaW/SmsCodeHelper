/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.BroadcastReceiver
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.content.res.Resources
 *  android.database.Cursor
 *  android.database.DataSetObserver
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.os.Bundle
 *  android.util.SparseBooleanArray
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.Button
 *  android.widget.CheckBox
 *  android.widget.ListAdapter
 *  android.widget.TextView
 *  java.lang.Class
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.android.mms.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.transaction.SendMessageService;
import com.android.mms.ui.ExpiredTimedMessageListAdapter;
import com.android.mms.ui.MessageItem;
import com.android.mms.ui.MessageListAdapter;
import com.android.mms.ui.MessageListView;
import com.android.mms.ui.MessageUtils;
import java.util.ArrayList;

public class TimedMessageExpiredActivity
extends Activity {
    private ExpiredTimedMessageListAdapter mAdapter;
    private final View.OnClickListener mCloseOnClickListener;
    private BroadcastReceiver mHomeKeyExpiredMsgReceiver;
    private MessageListView mListView;
    private final AdapterView.OnItemClickListener mOnItemClickListener;
    private Button mReSend;
    private final View.OnClickListener mResendOnClickListener;
    private TextView mTitleView;

    public TimedMessageExpiredActivity() {
        this.mHomeKeyExpiredMsgReceiver = new BroadcastReceiver(){

            public void onReceive(Context context, Intent intent) {
                if ("homekey".equals((Object)intent.getStringExtra("reason"))) {
                    TimedMessageExpiredActivity.this.markExpiredMsgAsFailed(false);
                    TimedMessageExpiredActivity.this.finish();
                }
            }
        };
        this.mOnItemClickListener = new AdapterView.OnItemClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onItemClick(AdapterView<?> object, View view, int n, long l) {
                if (TimedMessageExpiredActivity.this.mAdapter.getCachedMessageItem(n) == null || view == null) {
                    return;
                }
                object = (CheckBox)view.findViewById(2131820631);
                if (TimedMessageExpiredActivity.this.mListView.isItemChecked(n)) {
                    object.setChecked(true);
                } else {
                    object.setChecked(false);
                }
                TimedMessageExpiredActivity.this.mAdapter.setListViewCheckStates(TimedMessageExpiredActivity.this.mListView.getCheckedItemPositions());
                n = TimedMessageExpiredActivity.this.mListView.getCheckedItemCount();
                object = TimedMessageExpiredActivity.this.getResources().getString(2131362071);
                if (n == 0) {
                    TimedMessageExpiredActivity.this.mReSend.setText((CharSequence)object);
                    TimedMessageExpiredActivity.this.mReSend.setEnabled(false);
                    return;
                }
                TimedMessageExpiredActivity.this.mReSend.setText((CharSequence)((String)object + "(" + n + ")"));
                TimedMessageExpiredActivity.this.mReSend.setEnabled(true);
            }
        };
        this.mResendOnClickListener = new View.OnClickListener(){

            public void onClick(View view) {
                TimedMessageExpiredActivity.this.resendExpiredMsg();
                TimedMessageExpiredActivity.this.markExpiredMsgAsFailed(true);
                TimedMessageExpiredActivity.this.finish();
            }
        };
        this.mCloseOnClickListener = new View.OnClickListener(){

            public void onClick(View view) {
                TimedMessageExpiredActivity.this.markExpiredMsgAsFailed(false);
                TimedMessageExpiredActivity.this.finish();
            }
        };
    }

    /*
     * Enabled aggressive block sorting
     */
    private void markExpiredMsgAsFailed(boolean bl) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < this.mAdapter.getCount(); ++i) {
            MessageItem messageItem = this.mAdapter.getCachedMessageItem(i);
            if (messageItem == null || bl && this.mListView.isItemChecked(i)) continue;
            if (messageItem.isMms()) {
                arrayList2.add((Object)String.valueOf((long)messageItem.getMsgId()));
                continue;
            }
            arrayList.add((Object)String.valueOf((long)messageItem.getMsgId()));
        }
        if (arrayList2.size() > 0 || arrayList.size() > 0) {
            SendMessageService.startMarkFail((Context)this, arrayList2, arrayList);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void markPrivateExpiredMsgFailed(Context context) {
        Uri uri = Uri.withAppendedPath((Uri)ExpiredTimedMessageListAdapter.PRIVATE_EXPIRED_URI, (String)Long.toString((long)MmsApp.getStartupTime()));
        uri = SqliteWrapper.query((Context)context, (ContentResolver)context.getContentResolver(), (Uri)uri, (String[])MessageListAdapter.PROJECTION, (String)null, (String[])null, (String)null);
        if (uri == null) {
            return;
        }
        try {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            while (uri.moveToNext()) {
                String string2 = uri.getString(0);
                long l = uri.getLong(1);
                if ("mms".equals((Object)string2)) {
                    arrayList2.add((Object)String.valueOf((long)l));
                    continue;
                }
                arrayList.add((Object)String.valueOf((long)l));
            }
            if (arrayList2.size() <= 0 && arrayList.size() <= 0) return;
            {
                SendMessageService.startMarkFail(context, arrayList2, arrayList);
                return;
            }
        }
        finally {
            uri.close();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void raiseIfNecessary(Context context) {
        Uri uri;
        block4 : {
            uri = Uri.withAppendedPath((Uri)ExpiredTimedMessageListAdapter.NO_PRIVATE_EXPIRED_URI, (String)Long.toString((long)MmsApp.getStartupTime()));
            uri = SqliteWrapper.query((Context)context, (ContentResolver)context.getContentResolver(), (Uri)uri, (String[])MessageListAdapter.PROJECTION, (String)null, (String[])null, (String)null);
            if (uri != null) {
                int n = uri.getCount();
                n = n > 0 ? 1 : 0;
                if (n != 0) break block4;
            }
            return;
        }
        uri = new Intent(context, (Class)TimedMessageExpiredActivity.class);
        uri.setFlags(268435456);
        context.startActivity((Intent)uri);
        return;
        finally {
            uri.close();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void resendExpiredMsg() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i = 0; i < this.mAdapter.getCount(); ++i) {
            MessageItem messageItem;
            if (!this.mListView.isItemChecked(i) || (messageItem = this.mAdapter.getCachedMessageItem(i)) == null) continue;
            if (messageItem.isMms()) {
                arrayList.add((Object)String.valueOf((long)messageItem.getMsgId()));
                continue;
            }
            arrayList2.add((Object)String.valueOf((long)messageItem.getMsgId()));
            arrayList3.add((Object)messageItem.getSimId());
        }
        if (arrayList2.size() > 0) {
            SendMessageService.startReSendSms((Context)this, arrayList2, MessageUtils.getSlotId(arrayList3));
        }
        if (arrayList.size() > 0) {
            SendMessageService.startReSendMms((Context)this, arrayList);
        }
    }

    public void onBackPressed() {
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968715);
        MessageUtils.setWindowLayoutParams(this);
        this.setFinishOnTouchOutside(false);
        this.mAdapter = new ExpiredTimedMessageListAdapter((Context)this);
        this.mTitleView = (TextView)this.findViewById(2131820892);
        this.mListView = (MessageListView)this.findViewById(16908298);
        this.mListView.setChoiceMode(2);
        this.mListView.setAdapter((ListAdapter)this.mAdapter);
        this.mListView.setOnItemClickListener(this.mOnItemClickListener);
        this.mAdapter.load();
        this.mAdapter.registerDataSetObserver(new DataSetObserver(){

            public void onChanged() {
                if (TimedMessageExpiredActivity.this.mAdapter.getCount() == 0) {
                    TimedMessageExpiredActivity.this.finish();
                    return;
                }
                TimedMessageExpiredActivity.this.mTitleView.setText((CharSequence)TimedMessageExpiredActivity.this.getString(2131362157, new Object[]{TimedMessageExpiredActivity.this.mAdapter.getCount()}));
            }
        });
        this.findViewById(2131820894).setOnClickListener(this.mCloseOnClickListener);
        this.mReSend = (Button)this.findViewById(2131820893);
        this.mReSend.setEnabled(false);
        this.mReSend.setOnClickListener(this.mResendOnClickListener);
        bundle = new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        this.registerReceiver(this.mHomeKeyExpiredMsgReceiver, (IntentFilter)bundle);
    }

    protected void onDestroy() {
        this.mAdapter.stop();
        this.mAdapter.changeCursor(null);
        this.unregisterReceiver(this.mHomeKeyExpiredMsgReceiver);
        super.onDestroy();
    }

}

