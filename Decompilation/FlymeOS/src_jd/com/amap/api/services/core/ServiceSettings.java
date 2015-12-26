package com.amap.api.services.core;

public class ServiceSettings
{
  public static final String CHINESE = "zh-CN";
  public static final String ENGLISH = "en";
  public static final int HTTP = 1;
  public static final int HTTPS = 2;
  private static ServiceSettings c;
  private String a = "zh-CN";
  private int b = 1;
  private int d = 20000;
  private int e = 20000;
  
  public static ServiceSettings getInstance()
  {
    if (c == null) {
      c = new ServiceSettings();
    }
    return c;
  }
  
  public int getConnectionTimeOut()
  {
    return d;
  }
  
  public String getLanguage()
  {
    return a;
  }
  
  public int getProtocol()
  {
    return b;
  }
  
  public int getSoTimeOut()
  {
    return e;
  }
  
  public void setApiKey(String paramString)
  {
    x.a(paramString);
  }
  
  public void setConnectionTimeOut(int paramInt)
  {
    if (paramInt < 5000)
    {
      d = 5000;
      return;
    }
    if (paramInt > 30000)
    {
      d = 30000;
      return;
    }
    d = paramInt;
  }
  
  public void setLanguage(String paramString)
  {
    if (("en".equals(paramString)) || ("zh-CN".equals(paramString))) {
      a = paramString;
    }
  }
  
  public void setProtocol(int paramInt)
  {
    b = paramInt;
  }
  
  public void setSoTimeOut(int paramInt)
  {
    if (paramInt < 5000)
    {
      e = 5000;
      return;
    }
    if (paramInt > 30000)
    {
      e = 30000;
      return;
    }
    e = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.ServiceSettings
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */