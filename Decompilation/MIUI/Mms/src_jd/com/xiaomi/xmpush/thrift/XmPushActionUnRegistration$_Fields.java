package com.xiaomi.xmpush.thrift;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum XmPushActionUnRegistration$_Fields
{
  private static final Map<String, _Fields> byName;
  private final String _fieldName;
  private final short _thriftId;
  
  static
  {
    ID = new _Fields("ID", 2, (short)3, "id");
    APP_ID = new _Fields("APP_ID", 3, (short)4, "appId");
    REG_ID = new _Fields("REG_ID", 4, (short)5, "regId");
    APP_VERSION = new _Fields("APP_VERSION", 5, (short)6, "appVersion");
    PACKAGE_NAME = new _Fields("PACKAGE_NAME", 6, (short)7, "packageName");
    TOKEN = new _Fields("TOKEN", 7, (short)8, "token");
    DEVICE_ID = new _Fields("DEVICE_ID", 8, (short)9, "deviceId");
    ALIAS_NAME = new _Fields("ALIAS_NAME", 9, (short)10, "aliasName");
    $VALUES = new _Fields[] { DEBUG, TARGET, ID, APP_ID, REG_ID, APP_VERSION, PACKAGE_NAME, TOKEN, DEVICE_ID, ALIAS_NAME };
    byName = new HashMap();
    Iterator localIterator = EnumSet.allOf(_Fields.class).iterator();
    while (localIterator.hasNext())
    {
      _Fields local_Fields = (_Fields)localIterator.next();
      byName.put(local_Fields.getFieldName(), local_Fields);
    }
  }
  
  private XmPushActionUnRegistration$_Fields(short paramShort, String paramString)
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
 * Qualified Name:     com.xiaomi.xmpush.thrift.XmPushActionUnRegistration._Fields
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */