package com.meizu.statsapp.util;

import akc;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Stack;

public class Utils
{
  public static final String BUILD_MASK = getProperty("ro.build.mask.id");
  private static final char[] DIGITS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  public static final String PRODUCT_MODEL = ;
  private static final String TAG = "Utils";
  private static volatile String sIMEI;
  private static volatile String sMACAddress;
  private static volatile String sSN;
  
  public static String bytesToHexString(byte[] paramArrayOfByte)
  {
    int i = 0;
    if (paramArrayOfByte == null) {
      return "";
    }
    char[] arrayOfChar = new char[paramArrayOfByte.length * 2];
    int k = paramArrayOfByte.length;
    int j = 0;
    while (i < k)
    {
      int m = paramArrayOfByte[i];
      int n = j + 1;
      arrayOfChar[j] = DIGITS[(m >> 4 & 0xF)];
      j = n + 1;
      arrayOfChar[n] = DIGITS[(m & 0xF)];
      i += 1;
    }
    return new String(arrayOfChar);
  }
  
  /* Error */
  public static void copyDir(File paramFile1, File paramFile2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 5
    //   6: aconst_null
    //   7: astore 7
    //   9: aload_0
    //   10: invokevirtual 72	java/io/File:listFiles	()[Ljava/io/File;
    //   13: astore 9
    //   15: aload 9
    //   17: arraylength
    //   18: istore_3
    //   19: iconst_0
    //   20: istore_2
    //   21: aconst_null
    //   22: astore_0
    //   23: aload 7
    //   25: astore 6
    //   27: iload_2
    //   28: iload_3
    //   29: if_icmpge +280 -> 309
    //   32: aload 9
    //   34: iload_2
    //   35: aaload
    //   36: astore 10
    //   38: aload 6
    //   40: astore 8
    //   42: aload_0
    //   43: astore 7
    //   45: aload 10
    //   47: invokevirtual 76	java/io/File:isFile	()Z
    //   50: ifeq +160 -> 210
    //   53: aload 6
    //   55: astore 8
    //   57: aload_0
    //   58: astore 7
    //   60: aload 10
    //   62: invokevirtual 79	java/io/File:toString	()Ljava/lang/String;
    //   65: pop
    //   66: aload 6
    //   68: astore 8
    //   70: aload_0
    //   71: astore 7
    //   73: new 81	java/io/FileInputStream
    //   76: dup
    //   77: aload 10
    //   79: invokespecial 84	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   82: astore 5
    //   84: new 86	java/io/FileOutputStream
    //   87: dup
    //   88: new 88	java/lang/StringBuilder
    //   91: dup
    //   92: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   95: aload_1
    //   96: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   99: getstatic 96	java/io/File:separator	Ljava/lang/String;
    //   102: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: aload 10
    //   107: invokevirtual 102	java/io/File:getName	()Ljava/lang/String;
    //   110: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   116: invokespecial 106	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   119: astore 6
    //   121: sipush 4096
    //   124: newarray <illegal type>
    //   126: astore_0
    //   127: aload 5
    //   129: aload_0
    //   130: invokevirtual 110	java/io/FileInputStream:read	([B)I
    //   133: istore 4
    //   135: iload 4
    //   137: iconst_m1
    //   138: if_icmpeq +53 -> 191
    //   141: aload 6
    //   143: aload_0
    //   144: iconst_0
    //   145: iload 4
    //   147: invokevirtual 114	java/io/FileOutputStream:write	([BII)V
    //   150: goto -23 -> 127
    //   153: astore_1
    //   154: aload 6
    //   156: astore_0
    //   157: aload 5
    //   159: astore 6
    //   161: aload 6
    //   163: astore 8
    //   165: aload_0
    //   166: astore 7
    //   168: aload_1
    //   169: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   172: aload 6
    //   174: ifnull +8 -> 182
    //   177: aload 6
    //   179: invokevirtual 120	java/io/FileInputStream:close	()V
    //   182: aload_0
    //   183: ifnull +7 -> 190
    //   186: aload_0
    //   187: invokevirtual 121	java/io/FileOutputStream:close	()V
    //   190: return
    //   191: aload 6
    //   193: invokevirtual 124	java/io/FileOutputStream:flush	()V
    //   196: iload_2
    //   197: iconst_1
    //   198: iadd
    //   199: istore_2
    //   200: aload 6
    //   202: astore_0
    //   203: aload 5
    //   205: astore 6
    //   207: goto -180 -> 27
    //   210: aload 6
    //   212: astore 8
    //   214: aload_0
    //   215: astore 7
    //   217: aload 10
    //   219: invokevirtual 127	java/io/File:isDirectory	()Z
    //   222: ifeq +77 -> 299
    //   225: aload 6
    //   227: astore 8
    //   229: aload_0
    //   230: astore 7
    //   232: new 68	java/io/File
    //   235: dup
    //   236: new 88	java/lang/StringBuilder
    //   239: dup
    //   240: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   243: aload_1
    //   244: invokevirtual 131	java/io/File:getAbsoluteFile	()Ljava/io/File;
    //   247: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   250: getstatic 96	java/io/File:separator	Ljava/lang/String;
    //   253: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   256: aload 10
    //   258: invokevirtual 102	java/io/File:getName	()Ljava/lang/String;
    //   261: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   264: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   267: invokespecial 132	java/io/File:<init>	(Ljava/lang/String;)V
    //   270: astore 5
    //   272: aload 6
    //   274: astore 8
    //   276: aload_0
    //   277: astore 7
    //   279: aload 5
    //   281: invokestatic 136	com/meizu/statsapp/util/Utils:ensureDir	(Ljava/io/File;)Z
    //   284: pop
    //   285: aload 6
    //   287: astore 8
    //   289: aload_0
    //   290: astore 7
    //   292: aload 10
    //   294: aload 5
    //   296: invokestatic 138	com/meizu/statsapp/util/Utils:copyDir	(Ljava/io/File;Ljava/io/File;)V
    //   299: aload 6
    //   301: astore 5
    //   303: aload_0
    //   304: astore 6
    //   306: goto -110 -> 196
    //   309: aload 6
    //   311: ifnull +8 -> 319
    //   314: aload 6
    //   316: invokevirtual 120	java/io/FileInputStream:close	()V
    //   319: aload_0
    //   320: ifnull -130 -> 190
    //   323: aload_0
    //   324: invokevirtual 121	java/io/FileOutputStream:close	()V
    //   327: return
    //   328: astore_0
    //   329: aload_0
    //   330: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   333: return
    //   334: astore_1
    //   335: aload_1
    //   336: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   339: goto -20 -> 319
    //   342: astore_1
    //   343: aload_1
    //   344: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   347: goto -165 -> 182
    //   350: astore_0
    //   351: aload_0
    //   352: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   355: return
    //   356: astore_1
    //   357: aconst_null
    //   358: astore_0
    //   359: aload 5
    //   361: ifnull +8 -> 369
    //   364: aload 5
    //   366: invokevirtual 120	java/io/FileInputStream:close	()V
    //   369: aload_0
    //   370: ifnull +7 -> 377
    //   373: aload_0
    //   374: invokevirtual 121	java/io/FileOutputStream:close	()V
    //   377: aload_1
    //   378: athrow
    //   379: astore 5
    //   381: aload 5
    //   383: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   386: goto -17 -> 369
    //   389: astore_0
    //   390: aload_0
    //   391: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   394: goto -17 -> 377
    //   397: astore_1
    //   398: aload 6
    //   400: astore_0
    //   401: goto -42 -> 359
    //   404: astore_1
    //   405: aload 8
    //   407: astore 5
    //   409: aload 7
    //   411: astore_0
    //   412: goto -53 -> 359
    //   415: astore_1
    //   416: goto -57 -> 359
    //   419: astore_1
    //   420: aconst_null
    //   421: astore_0
    //   422: goto -261 -> 161
    //   425: astore_1
    //   426: goto -265 -> 161
    //   429: astore_1
    //   430: aload 5
    //   432: astore 6
    //   434: goto -273 -> 161
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	437	0	paramFile1	File
    //   0	437	1	paramFile2	File
    //   20	180	2	i	int
    //   18	12	3	j	int
    //   133	13	4	k	int
    //   4	361	5	localObject1	Object
    //   379	3	5	localIOException	IOException
    //   407	24	5	localObject2	Object
    //   1	432	6	localObject3	Object
    //   7	403	7	localFile1	File
    //   40	366	8	localObject4	Object
    //   13	20	9	arrayOfFile	File[]
    //   36	257	10	localFile2	File
    // Exception table:
    //   from	to	target	type
    //   121	127	153	java/io/IOException
    //   127	135	153	java/io/IOException
    //   141	150	153	java/io/IOException
    //   191	196	153	java/io/IOException
    //   323	327	328	java/io/IOException
    //   314	319	334	java/io/IOException
    //   177	182	342	java/io/IOException
    //   186	190	350	java/io/IOException
    //   9	19	356	finally
    //   364	369	379	java/io/IOException
    //   373	377	389	java/io/IOException
    //   121	127	397	finally
    //   127	135	397	finally
    //   141	150	397	finally
    //   191	196	397	finally
    //   45	53	404	finally
    //   60	66	404	finally
    //   73	84	404	finally
    //   168	172	404	finally
    //   217	225	404	finally
    //   232	272	404	finally
    //   279	285	404	finally
    //   292	299	404	finally
    //   84	121	415	finally
    //   9	19	419	java/io/IOException
    //   45	53	425	java/io/IOException
    //   60	66	425	java/io/IOException
    //   73	84	425	java/io/IOException
    //   217	225	425	java/io/IOException
    //   232	272	425	java/io/IOException
    //   279	285	425	java/io/IOException
    //   292	299	425	java/io/IOException
    //   84	121	429	java/io/IOException
  }
  
  private static boolean delDir(File paramFile)
  {
    Stack localStack = new Stack();
    if (!delDir(localStack, paramFile)) {
      return false;
    }
    while (localStack.size() > 0) {
      if (!delDir(localStack, (File)localStack.pop())) {
        return false;
      }
    }
    return true;
  }
  
  private static boolean delDir(Stack<File> paramStack, File paramFile)
  {
    File[] arrayOfFile = paramFile.listFiles();
    if ((arrayOfFile == null) || (arrayOfFile.length < 1))
    {
      if (!paramFile.delete())
      {
        System.out.println("delete dir " + paramFile + " unsuccessfully.");
        return false;
      }
      return true;
    }
    paramStack.push(paramFile);
    int j = arrayOfFile.length;
    int i = 0;
    if (i < j)
    {
      paramFile = arrayOfFile[i];
      if (paramFile.isDirectory()) {
        paramStack.push(paramFile);
      }
      while (paramFile.delete())
      {
        i += 1;
        break;
      }
      System.out.println("delete file " + paramFile.getAbsolutePath() + " unsuccessfully.");
      return false;
    }
    return true;
  }
  
  public static boolean delete(File paramFile)
  {
    boolean bool1;
    if (paramFile == null) {
      bool1 = false;
    }
    boolean bool2;
    do
    {
      return bool1;
      if (!paramFile.exists()) {
        return true;
      }
      if (paramFile.isDirectory()) {
        return delDir(paramFile);
      }
      bool2 = paramFile.delete();
      bool1 = bool2;
    } while (bool2);
    System.out.println("delete file " + paramFile + " unsuccessfully.");
    return bool2;
  }
  
  public static String dumpKernalLog()
  {
    StringBuilder localStringBuilder = new StringBuilder(1024);
    localStringBuilder.append("\n\n----------Kernal----------\n");
    localStringBuilder.append(executeCmd("dmesg"));
    return localStringBuilder.toString();
  }
  
  /* Error */
  public static String dumpLogcat(int paramInt)
  {
    // Byte code:
    //   0: new 88	java/lang/StringBuilder
    //   3: dup
    //   4: sipush 1024
    //   7: invokespecial 191	java/lang/StringBuilder:<init>	(I)V
    //   10: astore 4
    //   12: aload 4
    //   14: ldc -54
    //   16: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: pop
    //   20: aconst_null
    //   21: astore_1
    //   22: new 204	java/lang/ProcessBuilder
    //   25: dup
    //   26: bipush 12
    //   28: anewarray 59	java/lang/String
    //   31: dup
    //   32: iconst_0
    //   33: ldc -50
    //   35: aastore
    //   36: dup
    //   37: iconst_1
    //   38: ldc -48
    //   40: aastore
    //   41: dup
    //   42: iconst_2
    //   43: ldc -46
    //   45: aastore
    //   46: dup
    //   47: iconst_3
    //   48: ldc -44
    //   50: aastore
    //   51: dup
    //   52: iconst_4
    //   53: ldc -42
    //   55: aastore
    //   56: dup
    //   57: iconst_5
    //   58: ldc -44
    //   60: aastore
    //   61: dup
    //   62: bipush 6
    //   64: ldc -40
    //   66: aastore
    //   67: dup
    //   68: bipush 7
    //   70: ldc -44
    //   72: aastore
    //   73: dup
    //   74: bipush 8
    //   76: ldc -38
    //   78: aastore
    //   79: dup
    //   80: bipush 9
    //   82: ldc -36
    //   84: aastore
    //   85: dup
    //   86: bipush 10
    //   88: iload_0
    //   89: invokestatic 223	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   92: aastore
    //   93: dup
    //   94: bipush 11
    //   96: ldc -31
    //   98: aastore
    //   99: invokespecial 228	java/lang/ProcessBuilder:<init>	([Ljava/lang/String;)V
    //   102: iconst_1
    //   103: invokevirtual 232	java/lang/ProcessBuilder:redirectErrorStream	(Z)Ljava/lang/ProcessBuilder;
    //   106: invokevirtual 236	java/lang/ProcessBuilder:start	()Ljava/lang/Process;
    //   109: astore_2
    //   110: aload_2
    //   111: invokevirtual 242	java/lang/Process:getOutputStream	()Ljava/io/OutputStream;
    //   114: invokevirtual 245	java/io/OutputStream:close	()V
    //   117: aload_2
    //   118: invokevirtual 249	java/lang/Process:getErrorStream	()Ljava/io/InputStream;
    //   121: invokevirtual 252	java/io/InputStream:close	()V
    //   124: new 254	java/io/InputStreamReader
    //   127: dup
    //   128: aload_2
    //   129: invokevirtual 257	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   132: invokespecial 260	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   135: astore_2
    //   136: aload_2
    //   137: astore_1
    //   138: sipush 8192
    //   141: newarray <illegal type>
    //   143: astore_3
    //   144: aload_2
    //   145: astore_1
    //   146: aload_2
    //   147: aload_3
    //   148: invokevirtual 263	java/io/InputStreamReader:read	([C)I
    //   151: istore_0
    //   152: iload_0
    //   153: ifle +44 -> 197
    //   156: aload_2
    //   157: astore_1
    //   158: aload 4
    //   160: aload_3
    //   161: iconst_0
    //   162: iload_0
    //   163: invokevirtual 266	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
    //   166: pop
    //   167: goto -23 -> 144
    //   170: astore_3
    //   171: aload_2
    //   172: astore_1
    //   173: ldc 12
    //   175: ldc_w 268
    //   178: aload_3
    //   179: invokestatic 274	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   182: pop
    //   183: aload_2
    //   184: ifnull +7 -> 191
    //   187: aload_2
    //   188: invokevirtual 275	java/io/InputStreamReader:close	()V
    //   191: aload 4
    //   193: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   196: areturn
    //   197: aload_2
    //   198: ifnull -7 -> 191
    //   201: aload_2
    //   202: invokevirtual 275	java/io/InputStreamReader:close	()V
    //   205: goto -14 -> 191
    //   208: astore_1
    //   209: goto -18 -> 191
    //   212: astore_3
    //   213: aload_1
    //   214: astore_2
    //   215: aload_3
    //   216: astore_1
    //   217: aload_2
    //   218: ifnull +7 -> 225
    //   221: aload_2
    //   222: invokevirtual 275	java/io/InputStreamReader:close	()V
    //   225: aload_1
    //   226: athrow
    //   227: astore_1
    //   228: goto -37 -> 191
    //   231: astore_2
    //   232: goto -7 -> 225
    //   235: astore_3
    //   236: aload_1
    //   237: astore_2
    //   238: aload_3
    //   239: astore_1
    //   240: goto -23 -> 217
    //   243: astore_3
    //   244: aconst_null
    //   245: astore_2
    //   246: goto -75 -> 171
    //   249: astore_3
    //   250: goto -126 -> 124
    //   253: astore_3
    //   254: goto -137 -> 117
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	257	0	paramInt	int
    //   21	152	1	localObject1	Object
    //   208	6	1	localIOException1	IOException
    //   216	10	1	localObject2	Object
    //   227	10	1	localIOException2	IOException
    //   239	1	1	localObject3	Object
    //   109	113	2	localObject4	Object
    //   231	1	2	localIOException3	IOException
    //   237	9	2	localIOException4	IOException
    //   143	18	3	arrayOfChar	char[]
    //   170	9	3	localIOException5	IOException
    //   212	4	3	localObject5	Object
    //   235	4	3	localObject6	Object
    //   243	1	3	localIOException6	IOException
    //   249	1	3	localIOException7	IOException
    //   253	1	3	localIOException8	IOException
    //   10	182	4	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   138	144	170	java/io/IOException
    //   146	152	170	java/io/IOException
    //   158	167	170	java/io/IOException
    //   201	205	208	java/io/IOException
    //   22	110	212	finally
    //   110	117	212	finally
    //   117	124	212	finally
    //   124	136	212	finally
    //   187	191	227	java/io/IOException
    //   221	225	231	java/io/IOException
    //   138	144	235	finally
    //   146	152	235	finally
    //   158	167	235	finally
    //   173	183	235	finally
    //   22	110	243	java/io/IOException
    //   124	136	243	java/io/IOException
    //   117	124	249	java/io/IOException
    //   110	117	253	java/io/IOException
  }
  
  public static boolean ensureDir(File paramFile)
  {
    int i = 0;
    if (paramFile == null) {
      return false;
    }
    if (paramFile.exists()) {
      return true;
    }
    while ((i < 3) && (!paramFile.mkdirs())) {
      i += 1;
    }
    return paramFile.exists();
  }
  
  /* Error */
  public static String executeCmd(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +10 -> 11
    //   4: aload_0
    //   5: invokevirtual 283	java/lang/String:length	()I
    //   8: ifgt +5 -> 13
    //   11: aconst_null
    //   12: areturn
    //   13: new 88	java/lang/StringBuilder
    //   16: dup
    //   17: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   20: astore 5
    //   22: invokestatic 289	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   25: aload_0
    //   26: invokevirtual 293	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   29: astore_0
    //   30: aload_0
    //   31: ifnull +81 -> 112
    //   34: new 254	java/io/InputStreamReader
    //   37: dup
    //   38: aload_0
    //   39: invokevirtual 257	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   42: invokespecial 260	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   45: astore_3
    //   46: sipush 2048
    //   49: newarray <illegal type>
    //   51: astore 4
    //   53: aload_3
    //   54: aload 4
    //   56: invokevirtual 263	java/io/InputStreamReader:read	([C)I
    //   59: istore_1
    //   60: aload_3
    //   61: astore_2
    //   62: iload_1
    //   63: ifle +51 -> 114
    //   66: aload 5
    //   68: aload 4
    //   70: iconst_0
    //   71: iload_1
    //   72: invokevirtual 266	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
    //   75: pop
    //   76: goto -23 -> 53
    //   79: astore 4
    //   81: aload_3
    //   82: astore_2
    //   83: aload 4
    //   85: astore_3
    //   86: aload_3
    //   87: invokevirtual 294	java/lang/Exception:printStackTrace	()V
    //   90: aload_2
    //   91: ifnull +7 -> 98
    //   94: aload_2
    //   95: invokevirtual 275	java/io/InputStreamReader:close	()V
    //   98: aload_0
    //   99: ifnull +7 -> 106
    //   102: aload_0
    //   103: invokevirtual 297	java/lang/Process:destroy	()V
    //   106: aload 5
    //   108: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   111: areturn
    //   112: aconst_null
    //   113: astore_2
    //   114: aload_2
    //   115: ifnull +7 -> 122
    //   118: aload_2
    //   119: invokevirtual 275	java/io/InputStreamReader:close	()V
    //   122: aload_0
    //   123: ifnull -17 -> 106
    //   126: aload_0
    //   127: invokevirtual 297	java/lang/Process:destroy	()V
    //   130: goto -24 -> 106
    //   133: astore_2
    //   134: aconst_null
    //   135: astore_0
    //   136: aconst_null
    //   137: astore_3
    //   138: aload_3
    //   139: ifnull +7 -> 146
    //   142: aload_3
    //   143: invokevirtual 275	java/io/InputStreamReader:close	()V
    //   146: aload_0
    //   147: ifnull +7 -> 154
    //   150: aload_0
    //   151: invokevirtual 297	java/lang/Process:destroy	()V
    //   154: aload_2
    //   155: athrow
    //   156: astore_2
    //   157: goto -35 -> 122
    //   160: astore_2
    //   161: goto -63 -> 98
    //   164: astore_3
    //   165: goto -19 -> 146
    //   168: astore_2
    //   169: aconst_null
    //   170: astore_3
    //   171: goto -33 -> 138
    //   174: astore_2
    //   175: goto -37 -> 138
    //   178: astore 4
    //   180: aload_2
    //   181: astore_3
    //   182: aload 4
    //   184: astore_2
    //   185: goto -47 -> 138
    //   188: astore_3
    //   189: aconst_null
    //   190: astore_2
    //   191: aconst_null
    //   192: astore_0
    //   193: goto -107 -> 86
    //   196: astore_3
    //   197: aconst_null
    //   198: astore_2
    //   199: goto -113 -> 86
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	202	0	paramString	String
    //   59	13	1	i	int
    //   61	58	2	localObject1	Object
    //   133	22	2	localObject2	Object
    //   156	1	2	localIOException1	IOException
    //   160	1	2	localIOException2	IOException
    //   168	1	2	localObject3	Object
    //   174	7	2	localObject4	Object
    //   184	15	2	localObject5	Object
    //   45	98	3	localObject6	Object
    //   164	1	3	localIOException3	IOException
    //   170	12	3	localObject7	Object
    //   188	1	3	localException1	Exception
    //   196	1	3	localException2	Exception
    //   51	18	4	arrayOfChar	char[]
    //   79	5	4	localException3	Exception
    //   178	5	4	localObject8	Object
    //   20	87	5	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   46	53	79	java/lang/Exception
    //   53	60	79	java/lang/Exception
    //   66	76	79	java/lang/Exception
    //   22	30	133	finally
    //   118	122	156	java/io/IOException
    //   94	98	160	java/io/IOException
    //   142	146	164	java/io/IOException
    //   34	46	168	finally
    //   46	53	174	finally
    //   53	60	174	finally
    //   66	76	174	finally
    //   86	90	178	finally
    //   22	30	188	java/lang/Exception
    //   34	46	196	java/lang/Exception
  }
  
  /* Error */
  private static boolean gZipFile(File paramFile1, File paramFile2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: aload_1
    //   7: invokevirtual 302	java/io/File:createNewFile	()Z
    //   10: pop
    //   11: new 86	java/io/FileOutputStream
    //   14: dup
    //   15: aload_1
    //   16: invokespecial 303	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   19: astore_1
    //   20: new 305	java/util/zip/GZIPOutputStream
    //   23: dup
    //   24: new 307	java/io/BufferedOutputStream
    //   27: dup
    //   28: aload_1
    //   29: invokespecial 310	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   32: invokespecial 311	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   35: astore_3
    //   36: new 81	java/io/FileInputStream
    //   39: dup
    //   40: aload_0
    //   41: invokespecial 84	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   44: astore_0
    //   45: ldc_w 312
    //   48: newarray <illegal type>
    //   50: astore 4
    //   52: aload_0
    //   53: aload 4
    //   55: invokevirtual 110	java/io/FileInputStream:read	([B)I
    //   58: istore_2
    //   59: iload_2
    //   60: ifle +54 -> 114
    //   63: aload_3
    //   64: aload 4
    //   66: iconst_0
    //   67: iload_2
    //   68: invokevirtual 313	java/util/zip/GZIPOutputStream:write	([BII)V
    //   71: goto -19 -> 52
    //   74: astore 5
    //   76: aload_3
    //   77: astore 4
    //   79: aload 5
    //   81: astore_3
    //   82: aload_3
    //   83: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   86: aload 4
    //   88: ifnull +8 -> 96
    //   91: aload 4
    //   93: invokevirtual 314	java/util/zip/GZIPOutputStream:close	()V
    //   96: aload_1
    //   97: ifnull +7 -> 104
    //   100: aload_1
    //   101: invokevirtual 121	java/io/FileOutputStream:close	()V
    //   104: aload_0
    //   105: ifnull +7 -> 112
    //   108: aload_0
    //   109: invokevirtual 120	java/io/FileInputStream:close	()V
    //   112: iconst_0
    //   113: ireturn
    //   114: aload_3
    //   115: invokevirtual 315	java/util/zip/GZIPOutputStream:flush	()V
    //   118: aload_3
    //   119: ifnull +7 -> 126
    //   122: aload_3
    //   123: invokevirtual 314	java/util/zip/GZIPOutputStream:close	()V
    //   126: aload_1
    //   127: ifnull +7 -> 134
    //   130: aload_1
    //   131: invokevirtual 121	java/io/FileOutputStream:close	()V
    //   134: aload_0
    //   135: ifnull +7 -> 142
    //   138: aload_0
    //   139: invokevirtual 120	java/io/FileInputStream:close	()V
    //   142: iconst_1
    //   143: ireturn
    //   144: astore_3
    //   145: aload_3
    //   146: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   149: goto -23 -> 126
    //   152: astore_1
    //   153: aload_1
    //   154: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   157: goto -23 -> 134
    //   160: astore_0
    //   161: aload_0
    //   162: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   165: goto -23 -> 142
    //   168: astore_3
    //   169: aload_3
    //   170: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   173: goto -77 -> 96
    //   176: astore_1
    //   177: aload_1
    //   178: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   181: goto -77 -> 104
    //   184: astore_0
    //   185: aload_0
    //   186: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   189: iconst_0
    //   190: ireturn
    //   191: astore_0
    //   192: aconst_null
    //   193: astore_3
    //   194: aconst_null
    //   195: astore_1
    //   196: aload_3
    //   197: ifnull +7 -> 204
    //   200: aload_3
    //   201: invokevirtual 314	java/util/zip/GZIPOutputStream:close	()V
    //   204: aload_1
    //   205: ifnull +7 -> 212
    //   208: aload_1
    //   209: invokevirtual 121	java/io/FileOutputStream:close	()V
    //   212: aload 4
    //   214: ifnull +8 -> 222
    //   217: aload 4
    //   219: invokevirtual 120	java/io/FileInputStream:close	()V
    //   222: aload_0
    //   223: athrow
    //   224: astore_3
    //   225: aload_3
    //   226: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   229: goto -25 -> 204
    //   232: astore_1
    //   233: aload_1
    //   234: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   237: goto -25 -> 212
    //   240: astore_1
    //   241: aload_1
    //   242: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   245: goto -23 -> 222
    //   248: astore_0
    //   249: aconst_null
    //   250: astore_3
    //   251: goto -55 -> 196
    //   254: astore_0
    //   255: goto -59 -> 196
    //   258: astore 5
    //   260: aload_0
    //   261: astore 4
    //   263: aload 5
    //   265: astore_0
    //   266: goto -70 -> 196
    //   269: astore 5
    //   271: aload 4
    //   273: astore_3
    //   274: aload_0
    //   275: astore 4
    //   277: aload 5
    //   279: astore_0
    //   280: goto -84 -> 196
    //   283: astore_3
    //   284: aconst_null
    //   285: astore_0
    //   286: aconst_null
    //   287: astore_1
    //   288: aload 5
    //   290: astore 4
    //   292: goto -210 -> 82
    //   295: astore_3
    //   296: aconst_null
    //   297: astore_0
    //   298: aload 5
    //   300: astore 4
    //   302: goto -220 -> 82
    //   305: astore 5
    //   307: aconst_null
    //   308: astore_0
    //   309: aload_3
    //   310: astore 4
    //   312: aload 5
    //   314: astore_3
    //   315: goto -233 -> 82
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	318	0	paramFile1	File
    //   0	318	1	paramFile2	File
    //   58	10	2	i	int
    //   35	88	3	localObject1	Object
    //   144	2	3	localIOException1	IOException
    //   168	2	3	localIOException2	IOException
    //   193	8	3	localObject2	Object
    //   224	2	3	localIOException3	IOException
    //   250	24	3	localObject3	Object
    //   283	1	3	localIOException4	IOException
    //   295	15	3	localIOException5	IOException
    //   314	1	3	localIOException6	IOException
    //   1	310	4	localObject4	Object
    //   4	1	5	localObject5	Object
    //   74	6	5	localIOException7	IOException
    //   258	6	5	localObject6	Object
    //   269	30	5	localObject7	Object
    //   305	8	5	localIOException8	IOException
    // Exception table:
    //   from	to	target	type
    //   45	52	74	java/io/IOException
    //   52	59	74	java/io/IOException
    //   63	71	74	java/io/IOException
    //   114	118	74	java/io/IOException
    //   122	126	144	java/io/IOException
    //   130	134	152	java/io/IOException
    //   138	142	160	java/io/IOException
    //   91	96	168	java/io/IOException
    //   100	104	176	java/io/IOException
    //   108	112	184	java/io/IOException
    //   6	20	191	finally
    //   200	204	224	java/io/IOException
    //   208	212	232	java/io/IOException
    //   217	222	240	java/io/IOException
    //   20	36	248	finally
    //   36	45	254	finally
    //   45	52	258	finally
    //   52	59	258	finally
    //   63	71	258	finally
    //   114	118	258	finally
    //   82	86	269	finally
    //   6	20	283	java/io/IOException
    //   20	36	295	java/io/IOException
    //   36	45	305	java/io/IOException
  }
  
  public static long getAppCpuTime()
  {
    Object localObject1 = null;
    try
    {
      int i = Process.myPid();
      Object localObject2 = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + i + "/stat")), 1000);
      String str = ((BufferedReader)localObject2).readLine();
      ((BufferedReader)localObject2).close();
      localObject2 = str.split(" ");
      localObject1 = localObject2;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        long l1;
        long l2;
        long l3;
        localIOException.printStackTrace();
      }
    }
    l1 = Long.parseLong(localObject1[13]);
    l2 = Long.parseLong(localObject1[14]);
    l3 = Long.parseLong(localObject1[15]);
    return Long.parseLong(localObject1[16]) + (l1 + l2 + l3);
  }
  
  public static String getCellId(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext == null) {
      return null;
    }
    if (paramContext.getPhoneType() == 1)
    {
      paramContext = (GsmCellLocation)paramContext.getCellLocation();
      if (paramContext != null) {
        return "cid: " + paramContext.getCid() + ", lac: " + paramContext.getLac();
      }
    }
    return null;
  }
  
  public static String getCountry(Context paramContext)
  {
    if ((paramContext != null) && (paramContext.getResources().getConfiguration() != null) && (getResourcesgetConfigurationlocale != null)) {
      return getResourcesgetConfigurationlocale.getCountry();
    }
    return "";
  }
  
  public static String getCpuInfo()
  {
    return executeCmd("top -d 1 -n 1");
  }
  
  public static String getCpuUsage()
  {
    return String.valueOf((int)getProcessCpuRate());
  }
  
  public static long getDataDirAvailableSize()
  {
    if (Build.VERSION.SDK_INT >= 18) {
      return new StatFs(Environment.getDataDirectory().getAbsolutePath()).getAvailableBytes();
    }
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getAvailableBlocks() * l;
  }
  
  /* Error */
  public static byte[] getFileMd5(File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: ifnonnull +8 -> 12
    //   7: aload 4
    //   9: astore_3
    //   10: aload_3
    //   11: areturn
    //   12: aload 4
    //   14: astore_3
    //   15: aload_0
    //   16: invokevirtual 185	java/io/File:exists	()Z
    //   19: ifeq -9 -> 10
    //   22: new 81	java/io/FileInputStream
    //   25: dup
    //   26: aload_0
    //   27: invokespecial 84	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   30: astore_2
    //   31: aload_2
    //   32: astore_0
    //   33: ldc_w 443
    //   36: invokestatic 449	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   39: astore_3
    //   40: aload_2
    //   41: astore_0
    //   42: ldc_w 450
    //   45: newarray <illegal type>
    //   47: astore 5
    //   49: aload_2
    //   50: astore_0
    //   51: aload_2
    //   52: aload 5
    //   54: invokevirtual 110	java/io/FileInputStream:read	([B)I
    //   57: istore_1
    //   58: iload_1
    //   59: iconst_m1
    //   60: if_icmpeq +43 -> 103
    //   63: aload_2
    //   64: astore_0
    //   65: aload_3
    //   66: aload 5
    //   68: iconst_0
    //   69: iload_1
    //   70: invokevirtual 453	java/security/MessageDigest:update	([BII)V
    //   73: goto -24 -> 49
    //   76: astore_3
    //   77: aload_2
    //   78: astore_0
    //   79: aload_3
    //   80: invokevirtual 454	java/io/FileNotFoundException:printStackTrace	()V
    //   83: aload 4
    //   85: astore_3
    //   86: aload_2
    //   87: ifnull -77 -> 10
    //   90: aload_2
    //   91: invokevirtual 120	java/io/FileInputStream:close	()V
    //   94: aconst_null
    //   95: areturn
    //   96: astore_0
    //   97: aload_0
    //   98: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   101: aconst_null
    //   102: areturn
    //   103: aload_2
    //   104: astore_0
    //   105: aload_3
    //   106: invokevirtual 458	java/security/MessageDigest:digest	()[B
    //   109: astore_3
    //   110: aload_3
    //   111: astore_0
    //   112: aload_0
    //   113: astore_3
    //   114: aload_2
    //   115: ifnull -105 -> 10
    //   118: aload_2
    //   119: invokevirtual 120	java/io/FileInputStream:close	()V
    //   122: aload_0
    //   123: areturn
    //   124: astore_2
    //   125: aload_2
    //   126: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   129: aload_0
    //   130: areturn
    //   131: astore_3
    //   132: aconst_null
    //   133: astore_2
    //   134: aload_2
    //   135: astore_0
    //   136: aload_3
    //   137: invokevirtual 459	java/security/NoSuchAlgorithmException:printStackTrace	()V
    //   140: aload 4
    //   142: astore_3
    //   143: aload_2
    //   144: ifnull -134 -> 10
    //   147: aload_2
    //   148: invokevirtual 120	java/io/FileInputStream:close	()V
    //   151: aconst_null
    //   152: areturn
    //   153: astore_0
    //   154: aload_0
    //   155: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   158: aconst_null
    //   159: areturn
    //   160: astore_3
    //   161: aconst_null
    //   162: astore_2
    //   163: aload_2
    //   164: astore_0
    //   165: aload_3
    //   166: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   169: aload 4
    //   171: astore_3
    //   172: aload_2
    //   173: ifnull -163 -> 10
    //   176: aload_2
    //   177: invokevirtual 120	java/io/FileInputStream:close	()V
    //   180: aconst_null
    //   181: areturn
    //   182: astore_0
    //   183: aload_0
    //   184: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   187: aconst_null
    //   188: areturn
    //   189: astore_2
    //   190: aconst_null
    //   191: astore_0
    //   192: aload_0
    //   193: ifnull +7 -> 200
    //   196: aload_0
    //   197: invokevirtual 120	java/io/FileInputStream:close	()V
    //   200: aload_2
    //   201: athrow
    //   202: astore_0
    //   203: aload_0
    //   204: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   207: goto -7 -> 200
    //   210: astore_2
    //   211: goto -19 -> 192
    //   214: astore_3
    //   215: goto -52 -> 163
    //   218: astore_3
    //   219: goto -85 -> 134
    //   222: astore_3
    //   223: aconst_null
    //   224: astore_2
    //   225: goto -148 -> 77
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	228	0	paramFile	File
    //   57	13	1	i	int
    //   30	89	2	localFileInputStream	FileInputStream
    //   124	2	2	localIOException1	IOException
    //   133	44	2	localObject1	Object
    //   189	12	2	localObject2	Object
    //   210	1	2	localObject3	Object
    //   224	1	2	localObject4	Object
    //   9	57	3	localObject5	Object
    //   76	4	3	localFileNotFoundException1	java.io.FileNotFoundException
    //   85	29	3	localObject6	Object
    //   131	6	3	localNoSuchAlgorithmException1	NoSuchAlgorithmException
    //   142	1	3	localObject7	Object
    //   160	6	3	localIOException2	IOException
    //   171	1	3	localObject8	Object
    //   214	1	3	localIOException3	IOException
    //   218	1	3	localNoSuchAlgorithmException2	NoSuchAlgorithmException
    //   222	1	3	localFileNotFoundException2	java.io.FileNotFoundException
    //   1	169	4	localObject9	Object
    //   47	20	5	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   33	40	76	java/io/FileNotFoundException
    //   42	49	76	java/io/FileNotFoundException
    //   51	58	76	java/io/FileNotFoundException
    //   65	73	76	java/io/FileNotFoundException
    //   105	110	76	java/io/FileNotFoundException
    //   90	94	96	java/io/IOException
    //   118	122	124	java/io/IOException
    //   22	31	131	java/security/NoSuchAlgorithmException
    //   147	151	153	java/io/IOException
    //   22	31	160	java/io/IOException
    //   176	180	182	java/io/IOException
    //   22	31	189	finally
    //   196	200	202	java/io/IOException
    //   33	40	210	finally
    //   42	49	210	finally
    //   51	58	210	finally
    //   65	73	210	finally
    //   79	83	210	finally
    //   105	110	210	finally
    //   136	140	210	finally
    //   165	169	210	finally
    //   33	40	214	java/io/IOException
    //   42	49	214	java/io/IOException
    //   51	58	214	java/io/IOException
    //   65	73	214	java/io/IOException
    //   105	110	214	java/io/IOException
    //   33	40	218	java/security/NoSuchAlgorithmException
    //   42	49	218	java/security/NoSuchAlgorithmException
    //   51	58	218	java/security/NoSuchAlgorithmException
    //   65	73	218	java/security/NoSuchAlgorithmException
    //   105	110	218	java/security/NoSuchAlgorithmException
    //   22	31	222	java/io/FileNotFoundException
  }
  
  public static byte[] getFileMd5(String paramString)
  {
    if (isEmpty(paramString)) {
      return null;
    }
    return getFileMd5(new File(paramString));
  }
  
  public static String getFlymeUid(Context paramContext)
  {
    try
    {
      paramContext = ((AccountManager)paramContext.getSystemService("account")).getAccountsByType("com.meizu.account");
      if ((paramContext != null) && (paramContext.length > 0) && (paramContext[0] != null))
      {
        paramContext = 0name;
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static String getIMEI(Context paramContext)
  {
    if (!isEmpty(sIMEI)) {
      return sIMEI;
    }
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext == null) {
        return "";
      }
      paramContext = paramContext.getDeviceId();
      if (!isEmpty(paramContext)) {
        sIMEI = paramContext;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    return sIMEI;
  }
  
  private static String getLine(String paramString, int paramInt)
  {
    int k = 0;
    if ((paramString == null) || (paramInt < 1)) {
      return null;
    }
    int i = -1;
    int j = 0;
    for (;;)
    {
      int m = i;
      if (j < paramString.length())
      {
        m = i;
        if (k < paramInt)
        {
          i = paramString.indexOf('\n', j);
          if (-1 != i) {
            break label82;
          }
          m = i;
        }
      }
      if ((k != paramInt - 1) || (-1 != m) || (j >= paramString.length())) {
        break;
      }
      return paramString.substring(j);
      label82:
      if (k == paramInt - 1)
      {
        if (i > j) {
          return paramString.substring(j, i - 1);
        }
        return null;
      }
      j = i + 1;
      k += 1;
    }
    return null;
  }
  
  public static String getLocation(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    try
    {
      paramContext = (LocationManager)paramContext.getSystemService("location");
      if (paramContext == null) {
        return null;
      }
      paramContext = paramContext.getLastKnownLocation("network");
      if (paramContext == null) {
        return null;
      }
      paramContext = String.format("%.6f, %.6f", new Object[] { Double.valueOf(paramContext.getLatitude()), Double.valueOf(paramContext.getLongitude()) });
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String getMACAddress(Context paramContext)
  {
    Object localObject = null;
    if (!isEmpty(sMACAddress)) {
      paramContext = sMACAddress;
    }
    WifiManager localWifiManager;
    do
    {
      return paramContext;
      localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
      paramContext = (Context)localObject;
    } while (localWifiManager == null);
    paramContext = localWifiManager.getConnectionInfo();
    if (paramContext == null) {}
    for (paramContext = null;; paramContext = paramContext.getMacAddress())
    {
      if (!isEmpty(paramContext)) {
        sMACAddress = paramContext;
      }
      return sMACAddress;
    }
  }
  
  public static byte[] getMD5(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length < 1)) {
      return null;
    }
    try
    {
      paramArrayOfByte = MessageDigest.getInstance("MD5").digest(paramArrayOfByte);
      return paramArrayOfByte;
    }
    catch (NoSuchAlgorithmException paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
    }
    return null;
  }
  
  public static String getMemInfo(String paramString)
  {
    return executeCmd("dumpsys meminfo " + paramString);
  }
  
  public static String getMemUsage()
  {
    Object localObject2 = executeCmd("cat /proc/meminfo");
    String str = String.valueOf(-1);
    if (localObject2 == null) {}
    for (;;)
    {
      return str;
      Object localObject1 = getLine((String)localObject2, 1);
      localObject2 = getLine((String)localObject2, 2);
      if ((localObject1 != null) && (localObject2 != null))
      {
        localObject1 = ((String)localObject1).split(" +");
        localObject2 = ((String)localObject2).split(" +");
        if ((localObject1 != null) && (localObject1.length >= 3) && (localObject2 != null) && (localObject2.length >= 3)) {
          try
          {
            long l1 = Long.parseLong(localObject1[1]);
            long l2 = Long.parseLong(localObject2[1]);
            if (l1 != 0L)
            {
              int i = (int)((l1 - l2 * 1.0D) * 100.0D / l1);
              return String.valueOf(i);
            }
          }
          catch (Exception localException) {}
        }
      }
    }
    return str;
  }
  
  public static String[] getMtkRestartReason()
  {
    Object localObject2 = null;
    String str = executeCmd("cat /proc/aed/reboot-reason");
    Object localObject1 = localObject2;
    int i;
    int j;
    int k;
    int m;
    if (!TextUtils.isEmpty(str))
    {
      i = str.indexOf("WDT status:");
      j = str.indexOf("fiq step:");
      k = str.indexOf("exception type:");
      m = str.indexOf("\n");
      if ((-1 == i) || (-1 == j) || (-1 != k)) {
        break label125;
      }
      localObject1 = new String[2];
      localObject1[0] = str.substring("WDT status:".length() + i, j).trim();
      localObject1[1] = str.substring("fiq step:".length() + j, m).trim();
    }
    label125:
    do
    {
      do
      {
        do
        {
          do
          {
            return (String[])localObject1;
            localObject1 = localObject2;
          } while (-1 == i);
          localObject1 = localObject2;
        } while (-1 == j);
        localObject1 = localObject2;
      } while (-1 == k);
      localObject1 = localObject2;
    } while (k >= m);
    return new String[] { str.substring("WDT status:".length() + i, j).trim(), str.substring("fiq step:".length() + j, k).trim() };
  }
  
  public static String getNetworkType(Context paramContext)
  {
    try
    {
      Object localObject = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (localObject != null)
      {
        localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
        if ((localObject != null) && (((NetworkInfo)localObject).isAvailable()))
        {
          if (((NetworkInfo)localObject).getType() == 1) {
            return "wifi";
          }
          paramContext = (TelephonyManager)paramContext.getSystemService("phone");
          if ((paramContext.getNetworkType() == 1) || (paramContext.getNetworkType() == 2)) {
            break label99;
          }
          if (paramContext.getNetworkType() != 13) {
            break label103;
          }
          return "4g";
        }
        return "off";
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "unknown";
    label99:
    return "2g";
    label103:
    return "3g";
  }
  
  public static String getOperater(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if ((paramContext != null) && (5 == paramContext.getSimState())) {
      return paramContext.getSimOperator();
    }
    return "";
  }
  
  public static float getProcessCpuRate()
  {
    float f1 = (float)getTotalCpuTime();
    float f2 = (float)getAppCpuTime();
    try
    {
      Thread.sleep(360L);
      float f3 = (float)getTotalCpuTime();
      return ((float)getAppCpuTime() - f2) * 100.0F / (f3 - f1);
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  private static String getProductModel()
  {
    String str2 = getProperty("ro.meizu.product.model");
    String str1 = str2;
    if (isEmpty(str2)) {
      str1 = Build.MODEL;
    }
    return str1;
  }
  
  private static String getProperty(String paramString)
  {
    try
    {
      paramString = akc.a("android.os.SystemProperties", "get", new Object[] { paramString, "" });
      if (paramString == null) {
        return null;
      }
      paramString = paramString.toString();
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static String getSN()
  {
    Object localObject;
    if (!isEmpty(sSN)) {
      localObject = sSN;
    }
    String str;
    do
    {
      return (String)localObject;
      str = getProperty("ro.serialno");
      localObject = str;
    } while (isEmpty(str));
    sSN = str;
    return str;
  }
  
  public static String getSamsungKernalBootReason()
  {
    Object localObject2 = null;
    String str = executeCmd("cat /proc/reset_reason");
    int i = str.indexOf("Last boot from: ");
    int j = str.indexOf(",");
    Object localObject1 = localObject2;
    if (-1 != i)
    {
      localObject1 = localObject2;
      if (-1 != j)
      {
        localObject1 = localObject2;
        if (i < j) {
          localObject1 = str.substring(i, j);
        }
      }
    }
    return (String)localObject1;
  }
  
  public static String getSamsungRestartReason()
  {
    String str2 = executeCmd("cat /proc/reset_reason");
    String str1 = str2;
    if (!TextUtils.isEmpty(str2))
    {
      int i = str2.indexOf("Last boot from:");
      int j = str2.indexOf(",");
      str1 = str2;
      if (-1 != i)
      {
        str1 = str2;
        if (-1 != j) {
          str1 = str2.substring("Last boot from:".length() + i, j).trim();
        }
      }
    }
    return str1;
  }
  
  public static long getTotalCpuTime()
  {
    Object localObject1 = null;
    try
    {
      Object localObject2 = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/stat")), 1000);
      String str = ((BufferedReader)localObject2).readLine();
      ((BufferedReader)localObject2).close();
      localObject2 = str.split(" ");
      localObject1 = localObject2;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        long l1;
        long l2;
        long l3;
        long l4;
        long l5;
        long l6;
        localIOException.printStackTrace();
      }
    }
    l1 = Long.parseLong(localObject1[2]);
    l2 = Long.parseLong(localObject1[3]);
    l3 = Long.parseLong(localObject1[4]);
    l4 = Long.parseLong(localObject1[6]);
    l5 = Long.parseLong(localObject1[5]);
    l6 = Long.parseLong(localObject1[7]);
    return Long.parseLong(localObject1[8]) + (l1 + l2 + l3 + l4 + l5 + l6);
  }
  
  /* Error */
  public static byte[] gzip(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: new 674	java/io/ByteArrayOutputStream
    //   9: dup
    //   10: invokespecial 675	java/io/ByteArrayOutputStream:<init>	()V
    //   13: astore_3
    //   14: new 305	java/util/zip/GZIPOutputStream
    //   17: dup
    //   18: aload_3
    //   19: invokespecial 311	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   22: astore_2
    //   23: aload_2
    //   24: astore_1
    //   25: aload_2
    //   26: aload_0
    //   27: iconst_0
    //   28: aload_0
    //   29: arraylength
    //   30: invokevirtual 313	java/util/zip/GZIPOutputStream:write	([BII)V
    //   33: aload_2
    //   34: astore_1
    //   35: aload_2
    //   36: invokevirtual 315	java/util/zip/GZIPOutputStream:flush	()V
    //   39: aload_2
    //   40: ifnull +7 -> 47
    //   43: aload_2
    //   44: invokevirtual 314	java/util/zip/GZIPOutputStream:close	()V
    //   47: aload_3
    //   48: invokevirtual 678	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   51: astore_0
    //   52: aload_3
    //   53: invokevirtual 679	java/io/ByteArrayOutputStream:close	()V
    //   56: aload_0
    //   57: areturn
    //   58: astore_1
    //   59: aload_1
    //   60: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   63: aload_0
    //   64: areturn
    //   65: astore_0
    //   66: aload_0
    //   67: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   70: goto -23 -> 47
    //   73: astore_3
    //   74: aconst_null
    //   75: astore_0
    //   76: aload_0
    //   77: astore_1
    //   78: aload_3
    //   79: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   82: aload_0
    //   83: ifnull -79 -> 4
    //   86: aload_0
    //   87: invokevirtual 314	java/util/zip/GZIPOutputStream:close	()V
    //   90: aconst_null
    //   91: areturn
    //   92: astore_0
    //   93: aload_0
    //   94: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   97: aconst_null
    //   98: areturn
    //   99: astore_0
    //   100: aconst_null
    //   101: astore_1
    //   102: aload_1
    //   103: ifnull +7 -> 110
    //   106: aload_1
    //   107: invokevirtual 314	java/util/zip/GZIPOutputStream:close	()V
    //   110: aload_0
    //   111: athrow
    //   112: astore_1
    //   113: aload_1
    //   114: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   117: goto -7 -> 110
    //   120: astore_0
    //   121: goto -19 -> 102
    //   124: astore_3
    //   125: aload_2
    //   126: astore_0
    //   127: goto -51 -> 76
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	130	0	paramArrayOfByte	byte[]
    //   24	11	1	localGZIPOutputStream1	java.util.zip.GZIPOutputStream
    //   58	2	1	localIOException1	IOException
    //   77	30	1	arrayOfByte	byte[]
    //   112	2	1	localIOException2	IOException
    //   22	104	2	localGZIPOutputStream2	java.util.zip.GZIPOutputStream
    //   13	40	3	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    //   73	6	3	localIOException3	IOException
    //   124	1	3	localIOException4	IOException
    // Exception table:
    //   from	to	target	type
    //   52	56	58	java/io/IOException
    //   43	47	65	java/io/IOException
    //   14	23	73	java/io/IOException
    //   86	90	92	java/io/IOException
    //   14	23	99	finally
    //   106	110	112	java/io/IOException
    //   25	33	120	finally
    //   35	39	120	finally
    //   78	82	120	finally
    //   25	33	124	java/io/IOException
    //   35	39	124	java/io/IOException
  }
  
  public static boolean isEmpty(String paramString)
  {
    return (paramString == null) || (paramString.length() < 1);
  }
  
  public static boolean isNetworkWorking(Context paramContext)
  {
    Object localObject = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (localObject == null) {
      return false;
    }
    paramContext = ((ConnectivityManager)localObject).getNetworkInfo(1);
    localObject = ((ConnectivityManager)localObject).getNetworkInfo(0);
    return ((paramContext != null) && (NetworkInfo.State.CONNECTED == paramContext.getState())) || ((localObject != null) && (NetworkInfo.State.CONNECTED == ((NetworkInfo)localObject).getState()));
  }
  
  public static boolean isRoot(Context paramContext)
  {
    paramContext = paramContext.getSystemService("deivce_states");
    if (paramContext == null) {
      return false;
    }
    try
    {
      Method localMethod = paramContext.getClass().getMethod("doCheckState", new Class[] { Integer.TYPE });
      if (localMethod == null) {
        return false;
      }
      localMethod.setAccessible(true);
      paramContext = (Integer)localMethod.invoke(paramContext, new Object[] { Integer.valueOf(1) });
      if (paramContext != null)
      {
        int i = paramContext.intValue();
        if (1 == i) {
          return true;
        }
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean isWiFiWorking(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.getNetworkInfo(1);
    return (paramContext != null) && (NetworkInfo.State.CONNECTED == paramContext.getState());
  }
  
  public static File readFileFromDropbox(String paramString)
  {
    File localFile = new File("/data/system/dropbox/");
    if (!localFile.exists()) {
      return null;
    }
    File[] arrayOfFile = localFile.listFiles();
    int j = arrayOfFile.length;
    int i = 0;
    if (i < j)
    {
      localFile = arrayOfFile[i];
      if (!localFile.getName().contains(paramString)) {}
    }
    for (paramString = localFile;; paramString = null)
    {
      return paramString;
      i += 1;
      break;
    }
  }
  
  /* Error */
  public static void writeFileAppend(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 744	java/io/BufferedWriter
    //   5: dup
    //   6: new 746	java/io/OutputStreamWriter
    //   9: dup
    //   10: new 86	java/io/FileOutputStream
    //   13: dup
    //   14: aload_0
    //   15: iconst_1
    //   16: invokespecial 749	java/io/FileOutputStream:<init>	(Ljava/lang/String;Z)V
    //   19: invokespecial 750	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
    //   22: invokespecial 753	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   25: astore_2
    //   26: aload_2
    //   27: astore_0
    //   28: aload_2
    //   29: aload_1
    //   30: invokevirtual 755	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   33: aload_2
    //   34: invokevirtual 756	java/io/BufferedWriter:close	()V
    //   37: return
    //   38: astore_0
    //   39: aload_0
    //   40: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   43: return
    //   44: astore_3
    //   45: aconst_null
    //   46: astore_1
    //   47: aload_1
    //   48: astore_0
    //   49: aload_3
    //   50: invokevirtual 294	java/lang/Exception:printStackTrace	()V
    //   53: aload_1
    //   54: invokevirtual 756	java/io/BufferedWriter:close	()V
    //   57: return
    //   58: astore_0
    //   59: aload_0
    //   60: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   63: return
    //   64: astore_0
    //   65: aload_3
    //   66: astore_1
    //   67: aload_1
    //   68: invokevirtual 756	java/io/BufferedWriter:close	()V
    //   71: aload_0
    //   72: athrow
    //   73: astore_1
    //   74: aload_1
    //   75: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   78: goto -7 -> 71
    //   81: astore_2
    //   82: aload_0
    //   83: astore_1
    //   84: aload_2
    //   85: astore_0
    //   86: goto -19 -> 67
    //   89: astore_3
    //   90: aload_2
    //   91: astore_1
    //   92: goto -45 -> 47
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	95	0	paramString1	String
    //   0	95	1	paramString2	String
    //   25	9	2	localBufferedWriter	java.io.BufferedWriter
    //   81	10	2	localObject1	Object
    //   1	1	3	localObject2	Object
    //   44	22	3	localException1	Exception
    //   89	1	3	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   33	37	38	java/io/IOException
    //   2	26	44	java/lang/Exception
    //   53	57	58	java/io/IOException
    //   2	26	64	finally
    //   67	71	73	java/io/IOException
    //   28	33	81	finally
    //   49	53	81	finally
    //   28	33	89	java/lang/Exception
  }
  
  /* Error */
  private static boolean zipDir(File paramFile1, File paramFile2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 4
    //   6: new 141	java/util/Stack
    //   9: dup
    //   10: invokespecial 142	java/util/Stack:<init>	()V
    //   13: astore 6
    //   15: aload_0
    //   16: invokevirtual 760	java/io/File:getParent	()Ljava/lang/String;
    //   19: astore_3
    //   20: aload_3
    //   21: ifnonnull +310 -> 331
    //   24: ldc_w 762
    //   27: astore_3
    //   28: aload_1
    //   29: invokevirtual 302	java/io/File:createNewFile	()Z
    //   32: pop
    //   33: new 86	java/io/FileOutputStream
    //   36: dup
    //   37: aload_1
    //   38: invokespecial 303	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   41: astore_1
    //   42: new 764	java/util/zip/ZipOutputStream
    //   45: dup
    //   46: new 307	java/io/BufferedOutputStream
    //   49: dup
    //   50: aload_1
    //   51: invokespecial 310	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   54: invokespecial 765	java/util/zip/ZipOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   57: astore 4
    //   59: aload 6
    //   61: aload_3
    //   62: aload_0
    //   63: aload 4
    //   65: invokestatic 768	com/meizu/statsapp/util/Utils:zipDir	(Ljava/util/Stack;Ljava/lang/String;Ljava/io/File;Ljava/util/zip/ZipOutputStream;)Z
    //   68: istore_2
    //   69: iload_2
    //   70: ifne +39 -> 109
    //   73: aload 4
    //   75: ifnull +8 -> 83
    //   78: aload 4
    //   80: invokevirtual 769	java/util/zip/ZipOutputStream:close	()V
    //   83: aload_1
    //   84: ifnull +7 -> 91
    //   87: aload_1
    //   88: invokevirtual 121	java/io/FileOutputStream:close	()V
    //   91: iconst_0
    //   92: ireturn
    //   93: astore_0
    //   94: aload_0
    //   95: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   98: goto -15 -> 83
    //   101: astore_0
    //   102: aload_0
    //   103: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   106: goto -15 -> 91
    //   109: aload 6
    //   111: invokevirtual 149	java/util/Stack:size	()I
    //   114: ifle +60 -> 174
    //   117: aload 6
    //   119: aload_3
    //   120: aload 6
    //   122: invokevirtual 153	java/util/Stack:pop	()Ljava/lang/Object;
    //   125: checkcast 68	java/io/File
    //   128: aload 4
    //   130: invokestatic 768	com/meizu/statsapp/util/Utils:zipDir	(Ljava/util/Stack;Ljava/lang/String;Ljava/io/File;Ljava/util/zip/ZipOutputStream;)Z
    //   133: istore_2
    //   134: iload_2
    //   135: ifne -26 -> 109
    //   138: aload 4
    //   140: ifnull +8 -> 148
    //   143: aload 4
    //   145: invokevirtual 769	java/util/zip/ZipOutputStream:close	()V
    //   148: aload_1
    //   149: ifnull +7 -> 156
    //   152: aload_1
    //   153: invokevirtual 121	java/io/FileOutputStream:close	()V
    //   156: iconst_0
    //   157: ireturn
    //   158: astore_0
    //   159: aload_0
    //   160: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   163: goto -15 -> 148
    //   166: astore_0
    //   167: aload_0
    //   168: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   171: goto -15 -> 156
    //   174: aload 4
    //   176: ifnull +8 -> 184
    //   179: aload 4
    //   181: invokevirtual 769	java/util/zip/ZipOutputStream:close	()V
    //   184: aload_1
    //   185: ifnull +7 -> 192
    //   188: aload_1
    //   189: invokevirtual 121	java/io/FileOutputStream:close	()V
    //   192: iconst_1
    //   193: ireturn
    //   194: astore_0
    //   195: aload_0
    //   196: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   199: goto -15 -> 184
    //   202: astore_0
    //   203: aload_0
    //   204: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   207: goto -15 -> 192
    //   210: astore_3
    //   211: aconst_null
    //   212: astore_0
    //   213: aload 4
    //   215: astore_1
    //   216: aload_3
    //   217: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   220: aload_0
    //   221: ifnull +7 -> 228
    //   224: aload_0
    //   225: invokevirtual 769	java/util/zip/ZipOutputStream:close	()V
    //   228: aload_1
    //   229: ifnull +7 -> 236
    //   232: aload_1
    //   233: invokevirtual 121	java/io/FileOutputStream:close	()V
    //   236: iconst_0
    //   237: ireturn
    //   238: astore_0
    //   239: aload_0
    //   240: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   243: goto -15 -> 228
    //   246: astore_0
    //   247: aload_0
    //   248: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   251: goto -15 -> 236
    //   254: astore_0
    //   255: aconst_null
    //   256: astore_1
    //   257: aload 5
    //   259: astore_3
    //   260: aload_3
    //   261: ifnull +7 -> 268
    //   264: aload_3
    //   265: invokevirtual 769	java/util/zip/ZipOutputStream:close	()V
    //   268: aload_1
    //   269: ifnull +7 -> 276
    //   272: aload_1
    //   273: invokevirtual 121	java/io/FileOutputStream:close	()V
    //   276: aload_0
    //   277: athrow
    //   278: astore_3
    //   279: aload_3
    //   280: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   283: goto -15 -> 268
    //   286: astore_1
    //   287: aload_1
    //   288: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   291: goto -15 -> 276
    //   294: astore_0
    //   295: aload 5
    //   297: astore_3
    //   298: goto -38 -> 260
    //   301: astore_0
    //   302: aload 4
    //   304: astore_3
    //   305: goto -45 -> 260
    //   308: astore 4
    //   310: aload_0
    //   311: astore_3
    //   312: aload 4
    //   314: astore_0
    //   315: goto -55 -> 260
    //   318: astore_3
    //   319: aconst_null
    //   320: astore_0
    //   321: goto -105 -> 216
    //   324: astore_3
    //   325: aload 4
    //   327: astore_0
    //   328: goto -112 -> 216
    //   331: goto -303 -> 28
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	334	0	paramFile1	File
    //   0	334	1	paramFile2	File
    //   68	67	2	bool	boolean
    //   19	101	3	str	String
    //   210	7	3	localIOException1	IOException
    //   259	6	3	localObject1	Object
    //   278	2	3	localIOException2	IOException
    //   297	15	3	localObject2	Object
    //   318	1	3	localIOException3	IOException
    //   324	1	3	localIOException4	IOException
    //   4	299	4	localZipOutputStream	java.util.zip.ZipOutputStream
    //   308	18	4	localObject3	Object
    //   1	295	5	localObject4	Object
    //   13	108	6	localStack	Stack
    // Exception table:
    //   from	to	target	type
    //   78	83	93	java/io/IOException
    //   87	91	101	java/io/IOException
    //   143	148	158	java/io/IOException
    //   152	156	166	java/io/IOException
    //   179	184	194	java/io/IOException
    //   188	192	202	java/io/IOException
    //   28	42	210	java/io/IOException
    //   224	228	238	java/io/IOException
    //   232	236	246	java/io/IOException
    //   28	42	254	finally
    //   264	268	278	java/io/IOException
    //   272	276	286	java/io/IOException
    //   42	59	294	finally
    //   59	69	301	finally
    //   109	134	301	finally
    //   216	220	308	finally
    //   42	59	318	java/io/IOException
    //   59	69	324	java/io/IOException
    //   109	134	324	java/io/IOException
  }
  
  /* Error */
  private static boolean zipDir(Stack<File> paramStack, String paramString, File paramFile, java.util.zip.ZipOutputStream paramZipOutputStream)
  {
    // Byte code:
    //   0: aload_2
    //   1: invokevirtual 180	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   4: astore 7
    //   6: aload 7
    //   8: ifnull +12 -> 20
    //   11: aload 7
    //   13: invokevirtual 283	java/lang/String:length	()I
    //   16: iconst_1
    //   17: if_icmpge +5 -> 22
    //   20: iconst_0
    //   21: ireturn
    //   22: aload 7
    //   24: invokevirtual 283	java/lang/String:length	()I
    //   27: aload_1
    //   28: invokevirtual 283	java/lang/String:length	()I
    //   31: getstatic 96	java/io/File:separator	Ljava/lang/String;
    //   34: invokevirtual 283	java/lang/String:length	()I
    //   37: iadd
    //   38: if_icmple -18 -> 20
    //   41: aload 7
    //   43: aload_1
    //   44: invokevirtual 283	java/lang/String:length	()I
    //   47: getstatic 96	java/io/File:separator	Ljava/lang/String;
    //   50: invokevirtual 283	java/lang/String:length	()I
    //   53: iadd
    //   54: invokevirtual 497	java/lang/String:substring	(I)Ljava/lang/String;
    //   57: astore_1
    //   58: aload_1
    //   59: getstatic 96	java/io/File:separator	Ljava/lang/String;
    //   62: invokevirtual 772	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   65: ifne +238 -> 303
    //   68: new 88	java/lang/StringBuilder
    //   71: dup
    //   72: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   75: aload_1
    //   76: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: getstatic 96	java/io/File:separator	Ljava/lang/String;
    //   82: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   88: astore_1
    //   89: aload_3
    //   90: new 774	java/util/zip/ZipEntry
    //   93: dup
    //   94: aload_1
    //   95: invokespecial 775	java/util/zip/ZipEntry:<init>	(Ljava/lang/String;)V
    //   98: invokevirtual 779	java/util/zip/ZipOutputStream:putNextEntry	(Ljava/util/zip/ZipEntry;)V
    //   101: aload_3
    //   102: invokevirtual 780	java/util/zip/ZipOutputStream:flush	()V
    //   105: aload_3
    //   106: invokevirtual 783	java/util/zip/ZipOutputStream:closeEntry	()V
    //   109: aload_2
    //   110: invokevirtual 72	java/io/File:listFiles	()[Ljava/io/File;
    //   113: astore_2
    //   114: aload_2
    //   115: ifnull +191 -> 306
    //   118: aload_2
    //   119: arraylength
    //   120: iconst_1
    //   121: if_icmpge +6 -> 127
    //   124: goto +182 -> 306
    //   127: ldc_w 312
    //   130: newarray <illegal type>
    //   132: astore 7
    //   134: aload_2
    //   135: arraylength
    //   136: istore 5
    //   138: iconst_0
    //   139: istore 4
    //   141: iload 4
    //   143: iload 5
    //   145: if_icmpge +156 -> 301
    //   148: aload_2
    //   149: iload 4
    //   151: aaload
    //   152: astore 8
    //   154: aload 8
    //   156: ifnull +152 -> 308
    //   159: aload 8
    //   161: invokevirtual 185	java/io/File:exists	()Z
    //   164: ifne +6 -> 170
    //   167: goto +141 -> 308
    //   170: aload 8
    //   172: invokevirtual 127	java/io/File:isDirectory	()Z
    //   175: ifeq +23 -> 198
    //   178: aload_0
    //   179: aload 8
    //   181: invokevirtual 175	java/util/Stack:push	(Ljava/lang/Object;)Ljava/lang/Object;
    //   184: pop
    //   185: goto +123 -> 308
    //   188: astore_0
    //   189: aload_0
    //   190: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   193: iconst_0
    //   194: ireturn
    //   195: astore_0
    //   196: aload_0
    //   197: athrow
    //   198: new 81	java/io/FileInputStream
    //   201: dup
    //   202: aload 8
    //   204: invokespecial 84	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   207: astore 9
    //   209: aload_3
    //   210: new 774	java/util/zip/ZipEntry
    //   213: dup
    //   214: new 88	java/lang/StringBuilder
    //   217: dup
    //   218: invokespecial 89	java/lang/StringBuilder:<init>	()V
    //   221: aload_1
    //   222: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: aload 8
    //   227: invokevirtual 102	java/io/File:getName	()Ljava/lang/String;
    //   230: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   233: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   236: invokespecial 775	java/util/zip/ZipEntry:<init>	(Ljava/lang/String;)V
    //   239: invokevirtual 779	java/util/zip/ZipOutputStream:putNextEntry	(Ljava/util/zip/ZipEntry;)V
    //   242: aload 9
    //   244: aload 7
    //   246: invokevirtual 110	java/io/FileInputStream:read	([B)I
    //   249: istore 6
    //   251: iload 6
    //   253: ifle +22 -> 275
    //   256: aload_3
    //   257: aload 7
    //   259: iconst_0
    //   260: iload 6
    //   262: invokevirtual 784	java/util/zip/ZipOutputStream:write	([BII)V
    //   265: goto -23 -> 242
    //   268: astore_0
    //   269: aload_0
    //   270: invokevirtual 294	java/lang/Exception:printStackTrace	()V
    //   273: iconst_0
    //   274: ireturn
    //   275: aload_3
    //   276: invokevirtual 780	java/util/zip/ZipOutputStream:flush	()V
    //   279: aload_3
    //   280: invokevirtual 783	java/util/zip/ZipOutputStream:closeEntry	()V
    //   283: aload 9
    //   285: invokevirtual 120	java/io/FileInputStream:close	()V
    //   288: goto +20 -> 308
    //   291: astore 8
    //   293: aload 8
    //   295: invokevirtual 117	java/io/IOException:printStackTrace	()V
    //   298: goto +10 -> 308
    //   301: iconst_1
    //   302: ireturn
    //   303: goto -214 -> 89
    //   306: iconst_1
    //   307: ireturn
    //   308: iload 4
    //   310: iconst_1
    //   311: iadd
    //   312: istore 4
    //   314: goto -173 -> 141
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	317	0	paramStack	Stack<File>
    //   0	317	1	paramString	String
    //   0	317	2	paramFile	File
    //   0	317	3	paramZipOutputStream	java.util.zip.ZipOutputStream
    //   139	174	4	i	int
    //   136	10	5	j	int
    //   249	12	6	k	int
    //   4	254	7	localObject1	Object
    //   152	74	8	localObject2	Object
    //   291	3	8	localIOException	IOException
    //   207	77	9	localFileInputStream	FileInputStream
    // Exception table:
    //   from	to	target	type
    //   22	89	188	java/io/IOException
    //   89	114	188	java/io/IOException
    //   118	124	188	java/io/IOException
    //   127	138	188	java/io/IOException
    //   159	167	188	java/io/IOException
    //   170	185	188	java/io/IOException
    //   198	242	188	java/io/IOException
    //   242	251	188	java/io/IOException
    //   256	265	188	java/io/IOException
    //   275	283	188	java/io/IOException
    //   293	298	188	java/io/IOException
    //   22	89	195	finally
    //   89	114	195	finally
    //   118	124	195	finally
    //   127	138	195	finally
    //   159	167	195	finally
    //   170	185	195	finally
    //   189	193	195	finally
    //   198	242	195	finally
    //   242	251	195	finally
    //   256	265	195	finally
    //   269	273	195	finally
    //   275	283	195	finally
    //   283	288	195	finally
    //   293	298	195	finally
    //   22	89	268	java/lang/Exception
    //   89	114	268	java/lang/Exception
    //   118	124	268	java/lang/Exception
    //   127	138	268	java/lang/Exception
    //   159	167	268	java/lang/Exception
    //   170	185	268	java/lang/Exception
    //   198	242	268	java/lang/Exception
    //   242	251	268	java/lang/Exception
    //   256	265	268	java/lang/Exception
    //   275	283	268	java/lang/Exception
    //   283	288	268	java/lang/Exception
    //   293	298	268	java/lang/Exception
    //   283	288	291	java/io/IOException
  }
  
  public static boolean zipFile(File paramFile1, File paramFile2)
  {
    if ((paramFile1 == null) || (paramFile2 == null) || (!paramFile1.exists())) {
      return false;
    }
    if (paramFile2.exists()) {
      paramFile2.delete();
    }
    if (paramFile1.isDirectory()) {
      return zipDir(paramFile1, paramFile2);
    }
    return gZipFile(paramFile1, paramFile2);
  }
}

/* Location:
 * Qualified Name:     com.meizu.statsapp.util.Utils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */