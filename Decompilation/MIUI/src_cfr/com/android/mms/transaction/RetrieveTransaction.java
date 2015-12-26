/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.ContentObserver
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Mms$Inbox
 *  android.provider.Telephony$MmsSms
 *  android.text.TextUtils
 *  android.util.Log
 *  com.google.android.mms.InvalidHeaderValueException
 *  com.google.android.mms.MmsException
 *  com.google.android.mms.pdu.AcknowledgeInd
 *  com.google.android.mms.pdu.EncodedStringValue
 *  com.google.android.mms.pdu.GenericPdu
 *  com.google.android.mms.pdu.MiuiPduParser
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  com.google.android.mms.pdu.PduComposer
 *  com.google.android.mms.pdu.RetrieveConf
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  miui.os.Build
 *  miui.telephony.TelephonyManager
 */
package com.android.mms.transaction;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.LogTag;
import com.android.mms.MmsConfig;
import com.android.mms.transaction.ProgressReceiver;
import com.android.mms.transaction.Transaction;
import com.android.mms.transaction.TransactionSettings;
import com.android.mms.transaction.TransactionState;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.DownloadManager;
import com.android.mms.util.MSimUtils;
import com.google.android.mms.InvalidHeaderValueException;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.AcknowledgeInd;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.MiuiPduParser;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.PduComposer;
import com.google.android.mms.pdu.RetrieveConf;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.IOException;
import miui.os.Build;
import miui.telephony.TelephonyManager;

public class RetrieveTransaction
extends Transaction
implements Runnable {
    static final String[] PROJECTION = new String[]{"ct_l", "locked", "m_size"};
    private final String mContentLocation;
    private boolean mLocked;
    private long mMessageSize;
    private final Uri mOldUri;
    private final Uri mUri;

    public RetrieveTransaction(Context object, TransactionSettings transactionSettings, String string) throws MmsException {
        super((Context)object, transactionSettings);
        if (string.startsWith("content://")) {
            this.mOldUri = Uri.parse((String)string);
            this.mUri = this.mOldUri.buildUpon().appendQueryParameter("blocked_flag", "2").build();
            this.mTransactionState.setContentUri(this.mUri);
            this.mContentLocation = object = this.getContentLocation((Context)object, this.mUri);
            this.mId = object;
            return;
        }
        throw new IllegalArgumentException("Initializing from X-Mms-Content-Location is abandoned!");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private String getContentLocation(Context context, Uri object) throws MmsException {
        context = SqliteWrapper.query((Context)context, (ContentResolver)context.getContentResolver(), (Uri)object, (String[])PROJECTION, (String)null, (String[])null, (String)null);
        this.mLocked = false;
        if (context == null) throw new MmsException("Cannot get X-Mms-Content-Location from: " + object);
        {
            try {
                if (context.getCount() != 1 || !context.moveToFirst()) throw new MmsException("Cannot get X-Mms-Content-Location from: " + object);
                {
                    boolean bl = context.getInt(1) == 1;
                    this.mLocked = bl;
                    this.mMessageSize = context.getLong(2);
                    object = context.getString(0);
                    return object;
                }
            }
            finally {
                context.close();
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean isDuplicateMessage(Context context, RetrieveConf object) {
        if ((object = (Object)object.getMessageId()) == null) return false;
        {
            object = new String((byte[])object);
            String string = "(m_id = ? AND m_type = ?) AND sim_id=" + this.mSimId;
            if ((context = SqliteWrapper.query((Context)context, (ContentResolver)context.getContentResolver(), (Uri)Telephony.Mms.CONTENT_URI, (String[])new String[]{"_id"}, (String)string, (String[])new String[]{object, String.valueOf((int)132)}, (String)null)) == null) return false;
            {
                try {
                    int n = context.getCount();
                    if (n <= 0) return false;
                    return true;
                }
                finally {
                    context.close();
                }
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void sendAcknowledgeInd(RetrieveConf object) throws MmsException, IOException {
        if ((object = (Object)object.getTransactionId()) != null) {
            String string;
            AcknowledgeInd acknowledgeInd = new AcknowledgeInd(18, (byte[])object);
            int n = MSimUtils.getSlotIdBySimInfoId(this.mSimId);
            object = string = TelephonyManager.getDefault().getLine1NumberForSlot(n);
            if (TextUtils.isEmpty((CharSequence)string)) {
                Log.e((String)"RetrieveTransaction", (String)"lineNumber is empty");
                object = "";
            }
            acknowledgeInd.setFrom(new EncodedStringValue((String)object));
            if (Build.IS_CM_CUSTOMIZATION_TEST) {
                n = MessageUtils.getSendDeliverReportAllowed(this.mContext, this.mSimId) ? 128 : 129;
                try {
                    acknowledgeInd.setReportAllowed(n);
                }
                catch (InvalidHeaderValueException var1_2) {
                    Log.e((String)"RetrieveTransaction", (String)"acknowledgeInd.setReportAllowed Failed !!");
                }
            }
            if (!MmsConfig.getNotifyWapMMSC()) {
                this.sendPdu(new PduComposer(this.mContext, (GenericPdu)acknowledgeInd).make(), null);
                return;
            }
            this.sendPdu(new PduComposer(this.mContext, (GenericPdu)acknowledgeInd).make(), this.mContentLocation, null);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private static void updateContentLocation(Context context, Uri uri, String string, boolean bl) {
        ContentValues contentValues = new ContentValues(2);
        contentValues.put("ct_l", string);
        int n = bl ? 1 : 0;
        contentValues.put("locked", Integer.valueOf((int)n));
        SqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)uri, (ContentValues)contentValues, (String)null, (String[])null);
    }

    @Override
    public int getType() {
        return 1;
    }

    @Override
    public void onEnqueue() {
        DownloadManager.getInstance().markState(this.mUri, 129, this.mSimId);
        this.mContext.getContentResolver().notifyChange(Telephony.MmsSms.CONTENT_CONVERSATIONS_URI, null);
    }

    @Override
    public void process() {
        new Thread((Runnable)this).start();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void run() {
        sCurrentTransactionMsgId = Long.valueOf((String)this.mUri.getLastPathSegment());
        sCurrentTransactionProgress = 0;
        try {
            RetrieveConf retrieveConf;
            try {
                retrieveConf = (RetrieveConf)new MiuiPduParser(this.getPdu(this.mContentLocation, new ProgressReceiver(){

                    @Override
                    public void onProgress(long l, long l2) {
                        if (l < 0) {
                            return;
                        }
                        long l3 = l2;
                        if (l2 <= 0) {
                            l3 = RetrieveTransaction.this.mMessageSize;
                        }
                        l2 = l3;
                        if (l3 <= 0) {
                            l2 = MmsConfig.getMaxMessageSize();
                        }
                        l3 = l;
                        if (l > l2) {
                            l3 = l2;
                        }
                        Transaction.sCurrentTransactionProgress = (int)(100 * l3 / l2);
                        RetrieveTransaction.this.mContext.getContentResolver().notifyChange(Telephony.MmsSms.CONTENT_CONVERSATIONS_URI, null);
                    }
                })).parse();
                if (retrieveConf == null) {
                    throw new MmsException("Invalid M-Retrieve.conf PDU.");
                }
            }
            catch (Throwable var2_2) {
                MyLog.e(LogTag.logFormat("Exception: %s", Log.getStackTraceString((Throwable)var2_2)));
                return;
            }
            if (this.isDuplicateMessage(this.mContext, retrieveConf)) {
                SqliteWrapper.delete((Context)this.mContext, (ContentResolver)this.mContext.getContentResolver(), (Uri)this.mUri, (String)null, (String[])null);
                this.mTransactionState.setState(2);
                this.mTransactionState.setContentUri(this.mUri);
            } else {
                Uri uri;
                MiuiPduPersister miuiPduPersister = MiuiPduPersister.getPduPersister((Context)this.mContext);
                int n = MessageUtils.getMmsBlockTypeByUri(this.mContext, this.mUri);
                if (n > 1) {
                    uri = this.mOldUri.buildUpon().appendQueryParameter("blocked_flag", "1").build();
                    miuiPduPersister = miuiPduPersister.persist((GenericPdu)retrieveConf, Telephony.Mms.Inbox.CONTENT_URI, uri, -1, n);
                } else {
                    miuiPduPersister = miuiPduPersister.persist((GenericPdu)retrieveConf, Telephony.Mms.Inbox.CONTENT_URI, this.mUri, -1, n);
                }
                uri = new ContentValues();
                uri.put("sim_id", Long.valueOf((long)this.mSimId));
                uri.put("m_size", Long.valueOf((long)this.mMessageSize));
                SqliteWrapper.update((Context)this.mContext, (ContentResolver)this.mContext.getContentResolver(), (Uri)miuiPduPersister, (ContentValues)uri, (String)null, (String[])null);
                this.mTransactionState.setState(1);
                this.mTransactionState.setContentUri((Uri)miuiPduPersister);
                RetrieveTransaction.updateContentLocation(this.mContext, (Uri)miuiPduPersister, this.mContentLocation, this.mLocked);
            }
            this.sendAcknowledgeInd(retrieveConf);
            return;
        }
        finally {
            if (this.mTransactionState.getState() != 1) {
                this.mTransactionState.setState(2);
                this.mTransactionState.setContentUri(this.mUri);
                MyLog.e(LogTag.logFormat("Retrieval failed.", new Object[0]));
            }
            this.notifyObservers();
            return;
        }
    }

}

