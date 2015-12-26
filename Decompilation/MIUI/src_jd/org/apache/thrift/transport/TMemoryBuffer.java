package org.apache.thrift.transport;

import org.apache.thrift.TByteArrayOutputStream;

public class TMemoryBuffer
  extends TTransport
{
  private TByteArrayOutputStream arr_;
  private int pos_;
  
  public TMemoryBuffer(int paramInt)
  {
    arr_ = new TByteArrayOutputStream(paramInt);
  }
  
  public int length()
  {
    return arr_.size();
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = arr_.get();
    if (paramInt2 > arr_.len() - pos_) {
      paramInt2 = arr_.len() - pos_;
    }
    for (;;)
    {
      if (paramInt2 > 0)
      {
        System.arraycopy(arrayOfByte, pos_, paramArrayOfByte, paramInt1, paramInt2);
        pos_ += paramInt2;
      }
      return paramInt2;
    }
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    arr_.write(paramArrayOfByte, paramInt1, paramInt2);
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.transport.TMemoryBuffer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */