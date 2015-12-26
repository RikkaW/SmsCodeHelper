package android.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public abstract class ICountryDetector$Stub
  extends Binder
  implements ICountryDetector
{
  private static final String DESCRIPTOR = "android.location.ICountryDetector";
  static final int TRANSACTION_addCountryListener = 2;
  static final int TRANSACTION_detectCountry = 1;
  static final int TRANSACTION_removeCountryListener = 3;
  
  public ICountryDetector$Stub()
  {
    attachInterface(this, "android.location.ICountryDetector");
  }
  
  public static ICountryDetector asInterface(IBinder paramIBinder)
  {
    if (paramIBinder == null) {
      return null;
    }
    IInterface localIInterface = paramIBinder.queryLocalInterface("android.location.ICountryDetector");
    if ((localIInterface != null) && ((localIInterface instanceof ICountryDetector))) {
      return (ICountryDetector)localIInterface;
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
      paramParcel2.writeString("android.location.ICountryDetector");
      return true;
    case 1: 
      paramParcel1.enforceInterface("android.location.ICountryDetector");
      paramParcel1 = detectCountry();
      paramParcel2.writeNoException();
      if (paramParcel1 != null)
      {
        paramParcel2.writeInt(1);
        paramParcel1.writeToParcel(paramParcel2, 1);
        return true;
      }
      paramParcel2.writeInt(0);
      return true;
    case 2: 
      paramParcel1.enforceInterface("android.location.ICountryDetector");
      addCountryListener(ICountryListener.Stub.asInterface(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    }
    paramParcel1.enforceInterface("android.location.ICountryDetector");
    removeCountryListener(ICountryListener.Stub.asInterface(paramParcel1.readStrongBinder()));
    paramParcel2.writeNoException();
    return true;
  }
  
  static class Proxy
    implements ICountryDetector
  {
    private IBinder mRemote;
    
    Proxy(IBinder paramIBinder)
    {
      mRemote = paramIBinder;
    }
    
    /* Error */
    public void addCountryListener(ICountryListener paramICountryListener)
    {
      // Byte code:
      //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_2
      //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   7: astore_3
      //   8: aload_2
      //   9: ldc 30
      //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   14: aload_1
      //   15: ifnull +42 -> 57
      //   18: aload_1
      //   19: invokeinterface 40 1 0
      //   24: astore_1
      //   25: aload_2
      //   26: aload_1
      //   27: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
      //   30: aload_0
      //   31: getfield 19	android/location/ICountryDetector$Stub$Proxy:mRemote	Landroid/os/IBinder;
      //   34: iconst_2
      //   35: aload_2
      //   36: aload_3
      //   37: iconst_0
      //   38: invokeinterface 49 5 0
      //   43: pop
      //   44: aload_3
      //   45: invokevirtual 52	android/os/Parcel:readException	()V
      //   48: aload_3
      //   49: invokevirtual 55	android/os/Parcel:recycle	()V
      //   52: aload_2
      //   53: invokevirtual 55	android/os/Parcel:recycle	()V
      //   56: return
      //   57: aconst_null
      //   58: astore_1
      //   59: goto -34 -> 25
      //   62: astore_1
      //   63: aload_3
      //   64: invokevirtual 55	android/os/Parcel:recycle	()V
      //   67: aload_2
      //   68: invokevirtual 55	android/os/Parcel:recycle	()V
      //   71: aload_1
      //   72: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	73	0	this	Proxy
      //   0	73	1	paramICountryListener	ICountryListener
      //   3	65	2	localParcel1	Parcel
      //   7	57	3	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   8	14	62	finally
      //   18	25	62	finally
      //   25	48	62	finally
    }
    
    public IBinder asBinder()
    {
      return mRemote;
    }
    
    /* Error */
    public Country detectCountry()
    {
      // Byte code:
      //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_2
      //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   7: astore_3
      //   8: aload_2
      //   9: ldc 30
      //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   14: aload_0
      //   15: getfield 19	android/location/ICountryDetector$Stub$Proxy:mRemote	Landroid/os/IBinder;
      //   18: iconst_1
      //   19: aload_2
      //   20: aload_3
      //   21: iconst_0
      //   22: invokeinterface 49 5 0
      //   27: pop
      //   28: aload_3
      //   29: invokevirtual 52	android/os/Parcel:readException	()V
      //   32: aload_3
      //   33: invokevirtual 61	android/os/Parcel:readInt	()I
      //   36: ifeq +26 -> 62
      //   39: getstatic 67	android/location/Country:CREATOR	Landroid/os/Parcelable$Creator;
      //   42: aload_3
      //   43: invokeinterface 73 2 0
      //   48: checkcast 63	android/location/Country
      //   51: astore_1
      //   52: aload_3
      //   53: invokevirtual 55	android/os/Parcel:recycle	()V
      //   56: aload_2
      //   57: invokevirtual 55	android/os/Parcel:recycle	()V
      //   60: aload_1
      //   61: areturn
      //   62: aconst_null
      //   63: astore_1
      //   64: goto -12 -> 52
      //   67: astore_1
      //   68: aload_3
      //   69: invokevirtual 55	android/os/Parcel:recycle	()V
      //   72: aload_2
      //   73: invokevirtual 55	android/os/Parcel:recycle	()V
      //   76: aload_1
      //   77: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	78	0	this	Proxy
      //   51	13	1	localCountry	Country
      //   67	10	1	localObject	Object
      //   3	70	2	localParcel1	Parcel
      //   7	62	3	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   8	52	67	finally
    }
    
    public String getInterfaceDescriptor()
    {
      return "android.location.ICountryDetector";
    }
    
    /* Error */
    public void removeCountryListener(ICountryListener paramICountryListener)
    {
      // Byte code:
      //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_2
      //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   7: astore_3
      //   8: aload_2
      //   9: ldc 30
      //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   14: aload_1
      //   15: ifnull +42 -> 57
      //   18: aload_1
      //   19: invokeinterface 40 1 0
      //   24: astore_1
      //   25: aload_2
      //   26: aload_1
      //   27: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
      //   30: aload_0
      //   31: getfield 19	android/location/ICountryDetector$Stub$Proxy:mRemote	Landroid/os/IBinder;
      //   34: iconst_3
      //   35: aload_2
      //   36: aload_3
      //   37: iconst_0
      //   38: invokeinterface 49 5 0
      //   43: pop
      //   44: aload_3
      //   45: invokevirtual 52	android/os/Parcel:readException	()V
      //   48: aload_3
      //   49: invokevirtual 55	android/os/Parcel:recycle	()V
      //   52: aload_2
      //   53: invokevirtual 55	android/os/Parcel:recycle	()V
      //   56: return
      //   57: aconst_null
      //   58: astore_1
      //   59: goto -34 -> 25
      //   62: astore_1
      //   63: aload_3
      //   64: invokevirtual 55	android/os/Parcel:recycle	()V
      //   67: aload_2
      //   68: invokevirtual 55	android/os/Parcel:recycle	()V
      //   71: aload_1
      //   72: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	73	0	this	Proxy
      //   0	73	1	paramICountryListener	ICountryListener
      //   3	65	2	localParcel1	Parcel
      //   7	57	3	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   8	14	62	finally
      //   18	25	62	finally
      //   25	48	62	finally
    }
  }
}

/* Location:
 * Qualified Name:     android.location.ICountryDetector.Stub
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */