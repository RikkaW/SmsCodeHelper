package com.amap.api.mapcore2d;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.json.JSONException;
import org.json.JSONObject;

class az
  extends ey
{
  private Context a;
  private String e;
  
  public az(Context paramContext)
  {
    a = paramContext;
    e = da.f(paramContext);
  }
  
  private ba a(byte[] paramArrayOfByte)
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
          paramArrayOfByte = new ba(a(b(a(a(paramArrayOfByte, "common"), "commoninfo"), "com_isupload")), bool);
          return paramArrayOfByte;
        }
      }
      catch (JSONException paramArrayOfByte)
      {
        cy.a(paramArrayOfByte, "ManifestManager", "loadData");
        return null;
      }
      catch (Exception paramArrayOfByte)
      {
        cy.a(paramArrayOfByte, "ManifestManager", "loadData");
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
  
  public ba a()
  {
    try
    {
      Object localObject = ex.a(false);
      a(dg.a(a));
      localObject = a(((ex)localObject).a(this));
      return (ba)localObject;
    }
    catch (Exception localException)
    {
      cy.a(localException, "ManifestManager", "feachManifest");
    }
    return null;
  }
  
  public Map<String, String> b()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("User-Agent", "AMAP SDK Android 2DMap 2.5.0");
    return localHashMap;
  }
  
  public Map<String, String> c()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", e);
    localHashMap.put("opertype", "common;exception");
    localHashMap.put("plattype", "android");
    localHashMap.put("product", "2dmap");
    localHashMap.put("version", "2.5.0");
    localHashMap.put("ext", "standard");
    localHashMap.put("output", "json");
    Object localObject = new StringBuffer();
    ((StringBuffer)localObject).append("key=").append(e);
    ((StringBuffer)localObject).append("&opertype=common;exception");
    ((StringBuffer)localObject).append("&plattype=android");
    ((StringBuffer)localObject).append("&product=").append("2dmap");
    ((StringBuffer)localObject).append("&version=").append("2.5.0");
    ((StringBuffer)localObject).append("&ext=standard");
    ((StringBuffer)localObject).append("&output=json");
    localObject = di.a(((StringBuffer)localObject).toString());
    String str = dc.a();
    localHashMap.put("ts", str);
    localHashMap.put("scode", dc.a(a, str, (String)localObject));
    return localHashMap;
  }
  
  public String d()
  {
    return "http://restapi.amap.com/v3/config/resource";
  }
  
  public HttpEntity e()
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.az
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */