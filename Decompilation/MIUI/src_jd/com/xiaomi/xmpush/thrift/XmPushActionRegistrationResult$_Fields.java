package com.xiaomi.xmpush.thrift;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum XmPushActionRegistrationResult$_Fields
{
  private static final Map<String, _Fields> byName;
  private final String _fieldName;
  private final short _thriftId;
  
  static
  {
    ID = new _Fields("ID", 2, (short)3, "id");
    APP_ID = new _Fields("APP_ID", 3, (short)4, "appId");
    REQUEST = new _Fields("REQUEST", 4, (short)5, "request");
    ERROR_CODE = new _Fields("ERROR_CODE", 5, (short)6, "errorCode");
    REASON = new _Fields("REASON", 6, (short)7, "reason");
    REG_ID = new _Fields("REG_ID", 7, (short)8, "regId");
    REG_SECRET = new _Fields("REG_SECRET", 8, (short)9, "regSecret");
    PACKAGE_NAME = new _Fields("PACKAGE_NAME", 9, (short)10, "packageName");
    $VALUES = new _Fields[] { DEBUG, TARGET, ID, APP_ID, REQUEST, ERROR_CODE, REASON, REG_ID, REG_SECRET, PACKAGE_NAME };
    byName = new HashMap();
    Iterator localIterator = EnumSet.allOf(_Fields.class).iterator();
    while (localIterator.hasNext())
    {
      _Fields local_Fields = (_Fields)localIterator.next();
      byName.put(local_Fields.getFieldName(), local_Fields);
    }
  }
  
  private XmPushActionRegistrationResult$_Fields(short paramShort, String paramString)
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
 * Qualified Name:     com.xiaomi.xmpush.thrift.XmPushActionRegistrationResult._Fields
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */