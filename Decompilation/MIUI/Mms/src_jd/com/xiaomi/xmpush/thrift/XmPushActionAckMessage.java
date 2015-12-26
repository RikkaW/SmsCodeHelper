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

public class XmPushActionAckMessage
  implements TBase<XmPushActionAckMessage, _Fields>, Serializable, Cloneable
{
  private static final TField ALIAS_NAME_FIELD_DESC;
  private static final TField APP_ID_FIELD_DESC;
  private static final TField CATEGORY_FIELD_DESC;
  private static final TField DEBUG_FIELD_DESC;
  private static final TField ID_FIELD_DESC;
  private static final TField MESSAGE_TS_FIELD_DESC;
  private static final TField PACKAGE_NAME_FIELD_DESC;
  private static final TField REQUEST_FIELD_DESC;
  private static final TStruct STRUCT_DESC = new TStruct("XmPushActionAckMessage");
  private static final TField TARGET_FIELD_DESC;
  private static final TField TOPIC_FIELD_DESC;
  public static final Map<_Fields, FieldMetaData> metaDataMap;
  private BitSet __isset_bit_vector = new BitSet(1);
  public String aliasName;
  public String appId;
  public String category;
  public String debug;
  public String id;
  public long messageTs;
  public String packageName;
  public XmPushActionSendMessage request;
  public Target target;
  public String topic;
  
  static
  {
    DEBUG_FIELD_DESC = new TField("debug", (byte)11, (short)1);
    TARGET_FIELD_DESC = new TField("target", (byte)12, (short)2);
    ID_FIELD_DESC = new TField("id", (byte)11, (short)3);
    APP_ID_FIELD_DESC = new TField("appId", (byte)11, (short)4);
    MESSAGE_TS_FIELD_DESC = new TField("messageTs", (byte)10, (short)5);
    TOPIC_FIELD_DESC = new TField("topic", (byte)11, (short)6);
    ALIAS_NAME_FIELD_DESC = new TField("aliasName", (byte)11, (short)7);
    REQUEST_FIELD_DESC = new TField("request", (byte)12, (short)8);
    PACKAGE_NAME_FIELD_DESC = new TField("packageName", (byte)11, (short)9);
    CATEGORY_FIELD_DESC = new TField("category", (byte)11, (short)10);
    EnumMap localEnumMap = new EnumMap(_Fields.class);
    localEnumMap.put(_Fields.DEBUG, new FieldMetaData("debug", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.TARGET, new FieldMetaData("target", (byte)2, new StructMetaData((byte)12, Target.class)));
    localEnumMap.put(_Fields.ID, new FieldMetaData("id", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.APP_ID, new FieldMetaData("appId", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.MESSAGE_TS, new FieldMetaData("messageTs", (byte)1, new FieldValueMetaData((byte)10)));
    localEnumMap.put(_Fields.TOPIC, new FieldMetaData("topic", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.ALIAS_NAME, new FieldMetaData("aliasName", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.REQUEST, new FieldMetaData("request", (byte)2, new StructMetaData((byte)12, XmPushActionSendMessage.class)));
    localEnumMap.put(_Fields.PACKAGE_NAME, new FieldMetaData("packageName", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.CATEGORY, new FieldMetaData("category", (byte)2, new FieldValueMetaData((byte)11)));
    metaDataMap = Collections.unmodifiableMap(localEnumMap);
    FieldMetaData.addStructMetaDataMap(XmPushActionAckMessage.class, metaDataMap);
  }
  
  public int compareTo(XmPushActionAckMessage paramXmPushActionAckMessage)
  {
    int i;
    if (!getClass().equals(paramXmPushActionAckMessage.getClass())) {
      i = getClass().getName().compareTo(paramXmPushActionAckMessage.getClass().getName());
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
                                            j = Boolean.valueOf(isSetDebug()).compareTo(Boolean.valueOf(paramXmPushActionAckMessage.isSetDebug()));
                                            i = j;
                                          } while (j != 0);
                                          if (!isSetDebug()) {
                                            break;
                                          }
                                          j = TBaseHelper.compareTo(debug, debug);
                                          i = j;
                                        } while (j != 0);
                                        j = Boolean.valueOf(isSetTarget()).compareTo(Boolean.valueOf(paramXmPushActionAckMessage.isSetTarget()));
                                        i = j;
                                      } while (j != 0);
                                      if (!isSetTarget()) {
                                        break;
                                      }
                                      j = TBaseHelper.compareTo(target, target);
                                      i = j;
                                    } while (j != 0);
                                    j = Boolean.valueOf(isSetId()).compareTo(Boolean.valueOf(paramXmPushActionAckMessage.isSetId()));
                                    i = j;
                                  } while (j != 0);
                                  if (!isSetId()) {
                                    break;
                                  }
                                  j = TBaseHelper.compareTo(id, id);
                                  i = j;
                                } while (j != 0);
                                j = Boolean.valueOf(isSetAppId()).compareTo(Boolean.valueOf(paramXmPushActionAckMessage.isSetAppId()));
                                i = j;
                              } while (j != 0);
                              if (!isSetAppId()) {
                                break;
                              }
                              j = TBaseHelper.compareTo(appId, appId);
                              i = j;
                            } while (j != 0);
                            j = Boolean.valueOf(isSetMessageTs()).compareTo(Boolean.valueOf(paramXmPushActionAckMessage.isSetMessageTs()));
                            i = j;
                          } while (j != 0);
                          if (!isSetMessageTs()) {
                            break;
                          }
                          j = TBaseHelper.compareTo(messageTs, messageTs);
                          i = j;
                        } while (j != 0);
                        j = Boolean.valueOf(isSetTopic()).compareTo(Boolean.valueOf(paramXmPushActionAckMessage.isSetTopic()));
                        i = j;
                      } while (j != 0);
                      if (!isSetTopic()) {
                        break;
                      }
                      j = TBaseHelper.compareTo(topic, topic);
                      i = j;
                    } while (j != 0);
                    j = Boolean.valueOf(isSetAliasName()).compareTo(Boolean.valueOf(paramXmPushActionAckMessage.isSetAliasName()));
                    i = j;
                  } while (j != 0);
                  if (!isSetAliasName()) {
                    break;
                  }
                  j = TBaseHelper.compareTo(aliasName, aliasName);
                  i = j;
                } while (j != 0);
                j = Boolean.valueOf(isSetRequest()).compareTo(Boolean.valueOf(paramXmPushActionAckMessage.isSetRequest()));
                i = j;
              } while (j != 0);
              if (!isSetRequest()) {
                break;
              }
              j = TBaseHelper.compareTo(request, request);
              i = j;
            } while (j != 0);
            j = Boolean.valueOf(isSetPackageName()).compareTo(Boolean.valueOf(paramXmPushActionAckMessage.isSetPackageName()));
            i = j;
          } while (j != 0);
          if (!isSetPackageName()) {
            break;
          }
          j = TBaseHelper.compareTo(packageName, packageName);
          i = j;
        } while (j != 0);
        j = Boolean.valueOf(isSetCategory()).compareTo(Boolean.valueOf(paramXmPushActionAckMessage.isSetCategory()));
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
  
  public boolean equals(XmPushActionAckMessage paramXmPushActionAckMessage)
  {
    if (paramXmPushActionAckMessage == null) {
      return false;
    }
    boolean bool1 = isSetDebug();
    boolean bool2 = paramXmPushActionAckMessage.isSetDebug();
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
    bool2 = paramXmPushActionAckMessage.isSetTarget();
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
    bool2 = paramXmPushActionAckMessage.isSetId();
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
    bool2 = paramXmPushActionAckMessage.isSetAppId();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!appId.equals(appId)) {
        return false;
      }
    }
    if ((1 != 0) || (1 != 0))
    {
      if ((1 == 0) || (1 == 0)) {
        return false;
      }
      if (messageTs != messageTs) {
        return false;
      }
    }
    bool1 = isSetTopic();
    bool2 = paramXmPushActionAckMessage.isSetTopic();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!topic.equals(topic)) {
        return false;
      }
    }
    bool1 = isSetAliasName();
    bool2 = paramXmPushActionAckMessage.isSetAliasName();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!aliasName.equals(aliasName)) {
        return false;
      }
    }
    bool1 = isSetRequest();
    bool2 = paramXmPushActionAckMessage.isSetRequest();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!request.equals(request)) {
        return false;
      }
    }
    bool1 = isSetPackageName();
    bool2 = paramXmPushActionAckMessage.isSetPackageName();
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
    bool2 = paramXmPushActionAckMessage.isSetCategory();
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
    while (!(paramObject instanceof XmPushActionAckMessage)) {
      return false;
    }
    return equals((XmPushActionAckMessage)paramObject);
  }
  
  public int hashCode()
  {
    return 0;
  }
  
  public boolean isSetAliasName()
  {
    return aliasName != null;
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
  
  public boolean isSetId()
  {
    return id != null;
  }
  
  public boolean isSetMessageTs()
  {
    return __isset_bit_vector.get(0);
  }
  
  public boolean isSetPackageName()
  {
    return packageName != null;
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
      if (!isSetMessageTs()) {
        throw new TProtocolException("Required field 'messageTs' was not found in serialized data! Struct: " + toString());
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
                if (type == 10)
                {
                  messageTs = paramTProtocol.readI64();
                  setMessageTsIsSet(true);
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
                      aliasName = paramTProtocol.readString();
                    }
                    else
                    {
                      TProtocolUtil.skip(paramTProtocol, type);
                      continue;
                      if (type == 12)
                      {
                        request = new XmPushActionSendMessage();
                        request.read(paramTProtocol);
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
  
  public XmPushActionAckMessage setAliasName(String paramString)
  {
    aliasName = paramString;
    return this;
  }
  
  public XmPushActionAckMessage setAppId(String paramString)
  {
    appId = paramString;
    return this;
  }
  
  public XmPushActionAckMessage setId(String paramString)
  {
    id = paramString;
    return this;
  }
  
  public XmPushActionAckMessage setMessageTs(long paramLong)
  {
    messageTs = paramLong;
    setMessageTsIsSet(true);
    return this;
  }
  
  public void setMessageTsIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(0, paramBoolean);
  }
  
  public XmPushActionAckMessage setTopic(String paramString)
  {
    topic = paramString;
    return this;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("XmPushActionAckMessage(");
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
          break label425;
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
        break label437;
      }
      localStringBuilder.append("null");
      label126:
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("appId:");
      if (appId != null) {
        break label449;
      }
      localStringBuilder.append("null");
      label161:
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("messageTs:");
      localStringBuilder.append(messageTs);
      if (isSetTopic())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("topic:");
        if (topic != null) {
          break label461;
        }
        localStringBuilder.append("null");
      }
      label232:
      if (isSetAliasName())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("aliasName:");
        if (aliasName != null) {
          break label473;
        }
        localStringBuilder.append("null");
      }
      label274:
      if (isSetRequest())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("request:");
        if (request != null) {
          break label485;
        }
        localStringBuilder.append("null");
      }
      label316:
      if (isSetPackageName())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("packageName:");
        if (packageName != null) {
          break label497;
        }
        localStringBuilder.append("null");
      }
      label358:
      if (isSetCategory())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("category:");
        if (category != null) {
          break label509;
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
      label425:
      localStringBuilder.append(target);
      break label89;
      label437:
      localStringBuilder.append(id);
      break label126;
      label449:
      localStringBuilder.append(appId);
      break label161;
      label461:
      localStringBuilder.append(topic);
      break label232;
      label473:
      localStringBuilder.append(aliasName);
      break label274;
      label485:
      localStringBuilder.append(request);
      break label316;
      label497:
      localStringBuilder.append(packageName);
      break label358;
      label509:
      localStringBuilder.append(category);
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
    if (appId != null)
    {
      paramTProtocol.writeFieldBegin(APP_ID_FIELD_DESC);
      paramTProtocol.writeString(appId);
      paramTProtocol.writeFieldEnd();
    }
    paramTProtocol.writeFieldBegin(MESSAGE_TS_FIELD_DESC);
    paramTProtocol.writeI64(messageTs);
    paramTProtocol.writeFieldEnd();
    if ((topic != null) && (isSetTopic()))
    {
      paramTProtocol.writeFieldBegin(TOPIC_FIELD_DESC);
      paramTProtocol.writeString(topic);
      paramTProtocol.writeFieldEnd();
    }
    if ((aliasName != null) && (isSetAliasName()))
    {
      paramTProtocol.writeFieldBegin(ALIAS_NAME_FIELD_DESC);
      paramTProtocol.writeString(aliasName);
      paramTProtocol.writeFieldEnd();
    }
    if ((request != null) && (isSetRequest()))
    {
      paramTProtocol.writeFieldBegin(REQUEST_FIELD_DESC);
      request.write(paramTProtocol);
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
      MESSAGE_TS = new _Fields("MESSAGE_TS", 4, (short)5, "messageTs");
      TOPIC = new _Fields("TOPIC", 5, (short)6, "topic");
      ALIAS_NAME = new _Fields("ALIAS_NAME", 6, (short)7, "aliasName");
      REQUEST = new _Fields("REQUEST", 7, (short)8, "request");
      PACKAGE_NAME = new _Fields("PACKAGE_NAME", 8, (short)9, "packageName");
      CATEGORY = new _Fields("CATEGORY", 9, (short)10, "category");
      $VALUES = new _Fields[] { DEBUG, TARGET, ID, APP_ID, MESSAGE_TS, TOPIC, ALIAS_NAME, REQUEST, PACKAGE_NAME, CATEGORY };
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
 * Qualified Name:     com.xiaomi.xmpush.thrift.XmPushActionAckMessage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */