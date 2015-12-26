package com.amap.api.maps2d;

import android.content.Context;
import com.amap.api.mapcore2d.cz;
import com.amap.api.mapcore2d.db;
import com.amap.api.mapcore2d.dh;
import com.amap.api.mapcore2d.dh.a;

class AMapUtils$a
  extends Thread
{
  String a = "";
  Context b;
  
  public AMapUtils$a(String paramString, Context paramContext)
  {
    a = paramString;
    if (paramContext != null) {
      b = paramContext.getApplicationContext();
    }
  }
  
  public void run()
  {
    if (b != null) {}
    try
    {
      dh localdh = new dh.a(a, "2.5.0", "AMAP SDK Android 2DMap 2.5.0").a(new String[] { "com.amap.api.maps" }).a();
      db.a(b, localdh);
      interrupt();
      return;
    }
    catch (cz localcz)
    {
      localcz.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.AMapUtils.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */