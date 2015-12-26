package com.amap.api.services.core;

public class SuggestionCity
{
  private String a;
  private String b;
  private String c;
  private int d;
  
  protected SuggestionCity() {}
  
  public SuggestionCity(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    a = paramString1;
    b = paramString2;
    c = paramString3;
    d = paramInt;
  }
  
  public String getAdCode()
  {
    return c;
  }
  
  public String getCityCode()
  {
    return b;
  }
  
  public String getCityName()
  {
    return a;
  }
  
  public int getSuggestionNum()
  {
    return d;
  }
  
  public void setAdCode(String paramString)
  {
    c = paramString;
  }
  
  public void setCityCode(String paramString)
  {
    b = paramString;
  }
  
  public void setCityName(String paramString)
  {
    a = paramString;
  }
  
  public void setSuggestionNum(int paramInt)
  {
    d = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.SuggestionCity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */