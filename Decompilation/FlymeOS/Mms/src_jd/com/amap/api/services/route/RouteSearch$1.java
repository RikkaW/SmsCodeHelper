package com.amap.api.services.route;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.d;
import com.amap.api.services.core.p;

class RouteSearch$1
  extends Thread
{
  RouteSearch$1(RouteSearch paramRouteSearch, RouteSearch.WalkRouteQuery paramWalkRouteQuery) {}
  
  public void run()
  {
    Message localMessage = p.a().obtainMessage();
    what = 12;
    arg1 = 1;
    Bundle localBundle = new Bundle();
    Object localObject3 = null;
    Object localObject1 = null;
    try
    {
      WalkRouteResult localWalkRouteResult = b.calculateWalkRoute(a);
      localObject1 = localWalkRouteResult;
      localObject3 = localWalkRouteResult;
      localBundle.putInt("errorCode", 0);
      obj = RouteSearch.a(b);
      localBundle.putParcelable("result", localWalkRouteResult);
      localMessage.setData(localBundle);
      RouteSearch.b(b).sendMessage(localMessage);
      return;
    }
    catch (AMapException localAMapException)
    {
      localObject3 = localObject1;
      d.a(localAMapException, "RouteSearch", "calculateWalkRouteAsyn");
      localObject3 = localObject1;
      localBundle.putInt("errorCode", localAMapException.getErrorCode());
      return;
    }
    finally
    {
      obj = RouteSearch.a(b);
      localBundle.putParcelable("result", (Parcelable)localObject3);
      localMessage.setData(localBundle);
      RouteSearch.b(b).sendMessage(localMessage);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.RouteSearch.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */