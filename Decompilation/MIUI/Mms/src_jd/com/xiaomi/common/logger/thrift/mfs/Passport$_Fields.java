package com.xiaomi.common.logger.thrift.mfs;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum Passport$_Fields
{
  private static final Map<String, _Fields> byName;
  private final String _fieldName;
  private final short _thriftId;
  
  static
  {
    NETWORK = new _Fields("NETWORK", 3, (short)4, "network");
    RID = new _Fields("RID", 4, (short)5, "rid");
    LOCATION = new _Fields("LOCATION", 5, (short)6, "location");
    HOST_INFO = new _Fields("HOST_INFO", 6, (short)7, "host_info");
    $VALUES = new _Fields[] { CATEGORY, UUID, VERSION, NETWORK, RID, LOCATION, HOST_INFO };
    byName = new HashMap();
    Iterator localIterator = EnumSet.allOf(_Fields.class).iterator();
    while (localIterator.hasNext())
    {
      _Fields local_Fields = (_Fields)localIterator.next();
      byName.put(local_Fields.getFieldName(), local_Fields);
    }
  }
  
  private Passport$_Fields(short paramShort, String paramString)
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
 * Qualified Name:     com.xiaomi.common.logger.thrift.mfs.Passport._Fields
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */