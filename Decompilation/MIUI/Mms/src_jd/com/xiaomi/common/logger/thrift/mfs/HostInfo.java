package com.xiaomi.common.logger.thrift.mfs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class HostInfo
  implements TBase<HostInfo, _Fields>, Serializable, Cloneable
{
  private static final TField HOST_FIELD_DESC;
  private static final TField LAND_NODE_INFO_FIELD_DESC;
  private static final TStruct STRUCT_DESC = new TStruct("HostInfo");
  public static final Map<_Fields, FieldMetaData> metaDataMap;
  private String host;
  private List<LandNodeInfo> land_node_info;
  
  static
  {
    HOST_FIELD_DESC = new TField("host", (byte)11, (short)1);
    LAND_NODE_INFO_FIELD_DESC = new TField("land_node_info", (byte)15, (short)2);
    EnumMap localEnumMap = new EnumMap(_Fields.class);
    localEnumMap.put(_Fields.HOST, new FieldMetaData("host", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.LAND_NODE_INFO, new FieldMetaData("land_node_info", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, LandNodeInfo.class))));
    metaDataMap = Collections.unmodifiableMap(localEnumMap);
    FieldMetaData.addStructMetaDataMap(HostInfo.class, metaDataMap);
  }
  
  public int compareTo(HostInfo paramHostInfo)
  {
    int i;
    if (!getClass().equals(paramHostInfo.getClass())) {
      i = getClass().getName().compareTo(paramHostInfo.getClass().getName());
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
            return i;
            j = Boolean.valueOf(isSetHost()).compareTo(Boolean.valueOf(paramHostInfo.isSetHost()));
            i = j;
          } while (j != 0);
          if (!isSetHost()) {
            break;
          }
          j = TBaseHelper.compareTo(host, host);
          i = j;
        } while (j != 0);
        j = Boolean.valueOf(isSetLand_node_info()).compareTo(Boolean.valueOf(paramHostInfo.isSetLand_node_info()));
        i = j;
      } while (j != 0);
      if (!isSetLand_node_info()) {
        break;
      }
      j = TBaseHelper.compareTo(land_node_info, land_node_info);
      i = j;
    } while (j != 0);
    return 0;
  }
  
  public boolean equals(HostInfo paramHostInfo)
  {
    if (paramHostInfo == null) {}
    boolean bool1;
    boolean bool2;
    do
    {
      do
      {
        return false;
        bool1 = isSetHost();
        bool2 = paramHostInfo.isSetHost();
      } while (((bool1) || (bool2)) && ((!bool1) || (!bool2) || (!host.equals(host))));
      bool1 = isSetLand_node_info();
      bool2 = paramHostInfo.isSetLand_node_info();
    } while (((bool1) || (bool2)) && ((!bool1) || (!bool2) || (!land_node_info.equals(land_node_info))));
    return true;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    while (!(paramObject instanceof HostInfo)) {
      return false;
    }
    return equals((HostInfo)paramObject);
  }
  
  public int hashCode()
  {
    return 0;
  }
  
  public boolean isSetHost()
  {
    return host != null;
  }
  
  public boolean isSetLand_node_info()
  {
    return land_node_info != null;
  }
  
  public void read(TProtocol paramTProtocol)
    throws TException
  {
    paramTProtocol.readStructBegin();
    Object localObject = paramTProtocol.readFieldBegin();
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
      if (type == 11)
      {
        host = paramTProtocol.readString();
      }
      else
      {
        TProtocolUtil.skip(paramTProtocol, type);
        continue;
        if (type == 15)
        {
          localObject = paramTProtocol.readListBegin();
          land_node_info = new ArrayList(size);
          int i = 0;
          while (i < size)
          {
            LandNodeInfo localLandNodeInfo = new LandNodeInfo();
            localLandNodeInfo.read(paramTProtocol);
            land_node_info.add(localLandNodeInfo);
            i += 1;
          }
          paramTProtocol.readListEnd();
        }
        else
        {
          TProtocolUtil.skip(paramTProtocol, type);
        }
      }
    }
  }
  
  public HostInfo setHost(String paramString)
  {
    host = paramString;
    return this;
  }
  
  public HostInfo setLand_node_info(List<LandNodeInfo> paramList)
  {
    land_node_info = paramList;
    return this;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("HostInfo(");
    localStringBuilder.append("host:");
    if (host == null)
    {
      localStringBuilder.append("null");
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("land_node_info:");
      if (land_node_info != null) {
        break label88;
      }
      localStringBuilder.append("null");
    }
    for (;;)
    {
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(host);
      break;
      label88:
      localStringBuilder.append(land_node_info);
    }
  }
  
  public void validate()
    throws TException
  {
    if (host == null) {
      throw new TProtocolException("Required field 'host' was not present! Struct: " + toString());
    }
    if (land_node_info == null) {
      throw new TProtocolException("Required field 'land_node_info' was not present! Struct: " + toString());
    }
  }
  
  public void write(TProtocol paramTProtocol)
    throws TException
  {
    validate();
    paramTProtocol.writeStructBegin(STRUCT_DESC);
    if (host != null)
    {
      paramTProtocol.writeFieldBegin(HOST_FIELD_DESC);
      paramTProtocol.writeString(host);
      paramTProtocol.writeFieldEnd();
    }
    if (land_node_info != null)
    {
      paramTProtocol.writeFieldBegin(LAND_NODE_INFO_FIELD_DESC);
      paramTProtocol.writeListBegin(new TList((byte)12, land_node_info.size()));
      Iterator localIterator = land_node_info.iterator();
      while (localIterator.hasNext()) {
        ((LandNodeInfo)localIterator.next()).write(paramTProtocol);
      }
      paramTProtocol.writeListEnd();
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
      $VALUES = new _Fields[] { HOST, LAND_NODE_INFO };
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
 * Qualified Name:     com.xiaomi.common.logger.thrift.mfs.HostInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */