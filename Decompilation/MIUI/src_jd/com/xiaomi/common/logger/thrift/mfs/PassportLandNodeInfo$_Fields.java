package com.xiaomi.common.logger.thrift.mfs;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum PassportLandNodeInfo$_Fields
{
  private static final Map<String, _Fields> byName;
  private final String _fieldName;
  private final short _thriftId;
  
  static
  {
    EID = new _Fields("EID", 1, (short)2, "eid");
    RT = new _Fields("RT", 2, (short)3, "rt");
    $VALUES = new _Fields[] { IP, EID, RT };
    byName = new HashMap();
    Iterator localIterator = EnumSet.allOf(_Fields.class).iterator();
    while (localIterator.hasNext())
    {
      _Fields local_Fields = (_Fields)localIterator.next();
      byName.put(local_Fields.getFieldName(), local_Fields);
    }
  }
  
  private PassportLandNodeInfo$_Fields(short paramShort, String paramString)
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
 * Qualified Name:     com.xiaomi.common.logger.thrift.mfs.PassportLandNodeInfo._Fields
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */