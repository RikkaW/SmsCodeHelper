package cn.com.xy.sms.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.net.NetWebUtil;
import cn.com.xy.sms.sdk.net.util.l;
import cn.com.xy.sms.sdk.util.PopupUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseCardManager
{
  public static String SMS_DATE_TIME = "sms_date_time";
  private static String a = "yunyingshang_s_0001";
  private static String b = "1003";
  private static String c = "xiaoyuan";
  
  private static String a(String paramString)
  {
    Object localObject1 = null;
    Object localObject2 = Uri.parse("content://com.yunos.lifecard/cards");
    localObject2 = Constant.getContext().getContentResolver().query((Uri)localObject2, null, " arg1 like ? ", new String[] { "%" + paramString + "%" }, null);
    paramString = (String)localObject1;
    if (((Cursor)localObject2).moveToNext())
    {
      new StringBuilder("cc card_id=").append(((Cursor)localObject2).getString(((Cursor)localObject2).getColumnIndex("card_id")));
      paramString = ((Cursor)localObject2).getString(((Cursor)localObject2).getColumnIndex("card_id"));
      ((Cursor)localObject2).close();
    }
    return paramString;
  }
  
  private static Map<String, Object> a(Map<String, Object> paramMap, String paramString)
  {
    Uri localUri = Uri.parse("content://com.yunos.lifecard/cards");
    if (paramMap == null)
    {
      paramMap = new HashMap();
      paramMap.put("Result", Boolean.valueOf(false));
      return paramMap;
    }
    Object localObject = ((JSONObject)paramMap.get("content")).toString();
    if ((localObject != null) && (!((String)localObject).equals("")))
    {
      localObject = Constant.getContext().getContentResolver();
      ContentValues localContentValues = (ContentValues)paramMap.get("contentValue");
      if (localContentValues == null) {
        break label165;
      }
      localContentValues.put("card_id", paramString);
      paramString = ((ContentResolver)localObject).insert(localUri, localContentValues);
      new StringBuilder("insert=").append(paramString);
    }
    for (;;)
    {
      if (paramString == null)
      {
        paramMap.put("Result", Boolean.valueOf(false));
        return paramMap;
      }
      paramMap.put("Result", Boolean.valueOf(true));
      return paramMap;
      paramMap.put("Result", Boolean.valueOf(false));
      return paramMap;
      label165:
      paramString = null;
    }
  }
  
  private static Map<String, Object> b(Map<String, Object> paramMap, String paramString)
  {
    Uri localUri = Uri.parse("content://com.yunos.lifecard/cards/");
    if (paramMap == null)
    {
      paramMap = new HashMap();
      paramMap.put("Result", Boolean.valueOf(false));
      return paramMap;
    }
    Object localObject = ((JSONObject)paramMap.get("content")).toString();
    if ((localObject != null) && (!((String)localObject).equals("")))
    {
      localObject = Constant.getContext().getContentResolver();
      paramMap = (ContentValues)paramMap.get("contentValue");
      if (paramMap == null) {
        break label183;
      }
      new StringBuilder("values=").append(paramMap);
      paramMap.remove("card_tag");
    }
    label183:
    for (int i = ((ContentResolver)localObject).update(localUri, paramMap, "card_id = ?", new String[] { paramString });; i = -1)
    {
      paramMap = new HashMap();
      if (i <= 0)
      {
        paramMap.put("Result", Boolean.valueOf(false));
        return paramMap;
      }
      paramMap.put("Result", Boolean.valueOf(true));
      return paramMap;
      paramMap.put("Result", Boolean.valueOf(false));
      return paramMap;
    }
  }
  
  public static boolean checkStationList(String paramString1, String paramString2, String paramString3)
  {
    return ParseManager.checkStationList(paramString1, paramString2, paramString3, false);
  }
  
  public static void expressage(String paramString1, String paramString2, SdkCallBack paramSdkCallBack)
  {
    if ((StringUtils.isNull(paramString1)) || (StringUtils.isNull(paramString2)))
    {
      XyUtil.doXycallBackResult(paramSdkCallBack, new Object[0]);
      return;
    }
    paramSdkCallBack = new e(paramSdkCallBack);
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("express_name", paramString1);
      localJSONObject.put("express_no", paramString2);
      NetWebUtil.sendPostRequest(NetWebUtil.WEB_SERVER_URL2, localJSONObject.toString(), paramSdkCallBack);
      return;
    }
    catch (JSONException paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static Map<String, Object> parseMsgForCard(Context paramContext, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap)
  {
    if (paramContext == null) {
      throw new Exception(" Context is null.");
    }
    if (paramString1 == null) {
      throw new Exception(" phoneNumber is null.");
    }
    if (paramString3 == null) {
      throw new Exception(" smsContent is null.");
    }
    paramContext = ParseManager.a(paramContext, paramString1, paramString2, paramString3, 0L, paramMap);
    if ((paramContext != null) && (ParseBubbleManager.getParseStatu(paramContext) != -1))
    {
      if ((paramMap != null) && (paramMap.containsKey(SMS_DATE_TIME))) {
        paramContext.put(SMS_DATE_TIME, paramMap.get(SMS_DATE_TIME));
      }
      paramContext = DexUtil.handerValueMap(paramContext);
      if (paramContext == null)
      {
        paramContext = new HashMap();
        paramContext.put("Result", Boolean.valueOf(false));
        return paramContext;
      }
      paramContext.put("Result", Boolean.valueOf(true));
      return paramContext;
    }
    paramContext = new HashMap();
    paramContext.put("Result", Boolean.valueOf(false));
    return paramContext;
  }
  
  public static String parseMsgForCardData(Context paramContext, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap)
  {
    if (paramContext == null) {
      throw new Exception(" Context is null.");
    }
    if (paramString1 == null) {
      throw new Exception(" phoneNumber is null.");
    }
    if (paramString3 == null) {
      throw new Exception(" smsContent is null.");
    }
    if (!l.a((byte)8)) {
      PopupUtil.getResultMap(false, false);
    }
    paramString1 = ParseManager.a(paramContext, paramString1, paramString2, paramString3, 0L, paramMap);
    if ((paramString1 != null) && (ParseBubbleManager.getParseStatu(paramString1) != -1))
    {
      paramContext = DexUtil.handerValueMap(paramString1);
      if (paramContext == null) {
        return null;
      }
      new StringBuilder("res=").append(paramString1.toString());
      new StringBuilder("map=").append(paramContext.toString());
      if (((JSONObject)paramContext.get("content")).toString() != null)
      {
        new StringBuilder("content=").append(((JSONObject)paramContext.get("content")).toString());
        paramString1 = ((JSONObject)paramContext.get("theReturn")).put("content", paramContext.get("content"));
        paramString1.put("title", paramContext.get("title"));
        paramString1.put("card_tag", "xiaoyuan");
        return paramString1.toString();
      }
    }
    return null;
  }
  
  public static void queryFlightData(String paramString1, String paramString2, String paramString3, Map<String, Object> paramMap, SdkCallBack paramSdkCallBack)
  {
    ParseManager.queryFlightData(paramString1, paramString2, paramString3, paramMap, paramSdkCallBack);
  }
  
  public static void queryTrainInfo(String paramString1, String paramString2, String paramString3, String paramString4, Map<String, Object> paramMap, SdkCallBack paramSdkCallBack)
  {
    ParseManager.queryTrainInfo(paramString1, paramString2, paramString3, paramString4, paramMap, paramSdkCallBack);
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.ParseCardManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */