package com.amap.api.services.core;

import java.util.Date;
import java.util.List;

class bc
  extends bi
{
  private a a;
  
  protected int a()
  {
    return 0;
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
    String str = bj.a(new Date().getTime());
    return ab.b(paramString + str);
  }
  
  protected String a(List<ad> paramList)
  {
    return null;
  }
  
  protected String b()
  {
    return bf.c;
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
 * Qualified Name:     com.amap.api.services.core.bc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */