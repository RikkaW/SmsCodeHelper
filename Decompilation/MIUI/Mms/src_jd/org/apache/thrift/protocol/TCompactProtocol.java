package org.apache.thrift.protocol;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.apache.thrift.ShortStack;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;

public final class TCompactProtocol
  extends TProtocol
{
  private static final TStruct ANONYMOUS_STRUCT = new TStruct("");
  private static final TField TSTOP = new TField("", (byte)0, (short)0);
  private static final byte[] ttypeToCompactType = new byte[16];
  private Boolean boolValue_ = null;
  private TField booleanField_ = null;
  private byte[] byteDirectBuffer = new byte[1];
  byte[] byteRawBuf = new byte[1];
  byte[] i32buf = new byte[5];
  private short lastFieldId_ = 0;
  private ShortStack lastField_ = new ShortStack(15);
  byte[] varint64out = new byte[10];
  
  static
  {
    ttypeToCompactType[0] = 0;
    ttypeToCompactType[2] = 1;
    ttypeToCompactType[3] = 3;
    ttypeToCompactType[6] = 4;
    ttypeToCompactType[8] = 5;
    ttypeToCompactType[10] = 6;
    ttypeToCompactType[4] = 7;
    ttypeToCompactType[11] = 8;
    ttypeToCompactType[15] = 9;
    ttypeToCompactType[14] = 10;
    ttypeToCompactType[13] = 11;
    ttypeToCompactType[12] = 12;
  }
  
  public TCompactProtocol(TTransport paramTTransport)
  {
    super(paramTTransport);
  }
  
  private long bytesToLong(byte[] paramArrayOfByte)
  {
    return (paramArrayOfByte[7] & 0xFF) << 56 | (paramArrayOfByte[6] & 0xFF) << 48 | (paramArrayOfByte[5] & 0xFF) << 40 | (paramArrayOfByte[4] & 0xFF) << 32 | (paramArrayOfByte[3] & 0xFF) << 24 | (paramArrayOfByte[2] & 0xFF) << 16 | (paramArrayOfByte[1] & 0xFF) << 8 | paramArrayOfByte[0] & 0xFF;
  }
  
  private byte getCompactType(byte paramByte)
  {
    return ttypeToCompactType[paramByte];
  }
  
  private byte getTType(byte paramByte)
    throws TProtocolException
  {
    switch ((byte)(paramByte & 0xF))
    {
    default: 
      throw new TProtocolException("don't know what type: " + (byte)(paramByte & 0xF));
    case 0: 
      return 0;
    case 1: 
    case 2: 
      return 2;
    case 3: 
      return 3;
    case 4: 
      return 6;
    case 5: 
      return 8;
    case 6: 
      return 10;
    case 7: 
      return 4;
    case 8: 
      return 11;
    case 9: 
      return 15;
    case 10: 
      return 14;
    case 11: 
      return 13;
    }
    return 12;
  }
  
  private int intToZigZag(int paramInt)
  {
    return paramInt << 1 ^ paramInt >> 31;
  }
  
  private boolean isBoolType(byte paramByte)
  {
    paramByte &= 0xF;
    return (paramByte == 1) || (paramByte == 2);
  }
  
  private long longToZigzag(long paramLong)
  {
    return paramLong << 1 ^ paramLong >> 63;
  }
  
  private byte[] readBinary(int paramInt)
    throws TException
  {
    if (paramInt == 0) {
      return new byte[0];
    }
    byte[] arrayOfByte = new byte[paramInt];
    trans_.readAll(arrayOfByte, 0, paramInt);
    return arrayOfByte;
  }
  
  private int readVarint32()
    throws TException
  {
    int i = 0;
    int k = 0;
    int j = 0;
    int m = 0;
    if (trans_.getBytesRemainingInBuffer() >= 5)
    {
      byte[] arrayOfByte = trans_.getBuffer();
      int n = trans_.getBufferPosition();
      j = 0;
      i = m;
      for (;;)
      {
        m = arrayOfByte[(n + j)];
        k |= (m & 0x7F) << i;
        if ((m & 0x80) != 128)
        {
          trans_.consumeBuffer(j + 1);
          return k;
        }
        i += 7;
        j += 1;
      }
    }
    do
    {
      j += 7;
      k = readByte();
      i |= (k & 0x7F) << j;
    } while ((k & 0x80) == 128);
    return i;
  }
  
  private long readVarint64()
    throws TException
  {
    int i = 0;
    int j = 0;
    long l1 = 0L;
    long l2 = l1;
    if (trans_.getBytesRemainingInBuffer() >= 10)
    {
      byte[] arrayOfByte = trans_.getBuffer();
      int k = trans_.getBufferPosition();
      i = 0;
      for (;;)
      {
        int m = arrayOfByte[(k + i)];
        l1 |= (m & 0x7F) << j;
        if ((m & 0x80) != 128)
        {
          trans_.consumeBuffer(i + 1);
          return l1;
        }
        j += 7;
        i += 1;
      }
    }
    do
    {
      i += 7;
      j = readByte();
      l2 |= (j & 0x7F) << i;
    } while ((j & 0x80) == 128);
    return l2;
  }
  
  private void writeBinary(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws TException
  {
    writeVarint32(paramInt2);
    trans_.write(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  private void writeByteDirect(byte paramByte)
    throws TException
  {
    byteDirectBuffer[0] = paramByte;
    trans_.write(byteDirectBuffer);
  }
  
  private void writeByteDirect(int paramInt)
    throws TException
  {
    writeByteDirect((byte)paramInt);
  }
  
  private void writeFieldBeginInternal(TField paramTField, byte paramByte)
    throws TException
  {
    if (paramByte == -1)
    {
      paramByte = getCompactType(type);
      if ((id <= lastFieldId_) || (id - lastFieldId_ > 15)) {
        break label68;
      }
      writeByteDirect(id - lastFieldId_ << 4 | paramByte);
    }
    for (;;)
    {
      lastFieldId_ = id;
      return;
      break;
      label68:
      writeByteDirect(paramByte);
      writeI16(id);
    }
  }
  
  private void writeVarint32(int paramInt)
    throws TException
  {
    int j = 0;
    int i = paramInt;
    paramInt = j;
    for (;;)
    {
      if ((i & 0xFFFFFF80) == 0)
      {
        i32buf[paramInt] = ((byte)i);
        trans_.write(i32buf, 0, paramInt + 1);
        return;
      }
      i32buf[paramInt] = ((byte)(i & 0x7F | 0x80));
      i >>>= 7;
      paramInt += 1;
    }
  }
  
  private void writeVarint64(long paramLong)
    throws TException
  {
    int i = 0;
    for (;;)
    {
      if ((0xFFFFFFFFFFFFFF80 & paramLong) == 0L)
      {
        varint64out[i] = ((byte)(int)paramLong);
        trans_.write(varint64out, 0, i + 1);
        return;
      }
      varint64out[i] = ((byte)(int)(0x7F & paramLong | 0x80));
      paramLong >>>= 7;
      i += 1;
    }
  }
  
  private int zigzagToInt(int paramInt)
  {
    return paramInt >>> 1 ^ -(paramInt & 0x1);
  }
  
  private long zigzagToLong(long paramLong)
  {
    return paramLong >>> 1 ^ -(1L & paramLong);
  }
  
  public ByteBuffer readBinary()
    throws TException
  {
    int i = readVarint32();
    if (i == 0) {
      return ByteBuffer.wrap(new byte[0]);
    }
    byte[] arrayOfByte = new byte[i];
    trans_.readAll(arrayOfByte, 0, i);
    return ByteBuffer.wrap(arrayOfByte);
  }
  
  public boolean readBool()
    throws TException
  {
    boolean bool = true;
    if (boolValue_ != null)
    {
      bool = boolValue_.booleanValue();
      boolValue_ = null;
      return bool;
    }
    if (readByte() == 1) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  public byte readByte()
    throws TException
  {
    if (trans_.getBytesRemainingInBuffer() > 0)
    {
      byte b = trans_.getBuffer()[trans_.getBufferPosition()];
      trans_.consumeBuffer(1);
      return b;
    }
    trans_.readAll(byteRawBuf, 0, 1);
    return byteRawBuf[0];
  }
  
  public double readDouble()
    throws TException
  {
    byte[] arrayOfByte = new byte[8];
    trans_.readAll(arrayOfByte, 0, 8);
    return Double.longBitsToDouble(bytesToLong(arrayOfByte));
  }
  
  public TField readFieldBegin()
    throws TException
  {
    int i = readByte();
    if (i == 0) {
      return TSTOP;
    }
    int j = (short)((i & 0xF0) >> 4);
    short s;
    TField localTField;
    if (j == 0)
    {
      s = readI16();
      localTField = new TField("", getTType((byte)(i & 0xF)), s);
      if (isBoolType(i)) {
        if ((byte)(i & 0xF) != 1) {
          break label103;
        }
      }
    }
    label103:
    for (Boolean localBoolean = Boolean.TRUE;; localBoolean = Boolean.FALSE)
    {
      boolValue_ = localBoolean;
      lastFieldId_ = id;
      return localTField;
      s = (short)(lastFieldId_ + j);
      break;
    }
  }
  
  public void readFieldEnd()
    throws TException
  {}
  
  public short readI16()
    throws TException
  {
    return (short)zigzagToInt(readVarint32());
  }
  
  public int readI32()
    throws TException
  {
    return zigzagToInt(readVarint32());
  }
  
  public long readI64()
    throws TException
  {
    return zigzagToLong(readVarint64());
  }
  
  public TList readListBegin()
    throws TException
  {
    byte b = readByte();
    int j = b >> 4 & 0xF;
    int i = j;
    if (j == 15) {
      i = readVarint32();
    }
    return new TList(getTType(b), i);
  }
  
  public void readListEnd()
    throws TException
  {}
  
  public TMap readMapBegin()
    throws TException
  {
    int j = readVarint32();
    if (j == 0) {}
    for (int i = 0;; i = readByte()) {
      return new TMap(getTType((byte)(i >> 4)), getTType((byte)(i & 0xF)), j);
    }
  }
  
  public void readMapEnd()
    throws TException
  {}
  
  public TSet readSetBegin()
    throws TException
  {
    return new TSet(readListBegin());
  }
  
  public void readSetEnd()
    throws TException
  {}
  
  public String readString()
    throws TException
  {
    int i = readVarint32();
    if (i == 0) {
      return "";
    }
    try
    {
      if (trans_.getBytesRemainingInBuffer() >= i)
      {
        String str1 = new String(trans_.getBuffer(), trans_.getBufferPosition(), i, "UTF-8");
        trans_.consumeBuffer(i);
        return str1;
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new TException("UTF-8 not supported!");
    }
    String str2 = new String(readBinary(i), "UTF-8");
    return str2;
  }
  
  public TStruct readStructBegin()
    throws TException
  {
    lastField_.push(lastFieldId_);
    lastFieldId_ = 0;
    return ANONYMOUS_STRUCT;
  }
  
  public void readStructEnd()
    throws TException
  {
    lastFieldId_ = lastField_.pop();
  }
  
  public void reset()
  {
    lastField_.clear();
    lastFieldId_ = 0;
  }
  
  public void writeBinary(ByteBuffer paramByteBuffer)
    throws TException
  {
    int i = paramByteBuffer.limit();
    int j = paramByteBuffer.position();
    int k = paramByteBuffer.arrayOffset();
    writeBinary(paramByteBuffer.array(), paramByteBuffer.position() + paramByteBuffer.arrayOffset(), i - j - k);
  }
  
  public void writeBool(boolean paramBoolean)
    throws TException
  {
    byte b2 = 1;
    byte b1 = 1;
    if (booleanField_ != null)
    {
      TField localTField = booleanField_;
      if (paramBoolean) {}
      for (;;)
      {
        writeFieldBeginInternal(localTField, b1);
        booleanField_ = null;
        return;
        b1 = 2;
      }
    }
    if (paramBoolean) {}
    for (b1 = b2;; b1 = 2)
    {
      writeByteDirect(b1);
      return;
    }
  }
  
  public void writeByte(byte paramByte)
    throws TException
  {
    writeByteDirect(paramByte);
  }
  
  protected void writeCollectionBegin(byte paramByte, int paramInt)
    throws TException
  {
    if (paramInt <= 14)
    {
      writeByteDirect(paramInt << 4 | getCompactType(paramByte));
      return;
    }
    writeByteDirect(getCompactType(paramByte) | 0xF0);
    writeVarint32(paramInt);
  }
  
  public void writeFieldBegin(TField paramTField)
    throws TException
  {
    if (type == 2)
    {
      booleanField_ = paramTField;
      return;
    }
    writeFieldBeginInternal(paramTField, (byte)-1);
  }
  
  public void writeFieldEnd()
    throws TException
  {}
  
  public void writeFieldStop()
    throws TException
  {
    writeByteDirect((byte)0);
  }
  
  public void writeI16(short paramShort)
    throws TException
  {
    writeVarint32(intToZigZag(paramShort));
  }
  
  public void writeI32(int paramInt)
    throws TException
  {
    writeVarint32(intToZigZag(paramInt));
  }
  
  public void writeI64(long paramLong)
    throws TException
  {
    writeVarint64(longToZigzag(paramLong));
  }
  
  public void writeListBegin(TList paramTList)
    throws TException
  {
    writeCollectionBegin(elemType, size);
  }
  
  public void writeListEnd()
    throws TException
  {}
  
  public void writeMapBegin(TMap paramTMap)
    throws TException
  {
    if (size == 0)
    {
      writeByteDirect(0);
      return;
    }
    writeVarint32(size);
    writeByteDirect(getCompactType(keyType) << 4 | getCompactType(valueType));
  }
  
  public void writeMapEnd()
    throws TException
  {}
  
  public void writeSetBegin(TSet paramTSet)
    throws TException
  {
    writeCollectionBegin(elemType, size);
  }
  
  public void writeSetEnd()
    throws TException
  {}
  
  public void writeString(String paramString)
    throws TException
  {
    try
    {
      paramString = paramString.getBytes("UTF-8");
      writeBinary(paramString, 0, paramString.length);
      return;
    }
    catch (UnsupportedEncodingException paramString)
    {
      throw new TException("UTF-8 not supported!");
    }
  }
  
  public void writeStructBegin(TStruct paramTStruct)
    throws TException
  {
    lastField_.push(lastFieldId_);
    lastFieldId_ = 0;
  }
  
  public void writeStructEnd()
    throws TException
  {
    lastFieldId_ = lastField_.pop();
  }
  
  public static class Factory
    implements TProtocolFactory
  {
    public TProtocol getProtocol(TTransport paramTTransport)
    {
      return new TCompactProtocol(paramTTransport);
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.protocol.TCompactProtocol
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */