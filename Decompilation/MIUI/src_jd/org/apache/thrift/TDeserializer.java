package org.apache.thrift;

import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TMemoryInputTransport;

public class TDeserializer
{
  private final TProtocol protocol_;
  private final TMemoryInputTransport trans_ = new TMemoryInputTransport();
  
  public TDeserializer()
  {
    this(new TBinaryProtocol.Factory());
  }
  
  public TDeserializer(TProtocolFactory paramTProtocolFactory)
  {
    protocol_ = paramTProtocolFactory.getProtocol(trans_);
  }
  
  public void deserialize(TBase paramTBase, byte[] paramArrayOfByte)
    throws TException
  {
    try
    {
      trans_.reset(paramArrayOfByte);
      paramTBase.read(protocol_);
      return;
    }
    finally
    {
      protocol_.reset();
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.TDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */