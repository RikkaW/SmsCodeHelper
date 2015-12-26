package com.meizu.account.pay.a;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

final class i
  implements g
{
  private IBinder a;
  
  i(IBinder paramIBinder)
  {
    a = paramIBinder;
  }
  
  public final void a(Bundle paramBundle, d paramd)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    for (;;)
    {
      try
      {
        localParcel1.writeInterfaceToken("com.meizu.account.pay.service.IMzSystemPayService");
        if (paramBundle != null)
        {
          localParcel1.writeInt(1);
          paramBundle.writeToParcel(localParcel1, 0);
          if (paramd != null)
          {
            paramBundle = paramd.asBinder();
            localParcel1.writeStrongBinder(paramBundle);
            a.transact(1, localParcel1, localParcel2, 0);
            localParcel2.readException();
          }
        }
        else
        {
          localParcel1.writeInt(0);
          continue;
        }
        paramBundle = null;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
  }
  
  public final void a(Bundle paramBundle, d paramd, a parama)
  {
    Object localObject = null;
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    for (;;)
    {
      try
      {
        localParcel1.writeInterfaceToken("com.meizu.account.pay.service.IMzSystemPayService");
        if (paramBundle != null)
        {
          localParcel1.writeInt(1);
          paramBundle.writeToParcel(localParcel1, 0);
          if (paramd != null)
          {
            paramBundle = paramd.asBinder();
            localParcel1.writeStrongBinder(paramBundle);
            paramBundle = (Bundle)localObject;
            if (parama != null) {
              paramBundle = parama.asBinder();
            }
            localParcel1.writeStrongBinder(paramBundle);
            a.transact(2, localParcel1, localParcel2, 0);
            localParcel2.readException();
          }
        }
        else
        {
          localParcel1.writeInt(0);
          continue;
        }
        paramBundle = null;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
  }
  
  public final IBinder asBinder()
  {
    return a;
  }
}

/* Location:
 * Qualified Name:     com.meizu.account.pay.a.i
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */