package com.amap.api.maps2d.model;

import android.os.RemoteException;

public final class RuntimeRemoteException
  extends RuntimeException
{
  public RuntimeRemoteException(RemoteException paramRemoteException)
  {
    super(paramRemoteException);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.RuntimeRemoteException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */