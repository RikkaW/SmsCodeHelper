package com.amap.api.mapcore2d;

public class dh$a
{
  private String a;
  private String b;
  private String c;
  private boolean d = true;
  private String e = "standard";
  private String[] f = null;
  
  public dh$a(String paramString1, String paramString2, String paramString3)
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
  
  public dh a()
  {
    if (f == null) {
      throw new cz("sdk packages is null");
    }
    return new dh(this, null);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.dh.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */