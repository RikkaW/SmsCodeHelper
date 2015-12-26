package cn.com.xy.sms.sdk.util;

import android.content.ContentValues;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil
{
  public static void JSONCombine(JSONObject paramJSONObject1, JSONObject paramJSONObject2)
  {
    if (paramJSONObject1 != null) {
      try
      {
        Iterator localIterator = paramJSONObject2.keys();
        for (;;)
        {
          if (!localIterator.hasNext()) {
            return;
          }
          String str = (String)localIterator.next();
          paramJSONObject1.put(str, paramJSONObject2.get(str));
        }
        return;
      }
      catch (Throwable paramJSONObject1)
      {
        paramJSONObject1.printStackTrace();
      }
    }
  }
  
  public static String PubInfoToJson(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {
      return "";
    }
    for (;;)
    {
      try
      {
        paramJSONObject.put("id", paramJSONObject.optString("pubId"));
        paramJSONObject.put("name", paramJSONObject.optString("pubName"));
        paramJSONObject.put("classifyName", paramJSONObject.optString("pubType"));
        paramJSONObject.put("weiboName", paramJSONObject.optString("weiBoName"));
        paramJSONObject.put("weiboUrl", paramJSONObject.optString("weiBoUrl"));
        paramJSONObject.put("weixin", paramJSONObject.optString("weiXin"));
        paramJSONObject.put("logo", paramJSONObject.optString("rectLogoName"));
        paramJSONObject.put("logoc", paramJSONObject.optString("circleLogoName"));
        paramJSONObject.put("website", paramJSONObject.optString("webSite"));
        JSONArray localJSONArray1 = paramJSONObject.optJSONArray("pubNumInfolist");
        int i;
        if ((localJSONArray1 != null) && (localJSONArray1.length() > 0))
        {
          int j = localJSONArray1.length();
          JSONArray localJSONArray2 = new JSONArray();
          i = 0;
          if (i >= j)
          {
            paramJSONObject.put("pubnum", localJSONArray2);
            paramJSONObject.remove("pubId");
            paramJSONObject.remove("pubName");
            paramJSONObject.remove("pubType");
            paramJSONObject.remove("pubTypeCode");
            paramJSONObject.remove("weiXin");
            paramJSONObject.remove("weiBoName");
            paramJSONObject.remove("weiBoUrl");
            paramJSONObject.remove("introduce");
            paramJSONObject.remove("address");
            paramJSONObject.remove("faxNum");
            paramJSONObject.remove("webSite");
            paramJSONObject.remove("versionCode");
            paramJSONObject.remove("email");
            paramJSONObject.remove("parentPubId");
            paramJSONObject.remove("slogan");
            paramJSONObject.remove("rectLogoName");
            paramJSONObject.remove("circleLogoName");
            paramJSONObject.remove("extend");
            paramJSONObject.remove("pubNumInfolist");
            paramJSONObject.remove("loadMenuTime");
            paramJSONObject.remove("updateInfoTime");
            paramJSONObject.remove("hasmenu");
            return paramJSONObject.toString();
          }
          JSONObject localJSONObject1 = (JSONObject)localJSONArray1.get(i);
          if ("2".equals(localJSONObject1.optString("type")))
          {
            JSONObject localJSONObject2 = new JSONObject();
            localJSONObject2.put("purpose", localJSONObject1.optString("purpose"));
            localJSONObject2.put("num", localJSONObject1.optString("num"));
            localJSONObject2.put("areaCode", localJSONObject1.optString("areaCode"));
            localJSONObject2.put("extend", localJSONObject1.optString("extend"));
            localJSONArray2.put(localJSONObject2);
          }
        }
        else
        {
          paramJSONObject.put("pubnum", "");
          continue;
        }
        i += 1;
      }
      catch (Throwable paramJSONObject)
      {
        paramJSONObject.printStackTrace();
        return "";
      }
    }
  }
  
  public static JSONObject getJsonObject(JSONObject paramJSONObject, String... paramVarArgs)
  {
    JSONObject localJSONObject;
    if ((paramVarArgs == null) || (paramJSONObject == null))
    {
      localJSONObject = null;
      return localJSONObject;
    }
    int j = paramVarArgs.length;
    if ((j == 0) || (j % 2 != 0)) {
      return null;
    }
    int i = 0;
    for (;;)
    {
      localJSONObject = paramJSONObject;
      if (i >= j) {
        break;
      }
      if ((paramVarArgs[i] != null) && (paramVarArgs[(i + 1)] != null)) {}
      try
      {
        paramJSONObject.put(paramVarArgs[i], paramVarArgs[(i + 1)]);
        i += 2;
      }
      catch (JSONException paramJSONObject)
      {
        paramJSONObject.printStackTrace();
      }
    }
    return null;
  }
  
  public static JSONObject getJsonObject(String... paramVarArgs)
  {
    if (paramVarArgs == null) {}
    int j;
    do
    {
      return null;
      j = paramVarArgs.length;
    } while ((j == 0) || (j % 2 != 0));
    JSONObject localJSONObject;
    int i;
    do
    {
      try
      {
        localJSONObject = new JSONObject();
        i = 0;
      }
      catch (JSONException paramVarArgs)
      {
        paramVarArgs.printStackTrace();
        return null;
      }
      if ((paramVarArgs[i] != null) && (paramVarArgs[(i + 1)] != null)) {
        localJSONObject.put(paramVarArgs[i], paramVarArgs[(i + 1)]);
      }
      i += 2;
    } while (i < j);
    return localJSONObject;
  }
  
  public static Object getValFromJsonObject(JSONObject paramJSONObject, String paramString)
  {
    if ((paramString != null) && (paramJSONObject != null)) {
      try
      {
        if (paramJSONObject.has(paramString))
        {
          paramJSONObject = paramJSONObject.get(paramString);
          return paramJSONObject;
        }
      }
      catch (Throwable paramJSONObject)
      {
        paramJSONObject.printStackTrace();
      }
    }
    return "";
  }
  
  public static String getValueFromJson(JSONObject paramJSONObject, String paramString1, String paramString2)
  {
    if (paramJSONObject != null) {}
    try
    {
      paramJSONObject = paramJSONObject.optString(paramString1);
      boolean bool = StringUtils.isNull(paramJSONObject);
      if (bool) {
        return paramString2;
      }
      return paramJSONObject;
    }
    catch (Throwable paramJSONObject)
    {
      paramJSONObject.printStackTrace();
    }
    return paramString2;
  }
  
  public static Object getValueFromJsonObject(JSONObject paramJSONObject, String paramString)
  {
    if ((paramString != null) && (paramJSONObject != null)) {
      try
      {
        if (paramJSONObject.has(paramString))
        {
          paramJSONObject = paramJSONObject.get(paramString);
          return paramJSONObject;
        }
      }
      catch (Throwable paramJSONObject)
      {
        paramJSONObject.printStackTrace();
      }
    }
    return null;
  }
  
  public static Object getValueWithMap(Map<String, Object> paramMap, String paramString)
  {
    if (paramMap != null) {
      try
      {
        if ((!paramMap.isEmpty()) && (!StringUtils.isNull(paramString)) && (paramMap.containsKey(paramString)))
        {
          paramMap = paramMap.get(paramString);
          return paramMap;
        }
      }
      catch (Throwable paramMap) {}
    }
    return "";
  }
  
  public static JSONArray parseStrToJsonArray(String paramString)
  {
    if (!StringUtils.isNull(paramString)) {
      try
      {
        paramString = new JSONArray(paramString);
        return paramString;
      }
      catch (Throwable paramString)
      {
        paramString.printStackTrace();
      }
    }
    return null;
  }
  
  public static JSONObject parseStrToJsonObject(String paramString)
  {
    if (!StringUtils.isNull(paramString)) {
      try
      {
        paramString = new JSONObject(paramString.trim());
        return paramString;
      }
      catch (Throwable paramString)
      {
        paramString.printStackTrace();
      }
    }
    return null;
  }
  
  public static void putJsonToConV(ContentValues paramContentValues, JSONObject paramJSONObject, String paramString1, String paramString2)
  {
    paramJSONObject = paramJSONObject.optString(paramString2);
    if (!StringUtils.isNull(paramJSONObject))
    {
      paramContentValues.put(paramString1, paramJSONObject);
      return;
    }
    paramContentValues.remove(paramString1);
  }
  
  public static void putJsonToMap(JSONObject paramJSONObject, Map<String, String> paramMap)
  {
    if ((paramJSONObject != null) && (paramMap != null)) {
      try
      {
        Iterator localIterator = paramJSONObject.keys();
        for (;;)
        {
          if (!localIterator.hasNext()) {
            return;
          }
          String str = (String)localIterator.next();
          paramMap.put(str, paramJSONObject.getString(str));
        }
        return;
      }
      catch (Throwable paramJSONObject) {}
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.JsonUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */