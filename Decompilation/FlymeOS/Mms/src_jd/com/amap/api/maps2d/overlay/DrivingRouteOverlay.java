package com.amap.api.maps2d.overlay;

import android.content.Context;
import android.graphics.Bitmap;
import com.amap.api.mapcore2d.y;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.LatLngBounds.Builder;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveStep;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DrivingRouteOverlay
  extends b
{
  private DrivePath a;
  private Bitmap b;
  private List<LatLonPoint> c;
  private boolean d = true;
  protected List<Marker> mPassByMarkers = new ArrayList();
  
  public DrivingRouteOverlay(Context paramContext, AMap paramAMap, DrivePath paramDrivePath, LatLonPoint paramLatLonPoint1, LatLonPoint paramLatLonPoint2)
  {
    super(paramContext);
    mAMap = paramAMap;
    a = paramDrivePath;
    startPoint = a.a(paramLatLonPoint1);
    endPoint = a.a(paramLatLonPoint2);
  }
  
  public DrivingRouteOverlay(Context paramContext, AMap paramAMap, DrivePath paramDrivePath, LatLonPoint paramLatLonPoint1, LatLonPoint paramLatLonPoint2, List<LatLonPoint> paramList)
  {
    super(paramContext);
    mAMap = paramAMap;
    a = paramDrivePath;
    startPoint = a.a(paramLatLonPoint1);
    endPoint = a.a(paramLatLonPoint2);
    c = paramList;
  }
  
  private void a()
  {
    if ((c == null) || (c.size() == 0)) {}
    for (;;)
    {
      return;
      Iterator localIterator = c.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = a.a((LatLonPoint)localIterator.next());
        localObject = mAMap.addMarker(new MarkerOptions().position((LatLng)localObject).title("途经点").visible(d).icon(getPassedByBitmapDescriptor()));
        mPassByMarkers.add(localObject);
      }
    }
  }
  
  public void addToMap()
  {
    List localList = a.getSteps();
    int i = 0;
    if (i < localList.size())
    {
      Object localObject1 = (DriveStep)localList.get(i);
      Object localObject2 = a.a((LatLonPoint)((DriveStep)localObject1).getPolyline().get(0));
      Object localObject3;
      if (i < localList.size() - 1)
      {
        if (i == 0)
        {
          localObject3 = mAMap.addPolyline(new PolylineOptions().add(new LatLng[] { startPoint, localObject2 }).color(getDriveColor()).width(getBuslineWidth()));
          allPolyLines.add(localObject3);
        }
        localObject3 = a.a((LatLonPoint)((DriveStep)localObject1).getPolyline().get(((DriveStep)localObject1).getPolyline().size() - 1));
        LatLng localLatLng = a.a((LatLonPoint)((DriveStep)localList.get(i + 1)).getPolyline().get(0));
        if (!((LatLng)localObject3).equals(localLatLng))
        {
          localObject3 = mAMap.addPolyline(new PolylineOptions().add(new LatLng[] { localObject3, localLatLng }).color(getDriveColor()).width(getBuslineWidth()));
          allPolyLines.add(localObject3);
        }
      }
      for (;;)
      {
        localObject2 = mAMap.addMarker(new MarkerOptions().position((LatLng)localObject2).title("方向:" + ((DriveStep)localObject1).getAction() + "\n道路:" + ((DriveStep)localObject1).getRoad()).snippet(((DriveStep)localObject1).getInstruction()).anchor(0.5F, 0.5F).visible(mNodeIconVisible).icon(getDriveBitmapDescriptor()));
        stationMarkers.add(localObject2);
        localObject1 = mAMap.addPolyline(new PolylineOptions().addAll(a.a(((DriveStep)localObject1).getPolyline())).color(getDriveColor()).width(getBuslineWidth()));
        allPolyLines.add(localObject1);
        i += 1;
        break;
        localObject3 = a.a((LatLonPoint)((DriveStep)localObject1).getPolyline().get(((DriveStep)localObject1).getPolyline().size() - 1));
        localObject3 = mAMap.addPolyline(new PolylineOptions().add(new LatLng[] { localObject3, endPoint }).color(getDriveColor()).width(getBuslineWidth()));
        allPolyLines.add(localObject3);
      }
    }
    a();
    addStartAndEndMarker();
  }
  
  protected float getBuslineWidth()
  {
    return y.b;
  }
  
  protected LatLngBounds getLatLngBounds()
  {
    LatLngBounds.Builder localBuilder = LatLngBounds.builder();
    localBuilder.include(new LatLng(startPoint.latitude, startPoint.longitude));
    localBuilder.include(new LatLng(endPoint.latitude, endPoint.longitude));
    if ((c != null) && (c.size() > 0))
    {
      int i = 0;
      while (i < c.size())
      {
        localBuilder.include(new LatLng(((LatLonPoint)c.get(i)).getLatitude(), ((LatLonPoint)c.get(i)).getLongitude()));
        i += 1;
      }
    }
    return localBuilder.build();
  }
  
  protected BitmapDescriptor getPassedByBitmapDescriptor()
  {
    return getBitDes(b, "amap_throughpoint.png");
  }
  
  public void removeFromMap()
  {
    super.removeFromMap();
    Iterator localIterator = mPassByMarkers.iterator();
    while (localIterator.hasNext()) {
      ((Marker)localIterator.next()).remove();
    }
  }
  
  public void setThroughPointIconVisibility(boolean paramBoolean)
  {
    d = paramBoolean;
    Iterator localIterator = mPassByMarkers.iterator();
    while (localIterator.hasNext()) {
      ((Marker)localIterator.next()).setVisible(paramBoolean);
    }
    mAMap.postInvalidate();
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.overlay.DrivingRouteOverlay
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */