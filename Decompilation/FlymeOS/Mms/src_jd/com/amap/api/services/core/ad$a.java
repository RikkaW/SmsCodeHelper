package com.amap.api.services.core;

public class ad$a
{
  private String a;
  private String b;
  private String c;
  private boolean d = true;
  private String e = "standard";
  private String[] f = null;
  
  public ad$a(String paramString1, String paramString2, String paramString3)
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

/* Location:
 * Qualified Name:     com.amap.api.services.core.ad.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */