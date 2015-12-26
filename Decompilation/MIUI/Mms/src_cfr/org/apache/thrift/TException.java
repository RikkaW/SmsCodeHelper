/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.String
 *  java.lang.Throwable
 */
package org.apache.thrift;

public class TException
extends Exception {
    private static final long serialVersionUID = 1;

    public TException() {
    }

    public TException(String string2) {
        super(string2);
    }

    public TException(String string2, Throwable throwable) {
        super(string2, throwable);
    }

    public TException(Throwable throwable) {
        super(throwable);
    }
}

