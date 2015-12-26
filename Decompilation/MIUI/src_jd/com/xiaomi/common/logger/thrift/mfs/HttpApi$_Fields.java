package com.xiaomi.common.logger.thrift.mfs;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum HttpApi$_Fields
{
  private static final Map<String, _Fields> byName;
  private final String _fieldName;
  private final short _thriftId;
  
  static
  {
    NETWORK = new _Fields("NETWORK", 3, (short)4, "network");
    CLIENT_IP = new _Fields("CLIENT_IP", 4, (short)5, "client_ip");
    LOCATION = new _Fields("LOCATION", 5, (short)6, "location");
    HOST_INFO = new _Fields("HOST_INFO", 6, (short)7, "host_info");
    VERSION_TYPE = new _Fields("VERSION_TYPE", 7, (short)8, "version_type");
    APP_NAME = new _Fields("APP_NAME", 8, (short)9, "app_name");
    APP_VERSION = new _Fields("APP_VERSION", 9, (short)10, "app_version");
    $VALUES = new _Fields[] { CATEGORY, UUID, VERSION, NETWORK, CLIENT_IP, LOCATION, HOST_INFO, VERSION_TYPE, APP_NAME, APP_VERSION };
    byName = new HashMap();
    Iterator localIterator = EnumSet.allOf(_Fields.class).iterator();
    while (localIterator.hasNext())
    {
      _Fields local_Fields = (_Fields)localIterator.next();
      byName.put(local_Fields.getFieldName(), local_Fields);
    }
  }
  
  private HttpApi$_Fields(short paramShort, String paramString)
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
 * Qualified Name:     com.xiaomi.common.logger.thrift.mfs.HttpApi._Fields
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */