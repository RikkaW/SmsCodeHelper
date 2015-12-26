package com.xiaomi.common.logger.thrift;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum Common$_Fields
{
  private static final Map<String, _Fields> byName;
  private final String _fieldName;
  private final short _thriftId;
  
  static
  {
    TIME = new _Fields("TIME", 1, (short)2, "time");
    CLIENT_IP = new _Fields("CLIENT_IP", 2, (short)3, "clientIp");
    SERVER_IP = new _Fields("SERVER_IP", 3, (short)4, "serverIp");
    SERVER_HOST = new _Fields("SERVER_HOST", 4, (short)5, "serverHost");
    $VALUES = new _Fields[] { UUID, TIME, CLIENT_IP, SERVER_IP, SERVER_HOST };
    byName = new HashMap();
    Iterator localIterator = EnumSet.allOf(_Fields.class).iterator();
    while (localIterator.hasNext())
    {
      _Fields local_Fields = (_Fields)localIterator.next();
      byName.put(local_Fields.getFieldName(), local_Fields);
    }
  }
  
  private Common$_Fields(short paramShort, String paramString)
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
 * Qualified Name:     com.xiaomi.common.logger.thrift.Common._Fields
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */