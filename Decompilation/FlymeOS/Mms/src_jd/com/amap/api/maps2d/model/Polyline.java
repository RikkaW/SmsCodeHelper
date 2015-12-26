package com.amap.api.maps2d.model;

import android.os.RemoteException;
import com.amap.api.mapcore2d.ap;
import com.amap.api.mapcore2d.cy;
import java.util.List;

public class Polyline
{
  private final ap a;
  
  public Polyline(ap paramap)
  {
    a = paramap;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Polyline)) {}
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
        cy.a((Throwable)paramObject, "Polyline", "equals");
        throw new RuntimeRemoteException((RemoteException)paramObject);
      }
    }
  }
  
  public int getColor()
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
      cy.a(localRemoteException, "Polyline", "getColor");
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
      cy.a(localRemoteException, "Polyline", "getId");
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
      cy.a(localRemoteException, "Polyline", "getPoints");
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
      float f = a.g();
      return f;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "Polyline", "getWidth");
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
      cy.a(localRemoteException, "Polyline", "getZIndex");
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
      cy.a(localRemoteException, "Polyline", "hashCode");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public boolean isDottedLine()
  {
    if (a == null) {
      return false;
    }
    return a.j();
  }
  
  public boolean isGeodesic()
  {
    if (a == null) {
      return false;
    }
    return a.k();
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
      cy.a(localRemoteException, "Polyline", "isVisible");
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
      cy.a(localRemoteException, "Polyline", "remove");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setColor(int paramInt)
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
      cy.a(localRemoteException, "Polyline", "setColor");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setDottedLine(boolean paramBoolean)
  {
    if (a == null) {
      return;
    }
    a.b(paramBoolean);
  }
  
  public void setGeodesic(boolean paramBoolean)
  {
    try
    {
      if (a == null) {
        return;
      }
      if (a.k() != paramBoolean)
      {
        List localList = getPoints();
        a.c(paramBoolean);
        setPoints(localList);
        return;
      }
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "Polyline", "setGeodesic");
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
      cy.a(paramList, "Polyline", "setPoints");
      throw new RuntimeRemoteException(paramList);
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
      cy.a(localRemoteException, "Polyline", "setVisible");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setWidth(float paramFloat)
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
      cy.a(localRemoteException, "Polyline", "setWidth");
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
      cy.a(localRemoteException, "Polyline", "setZIndex");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.Polyline
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */