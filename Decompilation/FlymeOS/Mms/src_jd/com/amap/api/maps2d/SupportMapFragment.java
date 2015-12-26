package com.amap.api.maps2d;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amap.api.mapcore2d.ag;
import com.amap.api.mapcore2d.aj;
import com.amap.api.mapcore2d.bc;
import com.amap.api.mapcore2d.cy;
import com.amap.api.maps2d.model.RuntimeRemoteException;

public class SupportMapFragment
  extends Fragment
{
  private AMap a;
  private aj b;
  
  public static SupportMapFragment newInstance()
  {
    return newInstance(new AMapOptions());
  }
  
  public static SupportMapFragment newInstance(AMapOptions paramAMapOptions)
  {
    SupportMapFragment localSupportMapFragment = new SupportMapFragment();
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("MapOptions", paramAMapOptions);
    localSupportMapFragment.setArguments(localBundle);
    return localSupportMapFragment;
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
        cy.a(localRemoteException, "SupportMapFragment", "getMap");
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
  }
  
  protected aj getMapFragmentDelegate()
  {
    if (b == null) {
      b = new bc();
    }
    b.a(getActivity());
    return b;
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
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
      cy.a(paramLayoutInflater, "SupportMapFragment", "onCreateView");
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
        cy.a(localRemoteException, "SupportMapFragment", "onDestroy");
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
        cy.a(localRemoteException, "SupportMapFragment", "onDestroyView");
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
      cy.a(paramActivity, "SupportMapFragment", "onInflate");
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
      cy.a(localRemoteException, "SupportMapFragment", "onLowMemory");
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
      cy.a(localRemoteException, "SupportMapFragment", "onPause");
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
      cy.a(localRemoteException, "SupportMapFragment", "onResume");
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
        cy.a(localRemoteException, "SupportMapFragment", "onSaveInstanceState");
      }
    }
  }
  
  public void setArguments(Bundle paramBundle)
  {
    super.setArguments(paramBundle);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.SupportMapFragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */