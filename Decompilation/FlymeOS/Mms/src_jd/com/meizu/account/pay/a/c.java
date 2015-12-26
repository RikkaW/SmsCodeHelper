package com.meizu.account.pay.a;

import android.os.IBinder;

final class c
  implements a
{
  private IBinder a;
  
  c(IBinder paramIBinder)
  {
    a = paramIBinder;
  }
  
  /* Error */
  public final android.os.Bundle a(String paramString)
  {
    // Byte code:
    //   0: invokestatic 23	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   3: astore_2
    //   4: invokestatic 23	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   7: astore_3
    //   8: aload_2
    //   9: ldc 25
    //   11: invokevirtual 29	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
    //   14: aload_2
    //   15: aload_1
    //   16: invokevirtual 32	android/os/Parcel:writeString	(Ljava/lang/String;)V
    //   19: aload_0
    //   20: getfield 15	com/meizu/account/pay/a/c:a	Landroid/os/IBinder;
    //   23: iconst_2
    //   24: aload_2
    //   25: aload_3
    //   26: iconst_0
    //   27: invokeinterface 38 5 0
    //   32: pop
    //   33: aload_3
    //   34: invokevirtual 41	android/os/Parcel:readException	()V
    //   37: aload_3
    //   38: invokevirtual 45	android/os/Parcel:readInt	()I
    //   41: ifeq +26 -> 67
    //   44: getstatic 51	android/os/Bundle:CREATOR	Landroid/os/Parcelable$Creator;
    //   47: aload_3
    //   48: invokeinterface 57 2 0
    //   53: checkcast 47	android/os/Bundle
    //   56: astore_1
    //   57: aload_3
    //   58: invokevirtual 60	android/os/Parcel:recycle	()V
    //   61: aload_2
    //   62: invokevirtual 60	android/os/Parcel:recycle	()V
    //   65: aload_1
    //   66: areturn
    //   67: aconst_null
    //   68: astore_1
    //   69: goto -12 -> 57
    //   72: astore_1
    //   73: aload_3
    //   74: invokevirtual 60	android/os/Parcel:recycle	()V
    //   77: aload_2
    //   78: invokevirtual 60	android/os/Parcel:recycle	()V
    //   81: aload_1
    //   82: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	83	0	this	c
    //   0	83	1	paramString	String
    //   3	75	2	localParcel1	android.os.Parcel
    //   7	67	3	localParcel2	android.os.Parcel
    // Exception table:
    //   from	to	target	type
    //   8	57	72	finally
  }
  
  /* Error */
  public final android.os.Bundle a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: invokestatic 23	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   3: astore_3
    //   4: invokestatic 23	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   7: astore 4
    //   9: aload_3
    //   10: ldc 25
    //   12: invokevirtual 29	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
    //   15: aload_3
    //   16: aload_1
    //   17: invokevirtual 32	android/os/Parcel:writeString	(Ljava/lang/String;)V
    //   20: aload_3
    //   21: aload_2
    //   22: invokevirtual 32	android/os/Parcel:writeString	(Ljava/lang/String;)V
    //   25: aload_0
    //   26: getfield 15	com/meizu/account/pay/a/c:a	Landroid/os/IBinder;
    //   29: iconst_1
    //   30: aload_3
    //   31: aload 4
    //   33: iconst_0
    //   34: invokeinterface 38 5 0
    //   39: pop
    //   40: aload 4
    //   42: invokevirtual 41	android/os/Parcel:readException	()V
    //   45: aload 4
    //   47: invokevirtual 45	android/os/Parcel:readInt	()I
    //   50: ifeq +28 -> 78
    //   53: getstatic 51	android/os/Bundle:CREATOR	Landroid/os/Parcelable$Creator;
    //   56: aload 4
    //   58: invokeinterface 57 2 0
    //   63: checkcast 47	android/os/Bundle
    //   66: astore_1
    //   67: aload 4
    //   69: invokevirtual 60	android/os/Parcel:recycle	()V
    //   72: aload_3
    //   73: invokevirtual 60	android/os/Parcel:recycle	()V
    //   76: aload_1
    //   77: areturn
    //   78: aconst_null
    //   79: astore_1
    //   80: goto -13 -> 67
    //   83: astore_1
    //   84: aload 4
    //   86: invokevirtual 60	android/os/Parcel:recycle	()V
    //   89: aload_3
    //   90: invokevirtual 60	android/os/Parcel:recycle	()V
    //   93: aload_1
    //   94: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	95	0	this	c
    //   0	95	1	paramString1	String
    //   0	95	2	paramString2	String
    //   3	87	3	localParcel1	android.os.Parcel
    //   7	78	4	localParcel2	android.os.Parcel
    // Exception table:
    //   from	to	target	type
    //   9	67	83	finally
  }
  
  public final IBinder asBinder()
  {
    return a;
  }
}

/* Location:
 * Qualified Name:     com.meizu.account.pay.a.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */