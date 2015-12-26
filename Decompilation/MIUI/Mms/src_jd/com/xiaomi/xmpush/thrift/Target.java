package com.xiaomi.xmpush.thrift;

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

public class Target
  implements TBase<Target, _Fields>, Serializable, Cloneable
{
  private static final TField CHANNEL_ID_FIELD_DESC;
  private static final TField IS_PREVIEW_FIELD_DESC;
  private static final TField RESOURCE_FIELD_DESC;
  private static final TField SERVER_FIELD_DESC;
  private static final TStruct STRUCT_DESC = new TStruct("Target");
  private static final TField USER_ID_FIELD_DESC;
  public static final Map<_Fields, FieldMetaData> metaDataMap;
  private BitSet __isset_bit_vector = new BitSet(2);
  public long channelId = 5L;
  public boolean isPreview = false;
  public String resource = "";
  public String server = "xiaomi.com";
  public String userId;
  
  static
  {
    CHANNEL_ID_FIELD_DESC = new TField("channelId", (byte)10, (short)1);
    USER_ID_FIELD_DESC = new TField("userId", (byte)11, (short)2);
    SERVER_FIELD_DESC = new TField("server", (byte)11, (short)3);
    RESOURCE_FIELD_DESC = new TField("resource", (byte)11, (short)4);
    IS_PREVIEW_FIELD_DESC = new TField("isPreview", (byte)2, (short)5);
    EnumMap localEnumMap = new EnumMap(_Fields.class);
    localEnumMap.put(_Fields.CHANNEL_ID, new FieldMetaData("channelId", (byte)1, new FieldValueMetaData((byte)10)));
    localEnumMap.put(_Fields.USER_ID, new FieldMetaData("userId", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.SERVER, new FieldMetaData("server", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.RESOURCE, new FieldMetaData("resource", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.IS_PREVIEW, new FieldMetaData("isPreview", (byte)2, new FieldValueMetaData((byte)2)));
    metaDataMap = Collections.unmodifiableMap(localEnumMap);
    FieldMetaData.addStructMetaDataMap(Target.class, metaDataMap);
  }
  
  public int compareTo(Target paramTarget)
  {
    int i;
    if (!getClass().equals(paramTarget.getClass())) {
      i = getClass().getName().compareTo(paramTarget.getClass().getName());
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
                        j = Boolean.valueOf(isSetChannelId()).compareTo(Boolean.valueOf(paramTarget.isSetChannelId()));
                        i = j;
                      } while (j != 0);
                      if (!isSetChannelId()) {
                        break;
                      }
                      j = TBaseHelper.compareTo(channelId, channelId);
                      i = j;
                    } while (j != 0);
                    j = Boolean.valueOf(isSetUserId()).compareTo(Boolean.valueOf(paramTarget.isSetUserId()));
                    i = j;
                  } while (j != 0);
                  if (!isSetUserId()) {
                    break;
                  }
                  j = TBaseHelper.compareTo(userId, userId);
                  i = j;
                } while (j != 0);
                j = Boolean.valueOf(isSetServer()).compareTo(Boolean.valueOf(paramTarget.isSetServer()));
                i = j;
              } while (j != 0);
              if (!isSetServer()) {
                break;
              }
              j = TBaseHelper.compareTo(server, server);
              i = j;
            } while (j != 0);
            j = Boolean.valueOf(isSetResource()).compareTo(Boolean.valueOf(paramTarget.isSetResource()));
            i = j;
          } while (j != 0);
          if (!isSetResource()) {
            break;
          }
          j = TBaseHelper.compareTo(resource, resource);
          i = j;
        } while (j != 0);
        j = Boolean.valueOf(isSetIsPreview()).compareTo(Boolean.valueOf(paramTarget.isSetIsPreview()));
        i = j;
      } while (j != 0);
      if (!isSetIsPreview()) {
        break;
      }
      j = TBaseHelper.compareTo(isPreview, isPreview);
      i = j;
    } while (j != 0);
    return 0;
  }
  
  public boolean equals(Target paramTarget)
  {
    if (paramTarget == null) {
      return false;
    }
    if ((1 != 0) || (1 != 0))
    {
      if ((1 == 0) || (1 == 0)) {
        return false;
      }
      if (channelId != channelId) {
        return false;
      }
    }
    boolean bool1 = isSetUserId();
    boolean bool2 = paramTarget.isSetUserId();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!userId.equals(userId)) {
        return false;
      }
    }
    bool1 = isSetServer();
    bool2 = paramTarget.isSetServer();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!server.equals(server)) {
        return false;
      }
    }
    bool1 = isSetResource();
    bool2 = paramTarget.isSetResource();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!resource.equals(resource)) {
        return false;
      }
    }
    bool1 = isSetIsPreview();
    bool2 = paramTarget.isSetIsPreview();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (isPreview != isPreview) {
        return false;
      }
    }
    return true;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    while (!(paramObject instanceof Target)) {
      return false;
    }
    return equals((Target)paramObject);
  }
  
  public int hashCode()
  {
    return 0;
  }
  
  public boolean isSetChannelId()
  {
    return __isset_bit_vector.get(0);
  }
  
  public boolean isSetIsPreview()
  {
    return __isset_bit_vector.get(1);
  }
  
  public boolean isSetResource()
  {
    return resource != null;
  }
  
  public boolean isSetServer()
  {
    return server != null;
  }
  
  public boolean isSetUserId()
  {
    return userId != null;
  }
  
  public void read(TProtocol paramTProtocol)
    throws TException
  {
    paramTProtocol.readStructBegin();
    TField localTField = paramTProtocol.readFieldBegin();
    if (type == 0)
    {
      paramTProtocol.readStructEnd();
      if (!isSetChannelId()) {
        throw new TProtocolException("Required field 'channelId' was not found in serialized data! Struct: " + toString());
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
        if (type == 10)
        {
          channelId = paramTProtocol.readI64();
          setChannelIdIsSet(true);
        }
        else
        {
          TProtocolUtil.skip(paramTProtocol, type);
          continue;
          if (type == 11)
          {
            userId = paramTProtocol.readString();
          }
          else
          {
            TProtocolUtil.skip(paramTProtocol, type);
            continue;
            if (type == 11)
            {
              server = paramTProtocol.readString();
            }
            else
            {
              TProtocolUtil.skip(paramTProtocol, type);
              continue;
              if (type == 11)
              {
                resource = paramTProtocol.readString();
              }
              else
              {
                TProtocolUtil.skip(paramTProtocol, type);
                continue;
                if (type == 2)
                {
                  isPreview = paramTProtocol.readBool();
                  setIsPreviewIsSet(true);
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
    validate();
  }
  
  public void setChannelIdIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(0, paramBoolean);
  }
  
  public void setIsPreviewIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(1, paramBoolean);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Target(");
    localStringBuilder.append("channelId:");
    localStringBuilder.append(channelId);
    if (0 == 0) {
      localStringBuilder.append(", ");
    }
    localStringBuilder.append("userId:");
    if (userId == null)
    {
      localStringBuilder.append("null");
      if (isSetServer())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("server:");
        if (server != null) {
          break label208;
        }
        localStringBuilder.append("null");
      }
      label105:
      if (isSetResource())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("resource:");
        if (resource != null) {
          break label220;
        }
        localStringBuilder.append("null");
      }
    }
    for (;;)
    {
      if (isSetIsPreview())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("isPreview:");
        localStringBuilder.append(isPreview);
      }
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(userId);
      break;
      label208:
      localStringBuilder.append(server);
      break label105;
      label220:
      localStringBuilder.append(resource);
    }
  }
  
  public void validate()
    throws TException
  {
    if (userId == null) {
      throw new TProtocolException("Required field 'userId' was not present! Struct: " + toString());
    }
  }
  
  public void write(TProtocol paramTProtocol)
    throws TException
  {
    validate();
    paramTProtocol.writeStructBegin(STRUCT_DESC);
    paramTProtocol.writeFieldBegin(CHANNEL_ID_FIELD_DESC);
    paramTProtocol.writeI64(channelId);
    paramTProtocol.writeFieldEnd();
    if (userId != null)
    {
      paramTProtocol.writeFieldBegin(USER_ID_FIELD_DESC);
      paramTProtocol.writeString(userId);
      paramTProtocol.writeFieldEnd();
    }
    if ((server != null) && (isSetServer()))
    {
      paramTProtocol.writeFieldBegin(SERVER_FIELD_DESC);
      paramTProtocol.writeString(server);
      paramTProtocol.writeFieldEnd();
    }
    if ((resource != null) && (isSetResource()))
    {
      paramTProtocol.writeFieldBegin(RESOURCE_FIELD_DESC);
      paramTProtocol.writeString(resource);
      paramTProtocol.writeFieldEnd();
    }
    if (isSetIsPreview())
    {
      paramTProtocol.writeFieldBegin(IS_PREVIEW_FIELD_DESC);
      paramTProtocol.writeBool(isPreview);
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
      SERVER = new _Fields("SERVER", 2, (short)3, "server");
      RESOURCE = new _Fields("RESOURCE", 3, (short)4, "resource");
      IS_PREVIEW = new _Fields("IS_PREVIEW", 4, (short)5, "isPreview");
      $VALUES = new _Fields[] { CHANNEL_ID, USER_ID, SERVER, RESOURCE, IS_PREVIEW };
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
 * Qualified Name:     com.xiaomi.xmpush.thrift.Target
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */