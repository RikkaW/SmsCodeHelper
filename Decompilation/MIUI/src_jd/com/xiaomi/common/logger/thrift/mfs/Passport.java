package com.xiaomi.common.logger.thrift.mfs;

import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.SetMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TSet;
import org.apache.thrift.protocol.TStruct;

public class Passport
  implements TBase<Passport, _Fields>, Serializable, Cloneable
{
  private static final TField CATEGORY_FIELD_DESC;
  private static final TField HOST_INFO_FIELD_DESC;
  private static final TField LOCATION_FIELD_DESC;
  private static final TField NETWORK_FIELD_DESC;
  private static final TField RID_FIELD_DESC;
  private static final TStruct STRUCT_DESC = new TStruct("Passport");
  private static final TField UUID_FIELD_DESC;
  private static final TField VERSION_FIELD_DESC;
  public static final Map<_Fields, FieldMetaData> metaDataMap;
  private String category = "";
  private Set<PassportHostInfo> host_info;
  private Location location;
  private String network;
  private String rid;
  private String uuid;
  private String version;
  
  static
  {
    CATEGORY_FIELD_DESC = new TField("category", (byte)11, (short)1);
    UUID_FIELD_DESC = new TField("uuid", (byte)11, (short)2);
    VERSION_FIELD_DESC = new TField("version", (byte)11, (short)3);
    NETWORK_FIELD_DESC = new TField("network", (byte)11, (short)4);
    RID_FIELD_DESC = new TField("rid", (byte)11, (short)5);
    LOCATION_FIELD_DESC = new TField("location", (byte)12, (short)6);
    HOST_INFO_FIELD_DESC = new TField("host_info", (byte)14, (short)7);
    EnumMap localEnumMap = new EnumMap(_Fields.class);
    localEnumMap.put(_Fields.CATEGORY, new FieldMetaData("category", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.UUID, new FieldMetaData("uuid", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.VERSION, new FieldMetaData("version", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.NETWORK, new FieldMetaData("network", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.RID, new FieldMetaData("rid", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.LOCATION, new FieldMetaData("location", (byte)2, new StructMetaData((byte)12, Location.class)));
    localEnumMap.put(_Fields.HOST_INFO, new FieldMetaData("host_info", (byte)2, new SetMetaData((byte)14, new StructMetaData((byte)12, PassportHostInfo.class))));
    metaDataMap = Collections.unmodifiableMap(localEnumMap);
    FieldMetaData.addStructMetaDataMap(Passport.class, metaDataMap);
  }
  
  public int compareTo(Passport paramPassport)
  {
    int i;
    if (!getClass().equals(paramPassport.getClass())) {
      i = getClass().getName().compareTo(paramPassport.getClass().getName());
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
                                j = Boolean.valueOf(isSetCategory()).compareTo(Boolean.valueOf(paramPassport.isSetCategory()));
                                i = j;
                              } while (j != 0);
                              if (!isSetCategory()) {
                                break;
                              }
                              j = TBaseHelper.compareTo(category, category);
                              i = j;
                            } while (j != 0);
                            j = Boolean.valueOf(isSetUuid()).compareTo(Boolean.valueOf(paramPassport.isSetUuid()));
                            i = j;
                          } while (j != 0);
                          if (!isSetUuid()) {
                            break;
                          }
                          j = TBaseHelper.compareTo(uuid, uuid);
                          i = j;
                        } while (j != 0);
                        j = Boolean.valueOf(isSetVersion()).compareTo(Boolean.valueOf(paramPassport.isSetVersion()));
                        i = j;
                      } while (j != 0);
                      if (!isSetVersion()) {
                        break;
                      }
                      j = TBaseHelper.compareTo(version, version);
                      i = j;
                    } while (j != 0);
                    j = Boolean.valueOf(isSetNetwork()).compareTo(Boolean.valueOf(paramPassport.isSetNetwork()));
                    i = j;
                  } while (j != 0);
                  if (!isSetNetwork()) {
                    break;
                  }
                  j = TBaseHelper.compareTo(network, network);
                  i = j;
                } while (j != 0);
                j = Boolean.valueOf(isSetRid()).compareTo(Boolean.valueOf(paramPassport.isSetRid()));
                i = j;
              } while (j != 0);
              if (!isSetRid()) {
                break;
              }
              j = TBaseHelper.compareTo(rid, rid);
              i = j;
            } while (j != 0);
            j = Boolean.valueOf(isSetLocation()).compareTo(Boolean.valueOf(paramPassport.isSetLocation()));
            i = j;
          } while (j != 0);
          if (!isSetLocation()) {
            break;
          }
          j = TBaseHelper.compareTo(location, location);
          i = j;
        } while (j != 0);
        j = Boolean.valueOf(isSetHost_info()).compareTo(Boolean.valueOf(paramPassport.isSetHost_info()));
        i = j;
      } while (j != 0);
      if (!isSetHost_info()) {
        break;
      }
      j = TBaseHelper.compareTo(host_info, host_info);
      i = j;
    } while (j != 0);
    return 0;
  }
  
  public boolean equals(Passport paramPassport)
  {
    if (paramPassport == null) {
      return false;
    }
    boolean bool1 = isSetCategory();
    boolean bool2 = paramPassport.isSetCategory();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!category.equals(category)) {
        return false;
      }
    }
    bool1 = isSetUuid();
    bool2 = paramPassport.isSetUuid();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!uuid.equals(uuid)) {
        return false;
      }
    }
    bool1 = isSetVersion();
    bool2 = paramPassport.isSetVersion();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!version.equals(version)) {
        return false;
      }
    }
    bool1 = isSetNetwork();
    bool2 = paramPassport.isSetNetwork();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!network.equals(network)) {
        return false;
      }
    }
    bool1 = isSetRid();
    bool2 = paramPassport.isSetRid();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!rid.equals(rid)) {
        return false;
      }
    }
    bool1 = isSetLocation();
    bool2 = paramPassport.isSetLocation();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!location.equals(location)) {
        return false;
      }
    }
    bool1 = isSetHost_info();
    bool2 = paramPassport.isSetHost_info();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!host_info.equals(host_info)) {
        return false;
      }
    }
    return true;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    while (!(paramObject instanceof Passport)) {
      return false;
    }
    return equals((Passport)paramObject);
  }
  
  public int hashCode()
  {
    return 0;
  }
  
  public boolean isSetCategory()
  {
    return category != null;
  }
  
  public boolean isSetHost_info()
  {
    return host_info != null;
  }
  
  public boolean isSetLocation()
  {
    return location != null;
  }
  
  public boolean isSetNetwork()
  {
    return network != null;
  }
  
  public boolean isSetRid()
  {
    return rid != null;
  }
  
  public boolean isSetUuid()
  {
    return uuid != null;
  }
  
  public boolean isSetVersion()
  {
    return version != null;
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
        category = paramTProtocol.readString();
      }
      else
      {
        TProtocolUtil.skip(paramTProtocol, type);
        continue;
        if (type == 11)
        {
          uuid = paramTProtocol.readString();
        }
        else
        {
          TProtocolUtil.skip(paramTProtocol, type);
          continue;
          if (type == 11)
          {
            version = paramTProtocol.readString();
          }
          else
          {
            TProtocolUtil.skip(paramTProtocol, type);
            continue;
            if (type == 11)
            {
              network = paramTProtocol.readString();
            }
            else
            {
              TProtocolUtil.skip(paramTProtocol, type);
              continue;
              if (type == 11)
              {
                rid = paramTProtocol.readString();
              }
              else
              {
                TProtocolUtil.skip(paramTProtocol, type);
                continue;
                if (type == 12)
                {
                  location = new Location();
                  location.read(paramTProtocol);
                }
                else
                {
                  TProtocolUtil.skip(paramTProtocol, type);
                  continue;
                  if (type == 14)
                  {
                    localObject = paramTProtocol.readSetBegin();
                    host_info = new HashSet(size * 2);
                    int i = 0;
                    while (i < size)
                    {
                      PassportHostInfo localPassportHostInfo = new PassportHostInfo();
                      localPassportHostInfo.read(paramTProtocol);
                      host_info.add(localPassportHostInfo);
                      i += 1;
                    }
                    paramTProtocol.readSetEnd();
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
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Passport(");
    localStringBuilder.append("category:");
    if (category == null)
    {
      localStringBuilder.append("null");
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("uuid:");
      if (uuid != null) {
        break label283;
      }
      localStringBuilder.append("null");
      label69:
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("version:");
      if (version != null) {
        break label295;
      }
      localStringBuilder.append("null");
      label104:
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("network:");
      if (network != null) {
        break label307;
      }
      localStringBuilder.append("null");
      label139:
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("rid:");
      if (rid != null) {
        break label319;
      }
      localStringBuilder.append("null");
      label174:
      if (isSetLocation())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("location:");
        if (location != null) {
          break label331;
        }
        localStringBuilder.append("null");
      }
      label216:
      if (isSetHost_info())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("host_info:");
        if (host_info != null) {
          break label343;
        }
        localStringBuilder.append("null");
      }
    }
    for (;;)
    {
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(category);
      break;
      label283:
      localStringBuilder.append(uuid);
      break label69;
      label295:
      localStringBuilder.append(version);
      break label104;
      label307:
      localStringBuilder.append(network);
      break label139;
      label319:
      localStringBuilder.append(rid);
      break label174;
      label331:
      localStringBuilder.append(location);
      break label216;
      label343:
      localStringBuilder.append(host_info);
    }
  }
  
  public void validate()
    throws TException
  {
    if (category == null) {
      throw new TProtocolException("Required field 'category' was not present! Struct: " + toString());
    }
    if (uuid == null) {
      throw new TProtocolException("Required field 'uuid' was not present! Struct: " + toString());
    }
    if (version == null) {
      throw new TProtocolException("Required field 'version' was not present! Struct: " + toString());
    }
    if (network == null) {
      throw new TProtocolException("Required field 'network' was not present! Struct: " + toString());
    }
    if (rid == null) {
      throw new TProtocolException("Required field 'rid' was not present! Struct: " + toString());
    }
  }
  
  public void write(TProtocol paramTProtocol)
    throws TException
  {
    validate();
    paramTProtocol.writeStructBegin(STRUCT_DESC);
    if (category != null)
    {
      paramTProtocol.writeFieldBegin(CATEGORY_FIELD_DESC);
      paramTProtocol.writeString(category);
      paramTProtocol.writeFieldEnd();
    }
    if (uuid != null)
    {
      paramTProtocol.writeFieldBegin(UUID_FIELD_DESC);
      paramTProtocol.writeString(uuid);
      paramTProtocol.writeFieldEnd();
    }
    if (version != null)
    {
      paramTProtocol.writeFieldBegin(VERSION_FIELD_DESC);
      paramTProtocol.writeString(version);
      paramTProtocol.writeFieldEnd();
    }
    if (network != null)
    {
      paramTProtocol.writeFieldBegin(NETWORK_FIELD_DESC);
      paramTProtocol.writeString(network);
      paramTProtocol.writeFieldEnd();
    }
    if (rid != null)
    {
      paramTProtocol.writeFieldBegin(RID_FIELD_DESC);
      paramTProtocol.writeString(rid);
      paramTProtocol.writeFieldEnd();
    }
    if ((location != null) && (isSetLocation()))
    {
      paramTProtocol.writeFieldBegin(LOCATION_FIELD_DESC);
      location.write(paramTProtocol);
      paramTProtocol.writeFieldEnd();
    }
    if ((host_info != null) && (isSetHost_info()))
    {
      paramTProtocol.writeFieldBegin(HOST_INFO_FIELD_DESC);
      paramTProtocol.writeSetBegin(new TSet((byte)12, host_info.size()));
      Iterator localIterator = host_info.iterator();
      while (localIterator.hasNext()) {
        ((PassportHostInfo)localIterator.next()).write(paramTProtocol);
      }
      paramTProtocol.writeSetEnd();
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
      NETWORK = new _Fields("NETWORK", 3, (short)4, "network");
      RID = new _Fields("RID", 4, (short)5, "rid");
      LOCATION = new _Fields("LOCATION", 5, (short)6, "location");
      HOST_INFO = new _Fields("HOST_INFO", 6, (short)7, "host_info");
      $VALUES = new _Fields[] { CATEGORY, UUID, VERSION, NETWORK, RID, LOCATION, HOST_INFO };
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
 * Qualified Name:     com.xiaomi.common.logger.thrift.mfs.Passport
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */