package com.amap.api.maps2d;

import android.graphics.Point;
import android.graphics.PointF;
import android.os.RemoteException;
import com.amap.api.mapcore2d.aq;
import com.amap.api.mapcore2d.cy;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.RuntimeRemoteException;
import com.amap.api.maps2d.model.VisibleRegion;

public class Projection
{
  private final aq a;
  
  Projection(aq paramaq)
  {
    a = paramaq;
  }
  
  public LatLng fromScreenLocation(Point paramPoint)
  {
    try
    {
      paramPoint = a.a(paramPoint);
      return paramPoint;
    }
    catch (RemoteException paramPoint)
    {
      cy.a(paramPoint, "Projection", "fromScreenLocation");
      throw new RuntimeRemoteException(paramPoint);
    }
  }
  
  public VisibleRegion getVisibleRegion()
  {
    try
    {
      VisibleRegion localVisibleRegion = a.a();
      return localVisibleRegion;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "Projection", "getVisibleRegion");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public PointF toMapLocation(LatLng paramLatLng)
  {
    try
    {
      paramLatLng = a.b(paramLatLng);
      return paramLatLng;
    }
    catch (RemoteException paramLatLng)
    {
      cy.a(paramLatLng, "Projection", "toMapLocation");
      throw new RuntimeRemoteException(paramLatLng);
    }
  }
  
  public Point toScreenLocation(LatLng paramLatLng)
  {
    try
    {
      paramLatLng = a.a(paramLatLng);
      return paramLatLng;
    }
    catch (RemoteException paramLatLng)
    {
      cy.a(paramLatLng, "Projection", "toScreenLocation");
      throw new RuntimeRemoteException(paramLatLng);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.Projection
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */