package cn.com.xy.sms.sdk.db.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.iccid.IccidLocationUtil;
import cn.com.xy.sms.sdk.util.StringUtils;

public class IccidInfoManager
{
  public static final String ADD_SIM_INDEX = "ALTER TABLE tb_phone_info ADD COLUMN sim_index INTEGER DEFAULT -1 ";
  public static final String ADD_USER_AREACODE = "ALTER TABLE tb_phone_info ADD COLUMN user_areacode TEXT ";
  public static final String ADD_USER_OPERATOR = "ALTER TABLE tb_phone_info ADD COLUMN user_operator TEXT ";
  public static final String ADD_USER_PROVINCES = "ALTER TABLE tb_phone_info ADD COLUMN user_provinces TEXT ";
  public static final String AREACODE = "areacode";
  public static final String CITY = "city";
  public static final String CNUM = "cnum";
  public static final String CREATE_TABLE = "create table  if not exists tb_phone_info (id INTEGER PRIMARY KEY,iccid TEXT ,city TEXT,provinces TEXT,operator TEXT,areacode TEXT,ispost INTEGER DEFAULT 0,num TEXT,cnum TEXT,updateTime LONG,deft  INTEGER DEFAULT 0,net_updateTime LONG DEFAULT 0,user_provinces TEXT,user_areacode TEXT,user_operator TEXT,sim_index INTEGER DEFAULT -1)";
  public static final String DEFT = "deft";
  public static final String DROP_TABLE = " DROP TABLE IF EXISTS tb_phone_info";
  public static final String ICCID = "iccid";
  public static final String ID = "id";
  public static final String ISPOST = "ispost";
  public static final String NET_UPDATE_TIME = "net_updateTime";
  public static final String NUM = "num";
  public static final String OPERATOR = "operator";
  public static final String PROVINCES = "provinces";
  public static final String SIM_INDEX = "sim_index";
  public static final String TABLE_NAME = "tb_phone_info";
  public static final String UPDATE_TIME = "updateTime";
  public static final String USER_AREACODE = "user_areacode";
  public static final String USER_OPERATOR = "user_operator";
  public static final String USER_PROVINCES = "user_provinces";
  
  private static long a(SQLiteDatabase paramSQLiteDatabase, String paramString1, int paramInt, String paramString2, String paramString3, String paramString4)
  {
    long l1;
    for (;;)
    {
      try
      {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("iccid", paramString1);
        localContentValues.put("sim_index", Integer.valueOf(paramInt));
        if (!StringUtils.isNull(paramString2))
        {
          localContentValues.put("user_provinces", paramString2.trim());
          localContentValues.put("updateTime", Long.valueOf(System.currentTimeMillis()));
        }
        if (!StringUtils.isNull(paramString3))
        {
          localContentValues.put("user_areacode", paramString3.trim());
          if (!StringUtils.isNull(paramString4)) {
            localContentValues.put("user_operator", paramString4);
          }
          if (StringUtils.isNull(paramString1))
          {
            l1 = paramSQLiteDatabase.update("tb_phone_info", localContentValues, "sim_index = " + paramInt + " AND iccid" + " IS NULL", null);
            if (l1 >= 1L) {
              break;
            }
            return paramSQLiteDatabase.insert("tb_phone_info", null, localContentValues);
          }
        }
        else
        {
          localContentValues.put("user_areacode", getProviceCode(paramString2));
          continue;
        }
      }
      catch (Throwable paramSQLiteDatabase)
      {
        paramSQLiteDatabase.printStackTrace();
        return -1L;
      }
      tmp192_189[0] = paramString1;
      long l2 = paramSQLiteDatabase.update("tb_phone_info", localContentValues, "iccid = ?", tmp192_189);
      l1 = l2;
      if (l2 < 1L)
      {
        paramSQLiteDatabase.execSQL("UPDATE tb_phone_info SET sim_index= -1 WHERE sim_index=" + paramInt + " AND iccid" + " IS NOT NULL");
        l1 = l2;
      }
    }
    return l1;
  }
  
  private static boolean a(String paramString1, String paramString2)
  {
    return (paramString1 != null) && (paramString2 != null) && (paramString1.indexOf(paramString2) != -1);
  }
  
  public static int deleteIccidInfo(String paramString, int paramInt)
  {
    if ((paramString != null) && (paramString.length() > 0)) {}
    for (paramString = "iccid='" + paramString + "'";; paramString = "iccid IS NULL AND sim_index='" + paramInt + "'") {
      try
      {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("user_provinces", "");
        localContentValues.put("user_areacode", "");
        localContentValues.put("user_operator", "");
        paramInt = DBManager.update("tb_phone_info", localContentValues, paramString, null);
        return paramInt;
      }
      catch (Throwable paramString)
      {
        paramString.printStackTrace();
      }
    }
    return -1;
  }
  
  public static String getHead(IccidInfo paramIccidInfo)
  {
    if ((paramIccidInfo != null) && (isPost == 0) && (!StringUtils.isNull(iccid)) && (!StringUtils.isNull(num)) && (!StringUtils.isNull(cnum))) {
      return num + ";" + cnum + ";" + iccid;
    }
    return "";
  }
  
  public static String getProviceCode(String paramString)
  {
    String[][] arrayOfString = new String[34][];
    arrayOfString[0] = { "北京", "BJ" };
    arrayOfString[1] = { "上海", "SH" };
    arrayOfString[2] = { "天津", "TJ" };
    arrayOfString[3] = { "重庆", "CQ" };
    arrayOfString[4] = { "黑龙江", "HL" };
    arrayOfString[5] = { "吉林", "JL" };
    arrayOfString[6] = { "辽宁", "LN" };
    arrayOfString[7] = { "新疆", "XJ" };
    arrayOfString[8] = { "西藏", "XZ" };
    arrayOfString[9] = { "内蒙古", "NM" };
    arrayOfString[10] = { "甘肃", "GS" };
    arrayOfString[11] = { "青海", "QH" };
    arrayOfString[12] = { "陕西", "XA" };
    arrayOfString[13] = { "宁夏", "NX" };
    arrayOfString[14] = { "山西", "SX" };
    arrayOfString[15] = { "山东", "SD" };
    arrayOfString[16] = { "安徽", "AW" };
    arrayOfString[17] = { "河南", "HN" };
    arrayOfString[18] = { "河北", "HB" };
    arrayOfString[19] = { "浙江", "ZJ" };
    arrayOfString[20] = { "江苏", "JS" };
    arrayOfString[21] = { "湖南", "CS" };
    arrayOfString[22] = { "湖北", "WH" };
    arrayOfString[23] = { "贵州", "GZ" };
    arrayOfString[24] = { "四川", "SC" };
    arrayOfString[25] = { "江西", "JX" };
    arrayOfString[26] = { "云南", "YN" };
    arrayOfString[27] = { "广东", "GD" };
    arrayOfString[28] = { "广西", "GX" };
    arrayOfString[29] = { "福建", "FJ" };
    arrayOfString[30] = { "海南", "HK" };
    arrayOfString[31] = { "香港", "XG" };
    arrayOfString[32] = { "澳门", "OM" };
    arrayOfString[33] = { "台湾", "TW" };
    int i = 0;
    for (;;)
    {
      if (i >= 34) {
        return null;
      }
      String str = arrayOfString[i][0];
      if ((paramString != null) && (str != null) && (paramString.indexOf(str) != -1)) {}
      for (int j = 1; j != 0; j = 0) {
        return arrayOfString[i][1];
      }
      i += 1;
    }
  }
  
  public static long insertIccid(String paramString1, boolean paramBoolean, String paramString2, String paramString3, String paramString4, String paramString5, Context paramContext)
  {
    for (;;)
    {
      int i;
      long l2;
      long l1;
      try
      {
        paramContext = new ContentValues();
        paramContext.put("iccid", paramString1);
        if ((!StringUtils.isNull(paramString2)) && (paramString2.indexOf(";") == -1))
        {
          if (!StringUtils.isNull(paramString2))
          {
            paramContext.put("provinces", paramString2.trim());
            paramContext.put("updateTime", Long.valueOf(System.currentTimeMillis()));
          }
          if (!StringUtils.isNull(paramString3))
          {
            paramContext.put("areacode", paramString3.trim());
            if (!StringUtils.isNull(paramString4)) {
              paramContext.put("city", paramString4.trim());
            }
            if (!StringUtils.isNull(paramString5)) {
              paramContext.put("operator", paramString5);
            }
            if (!paramBoolean) {
              break label394;
            }
            paramString2 = new ContentValues();
            paramString2.put("deft", Integer.valueOf(0));
            DBManager.update("tb_phone_info", paramString2, null, null);
            break label394;
            paramContext.put("deft", Integer.valueOf(i));
            i = DBManager.update("tb_phone_info", paramContext, "iccid = ?", new String[] { paramString1 });
            l2 = i;
            if (l2 >= 1L) {
              break label343;
            }
            l1 = l2;
          }
        }
      }
      catch (Throwable paramString1)
      {
        label233:
        l1 = -1L;
        paramString1.printStackTrace();
        return l1;
      }
      try
      {
        l2 = DBManager.insert("tb_phone_info", paramContext);
        l1 = l2;
        IccidLocationUtil.putIccidAreaCodeToCache(paramString1, paramContext.getAsString("areacode"), paramString5, null, null);
        l1 = l2;
        return l1;
      }
      catch (Throwable paramString1)
      {
        continue;
      }
      paramContext.put("areacode", getProviceCode(paramString2));
      continue;
      if (!StringUtils.isNull(paramString2))
      {
        paramString2 = paramString2.split(";");
        if (paramString2.length > 0)
        {
          paramContext.put("provinces", paramString2[0]);
          paramContext.put("updateTime", Long.valueOf(System.currentTimeMillis()));
          paramContext.put("areacode", getProviceCode(paramString2[0]));
        }
        if (paramString2.length >= 2)
        {
          paramContext.put("city", paramString2[1]);
          continue;
          label343:
          label394:
          while (!paramBoolean)
          {
            i = 0;
            break;
            l1 = l2;
            paramString2 = queryIccidInfo(paramString1, Constant.getContext());
            l1 = l2;
            if (paramString2 == null) {
              break label233;
            }
            l1 = l2;
            IccidLocationUtil.putIccidAreaCodeToCache(paramString1, areaCode, operator, userAreacode, userOperator);
            return l2;
          }
          i = 1;
        }
      }
    }
  }
  
  /* Error */
  public static boolean insertOrUpdateIccid(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4, boolean paramBoolean)
  {
    // Byte code:
    //   0: iload_1
    //   1: iflt +25 -> 26
    //   4: aload_2
    //   5: invokestatic 105	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   8: ifne +18 -> 26
    //   11: aload_3
    //   12: invokestatic 105	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   15: ifne +11 -> 26
    //   18: aload 4
    //   20: invokestatic 105	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   23: ifeq +5 -> 28
    //   26: iconst_0
    //   27: ireturn
    //   28: aconst_null
    //   29: astore 8
    //   31: aconst_null
    //   32: astore 10
    //   34: invokestatic 398	cn/com/xy/sms/sdk/db/DBManager:getSQLiteDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   37: astore 9
    //   39: aload 9
    //   41: invokevirtual 401	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   44: aload 9
    //   46: aload_0
    //   47: iload_1
    //   48: aload_2
    //   49: aload_3
    //   50: aload 4
    //   52: invokestatic 403	cn/com/xy/sms/sdk/db/entity/IccidInfoManager:a	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)J
    //   55: lstore 6
    //   57: lload 6
    //   59: lconst_1
    //   60: lcmp
    //   61: ifge +49 -> 110
    //   64: aload 9
    //   66: ifnull +21 -> 87
    //   69: aload 9
    //   71: invokevirtual 407	android/database/sqlite/SQLiteDatabase:inTransaction	()Z
    //   74: ifeq +8 -> 82
    //   77: aload 9
    //   79: invokevirtual 410	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   82: aload 9
    //   84: invokestatic 414	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   87: iconst_0
    //   88: ireturn
    //   89: astore_0
    //   90: aload_0
    //   91: invokevirtual 163	java/lang/Throwable:printStackTrace	()V
    //   94: aload 9
    //   96: invokestatic 414	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   99: goto -12 -> 87
    //   102: astore_0
    //   103: aload 9
    //   105: invokestatic 414	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   108: aload_0
    //   109: athrow
    //   110: aload 9
    //   112: invokevirtual 417	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   115: aload 9
    //   117: ifnull +21 -> 138
    //   120: aload 9
    //   122: invokevirtual 407	android/database/sqlite/SQLiteDatabase:inTransaction	()Z
    //   125: ifeq +8 -> 133
    //   128: aload 9
    //   130: invokevirtual 410	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   133: aload 9
    //   135: invokestatic 414	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   138: iconst_1
    //   139: ireturn
    //   140: astore_0
    //   141: aload_0
    //   142: invokevirtual 163	java/lang/Throwable:printStackTrace	()V
    //   145: aload 9
    //   147: invokestatic 414	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   150: goto -12 -> 138
    //   153: astore_0
    //   154: aload 9
    //   156: invokestatic 414	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   159: aload_0
    //   160: athrow
    //   161: astore_2
    //   162: aload 10
    //   164: astore_0
    //   165: aload_0
    //   166: astore 8
    //   168: aload_2
    //   169: invokevirtual 163	java/lang/Throwable:printStackTrace	()V
    //   172: aload_0
    //   173: ifnull +18 -> 191
    //   176: aload_0
    //   177: invokevirtual 407	android/database/sqlite/SQLiteDatabase:inTransaction	()Z
    //   180: ifeq +7 -> 187
    //   183: aload_0
    //   184: invokevirtual 410	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   187: aload_0
    //   188: invokestatic 414	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   191: iconst_0
    //   192: ireturn
    //   193: astore_2
    //   194: aload_2
    //   195: invokevirtual 163	java/lang/Throwable:printStackTrace	()V
    //   198: aload_0
    //   199: invokestatic 414	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   202: goto -11 -> 191
    //   205: astore_2
    //   206: aload_0
    //   207: invokestatic 414	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   210: aload_2
    //   211: athrow
    //   212: astore_0
    //   213: aload 8
    //   215: ifnull +21 -> 236
    //   218: aload 8
    //   220: invokevirtual 407	android/database/sqlite/SQLiteDatabase:inTransaction	()Z
    //   223: ifeq +8 -> 231
    //   226: aload 8
    //   228: invokevirtual 410	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   231: aload 8
    //   233: invokestatic 414	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   236: aload_0
    //   237: athrow
    //   238: astore_2
    //   239: aload_2
    //   240: invokevirtual 163	java/lang/Throwable:printStackTrace	()V
    //   243: aload 8
    //   245: invokestatic 414	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   248: goto -12 -> 236
    //   251: astore_0
    //   252: aload 8
    //   254: invokestatic 414	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   257: aload_0
    //   258: athrow
    //   259: astore_0
    //   260: aload 9
    //   262: astore 8
    //   264: goto -51 -> 213
    //   267: astore_2
    //   268: aload 9
    //   270: astore_0
    //   271: goto -106 -> 165
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	274	0	paramString1	String
    //   0	274	1	paramInt	int
    //   0	274	2	paramString2	String
    //   0	274	3	paramString3	String
    //   0	274	4	paramString4	String
    //   0	274	5	paramBoolean	boolean
    //   55	3	6	l	long
    //   29	234	8	localObject1	Object
    //   37	232	9	localSQLiteDatabase	SQLiteDatabase
    //   32	131	10	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   69	82	89	java/lang/Throwable
    //   69	82	102	finally
    //   90	94	102	finally
    //   120	133	140	java/lang/Throwable
    //   120	133	153	finally
    //   141	145	153	finally
    //   34	39	161	java/lang/Throwable
    //   176	187	193	java/lang/Throwable
    //   176	187	205	finally
    //   194	198	205	finally
    //   34	39	212	finally
    //   168	172	212	finally
    //   218	231	238	java/lang/Throwable
    //   218	231	251	finally
    //   239	243	251	finally
    //   39	57	259	finally
    //   110	115	259	finally
    //   39	57	267	java/lang/Throwable
    //   110	115	267	java/lang/Throwable
  }
  
  public static IccidInfo queryDeftIccidInfo(Context paramContext)
  {
    return queryIccidInfo(null, paramContext);
  }
  
  /* Error */
  public static IccidInfo queryIccidInfo(String paramString, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +377 -> 378
    //   4: aload_0
    //   5: invokevirtual 185	java/lang/String:length	()I
    //   8: ifle +370 -> 378
    //   11: new 127	java/lang/StringBuilder
    //   14: dup
    //   15: ldc -69
    //   17: invokespecial 132	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   20: aload_0
    //   21: invokevirtual 141	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: ldc -67
    //   26: invokevirtual 141	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: invokevirtual 146	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   32: astore_0
    //   33: ldc 62
    //   35: bipush 14
    //   37: anewarray 107	java/lang/String
    //   40: dup
    //   41: iconst_0
    //   42: ldc 23
    //   44: aastore
    //   45: dup
    //   46: iconst_1
    //   47: ldc 56
    //   49: aastore
    //   50: dup
    //   51: iconst_2
    //   52: ldc 65
    //   54: aastore
    //   55: dup
    //   56: iconst_3
    //   57: ldc 53
    //   59: aastore
    //   60: dup
    //   61: iconst_4
    //   62: ldc 44
    //   64: aastore
    //   65: dup
    //   66: iconst_5
    //   67: ldc 50
    //   69: aastore
    //   70: dup
    //   71: bipush 6
    //   73: ldc 26
    //   75: aastore
    //   76: dup
    //   77: bipush 7
    //   79: ldc 47
    //   81: aastore
    //   82: dup
    //   83: bipush 8
    //   85: ldc 20
    //   87: aastore
    //   88: dup
    //   89: bipush 9
    //   91: ldc 38
    //   93: aastore
    //   94: dup
    //   95: bipush 10
    //   97: ldc 74
    //   99: aastore
    //   100: dup
    //   101: bipush 11
    //   103: ldc 68
    //   105: aastore
    //   106: dup
    //   107: bipush 12
    //   109: ldc 71
    //   111: aastore
    //   112: dup
    //   113: bipush 13
    //   115: ldc 59
    //   117: aastore
    //   118: aload_0
    //   119: aconst_null
    //   120: invokestatic 424	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   123: astore_0
    //   124: aload_0
    //   125: ifnull +335 -> 460
    //   128: aload_0
    //   129: astore_2
    //   130: aload_0
    //   131: invokevirtual 429	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   134: ifeq +326 -> 460
    //   137: aload_0
    //   138: astore_2
    //   139: new 200	cn/com/xy/sms/sdk/db/entity/IccidInfo
    //   142: dup
    //   143: invokespecial 430	cn/com/xy/sms/sdk/db/entity/IccidInfo:<init>	()V
    //   146: astore_3
    //   147: aload_0
    //   148: astore_2
    //   149: aload_3
    //   150: aload_0
    //   151: aload_0
    //   152: ldc 23
    //   154: invokevirtual 433	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   157: invokevirtual 437	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   160: putfield 439	cn/com/xy/sms/sdk/db/entity/IccidInfo:city	Ljava/lang/String;
    //   163: aload_0
    //   164: astore_2
    //   165: aload_3
    //   166: aload_0
    //   167: aload_0
    //   168: ldc 53
    //   170: invokevirtual 433	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   173: invokevirtual 437	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   176: putfield 386	cn/com/xy/sms/sdk/db/entity/IccidInfo:operator	Ljava/lang/String;
    //   179: aload_0
    //   180: astore_2
    //   181: aload_3
    //   182: aload_0
    //   183: aload_0
    //   184: ldc 20
    //   186: invokevirtual 433	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   189: invokevirtual 437	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   192: putfield 384	cn/com/xy/sms/sdk/db/entity/IccidInfo:areaCode	Ljava/lang/String;
    //   195: aload_0
    //   196: astore_2
    //   197: aload_3
    //   198: aload_0
    //   199: aload_0
    //   200: ldc 56
    //   202: invokevirtual 433	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   205: invokevirtual 437	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   208: putfield 441	cn/com/xy/sms/sdk/db/entity/IccidInfo:provinces	Ljava/lang/String;
    //   211: aload_0
    //   212: astore_2
    //   213: aload_3
    //   214: aload_0
    //   215: aload_0
    //   216: ldc 65
    //   218: invokevirtual 433	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   221: invokevirtual 445	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   224: putfield 448	cn/com/xy/sms/sdk/db/entity/IccidInfo:updateTime	J
    //   227: aload_0
    //   228: astore_2
    //   229: aload_3
    //   230: aload_0
    //   231: aload_0
    //   232: ldc 38
    //   234: invokevirtual 433	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   237: invokevirtual 437	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   240: putfield 206	cn/com/xy/sms/sdk/db/entity/IccidInfo:iccid	Ljava/lang/String;
    //   243: aload_0
    //   244: astore_2
    //   245: aload_3
    //   246: aload_0
    //   247: aload_0
    //   248: ldc 44
    //   250: invokevirtual 433	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   253: invokevirtual 452	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   256: putfield 204	cn/com/xy/sms/sdk/db/entity/IccidInfo:isPost	I
    //   259: aload_0
    //   260: astore_2
    //   261: aload_3
    //   262: aload_0
    //   263: aload_0
    //   264: ldc 50
    //   266: invokevirtual 433	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   269: invokevirtual 437	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   272: putfield 208	cn/com/xy/sms/sdk/db/entity/IccidInfo:num	Ljava/lang/String;
    //   275: aload_0
    //   276: astore_2
    //   277: aload_3
    //   278: aload_0
    //   279: aload_0
    //   280: ldc 26
    //   282: invokevirtual 433	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   285: invokevirtual 437	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   288: putfield 210	cn/com/xy/sms/sdk/db/entity/IccidInfo:cnum	Ljava/lang/String;
    //   291: aload_0
    //   292: astore_2
    //   293: aload_3
    //   294: aload_0
    //   295: aload_0
    //   296: ldc 47
    //   298: invokevirtual 433	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   301: invokevirtual 445	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   304: putfield 455	cn/com/xy/sms/sdk/db/entity/IccidInfo:netUpdateTime	J
    //   307: aload_0
    //   308: astore_2
    //   309: aload_3
    //   310: aload_0
    //   311: aload_0
    //   312: ldc 74
    //   314: invokevirtual 433	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   317: invokevirtual 437	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   320: putfield 458	cn/com/xy/sms/sdk/db/entity/IccidInfo:userProvinces	Ljava/lang/String;
    //   323: aload_0
    //   324: astore_2
    //   325: aload_3
    //   326: aload_0
    //   327: aload_0
    //   328: ldc 68
    //   330: invokevirtual 433	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   333: invokevirtual 437	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   336: putfield 389	cn/com/xy/sms/sdk/db/entity/IccidInfo:userAreacode	Ljava/lang/String;
    //   339: aload_0
    //   340: astore_2
    //   341: aload_3
    //   342: aload_0
    //   343: aload_0
    //   344: ldc 71
    //   346: invokevirtual 433	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   349: invokevirtual 437	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   352: putfield 392	cn/com/xy/sms/sdk/db/entity/IccidInfo:userOperator	Ljava/lang/String;
    //   355: aload_0
    //   356: astore_2
    //   357: aload_3
    //   358: aload_0
    //   359: aload_0
    //   360: ldc 59
    //   362: invokevirtual 433	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   365: invokevirtual 452	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   368: putfield 461	cn/com/xy/sms/sdk/db/entity/IccidInfo:simIndex	I
    //   371: aload_0
    //   372: iconst_1
    //   373: invokestatic 465	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   376: aload_3
    //   377: areturn
    //   378: iload_1
    //   379: iflt +28 -> 407
    //   382: new 127	java/lang/StringBuilder
    //   385: dup
    //   386: ldc -60
    //   388: invokespecial 132	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   391: iload_1
    //   392: invokevirtual 136	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   395: ldc -67
    //   397: invokevirtual 141	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   400: invokevirtual 146	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   403: astore_0
    //   404: goto -371 -> 33
    //   407: ldc_w 467
    //   410: astore_0
    //   411: goto -378 -> 33
    //   414: astore 4
    //   416: aconst_null
    //   417: astore_0
    //   418: aconst_null
    //   419: astore_3
    //   420: aload_0
    //   421: astore_2
    //   422: aload 4
    //   424: invokevirtual 163	java/lang/Throwable:printStackTrace	()V
    //   427: aload_0
    //   428: iconst_1
    //   429: invokestatic 465	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   432: aload_3
    //   433: areturn
    //   434: astore_0
    //   435: aconst_null
    //   436: astore_2
    //   437: aload_2
    //   438: iconst_1
    //   439: invokestatic 465	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   442: aload_0
    //   443: athrow
    //   444: astore_0
    //   445: goto -8 -> 437
    //   448: astore 4
    //   450: aconst_null
    //   451: astore_3
    //   452: goto -32 -> 420
    //   455: astore 4
    //   457: goto -37 -> 420
    //   460: aconst_null
    //   461: astore_3
    //   462: goto -91 -> 371
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	465	0	paramString	String
    //   0	465	1	paramInt	int
    //   129	309	2	str	String
    //   146	316	3	localIccidInfo	IccidInfo
    //   414	9	4	localThrowable1	Throwable
    //   448	1	4	localThrowable2	Throwable
    //   455	1	4	localThrowable3	Throwable
    // Exception table:
    //   from	to	target	type
    //   4	33	414	java/lang/Throwable
    //   33	124	414	java/lang/Throwable
    //   382	404	414	java/lang/Throwable
    //   4	33	434	finally
    //   33	124	434	finally
    //   382	404	434	finally
    //   130	137	444	finally
    //   139	147	444	finally
    //   149	163	444	finally
    //   165	179	444	finally
    //   181	195	444	finally
    //   197	211	444	finally
    //   213	227	444	finally
    //   229	243	444	finally
    //   245	259	444	finally
    //   261	275	444	finally
    //   277	291	444	finally
    //   293	307	444	finally
    //   309	323	444	finally
    //   325	339	444	finally
    //   341	355	444	finally
    //   357	371	444	finally
    //   422	427	444	finally
    //   130	137	448	java/lang/Throwable
    //   139	147	448	java/lang/Throwable
    //   149	163	455	java/lang/Throwable
    //   165	179	455	java/lang/Throwable
    //   181	195	455	java/lang/Throwable
    //   197	211	455	java/lang/Throwable
    //   213	227	455	java/lang/Throwable
    //   229	243	455	java/lang/Throwable
    //   245	259	455	java/lang/Throwable
    //   261	275	455	java/lang/Throwable
    //   277	291	455	java/lang/Throwable
    //   293	307	455	java/lang/Throwable
    //   309	323	455	java/lang/Throwable
    //   325	339	455	java/lang/Throwable
    //   341	355	455	java/lang/Throwable
    //   357	371	455	java/lang/Throwable
  }
  
  public static IccidInfo queryIccidInfo(String paramString, Context paramContext)
  {
    return queryIccidInfo(paramString, -1);
  }
  
  public static void updateIccidCnum(String paramString1, String paramString2, String paramString3, Context paramContext)
  {
    try
    {
      if ((!StringUtils.isNull(paramString1)) && (!StringUtils.isNull(paramString2)) && (!StringUtils.isNull(paramString3)) && ((paramString3.equals("10086")) || (paramString3.equals("10010")) || (paramString3.equals("10000"))))
      {
        paramContext = new ContentValues();
        paramContext.put("num", paramString3);
        paramContext.put("cnum", paramString2);
        DBManager.update("tb_phone_info", paramContext, "iccid = ? and ispost = 0", new String[] { paramString1 });
      }
      return;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.IccidInfoManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */