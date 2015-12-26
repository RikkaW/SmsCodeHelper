package com.amap.api.maps2d.overlay;

import android.content.Context;
import com.amap.api.mapcore2d.y;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.PolylineOptions;
import com.amap.api.services.busline.BusStationItem;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusStep;
import com.amap.api.services.route.Doorway;
import com.amap.api.services.route.RouteBusLineItem;
import com.amap.api.services.route.RouteBusWalkItem;
import com.amap.api.services.route.WalkStep;
import java.util.Iterator;
import java.util.List;

public class BusRouteOverlay
  extends b
{
  private BusPath a;
  private LatLng b;
  
  public BusRouteOverlay(Context paramContext, AMap paramAMap, BusPath paramBusPath, LatLonPoint paramLatLonPoint1, LatLonPoint paramLatLonPoint2)
  {
    super(paramContext);
    a = paramBusPath;
    startPoint = a.a(paramLatLonPoint1);
    endPoint = a.a(paramLatLonPoint2);
    mAMap = paramAMap;
  }
  
  private LatLonPoint a(BusStep paramBusStep)
  {
    paramBusStep = paramBusStep.getExit();
    if (paramBusStep == null) {
      return null;
    }
    return paramBusStep.getLatLonPoint();
  }
  
  private String a(RouteBusLineItem paramRouteBusLineItem)
  {
    return "(" + paramRouteBusLineItem.getDepartureBusStation().getBusStationName() + "-->" + paramRouteBusLineItem.getArrivalBusStation().getBusStationName() + ") 经过" + (paramRouteBusLineItem.getPassStationNum() + 1) + "站";
  }
  
  private String a(List<WalkStep> paramList)
  {
    paramList = paramList.iterator();
    for (float f = 0.0F; paramList.hasNext(); f = ((WalkStep)paramList.next()).getDistance() + f) {}
    return "步行" + f + "米";
  }
  
  private LatLonPoint b(BusStep paramBusStep)
  {
    paramBusStep = paramBusStep.getEntrance();
    if (paramBusStep == null) {
      return null;
    }
    return paramBusStep.getLatLonPoint();
  }
  
  public void addToMap()
  {
    List localList = a.getSteps();
    int i = 0;
    while (i < localList.size())
    {
      Object localObject3 = (BusStep)localList.get(i);
      Object localObject4;
      Object localObject5;
      Object localObject2;
      Object localObject1;
      if (i < localList.size() - 1)
      {
        localObject4 = (BusStep)localList.get(i + 1);
        if ((((BusStep)localObject3).getWalk() != null) && (((BusStep)localObject3).getBusLine() != null))
        {
          localObject5 = a.a((LatLonPoint)((WalkStep)((BusStep)localObject3).getWalk().getSteps().get(((BusStep)localObject3).getWalk().getSteps().size() - 1)).getPolyline().get(((WalkStep)((BusStep)localObject3).getWalk().getSteps().get(((BusStep)localObject3).getWalk().getSteps().size() - 1)).getPolyline().size() - 1));
          localObject2 = b((BusStep)localObject3);
          localObject1 = localObject2;
          if (localObject2 == null) {
            localObject1 = (LatLonPoint)((BusStep)localObject3).getBusLine().getPolyline().get(0);
          }
          localObject1 = a.a((LatLonPoint)localObject1);
          localObject1 = mAMap.addPolyline(new PolylineOptions().add(new LatLng[] { localObject5, localObject1 }).width(getBuslineWidth()).color(getWalkColor()));
          allPolyLines.add(localObject1);
        }
        if ((((BusStep)localObject3).getBusLine() != null) && (((BusStep)localObject4).getWalk() != null))
        {
          localObject1 = a((BusStep)localObject3);
          localObject2 = (LatLonPoint)((BusStep)localObject3).getBusLine().getPolyline().get(((BusStep)localObject3).getBusLine().getPolyline().size() - 1);
          if (localObject1 != null) {
            break label1244;
          }
          localObject1 = localObject2;
        }
      }
      for (;;)
      {
        localObject1 = a.a((LatLonPoint)localObject1);
        localObject2 = a.a((LatLonPoint)((WalkStep)((BusStep)localObject4).getWalk().getSteps().get(0)).getPolyline().get(0));
        localObject1 = mAMap.addPolyline(new PolylineOptions().add(new LatLng[] { localObject1, localObject2 }).width(getBuslineWidth()).color(getWalkColor()));
        allPolyLines.add(localObject1);
        if ((((BusStep)localObject3).getBusLine() != null) && (((BusStep)localObject4).getWalk() == null) && (((BusStep)localObject4).getBusLine() != null))
        {
          localObject2 = a((BusStep)localObject3);
          localObject1 = localObject2;
          if (localObject2 == null) {
            localObject1 = (LatLonPoint)((BusStep)localObject3).getBusLine().getPolyline().get(((BusStep)localObject3).getBusLine().getPolyline().size() - 1);
          }
          localObject5 = a.a((LatLonPoint)localObject1);
          localObject2 = b((BusStep)localObject4);
          localObject1 = localObject2;
          if (localObject2 == null) {
            localObject1 = (LatLonPoint)((BusStep)localObject4).getBusLine().getPolyline().get(0);
          }
          drawLineArrow((LatLng)localObject5, a.a((LatLonPoint)localObject1));
        }
        if ((((BusStep)localObject3).getBusLine() != null) && (((BusStep)localObject4).getWalk() == null) && (((BusStep)localObject4).getBusLine() != null))
        {
          localObject1 = a.a((LatLonPoint)((BusStep)localObject3).getBusLine().getPolyline().get(((BusStep)localObject3).getBusLine().getPolyline().size() - 1));
          localObject2 = a.a((LatLonPoint)((BusStep)localObject4).getBusLine().getPolyline().get(0));
          if ((latitude - latitude > 1.0E-4D) || (longitude - longitude > 1.0E-4D)) {
            drawLineArrow((LatLng)localObject1, (LatLng)localObject2);
          }
        }
        if ((((BusStep)localObject3).getWalk() != null) && (((BusStep)localObject3).getBusLine() != null))
        {
          localObject2 = b((BusStep)localObject3);
          localObject1 = localObject2;
          if (localObject2 == null) {
            localObject1 = (LatLonPoint)((WalkStep)((BusStep)localObject3).getWalk().getSteps().get(((BusStep)localObject3).getWalk().getSteps().size() - 1)).getPolyline().get(((WalkStep)((BusStep)localObject3).getWalk().getSteps().get(((BusStep)localObject3).getWalk().getSteps().size() - 1)).getPolyline().size() - 1);
          }
          localObject1 = a.a((LatLonPoint)localObject1);
          localObject2 = a.a((LatLonPoint)((BusStep)localObject3).getBusLine().getPolyline().get(0));
          localObject1 = mAMap.addPolyline(new PolylineOptions().add(new LatLng[] { localObject1, localObject2 }).width(3.0F).color(getWalkColor()).width(getBuslineWidth()));
          allPolyLines.add(localObject1);
        }
        if ((((BusStep)localObject3).getWalk() == null) || (((BusStep)localObject3).getWalk().getSteps().size() <= 0)) {
          break;
        }
        localObject1 = ((BusStep)localObject3).getWalk().getSteps();
        int j = 0;
        while (j < ((List)localObject1).size())
        {
          localObject2 = (WalkStep)((List)localObject1).get(j);
          localObject4 = a.a((LatLonPoint)((WalkStep)localObject2).getPolyline().get(0));
          localObject5 = ((WalkStep)localObject2).getRoad();
          String str = a((List)localObject1);
          localObject2 = a.a(((WalkStep)localObject2).getPolyline());
          b = ((LatLng)((List)localObject2).get(((List)localObject2).size() - 1));
          if (j == 0)
          {
            localObject4 = mAMap.addMarker(new MarkerOptions().position((LatLng)localObject4).title((String)localObject5).snippet(str).anchor(0.5F, 0.5F).visible(mNodeIconVisible).icon(getWalkBitmapDescriptor()));
            stationMarkers.add(localObject4);
          }
          localObject4 = mAMap.addPolyline(new PolylineOptions().addAll((Iterable)localObject2).color(getWalkColor()).width(getBuslineWidth()));
          allPolyLines.add(localObject4);
          if (j < ((List)localObject1).size() - 1)
          {
            localObject2 = (LatLng)((List)localObject2).get(((List)localObject2).size() - 1);
            localObject4 = a.a((LatLonPoint)((WalkStep)((List)localObject1).get(j + 1)).getPolyline().get(0));
            if (!((LatLng)localObject2).equals(localObject4))
            {
              localObject2 = mAMap.addPolyline(new PolylineOptions().add(new LatLng[] { localObject2, localObject4 }).width(getBuslineWidth()).color(getWalkColor()));
              allPolyLines.add(localObject2);
            }
          }
          j += 1;
        }
        label1244:
        localObject2 = a.a((LatLonPoint)localObject2);
        localObject5 = a.a((LatLonPoint)localObject1);
        localObject2 = mAMap.addPolyline(new PolylineOptions().add(new LatLng[] { localObject2, localObject5 }).width(getBuslineWidth()).color(getWalkColor()));
        allPolyLines.add(localObject2);
      }
      if (((BusStep)localObject3).getBusLine() == null)
      {
        localObject1 = mAMap.addPolyline(new PolylineOptions().add(new LatLng[] { b, endPoint }).color(getWalkColor()).width(getBuslineWidth()));
        allPolyLines.add(localObject1);
      }
      if (((BusStep)localObject3).getBusLine() != null)
      {
        localObject1 = ((BusStep)localObject3).getBusLine();
        localObject3 = a.a(((RouteBusLineItem)localObject1).getPolyline());
        localObject2 = ((RouteBusLineItem)localObject1).getDepartureBusStation();
        localObject3 = mAMap.addPolyline(new PolylineOptions().addAll((Iterable)localObject3).color(getBusColor()).width(getBuslineWidth()));
        allPolyLines.add(localObject3);
        localObject1 = mAMap.addMarker(new MarkerOptions().position(a.a(((BusStationItem)localObject2).getLatLonPoint())).title(((RouteBusLineItem)localObject1).getBusLineName()).snippet(a((RouteBusLineItem)localObject1)).visible(mNodeIconVisible).anchor(0.5F, 0.5F).icon(getBusBitmapDescriptor()));
        stationMarkers.add(localObject1);
      }
      i += 1;
    }
    addStartAndEndMarker();
  }
  
  public void drawLineArrow(LatLng paramLatLng1, LatLng paramLatLng2)
  {
    mAMap.addPolyline(new PolylineOptions().add(new LatLng[] { paramLatLng1, paramLatLng2 }).width(3.0F).color(getBusColor()).width(getBuslineWidth()));
  }
  
  protected float getBuslineWidth()
  {
    return y.b;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.overlay.BusRouteOverlay
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */