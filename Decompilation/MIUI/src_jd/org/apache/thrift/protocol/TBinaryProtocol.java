package org.apache.thrift.protocol;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;

public class TBinaryProtocol
  extends TProtocol
{
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
  
  public TBinaryProtocol(TTransport paramTTransport)
  {
    this(paramTTransport, false, true);
  }
  
  public TBinaryProtocol(TTransport paramTTransport, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramTTransport);
    strictRead_ = paramBoolean1;
    strictWrite_ = paramBoolean2;
  }
  
  private int readAll(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws TException
  {
    checkReadLength(paramInt2);
    return trans_.readAll(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  protected void checkReadLength(int paramInt)
    throws TException
  {
    if (paramInt < 0) {
      throw new TException("Negative length: " + paramInt);
    }
    if (checkReadLength_)
    {
      readLength_ -= paramInt;
      if (readLength_ < 0) {
        throw new TException("Message length exceeded: " + paramInt);
      }
    }
  }
  
  public ByteBuffer readBinary()
    throws TException
  {
    int i = readI32();
    checkReadLength(i);
    if (trans_.getBytesRemainingInBuffer() >= i)
    {
      localObject = ByteBuffer.wrap(trans_.getBuffer(), trans_.getBufferPosition(), i);
      trans_.consumeBuffer(i);
      return (ByteBuffer)localObject;
    }
    Object localObject = new byte[i];
    trans_.readAll((byte[])localObject, 0, i);
    return ByteBuffer.wrap((byte[])localObject);
  }
  
  public boolean readBool()
    throws TException
  {
    return readByte() == 1;
  }
  
  public byte readByte()
    throws TException
  {
    if (trans_.getBytesRemainingInBuffer() >= 1)
    {
      byte b = trans_.getBuffer()[trans_.getBufferPosition()];
      trans_.consumeBuffer(1);
      return b;
    }
    readAll(bin, 0, 1);
    return bin[0];
  }
  
  public double readDouble()
    throws TException
  {
    return Double.longBitsToDouble(readI64());
  }
  
  public TField readFieldBegin()
    throws TException
  {
    byte b = readByte();
    if (b == 0) {}
    for (short s = 0;; s = readI16()) {
      return new TField("", b, s);
    }
  }
  
  public void readFieldEnd() {}
  
  public short readI16()
    throws TException
  {
    byte[] arrayOfByte = i16rd;
    int i = 0;
    if (trans_.getBytesRemainingInBuffer() >= 2)
    {
      arrayOfByte = trans_.getBuffer();
      i = trans_.getBufferPosition();
      trans_.consumeBuffer(2);
    }
    for (;;)
    {
      return (short)((arrayOfByte[i] & 0xFF) << 8 | arrayOfByte[(i + 1)] & 0xFF);
      readAll(i16rd, 0, 2);
    }
  }
  
  public int readI32()
    throws TException
  {
    byte[] arrayOfByte = i32rd;
    int i = 0;
    if (trans_.getBytesRemainingInBuffer() >= 4)
    {
      arrayOfByte = trans_.getBuffer();
      i = trans_.getBufferPosition();
      trans_.consumeBuffer(4);
    }
    for (;;)
    {
      return (arrayOfByte[i] & 0xFF) << 24 | (arrayOfByte[(i + 1)] & 0xFF) << 16 | (arrayOfByte[(i + 2)] & 0xFF) << 8 | arrayOfByte[(i + 3)] & 0xFF;
      readAll(i32rd, 0, 4);
    }
  }
  
  public long readI64()
    throws TException
  {
    byte[] arrayOfByte = i64rd;
    int i = 0;
    if (trans_.getBytesRemainingInBuffer() >= 8)
    {
      arrayOfByte = trans_.getBuffer();
      i = trans_.getBufferPosition();
      trans_.consumeBuffer(8);
    }
    for (;;)
    {
      return (arrayOfByte[i] & 0xFF) << 56 | (arrayOfByte[(i + 1)] & 0xFF) << 48 | (arrayOfByte[(i + 2)] & 0xFF) << 40 | (arrayOfByte[(i + 3)] & 0xFF) << 32 | (arrayOfByte[(i + 4)] & 0xFF) << 24 | (arrayOfByte[(i + 5)] & 0xFF) << 16 | (arrayOfByte[(i + 6)] & 0xFF) << 8 | arrayOfByte[(i + 7)] & 0xFF;
      readAll(i64rd, 0, 8);
    }
  }
  
  public TList readListBegin()
    throws TException
  {
    return new TList(readByte(), readI32());
  }
  
  public void readListEnd() {}
  
  public TMap readMapBegin()
    throws TException
  {
    return new TMap(readByte(), readByte(), readI32());
  }
  
  public void readMapEnd() {}
  
  public TSet readSetBegin()
    throws TException
  {
    return new TSet(readByte(), readI32());
  }
  
  public void readSetEnd() {}
  
  public String readString()
    throws TException
  {
    int i = readI32();
    if (trans_.getBytesRemainingInBuffer() >= i) {
      try
      {
        String str = new String(trans_.getBuffer(), trans_.getBufferPosition(), i, "UTF-8");
        trans_.consumeBuffer(i);
        return str;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        throw new TException("JVM DOES NOT SUPPORT UTF-8");
      }
    }
    return readStringBody(i);
  }
  
  public String readStringBody(int paramInt)
    throws TException
  {
    try
    {
      checkReadLength(paramInt);
      Object localObject = new byte[paramInt];
      trans_.readAll((byte[])localObject, 0, paramInt);
      localObject = new String((byte[])localObject, "UTF-8");
      return (String)localObject;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new TException("JVM DOES NOT SUPPORT UTF-8");
    }
  }
  
  public TStruct readStructBegin()
  {
    return ANONYMOUS_STRUCT;
  }
  
  public void readStructEnd() {}
  
  public void setReadLength(int paramInt)
  {
    readLength_ = paramInt;
    checkReadLength_ = true;
  }
  
  public void writeBinary(ByteBuffer paramByteBuffer)
    throws TException
  {
    int i = paramByteBuffer.limit() - paramByteBuffer.position() - paramByteBuffer.arrayOffset();
    writeI32(i);
    trans_.write(paramByteBuffer.array(), paramByteBuffer.position() + paramByteBuffer.arrayOffset(), i);
  }
  
  public void writeBool(boolean paramBoolean)
    throws TException
  {
    if (paramBoolean) {}
    for (byte b = 1;; b = 0)
    {
      writeByte(b);
      return;
    }
  }
  
  public void writeByte(byte paramByte)
    throws TException
  {
    bout[0] = paramByte;
    trans_.write(bout, 0, 1);
  }
  
  public void writeFieldBegin(TField paramTField)
    throws TException
  {
    writeByte(type);
    writeI16(id);
  }
  
  public void writeFieldEnd() {}
  
  public void writeFieldStop()
    throws TException
  {
    writeByte((byte)0);
  }
  
  public void writeI16(short paramShort)
    throws TException
  {
    i16out[0] = ((byte)(paramShort >> 8 & 0xFF));
    i16out[1] = ((byte)(paramShort & 0xFF));
    trans_.write(i16out, 0, 2);
  }
  
  public void writeI32(int paramInt)
    throws TException
  {
    i32out[0] = ((byte)(paramInt >> 24 & 0xFF));
    i32out[1] = ((byte)(paramInt >> 16 & 0xFF));
    i32out[2] = ((byte)(paramInt >> 8 & 0xFF));
    i32out[3] = ((byte)(paramInt & 0xFF));
    trans_.write(i32out, 0, 4);
  }
  
  public void writeI64(long paramLong)
    throws TException
  {
    i64out[0] = ((byte)(int)(paramLong >> 56 & 0xFF));
    i64out[1] = ((byte)(int)(paramLong >> 48 & 0xFF));
    i64out[2] = ((byte)(int)(paramLong >> 40 & 0xFF));
    i64out[3] = ((byte)(int)(paramLong >> 32 & 0xFF));
    i64out[4] = ((byte)(int)(paramLong >> 24 & 0xFF));
    i64out[5] = ((byte)(int)(paramLong >> 16 & 0xFF));
    i64out[6] = ((byte)(int)(paramLong >> 8 & 0xFF));
    i64out[7] = ((byte)(int)(0xFF & paramLong));
    trans_.write(i64out, 0, 8);
  }
  
  public void writeListBegin(TList paramTList)
    throws TException
  {
    writeByte(elemType);
    writeI32(size);
  }
  
  public void writeListEnd() {}
  
  public void writeMapBegin(TMap paramTMap)
    throws TException
  {
    writeByte(keyType);
    writeByte(valueType);
    writeI32(size);
  }
  
  public void writeMapEnd() {}
  
  public void writeSetBegin(TSet paramTSet)
    throws TException
  {
    writeByte(elemType);
    writeI32(size);
  }
  
  public void writeSetEnd() {}
  
  public void writeString(String paramString)
    throws TException
  {
    try
    {
      paramString = paramString.getBytes("UTF-8");
      writeI32(paramString.length);
      trans_.write(paramString, 0, paramString.length);
      return;
    }
    catch (UnsupportedEncodingException paramString)
    {
      throw new TException("JVM DOES NOT SUPPORT UTF-8");
    }
  }
  
  public void writeStructBegin(TStruct paramTStruct) {}
  
  public void writeStructEnd() {}
  
  public static class Factory
    implements TProtocolFactory
  {
    protected int readLength_;
    protected boolean strictRead_ = false;
    protected boolean strictWrite_ = true;
    
    public Factory()
    {
      this(false, true);
    }
    
    public Factory(boolean paramBoolean1, boolean paramBoolean2)
    {
      this(paramBoolean1, paramBoolean2, 0);
    }
    
    public Factory(boolean paramBoolean1, boolean paramBoolean2, int paramInt)
    {
      strictRead_ = paramBoolean1;
      strictWrite_ = paramBoolean2;
      readLength_ = paramInt;
    }
    
    public TProtocol getProtocol(TTransport paramTTransport)
    {
      paramTTransport = new TBinaryProtocol(paramTTransport, strictRead_, strictWrite_);
      if (readLength_ != 0) {
        paramTTransport.setReadLength(readLength_);
      }
      return paramTTransport;
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.protocol.TBinaryProtocol
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */