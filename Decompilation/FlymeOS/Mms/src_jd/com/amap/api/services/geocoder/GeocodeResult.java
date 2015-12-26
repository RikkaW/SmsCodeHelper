package com.amap.api.services.geocoder;

import java.util.ArrayList;
import java.util.List;

public class GeocodeResult
{
  private GeocodeQuery a;
  private List<GeocodeAddress> b = new ArrayList();
  
  public GeocodeResult(GeocodeQuery paramGeocodeQuery, List<GeocodeAddress> paramList)
  {
    a = paramGeocodeQuery;
    b = paramList;
  }
  
  public List<GeocodeAddress> getGeocodeAddressList()
  {
    return b;
  }
  
  public GeocodeQuery getGeocodeQuery()
  {
    return a;
  }
  
  public void setGeocodeAddressList(List<GeocodeAddress> paramList)
  {
    b = paramList;
  }
  
  public void setGeocodeQuery(GeocodeQuery paramGeocodeQuery)
  {
    a = paramGeocodeQuery;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.geocoder.GeocodeResult
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */