package com.amap.api.maps2d.overlay;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.LatLngBounds.Builder;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PoiOverlay
{
  private List<PoiItem> a;
  private AMap b;
  private ArrayList<Marker> c = new ArrayList();
  
  public PoiOverlay(AMap paramAMap, List<PoiItem> paramList)
  {
    b = paramAMap;
    a = paramList;
  }
  
  private LatLngBounds a()
  {
    LatLngBounds.Builder localBuilder = LatLngBounds.builder();
    int i = 0;
    while (i < a.size())
    {
      localBuilder.include(new LatLng(((PoiItem)a.get(i)).getLatLonPoint().getLatitude(), ((PoiItem)a.get(i)).getLatLonPoint().getLongitude()));
      i += 1;
    }
    return localBuilder.build();
  }
  
  private MarkerOptions a(int paramInt)
  {
    return new MarkerOptions().position(new LatLng(((PoiItem)a.get(paramInt)).getLatLonPoint().getLatitude(), ((PoiItem)a.get(paramInt)).getLatLonPoint().getLongitude())).title(getTitle(paramInt)).snippet(getSnippet(paramInt)).icon(getBitmapDescriptor(paramInt));
  }
  
  public void addToMap()
  {
    int i = 0;
    while (i < a.size())
    {
      Marker localMarker = b.addMarker(a(i));
      localMarker.setObject(Integer.valueOf(i));
      c.add(localMarker);
      i += 1;
    }
  }
  
  protected BitmapDescriptor getBitmapDescriptor(int paramInt)
  {
    return null;
  }
  
  public int getPoiIndex(Marker paramMarker)
  {
    int i = 0;
    while (i < c.size())
    {
      if (((Marker)c.get(i)).equals(paramMarker)) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public PoiItem getPoiItem(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= a.size())) {
      return null;
    }
    return (PoiItem)a.get(paramInt);
  }
  
  protected String getSnippet(int paramInt)
  {
    return ((PoiItem)a.get(paramInt)).getSnippet();
  }
  
  protected String getTitle(int paramInt)
  {
    return ((PoiItem)a.get(paramInt)).getTitle();
  }
  
  public void removeFromMap()
  {
    Iterator localIterator = c.iterator();
    while (localIterator.hasNext()) {
      ((Marker)localIterator.next()).remove();
    }
  }
  
  public void zoomToSpan()
  {
    if ((a == null) || (a.size() <= 0) || (b == null)) {
      return;
    }
    LatLngBounds localLatLngBounds = a();
    b.moveCamera(CameraUpdateFactory.newLatLngBounds(localLatLngBounds, 5));
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.overlay.PoiOverlay
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */