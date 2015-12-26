package org.apache.thrift.protocol;

import java.io.Serializable;
import org.apache.thrift.transport.TTransport;

public abstract interface TProtocolFactory
  extends Serializable
{
  public abstract TProtocol getProtocol(TTransport paramTTransport);
}

/* Location:
 * Qualified Name:     org.apache.thrift.protocol.TProtocolFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */