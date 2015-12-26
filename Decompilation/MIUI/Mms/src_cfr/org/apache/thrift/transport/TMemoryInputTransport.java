/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package org.apache.thrift.transport;

import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public final class TMemoryInputTransport
extends TTransport {
    private byte[] buf_;
    private int endPos_;
    private int pos_;

    @Override
    public void consumeBuffer(int n) {
        this.pos_ += n;
    }

    @Override
    public byte[] getBuffer() {
        return this.buf_;
    }

    @Override
    public int getBufferPosition() {
        return this.pos_;
    }

    @Override
    public int getBytesRemainingInBuffer() {
        return this.endPos_ - this.pos_;
    }

    @Override
    public int read(byte[] arrby, int n, int n2) throws TTransportException {
        int n3 = this.getBytesRemainingInBuffer();
        if (n2 > n3) {
            n2 = n3;
        }
        if (n2 > 0) {
            System.arraycopy((Object)this.buf_, (int)this.pos_, (Object)arrby, (int)n, (int)n2);
            this.consumeBuffer(n2);
        }
        return n2;
    }

    public void reset(byte[] arrby) {
        this.reset(arrby, 0, arrby.length);
    }

    public void reset(byte[] arrby, int n, int n2) {
        this.buf_ = arrby;
        this.pos_ = n;
        this.endPos_ = n + n2;
    }

    @Override
    public void write(byte[] arrby, int n, int n2) throws TTransportException {
        throw new UnsupportedOperationException("No writing allowed!");
    }
}

