package com.xiaomi.xmpush.thrift;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum XmPushActionContainer$_Fields
{
  private static final Map<String, _Fields> byName;
  private final String _fieldName;
  private final short _thriftId;
  
  static
  {
    APPID = new _Fields("APPID", 4, (short)5, "appid");
    PACKAGE_NAME = new _Fields("PACKAGE_NAME", 5, (short)6, "packageName");
    TARGET = new _Fields("TARGET", 6, (short)7, "target");
    META_INFO = new _Fields("META_INFO", 7, (short)8, "metaInfo");
    $VALUES = new _Fields[] { ACTION, ENCRYPT_ACTION, IS_REQUEST, PUSH_ACTION, APPID, PACKAGE_NAME, TARGET, META_INFO };
    byName = new HashMap();
    Iterator localIterator = EnumSet.allOf(_Fields.class).iterator();
    while (localIterator.hasNext())
    {
      _Fields local_Fields = (_Fields)localIterator.next();
      byName.put(local_Fields.getFieldName(), local_Fields);
    }
  }
  
  private XmPushActionContainer$_Fields(short paramShort, String paramString)
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
 * Qualified Name:     com.xiaomi.xmpush.thrift.XmPushActionContainer._Fields
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */