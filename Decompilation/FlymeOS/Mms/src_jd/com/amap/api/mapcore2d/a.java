package com.amap.api.mapcore2d;

import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;

class a
{
  private b a;
  private int b;
  
  public a(b paramb)
  {
    a = paramb;
  }
  
  protected void a(u paramu)
  {
    for (;;)
    {
      int i;
      try
      {
        if (a == null) {
          break;
        }
        if (a.D() == null) {
          return;
        }
        i = (int)a.f();
        if (a == u.a.h)
        {
          a.a.c((int)b, (int)c);
          a.postInvalidate();
          if ((i == b) || (!a.q().a())) {
            break;
          }
          a.L();
          return;
        }
      }
      catch (Exception paramu)
      {
        cy.a(paramu, "AMapCallback", "runCameraUpdate");
        return;
      }
      if (a == u.a.b)
      {
        a.D().c();
      }
      else if (a == u.a.e)
      {
        a.D().d();
      }
      else
      {
        int j;
        if (a == u.a.f)
        {
          j = (int)d;
          a.D().c(j);
        }
        else if (a == u.a.g)
        {
          float f = e;
          j = a.a((int)(f + i));
          paramu = h;
          f = j - i;
          if (paramu != null) {
            a.a(f, paramu, false);
          } else {
            a.D().c(j);
          }
        }
        else
        {
          int k;
          if (a == u.a.i)
          {
            paramu = f;
            j = (int)(target.latitude * 1000000.0D);
            k = (int)(target.longitude * 1000000.0D);
            a.D().a(new ae(j, k), (int)zoom);
          }
          else if (a == u.a.c)
          {
            paramu = f;
            j = (int)(target.latitude * 1000000.0D);
            k = (int)(target.longitude * 1000000.0D);
            a.D().a(new ae(j, k));
            t.a().b();
          }
          else if ((a == u.a.j) || (a == u.a.k))
          {
            a.a(paramu, false, -1L);
          }
          else
          {
            i = true;
          }
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */