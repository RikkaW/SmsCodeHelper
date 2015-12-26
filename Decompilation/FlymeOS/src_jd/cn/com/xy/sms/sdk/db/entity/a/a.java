package cn.com.xy.sms.sdk.db.entity.a;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.util.StringUtils;

public final class a
{
  /* Error */
  public static cn.com.xy.sms.sdk.db.entity.a a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aconst_null
    //   4: astore 8
    //   6: aconst_null
    //   7: astore 10
    //   9: aconst_null
    //   10: astore 9
    //   12: aload_0
    //   13: invokestatic 19	cn/com/xy/sms/sdk/util/StringUtils:getSubString	(Ljava/lang/String;)Ljava/lang/String;
    //   16: astore 11
    //   18: new 21	java/lang/StringBuilder
    //   21: dup
    //   22: aload 11
    //   24: invokestatic 27	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   27: invokespecial 30	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   30: invokevirtual 34	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   33: astore_0
    //   34: ldc 36
    //   36: iconst_5
    //   37: anewarray 23	java/lang/String
    //   40: dup
    //   41: iconst_0
    //   42: ldc 38
    //   44: aastore
    //   45: dup
    //   46: iconst_1
    //   47: ldc 40
    //   49: aastore
    //   50: dup
    //   51: iconst_2
    //   52: ldc 42
    //   54: aastore
    //   55: dup
    //   56: iconst_3
    //   57: ldc 44
    //   59: aastore
    //   60: dup
    //   61: iconst_4
    //   62: ldc 46
    //   64: aastore
    //   65: ldc 48
    //   67: iconst_1
    //   68: anewarray 23	java/lang/String
    //   71: dup
    //   72: iconst_0
    //   73: aload_0
    //   74: aastore
    //   75: invokestatic 54	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   78: astore 6
    //   80: aload 7
    //   82: astore_0
    //   83: aload 6
    //   85: ifnull +109 -> 194
    //   88: aload 7
    //   90: astore_0
    //   91: aload 6
    //   93: astore 7
    //   95: aload 10
    //   97: astore 8
    //   99: aload 6
    //   101: invokevirtual 60	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   104: ifle +90 -> 194
    //   107: aload 6
    //   109: astore 7
    //   111: aload 10
    //   113: astore 8
    //   115: aload 6
    //   117: ldc 40
    //   119: invokevirtual 64	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   122: istore_1
    //   123: aload 6
    //   125: astore 7
    //   127: aload 10
    //   129: astore 8
    //   131: aload 6
    //   133: ldc 42
    //   135: invokevirtual 64	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   138: istore_2
    //   139: aload 6
    //   141: astore 7
    //   143: aload 10
    //   145: astore 8
    //   147: aload 6
    //   149: ldc 44
    //   151: invokevirtual 64	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   154: istore_3
    //   155: aload 6
    //   157: astore 7
    //   159: aload 10
    //   161: astore 8
    //   163: aload 6
    //   165: ldc 46
    //   167: invokevirtual 64	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   170: istore 4
    //   172: aload 9
    //   174: astore_0
    //   175: aload 6
    //   177: astore 7
    //   179: aload_0
    //   180: astore 8
    //   182: aload 6
    //   184: invokevirtual 68	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   187: istore 5
    //   189: iload 5
    //   191: ifne +11 -> 202
    //   194: aload 6
    //   196: iconst_1
    //   197: invokestatic 72	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   200: aload_0
    //   201: areturn
    //   202: aload 6
    //   204: astore 7
    //   206: aload_0
    //   207: astore 8
    //   209: new 74	cn/com/xy/sms/sdk/db/entity/a
    //   212: dup
    //   213: invokespecial 75	cn/com/xy/sms/sdk/db/entity/a:<init>	()V
    //   216: astore_0
    //   217: aload 6
    //   219: astore 7
    //   221: aload_0
    //   222: aload 11
    //   224: putfield 79	cn/com/xy/sms/sdk/db/entity/a:b	Ljava/lang/String;
    //   227: aload 6
    //   229: astore 7
    //   231: aload_0
    //   232: aload 6
    //   234: iload_1
    //   235: invokevirtual 83	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   238: putfield 86	cn/com/xy/sms/sdk/db/entity/a:c	Ljava/lang/String;
    //   241: aload 6
    //   243: astore 7
    //   245: aload_0
    //   246: aload 6
    //   248: iload_2
    //   249: invokevirtual 83	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   252: putfield 89	cn/com/xy/sms/sdk/db/entity/a:e	Ljava/lang/String;
    //   255: aload 6
    //   257: astore 7
    //   259: aload_0
    //   260: aload 6
    //   262: iload_3
    //   263: invokevirtual 83	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   266: putfield 92	cn/com/xy/sms/sdk/db/entity/a:f	Ljava/lang/String;
    //   269: aload 6
    //   271: astore 7
    //   273: aload_0
    //   274: aload 6
    //   276: iload 4
    //   278: invokevirtual 96	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   281: putfield 100	cn/com/xy/sms/sdk/db/entity/a:g	J
    //   284: goto -109 -> 175
    //   287: astore_0
    //   288: aconst_null
    //   289: astore 6
    //   291: aload 6
    //   293: astore 7
    //   295: aload_0
    //   296: invokevirtual 103	java/lang/Throwable:printStackTrace	()V
    //   299: aload 6
    //   301: iconst_1
    //   302: invokestatic 72	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   305: aload 8
    //   307: areturn
    //   308: astore_0
    //   309: aconst_null
    //   310: astore 7
    //   312: aload 7
    //   314: iconst_1
    //   315: invokestatic 72	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   318: aload_0
    //   319: athrow
    //   320: astore_0
    //   321: goto -9 -> 312
    //   324: astore_0
    //   325: goto -34 -> 291
    //   328: astore 7
    //   330: aload_0
    //   331: astore 8
    //   333: aload 7
    //   335: astore_0
    //   336: goto -45 -> 291
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	339	0	paramString	String
    //   122	113	1	i	int
    //   138	111	2	j	int
    //   154	109	3	k	int
    //   170	107	4	m	int
    //   187	3	5	bool	boolean
    //   78	222	6	localXyCursor	cn.com.xy.sms.sdk.db.XyCursor
    //   1	312	7	localObject1	Object
    //   328	6	7	localThrowable	Throwable
    //   4	328	8	localObject2	Object
    //   10	163	9	localObject3	Object
    //   7	153	10	localObject4	Object
    //   16	207	11	str	String
    // Exception table:
    //   from	to	target	type
    //   12	80	287	java/lang/Throwable
    //   12	80	308	finally
    //   99	107	320	finally
    //   115	123	320	finally
    //   131	139	320	finally
    //   147	155	320	finally
    //   163	172	320	finally
    //   182	189	320	finally
    //   209	217	320	finally
    //   221	227	320	finally
    //   231	241	320	finally
    //   245	255	320	finally
    //   259	269	320	finally
    //   273	284	320	finally
    //   295	299	320	finally
    //   99	107	324	java/lang/Throwable
    //   115	123	324	java/lang/Throwable
    //   131	139	324	java/lang/Throwable
    //   147	155	324	java/lang/Throwable
    //   163	172	324	java/lang/Throwable
    //   182	189	324	java/lang/Throwable
    //   209	217	324	java/lang/Throwable
    //   221	227	328	java/lang/Throwable
    //   231	241	328	java/lang/Throwable
    //   245	255	328	java/lang/Throwable
    //   259	269	328	java/lang/Throwable
    //   273	284	328	java/lang/Throwable
  }
  
  public static void a(cn.com.xy.sms.sdk.db.entity.a parama)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("cnum", b);
      if (!StringUtils.isNull(c)) {
        localContentValues.put("areaCode", c);
      }
      if (!StringUtils.isNull(e)) {
        localContentValues.put("city", e);
      }
      if (!StringUtils.isNull(f)) {
        localContentValues.put("operator", f);
      }
      localContentValues.put("checkTime", Long.valueOf(g));
      if (DBManager.update("tb_centernum_location_info", localContentValues, "cnum = ?", new String[] { b }) < 1L) {
        DBManager.insert("tb_centernum_location_info", localContentValues);
      }
      return;
    }
    catch (Throwable parama)
    {
      parama.printStackTrace();
    }
  }
  
  private static ContentValues b(cn.com.xy.sms.sdk.db.entity.a parama)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("cnum", b);
    if (!StringUtils.isNull(c)) {
      localContentValues.put("areaCode", c);
    }
    if (!StringUtils.isNull(e)) {
      localContentValues.put("city", e);
    }
    if (!StringUtils.isNull(f)) {
      localContentValues.put("operator", f);
    }
    localContentValues.put("checkTime", Long.valueOf(g));
    return localContentValues;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.a.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */