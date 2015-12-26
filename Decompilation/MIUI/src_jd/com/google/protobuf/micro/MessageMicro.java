package com.google.protobuf.micro;

import java.io.IOException;

public abstract class MessageMicro
{
  public abstract int getSerializedSize();
  
  public abstract MessageMicro mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
    throws IOException;
  
  public MessageMicro mergeFrom(byte[] paramArrayOfByte)
    throws InvalidProtocolBufferMicroException
  {
    return mergeFrom(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public MessageMicro mergeFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws InvalidProtocolBufferMicroException
  {
    try
    {
      paramArrayOfByte = CodedInputStreamMicro.newInstance(paramArrayOfByte, paramInt1, paramInt2);
      mergeFrom(paramArrayOfByte);
      paramArrayOfByte.checkLastTagWas(0);
      return this;
    }
    catch (InvalidProtocolBufferMicroException paramArrayOfByte)
    {
      throw paramArrayOfByte;
    }
    catch (IOException paramArrayOfByte)
    {
      throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
    }
  }
  
  protected boolean parseUnknownField(CodedInputStreamMicro paramCodedInputStreamMicro, int paramInt)
    throws IOException
  {
    return paramCodedInputStreamMicro.skipField(paramInt);
  }
  
  public void toByteArray(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
      paramArrayOfByte = CodedOutputStreamMicro.newInstance(paramArrayOfByte, paramInt1, paramInt2);
      writeTo(paramArrayOfByte);
      paramArrayOfByte.checkNoSpaceLeft();
      return;
    }
    catch (IOException paramArrayOfByte)
    {
      throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).");
    }
  }
  
  public byte[] toByteArray()
  {
    byte[] arrayOfByte = new byte[getSerializedSize()];
    toByteArray(arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }
  
  public abstract void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
    throws IOException;
}

/* Location:
 * Qualified Name:     com.google.protobuf.micro.MessageMicro
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */