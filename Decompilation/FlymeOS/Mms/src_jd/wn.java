import android.content.Context;
import android.net.Uri;
import android.os.Handler;

final class wn
  implements Runnable
{
  wn(Context paramContext, Uri paramUri, Handler paramHandler, wd.d paramd, boolean paramBoolean, int paramInt) {}
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: new 39	zf
    //   3: dup
    //   4: aload_0
    //   5: getfield 22	wn:a	Landroid/content/Context;
    //   8: aload_0
    //   9: getfield 24	wn:b	Landroid/net/Uri;
    //   12: invokespecial 42	zf:<init>	(Landroid/content/Context;Landroid/net/Uri;)V
    //   15: astore_1
    //   16: aload_0
    //   17: getfield 22	wn:a	Landroid/content/Context;
    //   20: invokestatic 47	wd:a	(Landroid/content/Context;)Z
    //   23: ifeq +36 -> 59
    //   26: aload_1
    //   27: invokestatic 53	ga:n	()I
    //   30: invokestatic 56	ga:m	()I
    //   33: invokestatic 59	ga:g	()I
    //   36: i2l
    //   37: invokevirtual 62	zf:a	(IIJ)Lcom/meizu/android/mms/pdu/MzPduPart;
    //   40: astore_1
    //   41: aload_0
    //   42: getfield 26	wn:c	Landroid/os/Handler;
    //   45: new 64	wo
    //   48: dup
    //   49: aload_0
    //   50: aload_1
    //   51: invokespecial 67	wo:<init>	(Lwn;Lcom/meizu/android/mms/pdu/MzPduPart;)V
    //   54: invokevirtual 73	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   57: pop
    //   58: return
    //   59: aload_1
    //   60: invokestatic 76	ga:l	()I
    //   63: invokestatic 79	ga:k	()I
    //   66: invokestatic 81	ga:f	()I
    //   69: i2l
    //   70: invokevirtual 62	zf:a	(IIJ)Lcom/meizu/android/mms/pdu/MzPduPart;
    //   73: astore_1
    //   74: goto -33 -> 41
    //   77: astore_1
    //   78: aload_1
    //   79: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	80	0	this	wn
    //   15	59	1	localObject1	Object
    //   77	2	1	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   0	41	77	finally
    //   59	74	77	finally
  }
}

/* Location:
 * Qualified Name:     wn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */