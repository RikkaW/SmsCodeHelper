/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.AlarmManager
 *  android.app.PendingIntent
 *  android.app.Service
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.Intent
 *  android.database.Cursor
 *  android.database.sqlite.SqliteWrapper
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.HandlerThread
 *  android.os.IBinder
 *  android.os.Looper
 *  android.os.Parcelable
 *  android.os.PowerManager
 *  android.os.PowerManager$WakeLock
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Mms$Inbox
 *  android.provider.Telephony$Mms$Outbox
 *  android.telephony.PhoneStateListener
 *  android.widget.Toast
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  miui.telephony.TelephonyManager
 */
package com.android.mms.transaction;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcelable;
import android.os.PowerManager;
import android.provider.Telephony;
import android.telephony.PhoneStateListener;
import android.widget.Toast;
import com.android.mms.LogTag;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.transaction.MmsSystemEventReceiver;
import com.android.mms.transaction.NotificationTransaction;
import com.android.mms.transaction.Observable;
import com.android.mms.transaction.Observer;
import com.android.mms.transaction.Transaction;
import com.android.mms.transaction.TransactionBundle;
import com.android.mms.transaction.TransactionState;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.RateController;
import com.google.android.mms.pdu.MiuiPduPersister;
import java.util.Iterator;
import java.util.LinkedHashSet;
import miui.telephony.TelephonyManager;

public class TransactionService
extends Service
implements Observer {
    private ConnectivityManager mConnMgr;
    private Transaction mCurrentTransaction;
    private int mFailureCount = 0;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private boolean mIsWaitingReConnect = false;
    private long mLastSimId = -1;
    private int mLastStartId = -1;
    private int mMmsConnectivityFailedRetryCount = 0;
    private int mMmsConnectivityStartedRetryCount = 0;
    private Runnable mOnSim1CallStateChangedCallback;
    private Runnable mOnSim2CallStateChangedCallback;
    private Transaction mPendingTransaction;
    private int mPhoneState = 0;
    private int mPhoneState2 = 0;
    private PhoneStateListener mPhoneStateListener;
    private PhoneStateListener mPhoneStateListener2;
    private Object mPhoneStateLock = new Object();
    private boolean mReConnectWhenCallIdle = false;
    private int mSuccessCount = 0;
    private LinkedHashSet<Transaction> mTransactionQueue = new LinkedHashSet();
    private Handler mUIHandler = new Handler();
    private PowerManager.WakeLock mWakeLock;

    public TransactionService() {
        this.mOnSim1CallStateChangedCallback = new Runnable(){

            @Override
            public void run() {
                if (TransactionService.this.mPhoneState == 0 && TransactionService.this.mPhoneState2 == 0) {
                    LogTag.verbose("get slot0 new mReConnectWhenCallIdle : " + TransactionService.this.mReConnectWhenCallIdle, new Object[0]);
                    if (TransactionService.this.mReConnectWhenCallIdle) {
                        TransactionService.this.mReConnectWhenCallIdle = false;
                        TransactionService.this.connectAndProcessNextTransaction();
                    }
                }
            }
        };
        this.mOnSim2CallStateChangedCallback = new Runnable(){

            @Override
            public void run() {
                if (TransactionService.this.mPhoneState == 0 && TransactionService.this.mPhoneState2 == 0) {
                    LogTag.verbose("get slot1 new mReConnectWhenCallIdle : " + TransactionService.this.mReConnectWhenCallIdle, new Object[0]);
                    if (TransactionService.this.mReConnectWhenCallIdle) {
                        TransactionService.this.mReConnectWhenCallIdle = false;
                        TransactionService.this.connectAndProcessNextTransaction();
                    }
                }
            }
        };
    }

    static /* synthetic */ int access$1404(TransactionService transactionService) {
        int n;
        transactionService.mSuccessCount = n = transactionService.mSuccessCount + 1;
        return n;
    }

    static /* synthetic */ int access$1604(TransactionService transactionService) {
        int n;
        transactionService.mFailureCount = n = transactionService.mFailureCount + 1;
        return n;
    }

    static /* synthetic */ int access$204(TransactionService transactionService) {
        int n;
        transactionService.mMmsConnectivityFailedRetryCount = n = transactionService.mMmsConnectivityFailedRetryCount + 1;
        return n;
    }

    static /* synthetic */ int access$504(TransactionService transactionService) {
        int n;
        transactionService.mMmsConnectivityStartedRetryCount = n = transactionService.mMmsConnectivityStartedRetryCount + 1;
        return n;
    }

    private boolean checkMobileNetwork(int n) {
        if (MmsSystemEventReceiver.getInstance().isMmsAllowed()) {
            LogTag.verbose("checkMobileNetwork isMmsAllowed is true", new Object[0]);
            boolean bl = this.isNetworkAvailable(n);
            if (bl) {
                MmsSystemEventReceiver.getInstance().unlistenForMmsAvailability(n);
                return true;
            }
            if (this.mReConnectWhenCallIdle || !bl && this.isDuringCall()) {
                this.mReConnectWhenCallIdle = true;
                LogTag.verbose("checkMobile during call", new Object[0]);
                MmsSystemEventReceiver.getInstance().unlistenForMmsAvailability(n);
                return true;
            }
        } else {
            LogTag.verbose("checkMobileNetwork isMmsAllowed is false", new Object[0]);
            this.mReConnectWhenCallIdle = false;
        }
        MmsSystemEventReceiver.getInstance().listenForMmsAvailability(n);
        return false;
    }

    private void cleanOrStop() {
        if (this.mCurrentTransaction == null && this.mTransactionQueue.isEmpty()) {
            this.endMmsConnectivity();
            this.stopSelfIfIdle(this.mLastStartId);
            LogTag.debug("TrasctionService stopped.", new Object[0]);
            return;
        }
        LogTag.error("no pending messages in database but in memory", new Object[0]);
    }

    private void connectAndProcessNextTransaction() {
        boolean bl;
        int n;
        if (this.mIsWaitingReConnect) {
            LogTag.verbose("mIsWaitingReConnect is true", new Object[0]);
            return;
        }
        if (this.mCurrentTransaction != null) {
            LogTag.verbose("while there is already one processing, transaction=%s", this.mCurrentTransaction);
            return;
        }
        if (this.mTransactionQueue.isEmpty()) {
            if (!this.mReConnectWhenCallIdle) {
                this.onFinish();
                return;
            }
            LogTag.verbose("not on finish when calling", new Object[0]);
            return;
        }
        if (this.mPendingTransaction == null) {
            this.mPendingTransaction = this.getPendingTransaction();
        }
        if (!(bl = MSimUtils.isSimInserted(n = MSimUtils.getSlotIdBySimInfoId(this.mPendingTransaction.mSimId))) || !this.checkMobileNetwork(n)) {
            LogTag.verbose("transaction is not available in case of no slotId", new Object[0]);
            MmsSystemEventReceiver.getInstance().listenForMmsAvailability(n);
            this.markTransactionFailureBySimId(this.mPendingTransaction.mSimId, false, false, bl);
            this.mMmsConnectivityFailedRetryCount = 0;
            this.mMmsConnectivityStartedRetryCount = 0;
            this.mPendingTransaction = null;
            this.requestRetryConnect();
            return;
        }
        if (this.mPendingTransaction.getType() == 0 && !((NotificationTransaction)this.mPendingTransaction).mShouldDownload) {
            LogTag.verbose("notification trasaction should download is false", new Object[0]);
            this.mLastSimId = this.mPendingTransaction.mSimId;
            this.processTransaction(this.mPendingTransaction);
            this.mPendingTransaction = null;
            this.mMmsConnectivityFailedRetryCount = 0;
            this.mMmsConnectivityStartedRetryCount = 0;
            return;
        }
        LogTag.verbose("startUsingNetworkFeature slotId = " + n, new Object[0]);
        n = this.mConnMgr.startUsingNetworkFeature(0, "enableMMS", n);
        this.mLastSimId = this.mPendingTransaction.mSimId;
        LogTag.verbose("connectAndProcessNextTransaction: result=" + n, new Object[0]);
        switch (n) {
            default: {
                if (!this.isDuringCall()) break;
                this.mReConnectWhenCallIdle = true;
                this.markTransactionFailureBySimId(this.mLastSimId, false, true, true);
                this.mMmsConnectivityFailedRetryCount = 0;
                this.mMmsConnectivityStartedRetryCount = 0;
                this.mPendingTransaction = null;
                this.requestRetryConnect();
                return;
            }
            case 0: {
                this.processTransaction(this.mPendingTransaction);
                this.mPendingTransaction = null;
                this.mMmsConnectivityFailedRetryCount = 0;
                this.mMmsConnectivityStartedRetryCount = 0;
                return;
            }
            case 1: {
                this.mWakeLock.acquire();
                this.sendBroadcast(new Intent("com.android.mms.transaction.START"));
                this.requestRetryConnectForStarted();
                return;
            }
        }
        this.requestRetryConnectForFailed();
    }

    private void endMmsConnectivity() {
        block4 : {
            LogTag.verbose("endMmsConnectivity", new Object[0]);
            int n = MSimUtils.getSlotIdBySimInfoId(this.mLastSimId);
            if (this.mConnMgr == null || n < 0) break block4;
            LogTag.verbose("mLastSimId = %d ", this.mLastSimId);
            this.mConnMgr.stopUsingNetworkFeature(0, "enableMMS", n);
            this.sendBroadcast(new Intent("com.android.mms.transaction.STOP"));
        }
        return;
        finally {
            this.mWakeLock.release();
        }
    }

    /*
     * Exception decompiling
     */
    private void enqueueAllMessages(int var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 5[SWITCH]
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

    /*
     * Exception decompiling
     */
    private boolean enqueueMessage(TransactionBundle var1_1, long var2_3, int var4_4) {
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

    /*
     * Enabled aggressive block sorting
     */
    private void enqueueSingleMessage(TransactionBundle transactionBundle) {
        String string = transactionBundle.getUri();
        long l = this.getSimId(string);
        int n = MSimUtils.getSlotIdBySimInfoId(l);
        if (!MSimUtils.isSimInserted(n)) {
            LogTag.error("sim id is not inserted to slot", new Object[0]);
            MmsSystemEventReceiver.getInstance().listenForMmsAvailability(n);
            this.onTransactionFailure(Uri.parse((String)string), l, true, false, false);
            return;
        }
        if (this.checkMobileNetwork(n)) {
            this.enqueueMessage(transactionBundle, l, n);
        } else {
            this.toast(transactionBundle.getTransactionType());
        }
        this.connectAndProcessNextTransaction();
    }

    private Transaction getPendingTransaction() {
        if (this.mLastSimId == -1) {
            return (Transaction)this.mTransactionQueue.iterator().next();
        }
        for (Transaction transaction : this.mTransactionQueue) {
            if (transaction.mSimId != this.mLastSimId) continue;
            return transaction;
        }
        this.endMmsConnectivity();
        return (Transaction)this.mTransactionQueue.iterator().next();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private int getResponseStatus(long l) {
        Cursor cursor;
        int n;
        block3 : {
            cursor = SqliteWrapper.query((Context)this, (ContentResolver)this.getContentResolver(), (Uri)Telephony.Mms.Outbox.CONTENT_URI, (String[])null, (String)("_id=" + l), (String[])null, (String)null);
            n = cursor.moveToFirst() ? cursor.getInt(cursor.getColumnIndexOrThrow("resp_st")) : 0;
            if (n == 0) break block3;
            LogTag.warn("Response status is: %d", n);
        }
        return n;
        finally {
            cursor.close();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private long getSimId(String string) {
        long l;
        block2 : {
            int n;
            l = -1;
            if (!MSimUtils.isMSim()) return 0;
            string = SqliteWrapper.query((Context)this, (ContentResolver)this.getContentResolver(), (Uri)Uri.parse((String)string), (String[])new String[]{"sim_id"}, (String)null, (String[])null, (String)null);
            long l2 = l;
            if (string == null) return l2;
            try {
                if (!string.moveToFirst()) break block2;
                n = string.getInt(0);
            }
            catch (Throwable throwable) {
                string.close();
                throw throwable;
            }
            l = n;
        }
        string.close();
        return l;
    }

    private int getTransactionType(int n) {
        int n2 = 1;
        switch (n) {
            default: {
                LogTag.warn("Unrecognized MESSAGE_TYPE: %d", n);
                n2 = -1;
            }
            case 130: {
                return n2;
            }
            case 135: {
                return 3;
            }
            case 128: 
        }
        return 2;
    }

    private boolean isDuringCall() {
        this.updateCallState();
        if (this.mPhoneState != 0 || this.mPhoneState2 != 0) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean isNetworkAvailable(int n) {
        boolean bl;
        boolean bl2 = false;
        if (n != MSimUtils.getPreferredDataSlotId()) {
            LogTag.verbose("isNetworkAvailable not preferred data slotId", new Object[0]);
            bl = true;
        } else {
            NetworkInfo networkInfo = this.mConnMgr.getNetworkInfo(2);
            if (networkInfo != null && networkInfo.isAvailable()) {
                bl = true;
            } else {
                networkInfo = this.mConnMgr.getNetworkInfo(0);
                bl = bl2;
                if (networkInfo != null) {
                    bl = bl2;
                    if (networkInfo.isAvailable()) {
                        bl = true;
                    }
                }
            }
        }
        LogTag.verbose("isNetworkAvaliable is " + bl, new Object[0]);
        return bl;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean isRetry() {
        Cursor cursor;
        boolean bl;
        block6 : {
            bl = true;
            if (MmsSystemEventReceiver.getInstance().isListening()) {
                LogTag.verbose("for listen mms available, not retry", new Object[0]);
                return false;
            }
            cursor = MiuiPduPersister.getPduPersister((Context)this).getPendingMessages(Integer.MAX_VALUE);
            if (cursor == null) {
                return false;
            }
            try {
                while (cursor.moveToNext()) {
                    int n;
                    if (1 == this.getTransactionType(cursor.getInt(cursor.getColumnIndexOrThrow("msg_type"))) && ((n = cursor.getInt(cursor.getColumnIndexOrThrow("err_type"))) >= 10 || n <= 0) || (n = cursor.getInt(cursor.getColumnIndexOrThrow("retry_index"))) >= 5) continue;
                    break block6;
                }
            }
            catch (Throwable var4_4) {
                cursor.close();
                throw var4_4;
            }
            bl = false;
        }
        cursor.close();
        return bl;
    }

    private void markTransactionFailureAndRemove(Transaction transaction, boolean bl, boolean bl2) {
        if (transaction != null) {
            LogTag.verbose("markTransactionFailureAndRemove transaction : %s", transaction);
            this.onTransactionFailure(transaction.getState().getContentUri(), transaction.mSimId, bl, bl2, true);
            this.mTransactionQueue.remove((Object)transaction);
            ++this.mFailureCount;
        }
    }

    private void markTransactionFailureBySimId(long l, boolean bl, boolean bl2, boolean bl3) {
        Object object = new LinkedHashSet();
        for (Transaction transaction : this.mTransactionQueue) {
            if (transaction.mSimId != l) continue;
            object.add((Object)transaction);
        }
        object = object.iterator();
        while (object.hasNext()) {
            Transaction transaction2 = (Transaction)object.next();
            this.onTransactionFailure(transaction2.getState().getContentUri(), l, bl, bl2, bl3);
            this.mTransactionQueue.remove((Object)transaction2);
            ++this.mFailureCount;
        }
    }

    private void onFinish() {
        LogTag.debug("Finished all transactions in queue. %d success, %d failure.", this.mSuccessCount, this.mFailureCount);
        if (this.mFailureCount > 0) {
            this.scheduleNextRetry();
        }
        this.mFailureCount = 0;
        this.endMmsConnectivity();
        this.stopSelfIfIdle(this.mLastStartId);
        LogTag.debug("TrasctionService stopped.", new Object[0]);
    }

    /*
     * Exception decompiling
     */
    private void onTransactionFailure(Uri var1_1, long var2_4, boolean var4_5, boolean var5_6, boolean var6_7) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: First case is not immediately after switch.
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:366)
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

    private void processTransaction(Transaction transaction) {
        this.mCurrentTransaction = transaction;
        this.mIsWaitingReConnect = false;
        LogTag.verbose("Processing next transaction: %s", this.mCurrentTransaction);
        this.sendBroadcast(new Intent("com.android.mms.transaction.START"));
        this.mCurrentTransaction.attach(this);
        this.mCurrentTransaction.process();
    }

    private void registerPhoneCallListener() {
        this.mPhoneStateListener = new PhoneStateListener(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            public void onCallStateChanged(int n, String object) {
                object = TransactionService.this.mPhoneStateLock;
                synchronized (object) {
                    TransactionService.this.updateCallState();
                }
                TransactionService.this.runInWorkerThread(TransactionService.this.mOnSim1CallStateChangedCallback);
            }
        };
        TelephonyManager.getDefault().listenForSlot(0, this.mPhoneStateListener, 32);
        if (TelephonyManager.getDefault().isMultiSimEnabled()) {
            this.mPhoneStateListener2 = new PhoneStateListener(){

                /*
                 * Enabled aggressive block sorting
                 * Enabled unnecessary exception pruning
                 * Enabled aggressive exception aggregation
                 */
                public void onCallStateChanged(int n, String object) {
                    object = TransactionService.this.mPhoneStateLock;
                    synchronized (object) {
                        TransactionService.this.updateCallState();
                    }
                    TransactionService.this.runInWorkerThread(TransactionService.this.mOnSim2CallStateChangedCallback);
                }
            };
            TelephonyManager.getDefault().listenForSlot(1, this.mPhoneStateListener2, 32);
        }
        LogTag.verbose("register Phone Call Listener.", new Object[0]);
    }

    private void requestRetryConnect() {
        this.mIsWaitingReConnect = true;
        LogTag.verbose("requestRetryConnect start", new Object[0]);
        this.runInWorkerThread(new Runnable(){

            @Override
            public void run() {
                TransactionService.this.mIsWaitingReConnect = false;
                LogTag.verbose("requestRetryConnect end", new Object[0]);
                TransactionService.this.connectAndProcessNextTransaction();
            }
        }, 5000);
    }

    private void requestRetryConnectForFailed() {
        this.mIsWaitingReConnect = true;
        LogTag.verbose("requestRetryConnectForFailed start", new Object[0]);
        this.runInWorkerThread(new Runnable(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void run() {
                TransactionService.this.mIsWaitingReConnect = false;
                LogTag.verbose("requestRetryConnectForFailed end", new Object[0]);
                if (TransactionService.this.mMmsConnectivityFailedRetryCount < 3) {
                    TransactionService.access$204(TransactionService.this);
                } else {
                    TransactionService.this.markTransactionFailureBySimId(TransactionService.this.mLastSimId, false, false, true);
                    TransactionService.this.mMmsConnectivityFailedRetryCount = 0;
                    TransactionService.this.mMmsConnectivityStartedRetryCount = 0;
                    TransactionService.this.mPendingTransaction = null;
                }
                TransactionService.this.connectAndProcessNextTransaction();
            }
        }, 5000);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void requestRetryConnectForStarted() {
        this.mIsWaitingReConnect = true;
        LogTag.verbose("requestRetryConnectForStarted start", new Object[0]);
        int n = 5000;
        if (MSimUtils.isMSim()) {
            n = MSimUtils.isLeadcore() ? 3000 : 12000;
        }
        this.runInWorkerThread(new Runnable(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void run() {
                TransactionService.this.mIsWaitingReConnect = false;
                LogTag.verbose("requestRetryConnectForStarted end", new Object[0]);
                int n = 18;
                if (MSimUtils.isMSim()) {
                    n = 20;
                }
                if (TransactionService.this.mMmsConnectivityStartedRetryCount < n) {
                    TransactionService.access$504(TransactionService.this);
                } else {
                    TransactionService.this.markTransactionFailureAndRemove(TransactionService.this.mPendingTransaction, false, false);
                    TransactionService.this.mMmsConnectivityFailedRetryCount = 0;
                    TransactionService.this.mMmsConnectivityStartedRetryCount = 0;
                    TransactionService.this.mPendingTransaction = null;
                    TransactionService.this.endMmsConnectivity();
                }
                TransactionService.this.connectAndProcessNextTransaction();
            }
        }, n);
    }

    private void resetDownloadState() {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("st", Integer.valueOf((int)128));
        SqliteWrapper.update((Context)this, (ContentResolver)this.getContentResolver(), (Uri)Telephony.Mms.Inbox.CONTENT_URI, (ContentValues)contentValues, (String)"m_type=130", (String[])null);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void runInWorkerThread(Runnable runnable) {
        synchronized (this) {
            if (this.mHandler != null) {
                this.mHandler.post(runnable);
            } else {
                LogTag.error("Attempt to post runnable while handler thread is dead.", new Object[0]);
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void runInWorkerThread(Runnable runnable, int n) {
        synchronized (this) {
            if (this.mHandler != null) {
                void var2_2;
                this.mHandler.postDelayed(runnable, (long)var2_2);
            } else {
                LogTag.error("Attempt to post delayed runnable while handler thread is dead.", new Object[0]);
            }
            return;
        }
    }

    private void scheduleNextRetry() {
        if (this.isRetry()) {
            LogTag.verbose("scheduleNextRetry isRetry", new Object[0]);
            long l = System.currentTimeMillis();
            PendingIntent pendingIntent = PendingIntent.getService((Context)this, (int)0, (Intent)new Intent("android.intent.action.ACTION_ONALARM", null, (Context)this, (Class)TransactionService.class), (int)1073741824);
            ((AlarmManager)this.getSystemService("alarm")).set(1, l + 45000, pendingIntent);
        }
    }

    private void stopSelfIfIdle(int n) {
        if (this.mReConnectWhenCallIdle) {
            LogTag.verbose("need wait call end, no stop.", new Object[0]);
            return;
        }
        LogTag.verbose("stop TransactionService.", new Object[0]);
        this.stopSelf(n);
    }

    private void toast(int n) {
        this.toast(n, false);
    }

    private void toast(final int n, final boolean bl) {
        this.mUIHandler.post(new Runnable(){

            @Override
            public void run() {
                if (bl) {
                    Toast.makeText((Context)TransactionService.this, (int)2131362380, (int)1).show();
                    return;
                }
                switch (n) {
                    default: {
                        return;
                    }
                    case 1: {
                        Toast.makeText((Context)TransactionService.this, (int)2131362010, (int)1).show();
                        return;
                    }
                    case 2: 
                }
                Toast.makeText((Context)TransactionService.this, (int)2131362000, (int)1).show();
            }
        });
    }

    private void unregisterPhoneCallListener() {
        TelephonyManager.getDefault().listenForSlot(0, this.mPhoneStateListener, 0);
        this.mPhoneStateListener = null;
        if (this.mPhoneStateListener2 != null) {
            TelephonyManager.getDefault().listenForSlot(1, this.mPhoneStateListener2, 0);
            this.mPhoneStateListener2 = null;
        }
        LogTag.verbose("unregister Phone Call Listener.", new Object[0]);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void updateCallState() {
        Object object = this.mPhoneStateLock;
        synchronized (object) {
            this.mPhoneState = TelephonyManager.getDefault().getCallStateForSlot(0);
            if (TelephonyManager.getDefault().isMultiSimEnabled()) {
                this.mPhoneState2 = TelephonyManager.getDefault().getCallStateForSlot(1);
            }
        }
        LogTag.verbose("whether is during call and mPhoneState = " + this.mPhoneState + " and mPhoneState2 = " + this.mPhoneState2, new Object[0]);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        LogTag.debug("Creating TransactionService...", new Object[0]);
        this.mWakeLock = ((PowerManager)this.getSystemService("power")).newWakeLock(1, "MMS Connectivity");
        this.mWakeLock.setReferenceCounted(false);
        this.mConnMgr = (ConnectivityManager)this.getSystemService("connectivity");
        this.mHandlerThread = new HandlerThread("TransactionService");
        this.mHandlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        this.mSuccessCount = 0;
        this.mFailureCount = 0;
        this.registerPhoneCallListener();
        this.resetDownloadState();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void onDestroy() {
        LogTag.debug("Destroying TransactionService", new Object[0]);
        synchronized (this) {
            this.mHandlerThread.quit();
            this.mHandler = null;
            LogTag.verbose("Requested killing handler thread.", new Object[0]);
        }
        this.unregisterPhoneCallListener();
        if (this.mCurrentTransaction != null) {
            LogTag.error("TransactionService exiting with transaction still processing", new Object[0]);
            try {
                this.mCurrentTransaction.abort();
            }
            catch (Exception var1_1) {}
        }
        this.mCurrentTransaction = null;
        if (!this.mTransactionQueue.isEmpty()) {
            LogTag.error("TransactionService exiting with transaction in queue", new Object[0]);
            this.mTransactionQueue.clear();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public int onStartCommand(Intent object, final int n, final int n2) {
        LogTag.debug("onStartService: startId=%d", n2);
        if (object != null && !"android.intent.action.ACTION_ONALARM".equals((Object)object.getAction()) && !"android.intent.action.ACTION_WAKEUP".equals((Object)object.getAction()) && object.getExtras() != null) {
            object = new TransactionBundle(object.getExtras());
            LogTag.verbose("Posting enqueueSingleMessage", new Object[0]);
            this.runInWorkerThread(new Runnable((TransactionBundle)object){
                final /* synthetic */ TransactionBundle val$args;

                @Override
                public void run() {
                    TransactionService.this.mLastStartId = n2;
                    TransactionService.this.enqueueSingleMessage(this.val$args);
                }
            });
            return 2;
        }
        LogTag.verbose("Posting enqueueAllMessages", new Object[0]);
        if (object != null && "android.intent.action.ACTION_WAKEUP".equals((Object)object.getAction())) {
            n = MSimUtils.getSlotIdFromIntent((Intent)object);
            LogTag.verbose("onStartCommand ACTION_WAKEUP slotId " + n, new Object[0]);
        } else {
            n = MSimUtils.SLOT_ID_INVALID;
        }
        this.runInWorkerThread(new Runnable(){

            @Override
            public void run() {
                LogTag.verbose("Posting enqueueAllMessages", new Object[0]);
                TransactionService.this.mLastStartId = n2;
                TransactionService.this.enqueueAllMessages(n);
            }
        });
        return 2;
    }

    @Override
    public void update(Observable observable) {
        observable = (Transaction)observable;
        LogTag.verbose("Posting transaction update", new Object[0]);
        this.runInWorkerThread(new Runnable((Transaction)observable){
            final /* synthetic */ Transaction val$transaction;

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void run() {
                LogTag.verbose("Update transaction %s", this.val$transaction);
                if (this.val$transaction != TransactionService.this.mCurrentTransaction) {
                    LogTag.error("Expecting %s to update but it is actually %s", TransactionService.this.mCurrentTransaction, this.val$transaction);
                }
                Intent intent = new Intent("android.intent.action.TRANSACTION_COMPLETED_ACTION");
                TransactionState transactionState = this.val$transaction.getState();
                int n = transactionState.getState();
                intent.putExtra("state", n);
                intent.putExtra("uri", (Parcelable)transactionState.getContentUri());
                switch (n) {
                    default: {
                        LogTag.verbose("Transaction state unknown: %s. result=%d", this.val$transaction, n);
                        break;
                    }
                    case 1: {
                        LogTag.verbose("Transaction complete: %s", this.val$transaction);
                        switch (this.val$transaction.getType()) {
                            case 0: 
                            case 1: {
                                MessagingNotification.blockingUpdateNewMessageIndicator((Context)TransactionService.this, true, false);
                                MessagingNotification.updateDownloadFailedNotification((Context)TransactionService.this);
                            }
                            default: {
                                break;
                            }
                            case 2: {
                                RateController.getInstance().update();
                            }
                        }
                        TransactionService.this.mTransactionQueue.remove((Object)this.val$transaction);
                        TransactionService.access$1404(TransactionService.this);
                        break;
                    }
                    case 2: {
                        LogTag.verbose("Transaction failed: %s", this.val$transaction);
                        TransactionService.this.onTransactionFailure(transactionState.getContentUri(), this.val$transaction.mSimId, false, false, true);
                        TransactionService.this.mTransactionQueue.remove((Object)this.val$transaction);
                        TransactionService.access$1604(TransactionService.this);
                    }
                }
                this.val$transaction.detach(TransactionService.this);
                TransactionService.this.mCurrentTransaction = null;
                TransactionService.this.sendBroadcast(intent);
                TransactionService.this.connectAndProcessNextTransaction();
            }
        });
    }

}

