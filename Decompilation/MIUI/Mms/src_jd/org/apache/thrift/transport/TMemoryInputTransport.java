package org.apache.thrift.transport;

public final class TMemoryInputTransport
  extends TTransport
{
  private byte[] buf_;
  private int endPos_;
  private int pos_;
  
  public void consumeBuffer(int paramInt)
  {
    pos_ += paramInt;
  }
  
  public byte[] getBuffer()
  {
    return buf_;
  }
  
  public int getBufferPosition()
  {
    return pos_;
  }
  
  public int getBytesRemainingInBuffer()
  {
    return endPos_ - pos_;
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws TTransportException
  {
    int i = getBytesRemainingInBuffer();
    if (paramInt2 > i) {
      paramInt2 = i;
    }
    for (;;)
    {
      if (paramInt2 > 0)
      {
        System.arraycopy(buf_, pos_, paramArrayOfByte, paramInt1, paramInt2);
        consumeBuffer(paramInt2);
      }
      return paramInt2;
    }
  }
  
  public void reset(byte[] paramArrayOfByte)
  {
    reset(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public void reset(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    buf_ = paramArrayOfByte;
    pos_ = paramInt1;
    endPos_ = (paramInt1 + paramInt2);
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws TTransportException
  {
    throw new UnsupportedOperationException("No writing allowed!");
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.transport.TMemoryInputTransport
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */