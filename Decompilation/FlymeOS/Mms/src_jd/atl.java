import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class atl
{
  /* Error */
  public static java.util.LinkedHashMap<String, String> a(String paramString)
  {
    // Byte code:
    //   0: new 15	java/util/LinkedHashMap
    //   3: dup
    //   4: invokespecial 16	java/util/LinkedHashMap:<init>	()V
    //   7: astore_3
    //   8: new 18	java/io/File
    //   11: dup
    //   12: aload_0
    //   13: invokespecial 21	java/io/File:<init>	(Ljava/lang/String;)V
    //   16: astore_2
    //   17: aload_2
    //   18: invokestatic 24	atl:a	(Ljava/io/File;)Z
    //   21: ifne +8 -> 29
    //   24: aload_0
    //   25: invokestatic 27	atl:b	(Ljava/lang/String;)Ljava/util/LinkedHashMap;
    //   28: areturn
    //   29: aconst_null
    //   30: astore_1
    //   31: aconst_null
    //   32: astore_0
    //   33: aload_2
    //   34: invokestatic 30	atl:b	(Ljava/io/File;)Ljava/io/BufferedReader;
    //   37: astore_2
    //   38: aload_2
    //   39: astore_0
    //   40: aload_2
    //   41: astore_1
    //   42: aload_2
    //   43: invokevirtual 36	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   46: astore 4
    //   48: aload 4
    //   50: ifnonnull +9 -> 59
    //   53: aload_2
    //   54: invokestatic 39	atl:a	(Ljava/io/Reader;)V
    //   57: aload_3
    //   58: areturn
    //   59: aload_2
    //   60: astore_0
    //   61: aload_2
    //   62: astore_1
    //   63: aload 4
    //   65: ldc 41
    //   67: invokevirtual 47	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   70: astore 4
    //   72: aload 4
    //   74: ifnull -36 -> 38
    //   77: aload_2
    //   78: astore_0
    //   79: aload_2
    //   80: astore_1
    //   81: aload 4
    //   83: arraylength
    //   84: iconst_2
    //   85: if_icmplt -47 -> 38
    //   88: aload_2
    //   89: astore_0
    //   90: aload_2
    //   91: astore_1
    //   92: aload_3
    //   93: aload 4
    //   95: iconst_0
    //   96: aaload
    //   97: aload 4
    //   99: iconst_1
    //   100: aaload
    //   101: invokevirtual 51	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   104: pop
    //   105: goto -67 -> 38
    //   108: astore_2
    //   109: aload_0
    //   110: astore_1
    //   111: ldc 53
    //   113: aload_2
    //   114: invokestatic 58	atm:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   117: aload_0
    //   118: invokestatic 39	atl:a	(Ljava/io/Reader;)V
    //   121: aload_3
    //   122: areturn
    //   123: astore_0
    //   124: aload_1
    //   125: invokestatic 39	atl:a	(Ljava/io/Reader;)V
    //   128: aload_0
    //   129: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	130	0	paramString	String
    //   30	95	1	localObject1	Object
    //   16	75	2	localObject2	Object
    //   108	6	2	localIOException	IOException
    //   7	115	3	localLinkedHashMap	java.util.LinkedHashMap
    //   46	52	4	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   33	38	108	java/io/IOException
    //   42	48	108	java/io/IOException
    //   63	72	108	java/io/IOException
    //   81	88	108	java/io/IOException
    //   92	105	108	java/io/IOException
    //   33	38	123	finally
    //   42	48	123	finally
    //   63	72	123	finally
    //   81	88	123	finally
    //   92	105	123	finally
    //   111	117	123	finally
  }
  
  private static void a(Reader paramReader)
  {
    if (paramReader == null) {
      return;
    }
    try
    {
      paramReader.close();
      return;
    }
    catch (IOException paramReader)
    {
      atm.a("closeReader , IOException: ", paramReader);
    }
  }
  
  public static boolean a(File paramFile)
  {
    return (paramFile != null) && (paramFile.exists()) && (paramFile.isFile()) && (paramFile.canRead());
  }
  
  private static BufferedReader b(File paramFile)
  {
    return new BufferedReader(new InputStreamReader(new FileInputStream(paramFile), "UTF-8"));
  }
  
  /* Error */
  public static java.util.LinkedHashMap<String, String> b(String paramString)
  {
    // Byte code:
    //   0: new 15	java/util/LinkedHashMap
    //   3: dup
    //   4: invokespecial 16	java/util/LinkedHashMap:<init>	()V
    //   7: astore_1
    //   8: new 32	java/io/BufferedReader
    //   11: dup
    //   12: new 79	java/io/InputStreamReader
    //   15: dup
    //   16: ldc 2
    //   18: invokevirtual 97	java/lang/Class:getClassLoader	()Ljava/lang/ClassLoader;
    //   21: aload_0
    //   22: invokevirtual 103	java/lang/ClassLoader:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   25: invokespecial 106	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   28: invokespecial 91	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   31: astore_0
    //   32: aload_0
    //   33: invokevirtual 36	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   36: astore_2
    //   37: aload_2
    //   38: ifnonnull +9 -> 47
    //   41: aload_0
    //   42: invokestatic 39	atl:a	(Ljava/io/Reader;)V
    //   45: aload_1
    //   46: areturn
    //   47: aload_2
    //   48: ldc 41
    //   50: invokevirtual 47	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   53: astore_2
    //   54: aload_2
    //   55: ifnull -23 -> 32
    //   58: aload_2
    //   59: arraylength
    //   60: iconst_2
    //   61: if_icmplt -29 -> 32
    //   64: aload_1
    //   65: aload_2
    //   66: iconst_0
    //   67: aaload
    //   68: aload_2
    //   69: iconst_1
    //   70: aaload
    //   71: invokevirtual 51	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   74: pop
    //   75: goto -43 -> 32
    //   78: astore_2
    //   79: ldc 53
    //   81: aload_2
    //   82: invokestatic 58	atm:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   85: aload_0
    //   86: invokestatic 39	atl:a	(Ljava/io/Reader;)V
    //   89: aload_1
    //   90: areturn
    //   91: astore_1
    //   92: aload_0
    //   93: invokestatic 39	atl:a	(Ljava/io/Reader;)V
    //   96: aload_1
    //   97: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	98	0	paramString	String
    //   7	83	1	localLinkedHashMap	java.util.LinkedHashMap
    //   91	6	1	localObject1	Object
    //   36	33	2	localObject2	Object
    //   78	4	2	localIOException	IOException
    // Exception table:
    //   from	to	target	type
    //   32	37	78	java/io/IOException
    //   47	54	78	java/io/IOException
    //   58	75	78	java/io/IOException
    //   32	37	91	finally
    //   47	54	91	finally
    //   58	75	91	finally
    //   79	85	91	finally
  }
}

/* Location:
 * Qualified Name:     atl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */