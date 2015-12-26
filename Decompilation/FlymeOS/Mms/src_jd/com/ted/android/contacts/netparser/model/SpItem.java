package com.ted.android.contacts.netparser.model;

import java.util.Map;

public class SpItem
{
  private SpType a;
  private String b;
  private String c;
  private Map<String, String> d;
  
  public Map<String, String> a()
  {
    return d;
  }
  
  public void a(SpType paramSpType)
  {
    a = paramSpType;
  }
  
  public void a(String paramString)
  {
    b = paramString;
  }
  
  public void a(Map<String, String> paramMap)
  {
    d = paramMap;
  }
  
  public SpType b()
  {
    return a;
  }
  
  public void b(String paramString)
  {
    c = paramString;
  }
  
  public String c()
  {
    return b;
  }
  
  public String d()
  {
    return c;
  }
  
  public static enum SpType {}
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.netparser.model.SpItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */