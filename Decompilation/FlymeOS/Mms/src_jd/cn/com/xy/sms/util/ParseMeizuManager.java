package cn.com.xy.sms.util;

import android.content.Context;
import android.text.TextUtils;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class ParseMeizuManager
{
  public static final String SMS_BALANCE = "1";
  public static final String SMS_FLOW = "2";
  public static final String SMS_FLOW_FOUR = "23";
  public static final String SMS_FLOW_THREE = "22";
  public static final String SMS_FLOW_TWO = "21";
  private static final String a = "查询余额";
  private static final String b = "查询流量";
  private static final String c = "查2/3G流量";
  private static final String d = "查3/4G流量";
  private static final String e = "查询2G流量";
  private static final String f = "查询3G流量";
  private static final String g = "查询4G流量";
  private static final String h = "查询充值";
  private static final String i = "查询业务";
  
  private static String a(String paramString, JSONObject paramJSONObject)
  {
    if (("1".equals(paramString)) && ("查询余额".equals(paramJSONObject.getString("name")))) {
      return paramJSONObject.getString("action_data");
    }
    if ((("2".equals(paramString)) || ("21".equals(paramString)) || ("22".equals(paramString)) || ("23".equals(paramString))) && ("查询流量".equals(paramJSONObject.getString("name")))) {
      return paramJSONObject.getString("action_data");
    }
    if (("21".equals(paramString)) && (("查询2G流量".equals(paramJSONObject.getString("name"))) || ("查2/3G流量".equals(paramJSONObject.getString("name"))))) {
      return paramJSONObject.getString("action_data");
    }
    if (("22".equals(paramString)) && (("查询3G流量".equals(paramJSONObject.getString("name"))) || ("查2/3G流量".equals(paramJSONObject.getString("name"))) || ("查3/4G流量".equals(paramJSONObject.getString("name"))))) {
      return paramJSONObject.getString("action_data");
    }
    if (("23".equals(paramString)) && (("查询4G流量".equals(paramJSONObject.getString("name"))) || ("查3/4G流量".equals(paramJSONObject.getString("name"))))) {
      return paramJSONObject.getString("action_data");
    }
    return null;
  }
  
  public static String querySmsOrderActionData(Context paramContext, String paramString1, String paramString2, int paramInt, String paramString3, Map<String, String> paramMap)
  {
    paramContext = ParseManager.queryMenuByPhoneNum(paramContext, paramString1, paramInt, paramString3, paramMap);
    if (TextUtils.isEmpty(paramContext)) {
      return null;
    }
    paramContext = new JSONArray(paramContext);
    paramInt = 0;
    if (paramInt >= paramContext.length()) {
      return null;
    }
    paramString1 = paramContext.getJSONObject(paramInt);
    int j;
    if (("查询充值".equals(paramString1.getString("name"))) || ("查询业务".equals(paramString1.getString("name"))))
    {
      paramString1 = paramString1.getJSONArray("secondmenu");
      j = 0;
      label85:
      if (j < paramString1.length()) {}
    }
    do
    {
      paramInt += 1;
      break;
      paramString3 = a(paramString2, paramString1.getJSONObject(j));
      if (!TextUtils.isEmpty(paramString3)) {
        return paramString3;
      }
      j += 1;
      break label85;
      paramString1 = a(paramString2, paramString1);
    } while (TextUtils.isEmpty(paramString1));
    return paramString1;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.ParseMeizuManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */