package com.amap.api.maps2d.model;

import android.os.RemoteException;
import com.amap.api.mapcore2d.ah;
import com.amap.api.mapcore2d.cy;

public final class Circle
{
  private final ah a;
  
  public Circle(ah paramah)
  {
    a = paramah;
  }
  
  public boolean contains(LatLng paramLatLng)
  {
    try
    {
      if (a == null) {
        return false;
      }
      boolean bool = a.b(paramLatLng);
      return bool;
    }
    catch (RemoteException paramLatLng)
    {
      cy.a(paramLatLng, "Circle", "contains");
      throw new RuntimeRemoteException(paramLatLng);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Circle)) {}
    for (;;)
    {
      return false;
      try
      {
        if (a == null)
        {
          if (paramObject != null) {
            continue;
          }
          return true;
        }
        boolean bool = a.a(a);
        return bool;
      }
      catch (RemoteException paramObject)
      {
        cy.a((Throwable)paramObject, "Circle", "equals");
        throw new RuntimeRemoteException((RemoteException)paramObject);
      }
    }
  }
  
  public LatLng getCenter()
  {
    try
    {
      if (a == null) {
        return null;
      }
      LatLng localLatLng = a.g();
      return localLatLng;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "Circle", "getCenter");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public int getFillColor()
  {
    try
    {
      if (a == null) {
        return 0;
      }
      int i = a.k();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "Circle", "getFillColor");
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
      cy.a(localRemoteException, "Circle", "getId");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public double getRadius()
  {
    try
    {
      if (a == null) {
        return 0.0D;
      }
      double d = a.h();
      return d;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "Circle", "getRadius");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public int getStrokeColor()
  {
    try
    {
      if (a == null) {
        return 0;
      }
      int i = a.j();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "Circle", "getStrokeColor");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public float getStrokeWidth()
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
      cy.a(localRemoteException, "Circle", "getStrokeWidth");
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
      cy.a(localRemoteException, "Circle", "getZIndex");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public int hashCode()
  {
    try
    {
      if (a == null) {
        return 0;
      }
      int i = a.f();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "Circle", "hashCode");
      throw new RuntimeRemoteException(localRemoteException);
    }
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
      cy.a(localRemoteException, "Circle", "isVisible");
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
      cy.a(localRemoteException, "Circle", "remove");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setCenter(LatLng paramLatLng)
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
      cy.a(paramLatLng, "Circle", "setCenter");
      throw new RuntimeRemoteException(paramLatLng);
    }
  }
  
  public void setFillColor(int paramInt)
  {
    try
    {
      if (a == null) {
        return;
      }
      a.b(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "Circle", "setFillColor");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setRadius(double paramDouble)
  {
    try
    {
      if (a == null) {
        return;
      }
      a.a(paramDouble);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "Circle", "setRadius");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setStrokeColor(int paramInt)
  {
    try
    {
      if (a == null) {
        return;
      }
      a.a(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "Circle", "setStrokeColor");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setStrokeWidth(float paramFloat)
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
      cy.a(localRemoteException, "Circle", "setStrokeWidth");
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
      cy.a(localRemoteException, "Circle", "setVisible");
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
      cy.a(localRemoteException, "Circle", "setZIndex");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.Circle
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */