/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Double
 *  java.lang.Object
 *  java.lang.String
 *  java.nio.ByteBuffer
 */
package org.apache.thrift.protocol;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.apache.thrift.ShortStack;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.protocol.TSet;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.transport.TTransport;

public final class TCompactProtocol
extends TProtocol {
    private static final TStruct ANONYMOUS_STRUCT = new TStruct("");
    private static final TField TSTOP = new TField("", 0, 0);
    private static final byte[] ttypeToCompactType = new byte[16];
    private Boolean boolValue_ = null;
    private TField booleanField_ = null;
    private byte[] byteDirectBuffer = new byte[1];
    byte[] byteRawBuf = new byte[1];
    byte[] i32buf = new byte[5];
    private short lastFieldId_ = 0;
    private ShortStack lastField_ = new ShortStack(15);
    byte[] varint64out = new byte[10];

    static {
        TCompactProtocol.ttypeToCompactType[0] = 0;
        TCompactProtocol.ttypeToCompactType[2] = 1;
        TCompactProtocol.ttypeToCompactType[3] = 3;
        TCompactProtocol.ttypeToCompactType[6] = 4;
        TCompactProtocol.ttypeToCompactType[8] = 5;
        TCompactProtocol.ttypeToCompactType[10] = 6;
        TCompactProtocol.ttypeToCompactType[4] = 7;
        TCompactProtocol.ttypeToCompactType[11] = 8;
        TCompactProtocol.ttypeToCompactType[15] = 9;
        TCompactProtocol.ttypeToCompactType[14] = 10;
        TCompactProtocol.ttypeToCompactType[13] = 11;
        TCompactProtocol.ttypeToCompactType[12] = 12;
    }

    public TCompactProtocol(TTransport tTransport) {
        super(tTransport);
    }

    private long bytesToLong(byte[] arrby) {
        return ((long)arrby[7] & 255) << 56 | ((long)arrby[6] & 255) << 48 | ((long)arrby[5] & 255) << 40 | ((long)arrby[4] & 255) << 32 | ((long)arrby[3] & 255) << 24 | ((long)arrby[2] & 255) << 16 | ((long)arrby[1] & 255) << 8 | (long)arrby[0] & 255;
    }

    private byte getCompactType(byte by) {
        return ttypeToCompactType[by];
    }

    private byte getTType(byte by) throws TProtocolException {
        switch ((byte)(by & 15)) {
            default: {
                throw new TProtocolException("don't know what type: " + (byte)(by & 15));
            }
            case 0: {
                return 0;
            }
            case 1: 
            case 2: {
                return 2;
            }
            case 3: {
                return 3;
            }
            case 4: {
                return 6;
            }
            case 5: {
                return 8;
            }
            case 6: {
                return 10;
            }
            case 7: {
                return 4;
            }
            case 8: {
                return 11;
            }
            case 9: {
                return 15;
            }
            case 10: {
                return 14;
            }
            case 11: {
                return 13;
            }
            case 12: 
        }
        return 12;
    }

    private int intToZigZag(int n) {
        return n << 1 ^ n >> 31;
    }

    private boolean isBoolType(byte by) {
        if ((by = (byte)(by & 15)) == 1 || by == 2) {
            return true;
        }
        return false;
    }

    private long longToZigzag(long l) {
        return l << 1 ^ l >> 63;
    }

    private byte[] readBinary(int n) throws TException {
        if (n == 0) {
            return new byte[0];
        }
        byte[] arrby = new byte[n];
        this.trans_.readAll(arrby, 0, n);
        return arrby;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private int readVarint32() throws TException {
        var1_1 = 0;
        var3_2 = 0;
        var2_3 = 0;
        var4_4 = 0;
        if (this.trans_.getBytesRemainingInBuffer() < 5) ** GOTO lbl20
        var6_5 = this.trans_.getBuffer();
        var5_6 = this.trans_.getBufferPosition();
        var2_3 = 0;
        var1_1 = var4_4;
        do {
            var4_4 = var6_5[var5_6 + var2_3];
            var3_2 |= (var4_4 & 127) << var1_1;
            if ((var4_4 & 128) != 128) {
                this.trans_.consumeBuffer(var2_3 + 1);
                return var3_2;
            }
            var1_1 += 7;
            ++var2_3;
        } while (true);
lbl-1000: // 1 sources:
        {
            var2_3 += 7;
lbl20: // 2 sources:
            var3_2 = this.readByte();
            var1_1 |= (var3_2 & 127) << var2_3;
            ** while ((var3_2 & 128) == 128)
        }
lbl23: // 1 sources:
        return var1_1;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private long readVarint64() throws TException {
        var1_1 = 0;
        var2_2 = 0;
        var7_4 = var5_3 = 0;
        if (this.trans_.getBytesRemainingInBuffer() < 10) ** GOTO lbl18
        var9_5 = this.trans_.getBuffer();
        var3_6 = this.trans_.getBufferPosition();
        var1_1 = 0;
        do {
            var4_7 = var9_5[var3_6 + var1_1];
            var5_3 |= (long)(var4_7 & 127) << var2_2;
            if ((var4_7 & 128) != 128) {
                this.trans_.consumeBuffer(var1_1 + 1);
                return var5_3;
            }
            var2_2 += 7;
            ++var1_1;
        } while (true);
lbl-1000: // 1 sources:
        {
            var1_1 += 7;
lbl18: // 2 sources:
            var2_2 = this.readByte();
            var7_4 |= (long)(var2_2 & 127) << var1_1;
            ** while ((var2_2 & 128) == 128)
        }
lbl21: // 1 sources:
        return var7_4;
    }

    private void writeBinary(byte[] arrby, int n, int n2) throws TException {
        this.writeVarint32(n2);
        this.trans_.write(arrby, n, n2);
    }

    private void writeByteDirect(byte by) throws TException {
        this.byteDirectBuffer[0] = by;
        this.trans_.write(this.byteDirectBuffer);
    }

    private void writeByteDirect(int n) throws TException {
        this.writeByteDirect((byte)n);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void writeFieldBeginInternal(TField tField, byte by) throws TException {
        if (by == -1) {
            by = this.getCompactType(tField.type);
        }
        if (tField.id > this.lastFieldId_ && tField.id - this.lastFieldId_ <= 15) {
            this.writeByteDirect(tField.id - this.lastFieldId_ << 4 | by);
        } else {
            this.writeByteDirect(by);
            this.writeI16(tField.id);
        }
        this.lastFieldId_ = tField.id;
    }

    private void writeVarint32(int n) throws TException {
        int n2 = 0;
        int n3 = n;
        n = n2;
        do {
            if ((n3 & -128) == 0) {
                this.i32buf[n] = (byte)n3;
                this.trans_.write(this.i32buf, 0, n + 1);
                return;
            }
            this.i32buf[n] = (byte)(n3 & 127 | 128);
            n3 >>>= 7;
            ++n;
        } while (true);
    }

    private void writeVarint64(long l) throws TException {
        int n = 0;
        do {
            if ((-128 & l) == 0) {
                this.varint64out[n] = (byte)l;
                this.trans_.write(this.varint64out, 0, n + 1);
                return;
            }
            this.varint64out[n] = (byte)(127 & l | 128);
            l >>>= 7;
            ++n;
        } while (true);
    }

    private int zigzagToInt(int n) {
        return n >>> 1 ^ - (n & 1);
    }

    private long zigzagToLong(long l) {
        return l >>> 1 ^ - (1 & l);
    }

    @Override
    public ByteBuffer readBinary() throws TException {
        int n = this.readVarint32();
        if (n == 0) {
            return ByteBuffer.wrap((byte[])new byte[0]);
        }
        byte[] arrby = new byte[n];
        this.trans_.readAll(arrby, 0, n);
        return ByteBuffer.wrap((byte[])arrby);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean readBool() throws TException {
        boolean bl = true;
        if (this.boolValue_ != null) {
            bl = this.boolValue_;
            this.boolValue_ = null;
            return bl;
        }
        if (this.readByte() != 1) return false;
        return bl;
    }

    @Override
    public byte readByte() throws TException {
        if (this.trans_.getBytesRemainingInBuffer() > 0) {
            byte by = this.trans_.getBuffer()[this.trans_.getBufferPosition()];
            this.trans_.consumeBuffer(1);
            return by;
        }
        this.trans_.readAll(this.byteRawBuf, 0, 1);
        return this.byteRawBuf[0];
    }

    @Override
    public double readDouble() throws TException {
        byte[] arrby = new byte[8];
        this.trans_.readAll(arrby, 0, 8);
        return Double.longBitsToDouble((long)this.bytesToLong(arrby));
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public TField readFieldBegin() throws TException {
        byte by = this.readByte();
        if (by == 0) {
            return TSTOP;
        }
        short s = (short)((by & 240) >> 4);
        short s2 = s == 0 ? this.readI16() : (short)(this.lastFieldId_ + s);
        TField tField = new TField("", this.getTType((byte)(by & 15)), s2);
        if (this.isBoolType(by)) {
            Boolean bl = (byte)(by & 15) == 1 ? Boolean.TRUE : Boolean.FALSE;
            this.boolValue_ = bl;
        }
        this.lastFieldId_ = tField.id;
        return tField;
    }

    @Override
    public void readFieldEnd() throws TException {
    }

    @Override
    public short readI16() throws TException {
        return (short)this.zigzagToInt(this.readVarint32());
    }

    @Override
    public int readI32() throws TException {
        return this.zigzagToInt(this.readVarint32());
    }

    @Override
    public long readI64() throws TException {
        return this.zigzagToLong(this.readVarint64());
    }

    @Override
    public TList readListBegin() throws TException {
        int n;
        byte by = this.readByte();
        int n2 = n = by >> 4 & 15;
        if (n == 15) {
            n2 = this.readVarint32();
        }
        return new TList(this.getTType(by), n2);
    }

    @Override
    public void readListEnd() throws TException {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public TMap readMapBegin() throws TException {
        byte by;
        int n = this.readVarint32();
        if (n == 0) {
            by = 0;
            do {
                return new TMap(this.getTType((byte)(by >> 4)), this.getTType((byte)(by & 15)), n);
                break;
            } while (true);
        }
        by = this.readByte();
        return new TMap(this.getTType((byte)(by >> 4)), this.getTType((byte)(by & 15)), n);
    }

    @Override
    public void readMapEnd() throws TException {
    }

    @Override
    public TSet readSetBegin() throws TException {
        return new TSet(this.readListBegin());
    }

    @Override
    public void readSetEnd() throws TException {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public String readString() throws TException {
        int n = this.readVarint32();
        if (n == 0) {
            return "";
        }
        try {
            if (this.trans_.getBytesRemainingInBuffer() < n) return new String(this.readBinary(n), "UTF-8");
            String string2 = new String(this.trans_.getBuffer(), this.trans_.getBufferPosition(), n, "UTF-8");
            this.trans_.consumeBuffer(n);
            return string2;
        }
        catch (UnsupportedEncodingException var2_3) {
            throw new TException("UTF-8 not supported!");
        }
    }

    @Override
    public TStruct readStructBegin() throws TException {
        this.lastField_.push(this.lastFieldId_);
        this.lastFieldId_ = 0;
        return ANONYMOUS_STRUCT;
    }

    @Override
    public void readStructEnd() throws TException {
        this.lastFieldId_ = this.lastField_.pop();
    }

    @Override
    public void reset() {
        this.lastField_.clear();
        this.lastFieldId_ = 0;
    }

    @Override
    public void writeBinary(ByteBuffer byteBuffer) throws TException {
        int n = byteBuffer.limit();
        int n2 = byteBuffer.position();
        int n3 = byteBuffer.arrayOffset();
        this.writeBinary(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), n - n2 - n3);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void writeBool(boolean bl) throws TException {
        byte by = 1;
        byte by2 = 1;
        if (this.booleanField_ != null) {
            TField tField = this.booleanField_;
            if (!bl) {
                by2 = 2;
            }
            this.writeFieldBeginInternal(tField, by2);
            this.booleanField_ = null;
            return;
        }
        by2 = bl ? by : 2;
        this.writeByteDirect(by2);
    }

    @Override
    public void writeByte(byte by) throws TException {
        this.writeByteDirect(by);
    }

    protected void writeCollectionBegin(byte by, int n) throws TException {
        if (n <= 14) {
            this.writeByteDirect(n << 4 | this.getCompactType(by));
            return;
        }
        this.writeByteDirect(this.getCompactType(by) | 240);
        this.writeVarint32(n);
    }

    @Override
    public void writeFieldBegin(TField tField) throws TException {
        if (tField.type == 2) {
            this.booleanField_ = tField;
            return;
        }
        this.writeFieldBeginInternal(tField, -1);
    }

    @Override
    public void writeFieldEnd() throws TException {
    }

    @Override
    public void writeFieldStop() throws TException {
        this.writeByteDirect(0);
    }

    public void writeI16(short s) throws TException {
        this.writeVarint32(this.intToZigZag(s));
    }

    @Override
    public void writeI32(int n) throws TException {
        this.writeVarint32(this.intToZigZag(n));
    }

    @Override
    public void writeI64(long l) throws TException {
        this.writeVarint64(this.longToZigzag(l));
    }

    @Override
    public void writeListBegin(TList tList) throws TException {
        this.writeCollectionBegin(tList.elemType, tList.size);
    }

    @Override
    public void writeListEnd() throws TException {
    }

    @Override
    public void writeMapBegin(TMap tMap) throws TException {
        if (tMap.size == 0) {
            this.writeByteDirect(0);
            return;
        }
        this.writeVarint32(tMap.size);
        this.writeByteDirect(this.getCompactType(tMap.keyType) << 4 | this.getCompactType(tMap.valueType));
    }

    @Override
    public void writeMapEnd() throws TException {
    }

    @Override
    public void writeSetBegin(TSet tSet) throws TException {
        this.writeCollectionBegin(tSet.elemType, tSet.size);
    }

    @Override
    public void writeSetEnd() throws TException {
    }

    @Override
    public void writeString(String string2) throws TException {
        try {
            string2 = (String)string2.getBytes("UTF-8");
            this.writeBinary((byte[])string2, 0, string2.length);
            return;
        }
        catch (UnsupportedEncodingException var1_2) {
            throw new TException("UTF-8 not supported!");
        }
    }

    @Override
    public void writeStructBegin(TStruct tStruct) throws TException {
        this.lastField_.push(this.lastFieldId_);
        this.lastFieldId_ = 0;
    }

    @Override
    public void writeStructEnd() throws TException {
        this.lastFieldId_ = this.lastField_.pop();
    }

    public static class Factory
    implements TProtocolFactory {
        @Override
        public TProtocol getProtocol(TTransport tTransport) {
            return new TCompactProtocol(tTransport);
        }
    }

}

