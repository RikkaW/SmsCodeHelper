package com.xiaomi.common.logger.thrift.mfs;

import com.xiaomi.common.logger.thrift.Common;
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
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class HttpLog
  implements TBase<HttpLog, _Fields>, Serializable, Cloneable
{
  private static final TField CATEGORY_FIELD_DESC;
  private static final TField COMMON_FIELD_DESC;
  private static final TField HTTP_API_FIELD_DESC;
  private static final TField PASSPORT_FIELD_DESC;
  private static final TStruct STRUCT_DESC = new TStruct("HttpLog");
  public static final Map<_Fields, FieldMetaData> metaDataMap;
  private String category = "";
  private Common common;
  private HttpApi httpApi;
  private Passport passport;
  
  static
  {
    COMMON_FIELD_DESC = new TField("common", (byte)12, (short)1);
    CATEGORY_FIELD_DESC = new TField("category", (byte)11, (short)2);
    HTTP_API_FIELD_DESC = new TField("httpApi", (byte)12, (short)3);
    PASSPORT_FIELD_DESC = new TField("passport", (byte)12, (short)4);
    EnumMap localEnumMap = new EnumMap(_Fields.class);
    localEnumMap.put(_Fields.COMMON, new FieldMetaData("common", (byte)1, new StructMetaData((byte)12, Common.class)));
    localEnumMap.put(_Fields.CATEGORY, new FieldMetaData("category", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.HTTP_API, new FieldMetaData("httpApi", (byte)2, new StructMetaData((byte)12, HttpApi.class)));
    localEnumMap.put(_Fields.PASSPORT, new FieldMetaData("passport", (byte)2, new StructMetaData((byte)12, Passport.class)));
    metaDataMap = Collections.unmodifiableMap(localEnumMap);
    FieldMetaData.addStructMetaDataMap(HttpLog.class, metaDataMap);
  }
  
  public int compareTo(HttpLog paramHttpLog)
  {
    int i;
    if (!getClass().equals(paramHttpLog.getClass())) {
      i = getClass().getName().compareTo(paramHttpLog.getClass().getName());
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
                    j = Boolean.valueOf(isSetCommon()).compareTo(Boolean.valueOf(paramHttpLog.isSetCommon()));
                    i = j;
                  } while (j != 0);
                  if (!isSetCommon()) {
                    break;
                  }
                  j = TBaseHelper.compareTo(common, common);
                  i = j;
                } while (j != 0);
                j = Boolean.valueOf(isSetCategory()).compareTo(Boolean.valueOf(paramHttpLog.isSetCategory()));
                i = j;
              } while (j != 0);
              if (!isSetCategory()) {
                break;
              }
              j = TBaseHelper.compareTo(category, category);
              i = j;
            } while (j != 0);
            j = Boolean.valueOf(isSetHttpApi()).compareTo(Boolean.valueOf(paramHttpLog.isSetHttpApi()));
            i = j;
          } while (j != 0);
          if (!isSetHttpApi()) {
            break;
          }
          j = TBaseHelper.compareTo(httpApi, httpApi);
          i = j;
        } while (j != 0);
        j = Boolean.valueOf(isSetPassport()).compareTo(Boolean.valueOf(paramHttpLog.isSetPassport()));
        i = j;
      } while (j != 0);
      if (!isSetPassport()) {
        break;
      }
      j = TBaseHelper.compareTo(passport, passport);
      i = j;
    } while (j != 0);
    return 0;
  }
  
  public boolean equals(HttpLog paramHttpLog)
  {
    if (paramHttpLog == null) {}
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
            bool1 = isSetCommon();
            bool2 = paramHttpLog.isSetCommon();
          } while (((bool1) || (bool2)) && ((!bool1) || (!bool2) || (!common.equals(common))));
          bool1 = isSetCategory();
          bool2 = paramHttpLog.isSetCategory();
        } while (((bool1) || (bool2)) && ((!bool1) || (!bool2) || (!category.equals(category))));
        bool1 = isSetHttpApi();
        bool2 = paramHttpLog.isSetHttpApi();
      } while (((bool1) || (bool2)) && ((!bool1) || (!bool2) || (!httpApi.equals(httpApi))));
      bool1 = isSetPassport();
      bool2 = paramHttpLog.isSetPassport();
    } while (((bool1) || (bool2)) && ((!bool1) || (!bool2) || (!passport.equals(passport))));
    return true;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    while (!(paramObject instanceof HttpLog)) {
      return false;
    }
    return equals((HttpLog)paramObject);
  }
  
  public int hashCode()
  {
    return 0;
  }
  
  public boolean isSetCategory()
  {
    return category != null;
  }
  
  public boolean isSetCommon()
  {
    return common != null;
  }
  
  public boolean isSetHttpApi()
  {
    return httpApi != null;
  }
  
  public boolean isSetPassport()
  {
    return passport != null;
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
      if (type == 12)
      {
        common = new Common();
        common.read(paramTProtocol);
      }
      else
      {
        TProtocolUtil.skip(paramTProtocol, type);
        continue;
        if (type == 11)
        {
          category = paramTProtocol.readString();
        }
        else
        {
          TProtocolUtil.skip(paramTProtocol, type);
          continue;
          if (type == 12)
          {
            httpApi = new HttpApi();
            httpApi.read(paramTProtocol);
          }
          else
          {
            TProtocolUtil.skip(paramTProtocol, type);
            continue;
            if (type == 12)
            {
              passport = new Passport();
              passport.read(paramTProtocol);
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
  
  public HttpLog setCategory(String paramString)
  {
    category = paramString;
    return this;
  }
  
  public HttpLog setCommon(Common paramCommon)
  {
    common = paramCommon;
    return this;
  }
  
  public HttpLog setHttpApi(HttpApi paramHttpApi)
  {
    httpApi = paramHttpApi;
    return this;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("HttpLog(");
    localStringBuilder.append("common:");
    if (common == null)
    {
      localStringBuilder.append("null");
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("category:");
      if (category != null) {
        break label178;
      }
      localStringBuilder.append("null");
      label69:
      if (isSetHttpApi())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("httpApi:");
        if (httpApi != null) {
          break label190;
        }
        localStringBuilder.append("null");
      }
      label111:
      if (isSetPassport())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("passport:");
        if (passport != null) {
          break label202;
        }
        localStringBuilder.append("null");
      }
    }
    for (;;)
    {
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(common);
      break;
      label178:
      localStringBuilder.append(category);
      break label69;
      label190:
      localStringBuilder.append(httpApi);
      break label111;
      label202:
      localStringBuilder.append(passport);
    }
  }
  
  public void validate()
    throws TException
  {
    if (common == null) {
      throw new TProtocolException("Required field 'common' was not present! Struct: " + toString());
    }
    if (category == null) {
      throw new TProtocolException("Required field 'category' was not present! Struct: " + toString());
    }
  }
  
  public void write(TProtocol paramTProtocol)
    throws TException
  {
    validate();
    paramTProtocol.writeStructBegin(STRUCT_DESC);
    if (common != null)
    {
      paramTProtocol.writeFieldBegin(COMMON_FIELD_DESC);
      common.write(paramTProtocol);
      paramTProtocol.writeFieldEnd();
    }
    if (category != null)
    {
      paramTProtocol.writeFieldBegin(CATEGORY_FIELD_DESC);
      paramTProtocol.writeString(category);
      paramTProtocol.writeFieldEnd();
    }
    if ((httpApi != null) && (isSetHttpApi()))
    {
      paramTProtocol.writeFieldBegin(HTTP_API_FIELD_DESC);
      httpApi.write(paramTProtocol);
      paramTProtocol.writeFieldEnd();
    }
    if ((passport != null) && (isSetPassport()))
    {
      paramTProtocol.writeFieldBegin(PASSPORT_FIELD_DESC);
      passport.write(paramTProtocol);
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
      CATEGORY = new _Fields("CATEGORY", 1, (short)2, "category");
      HTTP_API = new _Fields("HTTP_API", 2, (short)3, "httpApi");
      PASSPORT = new _Fields("PASSPORT", 3, (short)4, "passport");
      $VALUES = new _Fields[] { COMMON, CATEGORY, HTTP_API, PASSPORT };
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
 * Qualified Name:     com.xiaomi.common.logger.thrift.mfs.HttpLog
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */