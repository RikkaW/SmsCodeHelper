import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public abstract class aiu$a
  extends Binder
  implements aiu
{
  public aiu$a()
  {
    attachInterface(this, "com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
  }
  
  public static aiu a(IBinder paramIBinder)
  {
    if (paramIBinder == null) {
      return null;
    }
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
    if ((localIInterface != null) && ((localIInterface instanceof aiu))) {
      return (aiu)localIInterface;
    }
    return new aiu.a.a(paramIBinder);
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
      paramParcel2.writeString("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
      return true;
    case 1: 
      paramParcel1.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
      a(paramParcel1.readString(), paramParcel1.readLong());
      paramParcel2.writeNoException();
      return true;
    case 2: 
      paramParcel1.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
      a(paramParcel1.readString(), paramParcel1.readLong(), paramParcel1.readLong());
      paramParcel2.writeNoException();
      return true;
    case 3: 
      paramParcel1.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
      a(paramParcel1.readString(), paramParcel1.readString());
      paramParcel2.writeNoException();
      return true;
    case 4: 
      paramParcel1.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
      a(paramParcel1.readString(), paramParcel1.createByteArray());
      paramParcel2.writeNoException();
      return true;
    case 5: 
      paramParcel1.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
      b(paramParcel1.readString());
      paramParcel2.writeNoException();
      return true;
    case 6: 
      paramParcel1.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
      c(paramParcel1.readString());
      paramParcel2.writeNoException();
      return true;
    case 7: 
      paramParcel1.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
      b(paramParcel1.readString(), paramParcel1.readString());
      paramParcel2.writeNoException();
      return true;
    }
    paramParcel1.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
    a(paramParcel1.readString());
    paramParcel2.writeNoException();
    return true;
  }
  
  static class a
    implements aiu
  {
    private IBinder a;
    
    a(IBinder paramIBinder)
    {
      a = paramIBinder;
    }
    
    public void a(String paramString)
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
        localParcel1.writeString(paramString);
        a.transact(8, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public void a(String paramString, long paramLong)
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
        localParcel1.writeString(paramString);
        localParcel1.writeLong(paramLong);
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
    
    public void a(String paramString, long paramLong1, long paramLong2)
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
        localParcel1.writeString(paramString);
        localParcel1.writeLong(paramLong1);
        localParcel1.writeLong(paramLong2);
        a.transact(2, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public void a(String paramString1, String paramString2)
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
        localParcel1.writeString(paramString1);
        localParcel1.writeString(paramString2);
        a.transact(3, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public void a(String paramString, byte[] paramArrayOfByte)
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
        localParcel1.writeString(paramString);
        localParcel1.writeByteArray(paramArrayOfByte);
        a.transact(4, localParcel1, localParcel2, 0);
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
    
    public void b(String paramString)
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
        localParcel1.writeString(paramString);
        a.transact(5, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public void b(String paramString1, String paramString2)
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
        localParcel1.writeString(paramString1);
        localParcel1.writeString(paramString2);
        a.transact(7, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public void c(String paramString)
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMSFileListener");
        localParcel1.writeString(paramString);
        a.transact(6, localParcel1, localParcel2, 0);
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

/* Location:
 * Qualified Name:     aiu.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */