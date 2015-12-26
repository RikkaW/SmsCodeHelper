package com.xiaomi.xmpush.thrift;

import java.io.Serializable;
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

public class XmPushActionSendFeedback
  implements TBase<XmPushActionSendFeedback, _Fields>, Serializable, Cloneable
{
  private static final TField APP_ID_FIELD_DESC;
  private static final TField CATEGORY_FIELD_DESC;
  private static final TField DEBUG_FIELD_DESC;
  private static final TField FEEDBACKS_FIELD_DESC;
  private static final TField ID_FIELD_DESC;
  private static final TStruct STRUCT_DESC = new TStruct("XmPushActionSendFeedback");
  private static final TField TARGET_FIELD_DESC;
  public static final Map<_Fields, FieldMetaData> metaDataMap;
  public String appId;
  public String category;
  public String debug;
  public Map<String, String> feedbacks;
  public String id;
  public Target target;
  
  static
  {
    DEBUG_FIELD_DESC = new TField("debug", (byte)11, (short)1);
    TARGET_FIELD_DESC = new TField("target", (byte)12, (short)2);
    ID_FIELD_DESC = new TField("id", (byte)11, (short)3);
    APP_ID_FIELD_DESC = new TField("appId", (byte)11, (short)4);
    FEEDBACKS_FIELD_DESC = new TField("feedbacks", (byte)13, (short)5);
    CATEGORY_FIELD_DESC = new TField("category", (byte)11, (short)6);
    EnumMap localEnumMap = new EnumMap(_Fields.class);
    localEnumMap.put(_Fields.DEBUG, new FieldMetaData("debug", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.TARGET, new FieldMetaData("target", (byte)2, new StructMetaData((byte)12, Target.class)));
    localEnumMap.put(_Fields.ID, new FieldMetaData("id", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.APP_ID, new FieldMetaData("appId", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.FEEDBACKS, new FieldMetaData("feedbacks", (byte)2, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
    localEnumMap.put(_Fields.CATEGORY, new FieldMetaData("category", (byte)2, new FieldValueMetaData((byte)11)));
    metaDataMap = Collections.unmodifiableMap(localEnumMap);
    FieldMetaData.addStructMetaDataMap(XmPushActionSendFeedback.class, metaDataMap);
  }
  
  public int compareTo(XmPushActionSendFeedback paramXmPushActionSendFeedback)
  {
    int i;
    if (!getClass().equals(paramXmPushActionSendFeedback.getClass())) {
      i = getClass().getName().compareTo(paramXmPushActionSendFeedback.getClass().getName());
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
                            return i;
                            j = Boolean.valueOf(isSetDebug()).compareTo(Boolean.valueOf(paramXmPushActionSendFeedback.isSetDebug()));
                            i = j;
                          } while (j != 0);
                          if (!isSetDebug()) {
                            break;
                          }
                          j = TBaseHelper.compareTo(debug, debug);
                          i = j;
                        } while (j != 0);
                        j = Boolean.valueOf(isSetTarget()).compareTo(Boolean.valueOf(paramXmPushActionSendFeedback.isSetTarget()));
                        i = j;
                      } while (j != 0);
                      if (!isSetTarget()) {
                        break;
                      }
                      j = TBaseHelper.compareTo(target, target);
                      i = j;
                    } while (j != 0);
                    j = Boolean.valueOf(isSetId()).compareTo(Boolean.valueOf(paramXmPushActionSendFeedback.isSetId()));
                    i = j;
                  } while (j != 0);
                  if (!isSetId()) {
                    break;
                  }
                  j = TBaseHelper.compareTo(id, id);
                  i = j;
                } while (j != 0);
                j = Boolean.valueOf(isSetAppId()).compareTo(Boolean.valueOf(paramXmPushActionSendFeedback.isSetAppId()));
                i = j;
              } while (j != 0);
              if (!isSetAppId()) {
                break;
              }
              j = TBaseHelper.compareTo(appId, appId);
              i = j;
            } while (j != 0);
            j = Boolean.valueOf(isSetFeedbacks()).compareTo(Boolean.valueOf(paramXmPushActionSendFeedback.isSetFeedbacks()));
            i = j;
          } while (j != 0);
          if (!isSetFeedbacks()) {
            break;
          }
          j = TBaseHelper.compareTo(feedbacks, feedbacks);
          i = j;
        } while (j != 0);
        j = Boolean.valueOf(isSetCategory()).compareTo(Boolean.valueOf(paramXmPushActionSendFeedback.isSetCategory()));
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
  
  public boolean equals(XmPushActionSendFeedback paramXmPushActionSendFeedback)
  {
    if (paramXmPushActionSendFeedback == null) {
      return false;
    }
    boolean bool1 = isSetDebug();
    boolean bool2 = paramXmPushActionSendFeedback.isSetDebug();
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
    bool2 = paramXmPushActionSendFeedback.isSetTarget();
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
    bool2 = paramXmPushActionSendFeedback.isSetId();
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
    bool2 = paramXmPushActionSendFeedback.isSetAppId();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!appId.equals(appId)) {
        return false;
      }
    }
    bool1 = isSetFeedbacks();
    bool2 = paramXmPushActionSendFeedback.isSetFeedbacks();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!feedbacks.equals(feedbacks)) {
        return false;
      }
    }
    bool1 = isSetCategory();
    bool2 = paramXmPushActionSendFeedback.isSetCategory();
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
    while (!(paramObject instanceof XmPushActionSendFeedback)) {
      return false;
    }
    return equals((XmPushActionSendFeedback)paramObject);
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
  
  public boolean isSetFeedbacks()
  {
    return feedbacks != null;
  }
  
  public boolean isSetId()
  {
    return id != null;
  }
  
  public boolean isSetTarget()
  {
    return target != null;
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
              if (type == 13)
              {
                localObject = paramTProtocol.readMapBegin();
                feedbacks = new HashMap(size * 2);
                int i = 0;
                while (i < size)
                {
                  String str1 = paramTProtocol.readString();
                  String str2 = paramTProtocol.readString();
                  feedbacks.put(str1, str2);
                  i += 1;
                }
                paramTProtocol.readMapEnd();
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
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("XmPushActionSendFeedback(");
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
          break label270;
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
        break label282;
      }
      localStringBuilder.append("null");
      label126:
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("appId:");
      if (appId != null) {
        break label294;
      }
      localStringBuilder.append("null");
      label161:
      if (isSetFeedbacks())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("feedbacks:");
        if (feedbacks != null) {
          break label306;
        }
        localStringBuilder.append("null");
      }
      label203:
      if (isSetCategory())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("category:");
        if (category != null) {
          break label318;
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
      label270:
      localStringBuilder.append(target);
      break label89;
      label282:
      localStringBuilder.append(id);
      break label126;
      label294:
      localStringBuilder.append(appId);
      break label161;
      label306:
      localStringBuilder.append(feedbacks);
      break label203;
      label318:
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
    if ((feedbacks != null) && (isSetFeedbacks()))
    {
      paramTProtocol.writeFieldBegin(FEEDBACKS_FIELD_DESC);
      paramTProtocol.writeMapBegin(new TMap((byte)11, (byte)11, feedbacks.size()));
      Iterator localIterator = feedbacks.entrySet().iterator();
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
      FEEDBACKS = new _Fields("FEEDBACKS", 4, (short)5, "feedbacks");
      CATEGORY = new _Fields("CATEGORY", 5, (short)6, "category");
      $VALUES = new _Fields[] { DEBUG, TARGET, ID, APP_ID, FEEDBACKS, CATEGORY };
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
 * Qualified Name:     com.xiaomi.xmpush.thrift.XmPushActionSendFeedback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */