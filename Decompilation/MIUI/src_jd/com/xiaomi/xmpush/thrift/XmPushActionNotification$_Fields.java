package com.xiaomi.xmpush.thrift;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum XmPushActionNotification$_Fields
{
  private static final Map<String, _Fields> byName;
  private final String _fieldName;
  private final short _thriftId;
  
  static
  {
    ID = new _Fields("ID", 2, (short)3, "id");
    APP_ID = new _Fields("APP_ID", 3, (short)4, "appId");
    TYPE = new _Fields("TYPE", 4, (short)5, "type");
    REQUIRE_ACK = new _Fields("REQUIRE_ACK", 5, (short)6, "requireAck");
    PAYLOAD = new _Fields("PAYLOAD", 6, (short)7, "payload");
    EXTRA = new _Fields("EXTRA", 7, (short)8, "extra");
    PACKAGE_NAME = new _Fields("PACKAGE_NAME", 8, (short)9, "packageName");
    CATEGORY = new _Fields("CATEGORY", 9, (short)10, "category");
    $VALUES = new _Fields[] { DEBUG, TARGET, ID, APP_ID, TYPE, REQUIRE_ACK, PAYLOAD, EXTRA, PACKAGE_NAME, CATEGORY };
    byName = new HashMap();
    Iterator localIterator = EnumSet.allOf(_Fields.class).iterator();
    while (localIterator.hasNext())
    {
      _Fields local_Fields = (_Fields)localIterator.next();
      byName.put(local_Fields.getFieldName(), local_Fields);
    }
  }
  
  private XmPushActionNotification$_Fields(short paramShort, String paramString)
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
 * Qualified Name:     com.xiaomi.xmpush.thrift.XmPushActionNotification._Fields
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */