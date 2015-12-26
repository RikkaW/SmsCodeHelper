package com.amap.api.maps2d;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amap.api.mapcore2d.ag;
import com.amap.api.mapcore2d.aj;
import com.amap.api.mapcore2d.bc;
import com.amap.api.mapcore2d.cy;
import com.amap.api.maps2d.model.RuntimeRemoteException;

public class MapFragment
  extends Fragment
{
  private AMap a;
  private aj b;
  
  public static MapFragment newInstance()
  {
    return newInstance(new AMapOptions());
  }
  
  public static MapFragment newInstance(AMapOptions paramAMapOptions)
  {
    MapFragment localMapFragment = new MapFragment();
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("MapOptions", paramAMapOptions);
    localMapFragment.setArguments(localBundle);
    return localMapFragment;
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
        if (a == null) {
          a = new AMap((ag)localObject);
        }
        return a;
      }
      catch (RemoteException localRemoteException)
      {
        cy.a(localRemoteException, "MapFragment", "getMap");
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
  }
  
  protected aj getMapFragmentDelegate()
  {
    if (b == null) {
      b = new bc();
    }
    if (getActivity() != null) {
      b.a(getActivity());
    }
    return b;
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    try
    {
      getMapFragmentDelegate().a(paramBundle);
      return;
    }
    catch (RemoteException paramBundle)
    {
      cy.a(paramBundle, "MapFragment", "onCreate");
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {}
    try
    {
      localBundle = getArguments();
      paramLayoutInflater = getMapFragmentDelegate().a(paramLayoutInflater, paramViewGroup, localBundle);
      return paramLayoutInflater;
    }
    catch (RemoteException paramLayoutInflater)
    {
      cy.a(paramLayoutInflater, "MapFragment", "onCreateView");
    }
    return null;
  }
  
  public void onDestroy()
  {
    try
    {
      getMapFragmentDelegate().e();
      super.onDestroy();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        cy.a(localRemoteException, "MapFragment", "onDestroy");
      }
    }
  }
  
  public void onDestroyView()
  {
    try
    {
      getMapFragmentDelegate().d();
      super.onDestroyView();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        cy.a(localRemoteException, "MapFragment", "onDestroyView");
      }
    }
  }
  
  public void onInflate(Activity paramActivity, AttributeSet paramAttributeSet, Bundle paramBundle)
  {
    super.onInflate(paramActivity, paramAttributeSet, paramBundle);
    try
    {
      getMapFragmentDelegate().a(paramActivity, new AMapOptions(), paramBundle);
      return;
    }
    catch (RemoteException paramActivity)
    {
      cy.a(paramActivity, "MapFragment", "onInflate");
    }
  }
  
  public void onLowMemory()
  {
    super.onLowMemory();
    try
    {
      getMapFragmentDelegate().f();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "MapFragment", "onLowMemory");
    }
  }
  
  public void onPause()
  {
    super.onPause();
    try
    {
      getMapFragmentDelegate().c();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "MapFragment", "onPause");
    }
  }
  
  public void onResume()
  {
    super.onResume();
    try
    {
      getMapFragmentDelegate().b();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "MapFragment", "onResume");
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    try
    {
      getMapFragmentDelegate().b(paramBundle);
      super.onSaveInstanceState(paramBundle);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        cy.a(localRemoteException, "MapFragment", "onSaveInstanceState");
      }
    }
  }
  
  public void setArguments(Bundle paramBundle)
  {
    super.setArguments(paramBundle);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.MapFragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */