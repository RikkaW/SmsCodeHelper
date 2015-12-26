package com.amap.api.services.busline;

import android.content.Context;
import android.os.Handler;
import com.amap.api.services.core.b;
import com.amap.api.services.core.l;
import com.amap.api.services.core.p;
import java.util.ArrayList;

public class BusStationSearch
{
  private Context a;
  private OnBusStationSearchListener b;
  private BusStationQuery c;
  private BusStationQuery d;
  private ArrayList<BusStationResult> e = new ArrayList();
  private int f;
  private Handler g;
  
  public BusStationSearch(Context paramContext, BusStationQuery paramBusStationQuery)
  {
    a = paramContext.getApplicationContext();
    c = paramBusStationQuery;
    g = p.a();
  }
  
  private void a(BusStationResult paramBusStationResult)
  {
    e = new ArrayList();
    int i = 0;
    while (i <= f)
    {
      e.add(null);
      i += 1;
    }
    if (f > 0) {
      e.set(c.getPageNumber(), paramBusStationResult);
    }
  }
  
  private boolean a(int paramInt)
  {
    return (paramInt <= f) && (paramInt >= 0);
  }
  
  private BusStationResult b(int paramInt)
  {
    if (!a(paramInt)) {
      throw new IllegalArgumentException("page out of range");
    }
    return (BusStationResult)e.get(paramInt);
  }
  
  public BusStationQuery getQuery()
  {
    return c;
  }
  
  public BusStationResult searchBusStation()
  {
    l.a(a);
    if (!c.weakEquals(d))
    {
      d = c.clone();
      f = 0;
      if (e != null) {
        e.clear();
      }
    }
    if (f == 0)
    {
      localObject = new b(a, c);
      localObject = BusStationResult.a((b)localObject, (ArrayList)((b)localObject).g());
      f = ((BusStationResult)localObject).getPageCount();
      a((BusStationResult)localObject);
    }
    BusStationResult localBusStationResult;
    do
    {
      return (BusStationResult)localObject;
      localBusStationResult = b(c.getPageNumber());
      localObject = localBusStationResult;
    } while (localBusStationResult != null);
    Object localObject = new b(a, c);
    localObject = BusStationResult.a((b)localObject, (ArrayList)((b)localObject).g());
    e.set(c.getPageNumber(), localObject);
    return (BusStationResult)localObject;
  }
  
  public void searchBusStationAsyn()
  {
    new Thread(new BusStationSearch.1(this)).start();
  }
  
  public void setOnBusStationSearchListener(OnBusStationSearchListener paramOnBusStationSearchListener)
  {
    b = paramOnBusStationSearchListener;
  }
  
  public void setQuery(BusStationQuery paramBusStationQuery)
  {
    if (!paramBusStationQuery.weakEquals(c)) {
      c = paramBusStationQuery;
    }
  }
  
  public static abstract interface OnBusStationSearchListener
  {
    public abstract void onBusStationSearched(BusStationResult paramBusStationResult, int paramInt);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.busline.BusStationSearch
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */