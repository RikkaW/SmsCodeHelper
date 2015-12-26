package com.android.mms.location.amap;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.overlay.PoiOverlay;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kf;
import kv;
import la;
import lb;
import lb.b;
import ld;
import wd;

class GetLocationView$b
  implements lb.b
{
  private GetLocationView$b(GetLocationView paramGetLocationView) {}
  
  public void a(int paramInt)
  {
    wd.a(paramInt, GetLocationView.m(a), 0, 1, true, 0);
  }
  
  public void a(LatLng paramLatLng, String paramString1, String paramString2, boolean paramBoolean)
  {
    GetLocationView.a(a, paramLatLng, paramString1, paramString2);
  }
  
  public void a(PoiResult arg1, int paramInt)
  {
    if (paramInt == 0)
    {
      if ((???.getPois() != null) && (???.getPois().size() > 0))
      {
        ??? = ???.getPois().iterator();
        while (???.hasNext())
        {
          PoiItem localPoiItem = (PoiItem)???.next();
          if (localPoiItem.getLatLonPoint() != null) {
            a.a(localPoiItem);
          }
        }
      }
      synchronized (a.n)
      {
        a.n.notify();
        return;
      }
    }
  }
  
  public void a(String paramString)
  {
    GetLocationView.j(a).a(paramString);
  }
  
  public void a(String paramString1, String paramString2)
  {
    GetLocationView.a(a, paramString1, paramString2);
  }
  
  public void a(ArrayList<PoiItem> paramArrayList)
  {
    GetLocationView.c(a).clear();
    GetLocationView.a(a, new PoiOverlay(GetLocationView.c(a), paramArrayList));
    GetLocationView.n(a).removeFromMap();
    GetLocationView.n(a).addToMap();
    GetLocationView.n(a).zoomToSpan();
  }
  
  public void a(List<PoiItem> paramList)
  {
    if (paramList != null)
    {
      GetLocationView.d(a).a(paramList, GetLocationView.k(a));
      GetLocationView.l(a).p().notifyDataSetChanged();
    }
  }
  
  public void a(ld paramld)
  {
    a.a(paramld, GetLocationView.j(a).b());
  }
  
  public void a(boolean paramBoolean)
  {
    GetLocationView.b(a, paramBoolean);
  }
  
  public boolean a()
  {
    return a.k();
  }
  
  public LatLng b()
  {
    return GetLocationView.j(a).c();
  }
  
  public void c()
  {
    a.c();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.location.amap.GetLocationView.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */