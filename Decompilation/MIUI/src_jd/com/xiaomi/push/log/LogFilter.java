package com.xiaomi.push.log;

import android.annotation.SuppressLint;
import android.content.Context;
import com.xiaomi.channel.commonutils.file.IOUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class LogFilter
{
  private static String MIPUSH_LOG_PATH = "/MiPushLog";
  @SuppressLint({"SimpleDateFormat"})
  private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private int mCurrentLen;
  private String mEndTime;
  private ArrayList<File> mFiles = new ArrayList();
  private String mFromTime;
  private int mMaxLen = 2097152;
  private boolean mStartFound;
  
  private void doFilter(BufferedReader paramBufferedReader, BufferedWriter paramBufferedWriter, Pattern paramPattern)
    throws IOException
  {
    char[] arrayOfChar = new char['က'];
    int k = 0;
    int i = paramBufferedReader.read(arrayOfChar);
    for (;;)
    {
      int j;
      int n;
      int i1;
      if ((i != -1) && (k != 1))
      {
        String str1 = new String(arrayOfChar, 0, i);
        Matcher localMatcher = paramPattern.matcher(str1);
        j = 0;
        n = 0;
        int m = i;
        int i2 = m;
        i1 = k;
        if (j < i)
        {
          i2 = m;
          i1 = k;
          if (localMatcher.find(j))
          {
            j = localMatcher.start();
            String str2 = str1.substring(j, mFromTime.length() + j);
            if (!mStartFound)
            {
              i1 = n;
              if (str2.compareTo(mFromTime) >= 0)
              {
                i1 = j;
                mStartFound = true;
              }
            }
            do
            {
              n = str1.indexOf('\n', j);
              if (n == -1) {
                break label248;
              }
              j += n;
              n = i1;
              break;
              i1 = n;
            } while (str2.compareTo(mEndTime) <= 0);
            i1 = 1;
            i2 = j;
          }
        }
        if (!mStartFound) {
          break label288;
        }
        i = i2 - n;
        mCurrentLen += i;
        if (i1 == 0) {
          break label267;
        }
        paramBufferedWriter.write(arrayOfChar, n, i);
      }
      label248:
      label267:
      do
      {
        return;
        j += mFromTime.length();
        n = i1;
        break;
        paramBufferedWriter.write(arrayOfChar, n, i);
      } while (mCurrentLen > mMaxLen);
      label288:
      i = paramBufferedReader.read(arrayOfChar);
      k = i1;
    }
  }
  
  /* Error */
  private void filter2File(File paramFile)
  {
    // Byte code:
    //   0: ldc 114
    //   2: invokestatic 118	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   5: astore 12
    //   7: aconst_null
    //   8: astore 10
    //   10: aconst_null
    //   11: astore_2
    //   12: aconst_null
    //   13: astore 6
    //   15: aconst_null
    //   16: astore 7
    //   18: aconst_null
    //   19: astore 8
    //   21: aconst_null
    //   22: astore 9
    //   24: aconst_null
    //   25: astore 11
    //   27: aconst_null
    //   28: astore_3
    //   29: aconst_null
    //   30: astore 4
    //   32: new 104	java/io/BufferedWriter
    //   35: dup
    //   36: new 120	java/io/OutputStreamWriter
    //   39: dup
    //   40: new 122	java/io/FileOutputStream
    //   43: dup
    //   44: aload_1
    //   45: invokespecial 124	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   48: invokespecial 127	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
    //   51: invokespecial 130	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   54: astore_1
    //   55: aload 6
    //   57: astore_2
    //   58: aload 7
    //   60: astore 4
    //   62: aload 8
    //   64: astore 5
    //   66: new 132	java/lang/StringBuilder
    //   69: dup
    //   70: invokespecial 133	java/lang/StringBuilder:<init>	()V
    //   73: astore_3
    //   74: aload 6
    //   76: astore_2
    //   77: aload 7
    //   79: astore 4
    //   81: aload 8
    //   83: astore 5
    //   85: aload_3
    //   86: ldc -121
    //   88: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: getstatic 144	android/os/Build:MODEL	Ljava/lang/String;
    //   94: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: pop
    //   98: aload 6
    //   100: astore_2
    //   101: aload 7
    //   103: astore 4
    //   105: aload 8
    //   107: astore 5
    //   109: aload_3
    //   110: ldc -110
    //   112: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: getstatic 151	android/os/Build$VERSION:INCREMENTAL	Ljava/lang/String;
    //   118: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: pop
    //   122: aload 6
    //   124: astore_2
    //   125: aload 7
    //   127: astore 4
    //   129: aload 8
    //   131: astore 5
    //   133: aload_3
    //   134: ldc -103
    //   136: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: invokestatic 159	com/xiaomi/smack/util/SystemUtils:getDeviceUUID	()Ljava/lang/String;
    //   142: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: pop
    //   146: aload 6
    //   148: astore_2
    //   149: aload 7
    //   151: astore 4
    //   153: aload 8
    //   155: astore 5
    //   157: aload_3
    //   158: ldc -95
    //   160: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   163: invokestatic 167	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   166: invokevirtual 170	java/util/Locale:toString	()Ljava/lang/String;
    //   169: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: pop
    //   173: aload 6
    //   175: astore_2
    //   176: aload 7
    //   178: astore 4
    //   180: aload 8
    //   182: astore 5
    //   184: aload_3
    //   185: ldc -84
    //   187: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: bipush 7
    //   192: invokevirtual 175	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   195: pop
    //   196: aload 6
    //   198: astore_2
    //   199: aload 7
    //   201: astore 4
    //   203: aload 8
    //   205: astore 5
    //   207: aload_3
    //   208: ldc -79
    //   210: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   213: pop
    //   214: aload 6
    //   216: astore_2
    //   217: aload 7
    //   219: astore 4
    //   221: aload 8
    //   223: astore 5
    //   225: aload_1
    //   226: aload_3
    //   227: invokevirtual 178	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   230: invokevirtual 180	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   233: aload 6
    //   235: astore_2
    //   236: aload 7
    //   238: astore 4
    //   240: aload 8
    //   242: astore 5
    //   244: aload_0
    //   245: iconst_0
    //   246: putfield 102	com/xiaomi/push/log/LogFilter:mCurrentLen	I
    //   249: aload 6
    //   251: astore_2
    //   252: aload 7
    //   254: astore 4
    //   256: aload 8
    //   258: astore 5
    //   260: aload_0
    //   261: getfield 48	com/xiaomi/push/log/LogFilter:mFiles	Ljava/util/ArrayList;
    //   264: invokevirtual 184	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   267: astore 6
    //   269: aconst_null
    //   270: astore_2
    //   271: aload 6
    //   273: invokeinterface 190 1 0
    //   278: ifeq +68 -> 346
    //   281: new 54	java/io/BufferedReader
    //   284: dup
    //   285: new 192	java/io/InputStreamReader
    //   288: dup
    //   289: new 194	java/io/FileInputStream
    //   292: dup
    //   293: aload 6
    //   295: invokeinterface 198 1 0
    //   300: checkcast 200	java/io/File
    //   303: invokespecial 201	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   306: invokespecial 204	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   309: invokespecial 207	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   312: astore_3
    //   313: aload_3
    //   314: astore_2
    //   315: aload_3
    //   316: astore 4
    //   318: aload_3
    //   319: astore 5
    //   321: aload_0
    //   322: aload_3
    //   323: aload_1
    //   324: aload 12
    //   326: invokespecial 209	com/xiaomi/push/log/LogFilter:doFilter	(Ljava/io/BufferedReader;Ljava/io/BufferedWriter;Ljava/util/regex/Pattern;)V
    //   329: aload_3
    //   330: astore_2
    //   331: aload_3
    //   332: astore 4
    //   334: aload_3
    //   335: astore 5
    //   337: aload_3
    //   338: invokevirtual 212	java/io/BufferedReader:close	()V
    //   341: aload_3
    //   342: astore_2
    //   343: goto -72 -> 271
    //   346: aload_1
    //   347: invokestatic 217	com/xiaomi/channel/commonutils/file/IOUtils:closeQuietly	(Ljava/io/Writer;)V
    //   350: aload_2
    //   351: invokestatic 219	com/xiaomi/channel/commonutils/file/IOUtils:closeQuietly	(Ljava/io/Reader;)V
    //   354: return
    //   355: astore 5
    //   357: aload 9
    //   359: astore_1
    //   360: aload_1
    //   361: astore_2
    //   362: aload 4
    //   364: astore_3
    //   365: new 132	java/lang/StringBuilder
    //   368: dup
    //   369: invokespecial 133	java/lang/StringBuilder:<init>	()V
    //   372: ldc -35
    //   374: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   377: aload 5
    //   379: invokevirtual 224	java/io/FileNotFoundException:getMessage	()Ljava/lang/String;
    //   382: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   385: invokevirtual 178	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   388: invokestatic 229	com/xiaomi/channel/commonutils/logger/MyLog:v	(Ljava/lang/String;)V
    //   391: aload 4
    //   393: invokestatic 217	com/xiaomi/channel/commonutils/file/IOUtils:closeQuietly	(Ljava/io/Writer;)V
    //   396: aload_1
    //   397: invokestatic 219	com/xiaomi/channel/commonutils/file/IOUtils:closeQuietly	(Ljava/io/Reader;)V
    //   400: return
    //   401: astore 5
    //   403: aload 11
    //   405: astore 4
    //   407: aload 10
    //   409: astore_1
    //   410: aload_1
    //   411: astore_2
    //   412: aload 4
    //   414: astore_3
    //   415: new 132	java/lang/StringBuilder
    //   418: dup
    //   419: invokespecial 133	java/lang/StringBuilder:<init>	()V
    //   422: ldc -35
    //   424: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   427: aload 5
    //   429: invokevirtual 230	java/io/IOException:getMessage	()Ljava/lang/String;
    //   432: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   435: invokevirtual 178	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   438: invokestatic 229	com/xiaomi/channel/commonutils/logger/MyLog:v	(Ljava/lang/String;)V
    //   441: aload 4
    //   443: invokestatic 217	com/xiaomi/channel/commonutils/file/IOUtils:closeQuietly	(Ljava/io/Writer;)V
    //   446: aload_1
    //   447: invokestatic 219	com/xiaomi/channel/commonutils/file/IOUtils:closeQuietly	(Ljava/io/Reader;)V
    //   450: return
    //   451: astore_1
    //   452: aload_3
    //   453: invokestatic 217	com/xiaomi/channel/commonutils/file/IOUtils:closeQuietly	(Ljava/io/Writer;)V
    //   456: aload_2
    //   457: invokestatic 219	com/xiaomi/channel/commonutils/file/IOUtils:closeQuietly	(Ljava/io/Reader;)V
    //   460: aload_1
    //   461: athrow
    //   462: astore 4
    //   464: aload_1
    //   465: astore_3
    //   466: aload 4
    //   468: astore_1
    //   469: goto -17 -> 452
    //   472: astore 4
    //   474: aload_1
    //   475: astore_3
    //   476: aload 4
    //   478: astore_1
    //   479: goto -27 -> 452
    //   482: astore 5
    //   484: aload_1
    //   485: astore_2
    //   486: aload 4
    //   488: astore_1
    //   489: aload_2
    //   490: astore 4
    //   492: goto -82 -> 410
    //   495: astore 5
    //   497: aload_1
    //   498: astore 4
    //   500: aload_2
    //   501: astore_1
    //   502: goto -92 -> 410
    //   505: astore_2
    //   506: aload_1
    //   507: astore 4
    //   509: aload 5
    //   511: astore_1
    //   512: aload_2
    //   513: astore 5
    //   515: goto -155 -> 360
    //   518: astore 5
    //   520: aload_1
    //   521: astore 4
    //   523: aload_2
    //   524: astore_1
    //   525: goto -165 -> 360
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	528	0	this	LogFilter
    //   0	528	1	paramFile	File
    //   11	490	2	localObject1	Object
    //   505	19	2	localFileNotFoundException1	java.io.FileNotFoundException
    //   28	448	3	localObject2	Object
    //   30	412	4	localObject3	Object
    //   462	5	4	localObject4	Object
    //   472	15	4	localObject5	Object
    //   490	32	4	localObject6	Object
    //   64	272	5	localObject7	Object
    //   355	23	5	localFileNotFoundException2	java.io.FileNotFoundException
    //   401	27	5	localIOException1	IOException
    //   482	1	5	localIOException2	IOException
    //   495	15	5	localIOException3	IOException
    //   513	1	5	localFileNotFoundException3	java.io.FileNotFoundException
    //   518	1	5	localFileNotFoundException4	java.io.FileNotFoundException
    //   13	281	6	localIterator	java.util.Iterator
    //   16	237	7	localObject8	Object
    //   19	238	8	localObject9	Object
    //   22	336	9	localObject10	Object
    //   8	400	10	localObject11	Object
    //   25	379	11	localObject12	Object
    //   5	320	12	localPattern	Pattern
    // Exception table:
    //   from	to	target	type
    //   32	55	355	java/io/FileNotFoundException
    //   32	55	401	java/io/IOException
    //   32	55	451	finally
    //   365	391	451	finally
    //   415	441	451	finally
    //   66	74	462	finally
    //   85	98	462	finally
    //   109	122	462	finally
    //   133	146	462	finally
    //   157	173	462	finally
    //   184	196	462	finally
    //   207	214	462	finally
    //   225	233	462	finally
    //   244	249	462	finally
    //   260	269	462	finally
    //   321	329	462	finally
    //   337	341	462	finally
    //   271	313	472	finally
    //   66	74	482	java/io/IOException
    //   85	98	482	java/io/IOException
    //   109	122	482	java/io/IOException
    //   133	146	482	java/io/IOException
    //   157	173	482	java/io/IOException
    //   184	196	482	java/io/IOException
    //   207	214	482	java/io/IOException
    //   225	233	482	java/io/IOException
    //   244	249	482	java/io/IOException
    //   260	269	482	java/io/IOException
    //   321	329	482	java/io/IOException
    //   337	341	482	java/io/IOException
    //   271	313	495	java/io/IOException
    //   66	74	505	java/io/FileNotFoundException
    //   85	98	505	java/io/FileNotFoundException
    //   109	122	505	java/io/FileNotFoundException
    //   133	146	505	java/io/FileNotFoundException
    //   157	173	505	java/io/FileNotFoundException
    //   184	196	505	java/io/FileNotFoundException
    //   207	214	505	java/io/FileNotFoundException
    //   225	233	505	java/io/FileNotFoundException
    //   244	249	505	java/io/FileNotFoundException
    //   260	269	505	java/io/FileNotFoundException
    //   321	329	505	java/io/FileNotFoundException
    //   337	341	505	java/io/FileNotFoundException
    //   271	313	518	java/io/FileNotFoundException
  }
  
  LogFilter addFile(File paramFile)
  {
    if (paramFile.exists()) {
      mFiles.add(paramFile);
    }
    return this;
  }
  
  File filter(Context paramContext, Date paramDate1, Date paramDate2, File paramFile)
  {
    if ("com.xiaomi.xmsf".equalsIgnoreCase(paramContext.getPackageName()))
    {
      paramContext = paramContext.getFilesDir();
      addFile(new File(paramContext, "xmsf.log.1"));
      addFile(new File(paramContext, "xmsf.log"));
      if (paramContext.isDirectory()) {
        break label127;
      }
      paramContext = null;
    }
    label127:
    File localFile;
    do
    {
      return paramContext;
      paramContext = new File(paramContext.getExternalFilesDir(null) + MIPUSH_LOG_PATH);
      addFile(new File(paramContext, "log0.txt"));
      addFile(new File(paramContext, "log1.txt"));
      break;
      localFile = new File(paramFile, paramDate1.getTime() + "-" + paramDate2.getTime() + ".zip");
      if (localFile.exists()) {
        return null;
      }
      setRange(paramDate1, paramDate2);
      long l = System.currentTimeMillis();
      paramContext = new File(paramFile, "log.txt");
      filter2File(paramContext);
      MyLog.v("LOG: filter cost = " + (System.currentTimeMillis() - l));
      if (!paramContext.exists()) {
        break label304;
      }
      l = System.currentTimeMillis();
      IOUtils.zip(localFile, paramContext);
      MyLog.v("LOG: zip cost = " + (System.currentTimeMillis() - l));
      paramContext.delete();
      paramContext = localFile;
    } while (localFile.exists());
    label304:
    return null;
  }
  
  void setMaxLen(int paramInt)
  {
    if (paramInt != 0) {
      mMaxLen = paramInt;
    }
  }
  
  LogFilter setRange(Date paramDate1, Date paramDate2)
  {
    if (paramDate1.after(paramDate2))
    {
      mFromTime = dateFormatter.format(paramDate2);
      mEndTime = dateFormatter.format(paramDate1);
      return this;
    }
    mFromTime = dateFormatter.format(paramDate1);
    mEndTime = dateFormatter.format(paramDate2);
    return this;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.log.LogFilter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */