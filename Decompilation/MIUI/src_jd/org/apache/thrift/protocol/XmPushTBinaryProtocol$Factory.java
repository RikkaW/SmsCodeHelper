package org.apache.thrift.protocol;

import org.apache.thrift.transport.TTransport;

public class XmPushTBinaryProtocol$Factory
  extends TBinaryProtocol.Factory
{
  public XmPushTBinaryProtocol$Factory()
  {
    super(false, true);
  }
  
  public XmPushTBinaryProtocol$Factory(boolean paramBoolean1, boolean paramBoolean2, int paramInt)
  {
    super(paramBoolean1, paramBoolean2, paramInt);
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

/* Location:
 * Qualified Name:     org.apache.thrift.protocol.XmPushTBinaryProtocol.Factory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */