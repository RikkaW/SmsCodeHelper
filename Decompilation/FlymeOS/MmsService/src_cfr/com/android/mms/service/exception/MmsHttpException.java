/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.android.mms.service.exception;

public class MmsHttpException
extends Exception {
    private final int mStatusCode;

    public MmsHttpException(int n, String string) {
        super(string);
        this.mStatusCode = n;
    }

    public MmsHttpException(int n, String string, Throwable throwable) {
        super(string, throwable);
        this.mStatusCode = n;
    }

    public MmsHttpException(int n, Throwable throwable) {
        super(throwable);
        this.mStatusCode = n;
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }
}

