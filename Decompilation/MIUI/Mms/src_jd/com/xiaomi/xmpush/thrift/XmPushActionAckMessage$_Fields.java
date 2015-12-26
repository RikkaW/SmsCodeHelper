package com.xiaomi.xmpush.thrift;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum XmPushActionAckMessage$_Fields
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
  
  private XmPushActionAckMessage$_Fields(short paramShort, String paramString)
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
 * Qualified Name:     com.xiaomi.xmpush.thrift.XmPushActionAckMessage._Fields
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */