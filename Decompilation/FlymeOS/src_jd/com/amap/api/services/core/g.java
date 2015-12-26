package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class g
  extends r<GeocodeQuery, ArrayList<GeocodeAddress>>
{
  public g(Context paramContext, GeocodeQuery paramGeocodeQuery)
  {
    super(paramContext, paramGeocodeQuery);
  }
  
  protected ArrayList<GeocodeAddress> a(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      paramString = new JSONObject(paramString);
      if (!paramString.has("count")) {
        return localArrayList;
      }
      if (paramString.getInt("count") > 0)
      {
        paramString = j.n(paramString);
        return paramString;
      }
    }
    catch (JSONException paramString)
    {
      d.a(paramString, "GeocodingHandler", "paseJSONJSONException");
      return localArrayList;
    }
    catch (Exception paramString)
    {
      d.a(paramString, "GeocodingHandler", "paseJSONException");
    }
    return localArrayList;
  }
  
  protected String a_()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("output=json").append("&address=").append(c(((GeocodeQuery)a).getLocationName()));
    String str = ((GeocodeQuery)a).getCity();
    if (!j.h(str))
    {
      str = c(str);
      localStringBuffer.append("&city=").append(str);
    }
    localStringBuffer.append("&key=" + w.f(d));
    localStringBuffer.append("&language=").append(c.b());
    return localStringBuffer.toString();
  }
  
  public String b()
  {
    return c.a() + "/geocode/geo?";
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.g
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */