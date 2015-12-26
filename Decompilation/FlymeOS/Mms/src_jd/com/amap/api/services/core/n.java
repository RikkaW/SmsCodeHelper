package com.amap.api.services.core;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.json.JSONException;
import org.json.JSONObject;

class n
  extends bt
{
  private Context a;
  private String b;
  
  public n(Context paramContext)
  {
    a = paramContext;
    b = w.f(paramContext);
  }
  
  private o a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {}
    for (;;)
    {
      return null;
      try
      {
        paramArrayOfByte = new JSONObject(new String(paramArrayOfByte));
        String str = paramArrayOfByte.optString("status");
        if ((!"0".equals(str)) && ("1".equals(str)))
        {
          paramArrayOfByte = a(paramArrayOfByte, "result");
          boolean bool = a(b(a(a(paramArrayOfByte, "exception"), "exceptinfo"), "ex_isupload"));
          paramArrayOfByte = new o(a(b(a(a(paramArrayOfByte, "common"), "commoninfo"), "com_isupload")), bool);
          return paramArrayOfByte;
        }
      }
      catch (JSONException paramArrayOfByte)
      {
        d.a(paramArrayOfByte, "ManifestManager", "loadData");
        return null;
      }
      catch (Exception paramArrayOfByte)
      {
        d.a(paramArrayOfByte, "ManifestManager", "loadData");
      }
    }
    return null;
  }
  
  private JSONObject a(JSONObject paramJSONObject, String paramString)
  {
    if (paramJSONObject == null) {
      return null;
    }
    return paramJSONObject.optJSONObject(paramString);
  }
  
  private boolean a(String paramString)
  {
    if (paramString == null) {}
    while (!paramString.equals("1")) {
      return false;
    }
    return true;
  }
  
  private String b(JSONObject paramJSONObject, String paramString)
  {
    if (paramJSONObject == null) {
      return null;
    }
    return paramJSONObject.optString(paramString);
  }
  
  public o a()
  {
    try
    {
      Object localObject = bs.a(false);
      a(ac.a(a));
      localObject = a(((bs)localObject).a(this));
      return (o)localObject;
    }
    catch (Exception localException)
    {
      d.a(localException, "ManifestManager", "feachManifest");
    }
    return null;
  }
  
  public String b()
  {
    return c.a() + "/config/resource";
  }
  
  public Map<String, String> c_()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", b);
    localHashMap.put("opertype", "common;exception");
    localHashMap.put("plattype", "android");
    localHashMap.put("product", "sea");
    localHashMap.put("version", "2.5.0");
    localHashMap.put("ext", "standard");
    localHashMap.put("output", "json");
    Object localObject = new StringBuffer();
    ((StringBuffer)localObject).append("key=").append(b);
    ((StringBuffer)localObject).append("&opertype=common;exception");
    ((StringBuffer)localObject).append("&plattype=android");
    ((StringBuffer)localObject).append("&product=").append("sea");
    ((StringBuffer)localObject).append("&version=").append("2.5.0");
    ((StringBuffer)localObject).append("&ext=standard");
    ((StringBuffer)localObject).append("&output=json");
    localObject = ae.a(((StringBuffer)localObject).toString());
    String str = y.a();
    localHashMap.put("ts", str);
    localHashMap.put("scode", y.a(a, str, (String)localObject));
    return localHashMap;
  }
  
  public Map<String, String> d_()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("User-Agent", "AMAP SDK Android Search 2.5.0");
    return localHashMap;
  }
  
  public HttpEntity e()
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.n
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */