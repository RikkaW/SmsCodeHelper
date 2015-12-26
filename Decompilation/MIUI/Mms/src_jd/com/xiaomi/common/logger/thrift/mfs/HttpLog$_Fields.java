package com.xiaomi.common.logger.thrift.mfs;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum HttpLog$_Fields
{
  private static final Map<String, _Fields> byName;
  private final String _fieldName;
  private final short _thriftId;
  
  static
  {
    CATEGORY = new _Fields("CATEGORY", 1, (short)2, "category");
    HTTP_API = new _Fields("HTTP_API", 2, (short)3, "httpApi");
    PASSPORT = new _Fields("PASSPORT", 3, (short)4, "passport");
    $VALUES = new _Fields[] { COMMON, CATEGORY, HTTP_API, PASSPORT };
    byName = new HashMap();
    Iterator localIterator = EnumSet.allOf(_Fields.class).iterator();
    while (localIterator.hasNext())
    {
      _Fields local_Fields = (_Fields)localIterator.next();
      byName.put(local_Fields.getFieldName(), local_Fields);
    }
  }
  
  private HttpLog$_Fields(short paramShort, String paramString)
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
 * Qualified Name:     com.xiaomi.common.logger.thrift.mfs.HttpLog._Fields
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */