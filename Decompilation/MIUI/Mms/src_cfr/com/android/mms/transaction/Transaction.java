/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.net.ConnectivityManager
 *  android.net.Uri
 *  android.util.Pair
 *  com.google.android.mms.MmsException
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.net.InetAddress
 */
package com.android.mms.transaction;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.util.Pair;
import com.android.mms.transaction.HttpUtils;
import com.android.mms.transaction.Observable;
import com.android.mms.transaction.ProgressReceiver;
import com.android.mms.transaction.TransactionSettings;
import com.android.mms.transaction.TransactionState;
import com.google.android.mms.MmsException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public abstract class Transaction
extends Observable {
    private static long mTokenGenerator = 1;
    protected static volatile long sCurrentTransactionMsgId;
    protected static volatile int sCurrentTransactionProgress;
    protected Context mContext;
    protected String mId;
    protected long mSimId;
    protected long mToken;
    protected TransactionSettings mTransactionSettings;
    protected TransactionState mTransactionState;

    public Transaction(Context context, TransactionSettings transactionSettings) {
        this.mContext = context;
        this.mTransactionState = new TransactionState();
        this.mTransactionSettings = transactionSettings;
    }

    private void ensureRouteToHost(String string, TransactionSettings transactionSettings) throws IOException {
        if (transactionSettings.isProxySet()) {
            int n = Transaction.lookupHost(transactionSettings.getProxyAddress());
            if (n == -1) {
                throw new IOException("Cannot establish route for " + string + ": Unknown host");
            }
            if (!((ConnectivityManager)this.mContext.getSystemService("connectivity")).requestRouteToHost(2, n)) {
                throw new IOException("Cannot establish route to proxy " + n);
            }
        } else {
            int n = Transaction.lookupHost(Uri.parse((String)string).getHost());
            if (n == -1) {
                throw new IOException("Cannot establish route for " + string + ": Unknown host");
            }
            if (!((ConnectivityManager)this.mContext.getSystemService("connectivity")).requestRouteToHost(2, n)) {
                throw new IOException("Cannot establish route to " + n + " for " + string);
            }
        }
    }

    public static long getCurrentTransactionMsgId() {
        return sCurrentTransactionMsgId;
    }

    public static int getCurrentTransactionProgress() {
        return sCurrentTransactionProgress;
    }

    public static int lookupHost(String string) {
        try {
            string = InetAddress.getByName((String)string);
        }
        catch (UnknownHostException var0_1) {
            return -1;
        }
        string = (String)string.getAddress();
        return (string[3] & 255) << 24 | (string[2] & 255) << 16 | (string[1] & 255) << 8 | string[0] & 255;
    }

    public boolean abort() {
        return HttpUtils.abortHttpConnection(this.mToken);
    }

    public boolean equals(Object object) {
        if (!(object instanceof Transaction)) {
            return false;
        }
        return this.isEquivalent((Transaction)object);
    }

    protected byte[] getPdu(String string, ProgressReceiver progressReceiver) throws IOException {
        this.ensureRouteToHost(string, this.mTransactionSettings);
        long l = mTokenGenerator;
        mTokenGenerator = 1 + l;
        this.mToken = l;
        return HttpUtils.httpConnection(this.mContext, this.mToken, string, null, 2, this.mTransactionSettings.isProxySet(), this.mTransactionSettings.getProxyAddress(), this.mTransactionSettings.getProxyPort(), progressReceiver);
    }

    public TransactionState getState() {
        return this.mTransactionState;
    }

    public abstract int getType();

    public int hashCode() {
        return Pair.create((Object)this.getClass(), (Object)this.mId).hashCode();
    }

    public boolean isEquivalent(Transaction transaction) {
        if (this.getClass().equals((Object)transaction.getClass()) && this.mId.equals((Object)transaction.mId)) {
            return true;
        }
        return false;
    }

    public void onEnqueue() {
    }

    public abstract void process();

    protected byte[] sendPdu(byte[] arrby, ProgressReceiver progressReceiver) throws IOException, MmsException {
        return this.sendPdu(arrby, this.mTransactionSettings.getMmscUrl(), progressReceiver);
    }

    protected byte[] sendPdu(byte[] arrby, String string, ProgressReceiver progressReceiver) throws IOException {
        this.ensureRouteToHost(string, this.mTransactionSettings);
        long l = mTokenGenerator;
        mTokenGenerator = 1 + l;
        this.mToken = l;
        return HttpUtils.httpConnection(this.mContext, this.mToken, string, arrby, 1, this.mTransactionSettings.isProxySet(), this.mTransactionSettings.getProxyAddress(), this.mTransactionSettings.getProxyPort(), progressReceiver);
    }

    public void setSimId(long l) {
        this.mSimId = l;
    }

    public String toString() {
        String string = this.getClass().getSimpleName();
        if (this.mTransactionState != null && this.mTransactionState.getContentUri() != null) {
            return string + ", uri=" + (Object)this.mTransactionState.getContentUri();
        }
        return string + ", uri=null";
    }
}

