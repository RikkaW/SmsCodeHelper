/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.Vector
 */
package com.google.protobuf.micro;

import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import com.google.protobuf.micro.WireFormatMicro;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public final class CodedInputStreamMicro {
    private final byte[] buffer;
    private int bufferPos;
    private int bufferSize;
    private int bufferSizeAfterLimit;
    private int currentLimit = Integer.MAX_VALUE;
    private final InputStream input;
    private int lastTag;
    private int recursionLimit = 64;
    private int sizeLimit = 67108864;
    private int totalBytesRetired;

    private CodedInputStreamMicro(InputStream inputStream) {
        this.buffer = new byte[4096];
        this.bufferSize = 0;
        this.bufferPos = 0;
        this.input = inputStream;
    }

    private CodedInputStreamMicro(byte[] arrby, int n, int n2) {
        this.buffer = arrby;
        this.bufferSize = n + n2;
        this.bufferPos = n;
        this.input = null;
    }

    public static CodedInputStreamMicro newInstance(InputStream inputStream) {
        return new CodedInputStreamMicro(inputStream);
    }

    public static CodedInputStreamMicro newInstance(byte[] arrby, int n, int n2) {
        return new CodedInputStreamMicro(arrby, n, n2);
    }

    private void recomputeBufferSizeAfterLimit() {
        this.bufferSize += this.bufferSizeAfterLimit;
        int n = this.totalBytesRetired + this.bufferSize;
        if (n > this.currentLimit) {
            this.bufferSizeAfterLimit = n - this.currentLimit;
            this.bufferSize -= this.bufferSizeAfterLimit;
            return;
        }
        this.bufferSizeAfterLimit = 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean refillBuffer(boolean bl) throws IOException {
        if (this.bufferPos < this.bufferSize) {
            throw new IllegalStateException("refillBuffer() called when buffer wasn't empty.");
        }
        if (this.totalBytesRetired + this.bufferSize == this.currentLimit) {
            if (bl) {
                throw InvalidProtocolBufferMicroException.truncatedMessage();
            }
            return false;
        }
        this.totalBytesRetired += this.bufferSize;
        this.bufferPos = 0;
        int n = this.input == null ? -1 : this.input.read(this.buffer);
        this.bufferSize = n;
        if (this.bufferSize == 0 || this.bufferSize < -1) {
            throw new IllegalStateException("InputStream#read(byte[]) returned invalid result: " + this.bufferSize + "\nThe InputStream implementation is buggy.");
        }
        if (this.bufferSize == -1) {
            this.bufferSize = 0;
            if (bl) {
                throw InvalidProtocolBufferMicroException.truncatedMessage();
            }
            return false;
        }
        this.recomputeBufferSizeAfterLimit();
        n = this.totalBytesRetired + this.bufferSize + this.bufferSizeAfterLimit;
        if (n <= this.sizeLimit && n >= 0) {
            return true;
        }
        throw InvalidProtocolBufferMicroException.sizeLimitExceeded();
    }

    public void checkLastTagWas(int n) throws InvalidProtocolBufferMicroException {
        if (this.lastTag != n) {
            throw InvalidProtocolBufferMicroException.invalidEndTag();
        }
    }

    public boolean isAtEnd() throws IOException {
        boolean bl;
        boolean bl2 = bl = false;
        if (this.bufferPos == this.bufferSize) {
            bl2 = bl;
            if (!this.refillBuffer(false)) {
                bl2 = true;
            }
        }
        return bl2;
    }

    public boolean readBool() throws IOException {
        if (this.readRawVarint32() != 0) {
            return true;
        }
        return false;
    }

    public int readInt32() throws IOException {
        return this.readRawVarint32();
    }

    public byte readRawByte() throws IOException {
        if (this.bufferPos == this.bufferSize) {
            this.refillBuffer(true);
        }
        byte[] arrby = this.buffer;
        int n = this.bufferPos;
        this.bufferPos = n + 1;
        return arrby[n];
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public byte[] readRawBytes(int n) throws IOException {
        byte[] arrby;
        if (n < 0) {
            throw InvalidProtocolBufferMicroException.negativeSize();
        }
        if (this.totalBytesRetired + this.bufferPos + n > this.currentLimit) {
            this.skipRawBytes(this.currentLimit - this.totalBytesRetired - this.bufferPos);
            throw InvalidProtocolBufferMicroException.truncatedMessage();
        }
        if (n <= this.bufferSize - this.bufferPos) {
            arrby = new byte[n];
            System.arraycopy((Object)this.buffer, (int)this.bufferPos, (Object)arrby, (int)0, (int)n);
            this.bufferPos += n;
            return arrby;
        }
        if (n < 4096) {
            byte[] arrby2 = new byte[n];
            int n2 = this.bufferSize - this.bufferPos;
            System.arraycopy((Object)this.buffer, (int)this.bufferPos, (Object)arrby2, (int)0, (int)n2);
            this.bufferPos = this.bufferSize;
            this.refillBuffer(true);
            do {
                if (n - n2 <= this.bufferSize) {
                    System.arraycopy((Object)this.buffer, (int)0, (Object)arrby2, (int)n2, (int)(n - n2));
                    this.bufferPos = n - n2;
                    return arrby2;
                }
                System.arraycopy((Object)this.buffer, (int)0, (Object)arrby2, (int)n2, (int)this.bufferSize);
                n2 += this.bufferSize;
                this.bufferPos = this.bufferSize;
                this.refillBuffer(true);
            } while (true);
        }
        int n3 = this.bufferPos;
        int n4 = this.bufferSize;
        this.totalBytesRetired += this.bufferSize;
        this.bufferPos = 0;
        this.bufferSize = 0;
        int n5 = n - (n4 - n3);
        Vector vector = new Vector();
        do {
            int n6;
            if (n5 > 0) {
                arrby = new byte[Math.min((int)n5, (int)4096)];
            } else {
                byte[] arrby3 = new byte[n];
                n5 = n4 - n3;
                System.arraycopy((Object)this.buffer, (int)n3, (Object)arrby3, (int)0, (int)n5);
                n = 0;
                do {
                    arrby = arrby3;
                    if (n >= vector.size()) return arrby;
                    arrby = (byte[])vector.elementAt(n);
                    System.arraycopy((Object)arrby, (int)0, (Object)arrby3, (int)n5, (int)arrby.length);
                    n5 += arrby.length;
                    ++n;
                } while (true);
            }
            for (int i = 0; i < arrby.length; this.totalBytesRetired += n6, i += n6) {
                n6 = this.input == null ? -1 : this.input.read(arrby, i, arrby.length - i);
                if (n6 != -1) continue;
                throw InvalidProtocolBufferMicroException.truncatedMessage();
            }
            n5 -= arrby.length;
            vector.addElement((Object)arrby);
        } while (true);
    }

    public int readRawLittleEndian32() throws IOException {
        return this.readRawByte() & 255 | (this.readRawByte() & 255) << 8 | (this.readRawByte() & 255) << 16 | (this.readRawByte() & 255) << 24;
    }

    public long readRawLittleEndian64() throws IOException {
        byte by = this.readRawByte();
        byte by2 = this.readRawByte();
        byte by3 = this.readRawByte();
        byte by4 = this.readRawByte();
        byte by5 = this.readRawByte();
        byte by6 = this.readRawByte();
        byte by7 = this.readRawByte();
        byte by8 = this.readRawByte();
        return (long)by & 255 | ((long)by2 & 255) << 8 | ((long)by3 & 255) << 16 | ((long)by4 & 255) << 24 | ((long)by5 & 255) << 32 | ((long)by6 & 255) << 40 | ((long)by7 & 255) << 48 | ((long)by8 & 255) << 56;
    }

    /*
     * Enabled aggressive block sorting
     */
    public int readRawVarint32() throws IOException {
        int n = this.readRawByte();
        if (n < 0) {
            n &= 127;
            int n2 = this.readRawByte();
            if (n2 >= 0) {
                return n | n2 << 7;
            }
            n |= (n2 & 127) << 7;
            n2 = this.readRawByte();
            if (n2 >= 0) {
                return n | n2 << 14;
            }
            n |= (n2 & 127) << 14;
            int n3 = this.readRawByte();
            if (n3 >= 0) {
                return n | n3 << 21;
            }
            n2 = this.readRawByte();
            n = n3 = n | (n3 & 127) << 21 | n2 << 28;
            if (n2 < 0) {
                n2 = 0;
                do {
                    if (n2 >= 5) {
                        throw InvalidProtocolBufferMicroException.malformedVarint();
                    }
                    n = n3;
                    if (this.readRawByte() >= 0) break;
                    ++n2;
                } while (true);
            }
        }
        return n;
    }

    public String readString() throws IOException {
        int n = this.readRawVarint32();
        if (n <= this.bufferSize - this.bufferPos && n > 0) {
            String string2 = new String(this.buffer, this.bufferPos, n, "UTF-8");
            this.bufferPos += n;
            return string2;
        }
        return new String(this.readRawBytes(n), "UTF-8");
    }

    public int readTag() throws IOException {
        if (this.isAtEnd()) {
            this.lastTag = 0;
            return 0;
        }
        this.lastTag = this.readRawVarint32();
        if (this.lastTag == 0) {
            throw InvalidProtocolBufferMicroException.invalidTag();
        }
        return this.lastTag;
    }

    public int readUInt32() throws IOException {
        return this.readRawVarint32();
    }

    public boolean skipField(int n) throws IOException {
        switch (WireFormatMicro.getTagWireType(n)) {
            default: {
                throw InvalidProtocolBufferMicroException.invalidWireType();
            }
            case 0: {
                this.readInt32();
                return true;
            }
            case 1: {
                this.readRawLittleEndian64();
                return true;
            }
            case 2: {
                this.skipRawBytes(this.readRawVarint32());
                return true;
            }
            case 3: {
                this.skipMessage();
                this.checkLastTagWas(WireFormatMicro.makeTag(WireFormatMicro.getTagFieldNumber(n), 4));
                return true;
            }
            case 4: {
                return false;
            }
            case 5: 
        }
        this.readRawLittleEndian32();
        return true;
    }

    public void skipMessage() throws IOException {
        int n;
        while ((n = this.readTag()) != 0 && this.skipField(n)) {
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void skipRawBytes(int n) throws IOException {
        if (n < 0) {
            throw InvalidProtocolBufferMicroException.negativeSize();
        }
        if (this.totalBytesRetired + this.bufferPos + n > this.currentLimit) {
            this.skipRawBytes(this.currentLimit - this.totalBytesRetired - this.bufferPos);
            throw InvalidProtocolBufferMicroException.truncatedMessage();
        }
        if (n <= this.bufferSize - this.bufferPos) {
            this.bufferPos += n;
            return;
        }
        int n2 = this.bufferSize - this.bufferPos;
        this.totalBytesRetired += this.bufferSize;
        this.bufferPos = 0;
        this.bufferSize = 0;
        while (n2 < n) {
            int n3 = this.input == null ? -1 : (int)this.input.skip(n - n2);
            if (n3 <= 0) {
                throw InvalidProtocolBufferMicroException.truncatedMessage();
            }
            n2 += n3;
            this.totalBytesRetired += n3;
        }
        return;
    }
}

