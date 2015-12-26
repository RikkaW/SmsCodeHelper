/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.nio.ByteBuffer
 */
package org.apache.thrift.protocol;

import java.nio.ByteBuffer;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TSet;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.transport.TTransport;

public abstract class TProtocol {
    protected TTransport trans_;

    private TProtocol() {
    }

    protected TProtocol(TTransport tTransport) {
        this.trans_ = tTransport;
    }

    public abstract ByteBuffer readBinary() throws TException;

    public abstract boolean readBool() throws TException;

    public abstract byte readByte() throws TException;

    public abstract double readDouble() throws TException;

    public abstract TField readFieldBegin() throws TException;

    public abstract void readFieldEnd() throws TException;

    public abstract short readI16() throws TException;

    public abstract int readI32() throws TException;

    public abstract long readI64() throws TException;

    public abstract TList readListBegin() throws TException;

    public abstract void readListEnd() throws TException;

    public abstract TMap readMapBegin() throws TException;

    public abstract void readMapEnd() throws TException;

    public abstract TSet readSetBegin() throws TException;

    public abstract void readSetEnd() throws TException;

    public abstract String readString() throws TException;

    public abstract TStruct readStructBegin() throws TException;

    public abstract void readStructEnd() throws TException;

    public void reset() {
    }

    public abstract void writeBinary(ByteBuffer var1) throws TException;

    public abstract void writeBool(boolean var1) throws TException;

    public abstract void writeByte(byte var1) throws TException;

    public abstract void writeFieldBegin(TField var1) throws TException;

    public abstract void writeFieldEnd() throws TException;

    public abstract void writeFieldStop() throws TException;

    public abstract void writeI32(int var1) throws TException;

    public abstract void writeI64(long var1) throws TException;

    public abstract void writeListBegin(TList var1) throws TException;

    public abstract void writeListEnd() throws TException;

    public abstract void writeMapBegin(TMap var1) throws TException;

    public abstract void writeMapEnd() throws TException;

    public abstract void writeSetBegin(TSet var1) throws TException;

    public abstract void writeSetEnd() throws TException;

    public abstract void writeString(String var1) throws TException;

    public abstract void writeStructBegin(TStruct var1) throws TException;

    public abstract void writeStructEnd() throws TException;
}

