package com.meizu.account.pay.a;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public abstract class h
  extends Binder
  implements g
{
  public static g a(IBinder paramIBinder)
  {
    if (paramIBinder == null) {
      return null;
    }
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.meizu.account.pay.service.IMzSystemPayService");
    if ((localIInterface != null) && ((localIInterface instanceof g))) {
      return (g)localIInterface;
    }
    return new i(paramIBinder);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
  {
    IInterface localIInterface = null;
    Bundle localBundle;
    switch (paramInt1)
    {
    default: 
      return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
    case 1598968902: 
      paramParcel2.writeString("com.meizu.account.pay.service.IMzSystemPayService");
      return true;
    case 1: 
      paramParcel1.enforceInterface("com.meizu.account.pay.service.IMzSystemPayService");
      if (paramParcel1.readInt() != 0) {}
      for (localBundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; localBundle = null)
      {
        a(localBundle, e.a(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      }
    }
    paramParcel1.enforceInterface("com.meizu.account.pay.service.IMzSystemPayService");
    d locald;
    if (paramParcel1.readInt() != 0)
    {
      localBundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
      locald = e.a(paramParcel1.readStrongBinder());
      paramParcel1 = paramParcel1.readStrongBinder();
      if (paramParcel1 != null) {
        break label179;
      }
      paramParcel1 = localIInterface;
    }
    for (;;)
    {
      a(localBundle, locald, paramParcel1);
      paramParcel2.writeNoException();
      return true;
      localBundle = null;
      break;
      label179:
      localIInterface = paramParcel1.queryLocalInterface("com.meizu.account.pay.service.IMzSystemPayBridge");
      if ((localIInterface != null) && ((localIInterface instanceof a))) {
        paramParcel1 = (a)localIInterface;
      } else {
        paramParcel1 = new c(paramParcel1);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.account.pay.a.h
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */