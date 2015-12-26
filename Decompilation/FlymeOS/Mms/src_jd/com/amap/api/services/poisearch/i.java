package com.amap.api.services.poisearch;

import android.content.Context;
import com.amap.api.services.core.ServiceSettings;
import com.amap.api.services.core.c;
import com.amap.api.services.core.d;
import com.amap.api.services.core.j;
import com.amap.api.services.core.w;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class i
  extends g<String, PoiItemDetail>
{
  private String h = "zh-CN";
  
  public i(Context paramContext, String paramString1, String paramString2)
  {
    super(paramContext, paramString1);
    if ("en".equals(paramString2)) {
      h = paramString2;
    }
  }
  
  private PoiItemDetail a(JSONObject paramJSONObject)
  {
    PoiItemDetail localPoiItemDetail = null;
    if (paramJSONObject == null) {
      paramJSONObject = localPoiItemDetail;
    }
    Object localObject;
    JSONObject localJSONObject;
    do
    {
      do
      {
        do
        {
          do
          {
            return paramJSONObject;
            localObject = paramJSONObject.optJSONArray("pois");
            paramJSONObject = localPoiItemDetail;
          } while (localObject == null);
          paramJSONObject = localPoiItemDetail;
        } while (((JSONArray)localObject).length() <= 0);
        localObject = ((JSONArray)localObject).optJSONObject(0);
        paramJSONObject = localPoiItemDetail;
      } while (localObject == null);
      localPoiItemDetail = j.d((JSONObject)localObject);
      paramJSONObject = ((JSONObject)localObject).optJSONObject("rich_content");
      if (paramJSONObject != null) {
        j.a(localPoiItemDetail, paramJSONObject);
      }
      localJSONObject = ((JSONObject)localObject).optJSONObject("deep_info");
      paramJSONObject = localPoiItemDetail;
    } while (localJSONObject == null);
    j.e(localPoiItemDetail, localJSONObject, (JSONObject)localObject);
    return localPoiItemDetail;
  }
  
  private String f()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("id=").append((String)a);
    localStringBuilder.append("&output=json");
    localStringBuilder.append("&extensions=all");
    localStringBuilder.append("&language=").append(ServiceSettings.getInstance().getLanguage());
    localStringBuilder.append("&key=" + w.f(d));
    return localStringBuilder.toString();
  }
  
  protected String a_()
  {
    return f();
  }
  
  public String b()
  {
    return c.a() + "/place/detail?";
  }
  
  public PoiItemDetail e(String paramString)
  {
    try
    {
      paramString = a(new JSONObject(paramString));
      return paramString;
    }
    catch (JSONException paramString)
    {
      d.a(paramString, "PoiSearchIdHandler", "paseJSONJSONException");
      return null;
    }
    catch (Exception paramString)
    {
      d.a(paramString, "PoiSearchIdHandler", "paseJSONException");
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.poisearch.i
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */