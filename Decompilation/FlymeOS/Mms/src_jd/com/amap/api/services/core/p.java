package com.amap.api.services.core;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.services.busline.BusLineResult;
import com.amap.api.services.busline.BusLineSearch.OnBusLineSearchListener;
import com.amap.api.services.busline.BusStationResult;
import com.amap.api.services.busline.BusStationSearch.OnBusStationSearchListener;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.DistrictSearch.OnDistrictSearchListener;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.help.Inputtips.InputtipsListener;
import com.amap.api.services.poisearch.PoiItemDetail;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch.OnPoiSearchListener;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RouteSearch.OnRouteSearchListener;
import com.amap.api.services.route.WalkRouteResult;
import java.util.ArrayList;

public class p
  extends Handler
{
  private static p a;
  
  p() {}
  
  p(Looper paramLooper)
  {
    super(paramLooper);
  }
  
  /* Error */
  public static p a()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 34	com/amap/api/services/core/p:a	Lcom/amap/api/services/core/p;
    //   6: ifnonnull +31 -> 37
    //   9: invokestatic 40	android/os/Looper:myLooper	()Landroid/os/Looper;
    //   12: ifnull +12 -> 24
    //   15: invokestatic 40	android/os/Looper:myLooper	()Landroid/os/Looper;
    //   18: invokestatic 43	android/os/Looper:getMainLooper	()Landroid/os/Looper;
    //   21: if_acmpeq +25 -> 46
    //   24: new 2	com/amap/api/services/core/p
    //   27: dup
    //   28: invokestatic 43	android/os/Looper:getMainLooper	()Landroid/os/Looper;
    //   31: invokespecial 44	com/amap/api/services/core/p:<init>	(Landroid/os/Looper;)V
    //   34: putstatic 34	com/amap/api/services/core/p:a	Lcom/amap/api/services/core/p;
    //   37: getstatic 34	com/amap/api/services/core/p:a	Lcom/amap/api/services/core/p;
    //   40: astore_0
    //   41: ldc 2
    //   43: monitorexit
    //   44: aload_0
    //   45: areturn
    //   46: new 2	com/amap/api/services/core/p
    //   49: dup
    //   50: invokespecial 45	com/amap/api/services/core/p:<init>	()V
    //   53: putstatic 34	com/amap/api/services/core/p:a	Lcom/amap/api/services/core/p;
    //   56: goto -19 -> 37
    //   59: astore_0
    //   60: ldc 2
    //   62: monitorexit
    //   63: aload_0
    //   64: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   40	5	0	localp	p
    //   59	5	0	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   3	24	59	finally
    //   24	37	59	finally
    //   37	41	59	finally
    //   46	56	59	finally
  }
  
  private void a(Message paramMessage)
  {
    Object localObject = (b)obj;
    if (localObject == null) {}
    BusStationSearch.OnBusStationSearchListener localOnBusStationSearchListener;
    do
    {
      return;
      localOnBusStationSearchListener = b;
    } while (localOnBusStationSearchListener == null);
    if (what == 0) {}
    for (localObject = a;; localObject = null)
    {
      localOnBusStationSearchListener.onBusStationSearched((BusStationResult)localObject, what);
      return;
    }
  }
  
  private void b(Message paramMessage)
  {
    Object localObject;
    if (what == 60)
    {
      localObject = (e)obj;
      if (localObject != null) {}
    }
    PoiSearch.OnPoiSearchListener localOnPoiSearchListener;
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
              return;
              localOnPoiSearchListener = b;
            } while (localOnPoiSearchListener == null);
            paramMessage = paramMessage.getData();
          } while (paramMessage == null);
          i = paramMessage.getInt("errorCode");
          localOnPoiSearchListener.onPoiSearched(a, i);
          return;
        } while (what != 61);
        localObject = (d)obj;
      } while (localObject == null);
      localOnPoiSearchListener = b;
      paramMessage = paramMessage.getData();
    } while (paramMessage == null);
    int i = paramMessage.getInt("errorCode");
    localOnPoiSearchListener.onPoiItemDetailSearched(a, i);
  }
  
  private void c(Message paramMessage)
  {
    Inputtips.InputtipsListener localInputtipsListener = (Inputtips.InputtipsListener)obj;
    if (localInputtipsListener == null) {
      return;
    }
    ArrayList localArrayList = null;
    if (what == 0) {
      localArrayList = paramMessage.getData().getParcelableArrayList("result");
    }
    localInputtipsListener.onGetInputtips(localArrayList, what);
  }
  
  private void d(Message paramMessage)
  {
    Object localObject;
    if (what == 21)
    {
      localObject = (f)obj;
      if (localObject != null) {}
    }
    GeocodeSearch.OnGeocodeSearchListener localOnGeocodeSearchListener;
    do
    {
      do
      {
        do
        {
          do
          {
            return;
            localOnGeocodeSearchListener = b;
          } while (localOnGeocodeSearchListener == null);
          localOnGeocodeSearchListener.onRegeocodeSearched(a, arg2);
          return;
        } while (what != 20);
        localObject = (c)obj;
      } while (localObject == null);
      localOnGeocodeSearchListener = b;
    } while (localOnGeocodeSearchListener == null);
    localOnGeocodeSearchListener.onGeocodeSearched(a, arg2);
  }
  
  private void e(Message paramMessage)
  {
    DistrictSearch.OnDistrictSearchListener localOnDistrictSearchListener = (DistrictSearch.OnDistrictSearchListener)obj;
    if (localOnDistrictSearchListener == null) {
      return;
    }
    localOnDistrictSearchListener.onDistrictSearched((DistrictResult)paramMessage.getData().getParcelable("result"));
  }
  
  private void f(Message paramMessage)
  {
    Object localObject = (a)obj;
    if (localObject == null) {}
    BusLineSearch.OnBusLineSearchListener localOnBusLineSearchListener;
    do
    {
      return;
      localOnBusLineSearchListener = b;
    } while (localOnBusLineSearchListener == null);
    if (what == 0) {}
    for (localObject = a;; localObject = null)
    {
      localOnBusLineSearchListener.onBusLineSearched((BusLineResult)localObject, what);
      return;
    }
  }
  
  private void g(Message paramMessage)
  {
    RouteSearch.OnRouteSearchListener localOnRouteSearchListener = (RouteSearch.OnRouteSearchListener)obj;
    if (localOnRouteSearchListener == null) {}
    Bundle localBundle;
    do
    {
      do
      {
        do
        {
          do
          {
            return;
            if (what != 10) {
              break;
            }
            localBundle = paramMessage.getData();
          } while (localBundle == null);
          i = localBundle.getInt("errorCode");
          localOnRouteSearchListener.onBusRouteSearched((BusRouteResult)paramMessage.getData().getParcelable("result"), i);
          return;
          if (what != 11) {
            break;
          }
          localBundle = paramMessage.getData();
        } while (localBundle == null);
        i = localBundle.getInt("errorCode");
        localOnRouteSearchListener.onDriveRouteSearched((DriveRouteResult)paramMessage.getData().getParcelable("result"), i);
        return;
      } while (what != 12);
      localBundle = paramMessage.getData();
    } while (localBundle == null);
    int i = localBundle.getInt("errorCode");
    localOnRouteSearchListener.onWalkRouteSearched((WalkRouteResult)paramMessage.getData().getParcelable("result"), i);
  }
  
  public void handleMessage(Message paramMessage)
  {
    try
    {
      switch (arg1)
      {
      case 1: 
        g(paramMessage);
        return;
      }
    }
    catch (Throwable paramMessage)
    {
      d.a(paramMessage, "MessageHandler", "handleMessage");
      return;
    }
    f(paramMessage);
    return;
    e(paramMessage);
    return;
    d(paramMessage);
    return;
    c(paramMessage);
    return;
    b(paramMessage);
    return;
    a(paramMessage);
    return;
  }
  
  public static class a
  {
    public BusLineResult a;
    public BusLineSearch.OnBusLineSearchListener b;
  }
  
  public static class b
  {
    public BusStationResult a;
    public BusStationSearch.OnBusStationSearchListener b;
  }
  
  public static class c
  {
    public GeocodeResult a;
    public GeocodeSearch.OnGeocodeSearchListener b;
  }
  
  public static class d
  {
    public PoiItemDetail a;
    public PoiSearch.OnPoiSearchListener b;
  }
  
  public static class e
  {
    public PoiResult a;
    public PoiSearch.OnPoiSearchListener b;
  }
  
  public static class f
  {
    public RegeocodeResult a;
    public GeocodeSearch.OnGeocodeSearchListener b;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.p
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */