package com.amap.api.services.busline;

import android.content.Context;
import android.os.Handler;
import com.amap.api.services.core.b;
import com.amap.api.services.core.l;
import com.amap.api.services.core.p;
import java.util.ArrayList;

public class BusLineSearch
{
  private Context a;
  private OnBusLineSearchListener b;
  private BusLineQuery c;
  private BusLineQuery d;
  private int e;
  private ArrayList<BusLineResult> f = new ArrayList();
  private Handler g = null;
  
  public BusLineSearch(Context paramContext, BusLineQuery paramBusLineQuery)
  {
    a = paramContext.getApplicationContext();
    c = paramBusLineQuery;
    d = paramBusLineQuery.clone();
    g = p.a();
  }
  
  private void a(BusLineResult paramBusLineResult)
  {
    f = new ArrayList();
    int i = 0;
    while (i < e)
    {
      f.add(null);
      i += 1;
    }
    if ((e >= 0) && (a(c.getPageNumber()))) {
      f.set(c.getPageNumber(), paramBusLineResult);
    }
  }
  
  private boolean a(int paramInt)
  {
    return (paramInt < e) && (paramInt >= 0);
  }
  
  private BusLineResult b(int paramInt)
  {
    if (!a(paramInt)) {
      throw new IllegalArgumentException("page out of range");
    }
    return (BusLineResult)f.get(paramInt);
  }
  
  public BusLineQuery getQuery()
  {
    return c;
  }
  
  public BusLineResult searchBusLine()
  {
    l.a(a);
    if (!c.weakEquals(d))
    {
      d = c.clone();
      e = 0;
      if (f != null) {
        f.clear();
      }
    }
    if (e == 0)
    {
      localObject = new b(a, c.clone());
      localObject = BusLineResult.a((b)localObject, (ArrayList)((b)localObject).g());
      e = ((BusLineResult)localObject).getPageCount();
      a((BusLineResult)localObject);
    }
    BusLineResult localBusLineResult;
    do
    {
      return (BusLineResult)localObject;
      localBusLineResult = b(c.getPageNumber());
      localObject = localBusLineResult;
    } while (localBusLineResult != null);
    Object localObject = new b(a, c);
    localObject = BusLineResult.a((b)localObject, (ArrayList)((b)localObject).g());
    f.set(c.getPageNumber(), localObject);
    return (BusLineResult)localObject;
  }
  
  public void searchBusLineAsyn()
  {
    new Thread(new BusLineSearch.1(this)).start();
  }
  
  public void setOnBusLineSearchListener(OnBusLineSearchListener paramOnBusLineSearchListener)
  {
    b = paramOnBusLineSearchListener;
  }
  
  public void setQuery(BusLineQuery paramBusLineQuery)
  {
    if (!c.weakEquals(paramBusLineQuery))
    {
      c = paramBusLineQuery;
      d = paramBusLineQuery.clone();
    }
  }
  
  public static abstract interface OnBusLineSearchListener
  {
    public abstract void onBusLineSearched(BusLineResult paramBusLineResult, int paramInt);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.busline.BusLineSearch
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */