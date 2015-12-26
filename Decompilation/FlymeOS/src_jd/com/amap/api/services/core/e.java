package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.DistrictSearchQuery;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class e
  extends r<DistrictSearchQuery, DistrictResult>
{
  public e(Context paramContext, DistrictSearchQuery paramDistrictSearchQuery)
  {
    super(paramContext, paramDistrictSearchQuery);
  }
  
  protected DistrictResult a(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    DistrictResult localDistrictResult = new DistrictResult((DistrictSearchQuery)a, localArrayList);
    try
    {
      paramString = new JSONObject(paramString);
      localDistrictResult.setPageCount(paramString.optInt("count"));
      paramString = paramString.optJSONArray("districts");
      if (paramString == null) {
        return localDistrictResult;
      }
      j.a(paramString, localArrayList, null);
    }
    catch (JSONException paramString)
    {
      for (;;)
      {
        d.a(paramString, "DistrictServerHandler", "paseJSONJSONException");
      }
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        d.a(paramString, "DistrictServerHandler", "paseJSONException");
      }
    }
    return localDistrictResult;
  }
  
  protected String a_()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("output=json");
    localStringBuffer.append("&page=").append(((DistrictSearchQuery)a).getPageNum() + 1);
    localStringBuffer.append("&offset=").append(((DistrictSearchQuery)a).getPageSize());
    localStringBuffer.append("&showChild=").append(((DistrictSearchQuery)a).isShowChild());
    if (((DistrictSearchQuery)a).isShowBoundary()) {
      localStringBuffer.append("&extensions=all");
    }
    for (;;)
    {
      if (((DistrictSearchQuery)a).checkKeyWords())
      {
        String str = c(((DistrictSearchQuery)a).getKeywords());
        localStringBuffer.append("&keywords=").append(str);
      }
      if (((DistrictSearchQuery)a).checkLevels()) {
        localStringBuffer.append("&level=").append(((DistrictSearchQuery)a).getKeywordsLevel());
      }
      localStringBuffer.append("&key=" + w.f(d));
      return localStringBuffer.toString();
      localStringBuffer.append("&extensions=base");
    }
  }
  
  public String b()
  {
    return c.a() + "/config/district?";
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.e
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */