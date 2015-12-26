/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.transaction;

import android.os.Bundle;

public class TransactionBundle {
    private final Bundle mBundle;

    private TransactionBundle(int n) {
        this.mBundle = new Bundle();
        this.mBundle.putInt("type", n);
    }

    public TransactionBundle(int n, String string) {
        this(n);
        this.mBundle.putString("uri", string);
    }

    public TransactionBundle(Bundle bundle) {
        this.mBundle = bundle;
    }

    public String getMmscUrl() {
        return this.mBundle.getString("mmsc-url");
    }

    public String getProxyAddress() {
        return this.mBundle.getString("proxy-address");
    }

    public int getProxyPort() {
        return this.mBundle.getInt("proxy-port");
    }

    public byte[] getPushData() {
        return this.mBundle.getByteArray("mms-push-data");
    }

    public int getTransactionType() {
        return this.mBundle.getInt("type");
    }

    public String getUri() {
        return this.mBundle.getString("uri");
    }

    public String toString() {
        return "transactionType: " + this.getTransactionType() + " uri: " + this.getUri() + " pushData: " + this.getPushData() + " mmscUrl: " + this.getMmscUrl() + " proxyAddress: " + this.getProxyAddress() + " proxyPort: " + this.getProxyPort();
    }
}

