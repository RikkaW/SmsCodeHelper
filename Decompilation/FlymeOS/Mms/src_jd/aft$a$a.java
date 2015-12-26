import android.os.IBinder;

class aft$a$a
  implements aft
{
  private IBinder a;
  
  aft$a$a(IBinder paramIBinder)
  {
    a = paramIBinder;
  }
  
  /* Error */
  public int a(android.os.Bundle paramBundle)
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
    //   0	101	1	paramBundle	android.os.Bundle
    //   55	25	2	i	int
    //   3	93	3	localParcel1	android.os.Parcel
    //   7	84	4	localParcel2	android.os.Parcel
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

/* Location:
 * Qualified Name:     aft.a.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */