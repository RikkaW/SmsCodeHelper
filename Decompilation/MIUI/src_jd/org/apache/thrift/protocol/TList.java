package org.apache.thrift.protocol;

public final class TList
{
  public final byte elemType;
  public final int size;
  
  public TList()
  {
    this((byte)0, 0);
  }
  
  public TList(byte paramByte, int paramInt)
  {
    elemType = paramByte;
    size = paramInt;
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.protocol.TList
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */