package org.apache.thrift.protocol;

import org.apache.thrift.TException;

public class TProtocolUtil
{
  private static int maxSkipDepth = Integer.MAX_VALUE;
  
  public static void skip(TProtocol paramTProtocol, byte paramByte)
    throws TException
  {
    skip(paramTProtocol, paramByte, maxSkipDepth);
  }
  
  public static void skip(TProtocol paramTProtocol, byte paramByte, int paramInt)
    throws TException
  {
    if (paramInt <= 0) {
      throw new TException("Maximum skip depth exceeded");
    }
    switch (paramByte)
    {
    case 5: 
    case 7: 
    case 9: 
    default: 
      return;
    case 2: 
      paramTProtocol.readBool();
      return;
    case 3: 
      paramTProtocol.readByte();
      return;
    case 6: 
      paramTProtocol.readI16();
      return;
    case 8: 
      paramTProtocol.readI32();
      return;
    case 10: 
      paramTProtocol.readI64();
      return;
    case 4: 
      paramTProtocol.readDouble();
      return;
    case 11: 
      paramTProtocol.readBinary();
      return;
    case 12: 
      paramTProtocol.readStructBegin();
      for (;;)
      {
        localObject = paramTProtocol.readFieldBegin();
        if (type == 0)
        {
          paramTProtocol.readStructEnd();
          return;
        }
        skip(paramTProtocol, type, paramInt - 1);
        paramTProtocol.readFieldEnd();
      }
    case 13: 
      localObject = paramTProtocol.readMapBegin();
      paramByte = 0;
      while (paramByte < size)
      {
        skip(paramTProtocol, keyType, paramInt - 1);
        skip(paramTProtocol, valueType, paramInt - 1);
        paramByte += 1;
      }
      paramTProtocol.readMapEnd();
      return;
    case 14: 
      localObject = paramTProtocol.readSetBegin();
      paramByte = 0;
      while (paramByte < size)
      {
        skip(paramTProtocol, elemType, paramInt - 1);
        paramByte += 1;
      }
      paramTProtocol.readSetEnd();
      return;
    }
    Object localObject = paramTProtocol.readListBegin();
    paramByte = 0;
    while (paramByte < size)
    {
      skip(paramTProtocol, elemType, paramInt - 1);
      paramByte += 1;
    }
    paramTProtocol.readListEnd();
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.protocol.TProtocolUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */