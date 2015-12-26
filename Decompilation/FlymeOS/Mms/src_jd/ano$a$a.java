import android.os.IBinder;
import android.os.Parcel;

class ano$a$a
  implements ano
{
  private IBinder a;
  
  ano$a$a(IBinder paramIBinder)
  {
    a = paramIBinder;
  }
  
  public void a(IBinder paramIBinder)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
      localParcel1.writeStrongBinder(paramIBinder);
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
  
  public void a(String paramString)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
      localParcel1.writeString(paramString);
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
  
  public void a(String paramString, int paramInt)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
      localParcel1.writeString(paramString);
      localParcel1.writeInt(paramInt);
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
  
  public void a(String paramString, IBinder paramIBinder)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
      localParcel1.writeString(paramString);
      localParcel1.writeStrongBinder(paramIBinder);
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
  
  /* Error */
  public void a(String paramString, ann paramann)
  {
    // Byte code:
    //   0: invokestatic 24	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   3: astore_3
    //   4: invokestatic 24	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   7: astore 4
    //   9: aload_3
    //   10: ldc 26
    //   12: invokevirtual 30	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
    //   15: aload_3
    //   16: aload_1
    //   17: invokevirtual 48	android/os/Parcel:writeString	(Ljava/lang/String;)V
    //   20: aload_2
    //   21: ifnull +45 -> 66
    //   24: aload_2
    //   25: invokeinterface 61 1 0
    //   30: astore_1
    //   31: aload_3
    //   32: aload_1
    //   33: invokevirtual 33	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
    //   36: aload_0
    //   37: getfield 17	ano$a$a:a	Landroid/os/IBinder;
    //   40: iconst_2
    //   41: aload_3
    //   42: aload 4
    //   44: iconst_0
    //   45: invokeinterface 39 5 0
    //   50: pop
    //   51: aload 4
    //   53: invokevirtual 42	android/os/Parcel:readException	()V
    //   56: aload 4
    //   58: invokevirtual 45	android/os/Parcel:recycle	()V
    //   61: aload_3
    //   62: invokevirtual 45	android/os/Parcel:recycle	()V
    //   65: return
    //   66: aconst_null
    //   67: astore_1
    //   68: goto -37 -> 31
    //   71: astore_1
    //   72: aload 4
    //   74: invokevirtual 45	android/os/Parcel:recycle	()V
    //   77: aload_3
    //   78: invokevirtual 45	android/os/Parcel:recycle	()V
    //   81: aload_1
    //   82: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	83	0	this	a
    //   0	83	1	paramString	String
    //   0	83	2	paramann	ann
    //   3	75	3	localParcel1	Parcel
    //   7	66	4	localParcel2	Parcel
    // Exception table:
    //   from	to	target	type
    //   9	20	71	finally
    //   24	31	71	finally
    //   31	56	71	finally
  }
  
  /* Error */
  public void a(String paramString, anp paramanp)
  {
    // Byte code:
    //   0: invokestatic 24	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   3: astore_3
    //   4: invokestatic 24	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   7: astore 4
    //   9: aload_3
    //   10: ldc 26
    //   12: invokevirtual 30	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
    //   15: aload_3
    //   16: aload_1
    //   17: invokevirtual 48	android/os/Parcel:writeString	(Ljava/lang/String;)V
    //   20: aload_2
    //   21: ifnull +46 -> 67
    //   24: aload_2
    //   25: invokeinterface 65 1 0
    //   30: astore_1
    //   31: aload_3
    //   32: aload_1
    //   33: invokevirtual 33	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
    //   36: aload_0
    //   37: getfield 17	ano$a$a:a	Landroid/os/IBinder;
    //   40: bipush 10
    //   42: aload_3
    //   43: aload 4
    //   45: iconst_0
    //   46: invokeinterface 39 5 0
    //   51: pop
    //   52: aload 4
    //   54: invokevirtual 42	android/os/Parcel:readException	()V
    //   57: aload 4
    //   59: invokevirtual 45	android/os/Parcel:recycle	()V
    //   62: aload_3
    //   63: invokevirtual 45	android/os/Parcel:recycle	()V
    //   66: return
    //   67: aconst_null
    //   68: astore_1
    //   69: goto -38 -> 31
    //   72: astore_1
    //   73: aload 4
    //   75: invokevirtual 45	android/os/Parcel:recycle	()V
    //   78: aload_3
    //   79: invokevirtual 45	android/os/Parcel:recycle	()V
    //   82: aload_1
    //   83: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	84	0	this	a
    //   0	84	1	paramString	String
    //   0	84	2	paramanp	anp
    //   3	76	3	localParcel1	Parcel
    //   7	67	4	localParcel2	Parcel
    // Exception table:
    //   from	to	target	type
    //   9	20	72	finally
    //   24	31	72	finally
    //   31	57	72	finally
  }
  
  public void a(String paramString1, String paramString2)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
      localParcel1.writeString(paramString1);
      localParcel1.writeString(paramString2);
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
  
  public void a(String paramString, String[] paramArrayOfString)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
      localParcel1.writeString(paramString);
      localParcel1.writeStringArray(paramArrayOfString);
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
  
  public boolean a()
  {
    boolean bool = false;
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
      a.transact(6, localParcel1, localParcel2, 0);
      localParcel2.readException();
      int i = localParcel2.readInt();
      if (i != 0) {
        bool = true;
      }
      return bool;
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
  
  public void b()
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
      a.transact(9, localParcel1, localParcel2, 0);
      localParcel2.readException();
      return;
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
  }
  
  public void b(String paramString)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
      localParcel1.writeString(paramString);
      a.transact(11, localParcel1, localParcel2, 0);
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
      localParcel1.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
      localParcel1.writeString(paramString1);
      localParcel1.writeString(paramString2);
      a.transact(12, localParcel1, localParcel2, 0);
      localParcel2.readException();
      return;
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
  }
  
  public void c(String paramString1, String paramString2)
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
      localParcel1.writeString(paramString1);
      localParcel1.writeString(paramString2);
      a.transact(15, localParcel1, localParcel2, 0);
      localParcel2.readException();
      return;
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
  }
  
  public boolean c()
  {
    boolean bool = false;
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
      a.transact(13, localParcel1, localParcel2, 0);
      localParcel2.readException();
      int i = localParcel2.readInt();
      if (i != 0) {
        bool = true;
      }
      return bool;
    }
    finally
    {
      localParcel2.recycle();
      localParcel1.recycle();
    }
  }
  
  public void d()
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
      a.transact(14, localParcel1, localParcel2, 0);
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
 * Qualified Name:     ano.a.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */