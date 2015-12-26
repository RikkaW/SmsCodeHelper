import android.content.Context;

public class bp
{
  private final Context a;
  private final ans b;
  
  /* Error */
  public bp(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 15	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: new 17	ans
    //   8: dup
    //   9: invokespecial 18	ans:<init>	()V
    //   12: putfield 20	bp:b	Lans;
    //   15: aload_0
    //   16: aload_1
    //   17: putfield 22	bp:a	Landroid/content/Context;
    //   20: aconst_null
    //   21: astore_2
    //   22: aload_0
    //   23: getfield 22	bp:a	Landroid/content/Context;
    //   26: ldc 24
    //   28: invokestatic 30	com/ted/android/contacts/common/util/NovoFileUtil:openLatestInputFile	(Landroid/content/Context;Ljava/lang/String;)Ljava/io/InputStream;
    //   31: astore_1
    //   32: aload_1
    //   33: astore_2
    //   34: aload_0
    //   35: getfield 20	bp:b	Lans;
    //   38: aload_1
    //   39: invokevirtual 33	ans:a	(Ljava/io/InputStream;)V
    //   42: aload_1
    //   43: ifnull +7 -> 50
    //   46: aload_1
    //   47: invokevirtual 38	java/io/InputStream:close	()V
    //   50: return
    //   51: astore_1
    //   52: aload_2
    //   53: ifnull -3 -> 50
    //   56: aload_2
    //   57: invokevirtual 38	java/io/InputStream:close	()V
    //   60: return
    //   61: astore_1
    //   62: return
    //   63: astore_1
    //   64: aconst_null
    //   65: astore_2
    //   66: aload_2
    //   67: ifnull +7 -> 74
    //   70: aload_2
    //   71: invokevirtual 38	java/io/InputStream:close	()V
    //   74: aload_1
    //   75: athrow
    //   76: astore_2
    //   77: goto -3 -> 74
    //   80: astore_1
    //   81: return
    //   82: astore_3
    //   83: aload_1
    //   84: astore_2
    //   85: aload_3
    //   86: astore_1
    //   87: goto -21 -> 66
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	90	0	this	bp
    //   0	90	1	paramContext	Context
    //   21	50	2	localContext1	Context
    //   76	1	2	localException	Exception
    //   84	1	2	localContext2	Context
    //   82	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   22	32	51	java/lang/Exception
    //   34	42	51	java/lang/Exception
    //   56	60	61	java/lang/Exception
    //   22	32	63	finally
    //   70	74	76	java/lang/Exception
    //   46	50	80	java/lang/Exception
    //   34	42	82	finally
  }
  
  public String a(String paramString1, String paramString2)
  {
    return b.b(paramString1, paramString2);
  }
}

/* Location:
 * Qualified Name:     bp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */