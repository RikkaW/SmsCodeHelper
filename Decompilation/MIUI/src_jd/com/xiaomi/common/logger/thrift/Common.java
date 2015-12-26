package com.xiaomi.common.logger.thrift;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class Common
  implements TBase<Common, _Fields>, Serializable, Cloneable
{
  private static final TField CLIENT_IP_FIELD_DESC;
  private static final TField SERVER_HOST_FIELD_DESC;
  private static final TField SERVER_IP_FIELD_DESC;
  private static final TStruct STRUCT_DESC = new TStruct("Common");
  private static final TField TIME_FIELD_DESC;
  private static final TField UUID_FIELD_DESC = new TField("uuid", (byte)10, (short)1);
  public static final Map<_Fields, FieldMetaData> metaDataMap;
  private BitSet __isset_bit_vector = new BitSet(1);
  public String clientIp = "";
  public String serverHost = "";
  public String serverIp = "";
  public String time = "";
  public long uuid = 0L;
  
  static
  {
    TIME_FIELD_DESC = new TField("time", (byte)11, (short)2);
    CLIENT_IP_FIELD_DESC = new TField("clientIp", (byte)11, (short)3);
    SERVER_IP_FIELD_DESC = new TField("serverIp", (byte)11, (short)4);
    SERVER_HOST_FIELD_DESC = new TField("serverHost", (byte)11, (short)5);
    EnumMap localEnumMap = new EnumMap(_Fields.class);
    localEnumMap.put(_Fields.UUID, new FieldMetaData("uuid", (byte)2, new FieldValueMetaData((byte)10)));
    localEnumMap.put(_Fields.TIME, new FieldMetaData("time", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.CLIENT_IP, new FieldMetaData("clientIp", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.SERVER_IP, new FieldMetaData("serverIp", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.SERVER_HOST, new FieldMetaData("serverHost", (byte)2, new FieldValueMetaData((byte)11)));
    metaDataMap = Collections.unmodifiableMap(localEnumMap);
    FieldMetaData.addStructMetaDataMap(Common.class, metaDataMap);
  }
  
  public int compareTo(Common paramCommon)
  {
    int i;
    if (!getClass().equals(paramCommon.getClass())) {
      i = getClass().getName().compareTo(paramCommon.getClass().getName());
    }
    int j;
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      do
                      {
                        return i;
                        j = Boolean.valueOf(isSetUuid()).compareTo(Boolean.valueOf(paramCommon.isSetUuid()));
                        i = j;
                      } while (j != 0);
                      if (!isSetUuid()) {
                        break;
                      }
                      j = TBaseHelper.compareTo(uuid, uuid);
                      i = j;
                    } while (j != 0);
                    j = Boolean.valueOf(isSetTime()).compareTo(Boolean.valueOf(paramCommon.isSetTime()));
                    i = j;
                  } while (j != 0);
                  if (!isSetTime()) {
                    break;
                  }
                  j = TBaseHelper.compareTo(time, time);
                  i = j;
                } while (j != 0);
                j = Boolean.valueOf(isSetClientIp()).compareTo(Boolean.valueOf(paramCommon.isSetClientIp()));
                i = j;
              } while (j != 0);
              if (!isSetClientIp()) {
                break;
              }
              j = TBaseHelper.compareTo(clientIp, clientIp);
              i = j;
            } while (j != 0);
            j = Boolean.valueOf(isSetServerIp()).compareTo(Boolean.valueOf(paramCommon.isSetServerIp()));
            i = j;
          } while (j != 0);
          if (!isSetServerIp()) {
            break;
          }
          j = TBaseHelper.compareTo(serverIp, serverIp);
          i = j;
        } while (j != 0);
        j = Boolean.valueOf(isSetServerHost()).compareTo(Boolean.valueOf(paramCommon.isSetServerHost()));
        i = j;
      } while (j != 0);
      if (!isSetServerHost()) {
        break;
      }
      j = TBaseHelper.compareTo(serverHost, serverHost);
      i = j;
    } while (j != 0);
    return 0;
  }
  
  public boolean equals(Common paramCommon)
  {
    if (paramCommon == null) {
      return false;
    }
    boolean bool1 = isSetUuid();
    boolean bool2 = paramCommon.isSetUuid();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (uuid != uuid) {
        return false;
      }
    }
    bool1 = isSetTime();
    bool2 = paramCommon.isSetTime();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!time.equals(time)) {
        return false;
      }
    }
    bool1 = isSetClientIp();
    bool2 = paramCommon.isSetClientIp();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!clientIp.equals(clientIp)) {
        return false;
      }
    }
    bool1 = isSetServerIp();
    bool2 = paramCommon.isSetServerIp();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!serverIp.equals(serverIp)) {
        return false;
      }
    }
    bool1 = isSetServerHost();
    bool2 = paramCommon.isSetServerHost();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!serverHost.equals(serverHost)) {
        return false;
      }
    }
    return true;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    while (!(paramObject instanceof Common)) {
      return false;
    }
    return equals((Common)paramObject);
  }
  
  public int hashCode()
  {
    return 0;
  }
  
  public boolean isSetClientIp()
  {
    return clientIp != null;
  }
  
  public boolean isSetServerHost()
  {
    return serverHost != null;
  }
  
  public boolean isSetServerIp()
  {
    return serverIp != null;
  }
  
  public boolean isSetTime()
  {
    return time != null;
  }
  
  public boolean isSetUuid()
  {
    return __isset_bit_vector.get(0);
  }
  
  public void read(TProtocol paramTProtocol)
    throws TException
  {
    paramTProtocol.readStructBegin();
    TField localTField = paramTProtocol.readFieldBegin();
    if (type == 0)
    {
      paramTProtocol.readStructEnd();
      validate();
      return;
    }
    switch (id)
    {
    default: 
      TProtocolUtil.skip(paramTProtocol, type);
    }
    for (;;)
    {
      paramTProtocol.readFieldEnd();
      break;
      if (type == 10)
      {
        uuid = paramTProtocol.readI64();
        setUuidIsSet(true);
      }
      else
      {
        TProtocolUtil.skip(paramTProtocol, type);
        continue;
        if (type == 11)
        {
          time = paramTProtocol.readString();
        }
        else
        {
          TProtocolUtil.skip(paramTProtocol, type);
          continue;
          if (type == 11)
          {
            clientIp = paramTProtocol.readString();
          }
          else
          {
            TProtocolUtil.skip(paramTProtocol, type);
            continue;
            if (type == 11)
            {
              serverIp = paramTProtocol.readString();
            }
            else
            {
              TProtocolUtil.skip(paramTProtocol, type);
              continue;
              if (type == 11) {
                serverHost = paramTProtocol.readString();
              } else {
                TProtocolUtil.skip(paramTProtocol, type);
              }
            }
          }
        }
      }
    }
  }
  
  public void setUuidIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(0, paramBoolean);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Common(");
    int j = 1;
    if (isSetUuid())
    {
      localStringBuilder.append("uuid:");
      localStringBuilder.append(uuid);
      j = 0;
    }
    int i = j;
    if (isSetTime())
    {
      if (j == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("time:");
      if (time == null)
      {
        localStringBuilder.append("null");
        i = 0;
      }
    }
    else
    {
      j = i;
      if (isSetClientIp())
      {
        if (i == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("clientIp:");
        if (clientIp != null) {
          break label244;
        }
        localStringBuilder.append("null");
        label129:
        j = 0;
      }
      i = j;
      if (isSetServerIp())
      {
        if (j == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("serverIp:");
        if (serverIp != null) {
          break label256;
        }
        localStringBuilder.append("null");
        label175:
        i = 0;
      }
      if (isSetServerHost())
      {
        if (i == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("serverHost:");
        if (serverHost != null) {
          break label268;
        }
        localStringBuilder.append("null");
      }
    }
    for (;;)
    {
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(time);
      break;
      label244:
      localStringBuilder.append(clientIp);
      break label129;
      label256:
      localStringBuilder.append(serverIp);
      break label175;
      label268:
      localStringBuilder.append(serverHost);
    }
  }
  
  public void validate()
    throws TException
  {}
  
  public void write(TProtocol paramTProtocol)
    throws TException
  {
    validate();
    paramTProtocol.writeStructBegin(STRUCT_DESC);
    if (isSetUuid())
    {
      paramTProtocol.writeFieldBegin(UUID_FIELD_DESC);
      paramTProtocol.writeI64(uuid);
      paramTProtocol.writeFieldEnd();
    }
    if ((time != null) && (isSetTime()))
    {
      paramTProtocol.writeFieldBegin(TIME_FIELD_DESC);
      paramTProtocol.writeString(time);
      paramTProtocol.writeFieldEnd();
    }
    if ((clientIp != null) && (isSetClientIp()))
    {
      paramTProtocol.writeFieldBegin(CLIENT_IP_FIELD_DESC);
      paramTProtocol.writeString(clientIp);
      paramTProtocol.writeFieldEnd();
    }
    if ((serverIp != null) && (isSetServerIp()))
    {
      paramTProtocol.writeFieldBegin(SERVER_IP_FIELD_DESC);
      paramTProtocol.writeString(serverIp);
      paramTProtocol.writeFieldEnd();
    }
    if ((serverHost != null) && (isSetServerHost()))
    {
      paramTProtocol.writeFieldBegin(SERVER_HOST_FIELD_DESC);
      paramTProtocol.writeString(serverHost);
      paramTProtocol.writeFieldEnd();
    }
    paramTProtocol.writeFieldStop();
    paramTProtocol.writeStructEnd();
  }
  
  public static enum _Fields
  {
    private static final Map<String, _Fields> byName;
    private final String _fieldName;
    private final short _thriftId;
    
    static
    {
      TIME = new _Fields("TIME", 1, (short)2, "time");
      CLIENT_IP = new _Fields("CLIENT_IP", 2, (short)3, "clientIp");
      SERVER_IP = new _Fields("SERVER_IP", 3, (short)4, "serverIp");
      SERVER_HOST = new _Fields("SERVER_HOST", 4, (short)5, "serverHost");
      $VALUES = new _Fields[] { UUID, TIME, CLIENT_IP, SERVER_IP, SERVER_HOST };
      byName = new HashMap();
      Iterator localIterator = EnumSet.allOf(_Fields.class).iterator();
      while (localIterator.hasNext())
      {
        _Fields local_Fields = (_Fields)localIterator.next();
        byName.put(local_Fields.getFieldName(), local_Fields);
      }
    }
    
    private _Fields(short paramShort, String paramString)
    {
      _thriftId = paramShort;
      _fieldName = paramString;
    }
    
    public String getFieldName()
    {
      return _fieldName;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.common.logger.thrift.Common
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */