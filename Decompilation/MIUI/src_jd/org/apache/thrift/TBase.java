package org.apache.thrift;

import java.io.Serializable;
import org.apache.thrift.protocol.TProtocol;

public abstract interface TBase<T extends TBase, F>
  extends Comparable<T>, Serializable
{
  public abstract void read(TProtocol paramTProtocol)
    throws TException;
  
  public abstract void write(TProtocol paramTProtocol)
    throws TException;
}

/* Location:
 * Qualified Name:     org.apache.thrift.TBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */