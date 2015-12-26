package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public abstract interface IPackageInstallObserver
  extends IInterface
{
  public abstract void packageInstalled(String paramString, int paramInt);
  
  public static abstract class Stub
    extends Binder
    implements IPackageInstallObserver
  {
    private static final String DESCRIPTOR = "android.content.pm.IPackageInstallObserver";
    static final int TRANSACTION_packageInstalled = 1;
    
    public Stub()
    {
      attachInterface(this, "android.content.pm.IPackageInstallObserver");
    }
    
    public static IPackageInstallObserver asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("android.content.pm.IPackageInstallObserver");
      if ((localIInterface != null) && ((localIInterface instanceof IPackageInstallObserver))) {
        return (IPackageInstallObserver)localIInterface;
      }
      return new Proxy(paramIBinder);
    }
    
    public IBinder asBinder()
    {
      return this;
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    {
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("android.content.pm.IPackageInstallObserver");
        return true;
      }
      paramParcel1.enforceInterface("android.content.pm.IPackageInstallObserver");
      packageInstalled(paramParcel1.readString(), paramParcel1.readInt());
      return true;
    }
    
    static class Proxy
      implements IPackageInstallObserver
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
  }
}

/* Location:
 * Qualified Name:     android.content.pm.IPackageInstallObserver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */