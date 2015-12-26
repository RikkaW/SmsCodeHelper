package com.amap.api.services.poisearch;

public abstract interface PoiSearch$OnPoiSearchListener
{
  public abstract void onPoiItemDetailSearched(PoiItemDetail paramPoiItemDetail, int paramInt);
  
  public abstract void onPoiSearched(PoiResult paramPoiResult, int paramInt);
}

/* Location:
 * Qualified Name:     com.amap.api.services.poisearch.PoiSearch.OnPoiSearchListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */