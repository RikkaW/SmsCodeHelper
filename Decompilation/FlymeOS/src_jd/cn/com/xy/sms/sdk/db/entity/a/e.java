package cn.com.xy.sms.sdk.db.entity.a;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.db.entity.IccidInfo;
import cn.com.xy.sms.sdk.db.entity.IccidInfoManager;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.iccid.IccidLocationUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.util.SdkCallBack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public final class e
{
  private static String a = "tb_public_info";
  private static String b = "tb_public_menu_info";
  private static String c = "tb_public_num_info";
  private static String d = " DROP TABLE IF EXISTS tb_public_info";
  private static String e = " DROP TABLE IF EXISTS tb_public_menu_info";
  private static String f = " DROP TABLE IF EXISTS tb_public_num_info";
  private static String g = "ALTER TABLE tb_public_info ADD COLUMN classifyCode TEXT";
  private static String h = " ALTER TABLE tb_public_num_info ADD COLUMN lastloadtime LONG default 0";
  private static String i = " ALTER TABLE tb_public_num_info ADD COLUMN isrulenum INTEGER default 0";
  private static String j = "queryTraffic";
  private static String k = "queryCharge";
  private static String l = "selectSimCard";
  
  private static int a(HashMap<String, String> paramHashMap)
  {
    if (paramHashMap == null) {
      return -1;
    }
    return g((String)paramHashMap.get("extend"));
  }
  
  /* Error */
  private static int a(String[] paramArrayOfString)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +8 -> 9
    //   4: aload_0
    //   5: arraylength
    //   6: ifne +8 -> 14
    //   9: bipush -2
    //   11: istore_1
    //   12: iload_1
    //   13: ireturn
    //   14: aload_0
    //   15: arraylength
    //   16: iconst_4
    //   17: if_icmple +29 -> 46
    //   20: aload_0
    //   21: iconst_4
    //   22: aaload
    //   23: invokestatic 71	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   26: istore_3
    //   27: iload_3
    //   28: ifne +18 -> 46
    //   31: aload_0
    //   32: iconst_4
    //   33: aaload
    //   34: invokestatic 76	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   37: istore_2
    //   38: iload_2
    //   39: istore_1
    //   40: iload_2
    //   41: bipush -2
    //   43: if_icmpne -31 -> 12
    //   46: aload_0
    //   47: arraylength
    //   48: iconst_2
    //   49: if_icmple +28 -> 77
    //   52: aload_0
    //   53: iconst_2
    //   54: aaload
    //   55: invokestatic 71	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   58: istore_3
    //   59: iload_3
    //   60: ifne +17 -> 77
    //   63: aload_0
    //   64: iconst_2
    //   65: aaload
    //   66: invokestatic 76	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   69: istore_1
    //   70: iload_1
    //   71: ireturn
    //   72: astore_0
    //   73: aload_0
    //   74: invokevirtual 79	java/lang/Throwable:printStackTrace	()V
    //   77: bipush -2
    //   79: ireturn
    //   80: astore_0
    //   81: goto -4 -> 77
    //   84: astore 4
    //   86: goto -40 -> 46
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	89	0	paramArrayOfString	String[]
    //   11	60	1	m	int
    //   37	7	2	n	int
    //   26	34	3	bool	boolean
    //   84	1	4	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   14	27	72	java/lang/Throwable
    //   46	59	72	java/lang/Throwable
    //   63	70	80	java/lang/Throwable
    //   31	38	84	java/lang/Throwable
  }
  
  public static String a()
  {
    return " create table  if not exists tb_public_info ( id INTEGER PRIMARY KEY, pubId INTEGER not null unique, pubName TEXT not null, pubType TEXT, classifyCode TEXT, weiXin TEXT, weiBoName TEXT, weiBoUrl TEXT, introduce TEXT, address TEXT, faxNum TEXT, webSite TEXT, moveWebSite TEXT, versionCode TEXT, email TEXT, parentPubId int, slogan TEXT, rectLogoName TEXT, circleLogoName TEXT, extend TEXT, hasmenu int, loadMenuTime long, updateInfoTime long )";
  }
  
  private static String a(int paramInt1, int paramInt2, String paramString)
  {
    if ((StringUtils.isNull(paramString)) || ("CN".equalsIgnoreCase(paramString))) {
      return "CN";
    }
    if (paramInt2 < 0)
    {
      LogManager.e("PubInfoManager getQueryAreaCodeBySimIndex", "simIndex小于0，返回CN，simIndex=" + paramInt2, null);
      return "CN";
    }
    Object localObject = IccidInfoManager.queryIccidInfo(null, paramInt2);
    if (localObject == null) {
      return "CN";
    }
    String str1 = String.valueOf(paramInt1);
    String str2 = userAreacode;
    String str3 = IccidLocationUtil.getOperatorNum(null, userOperator);
    if ((paramString.equalsIgnoreCase(str2)) && (str1.equals(str3)))
    {
      new StringBuilder("用户设置的区域编码及运营商编号与当前接入码的运营商编号匹配，返回当前区域编码，numOperator=").append(paramInt1).append(" simIndex=").append(paramInt2).append(" areaCode=").append(paramString);
      return paramString;
    }
    if ((!str2.equals("-2")) && (!StringUtils.isNull(str3)))
    {
      new StringBuilder("用户已经设置了区域编码及运营商编号，但与当前接入码的运营商编号不匹配，返回CN，numOperator=").append(paramInt1).append(" simIndex=").append(paramInt2).append(" areaCode=").append(paramString);
      return "CN";
    }
    str2 = areaCode;
    localObject = IccidLocationUtil.getOperatorNum(null, operator);
    if ((paramString.equalsIgnoreCase(str2)) && (str1.equals(localObject)))
    {
      new StringBuilder("卡位的区域编码及运营商编号与当前接入码的运营商编号匹配，返回当前区域编码，numOperator=").append(paramInt1).append(" simIndex=").append(paramInt2).append(" areaCode=").append(paramString);
      return paramString;
    }
    new StringBuilder("卡位与当前接入码的运营商编号不匹配，返回CN，numOperator=").append(paramInt1).append(" simIndex=").append(paramInt2).append(" areaCode=").append(paramString);
    return "CN";
  }
  
  private static String a(int paramInt, String paramString1, String paramString2)
  {
    int n = a(IccidLocationUtil.getIccidInfoArr(paramString1));
    int m = n;
    if (n == -2) {
      m = IccidLocationUtil.getOperatorByICCID(paramString1);
    }
    if ((m != -2) && (m == paramInt))
    {
      new StringBuilder("当前接入码所属运营商与当前使用的sim卡iccid所属运营商相同直接返回区域编码，numOperator=").append(paramInt).append(" simIccid=").append(paramString1).append(" areaCode=").append(paramString2);
      return paramString2;
    }
    new StringBuilder("当前接入码所属运营商与当前使用的sim卡iccid所属运营商不相同，返回CN，numOperator=").append(paramInt).append(" simIccid=").append(paramString1).append(" areaCode=").append(paramString2);
    return "CN";
  }
  
  private static String a(int paramInt1, String paramString1, String paramString2, int paramInt2)
  {
    if ("CN".equalsIgnoreCase(paramString2)) {
      return paramString2;
    }
    String str;
    try
    {
      if ((StringUtils.isNull(paramString1)) && (paramInt2 < 0))
      {
        localObject1 = IccidLocationUtil.getIccidAreaCodeMap();
        if ((localObject1 == null) || (((HashMap)localObject1).isEmpty())) {
          break label690;
        }
        localObject1 = ((HashMap)localObject1).entrySet().iterator();
        do
        {
          if (!((Iterator)localObject1).hasNext()) {
            return "CN";
          }
          localObject2 = (Map.Entry)((Iterator)localObject1).next();
          localObject3 = (String[])((Map.Entry)localObject2).getValue();
          m = a((String[])localObject3);
        } while ((m == -2) || (m != paramInt1) || (localObject3 == null) || (localObject3.length <= 0) || (StringUtils.isNull(localObject3[0])));
        new StringBuilder("当前接入码所属运营商与其中一张sim卡的iccid所属运营商相同时返回对应sim卡的区域编码，numOperator=").append(paramInt1).append(" AreaCode=").append(localObject3[0]).append(" ICCID=").append((String)((Map.Entry)localObject2).getKey());
        return localObject3[0] + "_" + (String)((Map.Entry)localObject2).getKey();
      }
      if ((!StringUtils.isNull(paramString1)) || (paramInt2 < 0)) {
        break label582;
      }
      if ((StringUtils.isNull(paramString2)) || ("CN".equalsIgnoreCase(paramString2))) {
        break label693;
      }
      if (paramInt2 < 0)
      {
        LogManager.e("PubInfoManager getQueryAreaCodeBySimIndex", "simIndex小于0，返回CN，simIndex=" + paramInt2, null);
        return "CN";
      }
      localObject2 = IccidInfoManager.queryIccidInfo(null, paramInt2);
      if (localObject2 == null) {
        return "CN";
      }
      Object localObject1 = String.valueOf(paramInt1);
      localObject3 = userAreacode;
      str = IccidLocationUtil.getOperatorNum(null, userOperator);
      if ((paramString2.equalsIgnoreCase((String)localObject3)) && (((String)localObject1).equals(str)))
      {
        new StringBuilder("用户设置的区域编码及运营商编号与当前接入码的运营商编号匹配，返回当前区域编码，numOperator=").append(paramInt1).append(" simIndex=").append(paramInt2).append(" areaCode=").append(paramString2);
        return paramString2;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      LogManager.e("PubInfoManager getQueryAreaCode", "出现异常，返回CN，numOperator=" + paramInt1 + " simIccid=" + paramString1 + " areaCode=" + paramString2 + " simIndex=" + paramInt2, null);
      return "CN";
    }
    if ((!((String)localObject3).equals("-2")) && (!StringUtils.isNull(str)))
    {
      new StringBuilder("用户已经设置了区域编码及运营商编号，但与当前接入码的运营商编号不匹配，返回CN，numOperator=").append(paramInt1).append(" simIndex=").append(paramInt2).append(" areaCode=").append(paramString2);
      return "CN";
    }
    Object localObject3 = areaCode;
    Object localObject2 = IccidLocationUtil.getOperatorNum(null, operator);
    if ((paramString2.equalsIgnoreCase((String)localObject3)) && (localThrowable.equals(localObject2)))
    {
      new StringBuilder("卡位的区域编码及运营商编号与当前接入码的运营商编号匹配，返回当前区域编码，numOperator=").append(paramInt1).append(" simIndex=").append(paramInt2).append(" areaCode=").append(paramString2);
      return paramString2;
    }
    new StringBuilder("卡位与当前接入码的运营商编号不匹配，返回CN，numOperator=").append(paramInt1).append(" simIndex=").append(paramInt2).append(" areaCode=").append(paramString2);
    return "CN";
    label582:
    int n = a(IccidLocationUtil.getIccidInfoArr(paramString1));
    int m = n;
    if (n == -2) {
      m = IccidLocationUtil.getOperatorByICCID(paramString1);
    }
    if ((m != -2) && (m == paramInt1))
    {
      new StringBuilder("当前接入码所属运营商与当前使用的sim卡iccid所属运营商相同直接返回区域编码，numOperator=").append(paramInt1).append(" simIccid=").append(paramString1).append(" areaCode=").append(paramString2);
      return paramString2;
    }
    new StringBuilder("当前接入码所属运营商与当前使用的sim卡iccid所属运营商不相同，返回CN，numOperator=").append(paramInt1).append(" simIccid=").append(paramString1).append(" areaCode=").append(paramString2);
    return "CN";
    label690:
    return "CN";
    label693:
    return "CN";
  }
  
  /* Error */
  public static String a(String paramString, java.util.Map<String, String> paramMap)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 71	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   4: ifne +25 -> 29
    //   7: aload_0
    //   8: ldc -24
    //   10: invokevirtual 236	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   13: ifeq +16 -> 29
    //   16: aload_1
    //   17: ifnull +12 -> 29
    //   20: aload_1
    //   21: invokeinterface 239 1 0
    //   26: ifeq +5 -> 31
    //   29: aconst_null
    //   30: areturn
    //   31: new 241	org/json/JSONArray
    //   34: dup
    //   35: aload_0
    //   36: invokespecial 242	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   39: astore_0
    //   40: aload_0
    //   41: iconst_0
    //   42: invokevirtual 246	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   45: astore_2
    //   46: aload_2
    //   47: ldc -8
    //   49: ldc -6
    //   51: invokevirtual 256	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   54: pop
    //   55: aload_2
    //   56: ldc_w 258
    //   59: invokevirtual 262	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   62: astore_2
    //   63: aload_1
    //   64: invokeinterface 263 1 0
    //   69: invokeinterface 195 1 0
    //   74: astore_3
    //   75: aload_0
    //   76: astore_1
    //   77: aload_3
    //   78: invokeinterface 200 1 0
    //   83: ifeq +152 -> 235
    //   86: new 252	org/json/JSONObject
    //   89: dup
    //   90: aload_3
    //   91: invokeinterface 204 1 0
    //   96: checkcast 206	java/util/Map$Entry
    //   99: invokeinterface 209 1 0
    //   104: checkcast 57	java/lang/String
    //   107: invokespecial 264	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   110: astore_1
    //   111: aload_1
    //   112: ldc 35
    //   114: invokevirtual 268	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   117: astore_3
    //   118: aload_3
    //   119: ifnull +52 -> 171
    //   122: aload_2
    //   123: bipush 6
    //   125: anewarray 57	java/lang/String
    //   128: dup
    //   129: iconst_0
    //   130: ldc -8
    //   132: aastore
    //   133: dup
    //   134: iconst_1
    //   135: aload_3
    //   136: ldc -8
    //   138: invokevirtual 272	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   141: aastore
    //   142: dup
    //   143: iconst_2
    //   144: ldc_w 274
    //   147: aastore
    //   148: dup
    //   149: iconst_3
    //   150: ldc 41
    //   152: aastore
    //   153: dup
    //   154: iconst_4
    //   155: ldc_w 276
    //   158: aastore
    //   159: dup
    //   160: iconst_5
    //   161: ldc 35
    //   163: aastore
    //   164: invokestatic 282	cn/com/xy/sms/sdk/util/JsonUtil:getJsonObject	([Ljava/lang/String;)Lorg/json/JSONObject;
    //   167: invokevirtual 285	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   170: pop
    //   171: aload_1
    //   172: ldc 38
    //   174: invokevirtual 268	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   177: astore_3
    //   178: aload_0
    //   179: astore_1
    //   180: aload_3
    //   181: ifnull +54 -> 235
    //   184: aload_2
    //   185: bipush 6
    //   187: anewarray 57	java/lang/String
    //   190: dup
    //   191: iconst_0
    //   192: ldc -8
    //   194: aastore
    //   195: dup
    //   196: iconst_1
    //   197: aload_3
    //   198: ldc -8
    //   200: invokevirtual 272	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   203: aastore
    //   204: dup
    //   205: iconst_2
    //   206: ldc_w 274
    //   209: aastore
    //   210: dup
    //   211: iconst_3
    //   212: ldc 41
    //   214: aastore
    //   215: dup
    //   216: iconst_4
    //   217: ldc_w 276
    //   220: aastore
    //   221: dup
    //   222: iconst_5
    //   223: ldc 38
    //   225: aastore
    //   226: invokestatic 282	cn/com/xy/sms/sdk/util/JsonUtil:getJsonObject	([Ljava/lang/String;)Lorg/json/JSONObject;
    //   229: invokevirtual 285	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   232: pop
    //   233: aload_0
    //   234: astore_1
    //   235: aload_1
    //   236: ifnonnull +17 -> 253
    //   239: aconst_null
    //   240: areturn
    //   241: astore_1
    //   242: aconst_null
    //   243: astore_0
    //   244: aload_1
    //   245: invokevirtual 79	java/lang/Throwable:printStackTrace	()V
    //   248: aload_0
    //   249: astore_1
    //   250: goto -15 -> 235
    //   253: aload_1
    //   254: invokevirtual 286	org/json/JSONArray:toString	()Ljava/lang/String;
    //   257: areturn
    //   258: astore_1
    //   259: goto -15 -> 244
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	262	0	paramString	String
    //   0	262	1	paramMap	java.util.Map<String, String>
    //   45	140	2	localObject1	Object
    //   74	124	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   31	40	241	java/lang/Throwable
    //   40	75	258	java/lang/Throwable
    //   77	118	258	java/lang/Throwable
    //   122	171	258	java/lang/Throwable
    //   171	178	258	java/lang/Throwable
    //   184	233	258	java/lang/Throwable
  }
  
  public static String a(String paramString, JSONObject paramJSONObject)
  {
    new StringBuilder("actionType=").append(paramString).append(" pubMenuInfo=").append(paramJSONObject);
    if (StringUtils.isNull(paramString)) {
      return "";
    }
    StringBuffer localStringBuffer = new StringBuffer();
    String str = paramString;
    if (!paramString.startsWith("WEB_")) {
      str = paramString.toLowerCase();
    }
    if ("reply_sms".equalsIgnoreCase(str))
    {
      localStringBuffer.append("{");
      localStringBuffer.append("\"type\":\"" + str + "\",");
      localStringBuffer.append("\"send_code\":\"" + paramJSONObject.optString("sms") + "\",");
      localStringBuffer.append("\"phone\":\"" + paramJSONObject.optString("sendTo") + "\",");
      localStringBuffer.append("\"menuName\":\"" + paramJSONObject.optString("menuName") + "\",");
      localStringBuffer.append("\"publicId\":\"" + paramJSONObject.optString("pubId") + "\"");
      localStringBuffer.append("}");
    }
    for (;;)
    {
      return StringUtils.encode(localStringBuffer.toString());
      if ("send_sms".equalsIgnoreCase(str))
      {
        localStringBuffer.append("{");
        localStringBuffer.append("\"type\":\"" + str + "\",");
        localStringBuffer.append("\"send_code\":\"" + paramJSONObject.optString("sms") + "\",");
        localStringBuffer.append("\"phone\":\"" + paramJSONObject.optString("sendTo") + "\",");
        localStringBuffer.append("\"menuName\":\"" + paramJSONObject.optString("menuName") + "\",");
        localStringBuffer.append("\"publicId\":\"" + paramJSONObject.optString("pubId") + "\"");
        localStringBuffer.append("}");
      }
      else if (("access_url".equalsIgnoreCase(str)) || ("open_url".equalsIgnoreCase(str)))
      {
        localStringBuffer.append("{");
        localStringBuffer.append("\"type\":\"" + str + "\",");
        localStringBuffer.append("\"url\":\"" + paramJSONObject.optString("url") + "\",");
        localStringBuffer.append("\"menuName\":\"" + paramJSONObject.optString("menuName") + "\",");
        localStringBuffer.append("\"publicId\":\"" + paramJSONObject.optString("pubId") + "\"");
        localStringBuffer.append("}");
      }
      else if ("down_url".equalsIgnoreCase(str))
      {
        localStringBuffer.append("{");
        localStringBuffer.append("\"type\":\"" + str + "\",");
        localStringBuffer.append("\"url\":\"" + paramJSONObject.optString("url") + "\",");
        localStringBuffer.append("\"menuName\":\"" + paramJSONObject.optString("menuName") + "\",");
        localStringBuffer.append("\"publicId\":\"" + paramJSONObject.optString("pubId") + "\"");
        localStringBuffer.append("}");
      }
      else if ("download".equalsIgnoreCase(str))
      {
        localStringBuffer.append("{");
        localStringBuffer.append("\"type\":\"" + str + "\",");
        localStringBuffer.append("\"url\":\"" + paramJSONObject.optString("url") + "\",");
        localStringBuffer.append("\"appName\":\"" + paramJSONObject.optString("appName") + "\",");
        localStringBuffer.append("\"menuName\":\"" + paramJSONObject.optString("menuName") + "\",");
        localStringBuffer.append("\"extend\":\"" + paramJSONObject.optString("extend") + "\",");
        localStringBuffer.append("\"publicId\":\"" + paramJSONObject.optString("pubId") + "\"");
        localStringBuffer.append("}");
      }
      else if ("weibo_url".equalsIgnoreCase(str))
      {
        localStringBuffer.append("{");
        localStringBuffer.append("\"type\":\"" + str + "\",");
        localStringBuffer.append("\"url\":\"" + paramJSONObject.optString("url") + "\",");
        localStringBuffer.append("\"menuName\":\"" + paramJSONObject.optString("menuName") + "\",");
        localStringBuffer.append("\"publicId\":\"" + paramJSONObject.optString("pubId") + "\"");
        localStringBuffer.append("}");
      }
      else if (("call_phone".equalsIgnoreCase(str)) || ("call".equalsIgnoreCase(str)))
      {
        localStringBuffer.append("{");
        localStringBuffer.append("\"type\":\"" + str + "\",");
        localStringBuffer.append("\"phoneNum\":\"" + paramJSONObject.optString("phoneNum") + "\",");
        localStringBuffer.append("\"menuName\":\"" + paramJSONObject.optString("menuName") + "\",");
        localStringBuffer.append("\"publicId\":\"" + paramJSONObject.optString("pubId") + "\"");
        localStringBuffer.append("}");
      }
      else if (("map_site".equalsIgnoreCase(str)) || ("open_map".equalsIgnoreCase(str)))
      {
        localStringBuffer.append("{");
        localStringBuffer.append("\"type\":\"" + str + "\",");
        localStringBuffer.append("\"address\":\"" + paramJSONObject.optString("extend") + "\",");
        localStringBuffer.append("\"menuName\":\"" + paramJSONObject.optString("menuName") + "\",");
        localStringBuffer.append("\"publicId\":\"" + paramJSONObject.optString("pubId") + "\"");
        localStringBuffer.append("}");
      }
      else if ("open_map_list".equalsIgnoreCase(str))
      {
        localStringBuffer.append("{");
        localStringBuffer.append("\"type\":\"" + str + "\",");
        localStringBuffer.append("\"address\":\"" + paramJSONObject.optString("extend") + "\",");
        localStringBuffer.append("\"menuName\":\"" + paramJSONObject.optString("menuName") + "\",");
        localStringBuffer.append("\"publicId\":\"" + paramJSONObject.optString("pubId") + "\"");
        localStringBuffer.append("}");
      }
      else if (("repayment".equalsIgnoreCase(str)) || ("zfb_repayment".equalsIgnoreCase(str)))
      {
        localStringBuffer.append("{");
        localStringBuffer.append("\"type\":\"" + str + "\",");
        localStringBuffer.append("\"appName\":\"" + paramJSONObject.optString("extend") + "\",");
        localStringBuffer.append("\"appDownUrl\":\"" + paramJSONObject.optString("url") + "\",");
        localStringBuffer.append("\"menuName\":\"" + paramJSONObject.optString("menuName") + "\",");
        localStringBuffer.append("\"publicId\":\"" + paramJSONObject.optString("pubId") + "\"");
        localStringBuffer.append("}");
      }
      else if (("recharge".equalsIgnoreCase(str)) || ("zfb_recharge".equalsIgnoreCase(str)))
      {
        localStringBuffer.append("{");
        localStringBuffer.append("\"type\":\"" + str + "\",");
        localStringBuffer.append("\"sp\":\"" + paramJSONObject.optString("sp") + "\",");
        localStringBuffer.append("\"appName\":\"" + paramJSONObject.optString("extend") + "\",");
        localStringBuffer.append("\"appDownUrl\":\"" + paramJSONObject.optString("url") + "\",");
        localStringBuffer.append("\"menuName\":\"" + paramJSONObject.optString("menuName") + "\",");
        localStringBuffer.append("\"publicId\":\"" + paramJSONObject.optString("pubId") + "\"");
        localStringBuffer.append("}");
      }
      else if ("open_app".equalsIgnoreCase(str))
      {
        localStringBuffer.append("{");
        localStringBuffer.append("\"type\":\"" + str + "\",");
        localStringBuffer.append("\"appName\":\"" + paramJSONObject.optString("extend") + "\",");
        localStringBuffer.append("\"appDownUrl\":\"" + paramJSONObject.optString("url") + "\",");
        localStringBuffer.append("\"menuName\":\"" + paramJSONObject.optString("menuName") + "\",");
        localStringBuffer.append("\"publicId\":\"" + paramJSONObject.optString("pubId") + "\"");
        localStringBuffer.append("}");
      }
      else if ("open_app_url".equalsIgnoreCase(str))
      {
        localStringBuffer.append("{");
        localStringBuffer.append("\"type\":\"" + str + "\",");
        localStringBuffer.append("\"appName\":\"" + paramJSONObject.optString("extend") + "\",");
        localStringBuffer.append("\"appDownUrl\":\"" + paramJSONObject.optString("url") + "\",");
        localStringBuffer.append("\"menuName\":\"" + paramJSONObject.optString("menuName") + "\",");
        localStringBuffer.append("\"publicId\":\"" + paramJSONObject.optString("pubId") + "\"");
        localStringBuffer.append("}");
      }
      else if ("WEB_TRAFFIC_ORDER".equalsIgnoreCase(str))
      {
        localStringBuffer.append("{");
        localStringBuffer.append("\"type\":\"" + str + "\",");
        localStringBuffer.append("\"sp\":\"" + paramJSONObject.optString("sp") + "\",");
        localStringBuffer.append("\"appName\":\"" + paramJSONObject.optString("extend") + "\",");
        localStringBuffer.append("\"appDownUrl\":\"" + paramJSONObject.optString("url") + "\",");
        localStringBuffer.append("\"menuName\":\"" + paramJSONObject.optString("menuName") + "\",");
        localStringBuffer.append("\"publicId\":\"" + paramJSONObject.optString("pubId") + "\"");
        localStringBuffer.append("}");
      }
    }
  }
  
  /* Error */
  public static ArrayList<String> a(String paramString)
  {
    // Byte code:
    //   0: new 407	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 408	java/util/ArrayList:<init>	()V
    //   7: astore 5
    //   9: new 92	java/lang/StringBuilder
    //   12: dup
    //   13: ldc_w 410
    //   16: invokespecial 97	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   19: aload_0
    //   20: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: ldc_w 412
    //   26: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   32: aconst_null
    //   33: invokestatic 418	cn/com/xy/sms/sdk/db/DBManager:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   36: astore 4
    //   38: aload 4
    //   40: ifnull +169 -> 209
    //   43: aload 4
    //   45: astore_0
    //   46: aload 4
    //   48: invokevirtual 424	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   51: ifle +158 -> 209
    //   54: aload 4
    //   56: astore_0
    //   57: aload 4
    //   59: ldc_w 338
    //   62: invokevirtual 427	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   65: istore_1
    //   66: aload 4
    //   68: astore_0
    //   69: aload 4
    //   71: ldc_w 429
    //   74: invokevirtual 427	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   77: istore_2
    //   78: aload 4
    //   80: astore_0
    //   81: aload 4
    //   83: invokevirtual 432	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   86: istore_3
    //   87: iload_3
    //   88: ifne +12 -> 100
    //   91: aload 4
    //   93: iconst_1
    //   94: invokestatic 436	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   97: aload 5
    //   99: areturn
    //   100: aload 4
    //   102: astore_0
    //   103: aload 4
    //   105: iload_1
    //   106: invokevirtual 438	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   109: astore 6
    //   111: aload 4
    //   113: astore_0
    //   114: aload 4
    //   116: iload_2
    //   117: invokevirtual 438	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   120: astore 7
    //   122: aload 4
    //   124: astore_0
    //   125: new 252	org/json/JSONObject
    //   128: dup
    //   129: invokespecial 439	org/json/JSONObject:<init>	()V
    //   132: astore 8
    //   134: aload 4
    //   136: astore_0
    //   137: aload 8
    //   139: ldc_w 441
    //   142: aload 6
    //   144: invokevirtual 256	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   147: pop
    //   148: aload 4
    //   150: astore_0
    //   151: aload 8
    //   153: ldc_w 443
    //   156: aload 7
    //   158: invokevirtual 256	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   161: pop
    //   162: aload 4
    //   164: astore_0
    //   165: aload 5
    //   167: aload 8
    //   169: invokevirtual 444	org/json/JSONObject:toString	()Ljava/lang/String;
    //   172: invokevirtual 447	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   175: pop
    //   176: goto -98 -> 78
    //   179: astore 5
    //   181: aload 4
    //   183: astore_0
    //   184: aload 5
    //   186: invokevirtual 79	java/lang/Throwable:printStackTrace	()V
    //   189: aload 4
    //   191: iconst_1
    //   192: invokestatic 436	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   195: aconst_null
    //   196: areturn
    //   197: astore 4
    //   199: aconst_null
    //   200: astore_0
    //   201: aload_0
    //   202: iconst_1
    //   203: invokestatic 436	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   206: aload 4
    //   208: athrow
    //   209: aload 4
    //   211: iconst_1
    //   212: invokestatic 436	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   215: goto -20 -> 195
    //   218: astore 4
    //   220: goto -19 -> 201
    //   223: astore 5
    //   225: aconst_null
    //   226: astore 4
    //   228: goto -47 -> 181
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	231	0	paramString	String
    //   65	41	1	m	int
    //   77	40	2	n	int
    //   86	2	3	bool	boolean
    //   36	154	4	localXyCursor1	XyCursor
    //   197	13	4	localXyCursor2	XyCursor
    //   218	1	4	localObject1	Object
    //   226	1	4	localObject2	Object
    //   7	159	5	localArrayList	ArrayList
    //   179	6	5	localThrowable1	Throwable
    //   223	1	5	localThrowable2	Throwable
    //   109	34	6	str1	String
    //   120	37	7	str2	String
    //   132	36	8	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   46	54	179	java/lang/Throwable
    //   57	66	179	java/lang/Throwable
    //   69	78	179	java/lang/Throwable
    //   81	87	179	java/lang/Throwable
    //   103	111	179	java/lang/Throwable
    //   114	122	179	java/lang/Throwable
    //   125	134	179	java/lang/Throwable
    //   137	148	179	java/lang/Throwable
    //   151	162	179	java/lang/Throwable
    //   165	176	179	java/lang/Throwable
    //   0	38	197	finally
    //   46	54	218	finally
    //   57	66	218	finally
    //   69	78	218	finally
    //   81	87	218	finally
    //   103	111	218	finally
    //   114	122	218	finally
    //   125	134	218	finally
    //   137	148	218	finally
    //   151	162	218	finally
    //   165	176	218	finally
    //   184	189	218	finally
    //   0	38	223	java/lang/Throwable
  }
  
  /* Error */
  private static HashMap<String, String> a(android.database.sqlite.SQLiteDatabase paramSQLiteDatabase, String paramString1, String paramString2, String paramString3, int paramInt)
  {
    // Byte code:
    //   0: iconst_m1
    //   1: istore 6
    //   3: aconst_null
    //   4: astore 18
    //   6: aconst_null
    //   7: astore 12
    //   9: aconst_null
    //   10: astore 19
    //   12: aconst_null
    //   13: astore 15
    //   15: aconst_null
    //   16: astore 20
    //   18: aconst_null
    //   19: astore 13
    //   21: aconst_null
    //   22: astore 17
    //   24: aconst_null
    //   25: astore 16
    //   27: aload_0
    //   28: aload_2
    //   29: aconst_null
    //   30: invokevirtual 454	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   33: astore_0
    //   34: aload_0
    //   35: astore 12
    //   37: iload 6
    //   39: istore 7
    //   41: aload 18
    //   43: astore 13
    //   45: aload 20
    //   47: astore 14
    //   49: aload 12
    //   51: ifnull +71 -> 122
    //   54: iload 6
    //   56: istore 7
    //   58: aload 18
    //   60: astore 13
    //   62: aload 20
    //   64: astore 14
    //   66: iload 6
    //   68: istore 5
    //   70: aload 19
    //   72: astore_0
    //   73: aload 17
    //   75: astore_2
    //   76: aload 12
    //   78: invokeinterface 457 1 0
    //   83: ifle +39 -> 122
    //   86: aload 16
    //   88: astore 13
    //   90: iload 6
    //   92: istore 5
    //   94: aload 15
    //   96: astore_0
    //   97: aload 13
    //   99: astore_2
    //   100: aload 12
    //   102: invokeinterface 458 1 0
    //   107: ifne +132 -> 239
    //   110: aload 13
    //   112: astore 14
    //   114: aload 15
    //   116: astore 13
    //   118: iload 6
    //   120: istore 7
    //   122: iload 7
    //   124: ifle +28 -> 152
    //   127: iload 7
    //   129: istore 5
    //   131: aload 13
    //   133: astore_0
    //   134: aload 14
    //   136: astore_2
    //   137: iload 7
    //   139: ldc_w 460
    //   142: aload_1
    //   143: iload 4
    //   145: aload 13
    //   147: aload 14
    //   149: invokestatic 463	cn/com/xy/sms/sdk/db/entity/a/e:a	(ILjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V
    //   152: aload 12
    //   154: invokestatic 466	cn/com/xy/sms/sdk/db/DBManager:closeCursor	(Landroid/database/Cursor;)V
    //   157: aconst_null
    //   158: astore_3
    //   159: aload 14
    //   161: astore_0
    //   162: aload 13
    //   164: astore_1
    //   165: iload 7
    //   167: iconst_m1
    //   168: if_icmpeq +745 -> 913
    //   171: new 51	java/util/HashMap
    //   174: dup
    //   175: invokespecial 467	java/util/HashMap:<init>	()V
    //   178: astore_3
    //   179: aload_3
    //   180: ldc_w 338
    //   183: iload 7
    //   185: invokestatic 119	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   188: invokevirtual 470	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   191: pop
    //   192: aload_1
    //   193: astore_2
    //   194: aload_1
    //   195: ifnonnull +7 -> 202
    //   198: ldc_w 297
    //   201: astore_2
    //   202: aload_3
    //   203: ldc_w 472
    //   206: aload_2
    //   207: invokevirtual 470	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   210: pop
    //   211: aload_3
    //   212: ldc_w 473
    //   215: ldc 85
    //   217: invokevirtual 470	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   220: pop
    //   221: aload_0
    //   222: ifnonnull +688 -> 910
    //   225: ldc_w 297
    //   228: astore_0
    //   229: aload_3
    //   230: ldc 49
    //   232: aload_0
    //   233: invokevirtual 470	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   236: pop
    //   237: aload_3
    //   238: areturn
    //   239: iload 6
    //   241: istore 5
    //   243: aload 15
    //   245: astore_0
    //   246: aload 13
    //   248: astore_2
    //   249: aload 12
    //   251: iconst_1
    //   252: invokeinterface 477 2 0
    //   257: istore 7
    //   259: iload 6
    //   261: istore 5
    //   263: aload 15
    //   265: astore_0
    //   266: aload 13
    //   268: astore_2
    //   269: aload 12
    //   271: iconst_2
    //   272: invokeinterface 477 2 0
    //   277: istore 8
    //   279: iload 6
    //   281: istore 5
    //   283: aload 15
    //   285: astore_0
    //   286: aload 13
    //   288: astore_2
    //   289: aload 12
    //   291: iconst_3
    //   292: invokeinterface 477 2 0
    //   297: istore 9
    //   299: iload 6
    //   301: istore 5
    //   303: aload 15
    //   305: astore_0
    //   306: aload 13
    //   308: astore_2
    //   309: aload 12
    //   311: iconst_4
    //   312: invokeinterface 478 2 0
    //   317: astore 14
    //   319: iload 6
    //   321: istore 5
    //   323: aload 15
    //   325: astore_0
    //   326: aload 13
    //   328: astore_2
    //   329: aload 12
    //   331: iconst_5
    //   332: invokeinterface 478 2 0
    //   337: astore 18
    //   339: iload 6
    //   341: istore 5
    //   343: aload 15
    //   345: astore_0
    //   346: aload 13
    //   348: astore_2
    //   349: aload 12
    //   351: bipush 6
    //   353: invokeinterface 478 2 0
    //   358: astore 16
    //   360: iload 6
    //   362: istore 5
    //   364: aload 15
    //   366: astore_0
    //   367: aload 13
    //   369: astore_2
    //   370: aload 18
    //   372: ldc_w 480
    //   375: invokevirtual 483	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   378: istore 10
    //   380: iload 10
    //   382: iflt +611 -> 993
    //   385: iload 6
    //   387: istore 5
    //   389: aload 15
    //   391: astore_0
    //   392: aload 13
    //   394: astore_2
    //   395: aload 18
    //   397: iconst_0
    //   398: iload 10
    //   400: invokevirtual 487	java/lang/String:substring	(II)Ljava/lang/String;
    //   403: astore 17
    //   405: iload 6
    //   407: istore 5
    //   409: aload 15
    //   411: astore_0
    //   412: aload 13
    //   414: astore_2
    //   415: aload 18
    //   417: iload 10
    //   419: iconst_1
    //   420: iadd
    //   421: invokevirtual 489	java/lang/String:substring	(I)Ljava/lang/String;
    //   424: astore 18
    //   426: iload 6
    //   428: istore 5
    //   430: aload 15
    //   432: astore_0
    //   433: aload 13
    //   435: astore_2
    //   436: aload_1
    //   437: aload 17
    //   439: invokevirtual 305	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   442: ifeq +503 -> 945
    //   445: iload 6
    //   447: istore 5
    //   449: aload 15
    //   451: astore_0
    //   452: aload 13
    //   454: astore_2
    //   455: aload_1
    //   456: aload 18
    //   458: invokevirtual 492	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   461: ifne +202 -> 663
    //   464: goto +481 -> 945
    //   467: iload 5
    //   469: ifeq -379 -> 90
    //   472: iload 6
    //   474: istore 5
    //   476: aload 15
    //   478: astore_0
    //   479: aload 13
    //   481: astore_2
    //   482: aload 16
    //   484: aload_3
    //   485: invokevirtual 483	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   488: iconst_m1
    //   489: if_icmpeq +292 -> 781
    //   492: iload 6
    //   494: istore 5
    //   496: aload 15
    //   498: astore_0
    //   499: aload 13
    //   501: astore_2
    //   502: aload 12
    //   504: iconst_0
    //   505: invokeinterface 477 2 0
    //   510: istore 6
    //   512: iload 6
    //   514: istore 5
    //   516: aload 15
    //   518: astore_0
    //   519: aload 13
    //   521: astore_2
    //   522: aload 12
    //   524: bipush 7
    //   526: invokeinterface 478 2 0
    //   531: astore 14
    //   533: iload 6
    //   535: istore 5
    //   537: aload 14
    //   539: astore_0
    //   540: aload 13
    //   542: astore_2
    //   543: aload 12
    //   545: bipush 8
    //   547: invokeinterface 478 2 0
    //   552: astore 13
    //   554: iload 6
    //   556: istore 5
    //   558: aload 14
    //   560: astore_0
    //   561: aload 13
    //   563: astore_2
    //   564: iload 6
    //   566: new 92	java/lang/StringBuilder
    //   569: dup
    //   570: aload_3
    //   571: invokestatic 223	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   574: invokespecial 97	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   577: ldc_w 494
    //   580: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   583: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   586: aload_1
    //   587: iload 4
    //   589: aload 14
    //   591: aload 13
    //   593: invokestatic 463	cn/com/xy/sms/sdk/db/entity/a/e:a	(ILjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V
    //   596: iload 6
    //   598: istore 5
    //   600: aload 14
    //   602: astore_0
    //   603: aload 13
    //   605: astore_2
    //   606: new 51	java/util/HashMap
    //   609: dup
    //   610: invokespecial 467	java/util/HashMap:<init>	()V
    //   613: astore_1
    //   614: aload_1
    //   615: ldc_w 338
    //   618: iload 6
    //   620: invokestatic 119	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   623: invokevirtual 470	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   626: pop
    //   627: aload_1
    //   628: ldc_w 472
    //   631: aload 14
    //   633: invokevirtual 470	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   636: pop
    //   637: aload_1
    //   638: ldc_w 473
    //   641: aload 16
    //   643: invokevirtual 470	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   646: pop
    //   647: aload_1
    //   648: ldc 49
    //   650: aload 13
    //   652: invokevirtual 470	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   655: pop
    //   656: aload 12
    //   658: invokestatic 466	cn/com/xy/sms/sdk/db/DBManager:closeCursor	(Landroid/database/Cursor;)V
    //   661: aload_1
    //   662: areturn
    //   663: iload 6
    //   665: istore 5
    //   667: aload 15
    //   669: astore_0
    //   670: aload 13
    //   672: astore_2
    //   673: aload 17
    //   675: invokevirtual 497	java/lang/String:length	()I
    //   678: istore 10
    //   680: iload 6
    //   682: istore 5
    //   684: aload 15
    //   686: astore_0
    //   687: aload 13
    //   689: astore_2
    //   690: aload 18
    //   692: invokevirtual 497	java/lang/String:length	()I
    //   695: iload 10
    //   697: iadd
    //   698: istore 10
    //   700: iload 6
    //   702: istore 5
    //   704: aload 15
    //   706: astore_0
    //   707: aload 13
    //   709: astore_2
    //   710: aload_1
    //   711: invokevirtual 497	java/lang/String:length	()I
    //   714: istore 11
    //   716: iload 9
    //   718: ifle +233 -> 951
    //   721: iload 10
    //   723: iload 9
    //   725: iadd
    //   726: iload 11
    //   728: if_icmpeq +9 -> 737
    //   731: iconst_0
    //   732: istore 5
    //   734: goto -267 -> 467
    //   737: iload 6
    //   739: istore 5
    //   741: aload 15
    //   743: astore_0
    //   744: aload 13
    //   746: astore_2
    //   747: ldc_w 499
    //   750: aload 14
    //   752: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   755: ifeq +244 -> 999
    //   758: iload 6
    //   760: istore 5
    //   762: aload 15
    //   764: astore_0
    //   765: aload 13
    //   767: astore_2
    //   768: aload_1
    //   769: invokestatic 501	cn/com/xy/sms/sdk/util/StringUtils:sj	(Ljava/lang/String;)Z
    //   772: ifne +227 -> 999
    //   775: iconst_0
    //   776: istore 5
    //   778: goto -311 -> 467
    //   781: iload 6
    //   783: istore 5
    //   785: aload 15
    //   787: astore_0
    //   788: aload 13
    //   790: astore_2
    //   791: aload 16
    //   793: ldc 85
    //   795: invokevirtual 483	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   798: iconst_m1
    //   799: if_icmpeq -709 -> 90
    //   802: iload 6
    //   804: istore 5
    //   806: aload 15
    //   808: astore_0
    //   809: aload 13
    //   811: astore_2
    //   812: aload 12
    //   814: iconst_0
    //   815: invokeinterface 477 2 0
    //   820: istore 6
    //   822: iload 6
    //   824: istore 5
    //   826: aload 15
    //   828: astore_0
    //   829: aload 13
    //   831: astore_2
    //   832: aload 12
    //   834: bipush 7
    //   836: invokeinterface 478 2 0
    //   841: astore 15
    //   843: iload 6
    //   845: istore 5
    //   847: aload 15
    //   849: astore_0
    //   850: aload 13
    //   852: astore_2
    //   853: aload 12
    //   855: bipush 8
    //   857: invokeinterface 478 2 0
    //   862: astore 13
    //   864: goto -774 -> 90
    //   867: astore_0
    //   868: aconst_null
    //   869: astore_3
    //   870: aconst_null
    //   871: astore_1
    //   872: aload 13
    //   874: astore_2
    //   875: aload 12
    //   877: astore_0
    //   878: iload 6
    //   880: istore 5
    //   882: aload_3
    //   883: invokestatic 466	cn/com/xy/sms/sdk/db/DBManager:closeCursor	(Landroid/database/Cursor;)V
    //   886: aload_1
    //   887: astore_3
    //   888: aload_0
    //   889: astore_1
    //   890: aload_2
    //   891: astore_0
    //   892: iload 5
    //   894: istore 7
    //   896: goto -731 -> 165
    //   899: astore_0
    //   900: aconst_null
    //   901: astore 12
    //   903: aload 12
    //   905: invokestatic 466	cn/com/xy/sms/sdk/db/DBManager:closeCursor	(Landroid/database/Cursor;)V
    //   908: aload_0
    //   909: athrow
    //   910: goto -681 -> 229
    //   913: aload_3
    //   914: areturn
    //   915: astore_0
    //   916: goto -13 -> 903
    //   919: astore_1
    //   920: aconst_null
    //   921: astore_1
    //   922: aload 12
    //   924: astore_3
    //   925: goto -43 -> 882
    //   928: astore_0
    //   929: aload 12
    //   931: astore_3
    //   932: iload 6
    //   934: istore 5
    //   936: aload 14
    //   938: astore_0
    //   939: aload 13
    //   941: astore_2
    //   942: goto -60 -> 882
    //   945: iconst_0
    //   946: istore 5
    //   948: goto -481 -> 467
    //   951: iload 8
    //   953: ifle +19 -> 972
    //   956: iload 11
    //   958: iload 8
    //   960: iload 10
    //   962: iadd
    //   963: if_icmple +9 -> 972
    //   966: iconst_0
    //   967: istore 5
    //   969: goto -502 -> 467
    //   972: iload 7
    //   974: ifle -237 -> 737
    //   977: iload 11
    //   979: iload 7
    //   981: iload 10
    //   983: iadd
    //   984: if_icmpge -247 -> 737
    //   987: iconst_0
    //   988: istore 5
    //   990: goto -523 -> 467
    //   993: iconst_0
    //   994: istore 5
    //   996: goto -529 -> 467
    //   999: iconst_1
    //   1000: istore 5
    //   1002: goto -535 -> 467
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1005	0	paramSQLiteDatabase	android.database.sqlite.SQLiteDatabase
    //   0	1005	1	paramString1	String
    //   0	1005	2	paramString2	String
    //   0	1005	3	paramString3	String
    //   0	1005	4	paramInt	int
    //   68	933	5	m	int
    //   1	932	6	n	int
    //   39	945	7	i1	int
    //   277	686	8	i2	int
    //   297	429	9	i3	int
    //   378	606	10	i4	int
    //   714	271	11	i5	int
    //   7	923	12	localSQLiteDatabase	android.database.sqlite.SQLiteDatabase
    //   19	921	13	localObject1	Object
    //   47	890	14	localObject2	Object
    //   13	835	15	str1	String
    //   25	767	16	str2	String
    //   22	652	17	str3	String
    //   4	687	18	str4	String
    //   10	61	19	localObject3	Object
    //   16	47	20	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   27	34	867	java/lang/Throwable
    //   27	34	899	finally
    //   76	86	915	finally
    //   100	110	915	finally
    //   137	152	915	finally
    //   249	259	915	finally
    //   269	279	915	finally
    //   289	299	915	finally
    //   309	319	915	finally
    //   329	339	915	finally
    //   349	360	915	finally
    //   370	380	915	finally
    //   395	405	915	finally
    //   415	426	915	finally
    //   436	445	915	finally
    //   455	464	915	finally
    //   482	492	915	finally
    //   502	512	915	finally
    //   522	533	915	finally
    //   543	554	915	finally
    //   564	596	915	finally
    //   606	614	915	finally
    //   614	656	915	finally
    //   673	680	915	finally
    //   690	700	915	finally
    //   710	716	915	finally
    //   747	758	915	finally
    //   768	775	915	finally
    //   791	802	915	finally
    //   812	822	915	finally
    //   832	843	915	finally
    //   853	864	915	finally
    //   76	86	919	java/lang/Throwable
    //   100	110	919	java/lang/Throwable
    //   137	152	919	java/lang/Throwable
    //   249	259	919	java/lang/Throwable
    //   269	279	919	java/lang/Throwable
    //   289	299	919	java/lang/Throwable
    //   309	319	919	java/lang/Throwable
    //   329	339	919	java/lang/Throwable
    //   349	360	919	java/lang/Throwable
    //   370	380	919	java/lang/Throwable
    //   395	405	919	java/lang/Throwable
    //   415	426	919	java/lang/Throwable
    //   436	445	919	java/lang/Throwable
    //   455	464	919	java/lang/Throwable
    //   482	492	919	java/lang/Throwable
    //   502	512	919	java/lang/Throwable
    //   522	533	919	java/lang/Throwable
    //   543	554	919	java/lang/Throwable
    //   564	596	919	java/lang/Throwable
    //   606	614	919	java/lang/Throwable
    //   673	680	919	java/lang/Throwable
    //   690	700	919	java/lang/Throwable
    //   710	716	919	java/lang/Throwable
    //   747	758	919	java/lang/Throwable
    //   768	775	919	java/lang/Throwable
    //   791	802	919	java/lang/Throwable
    //   812	822	919	java/lang/Throwable
    //   832	843	919	java/lang/Throwable
    //   853	864	919	java/lang/Throwable
    //   614	656	928	java/lang/Throwable
  }
  
  public static HashMap<String, JSONObject> a(Set<String> paramSet)
  {
    str = null;
    localJSONObject = null;
    if ((paramSet == null) || (paramSet.isEmpty())) {
      return null;
    }
    localHashMap = new HashMap();
    localObject2 = localJSONObject;
    localObject1 = str;
    for (;;)
    {
      try
      {
        localStringBuffer = new StringBuffer();
        localObject2 = localJSONObject;
        localObject1 = str;
        paramSet = paramSet.iterator();
        localObject2 = localJSONObject;
        localObject1 = str;
        if (paramSet.hasNext()) {
          continue;
        }
        localObject2 = localJSONObject;
        localObject1 = str;
        localStringBuffer.deleteCharAt(localStringBuffer.length() - 1);
        localObject2 = localJSONObject;
        localObject1 = str;
        paramSet = DBManager.rawQuery("SELECT pubId,pubName,rectLogoName,circleLogoName from tb_public_info where pubId in  (" + localStringBuffer.toString() + ")", null);
        if (paramSet == null) {
          continue;
        }
        localObject2 = paramSet;
        localObject1 = paramSet;
        if (paramSet.getCount() <= 0) {
          continue;
        }
        localObject2 = paramSet;
        localObject1 = paramSet;
        m = paramSet.getColumnIndex("pubId");
        localObject2 = paramSet;
        localObject1 = paramSet;
        n = paramSet.getColumnIndex("pubName");
        localObject2 = paramSet;
        localObject1 = paramSet;
        i1 = paramSet.getColumnIndex("rectLogoName");
        localObject2 = paramSet;
        localObject1 = paramSet;
        i2 = paramSet.getColumnIndex("circleLogoName");
      }
      catch (Throwable paramSet)
      {
        StringBuffer localStringBuffer;
        int m;
        int n;
        int i1;
        int i2;
        boolean bool;
        localObject1 = localObject2;
        paramSet.printStackTrace();
        XyCursor.closeCursor((XyCursor)localObject2, true);
        continue;
        localObject2 = paramSet;
        localObject1 = paramSet;
        localJSONObject = new JSONObject();
        localObject2 = paramSet;
        localObject1 = paramSet;
        str = String.valueOf(paramSet.getInt(m));
        localObject2 = paramSet;
        localObject1 = paramSet;
        localJSONObject.put("id", str);
        localObject2 = paramSet;
        localObject1 = paramSet;
        localJSONObject.put("name", paramSet.getString(n));
        localObject2 = paramSet;
        localObject1 = paramSet;
        localJSONObject.put("logo", paramSet.getString(i1));
        localObject2 = paramSet;
        localObject1 = paramSet;
        localJSONObject.put("logoc", paramSet.getString(i2));
        localObject2 = paramSet;
        localObject1 = paramSet;
        localHashMap.put(str, localJSONObject);
        continue;
      }
      finally
      {
        XyCursor.closeCursor((XyCursor)localObject1, true);
      }
      localObject2 = paramSet;
      localObject1 = paramSet;
      bool = paramSet.moveToNext();
      if (bool) {
        continue;
      }
      XyCursor.closeCursor(paramSet, true);
      return localHashMap;
      localObject2 = localJSONObject;
      localObject1 = str;
      localStringBuffer.append((String)paramSet.next() + ",");
    }
  }
  
  public static JSONArray a(String paramString1, String paramString2, int paramInt1, boolean paramBoolean, String paramString3, String paramString4, int paramInt2, SdkCallBack paramSdkCallBack)
  {
    if (paramBoolean) {}
    for (;;)
    {
      Object localObject2;
      int m;
      try
      {
        if ("CN".equals(paramString2)) {
          return null;
        }
        localObject1 = c(paramString1, paramString2, paramInt1);
        if (localObject1 == null)
        {
          a(paramString2, paramString3, paramString1, paramString4, paramInt1, false, paramSdkCallBack);
          return null;
        }
        Object localObject3 = (String)((HashMap)localObject1).get("areaCode");
        new StringBuilder("areaCode=").append(paramString2).append("currentDataCode=").append((String)localObject3);
        if (("CN".equals(localObject3)) && (!((HashMap)localObject1).containsKey("ruleMatch")))
        {
          if ((StringUtils.isNull(paramString2)) || ("CN".equals(paramString2))) {
            break label410;
          }
          a(paramString2, paramString3, paramString1, paramString4, paramInt1, true, new f(paramSdkCallBack, (HashMap)localObject1));
          break label410;
        }
        localObject2 = null;
        paramBoolean = "true".equals(SysParamEntityManager.getStringParam(Constant.getContext(), "COMPARE_PUBNUM_OPERATOR"));
        if (IccidLocationUtil.getIccidAreaCodeMap().size() <= 0) {
          break label416;
        }
        m = 1;
        if ((m != 0) && (paramBoolean) && (!"CN".equals(localObject3)))
        {
          if (localObject1 == null)
          {
            m = -1;
            if (m == -1) {
              break label398;
            }
            paramString2 = a(m, paramString3, paramString2, paramInt2);
            if ((paramString2 != null) && (paramString2.startsWith("CN")))
            {
              localObject3 = c(paramString1, paramString2, paramInt1);
              if (localObject3 != null)
              {
                paramString2 = (String)localObject3;
                localObject1 = localObject2;
                if (((HashMap)localObject3).containsKey("pubId")) {
                  continue;
                }
              }
              a("CN", paramString3, paramString1, paramString4, paramInt1, false, paramSdkCallBack);
              return null;
            }
          }
          else
          {
            m = g((String)((HashMap)localObject1).get("extend"));
            continue;
          }
          if ((!StringUtils.isNull(paramString2)) && (paramString2.contains("_")))
          {
            paramString1 = paramString2.split("_");
            if (paramString1.length > 1)
            {
              paramString1 = paramString1[1];
              paramString2 = (String)localObject1;
              localObject1 = paramString1;
              paramString1 = b(Integer.valueOf((String)paramString2.get("pubId")).intValue());
              if ((StringUtils.isNull((String)localObject1)) || (paramString1 == null) || (paramString1.length() <= 0)) {
                break label408;
              }
              paramString1.getJSONObject(0).put("iccid", localObject1);
              return paramString1;
            }
          }
        }
      }
      catch (Throwable paramString1)
      {
        paramString1.printStackTrace();
        return null;
      }
      label398:
      paramString2 = (String)localObject1;
      Object localObject1 = localObject2;
      continue;
      label408:
      return paramString1;
      label410:
      if (paramBoolean)
      {
        return null;
        label416:
        m = 0;
      }
    }
  }
  
  /* Error */
  private static JSONObject a(int paramInt)
  {
    // Byte code:
    //   0: bipush 23
    //   2: anewarray 57	java/lang/String
    //   5: astore_3
    //   6: aload_3
    //   7: iconst_0
    //   8: ldc_w 524
    //   11: aastore
    //   12: aload_3
    //   13: iconst_1
    //   14: ldc_w 338
    //   17: aastore
    //   18: aload_3
    //   19: iconst_2
    //   20: ldc_w 515
    //   23: aastore
    //   24: aload_3
    //   25: iconst_3
    //   26: ldc_w 588
    //   29: aastore
    //   30: aload_3
    //   31: iconst_4
    //   32: ldc_w 590
    //   35: aastore
    //   36: aload_3
    //   37: iconst_5
    //   38: ldc_w 592
    //   41: aastore
    //   42: aload_3
    //   43: bipush 6
    //   45: ldc_w 594
    //   48: aastore
    //   49: aload_3
    //   50: bipush 7
    //   52: ldc_w 596
    //   55: aastore
    //   56: aload_3
    //   57: bipush 8
    //   59: ldc_w 598
    //   62: aastore
    //   63: aload_3
    //   64: bipush 9
    //   66: ldc_w 600
    //   69: aastore
    //   70: aload_3
    //   71: bipush 10
    //   73: ldc_w 602
    //   76: aastore
    //   77: aload_3
    //   78: bipush 11
    //   80: ldc_w 604
    //   83: aastore
    //   84: aload_3
    //   85: bipush 12
    //   87: ldc_w 429
    //   90: aastore
    //   91: aload_3
    //   92: bipush 13
    //   94: ldc_w 606
    //   97: aastore
    //   98: aload_3
    //   99: bipush 14
    //   101: ldc_w 608
    //   104: aastore
    //   105: aload_3
    //   106: bipush 15
    //   108: ldc_w 610
    //   111: aastore
    //   112: aload_3
    //   113: bipush 16
    //   115: ldc_w 517
    //   118: aastore
    //   119: aload_3
    //   120: bipush 17
    //   122: ldc_w 519
    //   125: aastore
    //   126: aload_3
    //   127: bipush 18
    //   129: ldc 49
    //   131: aastore
    //   132: aload_3
    //   133: bipush 19
    //   135: ldc_w 612
    //   138: aastore
    //   139: aload_3
    //   140: bipush 20
    //   142: ldc_w 614
    //   145: aastore
    //   146: aload_3
    //   147: bipush 21
    //   149: ldc_w 616
    //   152: aastore
    //   153: aload_3
    //   154: bipush 22
    //   156: ldc_w 618
    //   159: aastore
    //   160: ldc 8
    //   162: aload_3
    //   163: ldc_w 620
    //   166: iconst_1
    //   167: anewarray 57	java/lang/String
    //   170: dup
    //   171: iconst_0
    //   172: new 92	java/lang/StringBuilder
    //   175: dup
    //   176: iload_0
    //   177: invokestatic 119	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   180: invokespecial 97	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   183: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   186: aastore
    //   187: invokestatic 624	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   190: astore_2
    //   191: aload_2
    //   192: astore_1
    //   193: aload_3
    //   194: aload_2
    //   195: invokestatic 630	cn/com/xy/sms/sdk/db/base/BaseManager:loadSingleDataFromCursor	([Ljava/lang/String;Lcn/com/xy/sms/sdk/db/XyCursor;)Lorg/json/JSONObject;
    //   198: astore_3
    //   199: aload_2
    //   200: iconst_1
    //   201: invokestatic 436	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   204: aload_3
    //   205: areturn
    //   206: astore_3
    //   207: aconst_null
    //   208: astore_2
    //   209: aload_2
    //   210: astore_1
    //   211: aload_3
    //   212: invokevirtual 79	java/lang/Throwable:printStackTrace	()V
    //   215: aload_2
    //   216: iconst_1
    //   217: invokestatic 436	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   220: aconst_null
    //   221: areturn
    //   222: astore_1
    //   223: aconst_null
    //   224: astore_3
    //   225: aload_1
    //   226: astore_2
    //   227: aload_3
    //   228: iconst_1
    //   229: invokestatic 436	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   232: aload_2
    //   233: athrow
    //   234: astore_2
    //   235: aload_1
    //   236: astore_3
    //   237: goto -10 -> 227
    //   240: astore_3
    //   241: goto -32 -> 209
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	244	0	paramInt	int
    //   192	19	1	localObject1	Object
    //   222	14	1	localObject2	Object
    //   190	43	2	localObject3	Object
    //   234	1	2	localObject4	Object
    //   5	200	3	localObject5	Object
    //   206	6	3	localThrowable1	Throwable
    //   224	13	3	localObject6	Object
    //   240	1	3	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   0	6	206	java/lang/Throwable
    //   160	191	206	java/lang/Throwable
    //   0	6	222	finally
    //   160	191	222	finally
    //   193	199	234	finally
    //   211	215	234	finally
    //   193	199	240	java/lang/Throwable
  }
  
  public static JSONObject a(String paramString1, String paramString2, int paramInt)
  {
    for (;;)
    {
      try
      {
        paramString2 = c(paramString1, paramString2, paramInt);
        if (paramString2 == null) {
          return null;
        }
        paramInt = Integer.valueOf((String)paramString2.get("pubId")).intValue();
        if (paramInt == -1) {
          continue;
        }
        paramString1 = a(paramInt);
        if (paramString1 == null) {
          break label191;
        }
      }
      catch (Throwable paramString2)
      {
        boolean bool;
        paramString1 = null;
        continue;
        paramString2 = null;
        continue;
      }
      try
      {
        paramString1.put("purpose", paramString2.get("purpose"));
        paramString1.put("extend", paramString2.get("extend"));
        bool = StringUtils.isNull(paramString1.optString("pubName"));
        paramString2 = paramString1;
        if (!bool)
        {
          if (paramString1 == null) {
            paramString2 = paramString1;
          }
        }
        else {
          return paramString2;
        }
        try
        {
          paramString2 = new ContentValues();
          paramString2.put("loadMenuTime", Long.valueOf(System.currentTimeMillis()));
          paramString2.put("updateInfoTime", Long.valueOf(System.currentTimeMillis()));
          DBManager.update("tb_public_info", paramString2, "pubId = ?", new String[] { paramString1.optString("pubId") });
          paramString2 = paramString1;
        }
        catch (Throwable paramString2)
        {
          paramString2.printStackTrace();
          paramString2 = paramString1;
        }
        continue;
        paramString2.printStackTrace();
      }
      catch (Throwable paramString2) {}
      paramString2 = paramString1;
    }
    label191:
    return null;
  }
  
  private static void a(int paramInt1, String paramString1, String paramString2, int paramInt2, String paramString3, String paramString4)
  {
    NetUtil.executeRunnable(new h(paramInt1, paramString1, paramString2, paramInt2, paramString3, paramString4));
  }
  
  private static void a(XyCursor paramXyCursor, ArrayList<String> paramArrayList)
  {
    if (paramXyCursor == null) {}
    for (;;)
    {
      return;
      int m = paramXyCursor.getColumnIndex("num");
      int n = paramXyCursor.getColumnIndex("versionCode");
      if ((paramXyCursor == null) || (paramXyCursor.getCount() <= 0)) {
        break;
      }
      LogManager.e("queryPubInfo", "greatPostData: is :" + paramXyCursor.getCount());
      while (paramXyCursor.moveToNext())
      {
        String str1 = paramXyCursor.getString(m);
        String str2 = paramXyCursor.getString(n);
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("num", str1);
        localJSONObject.put("version", str2);
        paramArrayList.add(localJSONObject.toString());
      }
    }
    LogManager.e("queryPubInfo", "greatPostData: is 0");
  }
  
  private static void a(String paramString1, String paramString2)
  {
    NetUtil.executeRunnable(new i(paramString1, paramString2));
  }
  
  private static void a(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, boolean paramBoolean, SdkCallBack paramSdkCallBack)
  {
    try
    {
      NetUtil.executeRunnable(new g(paramString3, paramString4, paramString1, paramString2, String.valueOf(paramInt), paramSdkCallBack, paramBoolean));
      return;
    }
    catch (Throwable paramString1)
    {
      LogManager.e("PubInfoManager", "queryPubInfoRequestAsync " + paramString1.getMessage(), paramString1);
    }
  }
  
  public static void a(ArrayList<String> paramArrayList)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("lastloadtime", Integer.valueOf(0));
    paramArrayList = paramArrayList.iterator();
    for (;;)
    {
      if (!paramArrayList.hasNext()) {
        return;
      }
      DBManager.update("tb_public_num_info", localContentValues, "num = ?", new String[] { new JSONObject((String)paramArrayList.next()).getString("num") });
    }
  }
  
  /* Error */
  public static void a(JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +4 -> 5
    //   4: return
    //   5: aconst_null
    //   6: bipush 42
    //   8: anewarray 57	java/lang/String
    //   11: dup
    //   12: iconst_0
    //   13: ldc_w 338
    //   16: aastore
    //   17: dup
    //   18: iconst_1
    //   19: aload_0
    //   20: ldc_w 338
    //   23: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   26: aastore
    //   27: dup
    //   28: iconst_2
    //   29: ldc_w 515
    //   32: aastore
    //   33: dup
    //   34: iconst_3
    //   35: aload_0
    //   36: ldc_w 515
    //   39: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   42: aastore
    //   43: dup
    //   44: iconst_4
    //   45: ldc_w 588
    //   48: aastore
    //   49: dup
    //   50: iconst_5
    //   51: aload_0
    //   52: ldc_w 588
    //   55: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   58: aastore
    //   59: dup
    //   60: bipush 6
    //   62: ldc_w 590
    //   65: aastore
    //   66: dup
    //   67: bipush 7
    //   69: aload_0
    //   70: ldc_w 590
    //   73: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   76: aastore
    //   77: dup
    //   78: bipush 8
    //   80: ldc_w 592
    //   83: aastore
    //   84: dup
    //   85: bipush 9
    //   87: aload_0
    //   88: ldc_w 592
    //   91: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   94: aastore
    //   95: dup
    //   96: bipush 10
    //   98: ldc_w 594
    //   101: aastore
    //   102: dup
    //   103: bipush 11
    //   105: aload_0
    //   106: ldc_w 594
    //   109: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   112: aastore
    //   113: dup
    //   114: bipush 12
    //   116: ldc_w 596
    //   119: aastore
    //   120: dup
    //   121: bipush 13
    //   123: aload_0
    //   124: ldc_w 596
    //   127: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   130: aastore
    //   131: dup
    //   132: bipush 14
    //   134: ldc_w 598
    //   137: aastore
    //   138: dup
    //   139: bipush 15
    //   141: aload_0
    //   142: ldc_w 598
    //   145: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   148: aastore
    //   149: dup
    //   150: bipush 16
    //   152: ldc_w 600
    //   155: aastore
    //   156: dup
    //   157: bipush 17
    //   159: aload_0
    //   160: ldc_w 600
    //   163: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   166: aastore
    //   167: dup
    //   168: bipush 18
    //   170: ldc_w 602
    //   173: aastore
    //   174: dup
    //   175: bipush 19
    //   177: aload_0
    //   178: ldc_w 602
    //   181: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   184: aastore
    //   185: dup
    //   186: bipush 20
    //   188: ldc_w 604
    //   191: aastore
    //   192: dup
    //   193: bipush 21
    //   195: aload_0
    //   196: ldc_w 604
    //   199: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   202: aastore
    //   203: dup
    //   204: bipush 22
    //   206: ldc_w 429
    //   209: aastore
    //   210: dup
    //   211: bipush 23
    //   213: aload_0
    //   214: ldc_w 429
    //   217: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   220: aastore
    //   221: dup
    //   222: bipush 24
    //   224: ldc_w 606
    //   227: aastore
    //   228: dup
    //   229: bipush 25
    //   231: aload_0
    //   232: ldc_w 606
    //   235: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   238: aastore
    //   239: dup
    //   240: bipush 26
    //   242: ldc_w 608
    //   245: aastore
    //   246: dup
    //   247: bipush 27
    //   249: aload_0
    //   250: ldc_w 608
    //   253: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   256: aastore
    //   257: dup
    //   258: bipush 28
    //   260: ldc_w 610
    //   263: aastore
    //   264: dup
    //   265: bipush 29
    //   267: aload_0
    //   268: ldc_w 610
    //   271: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   274: aastore
    //   275: dup
    //   276: bipush 30
    //   278: ldc_w 517
    //   281: aastore
    //   282: dup
    //   283: bipush 31
    //   285: aload_0
    //   286: ldc_w 517
    //   289: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   292: aastore
    //   293: dup
    //   294: bipush 32
    //   296: ldc_w 519
    //   299: aastore
    //   300: dup
    //   301: bipush 33
    //   303: aload_0
    //   304: ldc_w 519
    //   307: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   310: aastore
    //   311: dup
    //   312: bipush 34
    //   314: ldc 49
    //   316: aastore
    //   317: dup
    //   318: bipush 35
    //   320: aload_0
    //   321: ldc 49
    //   323: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   326: aastore
    //   327: dup
    //   328: bipush 36
    //   330: ldc_w 709
    //   333: aastore
    //   334: dup
    //   335: bipush 37
    //   337: aload_0
    //   338: ldc_w 709
    //   341: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   344: aastore
    //   345: dup
    //   346: bipush 38
    //   348: ldc_w 614
    //   351: aastore
    //   352: dup
    //   353: bipush 39
    //   355: aload_0
    //   356: ldc_w 614
    //   359: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   362: aastore
    //   363: dup
    //   364: bipush 40
    //   366: ldc_w 618
    //   369: aastore
    //   370: dup
    //   371: bipush 41
    //   373: aload_0
    //   374: ldc_w 618
    //   377: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   380: aastore
    //   381: invokestatic 713	cn/com/xy/sms/sdk/db/base/BaseManager:getContentValues	(Landroid/content/ContentValues;[Ljava/lang/String;)Landroid/content/ContentValues;
    //   384: astore 5
    //   386: ldc 8
    //   388: aload 5
    //   390: ldc_w 652
    //   393: iconst_1
    //   394: anewarray 57	java/lang/String
    //   397: dup
    //   398: iconst_0
    //   399: aload_0
    //   400: ldc_w 338
    //   403: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   406: aastore
    //   407: invokestatic 656	cn/com/xy/sms/sdk/db/DBManager:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   410: i2l
    //   411: lconst_1
    //   412: lcmp
    //   413: ifge +11 -> 424
    //   416: ldc 8
    //   418: aload 5
    //   420: invokestatic 717	cn/com/xy/sms/sdk/db/DBManager:insert	(Ljava/lang/String;Landroid/content/ContentValues;)J
    //   423: pop2
    //   424: aload_0
    //   425: ldc_w 719
    //   428: invokevirtual 722	org/json/JSONObject:optJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   431: astore 5
    //   433: aload 5
    //   435: ifnull +51 -> 486
    //   438: aload 5
    //   440: invokevirtual 584	org/json/JSONArray:length	()I
    //   443: ifle +43 -> 486
    //   446: aload 5
    //   448: invokevirtual 584	org/json/JSONArray:length	()I
    //   451: istore_2
    //   452: aload_0
    //   453: ldc_w 338
    //   456: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   459: astore 6
    //   461: ldc 14
    //   463: ldc_w 724
    //   466: iconst_1
    //   467: anewarray 57	java/lang/String
    //   470: dup
    //   471: iconst_0
    //   472: aload 6
    //   474: aastore
    //   475: invokestatic 728	cn/com/xy/sms/sdk/db/DBManager:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   478: pop
    //   479: iconst_0
    //   480: istore_1
    //   481: iload_1
    //   482: iload_2
    //   483: if_icmplt +93 -> 576
    //   486: aload_0
    //   487: ldc_w 730
    //   490: invokevirtual 722	org/json/JSONObject:optJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   493: astore 5
    //   495: aload 5
    //   497: ifnull -493 -> 4
    //   500: aload 5
    //   502: invokevirtual 584	org/json/JSONArray:length	()I
    //   505: ifle -501 -> 4
    //   508: aload 5
    //   510: invokevirtual 584	org/json/JSONArray:length	()I
    //   513: istore_3
    //   514: aload_0
    //   515: ldc_w 338
    //   518: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   521: astore_0
    //   522: ldc 11
    //   524: ldc_w 652
    //   527: iconst_1
    //   528: anewarray 57	java/lang/String
    //   531: dup
    //   532: iconst_0
    //   533: aload_0
    //   534: aastore
    //   535: invokestatic 728	cn/com/xy/sms/sdk/db/DBManager:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   538: pop
    //   539: iconst_0
    //   540: istore_1
    //   541: iload_1
    //   542: iload_3
    //   543: if_icmpge -539 -> 4
    //   546: aload 5
    //   548: iload_1
    //   549: invokevirtual 732	org/json/JSONArray:optJSONObject	(I)Lorg/json/JSONObject;
    //   552: astore_0
    //   553: aload_0
    //   554: ifnonnull +333 -> 887
    //   557: goto +406 -> 963
    //   560: astore 6
    //   562: aload 6
    //   564: invokevirtual 79	java/lang/Throwable:printStackTrace	()V
    //   567: goto -88 -> 479
    //   570: astore_0
    //   571: aload_0
    //   572: invokevirtual 79	java/lang/Throwable:printStackTrace	()V
    //   575: return
    //   576: aload 5
    //   578: iload_1
    //   579: invokevirtual 732	org/json/JSONArray:optJSONObject	(I)Lorg/json/JSONObject;
    //   582: astore 6
    //   584: aload 6
    //   586: ifnonnull +10 -> 596
    //   589: iload_1
    //   590: iconst_1
    //   591: iadd
    //   592: istore_1
    //   593: goto -112 -> 481
    //   596: aload 6
    //   598: invokestatic 734	cn/com/xy/sms/sdk/db/entity/a/e:f	(Lorg/json/JSONObject;)V
    //   601: ldc 14
    //   603: aconst_null
    //   604: bipush 26
    //   606: anewarray 57	java/lang/String
    //   609: dup
    //   610: iconst_0
    //   611: ldc_w 338
    //   614: aastore
    //   615: dup
    //   616: iconst_1
    //   617: aload 6
    //   619: ldc_w 338
    //   622: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   625: aastore
    //   626: dup
    //   627: iconst_2
    //   628: ldc_w 441
    //   631: aastore
    //   632: dup
    //   633: iconst_3
    //   634: aload 6
    //   636: ldc_w 441
    //   639: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   642: aastore
    //   643: dup
    //   644: iconst_4
    //   645: ldc_w 736
    //   648: aastore
    //   649: dup
    //   650: iconst_5
    //   651: aload 6
    //   653: ldc_w 736
    //   656: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   659: aastore
    //   660: dup
    //   661: bipush 6
    //   663: ldc_w 738
    //   666: aastore
    //   667: dup
    //   668: bipush 7
    //   670: aload 6
    //   672: ldc_w 738
    //   675: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   678: aastore
    //   679: dup
    //   680: bipush 8
    //   682: ldc_w 472
    //   685: aastore
    //   686: dup
    //   687: bipush 9
    //   689: aload 6
    //   691: ldc_w 472
    //   694: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   697: aastore
    //   698: dup
    //   699: bipush 10
    //   701: ldc_w 473
    //   704: aastore
    //   705: dup
    //   706: bipush 11
    //   708: aload 6
    //   710: ldc_w 473
    //   713: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   716: aastore
    //   717: dup
    //   718: bipush 12
    //   720: ldc 49
    //   722: aastore
    //   723: dup
    //   724: bipush 13
    //   726: aload 6
    //   728: ldc 49
    //   730: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   733: aastore
    //   734: dup
    //   735: bipush 14
    //   737: ldc_w 740
    //   740: aastore
    //   741: dup
    //   742: bipush 15
    //   744: aload 6
    //   746: ldc_w 274
    //   749: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   752: aastore
    //   753: dup
    //   754: bipush 16
    //   756: ldc_w 742
    //   759: aastore
    //   760: dup
    //   761: bipush 17
    //   763: aload 6
    //   765: ldc_w 742
    //   768: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   771: aastore
    //   772: dup
    //   773: bipush 18
    //   775: ldc_w 744
    //   778: aastore
    //   779: dup
    //   780: bipush 19
    //   782: aload 6
    //   784: ldc_w 744
    //   787: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   790: aastore
    //   791: dup
    //   792: bipush 20
    //   794: ldc_w 746
    //   797: aastore
    //   798: dup
    //   799: bipush 21
    //   801: aload 6
    //   803: ldc_w 746
    //   806: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   809: aastore
    //   810: dup
    //   811: bipush 22
    //   813: ldc_w 748
    //   816: aastore
    //   817: dup
    //   818: bipush 23
    //   820: aload 6
    //   822: ldc_w 748
    //   825: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   828: aastore
    //   829: dup
    //   830: bipush 24
    //   832: ldc_w 750
    //   835: aastore
    //   836: dup
    //   837: bipush 25
    //   839: aload 6
    //   841: ldc_w 750
    //   844: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   847: aastore
    //   848: invokestatic 713	cn/com/xy/sms/sdk/db/base/BaseManager:getContentValues	(Landroid/content/ContentValues;[Ljava/lang/String;)Landroid/content/ContentValues;
    //   851: invokestatic 717	cn/com/xy/sms/sdk/db/DBManager:insert	(Ljava/lang/String;Landroid/content/ContentValues;)J
    //   854: pop2
    //   855: aload 6
    //   857: ldc_w 441
    //   860: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   863: aload 6
    //   865: ldc_w 473
    //   868: invokevirtual 326	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   871: invokestatic 754	cn/com/xy/sms/sdk/db/entity/g:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   874: goto -285 -> 589
    //   877: astore 6
    //   879: aload 6
    //   881: invokevirtual 79	java/lang/Throwable:printStackTrace	()V
    //   884: goto -295 -> 589
    //   887: aload_0
    //   888: ldc_w 258
    //   891: invokevirtual 722	org/json/JSONObject:optJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   894: astore 6
    //   896: aload 6
    //   898: ifnull +46 -> 944
    //   901: aload 6
    //   903: invokevirtual 584	org/json/JSONArray:length	()I
    //   906: ifle +38 -> 944
    //   909: aload_0
    //   910: invokestatic 756	cn/com/xy/sms/sdk/db/entity/a/e:d	(Lorg/json/JSONObject;)V
    //   913: aload 6
    //   915: invokevirtual 584	org/json/JSONArray:length	()I
    //   918: istore 4
    //   920: iconst_0
    //   921: istore_2
    //   922: iload_2
    //   923: iload 4
    //   925: if_icmpge +38 -> 963
    //   928: aload 6
    //   930: iload_2
    //   931: invokevirtual 246	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   934: invokestatic 756	cn/com/xy/sms/sdk/db/entity/a/e:d	(Lorg/json/JSONObject;)V
    //   937: iload_2
    //   938: iconst_1
    //   939: iadd
    //   940: istore_2
    //   941: goto -19 -> 922
    //   944: aload_0
    //   945: invokestatic 756	cn/com/xy/sms/sdk/db/entity/a/e:d	(Lorg/json/JSONObject;)V
    //   948: goto +15 -> 963
    //   951: astore_0
    //   952: aload_0
    //   953: invokevirtual 79	java/lang/Throwable:printStackTrace	()V
    //   956: goto +7 -> 963
    //   959: astore_0
    //   960: goto -421 -> 539
    //   963: iload_1
    //   964: iconst_1
    //   965: iadd
    //   966: istore_1
    //   967: goto -426 -> 541
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	970	0	paramJSONObject	JSONObject
    //   480	487	1	m	int
    //   451	490	2	n	int
    //   513	31	3	i1	int
    //   918	8	4	i2	int
    //   384	193	5	localObject	Object
    //   459	14	6	str	String
    //   560	3	6	localThrowable1	Throwable
    //   582	282	6	localJSONObject	JSONObject
    //   877	3	6	localThrowable2	Throwable
    //   894	35	6	localJSONArray	JSONArray
    // Exception table:
    //   from	to	target	type
    //   461	479	560	java/lang/Throwable
    //   5	424	570	java/lang/Throwable
    //   424	433	570	java/lang/Throwable
    //   438	461	570	java/lang/Throwable
    //   486	495	570	java/lang/Throwable
    //   500	522	570	java/lang/Throwable
    //   546	553	570	java/lang/Throwable
    //   562	567	570	java/lang/Throwable
    //   576	584	570	java/lang/Throwable
    //   879	884	570	java/lang/Throwable
    //   952	956	570	java/lang/Throwable
    //   596	874	877	java/lang/Throwable
    //   887	896	951	java/lang/Throwable
    //   901	920	951	java/lang/Throwable
    //   928	937	951	java/lang/Throwable
    //   944	948	951	java/lang/Throwable
    //   522	539	959	java/lang/Throwable
  }
  
  private static boolean a(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, String paramString3)
  {
    int m = paramString2.indexOf("*");
    String str;
    if (m >= 0)
    {
      str = paramString2.substring(0, m);
      paramString2 = paramString2.substring(m + 1);
      if ((paramString1.startsWith(str)) && (paramString1.endsWith(paramString2))) {
        break label51;
      }
    }
    label51:
    int n;
    do
    {
      do
      {
        do
        {
          return false;
          m = str.length();
          m = paramString2.length() + m;
          n = paramString1.length();
          if (paramInt1 <= 0) {
            break;
          }
        } while ((m + paramInt1 != n) || (("sj".equals(paramString3)) && (!StringUtils.sj(paramString1))));
        return true;
      } while ((paramInt2 > 0) && (n > m + paramInt2));
    } while ((paramInt3 <= 0) || (n >= m + paramInt3));
    return false;
  }
  
  /* Error */
  public static int b(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: invokestatic 71	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   7: ifeq +5 -> 12
    //   10: iconst_m1
    //   11: ireturn
    //   12: ldc_w 759
    //   15: iconst_1
    //   16: anewarray 57	java/lang/String
    //   19: dup
    //   20: iconst_0
    //   21: aload_0
    //   22: aastore
    //   23: invokestatic 418	cn/com/xy/sms/sdk/db/DBManager:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   26: astore_3
    //   27: aload_3
    //   28: ifnull +14 -> 42
    //   31: aload_3
    //   32: astore_2
    //   33: aload_3
    //   34: invokevirtual 424	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   37: istore_1
    //   38: iload_1
    //   39: ifgt +19 -> 58
    //   42: aload_3
    //   43: iconst_1
    //   44: invokestatic 436	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   47: iconst_m1
    //   48: ireturn
    //   49: aload_3
    //   50: astore_2
    //   51: aload_3
    //   52: iconst_0
    //   53: invokevirtual 438	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   56: astore 4
    //   58: aload_3
    //   59: astore_2
    //   60: aload_3
    //   61: invokevirtual 432	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   64: ifne -15 -> 49
    //   67: aload_3
    //   68: astore_2
    //   69: aload 4
    //   71: invokestatic 60	cn/com/xy/sms/sdk/db/entity/a/e:g	(Ljava/lang/String;)I
    //   74: istore_1
    //   75: aload_3
    //   76: iconst_1
    //   77: invokestatic 436	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   80: iload_1
    //   81: ireturn
    //   82: astore 4
    //   84: aconst_null
    //   85: astore_3
    //   86: aload_3
    //   87: astore_2
    //   88: ldc_w 761
    //   91: new 92	java/lang/StringBuilder
    //   94: dup
    //   95: ldc_w 763
    //   98: invokespecial 97	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   101: aload_0
    //   102: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   108: aload 4
    //   110: invokestatic 109	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   113: aload_3
    //   114: iconst_1
    //   115: invokestatic 436	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   118: iconst_m1
    //   119: ireturn
    //   120: astore_0
    //   121: aconst_null
    //   122: astore_2
    //   123: aload_2
    //   124: iconst_1
    //   125: invokestatic 436	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   128: aload_0
    //   129: athrow
    //   130: astore_0
    //   131: goto -8 -> 123
    //   134: astore 4
    //   136: goto -50 -> 86
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	paramString	String
    //   37	44	1	m	int
    //   32	92	2	localXyCursor1	XyCursor
    //   26	88	3	localXyCursor2	XyCursor
    //   1	69	4	str	String
    //   82	27	4	localThrowable1	Throwable
    //   134	1	4	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   12	27	82	java/lang/Throwable
    //   12	27	120	finally
    //   33	38	130	finally
    //   51	58	130	finally
    //   60	67	130	finally
    //   69	75	130	finally
    //   88	113	130	finally
    //   33	38	134	java/lang/Throwable
    //   51	58	134	java/lang/Throwable
    //   60	67	134	java/lang/Throwable
    //   69	75	134	java/lang/Throwable
  }
  
  public static String b()
  {
    return "create table  if not exists tb_public_menu_info ( id INTEGER PRIMARY KEY, menuCode text not null, pubId INTEGER, menuName text not null, menuType text not null, sendTo text, sp text , menuDesc text , sms text, url text, phoneNum text  , actionData text  , extend text  )";
  }
  
  /* Error */
  private static HashMap<String, String> b(String paramString1, String paramString2, int paramInt)
  {
    // Byte code:
    //   0: invokestatic 769	cn/com/xy/sms/sdk/db/DBManager:getSQLiteDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   3: astore 4
    //   5: aload 4
    //   7: astore_3
    //   8: new 92	java/lang/StringBuilder
    //   11: dup
    //   12: ldc_w 771
    //   15: invokespecial 97	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   18: aload_0
    //   19: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: ldc_w 773
    //   25: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: iload_2
    //   29: invokevirtual 101	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   32: ldc_w 775
    //   35: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   41: astore 6
    //   43: aload 6
    //   45: astore 5
    //   47: aload 4
    //   49: astore_3
    //   50: ldc 85
    //   52: aload_1
    //   53: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   56: ifeq +29 -> 85
    //   59: aload 4
    //   61: astore_3
    //   62: new 92	java/lang/StringBuilder
    //   65: dup
    //   66: aload 6
    //   68: invokestatic 223	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   71: invokespecial 97	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   74: ldc_w 777
    //   77: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   83: astore 5
    //   85: aload 4
    //   87: astore_3
    //   88: aload 4
    //   90: aload_0
    //   91: new 92	java/lang/StringBuilder
    //   94: dup
    //   95: aload 5
    //   97: invokestatic 223	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   100: invokespecial 97	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   103: ldc_w 779
    //   106: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   112: aload_1
    //   113: iload_2
    //   114: invokestatic 781	cn/com/xy/sms/sdk/db/entity/a/e:a	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)Ljava/util/HashMap;
    //   117: astore_0
    //   118: aload 4
    //   120: invokestatic 785	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   123: aload_0
    //   124: areturn
    //   125: astore_0
    //   126: aconst_null
    //   127: astore 4
    //   129: aload 4
    //   131: astore_3
    //   132: aload_0
    //   133: invokevirtual 79	java/lang/Throwable:printStackTrace	()V
    //   136: aload 4
    //   138: invokestatic 785	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   141: aconst_null
    //   142: areturn
    //   143: astore_0
    //   144: aconst_null
    //   145: astore_3
    //   146: aload_3
    //   147: invokestatic 785	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   150: aload_0
    //   151: athrow
    //   152: astore_0
    //   153: goto -7 -> 146
    //   156: astore_0
    //   157: goto -28 -> 129
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	160	0	paramString1	String
    //   0	160	1	paramString2	String
    //   0	160	2	paramInt	int
    //   7	140	3	localSQLiteDatabase1	android.database.sqlite.SQLiteDatabase
    //   3	134	4	localSQLiteDatabase2	android.database.sqlite.SQLiteDatabase
    //   45	51	5	str1	String
    //   41	26	6	str2	String
    // Exception table:
    //   from	to	target	type
    //   0	5	125	java/lang/Throwable
    //   0	5	143	finally
    //   8	43	152	finally
    //   50	59	152	finally
    //   62	85	152	finally
    //   88	118	152	finally
    //   132	136	152	finally
    //   8	43	156	java/lang/Throwable
    //   50	59	156	java/lang/Throwable
    //   62	85	156	java/lang/Throwable
    //   88	118	156	java/lang/Throwable
  }
  
  public static HashMap<String, String> b(Set<String> paramSet)
  {
    Object localObject4 = null;
    Object localObject3 = null;
    if ((paramSet == null) || (paramSet.isEmpty())) {
      return null;
    }
    localHashMap = new HashMap();
    localObject2 = localObject3;
    localObject1 = localObject4;
    for (;;)
    {
      try
      {
        localStringBuffer = new StringBuffer();
        localObject2 = localObject3;
        localObject1 = localObject4;
        paramSet = paramSet.iterator();
        localObject2 = localObject3;
        localObject1 = localObject4;
        if (paramSet.hasNext()) {
          continue;
        }
        localObject2 = localObject3;
        localObject1 = localObject4;
        localStringBuffer.deleteCharAt(localStringBuffer.length() - 1);
        localObject2 = localObject3;
        localObject1 = localObject4;
        paramSet = DBManager.rawQuery("SELECT pubId,num from tb_public_num_info where num in  (" + localStringBuffer.toString() + ")", null);
        if (paramSet == null) {
          continue;
        }
        localObject2 = paramSet;
        localObject1 = paramSet;
        if (paramSet.getCount() <= 0) {
          continue;
        }
        localObject2 = paramSet;
        localObject1 = paramSet;
        m = paramSet.getColumnIndex("pubId");
        localObject2 = paramSet;
        localObject1 = paramSet;
        n = paramSet.getColumnIndex("num");
      }
      catch (Throwable paramSet)
      {
        StringBuffer localStringBuffer;
        int m;
        int n;
        boolean bool;
        localObject1 = localObject2;
        paramSet.printStackTrace();
        XyCursor.closeCursor((XyCursor)localObject2, true);
        continue;
        localObject2 = paramSet;
        localObject1 = paramSet;
        localHashMap.put(paramSet.getString(n), paramSet.getString(m));
        continue;
      }
      finally
      {
        XyCursor.closeCursor((XyCursor)localObject1, true);
      }
      localObject2 = paramSet;
      localObject1 = paramSet;
      bool = paramSet.moveToNext();
      if (bool) {
        continue;
      }
      XyCursor.closeCursor(paramSet, true);
      return localHashMap;
      localObject2 = localObject3;
      localObject1 = localObject4;
      localStringBuffer.append((String)paramSet.next() + ",");
    }
  }
  
  /* Error */
  private static JSONArray b(int paramInt)
  {
    // Byte code:
    //   0: ldc 11
    //   2: iconst_4
    //   3: anewarray 57	java/lang/String
    //   6: dup
    //   7: iconst_0
    //   8: ldc_w 791
    //   11: aastore
    //   12: dup
    //   13: iconst_1
    //   14: ldc_w 334
    //   17: aastore
    //   18: dup
    //   19: iconst_2
    //   20: ldc_w 793
    //   23: aastore
    //   24: dup
    //   25: iconst_3
    //   26: ldc_w 795
    //   29: aastore
    //   30: ldc_w 620
    //   33: iconst_1
    //   34: anewarray 57	java/lang/String
    //   37: dup
    //   38: iconst_0
    //   39: iload_0
    //   40: invokestatic 119	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   43: aastore
    //   44: aconst_null
    //   45: aconst_null
    //   46: ldc_w 797
    //   49: aconst_null
    //   50: invokestatic 800	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   53: astore_1
    //   54: aload_1
    //   55: ifnonnull +10 -> 65
    //   58: aload_1
    //   59: iconst_1
    //   60: invokestatic 436	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   63: aconst_null
    //   64: areturn
    //   65: new 51	java/util/HashMap
    //   68: dup
    //   69: invokespecial 467	java/util/HashMap:<init>	()V
    //   72: astore_3
    //   73: new 241	org/json/JSONArray
    //   76: dup
    //   77: invokespecial 801	org/json/JSONArray:<init>	()V
    //   80: astore 4
    //   82: new 803	java/util/HashSet
    //   85: dup
    //   86: invokespecial 804	java/util/HashSet:<init>	()V
    //   89: astore 5
    //   91: aload_1
    //   92: invokevirtual 432	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   95: ifne +20 -> 115
    //   98: aload_3
    //   99: invokevirtual 807	java/util/HashMap:clear	()V
    //   102: aload 5
    //   104: invokevirtual 808	java/util/HashSet:clear	()V
    //   107: aload_1
    //   108: iconst_1
    //   109: invokestatic 436	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   112: aload 4
    //   114: areturn
    //   115: aload_1
    //   116: iconst_0
    //   117: invokevirtual 438	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   120: astore 6
    //   122: aload 5
    //   124: aload 6
    //   126: invokevirtual 810	java/util/HashSet:contains	(Ljava/lang/Object;)Z
    //   129: ifne -38 -> 91
    //   132: aload 5
    //   134: aload 6
    //   136: invokevirtual 811	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   139: pop
    //   140: aload_1
    //   141: iconst_1
    //   142: invokevirtual 438	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   145: astore_2
    //   146: aload_1
    //   147: iconst_2
    //   148: invokevirtual 438	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   151: astore 7
    //   153: aload_1
    //   154: iconst_3
    //   155: invokevirtual 438	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   158: astore 8
    //   160: aload 6
    //   162: invokevirtual 497	java/lang/String:length	()I
    //   165: iconst_2
    //   166: if_icmpne +145 -> 311
    //   169: ldc_w 813
    //   172: aload 7
    //   174: invokevirtual 88	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   177: ifne +68 -> 245
    //   180: bipush 6
    //   182: anewarray 57	java/lang/String
    //   185: dup
    //   186: iconst_0
    //   187: ldc -8
    //   189: aastore
    //   190: dup
    //   191: iconst_1
    //   192: aload_2
    //   193: aastore
    //   194: dup
    //   195: iconst_2
    //   196: ldc_w 274
    //   199: aastore
    //   200: dup
    //   201: iconst_3
    //   202: aload 7
    //   204: aastore
    //   205: dup
    //   206: iconst_4
    //   207: ldc -24
    //   209: aastore
    //   210: dup
    //   211: iconst_5
    //   212: aload 8
    //   214: aastore
    //   215: invokestatic 282	cn/com/xy/sms/sdk/util/JsonUtil:getJsonObject	([Ljava/lang/String;)Lorg/json/JSONObject;
    //   218: astore_2
    //   219: aload_2
    //   220: ifnull -129 -> 91
    //   223: aload 4
    //   225: aload_2
    //   226: invokevirtual 285	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   229: pop
    //   230: goto -139 -> 91
    //   233: astore_2
    //   234: aload_2
    //   235: invokevirtual 79	java/lang/Throwable:printStackTrace	()V
    //   238: aload_1
    //   239: iconst_1
    //   240: invokestatic 436	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   243: aconst_null
    //   244: areturn
    //   245: iconst_4
    //   246: anewarray 57	java/lang/String
    //   249: dup
    //   250: iconst_0
    //   251: ldc -8
    //   253: aastore
    //   254: dup
    //   255: iconst_1
    //   256: aload_2
    //   257: aastore
    //   258: dup
    //   259: iconst_2
    //   260: ldc_w 274
    //   263: aastore
    //   264: dup
    //   265: iconst_3
    //   266: aload 7
    //   268: aastore
    //   269: invokestatic 282	cn/com/xy/sms/sdk/util/JsonUtil:getJsonObject	([Ljava/lang/String;)Lorg/json/JSONObject;
    //   272: astore_2
    //   273: aload_2
    //   274: ldc_w 258
    //   277: new 241	org/json/JSONArray
    //   280: dup
    //   281: invokespecial 801	org/json/JSONArray:<init>	()V
    //   284: invokevirtual 256	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   287: pop
    //   288: aload_3
    //   289: aload 6
    //   291: aload_2
    //   292: invokevirtual 470	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   295: pop
    //   296: goto -77 -> 219
    //   299: astore_3
    //   300: aload_1
    //   301: astore_2
    //   302: aload_3
    //   303: astore_1
    //   304: aload_2
    //   305: iconst_1
    //   306: invokestatic 436	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   309: aload_1
    //   310: athrow
    //   311: aload 6
    //   313: invokevirtual 497	java/lang/String:length	()I
    //   316: iconst_4
    //   317: if_icmpne -226 -> 91
    //   320: bipush 6
    //   322: anewarray 57	java/lang/String
    //   325: dup
    //   326: iconst_0
    //   327: ldc -8
    //   329: aastore
    //   330: dup
    //   331: iconst_1
    //   332: aload_2
    //   333: aastore
    //   334: dup
    //   335: iconst_2
    //   336: ldc_w 274
    //   339: aastore
    //   340: dup
    //   341: iconst_3
    //   342: aload 7
    //   344: aastore
    //   345: dup
    //   346: iconst_4
    //   347: ldc -24
    //   349: aastore
    //   350: dup
    //   351: iconst_5
    //   352: aload 8
    //   354: aastore
    //   355: invokestatic 282	cn/com/xy/sms/sdk/util/JsonUtil:getJsonObject	([Ljava/lang/String;)Lorg/json/JSONObject;
    //   358: astore_2
    //   359: aload_3
    //   360: aload 6
    //   362: iconst_0
    //   363: iconst_2
    //   364: invokevirtual 487	java/lang/String:substring	(II)Ljava/lang/String;
    //   367: invokevirtual 55	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   370: checkcast 252	org/json/JSONObject
    //   373: ldc_w 258
    //   376: invokevirtual 722	org/json/JSONObject:optJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   379: aload_2
    //   380: invokevirtual 285	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   383: pop
    //   384: goto -293 -> 91
    //   387: astore_1
    //   388: aconst_null
    //   389: astore_2
    //   390: goto -86 -> 304
    //   393: astore_3
    //   394: aload_1
    //   395: astore_2
    //   396: aload_3
    //   397: astore_1
    //   398: goto -94 -> 304
    //   401: astore_2
    //   402: aconst_null
    //   403: astore_1
    //   404: goto -170 -> 234
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	407	0	paramInt	int
    //   53	257	1	localObject1	Object
    //   387	8	1	localObject2	Object
    //   397	7	1	localObject3	Object
    //   145	81	2	localObject4	Object
    //   233	24	2	localThrowable1	Throwable
    //   272	124	2	localObject5	Object
    //   401	1	2	localThrowable2	Throwable
    //   72	217	3	localHashMap	HashMap
    //   299	61	3	localObject6	Object
    //   393	4	3	localObject7	Object
    //   80	144	4	localJSONArray	JSONArray
    //   89	44	5	localHashSet	java.util.HashSet
    //   120	241	6	str1	String
    //   151	192	7	str2	String
    //   158	195	8	str3	String
    // Exception table:
    //   from	to	target	type
    //   65	91	233	java/lang/Throwable
    //   91	107	233	java/lang/Throwable
    //   115	219	233	java/lang/Throwable
    //   223	230	233	java/lang/Throwable
    //   245	296	233	java/lang/Throwable
    //   311	384	233	java/lang/Throwable
    //   65	91	299	finally
    //   91	107	299	finally
    //   115	219	299	finally
    //   223	230	299	finally
    //   245	296	299	finally
    //   311	384	299	finally
    //   0	54	387	finally
    //   234	238	393	finally
    //   0	54	401	java/lang/Throwable
  }
  
  private static void b(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {
      return;
    }
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("loadMenuTime", Long.valueOf(System.currentTimeMillis()));
      localContentValues.put("updateInfoTime", Long.valueOf(System.currentTimeMillis()));
      DBManager.update("tb_public_info", localContentValues, "pubId = ?", new String[] { paramJSONObject.optString("pubId") });
      return;
    }
    catch (Throwable paramJSONObject)
    {
      paramJSONObject.printStackTrace();
    }
  }
  
  public static int c(String paramString)
  {
    if (StringUtils.isNull(paramString)) {
      return -1;
    }
    try
    {
      int m = Integer.parseInt(paramString);
      return m;
    }
    catch (Throwable localThrowable)
    {
      LogManager.e("PubInfoManager parseSimIndex", "无效的卡位,simIndexStr=" + paramString, localThrowable);
    }
    return -1;
  }
  
  public static String c()
  {
    return "create table  if not exists tb_public_num_info ( id INTEGER PRIMARY KEY, pubId INTEGER not null, num text not null, purpose text , areaCode text not null, ptype int default 1, main INTEGER default 0, communication INTEGER default 0, isfull INTEGER default 0, minLen INTEGER default 0, maxLen INTEGER default 0, len INTEGER default 0, ntype text, extend text, lastloadtime LONG default 0, isrulenum INTEGER default 0)";
  }
  
  private static String c(int paramInt)
  {
    Object localObject = IccidLocationUtil.getIccidAreaCodeMap();
    if ((localObject == null) || (((HashMap)localObject).isEmpty())) {
      return "CN";
    }
    localObject = ((HashMap)localObject).entrySet().iterator();
    Map.Entry localEntry;
    String[] arrayOfString;
    int m;
    do
    {
      if (!((Iterator)localObject).hasNext()) {
        return "CN";
      }
      localEntry = (Map.Entry)((Iterator)localObject).next();
      arrayOfString = (String[])localEntry.getValue();
      m = a(arrayOfString);
    } while ((m == -2) || (m != paramInt) || (arrayOfString == null) || (arrayOfString.length <= 0) || (StringUtils.isNull(arrayOfString[0])));
    new StringBuilder("当前接入码所属运营商与其中一张sim卡的iccid所属运营商相同时返回对应sim卡的区域编码，numOperator=").append(paramInt).append(" AreaCode=").append(arrayOfString[0]).append(" ICCID=").append((String)localEntry.getKey());
    return arrayOfString[0] + "_" + (String)localEntry.getKey();
  }
  
  /* Error */
  private static HashMap<String, String> c(String paramString1, String paramString2, int paramInt)
  {
    // Byte code:
    //   0: new 92	java/lang/StringBuilder
    //   3: dup
    //   4: ldc_w 821
    //   7: invokespecial 97	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   10: aload_0
    //   11: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   14: ldc_w 823
    //   17: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   20: iload_2
    //   21: invokevirtual 101	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   24: ldc_w 825
    //   27: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: aload_1
    //   31: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: ldc_w 827
    //   37: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   43: aconst_null
    //   44: invokestatic 418	cn/com/xy/sms/sdk/db/DBManager:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   47: astore 4
    //   49: aload 4
    //   51: ifnull +128 -> 179
    //   54: aload 4
    //   56: invokevirtual 424	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   59: ifle +120 -> 179
    //   62: aload 4
    //   64: invokevirtual 432	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   67: ifeq +606 -> 673
    //   70: aload 4
    //   72: iconst_0
    //   73: invokevirtual 522	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   76: istore_2
    //   77: aload 4
    //   79: iconst_1
    //   80: invokevirtual 438	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   83: astore 6
    //   85: aload 4
    //   87: iconst_2
    //   88: invokevirtual 438	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   91: astore 7
    //   93: iconst_0
    //   94: istore_3
    //   95: aload 6
    //   97: astore 5
    //   99: aconst_null
    //   100: astore 8
    //   102: aload 7
    //   104: astore 6
    //   106: aload 4
    //   108: astore 7
    //   110: aload_1
    //   111: astore 4
    //   113: aload 6
    //   115: astore_1
    //   116: aload 8
    //   118: astore 6
    //   120: iload_2
    //   121: ifle +24 -> 145
    //   124: iload_3
    //   125: ifne +20 -> 145
    //   128: aload 7
    //   130: astore 8
    //   132: new 679	cn/com/xy/sms/sdk/db/entity/a/i
    //   135: dup
    //   136: aload_0
    //   137: aload 4
    //   139: invokespecial 681	cn/com/xy/sms/sdk/db/entity/a/i:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   142: invokestatic 666	cn/com/xy/sms/sdk/net/NetUtil:executeRunnable	(Ljava/lang/Runnable;)V
    //   145: aload 6
    //   147: ifnull +347 -> 494
    //   150: iload_3
    //   151: ifeq +343 -> 494
    //   154: aload 7
    //   156: astore 8
    //   158: aload 6
    //   160: ldc_w 542
    //   163: ldc_w 297
    //   166: invokevirtual 470	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   169: pop
    //   170: aload 7
    //   172: iconst_1
    //   173: invokestatic 436	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   176: aload 6
    //   178: areturn
    //   179: aload 4
    //   181: iconst_1
    //   182: invokestatic 436	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   185: ldc 85
    //   187: aload_1
    //   188: invokevirtual 88	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   191: ifne +142 -> 333
    //   194: new 92	java/lang/StringBuilder
    //   197: dup
    //   198: ldc_w 821
    //   201: invokespecial 97	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   204: aload_0
    //   205: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: ldc_w 823
    //   211: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   214: iload_2
    //   215: invokevirtual 101	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   218: ldc_w 829
    //   221: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   224: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   227: aconst_null
    //   228: invokestatic 418	cn/com/xy/sms/sdk/db/DBManager:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   231: astore 5
    //   233: aload 5
    //   235: ifnull +67 -> 302
    //   238: aload 5
    //   240: invokevirtual 424	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   243: ifle +59 -> 302
    //   246: aload 5
    //   248: invokevirtual 432	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   251: ifeq +396 -> 647
    //   254: aload 5
    //   256: iconst_0
    //   257: invokevirtual 522	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   260: istore_2
    //   261: aload 5
    //   263: iconst_1
    //   264: invokevirtual 438	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   267: astore_1
    //   268: aload 5
    //   270: iconst_2
    //   271: invokevirtual 438	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   274: astore 4
    //   276: iconst_0
    //   277: istore_3
    //   278: aload 5
    //   280: astore 7
    //   282: aconst_null
    //   283: astore 6
    //   285: aload_1
    //   286: astore 5
    //   288: ldc 85
    //   290: astore 8
    //   292: aload 4
    //   294: astore_1
    //   295: aload 8
    //   297: astore 4
    //   299: goto -179 -> 120
    //   302: aload_0
    //   303: aload_1
    //   304: iload_2
    //   305: invokestatic 831	cn/com/xy/sms/sdk/db/entity/a/e:b	(Ljava/lang/String;Ljava/lang/String;I)Ljava/util/HashMap;
    //   308: astore 6
    //   310: iconst_1
    //   311: istore_3
    //   312: aconst_null
    //   313: astore 4
    //   315: aconst_null
    //   316: astore 8
    //   318: aload 5
    //   320: astore 7
    //   322: iconst_m1
    //   323: istore_2
    //   324: aconst_null
    //   325: astore_1
    //   326: aload 8
    //   328: astore 5
    //   330: goto -210 -> 120
    //   333: aload_0
    //   334: aload_1
    //   335: iload_2
    //   336: invokestatic 831	cn/com/xy/sms/sdk/db/entity/a/e:b	(Ljava/lang/String;Ljava/lang/String;I)Ljava/util/HashMap;
    //   339: astore 6
    //   341: iconst_1
    //   342: istore_3
    //   343: aconst_null
    //   344: astore 5
    //   346: iconst_m1
    //   347: istore_2
    //   348: aload 4
    //   350: astore 7
    //   352: aconst_null
    //   353: astore_1
    //   354: aconst_null
    //   355: astore 8
    //   357: aload 5
    //   359: astore 4
    //   361: aload 8
    //   363: astore 5
    //   365: goto -245 -> 120
    //   368: astore 6
    //   370: aconst_null
    //   371: astore_1
    //   372: aconst_null
    //   373: astore 4
    //   375: iconst_m1
    //   376: istore_2
    //   377: aconst_null
    //   378: astore 5
    //   380: aconst_null
    //   381: astore_0
    //   382: aload 5
    //   384: astore 8
    //   386: aload 6
    //   388: invokevirtual 79	java/lang/Throwable:printStackTrace	()V
    //   391: aload 5
    //   393: iconst_1
    //   394: invokestatic 436	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   397: iload_2
    //   398: iflt +114 -> 512
    //   401: new 51	java/util/HashMap
    //   404: dup
    //   405: invokespecial 467	java/util/HashMap:<init>	()V
    //   408: astore 5
    //   410: aload 5
    //   412: ldc_w 338
    //   415: iload_2
    //   416: invokestatic 119	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   419: invokevirtual 470	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   422: pop
    //   423: aload 4
    //   425: invokestatic 71	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   428: ifne +14 -> 442
    //   431: aload 5
    //   433: ldc_w 472
    //   436: aload 4
    //   438: invokevirtual 470	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   441: pop
    //   442: aload_1
    //   443: invokestatic 71	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   446: ifne +13 -> 459
    //   449: aload 5
    //   451: ldc_w 473
    //   454: aload_1
    //   455: invokevirtual 470	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   458: pop
    //   459: aload 5
    //   461: astore 6
    //   463: aload_0
    //   464: invokestatic 71	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   467: ifne -291 -> 176
    //   470: aload 5
    //   472: ldc 49
    //   474: aload_0
    //   475: invokevirtual 470	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   478: pop
    //   479: aload 5
    //   481: areturn
    //   482: astore_0
    //   483: aconst_null
    //   484: astore 4
    //   486: aload 4
    //   488: iconst_1
    //   489: invokestatic 436	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   492: aload_0
    //   493: athrow
    //   494: aload 7
    //   496: iconst_1
    //   497: invokestatic 436	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   500: aload_1
    //   501: astore_0
    //   502: aload 4
    //   504: astore_1
    //   505: aload 5
    //   507: astore 4
    //   509: goto -112 -> 397
    //   512: aconst_null
    //   513: areturn
    //   514: astore_0
    //   515: goto -29 -> 486
    //   518: astore_0
    //   519: aload 5
    //   521: astore 4
    //   523: goto -37 -> 486
    //   526: astore_0
    //   527: aload 8
    //   529: astore 4
    //   531: goto -45 -> 486
    //   534: astore 6
    //   536: aconst_null
    //   537: astore_1
    //   538: iconst_m1
    //   539: istore_2
    //   540: aload 4
    //   542: astore 5
    //   544: aconst_null
    //   545: astore_0
    //   546: aconst_null
    //   547: astore 4
    //   549: goto -167 -> 382
    //   552: astore 6
    //   554: aconst_null
    //   555: astore_0
    //   556: aload 4
    //   558: astore 5
    //   560: aconst_null
    //   561: astore_1
    //   562: aconst_null
    //   563: astore 4
    //   565: goto -183 -> 382
    //   568: astore_0
    //   569: aload 4
    //   571: astore 5
    //   573: aload 6
    //   575: astore 4
    //   577: aconst_null
    //   578: astore_1
    //   579: aconst_null
    //   580: astore 7
    //   582: aload_0
    //   583: astore 6
    //   585: aload 7
    //   587: astore_0
    //   588: goto -206 -> 382
    //   591: astore 6
    //   593: aconst_null
    //   594: astore_1
    //   595: aconst_null
    //   596: astore 4
    //   598: iconst_m1
    //   599: istore_2
    //   600: aconst_null
    //   601: astore_0
    //   602: goto -220 -> 382
    //   605: astore 6
    //   607: aconst_null
    //   608: astore_0
    //   609: aconst_null
    //   610: astore_1
    //   611: aconst_null
    //   612: astore 4
    //   614: goto -232 -> 382
    //   617: astore 6
    //   619: aconst_null
    //   620: astore_0
    //   621: aload_1
    //   622: astore 4
    //   624: aconst_null
    //   625: astore_1
    //   626: goto -244 -> 382
    //   629: astore 6
    //   631: aload_1
    //   632: astore_0
    //   633: aload 4
    //   635: astore_1
    //   636: aload 5
    //   638: astore 4
    //   640: aload 7
    //   642: astore 5
    //   644: goto -262 -> 382
    //   647: iconst_0
    //   648: istore_3
    //   649: aconst_null
    //   650: astore 4
    //   652: aconst_null
    //   653: astore 8
    //   655: aload 5
    //   657: astore 7
    //   659: iconst_m1
    //   660: istore_2
    //   661: aconst_null
    //   662: astore 6
    //   664: aconst_null
    //   665: astore_1
    //   666: aload 8
    //   668: astore 5
    //   670: goto -550 -> 120
    //   673: iconst_0
    //   674: istore_3
    //   675: aconst_null
    //   676: astore 5
    //   678: iconst_m1
    //   679: istore_2
    //   680: aload 4
    //   682: astore 7
    //   684: aconst_null
    //   685: astore 8
    //   687: aconst_null
    //   688: astore_1
    //   689: aconst_null
    //   690: astore 6
    //   692: aload 5
    //   694: astore 4
    //   696: aload 8
    //   698: astore 5
    //   700: goto -580 -> 120
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	703	0	paramString1	String
    //   0	703	1	paramString2	String
    //   0	703	2	paramInt	int
    //   94	581	3	m	int
    //   47	648	4	localObject1	Object
    //   97	602	5	localObject2	Object
    //   83	257	6	localObject3	Object
    //   368	19	6	localThrowable1	Throwable
    //   461	1	6	localObject4	Object
    //   534	1	6	localThrowable2	Throwable
    //   552	22	6	localThrowable3	Throwable
    //   583	1	6	str	String
    //   591	1	6	localThrowable4	Throwable
    //   605	1	6	localThrowable5	Throwable
    //   617	1	6	localThrowable6	Throwable
    //   629	1	6	localThrowable7	Throwable
    //   662	29	6	localObject5	Object
    //   91	592	7	localObject6	Object
    //   100	597	8	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   0	49	368	java/lang/Throwable
    //   0	49	482	finally
    //   54	77	514	finally
    //   77	85	514	finally
    //   85	93	514	finally
    //   179	233	514	finally
    //   333	341	514	finally
    //   238	261	518	finally
    //   261	268	518	finally
    //   268	276	518	finally
    //   302	310	518	finally
    //   132	145	526	finally
    //   158	170	526	finally
    //   386	391	526	finally
    //   54	77	534	java/lang/Throwable
    //   179	233	534	java/lang/Throwable
    //   333	341	534	java/lang/Throwable
    //   77	85	552	java/lang/Throwable
    //   85	93	568	java/lang/Throwable
    //   238	261	591	java/lang/Throwable
    //   302	310	591	java/lang/Throwable
    //   261	268	605	java/lang/Throwable
    //   268	276	617	java/lang/Throwable
    //   132	145	629	java/lang/Throwable
    //   158	170	629	java/lang/Throwable
  }
  
  private static void c(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {}
    for (;;)
    {
      return;
      try
      {
        JSONArray localJSONArray = paramJSONObject.optJSONArray("secondmenu");
        if ((localJSONArray != null) && (localJSONArray.length() > 0))
        {
          d(paramJSONObject);
          int n = localJSONArray.length();
          int m = 0;
          while (m < n)
          {
            d(localJSONArray.getJSONObject(m));
            m += 1;
          }
        }
        d(paramJSONObject);
        return;
      }
      catch (Throwable paramJSONObject)
      {
        paramJSONObject.printStackTrace();
      }
    }
  }
  
  public static String d(String paramString)
  {
    if ((StringUtils.isNull(paramString)) || (!paramString.contains("action_data"))) {
      return null;
    }
    JSONObject localJSONObject1 = new JSONObject();
    int n;
    int m;
    label52:
    label62:
    int i1;
    int i3;
    try
    {
      paramString = new JSONArray(paramString);
      int i2 = paramString.length();
      n = 0;
      m = 0;
      if (n < i2) {
        break label62;
      }
    }
    catch (Throwable paramString)
    {
      for (;;)
      {
        Object localObject;
        paramString.printStackTrace();
      }
    }
    if (localJSONObject1.length() == 0)
    {
      return null;
      localObject = paramString.getJSONObject(n);
      i1 = m;
      if (!((JSONObject)localObject).has("secondmenu")) {
        break label275;
      }
      localObject = ((JSONObject)localObject).getJSONArray("secondmenu");
      i1 = m;
      if (((JSONArray)localObject).length() == 0) {
        break label275;
      }
      i3 = ((JSONArray)localObject).length();
      i1 = 0;
    }
    label269:
    label275:
    label289:
    for (;;)
    {
      JSONObject localJSONObject2 = ((JSONArray)localObject).getJSONObject(i1);
      String str = localJSONObject2.getString("name");
      if ((str.contains("查")) && (str.contains("流量")))
      {
        localJSONObject1.put("queryTraffic", localJSONObject2);
      }
      else
      {
        if (((str.contains("查")) && (str.contains("话费"))) || ((str.contains("查")) && (str.contains("余额")))) {
          localJSONObject1.put("queryCharge", localJSONObject2);
        }
        if (localJSONObject1.has("queryTraffic"))
        {
          boolean bool = localJSONObject1.has("queryCharge");
          if (bool)
          {
            m = 1;
            break label269;
            return localJSONObject1.toString();
          }
        }
      }
      for (;;)
      {
        if (i1 < i3) {
          break label289;
        }
        if (m != 0) {
          break label52;
        }
        i1 = m;
        n += 1;
        m = i1;
        break;
        i1 += 1;
      }
    }
  }
  
  public static ArrayList<String> d()
  {
    ArrayList localArrayList = new ArrayList();
    long l1 = SysParamEntityManager.getLongParam("LastPublicUpdate", 0L, Constant.getContext());
    for (;;)
    {
      try
      {
        XyCursor localXyCursor = DBManager.rawQuery(" select DISTINCT tb_public_num_info.num , tb_public_info.pubId , tb_public_info.versionCode  from tb_public_num_info join tb_public_info   on tb_public_info.pubId = tb_public_num_info.pubId  and tb_public_num_info.lastloadtime > ?  and  tb_public_num_info.pubId  in ( select  tb_public_info.pubId   from tb_public_num_info join tb_public_info  on tb_public_info.pubId = tb_public_num_info.pubId   and tb_public_num_info.lastloadtime > ? group by tb_public_info.pubId limit 10)", new String[] { String.valueOf(l1), String.valueOf(l1) });
        localThrowable1.printStackTrace();
      }
      catch (Throwable localThrowable1)
      {
        try
        {
          a(localXyCursor, localArrayList);
          XyCursor.closeCursor(localXyCursor, true);
          return localArrayList;
        }
        catch (Throwable localThrowable2)
        {
          for (;;) {}
        }
        localThrowable1 = localThrowable1;
        localXyCursor = null;
      }
    }
  }
  
  private static void d(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {
      return;
    }
    try
    {
      ContentValues localContentValues = BaseManager.getContentValues(null, new String[] { "menuCode", paramJSONObject.optString("menuCode"), "pubId", paramJSONObject.optString("pubId"), "menuName", paramJSONObject.optString("name"), "menuType", paramJSONObject.optString("type"), "extend", paramJSONObject.optString("extend"), "actionData", paramJSONObject.optString("action_data") });
      if (DBManager.update("tb_public_menu_info", localContentValues, "pubId = ? and menuCode = ?", new String[] { paramJSONObject.optString("pubId"), paramJSONObject.optString("menuCode") }) < 1L)
      {
        DBManager.insert("tb_public_menu_info", localContentValues);
        new StringBuilder("insert=").append(paramJSONObject.toString());
        return;
      }
    }
    catch (Throwable paramJSONObject)
    {
      paramJSONObject.printStackTrace();
      return;
    }
    new StringBuilder("update=").append(paramJSONObject.toString());
  }
  
  public static void e()
  {
    try
    {
      DBManager.delete("tb_public_num_info", "isrulenum = 1", null);
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  private static void e(String paramString)
  {
    try
    {
      DBManager.delete("tb_public_menu_info", "pubId = ?", new String[] { paramString });
      return;
    }
    catch (Throwable paramString) {}
  }
  
  private static void e(JSONObject paramJSONObject)
  {
    int m = 0;
    if (paramJSONObject == null) {
      return;
    }
    for (;;)
    {
      try
      {
        arrayOfString = paramJSONObject.optString("areaCode").split(";");
        int n = arrayOfString.length;
        if (m < n) {
          continue;
        }
      }
      catch (Throwable localThrowable)
      {
        String[] arrayOfString;
        String str;
        localThrowable.printStackTrace();
        continue;
      }
      try
      {
        DBManager.insert("tb_public_num_info", BaseManager.getContentValues(null, new String[] { "pubId", paramJSONObject.optString("pubId"), "num", paramJSONObject.optString("num"), "main", paramJSONObject.optString("main"), "communication", paramJSONObject.optString("communication"), "purpose", paramJSONObject.optString("purpose"), "areaCode", paramJSONObject.optString("areaCode"), "extend", paramJSONObject.optString("extend"), "ptype", paramJSONObject.optString("type"), "isfull", paramJSONObject.optString("isfull"), "minLen", paramJSONObject.optString("minLen"), "maxLen", paramJSONObject.optString("maxLen"), "len", paramJSONObject.optString("len"), "ntype", paramJSONObject.optString("ntype") }));
        cn.com.xy.sms.sdk.db.entity.g.b(paramJSONObject.optString("num"), paramJSONObject.optString("areaCode"));
        return;
      }
      catch (Throwable paramJSONObject)
      {
        paramJSONObject.printStackTrace();
        return;
      }
      str = arrayOfString[m];
      if (!StringUtils.isNull(str)) {
        DBManager.delete("tb_public_num_info", "  ptype = ? and num = ? and areaCode like '%" + str + "%'  and pubId !=? ", new String[] { String.valueOf(paramJSONObject.optString("type")), paramJSONObject.optString("num"), paramJSONObject.optString("pubId") });
      }
      m += 1;
    }
  }
  
  private static void f(String paramString)
  {
    try
    {
      DBManager.delete("tb_public_num_info", " pubId =? ", new String[] { paramString });
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  private static void f(JSONObject paramJSONObject)
  {
    int m = 0;
    int n;
    do
    {
      try
      {
        arrayOfString = paramJSONObject.optString("areaCode").split(";");
        n = arrayOfString.length;
      }
      catch (Throwable paramJSONObject)
      {
        String[] arrayOfString;
        String str;
        paramJSONObject.printStackTrace();
        return;
      }
      str = arrayOfString[m];
      if (!StringUtils.isNull(str)) {
        DBManager.delete("tb_public_num_info", "  ptype = ? and num = ? and areaCode like '%" + str + "%'  and pubId !=? ", new String[] { String.valueOf(paramJSONObject.optString("type")), paramJSONObject.optString("num"), paramJSONObject.optString("pubId") });
      }
      m += 1;
    } while (m < n);
  }
  
  private static int g(String paramString)
  {
    if ((paramString == null) || (paramString.indexOf("{") == -1)) {
      return -1;
    }
    try
    {
      int m = new JSONObject(paramString).getInt("sp");
      return m;
    }
    catch (Throwable localThrowable)
    {
      LogManager.e("PubInfoManager getOperatorNumByExtend", "公众号运营商信息转JSONObject异常,extend=" + paramString, localThrowable);
    }
    return -1;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.a.e
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */