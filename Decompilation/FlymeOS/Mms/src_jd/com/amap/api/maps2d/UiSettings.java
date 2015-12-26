package com.amap.api.maps2d;

import android.os.RemoteException;
import com.amap.api.mapcore2d.au;
import com.amap.api.mapcore2d.cy;
import com.amap.api.maps2d.model.RuntimeRemoteException;

public final class UiSettings
{
  private final au a;
  
  UiSettings(au paramau)
  {
    a = paramau;
  }
  
  public int getLogoPosition()
  {
    try
    {
      int i = a.g();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "UiSettings", "getLogoPosition");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public int getZoomPosition()
  {
    try
    {
      int i = a.h();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "UiSettings", "getZoomPosition");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public boolean isCompassEnabled()
  {
    try
    {
      boolean bool = a.c();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "UiSettings", "isCompassEnabled");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public boolean isMyLocationButtonEnabled()
  {
    try
    {
      boolean bool = a.d();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "UiSettings", "isMyLocationButtonEnabled");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public boolean isScaleControlsEnabled()
  {
    try
    {
      boolean bool = a.a();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "UiSettings", "isScaleControlsEnabled");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public boolean isScrollGesturesEnabled()
  {
    try
    {
      boolean bool = a.e();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "UiSettings", "isScrollGestureEnabled");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public boolean isZoomControlsEnabled()
  {
    try
    {
      boolean bool = a.b();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "UiSettings", "isZoomControlsEnabled");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public boolean isZoomGesturesEnabled()
  {
    try
    {
      boolean bool = a.f();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "UiSettings", "isZoomGesturesEnabled");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setAllGesturesEnabled(boolean paramBoolean)
  {
    try
    {
      a.g(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "UiSettings", "setAllGesturesEnabled");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setCompassEnabled(boolean paramBoolean)
  {
    try
    {
      a.c(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "UiSettings", "setCompassEnabled");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setLogoPosition(int paramInt)
  {
    try
    {
      a.a(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "UiSettings", "setLogoPosition");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setMyLocationButtonEnabled(boolean paramBoolean)
  {
    try
    {
      a.d(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "UiSettings", "setMyLocationButtonEnabled");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setScaleControlsEnabled(boolean paramBoolean)
  {
    try
    {
      a.a(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "UiSettings", "setScaleControlsEnabled");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setScrollGesturesEnabled(boolean paramBoolean)
  {
    try
    {
      a.e(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "UiSettings", "setScrollGesturesEnabled");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setZoomControlsEnabled(boolean paramBoolean)
  {
    try
    {
      a.b(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "UiSettings", "setZoomControlsEnabled");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setZoomGesturesEnabled(boolean paramBoolean)
  {
    try
    {
      a.f(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "UiSettings", "setZoomGesturesEnabled");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setZoomPosition(int paramInt)
  {
    try
    {
      a.b(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "UiSettings", "setZoomPosition");
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.UiSettings
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */