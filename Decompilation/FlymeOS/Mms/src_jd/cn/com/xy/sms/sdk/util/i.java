package cn.com.xy.sms.sdk.util;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.action.AbsSdkDoAction;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.c;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.e;
import cn.com.xy.sms.sdk.db.entity.k;
import cn.com.xy.sms.sdk.db.entity.s;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.util.l;
import cn.com.xy.sms.util.ParseManager;
import cn.com.xy.sms.util.SdkParamUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class i
  extends Thread
{
  private static boolean a = false;
  
  public static void a()
  {
    try
    {
      if (!a) {
        new i().start();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static void a(cn.com.xy.sms.sdk.db.entity.d paramd)
  {
    do
    {
      do
      {
        for (;;)
        {
          try
          {
            long l = System.currentTimeMillis();
            new StringBuilder("检查下载时间jarSubInfo.delaystart=").append(h).append("jarSubInfo.delayend=").append(paramd.i).append("nowTime=").append(l).append(" jarSubInfo.status=").append(f);
            if ((f != 0) || (h > l) || (paramd.i < l)) {
              break;
            }
            new StringBuilder("超过周期，启动下载 name=").append(b).append(" url=").append(d);
            String str1;
            Object localObject;
            String str2;
            if (!StringUtils.isNull(d))
            {
              str1 = b + ".zip";
              String str3 = b + ".sql";
              localObject = b + ".txt";
              d.f(d, Constant.getFilePath(), str1);
              XyUtil.upZipFile(Constant.getFilePath() + str1, Constant.getPARSE_PATH());
              new StringBuilder("成功下载").append(str1).append("，成功解压").append(str1);
              str2 = Constant.getPARSE_PATH() + str3;
              if (d.a(str2))
              {
                str3 = StringUtils.getFileMD5(Constant.getPARSE_PATH() + str3);
                localObject = l.a(Constant.getPARSE_PATH(), (String)localObject);
                if ((!LogManager.debug) || (str3.equals(localObject)))
                {
                  Constant.getContext();
                  a(str2);
                }
              }
              str2 = b;
            }
            try
            {
              l = System.currentTimeMillis();
              localObject = new ContentValues();
              ((ContentValues)localObject).put("last_load_time", l);
              ((ContentValues)localObject).put("status", "1");
              DBManager.update("tb_jar_list", (ContentValues)localObject, "name = ? ", new String[] { str2 });
              SdkParamUtil.setParamValue(Constant.getContext(), "SMART_DATA_UPDATE_TIME", l);
              d.a(Constant.getPARSE_PATH(), b + "_", ".jar", b + "_" + c + ".jar");
              d.b(b + "_", ".dex", b + "_" + c + ".dex");
              d.a(Constant.getPARSE_PATH(), b + ".jar", b + "_" + c + ".jar");
              if ("parseUtilMain".equals(b))
              {
                DexUtil.init();
                d.c(Constant.getFilePath() + str1);
                SysParamEntityManager.setParam("BEFORE_HAND_PARSE_SMS_TIME", System.currentTimeMillis());
              }
            }
            catch (Throwable localThrowable)
            {
              try
              {
                int i = ParseManager.getParseVersion(Constant.getContext(), null) + 1;
                SdkParamUtil.setParamValue(Constant.getContext(), "PARSE_VERSION", i);
                if (System.currentTimeMillis() >= Constant.lastVersionChangeTime + 600000L)
                {
                  DuoquUtils.getSdkDoAction().parseVersionChange(i);
                  Constant.lastVersionChangeTime = System.currentTimeMillis();
                }
                return;
              }
              catch (Throwable paramd) {}
              localThrowable = localThrowable;
              localThrowable.printStackTrace();
              continue;
            }
            DexUtil.removeClassLoaderBySubname(b);
          }
          catch (Throwable paramd)
          {
            paramd.printStackTrace();
            return;
          }
          if ("OnlineUpdateCycleConfig".equals(b)) {
            DexUtil.initOnlineUpdateCycleConfig();
          }
        }
        if (f != 1) {
          break;
        }
      } while (!LogManager.debug);
      new StringBuilder(String.valueOf(b)).append("已经下载，不需要下载 url=").append(d);
      return;
    } while (!LogManager.debug);
    new StringBuilder("当前时间不在下载区域内，不下载name=").append(b).append(" url=").append(d);
    return;
  }
  
  /* Error */
  private static void a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: new 29	java/lang/StringBuilder
    //   6: dup
    //   7: ldc_w 257
    //   10: invokespecial 34	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   13: aload_0
    //   14: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: ldc_w 259
    //   20: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: pop
    //   24: aload_0
    //   25: invokestatic 121	cn/com/xy/sms/sdk/util/d:a	(Ljava/lang/String;)Z
    //   28: ifeq +80 -> 108
    //   31: new 261	java/io/BufferedReader
    //   34: dup
    //   35: new 263	java/io/FileReader
    //   38: dup
    //   39: new 265	java/io/File
    //   42: dup
    //   43: aload_0
    //   44: invokespecial 266	java/io/File:<init>	(Ljava/lang/String;)V
    //   47: invokespecial 269	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   50: invokespecial 272	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   53: astore 4
    //   55: new 274	java/io/LineNumberReader
    //   58: dup
    //   59: aload 4
    //   61: invokespecial 275	java/io/LineNumberReader:<init>	(Ljava/io/Reader;)V
    //   64: astore 6
    //   66: invokestatic 280	cn/com/xy/sms/sdk/db/a/a:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   69: astore 7
    //   71: aload 7
    //   73: invokevirtual 285	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   76: aload 6
    //   78: invokevirtual 288	java/io/LineNumberReader:readLine	()Ljava/lang/String;
    //   81: astore 9
    //   83: aload 9
    //   85: ifnonnull +24 -> 109
    //   88: aload 6
    //   90: invokevirtual 291	java/io/LineNumberReader:close	()V
    //   93: aload_0
    //   94: invokestatic 293	cn/com/xy/sms/sdk/util/d:d	(Ljava/lang/String;)V
    //   97: aload_0
    //   98: iconst_1
    //   99: aload 6
    //   101: aload 4
    //   103: aload 7
    //   105: invokestatic 297	cn/com/xy/sms/sdk/db/DBManager:closeDB	(Ljava/lang/String;ZLjava/io/LineNumberReader;Ljava/io/BufferedReader;Landroid/database/sqlite/SQLiteDatabase;)V
    //   108: return
    //   109: aload 9
    //   111: invokestatic 80	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   114: ifne -38 -> 76
    //   117: new 147	android/content/ContentValues
    //   120: dup
    //   121: invokespecial 148	android/content/ContentValues:<init>	()V
    //   124: astore 5
    //   126: aload 9
    //   128: iconst_0
    //   129: aload 9
    //   131: ldc_w 299
    //   134: invokevirtual 303	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   137: invokevirtual 307	java/lang/String:substring	(II)Ljava/lang/String;
    //   140: astore 8
    //   142: aload 9
    //   144: aload 9
    //   146: ldc_w 299
    //   149: invokevirtual 303	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   152: iconst_1
    //   153: iadd
    //   154: aload 9
    //   156: invokevirtual 311	java/lang/String:length	()I
    //   159: invokevirtual 307	java/lang/String:substring	(II)Ljava/lang/String;
    //   162: astore 10
    //   164: aload 10
    //   166: iconst_0
    //   167: aload 10
    //   169: ldc_w 299
    //   172: invokevirtual 303	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   175: invokevirtual 307	java/lang/String:substring	(II)Ljava/lang/String;
    //   178: astore 9
    //   180: aload 10
    //   182: aload 10
    //   184: ldc_w 299
    //   187: invokevirtual 303	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   190: iconst_1
    //   191: iadd
    //   192: aload 10
    //   194: invokevirtual 311	java/lang/String:length	()I
    //   197: invokevirtual 307	java/lang/String:substring	(II)Ljava/lang/String;
    //   200: astore 11
    //   202: aload 11
    //   204: iconst_0
    //   205: aload 11
    //   207: ldc_w 299
    //   210: invokevirtual 303	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   213: invokevirtual 307	java/lang/String:substring	(II)Ljava/lang/String;
    //   216: astore 10
    //   218: aload 11
    //   220: aload 11
    //   222: ldc_w 299
    //   225: invokevirtual 303	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   228: iconst_1
    //   229: iadd
    //   230: aload 11
    //   232: invokevirtual 311	java/lang/String:length	()I
    //   235: invokevirtual 307	java/lang/String:substring	(II)Ljava/lang/String;
    //   238: astore 12
    //   240: aload 12
    //   242: iconst_0
    //   243: aload 12
    //   245: ldc_w 299
    //   248: invokevirtual 303	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   251: invokevirtual 307	java/lang/String:substring	(II)Ljava/lang/String;
    //   254: astore 11
    //   256: aload 12
    //   258: aload 12
    //   260: ldc_w 299
    //   263: invokevirtual 303	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   266: iconst_1
    //   267: iadd
    //   268: aload 12
    //   270: invokevirtual 311	java/lang/String:length	()I
    //   273: invokevirtual 307	java/lang/String:substring	(II)Ljava/lang/String;
    //   276: astore 12
    //   278: aload 5
    //   280: ldc_w 313
    //   283: aload 8
    //   285: invokevirtual 156	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   288: aload 5
    //   290: ldc_w 315
    //   293: aload 9
    //   295: invokevirtual 156	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   298: aload 5
    //   300: ldc_w 317
    //   303: aload 10
    //   305: invokevirtual 156	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   308: aload 5
    //   310: ldc_w 319
    //   313: aload 11
    //   315: invokevirtual 156	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   318: aload 5
    //   320: ldc_w 321
    //   323: aload 12
    //   325: invokevirtual 156	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   328: new 29	java/lang/StringBuilder
    //   331: dup
    //   332: ldc_w 323
    //   335: invokespecial 34	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   338: aload 8
    //   340: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   343: ldc_w 325
    //   346: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   349: aload 9
    //   351: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   354: ldc_w 327
    //   357: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   360: aload 10
    //   362: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   365: pop
    //   366: aload 7
    //   368: ldc_w 329
    //   371: aload 5
    //   373: ldc_w 331
    //   376: iconst_2
    //   377: anewarray 82	java/lang/String
    //   380: dup
    //   381: iconst_0
    //   382: aload 8
    //   384: aastore
    //   385: dup
    //   386: iconst_1
    //   387: aload 9
    //   389: aastore
    //   390: invokevirtual 332	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   393: istore_1
    //   394: iload_1
    //   395: i2l
    //   396: lstore_2
    //   397: lload_2
    //   398: lconst_0
    //   399: lcmp
    //   400: ifne -324 -> 76
    //   403: aload 7
    //   405: ldc_w 329
    //   408: aconst_null
    //   409: aload 5
    //   411: invokevirtual 336	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   414: pop2
    //   415: goto -339 -> 76
    //   418: astore 5
    //   420: aload 5
    //   422: invokevirtual 243	java/lang/Throwable:printStackTrace	()V
    //   425: new 29	java/lang/StringBuilder
    //   428: dup
    //   429: ldc_w 338
    //   432: invokespecial 34	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   435: aload 5
    //   437: invokevirtual 341	java/lang/Throwable:getLocalizedMessage	()Ljava/lang/String;
    //   440: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   443: pop
    //   444: goto -368 -> 76
    //   447: astore 5
    //   449: aload 5
    //   451: invokevirtual 243	java/lang/Throwable:printStackTrace	()V
    //   454: new 29	java/lang/StringBuilder
    //   457: dup
    //   458: ldc_w 343
    //   461: invokespecial 34	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   464: aload 5
    //   466: invokevirtual 341	java/lang/Throwable:getLocalizedMessage	()Ljava/lang/String;
    //   469: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   472: pop
    //   473: goto -397 -> 76
    //   476: astore 5
    //   478: aload 5
    //   480: invokevirtual 243	java/lang/Throwable:printStackTrace	()V
    //   483: new 29	java/lang/StringBuilder
    //   486: dup
    //   487: ldc_w 345
    //   490: invokespecial 34	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   493: aload 5
    //   495: invokevirtual 341	java/lang/Throwable:getLocalizedMessage	()Ljava/lang/String;
    //   498: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   501: pop
    //   502: goto -409 -> 93
    //   505: astore 5
    //   507: aload 7
    //   509: astore 5
    //   511: aload_0
    //   512: iconst_1
    //   513: aload 6
    //   515: aload 4
    //   517: aload 5
    //   519: invokestatic 297	cn/com/xy/sms/sdk/db/DBManager:closeDB	(Ljava/lang/String;ZLjava/io/LineNumberReader;Ljava/io/BufferedReader;Landroid/database/sqlite/SQLiteDatabase;)V
    //   522: return
    //   523: astore 8
    //   525: aload 8
    //   527: invokevirtual 243	java/lang/Throwable:printStackTrace	()V
    //   530: new 29	java/lang/StringBuilder
    //   533: dup
    //   534: ldc_w 347
    //   537: invokespecial 34	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   540: aload 8
    //   542: invokevirtual 341	java/lang/Throwable:getLocalizedMessage	()Ljava/lang/String;
    //   545: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   548: pop
    //   549: lconst_0
    //   550: lstore_2
    //   551: goto -154 -> 397
    //   554: astore 5
    //   556: aconst_null
    //   557: astore 8
    //   559: aconst_null
    //   560: astore 4
    //   562: aconst_null
    //   563: astore 6
    //   565: aload_0
    //   566: iconst_1
    //   567: aload 6
    //   569: aload 8
    //   571: aload 4
    //   573: invokestatic 297	cn/com/xy/sms/sdk/db/DBManager:closeDB	(Ljava/lang/String;ZLjava/io/LineNumberReader;Ljava/io/BufferedReader;Landroid/database/sqlite/SQLiteDatabase;)V
    //   576: aload 5
    //   578: athrow
    //   579: astore 5
    //   581: aconst_null
    //   582: astore 7
    //   584: aconst_null
    //   585: astore 6
    //   587: aload 4
    //   589: astore 8
    //   591: aload 7
    //   593: astore 4
    //   595: goto -30 -> 565
    //   598: astore 5
    //   600: aconst_null
    //   601: astore 7
    //   603: aload 4
    //   605: astore 8
    //   607: aload 7
    //   609: astore 4
    //   611: goto -46 -> 565
    //   614: astore 5
    //   616: aload 4
    //   618: astore 8
    //   620: aload 7
    //   622: astore 4
    //   624: goto -59 -> 565
    //   627: astore 4
    //   629: aconst_null
    //   630: astore 4
    //   632: aconst_null
    //   633: astore 7
    //   635: aload 5
    //   637: astore 6
    //   639: aload 7
    //   641: astore 5
    //   643: goto -132 -> 511
    //   646: astore 6
    //   648: aconst_null
    //   649: astore 7
    //   651: aload 5
    //   653: astore 6
    //   655: aload 7
    //   657: astore 5
    //   659: goto -148 -> 511
    //   662: astore 5
    //   664: aconst_null
    //   665: astore 5
    //   667: goto -156 -> 511
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	670	0	paramString	String
    //   393	2	1	i	int
    //   396	155	2	l	long
    //   53	570	4	localObject1	Object
    //   627	1	4	localThrowable1	Throwable
    //   630	1	4	localObject2	Object
    //   1	409	5	localContentValues	ContentValues
    //   418	18	5	localThrowable2	Throwable
    //   447	18	5	localThrowable3	Throwable
    //   476	18	5	localThrowable4	Throwable
    //   505	1	5	localThrowable5	Throwable
    //   509	9	5	localObject3	Object
    //   554	23	5	localObject4	Object
    //   579	1	5	localObject5	Object
    //   598	1	5	localObject6	Object
    //   614	22	5	localObject7	Object
    //   641	17	5	localObject8	Object
    //   662	1	5	localThrowable6	Throwable
    //   665	1	5	localObject9	Object
    //   64	574	6	localObject10	Object
    //   646	1	6	localThrowable7	Throwable
    //   653	1	6	localObject11	Object
    //   69	587	7	localSQLiteDatabase	SQLiteDatabase
    //   140	243	8	str1	String
    //   523	18	8	localThrowable8	Throwable
    //   557	62	8	localObject12	Object
    //   81	307	9	str2	String
    //   162	199	10	str3	String
    //   200	114	11	str4	String
    //   238	86	12	str5	String
    // Exception table:
    //   from	to	target	type
    //   403	415	418	java/lang/Throwable
    //   109	366	447	java/lang/Throwable
    //   420	444	447	java/lang/Throwable
    //   525	549	447	java/lang/Throwable
    //   76	83	476	java/lang/Throwable
    //   88	93	476	java/lang/Throwable
    //   449	473	476	java/lang/Throwable
    //   71	76	505	java/lang/Throwable
    //   93	97	505	java/lang/Throwable
    //   478	502	505	java/lang/Throwable
    //   366	394	523	java/lang/Throwable
    //   31	55	554	finally
    //   55	66	579	finally
    //   66	71	598	finally
    //   71	76	614	finally
    //   76	83	614	finally
    //   88	93	614	finally
    //   93	97	614	finally
    //   109	366	614	finally
    //   366	394	614	finally
    //   403	415	614	finally
    //   420	444	614	finally
    //   449	473	614	finally
    //   478	502	614	finally
    //   525	549	614	finally
    //   31	55	627	java/lang/Throwable
    //   55	66	646	java/lang/Throwable
    //   66	71	662	java/lang/Throwable
  }
  
  private static void a(String paramString, SQLiteDatabase paramSQLiteDatabase)
  {
    long l;
    try
    {
      if (StringUtils.isNull(paramString)) {
        return;
      }
      localContentValues = new ContentValues();
      str1 = paramString.substring(0, paramString.indexOf(","));
      String str2 = paramString.substring(paramString.indexOf(",") + 1, paramString.length());
      paramString = str2.substring(0, str2.indexOf(","));
      String str3 = str2.substring(str2.indexOf(",") + 1, str2.length());
      str2 = str3.substring(0, str3.indexOf(","));
      String str4 = str3.substring(str3.indexOf(",") + 1, str3.length());
      str3 = str4.substring(0, str4.indexOf(","));
      str4 = str4.substring(str4.indexOf(",") + 1, str4.length());
      localContentValues.put("scene_id", str1);
      localContentValues.put("match_id", paramString);
      localContentValues.put("regex_type", str2);
      localContentValues.put("version_code", str3);
      localContentValues.put("regex_text", str4);
      new StringBuilder("scene_id=").append(str1).append(" match_id=").append(paramString).append(" regex_type=").append(str2);
    }
    catch (Throwable paramString)
    {
      ContentValues localContentValues;
      String str1;
      int i;
      label278:
      paramString.printStackTrace();
      new StringBuilder("未知 Throwable=").append(paramString.getLocalizedMessage());
      return;
    }
    try
    {
      i = paramSQLiteDatabase.update("tb_regex", localContentValues, "scene_id= ? and match_id =?", new String[] { str1, paramString });
      l = i;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
      new StringBuilder("更新数据 Throwable=").append(paramString.getLocalizedMessage());
      l = 0L;
      break label278;
    }
    if (l == 0L) {
      try
      {
        paramSQLiteDatabase.insert("tb_regex", null, localContentValues);
        return;
      }
      catch (Throwable paramString)
      {
        paramString.printStackTrace();
        new StringBuilder("插入数据 Throwable=").append(paramString.getLocalizedMessage());
        return;
      }
    }
  }
  
  public static void a(Map<String, String> paramMap, XyCallBack paramXyCallBack)
  {
    for (;;)
    {
      int j;
      int i;
      try
      {
        e();
        paramMap = e.a();
        if (!paramMap.isEmpty())
        {
          j = paramMap.size();
          i = 0;
        }
        else if ((!c.c(c.c())) && (!b()))
        {
          XyUtil.doXycallBack(paramXyCallBack, "1");
          return;
          cn.com.xy.sms.sdk.db.entity.d locald = (cn.com.xy.sms.sdk.db.entity.d)paramMap.get(i);
          new StringBuilder("checkJar =").append(locald);
          a(locald);
          i += 1;
        }
        else
        {
          XyUtil.doXycallBack(paramXyCallBack, "0");
          return;
        }
      }
      catch (Throwable paramMap)
      {
        return;
      }
      if (i < j) {}
    }
  }
  
  public static void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (!NetUtil.isEnhance()) {}
    for (;;)
    {
      return;
      e();
      List localList = e.a();
      try
      {
        if (!localList.isEmpty())
        {
          String str2 = SysParamEntityManager.getStringParam(Constant.getContext(), "EM_VERSION");
          String str1 = str2;
          if (StringUtils.isNull(str2)) {
            str1 = "-1";
          }
          str1 = cn.com.xy.sms.sdk.net.util.i.a(localList, k.b(), str1, paramBoolean2);
          if (str1 != null)
          {
            NetUtil.executeHttpRequest(0, str1, new j(paramBoolean1), NetUtil.getPopupServiceUrl() + "updatejar", null, false);
            return;
          }
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
      }
    }
  }
  
  public static boolean b()
  {
    try
    {
      e();
      a(false, true);
      Object localObject = e.a();
      if (!((List)localObject).isEmpty())
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          cn.com.xy.sms.sdk.db.entity.d locald = (cn.com.xy.sms.sdk.db.entity.d)((Iterator)localObject).next();
          if (!StringUtils.isNull(d))
          {
            int i = f;
            if (i == 0) {
              return true;
            }
          }
        }
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return false;
  }
  
  private static void c()
  {
    try
    {
      a(true, true);
      if (SysParamEntityManager.getIntParam(Constant.getContext(), "ONLINE_UPDATE_SDK") == 0) {
        return;
      }
      s.b("1");
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  private static void d()
  {
    try
    {
      int i = ParseManager.getParseVersion(Constant.getContext(), null) + 1;
      SdkParamUtil.setParamValue(Constant.getContext(), "PARSE_VERSION", i);
      if (System.currentTimeMillis() >= Constant.lastVersionChangeTime + 600000L)
      {
        DuoquUtils.getSdkDoAction().parseVersionChange(i);
        Constant.lastVersionChangeTime = System.currentTimeMillis();
      }
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  private static void e()
  {
    if (ParseManager.isInitData())
    {
      cn.com.xy.sms.sdk.db.entity.d locald = e.a("parseUtilMain");
      new StringBuilder("parseUtilMain jarSubInfo=").append(locald);
      if (locald == null)
      {
        e.a("parseUtilMain", "-1", 1);
        e.a("ParseHelper", "-1", 1);
      }
    }
  }
  
  public final void run()
  {
    if (!a) {
      a = true;
    }
    for (;;)
    {
      try
      {
        a(true, true);
        int i = SysParamEntityManager.getIntParam(Constant.getContext(), "ONLINE_UPDATE_SDK");
        if (i != 0) {
          continue;
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        continue;
      }
      a = false;
      return;
      s.b("1");
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.i
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */