import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public abstract interface aft
  extends IInterface
{
  public abstract int a(Bundle paramBundle);
  
  public static abstract class a
    extends Binder
    implements aft
  {
    public static aft a(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.amap.api.service.locationprovider.ILocationProviderService");
      if ((localIInterface != null) && ((localIInterface instanceof aft))) {
        return (aft)localIInterface;
      }
      return new aft.a.a(paramIBinder);
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    {
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.amap.api.service.locationprovider.ILocationProviderService");
        return true;
      }
      paramParcel1.enforceInterface("com.amap.api.service.locationprovider.ILocationProviderService");
      if (paramParcel1.readInt() != 0)
      {
        paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        paramInt1 = a(paramParcel1);
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        if (paramParcel1 == null) {
          break label109;
        }
        paramParcel2.writeInt(1);
        paramParcel1.writeToParcel(paramParcel2, 1);
      }
      for (;;)
      {
        return true;
        paramParcel1 = null;
        break;
        label109:
        paramParcel2.writeInt(0);
      }
    }
    
    static class a
      implements aft
    {
      private IBinder a;
      
      a(IBinder paramIBinder)
      {
        a = paramIBinder;
      }
      
      /* Error */
      public int a(Bundle paramBundle)
      {
        // Byte code:
        //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 27
        //   12: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +65 -> 81
        //   19: aload_3
        //   20: iconst_1
        //   21: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   24: aload_1
        //   25: aload_3
        //   26: iconst_0
        //   27: invokevirtual 41	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   30: aload_0
        //   31: getfield 17	aft$a$a:a	Landroid/os/IBinder;
        //   34: iconst_1
        //   35: aload_3
        //   36: aload 4
        //   38: iconst_0
        //   39: invokeinterface 47 5 0
        //   44: pop
        //   45: aload 4
        //   47: invokevirtual 50	android/os/Parcel:readException	()V
        //   50: aload 4
        //   52: invokevirtual 54	android/os/Parcel:readInt	()I
        //   55: istore_2
        //   56: aload 4
        //   58: invokevirtual 54	android/os/Parcel:readInt	()I
        //   61: ifeq +9 -> 70
        //   64: aload_1
        //   65: aload 4
        //   67: invokevirtual 58	android/os/Bundle:readFromParcel	(Landroid/os/Parcel;)V
        //   70: aload 4
        //   72: invokevirtual 61	android/os/Parcel:recycle	()V
        //   75: aload_3
        //   76: invokevirtual 61	android/os/Parcel:recycle	()V
        //   79: iload_2
        //   80: ireturn
        //   81: aload_3
        //   82: iconst_0
        //   83: invokevirtual 35	android/os/Parcel:writeInt	(I)V
        //   86: goto -56 -> 30
        //   89: astore_1
        //   90: aload 4
        //   92: invokevirtual 61	android/os/Parcel:recycle	()V
        //   95: aload_3
        //   96: invokevirtual 61	android/os/Parcel:recycle	()V
        //   99: aload_1
        //   100: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	101	0	this	a
        //   0	101	1	paramBundle	Bundle
        //   55	25	2	i	int
        //   3	93	3	localParcel1	Parcel
        //   7	84	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	89	finally
        //   19	30	89	finally
        //   30	70	89	finally
        //   81	86	89	finally
      }
      
      public IBinder asBinder()
      {
        return a;
      }
    }
  }
}

/* Location:
 * Qualified Name:     aft
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */