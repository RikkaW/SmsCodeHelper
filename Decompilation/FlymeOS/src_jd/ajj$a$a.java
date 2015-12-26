import android.os.IBinder;
import android.os.Parcel;
import com.meizu.statsapp.UsageStatsProxy.Event;

class ajj$a$a
  implements ajj
{
  private IBinder a;
  
  ajj$a$a(IBinder paramIBinder)
  {
    a = paramIBinder;
  }
  
  public void a(UsageStatsProxy.Event paramEvent)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.meizu.stats.IUsageStatsManager");
      paramEvent.writeToParcel(localParcel1, 0);
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
  
  /* Error */
  public void a(String paramString1, boolean paramBoolean, String paramString2, long paramLong)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore 6
    //   3: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   6: astore 7
    //   8: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   11: astore 8
    //   13: aload 7
    //   15: ldc 27
    //   17: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
    //   20: aload 7
    //   22: aload_1
    //   23: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
    //   26: iload_2
    //   27: ifeq +55 -> 82
    //   30: aload 7
    //   32: iload 6
    //   34: invokevirtual 57	android/os/Parcel:writeInt	(I)V
    //   37: aload 7
    //   39: aload_3
    //   40: invokevirtual 53	android/os/Parcel:writeString	(Ljava/lang/String;)V
    //   43: aload 7
    //   45: lload 4
    //   47: invokevirtual 61	android/os/Parcel:writeLong	(J)V
    //   50: aload_0
    //   51: getfield 17	ajj$a$a:a	Landroid/os/IBinder;
    //   54: iconst_1
    //   55: aload 7
    //   57: aload 8
    //   59: iconst_0
    //   60: invokeinterface 43 5 0
    //   65: pop
    //   66: aload 8
    //   68: invokevirtual 46	android/os/Parcel:readException	()V
    //   71: aload 8
    //   73: invokevirtual 49	android/os/Parcel:recycle	()V
    //   76: aload 7
    //   78: invokevirtual 49	android/os/Parcel:recycle	()V
    //   81: return
    //   82: iconst_0
    //   83: istore 6
    //   85: goto -55 -> 30
    //   88: astore_1
    //   89: aload 8
    //   91: invokevirtual 49	android/os/Parcel:recycle	()V
    //   94: aload 7
    //   96: invokevirtual 49	android/os/Parcel:recycle	()V
    //   99: aload_1
    //   100: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	101	0	this	a
    //   0	101	1	paramString1	String
    //   0	101	2	paramBoolean	boolean
    //   0	101	3	paramString2	String
    //   0	101	4	paramLong	long
    //   1	83	6	i	int
    //   6	89	7	localParcel1	Parcel
    //   11	79	8	localParcel2	Parcel
    // Exception table:
    //   from	to	target	type
    //   13	26	88	finally
    //   30	71	88	finally
  }
  
  public void a(boolean paramBoolean)
  {
    int i = 0;
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.meizu.stats.IUsageStatsManager");
      if (paramBoolean) {
        i = 1;
      }
      localParcel1.writeInt(i);
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
  
  public void b(UsageStatsProxy.Event paramEvent)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.meizu.stats.IUsageStatsManager");
      paramEvent.writeToParcel(localParcel1, 0);
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
}

/* Location:
 * Qualified Name:     ajj.a.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */