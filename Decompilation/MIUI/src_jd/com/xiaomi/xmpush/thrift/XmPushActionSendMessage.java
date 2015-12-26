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

public class XmPushActionSendMessage
  implements TBase<XmPushActionSendMessage, _Fields>, Serializable, Cloneable
{
  private static final TField ALIAS_NAME_FIELD_DESC;
  private static final TField APP_ID_FIELD_DESC;
  private static final TField CATEGORY_FIELD_DESC;
  private static final TField DEBUG_FIELD_DESC;
  private static final TField ID_FIELD_DESC;
  private static final TField MESSAGE_FIELD_DESC;
  private static final TField NEED_ACK_FIELD_DESC;
  private static final TField PACKAGE_NAME_FIELD_DESC;
  private static final TField PARAMS_FIELD_DESC;
  private static final TStruct STRUCT_DESC = new TStruct("XmPushActionSendMessage");
  private static final TField TARGET_FIELD_DESC;
  private static final TField TOPIC_FIELD_DESC;
  private static final TField USER_ACCOUNT_FIELD_DESC;
  public static final Map<_Fields, FieldMetaData> metaDataMap;
  private BitSet __isset_bit_vector = new BitSet(1);
  public String aliasName;
  public String appId;
  public String category;
  public String debug;
  public String id;
  public PushMessage message;
  public boolean needAck = true;
  public String packageName;
  public Map<String, String> params;
  public Target target;
  public String topic;
  public String userAccount;
  
  static
  {
    DEBUG_FIELD_DESC = new TField("debug", (byte)11, (short)1);
    TARGET_FIELD_DESC = new TField("target", (byte)12, (short)2);
    ID_FIELD_DESC = new TField("id", (byte)11, (short)3);
    APP_ID_FIELD_DESC = new TField("appId", (byte)11, (short)4);
    PACKAGE_NAME_FIELD_DESC = new TField("packageName", (byte)11, (short)5);
    TOPIC_FIELD_DESC = new TField("topic", (byte)11, (short)6);
    ALIAS_NAME_FIELD_DESC = new TField("aliasName", (byte)11, (short)7);
    MESSAGE_FIELD_DESC = new TField("message", (byte)12, (short)8);
    NEED_ACK_FIELD_DESC = new TField("needAck", (byte)2, (short)9);
    PARAMS_FIELD_DESC = new TField("params", (byte)13, (short)10);
    CATEGORY_FIELD_DESC = new TField("category", (byte)11, (short)11);
    USER_ACCOUNT_FIELD_DESC = new TField("userAccount", (byte)11, (short)12);
    EnumMap localEnumMap = new EnumMap(_Fields.class);
    localEnumMap.put(_Fields.DEBUG, new FieldMetaData("debug", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.TARGET, new FieldMetaData("target", (byte)2, new StructMetaData((byte)12, Target.class)));
    localEnumMap.put(_Fields.ID, new FieldMetaData("id", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.APP_ID, new FieldMetaData("appId", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.PACKAGE_NAME, new FieldMetaData("packageName", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.TOPIC, new FieldMetaData("topic", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.ALIAS_NAME, new FieldMetaData("aliasName", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.MESSAGE, new FieldMetaData("message", (byte)2, new StructMetaData((byte)12, PushMessage.class)));
    localEnumMap.put(_Fields.NEED_ACK, new FieldMetaData("needAck", (byte)2, new FieldValueMetaData((byte)2)));
    localEnumMap.put(_Fields.PARAMS, new FieldMetaData("params", (byte)2, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
    localEnumMap.put(_Fields.CATEGORY, new FieldMetaData("category", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.USER_ACCOUNT, new FieldMetaData("userAccount", (byte)2, new FieldValueMetaData((byte)11)));
    metaDataMap = Collections.unmodifiableMap(localEnumMap);
    FieldMetaData.addStructMetaDataMap(XmPushActionSendMessage.class, metaDataMap);
  }
  
  public int compareTo(XmPushActionSendMessage paramXmPushActionSendMessage)
  {
    int i;
    if (!getClass().equals(paramXmPushActionSendMessage.getClass())) {
      i = getClass().getName().compareTo(paramXmPushActionSendMessage.getClass().getName());
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
                                            do
                                            {
                                              do
                                              {
                                                do
                                                {
                                                  do
                                                  {
                                                    return i;
                                                    j = Boolean.valueOf(isSetDebug()).compareTo(Boolean.valueOf(paramXmPushActionSendMessage.isSetDebug()));
                                                    i = j;
                                                  } while (j != 0);
                                                  if (!isSetDebug()) {
                                                    break;
                                                  }
                                                  j = TBaseHelper.compareTo(debug, debug);
                                                  i = j;
                                                } while (j != 0);
                                                j = Boolean.valueOf(isSetTarget()).compareTo(Boolean.valueOf(paramXmPushActionSendMessage.isSetTarget()));
                                                i = j;
                                              } while (j != 0);
                                              if (!isSetTarget()) {
                                                break;
                                              }
                                              j = TBaseHelper.compareTo(target, target);
                                              i = j;
                                            } while (j != 0);
                                            j = Boolean.valueOf(isSetId()).compareTo(Boolean.valueOf(paramXmPushActionSendMessage.isSetId()));
                                            i = j;
                                          } while (j != 0);
                                          if (!isSetId()) {
                                            break;
                                          }
                                          j = TBaseHelper.compareTo(id, id);
                                          i = j;
                                        } while (j != 0);
                                        j = Boolean.valueOf(isSetAppId()).compareTo(Boolean.valueOf(paramXmPushActionSendMessage.isSetAppId()));
                                        i = j;
                                      } while (j != 0);
                                      if (!isSetAppId()) {
                                        break;
                                      }
                                      j = TBaseHelper.compareTo(appId, appId);
                                      i = j;
                                    } while (j != 0);
                                    j = Boolean.valueOf(isSetPackageName()).compareTo(Boolean.valueOf(paramXmPushActionSendMessage.isSetPackageName()));
                                    i = j;
                                  } while (j != 0);
                                  if (!isSetPackageName()) {
                                    break;
                                  }
                                  j = TBaseHelper.compareTo(packageName, packageName);
                                  i = j;
                                } while (j != 0);
                                j = Boolean.valueOf(isSetTopic()).compareTo(Boolean.valueOf(paramXmPushActionSendMessage.isSetTopic()));
                                i = j;
                              } while (j != 0);
                              if (!isSetTopic()) {
                                break;
                              }
                              j = TBaseHelper.compareTo(topic, topic);
                              i = j;
                            } while (j != 0);
                            j = Boolean.valueOf(isSetAliasName()).compareTo(Boolean.valueOf(paramXmPushActionSendMessage.isSetAliasName()));
                            i = j;
                          } while (j != 0);
                          if (!isSetAliasName()) {
                            break;
                          }
                          j = TBaseHelper.compareTo(aliasName, aliasName);
                          i = j;
                        } while (j != 0);
                        j = Boolean.valueOf(isSetMessage()).compareTo(Boolean.valueOf(paramXmPushActionSendMessage.isSetMessage()));
                        i = j;
                      } while (j != 0);
                      if (!isSetMessage()) {
                        break;
                      }
                      j = TBaseHelper.compareTo(message, message);
                      i = j;
                    } while (j != 0);
                    j = Boolean.valueOf(isSetNeedAck()).compareTo(Boolean.valueOf(paramXmPushActionSendMessage.isSetNeedAck()));
                    i = j;
                  } while (j != 0);
                  if (!isSetNeedAck()) {
                    break;
                  }
                  j = TBaseHelper.compareTo(needAck, needAck);
                  i = j;
                } while (j != 0);
                j = Boolean.valueOf(isSetParams()).compareTo(Boolean.valueOf(paramXmPushActionSendMessage.isSetParams()));
                i = j;
              } while (j != 0);
              if (!isSetParams()) {
                break;
              }
              j = TBaseHelper.compareTo(params, params);
              i = j;
            } while (j != 0);
            j = Boolean.valueOf(isSetCategory()).compareTo(Boolean.valueOf(paramXmPushActionSendMessage.isSetCategory()));
            i = j;
          } while (j != 0);
          if (!isSetCategory()) {
            break;
          }
          j = TBaseHelper.compareTo(category, category);
          i = j;
        } while (j != 0);
        j = Boolean.valueOf(isSetUserAccount()).compareTo(Boolean.valueOf(paramXmPushActionSendMessage.isSetUserAccount()));
        i = j;
      } while (j != 0);
      if (!isSetUserAccount()) {
        break;
      }
      j = TBaseHelper.compareTo(userAccount, userAccount);
      i = j;
    } while (j != 0);
    return 0;
  }
  
  public boolean equals(XmPushActionSendMessage paramXmPushActionSendMessage)
  {
    if (paramXmPushActionSendMessage == null) {
      return false;
    }
    boolean bool1 = isSetDebug();
    boolean bool2 = paramXmPushActionSendMessage.isSetDebug();
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
    bool2 = paramXmPushActionSendMessage.isSetTarget();
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
    bool2 = paramXmPushActionSendMessage.isSetId();
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
    bool2 = paramXmPushActionSendMessage.isSetAppId();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!appId.equals(appId)) {
        return false;
      }
    }
    bool1 = isSetPackageName();
    bool2 = paramXmPushActionSendMessage.isSetPackageName();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!packageName.equals(packageName)) {
        return false;
      }
    }
    bool1 = isSetTopic();
    bool2 = paramXmPushActionSendMessage.isSetTopic();
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
    bool2 = paramXmPushActionSendMessage.isSetAliasName();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!aliasName.equals(aliasName)) {
        return false;
      }
    }
    bool1 = isSetMessage();
    bool2 = paramXmPushActionSendMessage.isSetMessage();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!message.equals(message)) {
        return false;
      }
    }
    bool1 = isSetNeedAck();
    bool2 = paramXmPushActionSendMessage.isSetNeedAck();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (needAck != needAck) {
        return false;
      }
    }
    bool1 = isSetParams();
    bool2 = paramXmPushActionSendMessage.isSetParams();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!params.equals(params)) {
        return false;
      }
    }
    bool1 = isSetCategory();
    bool2 = paramXmPushActionSendMessage.isSetCategory();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!category.equals(category)) {
        return false;
      }
    }
    bool1 = isSetUserAccount();
    bool2 = paramXmPushActionSendMessage.isSetUserAccount();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!userAccount.equals(userAccount)) {
        return false;
      }
    }
    return true;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    while (!(paramObject instanceof XmPushActionSendMessage)) {
      return false;
    }
    return equals((XmPushActionSendMessage)paramObject);
  }
  
  public String getAliasName()
  {
    return aliasName;
  }
  
  public String getAppId()
  {
    return appId;
  }
  
  public String getCategory()
  {
    return category;
  }
  
  public String getId()
  {
    return id;
  }
  
  public PushMessage getMessage()
  {
    return message;
  }
  
  public String getTopic()
  {
    return topic;
  }
  
  public String getUserAccount()
  {
    return userAccount;
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
  
  public boolean isSetMessage()
  {
    return message != null;
  }
  
  public boolean isSetNeedAck()
  {
    return __isset_bit_vector.get(0);
  }
  
  public boolean isSetPackageName()
  {
    return packageName != null;
  }
  
  public boolean isSetParams()
  {
    return params != null;
  }
  
  public boolean isSetTarget()
  {
    return target != null;
  }
  
  public boolean isSetTopic()
  {
    return topic != null;
  }
  
  public boolean isSetUserAccount()
  {
    return userAccount != null;
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
                packageName = paramTProtocol.readString();
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
                      message = new PushMessage();
                      message.read(paramTProtocol);
                    }
                    else
                    {
                      TProtocolUtil.skip(paramTProtocol, type);
                      continue;
                      if (type == 2)
                      {
                        needAck = paramTProtocol.readBool();
                        setNeedAckIsSet(true);
                      }
                      else
                      {
                        TProtocolUtil.skip(paramTProtocol, type);
                        continue;
                        if (type == 13)
                        {
                          localObject = paramTProtocol.readMapBegin();
                          params = new HashMap(size * 2);
                          int i = 0;
                          while (i < size)
                          {
                            String str1 = paramTProtocol.readString();
                            String str2 = paramTProtocol.readString();
                            params.put(str1, str2);
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
                            category = paramTProtocol.readString();
                          }
                          else
                          {
                            TProtocolUtil.skip(paramTProtocol, type);
                            continue;
                            if (type == 11) {
                              userAccount = paramTProtocol.readString();
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
    }
  }
  
  public void setNeedAckIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(0, paramBoolean);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("XmPushActionSendMessage(");
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
          break label516;
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
        break label528;
      }
      localStringBuilder.append("null");
      label126:
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("appId:");
      if (appId != null) {
        break label540;
      }
      localStringBuilder.append("null");
      label161:
      if (isSetPackageName())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("packageName:");
        if (packageName != null) {
          break label552;
        }
        localStringBuilder.append("null");
      }
      label203:
      if (isSetTopic())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("topic:");
        if (topic != null) {
          break label564;
        }
        localStringBuilder.append("null");
      }
      label245:
      if (isSetAliasName())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("aliasName:");
        if (aliasName != null) {
          break label576;
        }
        localStringBuilder.append("null");
      }
      label287:
      if (isSetMessage())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("message:");
        if (message != null) {
          break label588;
        }
        localStringBuilder.append("null");
      }
      label329:
      if (isSetNeedAck())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("needAck:");
        localStringBuilder.append(needAck);
      }
      if (isSetParams())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("params:");
        if (params != null) {
          break label600;
        }
        localStringBuilder.append("null");
      }
      label407:
      if (isSetCategory())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("category:");
        if (category != null) {
          break label612;
        }
        localStringBuilder.append("null");
      }
      label449:
      if (isSetUserAccount())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("userAccount:");
        if (userAccount != null) {
          break label624;
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
      label516:
      localStringBuilder.append(target);
      break label89;
      label528:
      localStringBuilder.append(id);
      break label126;
      label540:
      localStringBuilder.append(appId);
      break label161;
      label552:
      localStringBuilder.append(packageName);
      break label203;
      label564:
      localStringBuilder.append(topic);
      break label245;
      label576:
      localStringBuilder.append(aliasName);
      break label287;
      label588:
      localStringBuilder.append(message);
      break label329;
      label600:
      localStringBuilder.append(params);
      break label407;
      label612:
      localStringBuilder.append(category);
      break label449;
      label624:
      localStringBuilder.append(userAccount);
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
    if ((packageName != null) && (isSetPackageName()))
    {
      paramTProtocol.writeFieldBegin(PACKAGE_NAME_FIELD_DESC);
      paramTProtocol.writeString(packageName);
      paramTProtocol.writeFieldEnd();
    }
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
    if ((message != null) && (isSetMessage()))
    {
      paramTProtocol.writeFieldBegin(MESSAGE_FIELD_DESC);
      message.write(paramTProtocol);
      paramTProtocol.writeFieldEnd();
    }
    if (isSetNeedAck())
    {
      paramTProtocol.writeFieldBegin(NEED_ACK_FIELD_DESC);
      paramTProtocol.writeBool(needAck);
      paramTProtocol.writeFieldEnd();
    }
    if ((params != null) && (isSetParams()))
    {
      paramTProtocol.writeFieldBegin(PARAMS_FIELD_DESC);
      paramTProtocol.writeMapBegin(new TMap((byte)11, (byte)11, params.size()));
      Iterator localIterator = params.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        paramTProtocol.writeString((String)localEntry.getKey());
        paramTProtocol.writeString((String)localEntry.getValue());
      }
      paramTProtocol.writeMapEnd();
      paramTProtocol.writeFieldEnd();
    }
    if ((category != null) && (isSetCategory()))
    {
      paramTProtocol.writeFieldBegin(CATEGORY_FIELD_DESC);
      paramTProtocol.writeString(category);
      paramTProtocol.writeFieldEnd();
    }
    if ((userAccount != null) && (isSetUserAccount()))
    {
      paramTProtocol.writeFieldBegin(USER_ACCOUNT_FIELD_DESC);
      paramTProtocol.writeString(userAccount);
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
      PACKAGE_NAME = new _Fields("PACKAGE_NAME", 4, (short)5, "packageName");
      TOPIC = new _Fields("TOPIC", 5, (short)6, "topic");
      ALIAS_NAME = new _Fields("ALIAS_NAME", 6, (short)7, "aliasName");
      MESSAGE = new _Fields("MESSAGE", 7, (short)8, "message");
      NEED_ACK = new _Fields("NEED_ACK", 8, (short)9, "needAck");
      PARAMS = new _Fields("PARAMS", 9, (short)10, "params");
      CATEGORY = new _Fields("CATEGORY", 10, (short)11, "category");
      USER_ACCOUNT = new _Fields("USER_ACCOUNT", 11, (short)12, "userAccount");
      $VALUES = new _Fields[] { DEBUG, TARGET, ID, APP_ID, PACKAGE_NAME, TOPIC, ALIAS_NAME, MESSAGE, NEED_ACK, PARAMS, CATEGORY, USER_ACCOUNT };
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
 * Qualified Name:     com.xiaomi.xmpush.thrift.XmPushActionSendMessage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */