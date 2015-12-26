import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

public class anw
{
  private static String a = null;
  
  public static Object a(Context paramContext, String paramString)
  {
    return paramContext.getApplicationContext().getSystemService(paramString);
  }
  
  private static String a()
  {
    Object localObject = null;
    try
    {
      String str = UUID.randomUUID().toString();
      localObject = str;
      str = str.replaceAll("-", "").replace(":", "").toLowerCase();
      return str;
    }
    catch (Exception localException) {}
    return (String)localObject;
  }
  
  public static String a(Context paramContext)
  {
    for (;;)
    {
      try
      {
        File localFile;
        if (a == null)
        {
          localFile = new File(paramContext.getFilesDir(), "UID");
          if (!localFile.exists()) {
            continue;
          }
          a = b(paramContext, localFile, true);
          if (TextUtils.isEmpty(a))
          {
            a(paramContext, localFile, true);
            a = b(paramContext, localFile, true);
          }
        }
        if (a == null)
        {
          paramContext = "";
          return paramContext;
          a(paramContext, localFile, true);
          a = b(paramContext, localFile, true);
        }
        else
        {
          paramContext = a;
        }
      }
      finally {}
    }
  }
  
  private static void a(Context paramContext, File paramFile, boolean paramBoolean)
  {
    label288:
    for (;;)
    {
      try
      {
        TelephonyManager localTelephonyManager = (TelephonyManager)a(paramContext, "phone");
        Object localObject1 = localTelephonyManager.getDeviceId();
        Object localObject2 = localObject1;
        if (b(paramContext, (String)localObject1)) {
          localObject2 = null;
        }
        localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject1 = c();
          if (b(paramContext, (String)localObject1)) {
            localObject1 = null;
          }
        }
        else
        {
          localObject2 = localObject1;
          if (localObject1 == null)
          {
            localObject1 = b();
            if (!b(paramContext, (String)localObject1)) {
              continue;
            }
            localObject2 = null;
          }
          localObject1 = localObject2;
          if (localObject2 == null)
          {
            localObject1 = d(paramContext);
            if (!b(paramContext, (String)localObject1)) {
              continue;
            }
            localObject1 = null;
          }
          if (localObject1 != null) {
            break label288;
          }
          localObject1 = localTelephonyManager.getSubscriberId();
          if (!b(paramContext, (String)localObject1)) {
            continue;
          }
          localObject2 = null;
          localObject1 = localObject2;
          if (localObject2 == null)
          {
            localObject1 = c(paramContext);
            if (!b(paramContext, (String)localObject1)) {
              continue;
            }
            localObject1 = null;
          }
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = "U" + a();
          }
          a = (String)localObject2;
          a(paramContext, a, paramFile, paramBoolean);
          return;
        }
        localObject1 = "C" + (String)localObject1;
        continue;
        localObject2 = "S" + (String)localObject1;
        continue;
        localObject1 = "A" + (String)localObject1;
        continue;
        localObject2 = "I" + (String)localObject1;
        continue;
        localObject1 = "M" + (String)localObject1;
        continue;
        localObject2 = localObject1;
      }
      catch (Exception paramContext)
      {
        return;
      }
    }
  }
  
  /* Error */
  private static void a(Context paramContext, String paramString, File paramFile, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 80	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   4: ifeq +4 -> 8
    //   7: return
    //   8: aconst_null
    //   9: astore 4
    //   11: new 137	java/io/FileOutputStream
    //   14: dup
    //   15: aload_2
    //   16: iconst_0
    //   17: invokespecial 140	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
    //   20: astore_2
    //   21: aload_1
    //   22: astore 4
    //   24: iload_3
    //   25: ifeq +13 -> 38
    //   28: aload_1
    //   29: aload_0
    //   30: invokevirtual 143	android/content/Context:getPackageName	()Ljava/lang/String;
    //   33: invokestatic 147	anv:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   36: astore 4
    //   38: aload_2
    //   39: aload 4
    //   41: invokevirtual 151	java/lang/String:getBytes	()[B
    //   44: invokevirtual 155	java/io/FileOutputStream:write	([B)V
    //   47: aload_2
    //   48: ifnull -41 -> 7
    //   51: aload_2
    //   52: invokevirtual 158	java/io/FileOutputStream:close	()V
    //   55: return
    //   56: astore_0
    //   57: return
    //   58: astore_0
    //   59: aconst_null
    //   60: astore_2
    //   61: aload_2
    //   62: ifnull -55 -> 7
    //   65: aload_2
    //   66: invokevirtual 158	java/io/FileOutputStream:close	()V
    //   69: return
    //   70: astore_0
    //   71: return
    //   72: astore_0
    //   73: aload 4
    //   75: astore_2
    //   76: aload_2
    //   77: ifnull +7 -> 84
    //   80: aload_2
    //   81: invokevirtual 158	java/io/FileOutputStream:close	()V
    //   84: aload_0
    //   85: athrow
    //   86: astore_1
    //   87: goto -3 -> 84
    //   90: astore_0
    //   91: goto -15 -> 76
    //   94: astore_0
    //   95: goto -34 -> 61
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	98	0	paramContext	Context
    //   0	98	1	paramString	String
    //   0	98	2	paramFile	File
    //   0	98	3	paramBoolean	boolean
    //   9	65	4	str	String
    // Exception table:
    //   from	to	target	type
    //   51	55	56	java/lang/Exception
    //   11	21	58	java/lang/Exception
    //   65	69	70	java/lang/Exception
    //   11	21	72	finally
    //   80	84	86	java/lang/Exception
    //   28	38	90	finally
    //   38	47	90	finally
    //   28	38	94	java/lang/Exception
    //   38	47	94	java/lang/Exception
  }
  
  private static String b()
  {
    String str = null;
    if (Build.VERSION.SDK_INT >= 9) {
      str = Build.SERIAL;
    }
    return str;
  }
  
  /* Error */
  private static String b(Context paramContext, File paramFile, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 172	java/io/RandomAccessFile
    //   5: dup
    //   6: aload_1
    //   7: ldc -82
    //   9: invokespecial 175	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   12: astore_1
    //   13: aload_1
    //   14: invokevirtual 179	java/io/RandomAccessFile:length	()J
    //   17: l2i
    //   18: newarray <illegal type>
    //   20: astore 4
    //   22: aload_1
    //   23: aload 4
    //   25: invokevirtual 182	java/io/RandomAccessFile:readFully	([B)V
    //   28: iload_2
    //   29: ifeq +34 -> 63
    //   32: new 40	java/lang/String
    //   35: dup
    //   36: aload 4
    //   38: invokespecial 184	java/lang/String:<init>	([B)V
    //   41: aload_0
    //   42: invokevirtual 143	android/content/Context:getPackageName	()Ljava/lang/String;
    //   45: invokestatic 186	anv:b	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   48: astore_0
    //   49: aload_0
    //   50: astore_3
    //   51: aload_1
    //   52: ifnull +9 -> 61
    //   55: aload_1
    //   56: invokevirtual 187	java/io/RandomAccessFile:close	()V
    //   59: aload_0
    //   60: astore_3
    //   61: aload_3
    //   62: areturn
    //   63: new 40	java/lang/String
    //   66: dup
    //   67: aload 4
    //   69: invokespecial 184	java/lang/String:<init>	([B)V
    //   72: astore_0
    //   73: goto -24 -> 49
    //   76: astore_0
    //   77: aconst_null
    //   78: astore_1
    //   79: aload_1
    //   80: ifnull -19 -> 61
    //   83: aload_1
    //   84: invokevirtual 187	java/io/RandomAccessFile:close	()V
    //   87: aconst_null
    //   88: areturn
    //   89: astore_0
    //   90: aconst_null
    //   91: areturn
    //   92: astore_0
    //   93: aconst_null
    //   94: astore_1
    //   95: aload_1
    //   96: ifnull +7 -> 103
    //   99: aload_1
    //   100: invokevirtual 187	java/io/RandomAccessFile:close	()V
    //   103: aload_0
    //   104: athrow
    //   105: astore_1
    //   106: goto -3 -> 103
    //   109: astore_1
    //   110: aload_0
    //   111: areturn
    //   112: astore_0
    //   113: goto -18 -> 95
    //   116: astore_0
    //   117: goto -38 -> 79
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	120	0	paramContext	Context
    //   0	120	1	paramFile	File
    //   0	120	2	paramBoolean	boolean
    //   1	61	3	localContext	Context
    //   20	48	4	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   2	13	76	java/lang/Throwable
    //   83	87	89	java/lang/Exception
    //   2	13	92	finally
    //   99	103	105	java/lang/Exception
    //   55	59	109	java/lang/Exception
    //   13	28	112	finally
    //   32	49	112	finally
    //   63	73	112	finally
    //   13	28	116	java/lang/Throwable
    //   32	49	116	java/lang/Throwable
    //   63	73	116	java/lang/Throwable
  }
  
  public static boolean b(Context paramContext)
  {
    paramContext = (ConnectivityManager)a(paramContext, "connectivity");
    try
    {
      paramContext = paramContext.getNetworkInfo(1);
      if (paramContext != null) {
        return paramContext.isConnected();
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = null;
      }
    }
    return false;
  }
  
  /* Error */
  private static boolean b(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 80	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   4: ifeq +5 -> 9
    //   7: iconst_1
    //   8: ireturn
    //   9: aload_0
    //   10: ldc -53
    //   12: invokestatic 209	com/ted/android/contacts/common/util/NovoFileUtil:openLatestInputFile	(Landroid/content/Context;Ljava/lang/String;)Ljava/io/InputStream;
    //   15: astore_0
    //   16: aload_0
    //   17: ifnull +30 -> 47
    //   20: new 211	java/io/BufferedReader
    //   23: dup
    //   24: new 213	java/io/InputStreamReader
    //   27: dup
    //   28: aload_0
    //   29: invokespecial 216	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   32: invokespecial 219	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   35: astore_3
    //   36: aload_3
    //   37: invokevirtual 222	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   40: astore 4
    //   42: aload 4
    //   44: ifnonnull +13 -> 57
    //   47: aload_0
    //   48: ifnull +7 -> 55
    //   51: aload_0
    //   52: invokevirtual 225	java/io/InputStream:close	()V
    //   55: iconst_0
    //   56: ireturn
    //   57: aload 4
    //   59: invokestatic 231	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   62: aload_1
    //   63: invokevirtual 235	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   66: invokevirtual 240	java/util/regex/Matcher:matches	()Z
    //   69: istore_2
    //   70: iload_2
    //   71: ifeq -35 -> 36
    //   74: aload_0
    //   75: ifnull -68 -> 7
    //   78: aload_0
    //   79: invokevirtual 225	java/io/InputStream:close	()V
    //   82: iconst_1
    //   83: ireturn
    //   84: astore_0
    //   85: iconst_1
    //   86: ireturn
    //   87: astore 4
    //   89: aload 4
    //   91: invokevirtual 243	java/lang/Exception:printStackTrace	()V
    //   94: goto -58 -> 36
    //   97: astore_1
    //   98: aload_0
    //   99: ifnull -44 -> 55
    //   102: aload_0
    //   103: invokevirtual 225	java/io/InputStream:close	()V
    //   106: goto -51 -> 55
    //   109: astore_0
    //   110: goto -55 -> 55
    //   113: astore_1
    //   114: aconst_null
    //   115: astore_0
    //   116: aload_0
    //   117: ifnull +7 -> 124
    //   120: aload_0
    //   121: invokevirtual 225	java/io/InputStream:close	()V
    //   124: aload_1
    //   125: athrow
    //   126: astore_0
    //   127: goto -3 -> 124
    //   130: astore_0
    //   131: goto -76 -> 55
    //   134: astore_1
    //   135: goto -19 -> 116
    //   138: astore_0
    //   139: aconst_null
    //   140: astore_0
    //   141: goto -43 -> 98
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	144	0	paramContext	Context
    //   0	144	1	paramString	String
    //   69	2	2	bool	boolean
    //   35	2	3	localBufferedReader	BufferedReader
    //   40	18	4	str	String
    //   87	3	4	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   78	82	84	java/lang/Exception
    //   57	70	87	java/lang/Exception
    //   20	36	97	java/lang/Exception
    //   36	42	97	java/lang/Exception
    //   89	94	97	java/lang/Exception
    //   102	106	109	java/lang/Exception
    //   9	16	113	finally
    //   120	124	126	java/lang/Exception
    //   51	55	130	java/lang/Exception
    //   20	36	134	finally
    //   36	42	134	finally
    //   57	70	134	finally
    //   89	94	134	finally
    //   9	16	138	java/lang/Exception
  }
  
  private static String c()
  {
    Object localObject3 = null;
    Object localObject2 = null;
    Object localObject1 = localObject3;
    for (;;)
    {
      try
      {
        localBufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("cat /proc/cpuinfo").getInputStream()));
        i = 1;
        if (i < 100) {
          continue;
        }
        localObject1 = localObject2;
      }
      catch (IOException localIOException)
      {
        BufferedReader localBufferedReader;
        int i;
        String str;
        int j;
        int k;
        continue;
      }
      localObject2 = localObject1;
      if (localObject1 != null) {
        localObject2 = ((String)localObject1).toLowerCase();
      }
      return (String)localObject2;
      localObject1 = localObject3;
      str = localBufferedReader.readLine();
      localObject1 = localObject2;
      if (str != null)
      {
        localObject1 = localObject3;
        str = str.toLowerCase();
        localObject1 = localObject3;
        j = str.indexOf("serial");
        localObject1 = localObject3;
        k = str.indexOf(":");
        if ((j > -1) && (k > 0))
        {
          localObject1 = localObject3;
          localObject2 = str.substring(k + 1);
          localObject1 = localObject2;
          localObject2 = ((String)localObject2).trim();
          localObject1 = localObject2;
        }
        else
        {
          i += 1;
        }
      }
    }
  }
  
  private static String c(Context paramContext)
  {
    try
    {
      paramContext = ((WifiManager)a(paramContext, "wifi")).getConnectionInfo();
      Object localObject;
      if (paramContext != null)
      {
        paramContext = paramContext.getMacAddress();
        localObject = paramContext;
        if (paramContext == null) {}
      }
      return null;
    }
    catch (Exception paramContext)
    {
      try
      {
        localObject = paramContext.replaceAll("-", "").replaceAll(":", "").toLowerCase();
        return (String)localObject;
      }
      catch (Exception localException)
      {
        return paramContext;
      }
      paramContext = paramContext;
      return null;
    }
  }
  
  private static String d(Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1 = null;
    if (Build.VERSION.SDK_INT >= 8) {
      localObject1 = localObject2;
    }
    try
    {
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      localObject1 = paramContext;
      if (paramContext != null)
      {
        localObject1 = paramContext;
        paramContext = paramContext.toLowerCase();
        localObject1 = paramContext;
      }
      return (String)localObject1;
    }
    catch (Exception paramContext) {}
    return (String)localObject1;
  }
}

/* Location:
 * Qualified Name:     anw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */