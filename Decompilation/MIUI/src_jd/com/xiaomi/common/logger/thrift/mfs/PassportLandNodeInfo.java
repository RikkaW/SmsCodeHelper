package com.xiaomi.common.logger.thrift.mfs;

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

public class PassportLandNodeInfo
  implements TBase<PassportLandNodeInfo, _Fields>, Serializable, Cloneable
{
  private static final TField EID_FIELD_DESC;
  private static final TField IP_FIELD_DESC;
  private static final TField RT_FIELD_DESC;
  private static final TStruct STRUCT_DESC = new TStruct("PassportLandNodeInfo");
  public static final Map<_Fields, FieldMetaData> metaDataMap;
  private BitSet __isset_bit_vector = new BitSet(3);
  private int eid;
  private int ip;
  private int rt;
  
  static
  {
    IP_FIELD_DESC = new TField("ip", (byte)8, (short)1);
    EID_FIELD_DESC = new TField("eid", (byte)8, (short)2);
    RT_FIELD_DESC = new TField("rt", (byte)8, (short)3);
    EnumMap localEnumMap = new EnumMap(_Fields.class);
    localEnumMap.put(_Fields.IP, new FieldMetaData("ip", (byte)1, new FieldValueMetaData((byte)8)));
    localEnumMap.put(_Fields.EID, new FieldMetaData("eid", (byte)1, new FieldValueMetaData((byte)8)));
    localEnumMap.put(_Fields.RT, new FieldMetaData("rt", (byte)1, new FieldValueMetaData((byte)8)));
    metaDataMap = Collections.unmodifiableMap(localEnumMap);
    FieldMetaData.addStructMetaDataMap(PassportLandNodeInfo.class, metaDataMap);
  }
  
  public int compareTo(PassportLandNodeInfo paramPassportLandNodeInfo)
  {
    int i;
    if (!getClass().equals(paramPassportLandNodeInfo.getClass())) {
      i = getClass().getName().compareTo(paramPassportLandNodeInfo.getClass().getName());
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
                return i;
                j = Boolean.valueOf(isSetIp()).compareTo(Boolean.valueOf(paramPassportLandNodeInfo.isSetIp()));
                i = j;
              } while (j != 0);
              if (!isSetIp()) {
                break;
              }
              j = TBaseHelper.compareTo(ip, ip);
              i = j;
            } while (j != 0);
            j = Boolean.valueOf(isSetEid()).compareTo(Boolean.valueOf(paramPassportLandNodeInfo.isSetEid()));
            i = j;
          } while (j != 0);
          if (!isSetEid()) {
            break;
          }
          j = TBaseHelper.compareTo(eid, eid);
          i = j;
        } while (j != 0);
        j = Boolean.valueOf(isSetRt()).compareTo(Boolean.valueOf(paramPassportLandNodeInfo.isSetRt()));
        i = j;
      } while (j != 0);
      if (!isSetRt()) {
        break;
      }
      j = TBaseHelper.compareTo(rt, rt);
      i = j;
    } while (j != 0);
    return 0;
  }
  
  public boolean equals(PassportLandNodeInfo paramPassportLandNodeInfo)
  {
    if (paramPassportLandNodeInfo == null) {}
    while (((1 != 0) || (1 != 0)) && ((1 == 0) || (1 == 0) || (ip != ip) || (((1 != 0) || (1 != 0)) && ((1 == 0) || (1 == 0) || (eid != eid) || (((1 != 0) || (1 != 0)) && ((1 == 0) || (1 == 0) || (rt != rt))))))) {
      return false;
    }
    return true;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    while (!(paramObject instanceof PassportLandNodeInfo)) {
      return false;
    }
    return equals((PassportLandNodeInfo)paramObject);
  }
  
  public int hashCode()
  {
    return 0;
  }
  
  public boolean isSetEid()
  {
    return __isset_bit_vector.get(1);
  }
  
  public boolean isSetIp()
  {
    return __isset_bit_vector.get(0);
  }
  
  public boolean isSetRt()
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
      if (!isSetIp()) {
        throw new TProtocolException("Required field 'ip' was not found in serialized data! Struct: " + toString());
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
        if (type == 8)
        {
          ip = paramTProtocol.readI32();
          setIpIsSet(true);
        }
        else
        {
          TProtocolUtil.skip(paramTProtocol, type);
          continue;
          if (type == 8)
          {
            eid = paramTProtocol.readI32();
            setEidIsSet(true);
          }
          else
          {
            TProtocolUtil.skip(paramTProtocol, type);
            continue;
            if (type == 8)
            {
              rt = paramTProtocol.readI32();
              setRtIsSet(true);
            }
            else
            {
              TProtocolUtil.skip(paramTProtocol, type);
            }
          }
        }
      }
    }
    if (!isSetEid()) {
      throw new TProtocolException("Required field 'eid' was not found in serialized data! Struct: " + toString());
    }
    if (!isSetRt()) {
      throw new TProtocolException("Required field 'rt' was not found in serialized data! Struct: " + toString());
    }
    validate();
  }
  
  public void setEidIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(1, paramBoolean);
  }
  
  public void setIpIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(0, paramBoolean);
  }
  
  public void setRtIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(2, paramBoolean);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("PassportLandNodeInfo(");
    localStringBuilder.append("ip:");
    localStringBuilder.append(ip);
    if (0 == 0) {
      localStringBuilder.append(", ");
    }
    localStringBuilder.append("eid:");
    localStringBuilder.append(eid);
    if (0 == 0) {
      localStringBuilder.append(", ");
    }
    localStringBuilder.append("rt:");
    localStringBuilder.append(rt);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void validate()
    throws TException
  {}
  
  public void write(TProtocol paramTProtocol)
    throws TException
  {
    validate();
    paramTProtocol.writeStructBegin(STRUCT_DESC);
    paramTProtocol.writeFieldBegin(IP_FIELD_DESC);
    paramTProtocol.writeI32(ip);
    paramTProtocol.writeFieldEnd();
    paramTProtocol.writeFieldBegin(EID_FIELD_DESC);
    paramTProtocol.writeI32(eid);
    paramTProtocol.writeFieldEnd();
    paramTProtocol.writeFieldBegin(RT_FIELD_DESC);
    paramTProtocol.writeI32(rt);
    paramTProtocol.writeFieldEnd();
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
      EID = new _Fields("EID", 1, (short)2, "eid");
      RT = new _Fields("RT", 2, (short)3, "rt");
      $VALUES = new _Fields[] { IP, EID, RT };
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
 * Qualified Name:     com.xiaomi.common.logger.thrift.mfs.PassportLandNodeInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */