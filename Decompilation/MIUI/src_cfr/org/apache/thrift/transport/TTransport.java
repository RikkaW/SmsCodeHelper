/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package org.apache.thrift.transport;

import org.apache.thrift.transport.TTransportException;

public abstract class TTransport {
    public void consumeBuffer(int n) {
    }

    public byte[] getBuffer() {
        return null;
    }

    public int getBufferPosition() {
        return 0;
    }

    public int getBytesRemainingInBuffer() {
        return -1;
    }

    public abstract int read(byte[] var1, int var2, int var3) throws TTransportException;

    public int readAll(byte[] arrby, int n, int n2) throws TTransportException {
        int n3;
        int n4;
        for (n3 = 0; n3 < n2; n3 += n4) {
            n4 = this.read(arrby, n + n3, n2 - n3);
            if (n4 > 0) continue;
            throw new TTransportException("Cannot read. Remote side has closed. Tried to read " + n2 + " bytes, but only got " + n3 + " bytes.");
        }
        return n3;
    }

    public void write(byte[] arrby) throws TTransportException {
        this.write(arrby, 0, arrby.length);
    }

    public abstract void write(byte[] var1, int var2, int var3) throws TTransportException;
}

