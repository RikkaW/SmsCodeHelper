package com.amap.api.services.geocoder;

public abstract interface GeocodeSearch$OnGeocodeSearchListener
{
  public abstract void onGeocodeSearched(GeocodeResult paramGeocodeResult, int paramInt);
  
  public abstract void onRegeocodeSearched(RegeocodeResult paramRegeocodeResult, int paramInt);
}

/* Location:
 * Qualified Name:     com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */