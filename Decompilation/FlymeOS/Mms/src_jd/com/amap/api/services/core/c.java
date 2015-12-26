package com.amap.api.services.core;

public class c
{
  public static final String[] a = { "com.amap.api.services" };
  
  public static ad a(boolean paramBoolean)
  {
    try
    {
      ad localad = new ad.a("sea", "2.5.0", "AMAP SDK Android Search 2.5.0").a(a).a(paramBoolean).a();
      return localad;
    }
    catch (v localv)
    {
      d.a(localv, "ConfigableConst", "getSDKInfo");
    }
    return null;
  }
  
  public static String a()
  {
    if (ServiceSettings.getInstance().getProtocol() == 1) {
      return "http://restapi.amap.com/v3";
    }
    return "https://restapi.amap.com/v3";
  }
  
  public static String b()
  {
    return ServiceSettings.getInstance().getLanguage();
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */