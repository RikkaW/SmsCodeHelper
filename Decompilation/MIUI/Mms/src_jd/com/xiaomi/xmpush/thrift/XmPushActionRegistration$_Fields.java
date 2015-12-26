package com.xiaomi.xmpush.thrift;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum XmPushActionRegistration$_Fields
{
  private static final Map<String, _Fields> byName;
  private final String _fieldName;
  private final short _thriftId;
  
  static
  {
    ID = new _Fields("ID", 2, (short)3, "id");
    APP_ID = new _Fields("APP_ID", 3, (short)4, "appId");
    APP_VERSION = new _Fields("APP_VERSION", 4, (short)5, "appVersion");
    PACKAGE_NAME = new _Fields("PACKAGE_NAME", 5, (short)6, "packageName");
    TOKEN = new _Fields("TOKEN", 6, (short)7, "token");
    DEVICE_ID = new _Fields("DEVICE_ID", 7, (short)8, "deviceId");
    ALIAS_NAME = new _Fields("ALIAS_NAME", 8, (short)9, "aliasName");
    SDK_VERSION = new _Fields("SDK_VERSION", 9, (short)10, "sdkVersion");
    $VALUES = new _Fields[] { DEBUG, TARGET, ID, APP_ID, APP_VERSION, PACKAGE_NAME, TOKEN, DEVICE_ID, ALIAS_NAME, SDK_VERSION };
    byName = new HashMap();
    Iterator localIterator = EnumSet.allOf(_Fields.class).iterator();
    while (localIterator.hasNext())
    {
      _Fields local_Fields = (_Fields)localIterator.next();
      byName.put(local_Fields.getFieldName(), local_Fields);
    }
  }
  
  private XmPushActionRegistration$_Fields(short paramShort, String paramString)
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
 * Qualified Name:     com.xiaomi.xmpush.thrift.XmPushActionRegistration._Fields
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */