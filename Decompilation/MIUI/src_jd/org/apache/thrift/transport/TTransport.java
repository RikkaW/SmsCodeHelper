package org.apache.thrift.transport;

public abstract class TTransport
{
  public void consumeBuffer(int paramInt) {}
  
  public byte[] getBuffer()
  {
    return null;
  }
  
  public int getBufferPosition()
  {
    return 0;
  }
  
  public int getBytesRemainingInBuffer()
  {
    return -1;
  }
  
  public abstract int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws TTransportException;
  
  public int readAll(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws TTransportException
  {
    int i = 0;
    while (i < paramInt2)
    {
      int j = read(paramArrayOfByte, paramInt1 + i, paramInt2 - i);
      if (j <= 0) {
        throw new TTransportException("Cannot read. Remote side has closed. Tried to read " + paramInt2 + " bytes, but only got " + i + " bytes.");
      }
      i += j;
    }
    return i;
  }
  
  public void write(byte[] paramArrayOfByte)
    throws TTransportException
  {
    write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public abstract void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws TTransportException;
}

/* Location:
 * Qualified Name:     org.apache.thrift.transport.TTransport
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */