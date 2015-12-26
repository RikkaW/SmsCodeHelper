package com.amap.api.maps2d.model;

import android.os.RemoteException;
import com.amap.api.mapcore2d.ai;
import com.amap.api.mapcore2d.cy;

public final class GroundOverlay
{
  private ai a;
  
  public GroundOverlay(ai paramai)
  {
    a = paramai;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof GroundOverlay)) {
      return false;
    }
    try
    {
      throw new RemoteException();
    }
    catch (RemoteException paramObject)
    {
      cy.a((Throwable)paramObject, "GroundOverlay", "equals");
      throw new RuntimeRemoteException((RemoteException)paramObject);
    }
  }
  
  public float getBearing()
  {
    try
    {
      if (a == null) {
        return 0.0F;
      }
      float f = a.m();
      return f;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "GroundOverlay", "getBearing");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public LatLngBounds getBounds()
  {
    try
    {
      if (a == null) {
        return null;
      }
      LatLngBounds localLatLngBounds = a.k();
      return localLatLngBounds;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "GroundOverlay", "getBounds");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public float getHeight()
  {
    try
    {
      if (a == null) {
        return 0.0F;
      }
      float f = a.j();
      return f;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "GroundOverlay", "getHeight");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public String getId()
  {
    try
    {
      if (a == null) {
        return "";
      }
      String str = a.c();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "GroundOverlay", "getId");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public LatLng getPosition()
  {
    try
    {
      if (a == null) {
        return null;
      }
      LatLng localLatLng = a.h();
      return localLatLng;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "GroundOverlay", "getPosition");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public float getTransparency()
  {
    try
    {
      if (a == null) {
        return 0.0F;
      }
      float f = a.n();
      return f;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "GroundOverlay", "getTransparency");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public float getWidth()
  {
    try
    {
      if (a == null) {
        return 0.0F;
      }
      float f = a.i();
      return f;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "GroundOverlay", "getWidth");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public float getZIndex()
  {
    try
    {
      if (a == null) {
        return 0.0F;
      }
      float f = a.d();
      return f;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "GroundOverlay", "getZIndex");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public int hashCode()
  {
    if (a == null) {
      return 0;
    }
    return a.hashCode();
  }
  
  public boolean isVisible()
  {
    try
    {
      if (a == null) {
        return false;
      }
      boolean bool = a.e();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "GroundOverlay", "isVisible");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void remove()
  {
    try
    {
      if (a == null) {
        return;
      }
      a.b();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "GroundOverlay", "remove");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setBearing(float paramFloat)
  {
    try
    {
      if (a == null) {
        return;
      }
      a.c(paramFloat);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "GroundOverlay", "setBearing");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setDimensions(float paramFloat)
  {
    try
    {
      if (a == null) {
        return;
      }
      a.b(paramFloat);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "GroundOverlay", "setDimensions");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setDimensions(float paramFloat1, float paramFloat2)
  {
    try
    {
      if (a == null) {
        return;
      }
      a.a(paramFloat1, paramFloat2);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "GroundOverlay", "setDimensions");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setImage(BitmapDescriptor paramBitmapDescriptor)
  {
    try
    {
      if (a == null) {
        return;
      }
      a.a(paramBitmapDescriptor);
      return;
    }
    catch (RemoteException paramBitmapDescriptor)
    {
      cy.a(paramBitmapDescriptor, "GroundOverlay", "setImage");
      throw new RuntimeRemoteException(paramBitmapDescriptor);
    }
  }
  
  public void setPosition(LatLng paramLatLng)
  {
    try
    {
      if (a == null) {
        return;
      }
      a.a(paramLatLng);
      return;
    }
    catch (RemoteException paramLatLng)
    {
      cy.a(paramLatLng, "GroundOverlay", "setPosition");
      throw new RuntimeRemoteException(paramLatLng);
    }
  }
  
  public void setPositionFromBounds(LatLngBounds paramLatLngBounds)
  {
    try
    {
      if (a == null) {
        return;
      }
      a.a(paramLatLngBounds);
      return;
    }
    catch (RemoteException paramLatLngBounds)
    {
      cy.a(paramLatLngBounds, "GroundOverlay", "setPositionFromBounds");
      throw new RuntimeRemoteException(paramLatLngBounds);
    }
  }
  
  public void setTransparency(float paramFloat)
  {
    try
    {
      if (a == null) {
        return;
      }
      a.d(paramFloat);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "GroundOverlay", "setTransparency");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setVisible(boolean paramBoolean)
  {
    try
    {
      if (a == null) {
        return;
      }
      a.a(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "GroundOverlay", "setVisible");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setZIndex(float paramFloat)
  {
    try
    {
      if (a == null) {
        return;
      }
      a.a(paramFloat);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "GroundOverlay", "setZIndex");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.GroundOverlay
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */