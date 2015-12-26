package org.apache.thrift.protocol;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;

public class XmPushTBinaryProtocol
  extends TBinaryProtocol
{
  private static int MAX_THRIFT_BINARY_SIZE = 104857600;
  private static int MAX_THRIFT_LIST_SIZE;
  private static int MAX_THRIFT_MAP_SIZE = 10000;
  private static int MAX_THRIFT_SET_SIZE;
  private static int MAX_THRIFT_STRING_SIZE;
  
  static
  {
    MAX_THRIFT_LIST_SIZE = 10000;
    MAX_THRIFT_SET_SIZE = 10000;
    MAX_THRIFT_STRING_SIZE = 10485760;
  }
  
  public XmPushTBinaryProtocol(TTransport paramTTransport, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramTTransport, paramBoolean1, paramBoolean2);
  }
  
  public ByteBuffer readBinary()
    throws TException
  {
    int i = readI32();
    if (i > MAX_THRIFT_BINARY_SIZE) {
      throw new TProtocolException(3, "Thrift binary size " + i + " out of range!");
    }
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
  
  public TList readListBegin()
    throws TException
  {
    byte b = readByte();
    int i = readI32();
    if (i > MAX_THRIFT_LIST_SIZE) {
      throw new TProtocolException(3, "Thrift list size " + i + " out of range!");
    }
    return new TList(b, i);
  }
  
  public TMap readMapBegin()
    throws TException
  {
    byte b1 = readByte();
    byte b2 = readByte();
    int i = readI32();
    if (i > MAX_THRIFT_MAP_SIZE) {
      throw new TProtocolException(3, "Thrift map size " + i + " out of range!");
    }
    return new TMap(b1, b2, i);
  }
  
  public TSet readSetBegin()
    throws TException
  {
    byte b = readByte();
    int i = readI32();
    if (i > MAX_THRIFT_SET_SIZE) {
      throw new TProtocolException(3, "Thrift set size " + i + " out of range!");
    }
    return new TSet(b, i);
  }
  
  public String readString()
    throws TException
  {
    int i = readI32();
    if (i > MAX_THRIFT_STRING_SIZE) {
      throw new TProtocolException(3, "Thrift string size " + i + " out of range!");
    }
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
  
  public static class Factory
    extends TBinaryProtocol.Factory
  {
    public Factory()
    {
      super(true);
    }
    
    public Factory(boolean paramBoolean1, boolean paramBoolean2, int paramInt)
    {
      super(paramBoolean2, paramInt);
    }
    
    public TProtocol getProtocol(TTransport paramTTransport)
    {
      paramTTransport = new XmPushTBinaryProtocol(paramTTransport, strictRead_, strictWrite_);
      if (readLength_ != 0) {
        paramTTransport.setReadLength(readLength_);
      }
      return paramTTransport;
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.protocol.XmPushTBinaryProtocol
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */