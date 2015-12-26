package org.apache.thrift.protocol;

public final class TSet
{
  public final byte elemType;
  public final int size;
  
  public TSet()
  {
    this((byte)0, 0);
  }
  
  public TSet(byte paramByte, int paramInt)
  {
    elemType = paramByte;
    size = paramInt;
  }
  
  public TSet(TList paramTList)
  {
    this(elemType, size);
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.protocol.TSet
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */