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

public class HttpApi
  implements TBase<HttpApi, _Fields>, Serializable, Cloneable
{
  private static final TField APP_NAME_FIELD_DESC;
  private static final TField APP_VERSION_FIELD_DESC;
  private static final TField CATEGORY_FIELD_DESC;
  private static final TField CLIENT_IP_FIELD_DESC;
  private static final TField HOST_INFO_FIELD_DESC;
  private static final TField LOCATION_FIELD_DESC;
  private static final TField NETWORK_FIELD_DESC;
  private static final TStruct STRUCT_DESC = new TStruct("HttpApi");
  private static final TField UUID_FIELD_DESC;
  private static final TField VERSION_FIELD_DESC;
  private static final TField VERSION_TYPE_FIELD_DESC;
  public static final Map<_Fields, FieldMetaData> metaDataMap;
  private String app_name = "";
  private String app_version = "";
  private String category = "";
  private String client_ip;
  private Set<HostInfo> host_info;
  private Location location;
  private String network;
  private String uuid;
  private String version;
  private String version_type = "";
  
  static
  {
    CATEGORY_FIELD_DESC = new TField("category", (byte)11, (short)1);
    UUID_FIELD_DESC = new TField("uuid", (byte)11, (short)2);
    VERSION_FIELD_DESC = new TField("version", (byte)11, (short)3);
    NETWORK_FIELD_DESC = new TField("network", (byte)11, (short)4);
    CLIENT_IP_FIELD_DESC = new TField("client_ip", (byte)11, (short)5);
    LOCATION_FIELD_DESC = new TField("location", (byte)12, (short)6);
    HOST_INFO_FIELD_DESC = new TField("host_info", (byte)14, (short)7);
    VERSION_TYPE_FIELD_DESC = new TField("version_type", (byte)11, (short)8);
    APP_NAME_FIELD_DESC = new TField("app_name", (byte)11, (short)9);
    APP_VERSION_FIELD_DESC = new TField("app_version", (byte)11, (short)10);
    EnumMap localEnumMap = new EnumMap(_Fields.class);
    localEnumMap.put(_Fields.CATEGORY, new FieldMetaData("category", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.UUID, new FieldMetaData("uuid", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.VERSION, new FieldMetaData("version", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.NETWORK, new FieldMetaData("network", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.CLIENT_IP, new FieldMetaData("client_ip", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.LOCATION, new FieldMetaData("location", (byte)2, new StructMetaData((byte)12, Location.class)));
    localEnumMap.put(_Fields.HOST_INFO, new FieldMetaData("host_info", (byte)2, new SetMetaData((byte)14, new StructMetaData((byte)12, HostInfo.class))));
    localEnumMap.put(_Fields.VERSION_TYPE, new FieldMetaData("version_type", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.APP_NAME, new FieldMetaData("app_name", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.APP_VERSION, new FieldMetaData("app_version", (byte)2, new FieldValueMetaData((byte)11)));
    metaDataMap = Collections.unmodifiableMap(localEnumMap);
    FieldMetaData.addStructMetaDataMap(HttpApi.class, metaDataMap);
  }
  
  public void addToHost_info(HostInfo paramHostInfo)
  {
    if (host_info == null) {
      host_info = new HashSet();
    }
    host_info.add(paramHostInfo);
  }
  
  public int compareTo(HttpApi paramHttpApi)
  {
    int i;
    if (!getClass().equals(paramHttpApi.getClass())) {
      i = getClass().getName().compareTo(paramHttpApi.getClass().getName());
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
                                            j = Boolean.valueOf(isSetCategory()).compareTo(Boolean.valueOf(paramHttpApi.isSetCategory()));
                                            i = j;
                                          } while (j != 0);
                                          if (!isSetCategory()) {
                                            break;
                                          }
                                          j = TBaseHelper.compareTo(category, category);
                                          i = j;
                                        } while (j != 0);
                                        j = Boolean.valueOf(isSetUuid()).compareTo(Boolean.valueOf(paramHttpApi.isSetUuid()));
                                        i = j;
                                      } while (j != 0);
                                      if (!isSetUuid()) {
                                        break;
                                      }
                                      j = TBaseHelper.compareTo(uuid, uuid);
                                      i = j;
                                    } while (j != 0);
                                    j = Boolean.valueOf(isSetVersion()).compareTo(Boolean.valueOf(paramHttpApi.isSetVersion()));
                                    i = j;
                                  } while (j != 0);
                                  if (!isSetVersion()) {
                                    break;
                                  }
                                  j = TBaseHelper.compareTo(version, version);
                                  i = j;
                                } while (j != 0);
                                j = Boolean.valueOf(isSetNetwork()).compareTo(Boolean.valueOf(paramHttpApi.isSetNetwork()));
                                i = j;
                              } while (j != 0);
                              if (!isSetNetwork()) {
                                break;
                              }
                              j = TBaseHelper.compareTo(network, network);
                              i = j;
                            } while (j != 0);
                            j = Boolean.valueOf(isSetClient_ip()).compareTo(Boolean.valueOf(paramHttpApi.isSetClient_ip()));
                            i = j;
                          } while (j != 0);
                          if (!isSetClient_ip()) {
                            break;
                          }
                          j = TBaseHelper.compareTo(client_ip, client_ip);
                          i = j;
                        } while (j != 0);
                        j = Boolean.valueOf(isSetLocation()).compareTo(Boolean.valueOf(paramHttpApi.isSetLocation()));
                        i = j;
                      } while (j != 0);
                      if (!isSetLocation()) {
                        break;
                      }
                      j = TBaseHelper.compareTo(location, location);
                      i = j;
                    } while (j != 0);
                    j = Boolean.valueOf(isSetHost_info()).compareTo(Boolean.valueOf(paramHttpApi.isSetHost_info()));
                    i = j;
                  } while (j != 0);
                  if (!isSetHost_info()) {
                    break;
                  }
                  j = TBaseHelper.compareTo(host_info, host_info);
                  i = j;
                } while (j != 0);
                j = Boolean.valueOf(isSetVersion_type()).compareTo(Boolean.valueOf(paramHttpApi.isSetVersion_type()));
                i = j;
              } while (j != 0);
              if (!isSetVersion_type()) {
                break;
              }
              j = TBaseHelper.compareTo(version_type, version_type);
              i = j;
            } while (j != 0);
            j = Boolean.valueOf(isSetApp_name()).compareTo(Boolean.valueOf(paramHttpApi.isSetApp_name()));
            i = j;
          } while (j != 0);
          if (!isSetApp_name()) {
            break;
          }
          j = TBaseHelper.compareTo(app_name, app_name);
          i = j;
        } while (j != 0);
        j = Boolean.valueOf(isSetApp_version()).compareTo(Boolean.valueOf(paramHttpApi.isSetApp_version()));
        i = j;
      } while (j != 0);
      if (!isSetApp_version()) {
        break;
      }
      j = TBaseHelper.compareTo(app_version, app_version);
      i = j;
    } while (j != 0);
    return 0;
  }
  
  public boolean equals(HttpApi paramHttpApi)
  {
    if (paramHttpApi == null) {
      return false;
    }
    boolean bool1 = isSetCategory();
    boolean bool2 = paramHttpApi.isSetCategory();
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
    bool2 = paramHttpApi.isSetUuid();
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
    bool2 = paramHttpApi.isSetVersion();
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
    bool2 = paramHttpApi.isSetNetwork();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!network.equals(network)) {
        return false;
      }
    }
    bool1 = isSetClient_ip();
    bool2 = paramHttpApi.isSetClient_ip();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!client_ip.equals(client_ip)) {
        return false;
      }
    }
    bool1 = isSetLocation();
    bool2 = paramHttpApi.isSetLocation();
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
    bool2 = paramHttpApi.isSetHost_info();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!host_info.equals(host_info)) {
        return false;
      }
    }
    bool1 = isSetVersion_type();
    bool2 = paramHttpApi.isSetVersion_type();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!version_type.equals(version_type)) {
        return false;
      }
    }
    bool1 = isSetApp_name();
    bool2 = paramHttpApi.isSetApp_name();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!app_name.equals(app_name)) {
        return false;
      }
    }
    bool1 = isSetApp_version();
    bool2 = paramHttpApi.isSetApp_version();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!app_version.equals(app_version)) {
        return false;
      }
    }
    return true;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    while (!(paramObject instanceof HttpApi)) {
      return false;
    }
    return equals((HttpApi)paramObject);
  }
  
  public int getHost_infoSize()
  {
    if (host_info == null) {
      return 0;
    }
    return host_info.size();
  }
  
  public int hashCode()
  {
    return 0;
  }
  
  public boolean isSetApp_name()
  {
    return app_name != null;
  }
  
  public boolean isSetApp_version()
  {
    return app_version != null;
  }
  
  public boolean isSetCategory()
  {
    return category != null;
  }
  
  public boolean isSetClient_ip()
  {
    return client_ip != null;
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
  
  public boolean isSetUuid()
  {
    return uuid != null;
  }
  
  public boolean isSetVersion()
  {
    return version != null;
  }
  
  public boolean isSetVersion_type()
  {
    return version_type != null;
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
                client_ip = paramTProtocol.readString();
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
                      HostInfo localHostInfo = new HostInfo();
                      localHostInfo.read(paramTProtocol);
                      host_info.add(localHostInfo);
                      i += 1;
                    }
                    paramTProtocol.readSetEnd();
                  }
                  else
                  {
                    TProtocolUtil.skip(paramTProtocol, type);
                    continue;
                    if (type == 11)
                    {
                      version_type = paramTProtocol.readString();
                    }
                    else
                    {
                      TProtocolUtil.skip(paramTProtocol, type);
                      continue;
                      if (type == 11)
                      {
                        app_name = paramTProtocol.readString();
                      }
                      else
                      {
                        TProtocolUtil.skip(paramTProtocol, type);
                        continue;
                        if (type == 11) {
                          app_version = paramTProtocol.readString();
                        } else {
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
  
  public HttpApi setApp_name(String paramString)
  {
    app_name = paramString;
    return this;
  }
  
  public HttpApi setApp_version(String paramString)
  {
    app_version = paramString;
    return this;
  }
  
  public HttpApi setCategory(String paramString)
  {
    category = paramString;
    return this;
  }
  
  public HttpApi setClient_ip(String paramString)
  {
    client_ip = paramString;
    return this;
  }
  
  public HttpApi setLocation(Location paramLocation)
  {
    location = paramLocation;
    return this;
  }
  
  public HttpApi setNetwork(String paramString)
  {
    network = paramString;
    return this;
  }
  
  public HttpApi setUuid(String paramString)
  {
    uuid = paramString;
    return this;
  }
  
  public HttpApi setVersion(String paramString)
  {
    version = paramString;
    return this;
  }
  
  public HttpApi setVersion_type(String paramString)
  {
    version_type = paramString;
    return this;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("HttpApi(");
    localStringBuilder.append("category:");
    if (category == null)
    {
      localStringBuilder.append("null");
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("uuid:");
      if (uuid != null) {
        break label416;
      }
      localStringBuilder.append("null");
      label69:
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("version:");
      if (version != null) {
        break label428;
      }
      localStringBuilder.append("null");
      label104:
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("network:");
      if (network != null) {
        break label440;
      }
      localStringBuilder.append("null");
      label139:
      if (isSetClient_ip())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("client_ip:");
        if (client_ip != null) {
          break label452;
        }
        localStringBuilder.append("null");
      }
      label181:
      if (isSetLocation())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("location:");
        if (location != null) {
          break label464;
        }
        localStringBuilder.append("null");
      }
      label223:
      if (isSetHost_info())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("host_info:");
        if (host_info != null) {
          break label476;
        }
        localStringBuilder.append("null");
      }
      label265:
      if (isSetVersion_type())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("version_type:");
        if (version_type != null) {
          break label488;
        }
        localStringBuilder.append("null");
      }
      label307:
      if (isSetApp_name())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("app_name:");
        if (app_name != null) {
          break label500;
        }
        localStringBuilder.append("null");
      }
      label349:
      if (isSetApp_version())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("app_version:");
        if (app_version != null) {
          break label512;
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
      label416:
      localStringBuilder.append(uuid);
      break label69;
      label428:
      localStringBuilder.append(version);
      break label104;
      label440:
      localStringBuilder.append(network);
      break label139;
      label452:
      localStringBuilder.append(client_ip);
      break label181;
      label464:
      localStringBuilder.append(location);
      break label223;
      label476:
      localStringBuilder.append(host_info);
      break label265;
      label488:
      localStringBuilder.append(version_type);
      break label307;
      label500:
      localStringBuilder.append(app_name);
      break label349;
      label512:
      localStringBuilder.append(app_version);
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
    if ((client_ip != null) && (isSetClient_ip()))
    {
      paramTProtocol.writeFieldBegin(CLIENT_IP_FIELD_DESC);
      paramTProtocol.writeString(client_ip);
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
        ((HostInfo)localIterator.next()).write(paramTProtocol);
      }
      paramTProtocol.writeSetEnd();
      paramTProtocol.writeFieldEnd();
    }
    if ((version_type != null) && (isSetVersion_type()))
    {
      paramTProtocol.writeFieldBegin(VERSION_TYPE_FIELD_DESC);
      paramTProtocol.writeString(version_type);
      paramTProtocol.writeFieldEnd();
    }
    if ((app_name != null) && (isSetApp_name()))
    {
      paramTProtocol.writeFieldBegin(APP_NAME_FIELD_DESC);
      paramTProtocol.writeString(app_name);
      paramTProtocol.writeFieldEnd();
    }
    if ((app_version != null) && (isSetApp_version()))
    {
      paramTProtocol.writeFieldBegin(APP_VERSION_FIELD_DESC);
      paramTProtocol.writeString(app_version);
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
      CLIENT_IP = new _Fields("CLIENT_IP", 4, (short)5, "client_ip");
      LOCATION = new _Fields("LOCATION", 5, (short)6, "location");
      HOST_INFO = new _Fields("HOST_INFO", 6, (short)7, "host_info");
      VERSION_TYPE = new _Fields("VERSION_TYPE", 7, (short)8, "version_type");
      APP_NAME = new _Fields("APP_NAME", 8, (short)9, "app_name");
      APP_VERSION = new _Fields("APP_VERSION", 9, (short)10, "app_version");
      $VALUES = new _Fields[] { CATEGORY, UUID, VERSION, NETWORK, CLIENT_IP, LOCATION, HOST_INFO, VERSION_TYPE, APP_NAME, APP_VERSION };
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
 * Qualified Name:     com.xiaomi.common.logger.thrift.mfs.HttpApi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */