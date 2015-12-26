package com.android.mms.update;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class FileUtil
{
  public static File createFile(String paramString)
  {
    paramString = new File(paramString);
    if (paramString.exists()) {}
    for (;;)
    {
      return paramString;
      File localFile = paramString.getParentFile();
      if ((!localFile.exists()) && (!localFile.mkdirs()))
      {
        Log.e("FileUtil", "Create file " + localFile.getAbsolutePath() + " fails");
        return localFile;
      }
      try
      {
        boolean bool = paramString.createNewFile();
        if (bool) {}
      }
      catch (IOException paramString)
      {
        for (;;)
        {
          paramString.printStackTrace();
        }
      }
    }
    return null;
  }
  
  public static boolean deleteFile(File paramFile)
  {
    if (paramFile == null) {}
    while (!paramFile.exists()) {
      return false;
    }
    if (paramFile.isDirectory())
    {
      File[] arrayOfFile = paramFile.listFiles();
      int j = arrayOfFile.length;
      int i = 0;
      for (;;)
      {
        if (i >= j) {
          break label51;
        }
        if (!deleteFile(arrayOfFile[i])) {
          break;
        }
        i += 1;
      }
      label51:
      paramFile.delete();
      return true;
    }
    return paramFile.delete();
  }
  
  public static boolean downLoadFileWithHeader(Context paramContext, String paramString1, String paramString2, Map<String, String> paramMap)
  {
    return downLoadFileWithHeader(paramContext, paramString1, paramString2, paramMap, 0);
  }
  
  /* Error */
  public static boolean downLoadFileWithHeader(Context paramContext, String paramString1, String paramString2, Map<String, String> paramMap, int paramInt)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 88	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   4: ifne +10 -> 14
    //   7: aload_2
    //   8: invokestatic 88	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   11: ifeq +13 -> 24
    //   14: ldc 31
    //   16: ldc 90
    //   18: invokestatic 93	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   21: pop
    //   22: iconst_0
    //   23: ireturn
    //   24: iconst_0
    //   25: istore 5
    //   27: new 95	com/android/mms/update/StreamRequest
    //   30: dup
    //   31: aload_0
    //   32: aload_1
    //   33: invokespecial 98	com/android/mms/update/StreamRequest:<init>	(Landroid/content/Context;Ljava/lang/String;)V
    //   36: astore 7
    //   38: aload 7
    //   40: iload 4
    //   42: invokevirtual 102	com/android/mms/update/StreamRequest:overwriteNetworkAccess	(I)V
    //   45: aconst_null
    //   46: astore_1
    //   47: aconst_null
    //   48: astore 6
    //   50: aload_1
    //   51: astore_0
    //   52: aload_2
    //   53: invokestatic 104	com/android/mms/update/FileUtil:createFile	(Ljava/lang/String;)Ljava/io/File;
    //   56: ifnonnull +57 -> 113
    //   59: aload_1
    //   60: astore_0
    //   61: ldc 31
    //   63: new 33	java/lang/StringBuilder
    //   66: dup
    //   67: invokespecial 34	java/lang/StringBuilder:<init>	()V
    //   70: ldc 36
    //   72: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: aload_2
    //   76: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: ldc 46
    //   81: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: invokevirtual 49	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   87: invokestatic 55	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   90: pop
    //   91: iconst_0
    //   92: ifeq +11 -> 103
    //   95: new 106	java/lang/NullPointerException
    //   98: dup
    //   99: invokespecial 107	java/lang/NullPointerException:<init>	()V
    //   102: athrow
    //   103: iconst_0
    //   104: ireturn
    //   105: astore_0
    //   106: aload_0
    //   107: invokevirtual 61	java/io/IOException:printStackTrace	()V
    //   110: goto -7 -> 103
    //   113: aload_1
    //   114: astore_0
    //   115: new 109	java/io/FileOutputStream
    //   118: dup
    //   119: new 15	java/io/File
    //   122: dup
    //   123: aload_2
    //   124: invokespecial 18	java/io/File:<init>	(Ljava/lang/String;)V
    //   127: invokespecial 112	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   130: astore_1
    //   131: aload 7
    //   133: aload_1
    //   134: aload_3
    //   135: invokevirtual 116	com/android/mms/update/StreamRequest:requestStream	(Ljava/io/OutputStream;Ljava/util/Map;)I
    //   138: ifne +47 -> 185
    //   141: ldc 31
    //   143: new 33	java/lang/StringBuilder
    //   146: dup
    //   147: invokespecial 34	java/lang/StringBuilder:<init>	()V
    //   150: ldc 118
    //   152: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   155: aload_2
    //   156: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: ldc 120
    //   161: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: invokevirtual 49	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   167: invokestatic 93	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   170: pop
    //   171: iconst_1
    //   172: istore 5
    //   174: aload_1
    //   175: ifnull +109 -> 284
    //   178: aload_1
    //   179: invokevirtual 123	java/io/FileOutputStream:close	()V
    //   182: iload 5
    //   184: ireturn
    //   185: ldc 31
    //   187: new 33	java/lang/StringBuilder
    //   190: dup
    //   191: invokespecial 34	java/lang/StringBuilder:<init>	()V
    //   194: ldc 118
    //   196: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   199: aload_2
    //   200: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: ldc 125
    //   205: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: invokevirtual 49	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   211: invokestatic 93	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   214: pop
    //   215: goto -41 -> 174
    //   218: astore_2
    //   219: aload_1
    //   220: astore_0
    //   221: aload_2
    //   222: invokevirtual 61	java/io/IOException:printStackTrace	()V
    //   225: aload_1
    //   226: ifnull -204 -> 22
    //   229: aload_1
    //   230: invokevirtual 123	java/io/FileOutputStream:close	()V
    //   233: iconst_0
    //   234: ireturn
    //   235: astore_0
    //   236: aload_0
    //   237: invokevirtual 61	java/io/IOException:printStackTrace	()V
    //   240: iconst_0
    //   241: ireturn
    //   242: astore_0
    //   243: aload_0
    //   244: invokevirtual 61	java/io/IOException:printStackTrace	()V
    //   247: iload 5
    //   249: ireturn
    //   250: astore_1
    //   251: aload_0
    //   252: ifnull +7 -> 259
    //   255: aload_0
    //   256: invokevirtual 123	java/io/FileOutputStream:close	()V
    //   259: aload_1
    //   260: athrow
    //   261: astore_0
    //   262: aload_0
    //   263: invokevirtual 61	java/io/IOException:printStackTrace	()V
    //   266: goto -7 -> 259
    //   269: astore_2
    //   270: aload_1
    //   271: astore_0
    //   272: aload_2
    //   273: astore_1
    //   274: goto -23 -> 251
    //   277: astore_2
    //   278: aload 6
    //   280: astore_1
    //   281: goto -62 -> 219
    //   284: iload 5
    //   286: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	287	0	paramContext	Context
    //   0	287	1	paramString1	String
    //   0	287	2	paramString2	String
    //   0	287	3	paramMap	Map<String, String>
    //   0	287	4	paramInt	int
    //   25	260	5	bool	boolean
    //   48	231	6	localObject	Object
    //   36	96	7	localStreamRequest	StreamRequest
    // Exception table:
    //   from	to	target	type
    //   95	103	105	java/io/IOException
    //   131	171	218	java/io/IOException
    //   185	215	218	java/io/IOException
    //   229	233	235	java/io/IOException
    //   178	182	242	java/io/IOException
    //   52	59	250	finally
    //   61	91	250	finally
    //   115	131	250	finally
    //   221	225	250	finally
    //   255	259	261	java/io/IOException
    //   131	171	269	finally
    //   185	215	269	finally
    //   52	59	277	java/io/IOException
    //   61	91	277	java/io/IOException
    //   115	131	277	java/io/IOException
  }
}

/* Location:
 * Qualified Name:     com.android.mms.update.FileUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */