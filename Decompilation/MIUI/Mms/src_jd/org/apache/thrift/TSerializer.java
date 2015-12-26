package org.apache.thrift;

import java.io.ByteArrayOutputStream;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TIOStreamTransport;

public class TSerializer
{
  private final ByteArrayOutputStream baos_ = new ByteArrayOutputStream();
  private TProtocol protocol_;
  private final TIOStreamTransport transport_ = new TIOStreamTransport(baos_);
  
  public TSerializer()
  {
    this(new TBinaryProtocol.Factory());
  }
  
  public TSerializer(TProtocolFactory paramTProtocolFactory)
  {
    protocol_ = paramTProtocolFactory.getProtocol(transport_);
  }
  
  public byte[] serialize(TBase paramTBase)
    throws TException
  {
    baos_.reset();
    paramTBase.write(protocol_);
    return baos_.toByteArray();
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.TSerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */