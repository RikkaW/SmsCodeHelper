package com.xiaomi.push.thrift;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum StatsEvent$_Fields
{
  private static final Map<String, _Fields> byName;
  private final String _fieldName;
  private final short _thriftId;
  
  static
  {
    CONNPT = new _Fields("CONNPT", 3, (short)4, "connpt");
    HOST = new _Fields("HOST", 4, (short)5, "host");
    SUBVALUE = new _Fields("SUBVALUE", 5, (short)6, "subvalue");
    ANNOTATION = new _Fields("ANNOTATION", 6, (short)7, "annotation");
    USER = new _Fields("USER", 7, (short)8, "user");
    TIME = new _Fields("TIME", 8, (short)9, "time");
    CLIENT_IP = new _Fields("CLIENT_IP", 9, (short)10, "clientIp");
    $VALUES = new _Fields[] { CHID, TYPE, VALUE, CONNPT, HOST, SUBVALUE, ANNOTATION, USER, TIME, CLIENT_IP };
    byName = new HashMap();
    Iterator localIterator = EnumSet.allOf(_Fields.class).iterator();
    while (localIterator.hasNext())
    {
      _Fields local_Fields = (_Fields)localIterator.next();
      byName.put(local_Fields.getFieldName(), local_Fields);
    }
  }
  
  private StatsEvent$_Fields(short paramShort, String paramString)
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
 * Qualified Name:     com.xiaomi.push.thrift.StatsEvent._Fields
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */