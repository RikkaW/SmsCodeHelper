package org.apache.thrift;

import java.io.ByteArrayOutputStream;

public class TByteArrayOutputStream
  extends ByteArrayOutputStream
{
  public TByteArrayOutputStream() {}
  
  public TByteArrayOutputStream(int paramInt)
  {
    super(paramInt);
  }
  
  public byte[] get()
  {
    return buf;
  }
  
  public int len()
  {
    return count;
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.TByteArrayOutputStream
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */