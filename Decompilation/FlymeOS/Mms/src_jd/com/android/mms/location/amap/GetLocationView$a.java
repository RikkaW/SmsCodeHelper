package com.android.mms.location.amap;

import android.app.Activity;
import android.util.Log;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import java.util.ArrayList;
import kf;
import kv;
import la;
import la.a;
import lb;
import ld;

public class GetLocationView$a
  implements la.a
{
  public GetLocationView$a(GetLocationView paramGetLocationView) {}
  
  public Activity a()
  {
    return GetLocationView.a(a);
  }
  
  public void a(int paramInt, LatLng paramLatLng)
  {
    GetLocationView.d(a).a(paramInt, paramLatLng);
  }
  
  public void a(LatLng paramLatLng, String paramString1, String paramString2)
  {
    GetLocationView.d(a).a(paramLatLng, paramString1, paramString2);
    GetLocationView.a(a, paramString1, paramString2);
  }
  
  public void a(LatLng paramLatLng, boolean paramBoolean)
  {
    if ((paramBoolean) && (GetLocationView.b(a)))
    {
      GetLocationView.c(a).moveCamera(CameraUpdateFactory.zoomTo(14.0F));
      return;
    }
    GetLocationView.a(a, paramLatLng);
  }
  
  public void a(String paramString)
  {
    GetLocationView.d(a).a(paramString);
  }
  
  public void a(ld paramld)
  {
    Log.i("amap/GetLocationView", "GetLocationView onCurrentLocationChanged currentLocation.getAddr = " + paramld.b());
    if (GetLocationView.e(a).size() == 0)
    {
      GetLocationView.f(a).b(0);
      GetLocationView.g(a).add(0, paramld);
    }
    for (;;)
    {
      GetLocationView.i(a).p().notifyDataSetChanged();
      return;
      GetLocationView.h(a).set(0, paramld);
    }
  }
  
  public void a(boolean paramBoolean)
  {
    a.a(paramBoolean, GetLocationView.j(a).b());
  }
  
  public void b(boolean paramBoolean)
  {
    GetLocationView.a(a, paramBoolean);
  }
  
  public boolean b()
  {
    return a.d();
  }
  
  public boolean c()
  {
    return a.k();
  }
  
  public LatLng d()
  {
    return ca).getCameraPosition().target;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.location.amap.GetLocationView.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */