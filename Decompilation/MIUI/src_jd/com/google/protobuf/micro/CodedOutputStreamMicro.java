package com.google.protobuf.micro;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public final class CodedOutputStreamMicro
{
  private final byte[] buffer;
  private final int limit;
  private final OutputStream output;
  private int position;
  
  private CodedOutputStreamMicro(OutputStream paramOutputStream, byte[] paramArrayOfByte)
  {
    output = paramOutputStream;
    buffer = paramArrayOfByte;
    position = 0;
    limit = paramArrayOfByte.length;
  }
  
  private CodedOutputStreamMicro(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    output = null;
    buffer = paramArrayOfByte;
    position = paramInt1;
    limit = (paramInt1 + paramInt2);
  }
  
  public static int computeBoolSize(int paramInt, boolean paramBoolean)
  {
    return computeTagSize(paramInt) + computeBoolSizeNoTag(paramBoolean);
  }
  
  public static int computeBoolSizeNoTag(boolean paramBoolean)
  {
    return 1;
  }
  
  public static int computeInt32Size(int paramInt1, int paramInt2)
  {
    return computeTagSize(paramInt1) + computeInt32SizeNoTag(paramInt2);
  }
  
  public static int computeInt32SizeNoTag(int paramInt)
  {
    if (paramInt >= 0) {
      return computeRawVarint32Size(paramInt);
    }
    return 10;
  }
  
  public static int computeRawVarint32Size(int paramInt)
  {
    if ((paramInt & 0xFFFFFF80) == 0) {
      return 1;
    }
    if ((paramInt & 0xC000) == 0) {
      return 2;
    }
    if ((0xFFE00000 & paramInt) == 0) {
      return 3;
    }
    if ((0xF0000000 & paramInt) == 0) {
      return 4;
    }
    return 5;
  }
  
  public static int computeStringSizeNoTag(String paramString)
  {
    try
    {
      paramString = paramString.getBytes("UTF-8");
      int i = computeRawVarint32Size(paramString.length);
      int j = paramString.length;
      return i + j;
    }
    catch (UnsupportedEncodingException paramString)
    {
      throw new RuntimeException("UTF-8 not supported.");
    }
  }
  
  public static int computeTagSize(int paramInt)
  {
    return computeRawVarint32Size(WireFormatMicro.makeTag(paramInt, 0));
  }
  
  public static int computeUInt32Size(int paramInt1, int paramInt2)
  {
    return computeTagSize(paramInt1) + computeUInt32SizeNoTag(paramInt2);
  }
  
  public static int computeUInt32SizeNoTag(int paramInt)
  {
    return computeRawVarint32Size(paramInt);
  }
  
  public static CodedOutputStreamMicro newInstance(OutputStream paramOutputStream)
  {
    return newInstance(paramOutputStream, 4096);
  }
  
  public static CodedOutputStreamMicro newInstance(OutputStream paramOutputStream, int paramInt)
  {
    return new CodedOutputStreamMicro(paramOutputStream, new byte[paramInt]);
  }
  
  public static CodedOutputStreamMicro newInstance(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new CodedOutputStreamMicro(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  private void refreshBuffer()
    throws IOException
  {
    if (output == null) {
      throw new OutOfSpaceException();
    }
    output.write(buffer, 0, position);
    position = 0;
  }
  
  public void checkNoSpaceLeft()
  {
    if (spaceLeft() != 0) {
      throw new IllegalStateException("Did not write as much data as expected.");
    }
  }
  
  public void flush()
    throws IOException
  {
    if (output != null) {
      refreshBuffer();
    }
  }
  
  public int spaceLeft()
  {
    if (output == null) {
      return limit - position;
    }
    throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array.");
  }
  
  public void writeBool(int paramInt, boolean paramBoolean)
    throws IOException
  {
    writeTag(paramInt, 0);
    writeBoolNoTag(paramBoolean);
  }
  
  public void writeBoolNoTag(boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      writeRawByte(i);
      return;
    }
  }
  
  public void writeInt32(int paramInt1, int paramInt2)
    throws IOException
  {
    writeTag(paramInt1, 0);
    writeInt32NoTag(paramInt2);
  }
  
  public void writeInt32NoTag(int paramInt)
    throws IOException
  {
    if (paramInt >= 0)
    {
      writeRawVarint32(paramInt);
      return;
    }
    writeRawVarint64(paramInt);
  }
  
  public void writeRawByte(byte paramByte)
    throws IOException
  {
    if (position == limit) {
      refreshBuffer();
    }
    byte[] arrayOfByte = buffer;
    int i = position;
    position = (i + 1);
    arrayOfByte[i] = paramByte;
  }
  
  public void writeRawByte(int paramInt)
    throws IOException
  {
    writeRawByte((byte)paramInt);
  }
  
  public void writeRawBytes(byte[] paramArrayOfByte)
    throws IOException
  {
    writeRawBytes(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public void writeRawBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (limit - position >= paramInt2)
    {
      System.arraycopy(paramArrayOfByte, paramInt1, buffer, position, paramInt2);
      position += paramInt2;
      return;
    }
    int i = limit - position;
    System.arraycopy(paramArrayOfByte, paramInt1, buffer, position, i);
    paramInt1 += i;
    paramInt2 -= i;
    position = limit;
    refreshBuffer();
    if (paramInt2 <= limit)
    {
      System.arraycopy(paramArrayOfByte, paramInt1, buffer, 0, paramInt2);
      position = paramInt2;
      return;
    }
    output.write(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public void writeRawVarint32(int paramInt)
    throws IOException
  {
    for (;;)
    {
      if ((paramInt & 0xFFFFFF80) == 0)
      {
        writeRawByte(paramInt);
        return;
      }
      writeRawByte(paramInt & 0x7F | 0x80);
      paramInt >>>= 7;
    }
  }
  
  public void writeRawVarint64(long paramLong)
    throws IOException
  {
    for (;;)
    {
      if ((0xFFFFFFFFFFFFFF80 & paramLong) == 0L)
      {
        writeRawByte((int)paramLong);
        return;
      }
      writeRawByte((int)paramLong & 0x7F | 0x80);
      paramLong >>>= 7;
    }
  }
  
  public void writeString(int paramInt, String paramString)
    throws IOException
  {
    writeTag(paramInt, 2);
    writeStringNoTag(paramString);
  }
  
  public void writeStringNoTag(String paramString)
    throws IOException
  {
    paramString = paramString.getBytes("UTF-8");
    writeRawVarint32(paramString.length);
    writeRawBytes(paramString);
  }
  
  public void writeTag(int paramInt1, int paramInt2)
    throws IOException
  {
    writeRawVarint32(WireFormatMicro.makeTag(paramInt1, paramInt2));
  }
  
  public void writeUInt32(int paramInt1, int paramInt2)
    throws IOException
  {
    writeTag(paramInt1, 0);
    writeUInt32NoTag(paramInt2);
  }
  
  public void writeUInt32NoTag(int paramInt)
    throws IOException
  {
    writeRawVarint32(paramInt);
  }
  
  public static class OutOfSpaceException
    extends IOException
  {
    private static final long serialVersionUID = -6947486886997889499L;
    
    OutOfSpaceException()
    {
      super();
    }
  }
}

/* Location:
 * Qualified Name:     com.google.protobuf.micro.CodedOutputStreamMicro
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */