package com.xiaomi.xmpush.thrift;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum XmPushActionSendMessage$_Fields
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
  
  private XmPushActionSendMessage$_Fields(short paramShort, String paramString)
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
 * Qualified Name:     com.xiaomi.xmpush.thrift.XmPushActionSendMessage._Fields
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */