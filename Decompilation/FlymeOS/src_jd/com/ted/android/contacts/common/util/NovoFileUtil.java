package com.ted.android.contacts.common.util;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.InputStream;

public class NovoFileUtil
{
  private static final boolean DEBUG = false;
  private static final String TIMESTAMP_EXT = ".t";
  
  public static InputStream openLatestInputFile(Context paramContext, String paramString)
  {
    Object localObject3 = null;
    Object localObject1 = localObject3;
    if (readFileTimestamp(paramContext, paramString) >= readAssetsTimestamp(paramContext, paramString)) {}
    try
    {
      localObject1 = paramContext.openFileInput(paramString);
      localObject3 = localObject1;
      if (localObject1 != null) {}
    }
    catch (Exception localException)
    {
      Object localObject2;
      for (;;)
      {
        try
        {
          localObject3 = paramContext.getAssets().open(paramString);
          return (InputStream)localObject3;
        }
        catch (Exception paramContext) {}
        localException = localException;
        localObject2 = localObject3;
      }
      return (InputStream)localObject2;
    }
  }
  
  public static long readAssetsTimestamp(Context paramContext, String paramString)
  {
    try
    {
      long l = readTimestampFromStream(paramContext.getAssets().open(paramString + ".t"));
      return l;
    }
    catch (Exception paramContext) {}
    return 0L;
  }
  
  public static long readFileTimestamp(Context paramContext, String paramString)
  {
    Object localObject = null;
    try
    {
      paramContext = paramContext.openFileInput(paramString + ".t");
      if (paramContext != null) {
        return readTimestampFromStream(paramContext);
      }
      return 0L;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = (Context)localObject;
      }
    }
  }
  
  /* Error */
  public static long readTimestampFromStream(InputStream paramInputStream)
  {
    // Byte code:
    //   0: lconst_0
    //   1: lstore_3
    //   2: new 68	java/io/DataInputStream
    //   5: dup
    //   6: aload_0
    //   7: invokespecial 71	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   10: astore 6
    //   12: aload 6
    //   14: invokevirtual 74	java/io/DataInputStream:readLine	()Ljava/lang/String;
    //   17: invokestatic 80	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   20: lstore_1
    //   21: aload 6
    //   23: ifnull +8 -> 31
    //   26: aload 6
    //   28: invokevirtual 83	java/io/DataInputStream:close	()V
    //   31: lload_1
    //   32: lstore_3
    //   33: aload_0
    //   34: ifnull +9 -> 43
    //   37: aload_0
    //   38: invokevirtual 86	java/io/InputStream:close	()V
    //   41: lload_1
    //   42: lstore_3
    //   43: lload_3
    //   44: lreturn
    //   45: astore 5
    //   47: aconst_null
    //   48: astore 6
    //   50: aload 6
    //   52: ifnull +8 -> 60
    //   55: aload 6
    //   57: invokevirtual 83	java/io/DataInputStream:close	()V
    //   60: aload_0
    //   61: ifnull -18 -> 43
    //   64: aload_0
    //   65: invokevirtual 86	java/io/InputStream:close	()V
    //   68: lconst_0
    //   69: lreturn
    //   70: astore_0
    //   71: lconst_0
    //   72: lreturn
    //   73: astore 5
    //   75: aconst_null
    //   76: astore 6
    //   78: aload 6
    //   80: ifnull +8 -> 88
    //   83: aload 6
    //   85: invokevirtual 83	java/io/DataInputStream:close	()V
    //   88: aload_0
    //   89: ifnull +7 -> 96
    //   92: aload_0
    //   93: invokevirtual 86	java/io/InputStream:close	()V
    //   96: aload 5
    //   98: athrow
    //   99: astore 5
    //   101: goto -41 -> 60
    //   104: astore 6
    //   106: goto -18 -> 88
    //   109: astore_0
    //   110: goto -14 -> 96
    //   113: astore 5
    //   115: goto -84 -> 31
    //   118: astore_0
    //   119: lload_1
    //   120: lreturn
    //   121: astore 5
    //   123: goto -45 -> 78
    //   126: astore 5
    //   128: goto -78 -> 50
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	131	0	paramInputStream	InputStream
    //   20	100	1	l1	long
    //   1	43	3	l2	long
    //   45	1	5	localException1	Exception
    //   73	24	5	localObject1	Object
    //   99	1	5	localException2	Exception
    //   113	1	5	localException3	Exception
    //   121	1	5	localObject2	Object
    //   126	1	5	localException4	Exception
    //   10	74	6	localDataInputStream	java.io.DataInputStream
    //   104	1	6	localException5	Exception
    // Exception table:
    //   from	to	target	type
    //   2	12	45	java/lang/Exception
    //   64	68	70	java/lang/Exception
    //   2	12	73	finally
    //   55	60	99	java/lang/Exception
    //   83	88	104	java/lang/Exception
    //   92	96	109	java/lang/Exception
    //   26	31	113	java/lang/Exception
    //   37	41	118	java/lang/Exception
    //   12	21	121	finally
    //   12	21	126	java/lang/Exception
  }
  
  /* Error */
  public static void writeTimestamp2File(Context paramContext, String paramString, long paramLong)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: aload_0
    //   7: new 45	java/lang/StringBuilder
    //   10: dup
    //   11: aload_1
    //   12: invokestatic 51	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   15: invokespecial 54	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   18: ldc 11
    //   20: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   26: iconst_0
    //   27: invokevirtual 94	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   30: astore_0
    //   31: new 96	java/io/DataOutputStream
    //   34: dup
    //   35: aload_0
    //   36: invokespecial 99	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   39: astore_1
    //   40: aload_1
    //   41: lload_2
    //   42: invokestatic 102	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   45: invokevirtual 105	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   48: aload_1
    //   49: ifnull +7 -> 56
    //   52: aload_1
    //   53: invokevirtual 106	java/io/DataOutputStream:close	()V
    //   56: aload_0
    //   57: ifnull +7 -> 64
    //   60: aload_0
    //   61: invokevirtual 109	java/io/FileOutputStream:close	()V
    //   64: return
    //   65: astore_0
    //   66: aconst_null
    //   67: astore_0
    //   68: aload 5
    //   70: astore_1
    //   71: aload_0
    //   72: ifnull +7 -> 79
    //   75: aload_0
    //   76: invokevirtual 106	java/io/DataOutputStream:close	()V
    //   79: aload_1
    //   80: ifnull -16 -> 64
    //   83: aload_1
    //   84: invokevirtual 109	java/io/FileOutputStream:close	()V
    //   87: return
    //   88: astore_0
    //   89: return
    //   90: astore_1
    //   91: aconst_null
    //   92: astore_0
    //   93: aload 4
    //   95: ifnull +8 -> 103
    //   98: aload 4
    //   100: invokevirtual 106	java/io/DataOutputStream:close	()V
    //   103: aload_0
    //   104: ifnull +7 -> 111
    //   107: aload_0
    //   108: invokevirtual 109	java/io/FileOutputStream:close	()V
    //   111: aload_1
    //   112: athrow
    //   113: astore_0
    //   114: goto -35 -> 79
    //   117: astore 4
    //   119: goto -16 -> 103
    //   122: astore_0
    //   123: goto -12 -> 111
    //   126: astore_1
    //   127: goto -71 -> 56
    //   130: astore_0
    //   131: return
    //   132: astore_1
    //   133: goto -40 -> 93
    //   136: astore 5
    //   138: aload_1
    //   139: astore 4
    //   141: aload 5
    //   143: astore_1
    //   144: goto -51 -> 93
    //   147: astore_1
    //   148: aconst_null
    //   149: astore 4
    //   151: aload_0
    //   152: astore_1
    //   153: aload 4
    //   155: astore_0
    //   156: goto -85 -> 71
    //   159: astore 4
    //   161: aload_0
    //   162: astore 4
    //   164: aload_1
    //   165: astore_0
    //   166: aload 4
    //   168: astore_1
    //   169: goto -98 -> 71
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	172	0	paramContext	Context
    //   0	172	1	paramString	String
    //   0	172	2	paramLong	long
    //   1	98	4	localObject1	Object
    //   117	1	4	localException	Exception
    //   139	15	4	str	String
    //   159	1	4	localIOException	java.io.IOException
    //   162	5	4	localContext	Context
    //   4	65	5	localObject2	Object
    //   136	6	5	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   6	31	65	java/io/IOException
    //   83	87	88	java/lang/Exception
    //   6	31	90	finally
    //   75	79	113	java/lang/Exception
    //   98	103	117	java/lang/Exception
    //   107	111	122	java/lang/Exception
    //   52	56	126	java/lang/Exception
    //   60	64	130	java/lang/Exception
    //   31	40	132	finally
    //   40	48	136	finally
    //   31	40	147	java/io/IOException
    //   40	48	159	java/io/IOException
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.common.util.NovoFileUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */