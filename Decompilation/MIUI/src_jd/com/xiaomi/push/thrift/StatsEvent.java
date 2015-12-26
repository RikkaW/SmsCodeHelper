package com.xiaomi.push.thrift;

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
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class StatsEvent
  implements TBase<StatsEvent, _Fields>, Serializable, Cloneable
{
  private static final TField ANNOTATION_FIELD_DESC;
  private static final TField CHID_FIELD_DESC;
  private static final TField CLIENT_IP_FIELD_DESC;
  private static final TField CONNPT_FIELD_DESC;
  private static final TField HOST_FIELD_DESC;
  private static final TStruct STRUCT_DESC = new TStruct("StatsEvent");
  private static final TField SUBVALUE_FIELD_DESC;
  private static final TField TIME_FIELD_DESC;
  private static final TField TYPE_FIELD_DESC;
  private static final TField USER_FIELD_DESC;
  private static final TField VALUE_FIELD_DESC;
  public static final Map<_Fields, FieldMetaData> metaDataMap;
  private BitSet __isset_bit_vector = new BitSet(6);
  public String annotation;
  public byte chid;
  public int clientIp;
  public String connpt;
  public String host;
  public int subvalue;
  public int time;
  public int type;
  public String user;
  public int value;
  
  static
  {
    CHID_FIELD_DESC = new TField("chid", (byte)3, (short)1);
    TYPE_FIELD_DESC = new TField("type", (byte)8, (short)2);
    VALUE_FIELD_DESC = new TField("value", (byte)8, (short)3);
    CONNPT_FIELD_DESC = new TField("connpt", (byte)11, (short)4);
    HOST_FIELD_DESC = new TField("host", (byte)11, (short)5);
    SUBVALUE_FIELD_DESC = new TField("subvalue", (byte)8, (short)6);
    ANNOTATION_FIELD_DESC = new TField("annotation", (byte)11, (short)7);
    USER_FIELD_DESC = new TField("user", (byte)11, (short)8);
    TIME_FIELD_DESC = new TField("time", (byte)8, (short)9);
    CLIENT_IP_FIELD_DESC = new TField("clientIp", (byte)8, (short)10);
    EnumMap localEnumMap = new EnumMap(_Fields.class);
    localEnumMap.put(_Fields.CHID, new FieldMetaData("chid", (byte)1, new FieldValueMetaData((byte)3)));
    localEnumMap.put(_Fields.TYPE, new FieldMetaData("type", (byte)1, new FieldValueMetaData((byte)8)));
    localEnumMap.put(_Fields.VALUE, new FieldMetaData("value", (byte)1, new FieldValueMetaData((byte)8)));
    localEnumMap.put(_Fields.CONNPT, new FieldMetaData("connpt", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.HOST, new FieldMetaData("host", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.SUBVALUE, new FieldMetaData("subvalue", (byte)2, new FieldValueMetaData((byte)8)));
    localEnumMap.put(_Fields.ANNOTATION, new FieldMetaData("annotation", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.USER, new FieldMetaData("user", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.TIME, new FieldMetaData("time", (byte)2, new FieldValueMetaData((byte)8)));
    localEnumMap.put(_Fields.CLIENT_IP, new FieldMetaData("clientIp", (byte)2, new FieldValueMetaData((byte)8)));
    metaDataMap = Collections.unmodifiableMap(localEnumMap);
    FieldMetaData.addStructMetaDataMap(StatsEvent.class, metaDataMap);
  }
  
  public int compareTo(StatsEvent paramStatsEvent)
  {
    int i;
    if (!getClass().equals(paramStatsEvent.getClass())) {
      i = getClass().getName().compareTo(paramStatsEvent.getClass().getName());
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
                                            j = Boolean.valueOf(isSetChid()).compareTo(Boolean.valueOf(paramStatsEvent.isSetChid()));
                                            i = j;
                                          } while (j != 0);
                                          if (!isSetChid()) {
                                            break;
                                          }
                                          j = TBaseHelper.compareTo(chid, chid);
                                          i = j;
                                        } while (j != 0);
                                        j = Boolean.valueOf(isSetType()).compareTo(Boolean.valueOf(paramStatsEvent.isSetType()));
                                        i = j;
                                      } while (j != 0);
                                      if (!isSetType()) {
                                        break;
                                      }
                                      j = TBaseHelper.compareTo(type, type);
                                      i = j;
                                    } while (j != 0);
                                    j = Boolean.valueOf(isSetValue()).compareTo(Boolean.valueOf(paramStatsEvent.isSetValue()));
                                    i = j;
                                  } while (j != 0);
                                  if (!isSetValue()) {
                                    break;
                                  }
                                  j = TBaseHelper.compareTo(value, value);
                                  i = j;
                                } while (j != 0);
                                j = Boolean.valueOf(isSetConnpt()).compareTo(Boolean.valueOf(paramStatsEvent.isSetConnpt()));
                                i = j;
                              } while (j != 0);
                              if (!isSetConnpt()) {
                                break;
                              }
                              j = TBaseHelper.compareTo(connpt, connpt);
                              i = j;
                            } while (j != 0);
                            j = Boolean.valueOf(isSetHost()).compareTo(Boolean.valueOf(paramStatsEvent.isSetHost()));
                            i = j;
                          } while (j != 0);
                          if (!isSetHost()) {
                            break;
                          }
                          j = TBaseHelper.compareTo(host, host);
                          i = j;
                        } while (j != 0);
                        j = Boolean.valueOf(isSetSubvalue()).compareTo(Boolean.valueOf(paramStatsEvent.isSetSubvalue()));
                        i = j;
                      } while (j != 0);
                      if (!isSetSubvalue()) {
                        break;
                      }
                      j = TBaseHelper.compareTo(subvalue, subvalue);
                      i = j;
                    } while (j != 0);
                    j = Boolean.valueOf(isSetAnnotation()).compareTo(Boolean.valueOf(paramStatsEvent.isSetAnnotation()));
                    i = j;
                  } while (j != 0);
                  if (!isSetAnnotation()) {
                    break;
                  }
                  j = TBaseHelper.compareTo(annotation, annotation);
                  i = j;
                } while (j != 0);
                j = Boolean.valueOf(isSetUser()).compareTo(Boolean.valueOf(paramStatsEvent.isSetUser()));
                i = j;
              } while (j != 0);
              if (!isSetUser()) {
                break;
              }
              j = TBaseHelper.compareTo(user, user);
              i = j;
            } while (j != 0);
            j = Boolean.valueOf(isSetTime()).compareTo(Boolean.valueOf(paramStatsEvent.isSetTime()));
            i = j;
          } while (j != 0);
          if (!isSetTime()) {
            break;
          }
          j = TBaseHelper.compareTo(time, time);
          i = j;
        } while (j != 0);
        j = Boolean.valueOf(isSetClientIp()).compareTo(Boolean.valueOf(paramStatsEvent.isSetClientIp()));
        i = j;
      } while (j != 0);
      if (!isSetClientIp()) {
        break;
      }
      j = TBaseHelper.compareTo(clientIp, clientIp);
      i = j;
    } while (j != 0);
    return 0;
  }
  
  public boolean equals(StatsEvent paramStatsEvent)
  {
    if (paramStatsEvent == null) {
      return false;
    }
    if ((1 != 0) || (1 != 0))
    {
      if ((1 == 0) || (1 == 0)) {
        return false;
      }
      if (chid != chid) {
        return false;
      }
    }
    if ((1 != 0) || (1 != 0))
    {
      if ((1 == 0) || (1 == 0)) {
        return false;
      }
      if (type != type) {
        return false;
      }
    }
    if ((1 != 0) || (1 != 0))
    {
      if ((1 == 0) || (1 == 0)) {
        return false;
      }
      if (value != value) {
        return false;
      }
    }
    boolean bool1 = isSetConnpt();
    boolean bool2 = paramStatsEvent.isSetConnpt();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!connpt.equals(connpt)) {
        return false;
      }
    }
    bool1 = isSetHost();
    bool2 = paramStatsEvent.isSetHost();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!host.equals(host)) {
        return false;
      }
    }
    bool1 = isSetSubvalue();
    bool2 = paramStatsEvent.isSetSubvalue();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (subvalue != subvalue) {
        return false;
      }
    }
    bool1 = isSetAnnotation();
    bool2 = paramStatsEvent.isSetAnnotation();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!annotation.equals(annotation)) {
        return false;
      }
    }
    bool1 = isSetUser();
    bool2 = paramStatsEvent.isSetUser();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!user.equals(user)) {
        return false;
      }
    }
    bool1 = isSetTime();
    bool2 = paramStatsEvent.isSetTime();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (time != time) {
        return false;
      }
    }
    bool1 = isSetClientIp();
    bool2 = paramStatsEvent.isSetClientIp();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (clientIp != clientIp) {
        return false;
      }
    }
    return true;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    while (!(paramObject instanceof StatsEvent)) {
      return false;
    }
    return equals((StatsEvent)paramObject);
  }
  
  public int hashCode()
  {
    return 0;
  }
  
  public boolean isSetAnnotation()
  {
    return annotation != null;
  }
  
  public boolean isSetChid()
  {
    return __isset_bit_vector.get(0);
  }
  
  public boolean isSetClientIp()
  {
    return __isset_bit_vector.get(5);
  }
  
  public boolean isSetConnpt()
  {
    return connpt != null;
  }
  
  public boolean isSetHost()
  {
    return host != null;
  }
  
  public boolean isSetSubvalue()
  {
    return __isset_bit_vector.get(3);
  }
  
  public boolean isSetTime()
  {
    return __isset_bit_vector.get(4);
  }
  
  public boolean isSetType()
  {
    return __isset_bit_vector.get(1);
  }
  
  public boolean isSetUser()
  {
    return user != null;
  }
  
  public boolean isSetValue()
  {
    return __isset_bit_vector.get(2);
  }
  
  public void read(TProtocol paramTProtocol)
    throws TException
  {
    paramTProtocol.readStructBegin();
    TField localTField = paramTProtocol.readFieldBegin();
    if (type == 0)
    {
      paramTProtocol.readStructEnd();
      if (!isSetChid()) {
        throw new TProtocolException("Required field 'chid' was not found in serialized data! Struct: " + toString());
      }
    }
    else
    {
      switch (id)
      {
      default: 
        TProtocolUtil.skip(paramTProtocol, type);
      }
      for (;;)
      {
        paramTProtocol.readFieldEnd();
        break;
        if (type == 3)
        {
          chid = paramTProtocol.readByte();
          setChidIsSet(true);
        }
        else
        {
          TProtocolUtil.skip(paramTProtocol, type);
          continue;
          if (type == 8)
          {
            type = paramTProtocol.readI32();
            setTypeIsSet(true);
          }
          else
          {
            TProtocolUtil.skip(paramTProtocol, type);
            continue;
            if (type == 8)
            {
              value = paramTProtocol.readI32();
              setValueIsSet(true);
            }
            else
            {
              TProtocolUtil.skip(paramTProtocol, type);
              continue;
              if (type == 11)
              {
                connpt = paramTProtocol.readString();
              }
              else
              {
                TProtocolUtil.skip(paramTProtocol, type);
                continue;
                if (type == 11)
                {
                  host = paramTProtocol.readString();
                }
                else
                {
                  TProtocolUtil.skip(paramTProtocol, type);
                  continue;
                  if (type == 8)
                  {
                    subvalue = paramTProtocol.readI32();
                    setSubvalueIsSet(true);
                  }
                  else
                  {
                    TProtocolUtil.skip(paramTProtocol, type);
                    continue;
                    if (type == 11)
                    {
                      annotation = paramTProtocol.readString();
                    }
                    else
                    {
                      TProtocolUtil.skip(paramTProtocol, type);
                      continue;
                      if (type == 11)
                      {
                        user = paramTProtocol.readString();
                      }
                      else
                      {
                        TProtocolUtil.skip(paramTProtocol, type);
                        continue;
                        if (type == 8)
                        {
                          time = paramTProtocol.readI32();
                          setTimeIsSet(true);
                        }
                        else
                        {
                          TProtocolUtil.skip(paramTProtocol, type);
                          continue;
                          if (type == 8)
                          {
                            clientIp = paramTProtocol.readI32();
                            setClientIpIsSet(true);
                          }
                          else
                          {
                            TProtocolUtil.skip(paramTProtocol, type);
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    if (!isSetType()) {
      throw new TProtocolException("Required field 'type' was not found in serialized data! Struct: " + toString());
    }
    if (!isSetValue()) {
      throw new TProtocolException("Required field 'value' was not found in serialized data! Struct: " + toString());
    }
    validate();
  }
  
  public StatsEvent setAnnotation(String paramString)
  {
    annotation = paramString;
    return this;
  }
  
  public StatsEvent setChid(byte paramByte)
  {
    chid = paramByte;
    setChidIsSet(true);
    return this;
  }
  
  public void setChidIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(0, paramBoolean);
  }
  
  public StatsEvent setClientIp(int paramInt)
  {
    clientIp = paramInt;
    setClientIpIsSet(true);
    return this;
  }
  
  public void setClientIpIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(5, paramBoolean);
  }
  
  public StatsEvent setConnpt(String paramString)
  {
    connpt = paramString;
    return this;
  }
  
  public StatsEvent setHost(String paramString)
  {
    host = paramString;
    return this;
  }
  
  public StatsEvent setSubvalue(int paramInt)
  {
    subvalue = paramInt;
    setSubvalueIsSet(true);
    return this;
  }
  
  public void setSubvalueIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(3, paramBoolean);
  }
  
  public StatsEvent setTime(int paramInt)
  {
    time = paramInt;
    setTimeIsSet(true);
    return this;
  }
  
  public void setTimeIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(4, paramBoolean);
  }
  
  public StatsEvent setType(int paramInt)
  {
    type = paramInt;
    setTypeIsSet(true);
    return this;
  }
  
  public void setTypeIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(1, paramBoolean);
  }
  
  public StatsEvent setUser(String paramString)
  {
    user = paramString;
    return this;
  }
  
  public StatsEvent setValue(int paramInt)
  {
    value = paramInt;
    setValueIsSet(true);
    return this;
  }
  
  public void setValueIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(2, paramBoolean);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("StatsEvent(");
    localStringBuilder.append("chid:");
    localStringBuilder.append(chid);
    if (0 == 0) {
      localStringBuilder.append(", ");
    }
    localStringBuilder.append("type:");
    localStringBuilder.append(type);
    if (0 == 0) {
      localStringBuilder.append(", ");
    }
    localStringBuilder.append("value:");
    localStringBuilder.append(value);
    if (0 == 0) {
      localStringBuilder.append(", ");
    }
    localStringBuilder.append("connpt:");
    if (connpt == null)
    {
      localStringBuilder.append("null");
      if (isSetHost())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("host:");
        if (host != null) {
          break label380;
        }
        localStringBuilder.append("null");
      }
      label163:
      if (isSetSubvalue())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("subvalue:");
        localStringBuilder.append(subvalue);
      }
      if (isSetAnnotation())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("annotation:");
        if (annotation != null) {
          break label392;
        }
        localStringBuilder.append("null");
      }
      label241:
      if (isSetUser())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("user:");
        if (user != null) {
          break label404;
        }
        localStringBuilder.append("null");
      }
    }
    for (;;)
    {
      if (isSetTime())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("time:");
        localStringBuilder.append(time);
      }
      if (isSetClientIp())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("clientIp:");
        localStringBuilder.append(clientIp);
      }
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(connpt);
      break;
      label380:
      localStringBuilder.append(host);
      break label163;
      label392:
      localStringBuilder.append(annotation);
      break label241;
      label404:
      localStringBuilder.append(user);
    }
  }
  
  public void validate()
    throws TException
  {
    if (connpt == null) {
      throw new TProtocolException("Required field 'connpt' was not present! Struct: " + toString());
    }
  }
  
  public void write(TProtocol paramTProtocol)
    throws TException
  {
    validate();
    paramTProtocol.writeStructBegin(STRUCT_DESC);
    paramTProtocol.writeFieldBegin(CHID_FIELD_DESC);
    paramTProtocol.writeByte(chid);
    paramTProtocol.writeFieldEnd();
    paramTProtocol.writeFieldBegin(TYPE_FIELD_DESC);
    paramTProtocol.writeI32(type);
    paramTProtocol.writeFieldEnd();
    paramTProtocol.writeFieldBegin(VALUE_FIELD_DESC);
    paramTProtocol.writeI32(value);
    paramTProtocol.writeFieldEnd();
    if (connpt != null)
    {
      paramTProtocol.writeFieldBegin(CONNPT_FIELD_DESC);
      paramTProtocol.writeString(connpt);
      paramTProtocol.writeFieldEnd();
    }
    if ((host != null) && (isSetHost()))
    {
      paramTProtocol.writeFieldBegin(HOST_FIELD_DESC);
      paramTProtocol.writeString(host);
      paramTProtocol.writeFieldEnd();
    }
    if (isSetSubvalue())
    {
      paramTProtocol.writeFieldBegin(SUBVALUE_FIELD_DESC);
      paramTProtocol.writeI32(subvalue);
      paramTProtocol.writeFieldEnd();
    }
    if ((annotation != null) && (isSetAnnotation()))
    {
      paramTProtocol.writeFieldBegin(ANNOTATION_FIELD_DESC);
      paramTProtocol.writeString(annotation);
      paramTProtocol.writeFieldEnd();
    }
    if ((user != null) && (isSetUser()))
    {
      paramTProtocol.writeFieldBegin(USER_FIELD_DESC);
      paramTProtocol.writeString(user);
      paramTProtocol.writeFieldEnd();
    }
    if (isSetTime())
    {
      paramTProtocol.writeFieldBegin(TIME_FIELD_DESC);
      paramTProtocol.writeI32(time);
      paramTProtocol.writeFieldEnd();
    }
    if (isSetClientIp())
    {
      paramTProtocol.writeFieldBegin(CLIENT_IP_FIELD_DESC);
      paramTProtocol.writeI32(clientIp);
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
      CONNPT = new _Fields("CONNPT", 3, (short)4, "connpt");
      HOST = new _Fields("HOST", 4, (short)5, "host");
      SUBVALUE = new _Fields("SUBVALUE", 5, (short)6, "subvalue");
      ANNOTATION = new _Fields("ANNOTATION", 6, (short)7, "annotation");
      USER = new _Fields("USER", 7, (short)8, "user");
      TIME = new _Fields("TIME", 8, (short)9, "time");
      CLIENT_IP = new _Fields("CLIENT_IP", 9, (short)10, "clientIp");
      $VALUES = new _Fields[] { CHID, TYPE, VALUE, CONNPT, HOST, SUBVALUE, ANNOTATION, USER, TIME, CLIENT_IP };
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
 * Qualified Name:     com.xiaomi.push.thrift.StatsEvent
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */