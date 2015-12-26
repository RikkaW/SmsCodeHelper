package org.apache.thrift.protocol;

import org.apache.thrift.transport.TTransport;

public class TCompactProtocol$Factory
  implements TProtocolFactory
{
  public TProtocol getProtocol(TTransport paramTTransport)
  {
    return new TCompactProtocol(paramTTransport);
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.protocol.TCompactProtocol.Factory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */