package com.xiaomi.xmpush.thrift;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum PushMetaInfo$_Fields
{
  private static final Map<String, _Fields> byName;
  private final String _fieldName;
  private final short _thriftId;
  
  static
  {
    TITLE = new _Fields("TITLE", 3, (short)4, "title");
    DESCRIPTION = new _Fields("DESCRIPTION", 4, (short)5, "description");
    NOTIFY_TYPE = new _Fields("NOTIFY_TYPE", 5, (short)6, "notifyType");
    URL = new _Fields("URL", 6, (short)7, "url");
    PASS_THROUGH = new _Fields("PASS_THROUGH", 7, (short)8, "passThrough");
    NOTIFY_ID = new _Fields("NOTIFY_ID", 8, (short)9, "notifyId");
    EXTRA = new _Fields("EXTRA", 9, (short)10, "extra");
    INTERNAL = new _Fields("INTERNAL", 10, (short)11, "internal");
    IGNORE_REG_INFO = new _Fields("IGNORE_REG_INFO", 11, (short)12, "ignoreRegInfo");
    $VALUES = new _Fields[] { ID, MESSAGE_TS, TOPIC, TITLE, DESCRIPTION, NOTIFY_TYPE, URL, PASS_THROUGH, NOTIFY_ID, EXTRA, INTERNAL, IGNORE_REG_INFO };
    byName = new HashMap();
    Iterator localIterator = EnumSet.allOf(_Fields.class).iterator();
    while (localIterator.hasNext())
    {
      _Fields local_Fields = (_Fields)localIterator.next();
      byName.put(local_Fields.getFieldName(), local_Fields);
    }
  }
  
  private PushMetaInfo$_Fields(short paramShort, String paramString)
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
 * Qualified Name:     com.xiaomi.xmpush.thrift.PushMetaInfo._Fields
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */