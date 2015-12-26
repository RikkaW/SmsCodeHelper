package com.amap.api.maps2d;

import android.content.Context;
import com.amap.api.mapcore2d.db;

public final class MapsInitializer
{
  private static boolean a = true;
  public static String sdcardDir = "";
  
  public static boolean getNetworkEnable()
  {
    return a;
  }
  
  public static String getVersion()
  {
    return "2.5.0";
  }
  
  public static void initialize(Context paramContext)
  {
    com.amap.api.mapcore2d.bc.a = paramContext.getApplicationContext();
  }
  
  public static void replaceURL(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString1.equals(""))) {}
    do
    {
      return;
      com.amap.api.mapcore2d.y.h = paramString1;
      com.amap.api.mapcore2d.y.g = paramString2 + "DIY";
    } while (!paramString1.contains("openstreetmap"));
    com.amap.api.mapcore2d.y.c = 19;
  }
  
  public static void setApiKey(String paramString)
  {
    db.a(paramString);
  }
  
  public static void setNetworkEnable(boolean paramBoolean)
  {
    a = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.MapsInitializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */