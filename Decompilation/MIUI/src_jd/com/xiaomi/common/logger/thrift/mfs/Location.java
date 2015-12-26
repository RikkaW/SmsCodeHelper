package com.xiaomi.common.logger.thrift.mfs;

import java.io.Serializable;
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

public class Location
  implements TBase<Location, _Fields>, Serializable, Cloneable
{
  private static final TField CITY_FIELD_DESC;
  private static final TField CONTRY_FIELD_DESC;
  private static final TField ISP_FIELD_DESC;
  private static final TField PROVINCE_FIELD_DESC;
  private static final TStruct STRUCT_DESC = new TStruct("Location");
  public static final Map<_Fields, FieldMetaData> metaDataMap;
  private String city;
  private String contry;
  private String isp;
  private String province;
  
  static
  {
    CONTRY_FIELD_DESC = new TField("contry", (byte)11, (short)1);
    PROVINCE_FIELD_DESC = new TField("province", (byte)11, (short)2);
    CITY_FIELD_DESC = new TField("city", (byte)11, (short)3);
    ISP_FIELD_DESC = new TField("isp", (byte)11, (short)4);
    EnumMap localEnumMap = new EnumMap(_Fields.class);
    localEnumMap.put(_Fields.CONTRY, new FieldMetaData("contry", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.PROVINCE, new FieldMetaData("province", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.CITY, new FieldMetaData("city", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.ISP, new FieldMetaData("isp", (byte)2, new FieldValueMetaData((byte)11)));
    metaDataMap = Collections.unmodifiableMap(localEnumMap);
    FieldMetaData.addStructMetaDataMap(Location.class, metaDataMap);
  }
  
  public int compareTo(Location paramLocation)
  {
    int i;
    if (!getClass().equals(paramLocation.getClass())) {
      i = getClass().getName().compareTo(paramLocation.getClass().getName());
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
                    return i;
                    j = Boolean.valueOf(isSetContry()).compareTo(Boolean.valueOf(paramLocation.isSetContry()));
                    i = j;
                  } while (j != 0);
                  if (!isSetContry()) {
                    break;
                  }
                  j = TBaseHelper.compareTo(contry, contry);
                  i = j;
                } while (j != 0);
                j = Boolean.valueOf(isSetProvince()).compareTo(Boolean.valueOf(paramLocation.isSetProvince()));
                i = j;
              } while (j != 0);
              if (!isSetProvince()) {
                break;
              }
              j = TBaseHelper.compareTo(province, province);
              i = j;
            } while (j != 0);
            j = Boolean.valueOf(isSetCity()).compareTo(Boolean.valueOf(paramLocation.isSetCity()));
            i = j;
          } while (j != 0);
          if (!isSetCity()) {
            break;
          }
          j = TBaseHelper.compareTo(city, city);
          i = j;
        } while (j != 0);
        j = Boolean.valueOf(isSetIsp()).compareTo(Boolean.valueOf(paramLocation.isSetIsp()));
        i = j;
      } while (j != 0);
      if (!isSetIsp()) {
        break;
      }
      j = TBaseHelper.compareTo(isp, isp);
      i = j;
    } while (j != 0);
    return 0;
  }
  
  public boolean equals(Location paramLocation)
  {
    if (paramLocation == null) {}
    boolean bool1;
    boolean bool2;
    do
    {
      do
      {
        do
        {
          do
          {
            return false;
            bool1 = isSetContry();
            bool2 = paramLocation.isSetContry();
          } while (((bool1) || (bool2)) && ((!bool1) || (!bool2) || (!contry.equals(contry))));
          bool1 = isSetProvince();
          bool2 = paramLocation.isSetProvince();
        } while (((bool1) || (bool2)) && ((!bool1) || (!bool2) || (!province.equals(province))));
        bool1 = isSetCity();
        bool2 = paramLocation.isSetCity();
      } while (((bool1) || (bool2)) && ((!bool1) || (!bool2) || (!city.equals(city))));
      bool1 = isSetIsp();
      bool2 = paramLocation.isSetIsp();
    } while (((bool1) || (bool2)) && ((!bool1) || (!bool2) || (!isp.equals(isp))));
    return true;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    while (!(paramObject instanceof Location)) {
      return false;
    }
    return equals((Location)paramObject);
  }
  
  public int hashCode()
  {
    return 0;
  }
  
  public boolean isSetCity()
  {
    return city != null;
  }
  
  public boolean isSetContry()
  {
    return contry != null;
  }
  
  public boolean isSetIsp()
  {
    return isp != null;
  }
  
  public boolean isSetProvince()
  {
    return province != null;
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
      if (type == 11)
      {
        contry = paramTProtocol.readString();
      }
      else
      {
        TProtocolUtil.skip(paramTProtocol, type);
        continue;
        if (type == 11)
        {
          province = paramTProtocol.readString();
        }
        else
        {
          TProtocolUtil.skip(paramTProtocol, type);
          continue;
          if (type == 11)
          {
            city = paramTProtocol.readString();
          }
          else
          {
            TProtocolUtil.skip(paramTProtocol, type);
            continue;
            if (type == 11) {
              isp = paramTProtocol.readString();
            } else {
              TProtocolUtil.skip(paramTProtocol, type);
            }
          }
        }
      }
    }
  }
  
  public Location setCity(String paramString)
  {
    city = paramString;
    return this;
  }
  
  public Location setContry(String paramString)
  {
    contry = paramString;
    return this;
  }
  
  public Location setIsp(String paramString)
  {
    isp = paramString;
    return this;
  }
  
  public Location setProvince(String paramString)
  {
    province = paramString;
    return this;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Location(");
    int j = 1;
    if (isSetContry())
    {
      localStringBuilder.append("contry:");
      if (contry == null)
      {
        localStringBuilder.append("null");
        j = 0;
      }
    }
    else
    {
      int i = j;
      if (isSetProvince())
      {
        if (j == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("province:");
        if (province != null) {
          break label191;
        }
        localStringBuilder.append("null");
        label83:
        i = 0;
      }
      j = i;
      if (isSetCity())
      {
        if (i == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("city:");
        if (city != null) {
          break label203;
        }
        localStringBuilder.append("null");
        label126:
        j = 0;
      }
      if (isSetIsp())
      {
        if (j == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("isp:");
        if (isp != null) {
          break label215;
        }
        localStringBuilder.append("null");
      }
    }
    for (;;)
    {
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(contry);
      break;
      label191:
      localStringBuilder.append(province);
      break label83;
      label203:
      localStringBuilder.append(city);
      break label126;
      label215:
      localStringBuilder.append(isp);
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
    if ((contry != null) && (isSetContry()))
    {
      paramTProtocol.writeFieldBegin(CONTRY_FIELD_DESC);
      paramTProtocol.writeString(contry);
      paramTProtocol.writeFieldEnd();
    }
    if ((province != null) && (isSetProvince()))
    {
      paramTProtocol.writeFieldBegin(PROVINCE_FIELD_DESC);
      paramTProtocol.writeString(province);
      paramTProtocol.writeFieldEnd();
    }
    if ((city != null) && (isSetCity()))
    {
      paramTProtocol.writeFieldBegin(CITY_FIELD_DESC);
      paramTProtocol.writeString(city);
      paramTProtocol.writeFieldEnd();
    }
    if ((isp != null) && (isSetIsp()))
    {
      paramTProtocol.writeFieldBegin(ISP_FIELD_DESC);
      paramTProtocol.writeString(isp);
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
      CITY = new _Fields("CITY", 2, (short)3, "city");
      ISP = new _Fields("ISP", 3, (short)4, "isp");
      $VALUES = new _Fields[] { CONTRY, PROVINCE, CITY, ISP };
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
 * Qualified Name:     com.xiaomi.common.logger.thrift.mfs.Location
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */