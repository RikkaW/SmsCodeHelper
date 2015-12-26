import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public abstract class ais$a
  extends Binder
  implements ais
{
  public static ais a(IBinder paramIBinder)
  {
    if (paramIBinder == null) {
      return null;
    }
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.meizu.flymesms.interfaces.IFlymeSMS");
    if ((localIInterface != null) && ((localIInterface instanceof ais))) {
      return (ais)localIInterface;
    }
    return new ais.a.a(paramIBinder);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
  {
    Object localObject1 = null;
    boolean bool2 = false;
    boolean bool1 = false;
    String str1;
    Object localObject2;
    String str2;
    switch (paramInt1)
    {
    default: 
      return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
    case 1598968902: 
      paramParcel2.writeString("com.meizu.flymesms.interfaces.IFlymeSMS");
      return true;
    case 1: 
      paramParcel1.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMS");
      str1 = paramParcel1.readString();
      localObject2 = paramParcel1.readString();
      if (paramParcel1.readInt() != 0) {
        localObject1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);
      }
      str2 = paramParcel1.readString();
      if (paramParcel1.readInt() != 0) {
        bool1 = true;
      }
      a(str1, (String)localObject2, (PendingIntent)localObject1, str2, bool1);
      paramParcel2.writeNoException();
      return true;
    case 2: 
      paramParcel1.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMS");
      a(ait.a.a(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    case 3: 
      paramParcel1.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMS");
      b(ait.a.a(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    case 4: 
      paramParcel1.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMS");
      a(paramParcel1.readString(), aiu.a.a(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    case 5: 
      paramParcel1.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMS");
      b(paramParcel1.readString(), aiu.a.a(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    case 6: 
      paramParcel1.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMS");
      localObject1 = paramParcel1.createStringArray();
      str1 = paramParcel1.readString();
      localObject2 = paramParcel1.createByteArray();
      str2 = paramParcel1.readString();
      bool1 = bool2;
      if (paramParcel1.readInt() != 0) {
        bool1 = true;
      }
      a((String[])localObject1, str1, (byte[])localObject2, str2, bool1, paramParcel1.readString());
      paramParcel2.writeNoException();
      return true;
    case 7: 
      paramParcel1.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMS");
      a(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readLong());
      paramParcel2.writeNoException();
      return true;
    case 8: 
      paramParcel1.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMS");
      a(paramParcel1.readString());
      paramParcel2.writeNoException();
      return true;
    }
    paramParcel1.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMS");
    localObject1 = paramParcel1.createStringArray();
    if (paramParcel1.readInt() != 0) {}
    for (paramParcel1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
    {
      a((String[])localObject1, paramParcel1);
      paramParcel2.writeNoException();
      return true;
    }
  }
  
  static class a
    implements ais
  {
    private IBinder a;
    
    a(IBinder paramIBinder)
    {
      a = paramIBinder;
    }
    
    /* Error */
    public void a(ait paramait)
    {
      // Byte code:
      //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_2
      //   4: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   7: astore_3
      //   8: aload_2
      //   9: ldc 27
      //   11: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   14: aload_1
      //   15: ifnull +42 -> 57
      //   18: aload_1
      //   19: invokeinterface 37 1 0
      //   24: astore_1
      //   25: aload_2
      //   26: aload_1
      //   27: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
      //   30: aload_0
      //   31: getfield 17	ais$a$a:a	Landroid/os/IBinder;
      //   34: iconst_2
      //   35: aload_2
      //   36: aload_3
      //   37: iconst_0
      //   38: invokeinterface 46 5 0
      //   43: pop
      //   44: aload_3
      //   45: invokevirtual 49	android/os/Parcel:readException	()V
      //   48: aload_3
      //   49: invokevirtual 52	android/os/Parcel:recycle	()V
      //   52: aload_2
      //   53: invokevirtual 52	android/os/Parcel:recycle	()V
      //   56: return
      //   57: aconst_null
      //   58: astore_1
      //   59: goto -34 -> 25
      //   62: astore_1
      //   63: aload_3
      //   64: invokevirtual 52	android/os/Parcel:recycle	()V
      //   67: aload_2
      //   68: invokevirtual 52	android/os/Parcel:recycle	()V
      //   71: aload_1
      //   72: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	73	0	this	a
      //   0	73	1	paramait	ait
      //   3	65	2	localParcel1	Parcel
      //   7	57	3	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   8	14	62	finally
      //   18	25	62	finally
      //   25	48	62	finally
    }
    
    public void a(String paramString)
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMS");
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
    
    /* Error */
    public void a(String paramString, aiu paramaiu)
    {
      // Byte code:
      //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_3
      //   4: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   7: astore 4
      //   9: aload_3
      //   10: ldc 27
      //   12: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   15: aload_3
      //   16: aload_1
      //   17: invokevirtual 55	android/os/Parcel:writeString	(Ljava/lang/String;)V
      //   20: aload_2
      //   21: ifnull +45 -> 66
      //   24: aload_2
      //   25: invokeinterface 59 1 0
      //   30: astore_1
      //   31: aload_3
      //   32: aload_1
      //   33: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
      //   36: aload_0
      //   37: getfield 17	ais$a$a:a	Landroid/os/IBinder;
      //   40: iconst_4
      //   41: aload_3
      //   42: aload 4
      //   44: iconst_0
      //   45: invokeinterface 46 5 0
      //   50: pop
      //   51: aload 4
      //   53: invokevirtual 49	android/os/Parcel:readException	()V
      //   56: aload 4
      //   58: invokevirtual 52	android/os/Parcel:recycle	()V
      //   61: aload_3
      //   62: invokevirtual 52	android/os/Parcel:recycle	()V
      //   65: return
      //   66: aconst_null
      //   67: astore_1
      //   68: goto -37 -> 31
      //   71: astore_1
      //   72: aload 4
      //   74: invokevirtual 52	android/os/Parcel:recycle	()V
      //   77: aload_3
      //   78: invokevirtual 52	android/os/Parcel:recycle	()V
      //   81: aload_1
      //   82: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	83	0	this	a
      //   0	83	1	paramString	String
      //   0	83	2	paramaiu	aiu
      //   3	75	3	localParcel1	Parcel
      //   7	66	4	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   9	20	71	finally
      //   24	31	71	finally
      //   31	56	71	finally
    }
    
    public void a(String paramString1, String paramString2, PendingIntent paramPendingIntent, String paramString3, boolean paramBoolean)
    {
      int i = 1;
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      for (;;)
      {
        try
        {
          localParcel1.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMS");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          if (paramPendingIntent != null)
          {
            localParcel1.writeInt(1);
            paramPendingIntent.writeToParcel(localParcel1, 0);
            localParcel1.writeString(paramString3);
            if (paramBoolean)
            {
              localParcel1.writeInt(i);
              a.transact(1, localParcel1, localParcel2, 0);
              localParcel2.readException();
            }
          }
          else
          {
            localParcel1.writeInt(0);
            continue;
          }
          i = 0;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
    }
    
    public void a(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMS");
        localParcel1.writeString(paramString1);
        localParcel1.writeString(paramString2);
        localParcel1.writeString(paramString3);
        localParcel1.writeString(paramString4);
        localParcel1.writeLong(paramLong);
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
    
    /* Error */
    public void a(String[] paramArrayOfString, PendingIntent paramPendingIntent)
    {
      // Byte code:
      //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_3
      //   4: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   7: astore 4
      //   9: aload_3
      //   10: ldc 27
      //   12: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   15: aload_3
      //   16: aload_1
      //   17: invokevirtual 80	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
      //   20: aload_2
      //   21: ifnull +45 -> 66
      //   24: aload_3
      //   25: iconst_1
      //   26: invokevirtual 64	android/os/Parcel:writeInt	(I)V
      //   29: aload_2
      //   30: aload_3
      //   31: iconst_0
      //   32: invokevirtual 70	android/app/PendingIntent:writeToParcel	(Landroid/os/Parcel;I)V
      //   35: aload_0
      //   36: getfield 17	ais$a$a:a	Landroid/os/IBinder;
      //   39: bipush 9
      //   41: aload_3
      //   42: aload 4
      //   44: iconst_0
      //   45: invokeinterface 46 5 0
      //   50: pop
      //   51: aload 4
      //   53: invokevirtual 49	android/os/Parcel:readException	()V
      //   56: aload 4
      //   58: invokevirtual 52	android/os/Parcel:recycle	()V
      //   61: aload_3
      //   62: invokevirtual 52	android/os/Parcel:recycle	()V
      //   65: return
      //   66: aload_3
      //   67: iconst_0
      //   68: invokevirtual 64	android/os/Parcel:writeInt	(I)V
      //   71: goto -36 -> 35
      //   74: astore_1
      //   75: aload 4
      //   77: invokevirtual 52	android/os/Parcel:recycle	()V
      //   80: aload_3
      //   81: invokevirtual 52	android/os/Parcel:recycle	()V
      //   84: aload_1
      //   85: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	86	0	this	a
      //   0	86	1	paramArrayOfString	String[]
      //   0	86	2	paramPendingIntent	PendingIntent
      //   3	78	3	localParcel1	Parcel
      //   7	69	4	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   9	20	74	finally
      //   24	35	74	finally
      //   35	56	74	finally
      //   66	71	74	finally
    }
    
    public void a(String[] paramArrayOfString, String paramString1, byte[] paramArrayOfByte, String paramString2, boolean paramBoolean, String paramString3)
    {
      int i = 0;
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMS");
        localParcel1.writeStringArray(paramArrayOfString);
        localParcel1.writeString(paramString1);
        localParcel1.writeByteArray(paramArrayOfByte);
        localParcel1.writeString(paramString2);
        if (paramBoolean) {
          i = 1;
        }
        localParcel1.writeInt(i);
        localParcel1.writeString(paramString3);
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
    
    public IBinder asBinder()
    {
      return a;
    }
    
    /* Error */
    public void b(ait paramait)
    {
      // Byte code:
      //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_2
      //   4: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   7: astore_3
      //   8: aload_2
      //   9: ldc 27
      //   11: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   14: aload_1
      //   15: ifnull +42 -> 57
      //   18: aload_1
      //   19: invokeinterface 37 1 0
      //   24: astore_1
      //   25: aload_2
      //   26: aload_1
      //   27: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
      //   30: aload_0
      //   31: getfield 17	ais$a$a:a	Landroid/os/IBinder;
      //   34: iconst_3
      //   35: aload_2
      //   36: aload_3
      //   37: iconst_0
      //   38: invokeinterface 46 5 0
      //   43: pop
      //   44: aload_3
      //   45: invokevirtual 49	android/os/Parcel:readException	()V
      //   48: aload_3
      //   49: invokevirtual 52	android/os/Parcel:recycle	()V
      //   52: aload_2
      //   53: invokevirtual 52	android/os/Parcel:recycle	()V
      //   56: return
      //   57: aconst_null
      //   58: astore_1
      //   59: goto -34 -> 25
      //   62: astore_1
      //   63: aload_3
      //   64: invokevirtual 52	android/os/Parcel:recycle	()V
      //   67: aload_2
      //   68: invokevirtual 52	android/os/Parcel:recycle	()V
      //   71: aload_1
      //   72: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	73	0	this	a
      //   0	73	1	paramait	ait
      //   3	65	2	localParcel1	Parcel
      //   7	57	3	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   8	14	62	finally
      //   18	25	62	finally
      //   25	48	62	finally
    }
    
    /* Error */
    public void b(String paramString, aiu paramaiu)
    {
      // Byte code:
      //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_3
      //   4: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   7: astore 4
      //   9: aload_3
      //   10: ldc 27
      //   12: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   15: aload_3
      //   16: aload_1
      //   17: invokevirtual 55	android/os/Parcel:writeString	(Ljava/lang/String;)V
      //   20: aload_2
      //   21: ifnull +45 -> 66
      //   24: aload_2
      //   25: invokeinterface 59 1 0
      //   30: astore_1
      //   31: aload_3
      //   32: aload_1
      //   33: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
      //   36: aload_0
      //   37: getfield 17	ais$a$a:a	Landroid/os/IBinder;
      //   40: iconst_5
      //   41: aload_3
      //   42: aload 4
      //   44: iconst_0
      //   45: invokeinterface 46 5 0
      //   50: pop
      //   51: aload 4
      //   53: invokevirtual 49	android/os/Parcel:readException	()V
      //   56: aload 4
      //   58: invokevirtual 52	android/os/Parcel:recycle	()V
      //   61: aload_3
      //   62: invokevirtual 52	android/os/Parcel:recycle	()V
      //   65: return
      //   66: aconst_null
      //   67: astore_1
      //   68: goto -37 -> 31
      //   71: astore_1
      //   72: aload 4
      //   74: invokevirtual 52	android/os/Parcel:recycle	()V
      //   77: aload_3
      //   78: invokevirtual 52	android/os/Parcel:recycle	()V
      //   81: aload_1
      //   82: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	83	0	this	a
      //   0	83	1	paramString	String
      //   0	83	2	paramaiu	aiu
      //   3	75	3	localParcel1	Parcel
      //   7	66	4	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   9	20	71	finally
      //   24	31	71	finally
      //   31	56	71	finally
    }
  }
}

/* Location:
 * Qualified Name:     ais.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */