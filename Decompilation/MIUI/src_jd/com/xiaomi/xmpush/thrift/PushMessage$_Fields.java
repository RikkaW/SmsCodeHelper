package com.xiaomi.xmpush.thrift;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum PushMessage$_Fields
{
  private static final Map<String, _Fields> byName;
  private final String _fieldName;
  private final short _thriftId;
  
  static
  {
    ID = new _Fields("ID", 1, (short)2, "id");
    APP_ID = new _Fields("APP_ID", 2, (short)3, "appId");
    PAYLOAD = new _Fields("PAYLOAD", 3, (short)4, "payload");
    CREATE_AT = new _Fields("CREATE_AT", 4, (short)5, "createAt");
    TTL = new _Fields("TTL", 5, (short)6, "ttl");
    COLLAPSE_KEY = new _Fields("COLLAPSE_KEY", 6, (short)7, "collapseKey");
    PACKAGE_NAME = new _Fields("PACKAGE_NAME", 7, (short)8, "packageName");
    $VALUES = new _Fields[] { TO, ID, APP_ID, PAYLOAD, CREATE_AT, TTL, COLLAPSE_KEY, PACKAGE_NAME };
    byName = new HashMap();
    Iterator localIterator = EnumSet.allOf(_Fields.class).iterator();
    while (localIterator.hasNext())
    {
      _Fields local_Fields = (_Fields)localIterator.next();
      byName.put(local_Fields.getFieldName(), local_Fields);
    }
  }
  
  private PushMessage$_Fields(short paramShort, String paramString)
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
 * Qualified Name:     com.xiaomi.xmpush.thrift.PushMessage._Fields
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */