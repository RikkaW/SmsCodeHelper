package org.apache.thrift.protocol;

import java.nio.ByteBuffer;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;

public abstract class TProtocol
{
  protected TTransport trans_;
  
  private TProtocol() {}
  
  protected TProtocol(TTransport paramTTransport)
  {
    trans_ = paramTTransport;
  }
  
  public abstract ByteBuffer readBinary()
    throws TException;
  
  public abstract boolean readBool()
    throws TException;
  
  public abstract byte readByte()
    throws TException;
  
  public abstract double readDouble()
    throws TException;
  
  public abstract TField readFieldBegin()
    throws TException;
  
  public abstract void readFieldEnd()
    throws TException;
  
  public abstract short readI16()
    throws TException;
  
  public abstract int readI32()
    throws TException;
  
  public abstract long readI64()
    throws TException;
  
  public abstract TList readListBegin()
    throws TException;
  
  public abstract void readListEnd()
    throws TException;
  
  public abstract TMap readMapBegin()
    throws TException;
  
  public abstract void readMapEnd()
    throws TException;
  
  public abstract TSet readSetBegin()
    throws TException;
  
  public abstract void readSetEnd()
    throws TException;
  
  public abstract String readString()
    throws TException;
  
  public abstract TStruct readStructBegin()
    throws TException;
  
  public abstract void readStructEnd()
    throws TException;
  
  public void reset() {}
  
  public abstract void writeBinary(ByteBuffer paramByteBuffer)
    throws TException;
  
  public abstract void writeBool(boolean paramBoolean)
    throws TException;
  
  public abstract void writeByte(byte paramByte)
    throws TException;
  
  public abstract void writeFieldBegin(TField paramTField)
    throws TException;
  
  public abstract void writeFieldEnd()
    throws TException;
  
  public abstract void writeFieldStop()
    throws TException;
  
  public abstract void writeI32(int paramInt)
    throws TException;
  
  public abstract void writeI64(long paramLong)
    throws TException;
  
  public abstract void writeListBegin(TList paramTList)
    throws TException;
  
  public abstract void writeListEnd()
    throws TException;
  
  public abstract void writeMapBegin(TMap paramTMap)
    throws TException;
  
  public abstract void writeMapEnd()
    throws TException;
  
  public abstract void writeSetBegin(TSet paramTSet)
    throws TException;
  
  public abstract void writeSetEnd()
    throws TException;
  
  public abstract void writeString(String paramString)
    throws TException;
  
  public abstract void writeStructBegin(TStruct paramTStruct)
    throws TException;
  
  public abstract void writeStructEnd()
    throws TException;
}

/* Location:
 * Qualified Name:     org.apache.thrift.protocol.TProtocol
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */