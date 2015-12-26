package com.xiaomi.xmpush.thrift;

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
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class XmPushActionNotification
  implements TBase<XmPushActionNotification, _Fields>, Serializable, Cloneable
{
  private static final TField APP_ID_FIELD_DESC;
  private static final TField CATEGORY_FIELD_DESC;
  private static final TField DEBUG_FIELD_DESC;
  private static final TField EXTRA_FIELD_DESC;
  private static final TField ID_FIELD_DESC;
  private static final TField PACKAGE_NAME_FIELD_DESC;
  private static final TField PAYLOAD_FIELD_DESC;
  private static final TField REQUIRE_ACK_FIELD_DESC;
  private static final TStruct STRUCT_DESC = new TStruct("XmPushActionNotification");
  private static final TField TARGET_FIELD_DESC;
  private static final TField TYPE_FIELD_DESC;
  public static final Map<_Fields, FieldMetaData> metaDataMap;
  private BitSet __isset_bit_vector = new BitSet(1);
  public String appId;
  public String category;
  public String debug;
  public Map<String, String> extra;
  public String id;
  public String packageName;
  public String payload;
  public boolean requireAck = true;
  public Target target;
  public String type;
  
  static
  {
    DEBUG_FIELD_DESC = new TField("debug", (byte)11, (short)1);
    TARGET_FIELD_DESC = new TField("target", (byte)12, (short)2);
    ID_FIELD_DESC = new TField("id", (byte)11, (short)3);
    APP_ID_FIELD_DESC = new TField("appId", (byte)11, (short)4);
    TYPE_FIELD_DESC = new TField("type", (byte)11, (short)5);
    REQUIRE_ACK_FIELD_DESC = new TField("requireAck", (byte)2, (short)6);
    PAYLOAD_FIELD_DESC = new TField("payload", (byte)11, (short)7);
    EXTRA_FIELD_DESC = new TField("extra", (byte)13, (short)8);
    PACKAGE_NAME_FIELD_DESC = new TField("packageName", (byte)11, (short)9);
    CATEGORY_FIELD_DESC = new TField("category", (byte)11, (short)10);
    EnumMap localEnumMap = new EnumMap(_Fields.class);
    localEnumMap.put(_Fields.DEBUG, new FieldMetaData("debug", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.TARGET, new FieldMetaData("target", (byte)2, new StructMetaData((byte)12, Target.class)));
    localEnumMap.put(_Fields.ID, new FieldMetaData("id", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.APP_ID, new FieldMetaData("appId", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.TYPE, new FieldMetaData("type", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.REQUIRE_ACK, new FieldMetaData("requireAck", (byte)1, new FieldValueMetaData((byte)2)));
    localEnumMap.put(_Fields.PAYLOAD, new FieldMetaData("payload", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.EXTRA, new FieldMetaData("extra", (byte)2, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
    localEnumMap.put(_Fields.PACKAGE_NAME, new FieldMetaData("packageName", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.CATEGORY, new FieldMetaData("category", (byte)2, new FieldValueMetaData((byte)11)));
    metaDataMap = Collections.unmodifiableMap(localEnumMap);
    FieldMetaData.addStructMetaDataMap(XmPushActionNotification.class, metaDataMap);
  }
  
  public int compareTo(XmPushActionNotification paramXmPushActionNotification)
  {
    int i;
    if (!getClass().equals(paramXmPushActionNotification.getClass())) {
      i = getClass().getName().compareTo(paramXmPushActionNotification.getClass().getName());
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
                                            j = Boolean.valueOf(isSetDebug()).compareTo(Boolean.valueOf(paramXmPushActionNotification.isSetDebug()));
                                            i = j;
                                          } while (j != 0);
                                          if (!isSetDebug()) {
                                            break;
                                          }
                                          j = TBaseHelper.compareTo(debug, debug);
                                          i = j;
                                        } while (j != 0);
                                        j = Boolean.valueOf(isSetTarget()).compareTo(Boolean.valueOf(paramXmPushActionNotification.isSetTarget()));
                                        i = j;
                                      } while (j != 0);
                                      if (!isSetTarget()) {
                                        break;
                                      }
                                      j = TBaseHelper.compareTo(target, target);
                                      i = j;
                                    } while (j != 0);
                                    j = Boolean.valueOf(isSetId()).compareTo(Boolean.valueOf(paramXmPushActionNotification.isSetId()));
                                    i = j;
                                  } while (j != 0);
                                  if (!isSetId()) {
                                    break;
                                  }
                                  j = TBaseHelper.compareTo(id, id);
                                  i = j;
                                } while (j != 0);
                                j = Boolean.valueOf(isSetAppId()).compareTo(Boolean.valueOf(paramXmPushActionNotification.isSetAppId()));
                                i = j;
                              } while (j != 0);
                              if (!isSetAppId()) {
                                break;
                              }
                              j = TBaseHelper.compareTo(appId, appId);
                              i = j;
                            } while (j != 0);
                            j = Boolean.valueOf(isSetType()).compareTo(Boolean.valueOf(paramXmPushActionNotification.isSetType()));
                            i = j;
                          } while (j != 0);
                          if (!isSetType()) {
                            break;
                          }
                          j = TBaseHelper.compareTo(type, type);
                          i = j;
                        } while (j != 0);
                        j = Boolean.valueOf(isSetRequireAck()).compareTo(Boolean.valueOf(paramXmPushActionNotification.isSetRequireAck()));
                        i = j;
                      } while (j != 0);
                      if (!isSetRequireAck()) {
                        break;
                      }
                      j = TBaseHelper.compareTo(requireAck, requireAck);
                      i = j;
                    } while (j != 0);
                    j = Boolean.valueOf(isSetPayload()).compareTo(Boolean.valueOf(paramXmPushActionNotification.isSetPayload()));
                    i = j;
                  } while (j != 0);
                  if (!isSetPayload()) {
                    break;
                  }
                  j = TBaseHelper.compareTo(payload, payload);
                  i = j;
                } while (j != 0);
                j = Boolean.valueOf(isSetExtra()).compareTo(Boolean.valueOf(paramXmPushActionNotification.isSetExtra()));
                i = j;
              } while (j != 0);
              if (!isSetExtra()) {
                break;
              }
              j = TBaseHelper.compareTo(extra, extra);
              i = j;
            } while (j != 0);
            j = Boolean.valueOf(isSetPackageName()).compareTo(Boolean.valueOf(paramXmPushActionNotification.isSetPackageName()));
            i = j;
          } while (j != 0);
          if (!isSetPackageName()) {
            break;
          }
          j = TBaseHelper.compareTo(packageName, packageName);
          i = j;
        } while (j != 0);
        j = Boolean.valueOf(isSetCategory()).compareTo(Boolean.valueOf(paramXmPushActionNotification.isSetCategory()));
        i = j;
      } while (j != 0);
      if (!isSetCategory()) {
        break;
      }
      j = TBaseHelper.compareTo(category, category);
      i = j;
    } while (j != 0);
    return 0;
  }
  
  public boolean equals(XmPushActionNotification paramXmPushActionNotification)
  {
    if (paramXmPushActionNotification == null) {
      return false;
    }
    boolean bool1 = isSetDebug();
    boolean bool2 = paramXmPushActionNotification.isSetDebug();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!debug.equals(debug)) {
        return false;
      }
    }
    bool1 = isSetTarget();
    bool2 = paramXmPushActionNotification.isSetTarget();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!target.equals(target)) {
        return false;
      }
    }
    bool1 = isSetId();
    bool2 = paramXmPushActionNotification.isSetId();
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
    bool2 = paramXmPushActionNotification.isSetAppId();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!appId.equals(appId)) {
        return false;
      }
    }
    bool1 = isSetType();
    bool2 = paramXmPushActionNotification.isSetType();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!type.equals(type)) {
        return false;
      }
    }
    if ((1 != 0) || (1 != 0))
    {
      if ((1 == 0) || (1 == 0)) {
        return false;
      }
      if (requireAck != requireAck) {
        return false;
      }
    }
    bool1 = isSetPayload();
    bool2 = paramXmPushActionNotification.isSetPayload();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!payload.equals(payload)) {
        return false;
      }
    }
    bool1 = isSetExtra();
    bool2 = paramXmPushActionNotification.isSetExtra();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!extra.equals(extra)) {
        return false;
      }
    }
    bool1 = isSetPackageName();
    bool2 = paramXmPushActionNotification.isSetPackageName();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!packageName.equals(packageName)) {
        return false;
      }
    }
    bool1 = isSetCategory();
    bool2 = paramXmPushActionNotification.isSetCategory();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!category.equals(category)) {
        return false;
      }
    }
    return true;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    while (!(paramObject instanceof XmPushActionNotification)) {
      return false;
    }
    return equals((XmPushActionNotification)paramObject);
  }
  
  public Map<String, String> getExtra()
  {
    return extra;
  }
  
  public int hashCode()
  {
    return 0;
  }
  
  public boolean isSetAppId()
  {
    return appId != null;
  }
  
  public boolean isSetCategory()
  {
    return category != null;
  }
  
  public boolean isSetDebug()
  {
    return debug != null;
  }
  
  public boolean isSetExtra()
  {
    return extra != null;
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
  
  public boolean isSetRequireAck()
  {
    return __isset_bit_vector.get(0);
  }
  
  public boolean isSetTarget()
  {
    return target != null;
  }
  
  public boolean isSetType()
  {
    return type != null;
  }
  
  public void read(TProtocol paramTProtocol)
    throws TException
  {
    paramTProtocol.readStructBegin();
    Object localObject = paramTProtocol.readFieldBegin();
    if (type == 0)
    {
      paramTProtocol.readStructEnd();
      if (!isSetRequireAck()) {
        throw new TProtocolException("Required field 'requireAck' was not found in serialized data! Struct: " + toString());
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
          debug = paramTProtocol.readString();
        }
        else
        {
          TProtocolUtil.skip(paramTProtocol, type);
          continue;
          if (type == 12)
          {
            target = new Target();
            target.read(paramTProtocol);
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
                  type = paramTProtocol.readString();
                }
                else
                {
                  TProtocolUtil.skip(paramTProtocol, type);
                  continue;
                  if (type == 2)
                  {
                    requireAck = paramTProtocol.readBool();
                    setRequireAckIsSet(true);
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
                      if (type == 13)
                      {
                        localObject = paramTProtocol.readMapBegin();
                        extra = new HashMap(size * 2);
                        int i = 0;
                        while (i < size)
                        {
                          String str1 = paramTProtocol.readString();
                          String str2 = paramTProtocol.readString();
                          extra.put(str1, str2);
                          i += 1;
                        }
                        paramTProtocol.readMapEnd();
                      }
                      else
                      {
                        TProtocolUtil.skip(paramTProtocol, type);
                        continue;
                        if (type == 11)
                        {
                          packageName = paramTProtocol.readString();
                        }
                        else
                        {
                          TProtocolUtil.skip(paramTProtocol, type);
                          continue;
                          if (type == 11) {
                            category = paramTProtocol.readString();
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
    validate();
  }
  
  public XmPushActionNotification setAppId(String paramString)
  {
    appId = paramString;
    return this;
  }
  
  public XmPushActionNotification setExtra(Map<String, String> paramMap)
  {
    extra = paramMap;
    return this;
  }
  
  public XmPushActionNotification setId(String paramString)
  {
    id = paramString;
    return this;
  }
  
  public XmPushActionNotification setRequireAck(boolean paramBoolean)
  {
    requireAck = paramBoolean;
    setRequireAckIsSet(true);
    return this;
  }
  
  public void setRequireAckIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(0, paramBoolean);
  }
  
  public XmPushActionNotification setType(String paramString)
  {
    type = paramString;
    return this;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("XmPushActionNotification(");
    int i = 1;
    if (isSetDebug())
    {
      localStringBuilder.append("debug:");
      if (debug == null)
      {
        localStringBuilder.append("null");
        i = 0;
      }
    }
    else
    {
      int j = i;
      if (isSetTarget())
      {
        if (i == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("target:");
        if (target != null) {
          break label432;
        }
        localStringBuilder.append("null");
        label89:
        j = 0;
      }
      if (j == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("id:");
      if (id != null) {
        break label444;
      }
      localStringBuilder.append("null");
      label126:
      if (isSetAppId())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("appId:");
        if (appId != null) {
          break label456;
        }
        localStringBuilder.append("null");
      }
      label168:
      if (isSetType())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("type:");
        if (type != null) {
          break label468;
        }
        localStringBuilder.append("null");
      }
      label210:
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("requireAck:");
      localStringBuilder.append(requireAck);
      if (isSetPayload())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("payload:");
        if (payload != null) {
          break label480;
        }
        localStringBuilder.append("null");
      }
      label281:
      if (isSetExtra())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("extra:");
        if (extra != null) {
          break label492;
        }
        localStringBuilder.append("null");
      }
      label323:
      if (isSetPackageName())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("packageName:");
        if (packageName != null) {
          break label504;
        }
        localStringBuilder.append("null");
      }
      label365:
      if (isSetCategory())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("category:");
        if (category != null) {
          break label516;
        }
        localStringBuilder.append("null");
      }
    }
    for (;;)
    {
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(debug);
      break;
      label432:
      localStringBuilder.append(target);
      break label89;
      label444:
      localStringBuilder.append(id);
      break label126;
      label456:
      localStringBuilder.append(appId);
      break label168;
      label468:
      localStringBuilder.append(type);
      break label210;
      label480:
      localStringBuilder.append(payload);
      break label281;
      label492:
      localStringBuilder.append(extra);
      break label323;
      label504:
      localStringBuilder.append(packageName);
      break label365;
      label516:
      localStringBuilder.append(category);
    }
  }
  
  public void validate()
    throws TException
  {
    if (id == null) {
      throw new TProtocolException("Required field 'id' was not present! Struct: " + toString());
    }
  }
  
  public void write(TProtocol paramTProtocol)
    throws TException
  {
    validate();
    paramTProtocol.writeStructBegin(STRUCT_DESC);
    if ((debug != null) && (isSetDebug()))
    {
      paramTProtocol.writeFieldBegin(DEBUG_FIELD_DESC);
      paramTProtocol.writeString(debug);
      paramTProtocol.writeFieldEnd();
    }
    if ((target != null) && (isSetTarget()))
    {
      paramTProtocol.writeFieldBegin(TARGET_FIELD_DESC);
      target.write(paramTProtocol);
      paramTProtocol.writeFieldEnd();
    }
    if (id != null)
    {
      paramTProtocol.writeFieldBegin(ID_FIELD_DESC);
      paramTProtocol.writeString(id);
      paramTProtocol.writeFieldEnd();
    }
    if ((appId != null) && (isSetAppId()))
    {
      paramTProtocol.writeFieldBegin(APP_ID_FIELD_DESC);
      paramTProtocol.writeString(appId);
      paramTProtocol.writeFieldEnd();
    }
    if ((type != null) && (isSetType()))
    {
      paramTProtocol.writeFieldBegin(TYPE_FIELD_DESC);
      paramTProtocol.writeString(type);
      paramTProtocol.writeFieldEnd();
    }
    paramTProtocol.writeFieldBegin(REQUIRE_ACK_FIELD_DESC);
    paramTProtocol.writeBool(requireAck);
    paramTProtocol.writeFieldEnd();
    if ((payload != null) && (isSetPayload()))
    {
      paramTProtocol.writeFieldBegin(PAYLOAD_FIELD_DESC);
      paramTProtocol.writeString(payload);
      paramTProtocol.writeFieldEnd();
    }
    if ((extra != null) && (isSetExtra()))
    {
      paramTProtocol.writeFieldBegin(EXTRA_FIELD_DESC);
      paramTProtocol.writeMapBegin(new TMap((byte)11, (byte)11, extra.size()));
      Iterator localIterator = extra.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        paramTProtocol.writeString((String)localEntry.getKey());
        paramTProtocol.writeString((String)localEntry.getValue());
      }
      paramTProtocol.writeMapEnd();
      paramTProtocol.writeFieldEnd();
    }
    if ((packageName != null) && (isSetPackageName()))
    {
      paramTProtocol.writeFieldBegin(PACKAGE_NAME_FIELD_DESC);
      paramTProtocol.writeString(packageName);
      paramTProtocol.writeFieldEnd();
    }
    if ((category != null) && (isSetCategory()))
    {
      paramTProtocol.writeFieldBegin(CATEGORY_FIELD_DESC);
      paramTProtocol.writeString(category);
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
      ID = new _Fields("ID", 2, (short)3, "id");
      APP_ID = new _Fields("APP_ID", 3, (short)4, "appId");
      TYPE = new _Fields("TYPE", 4, (short)5, "type");
      REQUIRE_ACK = new _Fields("REQUIRE_ACK", 5, (short)6, "requireAck");
      PAYLOAD = new _Fields("PAYLOAD", 6, (short)7, "payload");
      EXTRA = new _Fields("EXTRA", 7, (short)8, "extra");
      PACKAGE_NAME = new _Fields("PACKAGE_NAME", 8, (short)9, "packageName");
      CATEGORY = new _Fields("CATEGORY", 9, (short)10, "category");
      $VALUES = new _Fields[] { DEBUG, TARGET, ID, APP_ID, TYPE, REQUIRE_ACK, PAYLOAD, EXTRA, PACKAGE_NAME, CATEGORY };
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
 * Qualified Name:     com.xiaomi.xmpush.thrift.XmPushActionNotification
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */