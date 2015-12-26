/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.ComponentName
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.database.DatabaseUtils
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.AsyncTask
 *  android.os.PowerManager
 *  android.os.PowerManager$WakeLock
 *  android.preference.PreferenceManager
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Mms$Inbox
 *  android.text.TextUtils
 *  com.google.android.mms.MmsException
 *  com.google.android.mms.pdu.DeliveryInd
 *  com.google.android.mms.pdu.GenericPdu
 *  com.google.android.mms.pdu.MiuiPduParser
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  com.google.android.mms.pdu.NotificationInd
 *  com.google.android.mms.pdu.ReadOrigInd
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  miui.os.Build
 *  miui.provider.ExtraTelephony
 */
package com.android.mms.transaction;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.DatabaseUtils;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.provider.Telephony;
import android.text.TextUtils;
import com.android.mms.MmsConfig;
import com.android.mms.jwap_port.WBXMLDecoder;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.transaction.TransactionService;
import com.android.mms.util.DownloadManager;
import com.android.mms.util.MSimUtils;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.DeliveryInd;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.MiuiPduParser;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.NotificationInd;
import com.google.android.mms.pdu.ReadOrigInd;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import miui.os.Build;
import miui.provider.ExtraTelephony;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class PushReceiver
extends BroadcastReceiver {
    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static long[] findThreadId(Context context, GenericPdu object, int n) {
        object = n == 134 ? new String(((DeliveryInd)object).getMessageId()) : new String(((ReadOrigInd)object).getMessageId());
        CharSequence charSequence = new StringBuilder(40);
        charSequence.append("m_id");
        charSequence.append('=');
        charSequence.append(DatabaseUtils.sqlEscapeString((String)object));
        charSequence.append(" AND ");
        charSequence.append("m_type");
        charSequence.append('=');
        charSequence.append(128);
        object = new long[2];
        object[0] = -1;
        object[1] = -1;
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = Telephony.Mms.CONTENT_URI;
        charSequence = charSequence.toString();
        context = SqliteWrapper.query((Context)context, (ContentResolver)contentResolver, (Uri)uri, (String[])new String[]{"thread_id", "_id"}, (String)charSequence, (String[])null, (String)null);
        if (context == null) return object;
        try {
            if (context.getCount() != 1) return object;
            if (!context.moveToFirst()) return object;
            object[0] = context.getLong(0);
            object[1] = context.getLong(1);
            return object;
        }
        finally {
            context.close();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean isDuplicateNotification(Context context, NotificationInd object) {
        block2 : {
            if ((object = (Object)object.getContentLocation()) == null) return false;
            object = new String((byte[])object);
            if ((context = SqliteWrapper.query((Context)context, (ContentResolver)context.getContentResolver(), (Uri)Telephony.Mms.CONTENT_URI, (String[])new String[]{"_id"}, (String)"ct_l = ?", (String[])new String[]{object}, (String)null)) == null) return false;
            try {
                int n = context.getCount();
                if (n <= 0) break block2;
            }
            catch (Throwable throwable) {
                context.close();
                throw throwable;
            }
            context.close();
            return true;
        }
        context.close();
        return false;
    }

    public void onReceive(Context context, Intent intent) {
        this.processReceive(context, intent);
    }

    protected boolean processReceive(Context context, Intent intent) {
        if ("android.provider.Telephony.WAP_PUSH_DELIVER".equals((Object)intent.getAction())) {
            String string = intent.getType();
            if ("application/vnd.wap.mms-message".equals((Object)string)) {
                ((PowerManager)context.getSystemService("power")).newWakeLock(1, "MMS PushReceiver").acquire(5000);
                new MmsPushTask(context).execute((Object[])new Intent[]{intent});
                return true;
            }
            if (("application/vnd.wap.sic".equals((Object)string) || "application/vnd.wap.slc".equals((Object)string)) && PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean("pref_key_allow_si_sl_push", true)) {
                new SiSlPushTask(context).execute((Object[])new Intent[]{intent});
                return true;
            }
        }
        return false;
    }

    private class MmsPushTask
    extends AsyncTask<Intent, Void, Void> {
        private Context mContext;

        public MmsPushTask(Context context) {
            this.mContext = context;
        }

        /*
         * Enabled aggressive block sorting
         */
        private void updateDeliveryStatus(long l, GenericPdu genericPdu) {
            if (l < 0) {
                MyLog.e("PushReceiver", "update delivery status is failed for msgId < 0");
                return;
            } else {
                Uri uri = Uri.parse((String)((Object)Telephony.Mms.CONTENT_URI + "/" + l));
                ContentValues contentValues = new ContentValues();
                contentValues.put("st", Integer.valueOf((int)((DeliveryInd)genericPdu).getStatus()));
                if (SqliteWrapper.update((Context)this.mContext, (ContentResolver)this.mContext.getContentResolver(), (Uri)uri, (ContentValues)contentValues, (String)null, (String[])null) > 0) return;
                {
                    MyLog.e("PushReceiver", "update delivery status is failed for msgId is " + l);
                    return;
                }
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        protected /* varargs */ Void doInBackground(Intent ... genericPdu) {
            MiuiPduPersister miuiPduPersister;
            ContentResolver contentResolver;
            int n;
            Object object = genericPdu[0];
            genericPdu = new MiuiPduParser(object.getByteArrayExtra("data")).parse();
            if (genericPdu == null) {
                MyLog.e("PushReceiver", "Invalid PUSH data");
                return null;
            } else {
                miuiPduPersister = MiuiPduPersister.getPduPersister((Context)this.mContext);
                contentResolver = this.mContext.getContentResolver();
                int n2 = genericPdu.getMessageType();
                switch (n2) {
                    default: {
                        MyLog.e("PushReceiver", "Received unrecognized PDU.");
                        return null;
                    }
                    case 134: 
                    case 136: {
                        object = PushReceiver.findThreadId(this.mContext, genericPdu, n2);
                        Object object2 = object[0];
                        if (object2 == -1) {
                            MyLog.e("PushReceiver", "delivery or read orig ind threadId == -1");
                            return null;
                        }
                        if (miuiPduPersister.persist(genericPdu, Telephony.Mms.Inbox.CONTENT_URI, null, (long)object2) == null) {
                            MyLog.e("PushReceiver", "delivery or read orig ind gen uri is null");
                        }
                        if (n2 != 134) return null;
                        try {
                            this.updateDeliveryStatus((long)object[1], genericPdu);
                            return null;
                        }
                        catch (MmsException var1_2) {
                            MyLog.e("PushReceiver", "Failed to save the data from PUSH: type=" + n2, (Throwable)var1_2);
                            return null;
                        }
                        catch (RuntimeException var1_3) {
                            MyLog.e("PushReceiver", "Unexpected RuntimeException.", var1_3);
                            return null;
                        }
                    }
                    case 130: {
                        byte[] arrby;
                        NotificationInd notificationInd = (NotificationInd)genericPdu;
                        if (MmsConfig.getTransIdEnabled() && 61 == (arrby = notificationInd.getContentLocation())[arrby.length - 1]) {
                            byte[] arrby2 = notificationInd.getTransactionId();
                            byte[] arrby3 = new byte[arrby.length + arrby2.length];
                            System.arraycopy((Object)arrby, (int)0, (Object)arrby3, (int)0, (int)arrby.length);
                            System.arraycopy((Object)arrby2, (int)0, (Object)arrby3, (int)arrby.length, (int)arrby2.length);
                            notificationInd.setContentLocation(arrby3);
                        }
                        if (PushReceiver.isDuplicateNotification(this.mContext, notificationInd)) return null;
                        n = object.getIntExtra("blockType", 0);
                        genericPdu = miuiPduPersister.persist(genericPdu, Telephony.Mms.Inbox.CONTENT_URI, null, -1, n);
                        genericPdu = n > 1 ? genericPdu.buildUpon().appendQueryParameter("blocked_flag", "1").build() : genericPdu.buildUpon().appendQueryParameter("blocked_flag", "0").build();
                    }
                }
            }
            miuiPduPersister = new ContentValues();
            n = MSimUtils.getSlotIdFromIntent((Intent)object);
            long l = MSimUtils.getSimIdBySlotId(n);
            if (l < 0) {
                MyLog.e("PushReceiver", "Cannot get sim id for slot " + n);
                return null;
            }
            miuiPduPersister.put("sim_id", Long.valueOf((long)l));
            SqliteWrapper.update((Context)this.mContext, (ContentResolver)contentResolver, (Uri)genericPdu, (ContentValues)miuiPduPersister, (String)null, (String[])null);
            if (Build.IS_CM_CUSTOMIZATION_TEST) {
                DownloadManager.getInstance().setOutOfMemory(false);
            }
            object = new Intent(this.mContext, (Class)TransactionService.class);
            object.putExtra("uri", genericPdu.toString());
            object.putExtra("type", 0);
            this.mContext.startService((Intent)object);
            return null;
        }
    }

    private class SiSlPushTask
    extends AsyncTask<Intent, Void, Void> {
        private String mAddress;
        private StringBuilder mBody;
        private Context mContext;

        public SiSlPushTask(Context context) {
            this.mContext = context;
        }

        /*
         * Enabled aggressive block sorting
         */
        private void appendNewLine(String string) {
            if (this.mBody.length() > 0) {
                this.mBody.append('\n');
            } else {
                this.mBody.append(this.mContext.getResources().getString(2131362146));
            }
            this.mBody.append(string);
        }

        private void storeWapPushMessage(int n, long l) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("address", this.mAddress);
            contentValues.put("protocol", Integer.valueOf((int)0));
            contentValues.put("read", Integer.valueOf((int)0));
            contentValues.put("status", Integer.valueOf((int)-1));
            contentValues.put("body", this.mBody.toString());
            contentValues.put("block_type", Integer.valueOf((int)n));
            contentValues.put("sim_id", Long.valueOf((long)l));
            this.mContext.getContentResolver().insert(Uri.parse((String)"content://sms/inbox"), contentValues);
        }

        private void traverse(Node object) {
            for (Node node = object.getFirstChild(); node != null; node = node.getNextSibling()) {
                this.traverse(node);
            }
            if (object.getNodeType() == 3) {
                this.appendNewLine(object.getNodeValue());
            }
            if ((object = object.getAttributes()) != null && (object = object.getNamedItem("href")) != null && (object = object.getNodeValue()) != null && object.length() > 0) {
                this.appendNewLine((String)object);
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        protected /* varargs */ Void doInBackground(Intent ... object) {
            MyLog.d("PushReceiver", "ReceiveWapPushTask doInBackground");
            object = object[0];
            byte[] arrby = object.getByteArrayExtra("data");
            this.mAddress = object.getStringExtra("address");
            int n = MSimUtils.getSlotIdFromIntent((Intent)object);
            long l = MSimUtils.getSimIdBySlotId(n);
            if (l < 0) {
                MyLog.e("PushReceiver", "Cannot get sim id for slot " + n);
                return null;
            } else {
                if (TextUtils.isEmpty((CharSequence)this.mAddress)) {
                    this.mAddress = this.mContext.getResources().getString(2131362146);
                }
                if ((object = new WBXMLDecoder(this.mContext).decode(new ByteArrayInputStream(arrby))) == null) return null;
                {
                    this.mBody = new StringBuilder();
                    this.traverse((Node)object);
                    if (this.mBody.length() <= 0) return null;
                    {
                        object = this.mBody.toString();
                        n = ExtraTelephony.getSmsBlockType((Context)this.mContext, (String)this.mAddress, (String)object, (int)n);
                        this.storeWapPushMessage(n, l);
                        if (n > 1) return null;
                        {
                            MessagingNotification.blockingUpdateNewMessageIndicator(this.mContext, true, false);
                            return null;
                        }
                    }
                }
            }
        }
    }

}

