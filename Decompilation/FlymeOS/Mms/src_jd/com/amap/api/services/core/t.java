package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class t
  extends r<RegeocodeQuery, RegeocodeAddress>
{
  public t(Context paramContext, RegeocodeQuery paramRegeocodeQuery)
  {
    super(paramContext, paramRegeocodeQuery);
  }
  
  protected RegeocodeAddress a(String paramString)
  {
    RegeocodeAddress localRegeocodeAddress = new RegeocodeAddress();
    try
    {
      paramString = new JSONObject(paramString).optJSONObject("regeocode");
      if (paramString == null) {
        return localRegeocodeAddress;
      }
      localRegeocodeAddress.setFormatAddress(j.b(paramString, "formatted_address"));
      Object localObject = paramString.optJSONObject("addressComponent");
      if (localObject != null) {
        j.a((JSONObject)localObject, localRegeocodeAddress);
      }
      localRegeocodeAddress.setPois(j.c(paramString));
      localObject = paramString.optJSONArray("roads");
      if (localObject != null) {
        j.b((JSONArray)localObject, localRegeocodeAddress);
      }
      paramString = paramString.optJSONArray("roadinters");
      if (paramString != null)
      {
        j.a(paramString, localRegeocodeAddress);
        return localRegeocodeAddress;
      }
    }
    catch (JSONException paramString)
    {
      d.a(paramString, "ReverseGeocodingHandler", "paseJSON");
    }
    return localRegeocodeAddress;
  }
  
  protected String a_()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("output=json").append("&extensions=all").append("&location=").append(((RegeocodeQuery)a).getPoint().getLongitude()).append(",").append(((RegeocodeQuery)a).getPoint().getLatitude());
    localStringBuffer.append("&radius=").append(((RegeocodeQuery)a).getRadius());
    localStringBuffer.append("&coordsys=").append(((RegeocodeQuery)a).getLatLonType());
    localStringBuffer.append("&key=" + w.f(d));
    localStringBuffer.append("&language=").append(c.b());
    return localStringBuffer.toString();
  }
  
  public String b()
  {
    return c.a() + "/geocode/regeo?";
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.t
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */