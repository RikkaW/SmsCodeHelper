/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.google.android.mms.util;

import com.google.android.mms.pdu.GenericPdu;

public final class PduCacheEntry {
    private final int mMessageBox;
    private final GenericPdu mPdu;
    private final long mThreadId;

    public PduCacheEntry(GenericPdu genericPdu, int n, long l) {
        this.mPdu = genericPdu;
        this.mMessageBox = n;
        this.mThreadId = l;
    }

    public int getMessageBox() {
        return this.mMessageBox;
    }

    public GenericPdu getPdu() {
        return this.mPdu;
    }

    public long getThreadId() {
        return this.mThreadId;
    }
}

