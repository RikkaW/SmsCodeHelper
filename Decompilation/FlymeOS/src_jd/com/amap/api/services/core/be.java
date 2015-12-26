package com.amap.api.services.core;

import java.util.List;

class be
  extends bi
{
  private a a;
  
  protected int a()
  {
    return 1;
  }
  
  protected bn a(ak paramak)
  {
    try
    {
      if (a == null) {
        a = new a(paramak);
      }
      return a;
    }
    catch (Throwable paramak)
    {
      for (;;)
      {
        paramak.printStackTrace();
      }
    }
  }
  
  protected String a(String paramString)
  {
    return ab.b(paramString);
  }
  
  protected String a(List<ad> paramList)
  {
    return null;
  }
  
  protected String b()
  {
    return bf.b;
  }
  
  class a
    implements bn
  {
    private ak b;
    
    a(ak paramak)
    {
      b = paramak;
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
 * Qualified Name:     com.amap.api.services.core.be
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */