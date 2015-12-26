package com.amap.api.services.busline;

import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.core.b;
import java.util.ArrayList;
import java.util.List;

public final class BusStationResult
{
  private int a;
  private ArrayList<BusStationItem> b = new ArrayList();
  private BusStationQuery c;
  private List<String> d = new ArrayList();
  private List<SuggestionCity> e = new ArrayList();
  
  private BusStationResult(b paramb, ArrayList<?> paramArrayList)
  {
    c = ((BusStationQuery)paramb.c());
    a = a(paramb.d());
    e = paramb.f();
    d = paramb.b_();
    b = paramArrayList;
  }
  
  private int a(int paramInt)
  {
    int i = c.getPageSize();
    paramInt = (paramInt + i - 1) / i;
    if (paramInt > 30) {
      return 30;
    }
    return paramInt;
  }
  
  static BusStationResult a(b paramb, ArrayList<?> paramArrayList)
  {
    return new BusStationResult(paramb, paramArrayList);
  }
  
  public List<BusStationItem> getBusStations()
  {
    return b;
  }
  
  public int getPageCount()
  {
    return a;
  }
  
  public BusStationQuery getQuery()
  {
    return c;
  }
  
  public List<SuggestionCity> getSearchSuggestionCities()
  {
    return e;
  }
  
  public List<String> getSearchSuggestionKeywords()
  {
    return d;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.busline.BusStationResult
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */