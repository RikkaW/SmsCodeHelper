package com.amap.api.maps2d;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.amap.api.mapcore2d.ag;
import com.amap.api.mapcore2d.aj;
import com.amap.api.mapcore2d.bc;
import com.amap.api.mapcore2d.cy;
import com.amap.api.maps2d.model.RuntimeRemoteException;

public class MapView
  extends FrameLayout
{
  private aj a;
  private AMap b;
  
  public MapView(Context paramContext)
  {
    super(paramContext);
    getMapFragmentDelegate().a(paramContext);
  }
  
  public MapView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    getMapFragmentDelegate().a(paramContext);
  }
  
  public MapView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    getMapFragmentDelegate().a(paramContext);
  }
  
  public MapView(Context paramContext, AMapOptions paramAMapOptions)
  {
    super(paramContext);
    getMapFragmentDelegate().a(paramContext);
    getMapFragmentDelegate().a(paramAMapOptions);
  }
  
  public AMap getMap()
  {
    Object localObject = getMapFragmentDelegate();
    if (localObject == null) {}
    for (;;)
    {
      return null;
      try
      {
        localObject = ((aj)localObject).a();
        if (localObject == null) {
          continue;
        }
        if (b == null) {
          b = new AMap((ag)localObject);
        }
        return b;
      }
      catch (RemoteException localRemoteException)
      {
        cy.a(localRemoteException, "MapView", "getMap");
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
  }
  
  protected aj getMapFragmentDelegate()
  {
    if (a == null) {
      a = new bc();
    }
    return a;
  }
  
  public final void onCreate(Bundle paramBundle)
  {
    try
    {
      addView(getMapFragmentDelegate().a(null, null, paramBundle), new ViewGroup.LayoutParams(-1, -1));
      return;
    }
    catch (RemoteException paramBundle)
    {
      cy.a(paramBundle, "MapView", "onCreate");
    }
  }
  
  public final void onDestroy()
  {
    try
    {
      getMapFragmentDelegate().e();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "MapView", "onDestroy");
    }
  }
  
  public final void onLowMemory()
  {
    try
    {
      getMapFragmentDelegate().f();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "MapView", "onLowMemory");
    }
  }
  
  public final void onPause()
  {
    try
    {
      getMapFragmentDelegate().c();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "MapView", "onPause");
    }
  }
  
  public final void onResume()
  {
    try
    {
      getMapFragmentDelegate().b();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "MapView", "onResume");
    }
  }
  
  public final void onSaveInstanceState(Bundle paramBundle)
  {
    try
    {
      getMapFragmentDelegate().b(paramBundle);
      return;
    }
    catch (RemoteException paramBundle)
    {
      cy.a(paramBundle, "MapView", "onSaveInstanceState");
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.MapView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */