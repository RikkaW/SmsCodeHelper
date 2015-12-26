package com.ted.sdk.location;

import android.content.Context;
import anu;
import java.io.IOException;
import java.io.InputStream;

public class LocationEngine
{
  private static final boolean DEBUG = true;
  private static final String LOCATION = "location.dat";
  private static final Integer[] chinaMobileArr = { Integer.valueOf(134), Integer.valueOf(135), Integer.valueOf(136), Integer.valueOf(137), Integer.valueOf(138), Integer.valueOf(139), Integer.valueOf(147), Integer.valueOf(150), Integer.valueOf(151), Integer.valueOf(152), Integer.valueOf(157), Integer.valueOf(158), Integer.valueOf(159), Integer.valueOf(182), Integer.valueOf(183), Integer.valueOf(187), Integer.valueOf(188), Integer.valueOf(184) };
  private static final Integer[] chinaTelArr;
  private static final Integer[] chinaUnicomArr = { Integer.valueOf(130), Integer.valueOf(131), Integer.valueOf(132), Integer.valueOf(145), Integer.valueOf(155), Integer.valueOf(156), Integer.valueOf(186), Integer.valueOf(185) };
  private static long mHandle;
  private static final Integer[] shortCodeArr;
  
  static
  {
    chinaTelArr = new Integer[] { Integer.valueOf(133), Integer.valueOf(153), Integer.valueOf(180), Integer.valueOf(181), Integer.valueOf(189), Integer.valueOf(177) };
    shortCodeArr = new Integer[] { Integer.valueOf(10), Integer.valueOf(21), Integer.valueOf(22), Integer.valueOf(23), Integer.valueOf(24), Integer.valueOf(25), Integer.valueOf(27), Integer.valueOf(28), Integer.valueOf(29), Integer.valueOf(20) };
    mHandle = 0L;
    System.loadLibrary("location");
  }
  
  static final int bytes2int(byte[] paramArrayOfByte)
  {
    return (paramArrayOfByte[3] & 0xFF) << 24 | (paramArrayOfByte[2] & 0xFF) << 16 | (paramArrayOfByte[1] & 0xFF) << 8 | paramArrayOfByte[0] & 0xFF;
  }
  
  public static void clear()
  {
    if (mHandle != 0L)
    {
      doRelease(mHandle);
      mHandle = 0L;
    }
  }
  
  private static native String doFindLocation(String paramString, long paramLong);
  
  private static native long doOpenFile(String paramString);
  
  private static native void doRelease(long paramLong);
  
  /* Error */
  public static int getCacheVersion(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 11
    //   3: invokevirtual 71	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   6: astore_0
    //   7: aload_0
    //   8: invokestatic 75	com/ted/sdk/location/LocationEngine:getVersion	(Ljava/io/InputStream;)I
    //   11: istore_1
    //   12: ldc 77
    //   14: new 79	java/lang/StringBuilder
    //   17: dup
    //   18: ldc 81
    //   20: invokespecial 83	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   23: iload_1
    //   24: invokestatic 87	java/lang/Integer:toHexString	(I)Ljava/lang/String;
    //   27: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   33: invokestatic 101	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   36: pop
    //   37: iload_1
    //   38: istore_2
    //   39: aload_0
    //   40: ifnull +9 -> 49
    //   43: aload_0
    //   44: invokevirtual 106	java/io/InputStream:close	()V
    //   47: iload_1
    //   48: istore_2
    //   49: iload_2
    //   50: ireturn
    //   51: astore_0
    //   52: aconst_null
    //   53: astore_0
    //   54: iconst_0
    //   55: istore_1
    //   56: iload_1
    //   57: istore_2
    //   58: aload_0
    //   59: ifnull -10 -> 49
    //   62: aload_0
    //   63: invokevirtual 106	java/io/InputStream:close	()V
    //   66: iload_1
    //   67: ireturn
    //   68: astore_0
    //   69: iload_1
    //   70: ireturn
    //   71: astore_3
    //   72: aconst_null
    //   73: astore_0
    //   74: aload_0
    //   75: ifnull +7 -> 82
    //   78: aload_0
    //   79: invokevirtual 106	java/io/InputStream:close	()V
    //   82: aload_3
    //   83: athrow
    //   84: astore_0
    //   85: goto -3 -> 82
    //   88: astore_0
    //   89: iload_1
    //   90: ireturn
    //   91: astore_3
    //   92: goto -18 -> 74
    //   95: astore_3
    //   96: iconst_0
    //   97: istore_1
    //   98: goto -42 -> 56
    //   101: astore_3
    //   102: goto -46 -> 56
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	105	0	paramContext	Context
    //   11	87	1	i	int
    //   38	20	2	j	int
    //   71	12	3	localObject1	Object
    //   91	1	3	localObject2	Object
    //   95	1	3	localFileNotFoundException1	java.io.FileNotFoundException
    //   101	1	3	localFileNotFoundException2	java.io.FileNotFoundException
    // Exception table:
    //   from	to	target	type
    //   0	7	51	java/io/FileNotFoundException
    //   62	66	68	java/io/IOException
    //   0	7	71	finally
    //   78	82	84	java/io/IOException
    //   43	47	88	java/io/IOException
    //   7	12	91	finally
    //   12	37	91	finally
    //   7	12	95	java/io/FileNotFoundException
    //   12	37	101	java/io/FileNotFoundException
  }
  
  public static String getHomeLocation(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (paramString == null) {
      return "";
    }
    String str = anu.b(paramString);
    if (str.length() < 3) {
      return "";
    }
    if (mHandle == 0L) {
      init(paramContext);
    }
    paramContext = null;
    if (mHandle != 0L) {
      paramContext = doFindLocation(getPrefix(str), mHandle);
    }
    paramString = paramContext;
    if (paramContext == null) {
      paramString = "";
    }
    paramString = new StringBuilder(String.valueOf(paramString));
    if (paramBoolean) {}
    for (paramContext = getOperatorName(str);; paramContext = "") {
      return paramContext;
    }
  }
  
  private static String getOperatorName(String paramString)
  {
    int j = 0;
    paramString = paramString.substring(0, 3);
    try
    {
      int k = Integer.parseInt(paramString);
      paramString = chinaMobileArr;
      int m = paramString.length;
      int i = 0;
      if (i >= m)
      {
        paramString = chinaUnicomArr;
        m = paramString.length;
        i = 0;
        label40:
        if (i < m) {
          break label85;
        }
        paramString = chinaTelArr;
        m = paramString.length;
        i = j;
      }
      for (;;)
      {
        if (i >= m)
        {
          return "";
          if (paramString[i].intValue() == k) {
            return "移动";
          }
          i += 1;
          break;
          label85:
          if (paramString[i].intValue() == k) {
            return "联通";
          }
          i += 1;
          break label40;
        }
        if (paramString[i].intValue() == k) {
          return "电信";
        }
        i += 1;
      }
      return "";
    }
    catch (Exception paramString) {}
  }
  
  private static String getPrefix(String paramString)
  {
    try
    {
      if (paramString.startsWith("0"))
      {
        String str = paramString.substring(0, 3);
        if (isShortCode(str)) {
          return str;
        }
        return paramString.substring(0, 4);
      }
      paramString = paramString.substring(0, 7);
      return paramString;
    }
    catch (Exception paramString) {}
    return "";
  }
  
  public static int getVersion(InputStream paramInputStream)
  {
    int j = 0;
    byte[] arrayOfByte = new byte[4];
    try
    {
      i = paramInputStream.read(arrayOfByte);
      if (i == 4) {
        j = bytes2int(arrayOfByte);
      }
      return j;
    }
    catch (IOException paramInputStream)
    {
      for (;;)
      {
        paramInputStream.printStackTrace();
        int i = 0;
      }
    }
  }
  
  /* Error */
  public static void init(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore_3
    //   4: aconst_null
    //   5: astore 6
    //   7: aconst_null
    //   8: astore 5
    //   10: new 175	java/io/File
    //   13: dup
    //   14: new 79	java/lang/StringBuilder
    //   17: dup
    //   18: aload_0
    //   19: invokevirtual 179	android/content/Context:getFilesDir	()Ljava/io/File;
    //   22: invokevirtual 182	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   25: invokestatic 134	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   28: invokespecial 83	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   31: ldc -72
    //   33: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: ldc 11
    //   38: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   44: invokespecial 185	java/io/File:<init>	(Ljava/lang/String;)V
    //   47: invokevirtual 189	java/io/File:exists	()Z
    //   50: ifne +96 -> 146
    //   53: sipush 4096
    //   56: newarray <illegal type>
    //   58: astore 7
    //   60: aload_0
    //   61: invokevirtual 193	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   64: ldc 11
    //   66: invokevirtual 199	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   69: astore 4
    //   71: aload_3
    //   72: astore_2
    //   73: aload 4
    //   75: astore_3
    //   76: aload 6
    //   78: astore 5
    //   80: aload_0
    //   81: ldc 11
    //   83: iconst_0
    //   84: invokevirtual 203	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   87: astore 6
    //   89: aload 6
    //   91: astore_2
    //   92: aload 4
    //   94: astore_3
    //   95: aload 6
    //   97: astore 5
    //   99: aload 4
    //   101: aload 7
    //   103: invokevirtual 168	java/io/InputStream:read	([B)I
    //   106: istore_1
    //   107: iload_1
    //   108: ifgt +44 -> 152
    //   111: aload 6
    //   113: astore_2
    //   114: aload 4
    //   116: astore_3
    //   117: aload 6
    //   119: astore 5
    //   121: aload 6
    //   123: invokevirtual 208	java/io/OutputStream:flush	()V
    //   126: aload 6
    //   128: ifnull +8 -> 136
    //   131: aload 6
    //   133: invokevirtual 209	java/io/OutputStream:close	()V
    //   136: aload 4
    //   138: ifnull +8 -> 146
    //   141: aload 4
    //   143: invokevirtual 106	java/io/InputStream:close	()V
    //   146: aload_0
    //   147: invokestatic 213	com/ted/sdk/location/LocationEngine:tryOpen	(Landroid/content/Context;)Z
    //   150: pop
    //   151: return
    //   152: aload 6
    //   154: astore_2
    //   155: aload 4
    //   157: astore_3
    //   158: aload 6
    //   160: astore 5
    //   162: aload 6
    //   164: aload 7
    //   166: iconst_0
    //   167: iload_1
    //   168: invokevirtual 217	java/io/OutputStream:write	([BII)V
    //   171: aload 6
    //   173: astore_2
    //   174: aload 4
    //   176: astore_3
    //   177: aload 6
    //   179: astore 5
    //   181: aload 4
    //   183: aload 7
    //   185: invokevirtual 168	java/io/InputStream:read	([B)I
    //   188: istore_1
    //   189: goto -82 -> 107
    //   192: astore 6
    //   194: aconst_null
    //   195: astore 4
    //   197: aload 5
    //   199: astore_2
    //   200: aload 4
    //   202: astore_3
    //   203: aload 6
    //   205: invokevirtual 218	java/lang/Exception:printStackTrace	()V
    //   208: aload 5
    //   210: ifnull +8 -> 218
    //   213: aload 5
    //   215: invokevirtual 209	java/io/OutputStream:close	()V
    //   218: aload 4
    //   220: ifnull -74 -> 146
    //   223: aload 4
    //   225: invokevirtual 106	java/io/InputStream:close	()V
    //   228: goto -82 -> 146
    //   231: astore_2
    //   232: goto -86 -> 146
    //   235: astore_0
    //   236: aconst_null
    //   237: astore_3
    //   238: aload_2
    //   239: ifnull +7 -> 246
    //   242: aload_2
    //   243: invokevirtual 209	java/io/OutputStream:close	()V
    //   246: aload_3
    //   247: ifnull +7 -> 254
    //   250: aload_3
    //   251: invokevirtual 106	java/io/InputStream:close	()V
    //   254: aload_0
    //   255: athrow
    //   256: astore_2
    //   257: goto -39 -> 218
    //   260: astore_2
    //   261: goto -15 -> 246
    //   264: astore_2
    //   265: goto -11 -> 254
    //   268: astore_2
    //   269: goto -133 -> 136
    //   272: astore_2
    //   273: goto -127 -> 146
    //   276: astore_0
    //   277: goto -39 -> 238
    //   280: astore 6
    //   282: goto -85 -> 197
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	285	0	paramContext	Context
    //   106	83	1	i	int
    //   1	199	2	localObject1	Object
    //   231	12	2	localIOException1	IOException
    //   256	1	2	localIOException2	IOException
    //   260	1	2	localIOException3	IOException
    //   264	1	2	localIOException4	IOException
    //   268	1	2	localIOException5	IOException
    //   272	1	2	localIOException6	IOException
    //   3	248	3	localObject2	Object
    //   69	155	4	localInputStream	InputStream
    //   8	206	5	localFileOutputStream1	java.io.FileOutputStream
    //   5	173	6	localFileOutputStream2	java.io.FileOutputStream
    //   192	12	6	localException1	Exception
    //   280	1	6	localException2	Exception
    //   58	126	7	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   60	71	192	java/lang/Exception
    //   223	228	231	java/io/IOException
    //   60	71	235	finally
    //   213	218	256	java/io/IOException
    //   242	246	260	java/io/IOException
    //   250	254	264	java/io/IOException
    //   131	136	268	java/io/IOException
    //   141	146	272	java/io/IOException
    //   80	89	276	finally
    //   99	107	276	finally
    //   121	126	276	finally
    //   162	171	276	finally
    //   181	189	276	finally
    //   203	208	276	finally
    //   80	89	280	java/lang/Exception
    //   99	107	280	java/lang/Exception
    //   121	126	280	java/lang/Exception
    //   162	171	280	java/lang/Exception
    //   181	189	280	java/lang/Exception
  }
  
  private static boolean isShortCode(String paramString)
  {
    try
    {
      i = Integer.parseInt(paramString);
      paramString = shortCodeArr;
      int k = paramString.length;
      j = 0;
      if (j >= k) {
        return false;
      }
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        int j;
        int i = 0;
        continue;
        if (paramString[j].intValue() == i) {
          return true;
        }
        j += 1;
      }
    }
  }
  
  /* Error */
  private static boolean tryOpen(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 36	com/ted/sdk/location/LocationEngine:mHandle	J
    //   6: lconst_0
    //   7: lcmp
    //   8: ifne +39 -> 47
    //   11: new 79	java/lang/StringBuilder
    //   14: dup
    //   15: aload_0
    //   16: invokevirtual 179	android/content/Context:getFilesDir	()Ljava/io/File;
    //   19: invokevirtual 182	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   22: invokestatic 134	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   25: invokespecial 83	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   28: ldc -72
    //   30: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: ldc 11
    //   35: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   41: invokestatic 220	com/ted/sdk/location/LocationEngine:doOpenFile	(Ljava/lang/String;)J
    //   44: putstatic 36	com/ted/sdk/location/LocationEngine:mHandle	J
    //   47: getstatic 36	com/ted/sdk/location/LocationEngine:mHandle	J
    //   50: lstore_1
    //   51: lload_1
    //   52: lconst_0
    //   53: lcmp
    //   54: ifeq +10 -> 64
    //   57: iconst_1
    //   58: istore_3
    //   59: ldc 2
    //   61: monitorexit
    //   62: iload_3
    //   63: ireturn
    //   64: iconst_0
    //   65: istore_3
    //   66: goto -7 -> 59
    //   69: astore_0
    //   70: ldc 2
    //   72: monitorexit
    //   73: aload_0
    //   74: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	75	0	paramContext	Context
    //   50	2	1	l	long
    //   58	8	3	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   3	47	69	finally
    //   47	51	69	finally
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.location.LocationEngine
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */