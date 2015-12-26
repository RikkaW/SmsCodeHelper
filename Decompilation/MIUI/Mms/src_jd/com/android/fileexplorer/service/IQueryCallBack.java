package com.android.fileexplorer.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IQueryCallBack
  extends IInterface
{
  public abstract void onQueryFinish()
    throws RemoteException;
  
  public abstract boolean onQueryItem(String paramString, int paramInt)
    throws RemoteException;
  
  public abstract void onQueryItemEnd(String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract void onStartQuery(int paramInt)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements IQueryCallBack
  {
    public Stub()
    {
      attachInterface(this, "com.android.fileexplorer.service.IQueryCallBack");
    }
    
    public static IQueryCallBack asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.android.fileexplorer.service.IQueryCallBack");
      if ((localIInterface != null) && ((localIInterface instanceof IQueryCallBack))) {
        return (IQueryCallBack)localIInterface;
      }
      return new Proxy(paramIBinder);
    }
    
    public IBinder asBinder()
    {
      return this;
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.android.fileexplorer.service.IQueryCallBack");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.android.fileexplorer.service.IQueryCallBack");
        onStartQuery(paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.android.fileexplorer.service.IQueryCallBack");
        boolean bool = onQueryItem(paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        if (bool) {}
        for (paramInt1 = 1;; paramInt1 = 0)
        {
          paramParcel2.writeInt(paramInt1);
          return true;
        }
      case 3: 
        paramParcel1.enforceInterface("com.android.fileexplorer.service.IQueryCallBack");
        onQueryItemEnd(paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      }
      paramParcel1.enforceInterface("com.android.fileexplorer.service.IQueryCallBack");
      onQueryFinish();
      paramParcel2.writeNoException();
      return true;
    }
    
    private static class Proxy
      implements IQueryCallBack
    {
      private IBinder mRemote;
      
      Proxy(IBinder paramIBinder)
      {
        mRemote = paramIBinder;
      }
      
      public IBinder asBinder()
      {
        return mRemote;
      }
      
      public void onQueryFinish()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.android.fileexplorer.service.IQueryCallBack");
          mRemote.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public boolean onQueryItem(String paramString, int paramInt)
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.android.fileexplorer.service.IQueryCallBack");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          mRemote.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt = localParcel2.readInt();
          if (paramInt != 0) {
            bool = true;
          }
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void onQueryItemEnd(String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.android.fileexplorer.service.IQueryCallBack");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          mRemote.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void onStartQuery(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.android.fileexplorer.service.IQueryCallBack");
          localParcel1.writeInt(paramInt);
          mRemote.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.fileexplorer.service.IQueryCallBack
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */