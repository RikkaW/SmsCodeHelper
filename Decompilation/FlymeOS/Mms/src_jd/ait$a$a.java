import android.os.IBinder;
import android.os.Parcel;

class ait$a$a
  implements ait
{
  private IBinder a;
  
  ait$a$a(IBinder paramIBinder)
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

/* Location:
 * Qualified Name:     ait.a.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */