package com.amap.api.maps2d.overlay;

import android.content.Context;
import com.amap.api.mapcore2d.y;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkStep;
import java.util.List;

public class WalkRouteOverlay
  extends b
{
  private WalkPath a;
  
  public WalkRouteOverlay(Context paramContext, AMap paramAMap, WalkPath paramWalkPath, LatLonPoint paramLatLonPoint1, LatLonPoint paramLatLonPoint2)
  {
    super(paramContext);
    mAMap = paramAMap;
    a = paramWalkPath;
    startPoint = a.a(paramLatLonPoint1);
    endPoint = a.a(paramLatLonPoint2);
  }
  
  public void addToMap()
  {
    List localList = a.getSteps();
    int i = 0;
    if (i < localList.size())
    {
      Object localObject1 = (WalkStep)localList.get(i);
      Object localObject2 = a.a((LatLonPoint)((WalkStep)localObject1).getPolyline().get(0));
      Object localObject3;
      if (i < localList.size() - 1)
      {
        if (i == 0)
        {
          localObject3 = mAMap.addPolyline(new PolylineOptions().add(new LatLng[] { startPoint, localObject2 }).color(getWalkColor()).width(getBuslineWidth()));
          allPolyLines.add(localObject3);
        }
        localObject3 = a.a((LatLonPoint)((WalkStep)localObject1).getPolyline().get(((WalkStep)localObject1).getPolyline().size() - 1));
        LatLng localLatLng = a.a((LatLonPoint)((WalkStep)localList.get(i + 1)).getPolyline().get(0));
        if (!((LatLng)localObject3).equals(localLatLng))
        {
          localObject3 = mAMap.addPolyline(new PolylineOptions().add(new LatLng[] { localObject3, localLatLng }).color(getWalkColor()).width(getBuslineWidth()));
          allPolyLines.add(localObject3);
        }
      }
      for (;;)
      {
        localObject2 = mAMap.addMarker(new MarkerOptions().position((LatLng)localObject2).title("方向:" + ((WalkStep)localObject1).getAction() + "\n道路:" + ((WalkStep)localObject1).getRoad()).snippet(((WalkStep)localObject1).getInstruction()).anchor(0.5F, 0.5F).visible(mNodeIconVisible).icon(getWalkBitmapDescriptor()));
        stationMarkers.add(localObject2);
        localObject1 = mAMap.addPolyline(new PolylineOptions().addAll(a.a(((WalkStep)localObject1).getPolyline())).color(getWalkColor()).width(getBuslineWidth()));
        allPolyLines.add(localObject1);
        i += 1;
        break;
        localObject3 = a.a((LatLonPoint)((WalkStep)localObject1).getPolyline().get(((WalkStep)localObject1).getPolyline().size() - 1));
        localObject3 = mAMap.addPolyline(new PolylineOptions().add(new LatLng[] { localObject3, endPoint }).color(getWalkColor()).width(getBuslineWidth()));
        allPolyLines.add(localObject3);
      }
    }
    addStartAndEndMarker();
  }
  
  protected float getBuslineWidth()
  {
    return y.b;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.overlay.WalkRouteOverlay
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */