/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.google.protobuf.micro;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import java.io.IOException;

public abstract class MessageMicro {
    public abstract int getSerializedSize();

    public abstract MessageMicro mergeFrom(CodedInputStreamMicro var1) throws IOException;

    public MessageMicro mergeFrom(byte[] arrby) throws InvalidProtocolBufferMicroException {
        return this.mergeFrom(arrby, 0, arrby.length);
    }

    public MessageMicro mergeFrom(byte[] object, int n, int n2) throws InvalidProtocolBufferMicroException {
        try {
            object = CodedInputStreamMicro.newInstance((byte[])object, n, n2);
            this.mergeFrom((CodedInputStreamMicro)object);
            object.checkLastTagWas(0);
            return this;
        }
        catch (InvalidProtocolBufferMicroException var1_2) {
            throw var1_2;
        }
        catch (IOException var1_3) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }

    protected boolean parseUnknownField(CodedInputStreamMicro codedInputStreamMicro, int n) throws IOException {
        return codedInputStreamMicro.skipField(n);
    }

    public void toByteArray(byte[] object, int n, int n2) {
        try {
            object = CodedOutputStreamMicro.newInstance((byte[])object, n, n2);
            this.writeTo((CodedOutputStreamMicro)object);
            object.checkNoSpaceLeft();
            return;
        }
        catch (IOException var1_2) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).");
        }
    }

    public byte[] toByteArray() {
        byte[] arrby = new byte[this.getSerializedSize()];
        this.toByteArray(arrby, 0, arrby.length);
        return arrby;
    }

    public abstract void writeTo(CodedOutputStreamMicro var1) throws IOException;
}

