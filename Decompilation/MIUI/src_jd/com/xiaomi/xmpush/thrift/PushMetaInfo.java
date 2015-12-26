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
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class PushMetaInfo
  implements TBase<PushMetaInfo, _Fields>, Serializable, Cloneable
{
  private static final TField DESCRIPTION_FIELD_DESC;
  private static final TField EXTRA_FIELD_DESC;
  private static final TField ID_FIELD_DESC;
  private static final TField IGNORE_REG_INFO_FIELD_DESC;
  private static final TField INTERNAL_FIELD_DESC;
  private static final TField MESSAGE_TS_FIELD_DESC;
  private static final TField NOTIFY_ID_FIELD_DESC;
  private static final TField NOTIFY_TYPE_FIELD_DESC;
  private static final TField PASS_THROUGH_FIELD_DESC;
  private static final TStruct STRUCT_DESC = new TStruct("PushMetaInfo");
  private static final TField TITLE_FIELD_DESC;
  private static final TField TOPIC_FIELD_DESC;
  private static final TField URL_FIELD_DESC;
  public static final Map<_Fields, FieldMetaData> metaDataMap;
  private BitSet __isset_bit_vector = new BitSet(5);
  public String description;
  public Map<String, String> extra;
  public String id;
  public boolean ignoreRegInfo;
  public Map<String, String> internal;
  public long messageTs;
  public int notifyId;
  public int notifyType;
  public int passThrough;
  public String title;
  public String topic;
  public String url;
  
  static
  {
    ID_FIELD_DESC = new TField("id", (byte)11, (short)1);
    MESSAGE_TS_FIELD_DESC = new TField("messageTs", (byte)10, (short)2);
    TOPIC_FIELD_DESC = new TField("topic", (byte)11, (short)3);
    TITLE_FIELD_DESC = new TField("title", (byte)11, (short)4);
    DESCRIPTION_FIELD_DESC = new TField("description", (byte)11, (short)5);
    NOTIFY_TYPE_FIELD_DESC = new TField("notifyType", (byte)8, (short)6);
    URL_FIELD_DESC = new TField("url", (byte)11, (short)7);
    PASS_THROUGH_FIELD_DESC = new TField("passThrough", (byte)8, (short)8);
    NOTIFY_ID_FIELD_DESC = new TField("notifyId", (byte)8, (short)9);
    EXTRA_FIELD_DESC = new TField("extra", (byte)13, (short)10);
    INTERNAL_FIELD_DESC = new TField("internal", (byte)13, (short)11);
    IGNORE_REG_INFO_FIELD_DESC = new TField("ignoreRegInfo", (byte)2, (short)12);
    EnumMap localEnumMap = new EnumMap(_Fields.class);
    localEnumMap.put(_Fields.ID, new FieldMetaData("id", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.MESSAGE_TS, new FieldMetaData("messageTs", (byte)1, new FieldValueMetaData((byte)10)));
    localEnumMap.put(_Fields.TOPIC, new FieldMetaData("topic", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.TITLE, new FieldMetaData("title", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.DESCRIPTION, new FieldMetaData("description", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.NOTIFY_TYPE, new FieldMetaData("notifyType", (byte)2, new FieldValueMetaData((byte)8)));
    localEnumMap.put(_Fields.URL, new FieldMetaData("url", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.PASS_THROUGH, new FieldMetaData("passThrough", (byte)2, new FieldValueMetaData((byte)8)));
    localEnumMap.put(_Fields.NOTIFY_ID, new FieldMetaData("notifyId", (byte)2, new FieldValueMetaData((byte)8)));
    localEnumMap.put(_Fields.EXTRA, new FieldMetaData("extra", (byte)2, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
    localEnumMap.put(_Fields.INTERNAL, new FieldMetaData("internal", (byte)2, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
    localEnumMap.put(_Fields.IGNORE_REG_INFO, new FieldMetaData("ignoreRegInfo", (byte)2, new FieldValueMetaData((byte)2)));
    metaDataMap = Collections.unmodifiableMap(localEnumMap);
    FieldMetaData.addStructMetaDataMap(PushMetaInfo.class, metaDataMap);
  }
  
  public PushMetaInfo()
  {
    ignoreRegInfo = false;
  }
  
  public PushMetaInfo(PushMetaInfo paramPushMetaInfo)
  {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(__isset_bit_vector);
    if (paramPushMetaInfo.isSetId()) {
      id = id;
    }
    messageTs = messageTs;
    if (paramPushMetaInfo.isSetTopic()) {
      topic = topic;
    }
    if (paramPushMetaInfo.isSetTitle()) {
      title = title;
    }
    if (paramPushMetaInfo.isSetDescription()) {
      description = description;
    }
    notifyType = notifyType;
    if (paramPushMetaInfo.isSetUrl()) {
      url = url;
    }
    passThrough = passThrough;
    notifyId = notifyId;
    HashMap localHashMap;
    Iterator localIterator;
    Map.Entry localEntry;
    if (paramPushMetaInfo.isSetExtra())
    {
      localHashMap = new HashMap();
      localIterator = extra.entrySet().iterator();
      while (localIterator.hasNext())
      {
        localEntry = (Map.Entry)localIterator.next();
        localHashMap.put((String)localEntry.getKey(), (String)localEntry.getValue());
      }
      extra = localHashMap;
    }
    if (paramPushMetaInfo.isSetInternal())
    {
      localHashMap = new HashMap();
      localIterator = internal.entrySet().iterator();
      while (localIterator.hasNext())
      {
        localEntry = (Map.Entry)localIterator.next();
        localHashMap.put((String)localEntry.getKey(), (String)localEntry.getValue());
      }
      internal = localHashMap;
    }
    ignoreRegInfo = ignoreRegInfo;
  }
  
  public int compareTo(PushMetaInfo paramPushMetaInfo)
  {
    int i;
    if (!getClass().equals(paramPushMetaInfo.getClass())) {
      i = getClass().getName().compareTo(paramPushMetaInfo.getClass().getName());
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
                                                    j = Boolean.valueOf(isSetId()).compareTo(Boolean.valueOf(paramPushMetaInfo.isSetId()));
                                                    i = j;
                                                  } while (j != 0);
                                                  if (!isSetId()) {
                                                    break;
                                                  }
                                                  j = TBaseHelper.compareTo(id, id);
                                                  i = j;
                                                } while (j != 0);
                                                j = Boolean.valueOf(isSetMessageTs()).compareTo(Boolean.valueOf(paramPushMetaInfo.isSetMessageTs()));
                                                i = j;
                                              } while (j != 0);
                                              if (!isSetMessageTs()) {
                                                break;
                                              }
                                              j = TBaseHelper.compareTo(messageTs, messageTs);
                                              i = j;
                                            } while (j != 0);
                                            j = Boolean.valueOf(isSetTopic()).compareTo(Boolean.valueOf(paramPushMetaInfo.isSetTopic()));
                                            i = j;
                                          } while (j != 0);
                                          if (!isSetTopic()) {
                                            break;
                                          }
                                          j = TBaseHelper.compareTo(topic, topic);
                                          i = j;
                                        } while (j != 0);
                                        j = Boolean.valueOf(isSetTitle()).compareTo(Boolean.valueOf(paramPushMetaInfo.isSetTitle()));
                                        i = j;
                                      } while (j != 0);
                                      if (!isSetTitle()) {
                                        break;
                                      }
                                      j = TBaseHelper.compareTo(title, title);
                                      i = j;
                                    } while (j != 0);
                                    j = Boolean.valueOf(isSetDescription()).compareTo(Boolean.valueOf(paramPushMetaInfo.isSetDescription()));
                                    i = j;
                                  } while (j != 0);
                                  if (!isSetDescription()) {
                                    break;
                                  }
                                  j = TBaseHelper.compareTo(description, description);
                                  i = j;
                                } while (j != 0);
                                j = Boolean.valueOf(isSetNotifyType()).compareTo(Boolean.valueOf(paramPushMetaInfo.isSetNotifyType()));
                                i = j;
                              } while (j != 0);
                              if (!isSetNotifyType()) {
                                break;
                              }
                              j = TBaseHelper.compareTo(notifyType, notifyType);
                              i = j;
                            } while (j != 0);
                            j = Boolean.valueOf(isSetUrl()).compareTo(Boolean.valueOf(paramPushMetaInfo.isSetUrl()));
                            i = j;
                          } while (j != 0);
                          if (!isSetUrl()) {
                            break;
                          }
                          j = TBaseHelper.compareTo(url, url);
                          i = j;
                        } while (j != 0);
                        j = Boolean.valueOf(isSetPassThrough()).compareTo(Boolean.valueOf(paramPushMetaInfo.isSetPassThrough()));
                        i = j;
                      } while (j != 0);
                      if (!isSetPassThrough()) {
                        break;
                      }
                      j = TBaseHelper.compareTo(passThrough, passThrough);
                      i = j;
                    } while (j != 0);
                    j = Boolean.valueOf(isSetNotifyId()).compareTo(Boolean.valueOf(paramPushMetaInfo.isSetNotifyId()));
                    i = j;
                  } while (j != 0);
                  if (!isSetNotifyId()) {
                    break;
                  }
                  j = TBaseHelper.compareTo(notifyId, notifyId);
                  i = j;
                } while (j != 0);
                j = Boolean.valueOf(isSetExtra()).compareTo(Boolean.valueOf(paramPushMetaInfo.isSetExtra()));
                i = j;
              } while (j != 0);
              if (!isSetExtra()) {
                break;
              }
              j = TBaseHelper.compareTo(extra, extra);
              i = j;
            } while (j != 0);
            j = Boolean.valueOf(isSetInternal()).compareTo(Boolean.valueOf(paramPushMetaInfo.isSetInternal()));
            i = j;
          } while (j != 0);
          if (!isSetInternal()) {
            break;
          }
          j = TBaseHelper.compareTo(internal, internal);
          i = j;
        } while (j != 0);
        j = Boolean.valueOf(isSetIgnoreRegInfo()).compareTo(Boolean.valueOf(paramPushMetaInfo.isSetIgnoreRegInfo()));
        i = j;
      } while (j != 0);
      if (!isSetIgnoreRegInfo()) {
        break;
      }
      j = TBaseHelper.compareTo(ignoreRegInfo, ignoreRegInfo);
      i = j;
    } while (j != 0);
    return 0;
  }
  
  public PushMetaInfo deepCopy()
  {
    return new PushMetaInfo(this);
  }
  
  public boolean equals(PushMetaInfo paramPushMetaInfo)
  {
    if (paramPushMetaInfo == null) {
      return false;
    }
    boolean bool1 = isSetId();
    boolean bool2 = paramPushMetaInfo.isSetId();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!id.equals(id)) {
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
    bool2 = paramPushMetaInfo.isSetTopic();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!topic.equals(topic)) {
        return false;
      }
    }
    bool1 = isSetTitle();
    bool2 = paramPushMetaInfo.isSetTitle();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!title.equals(title)) {
        return false;
      }
    }
    bool1 = isSetDescription();
    bool2 = paramPushMetaInfo.isSetDescription();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!description.equals(description)) {
        return false;
      }
    }
    bool1 = isSetNotifyType();
    bool2 = paramPushMetaInfo.isSetNotifyType();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (notifyType != notifyType) {
        return false;
      }
    }
    bool1 = isSetUrl();
    bool2 = paramPushMetaInfo.isSetUrl();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!url.equals(url)) {
        return false;
      }
    }
    bool1 = isSetPassThrough();
    bool2 = paramPushMetaInfo.isSetPassThrough();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (passThrough != passThrough) {
        return false;
      }
    }
    bool1 = isSetNotifyId();
    bool2 = paramPushMetaInfo.isSetNotifyId();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (notifyId != notifyId) {
        return false;
      }
    }
    bool1 = isSetExtra();
    bool2 = paramPushMetaInfo.isSetExtra();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!extra.equals(extra)) {
        return false;
      }
    }
    bool1 = isSetInternal();
    bool2 = paramPushMetaInfo.isSetInternal();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!internal.equals(internal)) {
        return false;
      }
    }
    bool1 = isSetIgnoreRegInfo();
    bool2 = paramPushMetaInfo.isSetIgnoreRegInfo();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (ignoreRegInfo != ignoreRegInfo) {
        return false;
      }
    }
    return true;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    while (!(paramObject instanceof PushMetaInfo)) {
      return false;
    }
    return equals((PushMetaInfo)paramObject);
  }
  
  public String getDescription()
  {
    return description;
  }
  
  public Map<String, String> getExtra()
  {
    return extra;
  }
  
  public String getId()
  {
    return id;
  }
  
  public long getMessageTs()
  {
    return messageTs;
  }
  
  public int getNotifyId()
  {
    return notifyId;
  }
  
  public int getNotifyType()
  {
    return notifyType;
  }
  
  public int getPassThrough()
  {
    return passThrough;
  }
  
  public String getTitle()
  {
    return title;
  }
  
  public String getTopic()
  {
    return topic;
  }
  
  public int hashCode()
  {
    return 0;
  }
  
  public boolean isIgnoreRegInfo()
  {
    return ignoreRegInfo;
  }
  
  public boolean isSetDescription()
  {
    return description != null;
  }
  
  public boolean isSetExtra()
  {
    return extra != null;
  }
  
  public boolean isSetId()
  {
    return id != null;
  }
  
  public boolean isSetIgnoreRegInfo()
  {
    return __isset_bit_vector.get(4);
  }
  
  public boolean isSetInternal()
  {
    return internal != null;
  }
  
  public boolean isSetMessageTs()
  {
    return __isset_bit_vector.get(0);
  }
  
  public boolean isSetNotifyId()
  {
    return __isset_bit_vector.get(3);
  }
  
  public boolean isSetNotifyType()
  {
    return __isset_bit_vector.get(1);
  }
  
  public boolean isSetPassThrough()
  {
    return __isset_bit_vector.get(2);
  }
  
  public boolean isSetTitle()
  {
    return title != null;
  }
  
  public boolean isSetTopic()
  {
    return topic != null;
  }
  
  public boolean isSetUrl()
  {
    return url != null;
  }
  
  public void putToExtra(String paramString1, String paramString2)
  {
    if (extra == null) {
      extra = new HashMap();
    }
    extra.put(paramString1, paramString2);
  }
  
  public void read(TProtocol paramTProtocol)
    throws TException
  {
    paramTProtocol.readStructBegin();
    Object localObject = paramTProtocol.readFieldBegin();
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
          id = paramTProtocol.readString();
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
                title = paramTProtocol.readString();
              }
              else
              {
                TProtocolUtil.skip(paramTProtocol, type);
                continue;
                if (type == 11)
                {
                  description = paramTProtocol.readString();
                }
                else
                {
                  TProtocolUtil.skip(paramTProtocol, type);
                  continue;
                  if (type == 8)
                  {
                    notifyType = paramTProtocol.readI32();
                    setNotifyTypeIsSet(true);
                  }
                  else
                  {
                    TProtocolUtil.skip(paramTProtocol, type);
                    continue;
                    if (type == 11)
                    {
                      url = paramTProtocol.readString();
                    }
                    else
                    {
                      TProtocolUtil.skip(paramTProtocol, type);
                      continue;
                      if (type == 8)
                      {
                        passThrough = paramTProtocol.readI32();
                        setPassThroughIsSet(true);
                      }
                      else
                      {
                        TProtocolUtil.skip(paramTProtocol, type);
                        continue;
                        if (type == 8)
                        {
                          notifyId = paramTProtocol.readI32();
                          setNotifyIdIsSet(true);
                        }
                        else
                        {
                          TProtocolUtil.skip(paramTProtocol, type);
                          continue;
                          int i;
                          String str1;
                          String str2;
                          if (type == 13)
                          {
                            localObject = paramTProtocol.readMapBegin();
                            extra = new HashMap(size * 2);
                            i = 0;
                            while (i < size)
                            {
                              str1 = paramTProtocol.readString();
                              str2 = paramTProtocol.readString();
                              extra.put(str1, str2);
                              i += 1;
                            }
                            paramTProtocol.readMapEnd();
                          }
                          else
                          {
                            TProtocolUtil.skip(paramTProtocol, type);
                            continue;
                            if (type == 13)
                            {
                              localObject = paramTProtocol.readMapBegin();
                              internal = new HashMap(size * 2);
                              i = 0;
                              while (i < size)
                              {
                                str1 = paramTProtocol.readString();
                                str2 = paramTProtocol.readString();
                                internal.put(str1, str2);
                                i += 1;
                              }
                              paramTProtocol.readMapEnd();
                            }
                            else
                            {
                              TProtocolUtil.skip(paramTProtocol, type);
                              continue;
                              if (type == 2)
                              {
                                ignoreRegInfo = paramTProtocol.readBool();
                                setIgnoreRegInfoIsSet(true);
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
            }
          }
        }
      }
    }
    validate();
  }
  
  public void setIgnoreRegInfoIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(4, paramBoolean);
  }
  
  public void setMessageTsIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(0, paramBoolean);
  }
  
  public void setNotifyIdIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(3, paramBoolean);
  }
  
  public void setNotifyTypeIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(1, paramBoolean);
  }
  
  public void setPassThroughIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(2, paramBoolean);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("PushMetaInfo(");
    localStringBuilder.append("id:");
    if (id == null)
    {
      localStringBuilder.append("null");
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
          break label484;
        }
        localStringBuilder.append("null");
      }
      label105:
      if (isSetTitle())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("title:");
        if (title != null) {
          break label496;
        }
        localStringBuilder.append("null");
      }
      label147:
      if (isSetDescription())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("description:");
        if (description != null) {
          break label508;
        }
        localStringBuilder.append("null");
      }
      label189:
      if (isSetNotifyType())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("notifyType:");
        localStringBuilder.append(notifyType);
      }
      if (isSetUrl())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("url:");
        if (url != null) {
          break label520;
        }
        localStringBuilder.append("null");
      }
      label267:
      if (isSetPassThrough())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("passThrough:");
        localStringBuilder.append(passThrough);
      }
      if (isSetNotifyId())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("notifyId:");
        localStringBuilder.append(notifyId);
      }
      if (isSetExtra())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("extra:");
        if (extra != null) {
          break label532;
        }
        localStringBuilder.append("null");
      }
      label381:
      if (isSetInternal())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("internal:");
        if (internal != null) {
          break label544;
        }
        localStringBuilder.append("null");
      }
    }
    for (;;)
    {
      if (isSetIgnoreRegInfo())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("ignoreRegInfo:");
        localStringBuilder.append(ignoreRegInfo);
      }
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(id);
      break;
      label484:
      localStringBuilder.append(topic);
      break label105;
      label496:
      localStringBuilder.append(title);
      break label147;
      label508:
      localStringBuilder.append(description);
      break label189;
      label520:
      localStringBuilder.append(url);
      break label267;
      label532:
      localStringBuilder.append(extra);
      break label381;
      label544:
      localStringBuilder.append(internal);
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
    if (id != null)
    {
      paramTProtocol.writeFieldBegin(ID_FIELD_DESC);
      paramTProtocol.writeString(id);
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
    if ((title != null) && (isSetTitle()))
    {
      paramTProtocol.writeFieldBegin(TITLE_FIELD_DESC);
      paramTProtocol.writeString(title);
      paramTProtocol.writeFieldEnd();
    }
    if ((description != null) && (isSetDescription()))
    {
      paramTProtocol.writeFieldBegin(DESCRIPTION_FIELD_DESC);
      paramTProtocol.writeString(description);
      paramTProtocol.writeFieldEnd();
    }
    if (isSetNotifyType())
    {
      paramTProtocol.writeFieldBegin(NOTIFY_TYPE_FIELD_DESC);
      paramTProtocol.writeI32(notifyType);
      paramTProtocol.writeFieldEnd();
    }
    if ((url != null) && (isSetUrl()))
    {
      paramTProtocol.writeFieldBegin(URL_FIELD_DESC);
      paramTProtocol.writeString(url);
      paramTProtocol.writeFieldEnd();
    }
    if (isSetPassThrough())
    {
      paramTProtocol.writeFieldBegin(PASS_THROUGH_FIELD_DESC);
      paramTProtocol.writeI32(passThrough);
      paramTProtocol.writeFieldEnd();
    }
    if (isSetNotifyId())
    {
      paramTProtocol.writeFieldBegin(NOTIFY_ID_FIELD_DESC);
      paramTProtocol.writeI32(notifyId);
      paramTProtocol.writeFieldEnd();
    }
    Iterator localIterator;
    Map.Entry localEntry;
    if ((extra != null) && (isSetExtra()))
    {
      paramTProtocol.writeFieldBegin(EXTRA_FIELD_DESC);
      paramTProtocol.writeMapBegin(new TMap((byte)11, (byte)11, extra.size()));
      localIterator = extra.entrySet().iterator();
      while (localIterator.hasNext())
      {
        localEntry = (Map.Entry)localIterator.next();
        paramTProtocol.writeString((String)localEntry.getKey());
        paramTProtocol.writeString((String)localEntry.getValue());
      }
      paramTProtocol.writeMapEnd();
      paramTProtocol.writeFieldEnd();
    }
    if ((internal != null) && (isSetInternal()))
    {
      paramTProtocol.writeFieldBegin(INTERNAL_FIELD_DESC);
      paramTProtocol.writeMapBegin(new TMap((byte)11, (byte)11, internal.size()));
      localIterator = internal.entrySet().iterator();
      while (localIterator.hasNext())
      {
        localEntry = (Map.Entry)localIterator.next();
        paramTProtocol.writeString((String)localEntry.getKey());
        paramTProtocol.writeString((String)localEntry.getValue());
      }
      paramTProtocol.writeMapEnd();
      paramTProtocol.writeFieldEnd();
    }
    if (isSetIgnoreRegInfo())
    {
      paramTProtocol.writeFieldBegin(IGNORE_REG_INFO_FIELD_DESC);
      paramTProtocol.writeBool(ignoreRegInfo);
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
      TITLE = new _Fields("TITLE", 3, (short)4, "title");
      DESCRIPTION = new _Fields("DESCRIPTION", 4, (short)5, "description");
      NOTIFY_TYPE = new _Fields("NOTIFY_TYPE", 5, (short)6, "notifyType");
      URL = new _Fields("URL", 6, (short)7, "url");
      PASS_THROUGH = new _Fields("PASS_THROUGH", 7, (short)8, "passThrough");
      NOTIFY_ID = new _Fields("NOTIFY_ID", 8, (short)9, "notifyId");
      EXTRA = new _Fields("EXTRA", 9, (short)10, "extra");
      INTERNAL = new _Fields("INTERNAL", 10, (short)11, "internal");
      IGNORE_REG_INFO = new _Fields("IGNORE_REG_INFO", 11, (short)12, "ignoreRegInfo");
      $VALUES = new _Fields[] { ID, MESSAGE_TS, TOPIC, TITLE, DESCRIPTION, NOTIFY_TYPE, URL, PASS_THROUGH, NOTIFY_ID, EXTRA, INTERNAL, IGNORE_REG_INFO };
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
 * Qualified Name:     com.xiaomi.xmpush.thrift.PushMetaInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */