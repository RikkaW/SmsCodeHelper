/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Service
 *  android.content.BroadcastReceiver
 *  android.content.ComponentName
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.database.Cursor
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.HandlerThread
 *  android.os.IBinder
 *  android.os.Looper
 *  android.os.Message
 *  android.provider.Telephony
 *  android.provider.Telephony$Sms
 *  android.provider.Telephony$Sms$Inbox
 *  android.provider.Telephony$Sms$Intents
 *  android.provider.Telephony$Sms$Outbox
 *  android.provider.Telephony$Threads
 *  android.telephony.ServiceState
 *  android.telephony.SmsMessage
 *  android.telephony.SmsMessage$MessageClass
 *  android.text.TextUtils
 *  android.util.Log
 *  android.widget.Toast
 *  com.android.internal.telephony.SmsMessageBase
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.GregorianCalendar
 */
package com.android.mms.transaction;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.provider.Telephony;
import android.telephony.ServiceState;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.android.internal.telephony.SmsMessageBase;
import com.android.mms.data.Contact;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.transaction.MmsSystemEventReceiver;
import com.android.mms.transaction.SmsReceiver;
import com.android.mms.transaction.SmsReportService;
import com.android.mms.ui.ClassZeroActivity;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.MSimUtils;
import com.miui.gallery.lib.MiuiGalleryUtils;
import java.util.GregorianCalendar;

public class SmsReceiverService
extends Service {
    private static final String[] REPLACE_PROJECTION;
    private static final String[] SEND_PROJECTION;
    private Runnable mDelaySend;
    private int mResultCode;
    private boolean[] mSending;
    private ServiceHandler mServiceHandler;
    private Looper mServiceLooper;
    public Handler mToastHandler = new Handler();

    static {
        SEND_PROJECTION = new String[]{"_id", "thread_id", "address", "body", "status", "mx_status", "mx_id", "sim_id", "b2c_numbers"};
        REPLACE_PROJECTION = new String[]{"_id", "address", "protocol", "sim_id"};
    }

    public SmsReceiverService() {
        this.mDelaySend = new Runnable(){

            @Override
            public void run() {
                Log.d((String)"SmsReceiverService", (String)"send queued message without toast");
                MSimUtils.sendQueuedMessageNoToast((Context)SmsReceiverService.this, 0);
            }
        };
    }

    private String buildMessageString(SmsMessage[] object) {
        int n;
        Object object2;
        StringBuilder stringBuilder = new StringBuilder();
        int n2 = 0;
        int n3 = 1;
        int n4 = object.length;
        for (n = 0; n < n4; ++n) {
            object2 = object[n];
            n2 += object2.getUserData().length;
            if (object2.mWrappedSmsMessage != null) {
                stringBuilder.append(object2.getDisplayMessageBody());
            }
            if (object2.getEncodingType() == 3) continue;
            n3 = 0;
        }
        if (n3 != 0) {
            stringBuilder = (StringBuilder)new byte[n2];
            n3 = 0;
            n2 = object.length;
            for (n = 0; n < n2; ++n) {
                object2 = object[n].getUserData();
                n4 = object2.length;
                System.arraycopy((Object)object2, (int)0, (Object)stringBuilder, (int)n3, (int)n4);
                n3 += n4;
            }
            try {
                object = new String((byte[])stringBuilder, "utf-16");
                return object;
            }
            catch (Exception var1_2) {
                Log.e((String)"SmsReceiverService", (String)"buildMessageString: new string utf-16 error");
                return null;
            }
        }
        return stringBuilder.toString();
    }

    private void displayClassZeroMessage(Context context, SmsMessage smsMessage, String string, long l) {
        smsMessage = new Intent(context, (Class)ClassZeroActivity.class).putExtra("pdu", smsMessage.getPdu()).putExtra("format", string).setFlags(402653184);
        smsMessage.putExtra("sim_id", l);
        context.startActivity((Intent)smsMessage);
    }

    /*
     * Enabled aggressive block sorting
     */
    private ContentValues extractContentValues(SmsMessage smsMessage) {
        ContentValues contentValues = new ContentValues();
        String string = smsMessage.getDisplayOriginatingAddress();
        contentValues.put("address", string);
        GregorianCalendar gregorianCalendar = new GregorianCalendar(2011, 8, 18);
        GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
        long l = System.currentTimeMillis();
        gregorianCalendar2.setTimeInMillis(l);
        if (gregorianCalendar2.before((Object)gregorianCalendar)) {
            l = smsMessage.getTimestampMillis();
        }
        contentValues.put("date", new Long(l));
        contentValues.put("date_sent", Long.valueOf((long)smsMessage.getTimestampMillis()));
        contentValues.put("protocol", Integer.valueOf((int)smsMessage.getProtocolIdentifier()));
        contentValues.put("read", Integer.valueOf((int)0));
        contentValues.put("seen", Integer.valueOf((int)0));
        if (MessageUtils.isServiceNumber((Context)this, string)) {
            contentValues.put("advanced_seen", Integer.valueOf((int)1));
        } else {
            contentValues.put("advanced_seen", Integer.valueOf((int)0));
        }
        if (smsMessage.getPseudoSubject().length() > 0) {
            contentValues.put("subject", smsMessage.getPseudoSubject());
        }
        int n = smsMessage.isReplyPathPresent() ? 1 : 0;
        contentValues.put("reply_path_present", Integer.valueOf((int)n));
        contentValues.put("service_center", smsMessage.getServiceCenterAddress());
        return contentValues;
    }

    /*
     * Enabled aggressive block sorting
     */
    private String getSelectionBySlotId(boolean bl, int n) {
        String string;
        String string2;
        long l = MSimUtils.getSimIdBySlotId(n);
        if (l < 0) {
            if (MSimUtils.isSimInserted(n)) {
                MSimUtils.sendQueuedMessage((Context)this, n);
                Log.v((String)"SmsReceiverService", (String)"sim info is not ready, retry");
                return null;
            }
            if (MSimUtils.isAndroid50() && MSimUtils.isMSim()) {
                String string3 = "address is not null AND address!='' AND mx_status!=196609";
                if (n == 0) {
                    string2 = string3;
                    if (MSimUtils.isSimInserted(1)) {
                        l = MSimUtils.getSimIdBySlotId(1);
                        string2 = "address is not null AND address!='' AND mx_status!=196609" + " AND " + "sim_id" + "!=" + l;
                    }
                } else {
                    string2 = string3;
                    if (n == 1) {
                        string2 = string3;
                        if (MSimUtils.isSimInserted(0)) {
                            l = MSimUtils.getSimIdBySlotId(0);
                            string2 = "address is not null AND address!='' AND mx_status!=196609" + " AND " + "sim_id" + "!=" + l;
                        }
                    }
                }
                Log.v((String)"SmsReceiverService", (String)"sim is not inserted, and not register");
                return string2;
            }
            this.registerForServiceStateChanged(n);
            if (bl) {
                this.showToastWhenOffline(n);
            }
            Log.v((String)"SmsReceiverService", (String)"sim is not inserted, but register");
            return null;
        }
        string2 = string = "address is not null AND address!='' AND mx_status!=196609";
        if (!MSimUtils.isMSim()) return string2;
        string2 = string;
        if (!MSimUtils.isMSimInserted()) return string2;
        {
            if (n == 1) {
                return "address is not null AND address!='' AND mx_status!=196609" + " AND " + "sim_id" + "=" + l;
            }
        }
        l = MSimUtils.getSimIdBySlotId(1);
        return "address is not null AND address!='' AND mx_status!=196609" + " AND " + "sim_id" + "!=" + l;
    }

    private void handleBootCompleted() {
        if (this.moveOutboxMessagesToFailedBox() > 0) {
            MessagingNotification.notifySendFailed(this.getApplicationContext(), true);
        }
        MSimUtils.sendQueuedMessageNoToast((Context)this, MSimUtils.SLOT_ID_ALL);
        MessagingNotification.blockingUpdateNewMessageIndicator((Context)this, true, false);
    }

    private void handleSendMessage(Intent intent) {
        int n = MSimUtils.getSlotIdFromIntent(intent);
        boolean bl = intent.getBooleanExtra("show_toast_when_offline", true);
        if (MSimUtils.isMSimSlotIdValid(n)) {
            if (!this.mSending[n]) {
                this.sendFirstQueuedMessage(bl, n);
            }
            return;
        }
        Log.v((String)"SmsReceiverService", (String)"handleSendMessage slot id is not valid");
    }

    private void handleServiceStateChanged(Intent intent) {
        if (ServiceState.newFromBundle((Bundle)intent.getExtras()).getState() == 0) {
            Log.d((String)"SmsReceiverService", (String)"service is in service");
            this.unRegisterForServiceStateChanged();
            this.mToastHandler.removeCallbacks(this.mDelaySend);
            this.mToastHandler.postDelayed(this.mDelaySend, 10000);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void handleSmsReceived(Intent intent, int n) {
        SmsMessage[] arrsmsMessage = Telephony.Sms.Intents.getMessagesFromIntent((Intent)intent);
        String string = intent.getStringExtra("format");
        int n2 = intent.getIntExtra("blockType", 0);
        int n3 = MSimUtils.getSlotIdFromIntent(intent);
        long l = MSimUtils.getSimIdBySlotId(n3);
        if (l < 0) {
            Log.e((String)"SmsReceiverService", (String)("handleSmsReceived: cannot get simId for slot " + n3));
            return;
        } else {
            intent = this.insertMessage((Context)this, arrsmsMessage, n, string, l, n2);
            string = arrsmsMessage[0].getOriginatingAddress();
            if (intent == null || MessageUtils.hasBlockedFlag((Uri)intent)) return;
            {
                MessagingNotification.blockingUpdateNewMessageIndicator((Context)this, true, false);
                if (n2 == 1) return;
                {
                    MessageUtils.processReceivedMsgReport((Context)this, string, this.buildMessageString(arrsmsMessage), n3);
                    return;
                }
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void handleSmsSent(Intent intent, int n) {
        Uri uri = intent.getData();
        boolean bl = intent.getBooleanExtra("SendNextMsg", false);
        int n2 = MSimUtils.getSlotIdFromIntent(intent);
        if (MSimUtils.isMSimSlotIdValid(n2)) {
            this.mSending[n2] = false;
        }
        Log.d((String)"SmsReceiverService", (String)("message sent, uri: " + (Object)uri + ", result: " + this.mResultCode));
        if (this.mResultCode == -1) {
            if (Log.isLoggable((String)"Mms:transaction", (int)2)) {
                Log.v((String)"SmsReceiverService", (String)("handleSmsSent move message to sent folder uri: " + (Object)uri));
            }
            if (!SmsReceiverService.moveMessageToFolderIfNotFailed((Context)this, uri, 2, n)) {
                Log.e((String)"SmsReceiverService", (String)("handleSmsSent: failed to move message " + (Object)uri + " to sent folder"));
            }
            if (bl && MSimUtils.isMSimSlotIdValid(n2)) {
                this.sendFirstQueuedMessage(true, n2);
            }
            MessagingNotification.updateSendFailedNotification((Context)this);
            return;
        } else if (this.mResultCode == 2 || this.mResultCode == 4) {
            Log.v((String)"SmsReceiverService", (String)("handleSmsSent: no service, queuing message w/ uri: " + (Object)uri + " slotId is " + n2));
            this.registerForServiceStateChanged(n2);
            Telephony.Sms.moveMessageToFolder((Context)this, (Uri)uri, (int)6, (int)n);
            if (!intent.getBooleanExtra("show_toast_when_offline", true)) return;
            {
                this.showToastWhenOffline(n2);
                return;
            }
        } else {
            if (this.mResultCode == 6) {
                this.mToastHandler.post(new Runnable(){

                    @Override
                    public void run() {
                        Toast.makeText((Context)SmsReceiverService.this, (CharSequence)SmsReceiverService.this.getString(2131362001), (int)0).show();
                    }
                });
                return;
            }
            if (this.mResultCode == 133404 && n == 0) {
                if (!Log.isLoggable((String)"Mms:transaction", (int)2)) return;
                {
                    Log.v((String)"SmsReceiverService", (String)"HTC phone mResultCode == RESULT_ERROR_HTC_TEMP_FAILURE && error == 0");
                    return;
                }
            } else {
                this.messageFailedToSend(uri, n, n2);
                if (!bl || !MSimUtils.isMSimSlotIdValid(n2)) return;
                {
                    this.sendFirstQueuedMessage(true, n2);
                    return;
                }
            }
        }
    }

    private Uri insertMessage(Context context, SmsMessage[] arrsmsMessage, int n, String string, long l, int n2) {
        SmsMessage smsMessage = arrsmsMessage[0];
        if (this.isDuplicate(smsMessage)) {
            Log.w((String)"SmsReceiverService", (String)"duplicated sms ignored");
            return null;
        }
        if (smsMessage.getDisplayMessageBody() == null) {
            return null;
        }
        if (smsMessage.getMessageClass() == SmsMessage.MessageClass.CLASS_0) {
            this.displayClassZeroMessage(context, smsMessage, string, l);
            return null;
        }
        if (smsMessage.isReplace()) {
            return this.replaceMessage(context, arrsmsMessage, n, l, n2);
        }
        return this.storeMessage(context, arrsmsMessage, n, l, n2);
    }

    private boolean isDuplicate(SmsMessage object) {
        block8 : {
            if (object.getMessageClass() == SmsMessage.MessageClass.CLASS_0 || object.getMessageClass() == SmsMessage.MessageClass.CLASS_2) {
                return false;
            }
            String string = object.getOriginatingAddress();
            if (TextUtils.isEmpty((CharSequence)string)) {
                return false;
            }
            long l = object.getTimestampMillis();
            if (l <= 0) {
                return false;
            }
            if (TextUtils.isEmpty((CharSequence)(object = object.getMessageBody()))) {
                return false;
            }
            ContentResolver contentResolver = this.getContentResolver();
            Uri uri = Telephony.Sms.Inbox.CONTENT_URI;
            String string2 = "address=? AND date_sent=" + l + " AND " + "body" + "=?" + " AND " + "mx_status" + "=" + 0;
            String[] arrstring = new String[]{string, object};
            object = contentResolver.query(uri, new String[]{"_id", "address"}, string2, arrstring, null);
            if (object != null) {
                if (!object.moveToFirst()) break block8;
                l = object.getLong(0);
                object.getString(1);
                Log.w((String)"SmsReceiverService", (String)("receive duplicated message from , msg id=" + l));
                return true;
            }
        }
        return false;
        finally {
            object.close();
        }
    }

    private void messageFailedToSend(Uri uri, int n, int n2) {
        Log.v((String)"SmsReceiverService", (String)("messageFailedToSend msg failed uri: " + (Object)uri + " error: " + n));
        Telephony.Sms.moveMessageToFolder((Context)this, (Uri)uri, (int)5, (int)n);
        MessagingNotification.notifySendFailed(this.getApplicationContext(), true, n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean moveMessageToFolderIfNotFailed(Context context, Uri uri, int n, int n2) {
        boolean bl;
        if (uri == null) {
            return false;
        }
        boolean bl2 = false;
        boolean bl3 = bl = false;
        boolean bl4 = bl2;
        switch (n) {
            default: {
                return false;
            }
            case 2: 
            case 4: {
                bl3 = true;
                bl4 = bl2;
                break;
            }
            case 5: 
            case 6: {
                bl4 = true;
                bl3 = bl;
            }
            case 1: 
            case 3: 
        }
        ContentValues contentValues = new ContentValues(3);
        contentValues.put("type", Integer.valueOf((int)n));
        if (bl4) {
            contentValues.put("read", Integer.valueOf((int)0));
        } else if (bl3) {
            contentValues.put("read", Integer.valueOf((int)1));
        }
        contentValues.put("error_code", Integer.valueOf((int)n2));
        if (1 != SqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)uri, (ContentValues)contentValues, (String)"type!=5", (String[])null)) return false;
        return true;
    }

    private int moveOutboxMessagesToFailedBox() {
        ContentValues contentValues = new ContentValues(3);
        contentValues.put("type", Integer.valueOf((int)5));
        contentValues.put("error_code", Integer.valueOf((int)1));
        contentValues.put("read", Integer.valueOf((int)0));
        int n = SqliteWrapper.update((Context)this.getApplicationContext(), (ContentResolver)this.getContentResolver(), (Uri)Telephony.Sms.Outbox.CONTENT_URI, (ContentValues)contentValues, (String)"type = 4", (String[])null);
        if (!Log.isLoggable((String)"Mms:transaction", (int)2)) {
            return n;
        }
        Log.v((String)"SmsReceiverService", (String)("moveOutboxMessagesToFailedBox messageCount: " + n));
        return n;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void registerForServiceStateChanged(final int n) {
        if (!MSimUtils.isMSim()) {
            Context context = this.getApplicationContext();
            this.unRegisterForServiceStateChanged();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SERVICE_STATE");
            Log.v((String)"SmsReceiverService", (String)"register for service state");
            context.registerReceiver((BroadcastReceiver)SmsReceiver.getInstance(), intentFilter);
            return;
        }
        if (MSimUtils.isAndroid50()) {
            this.mToastHandler.post(new Runnable(){

                @Override
                public void run() {
                    MmsSystemEventReceiver.getInstance().registerForServiceStateChanged(n);
                }
            });
        } else {
            MmsSystemEventReceiver.getInstance().registerForServiceStateChanged(n);
        }
        Log.v((String)"SmsReceiverService", (String)"register for service state");
    }

    public static String replaceFormFeeds(String string) {
        return string.replace('\f', '\n');
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private Uri replaceMessage(Context context, SmsMessage[] uri, int n, long l, int n2) {
        Object object;
        block7 : {
            StringBuilder stringBuilder;
            object = uri[0];
            ContentValues contentValues = this.extractContentValues((SmsMessage)object);
            contentValues.put("error_code", Integer.valueOf((int)n));
            int n3 = uri.length;
            if (n3 == 1) {
                contentValues.put("body", SmsReceiverService.replaceFormFeeds(object.getDisplayMessageBody()));
            } else {
                stringBuilder = new StringBuilder();
                for (int i = 0; i < n3; ++i) {
                    object = uri[i];
                    if (object.mWrappedSmsMessage == null) continue;
                    stringBuilder.append(object.getDisplayMessageBody());
                }
                contentValues.put("body", SmsReceiverService.replaceFormFeeds(stringBuilder.toString()));
            }
            stringBuilder = context.getContentResolver();
            String string = object.getOriginatingAddress();
            object = Integer.toString((int)object.getProtocolIdentifier());
            String string2 = Long.toString((long)l);
            String[] arrstring = new String[]{string, object, string2};
            object = SqliteWrapper.query((Context)context, (ContentResolver)stringBuilder, (Uri)Telephony.Sms.Inbox.CONTENT_URI, (String[])REPLACE_PROJECTION, (String)"address = ? AND protocol = ? AND sim_id = ?", (String[])arrstring, (String)null);
            if (object != null) {
                if (!object.moveToFirst()) break block7;
                l = object.getLong(0);
                uri = ContentUris.withAppendedId((Uri)Telephony.Sms.CONTENT_URI, (long)l);
                SqliteWrapper.update((Context)context, (ContentResolver)stringBuilder, (Uri)uri, (ContentValues)contentValues, (String)null, (String[])null);
                return uri;
            }
        }
        return this.storeMessage(context, (SmsMessage[])uri, n, l, n2);
        finally {
            object.close();
        }
    }

    /*
     * Exception decompiling
     */
    private void sendFirstQueuedMessage(boolean var1_1, int var2_2) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [3[TRYBLOCK]], but top level block is 7[TRYBLOCK]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    private void showToastWhenOffline(final int n) {
        this.mToastHandler.post(new Runnable(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void run() {
                String string;
                SmsReceiverService smsReceiverService = SmsReceiverService.this;
                String string2 = string = "";
                if (MSimUtils.isMSimInserted()) {
                    if (n == 0) {
                        string2 = smsReceiverService.getString(2131362235);
                    } else {
                        string2 = string;
                        if (n == 1) {
                            string2 = smsReceiverService.getString(2131362236);
                        }
                    }
                }
                string = string2;
                if (!TextUtils.isEmpty((CharSequence)string2)) {
                    string = string2 + " ";
                }
                Toast.makeText((Context)smsReceiverService, (CharSequence)(string + smsReceiverService.getString(2131362000)), (int)0).show();
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    private Uri storeMessage(Context context, SmsMessage[] object, int n, long l, int n2) {
        Object object2 = object[0];
        ContentValues contentValues = this.extractContentValues((SmsMessage)object2);
        contentValues.put("error_code", Integer.valueOf((int)n));
        String string = object.length == 1 ? SmsReceiverService.replaceFormFeeds(object2.getDisplayMessageBody()) : SmsReceiverService.replaceFormFeeds(this.buildMessageString((SmsMessage[])object));
        contentValues.put("body", string);
        Long l2 = contentValues.getAsLong("thread_id");
        object = contentValues.getAsString("address");
        if (!TextUtils.isEmpty((CharSequence)object)) {
            Contact contact = Contact.get((String)object);
            if (contact != null) {
                object = contact.getNumber();
            }
        } else {
            object = this.getString(2131362003);
            contentValues.put("address", (String)object);
        }
        contentValues.put("sim_id", Long.valueOf((long)l));
        if (MSimUtils.getSlotIdBySimInfoId(l) < 0) {
            Log.e((String)"SmsReceiverService", (String)("storeMessage: cannot get slotId for simId " + l));
            return null;
        }
        if ((l2 == null || l2 == 0) && object != null) {
            contentValues.put("thread_id", Long.valueOf((long)Telephony.Threads.getOrCreateThreadId((Context)context, (String)object)));
        }
        if (n2 < 3 && (MiuiGalleryUtils.handleAsAlbumShareInvitation(context, null, string, "mms") || MessageUtils.handleFileShareMessage(context, object2.getDisplayOriginatingAddress(), string))) {
            contentValues.put("block_type", Integer.valueOf((int)0));
            contentValues.put("read", Integer.valueOf((int)1));
            MessageUtils.insertUniqueMessage(context, contentValues);
            return null;
        }
        contentValues.put("read", Integer.valueOf((int)0));
        contentValues.put("block_type", Integer.valueOf((int)n2));
        return MessageUtils.insertUniqueMessage(context, contentValues);
    }

    private static String translateResultCode(int n) {
        switch (n) {
            default: {
                return "Unknown error code";
            }
            case -1: {
                return "Activity.RESULT_OK";
            }
            case 1: {
                return "SmsManager.RESULT_ERROR_GENERIC_FAILURE";
            }
            case 2: {
                return "SmsManager.RESULT_ERROR_RADIO_OFF";
            }
            case 3: {
                return "SmsManager.RESULT_ERROR_NULL_PDU";
            }
            case 4: {
                return "SmsManager.RESULT_ERROR_NO_SERVICE";
            }
            case 5: {
                return "SmsManager.RESULT_ERROR_LIMIT_EXCEEDED";
            }
            case 6: 
        }
        return "SmsManager.RESULT_ERROR_FDN_CHECK_FAILURE";
    }

    private void unRegisterForServiceStateChanged() {
        Log.v((String)"SmsReceiverService", (String)"un register for service state");
        try {
            this.getApplicationContext().unregisterReceiver((BroadcastReceiver)SmsReceiver.getInstance());
            return;
        }
        catch (IllegalArgumentException var1_1) {
            Log.e((String)"SmsReceiverService", (String)"allow un-matched register-unregister calls");
            return;
        }
    }

    private void updateSimId(long l, long l2, long l3) {
        if (MSimUtils.isMSim() && l2 != l3) {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put("sim_id", Long.valueOf((long)l3));
            SqliteWrapper.update((Context)this, (ContentResolver)this.getContentResolver(), (Uri)Telephony.Sms.CONTENT_URI, (ContentValues)contentValues, (String)("_id=" + l), (String[])null);
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        HandlerThread handlerThread = new HandlerThread("SmsReceiverService", 10);
        handlerThread.start();
        this.mServiceLooper = handlerThread.getLooper();
        this.mServiceHandler = new ServiceHandler(this.mServiceLooper);
        int n = MSimUtils.getMultiSimCount();
        this.mSending = new boolean[n];
        for (int i = 0; i < n; ++i) {
            this.mSending[i] = false;
        }
    }

    public void onDestroy() {
        this.mServiceLooper.quit();
    }

    public int onStartCommand(Intent intent, int n, int n2) {
        n = 0;
        if (intent != null) {
            n = intent.getIntExtra("result", 0);
        }
        this.mResultCode = n;
        if (this.mResultCode != 0) {
            Log.v((String)"SmsReceiverService", (String)("onStart: #" + n2 + " mResultCode: " + this.mResultCode + " = " + SmsReceiverService.translateResultCode(this.mResultCode)));
        }
        Message message = this.mServiceHandler.obtainMessage();
        message.arg1 = n2;
        message.obj = intent;
        this.mServiceHandler.sendMessage(message);
        this.startService(new Intent((Context)this, (Class)SmsReportService.class));
        return 2;
    }

    private final class ServiceHandler
    extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        /*
         * Enabled aggressive block sorting
         */
        public void handleMessage(Message message) {
            int n = message.arg1;
            message = (Intent)message.obj;
            if (message != null) {
                String string = message.getAction();
                int n2 = message.getIntExtra("errorCode", 0);
                if (Log.isLoggable((String)"Mms:transaction", (int)2)) {
                    Log.v((String)"SmsReceiverService", (String)("handleMessage action: " + string + " error: " + n2));
                }
                if ("com.android.mms.transaction.MESSAGE_SENT".equals((Object)message.getAction())) {
                    SmsReceiverService.this.handleSmsSent((Intent)message, n2);
                } else if ("android.provider.Telephony.SMS_DELIVER".equals((Object)string)) {
                    SmsReceiverService.this.handleSmsReceived((Intent)message, n2);
                } else if ("android.intent.action.BOOT_COMPLETED".equals((Object)string)) {
                    SmsReceiverService.this.handleBootCompleted();
                } else if ("android.intent.action.SERVICE_STATE".equals((Object)string)) {
                    SmsReceiverService.this.handleServiceStateChanged((Intent)message);
                } else if ("com.android.mms.transaction.SEND_MESSAGE".equals((Object)string)) {
                    SmsReceiverService.this.handleSendMessage((Intent)message);
                }
            }
            SmsReceiver.finishStartingService(SmsReceiverService.this, n);
        }
    }

}

