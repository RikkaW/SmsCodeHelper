package com.android.mms.understand;

import android.util.Log;
import com.android.mms.util.ThreadPool;
import java.io.File;
import java.io.IOException;

public class TemplateUpdate
{
  private static boolean createFile(String paramString)
  {
    paramString = new File(paramString);
    if (paramString.exists()) {
      return true;
    }
    File localFile = paramString.getParentFile();
    if ((!localFile.exists()) && (!localFile.mkdirs()))
    {
      Log.e("TemplateUpdate", "Create file " + localFile.getAbsolutePath() + " fails");
      return false;
    }
    try
    {
      boolean bool = paramString.createNewFile();
      return bool;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  public static String getDownloadedFile()
  {
    return "/data/data/com.android.mms/app_understand/downloads.tmp";
  }
  
  public static String getTemporaryFile()
  {
    return "/data/data/com.android.mms/app_understand/understand.temp";
  }
  
  public static String getUpdatedFile()
  {
    return "/data/data/com.android.mms/app_understand/understand.zip";
  }
  
  public static String getVersionFile()
  {
    return "/data/data/com.android.mms/app_understand/version";
  }
  
  public static void onTemplateDownloaded(UpdatePatch paramUpdatePatch)
  {
    ThreadPool.execute(new Runnable()
    {
      public void run()
      {
        TemplateUpdate.updateTemplate(val$patch);
      }
    });
  }
  
  /* Error */
  private static void updateTemplate(UpdatePatch paramUpdatePatch)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: getfield 96	com/android/mms/understand/TemplateUpdate$UpdatePatch:mIsIncremental	Z
    //   7: ifeq +404 -> 411
    //   10: new 98	miui/util/Patcher
    //   13: dup
    //   14: invokespecial 99	miui/util/Patcher:<init>	()V
    //   17: astore 5
    //   19: invokestatic 104	miui/os/Environment:getMiuiDataDirectory	()Ljava/io/File;
    //   22: astore_2
    //   23: new 43	java/lang/StringBuilder
    //   26: dup
    //   27: invokespecial 44	java/lang/StringBuilder:<init>	()V
    //   30: aload_2
    //   31: invokevirtual 54	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   34: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: ldc 106
    //   39: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   45: astore 4
    //   47: invokestatic 108	com/android/mms/understand/TemplateUpdate:getUpdatedFile	()Ljava/lang/String;
    //   50: astore_3
    //   51: aload_3
    //   52: astore_2
    //   53: new 25	java/io/File
    //   56: dup
    //   57: aload_3
    //   58: invokespecial 28	java/io/File:<init>	(Ljava/lang/String;)V
    //   61: invokevirtual 32	java/io/File:exists	()Z
    //   64: ifne +6 -> 70
    //   67: aload 4
    //   69: astore_2
    //   70: invokestatic 110	com/android/mms/understand/TemplateUpdate:getTemporaryFile	()Ljava/lang/String;
    //   73: astore_3
    //   74: invokestatic 112	com/android/mms/understand/TemplateUpdate:getDownloadedFile	()Ljava/lang/String;
    //   77: astore 4
    //   79: ldc 41
    //   81: new 43	java/lang/StringBuilder
    //   84: dup
    //   85: invokespecial 44	java/lang/StringBuilder:<init>	()V
    //   88: ldc 114
    //   90: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: aload 4
    //   95: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: ldc 116
    //   100: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: aload_2
    //   104: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   110: invokestatic 119	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   113: pop
    //   114: aload_0
    //   115: getfield 123	com/android/mms/understand/TemplateUpdate$UpdatePatch:mOldMd5	Ljava/lang/String;
    //   118: invokestatic 129	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   121: ifne +86 -> 207
    //   124: new 25	java/io/File
    //   127: dup
    //   128: aload_2
    //   129: invokespecial 28	java/io/File:<init>	(Ljava/lang/String;)V
    //   132: invokestatic 135	com/android/mms/util/Coder:encodeMD5	(Ljava/io/File;)Ljava/lang/String;
    //   135: astore 6
    //   137: aload_0
    //   138: getfield 123	com/android/mms/understand/TemplateUpdate$UpdatePatch:mOldMd5	Ljava/lang/String;
    //   141: aload 6
    //   143: invokevirtual 141	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   146: ifne +61 -> 207
    //   149: aload_0
    //   150: getfield 144	com/android/mms/understand/TemplateUpdate$UpdatePatch:mMd5	Ljava/lang/String;
    //   153: invokestatic 129	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   156: ifne +21 -> 177
    //   159: aload_0
    //   160: getfield 144	com/android/mms/understand/TemplateUpdate$UpdatePatch:mMd5	Ljava/lang/String;
    //   163: aload 6
    //   165: invokevirtual 141	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   168: istore_1
    //   169: iload_1
    //   170: ifeq +7 -> 177
    //   173: ldc 2
    //   175: monitorexit
    //   176: return
    //   177: lconst_0
    //   178: invokestatic 148	com/android/mms/understand/TemplateUpdate:updateTemplateVersion	(J)V
    //   181: ldc -106
    //   183: ldc -104
    //   185: ldc -102
    //   187: invokestatic 160	com/android/mms/util/MiStatSdkHelper:recordStringPropertyEvent	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   190: ldc 41
    //   192: ldc -94
    //   194: invokestatic 165	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   197: pop
    //   198: goto -25 -> 173
    //   201: astore_0
    //   202: ldc 2
    //   204: monitorexit
    //   205: aload_0
    //   206: athrow
    //   207: aload 5
    //   209: aload_2
    //   210: aload_3
    //   211: aload 4
    //   213: invokevirtual 169	miui/util/Patcher:applyPatch	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   216: ifne +146 -> 362
    //   219: ldc 41
    //   221: ldc -85
    //   223: invokestatic 119	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   226: pop
    //   227: aload_0
    //   228: getfield 144	com/android/mms/understand/TemplateUpdate$UpdatePatch:mMd5	Ljava/lang/String;
    //   231: invokestatic 129	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   234: ifne +32 -> 266
    //   237: aload_0
    //   238: getfield 144	com/android/mms/understand/TemplateUpdate$UpdatePatch:mMd5	Ljava/lang/String;
    //   241: new 25	java/io/File
    //   244: dup
    //   245: aload_3
    //   246: invokespecial 28	java/io/File:<init>	(Ljava/lang/String;)V
    //   249: invokestatic 135	com/android/mms/util/Coder:encodeMD5	(Ljava/io/File;)Ljava/lang/String;
    //   252: invokevirtual 141	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   255: ifeq +83 -> 338
    //   258: ldc 41
    //   260: ldc -83
    //   262: invokestatic 119	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   265: pop
    //   266: new 25	java/io/File
    //   269: dup
    //   270: aload 4
    //   272: invokespecial 28	java/io/File:<init>	(Ljava/lang/String;)V
    //   275: invokevirtual 176	java/io/File:delete	()Z
    //   278: pop
    //   279: invokestatic 108	com/android/mms/understand/TemplateUpdate:getUpdatedFile	()Ljava/lang/String;
    //   282: astore_2
    //   283: aload_2
    //   284: invokestatic 178	com/android/mms/understand/TemplateUpdate:createFile	(Ljava/lang/String;)Z
    //   287: ifeq +23 -> 310
    //   290: new 25	java/io/File
    //   293: dup
    //   294: aload_3
    //   295: invokespecial 28	java/io/File:<init>	(Ljava/lang/String;)V
    //   298: new 25	java/io/File
    //   301: dup
    //   302: aload_2
    //   303: invokespecial 28	java/io/File:<init>	(Ljava/lang/String;)V
    //   306: invokestatic 184	android/os/FileUtils:copyFile	(Ljava/io/File;Ljava/io/File;)Z
    //   309: pop
    //   310: new 25	java/io/File
    //   313: dup
    //   314: aload_3
    //   315: invokespecial 28	java/io/File:<init>	(Ljava/lang/String;)V
    //   318: invokevirtual 176	java/io/File:delete	()Z
    //   321: pop
    //   322: ldc -106
    //   324: ldc -104
    //   326: ldc -70
    //   328: invokestatic 160	com/android/mms/util/MiStatSdkHelper:recordStringPropertyEvent	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   331: aload_0
    //   332: invokestatic 191	com/android/mms/understand/UnderstandLoader:rePrepare	(Lcom/android/mms/understand/TemplateUpdate$UpdatePatch;)V
    //   335: goto -162 -> 173
    //   338: lconst_0
    //   339: invokestatic 148	com/android/mms/understand/TemplateUpdate:updateTemplateVersion	(J)V
    //   342: ldc -106
    //   344: ldc -104
    //   346: ldc -63
    //   348: invokestatic 160	com/android/mms/util/MiStatSdkHelper:recordStringPropertyEvent	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   351: ldc 41
    //   353: ldc -61
    //   355: invokestatic 165	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   358: pop
    //   359: goto -186 -> 173
    //   362: ldc -106
    //   364: ldc -104
    //   366: ldc -59
    //   368: invokestatic 160	com/android/mms/util/MiStatSdkHelper:recordStringPropertyEvent	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   371: ldc 41
    //   373: ldc -57
    //   375: invokestatic 65	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   378: pop
    //   379: new 25	java/io/File
    //   382: dup
    //   383: aload_3
    //   384: invokespecial 28	java/io/File:<init>	(Ljava/lang/String;)V
    //   387: invokevirtual 176	java/io/File:delete	()Z
    //   390: pop
    //   391: new 25	java/io/File
    //   394: dup
    //   395: aload 4
    //   397: invokespecial 28	java/io/File:<init>	(Ljava/lang/String;)V
    //   400: invokevirtual 176	java/io/File:delete	()Z
    //   403: pop
    //   404: lconst_0
    //   405: invokestatic 148	com/android/mms/understand/TemplateUpdate:updateTemplateVersion	(J)V
    //   408: goto -235 -> 173
    //   411: aconst_null
    //   412: astore 8
    //   414: aconst_null
    //   415: astore 7
    //   417: aconst_null
    //   418: astore_2
    //   419: aconst_null
    //   420: astore 5
    //   422: aconst_null
    //   423: astore 6
    //   425: aload 8
    //   427: astore_3
    //   428: aload_2
    //   429: astore 4
    //   431: aload_0
    //   432: getfield 144	com/android/mms/understand/TemplateUpdate$UpdatePatch:mMd5	Ljava/lang/String;
    //   435: invokestatic 129	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   438: ifne +46 -> 484
    //   441: aload 8
    //   443: astore_3
    //   444: aload_2
    //   445: astore 4
    //   447: aload_0
    //   448: getfield 144	com/android/mms/understand/TemplateUpdate$UpdatePatch:mMd5	Ljava/lang/String;
    //   451: new 25	java/io/File
    //   454: dup
    //   455: invokestatic 112	com/android/mms/understand/TemplateUpdate:getDownloadedFile	()Ljava/lang/String;
    //   458: invokespecial 28	java/io/File:<init>	(Ljava/lang/String;)V
    //   461: invokestatic 135	com/android/mms/util/Coder:encodeMD5	(Ljava/io/File;)Ljava/lang/String;
    //   464: invokevirtual 141	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   467: ifeq +138 -> 605
    //   470: aload 8
    //   472: astore_3
    //   473: aload_2
    //   474: astore 4
    //   476: ldc 41
    //   478: ldc -83
    //   480: invokestatic 119	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   483: pop
    //   484: aload 8
    //   486: astore_3
    //   487: aload_2
    //   488: astore 4
    //   490: new 201	java/io/FileInputStream
    //   493: dup
    //   494: invokestatic 112	com/android/mms/understand/TemplateUpdate:getDownloadedFile	()Ljava/lang/String;
    //   497: invokespecial 202	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   500: astore_2
    //   501: aload_2
    //   502: invokevirtual 208	java/io/InputStream:available	()I
    //   505: newarray <illegal type>
    //   507: astore 4
    //   509: aload_2
    //   510: aload 4
    //   512: invokevirtual 212	java/io/InputStream:read	([B)I
    //   515: pop
    //   516: new 214	java/io/FileOutputStream
    //   519: dup
    //   520: invokestatic 108	com/android/mms/understand/TemplateUpdate:getUpdatedFile	()Ljava/lang/String;
    //   523: invokespecial 215	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   526: astore_3
    //   527: aload_3
    //   528: aload 4
    //   530: invokevirtual 219	java/io/FileOutputStream:write	([B)V
    //   533: ldc -106
    //   535: ldc -104
    //   537: ldc -70
    //   539: invokestatic 160	com/android/mms/util/MiStatSdkHelper:recordStringPropertyEvent	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   542: aload_3
    //   543: ifnull +7 -> 550
    //   546: aload_3
    //   547: invokevirtual 222	java/io/FileOutputStream:close	()V
    //   550: aload_2
    //   551: ifnull +7 -> 558
    //   554: aload_2
    //   555: invokevirtual 223	java/io/InputStream:close	()V
    //   558: ldc 41
    //   560: new 43	java/lang/StringBuilder
    //   563: dup
    //   564: invokespecial 44	java/lang/StringBuilder:<init>	()V
    //   567: ldc -31
    //   569: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   572: invokestatic 112	com/android/mms/understand/TemplateUpdate:getDownloadedFile	()Ljava/lang/String;
    //   575: invokevirtual 226	java/lang/String:toString	()Ljava/lang/String;
    //   578: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   581: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   584: invokestatic 119	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   587: pop
    //   588: new 25	java/io/File
    //   591: dup
    //   592: invokestatic 112	com/android/mms/understand/TemplateUpdate:getDownloadedFile	()Ljava/lang/String;
    //   595: invokespecial 28	java/io/File:<init>	(Ljava/lang/String;)V
    //   598: invokevirtual 176	java/io/File:delete	()Z
    //   601: pop
    //   602: goto -271 -> 331
    //   605: aload 8
    //   607: astore_3
    //   608: aload_2
    //   609: astore 4
    //   611: ldc -106
    //   613: ldc -104
    //   615: ldc -28
    //   617: invokestatic 160	com/android/mms/util/MiStatSdkHelper:recordStringPropertyEvent	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   620: aload 8
    //   622: astore_3
    //   623: aload_2
    //   624: astore 4
    //   626: ldc 41
    //   628: ldc -61
    //   630: invokestatic 165	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   633: pop
    //   634: iconst_0
    //   635: ifeq +11 -> 646
    //   638: new 230	java/lang/NullPointerException
    //   641: dup
    //   642: invokespecial 231	java/lang/NullPointerException:<init>	()V
    //   645: athrow
    //   646: iconst_0
    //   647: ifeq +11 -> 658
    //   650: new 230	java/lang/NullPointerException
    //   653: dup
    //   654: invokespecial 231	java/lang/NullPointerException:<init>	()V
    //   657: athrow
    //   658: ldc 41
    //   660: new 43	java/lang/StringBuilder
    //   663: dup
    //   664: invokespecial 44	java/lang/StringBuilder:<init>	()V
    //   667: ldc -31
    //   669: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   672: invokestatic 112	com/android/mms/understand/TemplateUpdate:getDownloadedFile	()Ljava/lang/String;
    //   675: invokevirtual 226	java/lang/String:toString	()Ljava/lang/String;
    //   678: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   681: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   684: invokestatic 119	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   687: pop
    //   688: new 25	java/io/File
    //   691: dup
    //   692: invokestatic 112	com/android/mms/understand/TemplateUpdate:getDownloadedFile	()Ljava/lang/String;
    //   695: invokespecial 28	java/io/File:<init>	(Ljava/lang/String;)V
    //   698: invokevirtual 176	java/io/File:delete	()Z
    //   701: pop
    //   702: goto -529 -> 173
    //   705: astore_0
    //   706: aload_0
    //   707: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   710: goto -64 -> 646
    //   713: astore_0
    //   714: aload_0
    //   715: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   718: goto -60 -> 658
    //   721: astore_3
    //   722: aload_3
    //   723: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   726: goto -176 -> 550
    //   729: astore_2
    //   730: aload_2
    //   731: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   734: goto -176 -> 558
    //   737: astore 5
    //   739: aload 7
    //   741: astore_2
    //   742: aload_2
    //   743: astore_3
    //   744: aload 6
    //   746: astore 4
    //   748: ldc -106
    //   750: ldc -104
    //   752: ldc -23
    //   754: invokestatic 160	com/android/mms/util/MiStatSdkHelper:recordStringPropertyEvent	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   757: aload_2
    //   758: astore_3
    //   759: aload 6
    //   761: astore 4
    //   763: aload 5
    //   765: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   768: aload 6
    //   770: ifnull +8 -> 778
    //   773: aload 6
    //   775: invokevirtual 222	java/io/FileOutputStream:close	()V
    //   778: aload_2
    //   779: ifnull +7 -> 786
    //   782: aload_2
    //   783: invokevirtual 223	java/io/InputStream:close	()V
    //   786: ldc 41
    //   788: new 43	java/lang/StringBuilder
    //   791: dup
    //   792: invokespecial 44	java/lang/StringBuilder:<init>	()V
    //   795: ldc -31
    //   797: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   800: invokestatic 112	com/android/mms/understand/TemplateUpdate:getDownloadedFile	()Ljava/lang/String;
    //   803: invokevirtual 226	java/lang/String:toString	()Ljava/lang/String;
    //   806: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   809: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   812: invokestatic 119	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   815: pop
    //   816: new 25	java/io/File
    //   819: dup
    //   820: invokestatic 112	com/android/mms/understand/TemplateUpdate:getDownloadedFile	()Ljava/lang/String;
    //   823: invokespecial 28	java/io/File:<init>	(Ljava/lang/String;)V
    //   826: invokevirtual 176	java/io/File:delete	()Z
    //   829: pop
    //   830: goto -499 -> 331
    //   833: astore_3
    //   834: aload_3
    //   835: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   838: goto -60 -> 778
    //   841: astore_2
    //   842: aload_2
    //   843: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   846: goto -60 -> 786
    //   849: astore_0
    //   850: aload 4
    //   852: ifnull +8 -> 860
    //   855: aload 4
    //   857: invokevirtual 222	java/io/FileOutputStream:close	()V
    //   860: aload_3
    //   861: ifnull +7 -> 868
    //   864: aload_3
    //   865: invokevirtual 223	java/io/InputStream:close	()V
    //   868: ldc 41
    //   870: new 43	java/lang/StringBuilder
    //   873: dup
    //   874: invokespecial 44	java/lang/StringBuilder:<init>	()V
    //   877: ldc -31
    //   879: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   882: invokestatic 112	com/android/mms/understand/TemplateUpdate:getDownloadedFile	()Ljava/lang/String;
    //   885: invokevirtual 226	java/lang/String:toString	()Ljava/lang/String;
    //   888: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   891: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   894: invokestatic 119	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   897: pop
    //   898: new 25	java/io/File
    //   901: dup
    //   902: invokestatic 112	com/android/mms/understand/TemplateUpdate:getDownloadedFile	()Ljava/lang/String;
    //   905: invokespecial 28	java/io/File:<init>	(Ljava/lang/String;)V
    //   908: invokevirtual 176	java/io/File:delete	()Z
    //   911: pop
    //   912: aload_0
    //   913: athrow
    //   914: astore_2
    //   915: aload_2
    //   916: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   919: goto -59 -> 860
    //   922: astore_2
    //   923: aload_2
    //   924: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   927: goto -59 -> 868
    //   930: astore_0
    //   931: aload_2
    //   932: astore_3
    //   933: aload 5
    //   935: astore 4
    //   937: goto -87 -> 850
    //   940: astore_0
    //   941: aload_3
    //   942: astore 4
    //   944: aload_2
    //   945: astore_3
    //   946: goto -96 -> 850
    //   949: astore 5
    //   951: goto -209 -> 742
    //   954: astore 5
    //   956: aload_3
    //   957: astore 6
    //   959: goto -217 -> 742
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	962	0	paramUpdatePatch	UpdatePatch
    //   168	2	1	bool	boolean
    //   22	602	2	localObject1	Object
    //   729	2	2	localIOException1	IOException
    //   741	42	2	localObject2	Object
    //   841	2	2	localIOException2	IOException
    //   914	2	2	localIOException3	IOException
    //   922	23	2	localIOException4	IOException
    //   50	573	3	localObject3	Object
    //   721	2	3	localIOException5	IOException
    //   743	16	3	localObject4	Object
    //   833	32	3	localIOException6	IOException
    //   932	25	3	localIOException7	IOException
    //   45	898	4	localObject5	Object
    //   17	404	5	localPatcher	miui.util.Patcher
    //   737	197	5	localIOException8	IOException
    //   949	1	5	localIOException9	IOException
    //   954	1	5	localIOException10	IOException
    //   135	823	6	localObject6	Object
    //   415	325	7	localObject7	Object
    //   412	209	8	localObject8	Object
    // Exception table:
    //   from	to	target	type
    //   3	51	201	finally
    //   53	67	201	finally
    //   70	169	201	finally
    //   177	198	201	finally
    //   207	266	201	finally
    //   266	310	201	finally
    //   310	331	201	finally
    //   331	335	201	finally
    //   338	359	201	finally
    //   362	408	201	finally
    //   546	550	201	finally
    //   554	558	201	finally
    //   558	602	201	finally
    //   638	646	201	finally
    //   650	658	201	finally
    //   658	702	201	finally
    //   706	710	201	finally
    //   714	718	201	finally
    //   722	726	201	finally
    //   730	734	201	finally
    //   773	778	201	finally
    //   782	786	201	finally
    //   786	830	201	finally
    //   834	838	201	finally
    //   842	846	201	finally
    //   855	860	201	finally
    //   864	868	201	finally
    //   868	914	201	finally
    //   915	919	201	finally
    //   923	927	201	finally
    //   638	646	705	java/io/IOException
    //   650	658	713	java/io/IOException
    //   546	550	721	java/io/IOException
    //   554	558	729	java/io/IOException
    //   431	441	737	java/io/IOException
    //   447	470	737	java/io/IOException
    //   476	484	737	java/io/IOException
    //   490	501	737	java/io/IOException
    //   611	620	737	java/io/IOException
    //   626	634	737	java/io/IOException
    //   773	778	833	java/io/IOException
    //   782	786	841	java/io/IOException
    //   431	441	849	finally
    //   447	470	849	finally
    //   476	484	849	finally
    //   490	501	849	finally
    //   611	620	849	finally
    //   626	634	849	finally
    //   748	757	849	finally
    //   763	768	849	finally
    //   855	860	914	java/io/IOException
    //   864	868	922	java/io/IOException
    //   501	527	930	finally
    //   527	542	940	finally
    //   501	527	949	java/io/IOException
    //   527	542	954	java/io/IOException
  }
  
  /* Error */
  public static void updateTemplateVersion(long paramLong)
  {
    // Byte code:
    //   0: ldc 41
    //   2: new 43	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 44	java/lang/StringBuilder:<init>	()V
    //   9: ldc -21
    //   11: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   14: lload_0
    //   15: invokevirtual 238	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   18: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   21: invokestatic 119	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   24: pop
    //   25: aconst_null
    //   26: astore 7
    //   28: aconst_null
    //   29: astore 9
    //   31: aconst_null
    //   32: astore 8
    //   34: aconst_null
    //   35: astore 5
    //   37: aconst_null
    //   38: astore_3
    //   39: aconst_null
    //   40: astore 6
    //   42: aconst_null
    //   43: astore 10
    //   45: aconst_null
    //   46: astore 4
    //   48: new 214	java/io/FileOutputStream
    //   51: dup
    //   52: invokestatic 240	com/android/mms/understand/TemplateUpdate:getVersionFile	()Ljava/lang/String;
    //   55: invokespecial 215	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   58: astore_2
    //   59: new 242	java/io/OutputStreamWriter
    //   62: dup
    //   63: aload_2
    //   64: invokespecial 245	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
    //   67: astore_3
    //   68: new 247	java/io/BufferedWriter
    //   71: dup
    //   72: aload_3
    //   73: invokespecial 250	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   76: astore 4
    //   78: aload 4
    //   80: lload_0
    //   81: invokestatic 254	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   84: invokevirtual 256	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   87: aload 4
    //   89: invokevirtual 259	java/io/BufferedWriter:newLine	()V
    //   92: aload 4
    //   94: invokevirtual 262	java/io/BufferedWriter:flush	()V
    //   97: aload 4
    //   99: ifnull +8 -> 107
    //   102: aload 4
    //   104: invokevirtual 263	java/io/BufferedWriter:close	()V
    //   107: aload_3
    //   108: ifnull +7 -> 115
    //   111: aload_3
    //   112: invokevirtual 264	java/io/OutputStreamWriter:close	()V
    //   115: aload_2
    //   116: ifnull +236 -> 352
    //   119: aload_2
    //   120: invokevirtual 267	java/io/OutputStream:close	()V
    //   123: return
    //   124: astore 4
    //   126: aload 4
    //   128: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   131: goto -24 -> 107
    //   134: astore_3
    //   135: aload_3
    //   136: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   139: goto -24 -> 115
    //   142: astore_2
    //   143: aload_2
    //   144: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   147: return
    //   148: astore 5
    //   150: aload_3
    //   151: astore_2
    //   152: aload 5
    //   154: astore_3
    //   155: aload_2
    //   156: astore 5
    //   158: aload 4
    //   160: astore 6
    //   162: aload 8
    //   164: astore 7
    //   166: aload_3
    //   167: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   170: aload 8
    //   172: ifnull +8 -> 180
    //   175: aload 8
    //   177: invokevirtual 263	java/io/BufferedWriter:close	()V
    //   180: aload 4
    //   182: ifnull +8 -> 190
    //   185: aload 4
    //   187: invokevirtual 264	java/io/OutputStreamWriter:close	()V
    //   190: aload_2
    //   191: ifnull -68 -> 123
    //   194: aload_2
    //   195: invokevirtual 267	java/io/OutputStream:close	()V
    //   198: return
    //   199: astore_2
    //   200: aload_2
    //   201: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   204: return
    //   205: astore_3
    //   206: aload_3
    //   207: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   210: goto -30 -> 180
    //   213: astore_3
    //   214: aload_3
    //   215: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   218: goto -28 -> 190
    //   221: astore 4
    //   223: aload 6
    //   225: astore_3
    //   226: aload 5
    //   228: astore_2
    //   229: aload 7
    //   231: ifnull +8 -> 239
    //   234: aload 7
    //   236: invokevirtual 263	java/io/BufferedWriter:close	()V
    //   239: aload_3
    //   240: ifnull +7 -> 247
    //   243: aload_3
    //   244: invokevirtual 264	java/io/OutputStreamWriter:close	()V
    //   247: aload_2
    //   248: ifnull +7 -> 255
    //   251: aload_2
    //   252: invokevirtual 267	java/io/OutputStream:close	()V
    //   255: aload 4
    //   257: athrow
    //   258: astore 5
    //   260: aload 5
    //   262: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   265: goto -26 -> 239
    //   268: astore_3
    //   269: aload_3
    //   270: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   273: goto -26 -> 247
    //   276: astore_2
    //   277: aload_2
    //   278: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   281: goto -26 -> 255
    //   284: astore 4
    //   286: aload 10
    //   288: astore_3
    //   289: aload 9
    //   291: astore 7
    //   293: goto -64 -> 229
    //   296: astore 4
    //   298: aload 9
    //   300: astore 7
    //   302: goto -73 -> 229
    //   305: astore 5
    //   307: aload 4
    //   309: astore 7
    //   311: aload 5
    //   313: astore 4
    //   315: goto -86 -> 229
    //   318: astore_3
    //   319: goto -164 -> 155
    //   322: astore 5
    //   324: aload_3
    //   325: astore 4
    //   327: aload 5
    //   329: astore_3
    //   330: goto -175 -> 155
    //   333: astore 6
    //   335: aload_3
    //   336: astore 5
    //   338: aload 4
    //   340: astore 8
    //   342: aload 6
    //   344: astore_3
    //   345: aload 5
    //   347: astore 4
    //   349: goto -194 -> 155
    //   352: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	353	0	paramLong	long
    //   58	62	2	localFileOutputStream	java.io.FileOutputStream
    //   142	2	2	localIOException1	IOException
    //   151	44	2	localIOException2	IOException
    //   199	2	2	localIOException3	IOException
    //   228	24	2	localObject1	Object
    //   276	2	2	localIOException4	IOException
    //   38	74	3	localOutputStreamWriter	java.io.OutputStreamWriter
    //   134	17	3	localIOException5	IOException
    //   154	13	3	localIOException6	IOException
    //   205	2	3	localIOException7	IOException
    //   213	2	3	localIOException8	IOException
    //   225	19	3	localObject2	Object
    //   268	2	3	localIOException9	IOException
    //   288	1	3	localObject3	Object
    //   318	7	3	localIOException10	IOException
    //   329	16	3	localObject4	Object
    //   46	57	4	localBufferedWriter	java.io.BufferedWriter
    //   124	62	4	localIOException11	IOException
    //   221	35	4	localObject5	Object
    //   284	1	4	localObject6	Object
    //   296	12	4	localObject7	Object
    //   313	35	4	localObject8	Object
    //   35	1	5	localObject9	Object
    //   148	5	5	localIOException12	IOException
    //   156	71	5	localObject10	Object
    //   258	3	5	localIOException13	IOException
    //   305	7	5	localObject11	Object
    //   322	6	5	localIOException14	IOException
    //   336	10	5	localObject12	Object
    //   40	184	6	localObject13	Object
    //   333	10	6	localIOException15	IOException
    //   26	284	7	localObject14	Object
    //   32	309	8	localObject15	Object
    //   29	270	9	localObject16	Object
    //   43	244	10	localObject17	Object
    // Exception table:
    //   from	to	target	type
    //   102	107	124	java/io/IOException
    //   111	115	134	java/io/IOException
    //   119	123	142	java/io/IOException
    //   48	59	148	java/io/IOException
    //   194	198	199	java/io/IOException
    //   175	180	205	java/io/IOException
    //   185	190	213	java/io/IOException
    //   48	59	221	finally
    //   166	170	221	finally
    //   234	239	258	java/io/IOException
    //   243	247	268	java/io/IOException
    //   251	255	276	java/io/IOException
    //   59	68	284	finally
    //   68	78	296	finally
    //   78	97	305	finally
    //   59	68	318	java/io/IOException
    //   68	78	322	java/io/IOException
    //   78	97	333	java/io/IOException
  }
  
  public static class UpdatePatch
  {
    public boolean mIsIncremental;
    public String mMd5;
    public String mOldMd5;
    public long mVersion;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.understand.TemplateUpdate
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */