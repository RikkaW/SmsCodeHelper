package cn.com.xy.sms.sdk.db.entity;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.util.SdkParamUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class e
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
  private static String m = "tb_jar_list";
  private static String n = "is_use";
  private static int o = 1;
  private static int p = 0;
  private static String q = " DROP TABLE IF EXISTS tb_jar_list";
  private static String r = "create table  if not exists tb_jar_list (id INTEGER PRIMARY KEY,name TEXT,version TEXT,url TEXT,status INTEGER DEFAULT '0',update_time INTEGER DEFAULT '0',delaystart INTEGER DEFAULT '0',delayend INTEGER DEFAULT '0',count INTEGER DEFAULT '0',last_load_time INTEGER DEFAULT '0' ,is_use INTEGER DEFAULT '0')";
  private static String s = "ALTER TABLE tb_jar_list ADD COLUMN is_use INTEGER DEFAULT '0'";
  
  public static int a(List<String> paramList)
  {
    try
    {
      if (paramList.size() == 0) {
        return 0;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      paramList = paramList.iterator();
      for (;;)
      {
        if (!paramList.hasNext())
        {
          if (localStringBuilder.length() <= 0) {
            break;
          }
          localStringBuilder.setLength(localStringBuilder.length() - 1);
          return DBManager.delete("tb_jar_list", "name IN (" + localStringBuilder + ")", null);
        }
        String str = (String)paramList.next();
        if (!StringUtils.isNull(str))
        {
          localStringBuilder.append("'");
          localStringBuilder.append(str.trim());
          localStringBuilder.append("',");
        }
      }
      return -1;
    }
    catch (Throwable paramList)
    {
      paramList.printStackTrace();
    }
  }
  
  /* Error */
  public static d a(String paramString)
  {
    // Byte code:
    //   0: ldc 43
    //   2: bipush 10
    //   4: anewarray 121	java/lang/String
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
    //   61: ldc -114
    //   63: iconst_1
    //   64: anewarray 121	java/lang/String
    //   67: dup
    //   68: iconst_0
    //   69: aload_0
    //   70: aastore
    //   71: invokestatic 146	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   74: astore 11
    //   76: aload 11
    //   78: ifnull +331 -> 409
    //   81: aload 11
    //   83: astore_0
    //   84: aload 11
    //   86: invokevirtual 151	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   89: ifle +320 -> 409
    //   92: aload 11
    //   94: astore_0
    //   95: aload 11
    //   97: ldc 13
    //   99: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   102: istore_1
    //   103: aload 11
    //   105: astore_0
    //   106: aload 11
    //   108: ldc 16
    //   110: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   113: istore_2
    //   114: aload 11
    //   116: astore_0
    //   117: aload 11
    //   119: ldc 19
    //   121: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   124: istore_3
    //   125: aload 11
    //   127: astore_0
    //   128: aload 11
    //   130: ldc 22
    //   132: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   135: istore 4
    //   137: aload 11
    //   139: astore_0
    //   140: aload 11
    //   142: ldc 25
    //   144: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   147: istore 5
    //   149: aload 11
    //   151: astore_0
    //   152: aload 11
    //   154: ldc 28
    //   156: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   159: istore 6
    //   161: aload 11
    //   163: astore_0
    //   164: aload 11
    //   166: ldc 31
    //   168: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   171: istore 7
    //   173: aload 11
    //   175: astore_0
    //   176: aload 11
    //   178: ldc 34
    //   180: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   183: istore 8
    //   185: aload 11
    //   187: astore_0
    //   188: aload 11
    //   190: ldc 37
    //   192: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   195: istore 9
    //   197: aload 11
    //   199: astore_0
    //   200: aload 11
    //   202: ldc 40
    //   204: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   207: istore 10
    //   209: aload 11
    //   211: astore_0
    //   212: aload 11
    //   214: invokevirtual 158	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   217: ifeq +192 -> 409
    //   220: aload 11
    //   222: astore_0
    //   223: new 160	cn/com/xy/sms/sdk/db/entity/d
    //   226: dup
    //   227: invokespecial 161	cn/com/xy/sms/sdk/db/entity/d:<init>	()V
    //   230: astore 12
    //   232: aload 11
    //   234: astore_0
    //   235: aload 11
    //   237: iload_1
    //   238: invokevirtual 165	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   241: pop2
    //   242: aload 11
    //   244: astore_0
    //   245: aload 12
    //   247: aload 11
    //   249: iload_2
    //   250: invokevirtual 169	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   253: putfield 171	cn/com/xy/sms/sdk/db/entity/d:b	Ljava/lang/String;
    //   256: aload 11
    //   258: astore_0
    //   259: aload 12
    //   261: aload 11
    //   263: iload_3
    //   264: invokevirtual 169	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   267: putfield 173	cn/com/xy/sms/sdk/db/entity/d:c	Ljava/lang/String;
    //   270: aload 11
    //   272: astore_0
    //   273: aload 12
    //   275: aload 11
    //   277: iload 4
    //   279: invokevirtual 169	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   282: putfield 175	cn/com/xy/sms/sdk/db/entity/d:d	Ljava/lang/String;
    //   285: aload 11
    //   287: astore_0
    //   288: aload 12
    //   290: aload 11
    //   292: iload 5
    //   294: invokevirtual 179	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   297: putfield 181	cn/com/xy/sms/sdk/db/entity/d:f	I
    //   300: aload 11
    //   302: astore_0
    //   303: aload 11
    //   305: iload 6
    //   307: invokevirtual 165	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   310: pop2
    //   311: aload 11
    //   313: astore_0
    //   314: aload 12
    //   316: aload 11
    //   318: iload 7
    //   320: invokevirtual 165	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   323: putfield 184	cn/com/xy/sms/sdk/db/entity/d:e	J
    //   326: aload 11
    //   328: astore_0
    //   329: aload 12
    //   331: aload 11
    //   333: iload 8
    //   335: invokevirtual 165	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   338: putfield 186	cn/com/xy/sms/sdk/db/entity/d:h	J
    //   341: aload 11
    //   343: astore_0
    //   344: aload 12
    //   346: aload 11
    //   348: iload 9
    //   350: invokevirtual 165	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   353: putfield 188	cn/com/xy/sms/sdk/db/entity/d:i	J
    //   356: aload 11
    //   358: astore_0
    //   359: aload 11
    //   361: iload 10
    //   363: invokevirtual 179	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   366: pop
    //   367: aload 11
    //   369: iconst_1
    //   370: invokestatic 192	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   373: aload 12
    //   375: areturn
    //   376: astore 12
    //   378: aconst_null
    //   379: astore 11
    //   381: aload 11
    //   383: astore_0
    //   384: aload 12
    //   386: invokevirtual 137	java/lang/Throwable:printStackTrace	()V
    //   389: aload 11
    //   391: iconst_1
    //   392: invokestatic 192	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   395: aconst_null
    //   396: areturn
    //   397: astore 11
    //   399: aconst_null
    //   400: astore_0
    //   401: aload_0
    //   402: iconst_1
    //   403: invokestatic 192	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   406: aload 11
    //   408: athrow
    //   409: aload 11
    //   411: iconst_1
    //   412: invokestatic 192	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   415: goto -20 -> 395
    //   418: astore 11
    //   420: goto -19 -> 401
    //   423: astore 12
    //   425: goto -44 -> 381
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	428	0	paramString	String
    //   102	136	1	i1	int
    //   113	137	2	i2	int
    //   124	140	3	i3	int
    //   135	143	4	i4	int
    //   147	146	5	i5	int
    //   159	147	6	i6	int
    //   171	148	7	i7	int
    //   183	151	8	i8	int
    //   195	154	9	i9	int
    //   207	155	10	i10	int
    //   74	316	11	localXyCursor1	XyCursor
    //   397	13	11	localXyCursor2	XyCursor
    //   418	1	11	localObject	Object
    //   230	144	12	locald	d
    //   376	9	12	localThrowable1	Throwable
    //   423	1	12	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   0	76	376	java/lang/Throwable
    //   0	76	397	finally
    //   84	92	418	finally
    //   95	103	418	finally
    //   106	114	418	finally
    //   117	125	418	finally
    //   128	137	418	finally
    //   140	149	418	finally
    //   152	161	418	finally
    //   164	173	418	finally
    //   176	185	418	finally
    //   188	197	418	finally
    //   200	209	418	finally
    //   212	220	418	finally
    //   223	232	418	finally
    //   235	242	418	finally
    //   245	256	418	finally
    //   259	270	418	finally
    //   273	285	418	finally
    //   288	300	418	finally
    //   303	311	418	finally
    //   314	326	418	finally
    //   329	341	418	finally
    //   344	356	418	finally
    //   359	367	418	finally
    //   384	389	418	finally
    //   84	92	423	java/lang/Throwable
    //   95	103	423	java/lang/Throwable
    //   106	114	423	java/lang/Throwable
    //   117	125	423	java/lang/Throwable
    //   128	137	423	java/lang/Throwable
    //   140	149	423	java/lang/Throwable
    //   152	161	423	java/lang/Throwable
    //   164	173	423	java/lang/Throwable
    //   176	185	423	java/lang/Throwable
    //   188	197	423	java/lang/Throwable
    //   200	209	423	java/lang/Throwable
    //   212	220	423	java/lang/Throwable
    //   223	232	423	java/lang/Throwable
    //   235	242	423	java/lang/Throwable
    //   245	256	423	java/lang/Throwable
    //   259	270	423	java/lang/Throwable
    //   273	285	423	java/lang/Throwable
    //   288	300	423	java/lang/Throwable
    //   303	311	423	java/lang/Throwable
    //   314	326	423	java/lang/Throwable
    //   329	341	423	java/lang/Throwable
    //   344	356	423	java/lang/Throwable
    //   359	367	423	java/lang/Throwable
  }
  
  /* Error */
  public static List<d> a()
  {
    // Byte code:
    //   0: new 195	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 196	java/util/ArrayList:<init>	()V
    //   7: astore 14
    //   9: aconst_null
    //   10: astore 12
    //   12: aconst_null
    //   13: astore 11
    //   15: ldc 43
    //   17: bipush 10
    //   19: anewarray 121	java/lang/String
    //   22: dup
    //   23: iconst_0
    //   24: ldc 13
    //   26: aastore
    //   27: dup
    //   28: iconst_1
    //   29: ldc 16
    //   31: aastore
    //   32: dup
    //   33: iconst_2
    //   34: ldc 19
    //   36: aastore
    //   37: dup
    //   38: iconst_3
    //   39: ldc 22
    //   41: aastore
    //   42: dup
    //   43: iconst_4
    //   44: ldc 25
    //   46: aastore
    //   47: dup
    //   48: iconst_5
    //   49: ldc 28
    //   51: aastore
    //   52: dup
    //   53: bipush 6
    //   55: ldc 31
    //   57: aastore
    //   58: dup
    //   59: bipush 7
    //   61: ldc 34
    //   63: aastore
    //   64: dup
    //   65: bipush 8
    //   67: ldc 37
    //   69: aastore
    //   70: dup
    //   71: bipush 9
    //   73: ldc 40
    //   75: aastore
    //   76: ldc -58
    //   78: iconst_2
    //   79: anewarray 121	java/lang/String
    //   82: dup
    //   83: iconst_0
    //   84: ldc -56
    //   86: aastore
    //   87: dup
    //   88: iconst_1
    //   89: ldc -54
    //   91: aastore
    //   92: invokestatic 146	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   95: astore 13
    //   97: aload 13
    //   99: ifnull +205 -> 304
    //   102: aload 13
    //   104: astore 11
    //   106: aload 13
    //   108: astore 12
    //   110: aload 13
    //   112: invokevirtual 151	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   115: ifle +189 -> 304
    //   118: aload 13
    //   120: astore 11
    //   122: aload 13
    //   124: astore 12
    //   126: aload 13
    //   128: ldc 13
    //   130: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   133: istore_0
    //   134: aload 13
    //   136: astore 11
    //   138: aload 13
    //   140: astore 12
    //   142: aload 13
    //   144: ldc 16
    //   146: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   149: istore_1
    //   150: aload 13
    //   152: astore 11
    //   154: aload 13
    //   156: astore 12
    //   158: aload 13
    //   160: ldc 19
    //   162: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   165: istore_2
    //   166: aload 13
    //   168: astore 11
    //   170: aload 13
    //   172: astore 12
    //   174: aload 13
    //   176: ldc 22
    //   178: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   181: istore_3
    //   182: aload 13
    //   184: astore 11
    //   186: aload 13
    //   188: astore 12
    //   190: aload 13
    //   192: ldc 25
    //   194: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   197: istore 4
    //   199: aload 13
    //   201: astore 11
    //   203: aload 13
    //   205: astore 12
    //   207: aload 13
    //   209: ldc 28
    //   211: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   214: istore 5
    //   216: aload 13
    //   218: astore 11
    //   220: aload 13
    //   222: astore 12
    //   224: aload 13
    //   226: ldc 31
    //   228: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   231: istore 6
    //   233: aload 13
    //   235: astore 11
    //   237: aload 13
    //   239: astore 12
    //   241: aload 13
    //   243: ldc 34
    //   245: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   248: istore 7
    //   250: aload 13
    //   252: astore 11
    //   254: aload 13
    //   256: astore 12
    //   258: aload 13
    //   260: ldc 37
    //   262: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   265: istore 8
    //   267: aload 13
    //   269: astore 11
    //   271: aload 13
    //   273: astore 12
    //   275: aload 13
    //   277: ldc 40
    //   279: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   282: istore 9
    //   284: aload 13
    //   286: astore 11
    //   288: aload 13
    //   290: astore 12
    //   292: aload 13
    //   294: invokevirtual 158	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   297: istore 10
    //   299: iload 10
    //   301: ifne +12 -> 313
    //   304: aload 13
    //   306: iconst_1
    //   307: invokestatic 192	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   310: aload 14
    //   312: areturn
    //   313: aload 13
    //   315: astore 11
    //   317: aload 13
    //   319: astore 12
    //   321: new 160	cn/com/xy/sms/sdk/db/entity/d
    //   324: dup
    //   325: invokespecial 161	cn/com/xy/sms/sdk/db/entity/d:<init>	()V
    //   328: astore 15
    //   330: aload 13
    //   332: astore 11
    //   334: aload 13
    //   336: astore 12
    //   338: aload 13
    //   340: iload_0
    //   341: invokevirtual 165	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   344: pop2
    //   345: aload 13
    //   347: astore 11
    //   349: aload 13
    //   351: astore 12
    //   353: aload 15
    //   355: aload 13
    //   357: iload_1
    //   358: invokevirtual 169	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   361: putfield 171	cn/com/xy/sms/sdk/db/entity/d:b	Ljava/lang/String;
    //   364: aload 13
    //   366: astore 11
    //   368: aload 13
    //   370: astore 12
    //   372: aload 15
    //   374: aload 13
    //   376: iload_2
    //   377: invokevirtual 169	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   380: putfield 173	cn/com/xy/sms/sdk/db/entity/d:c	Ljava/lang/String;
    //   383: aload 13
    //   385: astore 11
    //   387: aload 13
    //   389: astore 12
    //   391: aload 15
    //   393: aload 13
    //   395: iload_3
    //   396: invokevirtual 169	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   399: putfield 175	cn/com/xy/sms/sdk/db/entity/d:d	Ljava/lang/String;
    //   402: aload 13
    //   404: astore 11
    //   406: aload 13
    //   408: astore 12
    //   410: aload 15
    //   412: aload 13
    //   414: iload 4
    //   416: invokevirtual 179	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   419: putfield 181	cn/com/xy/sms/sdk/db/entity/d:f	I
    //   422: aload 13
    //   424: astore 11
    //   426: aload 13
    //   428: astore 12
    //   430: aload 13
    //   432: iload 5
    //   434: invokevirtual 165	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   437: pop2
    //   438: aload 13
    //   440: astore 11
    //   442: aload 13
    //   444: astore 12
    //   446: aload 15
    //   448: aload 13
    //   450: iload 6
    //   452: invokevirtual 165	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   455: putfield 184	cn/com/xy/sms/sdk/db/entity/d:e	J
    //   458: aload 13
    //   460: astore 11
    //   462: aload 13
    //   464: astore 12
    //   466: aload 15
    //   468: aload 13
    //   470: iload 7
    //   472: invokevirtual 165	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   475: putfield 186	cn/com/xy/sms/sdk/db/entity/d:h	J
    //   478: aload 13
    //   480: astore 11
    //   482: aload 13
    //   484: astore 12
    //   486: aload 15
    //   488: aload 13
    //   490: iload 8
    //   492: invokevirtual 165	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   495: putfield 188	cn/com/xy/sms/sdk/db/entity/d:i	J
    //   498: aload 13
    //   500: astore 11
    //   502: aload 13
    //   504: astore 12
    //   506: aload 13
    //   508: iload 9
    //   510: invokevirtual 179	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   513: pop
    //   514: aload 13
    //   516: astore 11
    //   518: aload 13
    //   520: astore 12
    //   522: aload 14
    //   524: aload 15
    //   526: invokeinterface 206 2 0
    //   531: pop
    //   532: goto -248 -> 284
    //   535: astore 13
    //   537: aload 11
    //   539: astore 12
    //   541: aload 13
    //   543: invokevirtual 137	java/lang/Throwable:printStackTrace	()V
    //   546: aload 11
    //   548: iconst_1
    //   549: invokestatic 192	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   552: aload 14
    //   554: areturn
    //   555: astore 11
    //   557: aload 12
    //   559: iconst_1
    //   560: invokestatic 192	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   563: aload 11
    //   565: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   133	208	0	i1	int
    //   149	209	1	i2	int
    //   165	212	2	i3	int
    //   181	215	3	i4	int
    //   197	218	4	i5	int
    //   214	219	5	i6	int
    //   231	220	6	i7	int
    //   248	223	7	i8	int
    //   265	226	8	i9	int
    //   282	227	9	i10	int
    //   297	3	10	bool	boolean
    //   13	534	11	localObject1	Object
    //   555	9	11	localObject2	Object
    //   10	548	12	localObject3	Object
    //   95	424	13	localXyCursor	XyCursor
    //   535	7	13	localThrowable	Throwable
    //   7	546	14	localArrayList	ArrayList
    //   328	197	15	locald	d
    // Exception table:
    //   from	to	target	type
    //   15	97	535	java/lang/Throwable
    //   110	118	535	java/lang/Throwable
    //   126	134	535	java/lang/Throwable
    //   142	150	535	java/lang/Throwable
    //   158	166	535	java/lang/Throwable
    //   174	182	535	java/lang/Throwable
    //   190	199	535	java/lang/Throwable
    //   207	216	535	java/lang/Throwable
    //   224	233	535	java/lang/Throwable
    //   241	250	535	java/lang/Throwable
    //   258	267	535	java/lang/Throwable
    //   275	284	535	java/lang/Throwable
    //   292	299	535	java/lang/Throwable
    //   321	330	535	java/lang/Throwable
    //   338	345	535	java/lang/Throwable
    //   353	364	535	java/lang/Throwable
    //   372	383	535	java/lang/Throwable
    //   391	402	535	java/lang/Throwable
    //   410	422	535	java/lang/Throwable
    //   430	438	535	java/lang/Throwable
    //   446	458	535	java/lang/Throwable
    //   466	478	535	java/lang/Throwable
    //   486	498	535	java/lang/Throwable
    //   506	514	535	java/lang/Throwable
    //   522	532	535	java/lang/Throwable
    //   15	97	555	finally
    //   110	118	555	finally
    //   126	134	555	finally
    //   142	150	555	finally
    //   158	166	555	finally
    //   174	182	555	finally
    //   190	199	555	finally
    //   207	216	555	finally
    //   224	233	555	finally
    //   241	250	555	finally
    //   258	267	555	finally
    //   275	284	555	finally
    //   292	299	555	finally
    //   321	330	555	finally
    //   338	345	555	finally
    //   353	364	555	finally
    //   372	383	555	finally
    //   391	402	555	finally
    //   410	422	555	finally
    //   430	438	555	finally
    //   446	458	555	finally
    //   466	478	555	finally
    //   486	498	555	finally
    //   506	514	555	finally
    //   522	532	555	finally
    //   541	546	555	finally
  }
  
  public static void a(String paramString, int paramInt)
  {
    try
    {
      long l1 = System.currentTimeMillis();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("last_load_time", l1);
      localContentValues.put("status", "1");
      DBManager.update("tb_jar_list", localContentValues, "name = ? ", new String[] { paramString });
      SdkParamUtil.setParamValue(Constant.getContext(), "SMART_DATA_UPDATE_TIME", l1);
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  private static void a(String paramString, long paramLong1, long paramLong2)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("update_time", System.currentTimeMillis());
      localContentValues.put("delaystart", paramLong1);
      localContentValues.put("delayend", paramLong2);
      DBManager.update("tb_jar_list", localContentValues, "name = ? ", new String[] { paramString });
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static void a(String paramString1, String paramString2, int paramInt)
  {
    localObject2 = null;
    localObject1 = null;
    for (;;)
    {
      try
      {
        localXyCursor = DBManager.query("tb_jar_list", new String[] { "url", "version" }, "name = ? ", new String[] { paramString1 });
        localObject1 = localXyCursor;
        localObject2 = localXyCursor;
        localContentValues = new ContentValues();
        localObject1 = localXyCursor;
        localObject2 = localXyCursor;
        localContentValues.put("name", paramString1);
        if (paramInt == 1)
        {
          localObject1 = localXyCursor;
          localObject2 = localXyCursor;
          localContentValues.put("is_use", Integer.valueOf(paramInt));
        }
        localObject1 = localXyCursor;
        localObject2 = localXyCursor;
        new StringBuilder("更新或插入 name=").append(paramString1).append(" version=").append(paramString2);
        if (localXyCursor != null)
        {
          localObject1 = localXyCursor;
          localObject2 = localXyCursor;
          if (localXyCursor.getCount() > 0)
          {
            localObject1 = localXyCursor;
            localObject2 = localXyCursor;
            if (!StringUtils.isNull(paramString2))
            {
              localObject1 = localXyCursor;
              localObject2 = localXyCursor;
              if (!"-1".equalsIgnoreCase(paramString2))
              {
                localObject1 = localXyCursor;
                localObject2 = localXyCursor;
                localContentValues.put("version", paramString2);
              }
            }
            localObject1 = localXyCursor;
            localObject2 = localXyCursor;
            l1 = DBManager.update("tb_jar_list", localContentValues, "name = ? ", new String[] { paramString1 });
            if (l1 > 0L)
            {
              localObject1 = localXyCursor;
              localObject2 = localXyCursor;
              if (paramString1.startsWith("PU"))
              {
                localObject1 = localXyCursor;
                localObject2 = localXyCursor;
                paramString1 = paramString1.replaceFirst("PU", "");
                localObject1 = localXyCursor;
                localObject2 = localXyCursor;
                if (paramString1.length() >= 8)
                {
                  localObject1 = localXyCursor;
                  localObject2 = localXyCursor;
                  paramString1.substring(0, 8);
                }
              }
            }
          }
        }
      }
      catch (Throwable paramString1)
      {
        XyCursor localXyCursor;
        ContentValues localContentValues;
        long l1;
        localObject2 = localObject1;
        paramString1.printStackTrace();
        XyCursor.closeCursor((XyCursor)localObject1, true);
        continue;
      }
      finally
      {
        XyCursor.closeCursor((XyCursor)localObject2, true);
      }
      try
      {
        XyCursor.closeCursor(localXyCursor, true);
        return;
      }
      finally {}
      localObject1 = localXyCursor;
      localObject2 = localXyCursor;
      localContentValues.put("version", paramString2);
      localObject1 = localXyCursor;
      localObject2 = localXyCursor;
      l1 = DBManager.insert("tb_jar_list", localContentValues);
    }
  }
  
  public static void a(String paramString1, String paramString2, String paramString3, long paramLong1, int paramInt, long paramLong2, long paramLong3)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("version", paramString2);
      localContentValues.put("url", paramString3);
      localContentValues.put("status", Integer.valueOf(0));
      localContentValues.put("update_time", paramLong1);
      localContentValues.put("delaystart", paramLong2);
      localContentValues.put("delayend", paramLong3);
      DBManager.update("tb_jar_list", localContentValues, "name = ? ", new String[] { paramString1 });
      return;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  /* Error */
  public static java.util.HashMap<String, String> b()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_3
    //   5: new 286	java/util/HashMap
    //   8: dup
    //   9: invokespecial 287	java/util/HashMap:<init>	()V
    //   12: astore 6
    //   14: ldc 43
    //   16: iconst_2
    //   17: anewarray 121	java/lang/String
    //   20: dup
    //   21: iconst_0
    //   22: ldc 16
    //   24: aastore
    //   25: dup
    //   26: iconst_1
    //   27: ldc 19
    //   29: aastore
    //   30: aconst_null
    //   31: aconst_null
    //   32: invokestatic 146	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   35: astore 5
    //   37: aload 5
    //   39: ifnull +65 -> 104
    //   42: aload 5
    //   44: astore_3
    //   45: aload 5
    //   47: astore 4
    //   49: aload 5
    //   51: invokevirtual 151	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   54: ifle +50 -> 104
    //   57: aload 5
    //   59: astore_3
    //   60: aload 5
    //   62: astore 4
    //   64: aload 5
    //   66: ldc 16
    //   68: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   71: istore_0
    //   72: aload 5
    //   74: astore_3
    //   75: aload 5
    //   77: astore 4
    //   79: aload 5
    //   81: ldc 19
    //   83: invokevirtual 155	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   86: istore_1
    //   87: aload 5
    //   89: astore_3
    //   90: aload 5
    //   92: astore 4
    //   94: aload 5
    //   96: invokevirtual 158	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   99: istore_2
    //   100: iload_2
    //   101: ifne +12 -> 113
    //   104: aload 5
    //   106: iconst_1
    //   107: invokestatic 192	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   110: aload 6
    //   112: areturn
    //   113: aload 5
    //   115: astore_3
    //   116: aload 5
    //   118: astore 4
    //   120: aload 6
    //   122: aload 5
    //   124: iload_0
    //   125: invokevirtual 169	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   128: aload 5
    //   130: iload_1
    //   131: invokevirtual 169	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   134: invokevirtual 290	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   137: pop
    //   138: goto -51 -> 87
    //   141: astore 5
    //   143: aload_3
    //   144: astore 4
    //   146: aload 5
    //   148: invokevirtual 137	java/lang/Throwable:printStackTrace	()V
    //   151: aload_3
    //   152: iconst_1
    //   153: invokestatic 192	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   156: aload 6
    //   158: areturn
    //   159: astore_3
    //   160: aload 4
    //   162: iconst_1
    //   163: invokestatic 192	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   166: aload_3
    //   167: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   71	54	0	i1	int
    //   86	45	1	i2	int
    //   99	2	2	bool	boolean
    //   4	148	3	localObject1	Object
    //   159	8	3	localObject2	Object
    //   1	160	4	localObject3	Object
    //   35	94	5	localXyCursor	XyCursor
    //   141	6	5	localThrowable	Throwable
    //   12	145	6	localHashMap	java.util.HashMap
    // Exception table:
    //   from	to	target	type
    //   14	37	141	java/lang/Throwable
    //   49	57	141	java/lang/Throwable
    //   64	72	141	java/lang/Throwable
    //   79	87	141	java/lang/Throwable
    //   94	100	141	java/lang/Throwable
    //   120	138	141	java/lang/Throwable
    //   14	37	159	finally
    //   49	57	159	finally
    //   64	72	159	finally
    //   79	87	159	finally
    //   94	100	159	finally
    //   120	138	159	finally
    //   146	151	159	finally
  }
  
  public static void b(String paramString)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("update_time", System.currentTimeMillis());
      DBManager.update("tb_jar_list", localContentValues, "name = ? ", new String[] { paramString });
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  private static void b(String paramString, int paramInt)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("count", Integer.valueOf(paramInt + 1));
      DBManager.update("tb_jar_list", localContentValues, "name = ? ", new String[] { paramString });
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  /* Error */
  public static boolean b(List<String> paramList)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore_2
    //   4: invokestatic 296	cn/com/xy/sms/sdk/db/DBManager:getSQLiteDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   7: astore 4
    //   9: aload 4
    //   11: astore_2
    //   12: aload 4
    //   14: astore_3
    //   15: aload 4
    //   17: invokevirtual 301	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   20: aload 4
    //   22: astore_2
    //   23: aload 4
    //   25: astore_3
    //   26: new 73	java/lang/StringBuilder
    //   29: dup
    //   30: invokespecial 74	java/lang/StringBuilder:<init>	()V
    //   33: astore 5
    //   35: aload 4
    //   37: astore_2
    //   38: aload 4
    //   40: astore_3
    //   41: aload 5
    //   43: ldc_w 303
    //   46: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: pop
    //   50: aload 4
    //   52: astore_2
    //   53: aload 4
    //   55: astore_3
    //   56: aload 5
    //   58: ldc_w 305
    //   61: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: pop
    //   65: aload 4
    //   67: astore_2
    //   68: aload 4
    //   70: astore_3
    //   71: aload 5
    //   73: ldc_w 307
    //   76: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: pop
    //   80: aload 4
    //   82: astore_2
    //   83: aload 4
    //   85: astore_3
    //   86: aload 5
    //   88: ldc_w 309
    //   91: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: pop
    //   95: aload 4
    //   97: astore_2
    //   98: aload 4
    //   100: astore_3
    //   101: aload 5
    //   103: ldc_w 311
    //   106: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: pop
    //   110: aload 4
    //   112: astore_2
    //   113: aload 4
    //   115: astore_3
    //   116: aload 5
    //   118: ldc_w 313
    //   121: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: pop
    //   125: aload 4
    //   127: astore_2
    //   128: aload 4
    //   130: astore_3
    //   131: aload 5
    //   133: ldc_w 315
    //   136: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: pop
    //   140: aload 4
    //   142: astore_2
    //   143: aload 4
    //   145: astore_3
    //   146: aload 5
    //   148: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   151: astore 5
    //   153: aload 4
    //   155: astore_2
    //   156: aload 4
    //   158: astore_3
    //   159: aload_0
    //   160: invokeinterface 78 1 0
    //   165: astore_0
    //   166: aload 4
    //   168: astore_2
    //   169: aload 4
    //   171: astore_3
    //   172: aload_0
    //   173: invokeinterface 84 1 0
    //   178: istore_1
    //   179: iload_1
    //   180: ifne +41 -> 221
    //   183: aload 4
    //   185: ifnull +34 -> 219
    //   188: aload 4
    //   190: ldc_w 317
    //   193: invokevirtual 320	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   196: aload 4
    //   198: invokevirtual 323	android/database/sqlite/SQLiteDatabase:inTransaction	()Z
    //   201: ifeq +13 -> 214
    //   204: aload 4
    //   206: invokevirtual 326	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   209: aload 4
    //   211: invokevirtual 329	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   214: aload 4
    //   216: invokestatic 333	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   219: iconst_1
    //   220: ireturn
    //   221: aload 4
    //   223: astore_2
    //   224: aload 4
    //   226: astore_3
    //   227: aload 4
    //   229: aload 5
    //   231: ldc_w 335
    //   234: aload_0
    //   235: invokeinterface 119 1 0
    //   240: checkcast 121	java/lang/String
    //   243: invokevirtual 339	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   246: invokevirtual 320	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   249: goto -83 -> 166
    //   252: astore_0
    //   253: aload_2
    //   254: astore_3
    //   255: aload_0
    //   256: invokevirtual 137	java/lang/Throwable:printStackTrace	()V
    //   259: aload_2
    //   260: ifnull +29 -> 289
    //   263: aload_2
    //   264: ldc_w 317
    //   267: invokevirtual 320	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   270: aload_2
    //   271: invokevirtual 323	android/database/sqlite/SQLiteDatabase:inTransaction	()Z
    //   274: ifeq +11 -> 285
    //   277: aload_2
    //   278: invokevirtual 326	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   281: aload_2
    //   282: invokevirtual 329	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   285: aload_2
    //   286: invokestatic 333	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   289: iconst_0
    //   290: ireturn
    //   291: astore_0
    //   292: aload_0
    //   293: invokevirtual 137	java/lang/Throwable:printStackTrace	()V
    //   296: goto -100 -> 196
    //   299: astore_0
    //   300: aload_0
    //   301: invokevirtual 137	java/lang/Throwable:printStackTrace	()V
    //   304: goto -90 -> 214
    //   307: astore_0
    //   308: aload_0
    //   309: invokevirtual 137	java/lang/Throwable:printStackTrace	()V
    //   312: goto -42 -> 270
    //   315: astore_0
    //   316: aload_0
    //   317: invokevirtual 137	java/lang/Throwable:printStackTrace	()V
    //   320: goto -35 -> 285
    //   323: astore_0
    //   324: aload_3
    //   325: ifnull +29 -> 354
    //   328: aload_3
    //   329: ldc_w 317
    //   332: invokevirtual 320	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   335: aload_3
    //   336: invokevirtual 323	android/database/sqlite/SQLiteDatabase:inTransaction	()Z
    //   339: ifeq +11 -> 350
    //   342: aload_3
    //   343: invokevirtual 326	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   346: aload_3
    //   347: invokevirtual 329	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   350: aload_3
    //   351: invokestatic 333	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   354: aload_0
    //   355: athrow
    //   356: astore_2
    //   357: aload_2
    //   358: invokevirtual 137	java/lang/Throwable:printStackTrace	()V
    //   361: goto -26 -> 335
    //   364: astore_2
    //   365: aload_2
    //   366: invokevirtual 137	java/lang/Throwable:printStackTrace	()V
    //   369: goto -19 -> 350
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	372	0	paramList	List<String>
    //   178	2	1	bool	boolean
    //   3	283	2	localObject1	Object
    //   356	2	2	localThrowable1	Throwable
    //   364	2	2	localThrowable2	Throwable
    //   1	350	3	localObject2	Object
    //   7	221	4	localSQLiteDatabase	android.database.sqlite.SQLiteDatabase
    //   33	197	5	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   4	9	252	java/lang/Throwable
    //   15	20	252	java/lang/Throwable
    //   26	35	252	java/lang/Throwable
    //   41	50	252	java/lang/Throwable
    //   56	65	252	java/lang/Throwable
    //   71	80	252	java/lang/Throwable
    //   86	95	252	java/lang/Throwable
    //   101	110	252	java/lang/Throwable
    //   116	125	252	java/lang/Throwable
    //   131	140	252	java/lang/Throwable
    //   146	153	252	java/lang/Throwable
    //   159	166	252	java/lang/Throwable
    //   172	179	252	java/lang/Throwable
    //   227	249	252	java/lang/Throwable
    //   188	196	291	java/lang/Throwable
    //   196	214	299	java/lang/Throwable
    //   263	270	307	java/lang/Throwable
    //   270	285	315	java/lang/Throwable
    //   4	9	323	finally
    //   15	20	323	finally
    //   26	35	323	finally
    //   41	50	323	finally
    //   56	65	323	finally
    //   71	80	323	finally
    //   86	95	323	finally
    //   101	110	323	finally
    //   116	125	323	finally
    //   131	140	323	finally
    //   146	153	323	finally
    //   159	166	323	finally
    //   172	179	323	finally
    //   227	249	323	finally
    //   255	259	323	finally
    //   328	335	356	java/lang/Throwable
    //   335	350	364	java/lang/Throwable
  }
  
  public static int c()
  {
    try
    {
      int i1 = DBManager.delete("tb_jar_list", null, null);
      return i1;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return -1;
  }
  
  public static int c(String paramString)
  {
    if (("parseUtilMain".equals(paramString)) || ("ParseHelper".equals(paramString)) || ("ScenesScanner".equals(paramString))) {
      return 1;
    }
    return 0;
  }
  
  public static int d()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("ParseUtilCasual");
    localArrayList.add("ParseUtilEC");
    localArrayList.add("ParseUtilFinanceL");
    localArrayList.add("ParseUtilFinanceM");
    localArrayList.add("ParseUtilFinanceS");
    localArrayList.add("ParseUtilLife");
    localArrayList.add("ParseUtilMove");
    localArrayList.add("ParseUtilTelecom");
    localArrayList.add("ParseUtilTravel");
    localArrayList.add("ParseUtilUnicom");
    return a(localArrayList);
  }
  
  private static int d(String paramString)
  {
    try
    {
      if (StringUtils.isNull(paramString)) {
        return 0;
      }
      int i1 = DBManager.delete("tb_jar_list", "name = ?", new String[] { paramString });
      return i1;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
    return -1;
  }
  
  /* Error */
  public static boolean e()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: ldc 43
    //   4: iconst_1
    //   5: anewarray 121	java/lang/String
    //   8: dup
    //   9: iconst_0
    //   10: ldc 16
    //   12: aastore
    //   13: ldc_w 373
    //   16: iconst_3
    //   17: anewarray 121	java/lang/String
    //   20: dup
    //   21: iconst_0
    //   22: ldc -56
    //   24: aastore
    //   25: dup
    //   26: iconst_1
    //   27: ldc -54
    //   29: aastore
    //   30: dup
    //   31: iconst_2
    //   32: ldc_w 375
    //   35: aastore
    //   36: aconst_null
    //   37: aconst_null
    //   38: aconst_null
    //   39: ldc_w 377
    //   42: invokestatic 380	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   45: astore_1
    //   46: aload_1
    //   47: astore_2
    //   48: aload_2
    //   49: ifnull +51 -> 100
    //   52: aload_2
    //   53: astore_1
    //   54: aload_2
    //   55: invokevirtual 151	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   58: istore_0
    //   59: iload_0
    //   60: ifle +40 -> 100
    //   63: aload_2
    //   64: iconst_1
    //   65: invokestatic 192	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   68: iconst_1
    //   69: ireturn
    //   70: astore_3
    //   71: aconst_null
    //   72: astore_2
    //   73: aload_2
    //   74: astore_1
    //   75: ldc_w 382
    //   78: ldc_w 384
    //   81: aload_3
    //   82: invokestatic 389	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   85: aload_2
    //   86: iconst_1
    //   87: invokestatic 192	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   90: iconst_0
    //   91: ireturn
    //   92: astore_1
    //   93: aload_2
    //   94: iconst_1
    //   95: invokestatic 192	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   98: aload_1
    //   99: athrow
    //   100: aload_2
    //   101: iconst_1
    //   102: invokestatic 192	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   105: goto -15 -> 90
    //   108: astore_3
    //   109: aload_1
    //   110: astore_2
    //   111: aload_3
    //   112: astore_1
    //   113: goto -20 -> 93
    //   116: astore_3
    //   117: goto -44 -> 73
    // Local variable table:
    //   start	length	slot	name	signature
    //   58	2	0	i1	int
    //   45	30	1	localObject1	Object
    //   92	18	1	localObject2	Object
    //   112	1	1	localObject3	Object
    //   1	110	2	localObject4	Object
    //   70	12	3	localThrowable1	Throwable
    //   108	4	3	localObject5	Object
    //   116	1	3	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   2	46	70	java/lang/Throwable
    //   2	46	92	finally
    //   54	59	108	finally
    //   75	85	108	finally
    //   54	59	116	java/lang/Throwable
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.e
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */