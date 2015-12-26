package com.google.protobuf.micro;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public final class CodedInputStreamMicro
{
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
  
  private CodedInputStreamMicro(InputStream paramInputStream)
  {
    buffer = new byte['က'];
    bufferSize = 0;
    bufferPos = 0;
    input = paramInputStream;
  }
  
  private CodedInputStreamMicro(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    buffer = paramArrayOfByte;
    bufferSize = (paramInt1 + paramInt2);
    bufferPos = paramInt1;
    input = null;
  }
  
  public static CodedInputStreamMicro newInstance(InputStream paramInputStream)
  {
    return new CodedInputStreamMicro(paramInputStream);
  }
  
  public static CodedInputStreamMicro newInstance(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new CodedInputStreamMicro(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  private void recomputeBufferSizeAfterLimit()
  {
    bufferSize += bufferSizeAfterLimit;
    int i = totalBytesRetired + bufferSize;
    if (i > currentLimit)
    {
      bufferSizeAfterLimit = (i - currentLimit);
      bufferSize -= bufferSizeAfterLimit;
      return;
    }
    bufferSizeAfterLimit = 0;
  }
  
  private boolean refillBuffer(boolean paramBoolean)
    throws IOException
  {
    if (bufferPos < bufferSize) {
      throw new IllegalStateException("refillBuffer() called when buffer wasn't empty.");
    }
    if (totalBytesRetired + bufferSize == currentLimit)
    {
      if (paramBoolean) {
        throw InvalidProtocolBufferMicroException.truncatedMessage();
      }
      return false;
    }
    totalBytesRetired += bufferSize;
    bufferPos = 0;
    if (input == null) {}
    for (int i = -1;; i = input.read(buffer))
    {
      bufferSize = i;
      if ((bufferSize != 0) && (bufferSize >= -1)) {
        break;
      }
      throw new IllegalStateException("InputStream#read(byte[]) returned invalid result: " + bufferSize + "\nThe InputStream implementation is buggy.");
    }
    if (bufferSize == -1)
    {
      bufferSize = 0;
      if (paramBoolean) {
        throw InvalidProtocolBufferMicroException.truncatedMessage();
      }
      return false;
    }
    recomputeBufferSizeAfterLimit();
    i = totalBytesRetired + bufferSize + bufferSizeAfterLimit;
    if ((i > sizeLimit) || (i < 0)) {
      throw InvalidProtocolBufferMicroException.sizeLimitExceeded();
    }
    return true;
  }
  
  public void checkLastTagWas(int paramInt)
    throws InvalidProtocolBufferMicroException
  {
    if (lastTag != paramInt) {
      throw InvalidProtocolBufferMicroException.invalidEndTag();
    }
  }
  
  public boolean isAtEnd()
    throws IOException
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (bufferPos == bufferSize)
    {
      bool1 = bool2;
      if (!refillBuffer(false)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public boolean readBool()
    throws IOException
  {
    return readRawVarint32() != 0;
  }
  
  public int readInt32()
    throws IOException
  {
    return readRawVarint32();
  }
  
  public byte readRawByte()
    throws IOException
  {
    if (bufferPos == bufferSize) {
      refillBuffer(true);
    }
    byte[] arrayOfByte = buffer;
    int i = bufferPos;
    bufferPos = (i + 1);
    return arrayOfByte[i];
  }
  
  public byte[] readRawBytes(int paramInt)
    throws IOException
  {
    if (paramInt < 0) {
      throw InvalidProtocolBufferMicroException.negativeSize();
    }
    if (totalBytesRetired + bufferPos + paramInt > currentLimit)
    {
      skipRawBytes(currentLimit - totalBytesRetired - bufferPos);
      throw InvalidProtocolBufferMicroException.truncatedMessage();
    }
    Object localObject;
    if (paramInt <= bufferSize - bufferPos)
    {
      localObject = new byte[paramInt];
      System.arraycopy(buffer, bufferPos, localObject, 0, paramInt);
      bufferPos += paramInt;
      return (byte[])localObject;
    }
    if (paramInt < 4096)
    {
      localObject = new byte[paramInt];
      i = bufferSize - bufferPos;
      System.arraycopy(buffer, bufferPos, localObject, 0, i);
      bufferPos = bufferSize;
      refillBuffer(true);
      while (paramInt - i > bufferSize)
      {
        System.arraycopy(buffer, 0, localObject, i, bufferSize);
        i += bufferSize;
        bufferPos = bufferSize;
        refillBuffer(true);
      }
      System.arraycopy(buffer, 0, localObject, i, paramInt - i);
      bufferPos = (paramInt - i);
      return (byte[])localObject;
    }
    int m = bufferPos;
    int n = bufferSize;
    totalBytesRetired += bufferSize;
    bufferPos = 0;
    bufferSize = 0;
    int i = paramInt - (n - m);
    Vector localVector = new Vector();
    while (i > 0)
    {
      localObject = new byte[Math.min(i, 4096)];
      int j = 0;
      while (j < localObject.length)
      {
        if (input == null) {}
        for (int k = -1; k == -1; k = input.read((byte[])localObject, j, localObject.length - j)) {
          throw InvalidProtocolBufferMicroException.truncatedMessage();
        }
        totalBytesRetired += k;
        j += k;
      }
      i -= localObject.length;
      localVector.addElement(localObject);
    }
    byte[] arrayOfByte = new byte[paramInt];
    i = n - m;
    System.arraycopy(buffer, m, arrayOfByte, 0, i);
    paramInt = 0;
    for (;;)
    {
      localObject = arrayOfByte;
      if (paramInt >= localVector.size()) {
        break;
      }
      localObject = (byte[])localVector.elementAt(paramInt);
      System.arraycopy(localObject, 0, arrayOfByte, i, localObject.length);
      i += localObject.length;
      paramInt += 1;
    }
  }
  
  public int readRawLittleEndian32()
    throws IOException
  {
    return readRawByte() & 0xFF | (readRawByte() & 0xFF) << 8 | (readRawByte() & 0xFF) << 16 | (readRawByte() & 0xFF) << 24;
  }
  
  public long readRawLittleEndian64()
    throws IOException
  {
    int i = readRawByte();
    int j = readRawByte();
    int k = readRawByte();
    int m = readRawByte();
    int n = readRawByte();
    int i1 = readRawByte();
    int i2 = readRawByte();
    int i3 = readRawByte();
    return i & 0xFF | (j & 0xFF) << 8 | (k & 0xFF) << 16 | (m & 0xFF) << 24 | (n & 0xFF) << 32 | (i1 & 0xFF) << 40 | (i2 & 0xFF) << 48 | (i3 & 0xFF) << 56;
  }
  
  public int readRawVarint32()
    throws IOException
  {
    int i = readRawByte();
    if (i >= 0) {}
    int k;
    do
    {
      return i;
      i &= 0x7F;
      j = readRawByte();
      if (j >= 0) {
        return i | j << 7;
      }
      i |= (j & 0x7F) << 7;
      j = readRawByte();
      if (j >= 0) {
        return i | j << 14;
      }
      i |= (j & 0x7F) << 14;
      k = readRawByte();
      if (k >= 0) {
        return i | k << 21;
      }
      j = readRawByte();
      k = i | (k & 0x7F) << 21 | j << 28;
      i = k;
    } while (j >= 0);
    int j = 0;
    for (;;)
    {
      if (j >= 5) {
        break label133;
      }
      i = k;
      if (readRawByte() >= 0) {
        break;
      }
      j += 1;
    }
    label133:
    throw InvalidProtocolBufferMicroException.malformedVarint();
  }
  
  public String readString()
    throws IOException
  {
    int i = readRawVarint32();
    if ((i <= bufferSize - bufferPos) && (i > 0))
    {
      String str = new String(buffer, bufferPos, i, "UTF-8");
      bufferPos += i;
      return str;
    }
    return new String(readRawBytes(i), "UTF-8");
  }
  
  public int readTag()
    throws IOException
  {
    if (isAtEnd())
    {
      lastTag = 0;
      return 0;
    }
    lastTag = readRawVarint32();
    if (lastTag == 0) {
      throw InvalidProtocolBufferMicroException.invalidTag();
    }
    return lastTag;
  }
  
  public int readUInt32()
    throws IOException
  {
    return readRawVarint32();
  }
  
  public boolean skipField(int paramInt)
    throws IOException
  {
    switch (WireFormatMicro.getTagWireType(paramInt))
    {
    default: 
      throw InvalidProtocolBufferMicroException.invalidWireType();
    case 0: 
      readInt32();
      return true;
    case 1: 
      readRawLittleEndian64();
      return true;
    case 2: 
      skipRawBytes(readRawVarint32());
      return true;
    case 3: 
      skipMessage();
      checkLastTagWas(WireFormatMicro.makeTag(WireFormatMicro.getTagFieldNumber(paramInt), 4));
      return true;
    case 4: 
      return false;
    }
    readRawLittleEndian32();
    return true;
  }
  
  public void skipMessage()
    throws IOException
  {
    int i;
    do
    {
      i = readTag();
    } while ((i != 0) && (skipField(i)));
  }
  
  public void skipRawBytes(int paramInt)
    throws IOException
  {
    if (paramInt < 0) {
      throw InvalidProtocolBufferMicroException.negativeSize();
    }
    if (totalBytesRetired + bufferPos + paramInt > currentLimit)
    {
      skipRawBytes(currentLimit - totalBytesRetired - bufferPos);
      throw InvalidProtocolBufferMicroException.truncatedMessage();
    }
    if (paramInt <= bufferSize - bufferPos) {
      bufferPos += paramInt;
    }
    for (;;)
    {
      return;
      int i = bufferSize - bufferPos;
      totalBytesRetired += bufferSize;
      bufferPos = 0;
      bufferSize = 0;
      while (i < paramInt)
      {
        if (input == null) {}
        for (int j = -1; j <= 0; j = (int)input.skip(paramInt - i)) {
          throw InvalidProtocolBufferMicroException.truncatedMessage();
        }
        i += j;
        totalBytesRetired += j;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.google.protobuf.micro.CodedInputStreamMicro
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */