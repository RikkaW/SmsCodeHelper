package com.xiaomi.channel.commonutils.file;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public class IOUtils
{
  public static final String[] SUPPORTED_IMAGE_FORMATS = { "jpg", "png", "bmp", "gif", "webp" };
  
  public static void closeQuietly(InputStream paramInputStream)
  {
    if (paramInputStream != null) {}
    try
    {
      paramInputStream.close();
      return;
    }
    catch (IOException paramInputStream) {}
  }
  
  public static void closeQuietly(OutputStream paramOutputStream)
  {
    if (paramOutputStream != null) {}
    try
    {
      paramOutputStream.flush();
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        try
        {
          paramOutputStream.close();
          return;
        }
        catch (IOException paramOutputStream) {}
        localIOException = localIOException;
      }
    }
  }
  
  public static void closeQuietly(Reader paramReader)
  {
    if (paramReader != null) {}
    try
    {
      paramReader.close();
      return;
    }
    catch (IOException paramReader) {}
  }
  
  public static void closeQuietly(Writer paramWriter)
  {
    if (paramWriter != null) {}
    try
    {
      paramWriter.close();
      return;
    }
    catch (IOException paramWriter) {}
  }
  
  /* Error */
  public static void zip(File paramFile1, File paramFile2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore_2
    //   4: aconst_null
    //   5: astore 5
    //   7: aconst_null
    //   8: astore 4
    //   10: new 58	java/io/FileOutputStream
    //   13: dup
    //   14: aload_0
    //   15: iconst_0
    //   16: invokespecial 61	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
    //   19: astore_0
    //   20: new 63	java/util/zip/ZipOutputStream
    //   23: dup
    //   24: aload_0
    //   25: invokespecial 65	java/util/zip/ZipOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   28: astore_0
    //   29: aload_0
    //   30: aload_1
    //   31: aconst_null
    //   32: aconst_null
    //   33: invokestatic 68	com/xiaomi/channel/commonutils/file/IOUtils:zip	(Ljava/util/zip/ZipOutputStream;Ljava/io/File;Ljava/lang/String;Ljava/io/FileFilter;)V
    //   36: aload_0
    //   37: invokestatic 70	com/xiaomi/channel/commonutils/file/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   40: return
    //   41: astore_0
    //   42: aload 4
    //   44: astore_0
    //   45: aload_0
    //   46: invokestatic 70	com/xiaomi/channel/commonutils/file/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   49: return
    //   50: astore_0
    //   51: aload_3
    //   52: astore_1
    //   53: aload_1
    //   54: astore_2
    //   55: new 72	java/lang/StringBuilder
    //   58: dup
    //   59: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   62: ldc 75
    //   64: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: aload_0
    //   68: invokevirtual 83	java/io/IOException:getMessage	()Ljava/lang/String;
    //   71: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   77: invokestatic 92	com/xiaomi/channel/commonutils/logger/MyLog:warn	(Ljava/lang/String;)V
    //   80: aload_1
    //   81: invokestatic 70	com/xiaomi/channel/commonutils/file/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   84: return
    //   85: astore_0
    //   86: aload_2
    //   87: invokestatic 70	com/xiaomi/channel/commonutils/file/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   90: aload_0
    //   91: athrow
    //   92: astore_0
    //   93: aload 5
    //   95: astore_2
    //   96: goto -10 -> 86
    //   99: astore_1
    //   100: aload_0
    //   101: astore_2
    //   102: aload_1
    //   103: astore_0
    //   104: goto -18 -> 86
    //   107: astore_0
    //   108: aload_3
    //   109: astore_1
    //   110: goto -57 -> 53
    //   113: astore_2
    //   114: aload_0
    //   115: astore_1
    //   116: aload_2
    //   117: astore_0
    //   118: goto -65 -> 53
    //   121: astore_0
    //   122: aload 4
    //   124: astore_0
    //   125: goto -80 -> 45
    //   128: astore_1
    //   129: goto -84 -> 45
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	132	0	paramFile1	File
    //   0	132	1	paramFile2	File
    //   3	99	2	localObject1	Object
    //   113	4	2	localIOException	IOException
    //   1	108	3	localObject2	Object
    //   8	115	4	localObject3	Object
    //   5	89	5	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   10	20	41	java/io/FileNotFoundException
    //   10	20	50	java/io/IOException
    //   10	20	85	finally
    //   55	80	85	finally
    //   20	29	92	finally
    //   29	36	99	finally
    //   20	29	107	java/io/IOException
    //   29	36	113	java/io/IOException
    //   20	29	121	java/io/FileNotFoundException
    //   29	36	128	java/io/FileNotFoundException
  }
  
  /* Error */
  public static void zip(java.util.zip.ZipOutputStream paramZipOutputStream, File paramFile, String paramString, FileFilter paramFileFilter)
    throws IOException
  {
    // Byte code:
    //   0: aload_2
    //   1: astore 6
    //   3: aload_2
    //   4: ifnonnull +7 -> 11
    //   7: ldc 94
    //   9: astore 6
    //   11: aconst_null
    //   12: astore 10
    //   14: aconst_null
    //   15: astore 9
    //   17: aconst_null
    //   18: astore 8
    //   20: aload 9
    //   22: astore_2
    //   23: aload_1
    //   24: invokevirtual 100	java/io/File:isDirectory	()Z
    //   27: ifeq +267 -> 294
    //   30: aload_3
    //   31: ifnull +125 -> 156
    //   34: aload 9
    //   36: astore_2
    //   37: aload_1
    //   38: aload_3
    //   39: invokevirtual 104	java/io/File:listFiles	(Ljava/io/FileFilter;)[Ljava/io/File;
    //   42: astore 7
    //   44: aload 9
    //   46: astore_2
    //   47: aload_0
    //   48: new 106	java/util/zip/ZipEntry
    //   51: dup
    //   52: new 72	java/lang/StringBuilder
    //   55: dup
    //   56: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   59: aload 6
    //   61: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: getstatic 110	java/io/File:separator	Ljava/lang/String;
    //   67: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   73: invokespecial 112	java/util/zip/ZipEntry:<init>	(Ljava/lang/String;)V
    //   76: invokevirtual 116	java/util/zip/ZipOutputStream:putNextEntry	(Ljava/util/zip/ZipEntry;)V
    //   79: aload 9
    //   81: astore_2
    //   82: aload 6
    //   84: invokestatic 122	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   87: ifeq +81 -> 168
    //   90: ldc 94
    //   92: astore 6
    //   94: goto +384 -> 478
    //   97: aload 9
    //   99: astore_2
    //   100: iload 4
    //   102: aload 7
    //   104: arraylength
    //   105: if_icmpge +92 -> 197
    //   108: aload 9
    //   110: astore_2
    //   111: aload_0
    //   112: aload 7
    //   114: iload 4
    //   116: aaload
    //   117: new 72	java/lang/StringBuilder
    //   120: dup
    //   121: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   124: aload 6
    //   126: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: aload 7
    //   131: iload 4
    //   133: aaload
    //   134: invokevirtual 125	java/io/File:getName	()Ljava/lang/String;
    //   137: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   143: aconst_null
    //   144: invokestatic 68	com/xiaomi/channel/commonutils/file/IOUtils:zip	(Ljava/util/zip/ZipOutputStream;Ljava/io/File;Ljava/lang/String;Ljava/io/FileFilter;)V
    //   147: iload 4
    //   149: iconst_1
    //   150: iadd
    //   151: istore 4
    //   153: goto -56 -> 97
    //   156: aload 9
    //   158: astore_2
    //   159: aload_1
    //   160: invokevirtual 128	java/io/File:listFiles	()[Ljava/io/File;
    //   163: astore 7
    //   165: goto -121 -> 44
    //   168: aload 9
    //   170: astore_2
    //   171: new 72	java/lang/StringBuilder
    //   174: dup
    //   175: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   178: aload 6
    //   180: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: getstatic 110	java/io/File:separator	Ljava/lang/String;
    //   186: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   192: astore 6
    //   194: goto +284 -> 478
    //   197: aload 9
    //   199: astore_2
    //   200: aload_1
    //   201: new 6	com/xiaomi/channel/commonutils/file/IOUtils$1
    //   204: dup
    //   205: invokespecial 129	com/xiaomi/channel/commonutils/file/IOUtils$1:<init>	()V
    //   208: invokevirtual 104	java/io/File:listFiles	(Ljava/io/FileFilter;)[Ljava/io/File;
    //   211: astore 7
    //   213: aload 10
    //   215: astore_1
    //   216: aload 7
    //   218: ifnull +242 -> 460
    //   221: aload 9
    //   223: astore_2
    //   224: aload 7
    //   226: arraylength
    //   227: istore 5
    //   229: iconst_0
    //   230: istore 4
    //   232: aload 10
    //   234: astore_1
    //   235: iload 4
    //   237: iload 5
    //   239: if_icmpge +221 -> 460
    //   242: aload 7
    //   244: iload 4
    //   246: aaload
    //   247: astore_1
    //   248: aload 9
    //   250: astore_2
    //   251: aload_0
    //   252: aload_1
    //   253: new 72	java/lang/StringBuilder
    //   256: dup
    //   257: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   260: aload 6
    //   262: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   265: getstatic 110	java/io/File:separator	Ljava/lang/String;
    //   268: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: aload_1
    //   272: invokevirtual 125	java/io/File:getName	()Ljava/lang/String;
    //   275: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   278: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   281: aload_3
    //   282: invokestatic 68	com/xiaomi/channel/commonutils/file/IOUtils:zip	(Ljava/util/zip/ZipOutputStream;Ljava/io/File;Ljava/lang/String;Ljava/io/FileFilter;)V
    //   285: iload 4
    //   287: iconst_1
    //   288: iadd
    //   289: istore 4
    //   291: goto -59 -> 232
    //   294: aload 9
    //   296: astore_2
    //   297: aload 6
    //   299: invokestatic 122	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   302: ifne +98 -> 400
    //   305: aload 9
    //   307: astore_2
    //   308: aload_0
    //   309: new 106	java/util/zip/ZipEntry
    //   312: dup
    //   313: aload 6
    //   315: invokespecial 112	java/util/zip/ZipEntry:<init>	(Ljava/lang/String;)V
    //   318: invokevirtual 116	java/util/zip/ZipOutputStream:putNextEntry	(Ljava/util/zip/ZipEntry;)V
    //   321: aload 9
    //   323: astore_2
    //   324: new 131	java/io/FileInputStream
    //   327: dup
    //   328: aload_1
    //   329: invokespecial 134	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   332: astore_1
    //   333: sipush 1024
    //   336: newarray <illegal type>
    //   338: astore_2
    //   339: aload_1
    //   340: aload_2
    //   341: invokevirtual 138	java/io/FileInputStream:read	([B)I
    //   344: istore 4
    //   346: iload 4
    //   348: iconst_m1
    //   349: if_icmpeq +111 -> 460
    //   352: aload_0
    //   353: aload_2
    //   354: iconst_0
    //   355: iload 4
    //   357: invokevirtual 142	java/util/zip/ZipOutputStream:write	([BII)V
    //   360: goto -21 -> 339
    //   363: astore_2
    //   364: aload_1
    //   365: astore_0
    //   366: aload_2
    //   367: astore_1
    //   368: aload_0
    //   369: astore_2
    //   370: new 72	java/lang/StringBuilder
    //   373: dup
    //   374: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   377: ldc -112
    //   379: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   382: aload_1
    //   383: invokevirtual 145	java/io/IOException:toString	()Ljava/lang/String;
    //   386: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   389: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   392: invokestatic 148	com/xiaomi/channel/commonutils/logger/MyLog:e	(Ljava/lang/String;)V
    //   395: aload_0
    //   396: invokestatic 150	com/xiaomi/channel/commonutils/file/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   399: return
    //   400: aload 9
    //   402: astore_2
    //   403: new 152	java/util/Date
    //   406: dup
    //   407: invokespecial 153	java/util/Date:<init>	()V
    //   410: astore_3
    //   411: aload 9
    //   413: astore_2
    //   414: aload_0
    //   415: new 106	java/util/zip/ZipEntry
    //   418: dup
    //   419: new 72	java/lang/StringBuilder
    //   422: dup
    //   423: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   426: aload_3
    //   427: invokevirtual 157	java/util/Date:getTime	()J
    //   430: invokestatic 161	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   433: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   436: ldc -93
    //   438: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   441: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   444: invokespecial 112	java/util/zip/ZipEntry:<init>	(Ljava/lang/String;)V
    //   447: invokevirtual 116	java/util/zip/ZipOutputStream:putNextEntry	(Ljava/util/zip/ZipEntry;)V
    //   450: goto -129 -> 321
    //   453: astore_1
    //   454: aload 8
    //   456: astore_0
    //   457: goto -89 -> 368
    //   460: aload_1
    //   461: invokestatic 150	com/xiaomi/channel/commonutils/file/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   464: return
    //   465: astore_0
    //   466: aload_2
    //   467: invokestatic 150	com/xiaomi/channel/commonutils/file/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   470: aload_0
    //   471: athrow
    //   472: astore_0
    //   473: aload_1
    //   474: astore_2
    //   475: goto -9 -> 466
    //   478: iconst_0
    //   479: istore 4
    //   481: goto -384 -> 97
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	484	0	paramZipOutputStream	java.util.zip.ZipOutputStream
    //   0	484	1	paramFile	File
    //   0	484	2	paramString	String
    //   0	484	3	paramFileFilter	FileFilter
    //   100	380	4	i	int
    //   227	13	5	j	int
    //   1	313	6	str	String
    //   42	201	7	arrayOfFile	File[]
    //   18	437	8	localObject1	Object
    //   15	397	9	localObject2	Object
    //   12	221	10	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   333	339	363	java/io/IOException
    //   339	346	363	java/io/IOException
    //   352	360	363	java/io/IOException
    //   23	30	453	java/io/IOException
    //   37	44	453	java/io/IOException
    //   47	79	453	java/io/IOException
    //   82	90	453	java/io/IOException
    //   100	108	453	java/io/IOException
    //   111	147	453	java/io/IOException
    //   159	165	453	java/io/IOException
    //   171	194	453	java/io/IOException
    //   200	213	453	java/io/IOException
    //   224	229	453	java/io/IOException
    //   251	285	453	java/io/IOException
    //   297	305	453	java/io/IOException
    //   308	321	453	java/io/IOException
    //   324	333	453	java/io/IOException
    //   403	411	453	java/io/IOException
    //   414	450	453	java/io/IOException
    //   23	30	465	finally
    //   37	44	465	finally
    //   47	79	465	finally
    //   82	90	465	finally
    //   100	108	465	finally
    //   111	147	465	finally
    //   159	165	465	finally
    //   171	194	465	finally
    //   200	213	465	finally
    //   224	229	465	finally
    //   251	285	465	finally
    //   297	305	465	finally
    //   308	321	465	finally
    //   324	333	465	finally
    //   370	395	465	finally
    //   403	411	465	finally
    //   414	450	465	finally
    //   333	339	472	finally
    //   339	346	472	finally
    //   352	360	472	finally
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.channel.commonutils.file.IOUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */