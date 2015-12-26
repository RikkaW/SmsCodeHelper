package android.content.pm;

import android.os.IBinder;
import android.os.Parcel;

class IPackageInstallObserver$Stub$Proxy
  implements IPackageInstallObserver
{
  private IBinder mRemote;
  
  IPackageInstallObserver$Stub$Proxy(IBinder paramIBinder)
  {
    mRemote = paramIBinder;
  }
  
  public IBinder asBinder()
  {
    return mRemote;
  }
  
  public String getInterfaceDescriptor()
  {
    return "android.content.pm.IPackageInstallObserver";
  }
  
  public void packageInstalled(String paramString, int paramInt)
  {
    Parcel localParcel = Parcel.obtain();
    try
    {
      localParcel.writeInterfaceToken("android.content.pm.IPackageInstallObserver");
      localParcel.writeString(paramString);
      localParcel.writeInt(paramInt);
      mRemote.transact(1, localParcel, null, 1);
      return;
    }
    finally
    {
      localParcel.recycle();
    }
  }
}

/* Location:
 * Qualified Name:     android.content.pm.IPackageInstallObserver.Stub.Proxy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */