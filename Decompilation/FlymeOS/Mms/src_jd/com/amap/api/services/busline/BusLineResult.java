package com.amap.api.services.busline;

import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.core.b;
import java.util.ArrayList;
import java.util.List;

public final class BusLineResult
{
  private int a;
  private ArrayList<BusLineItem> b = new ArrayList();
  private BusLineQuery c;
  private List<String> d = new ArrayList();
  private List<SuggestionCity> e = new ArrayList();
  
  private BusLineResult(b paramb, ArrayList<?> paramArrayList)
  {
    c = ((BusLineQuery)paramb.c());
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
  
  static BusLineResult a(b paramb, ArrayList<?> paramArrayList)
  {
    return new BusLineResult(paramb, paramArrayList);
  }
  
  public List<BusLineItem> getBusLines()
  {
    return b;
  }
  
  public int getPageCount()
  {
    return a;
  }
  
  public BusLineQuery getQuery()
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
 * Qualified Name:     com.amap.api.services.busline.BusLineResult
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */