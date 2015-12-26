package com.amap.api.services.core;

public class ad
{
  String a;
  String b;
  String c;
  private boolean d = true;
  private String e = "standard";
  private String[] f = null;
  
  private ad(a parama)
  {
    a = a.a(parama);
    c = a.b(parama);
    b = a.c(parama);
    d = a.d(parama);
    e = a.e(parama);
    f = a.f(parama);
  }
  
  public String a()
  {
    return c;
  }
  
  public String b()
  {
    return a;
  }
  
  public String c()
  {
    return b;
  }
  
  public String d()
  {
    return e;
  }
  
  public boolean e()
  {
    return d;
  }
  
  public String[] f()
  {
    return (String[])f.clone();
  }
  
  public static class a
  {
    private String a;
    private String b;
    private String c;
    private boolean d = true;
    private String e = "standard";
    private String[] f = null;
    
    public a(String paramString1, String paramString2, String paramString3)
    {
      a = paramString2;
      c = paramString3;
      b = paramString1;
    }
    
    public a a(String paramString)
    {
      e = paramString;
      return this;
    }
    
    public a a(boolean paramBoolean)
    {
      d = paramBoolean;
      return this;
    }
    
    public a a(String[] paramArrayOfString)
    {
      f = ((String[])paramArrayOfString.clone());
      return this;
    }
    
    public ad a()
    {
      if (f == null) {
        throw new v("sdk packages is null");
      }
      return new ad(this, null);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.ad
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */