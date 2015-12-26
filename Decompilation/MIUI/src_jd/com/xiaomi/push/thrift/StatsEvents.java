package com.xiaomi.push.thrift;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class StatsEvents
  implements TBase<StatsEvents, _Fields>, Serializable, Cloneable
{
  private static final TField EVENTS_FIELD_DESC;
  private static final TField OPERATOR_FIELD_DESC;
  private static final TStruct STRUCT_DESC = new TStruct("StatsEvents");
  private static final TField UUID_FIELD_DESC = new TField("uuid", (byte)11, (short)1);
  public static final Map<_Fields, FieldMetaData> metaDataMap;
  public List<StatsEvent> events;
  public String operator;
  public String uuid;
  
  static
  {
    OPERATOR_FIELD_DESC = new TField("operator", (byte)11, (short)2);
    EVENTS_FIELD_DESC = new TField("events", (byte)15, (short)3);
    EnumMap localEnumMap = new EnumMap(_Fields.class);
    localEnumMap.put(_Fields.UUID, new FieldMetaData("uuid", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.OPERATOR, new FieldMetaData("operator", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.EVENTS, new FieldMetaData("events", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, StatsEvent.class))));
    metaDataMap = Collections.unmodifiableMap(localEnumMap);
    FieldMetaData.addStructMetaDataMap(StatsEvents.class, metaDataMap);
  }
  
  public StatsEvents() {}
  
  public StatsEvents(String paramString, List<StatsEvent> paramList)
  {
    this();
    uuid = paramString;
    events = paramList;
  }
  
  public int compareTo(StatsEvents paramStatsEvents)
  {
    int i;
    if (!getClass().equals(paramStatsEvents.getClass())) {
      i = getClass().getName().compareTo(paramStatsEvents.getClass().getName());
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
                return i;
                j = Boolean.valueOf(isSetUuid()).compareTo(Boolean.valueOf(paramStatsEvents.isSetUuid()));
                i = j;
              } while (j != 0);
              if (!isSetUuid()) {
                break;
              }
              j = TBaseHelper.compareTo(uuid, uuid);
              i = j;
            } while (j != 0);
            j = Boolean.valueOf(isSetOperator()).compareTo(Boolean.valueOf(paramStatsEvents.isSetOperator()));
            i = j;
          } while (j != 0);
          if (!isSetOperator()) {
            break;
          }
          j = TBaseHelper.compareTo(operator, operator);
          i = j;
        } while (j != 0);
        j = Boolean.valueOf(isSetEvents()).compareTo(Boolean.valueOf(paramStatsEvents.isSetEvents()));
        i = j;
      } while (j != 0);
      if (!isSetEvents()) {
        break;
      }
      j = TBaseHelper.compareTo(events, events);
      i = j;
    } while (j != 0);
    return 0;
  }
  
  public boolean equals(StatsEvents paramStatsEvents)
  {
    if (paramStatsEvents == null) {}
    boolean bool1;
    boolean bool2;
    do
    {
      do
      {
        do
        {
          return false;
          bool1 = isSetUuid();
          bool2 = paramStatsEvents.isSetUuid();
        } while (((bool1) || (bool2)) && ((!bool1) || (!bool2) || (!uuid.equals(uuid))));
        bool1 = isSetOperator();
        bool2 = paramStatsEvents.isSetOperator();
      } while (((bool1) || (bool2)) && ((!bool1) || (!bool2) || (!operator.equals(operator))));
      bool1 = isSetEvents();
      bool2 = paramStatsEvents.isSetEvents();
    } while (((bool1) || (bool2)) && ((!bool1) || (!bool2) || (!events.equals(events))));
    return true;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    while (!(paramObject instanceof StatsEvents)) {
      return false;
    }
    return equals((StatsEvents)paramObject);
  }
  
  public int hashCode()
  {
    return 0;
  }
  
  public boolean isSetEvents()
  {
    return events != null;
  }
  
  public boolean isSetOperator()
  {
    return operator != null;
  }
  
  public boolean isSetUuid()
  {
    return uuid != null;
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
        uuid = paramTProtocol.readString();
      }
      else
      {
        TProtocolUtil.skip(paramTProtocol, type);
        continue;
        if (type == 11)
        {
          operator = paramTProtocol.readString();
        }
        else
        {
          TProtocolUtil.skip(paramTProtocol, type);
          continue;
          if (type == 15)
          {
            localObject = paramTProtocol.readListBegin();
            events = new ArrayList(size);
            int i = 0;
            while (i < size)
            {
              StatsEvent localStatsEvent = new StatsEvent();
              localStatsEvent.read(paramTProtocol);
              events.add(localStatsEvent);
              i += 1;
            }
            paramTProtocol.readListEnd();
          }
          else
          {
            TProtocolUtil.skip(paramTProtocol, type);
          }
        }
      }
    }
  }
  
  public StatsEvents setOperator(String paramString)
  {
    operator = paramString;
    return this;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("StatsEvents(");
    localStringBuilder.append("uuid:");
    if (uuid == null)
    {
      localStringBuilder.append("null");
      if (isSetOperator())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("operator:");
        if (operator != null) {
          break label135;
        }
        localStringBuilder.append("null");
      }
      label75:
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("events:");
      if (events != null) {
        break label147;
      }
      localStringBuilder.append("null");
    }
    for (;;)
    {
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(uuid);
      break;
      label135:
      localStringBuilder.append(operator);
      break label75;
      label147:
      localStringBuilder.append(events);
    }
  }
  
  public void validate()
    throws TException
  {
    if (uuid == null) {
      throw new TProtocolException("Required field 'uuid' was not present! Struct: " + toString());
    }
    if (events == null) {
      throw new TProtocolException("Required field 'events' was not present! Struct: " + toString());
    }
  }
  
  public void write(TProtocol paramTProtocol)
    throws TException
  {
    validate();
    paramTProtocol.writeStructBegin(STRUCT_DESC);
    if (uuid != null)
    {
      paramTProtocol.writeFieldBegin(UUID_FIELD_DESC);
      paramTProtocol.writeString(uuid);
      paramTProtocol.writeFieldEnd();
    }
    if ((operator != null) && (isSetOperator()))
    {
      paramTProtocol.writeFieldBegin(OPERATOR_FIELD_DESC);
      paramTProtocol.writeString(operator);
      paramTProtocol.writeFieldEnd();
    }
    if (events != null)
    {
      paramTProtocol.writeFieldBegin(EVENTS_FIELD_DESC);
      paramTProtocol.writeListBegin(new TList((byte)12, events.size()));
      Iterator localIterator = events.iterator();
      while (localIterator.hasNext()) {
        ((StatsEvent)localIterator.next()).write(paramTProtocol);
      }
      paramTProtocol.writeListEnd();
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
      OPERATOR = new _Fields("OPERATOR", 1, (short)2, "operator");
      EVENTS = new _Fields("EVENTS", 2, (short)3, "events");
      $VALUES = new _Fields[] { UUID, OPERATOR, EVENTS };
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
 * Qualified Name:     com.xiaomi.push.thrift.StatsEvents
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */