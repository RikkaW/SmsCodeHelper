package com.xiaomi.common.logger.thrift.mfs;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.MapMetaData;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class LandNodeInfo
  implements TBase<LandNodeInfo, _Fields>, Serializable, Cloneable
{
  private static final TField DURATION_FIELD_DESC;
  private static final TField EXP_INFO_FIELD_DESC;
  private static final TField FAILED_COUNT_FIELD_DESC;
  private static final TField HTTP_INFO_FIELD_DESC;
  private static final TField IP_FIELD_DESC;
  private static final TField SIZE_FIELD_DESC;
  private static final TStruct STRUCT_DESC = new TStruct("LandNodeInfo");
  private static final TField SUCCESS_COUNT_FIELD_DESC;
  public static final Map<_Fields, FieldMetaData> metaDataMap;
  private BitSet __isset_bit_vector = new BitSet(4);
  private long duration;
  private Map<String, Integer> exp_info;
  private int failed_count;
  private Map<Integer, Integer> http_info;
  private String ip;
  private int size;
  private int success_count;
  
  static
  {
    IP_FIELD_DESC = new TField("ip", (byte)11, (short)1);
    FAILED_COUNT_FIELD_DESC = new TField("failed_count", (byte)8, (short)2);
    SUCCESS_COUNT_FIELD_DESC = new TField("success_count", (byte)8, (short)3);
    DURATION_FIELD_DESC = new TField("duration", (byte)10, (short)4);
    SIZE_FIELD_DESC = new TField("size", (byte)8, (short)5);
    EXP_INFO_FIELD_DESC = new TField("exp_info", (byte)13, (short)6);
    HTTP_INFO_FIELD_DESC = new TField("http_info", (byte)13, (short)7);
    EnumMap localEnumMap = new EnumMap(_Fields.class);
    localEnumMap.put(_Fields.IP, new FieldMetaData("ip", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.FAILED_COUNT, new FieldMetaData("failed_count", (byte)1, new FieldValueMetaData((byte)8)));
    localEnumMap.put(_Fields.SUCCESS_COUNT, new FieldMetaData("success_count", (byte)1, new FieldValueMetaData((byte)8)));
    localEnumMap.put(_Fields.DURATION, new FieldMetaData("duration", (byte)1, new FieldValueMetaData((byte)10)));
    localEnumMap.put(_Fields.SIZE, new FieldMetaData("size", (byte)1, new FieldValueMetaData((byte)8)));
    localEnumMap.put(_Fields.EXP_INFO, new FieldMetaData("exp_info", (byte)2, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)8))));
    localEnumMap.put(_Fields.HTTP_INFO, new FieldMetaData("http_info", (byte)2, new MapMetaData((byte)13, new FieldValueMetaData((byte)8), new FieldValueMetaData((byte)8))));
    metaDataMap = Collections.unmodifiableMap(localEnumMap);
    FieldMetaData.addStructMetaDataMap(LandNodeInfo.class, metaDataMap);
  }
  
  public int compareTo(LandNodeInfo paramLandNodeInfo)
  {
    int i;
    if (!getClass().equals(paramLandNodeInfo.getClass())) {
      i = getClass().getName().compareTo(paramLandNodeInfo.getClass().getName());
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
                                return i;
                                j = Boolean.valueOf(isSetIp()).compareTo(Boolean.valueOf(paramLandNodeInfo.isSetIp()));
                                i = j;
                              } while (j != 0);
                              if (!isSetIp()) {
                                break;
                              }
                              j = TBaseHelper.compareTo(ip, ip);
                              i = j;
                            } while (j != 0);
                            j = Boolean.valueOf(isSetFailed_count()).compareTo(Boolean.valueOf(paramLandNodeInfo.isSetFailed_count()));
                            i = j;
                          } while (j != 0);
                          if (!isSetFailed_count()) {
                            break;
                          }
                          j = TBaseHelper.compareTo(failed_count, failed_count);
                          i = j;
                        } while (j != 0);
                        j = Boolean.valueOf(isSetSuccess_count()).compareTo(Boolean.valueOf(paramLandNodeInfo.isSetSuccess_count()));
                        i = j;
                      } while (j != 0);
                      if (!isSetSuccess_count()) {
                        break;
                      }
                      j = TBaseHelper.compareTo(success_count, success_count);
                      i = j;
                    } while (j != 0);
                    j = Boolean.valueOf(isSetDuration()).compareTo(Boolean.valueOf(paramLandNodeInfo.isSetDuration()));
                    i = j;
                  } while (j != 0);
                  if (!isSetDuration()) {
                    break;
                  }
                  j = TBaseHelper.compareTo(duration, duration);
                  i = j;
                } while (j != 0);
                j = Boolean.valueOf(isSetSize()).compareTo(Boolean.valueOf(paramLandNodeInfo.isSetSize()));
                i = j;
              } while (j != 0);
              if (!isSetSize()) {
                break;
              }
              j = TBaseHelper.compareTo(size, size);
              i = j;
            } while (j != 0);
            j = Boolean.valueOf(isSetExp_info()).compareTo(Boolean.valueOf(paramLandNodeInfo.isSetExp_info()));
            i = j;
          } while (j != 0);
          if (!isSetExp_info()) {
            break;
          }
          j = TBaseHelper.compareTo(exp_info, exp_info);
          i = j;
        } while (j != 0);
        j = Boolean.valueOf(isSetHttp_info()).compareTo(Boolean.valueOf(paramLandNodeInfo.isSetHttp_info()));
        i = j;
      } while (j != 0);
      if (!isSetHttp_info()) {
        break;
      }
      j = TBaseHelper.compareTo(http_info, http_info);
      i = j;
    } while (j != 0);
    return 0;
  }
  
  public boolean equals(LandNodeInfo paramLandNodeInfo)
  {
    if (paramLandNodeInfo == null) {
      return false;
    }
    boolean bool1 = isSetIp();
    boolean bool2 = paramLandNodeInfo.isSetIp();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!ip.equals(ip)) {
        return false;
      }
    }
    if ((1 != 0) || (1 != 0))
    {
      if ((1 == 0) || (1 == 0)) {
        return false;
      }
      if (failed_count != failed_count) {
        return false;
      }
    }
    if ((1 != 0) || (1 != 0))
    {
      if ((1 == 0) || (1 == 0)) {
        return false;
      }
      if (success_count != success_count) {
        return false;
      }
    }
    if ((1 != 0) || (1 != 0))
    {
      if ((1 == 0) || (1 == 0)) {
        return false;
      }
      if (duration != duration) {
        return false;
      }
    }
    if ((1 != 0) || (1 != 0))
    {
      if ((1 == 0) || (1 == 0)) {
        return false;
      }
      if (size != size) {
        return false;
      }
    }
    bool1 = isSetExp_info();
    bool2 = paramLandNodeInfo.isSetExp_info();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!exp_info.equals(exp_info)) {
        return false;
      }
    }
    bool1 = isSetHttp_info();
    bool2 = paramLandNodeInfo.isSetHttp_info();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!http_info.equals(http_info)) {
        return false;
      }
    }
    return true;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    while (!(paramObject instanceof LandNodeInfo)) {
      return false;
    }
    return equals((LandNodeInfo)paramObject);
  }
  
  public int hashCode()
  {
    return 0;
  }
  
  public boolean isSetDuration()
  {
    return __isset_bit_vector.get(2);
  }
  
  public boolean isSetExp_info()
  {
    return exp_info != null;
  }
  
  public boolean isSetFailed_count()
  {
    return __isset_bit_vector.get(0);
  }
  
  public boolean isSetHttp_info()
  {
    return http_info != null;
  }
  
  public boolean isSetIp()
  {
    return ip != null;
  }
  
  public boolean isSetSize()
  {
    return __isset_bit_vector.get(3);
  }
  
  public boolean isSetSuccess_count()
  {
    return __isset_bit_vector.get(1);
  }
  
  public void read(TProtocol paramTProtocol)
    throws TException
  {
    paramTProtocol.readStructBegin();
    Object localObject = paramTProtocol.readFieldBegin();
    if (type == 0)
    {
      paramTProtocol.readStructEnd();
      if (!isSetFailed_count()) {
        throw new TProtocolException("Required field 'failed_count' was not found in serialized data! Struct: " + toString());
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
        if (type == 11)
        {
          ip = paramTProtocol.readString();
        }
        else
        {
          TProtocolUtil.skip(paramTProtocol, type);
          continue;
          if (type == 8)
          {
            failed_count = paramTProtocol.readI32();
            setFailed_countIsSet(true);
          }
          else
          {
            TProtocolUtil.skip(paramTProtocol, type);
            continue;
            if (type == 8)
            {
              success_count = paramTProtocol.readI32();
              setSuccess_countIsSet(true);
            }
            else
            {
              TProtocolUtil.skip(paramTProtocol, type);
              continue;
              if (type == 10)
              {
                duration = paramTProtocol.readI64();
                setDurationIsSet(true);
              }
              else
              {
                TProtocolUtil.skip(paramTProtocol, type);
                continue;
                if (type == 8)
                {
                  size = paramTProtocol.readI32();
                  setSizeIsSet(true);
                }
                else
                {
                  TProtocolUtil.skip(paramTProtocol, type);
                  continue;
                  int i;
                  int j;
                  if (type == 13)
                  {
                    localObject = paramTProtocol.readMapBegin();
                    exp_info = new HashMap(size * 2);
                    i = 0;
                    while (i < size)
                    {
                      String str = paramTProtocol.readString();
                      j = paramTProtocol.readI32();
                      exp_info.put(str, Integer.valueOf(j));
                      i += 1;
                    }
                    paramTProtocol.readMapEnd();
                  }
                  else
                  {
                    TProtocolUtil.skip(paramTProtocol, type);
                    continue;
                    if (type == 13)
                    {
                      localObject = paramTProtocol.readMapBegin();
                      http_info = new HashMap(size * 2);
                      i = 0;
                      while (i < size)
                      {
                        j = paramTProtocol.readI32();
                        int k = paramTProtocol.readI32();
                        http_info.put(Integer.valueOf(j), Integer.valueOf(k));
                        i += 1;
                      }
                      paramTProtocol.readMapEnd();
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
    if (!isSetSuccess_count()) {
      throw new TProtocolException("Required field 'success_count' was not found in serialized data! Struct: " + toString());
    }
    if (!isSetDuration()) {
      throw new TProtocolException("Required field 'duration' was not found in serialized data! Struct: " + toString());
    }
    if (!isSetSize()) {
      throw new TProtocolException("Required field 'size' was not found in serialized data! Struct: " + toString());
    }
    validate();
  }
  
  public LandNodeInfo setDuration(long paramLong)
  {
    duration = paramLong;
    setDurationIsSet(true);
    return this;
  }
  
  public void setDurationIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(2, paramBoolean);
  }
  
  public LandNodeInfo setExp_info(Map<String, Integer> paramMap)
  {
    exp_info = paramMap;
    return this;
  }
  
  public LandNodeInfo setFailed_count(int paramInt)
  {
    failed_count = paramInt;
    setFailed_countIsSet(true);
    return this;
  }
  
  public void setFailed_countIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(0, paramBoolean);
  }
  
  public LandNodeInfo setIp(String paramString)
  {
    ip = paramString;
    return this;
  }
  
  public LandNodeInfo setSize(int paramInt)
  {
    size = paramInt;
    setSizeIsSet(true);
    return this;
  }
  
  public void setSizeIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(3, paramBoolean);
  }
  
  public LandNodeInfo setSuccess_count(int paramInt)
  {
    success_count = paramInt;
    setSuccess_countIsSet(true);
    return this;
  }
  
  public void setSuccess_countIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(1, paramBoolean);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("LandNodeInfo(");
    localStringBuilder.append("ip:");
    if (ip == null)
    {
      localStringBuilder.append("null");
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("failed_count:");
      localStringBuilder.append(failed_count);
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("success_count:");
      localStringBuilder.append(success_count);
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("duration:");
      localStringBuilder.append(duration);
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("size:");
      localStringBuilder.append(size);
      if (isSetExp_info())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("exp_info:");
        if (exp_info != null) {
          break label259;
        }
        localStringBuilder.append("null");
      }
      label192:
      if (isSetHttp_info())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("http_info:");
        if (http_info != null) {
          break label271;
        }
        localStringBuilder.append("null");
      }
    }
    for (;;)
    {
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(ip);
      break;
      label259:
      localStringBuilder.append(exp_info);
      break label192;
      label271:
      localStringBuilder.append(http_info);
    }
  }
  
  public void validate()
    throws TException
  {
    if (ip == null) {
      throw new TProtocolException("Required field 'ip' was not present! Struct: " + toString());
    }
  }
  
  public void write(TProtocol paramTProtocol)
    throws TException
  {
    validate();
    paramTProtocol.writeStructBegin(STRUCT_DESC);
    if (ip != null)
    {
      paramTProtocol.writeFieldBegin(IP_FIELD_DESC);
      paramTProtocol.writeString(ip);
      paramTProtocol.writeFieldEnd();
    }
    paramTProtocol.writeFieldBegin(FAILED_COUNT_FIELD_DESC);
    paramTProtocol.writeI32(failed_count);
    paramTProtocol.writeFieldEnd();
    paramTProtocol.writeFieldBegin(SUCCESS_COUNT_FIELD_DESC);
    paramTProtocol.writeI32(success_count);
    paramTProtocol.writeFieldEnd();
    paramTProtocol.writeFieldBegin(DURATION_FIELD_DESC);
    paramTProtocol.writeI64(duration);
    paramTProtocol.writeFieldEnd();
    paramTProtocol.writeFieldBegin(SIZE_FIELD_DESC);
    paramTProtocol.writeI32(size);
    paramTProtocol.writeFieldEnd();
    Iterator localIterator;
    Map.Entry localEntry;
    if ((exp_info != null) && (isSetExp_info()))
    {
      paramTProtocol.writeFieldBegin(EXP_INFO_FIELD_DESC);
      paramTProtocol.writeMapBegin(new TMap((byte)11, (byte)8, exp_info.size()));
      localIterator = exp_info.entrySet().iterator();
      while (localIterator.hasNext())
      {
        localEntry = (Map.Entry)localIterator.next();
        paramTProtocol.writeString((String)localEntry.getKey());
        paramTProtocol.writeI32(((Integer)localEntry.getValue()).intValue());
      }
      paramTProtocol.writeMapEnd();
      paramTProtocol.writeFieldEnd();
    }
    if ((http_info != null) && (isSetHttp_info()))
    {
      paramTProtocol.writeFieldBegin(HTTP_INFO_FIELD_DESC);
      paramTProtocol.writeMapBegin(new TMap((byte)8, (byte)8, http_info.size()));
      localIterator = http_info.entrySet().iterator();
      while (localIterator.hasNext())
      {
        localEntry = (Map.Entry)localIterator.next();
        paramTProtocol.writeI32(((Integer)localEntry.getKey()).intValue());
        paramTProtocol.writeI32(((Integer)localEntry.getValue()).intValue());
      }
      paramTProtocol.writeMapEnd();
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
      FAILED_COUNT = new _Fields("FAILED_COUNT", 1, (short)2, "failed_count");
      SUCCESS_COUNT = new _Fields("SUCCESS_COUNT", 2, (short)3, "success_count");
      DURATION = new _Fields("DURATION", 3, (short)4, "duration");
      SIZE = new _Fields("SIZE", 4, (short)5, "size");
      EXP_INFO = new _Fields("EXP_INFO", 5, (short)6, "exp_info");
      HTTP_INFO = new _Fields("HTTP_INFO", 6, (short)7, "http_info");
      $VALUES = new _Fields[] { IP, FAILED_COUNT, SUCCESS_COUNT, DURATION, SIZE, EXP_INFO, HTTP_INFO };
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
 * Qualified Name:     com.xiaomi.common.logger.thrift.mfs.LandNodeInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */