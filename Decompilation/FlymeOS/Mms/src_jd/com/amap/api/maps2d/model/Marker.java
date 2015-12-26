package com.amap.api.maps2d.model;

import android.os.RemoteException;
import com.amap.api.mapcore2d.ak;
import com.amap.api.mapcore2d.cy;
import java.util.ArrayList;

public final class Marker
{
  ak a;
  
  public Marker(ak paramak)
  {
    a = paramak;
  }
  
  public void destroy()
  {
    try
    {
      if (a != null) {
        a.l();
      }
      return;
    }
    catch (Exception localException)
    {
      cy.a(localException, "Marker", "destroy");
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Marker)) {
      return false;
    }
    return a.a(a);
  }
  
  public ArrayList<BitmapDescriptor> getIcons()
  {
    try
    {
      ArrayList localArrayList = a.p();
      return localArrayList;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "Marker", "getIcons");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public String getId()
  {
    return a.d();
  }
  
  public Object getObject()
  {
    if (a != null) {
      return a.u();
    }
    return null;
  }
  
  public int getPeriod()
  {
    try
    {
      int i = a.o();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "Marker", "getPeriod");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public LatLng getPosition()
  {
    return a.t();
  }
  
  public String getSnippet()
  {
    return a.g();
  }
  
  public String getTitle()
  {
    return a.f();
  }
  
  public float getZIndex()
  {
    return a.r();
  }
  
  public int hashCode()
  {
    return a.m();
  }
  
  public void hideInfoWindow()
  {
    a.j();
  }
  
  public boolean isDraggable()
  {
    return a.h();
  }
  
  public boolean isInfoWindowShown()
  {
    return a.k();
  }
  
  public boolean isVisible()
  {
    return a.s();
  }
  
  public void remove()
  {
    try
    {
      a.a();
      return;
    }
    catch (Exception localException)
    {
      cy.a(localException, "Marker", "remove");
    }
  }
  
  public void setAnchor(float paramFloat1, float paramFloat2)
  {
    a.a(paramFloat1, paramFloat2);
  }
  
  public void setDraggable(boolean paramBoolean)
  {
    a.a(paramBoolean);
  }
  
  public void setIcon(BitmapDescriptor paramBitmapDescriptor)
  {
    if (paramBitmapDescriptor != null) {
      a.a(paramBitmapDescriptor);
    }
  }
  
  public void setIcons(ArrayList<BitmapDescriptor> paramArrayList)
  {
    try
    {
      a.a(paramArrayList);
      return;
    }
    catch (RemoteException paramArrayList)
    {
      cy.a(paramArrayList, "Marker", "setIcons");
      throw new RuntimeRemoteException(paramArrayList);
    }
  }
  
  public void setObject(Object paramObject)
  {
    a.a(paramObject);
  }
  
  public void setPeriod(int paramInt)
  {
    try
    {
      a.a(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "Marker", "setPeriod");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setPosition(LatLng paramLatLng)
  {
    a.b(paramLatLng);
  }
  
  public void setPositionByPixels(int paramInt1, int paramInt2)
  {
    try
    {
      a.a(paramInt1, paramInt2);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "Marker", "setPositionByPixels");
      localRemoteException.printStackTrace();
    }
  }
  
  public void setRotateAngle(float paramFloat)
  {
    try
    {
      a.a(paramFloat);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "Marker", "setRotateAngle");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setSnippet(String paramString)
  {
    a.b(paramString);
  }
  
  public void setTitle(String paramString)
  {
    a.a(paramString);
  }
  
  public void setVisible(boolean paramBoolean)
  {
    a.b(paramBoolean);
  }
  
  public void setZIndex(float paramFloat)
  {
    a.b(paramFloat);
  }
  
  public void showInfoWindow()
  {
    if (a != null) {
      a.i();
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.Marker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */