package com.xiaomi.xmpush.thrift;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum Target$_Fields
{
  private static final Map<String, _Fields> byName;
  private final String _fieldName;
  private final short _thriftId;
  
  static
  {
    SERVER = new _Fields("SERVER", 2, (short)3, "server");
    RESOURCE = new _Fields("RESOURCE", 3, (short)4, "resource");
    IS_PREVIEW = new _Fields("IS_PREVIEW", 4, (short)5, "isPreview");
    $VALUES = new _Fields[] { CHANNEL_ID, USER_ID, SERVER, RESOURCE, IS_PREVIEW };
    byName = new HashMap();
    Iterator localIterator = EnumSet.allOf(_Fields.class).iterator();
    while (localIterator.hasNext())
    {
      _Fields local_Fields = (_Fields)localIterator.next();
      byName.put(local_Fields.getFieldName(), local_Fields);
    }
  }
  
  private Target$_Fields(short paramShort, String paramString)
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
 * Qualified Name:     com.xiaomi.xmpush.thrift.Target._Fields
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */