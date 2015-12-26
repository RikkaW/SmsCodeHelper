package com.meizu.account.pay.a;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public abstract class e
  extends Binder
  implements d
{
  public e()
  {
    attachInterface(this, "com.meizu.account.pay.service.IMzSystemPayResponse");
  }
  
  public static d a(IBinder paramIBinder)
  {
    if (paramIBinder == null) {
      return null;
    }
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.meizu.account.pay.service.IMzSystemPayResponse");
    if ((localIInterface != null) && ((localIInterface instanceof d))) {
      return (d)localIInterface;
    }
    return new f(paramIBinder);
  }
  
  public IBinder asBinder()
  {
    return this;
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
  {
    Object localObject2 = null;
    Object localObject1 = null;
    switch (paramInt1)
    {
    default: 
      return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
    case 1598968902: 
      paramParcel2.writeString("com.meizu.account.pay.service.IMzSystemPayResponse");
      return true;
    case 1: 
      paramParcel1.enforceInterface("com.meizu.account.pay.service.IMzSystemPayResponse");
      if (paramParcel1.readInt() != 0) {
        localObject1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
      }
      a((Bundle)localObject1);
      paramParcel2.writeNoException();
      return true;
    case 2: 
      paramParcel1.enforceInterface("com.meizu.account.pay.service.IMzSystemPayResponse");
      a(paramParcel1.readInt(), paramParcel1.readString());
      paramParcel2.writeNoException();
      return true;
    }
    paramParcel1.enforceInterface("com.meizu.account.pay.service.IMzSystemPayResponse");
    localObject1 = localObject2;
    if (paramParcel1.readInt() != 0) {
      localObject1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
    }
    b((Bundle)localObject1);
    paramParcel2.writeNoException();
    return true;
  }
}

/* Location:
 * Qualified Name:     com.meizu.account.pay.a.e
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */