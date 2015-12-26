/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.nio.ByteBuffer
 */
package org.apache.thrift.protocol;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TSet;
import org.apache.thrift.transport.TTransport;

public class XmPushTBinaryProtocol
extends TBinaryProtocol {
    private static int MAX_THRIFT_BINARY_SIZE;
    private static int MAX_THRIFT_LIST_SIZE;
    private static int MAX_THRIFT_MAP_SIZE;
    private static int MAX_THRIFT_SET_SIZE;
    private static int MAX_THRIFT_STRING_SIZE;

    static {
        MAX_THRIFT_MAP_SIZE = 10000;
        MAX_THRIFT_LIST_SIZE = 10000;
        MAX_THRIFT_SET_SIZE = 10000;
        MAX_THRIFT_STRING_SIZE = 10485760;
        MAX_THRIFT_BINARY_SIZE = 104857600;
    }

    public XmPushTBinaryProtocol(TTransport tTransport, boolean bl, boolean bl2) {
        super(tTransport, bl, bl2);
    }

    @Override
    public ByteBuffer readBinary() throws TException {
        int n = this.readI32();
        if (n > MAX_THRIFT_BINARY_SIZE) {
            throw new TProtocolException(3, "Thrift binary size " + n + " out of range!");
        }
        this.checkReadLength(n);
        if (this.trans_.getBytesRemainingInBuffer() >= n) {
            ByteBuffer byteBuffer = ByteBuffer.wrap((byte[])this.trans_.getBuffer(), (int)this.trans_.getBufferPosition(), (int)n);
            this.trans_.consumeBuffer(n);
            return byteBuffer;
        }
        byte[] arrby = new byte[n];
        this.trans_.readAll(arrby, 0, n);
        return ByteBuffer.wrap((byte[])arrby);
    }

    @Override
    public TList readListBegin() throws TException {
        byte by = this.readByte();
        int n = this.readI32();
        if (n > MAX_THRIFT_LIST_SIZE) {
            throw new TProtocolException(3, "Thrift list size " + n + " out of range!");
        }
        return new TList(by, n);
    }

    @Override
    public TMap readMapBegin() throws TException {
        byte by = this.readByte();
        byte by2 = this.readByte();
        int n = this.readI32();
        if (n > MAX_THRIFT_MAP_SIZE) {
            throw new TProtocolException(3, "Thrift map size " + n + " out of range!");
        }
        return new TMap(by, by2, n);
    }

    @Override
    public TSet readSetBegin() throws TException {
        byte by = this.readByte();
        int n = this.readI32();
        if (n > MAX_THRIFT_SET_SIZE) {
            throw new TProtocolException(3, "Thrift set size " + n + " out of range!");
        }
        return new TSet(by, n);
    }

    @Override
    public String readString() throws TException {
        int n = this.readI32();
        if (n > MAX_THRIFT_STRING_SIZE) {
            throw new TProtocolException(3, "Thrift string size " + n + " out of range!");
        }
        if (this.trans_.getBytesRemainingInBuffer() >= n) {
            try {
                String string2 = new String(this.trans_.getBuffer(), this.trans_.getBufferPosition(), n, "UTF-8");
                this.trans_.consumeBuffer(n);
                return string2;
            }
            catch (UnsupportedEncodingException var2_3) {
                throw new TException("JVM DOES NOT SUPPORT UTF-8");
            }
        }
        return this.readStringBody(n);
    }

    public static class Factory
    extends TBinaryProtocol.Factory {
        public Factory() {
            super(false, true);
        }

        public Factory(boolean bl, boolean bl2, int n) {
            super(bl, bl2, n);
        }

        @Override
        public TProtocol getProtocol(TTransport object) {
            object = new XmPushTBinaryProtocol((TTransport)object, this.strictRead_, this.strictWrite_);
            if (this.readLength_ != 0) {
                object.setReadLength(this.readLength_);
            }
            return object;
        }
    }

}

