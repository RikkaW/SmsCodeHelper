package cn.com.xy.sms.sdk.db.entity;

import android.content.ContentValues;
import android.content.Context;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.ParseItemManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.queue.g;
import cn.com.xy.sms.sdk.queue.i;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.d;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SysParamEntityManager
{
  public static final String CREATE_TABLE = "create table  if not exists tb_sdk_param (id int primary key,p_key TEXT,p_value TEXT,pextend_value TEXT)";
  public static final String DROP_TABLE = " DROP TABLE IF EXISTS tb_sdk_param";
  public static final String ID = "id";
  public static final String PEXTENDVALUE = "pextend_value";
  public static final String PKEY = "p_key";
  public static final String PVALUE = "p_value";
  public static final String TABLE_NAME = "tb_sdk_param";
  public static HashMap<String, Object> cacheMap = new HashMap();
  
  public static void clearOldData(String paramString)
  {
    try
    {
      String str = queryValueParamKey(Constant.getContext(), "CHANNEL");
      if ((!StringUtils.isNull(str)) && (!StringUtils.isNull(paramString)) && (!str.equals(paramString))) {
        clearOldData(true);
      }
      return;
    }
    catch (Throwable paramString) {}
  }
  
  public static void clearOldData(boolean paramBoolean)
  {
    if (paramBoolean) {}
    try
    {
      DBManager.delete("tb_scene_config", null, null);
    }
    catch (Throwable localThrowable3)
    {
      try
      {
        DBManager.delete("tb_scenerule_config", null, null);
      }
      catch (Throwable localThrowable3)
      {
        try
        {
          DBManager.delete("tb_res_download", null, null);
        }
        catch (Throwable localThrowable3)
        {
          try
          {
            for (;;)
            {
              DBManager.delete("tb_xml_res_download", null, null);
              try
              {
                e.c();
                d.d(Constant.getPARSE_PATH());
                d.a(Constant.getContext().getDir("outdex", 0));
                ParseItemManager.deleteAll();
                return;
              }
              catch (Throwable localThrowable5) {}
              localThrowable1 = localThrowable1;
              localThrowable1.printStackTrace();
              continue;
              localThrowable2 = localThrowable2;
              localThrowable2.printStackTrace();
              continue;
              localThrowable3 = localThrowable3;
              localThrowable3.printStackTrace();
            }
          }
          catch (Throwable localThrowable4)
          {
            for (;;)
            {
              localThrowable4.printStackTrace();
            }
          }
        }
      }
    }
  }
  
  public static void deleteOldFile()
  {
    try
    {
      String str = Constant.getContext().getFilesDir().getPath() + File.separator + "parse" + File.separator;
      if (d.a(str))
      {
        d.a(str, "ParseUtilCasual_", ".jar", null);
        d.a(str, "ParseUtilEC_", ".jar", null);
        d.a(str, "ParseUtilFinanceL_", ".jar", null);
        d.a(str, "ParseUtilFinanceM_", ".jar", null);
        d.a(str, "ParseUtilFinanceS_", ".jar", null);
        d.a(str, "ParseUtilLife_", ".jar", null);
        d.a(str, "ParseUtilMove_", ".jar", null);
        d.a(str, "ParseUtilTelecom_", ".jar", null);
        d.a(str, "ParseUtilTravel_", ".jar", null);
        d.a(str, "ParseUtilUnicom_", ".jar", null);
      }
      if (d.a(Constant.getContext().getDir("outdex", 0).getPath()))
      {
        d.b("ParseUtilCasual_", ".dex", null);
        d.b("ParseUtilEC_", ".dex", null);
        d.b("ParseUtilFinanceL_", ".dex", null);
        d.b("ParseUtilFinanceM_", ".dex", null);
        d.b("ParseUtilFinanceS_", ".dex", null);
        d.b("ParseUtilLife_", ".dex", null);
        d.b("ParseUtilMove_", ".dex", null);
        d.b("ParseUtilTelecom_", ".dex", null);
        d.b("ParseUtilTravel_", ".dex", null);
        d.b("ParseUtilUnicom_", ".dex", null);
      }
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  public static boolean getBooleanParam(Context paramContext, String paramString)
  {
    return getBooleanParam(paramContext, paramString, false);
  }
  
  public static boolean getBooleanParam(Context paramContext, String paramString, boolean paramBoolean)
  {
    Object localObject = cacheMap.get(paramString);
    if (localObject == null) {}
    for (;;)
    {
      try
      {
        paramContext = queryValueParamKey(paramContext, paramString);
        if (paramContext == null) {
          cacheMap.put(paramString, Boolean.valueOf(paramBoolean));
        }
      }
      catch (Throwable paramContext)
      {
        paramBoolean = false;
        paramContext.printStackTrace();
        return paramBoolean;
      }
      try
      {
        cacheMap.put(paramString, Boolean.valueOf(paramBoolean));
        return paramBoolean;
      }
      catch (Throwable paramContext)
      {
        continue;
        paramBoolean = true;
      }
      if ("false".equalsIgnoreCase(paramContext))
      {
        paramBoolean = false;
        continue;
        paramBoolean = Boolean.parseBoolean(localObject.toString());
        return paramBoolean;
      }
    }
  }
  
  public static int getIntParam(Context paramContext, String paramString)
  {
    Object localObject = cacheMap.get(paramString);
    if (localObject == null) {}
    try
    {
      paramContext = queryValueParamKey(paramContext, paramString);
      if (paramContext == null) {
        break label54;
      }
      cacheMap.put(paramString, paramContext);
      return Integer.valueOf(paramContext).intValue();
    }
    catch (Throwable paramContext)
    {
      int i;
      paramContext.printStackTrace();
    }
    i = Integer.parseInt((String)localObject);
    return i;
    label54:
    return -1;
  }
  
  public static long getLongParam(String paramString, long paramLong, Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1 = null;
    XyCursor localXyCursor;
    try
    {
      localXyCursor = DBManager.query("tb_sdk_param", new String[] { "p_value" }, "p_key=?", new String[] { paramString });
      if (localXyCursor != null)
      {
        localObject1 = localXyCursor;
        localObject2 = localXyCursor;
        if (localXyCursor.moveToNext())
        {
          localObject1 = localXyCursor;
          localObject2 = localXyCursor;
          paramString = localXyCursor.getString(localXyCursor.getColumnIndex("p_value"));
          localObject1 = localXyCursor;
          localObject2 = localXyCursor;
          if (StringUtils.isNull(paramString)) {
            break label180;
          }
          localObject1 = localXyCursor;
          localObject2 = localXyCursor;
          long l = Long.valueOf(paramString).longValue();
          XyCursor.closeCursor(localXyCursor, true);
          return l;
        }
      }
      if (paramContext != null)
      {
        localObject1 = localXyCursor;
        localObject2 = localXyCursor;
        setParam(paramString, paramLong);
        XyCursor.closeCursor(localXyCursor, true);
        return paramLong;
      }
    }
    catch (Throwable paramString)
    {
      localObject2 = localObject1;
      paramString.printStackTrace();
      return paramLong;
    }
    finally
    {
      XyCursor.closeCursor((XyCursor)localObject2, true);
    }
    label180:
    XyCursor.closeCursor(localXyCursor, true);
    return paramLong;
  }
  
  public static String getStringParam(Context paramContext, String paramString)
  {
    Object localObject = cacheMap.get(paramString);
    if (localObject == null) {}
    try
    {
      paramContext = queryValueParamKey(paramContext, paramString);
      if (paramContext == null) {}
    }
    catch (Throwable paramString)
    {
      paramContext = null;
    }
    try
    {
      cacheMap.put(paramString, paramContext);
      return paramContext;
    }
    catch (Throwable paramString)
    {
      for (;;) {}
    }
    paramContext = (String)localObject;
    return paramContext;
    paramString.printStackTrace();
    return paramContext;
  }
  
  public static void initParams(Context paramContext, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, Map<String, String> paramMap)
  {
    clearOldData(paramString1);
    String str1 = queryValueParamKey(paramContext, "smartsms_enhance");
    if (str1 == null)
    {
      if (paramMap != null) {
        str1 = (String)paramMap.get("smartsms_enhance");
      }
      str2 = str1;
      if (str1 == null) {
        str2 = "true";
      }
      insertOrUpdateKeyValue(paramContext, "smartsms_enhance", str2, null);
      cacheMap.put("smartsms_enhance", str2);
    }
    str1 = queryValueParamKey(paramContext, "SUPPORT_NETWORK_TYPE");
    if (str1 == null)
    {
      if (paramMap != null) {
        str1 = (String)paramMap.get("SUPPORT_NETWORK_TYPE");
      }
      str2 = str1;
      if (str1 == null) {
        str2 = "1";
      }
      insertOrUpdateKeyValue(paramContext, "SUPPORT_NETWORK_TYPE", str2, null);
      cacheMap.put("SUPPORT_NETWORK_TYPE", str2);
    }
    str1 = queryValueParamKey(paramContext, "ONLINE_UPDATE_SDK_PERIOD");
    if (str1 == null)
    {
      if (paramMap != null) {
        str1 = (String)paramMap.get("ONLINE_UPDATE_SDK_PERIOD");
      }
      str2 = str1;
      if (str1 == null) {
        str2 = "1";
      }
      insertOrUpdateKeyValue(paramContext, "ONLINE_UPDATE_SDK_PERIOD", str2, null);
      cacheMap.put("ONLINE_UPDATE_SDK_PERIOD", str2);
    }
    insertOrUpdateKeyValue(paramContext, "PRELOADENABLE", paramBoolean1, null);
    insertOrUpdateKeyValue(paramContext, "SMSLOCATEENABLE", paramBoolean2, null);
    insertOrUpdateKeyValue(paramContext, "CHANNEL", paramString1, null);
    cacheMap.put("PRELOADENABLE", Boolean.valueOf(paramBoolean1));
    cacheMap.put("SMSLOCATEENABLE", Boolean.valueOf(paramBoolean2));
    cacheMap.put("CHANNEL", paramString1);
    String str15 = null;
    String str14 = null;
    String str13 = null;
    String str12 = null;
    String str10 = null;
    String str9 = null;
    String str7 = null;
    String str6 = null;
    String str4 = null;
    String str5 = null;
    String str3 = null;
    String str2 = null;
    String str8 = null;
    String str11 = null;
    str1 = null;
    paramString1 = null;
    if (paramMap != null)
    {
      paramString1 = (String)paramMap.get("SIM_ICCID_2");
      str1 = (String)paramMap.get("SMS_LOCATE_2");
      if (paramString1 != null)
      {
        IccidInfoManager.insertIccid(paramString1, false, str1, "", "", "", paramContext);
        g.a(new i(1, new String[] { "simIccid", paramString1, "smsLocate", str1 }));
      }
      str14 = (String)paramMap.get("CUSTOM_LOCATION_SERVER_URL");
      str13 = (String)paramMap.get("CUSTOM_PUBLIC_SERVER_URL");
      str12 = (String)paramMap.get("CUSTOM_SDK_SERVER_URL");
      str10 = (String)paramMap.get("ONLINE_UPDATE_SDK");
      str9 = (String)paramMap.get("QUERY_ONLINE");
      str15 = (String)paramMap.get("SMS_LOCATE");
      str7 = (String)paramMap.get("RECOGNIZE_LEVEL");
      paramString1 = (String)paramMap.get("SECRETKEY");
      str6 = (String)paramMap.get("OPEN_POPUP_DRAG");
      str5 = (String)paramMap.get("AUTO_UPDATE_DATA");
      cacheMap.put("SECRETKEY", paramString1);
      str4 = (String)paramMap.get("POPUP_BG_TYPE");
      str8 = (String)paramMap.get("SCENE_CENSUS_ONLINE");
      str11 = (String)paramMap.get("CUSTOM_SDK_RES_DOWNLAOD_URL");
      str3 = (String)paramMap.get("SUPPORT_NETWORK_TYPE_MAJOR");
      str2 = (String)paramMap.get("ONLINE_UPDATE_RES_PERIOD");
      str1 = (String)paramMap.get("REPARSE_BUBBLE_CYCLE");
      paramString1 = (String)paramMap.get("COMPARE_PUBNUM_OPERATOR");
    }
    paramMap = str14;
    if (str14 == null) {
      paramMap = "";
    }
    insertOrUpdateKeyValue(paramContext, "CUSTOM_LOCATION_SERVER_URL", paramMap, null);
    cacheMap.put("CUSTOM_LOCATION_SERVER_URL", paramMap);
    if (str13 == null) {}
    for (paramMap = "";; paramMap = str13)
    {
      if (!StringUtils.isNull(paramMap))
      {
        if (NetUtil.isUseHttps()) {
          NetUtil.PUBINFO_SERVER_URL_HTTPS = paramMap;
        }
      }
      else
      {
        insertOrUpdateKeyValue(paramContext, "CUSTOM_PUBLIC_SERVER_URL", paramMap, null);
        cacheMap.put("CUSTOM_PUBLIC_SERVER_URL", paramMap);
        if (str12 != null) {
          break label1421;
        }
      }
      label869:
      label1414:
      label1421:
      for (paramMap = "";; paramMap = str12)
      {
        insertOrUpdateKeyValue(paramContext, "CUSTOM_SDK_SERVER_URL", paramMap, null);
        cacheMap.put("CUSTOM_SDK_SERVER_URL", paramMap);
        if (!StringUtils.isNull(paramMap))
        {
          if (NetUtil.isUseHttps()) {
            NetUtil.POPUP_SERVER_URL_HTTPS = paramMap;
          }
        }
        else {
          if (str11 != null) {
            break label1414;
          }
        }
        for (paramMap = "";; paramMap = str11)
        {
          insertOrUpdateKeyValue(paramContext, "CUSTOM_SDK_RES_DOWNLAOD_URL", paramMap, null);
          cacheMap.put("CUSTOM_SDK_RES_DOWNLAOD_URL", paramMap);
          if (!StringUtils.isNull(paramMap)) {
            NetUtil.prex = paramMap;
          }
          if (str10 == null) {}
          for (paramMap = "1";; paramMap = str10)
          {
            insertOrUpdateKeyValue(paramContext, "ONLINE_UPDATE_SDK", paramMap, null);
            cacheMap.put("ONLINE_UPDATE_SDK", paramMap);
            if (str9 == null) {}
            for (paramMap = "1";; paramMap = str9)
            {
              insertOrUpdateKeyValue(paramContext, "QUERY_ONLINE", paramMap, null);
              cacheMap.put("QUERY_ONLINE", paramMap);
              if (str8 == null) {}
              for (paramMap = "0";; paramMap = str8)
              {
                insertOrUpdateKeyValue(paramContext, "SCENE_CENSUS_ONLINE", paramMap, null);
                cacheMap.put("SCENE_CENSUS_ONLINE", paramMap);
                if (str7 == null) {}
                for (paramMap = "3";; paramMap = str7)
                {
                  insertOrUpdateKeyValue(paramContext, "RECOGNIZE_LEVEL", paramMap, null);
                  cacheMap.put("RECOGNIZE_LEVEL", paramMap);
                  if (str6 == null) {}
                  for (paramMap = "0";; paramMap = str6)
                  {
                    cacheMap.put("OPEN_POPUP_DRAG", paramMap);
                    if (str5 == null) {}
                    for (paramMap = "0";; paramMap = str5)
                    {
                      insertOrUpdateKeyValue(paramContext, "AUTO_UPDATE_DATA", paramMap, null);
                      cacheMap.put("AUTO_UPDATE_DATA", paramMap);
                      if (str4 == null) {}
                      for (paramMap = "1";; paramMap = str4)
                      {
                        insertOrUpdateKeyValue(paramContext, "POPUP_BG_TYPE", paramMap, null);
                        cacheMap.put("POPUP_BG_TYPE", paramMap);
                        if (str3 == null) {}
                        for (paramMap = "2";; paramMap = str3)
                        {
                          insertOrUpdateKeyValue(paramContext, "SUPPORT_NETWORK_TYPE_MAJOR", paramMap, null);
                          cacheMap.put("SUPPORT_NETWORK_TYPE_MAJOR", paramMap);
                          if (str2 == null) {}
                          for (paramMap = "2";; paramMap = str2)
                          {
                            insertOrUpdateKeyValue(paramContext, "ONLINE_UPDATE_RES_PERIOD", paramMap, null);
                            cacheMap.put("ONLINE_UPDATE_RES_PERIOD", paramMap);
                            if (str1 == null) {}
                            for (paramMap = "-1";; paramMap = str1)
                            {
                              insertOrUpdateKeyValue(paramContext, "REPARSE_BUBBLE_CYCLE", paramMap, null);
                              cacheMap.put("REPARSE_BUBBLE_CYCLE", paramMap);
                              if (paramString1 == null) {
                                paramString1 = "true";
                              }
                              for (;;)
                              {
                                insertOrUpdateKeyValue(paramContext, "COMPARE_PUBNUM_OPERATOR", paramString1, null);
                                cacheMap.put("COMPARE_PUBNUM_OPERATOR", paramString1);
                                if (!StringUtils.isNull(paramString2))
                                {
                                  IccidInfoManager.insertIccid(paramString2, true, str15, "", "", "", paramContext);
                                  g.a(new i(1, new String[] { "simIccid", paramString2, "smsLocate", str15 }));
                                }
                                return;
                                NetUtil.serverUrl2 = paramMap;
                                break;
                                NetUtil.serverUrl = paramMap;
                                break label869;
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  public static long insertOrUpdateKeyValue(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    paramContext = queryValueParamKey(paramContext, paramString1);
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("p_key", paramString1);
    localContentValues.put("p_value", paramString2);
    localContentValues.put("pextend_value", paramString3);
    if (paramContext != null) {}
    for (int i = DBManager.update("tb_sdk_param", localContentValues, "p_key=?", new String[] { paramString1 });; i = (int)DBManager.insert("tb_sdk_param", localContentValues)) {
      return i;
    }
  }
  
  /* Error */
  public static String queryValueParamKey(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_0
    //   2: iconst_0
    //   3: ldc 26
    //   5: iconst_1
    //   6: anewarray 64	java/lang/String
    //   9: dup
    //   10: iconst_0
    //   11: ldc 23
    //   13: aastore
    //   14: ldc -28
    //   16: iconst_1
    //   17: anewarray 64	java/lang/String
    //   20: dup
    //   21: iconst_0
    //   22: aload_1
    //   23: aastore
    //   24: aconst_null
    //   25: aconst_null
    //   26: aconst_null
    //   27: ldc_w 285
    //   30: invokestatic 401	cn/com/xy/sms/sdk/db/DBManager:query	(ZLjava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   33: astore_1
    //   34: aload_1
    //   35: ifnull +69 -> 104
    //   38: aload_1
    //   39: astore_0
    //   40: aload_1
    //   41: invokevirtual 404	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   44: ifle +60 -> 104
    //   47: aload_1
    //   48: astore_0
    //   49: aload_1
    //   50: invokevirtual 238	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   53: ifeq +51 -> 104
    //   56: aload_1
    //   57: astore_0
    //   58: aload_1
    //   59: aload_1
    //   60: ldc 23
    //   62: invokevirtual 241	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   65: invokevirtual 245	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   68: astore_2
    //   69: aload_1
    //   70: iconst_1
    //   71: invokestatic 258	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   74: aload_2
    //   75: areturn
    //   76: astore_2
    //   77: aconst_null
    //   78: astore_1
    //   79: aload_1
    //   80: astore_0
    //   81: aload_2
    //   82: invokevirtual 120	java/lang/Throwable:printStackTrace	()V
    //   85: aload_1
    //   86: iconst_1
    //   87: invokestatic 258	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   90: aconst_null
    //   91: areturn
    //   92: astore_2
    //   93: aload_0
    //   94: astore_1
    //   95: aload_2
    //   96: astore_0
    //   97: aload_1
    //   98: iconst_1
    //   99: invokestatic 258	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   102: aload_0
    //   103: athrow
    //   104: aload_1
    //   105: iconst_1
    //   106: invokestatic 258	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   109: goto -19 -> 90
    //   112: astore_2
    //   113: aload_0
    //   114: astore_1
    //   115: aload_2
    //   116: astore_0
    //   117: goto -20 -> 97
    //   120: astore_2
    //   121: goto -42 -> 79
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	124	0	paramContext	Context
    //   0	124	1	paramString	String
    //   68	7	2	str	String
    //   76	6	2	localThrowable1	Throwable
    //   92	4	2	localObject1	Object
    //   112	4	2	localObject2	Object
    //   120	1	2	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   2	34	76	java/lang/Throwable
    //   2	34	92	finally
    //   40	47	112	finally
    //   49	56	112	finally
    //   58	69	112	finally
    //   81	85	112	finally
    //   40	47	120	java/lang/Throwable
    //   49	56	120	java/lang/Throwable
    //   58	69	120	java/lang/Throwable
  }
  
  public static long setParam(String paramString1, String paramString2)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("p_key", paramString1);
      localContentValues.put("p_value", paramString2);
      int i = DBManager.update("tb_sdk_param", localContentValues, "p_key=?", new String[] { paramString1 });
      cacheMap.put(paramString1, paramString2);
      if (i <= 0)
      {
        long l = DBManager.insert("tb_sdk_param", localContentValues);
        return l;
      }
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
    }
    return 0L;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.SysParamEntityManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */