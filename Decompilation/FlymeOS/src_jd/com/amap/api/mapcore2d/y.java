package com.amap.api.mapcore2d;

public class y
{
  public static float a = 0.9F;
  public static float b = 18.0F;
  public static int c = 20;
  public static int d = 3;
  public static final a e = a.a;
  public static final String[] f = { "com.amap.api.mapcore2d", "com.amap.api.maps2d" };
  public static String g;
  public static String h;
  public static int i = 256;
  public static int j = 21;
  public static int k;
  static int l = 0;
  static int m = 0;
  static int n = 0;
  static boolean o = true;
  static boolean p = true;
  
  public static dh a(boolean paramBoolean)
  {
    try
    {
      dh localdh = new dh.a("2dmap", "2.5.0", "AMAP SDK Android 2DMap 2.5.0").a(f).a(paramBoolean).a();
      return localdh;
    }
    catch (cz localcz)
    {
      cy.a(localcz, "ConfigableConst", "getSDKInfo");
    }
    return null;
  }
  
  static enum a
  {
    private a() {}
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.y
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */