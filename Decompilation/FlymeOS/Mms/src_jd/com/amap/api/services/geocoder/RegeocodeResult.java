package com.amap.api.services.geocoder;

public class RegeocodeResult
{
  private RegeocodeQuery a;
  private RegeocodeAddress b;
  
  public RegeocodeResult(RegeocodeQuery paramRegeocodeQuery, RegeocodeAddress paramRegeocodeAddress)
  {
    a = paramRegeocodeQuery;
    b = paramRegeocodeAddress;
  }
  
  public RegeocodeAddress getRegeocodeAddress()
  {
    return b;
  }
  
  public RegeocodeQuery getRegeocodeQuery()
  {
    return a;
  }
  
  public void setRegeocodeAddress(RegeocodeAddress paramRegeocodeAddress)
  {
    b = paramRegeocodeAddress;
  }
  
  public void setRegeocodeQuery(RegeocodeQuery paramRegeocodeQuery)
  {
    a = paramRegeocodeQuery;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.geocoder.RegeocodeResult
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */