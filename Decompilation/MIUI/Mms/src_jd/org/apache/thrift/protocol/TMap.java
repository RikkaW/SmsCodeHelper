package org.apache.thrift.protocol;

public final class TMap
{
  public final byte keyType;
  public final int size;
  public final byte valueType;
  
  public TMap()
  {
    this((byte)0, (byte)0, 0);
  }
  
  public TMap(byte paramByte1, byte paramByte2, int paramInt)
  {
    keyType = paramByte1;
    valueType = paramByte2;
    size = paramInt;
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.protocol.TMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */