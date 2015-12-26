package cn.com.xy.sms.sdk.db.entity;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.action.AbsSdkDoAction;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.util.DateUtils;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.HashMap;

public final class j
{
  private static String a = "scene_id";
  private static String b = "date";
  private static String c = "parse_times";
  private static String d = "popup_times";
  private static String e = "tb_popup_action_scene";
  private static String f = " DROP TABLE IF EXISTS tb_popup_action_scene";
  private static String g = "create table  if not exists tb_popup_action_scene (scene_id TEXT, date TEXT, parse_times INTEGER DEFAULT '0', popup_times INTEGER DEFAULT '0' ) ";
  
  public static long a(HashMap<String, String> paramHashMap)
  {
    Object localObject = null;
    String str = (String)paramHashMap.get("titleNo");
    long l2 = -1L;
    paramHashMap = (HashMap<String, String>)localObject;
    long l1 = l2;
    try
    {
      if (!StringUtils.isNull(str))
      {
        l1 = l2;
        paramHashMap = d(str);
      }
      if (paramHashMap != null)
      {
        l1 = l2;
        c += 1;
        l1 = l2;
        DBManager.update("tb_popup_action_scene", a(paramHashMap), "scene_id = ? and date = ? ", new String[] { a, b });
        return 0L;
      }
      l1 = l2;
      paramHashMap = new i();
      l1 = l2;
      a = str;
      l1 = l2;
      b = DateUtils.getCurrentTimeString("yyyyMMdd");
      l1 = l2;
      c = 1;
      l1 = l2;
      l2 = DBManager.insert("tb_popup_action_scene", a(paramHashMap));
      l1 = l2;
      DuoquUtils.getSdkDoAction().statisticAction(str, -2, null);
      return l2;
    }
    catch (Throwable paramHashMap)
    {
      paramHashMap.printStackTrace();
    }
    return l1;
  }
  
  private static ContentValues a(i parami)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("scene_id", a);
    localContentValues.put("date", b);
    localContentValues.put("parse_times", Integer.valueOf(c));
    localContentValues.put("popup_times", Integer.valueOf(d));
    return localContentValues;
  }
  
  /* Error */
  public static java.util.List<i> a(String paramString)
  {
    // Byte code:
    //   0: new 126	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 127	java/util/ArrayList:<init>	()V
    //   7: astore 5
    //   9: ldc 20
    //   11: iconst_1
    //   12: anewarray 44	java/lang/String
    //   15: dup
    //   16: iconst_0
    //   17: ldc 11
    //   19: aastore
    //   20: ldc -127
    //   22: iconst_1
    //   23: anewarray 44	java/lang/String
    //   26: dup
    //   27: iconst_0
    //   28: aload_0
    //   29: aastore
    //   30: ldc 11
    //   32: aconst_null
    //   33: aconst_null
    //   34: aconst_null
    //   35: invokestatic 133	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   38: astore_3
    //   39: aload_3
    //   40: ifnull +105 -> 145
    //   43: aload_3
    //   44: astore_0
    //   45: aload_3
    //   46: invokevirtual 139	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   49: ifle +96 -> 145
    //   52: aload_3
    //   53: astore_0
    //   54: aload_3
    //   55: ldc 11
    //   57: invokevirtual 143	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   60: istore_1
    //   61: aload_3
    //   62: astore_0
    //   63: aload_3
    //   64: invokevirtual 147	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   67: istore_2
    //   68: iload_2
    //   69: ifne +11 -> 80
    //   72: aload_3
    //   73: iconst_1
    //   74: invokestatic 151	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   77: aload 5
    //   79: areturn
    //   80: aload_3
    //   81: astore_0
    //   82: new 57	cn/com/xy/sms/sdk/db/entity/i
    //   85: dup
    //   86: invokespecial 76	cn/com/xy/sms/sdk/db/entity/i:<init>	()V
    //   89: astore 4
    //   91: aload_3
    //   92: astore_0
    //   93: aload 4
    //   95: aload_3
    //   96: iload_1
    //   97: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   100: putfield 69	cn/com/xy/sms/sdk/db/entity/i:b	Ljava/lang/String;
    //   103: aload_3
    //   104: astore_0
    //   105: aload 5
    //   107: aload 4
    //   109: invokeinterface 161 2 0
    //   114: pop
    //   115: goto -54 -> 61
    //   118: astore 4
    //   120: aload_3
    //   121: astore_0
    //   122: aload 4
    //   124: invokevirtual 103	java/lang/Throwable:printStackTrace	()V
    //   127: aload_3
    //   128: iconst_1
    //   129: invokestatic 151	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   132: aload 5
    //   134: areturn
    //   135: astore_3
    //   136: aconst_null
    //   137: astore_0
    //   138: aload_0
    //   139: iconst_1
    //   140: invokestatic 151	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   143: aload_3
    //   144: athrow
    //   145: aload_3
    //   146: iconst_1
    //   147: invokestatic 151	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   150: goto -18 -> 132
    //   153: astore_3
    //   154: goto -16 -> 138
    //   157: astore 4
    //   159: aconst_null
    //   160: astore_3
    //   161: goto -41 -> 120
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	164	0	paramString	String
    //   60	37	1	i	int
    //   67	2	2	bool	boolean
    //   38	90	3	localXyCursor1	cn.com.xy.sms.sdk.db.XyCursor
    //   135	11	3	localXyCursor2	cn.com.xy.sms.sdk.db.XyCursor
    //   153	1	3	localObject1	Object
    //   160	1	3	localObject2	Object
    //   89	19	4	locali	i
    //   118	5	4	localThrowable1	Throwable
    //   157	1	4	localThrowable2	Throwable
    //   7	126	5	localArrayList	java.util.ArrayList
    // Exception table:
    //   from	to	target	type
    //   45	52	118	java/lang/Throwable
    //   54	61	118	java/lang/Throwable
    //   63	68	118	java/lang/Throwable
    //   82	91	118	java/lang/Throwable
    //   93	103	118	java/lang/Throwable
    //   105	115	118	java/lang/Throwable
    //   9	39	135	finally
    //   45	52	153	finally
    //   54	61	153	finally
    //   63	68	153	finally
    //   82	91	153	finally
    //   93	103	153	finally
    //   105	115	153	finally
    //   122	127	153	finally
    //   9	39	157	java/lang/Throwable
  }
  
  private static void a()
  {
    try
    {
      DBManager.delete("tb_popup_action_scene", null, null);
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  public static long b(HashMap<String, String> paramHashMap)
  {
    String str = (String)paramHashMap.get("titleNo");
    paramHashMap = null;
    try
    {
      if (!StringUtils.isNull(str)) {
        paramHashMap = d(str);
      }
      if (paramHashMap != null)
      {
        d += 1;
        DBManager.update("tb_popup_action_scene", a(paramHashMap), "scene_id = ? and date = ? ", new String[] { a, b });
        return 0L;
      }
      paramHashMap = new i();
      a = str;
      b = DateUtils.getCurrentTimeString("yyyyMMdd");
      d = 1;
      long l = DBManager.insert("tb_popup_action_scene", a(paramHashMap));
      return l;
    }
    catch (Throwable paramHashMap)
    {
      paramHashMap.printStackTrace();
    }
    return -1L;
  }
  
  /* Error */
  public static java.util.List<i> b(String paramString)
  {
    // Byte code:
    //   0: new 126	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 127	java/util/ArrayList:<init>	()V
    //   7: astore 8
    //   9: ldc 20
    //   11: iconst_4
    //   12: anewarray 44	java/lang/String
    //   15: dup
    //   16: iconst_0
    //   17: ldc 8
    //   19: aastore
    //   20: dup
    //   21: iconst_1
    //   22: ldc 11
    //   24: aastore
    //   25: dup
    //   26: iconst_2
    //   27: ldc 14
    //   29: aastore
    //   30: dup
    //   31: iconst_3
    //   32: ldc 17
    //   34: aastore
    //   35: ldc -88
    //   37: iconst_1
    //   38: anewarray 44	java/lang/String
    //   41: dup
    //   42: iconst_0
    //   43: aload_0
    //   44: aastore
    //   45: aconst_null
    //   46: aconst_null
    //   47: aconst_null
    //   48: aconst_null
    //   49: invokestatic 133	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   52: astore 6
    //   54: aload 6
    //   56: ifnull +74 -> 130
    //   59: aload 6
    //   61: astore_0
    //   62: aload 6
    //   64: invokevirtual 139	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   67: ifle +63 -> 130
    //   70: aload 6
    //   72: astore_0
    //   73: aload 6
    //   75: ldc 8
    //   77: invokevirtual 143	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   80: istore_1
    //   81: aload 6
    //   83: astore_0
    //   84: aload 6
    //   86: ldc 11
    //   88: invokevirtual 143	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   91: istore_2
    //   92: aload 6
    //   94: astore_0
    //   95: aload 6
    //   97: ldc 14
    //   99: invokevirtual 143	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   102: istore_3
    //   103: aload 6
    //   105: astore_0
    //   106: aload 6
    //   108: ldc 17
    //   110: invokevirtual 143	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   113: istore 4
    //   115: aload 6
    //   117: astore_0
    //   118: aload 6
    //   120: invokevirtual 147	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   123: istore 5
    //   125: iload 5
    //   127: ifne +12 -> 139
    //   130: aload 6
    //   132: iconst_1
    //   133: invokestatic 151	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   136: aload 8
    //   138: areturn
    //   139: aload 6
    //   141: astore_0
    //   142: new 57	cn/com/xy/sms/sdk/db/entity/i
    //   145: dup
    //   146: invokespecial 76	cn/com/xy/sms/sdk/db/entity/i:<init>	()V
    //   149: astore 7
    //   151: aload 6
    //   153: astore_0
    //   154: aload 7
    //   156: aload 6
    //   158: iload_1
    //   159: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   162: putfield 67	cn/com/xy/sms/sdk/db/entity/i:a	Ljava/lang/String;
    //   165: aload 6
    //   167: astore_0
    //   168: aload 7
    //   170: aload 6
    //   172: iload_2
    //   173: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   176: putfield 69	cn/com/xy/sms/sdk/db/entity/i:b	Ljava/lang/String;
    //   179: aload 6
    //   181: astore_0
    //   182: aload 7
    //   184: aload 6
    //   186: iload_3
    //   187: invokevirtual 172	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   190: putfield 60	cn/com/xy/sms/sdk/db/entity/i:c	I
    //   193: aload 6
    //   195: astore_0
    //   196: aload 7
    //   198: aload 6
    //   200: iload 4
    //   202: invokevirtual 172	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   205: putfield 123	cn/com/xy/sms/sdk/db/entity/i:d	I
    //   208: aload 6
    //   210: astore_0
    //   211: aload 8
    //   213: aload 7
    //   215: invokeinterface 161 2 0
    //   220: pop
    //   221: goto -106 -> 115
    //   224: astore 7
    //   226: aload 6
    //   228: astore_0
    //   229: aload 7
    //   231: invokevirtual 103	java/lang/Throwable:printStackTrace	()V
    //   234: aload 6
    //   236: iconst_1
    //   237: invokestatic 151	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   240: aload 8
    //   242: areturn
    //   243: astore 6
    //   245: aconst_null
    //   246: astore_0
    //   247: aload_0
    //   248: iconst_1
    //   249: invokestatic 151	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   252: aload 6
    //   254: athrow
    //   255: astore 6
    //   257: goto -10 -> 247
    //   260: astore 7
    //   262: aconst_null
    //   263: astore 6
    //   265: goto -39 -> 226
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	268	0	paramString	String
    //   80	79	1	i	int
    //   91	82	2	j	int
    //   102	85	3	k	int
    //   113	88	4	m	int
    //   123	3	5	bool	boolean
    //   52	183	6	localXyCursor	cn.com.xy.sms.sdk.db.XyCursor
    //   243	10	6	localObject1	Object
    //   255	1	6	localObject2	Object
    //   263	1	6	localObject3	Object
    //   149	65	7	locali	i
    //   224	6	7	localThrowable1	Throwable
    //   260	1	7	localThrowable2	Throwable
    //   7	234	8	localArrayList	java.util.ArrayList
    // Exception table:
    //   from	to	target	type
    //   62	70	224	java/lang/Throwable
    //   73	81	224	java/lang/Throwable
    //   84	92	224	java/lang/Throwable
    //   95	103	224	java/lang/Throwable
    //   106	115	224	java/lang/Throwable
    //   118	125	224	java/lang/Throwable
    //   142	151	224	java/lang/Throwable
    //   154	165	224	java/lang/Throwable
    //   168	179	224	java/lang/Throwable
    //   182	193	224	java/lang/Throwable
    //   196	208	224	java/lang/Throwable
    //   211	221	224	java/lang/Throwable
    //   9	54	243	finally
    //   62	70	255	finally
    //   73	81	255	finally
    //   84	92	255	finally
    //   95	103	255	finally
    //   106	115	255	finally
    //   118	125	255	finally
    //   142	151	255	finally
    //   154	165	255	finally
    //   168	179	255	finally
    //   182	193	255	finally
    //   196	208	255	finally
    //   211	221	255	finally
    //   229	234	255	finally
    //   9	54	260	java/lang/Throwable
  }
  
  public static void c(String paramString)
  {
    try
    {
      DBManager.delete("tb_popup_action_scene", "date < ?", new String[] { paramString });
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  /* Error */
  private static i d(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: ldc 78
    //   5: invokestatic 84	cn/com/xy/sms/sdk/util/DateUtils:getCurrentTimeString	(Ljava/lang/String;)Ljava/lang/String;
    //   8: astore 7
    //   10: ldc 20
    //   12: iconst_4
    //   13: anewarray 44	java/lang/String
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
    //   31: dup
    //   32: iconst_3
    //   33: ldc 17
    //   35: aastore
    //   36: ldc 65
    //   38: iconst_2
    //   39: anewarray 44	java/lang/String
    //   42: dup
    //   43: iconst_0
    //   44: aload_0
    //   45: aastore
    //   46: dup
    //   47: iconst_1
    //   48: aload 7
    //   50: aastore
    //   51: aconst_null
    //   52: aconst_null
    //   53: aconst_null
    //   54: ldc -79
    //   56: invokestatic 133	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   59: astore_0
    //   60: aload_0
    //   61: ifnull +155 -> 216
    //   64: aload_0
    //   65: invokevirtual 139	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   68: ifle +148 -> 216
    //   71: aload_0
    //   72: ldc 8
    //   74: invokevirtual 143	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   77: istore_1
    //   78: aload_0
    //   79: ldc 11
    //   81: invokevirtual 143	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   84: istore_2
    //   85: aload_0
    //   86: ldc 14
    //   88: invokevirtual 143	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   91: istore_3
    //   92: aload_0
    //   93: ldc 17
    //   95: invokevirtual 143	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   98: istore 4
    //   100: aconst_null
    //   101: astore 6
    //   103: aload_0
    //   104: invokevirtual 147	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   107: istore 5
    //   109: iload 5
    //   111: ifne +11 -> 122
    //   114: aload_0
    //   115: iconst_1
    //   116: invokestatic 151	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   119: aload 6
    //   121: areturn
    //   122: new 57	cn/com/xy/sms/sdk/db/entity/i
    //   125: dup
    //   126: invokespecial 76	cn/com/xy/sms/sdk/db/entity/i:<init>	()V
    //   129: astore 8
    //   131: aload 8
    //   133: aload_0
    //   134: iload_1
    //   135: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   138: putfield 67	cn/com/xy/sms/sdk/db/entity/i:a	Ljava/lang/String;
    //   141: aload 8
    //   143: aload_0
    //   144: iload_2
    //   145: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   148: putfield 69	cn/com/xy/sms/sdk/db/entity/i:b	Ljava/lang/String;
    //   151: aload 8
    //   153: aload_0
    //   154: iload_3
    //   155: invokevirtual 172	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   158: putfield 60	cn/com/xy/sms/sdk/db/entity/i:c	I
    //   161: aload 8
    //   163: aload_0
    //   164: iload 4
    //   166: invokevirtual 172	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   169: putfield 123	cn/com/xy/sms/sdk/db/entity/i:d	I
    //   172: aload 8
    //   174: astore 6
    //   176: goto -73 -> 103
    //   179: astore 7
    //   181: aconst_null
    //   182: astore 8
    //   184: aload 6
    //   186: astore_0
    //   187: aload 8
    //   189: astore 6
    //   191: aload 7
    //   193: invokevirtual 103	java/lang/Throwable:printStackTrace	()V
    //   196: aload_0
    //   197: iconst_1
    //   198: invokestatic 151	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   201: aload 6
    //   203: areturn
    //   204: astore 6
    //   206: aconst_null
    //   207: astore_0
    //   208: aload_0
    //   209: iconst_1
    //   210: invokestatic 151	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   213: aload 6
    //   215: athrow
    //   216: aload_0
    //   217: iconst_1
    //   218: invokestatic 151	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   221: aconst_null
    //   222: areturn
    //   223: astore 6
    //   225: goto -17 -> 208
    //   228: astore 6
    //   230: goto -22 -> 208
    //   233: astore 7
    //   235: aconst_null
    //   236: astore 6
    //   238: goto -47 -> 191
    //   241: astore 7
    //   243: aload 8
    //   245: astore 6
    //   247: goto -56 -> 191
    //   250: astore 7
    //   252: goto -61 -> 191
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	255	0	paramString	String
    //   77	58	1	i	int
    //   84	61	2	j	int
    //   91	64	3	k	int
    //   98	67	4	m	int
    //   107	3	5	bool	boolean
    //   1	201	6	localObject1	Object
    //   204	10	6	localObject2	Object
    //   223	1	6	localObject3	Object
    //   228	1	6	localObject4	Object
    //   236	10	6	localObject5	Object
    //   8	41	7	str	String
    //   179	13	7	localThrowable1	Throwable
    //   233	1	7	localThrowable2	Throwable
    //   241	1	7	localThrowable3	Throwable
    //   250	1	7	localThrowable4	Throwable
    //   129	115	8	locali	i
    // Exception table:
    //   from	to	target	type
    //   10	60	179	java/lang/Throwable
    //   10	60	204	finally
    //   64	100	223	finally
    //   103	109	223	finally
    //   122	131	223	finally
    //   131	172	223	finally
    //   191	196	228	finally
    //   64	100	233	java/lang/Throwable
    //   131	172	241	java/lang/Throwable
    //   103	109	250	java/lang/Throwable
    //   122	131	250	java/lang/Throwable
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.j
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */