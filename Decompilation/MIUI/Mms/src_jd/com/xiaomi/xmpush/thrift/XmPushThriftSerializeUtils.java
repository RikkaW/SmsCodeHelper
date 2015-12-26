package com.xiaomi.xmpush.thrift;

import com.xiaomi.channel.commonutils.logger.MyLog;
import org.apache.thrift.TBase;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.protocol.XmPushTBinaryProtocol.Factory;

public class XmPushThriftSerializeUtils
{
  public static <T extends TBase<T, ?>> void convertByteArrayToThriftObject(T paramT, byte[] paramArrayOfByte)
    throws TException
  {
    if (paramArrayOfByte == null) {
      throw new TException("the message byte is empty.");
    }
    new TDeserializer(new XmPushTBinaryProtocol.Factory(true, true, paramArrayOfByte.length)).deserialize(paramT, paramArrayOfByte);
  }
  
  public static <T extends TBase<T, ?>> byte[] convertThriftObjectToBytes(T paramT)
  {
    if (paramT == null) {
      return null;
    }
    try
    {
      paramT = new TSerializer(new TBinaryProtocol.Factory()).serialize(paramT);
      return paramT;
    }
    catch (TException paramT)
    {
      MyLog.e("convertThriftObjectToBytes catch TException.", paramT);
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */