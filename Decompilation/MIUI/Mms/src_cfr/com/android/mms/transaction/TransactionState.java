/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.net.Uri
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.transaction;

import android.net.Uri;

public class TransactionState {
    private Uri mContentUri = null;
    private int mState = 0;

    public Uri getContentUri() {
        synchronized (this) {
            Uri uri = this.mContentUri;
            return uri;
        }
    }

    public int getState() {
        synchronized (this) {
            int n = this.mState;
            return n;
        }
    }

    void setContentUri(Uri uri) {
        synchronized (this) {
            this.mContentUri = uri;
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void setState(int n) {
        synchronized (this) {
            if (n < 0 && n > 2) {
                throw new IllegalArgumentException("Bad state: " + n);
            }
            this.mState = n;
            return;
        }
    }
}

