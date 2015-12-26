package com.amap.api.services.district;

import android.content.Context;
import android.os.Handler;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.e;
import com.amap.api.services.core.l;
import com.amap.api.services.core.p;
import java.util.HashMap;

public class DistrictSearch
{
  private static HashMap<Integer, DistrictResult> f;
  private Context a;
  private DistrictSearchQuery b;
  private OnDistrictSearchListener c;
  private DistrictSearchQuery d;
  private int e;
  private Handler g;
  
  public DistrictSearch(Context paramContext)
  {
    a = paramContext.getApplicationContext();
    g = p.a();
  }
  
  private void a(DistrictResult paramDistrictResult)
  {
    f = new HashMap();
    if ((b == null) || (paramDistrictResult == null)) {}
    while ((e <= 0) || (e <= b.getPageNum())) {
      return;
    }
    f.put(Integer.valueOf(b.getPageNum()), paramDistrictResult);
  }
  
  private boolean a()
  {
    return b != null;
  }
  
  private boolean a(int paramInt)
  {
    return (paramInt < e) && (paramInt >= 0);
  }
  
  private DistrictResult b()
  {
    Object localObject = new DistrictResult();
    l.a(a);
    if (!a()) {
      b = new DistrictSearchQuery();
    }
    ((DistrictResult)localObject).setQuery(b.clone());
    if (!b.weakEquals(d))
    {
      e = 0;
      d = b.clone();
      if (f != null) {
        f.clear();
      }
    }
    if (e == 0)
    {
      localObject = (DistrictResult)new e(a, b.clone()).g();
      if (localObject != null) {}
    }
    DistrictResult localDistrictResult;
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return (DistrictResult)localObject;
              e = ((DistrictResult)localObject).getPageCount();
              a((DistrictResult)localObject);
              return (DistrictResult)localObject;
              localDistrictResult = getPageLocal(b.getPageNum());
              localObject = localDistrictResult;
            } while (localDistrictResult != null);
            localDistrictResult = (DistrictResult)new e(a, b.clone()).g();
            localObject = localDistrictResult;
          } while (b == null);
          localObject = localDistrictResult;
        } while (localDistrictResult == null);
        localObject = localDistrictResult;
      } while (e <= 0);
      localObject = localDistrictResult;
    } while (e <= b.getPageNum());
    f.put(Integer.valueOf(b.getPageNum()), localDistrictResult);
    return localDistrictResult;
  }
  
  protected DistrictResult getPageLocal(int paramInt)
  {
    if (!a(paramInt)) {
      throw new AMapException("无效的参数 - IllegalArgumentException");
    }
    return (DistrictResult)f.get(Integer.valueOf(paramInt));
  }
  
  public DistrictSearchQuery getQuery()
  {
    return b;
  }
  
  public void searchDistrictAnsy()
  {
    new DistrictSearch.1(this).start();
  }
  
  public void setOnDistrictSearchListener(OnDistrictSearchListener paramOnDistrictSearchListener)
  {
    c = paramOnDistrictSearchListener;
  }
  
  public void setQuery(DistrictSearchQuery paramDistrictSearchQuery)
  {
    b = paramDistrictSearchQuery;
  }
  
  public static abstract interface OnDistrictSearchListener
  {
    public abstract void onDistrictSearched(DistrictResult paramDistrictResult);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.district.DistrictSearch
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */