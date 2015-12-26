/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.OutputStream
 *  java.lang.String
 *  java.lang.Throwable
 */
package org.apache.thrift.transport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class TIOStreamTransport
extends TTransport {
    protected InputStream inputStream_ = null;
    protected OutputStream outputStream_ = null;

    protected TIOStreamTransport() {
    }

    public TIOStreamTransport(OutputStream outputStream) {
        this.outputStream_ = outputStream;
    }

    @Override
    public int read(byte[] arrby, int n, int n2) throws TTransportException {
        if (this.inputStream_ == null) {
            throw new TTransportException(1, "Cannot read from null inputStream");
        }
        try {
            n = this.inputStream_.read(arrby, n, n2);
            if (n < 0) {
                throw new TTransportException(4);
            }
        }
        catch (IOException var1_2) {
            throw new TTransportException(0, (Throwable)var1_2);
        }
        return n;
    }

    @Override
    public void write(byte[] arrby, int n, int n2) throws TTransportException {
        if (this.outputStream_ == null) {
            throw new TTransportException(1, "Cannot write to null outputStream");
        }
        try {
            this.outputStream_.write(arrby, n, n2);
            return;
        }
        catch (IOException var1_2) {
            throw new TTransportException(0, (Throwable)var1_2);
        }
    }
}

