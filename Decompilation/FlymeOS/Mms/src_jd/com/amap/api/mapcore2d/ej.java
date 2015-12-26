package com.amap.api.mapcore2d;

import java.util.List;

class ej
  extends en
{
  private a a;
  
  protected int a()
  {
    return 1;
  }
  
  protected es a(dp paramdp)
  {
    try
    {
      if (a == null) {
        a = new a(paramdp);
      }
      return a;
    }
    catch (Throwable paramdp)
    {
      for (;;)
      {
        paramdp.printStackTrace();
      }
    }
  }
  
  protected String a(String paramString)
  {
    return df.b(paramString);
  }
  
  protected String a(List<dh> paramList)
  {
    return null;
  }
  
  protected String b()
  {
    return ek.b;
  }
  
  class a
    implements es
  {
    private dp b;
    
    a(dp paramdp)
    {
      b = paramdp;
    }
    
    public void a(String paramString)
    {
      try
      {
        b.b(paramString, a());
        return;
      }
      catch (Throwable paramString)
      {
        paramString.printStackTrace();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ej
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */