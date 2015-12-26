/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.String
 *  java.lang.Throwable
 */
package org.apache.thrift.protocol;

import org.apache.thrift.TException;

public class TProtocolException
extends TException {
    private static final long serialVersionUID = 1;
    protected int type_ = 0;

    public TProtocolException() {
    }

    public TProtocolException(int n, String string2) {
        super(string2);
        this.type_ = n;
    }

    public TProtocolException(String string2) {
        super(string2);
    }

    public TProtocolException(String string2, Throwable throwable) {
        super(string2, throwable);
    }

    public TProtocolException(Throwable throwable) {
        super(throwable);
    }
}

