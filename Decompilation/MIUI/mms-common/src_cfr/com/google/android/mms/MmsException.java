/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.google.android.mms;

public class MmsException
extends Exception {
    private static final long serialVersionUID = -7323249827281485390L;

    public MmsException() {
    }

    public MmsException(String string) {
        super(string);
    }

    public MmsException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public MmsException(Throwable throwable) {
        super(throwable);
    }
}

