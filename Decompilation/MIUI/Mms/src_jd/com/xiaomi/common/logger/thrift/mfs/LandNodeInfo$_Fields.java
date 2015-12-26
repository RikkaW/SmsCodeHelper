package com.xiaomi.common.logger.thrift.mfs;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum LandNodeInfo$_Fields
{
  private static final Map<String, _Fields> byName;
  private final String _fieldName;
  private final short _thriftId;
  
  static
  {
    FAILED_COUNT = new _Fields("FAILED_COUNT", 1, (short)2, "failed_count");
    SUCCESS_COUNT = new _Fields("SUCCESS_COUNT", 2, (short)3, "success_count");
    DURATION = new _Fields("DURATION", 3, (short)4, "duration");
    SIZE = new _Fields("SIZE", 4, (short)5, "size");
    EXP_INFO = new _Fields("EXP_INFO", 5, (short)6, "exp_info");
    HTTP_INFO = new _Fields("HTTP_INFO", 6, (short)7, "http_info");
    $VALUES = new _Fields[] { IP, FAILED_COUNT, SUCCESS_COUNT, DURATION, SIZE, EXP_INFO, HTTP_INFO };
    byName = new HashMap();
    Iterator localIterator = EnumSet.allOf(_Fields.class).iterator();
    while (localIterator.hasNext())
    {
      _Fields local_Fields = (_Fields)localIterator.next();
      byName.put(local_Fields.getFieldName(), local_Fields);
    }
  }
  
  private LandNodeInfo$_Fields(short paramShort, String paramString)
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
 * Qualified Name:     com.xiaomi.common.logger.thrift.mfs.LandNodeInfo._Fields
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */