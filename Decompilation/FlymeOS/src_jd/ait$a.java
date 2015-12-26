import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public abstract class ait$a
  extends Binder
  implements ait
{
  public ait$a()
  {
    attachInterface(this, "com.meizu.flymesms.interfaces.IFlymeSMSCallback");
  }
  
  public static ait a(IBinder paramIBinder)
  {
    if (paramIBinder == null) {
      return null;
    }
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.meizu.flymesms.interfaces.IFlymeSMSCallback");
    if ((localIInterface != null) && ((localIInterface instanceof ait))) {
      return (ait)localIInterface;
    }
    return new ait.a.a(paramIBinder);
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
      paramParcel2.writeString("com.meizu.flymesms.interfaces.IFlymeSMSCallback");
      return true;
    }
    paramParcel1.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMSCallback");
    a(paramParcel1.readInt(), paramParcel1.readString());
    paramParcel2.writeNoException();
    return true;
  }
  
  static class a
    implements ait
  {
    private IBinder a;
    
    a(IBinder paramIBinder)
    {
      a = paramIBinder;
    }
    
    public void a(int paramInt, String paramString)
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMSCallback");
        localParcel1.writeInt(paramInt);
        localParcel1.writeString(paramString);
        a.transact(1, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public IBinder asBinder()
    {
      return a;
    }
  }
}

/* Location:
 * Qualified Name:     ait.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */