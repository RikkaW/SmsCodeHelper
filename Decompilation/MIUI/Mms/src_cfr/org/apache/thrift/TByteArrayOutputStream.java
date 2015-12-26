/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.ByteArrayOutputStream
 */
package org.apache.thrift;

import java.io.ByteArrayOutputStream;

public class TByteArrayOutputStream
extends ByteArrayOutputStream {
    public TByteArrayOutputStream() {
    }

    public TByteArrayOutputStream(int n) {
        super(n);
    }

    public byte[] get() {
        return this.buf;
    }

    public int len() {
        return this.count;
    }
}

