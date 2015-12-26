/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.String
 *  java.lang.Throwable
 */
package org.apache.thrift.transport;

import org.apache.thrift.TException;

public class TTransportException
extends TException {
    private static final long serialVersionUID = 1;
    protected int type_ = 0;

    public TTransportException() {
    }

    public TTransportException(int n) {
        this.type_ = n;
    }

    public TTransportException(int n, String string2) {
        super(string2);
        this.type_ = n;
    }

    public TTransportException(int n, Throwable throwable) {
        super(throwable);
        this.type_ = n;
    }

    public TTransportException(String string2) {
        super(string2);
    }

    public TTransportException(String string2, Throwable throwable) {
        super(string2, throwable);
    }

    public TTransportException(Throwable throwable) {
        super(throwable);
    }
}

