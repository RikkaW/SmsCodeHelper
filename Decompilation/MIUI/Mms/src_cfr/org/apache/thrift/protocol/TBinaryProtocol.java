/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Double
 *  java.lang.Object
 *  java.lang.String
 *  java.nio.ByteBuffer
 */
package org.apache.thrift.protocol;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.protocol.TSet;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.transport.TTransport;

public class TBinaryProtocol
extends TProtocol {
    private static final TStruct ANONYMOUS_STRUCT = new TStruct();
    private byte[] bin = new byte[1];
    private byte[] bout = new byte[1];
    protected boolean checkReadLength_ = false;
    private byte[] i16out = new byte[2];
    private byte[] i16rd = new byte[2];
    private byte[] i32out = new byte[4];
    private byte[] i32rd = new byte[4];
    private byte[] i64out = new byte[8];
    private byte[] i64rd = new byte[8];
    protected int readLength_;
    protected boolean strictRead_ = false;
    protected boolean strictWrite_ = true;

    public TBinaryProtocol(TTransport tTransport) {
        this(tTransport, false, true);
    }

    public TBinaryProtocol(TTransport tTransport, boolean bl, boolean bl2) {
        super(tTransport);
        this.strictRead_ = bl;
        this.strictWrite_ = bl2;
    }

    private int readAll(byte[] arrby, int n, int n2) throws TException {
        this.checkReadLength(n2);
        return this.trans_.readAll(arrby, n, n2);
    }

    protected void checkReadLength(int n) throws TException {
        if (n < 0) {
            throw new TException("Negative length: " + n);
        }
        if (this.checkReadLength_) {
            this.readLength_ -= n;
            if (this.readLength_ < 0) {
                throw new TException("Message length exceeded: " + n);
            }
        }
    }

    @Override
    public ByteBuffer readBinary() throws TException {
        int n = this.readI32();
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
    public boolean readBool() throws TException {
        if (this.readByte() == 1) {
            return true;
        }
        return false;
    }

    @Override
    public byte readByte() throws TException {
        if (this.trans_.getBytesRemainingInBuffer() >= 1) {
            byte by = this.trans_.getBuffer()[this.trans_.getBufferPosition()];
            this.trans_.consumeBuffer(1);
            return by;
        }
        this.readAll(this.bin, 0, 1);
        return this.bin[0];
    }

    @Override
    public double readDouble() throws TException {
        return Double.longBitsToDouble((long)this.readI64());
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public TField readFieldBegin() throws TException {
        short s;
        byte by = this.readByte();
        if (by == 0) {
            s = 0;
            do {
                return new TField("", by, s);
                break;
            } while (true);
        }
        s = this.readI16();
        return new TField("", by, s);
    }

    @Override
    public void readFieldEnd() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public short readI16() throws TException {
        byte[] arrby = this.i16rd;
        int n = 0;
        if (this.trans_.getBytesRemainingInBuffer() >= 2) {
            arrby = this.trans_.getBuffer();
            n = this.trans_.getBufferPosition();
            this.trans_.consumeBuffer(2);
            do {
                return (short)((arrby[n] & 255) << 8 | arrby[n + 1] & 255);
                break;
            } while (true);
        }
        this.readAll(this.i16rd, 0, 2);
        return (short)((arrby[n] & 255) << 8 | arrby[n + 1] & 255);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int readI32() throws TException {
        byte[] arrby = this.i32rd;
        int n = 0;
        if (this.trans_.getBytesRemainingInBuffer() >= 4) {
            arrby = this.trans_.getBuffer();
            n = this.trans_.getBufferPosition();
            this.trans_.consumeBuffer(4);
            do {
                return (arrby[n] & 255) << 24 | (arrby[n + 1] & 255) << 16 | (arrby[n + 2] & 255) << 8 | arrby[n + 3] & 255;
                break;
            } while (true);
        }
        this.readAll(this.i32rd, 0, 4);
        return (arrby[n] & 255) << 24 | (arrby[n + 1] & 255) << 16 | (arrby[n + 2] & 255) << 8 | arrby[n + 3] & 255;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public long readI64() throws TException {
        byte[] arrby = this.i64rd;
        int n = 0;
        if (this.trans_.getBytesRemainingInBuffer() >= 8) {
            arrby = this.trans_.getBuffer();
            n = this.trans_.getBufferPosition();
            this.trans_.consumeBuffer(8);
            do {
                return (long)(arrby[n] & 255) << 56 | (long)(arrby[n + 1] & 255) << 48 | (long)(arrby[n + 2] & 255) << 40 | (long)(arrby[n + 3] & 255) << 32 | (long)(arrby[n + 4] & 255) << 24 | (long)(arrby[n + 5] & 255) << 16 | (long)(arrby[n + 6] & 255) << 8 | (long)(arrby[n + 7] & 255);
                break;
            } while (true);
        }
        this.readAll(this.i64rd, 0, 8);
        return (long)(arrby[n] & 255) << 56 | (long)(arrby[n + 1] & 255) << 48 | (long)(arrby[n + 2] & 255) << 40 | (long)(arrby[n + 3] & 255) << 32 | (long)(arrby[n + 4] & 255) << 24 | (long)(arrby[n + 5] & 255) << 16 | (long)(arrby[n + 6] & 255) << 8 | (long)(arrby[n + 7] & 255);
    }

    @Override
    public TList readListBegin() throws TException {
        return new TList(this.readByte(), this.readI32());
    }

    @Override
    public void readListEnd() {
    }

    @Override
    public TMap readMapBegin() throws TException {
        return new TMap(this.readByte(), this.readByte(), this.readI32());
    }

    @Override
    public void readMapEnd() {
    }

    @Override
    public TSet readSetBegin() throws TException {
        return new TSet(this.readByte(), this.readI32());
    }

    @Override
    public void readSetEnd() {
    }

    @Override
    public String readString() throws TException {
        int n = this.readI32();
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

    public String readStringBody(int n) throws TException {
        try {
            this.checkReadLength(n);
            Object object = new byte[n];
            this.trans_.readAll((byte[])object, 0, n);
            object = new String((byte[])object, "UTF-8");
            return object;
        }
        catch (UnsupportedEncodingException var2_3) {
            throw new TException("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override
    public TStruct readStructBegin() {
        return ANONYMOUS_STRUCT;
    }

    @Override
    public void readStructEnd() {
    }

    public void setReadLength(int n) {
        this.readLength_ = n;
        this.checkReadLength_ = true;
    }

    @Override
    public void writeBinary(ByteBuffer byteBuffer) throws TException {
        int n = byteBuffer.limit() - byteBuffer.position() - byteBuffer.arrayOffset();
        this.writeI32(n);
        this.trans_.write(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), n);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void writeBool(boolean bl) throws TException {
        byte by = bl ? 1 : 0;
        this.writeByte(by);
    }

    @Override
    public void writeByte(byte by) throws TException {
        this.bout[0] = by;
        this.trans_.write(this.bout, 0, 1);
    }

    @Override
    public void writeFieldBegin(TField tField) throws TException {
        this.writeByte(tField.type);
        this.writeI16(tField.id);
    }

    @Override
    public void writeFieldEnd() {
    }

    @Override
    public void writeFieldStop() throws TException {
        this.writeByte(0);
    }

    public void writeI16(short s) throws TException {
        this.i16out[0] = (byte)(s >> 8 & 255);
        this.i16out[1] = (byte)(s & 255);
        this.trans_.write(this.i16out, 0, 2);
    }

    @Override
    public void writeI32(int n) throws TException {
        this.i32out[0] = (byte)(n >> 24 & 255);
        this.i32out[1] = (byte)(n >> 16 & 255);
        this.i32out[2] = (byte)(n >> 8 & 255);
        this.i32out[3] = (byte)(n & 255);
        this.trans_.write(this.i32out, 0, 4);
    }

    @Override
    public void writeI64(long l) throws TException {
        this.i64out[0] = (byte)(l >> 56 & 255);
        this.i64out[1] = (byte)(l >> 48 & 255);
        this.i64out[2] = (byte)(l >> 40 & 255);
        this.i64out[3] = (byte)(l >> 32 & 255);
        this.i64out[4] = (byte)(l >> 24 & 255);
        this.i64out[5] = (byte)(l >> 16 & 255);
        this.i64out[6] = (byte)(l >> 8 & 255);
        this.i64out[7] = (byte)(255 & l);
        this.trans_.write(this.i64out, 0, 8);
    }

    @Override
    public void writeListBegin(TList tList) throws TException {
        this.writeByte(tList.elemType);
        this.writeI32(tList.size);
    }

    @Override
    public void writeListEnd() {
    }

    @Override
    public void writeMapBegin(TMap tMap) throws TException {
        this.writeByte(tMap.keyType);
        this.writeByte(tMap.valueType);
        this.writeI32(tMap.size);
    }

    @Override
    public void writeMapEnd() {
    }

    @Override
    public void writeSetBegin(TSet tSet) throws TException {
        this.writeByte(tSet.elemType);
        this.writeI32(tSet.size);
    }

    @Override
    public void writeSetEnd() {
    }

    @Override
    public void writeString(String string2) throws TException {
        try {
            string2 = (String)string2.getBytes("UTF-8");
            this.writeI32(string2.length);
            this.trans_.write((byte[])string2, 0, string2.length);
            return;
        }
        catch (UnsupportedEncodingException var1_2) {
            throw new TException("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override
    public void writeStructBegin(TStruct tStruct) {
    }

    @Override
    public void writeStructEnd() {
    }

    public static class Factory
    implements TProtocolFactory {
        protected int readLength_;
        protected boolean strictRead_ = false;
        protected boolean strictWrite_ = true;

        public Factory() {
            this(false, true);
        }

        public Factory(boolean bl, boolean bl2) {
            this(bl, bl2, 0);
        }

        public Factory(boolean bl, boolean bl2, int n) {
            this.strictRead_ = bl;
            this.strictWrite_ = bl2;
            this.readLength_ = n;
        }

        @Override
        public TProtocol getProtocol(TTransport object) {
            object = new TBinaryProtocol((TTransport)object, this.strictRead_, this.strictWrite_);
            if (this.readLength_ != 0) {
                object.setReadLength(this.readLength_);
            }
            return object;
        }
    }

}

