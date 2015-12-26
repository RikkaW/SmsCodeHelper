package com.xiaomi.xmpush.thrift;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum XmPushActionSendFeedback$_Fields
{
  private static final Map<String, _Fields> byName;
  private final String _fieldName;
  private final short _thriftId;
  
  static
  {
    ID = new _Fields("ID", 2, (short)3, "id");
    APP_ID = new _Fields("APP_ID", 3, (short)4, "appId");
    FEEDBACKS = new _Fields("FEEDBACKS", 4, (short)5, "feedbacks");
    CATEGORY = new _Fields("CATEGORY", 5, (short)6, "category");
    $VALUES = new _Fields[] { DEBUG, TARGET, ID, APP_ID, FEEDBACKS, CATEGORY };
    byName = new HashMap();
    Iterator localIterator = EnumSet.allOf(_Fields.class).iterator();
    while (localIterator.hasNext())
    {
      _Fields local_Fields = (_Fields)localIterator.next();
      byName.put(local_Fields.getFieldName(), local_Fields);
    }
  }
  
  private XmPushActionSendFeedback$_Fields(short paramShort, String paramString)
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
 * Qualified Name:     com.xiaomi.xmpush.thrift.XmPushActionSendFeedback._Fields
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */