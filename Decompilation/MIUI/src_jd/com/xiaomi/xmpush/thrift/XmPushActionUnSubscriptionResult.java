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

public class XmPushActionUnSubscriptionResult
  implements TBase<XmPushActionUnSubscriptionResult, _Fields>, Serializable, Cloneable
{
  private static final TField APP_ID_FIELD_DESC;
  private static final TField CATEGORY_FIELD_DESC;
  private static final TField DEBUG_FIELD_DESC;
  private static final TField ERROR_CODE_FIELD_DESC;
  private static final TField ID_FIELD_DESC;
  private static final TField PACKAGE_NAME_FIELD_DESC;
  private static final TField REASON_FIELD_DESC;
  private static final TField REQUEST_FIELD_DESC;
  private static final TStruct STRUCT_DESC = new TStruct("XmPushActionUnSubscriptionResult");
  private static final TField TARGET_FIELD_DESC;
  private static final TField TOPIC_FIELD_DESC;
  public static final Map<_Fields, FieldMetaData> metaDataMap;
  private BitSet __isset_bit_vector = new BitSet(1);
  public String appId;
  public String category;
  public String debug;
  public long errorCode;
  public String id;
  public String packageName;
  public String reason;
  public XmPushActionUnSubscription request;
  public Target target;
  public String topic;
  
  static
  {
    DEBUG_FIELD_DESC = new TField("debug", (byte)11, (short)1);
    TARGET_FIELD_DESC = new TField("target", (byte)12, (short)2);
    ID_FIELD_DESC = new TField("id", (byte)11, (short)3);
    APP_ID_FIELD_DESC = new TField("appId", (byte)11, (short)4);
    REQUEST_FIELD_DESC = new TField("request", (byte)12, (short)5);
    ERROR_CODE_FIELD_DESC = new TField("errorCode", (byte)10, (short)6);
    REASON_FIELD_DESC = new TField("reason", (byte)11, (short)7);
    TOPIC_FIELD_DESC = new TField("topic", (byte)11, (short)8);
    PACKAGE_NAME_FIELD_DESC = new TField("packageName", (byte)11, (short)9);
    CATEGORY_FIELD_DESC = new TField("category", (byte)11, (short)10);
    EnumMap localEnumMap = new EnumMap(_Fields.class);
    localEnumMap.put(_Fields.DEBUG, new FieldMetaData("debug", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.TARGET, new FieldMetaData("target", (byte)2, new StructMetaData((byte)12, Target.class)));
    localEnumMap.put(_Fields.ID, new FieldMetaData("id", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.APP_ID, new FieldMetaData("appId", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.REQUEST, new FieldMetaData("request", (byte)2, new StructMetaData((byte)12, XmPushActionUnSubscription.class)));
    localEnumMap.put(_Fields.ERROR_CODE, new FieldMetaData("errorCode", (byte)2, new FieldValueMetaData((byte)10)));
    localEnumMap.put(_Fields.REASON, new FieldMetaData("reason", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.TOPIC, new FieldMetaData("topic", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.PACKAGE_NAME, new FieldMetaData("packageName", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.CATEGORY, new FieldMetaData("category", (byte)2, new FieldValueMetaData((byte)11)));
    metaDataMap = Collections.unmodifiableMap(localEnumMap);
    FieldMetaData.addStructMetaDataMap(XmPushActionUnSubscriptionResult.class, metaDataMap);
  }
  
  public int compareTo(XmPushActionUnSubscriptionResult paramXmPushActionUnSubscriptionResult)
  {
    int i;
    if (!getClass().equals(paramXmPushActionUnSubscriptionResult.getClass())) {
      i = getClass().getName().compareTo(paramXmPushActionUnSubscriptionResult.getClass().getName());
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
                                            j = Boolean.valueOf(isSetDebug()).compareTo(Boolean.valueOf(paramXmPushActionUnSubscriptionResult.isSetDebug()));
                                            i = j;
                                          } while (j != 0);
                                          if (!isSetDebug()) {
                                            break;
                                          }
                                          j = TBaseHelper.compareTo(debug, debug);
                                          i = j;
                                        } while (j != 0);
                                        j = Boolean.valueOf(isSetTarget()).compareTo(Boolean.valueOf(paramXmPushActionUnSubscriptionResult.isSetTarget()));
                                        i = j;
                                      } while (j != 0);
                                      if (!isSetTarget()) {
                                        break;
                                      }
                                      j = TBaseHelper.compareTo(target, target);
                                      i = j;
                                    } while (j != 0);
                                    j = Boolean.valueOf(isSetId()).compareTo(Boolean.valueOf(paramXmPushActionUnSubscriptionResult.isSetId()));
                                    i = j;
                                  } while (j != 0);
                                  if (!isSetId()) {
                                    break;
                                  }
                                  j = TBaseHelper.compareTo(id, id);
                                  i = j;
                                } while (j != 0);
                                j = Boolean.valueOf(isSetAppId()).compareTo(Boolean.valueOf(paramXmPushActionUnSubscriptionResult.isSetAppId()));
                                i = j;
                              } while (j != 0);
                              if (!isSetAppId()) {
                                break;
                              }
                              j = TBaseHelper.compareTo(appId, appId);
                              i = j;
                            } while (j != 0);
                            j = Boolean.valueOf(isSetRequest()).compareTo(Boolean.valueOf(paramXmPushActionUnSubscriptionResult.isSetRequest()));
                            i = j;
                          } while (j != 0);
                          if (!isSetRequest()) {
                            break;
                          }
                          j = TBaseHelper.compareTo(request, request);
                          i = j;
                        } while (j != 0);
                        j = Boolean.valueOf(isSetErrorCode()).compareTo(Boolean.valueOf(paramXmPushActionUnSubscriptionResult.isSetErrorCode()));
                        i = j;
                      } while (j != 0);
                      if (!isSetErrorCode()) {
                        break;
                      }
                      j = TBaseHelper.compareTo(errorCode, errorCode);
                      i = j;
                    } while (j != 0);
                    j = Boolean.valueOf(isSetReason()).compareTo(Boolean.valueOf(paramXmPushActionUnSubscriptionResult.isSetReason()));
                    i = j;
                  } while (j != 0);
                  if (!isSetReason()) {
                    break;
                  }
                  j = TBaseHelper.compareTo(reason, reason);
                  i = j;
                } while (j != 0);
                j = Boolean.valueOf(isSetTopic()).compareTo(Boolean.valueOf(paramXmPushActionUnSubscriptionResult.isSetTopic()));
                i = j;
              } while (j != 0);
              if (!isSetTopic()) {
                break;
              }
              j = TBaseHelper.compareTo(topic, topic);
              i = j;
            } while (j != 0);
            j = Boolean.valueOf(isSetPackageName()).compareTo(Boolean.valueOf(paramXmPushActionUnSubscriptionResult.isSetPackageName()));
            i = j;
          } while (j != 0);
          if (!isSetPackageName()) {
            break;
          }
          j = TBaseHelper.compareTo(packageName, packageName);
          i = j;
        } while (j != 0);
        j = Boolean.valueOf(isSetCategory()).compareTo(Boolean.valueOf(paramXmPushActionUnSubscriptionResult.isSetCategory()));
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
  
  public boolean equals(XmPushActionUnSubscriptionResult paramXmPushActionUnSubscriptionResult)
  {
    if (paramXmPushActionUnSubscriptionResult == null) {
      return false;
    }
    boolean bool1 = isSetDebug();
    boolean bool2 = paramXmPushActionUnSubscriptionResult.isSetDebug();
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
    bool2 = paramXmPushActionUnSubscriptionResult.isSetTarget();
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
    bool2 = paramXmPushActionUnSubscriptionResult.isSetId();
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
    bool2 = paramXmPushActionUnSubscriptionResult.isSetAppId();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!appId.equals(appId)) {
        return false;
      }
    }
    bool1 = isSetRequest();
    bool2 = paramXmPushActionUnSubscriptionResult.isSetRequest();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!request.equals(request)) {
        return false;
      }
    }
    bool1 = isSetErrorCode();
    bool2 = paramXmPushActionUnSubscriptionResult.isSetErrorCode();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (errorCode != errorCode) {
        return false;
      }
    }
    bool1 = isSetReason();
    bool2 = paramXmPushActionUnSubscriptionResult.isSetReason();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!reason.equals(reason)) {
        return false;
      }
    }
    bool1 = isSetTopic();
    bool2 = paramXmPushActionUnSubscriptionResult.isSetTopic();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!topic.equals(topic)) {
        return false;
      }
    }
    bool1 = isSetPackageName();
    bool2 = paramXmPushActionUnSubscriptionResult.isSetPackageName();
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
    bool2 = paramXmPushActionUnSubscriptionResult.isSetCategory();
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
    while (!(paramObject instanceof XmPushActionUnSubscriptionResult)) {
      return false;
    }
    return equals((XmPushActionUnSubscriptionResult)paramObject);
  }
  
  public String getCategory()
  {
    return category;
  }
  
  public String getTopic()
  {
    return topic;
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
  
  public boolean isSetErrorCode()
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
  
  public boolean isSetReason()
  {
    return reason != null;
  }
  
  public boolean isSetRequest()
  {
    return request != null;
  }
  
  public boolean isSetTarget()
  {
    return target != null;
  }
  
  public boolean isSetTopic()
  {
    return topic != null;
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
              if (type == 12)
              {
                request = new XmPushActionUnSubscription();
                request.read(paramTProtocol);
              }
              else
              {
                TProtocolUtil.skip(paramTProtocol, type);
                continue;
                if (type == 10)
                {
                  errorCode = paramTProtocol.readI64();
                  setErrorCodeIsSet(true);
                }
                else
                {
                  TProtocolUtil.skip(paramTProtocol, type);
                  continue;
                  if (type == 11)
                  {
                    reason = paramTProtocol.readString();
                  }
                  else
                  {
                    TProtocolUtil.skip(paramTProtocol, type);
                    continue;
                    if (type == 11)
                    {
                      topic = paramTProtocol.readString();
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
  
  public void setErrorCodeIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(0, paramBoolean);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("XmPushActionUnSubscriptionResult(");
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
          break label439;
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
        break label451;
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
          break label463;
        }
        localStringBuilder.append("null");
      }
      label168:
      if (isSetRequest())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("request:");
        if (request != null) {
          break label475;
        }
        localStringBuilder.append("null");
      }
      label210:
      if (isSetErrorCode())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("errorCode:");
        localStringBuilder.append(errorCode);
      }
      if (isSetReason())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("reason:");
        if (reason != null) {
          break label487;
        }
        localStringBuilder.append("null");
      }
      label288:
      if (isSetTopic())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("topic:");
        if (topic != null) {
          break label499;
        }
        localStringBuilder.append("null");
      }
      label330:
      if (isSetPackageName())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("packageName:");
        if (packageName != null) {
          break label511;
        }
        localStringBuilder.append("null");
      }
      label372:
      if (isSetCategory())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("category:");
        if (category != null) {
          break label523;
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
      label439:
      localStringBuilder.append(target);
      break label89;
      label451:
      localStringBuilder.append(id);
      break label126;
      label463:
      localStringBuilder.append(appId);
      break label168;
      label475:
      localStringBuilder.append(request);
      break label210;
      label487:
      localStringBuilder.append(reason);
      break label288;
      label499:
      localStringBuilder.append(topic);
      break label330;
      label511:
      localStringBuilder.append(packageName);
      break label372;
      label523:
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
    if ((request != null) && (isSetRequest()))
    {
      paramTProtocol.writeFieldBegin(REQUEST_FIELD_DESC);
      request.write(paramTProtocol);
      paramTProtocol.writeFieldEnd();
    }
    if (isSetErrorCode())
    {
      paramTProtocol.writeFieldBegin(ERROR_CODE_FIELD_DESC);
      paramTProtocol.writeI64(errorCode);
      paramTProtocol.writeFieldEnd();
    }
    if ((reason != null) && (isSetReason()))
    {
      paramTProtocol.writeFieldBegin(REASON_FIELD_DESC);
      paramTProtocol.writeString(reason);
      paramTProtocol.writeFieldEnd();
    }
    if ((topic != null) && (isSetTopic()))
    {
      paramTProtocol.writeFieldBegin(TOPIC_FIELD_DESC);
      paramTProtocol.writeString(topic);
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
      REQUEST = new _Fields("REQUEST", 4, (short)5, "request");
      ERROR_CODE = new _Fields("ERROR_CODE", 5, (short)6, "errorCode");
      REASON = new _Fields("REASON", 6, (short)7, "reason");
      TOPIC = new _Fields("TOPIC", 7, (short)8, "topic");
      PACKAGE_NAME = new _Fields("PACKAGE_NAME", 8, (short)9, "packageName");
      CATEGORY = new _Fields("CATEGORY", 9, (short)10, "category");
      $VALUES = new _Fields[] { DEBUG, TARGET, ID, APP_ID, REQUEST, ERROR_CODE, REASON, TOPIC, PACKAGE_NAME, CATEGORY };
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
 * Qualified Name:     com.xiaomi.xmpush.thrift.XmPushActionUnSubscriptionResult
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */