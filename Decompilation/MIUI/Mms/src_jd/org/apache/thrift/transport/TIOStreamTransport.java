package org.apache.thrift.transport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TIOStreamTransport
  extends TTransport
{
  protected InputStream inputStream_ = null;
  protected OutputStream outputStream_ = null;
  
  protected TIOStreamTransport() {}
  
  public TIOStreamTransport(OutputStream paramOutputStream)
  {
    outputStream_ = paramOutputStream;
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws TTransportException
  {
    if (inputStream_ == null) {
      throw new TTransportException(1, "Cannot read from null inputStream");
    }
    try
    {
      paramInt1 = inputStream_.read(paramArrayOfByte, paramInt1, paramInt2);
      if (paramInt1 < 0) {
        throw new TTransportException(4);
      }
    }
    catch (IOException paramArrayOfByte)
    {
      throw new TTransportException(0, paramArrayOfByte);
    }
    return paramInt1;
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws TTransportException
  {
    if (outputStream_ == null) {
      throw new TTransportException(1, "Cannot write to null outputStream");
    }
    try
    {
      outputStream_.write(paramArrayOfByte, paramInt1, paramInt2);
      return;
    }
    catch (IOException paramArrayOfByte)
    {
      throw new TTransportException(0, paramArrayOfByte);
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.transport.TIOStreamTransport
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */