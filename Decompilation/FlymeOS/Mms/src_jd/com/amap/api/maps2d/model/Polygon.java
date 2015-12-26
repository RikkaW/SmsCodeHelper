package com.amap.api.maps2d.model;

import android.os.RemoteException;
import com.amap.api.mapcore2d.ao;
import com.amap.api.mapcore2d.cy;
import java.util.List;

public final class Polygon
{
  private ao a;
  
  public Polygon(ao paramao)
  {
    a = paramao;
  }
  
  public boolean contains(LatLng paramLatLng)
  {
    try
    {
      if (a == null) {
        return false;
      }
      boolean bool = a.a(paramLatLng);
      return bool;
    }
    catch (RemoteException paramLatLng)
    {
      cy.a(paramLatLng, "Polygon", "contains");
      throw new RuntimeRemoteException(paramLatLng);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Polygon)) {}
    for (;;)
    {
      return false;
      try
      {
        if (a == null)
        {
          if (paramObject == null) {
            return false;
          }
        }
        else
        {
          boolean bool = a.a(a);
          return bool;
        }
      }
      catch (RemoteException paramObject)
      {
        cy.a((Throwable)paramObject, "Polygon", "equeals");
      }
    }
    return false;
  }
  
  public int getFillColor()
  {
    try
    {
      if (a == null) {
        return 0;
      }
      int i = a.h();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "Polygon", "getFillColor");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public String getId()
  {
    try
    {
      if (a == null) {
        return null;
      }
      String str = a.c();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "Polygon", "getId");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public List<LatLng> getPoints()
  {
    try
    {
      if (a == null) {
        return null;
      }
      List localList = a.i();
      return localList;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "Polygon", "getPoints");
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
      cy.a(localRemoteException, "Polygon", "getStrokeColor");
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
      float f = a.g();
      return f;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "Polygon", "getStrokeWidth");
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
      cy.a(localRemoteException, "Polygon", "getZIndex");
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
      cy.a(localRemoteException, "Polygon", "hashCode");
    }
    return super.hashCode();
  }
  
  public boolean isVisible()
  {
    try
    {
      if (a == null) {
        return true;
      }
      boolean bool = a.e();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
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
      cy.a(localRemoteException, "Polygon", "remove");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setFillColor(int paramInt)
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
      cy.a(localRemoteException, "Polygon", "setFillColor");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setPoints(List<LatLng> paramList)
  {
    try
    {
      if (a == null) {
        return;
      }
      a.a(paramList);
      return;
    }
    catch (RemoteException paramList)
    {
      cy.a(paramList, "Polygon", "setPoints");
      throw new RuntimeRemoteException(paramList);
    }
  }
  
  public void setStrokeColor(int paramInt)
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
      cy.a(localRemoteException, "Polygon", "setStrokeColor");
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
      cy.a(localRemoteException, "Polygon", "setStrokeWidth");
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
      cy.a(localRemoteException, "Polygon", "setVisible");
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
      cy.a(localRemoteException, "Polygon", "setZIndex");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.Polygon
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */