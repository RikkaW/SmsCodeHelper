package com.xiaomi.xmpush.thrift;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum XmPushActionCommandResult$_Fields
{
  private static final Map<String, _Fields> byName;
  private final String _fieldName;
  private final short _thriftId;
  
  static
  {
    ID = new _Fields("ID", 2, (short)3, "id");
    APP_ID = new _Fields("APP_ID", 3, (short)4, "appId");
    CMD_NAME = new _Fields("CMD_NAME", 4, (short)5, "cmdName");
    REQUEST = new _Fields("REQUEST", 5, (short)6, "request");
    ERROR_CODE = new _Fields("ERROR_CODE", 6, (short)7, "errorCode");
    REASON = new _Fields("REASON", 7, (short)8, "reason");
    PACKAGE_NAME = new _Fields("PACKAGE_NAME", 8, (short)9, "packageName");
    CMD_ARGS = new _Fields("CMD_ARGS", 9, (short)10, "cmdArgs");
    CATEGORY = new _Fields("CATEGORY", 10, (short)12, "category");
    $VALUES = new _Fields[] { DEBUG, TARGET, ID, APP_ID, CMD_NAME, REQUEST, ERROR_CODE, REASON, PACKAGE_NAME, CMD_ARGS, CATEGORY };
    byName = new HashMap();
    Iterator localIterator = EnumSet.allOf(_Fields.class).iterator();
    while (localIterator.hasNext())
    {
      _Fields local_Fields = (_Fields)localIterator.next();
      byName.put(local_Fields.getFieldName(), local_Fields);
    }
  }
  
  private XmPushActionCommandResult$_Fields(short paramShort, String paramString)
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
 * Qualified Name:     com.xiaomi.xmpush.thrift.XmPushActionCommandResult._Fields
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */