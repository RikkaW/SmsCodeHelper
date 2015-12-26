package com.amap.api.services.route;

public abstract interface RouteSearch$OnRouteSearchListener
{
  public abstract void onBusRouteSearched(BusRouteResult paramBusRouteResult, int paramInt);
  
  public abstract void onDriveRouteSearched(DriveRouteResult paramDriveRouteResult, int paramInt);
  
  public abstract void onWalkRouteSearched(WalkRouteResult paramWalkRouteResult, int paramInt);
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.RouteSearch.OnRouteSearchListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */