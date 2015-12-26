package org.apache.thrift.protocol;

import org.apache.thrift.transport.TTransport;

public class TBinaryProtocol$Factory
  implements TProtocolFactory
{
  protected int readLength_;
  protected boolean strictRead_ = false;
  protected boolean strictWrite_ = true;
  
  public TBinaryProtocol$Factory()
  {
    this(false, true);
  }
  
  public TBinaryProtocol$Factory(boolean paramBoolean1, boolean paramBoolean2)
  {
    this(paramBoolean1, paramBoolean2, 0);
  }
  
  public TBinaryProtocol$Factory(boolean paramBoolean1, boolean paramBoolean2, int paramInt)
  {
    strictRead_ = paramBoolean1;
    strictWrite_ = paramBoolean2;
    readLength_ = paramInt;
  }
  
  public TProtocol getProtocol(TTransport paramTTransport)
  {
    paramTTransport = new TBinaryProtocol(paramTTransport, strictRead_, strictWrite_);
    if (readLength_ != 0) {
      paramTTransport.setReadLength(readLength_);
    }
    return paramTTransport;
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.protocol.TBinaryProtocol.Factory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */