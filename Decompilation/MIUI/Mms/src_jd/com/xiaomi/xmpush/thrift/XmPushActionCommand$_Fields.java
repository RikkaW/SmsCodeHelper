package com.xiaomi.xmpush.thrift;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum XmPushActionCommand$_Fields
{
  private static final Map<String, _Fields> byName;
  private final String _fieldName;
  private final short _thriftId;
  
  static
  {
    ID = new _Fields("ID", 2, (short)3, "id");
    APP_ID = new _Fields("APP_ID", 3, (short)4, "appId");
    CMD_NAME = new _Fields("CMD_NAME", 4, (short)5, "cmdName");
    CMD_ARGS = new _Fields("CMD_ARGS", 5, (short)6, "cmdArgs");
    PACKAGE_NAME = new _Fields("PACKAGE_NAME", 6, (short)7, "packageName");
    CATEGORY = new _Fields("CATEGORY", 7, (short)9, "category");
    $VALUES = new _Fields[] { DEBUG, TARGET, ID, APP_ID, CMD_NAME, CMD_ARGS, PACKAGE_NAME, CATEGORY };
    byName = new HashMap();
    Iterator localIterator = EnumSet.allOf(_Fields.class).iterator();
    while (localIterator.hasNext())
    {
      _Fields local_Fields = (_Fields)localIterator.next();
      byName.put(local_Fields.getFieldName(), local_Fields);
    }
  }
  
  private XmPushActionCommand$_Fields(short paramShort, String paramString)
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
 * Qualified Name:     com.xiaomi.xmpush.thrift.XmPushActionCommand._Fields
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */