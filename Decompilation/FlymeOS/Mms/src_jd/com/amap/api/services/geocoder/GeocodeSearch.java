package com.amap.api.services.geocoder;

import android.content.Context;
import android.os.Handler;
import com.amap.api.services.core.g;
import com.amap.api.services.core.l;
import com.amap.api.services.core.p;
import com.amap.api.services.core.t;
import java.util.List;

public final class GeocodeSearch
{
  public static final String AMAP = "autonavi";
  public static final String GPS = "gps";
  private Context a;
  private OnGeocodeSearchListener b;
  private Handler c;
  
  public GeocodeSearch(Context paramContext)
  {
    a = paramContext.getApplicationContext();
    c = p.a();
  }
  
  public RegeocodeAddress getFromLocation(RegeocodeQuery paramRegeocodeQuery)
  {
    l.a(a);
    return (RegeocodeAddress)new t(a, paramRegeocodeQuery).g();
  }
  
  public void getFromLocationAsyn(RegeocodeQuery paramRegeocodeQuery)
  {
    new Thread(new GeocodeSearch.1(this, paramRegeocodeQuery)).start();
  }
  
  public List<GeocodeAddress> getFromLocationName(GeocodeQuery paramGeocodeQuery)
  {
    l.a(a);
    return (List)new g(a, paramGeocodeQuery).g();
  }
  
  public void getFromLocationNameAsyn(GeocodeQuery paramGeocodeQuery)
  {
    new Thread(new GeocodeSearch.2(this, paramGeocodeQuery)).start();
  }
  
  public void setOnGeocodeSearchListener(OnGeocodeSearchListener paramOnGeocodeSearchListener)
  {
    b = paramOnGeocodeSearchListener;
  }
  
  public static abstract interface OnGeocodeSearchListener
  {
    public abstract void onGeocodeSearched(GeocodeResult paramGeocodeResult, int paramInt);
    
    public abstract void onRegeocodeSearched(RegeocodeResult paramRegeocodeResult, int paramInt);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.geocoder.GeocodeSearch
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */