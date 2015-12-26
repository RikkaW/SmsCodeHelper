package com.ted.android.contacts.common;

import java.util.HashMap;
import java.util.Map;

public class DataBus
{
  public static String APP_BUILD;
  public static String APP_VERSION;
  public static int CID;
  public static String CONTACT;
  public static String DID = null;
  public static String FILE_MASK;
  public static int PRO_ID;
  public static String U1_YEK;
  public static String USER_CITY_CODE;
  private static DataBus instance = null;
  private Map<Object, Object> data = new HashMap();
  
  static
  {
    PRO_ID = 1;
    CONTACT = null;
    APP_VERSION = "1.0.0";
    APP_BUILD = null;
    U1_YEK = null;
    CID = -1;
    USER_CITY_CODE = "";
    FILE_MASK = "";
  }
  
  public static DataBus getInstance()
  {
    try
    {
      if (instance == null) {
        instance = new DataBus();
      }
      DataBus localDataBus = instance;
      return localDataBus;
    }
    finally {}
  }
  
  public Object get(Object paramObject)
  {
    return data.get(paramObject);
  }
  
  public String getString(String paramString)
  {
    return (String)data.get(paramString);
  }
  
  public void set(Object paramObject1, Object paramObject2)
  {
    data.put(paramObject1, paramObject2);
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.common.DataBus
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */