package com.amap.api.mapcore2d;

import android.graphics.Color;
import android.os.RemoteException;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;

class bn
{
  private ag a;
  private ak b;
  private ah c;
  private MyLocationStyle d;
  private LatLng e;
  private double f;
  
  bn(ag paramag)
  {
    a = paramag;
  }
  
  private void b()
  {
    if (d == null)
    {
      c();
      return;
    }
    d();
  }
  
  private void c()
  {
    try
    {
      c = a.a(new CircleOptions().strokeWidth(1.0F).fillColor(Color.argb(20, 0, 0, 180)).strokeColor(Color.argb(255, 0, 0, 220)).center(new LatLng(0.0D, 0.0D)));
      c.a(200.0D);
      b = a.b(new MarkerOptions().anchor(0.5F, 0.5F).icon(BitmapDescriptorFactory.fromAsset(ar.a.c.name() + ".png")).position(new LatLng(0.0D, 0.0D)));
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "MyLocationOverlay", "defaultLocStyle");
    }
  }
  
  private void d()
  {
    if (d == null) {}
    for (;;)
    {
      return;
      try
      {
        c = a.a(new CircleOptions().strokeWidth(d.getStrokeWidth()).fillColor(d.getRadiusFillColor()).strokeColor(d.getStrokeColor()).center(new LatLng(0.0D, 0.0D)));
        if (e != null) {
          c.a(e);
        }
        c.a(f);
        b = a.b(new MarkerOptions().anchor(d.getAnchorU(), d.getAnchorV()).icon(d.getMyLocationIcon()).position(new LatLng(0.0D, 0.0D)));
        if (e != null)
        {
          b.b(e);
          return;
        }
      }
      catch (RemoteException localRemoteException)
      {
        localRemoteException.printStackTrace();
      }
    }
  }
  
  public void a()
  {
    if (c != null)
    {
      a.a(c.c());
      c = null;
    }
    if (b != null)
    {
      a.b(b.d());
      b = null;
    }
  }
  
  public void a(float paramFloat)
  {
    if (b != null) {}
    try
    {
      b.a(paramFloat);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "MyLocationOverlay", "setRotateAngle");
    }
  }
  
  public void a(LatLng paramLatLng, double paramDouble)
  {
    e = paramLatLng;
    f = paramDouble;
    if ((b == null) && (c == null)) {
      b();
    }
    if (b == null) {}
    for (;;)
    {
      return;
      b.b(paramLatLng);
      try
      {
        c.a(paramLatLng);
        if (paramDouble != -1.0D)
        {
          c.a(paramDouble);
          return;
        }
      }
      catch (RemoteException paramLatLng)
      {
        cy.a(paramLatLng, "MyLocationOverlay", "setCentAndRadius");
      }
    }
  }
  
  public void a(MyLocationStyle paramMyLocationStyle)
  {
    d = paramMyLocationStyle;
    if ((b == null) && (c == null)) {
      return;
    }
    try
    {
      a();
      d();
      return;
    }
    catch (RemoteException paramMyLocationStyle)
    {
      for (;;)
      {
        cy.a(paramMyLocationStyle, "MyLocationOverlay", "setMyLocationStyle");
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */