/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.System
 */
package org.apache.thrift.transport;

import org.apache.thrift.TByteArrayOutputStream;
import org.apache.thrift.transport.TTransport;

public class TMemoryBuffer
extends TTransport {
    private TByteArrayOutputStream arr_;
    private int pos_;

    public TMemoryBuffer(int n) {
        this.arr_ = new TByteArrayOutputStream(n);
    }

    public int length() {
        return this.arr_.size();
    }

    @Override
    public int read(byte[] arrby, int n, int n2) {
        byte[] arrby2 = this.arr_.get();
        if (n2 > this.arr_.len() - this.pos_) {
            n2 = this.arr_.len() - this.pos_;
        }
        if (n2 > 0) {
            System.arraycopy((Object)arrby2, (int)this.pos_, (Object)arrby, (int)n, (int)n2);
            this.pos_ += n2;
        }
        return n2;
    }

    @Override
    public void write(byte[] arrby, int n, int n2) {
        this.arr_.write(arrby, n, n2);
    }
}

