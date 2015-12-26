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
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class PushMessage
  implements TBase<PushMessage, _Fields>, Serializable, Cloneable
{
  private static final TField APP_ID_FIELD_DESC;
  private static final TField COLLAPSE_KEY_FIELD_DESC;
  private static final TField CREATE_AT_FIELD_DESC;
  private static final TField ID_FIELD_DESC;
  private static final TField PACKAGE_NAME_FIELD_DESC;
  private static final TField PAYLOAD_FIELD_DESC;
  private static final TStruct STRUCT_DESC = new TStruct("PushMessage");
  private static final TField TO_FIELD_DESC = new TField("to", (byte)12, (short)1);
  private static final TField TTL_FIELD_DESC;
  public static final Map<_Fields, FieldMetaData> metaDataMap;
  private BitSet __isset_bit_vector = new BitSet(2);
  public String appId;
  public String collapseKey;
  public long createAt;
  public String id;
  public String packageName;
  public String payload;
  public Target to;
  public long ttl;
  
  static
  {
    ID_FIELD_DESC = new TField("id", (byte)11, (short)2);
    APP_ID_FIELD_DESC = new TField("appId", (byte)11, (short)3);
    PAYLOAD_FIELD_DESC = new TField("payload", (byte)11, (short)4);
    CREATE_AT_FIELD_DESC = new TField("createAt", (byte)10, (short)5);
    TTL_FIELD_DESC = new TField("ttl", (byte)10, (short)6);
    COLLAPSE_KEY_FIELD_DESC = new TField("collapseKey", (byte)11, (short)7);
    PACKAGE_NAME_FIELD_DESC = new TField("packageName", (byte)11, (short)8);
    EnumMap localEnumMap = new EnumMap(_Fields.class);
    localEnumMap.put(_Fields.TO, new FieldMetaData("to", (byte)2, new StructMetaData((byte)12, Target.class)));
    localEnumMap.put(_Fields.ID, new FieldMetaData("id", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.APP_ID, new FieldMetaData("appId", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.PAYLOAD, new FieldMetaData("payload", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.CREATE_AT, new FieldMetaData("createAt", (byte)2, new FieldValueMetaData((byte)10)));
    localEnumMap.put(_Fields.TTL, new FieldMetaData("ttl", (byte)2, new FieldValueMetaData((byte)10)));
    localEnumMap.put(_Fields.COLLAPSE_KEY, new FieldMetaData("collapseKey", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.PACKAGE_NAME, new FieldMetaData("packageName", (byte)2, new FieldValueMetaData((byte)11)));
    metaDataMap = Collections.unmodifiableMap(localEnumMap);
    FieldMetaData.addStructMetaDataMap(PushMessage.class, metaDataMap);
  }
  
  public int compareTo(PushMessage paramPushMessage)
  {
    int i;
    if (!getClass().equals(paramPushMessage.getClass())) {
      i = getClass().getName().compareTo(paramPushMessage.getClass().getName());
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
                                    return i;
                                    j = Boolean.valueOf(isSetTo()).compareTo(Boolean.valueOf(paramPushMessage.isSetTo()));
                                    i = j;
                                  } while (j != 0);
                                  if (!isSetTo()) {
                                    break;
                                  }
                                  j = TBaseHelper.compareTo(to, to);
                                  i = j;
                                } while (j != 0);
                                j = Boolean.valueOf(isSetId()).compareTo(Boolean.valueOf(paramPushMessage.isSetId()));
                                i = j;
                              } while (j != 0);
                              if (!isSetId()) {
                                break;
                              }
                              j = TBaseHelper.compareTo(id, id);
                              i = j;
                            } while (j != 0);
                            j = Boolean.valueOf(isSetAppId()).compareTo(Boolean.valueOf(paramPushMessage.isSetAppId()));
                            i = j;
                          } while (j != 0);
                          if (!isSetAppId()) {
                            break;
                          }
                          j = TBaseHelper.compareTo(appId, appId);
                          i = j;
                        } while (j != 0);
                        j = Boolean.valueOf(isSetPayload()).compareTo(Boolean.valueOf(paramPushMessage.isSetPayload()));
                        i = j;
                      } while (j != 0);
                      if (!isSetPayload()) {
                        break;
                      }
                      j = TBaseHelper.compareTo(payload, payload);
                      i = j;
                    } while (j != 0);
                    j = Boolean.valueOf(isSetCreateAt()).compareTo(Boolean.valueOf(paramPushMessage.isSetCreateAt()));
                    i = j;
                  } while (j != 0);
                  if (!isSetCreateAt()) {
                    break;
                  }
                  j = TBaseHelper.compareTo(createAt, createAt);
                  i = j;
                } while (j != 0);
                j = Boolean.valueOf(isSetTtl()).compareTo(Boolean.valueOf(paramPushMessage.isSetTtl()));
                i = j;
              } while (j != 0);
              if (!isSetTtl()) {
                break;
              }
              j = TBaseHelper.compareTo(ttl, ttl);
              i = j;
            } while (j != 0);
            j = Boolean.valueOf(isSetCollapseKey()).compareTo(Boolean.valueOf(paramPushMessage.isSetCollapseKey()));
            i = j;
          } while (j != 0);
          if (!isSetCollapseKey()) {
            break;
          }
          j = TBaseHelper.compareTo(collapseKey, collapseKey);
          i = j;
        } while (j != 0);
        j = Boolean.valueOf(isSetPackageName()).compareTo(Boolean.valueOf(paramPushMessage.isSetPackageName()));
        i = j;
      } while (j != 0);
      if (!isSetPackageName()) {
        break;
      }
      j = TBaseHelper.compareTo(packageName, packageName);
      i = j;
    } while (j != 0);
    return 0;
  }
  
  public boolean equals(PushMessage paramPushMessage)
  {
    if (paramPushMessage == null) {
      return false;
    }
    boolean bool1 = isSetTo();
    boolean bool2 = paramPushMessage.isSetTo();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!to.equals(to)) {
        return false;
      }
    }
    bool1 = isSetId();
    bool2 = paramPushMessage.isSetId();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!id.equals(id)) {
        return false;
      }
    }
    bool1 = isSetAppId();
    bool2 = paramPushMessage.isSetAppId();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!appId.equals(appId)) {
        return false;
      }
    }
    bool1 = isSetPayload();
    bool2 = paramPushMessage.isSetPayload();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!payload.equals(payload)) {
        return false;
      }
    }
    bool1 = isSetCreateAt();
    bool2 = paramPushMessage.isSetCreateAt();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (createAt != createAt) {
        return false;
      }
    }
    bool1 = isSetTtl();
    bool2 = paramPushMessage.isSetTtl();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (ttl != ttl) {
        return false;
      }
    }
    bool1 = isSetCollapseKey();
    bool2 = paramPushMessage.isSetCollapseKey();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!collapseKey.equals(collapseKey)) {
        return false;
      }
    }
    bool1 = isSetPackageName();
    bool2 = paramPushMessage.isSetPackageName();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!packageName.equals(packageName)) {
        return false;
      }
    }
    return true;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    while (!(paramObject instanceof PushMessage)) {
      return false;
    }
    return equals((PushMessage)paramObject);
  }
  
  public String getAppId()
  {
    return appId;
  }
  
  public long getCreateAt()
  {
    return createAt;
  }
  
  public String getId()
  {
    return id;
  }
  
  public String getPayload()
  {
    return payload;
  }
  
  public int hashCode()
  {
    return 0;
  }
  
  public boolean isSetAppId()
  {
    return appId != null;
  }
  
  public boolean isSetCollapseKey()
  {
    return collapseKey != null;
  }
  
  public boolean isSetCreateAt()
  {
    return __isset_bit_vector.get(0);
  }
  
  public boolean isSetId()
  {
    return id != null;
  }
  
  public boolean isSetPackageName()
  {
    return packageName != null;
  }
  
  public boolean isSetPayload()
  {
    return payload != null;
  }
  
  public boolean isSetTo()
  {
    return to != null;
  }
  
  public boolean isSetTtl()
  {
    return __isset_bit_vector.get(1);
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
        to = new Target();
        to.read(paramTProtocol);
      }
      else
      {
        TProtocolUtil.skip(paramTProtocol, type);
        continue;
        if (type == 11)
        {
          id = paramTProtocol.readString();
        }
        else
        {
          TProtocolUtil.skip(paramTProtocol, type);
          continue;
          if (type == 11)
          {
            appId = paramTProtocol.readString();
          }
          else
          {
            TProtocolUtil.skip(paramTProtocol, type);
            continue;
            if (type == 11)
            {
              payload = paramTProtocol.readString();
            }
            else
            {
              TProtocolUtil.skip(paramTProtocol, type);
              continue;
              if (type == 10)
              {
                createAt = paramTProtocol.readI64();
                setCreateAtIsSet(true);
              }
              else
              {
                TProtocolUtil.skip(paramTProtocol, type);
                continue;
                if (type == 10)
                {
                  ttl = paramTProtocol.readI64();
                  setTtlIsSet(true);
                }
                else
                {
                  TProtocolUtil.skip(paramTProtocol, type);
                  continue;
                  if (type == 11)
                  {
                    collapseKey = paramTProtocol.readString();
                  }
                  else
                  {
                    TProtocolUtil.skip(paramTProtocol, type);
                    continue;
                    if (type == 11) {
                      packageName = paramTProtocol.readString();
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
  
  public void setCreateAtIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(0, paramBoolean);
  }
  
  public void setTtlIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(1, paramBoolean);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("PushMessage(");
    int i = 1;
    if (isSetTo())
    {
      localStringBuilder.append("to:");
      if (to == null)
      {
        localStringBuilder.append("null");
        i = 0;
      }
    }
    else
    {
      if (i == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("id:");
      if (id != null) {
        break label331;
      }
      localStringBuilder.append("null");
      label80:
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("appId:");
      if (appId != null) {
        break label343;
      }
      localStringBuilder.append("null");
      label115:
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("payload:");
      if (payload != null) {
        break label355;
      }
      localStringBuilder.append("null");
      label150:
      if (isSetCreateAt())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("createAt:");
        localStringBuilder.append(createAt);
      }
      if (isSetTtl())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("ttl:");
        localStringBuilder.append(ttl);
      }
      if (isSetCollapseKey())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("collapseKey:");
        if (collapseKey != null) {
          break label367;
        }
        localStringBuilder.append("null");
      }
      label264:
      if (isSetPackageName())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("packageName:");
        if (packageName != null) {
          break label379;
        }
        localStringBuilder.append("null");
      }
    }
    for (;;)
    {
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(to);
      break;
      label331:
      localStringBuilder.append(id);
      break label80;
      label343:
      localStringBuilder.append(appId);
      break label115;
      label355:
      localStringBuilder.append(payload);
      break label150;
      label367:
      localStringBuilder.append(collapseKey);
      break label264;
      label379:
      localStringBuilder.append(packageName);
    }
  }
  
  public void validate()
    throws TException
  {
    if (id == null) {
      throw new TProtocolException("Required field 'id' was not present! Struct: " + toString());
    }
    if (appId == null) {
      throw new TProtocolException("Required field 'appId' was not present! Struct: " + toString());
    }
    if (payload == null) {
      throw new TProtocolException("Required field 'payload' was not present! Struct: " + toString());
    }
  }
  
  public void write(TProtocol paramTProtocol)
    throws TException
  {
    validate();
    paramTProtocol.writeStructBegin(STRUCT_DESC);
    if ((to != null) && (isSetTo()))
    {
      paramTProtocol.writeFieldBegin(TO_FIELD_DESC);
      to.write(paramTProtocol);
      paramTProtocol.writeFieldEnd();
    }
    if (id != null)
    {
      paramTProtocol.writeFieldBegin(ID_FIELD_DESC);
      paramTProtocol.writeString(id);
      paramTProtocol.writeFieldEnd();
    }
    if (appId != null)
    {
      paramTProtocol.writeFieldBegin(APP_ID_FIELD_DESC);
      paramTProtocol.writeString(appId);
      paramTProtocol.writeFieldEnd();
    }
    if (payload != null)
    {
      paramTProtocol.writeFieldBegin(PAYLOAD_FIELD_DESC);
      paramTProtocol.writeString(payload);
      paramTProtocol.writeFieldEnd();
    }
    if (isSetCreateAt())
    {
      paramTProtocol.writeFieldBegin(CREATE_AT_FIELD_DESC);
      paramTProtocol.writeI64(createAt);
      paramTProtocol.writeFieldEnd();
    }
    if (isSetTtl())
    {
      paramTProtocol.writeFieldBegin(TTL_FIELD_DESC);
      paramTProtocol.writeI64(ttl);
      paramTProtocol.writeFieldEnd();
    }
    if ((collapseKey != null) && (isSetCollapseKey()))
    {
      paramTProtocol.writeFieldBegin(COLLAPSE_KEY_FIELD_DESC);
      paramTProtocol.writeString(collapseKey);
      paramTProtocol.writeFieldEnd();
    }
    if ((packageName != null) && (isSetPackageName()))
    {
      paramTProtocol.writeFieldBegin(PACKAGE_NAME_FIELD_DESC);
      paramTProtocol.writeString(packageName);
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
      ID = new _Fields("ID", 1, (short)2, "id");
      APP_ID = new _Fields("APP_ID", 2, (short)3, "appId");
      PAYLOAD = new _Fields("PAYLOAD", 3, (short)4, "payload");
      CREATE_AT = new _Fields("CREATE_AT", 4, (short)5, "createAt");
      TTL = new _Fields("TTL", 5, (short)6, "ttl");
      COLLAPSE_KEY = new _Fields("COLLAPSE_KEY", 6, (short)7, "collapseKey");
      PACKAGE_NAME = new _Fields("PACKAGE_NAME", 7, (short)8, "packageName");
      $VALUES = new _Fields[] { TO, ID, APP_ID, PAYLOAD, CREATE_AT, TTL, COLLAPSE_KEY, PACKAGE_NAME };
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
 * Qualified Name:     com.xiaomi.xmpush.thrift.PushMessage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */