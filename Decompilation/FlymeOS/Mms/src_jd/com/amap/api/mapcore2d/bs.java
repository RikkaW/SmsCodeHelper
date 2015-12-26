package com.amap.api.mapcore2d;

import android.graphics.Point;
import android.graphics.PointF;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.LatLngBounds.Builder;
import com.amap.api.maps2d.model.VisibleRegion;

class bs
  implements aq
{
  private String a = "ProjectionDelegateImp";
  private ag b;
  
  public bs(ag paramag)
  {
    b = paramag;
  }
  
  public Point a(LatLng paramLatLng)
  {
    an localan = new an();
    b.b(latitude, longitude, localan);
    return new Point(a, b);
  }
  
  public LatLng a(Point paramPoint)
  {
    aa localaa = new aa();
    b.a(x, y, localaa);
    return new LatLng(b, a);
  }
  
  public VisibleRegion a()
  {
    Object localObject5 = null;
    for (;;)
    {
      try
      {
        int i = b.c();
        int j = b.d();
        localObject2 = a(new Point(0, 0));
        LatLngBounds localLatLngBounds;
        cy.a(localThrowable1, a, "getVisibleRegion");
      }
      catch (Throwable localThrowable1)
      {
        try
        {
          localObject3 = a(new Point(i, 0));
        }
        catch (Throwable localThrowable2)
        {
          for (;;)
          {
            Object localObject1;
            localLatLng1 = null;
            localLatLng2 = null;
            localObject3 = null;
            localObject4 = localObject2;
            localObject2 = localObject3;
          }
        }
        try
        {
          localLatLng2 = a(new Point(0, j));
        }
        catch (Throwable localThrowable3)
        {
          localLatLng1 = null;
          localLatLng2 = null;
          localObject4 = localObject2;
          localObject2 = localObject3;
          break label143;
        }
        try
        {
          localLatLng1 = a(new Point(i, j));
        }
        catch (Throwable localThrowable4)
        {
          localLatLng1 = null;
          localObject4 = localObject2;
          localObject2 = localObject3;
          break label143;
        }
        try
        {
          localLatLngBounds = LatLngBounds.builder().include(localLatLng2).include(localLatLng1).include((LatLng)localObject2).include((LatLng)localObject3).build();
          localObject4 = localObject2;
          return new VisibleRegion(localLatLng2, localLatLng1, (LatLng)localObject4, (LatLng)localObject3, localLatLngBounds);
        }
        catch (Throwable localThrowable5)
        {
          localObject4 = localObject2;
          localObject2 = localObject3;
          break label143;
        }
        localThrowable1 = localThrowable1;
        localLatLng1 = null;
        localLatLng2 = null;
        localObject2 = null;
        localObject4 = null;
      }
      label143:
      localObject3 = localObject2;
      localObject1 = localObject5;
    }
  }
  
  public PointF b(LatLng paramLatLng)
  {
    ab localab = new ab();
    b.a(latitude, longitude, localab);
    return new PointF(a, b);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bs
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */