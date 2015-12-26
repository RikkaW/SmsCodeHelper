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
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Mms$Sent
 *  android.provider.Telephony$MmsSms
 *  android.text.TextUtils
 *  android.util.Log
 *  com.google.android.mms.pdu.EncodedStringValue
 *  com.google.android.mms.pdu.GenericPdu
 *  com.google.android.mms.pdu.MiuiPduParser
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  com.google.android.mms.pdu.PduComposer
 *  com.google.android.mms.pdu.SendConf
 *  com.google.android.mms.pdu.SendReq
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.Arrays
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
import com.android.mms.transaction.ProgressReceiver;
import com.android.mms.transaction.Transaction;
import com.android.mms.transaction.TransactionSettings;
import com.android.mms.transaction.TransactionState;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.RateController;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.MiuiPduParser;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.PduComposer;
import com.google.android.mms.pdu.SendConf;
import com.google.android.mms.pdu.SendReq;
import com.xiaomi.mms.utils.logger.MyLog;
import java.util.Arrays;
import miui.telephony.TelephonyManager;

public class SendTransaction
extends Transaction
implements Runnable {
    private final Uri mSendReqURI;
    private Thread mThread;

    public SendTransaction(Context context, TransactionSettings transactionSettings, String string) {
        super(context, transactionSettings);
        this.mSendReqURI = Uri.parse((String)string);
        this.mTransactionState.setContentUri(this.mSendReqURI);
        this.mId = string;
    }

    @Override
    public int getType() {
        return 2;
    }

    @Override
    public void process() {
        this.mThread = new Thread((Runnable)this);
        this.mThread.start();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @Override
    public void run() {
        SendTransaction.sCurrentTransactionMsgId = Long.valueOf((String)this.mSendReqURI.getLastPathSegment());
        SendTransaction.sCurrentTransactionProgress = 0;
        try {
            var4_1 = RateController.getInstance();
            if (var4_1.isLimitSurpassed() && !var4_1.isAllowedByUser()) {
                MyLog.e(LogTag.logFormat("Sending rate limit surpassed.", new Object[0]));
            }
            var6_4 = MiuiPduPersister.getPduPersister((Context)this.mContext);
            var7_5 = (SendReq)var6_4.load(this.mSendReqURI);
            var2_6 = System.currentTimeMillis();
            var7_5.setDate(var2_6 / 1000);
            var4_1 = new ContentValues(1);
            var4_1.put("date_full", Long.valueOf((long)var2_6));
            SqliteWrapper.update((Context)this.mContext, (ContentResolver)this.mContext.getContentResolver(), (Uri)this.mSendReqURI, (ContentValues)var4_1, (String)null, (String[])null);
            var1_7 = MSimUtils.getSlotIdBySimInfoId(this.mSimId);
            var4_1 = var5_8 = TelephonyManager.getDefault().getLine1NumberForSlot(var1_7);
            if (TextUtils.isEmpty((CharSequence)var5_8)) {
                Log.e((String)"SendTransaction", (String)"lineNumber is empty");
                var4_1 = "";
            }
            var7_5.setFrom(new EncodedStringValue((String)var4_1));
            var4_1 = (SendConf)new MiuiPduParser(this.sendPdu(new PduComposer(this.mContext, (GenericPdu)var7_5).make(), new ProgressReceiver(){

                /*
                 * Enabled aggressive block sorting
                 */
                @Override
                public void onProgress(long l, long l2) {
                    if (l < 0 || l2 <= 0) {
                        return;
                    }
                    long l3 = l;
                    if (l > l2) {
                        l3 = l2;
                    }
                    Transaction.sCurrentTransactionProgress = (int)(100 * l3 / l2);
                    SendTransaction.this.mContext.getContentResolver().notifyChange(Telephony.MmsSms.CONTENT_CONVERSATIONS_URI, null);
                }
            })).parse();
            if (var4_1 == null) {
                MyLog.e(LogTag.logFormat("No M-Send.conf received.", new Object[0]));
            }
            var5_9 = var7_5.getTransactionId();
            var7_5 = var4_1.getTransactionId();
            if (!Arrays.equals((byte[])var5_9, (byte[])var7_5)) {
                MyLog.e(LogTag.logFormat("Inconsistent Transaction-ID: req=%d, conf=%d", new Object[]{var5_9, var7_5}));
            }
            var5_10 = new ContentValues(2);
            var1_7 = var4_1.getResponseStatus();
            var5_10.put("resp_st", Integer.valueOf((int)var1_7));
            var5_10.put("sim_id", Long.valueOf((long)this.mSimId));
            if (var1_7 != 128) {
                SqliteWrapper.update((Context)this.mContext, (ContentResolver)this.mContext.getContentResolver(), (Uri)this.mSendReqURI, (ContentValues)var5_10, (String)null, (String[])null);
                MyLog.e(LogTag.logFormat("Server returned an error code: %d", new Object[]{var1_7}));
            }
            var5_10.put("m_id", MiuiPduPersister.toIsoString((byte[])var4_1.getMessageId()));
            SqliteWrapper.update((Context)this.mContext, (ContentResolver)this.mContext.getContentResolver(), (Uri)this.mSendReqURI, (ContentValues)var5_10, (String)null, (String[])null);
            MyLog.d(LogTag.logFormat("Moved to sent box: %s", new Object[]{this.mSendReqURI}));
            var4_1 = var6_4.move(this.mSendReqURI, Telephony.Mms.Sent.CONTENT_URI);
            this.mTransactionState.setState(1);
            this.mTransactionState.setContentUri((Uri)var4_1);
        }
        catch (Throwable var4_2) {
            MyLog.e(LogTag.logFormat("Exception: %s", new Object[]{Log.getStackTraceString((Throwable)var4_2)}));
            return;
        }
        finally {
            if (this.mTransactionState.getState() == 1) ** GOTO lbl-1000
            this.mTransactionState.setState(2);
            this.mTransactionState.setContentUri(this.mSendReqURI);
            MyLog.e(LogTag.logFormat("Delivery failed.", new Object[0]));
lbl-1000: // 1 sources:
            {
                this.notifyObservers();
            }
        }
    }

}

