package android.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public abstract class ICountryListener$Stub
  extends Binder
  implements ICountryListener
{
  private static final String DESCRIPTOR = "android.location.ICountryListener";
  static final int TRANSACTION_onCountryDetected = 1;
  
  public ICountryListener$Stub()
  {
    attachInterface(this, "android.location.ICountryListener");
  }
  
  public static ICountryListener asInterface(IBinder paramIBinder)
  {
    if (paramIBinder == null) {
      return null;
    }
    IInterface localIInterface = paramIBinder.queryLocalInterface("android.location.ICountryListener");
    if ((localIInterface != null) && ((localIInterface instanceof ICountryListener))) {
      return (ICountryListener)localIInterface;
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
      paramParcel2.writeString("android.location.ICountryListener");
      return true;
    }
    paramParcel1.enforceInterface("android.location.ICountryListener");
    if (paramParcel1.readInt() != 0) {}
    for (paramParcel1 = (Country)Country.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
    {
      onCountryDetected(paramParcel1);
      return true;
    }
  }
  
  static class Proxy
    implements ICountryListener
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
      return "android.location.ICountryListener";
    }
    
    /* Error */
    public void onCountryDetected(Country paramCountry)
    {
      // Byte code:
      //   0: invokestatic 34	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_2
      //   4: aload_2
      //   5: ldc 26
      //   7: invokevirtual 38	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   10: aload_1
      //   11: ifnull +33 -> 44
      //   14: aload_2
      //   15: iconst_1
      //   16: invokevirtual 42	android/os/Parcel:writeInt	(I)V
      //   19: aload_1
      //   20: aload_2
      //   21: iconst_0
      //   22: invokevirtual 48	android/location/Country:writeToParcel	(Landroid/os/Parcel;I)V
      //   25: aload_0
      //   26: getfield 19	android/location/ICountryListener$Stub$Proxy:mRemote	Landroid/os/IBinder;
      //   29: iconst_1
      //   30: aload_2
      //   31: aconst_null
      //   32: iconst_1
      //   33: invokeinterface 54 5 0
      //   38: pop
      //   39: aload_2
      //   40: invokevirtual 57	android/os/Parcel:recycle	()V
      //   43: return
      //   44: aload_2
      //   45: iconst_0
      //   46: invokevirtual 42	android/os/Parcel:writeInt	(I)V
      //   49: goto -24 -> 25
      //   52: astore_1
      //   53: aload_2
      //   54: invokevirtual 57	android/os/Parcel:recycle	()V
      //   57: aload_1
      //   58: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	59	0	this	Proxy
      //   0	59	1	paramCountry	Country
      //   3	51	2	localParcel	Parcel
      // Exception table:
      //   from	to	target	type
      //   4	10	52	finally
      //   14	25	52	finally
      //   25	39	52	finally
      //   44	49	52	finally
    }
  }
}

/* Location:
 * Qualified Name:     android.location.ICountryListener.Stub
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */