package cn.com.xy.sms.sdk.db;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.f;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.util.i;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.x;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public final class c
{
  private static int a = 0;
  private static int b = 1;
  private static String c = "id";
  private static String d = "name";
  private static String e = "version";
  private static String f = "url";
  private static String g = "status";
  private static String h = "last_load_time";
  private static String i = "update_time";
  private static String j = "delaystart";
  private static String k = "delayend";
  private static String l = "count";
  private static String m = "tb_menu_list";
  private static String n = " DROP TABLE IF EXISTS tb_menu_list";
  private static String o = "create table  if not exists tb_menu_list (id INTEGER PRIMARY KEY,name TEXT,version TEXT,url TEXT,status INTEGER DEFAULT '0',update_time INTEGER DEFAULT '0',delaystart INTEGER DEFAULT '0',delayend INTEGER DEFAULT '0',count INTEGER DEFAULT '0',last_load_time INTEGER DEFAULT '0' )";
  
  public static void a()
  {
    f localf = c();
    if (localf != null)
    {
      if (System.currentTimeMillis() <= e + DexUtil.getUpdateCycleByType(5, 172800000L)) {
        break label35;
      }
      a(localf, null, true, null);
    }
    label35:
    while ((SysParamEntityManager.getIntParam(Constant.getContext(), "AUTO_UPDATE_DATA") != 0) || (!NetUtil.checkAccessNetWork(1))) {
      return;
    }
    b(localf);
  }
  
  public static void a(f paramf)
  {
    try
    {
      new StringBuilder("超过周期，启动下载 name=").append(b).append(" url=").append(d);
      if (!StringUtils.isNull(d))
      {
        cn.com.xy.sms.sdk.util.d.f(d, Constant.getFilePath(), "duoqu_nqsql.zip");
        XyUtil.upZipFile(Constant.getFilePath() + "duoqu_nqsql.zip", Constant.getINITSQL_PATH());
        new StringBuilder("成功下载").append("duoqu_nqsql.zip").append("，成功解压").append("duoqu_nqsql.zip");
        if (cn.com.xy.sms.sdk.util.d.a(Constant.getINITSQL_PATH() + "menu.sql")) {
          b();
        }
        cn.com.xy.sms.sdk.util.d.c(Constant.getFilePath() + "duoqu_nqsql.zip");
        new StringBuilder("updateLastLoadTime").append(b);
        paramf = b;
      }
      try
      {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("last_load_time", System.currentTimeMillis());
        localContentValues.put("status", "1");
        DBManager.update("tb_menu_list", localContentValues, "name = ? ", new String[] { paramf });
        return;
      }
      catch (Throwable paramf)
      {
        paramf.printStackTrace();
        return;
      }
      return;
    }
    catch (Throwable paramf) {}
  }
  
  public static void a(f paramf, XyCallBack paramXyCallBack, boolean paramBoolean, Map<String, String> paramMap)
  {
    try
    {
      paramXyCallBack = new d(paramBoolean, paramMap, paramf, paramXyCallBack);
      String str1 = c;
      String str2 = b;
      str1 = i.a(str1, f, j);
      int i1;
      if (NetUtil.checkAccessNetWork(paramMap))
      {
        NetUtil.executeHttpPublicRequest("", str1, paramXyCallBack, NetUtil.getPubNumServiceUrl() + "updatepublic", paramMap);
        paramXyCallBack = b;
        i1 = j;
      }
      try
      {
        paramf = new ContentValues();
        paramf.put("count", Integer.valueOf(i1 + 1));
        DBManager.update("tb_menu_list", paramf, "name = ? ", new String[] { paramXyCallBack });
        return;
      }
      catch (Throwable paramf)
      {
        paramf.printStackTrace();
        return;
      }
      return;
    }
    catch (Throwable paramf)
    {
      paramf.printStackTrace();
    }
  }
  
  public static void a(String paramString)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("update_time", System.currentTimeMillis());
      DBManager.update("tb_menu_list", localContentValues, "name = ? ", new String[] { paramString });
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  private static void a(String paramString, int paramInt)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("last_load_time", System.currentTimeMillis());
      localContentValues.put("status", "1");
      DBManager.update("tb_menu_list", localContentValues, "name = ? ", new String[] { paramString });
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static void a(String paramString, long paramLong1, long paramLong2)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("update_time", System.currentTimeMillis());
      localContentValues.put("delaystart", paramLong1);
      localContentValues.put("delayend", paramLong2);
      DBManager.update("tb_menu_list", localContentValues, "name = ? ", new String[] { paramString });
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static void a(String paramString1, String paramString2, String paramString3, long paramLong1, int paramInt, long paramLong2, long paramLong3)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("version", paramString2);
      localContentValues.put("url", paramString3);
      localContentValues.put("status", Integer.valueOf(paramInt));
      localContentValues.put("update_time", paramLong1);
      localContentValues.put("delaystart", paramLong2);
      localContentValues.put("delayend", paramLong3);
      DBManager.update("tb_menu_list", localContentValues, "name = ? ", new String[] { paramString1 });
      return;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static void a(Map<String, String> paramMap, XyCallBack paramXyCallBack)
  {
    if (!NetUtil.checkAccessNetWork(paramMap)) {
      XyUtil.doXycallBack(paramXyCallBack, "-1");
    }
    f localf;
    do
    {
      return;
      localf = c();
    } while (localf == null);
    if ((!StringUtils.isNull(d)) && (f == 0))
    {
      XyUtil.doXycallBack(paramXyCallBack, "1");
      return;
    }
    a(localf, paramXyCallBack, false, paramMap);
  }
  
  public static f b(String paramString)
  {
    for (;;)
    {
      try
      {
        paramString = StringUtils.stringConvertXML(paramString, "");
        if (paramString == null) {
          return null;
        }
        f localf = new f();
        String str = "";
        Element localElement = paramString.getDocumentElement();
        NodeList localNodeList = localElement.getElementsByTagName("PublicInfoVersion");
        paramString = str;
        if (localNodeList != null)
        {
          paramString = str;
          if (localNodeList.getLength() > 0)
          {
            str = x.a(localNodeList.item(0));
            paramString = str;
            if (StringUtils.isNull(str)) {
              paramString = "";
            }
          }
        }
        c = paramString;
        str = "";
        localNodeList = localElement.getElementsByTagName("downLoadUrl");
        paramString = str;
        if (localNodeList != null)
        {
          paramString = str;
          if (localNodeList.getLength() > 0)
          {
            str = x.a(localNodeList.item(0));
            paramString = str;
            if (StringUtils.isNull(str)) {
              paramString = "";
            }
          }
        }
        d = paramString;
        paramString = localElement.getElementsByTagName("delaystart");
        if ((paramString != null) && (paramString.getLength() > 0))
        {
          paramString = x.a(paramString.item(0));
          boolean bool = StringUtils.isNull(paramString);
          if (!bool)
          {
            try
            {
              l1 = Long.parseLong(paramString);
              h = l1;
              paramString = localElement.getElementsByTagName("delayend");
              if ((paramString != null) && (paramString.getLength() > 0))
              {
                paramString = x.a(paramString.item(0));
                bool = StringUtils.isNull(paramString);
                if (bool) {}
              }
              long l2;
              l1 = 0L;
            }
            catch (Throwable paramString)
            {
              try
              {
                l1 = Long.parseLong(paramString);
                l2 = l1;
                if (l1 <= 0L) {
                  l2 = 86400000L;
                }
                i = l2;
                return localf;
              }
              catch (Throwable paramString)
              {
                paramString.printStackTrace();
              }
              paramString = paramString;
              paramString.printStackTrace();
            }
            continue;
          }
        }
        long l1 = 0L;
      }
      catch (Throwable paramString)
      {
        paramString.printStackTrace();
        return null;
      }
    }
  }
  
  public static void b()
  {
    try
    {
      d("pubInfo");
      d("pubNum");
      d("pubMenu");
      ParseItemManager.updateNeiQianSql(Constant.getContext());
      return;
    }
    catch (Throwable localThrowable)
    {
      LogManager.e("xiaoyuan", "insertMenuData error： " + localThrowable.getMessage(), localThrowable);
    }
  }
  
  public static void b(f paramf)
  {
    try
    {
      long l1 = System.currentTimeMillis();
      new StringBuilder("检查下载时间menuInfo.delaystart=").append(h).append("menuInfo.delayend=").append(i).append("nowTime=").append(l1).append(" menuInfo.status=").append(f);
      if ((f == 0) && (h <= l1) && (i >= l1)) {
        a(paramf);
      }
      return;
    }
    catch (Throwable paramf)
    {
      paramf.printStackTrace();
    }
  }
  
  private static void b(String paramString, int paramInt)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("count", Integer.valueOf(paramInt + 1));
      DBManager.update("tb_menu_list", localContentValues, "name = ? ", new String[] { paramString });
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  /* Error */
  public static f c()
  {
    // Byte code:
    //   0: ldc 43
    //   2: bipush 10
    //   4: anewarray 140	java/lang/String
    //   7: dup
    //   8: iconst_0
    //   9: ldc 13
    //   11: aastore
    //   12: dup
    //   13: iconst_1
    //   14: ldc 16
    //   16: aastore
    //   17: dup
    //   18: iconst_2
    //   19: ldc 19
    //   21: aastore
    //   22: dup
    //   23: iconst_3
    //   24: ldc 22
    //   26: aastore
    //   27: dup
    //   28: iconst_4
    //   29: ldc 25
    //   31: aastore
    //   32: dup
    //   33: iconst_5
    //   34: ldc 28
    //   36: aastore
    //   37: dup
    //   38: bipush 6
    //   40: ldc 31
    //   42: aastore
    //   43: dup
    //   44: bipush 7
    //   46: ldc 34
    //   48: aastore
    //   49: dup
    //   50: bipush 8
    //   52: ldc 37
    //   54: aastore
    //   55: dup
    //   56: bipush 9
    //   58: ldc 40
    //   60: aastore
    //   61: aconst_null
    //   62: aconst_null
    //   63: invokestatic 338	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   66: astore 11
    //   68: aload 11
    //   70: ifnull +359 -> 429
    //   73: aload 11
    //   75: astore 10
    //   77: aload 11
    //   79: invokevirtual 343	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   82: ifle +347 -> 429
    //   85: aload 11
    //   87: astore 10
    //   89: aload 11
    //   91: ldc 13
    //   93: invokevirtual 347	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   96: istore_0
    //   97: aload 11
    //   99: astore 10
    //   101: aload 11
    //   103: ldc 16
    //   105: invokevirtual 347	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   108: istore_1
    //   109: aload 11
    //   111: astore 10
    //   113: aload 11
    //   115: ldc 19
    //   117: invokevirtual 347	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   120: istore_2
    //   121: aload 11
    //   123: astore 10
    //   125: aload 11
    //   127: ldc 22
    //   129: invokevirtual 347	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   132: istore_3
    //   133: aload 11
    //   135: astore 10
    //   137: aload 11
    //   139: ldc 25
    //   141: invokevirtual 347	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   144: istore 4
    //   146: aload 11
    //   148: astore 10
    //   150: aload 11
    //   152: ldc 28
    //   154: invokevirtual 347	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   157: istore 5
    //   159: aload 11
    //   161: astore 10
    //   163: aload 11
    //   165: ldc 31
    //   167: invokevirtual 347	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   170: istore 6
    //   172: aload 11
    //   174: astore 10
    //   176: aload 11
    //   178: ldc 34
    //   180: invokevirtual 347	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   183: istore 7
    //   185: aload 11
    //   187: astore 10
    //   189: aload 11
    //   191: ldc 37
    //   193: invokevirtual 347	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   196: istore 8
    //   198: aload 11
    //   200: astore 10
    //   202: aload 11
    //   204: ldc 40
    //   206: invokevirtual 347	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   209: istore 9
    //   211: aload 11
    //   213: astore 10
    //   215: aload 11
    //   217: invokevirtual 351	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   220: ifeq +209 -> 429
    //   223: aload 11
    //   225: astore 10
    //   227: new 65	cn/com/xy/sms/sdk/db/entity/f
    //   230: dup
    //   231: invokespecial 249	cn/com/xy/sms/sdk/db/entity/f:<init>	()V
    //   234: astore 12
    //   236: aload 11
    //   238: astore 10
    //   240: aload 11
    //   242: iload_0
    //   243: invokevirtual 355	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   246: pop2
    //   247: aload 11
    //   249: astore 10
    //   251: aload 12
    //   253: aload 11
    //   255: iload_1
    //   256: invokevirtual 359	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   259: putfield 113	cn/com/xy/sms/sdk/db/entity/f:b	Ljava/lang/String;
    //   262: aload 11
    //   264: astore 10
    //   266: aload 12
    //   268: aload 11
    //   270: iload_2
    //   271: invokevirtual 359	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   274: putfield 199	cn/com/xy/sms/sdk/db/entity/f:c	Ljava/lang/String;
    //   277: aload 11
    //   279: astore 10
    //   281: aload 12
    //   283: aload 11
    //   285: iload_3
    //   286: invokevirtual 359	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   289: putfield 121	cn/com/xy/sms/sdk/db/entity/f:d	Ljava/lang/String;
    //   292: aload 11
    //   294: astore 10
    //   296: aload 12
    //   298: aload 11
    //   300: iload 4
    //   302: invokevirtual 363	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   305: putfield 201	cn/com/xy/sms/sdk/db/entity/f:f	I
    //   308: aload 11
    //   310: astore 10
    //   312: aload 11
    //   314: iload 5
    //   316: invokevirtual 355	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   319: pop2
    //   320: aload 11
    //   322: astore 10
    //   324: aload 12
    //   326: aload 11
    //   328: iload 6
    //   330: invokevirtual 355	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   333: putfield 68	cn/com/xy/sms/sdk/db/entity/f:e	J
    //   336: aload 11
    //   338: astore 10
    //   340: aload 12
    //   342: aload 11
    //   344: iload 7
    //   346: invokevirtual 355	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   349: putfield 288	cn/com/xy/sms/sdk/db/entity/f:h	J
    //   352: aload 11
    //   354: astore 10
    //   356: aload 12
    //   358: aload 11
    //   360: iload 8
    //   362: invokevirtual 355	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   365: putfield 292	cn/com/xy/sms/sdk/db/entity/f:i	J
    //   368: aload 11
    //   370: astore 10
    //   372: aload 12
    //   374: aload 11
    //   376: iload 9
    //   378: invokevirtual 363	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   381: putfield 203	cn/com/xy/sms/sdk/db/entity/f:j	I
    //   384: aload 11
    //   386: iconst_1
    //   387: invokestatic 367	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   390: aload 12
    //   392: areturn
    //   393: astore 12
    //   395: aconst_null
    //   396: astore 11
    //   398: aload 11
    //   400: astore 10
    //   402: aload 12
    //   404: invokevirtual 192	java/lang/Throwable:printStackTrace	()V
    //   407: aload 11
    //   409: iconst_1
    //   410: invokestatic 367	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   413: aconst_null
    //   414: areturn
    //   415: astore 11
    //   417: aconst_null
    //   418: astore 10
    //   420: aload 10
    //   422: iconst_1
    //   423: invokestatic 367	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   426: aload 11
    //   428: athrow
    //   429: aload 11
    //   431: iconst_1
    //   432: invokestatic 367	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   435: goto -22 -> 413
    //   438: astore 11
    //   440: goto -20 -> 420
    //   443: astore 12
    //   445: goto -47 -> 398
    // Local variable table:
    //   start	length	slot	name	signature
    //   96	147	0	i1	int
    //   108	148	1	i2	int
    //   120	151	2	i3	int
    //   132	154	3	i4	int
    //   144	157	4	i5	int
    //   157	158	5	i6	int
    //   170	159	6	i7	int
    //   183	162	7	i8	int
    //   196	165	8	i9	int
    //   209	168	9	i10	int
    //   75	346	10	localXyCursor1	XyCursor
    //   66	342	11	localXyCursor2	XyCursor
    //   415	15	11	localXyCursor3	XyCursor
    //   438	1	11	localObject	Object
    //   234	157	12	localf	f
    //   393	10	12	localThrowable1	Throwable
    //   443	1	12	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   0	68	393	java/lang/Throwable
    //   0	68	415	finally
    //   77	85	438	finally
    //   89	97	438	finally
    //   101	109	438	finally
    //   113	121	438	finally
    //   125	133	438	finally
    //   137	146	438	finally
    //   150	159	438	finally
    //   163	172	438	finally
    //   176	185	438	finally
    //   189	198	438	finally
    //   202	211	438	finally
    //   215	223	438	finally
    //   227	236	438	finally
    //   240	247	438	finally
    //   251	262	438	finally
    //   266	277	438	finally
    //   281	292	438	finally
    //   296	308	438	finally
    //   312	320	438	finally
    //   324	336	438	finally
    //   340	352	438	finally
    //   356	368	438	finally
    //   372	384	438	finally
    //   402	407	438	finally
    //   77	85	443	java/lang/Throwable
    //   89	97	443	java/lang/Throwable
    //   101	109	443	java/lang/Throwable
    //   113	121	443	java/lang/Throwable
    //   125	133	443	java/lang/Throwable
    //   137	146	443	java/lang/Throwable
    //   150	159	443	java/lang/Throwable
    //   163	172	443	java/lang/Throwable
    //   176	185	443	java/lang/Throwable
    //   189	198	443	java/lang/Throwable
    //   202	211	443	java/lang/Throwable
    //   215	223	443	java/lang/Throwable
    //   227	236	443	java/lang/Throwable
    //   240	247	443	java/lang/Throwable
    //   251	262	443	java/lang/Throwable
    //   266	277	443	java/lang/Throwable
    //   281	292	443	java/lang/Throwable
    //   296	308	443	java/lang/Throwable
    //   312	320	443	java/lang/Throwable
    //   324	336	443	java/lang/Throwable
    //   340	352	443	java/lang/Throwable
    //   356	368	443	java/lang/Throwable
    //   372	384	443	java/lang/Throwable
  }
  
  /* Error */
  public static void c(String paramString)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aconst_null
    //   4: astore_2
    //   5: aconst_null
    //   6: astore_1
    //   7: ldc 43
    //   9: iconst_2
    //   10: anewarray 140	java/lang/String
    //   13: dup
    //   14: iconst_0
    //   15: ldc 22
    //   17: aastore
    //   18: dup
    //   19: iconst_1
    //   20: ldc 19
    //   22: aastore
    //   23: ldc -73
    //   25: iconst_1
    //   26: anewarray 140	java/lang/String
    //   29: dup
    //   30: iconst_0
    //   31: aload_0
    //   32: aastore
    //   33: invokestatic 338	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   36: astore_3
    //   37: aload_3
    //   38: ifnull +14 -> 52
    //   41: aload_3
    //   42: astore_1
    //   43: aload_3
    //   44: astore_2
    //   45: aload_3
    //   46: invokevirtual 343	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   49: ifgt +53 -> 102
    //   52: aload_3
    //   53: astore_1
    //   54: aload_3
    //   55: astore_2
    //   56: new 172	android/content/ContentValues
    //   59: dup
    //   60: invokespecial 173	android/content/ContentValues:<init>	()V
    //   63: astore 4
    //   65: aload_3
    //   66: astore_1
    //   67: aload_3
    //   68: astore_2
    //   69: aload 4
    //   71: ldc 16
    //   73: aload_0
    //   74: invokevirtual 179	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   77: aload_3
    //   78: astore_1
    //   79: aload_3
    //   80: astore_2
    //   81: aload 4
    //   83: ldc 19
    //   85: ldc -18
    //   87: invokevirtual 179	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   90: aload_3
    //   91: astore_1
    //   92: aload_3
    //   93: astore_2
    //   94: ldc 43
    //   96: aload 4
    //   98: invokestatic 371	cn/com/xy/sms/sdk/db/DBManager:insert	(Ljava/lang/String;Landroid/content/ContentValues;)J
    //   101: pop2
    //   102: aload_3
    //   103: iconst_1
    //   104: invokestatic 367	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   107: ldc 2
    //   109: monitorexit
    //   110: return
    //   111: astore_0
    //   112: aload_1
    //   113: astore_2
    //   114: aload_0
    //   115: invokevirtual 192	java/lang/Throwable:printStackTrace	()V
    //   118: aload_1
    //   119: iconst_1
    //   120: invokestatic 367	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   123: goto -16 -> 107
    //   126: astore_0
    //   127: ldc 2
    //   129: monitorexit
    //   130: aload_0
    //   131: athrow
    //   132: astore_0
    //   133: aload_2
    //   134: iconst_1
    //   135: invokestatic 367	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   138: aload_0
    //   139: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	140	0	paramString	String
    //   6	113	1	localObject1	Object
    //   4	130	2	localObject2	Object
    //   36	67	3	localXyCursor	XyCursor
    //   63	34	4	localContentValues	ContentValues
    // Exception table:
    //   from	to	target	type
    //   7	37	111	java/lang/Throwable
    //   45	52	111	java/lang/Throwable
    //   56	65	111	java/lang/Throwable
    //   69	77	111	java/lang/Throwable
    //   81	90	111	java/lang/Throwable
    //   94	102	111	java/lang/Throwable
    //   102	107	126	finally
    //   118	123	126	finally
    //   133	140	126	finally
    //   7	37	132	finally
    //   45	52	132	finally
    //   56	65	132	finally
    //   69	77	132	finally
    //   81	90	132	finally
    //   94	102	132	finally
    //   114	118	132	finally
  }
  
  public static boolean c(f paramf)
  {
    if (paramf != null) {
      try
      {
        if (!StringUtils.isNull(d))
        {
          int i1 = f;
          if (i1 == 0) {
            return true;
          }
        }
      }
      catch (Throwable paramf) {}
    }
    return false;
  }
  
  private static void d(f paramf)
  {
    if (System.currentTimeMillis() > e + DexUtil.getUpdateCycleByType(5, 172800000L)) {
      a(paramf, null, true, null);
    }
    while ((SysParamEntityManager.getIntParam(Constant.getContext(), "AUTO_UPDATE_DATA") != 0) || (!NetUtil.checkAccessNetWork(1))) {
      return;
    }
    b(paramf);
  }
  
  private static void d(String paramString)
  {
    String str = "";
    if (!StringUtils.isNull(paramString))
    {
      if (!paramString.equalsIgnoreCase("pubInfo")) {
        break label39;
      }
      str = "tb_public_info";
    }
    for (;;)
    {
      if (!StringUtils.isNull(str)) {}
      try
      {
        DBManager.delete(str, null, null);
        return;
      }
      catch (Throwable paramString)
      {
        label39:
        paramString.printStackTrace();
      }
      if (paramString.equalsIgnoreCase("pubNum")) {
        str = "tb_public_num_info";
      } else if (paramString.equalsIgnoreCase("pubMenu")) {
        str = "tb_public_menu_info";
      }
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */