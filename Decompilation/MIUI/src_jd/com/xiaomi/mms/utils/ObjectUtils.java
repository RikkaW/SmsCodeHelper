package com.xiaomi.mms.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ObjectUtils
{
  private static Object convertObj(Object paramObject)
  {
    if ((paramObject instanceof JSONObject))
    {
      paramObject = jsonToMap((JSONObject)paramObject);
      return paramObject;
    }
    if ((paramObject instanceof JSONArray))
    {
      JSONArray localJSONArray = (JSONArray)paramObject;
      int j = localJSONArray.length();
      ArrayList localArrayList = new ArrayList();
      int i = 0;
      for (;;)
      {
        paramObject = localArrayList;
        if (i >= j) {
          break;
        }
        localArrayList.add(convertObj(localJSONArray.opt(i)));
        i += 1;
      }
    }
    if (paramObject == JSONObject.NULL) {
      return null;
    }
    return paramObject;
  }
  
  public static String joinMap(Map<?, ?> paramMap, String paramString)
  {
    if (paramMap == null) {
      return null;
    }
    Object localObject = paramMap.entrySet();
    paramMap = new StringBuilder();
    int i = 1;
    localObject = ((Set)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
      if (i == 0) {
        paramMap.append(paramString);
      }
      paramMap.append(localEntry.getKey());
      paramMap.append('=');
      paramMap.append(localEntry.getValue());
      i = 0;
    }
    return paramMap.toString();
  }
  
  public static Map<String, Object> jsonToMap(JSONObject paramJSONObject)
  {
    Object localObject;
    if (paramJSONObject == null)
    {
      localObject = null;
      return (Map<String, Object>)localObject;
    }
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramJSONObject.keys();
    for (;;)
    {
      localObject = localHashMap;
      if (!localIterator.hasNext()) {
        break;
      }
      localObject = (String)localIterator.next();
      localHashMap.put(localObject, convertObj(paramJSONObject.opt((String)localObject)));
    }
  }
  
  public static Map<String, Object> jsonToMap(byte[] paramArrayOfByte, String paramString)
  {
    try
    {
      paramArrayOfByte = jsonToMap(new JSONObject(new String(paramArrayOfByte, paramString)));
      return paramArrayOfByte;
    }
    catch (UnsupportedEncodingException paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
      return null;
    }
    catch (JSONException paramArrayOfByte)
    {
      for (;;)
      {
        paramArrayOfByte.printStackTrace();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.ObjectUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */