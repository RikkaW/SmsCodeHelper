/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Message
 *  android.os.SystemClock
 *  android.provider.Telephony
 *  android.provider.Telephony$Sms
 *  android.provider.Telephony$Sms$Inbox
 *  android.telephony.SmsMessage
 *  android.text.TextUtils
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.ArrayList
 *  miui.app.Activity
 *  miui.app.AlertDialog
 *  miui.app.AlertDialog$Builder
 */
package com.android.mms.ui;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.util.MSimUtils;
import java.util.ArrayList;
import miui.app.Activity;
import miui.app.AlertDialog;

public class ClassZeroActivity
extends Activity {
    private static final int BUFFER_OFFSET = "         ".length() * 2;
    private static final String[] REPLACE_PROJECTION = new String[]{"_id", "address", "protocol", "sim_id"};
    private final DialogInterface.OnClickListener mCancelListener;
    private AlertDialog mDialog = null;
    private Handler mHandler;
    private MessageItem mMessageItem;
    private ArrayList<MessageItem> mMessageQueue = null;
    private boolean mRead = false;
    private final DialogInterface.OnClickListener mSaveListener;
    private long mTimerSet = 0;

    public ClassZeroActivity() {
        this.mHandler = new Handler(){

            public void handleMessage(Message message) {
                if (message.what == 1) {
                    ClassZeroActivity.this.mRead = false;
                    ClassZeroActivity.this.mDialog.dismiss();
                    ClassZeroActivity.this.saveMessage();
                    ClassZeroActivity.this.processNextMessage();
                }
            }
        };
        this.mCancelListener = new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                dialogInterface.dismiss();
                ClassZeroActivity.this.processNextMessage();
            }
        };
        this.mSaveListener = new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                ClassZeroActivity.this.mRead = true;
                ClassZeroActivity.this.saveMessage();
                dialogInterface.dismiss();
                ClassZeroActivity.this.processNextMessage();
            }
        };
    }

    private void displayZeroMessage(MessageItem object) {
        this.mMessageItem = object;
        object = object.mMessage.getMessageBody();
        this.mDialog = new AlertDialog.Builder((Context)this).setMessage((CharSequence)object).setTitle(2131362026).setPositiveButton(2131362032, this.mSaveListener).setNegativeButton(17039360, this.mCancelListener).show();
        this.mDialog.setCancelable(false);
        this.mDialog.setCanceledOnTouchOutside(false);
        this.mTimerSet = 300000 + SystemClock.uptimeMillis();
    }

    /*
     * Enabled aggressive block sorting
     */
    private ContentValues extractContentValues(SmsMessage smsMessage) {
        int n = 1;
        ContentValues contentValues = new ContentValues();
        contentValues.put("address", smsMessage.getDisplayOriginatingAddress());
        contentValues.put("date", new Long(System.currentTimeMillis()));
        contentValues.put("protocol", Integer.valueOf((int)smsMessage.getProtocolIdentifier()));
        int n2 = this.mRead ? 1 : 0;
        contentValues.put("read", Integer.valueOf((int)n2));
        n2 = this.mRead ? 1 : 0;
        contentValues.put("seen", Integer.valueOf((int)n2));
        if (smsMessage.getPseudoSubject().length() > 0) {
            contentValues.put("subject", smsMessage.getPseudoSubject());
        }
        n2 = smsMessage.isReplyPathPresent() ? n : 0;
        contentValues.put("reply_path_present", Integer.valueOf((int)n2));
        contentValues.put("service_center", smsMessage.getServiceCenterAddress());
        return contentValues;
    }

    private void processNextMessage() {
        if (this.mMessageQueue.isEmpty()) {
            this.finish();
            return;
        }
        this.mMessageQueue.remove(0);
        if (this.mMessageQueue.isEmpty()) {
            this.finish();
            return;
        }
        this.displayZeroMessage((MessageItem)this.mMessageQueue.get(0));
    }

    private boolean queueMsgFromIntent(Intent intent) {
        byte[] arrby = intent.getByteArrayExtra("pdu");
        String string2 = intent.getStringExtra("format");
        long l = MSimUtils.getSimIdFromIntent(intent);
        intent = SmsMessage.createFromPdu((byte[])arrby, (String)string2);
        if (TextUtils.isEmpty((CharSequence)intent.getMessageBody()) || l < 0) {
            if (this.mMessageQueue.size() == 0) {
                this.finish();
            }
            return false;
        }
        this.mMessageQueue.add((Object)new MessageItem((SmsMessage)intent, l));
        return true;
    }

    private Uri replaceMessage(MessageItem messageItem) {
        Object object = messageItem.mMessage;
        ContentValues contentValues = this.extractContentValues((SmsMessage)object);
        contentValues.put("body", object.getMessageBody());
        ContentResolver contentResolver = this.getContentResolver();
        String string2 = object.getOriginatingAddress();
        object = Integer.toString((int)object.getProtocolIdentifier());
        String string3 = Long.toString((long)messageItem.mSimId);
        string2 = SqliteWrapper.query((Context)this, (ContentResolver)contentResolver, (Uri)Telephony.Sms.Inbox.CONTENT_URI, (String[])REPLACE_PROJECTION, (String)"address = ? AND protocol = ? AND sim_id = ?", (String[])new String[]{string2, object, string3}, (String)null);
        try {
            if (string2.moveToFirst()) {
                long l = string2.getLong(0);
                messageItem = ContentUris.withAppendedId((Uri)Telephony.Sms.CONTENT_URI, (long)l);
                SqliteWrapper.update((Context)this, (ContentResolver)contentResolver, (Uri)messageItem, (ContentValues)contentValues, (String)null, (String[])null);
                return messageItem;
            }
            return this.storeMessage(messageItem);
        }
        finally {
            string2.close();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void saveMessage() {
        Uri uri = this.mMessageItem.mMessage.isReplace() ? this.replaceMessage(this.mMessageItem) : this.storeMessage(this.mMessageItem);
        if (!this.mRead && uri != null) {
            MessagingNotification.nonBlockingUpdateNewMessageIndicator((Context)this, true, false);
        }
    }

    private Uri storeMessage(MessageItem messageItem) {
        SmsMessage smsMessage = messageItem.mMessage;
        ContentValues contentValues = this.extractContentValues(smsMessage);
        contentValues.put("body", smsMessage.getDisplayMessageBody());
        contentValues.put("sim_id", Long.valueOf((long)messageItem.mSimId));
        return SqliteWrapper.insert((Context)this, (ContentResolver)this.getContentResolver(), (Uri)Telephony.Sms.Inbox.CONTENT_URI, (ContentValues)contentValues);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.mMessageQueue == null) {
            this.mMessageQueue = new ArrayList();
        }
        if (!this.queueMsgFromIntent(this.getIntent())) {
            return;
        }
        if (this.mMessageQueue.size() == 1) {
            this.displayZeroMessage((MessageItem)this.mMessageQueue.get(0));
        }
        if (bundle == null) return;
        this.mTimerSet = bundle.getLong("timer_fire", this.mTimerSet);
    }

    protected void onNewIntent(Intent intent) {
        this.queueMsgFromIntent(intent);
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putLong("timer_fire", this.mTimerSet);
    }

    protected void onStart() {
        super.onStart();
        long l = SystemClock.uptimeMillis();
        if (this.mTimerSet <= l) {
            this.mHandler.sendEmptyMessage(1);
            return;
        }
        this.mHandler.sendEmptyMessageAtTime(1, this.mTimerSet);
    }

    protected void onStop() {
        super.onStop();
        this.mHandler.removeMessages(1);
    }

    private static class MessageItem {
        public SmsMessage mMessage;
        public long mSimId;

        public MessageItem(SmsMessage smsMessage, long l) {
            this.mMessage = smsMessage;
            this.mSimId = l;
        }
    }

}

