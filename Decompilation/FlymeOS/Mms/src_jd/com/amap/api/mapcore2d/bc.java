package com.amap.api.mapcore2d;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amap.api.maps2d.AMapOptions;
import com.amap.api.maps2d.model.CameraPosition;

public class bc
  implements aj
{
  public static volatile Context a;
  private ag b;
  private AMapOptions c;
  
  public View a(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (b == null)
    {
      if ((a == null) && (paramLayoutInflater != null)) {
        a = paramLayoutInflater.getContext().getApplicationContext();
      }
      if (a == null) {
        throw new NullPointerException("Context 为 null 请在地图调用之前 使用 MapsInitializer.initialize(Context paramContext) 来设置Context");
      }
      g();
      b = new b(a);
    }
    if ((c == null) && (paramBundle != null)) {
      c = ((AMapOptions)paramBundle.getParcelable("MapOptions"));
    }
    b(c);
    cw.a("MapFragmentDelegateImp", "onCreateView", 113);
    return b.e();
  }
  
  public ag a()
  {
    if (b == null)
    {
      if (a == null) {
        throw new NullPointerException("Context 为 null 请在地图调用之前 使用 MapsInitializer.initialize(Context paramContext) 来设置Context");
      }
      g();
      b = new b(a);
    }
    return b;
  }
  
  public void a(Activity paramActivity, AMapOptions paramAMapOptions, Bundle paramBundle)
  {
    a = paramActivity.getApplicationContext();
    c = paramAMapOptions;
  }
  
  public void a(Context paramContext)
  {
    if (paramContext != null) {
      a = paramContext.getApplicationContext();
    }
  }
  
  public void a(Bundle paramBundle)
  {
    cw.a("MapFragmentDelegateImp", "onCreate", 113);
  }
  
  public void a(AMapOptions paramAMapOptions)
  {
    c = paramAMapOptions;
  }
  
  public void b()
  {
    if (b != null) {
      b.y();
    }
  }
  
  public void b(Bundle paramBundle)
  {
    if (b != null)
    {
      if (c == null) {
        c = new AMapOptions();
      }
      c = c.camera(a().g());
      paramBundle.putParcelable("MapOptions", c);
    }
  }
  
  void b(AMapOptions paramAMapOptions)
  {
    if ((paramAMapOptions != null) && (b != null))
    {
      Object localObject = paramAMapOptions.getCamera();
      if (localObject != null) {
        b.a(u.a(target, zoom, bearing, tilt));
      }
      localObject = b.q();
      ((au)localObject).e(paramAMapOptions.getScrollGesturesEnabled().booleanValue());
      ((au)localObject).b(paramAMapOptions.getZoomControlsEnabled().booleanValue());
      ((au)localObject).f(paramAMapOptions.getZoomGesturesEnabled().booleanValue());
      ((au)localObject).c(paramAMapOptions.getCompassEnabled().booleanValue());
      ((au)localObject).a(paramAMapOptions.getScaleControlsEnabled().booleanValue());
      ((au)localObject).a(paramAMapOptions.getLogoPosition());
      b.b(paramAMapOptions.getMapType());
      b.a(paramAMapOptions.getZOrderOnTop().booleanValue());
    }
  }
  
  public void c()
  {
    if (b != null) {
      b.z();
    }
  }
  
  public void d() {}
  
  public void e()
  {
    if (a() != null)
    {
      a().k();
      a().v();
    }
  }
  
  public void f()
  {
    Log.d("onLowMemory", "onLowMemory run");
  }
  
  void g()
  {
    int i = agetResourcesgetDisplayMetricsdensityDpi;
    y.k = i;
    if (i <= 120)
    {
      y.a = 0.5F;
      return;
    }
    if (i <= 160)
    {
      y.a = 0.6F;
      return;
    }
    if (i <= 240)
    {
      y.a = 0.87F;
      return;
    }
    if (i <= 320)
    {
      y.a = 1.0F;
      return;
    }
    if (i <= 480)
    {
      y.i = 512;
      y.a = 1.5F;
      return;
    }
    y.i = 512;
    y.a = 1.8F;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */