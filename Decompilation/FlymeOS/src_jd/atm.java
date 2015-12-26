import java.io.PrintStream;
import java.util.Map;

public class atm
{
  public static void a(String paramString)
  {
    System.out.println(paramString);
  }
  
  public static void a(String paramString, Throwable paramThrowable)
  {
    System.out.println(paramString);
    if (paramThrowable != null) {
      paramThrowable.printStackTrace();
    }
  }
  
  public static boolean a(StringBuffer paramStringBuffer)
  {
    return (paramStringBuffer == null) || (paramStringBuffer.length() == 0);
  }
  
  public static boolean a(Map<?, ?> paramMap)
  {
    return (paramMap == null) || (paramMap.size() == 0);
  }
  
  public static final boolean a(String[] paramArrayOfString)
  {
    if (paramArrayOfString == null) {}
    for (;;)
    {
      return true;
      int j = paramArrayOfString.length;
      int i = 0;
      while (i < j)
      {
        if (!b(paramArrayOfString[i])) {
          return false;
        }
        i += 1;
      }
    }
  }
  
  /* Error */
  public static String[] a(java.io.InputStream paramInputStream, String[] paramArrayOfString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aload_1
    //   3: invokestatic 52	atm:a	([Ljava/lang/String;)Z
    //   6: ifeq +7 -> 13
    //   9: aconst_null
    //   10: astore_0
    //   11: aload_0
    //   12: areturn
    //   13: aload_1
    //   14: arraylength
    //   15: anewarray 54	java/lang/String
    //   18: astore 5
    //   20: new 56	java/util/Properties
    //   23: dup
    //   24: invokespecial 59	java/util/Properties:<init>	()V
    //   27: astore 6
    //   29: aload 6
    //   31: aload_0
    //   32: invokevirtual 63	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   35: aload_1
    //   36: arraylength
    //   37: istore 4
    //   39: iconst_0
    //   40: istore_2
    //   41: aload 5
    //   43: astore_0
    //   44: iload_3
    //   45: iload 4
    //   47: if_icmpge -36 -> 11
    //   50: aload 5
    //   52: iload_2
    //   53: aload 6
    //   55: aload_1
    //   56: iload_3
    //   57: aaload
    //   58: invokevirtual 67	java/util/Properties:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   61: aastore
    //   62: iload_3
    //   63: iconst_1
    //   64: iadd
    //   65: istore_3
    //   66: iload_2
    //   67: iconst_1
    //   68: iadd
    //   69: istore_2
    //   70: goto -29 -> 41
    //   73: astore_0
    //   74: ldc 69
    //   76: aload_0
    //   77: invokestatic 71	atm:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   80: aload 5
    //   82: areturn
    //   83: astore_0
    //   84: aload_0
    //   85: athrow
    //   86: astore_0
    //   87: ldc 73
    //   89: aload_0
    //   90: invokestatic 71	atm:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   93: aload 5
    //   95: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	96	0	paramInputStream	java.io.InputStream
    //   0	96	1	paramArrayOfString	String[]
    //   40	30	2	i	int
    //   1	65	3	j	int
    //   37	11	4	k	int
    //   18	76	5	arrayOfString	String[]
    //   27	27	6	localProperties	java.util.Properties
    // Exception table:
    //   from	to	target	type
    //   20	39	73	java/io/FileNotFoundException
    //   50	62	73	java/io/FileNotFoundException
    //   20	39	83	finally
    //   50	62	83	finally
    //   74	80	83	finally
    //   87	93	83	finally
    //   20	39	86	java/io/IOException
    //   50	62	86	java/io/IOException
  }
  
  public static boolean b(String paramString)
  {
    return (paramString == null) || (paramString.trim().length() == 0);
  }
}

/* Location:
 * Qualified Name:     atm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */