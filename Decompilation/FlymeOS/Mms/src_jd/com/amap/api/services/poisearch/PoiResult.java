package com.amap.api.services.poisearch;

import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import java.util.ArrayList;
import java.util.List;

public final class PoiResult
{
  private int a;
  private ArrayList<PoiItem> b = new ArrayList();
  private j c;
  
  private PoiResult(j paramj, ArrayList<PoiItem> paramArrayList)
  {
    c = paramj;
    a = a(paramj.i());
    b = paramArrayList;
  }
  
  private int a(int paramInt)
  {
    int i = c.f();
    paramInt = (paramInt + i - 1) / i;
    if (paramInt > 30) {
      return 30;
    }
    return paramInt;
  }
  
  static PoiResult a(j paramj, ArrayList<PoiItem> paramArrayList)
  {
    return new PoiResult(paramj, paramArrayList);
  }
  
  public PoiSearch.SearchBound getBound()
  {
    return c.k();
  }
  
  public int getPageCount()
  {
    return a;
  }
  
  public ArrayList<PoiItem> getPois()
  {
    return b;
  }
  
  public PoiSearch.Query getQuery()
  {
    return c.j();
  }
  
  public List<SuggestionCity> getSearchSuggestionCitys()
  {
    return c.m();
  }
  
  public List<String> getSearchSuggestionKeywords()
  {
    return c.l();
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.poisearch.PoiResult
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */