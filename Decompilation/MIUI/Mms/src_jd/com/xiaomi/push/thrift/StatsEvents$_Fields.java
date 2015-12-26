package com.xiaomi.push.thrift;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum StatsEvents$_Fields
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
  
  private StatsEvents$_Fields(short paramShort, String paramString)
  {
    _thriftId = paramShort;
    _fieldName = paramString;
  }
  
  public String getFieldName()
  {
    return _fieldName;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.thrift.StatsEvents._Fields
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */