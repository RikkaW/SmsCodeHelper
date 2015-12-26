/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.OutputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package com.google.protobuf.micro;

import com.google.protobuf.micro.WireFormatMicro;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public final class CodedOutputStreamMicro {
    private final byte[] buffer;
    private final int limit;
    private final OutputStream output;
    private int position;

    private CodedOutputStreamMicro(OutputStream outputStream, byte[] arrby) {
        this.output = outputStream;
        this.buffer = arrby;
        this.position = 0;
        this.limit = arrby.length;
    }

    private CodedOutputStreamMicro(byte[] arrby, int n, int n2) {
        this.output = null;
        this.buffer = arrby;
        this.position = n;
        this.limit = n + n2;
    }

    public static int computeBoolSize(int n, boolean bl) {
        return CodedOutputStreamMicro.computeTagSize(n) + CodedOutputStreamMicro.computeBoolSizeNoTag(bl);
    }

    public static int computeBoolSizeNoTag(boolean bl) {
        return 1;
    }

    public static int computeInt32Size(int n, int n2) {
        return CodedOutputStreamMicro.computeTagSize(n) + CodedOutputStreamMicro.computeInt32SizeNoTag(n2);
    }

    public static int computeInt32SizeNoTag(int n) {
        if (n >= 0) {
            return CodedOutputStreamMicro.computeRawVarint32Size(n);
        }
        return 10;
    }

    public static int computeRawVarint32Size(int n) {
        if ((n & -128) == 0) {
            return 1;
        }
        if ((n & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & n) == 0) {
            return 3;
        }
        if ((-268435456 & n) == 0) {
            return 4;
        }
        return 5;
    }

    public static int computeStringSizeNoTag(String string2) {
        try {
            string2 = (String)string2.getBytes("UTF-8");
            int n = CodedOutputStreamMicro.computeRawVarint32Size(string2.length);
            int n2 = string2.length;
            return n + n2;
        }
        catch (UnsupportedEncodingException var0_1) {
            throw new RuntimeException("UTF-8 not supported.");
        }
    }

    public static int computeTagSize(int n) {
        return CodedOutputStreamMicro.computeRawVarint32Size(WireFormatMicro.makeTag(n, 0));
    }

    public static int computeUInt32Size(int n, int n2) {
        return CodedOutputStreamMicro.computeTagSize(n) + CodedOutputStreamMicro.computeUInt32SizeNoTag(n2);
    }

    public static int computeUInt32SizeNoTag(int n) {
        return CodedOutputStreamMicro.computeRawVarint32Size(n);
    }

    public static CodedOutputStreamMicro newInstance(OutputStream outputStream) {
        return CodedOutputStreamMicro.newInstance(outputStream, 4096);
    }

    public static CodedOutputStreamMicro newInstance(OutputStream outputStream, int n) {
        return new CodedOutputStreamMicro(outputStream, new byte[n]);
    }

    public static CodedOutputStreamMicro newInstance(byte[] arrby, int n, int n2) {
        return new CodedOutputStreamMicro(arrby, n, n2);
    }

    private void refreshBuffer() throws IOException {
        if (this.output == null) {
            throw new OutOfSpaceException();
        }
        this.output.write(this.buffer, 0, this.position);
        this.position = 0;
    }

    public void checkNoSpaceLeft() {
        if (this.spaceLeft() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public void flush() throws IOException {
        if (this.output != null) {
            this.refreshBuffer();
        }
    }

    public int spaceLeft() {
        if (this.output == null) {
            return this.limit - this.position;
        }
        throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array.");
    }

    public void writeBool(int n, boolean bl) throws IOException {
        this.writeTag(n, 0);
        this.writeBoolNoTag(bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeBoolNoTag(boolean bl) throws IOException {
        int n = bl ? 1 : 0;
        this.writeRawByte(n);
    }

    public void writeInt32(int n, int n2) throws IOException {
        this.writeTag(n, 0);
        this.writeInt32NoTag(n2);
    }

    public void writeInt32NoTag(int n) throws IOException {
        if (n >= 0) {
            this.writeRawVarint32(n);
            return;
        }
        this.writeRawVarint64(n);
    }

    public void writeRawByte(byte by) throws IOException {
        if (this.position == this.limit) {
            this.refreshBuffer();
        }
        byte[] arrby = this.buffer;
        int n = this.position;
        this.position = n + 1;
        arrby[n] = by;
    }

    public void writeRawByte(int n) throws IOException {
        this.writeRawByte((byte)n);
    }

    public void writeRawBytes(byte[] arrby) throws IOException {
        this.writeRawBytes(arrby, 0, arrby.length);
    }

    public void writeRawBytes(byte[] arrby, int n, int n2) throws IOException {
        if (this.limit - this.position >= n2) {
            System.arraycopy((Object)arrby, (int)n, (Object)this.buffer, (int)this.position, (int)n2);
            this.position += n2;
            return;
        }
        int n3 = this.limit - this.position;
        System.arraycopy((Object)arrby, (int)n, (Object)this.buffer, (int)this.position, (int)n3);
        n += n3;
        this.position = this.limit;
        this.refreshBuffer();
        if ((n2 -= n3) <= this.limit) {
            System.arraycopy((Object)arrby, (int)n, (Object)this.buffer, (int)0, (int)n2);
            this.position = n2;
            return;
        }
        this.output.write(arrby, n, n2);
    }

    public void writeRawVarint32(int n) throws IOException {
        do {
            if ((n & -128) == 0) {
                this.writeRawByte(n);
                return;
            }
            this.writeRawByte(n & 127 | 128);
            n >>>= 7;
        } while (true);
    }

    public void writeRawVarint64(long l) throws IOException {
        do {
            if ((-128 & l) == 0) {
                this.writeRawByte((int)l);
                return;
            }
            this.writeRawByte((int)l & 127 | 128);
            l >>>= 7;
        } while (true);
    }

    public void writeString(int n, String string2) throws IOException {
        this.writeTag(n, 2);
        this.writeStringNoTag(string2);
    }

    public void writeStringNoTag(String string2) throws IOException {
        string2 = (String)string2.getBytes("UTF-8");
        this.writeRawVarint32(string2.length);
        this.writeRawBytes((byte[])string2);
    }

    public void writeTag(int n, int n2) throws IOException {
        this.writeRawVarint32(WireFormatMicro.makeTag(n, n2));
    }

    public void writeUInt32(int n, int n2) throws IOException {
        this.writeTag(n, 0);
        this.writeUInt32NoTag(n2);
    }

    public void writeUInt32NoTag(int n) throws IOException {
        this.writeRawVarint32(n);
    }

    public static class OutOfSpaceException
    extends IOException {
        private static final long serialVersionUID = -6947486886997889499L;

        OutOfSpaceException() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }
    }

}

