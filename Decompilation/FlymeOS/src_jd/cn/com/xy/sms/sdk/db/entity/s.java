package cn.com.xy.sms.sdk.db.entity;

import android.content.ContentValues;
import bs;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.db.entity.a.c;
import cn.com.xy.sms.sdk.db.entity.a.e;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class s
{
  private static String a = "id";
  private static String b = "content";
  private static String c = "t_group";
  private static String d = "tb_update_task";
  private static String e = "t_version";
  private static String f = " DROP TABLE IF EXISTS tb_update_task";
  private static String g = "create table  if not exists tb_update_task ( id INTEGER PRIMARY KEY,content TEXT,t_group TEXT,t_version long )";
  private static String h = "1";
  private static String i = "2";
  private static final int j = 20;
  
  /* Error */
  private static List<r> a(String paramString, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 52	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   4: ifeq +5 -> 9
    //   7: aconst_null
    //   8: areturn
    //   9: iconst_0
    //   10: ldc 17
    //   12: iconst_3
    //   13: anewarray 54	java/lang/String
    //   16: dup
    //   17: iconst_0
    //   18: ldc 8
    //   20: aastore
    //   21: dup
    //   22: iconst_1
    //   23: ldc 11
    //   25: aastore
    //   26: dup
    //   27: iconst_2
    //   28: ldc 14
    //   30: aastore
    //   31: ldc 56
    //   33: iconst_1
    //   34: anewarray 54	java/lang/String
    //   37: dup
    //   38: iconst_0
    //   39: aload_0
    //   40: aastore
    //   41: aconst_null
    //   42: aconst_null
    //   43: aconst_null
    //   44: ldc 58
    //   46: invokestatic 64	cn/com/xy/sms/sdk/db/DBManager:query	(ZLjava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   49: astore_3
    //   50: aload_3
    //   51: ifnull +14 -> 65
    //   54: aload_3
    //   55: astore_0
    //   56: aload_3
    //   57: invokevirtual 70	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   60: istore_1
    //   61: iload_1
    //   62: ifgt +10 -> 72
    //   65: aload_3
    //   66: iconst_1
    //   67: invokestatic 74	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   70: aconst_null
    //   71: areturn
    //   72: aload_3
    //   73: astore_0
    //   74: new 76	java/util/ArrayList
    //   77: dup
    //   78: invokespecial 77	java/util/ArrayList:<init>	()V
    //   81: astore 4
    //   83: aload_3
    //   84: astore_0
    //   85: aload_3
    //   86: invokevirtual 81	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   89: istore_2
    //   90: iload_2
    //   91: ifne +11 -> 102
    //   94: aload_3
    //   95: iconst_1
    //   96: invokestatic 74	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   99: aload 4
    //   101: areturn
    //   102: aload_3
    //   103: astore_0
    //   104: new 83	cn/com/xy/sms/sdk/db/entity/r
    //   107: dup
    //   108: invokespecial 84	cn/com/xy/sms/sdk/db/entity/r:<init>	()V
    //   111: astore 5
    //   113: aload_3
    //   114: astore_0
    //   115: aload 5
    //   117: aload_3
    //   118: aload_3
    //   119: ldc 8
    //   121: invokevirtual 88	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   124: invokevirtual 92	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   127: putfield 95	cn/com/xy/sms/sdk/db/entity/r:b	J
    //   130: aload_3
    //   131: astore_0
    //   132: aload 5
    //   134: aload_3
    //   135: aload_3
    //   136: ldc 11
    //   138: invokevirtual 88	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   141: invokevirtual 99	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   144: putfield 101	cn/com/xy/sms/sdk/db/entity/r:a	Ljava/lang/String;
    //   147: aload_3
    //   148: astore_0
    //   149: aload 5
    //   151: aload_3
    //   152: aload_3
    //   153: ldc 14
    //   155: invokevirtual 88	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   158: invokevirtual 99	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   161: putfield 103	cn/com/xy/sms/sdk/db/entity/r:c	Ljava/lang/String;
    //   164: aload_3
    //   165: astore_0
    //   166: aload 4
    //   168: aload 5
    //   170: invokeinterface 109 2 0
    //   175: pop
    //   176: goto -93 -> 83
    //   179: astore 4
    //   181: aload_3
    //   182: astore_0
    //   183: aload 4
    //   185: invokevirtual 112	java/lang/Throwable:printStackTrace	()V
    //   188: aload_3
    //   189: iconst_1
    //   190: invokestatic 74	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   193: aconst_null
    //   194: areturn
    //   195: astore_3
    //   196: aconst_null
    //   197: astore_0
    //   198: aload_0
    //   199: iconst_1
    //   200: invokestatic 74	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   203: aload_3
    //   204: athrow
    //   205: astore_3
    //   206: goto -8 -> 198
    //   209: astore 4
    //   211: aconst_null
    //   212: astore_3
    //   213: goto -32 -> 181
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	216	0	paramString	String
    //   0	216	1	paramInt	int
    //   89	2	2	bool	boolean
    //   49	140	3	localXyCursor	cn.com.xy.sms.sdk.db.XyCursor
    //   195	9	3	localObject1	Object
    //   205	1	3	localObject2	Object
    //   212	1	3	localObject3	Object
    //   81	86	4	localArrayList	ArrayList
    //   179	5	4	localThrowable1	Throwable
    //   209	1	4	localThrowable2	Throwable
    //   111	58	5	localr	r
    // Exception table:
    //   from	to	target	type
    //   56	61	179	java/lang/Throwable
    //   74	83	179	java/lang/Throwable
    //   85	90	179	java/lang/Throwable
    //   104	113	179	java/lang/Throwable
    //   115	130	179	java/lang/Throwable
    //   132	147	179	java/lang/Throwable
    //   149	164	179	java/lang/Throwable
    //   166	176	179	java/lang/Throwable
    //   9	50	195	finally
    //   56	61	205	finally
    //   74	83	205	finally
    //   85	90	205	finally
    //   104	113	205	finally
    //   115	130	205	finally
    //   132	147	205	finally
    //   149	164	205	finally
    //   166	176	205	finally
    //   183	188	205	finally
    //   9	50	209	java/lang/Throwable
  }
  
  public static void a(long paramLong)
  {
    try
    {
      DBManager.delete("tb_update_task", "id = ?", new String[] { String.valueOf(paramLong) });
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  public static void a(r paramr)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("content", a);
      localContentValues.put("t_group", c);
      localContentValues.put("t_version", Long.valueOf(0L));
      DBManager.insert("tb_update_task", localContentValues);
      return;
    }
    catch (Throwable paramr)
    {
      paramr.printStackTrace();
    }
  }
  
  public static void a(String paramString)
  {
    try
    {
      DBManager.delete("tb_update_task", "id IN (" + paramString + ")", null);
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static void a(String paramString1, String paramString2, int paramInt)
  {
    try
    {
      DBManager.insert("tb_update_task", BaseManager.getContentValues(null, new String[] { "content", paramString1, "t_group", paramString2, "t_version", "0" }));
      return;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  private static void b(r paramr)
  {
    if (paramr == null) {
      return;
    }
    IccidInfo localIccidInfo = IccidInfoManager.queryDeftIccidInfo(Constant.getContext());
    ArrayList localArrayList = e.a(a);
    t localt = new t(paramr);
    paramr = "";
    String str = "";
    if (localIccidInfo != null)
    {
      paramr = areaCode;
      str = iccid;
    }
    bs.a(localArrayList, paramr, str, "3", localt, true);
  }
  
  public static void b(String paramString)
  {
    for (;;)
    {
      try
      {
        boolean bool = StringUtils.isNull(paramString);
        if (bool) {
          return;
        }
        try
        {
          if (!"1".equals(paramString)) {
            break label113;
          }
          paramString = c(paramString);
          if (paramString == null) {
            continue;
          }
          IccidInfo localIccidInfo = IccidInfoManager.queryDeftIccidInfo(Constant.getContext());
          ArrayList localArrayList = e.a(a);
          t localt = new t(paramString);
          paramString = "";
          localObject = "";
          if (localIccidInfo != null)
          {
            paramString = areaCode;
            localObject = iccid;
          }
          bs.a(localArrayList, paramString, (String)localObject, "3", localt, true);
        }
        catch (Throwable paramString)
        {
          paramString.printStackTrace();
        }
        continue;
        if (!"2".equals(paramString)) {
          continue;
        }
      }
      finally {}
      label113:
      Object localObject = a(paramString, 20);
      if (localObject != null)
      {
        int k = ((List)localObject).size();
        if (k != 0) {
          try
          {
            if ((SysParamEntityManager.getBooleanParam(Constant.getContext(), "smartsms_enhance", true)) && (XyUtil.checkNetWork(Constant.getContext(), 2) != -1))
            {
              paramString = new u((List)localObject);
              localObject = d((List)localObject);
              if (!StringUtils.isNull((String)localObject)) {
                NetUtil.executePubNumServiceHttpRequest((String)localObject, "990005", paramString, null, false, false, "pubinfo", true);
              }
            }
          }
          catch (Throwable paramString)
          {
            paramString.printStackTrace();
          }
        }
      }
    }
  }
  
  private static void b(List<r> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0)) {}
    for (;;)
    {
      return;
      try
      {
        if ((SysParamEntityManager.getBooleanParam(Constant.getContext(), "smartsms_enhance", true)) && (XyUtil.checkNetWork(Constant.getContext(), 2) != -1))
        {
          u localu = new u(paramList);
          paramList = d(paramList);
          if (!StringUtils.isNull(paramList))
          {
            NetUtil.executePubNumServiceHttpRequest(paramList, "990005", localu, null, false, false, "pubinfo", true);
            return;
          }
        }
      }
      catch (Throwable paramList)
      {
        paramList.printStackTrace();
      }
    }
  }
  
  /* Error */
  private static r c(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: invokestatic 52	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   6: ifeq +5 -> 11
    //   9: aconst_null
    //   10: areturn
    //   11: ldc 56
    //   13: astore_2
    //   14: ldc 29
    //   16: aload_0
    //   17: invokevirtual 212	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   20: ifeq +25 -> 45
    //   23: new 148	java/lang/StringBuilder
    //   26: dup
    //   27: ldc 56
    //   29: invokestatic 255	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   32: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   35: ldc_w 257
    //   38: invokevirtual 156	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: invokevirtual 162	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   44: astore_2
    //   45: iconst_0
    //   46: ldc 17
    //   48: iconst_3
    //   49: anewarray 54	java/lang/String
    //   52: dup
    //   53: iconst_0
    //   54: ldc 8
    //   56: aastore
    //   57: dup
    //   58: iconst_1
    //   59: ldc 11
    //   61: aastore
    //   62: dup
    //   63: iconst_2
    //   64: ldc 14
    //   66: aastore
    //   67: aload_2
    //   68: iconst_1
    //   69: anewarray 54	java/lang/String
    //   72: dup
    //   73: iconst_0
    //   74: aload_0
    //   75: aastore
    //   76: aconst_null
    //   77: aconst_null
    //   78: aconst_null
    //   79: ldc 29
    //   81: invokestatic 64	cn/com/xy/sms/sdk/db/DBManager:query	(ZLjava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   84: astore_2
    //   85: aload_2
    //   86: ifnull +14 -> 100
    //   89: aload_2
    //   90: astore_0
    //   91: aload_2
    //   92: invokevirtual 70	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   95: istore_1
    //   96: iload_1
    //   97: ifgt +10 -> 107
    //   100: aload_2
    //   101: iconst_1
    //   102: invokestatic 74	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   105: aconst_null
    //   106: areturn
    //   107: aload_2
    //   108: astore_0
    //   109: aload_2
    //   110: invokevirtual 81	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   113: ifeq +94 -> 207
    //   116: aload_2
    //   117: astore_0
    //   118: new 83	cn/com/xy/sms/sdk/db/entity/r
    //   121: dup
    //   122: invokespecial 84	cn/com/xy/sms/sdk/db/entity/r:<init>	()V
    //   125: astore_3
    //   126: aload_2
    //   127: astore_0
    //   128: aload_3
    //   129: aload_2
    //   130: aload_2
    //   131: ldc 8
    //   133: invokevirtual 88	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   136: invokevirtual 92	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   139: putfield 95	cn/com/xy/sms/sdk/db/entity/r:b	J
    //   142: aload_2
    //   143: astore_0
    //   144: aload_3
    //   145: aload_2
    //   146: aload_2
    //   147: ldc 11
    //   149: invokevirtual 88	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   152: invokevirtual 99	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   155: putfield 101	cn/com/xy/sms/sdk/db/entity/r:a	Ljava/lang/String;
    //   158: aload_2
    //   159: astore_0
    //   160: aload_3
    //   161: aload_2
    //   162: aload_2
    //   163: ldc 14
    //   165: invokevirtual 88	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   168: invokevirtual 99	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   171: putfield 103	cn/com/xy/sms/sdk/db/entity/r:c	Ljava/lang/String;
    //   174: aload_2
    //   175: iconst_1
    //   176: invokestatic 74	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   179: aload_3
    //   180: areturn
    //   181: astore_3
    //   182: aconst_null
    //   183: astore_2
    //   184: aload_2
    //   185: astore_0
    //   186: aload_3
    //   187: invokevirtual 112	java/lang/Throwable:printStackTrace	()V
    //   190: aload_2
    //   191: iconst_1
    //   192: invokestatic 74	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   195: aconst_null
    //   196: areturn
    //   197: astore_0
    //   198: aload_3
    //   199: astore_2
    //   200: aload_2
    //   201: iconst_1
    //   202: invokestatic 74	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   205: aload_0
    //   206: athrow
    //   207: aload_2
    //   208: iconst_1
    //   209: invokestatic 74	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   212: goto -17 -> 195
    //   215: astore_3
    //   216: aload_0
    //   217: astore_2
    //   218: aload_3
    //   219: astore_0
    //   220: goto -20 -> 200
    //   223: astore_3
    //   224: goto -40 -> 184
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	227	0	paramString	String
    //   95	2	1	k	int
    //   13	205	2	localObject1	Object
    //   1	179	3	localr	r
    //   181	18	3	localThrowable1	Throwable
    //   215	4	3	localObject2	Object
    //   223	1	3	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   14	45	181	java/lang/Throwable
    //   45	85	181	java/lang/Throwable
    //   14	45	197	finally
    //   45	85	197	finally
    //   91	96	215	finally
    //   109	116	215	finally
    //   118	126	215	finally
    //   128	142	215	finally
    //   144	158	215	finally
    //   160	174	215	finally
    //   186	190	215	finally
    //   91	96	223	java/lang/Throwable
    //   109	116	223	java/lang/Throwable
    //   118	126	223	java/lang/Throwable
    //   128	142	223	java/lang/Throwable
    //   144	158	223	java/lang/Throwable
    //   160	174	223	java/lang/Throwable
  }
  
  private static String c(List<r> paramList)
  {
    if (paramList != null) {
      try
      {
        if (paramList.size() != 0)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          paramList = paramList.iterator();
          for (;;)
          {
            if (!paramList.hasNext())
            {
              if (localStringBuilder.length() <= 0) {
                break;
              }
              localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
              return localStringBuilder.toString();
            }
            localStringBuilder.append(nextb);
            localStringBuilder.append(",");
          }
          return null;
        }
      }
      catch (Throwable paramList)
      {
        paramList.printStackTrace();
      }
    }
    return null;
  }
  
  private static String d(List<r> paramList)
  {
    if (paramList != null) {}
    for (;;)
    {
      int k;
      try
      {
        if (paramList.size() != 0)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          paramList = paramList.iterator();
          if (!paramList.hasNext())
          {
            if (localStringBuilder.length() == 0) {
              return null;
            }
          }
          else
          {
            Object localObject = (r)paramList.next();
            if ((localObject == null) || (a == null)) {
              continue;
            }
            localObject = a.split(";");
            int m = localObject.length;
            k = 0;
            if (k >= m) {
              continue;
            }
            String str1 = localObject[k];
            String str2 = c.a(str1, false);
            if (StringUtils.isNull(str2)) {
              break label231;
            }
            localStringBuilder.append("<num sign =\"" + str2 + "\">");
            localStringBuilder.append(str1);
            localStringBuilder.append("</num>");
            break label231;
          }
          paramList = new StringBuilder("<?xml version='1.0' encoding='utf-8'?>");
          paramList.append("<QueryPubInfoRequest>");
          paramList.append("<allNums type=\"1\" >");
          paramList.append(localStringBuilder);
          paramList.append("</allNums>");
          paramList.append("</QueryPubInfoRequest>");
          paramList = paramList.toString();
          return paramList;
        }
      }
      catch (Throwable paramList)
      {
        paramList.printStackTrace();
        return null;
      }
      return null;
      label231:
      k += 1;
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.s
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */