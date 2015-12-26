/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.database.ContentObserver
 *  android.net.Uri
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Mms$Inbox
 *  android.provider.Telephony$MmsSms
 *  android.util.Log
 *  com.google.android.mms.InvalidHeaderValueException
 *  com.google.android.mms.MmsException
 *  com.google.android.mms.pdu.GenericPdu
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  com.google.android.mms.pdu.NotificationInd
 *  com.google.android.mms.pdu.NotifyRespInd
 *  com.google.android.mms.pdu.PduComposer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  miui.os.Build
 */
package com.android.mms.transaction;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.provider.Telephony;
import android.util.Log;
import com.android.mms.LogTag;
import com.android.mms.MmsConfig;
import com.android.mms.transaction.ProgressReceiver;
import com.android.mms.transaction.Transaction;
import com.android.mms.transaction.TransactionSettings;
import com.android.mms.transaction.TransactionState;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.DownloadManager;
import com.google.android.mms.InvalidHeaderValueException;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.NotificationInd;
import com.google.android.mms.pdu.NotifyRespInd;
import com.google.android.mms.pdu.PduComposer;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.IOException;
import miui.os.Build;

public class NotificationTransaction
extends Transaction
implements Runnable {
    private String mContentLocation;
    private NotificationInd mNotificationInd;
    public boolean mShouldDownload;
    private Uri mUri;

    public NotificationTransaction(Context context, TransactionSettings transactionSettings, NotificationInd notificationInd) {
        super(context, transactionSettings);
        try {
            this.mUri = MiuiPduPersister.getPduPersister((Context)context).persist((GenericPdu)notificationInd, Telephony.Mms.Inbox.CONTENT_URI, null, -1);
        }
        catch (MmsException var1_2) {
            MyLog.e(LogTag.logFormat("Failed to save NotificationInd in constructor.\nStack:%s", Log.getStackTraceString((Throwable)var1_2)));
            throw new IllegalArgumentException();
        }
        this.mNotificationInd = notificationInd;
        this.mId = new String(notificationInd.getTransactionId());
    }

    public NotificationTransaction(Context context, TransactionSettings transactionSettings, String string) {
        super(context, transactionSettings);
        this.mUri = Uri.parse((String)string);
        this.mTransactionState.setContentUri(this.mUri);
        try {
            this.mNotificationInd = (NotificationInd)MiuiPduPersister.getPduPersister((Context)context).load(this.mUri);
        }
        catch (MmsException var1_2) {
            MyLog.e(LogTag.logFormat("Failed to load NotificationInd from: %s\nStack:%s", string, Log.getStackTraceString((Throwable)var1_2)));
            throw new IllegalArgumentException();
        }
        this.mId = new String(this.mNotificationInd.getTransactionId());
        this.mContentLocation = new String(this.mNotificationInd.getContentLocation());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void sendNotifyRespInd(int n) throws MmsException, IOException {
        NotifyRespInd notifyRespInd = new NotifyRespInd(18, this.mNotificationInd.getTransactionId(), n);
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
            n = MessageUtils.getSendDeliverReportAllowed(this.mContext, this.mSimId) ? 128 : 129;
            try {
                notifyRespInd.setReportAllowed(n);
            }
            catch (InvalidHeaderValueException var3_3) {
                Log.e((String)"NotificationTransaction", (String)"acknowledgeInd.setReportAllowed Failed !!");
            }
        }
        if (MmsConfig.getNotifyWapMMSC()) {
            this.sendPdu(new PduComposer(this.mContext, (GenericPdu)notifyRespInd).make(), this.mContentLocation, null);
            return;
        }
        this.sendPdu(new PduComposer(this.mContext, (GenericPdu)notifyRespInd).make(), null);
    }

    @Override
    public int getType() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onEnqueue() {
        DownloadManager downloadManager = DownloadManager.getInstance();
        this.mShouldDownload = downloadManager.isAuto(this.mSimId);
        if (MessageUtils.hasBlockedFlag(this.mUri)) {
            this.mShouldDownload = false;
        }
        Uri uri = this.mUri;
        int n = this.mShouldDownload ? 129 : 128;
        downloadManager.markState(uri, n, this.mSimId);
        this.mContext.getContentResolver().notifyChange(Telephony.MmsSms.CONTENT_CONVERSATIONS_URI, null);
    }

    @Override
    public void process() {
        new Thread((Runnable)this).start();
    }

    /*
     * Exception decompiling
     */
    @Override
    public void run() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.CannotPerformDecode: reachable test BLOCK was exited and re-entered.
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Misc.getFarthestReachableInRange(Misc.java:143)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:385)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.replaceRawSwitches(SwitchReplacer.java:65)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:422)
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

}

