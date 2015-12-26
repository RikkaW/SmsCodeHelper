package com.amap.api.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import com.amap.api.location.core.d;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class LocationManagerProxy
{
  public static final String GPS_PROVIDER = "gps";
  public static final String KEY_LOCATION_CHANGED = "location";
  public static final String KEY_PROVIDER_ENABLED = "providerEnabled";
  public static final String KEY_PROXIMITY_ENTERING = "entering";
  public static final String KEY_STATUS_CHANGED = "status";
  public static final String NETWORK_PROVIDER = "network";
  public static final int WEATHER_TYPE_FORECAST = 2;
  public static final int WEATHER_TYPE_LIVE = 1;
  static Object a = new Object();
  private static LocationManagerProxy c = null;
  private LocationManager b = null;
  private a d = null;
  private Context e;
  private h f;
  private b g;
  private ArrayList<PendingIntent> h = new ArrayList();
  private Hashtable<String, LocationProviderProxy> i = new Hashtable();
  private Vector<i> j = new Vector();
  private Vector<i> k = new Vector();
  private a l = new a();
  
  private LocationManagerProxy(Activity paramActivity)
  {
    a(paramActivity.getApplicationContext());
  }
  
  private LocationManagerProxy(Context paramContext)
  {
    a(paramContext);
  }
  
  private static void a()
  {
    c = null;
  }
  
  private void a(Context paramContext)
  {
    try
    {
      e = paramContext;
      b = ((LocationManager)paramContext.getSystemService("location"));
      d = a.a(paramContext.getApplicationContext(), b);
      return;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private void a(String paramString, long paramLong, float paramFloat, AMapLocationListener paramAMapLocationListener, boolean paramBoolean)
  {
    for (;;)
    {
      try
      {
        if (d != null) {
          break label180;
        }
        d = a.a(e.getApplicationContext(), b);
      }
      catch (Throwable paramString)
      {
        paramString.printStackTrace();
        continue;
      }
      finally {}
      if ("lbs".equals(paramString))
      {
        if (d != null) {
          d.a(paramLong, paramFloat, paramAMapLocationListener, "lbs", paramBoolean);
        }
        return;
      }
      if ("gps".equals(paramString))
      {
        if (d != null) {
          d.a(paramLong, paramFloat, paramAMapLocationListener, "gps", paramBoolean);
        }
      }
      else
      {
        localLooper = e.getMainLooper();
        if (Looper.myLooper() == null) {
          Looper.prepare();
        }
        paramAMapLocationListener = new i(paramLong, paramFloat, paramAMapLocationListener, paramString, false);
        j.add(paramAMapLocationListener);
        b.requestLocationUpdates(paramString, paramLong, paramFloat, l, localLooper);
        continue;
        label180:
        while (paramString != null)
        {
          Looper localLooper;
          break;
        }
        paramString = "lbs";
      }
    }
  }
  
  public static LocationManagerProxy getInstance(Activity paramActivity)
  {
    try
    {
      synchronized (a)
      {
        if (c == null) {
          c = new LocationManagerProxy(paramActivity);
        }
        paramActivity = c;
        return paramActivity;
      }
      return null;
    }
    catch (Throwable paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
  
  public static LocationManagerProxy getInstance(Context paramContext)
  {
    try
    {
      if (c == null) {
        c = new LocationManagerProxy(paramContext);
      }
      paramContext = c;
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = null;
      }
    }
    finally {}
    return paramContext;
  }
  
  public static String getVersion()
  {
    return "V1.3.3";
  }
  
  public void addGeoFenceAlert(double paramDouble1, double paramDouble2, float paramFloat, long paramLong, PendingIntent paramPendingIntent)
  {
    try
    {
      if (d != null) {
        d.b(paramDouble1, paramDouble2, paramFloat, paramLong, paramPendingIntent);
      }
      return;
    }
    catch (Throwable paramPendingIntent)
    {
      paramPendingIntent.printStackTrace();
    }
  }
  
  public boolean addGpsStatusListener(GpsStatus.Listener paramListener)
  {
    try
    {
      if (b != null)
      {
        boolean bool = b.addGpsStatusListener(paramListener);
        return bool;
      }
    }
    catch (Throwable paramListener)
    {
      paramListener.printStackTrace();
    }
    return false;
  }
  
  public void addProximityAlert(double paramDouble1, double paramDouble2, float paramFloat, long paramLong, PendingIntent paramPendingIntent)
  {
    try
    {
      if (d.g) {
        b.addProximityAlert(paramDouble1, paramDouble2, paramFloat, paramLong, paramPendingIntent);
      }
      d.a(paramDouble1, paramDouble2, paramFloat, paramLong, paramPendingIntent);
      return;
    }
    catch (Throwable paramPendingIntent)
    {
      paramPendingIntent.printStackTrace();
    }
  }
  
  public void addTestProvider(String paramString, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6, boolean paramBoolean7, int paramInt1, int paramInt2)
  {
    try
    {
      if (b != null) {
        b.addTestProvider(paramString, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramBoolean5, paramBoolean6, paramBoolean7, paramInt1, paramInt2);
      }
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public void clearTestProviderEnabled(String paramString)
  {
    try
    {
      if (b != null) {
        b.clearTestProviderEnabled(paramString);
      }
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public void clearTestProviderLocation(String paramString)
  {
    try
    {
      if (b != null) {
        b.clearTestProviderLocation(paramString);
      }
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public void clearTestProviderStatus(String paramString)
  {
    try
    {
      if (b != null) {
        b.clearTestProviderStatus(paramString);
      }
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  @Deprecated
  public void destory()
  {
    try
    {
      destroy();
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  public void destroy()
  {
    label150:
    for (;;)
    {
      try
      {
        int m;
        synchronized (a)
        {
          a.c();
          if (i != null) {
            i.clear();
          }
          if (j != null) {
            j.clear();
          }
          if (b != null)
          {
            if (l != null) {
              b.removeUpdates(l);
            }
            if (h != null)
            {
              m = 0;
              if (m < h.size())
              {
                PendingIntent localPendingIntent = (PendingIntent)h.get(m);
                if (localPendingIntent == null) {
                  break label150;
                }
                b.removeUpdates(localPendingIntent);
                break label150;
              }
            }
          }
          if (h != null) {
            h.clear();
          }
          d = null;
          a();
          l = null;
          return;
        }
        m += 1;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        return;
      }
    }
  }
  
  public List<String> getAllProviders()
  {
    try
    {
      List localList1 = b.getAllProviders();
      Object localObject;
      if (localList1 != null)
      {
        localObject = localList1;
        if (!localList1.contains("lbs"))
        {
          localList1.add("lbs");
          return localList1;
        }
      }
      else
      {
        localObject = new ArrayList();
        ((List)localObject).add("lbs");
        ((List)localObject).addAll(b.getAllProviders());
        return (List<String>)localObject;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      List<String> localList = null;
      return localList;
    }
  }
  
  public String getBestProvider(Criteria paramCriteria, boolean paramBoolean)
  {
    String str1 = "lbs";
    if (paramCriteria == null) {
      return "lbs";
    }
    String str2;
    try
    {
      if (!getProvider("lbs").meetsCriteria(paramCriteria)) {
        str1 = b.getBestProvider(paramCriteria, paramBoolean);
      }
      str2 = str1;
      if (paramBoolean)
      {
        str2 = str1;
        if (!d.a(e))
        {
          paramCriteria = b.getBestProvider(paramCriteria, paramBoolean);
          return paramCriteria;
        }
      }
    }
    catch (Throwable paramCriteria)
    {
      paramCriteria.printStackTrace();
      str2 = "gps";
    }
    return str2;
  }
  
  public GpsStatus getGpsStatus(GpsStatus paramGpsStatus)
  {
    GpsStatus localGpsStatus = null;
    try
    {
      if (b != null) {
        localGpsStatus = b.getGpsStatus(paramGpsStatus);
      }
      return localGpsStatus;
    }
    catch (Throwable paramGpsStatus)
    {
      paramGpsStatus.printStackTrace();
    }
    return null;
  }
  
  public AMapLocation getLastKnownLocation(String paramString)
  {
    try
    {
      if (d == null) {
        return null;
      }
      if ("lbs".equals(paramString)) {
        return d.a();
      }
      if (b != null)
      {
        paramString = b.getLastKnownLocation(paramString);
        if (paramString != null)
        {
          paramString = new AMapLocation(paramString);
          return paramString;
        }
      }
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public LocationProviderProxy getProvider(String paramString)
  {
    if (paramString == null) {
      try
      {
        throw new IllegalArgumentException("name不能为空！");
      }
      catch (Throwable paramString)
      {
        paramString.printStackTrace();
        return null;
      }
    }
    if (i.containsKey(paramString)) {
      return (LocationProviderProxy)i.get(paramString);
    }
    LocationProviderProxy localLocationProviderProxy = LocationProviderProxy.a(b, paramString);
    i.put(paramString, localLocationProviderProxy);
    return localLocationProviderProxy;
  }
  
  public List<String> getProviders(Criteria paramCriteria, boolean paramBoolean)
  {
    try
    {
      List localList = b.getProviders(paramCriteria, paramBoolean);
      Object localObject;
      if (localList != null)
      {
        localObject = localList;
        if (localList.size() != 0) {}
      }
      else
      {
        localObject = new ArrayList();
      }
      if ("lbs".equals(getBestProvider(paramCriteria, paramBoolean))) {
        ((List)localObject).add("lbs");
      }
      return (List<String>)localObject;
    }
    catch (Throwable paramCriteria)
    {
      paramCriteria.printStackTrace();
    }
    return null;
  }
  
  public List<String> getProviders(boolean paramBoolean)
  {
    try
    {
      List localList = b.getProviders(paramBoolean);
      Object localObject = localList;
      if (isProviderEnabled("lbs"))
      {
        if (localList != null)
        {
          localObject = localList;
          if (localList.size() != 0) {}
        }
        else
        {
          localObject = new ArrayList();
        }
        ((List)localObject).add("lbs");
      }
      return (List<String>)localObject;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public boolean isProviderEnabled(String paramString)
  {
    try
    {
      if ("lbs".equals(paramString)) {
        return d.a(e);
      }
      boolean bool = b.isProviderEnabled(paramString);
      return bool;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  public void removeGeoFenceAlert(PendingIntent paramPendingIntent)
  {
    try
    {
      if (d != null) {
        d.b(paramPendingIntent);
      }
      return;
    }
    catch (Throwable paramPendingIntent)
    {
      paramPendingIntent.printStackTrace();
    }
  }
  
  public void removeGpsStatusListener(GpsStatus.Listener paramListener)
  {
    try
    {
      if (b != null) {
        b.removeGpsStatusListener(paramListener);
      }
      return;
    }
    catch (Throwable paramListener)
    {
      paramListener.printStackTrace();
    }
  }
  
  public void removeProximityAlert(PendingIntent paramPendingIntent)
  {
    try
    {
      if ((d != null) && (d.g) && (b != null)) {
        b.removeProximityAlert(paramPendingIntent);
      }
      if (d != null) {
        d.a(paramPendingIntent);
      }
      return;
    }
    catch (Throwable paramPendingIntent)
    {
      paramPendingIntent.printStackTrace();
    }
  }
  
  public void removeUpdates(PendingIntent paramPendingIntent)
  {
    try
    {
      if (f != null)
      {
        h.remove(paramPendingIntent);
        if (h.size() == 0) {
          f.a();
        }
      }
      f = null;
      b.removeUpdates(paramPendingIntent);
      return;
    }
    catch (Throwable paramPendingIntent)
    {
      paramPendingIntent.printStackTrace();
    }
  }
  
  public void removeUpdates(AMapLocationListener paramAMapLocationListener)
  {
    if (paramAMapLocationListener != null) {}
    for (;;)
    {
      int n;
      try
      {
        if (d != null) {
          d.a(paramAMapLocationListener);
        }
        b.removeUpdates(paramAMapLocationListener);
        if ((j != null) && (j.size() > 0))
        {
          int m = j.size();
          n = 0;
          if (n < m)
          {
            i locali = (i)j.get(n);
            if (!paramAMapLocationListener.equals(b)) {
              break label151;
            }
            j.remove(locali);
            n -= 1;
            m -= 1;
            break label151;
          }
          if ((j.size() == 0) && (l != null)) {
            b.removeUpdates(l);
          }
        }
      }
      catch (Throwable paramAMapLocationListener)
      {
        paramAMapLocationListener.printStackTrace();
        continue;
      }
      finally {}
      return;
      label151:
      n += 1;
    }
  }
  
  public void requestLocationData(String paramString, long paramLong, float paramFloat, AMapLocationListener paramAMapLocationListener)
  {
    try
    {
      a(paramString, paramLong, paramFloat, paramAMapLocationListener, true);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void requestLocationUpdates(String paramString, long paramLong, float paramFloat, PendingIntent paramPendingIntent)
  {
    try
    {
      if ("lbs".equals(paramString))
      {
        if (f == null) {
          f = new h(this);
        }
        if (g == null) {
          g = new b();
        }
        f.a(g, paramLong, paramFloat, paramString);
        h.add(paramPendingIntent);
        return;
      }
      h.add(paramPendingIntent);
      b.requestLocationUpdates(paramString, paramLong, paramFloat, paramPendingIntent);
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  @Deprecated
  public void requestLocationUpdates(String paramString, long paramLong, float paramFloat, AMapLocationListener paramAMapLocationListener)
  {
    try
    {
      a(paramString, paramLong, paramFloat, paramAMapLocationListener, false);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void requestWeatherUpdates(int paramInt, AMapLocalWeatherListener paramAMapLocalWeatherListener)
  {
    try
    {
      d.a(paramInt, paramAMapLocalWeatherListener);
      return;
    }
    catch (Throwable paramAMapLocalWeatherListener)
    {
      paramAMapLocalWeatherListener.printStackTrace();
    }
  }
  
  public void setGpsEnable(boolean paramBoolean)
  {
    try
    {
      if (d != null) {
        d.a(paramBoolean);
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  public void setTestProviderEnabled(String paramString, boolean paramBoolean)
  {
    try
    {
      if (b != null) {
        b.setTestProviderEnabled(paramString, paramBoolean);
      }
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public void setTestProviderLocation(String paramString, Location paramLocation)
  {
    try
    {
      if (b != null) {
        b.setTestProviderLocation(paramString, paramLocation);
      }
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public void setTestProviderStatus(String paramString, int paramInt, Bundle paramBundle, long paramLong)
  {
    try
    {
      if (b != null) {
        b.setTestProviderStatus(paramString, paramInt, paramBundle, paramLong);
      }
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  class a
    implements AMapLocationListener
  {
    a() {}
    
    /* Error */
    public void onLocationChanged(Location paramLocation)
    {
      // Byte code:
      //   0: iconst_0
      //   1: istore_3
      //   2: aload_1
      //   3: ifnull +497 -> 500
      //   6: new 24	com/amap/api/location/AMapLocation
      //   9: dup
      //   10: aload_1
      //   11: invokespecial 26	com/amap/api/location/AMapLocation:<init>	(Landroid/location/Location;)V
      //   14: astore_1
      //   15: iconst_0
      //   16: istore_2
      //   17: aload_0
      //   18: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   21: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   24: ifnull +100 -> 124
      //   27: iload_2
      //   28: aload_0
      //   29: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   32: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   35: invokevirtual 36	java/util/Vector:size	()I
      //   38: if_icmpge +86 -> 124
      //   41: aload_0
      //   42: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   45: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   48: iload_2
      //   49: invokevirtual 40	java/util/Vector:get	(I)Ljava/lang/Object;
      //   52: checkcast 42	com/amap/api/location/i
      //   55: astore 4
      //   57: aload 4
      //   59: ifnull +22 -> 81
      //   62: aload 4
      //   64: getfield 46	com/amap/api/location/i:b	Lcom/amap/api/location/AMapLocationListener;
      //   67: ifnull +14 -> 81
      //   70: aload 4
      //   72: getfield 46	com/amap/api/location/i:b	Lcom/amap/api/location/AMapLocationListener;
      //   75: aload_1
      //   76: invokeinterface 49 2 0
      //   81: aload 4
      //   83: ifnull +409 -> 492
      //   86: aload 4
      //   88: getfield 52	com/amap/api/location/i:a	J
      //   91: ldc2_w 53
      //   94: lcmp
      //   95: ifne +397 -> 492
      //   98: aload_0
      //   99: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   102: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   105: ifnull +387 -> 492
      //   108: aload_0
      //   109: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   112: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   115: aload 4
      //   117: invokevirtual 61	java/util/Vector:add	(Ljava/lang/Object;)Z
      //   120: pop
      //   121: goto +371 -> 492
      //   124: aload_0
      //   125: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   128: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   131: ifnull +368 -> 499
      //   134: aload_0
      //   135: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   138: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   141: invokevirtual 36	java/util/Vector:size	()I
      //   144: ifle +355 -> 499
      //   147: aload_0
      //   148: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   151: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   154: ifnull +345 -> 499
      //   157: iload_3
      //   158: istore_2
      //   159: iload_2
      //   160: aload_0
      //   161: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   164: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   167: invokevirtual 36	java/util/Vector:size	()I
      //   170: if_icmpge +32 -> 202
      //   173: aload_0
      //   174: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   177: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   180: aload_0
      //   181: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   184: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   187: iload_2
      //   188: invokevirtual 40	java/util/Vector:get	(I)Ljava/lang/Object;
      //   191: invokevirtual 64	java/util/Vector:remove	(Ljava/lang/Object;)Z
      //   194: pop
      //   195: iload_2
      //   196: iconst_1
      //   197: iadd
      //   198: istore_2
      //   199: goto -40 -> 159
      //   202: aload_0
      //   203: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   206: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   209: invokevirtual 67	java/util/Vector:clear	()V
      //   212: aload_0
      //   213: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   216: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   219: invokevirtual 36	java/util/Vector:size	()I
      //   222: ifne +277 -> 499
      //   225: aload_0
      //   226: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   229: invokestatic 71	com/amap/api/location/LocationManagerProxy:e	(Lcom/amap/api/location/LocationManagerProxy;)Landroid/location/LocationManager;
      //   232: ifnull +267 -> 499
      //   235: aload_0
      //   236: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   239: invokestatic 75	com/amap/api/location/LocationManagerProxy:f	(Lcom/amap/api/location/LocationManagerProxy;)Lcom/amap/api/location/LocationManagerProxy$a;
      //   242: ifnull +257 -> 499
      //   245: aload_0
      //   246: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   249: invokestatic 71	com/amap/api/location/LocationManagerProxy:e	(Lcom/amap/api/location/LocationManagerProxy;)Landroid/location/LocationManager;
      //   252: aload_0
      //   253: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   256: invokestatic 75	com/amap/api/location/LocationManagerProxy:f	(Lcom/amap/api/location/LocationManagerProxy;)Lcom/amap/api/location/LocationManagerProxy$a;
      //   259: invokevirtual 81	android/location/LocationManager:removeUpdates	(Landroid/location/LocationListener;)V
      //   262: return
      //   263: aload_0
      //   264: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   267: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   270: ifnull +72 -> 342
      //   273: iload_2
      //   274: aload_0
      //   275: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   278: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   281: invokevirtual 36	java/util/Vector:size	()I
      //   284: if_icmpge +58 -> 342
      //   287: aload_0
      //   288: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   291: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   294: iload_2
      //   295: invokevirtual 40	java/util/Vector:get	(I)Ljava/lang/Object;
      //   298: checkcast 42	com/amap/api/location/i
      //   301: astore_1
      //   302: aload_1
      //   303: ifnull +202 -> 505
      //   306: aload_1
      //   307: getfield 52	com/amap/api/location/i:a	J
      //   310: ldc2_w 53
      //   313: lcmp
      //   314: ifne +191 -> 505
      //   317: aload_0
      //   318: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   321: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   324: ifnull +181 -> 505
      //   327: aload_0
      //   328: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   331: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   334: aload_1
      //   335: invokevirtual 61	java/util/Vector:add	(Ljava/lang/Object;)Z
      //   338: pop
      //   339: goto +166 -> 505
      //   342: aload_0
      //   343: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   346: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   349: ifnull +150 -> 499
      //   352: aload_0
      //   353: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   356: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   359: invokevirtual 36	java/util/Vector:size	()I
      //   362: ifle +137 -> 499
      //   365: aload_0
      //   366: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   369: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   372: ifnull +127 -> 499
      //   375: iconst_0
      //   376: istore_2
      //   377: iload_2
      //   378: aload_0
      //   379: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   382: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   385: invokevirtual 36	java/util/Vector:size	()I
      //   388: if_icmpge +32 -> 420
      //   391: aload_0
      //   392: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   395: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   398: aload_0
      //   399: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   402: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   405: iload_2
      //   406: invokevirtual 40	java/util/Vector:get	(I)Ljava/lang/Object;
      //   409: invokevirtual 64	java/util/Vector:remove	(Ljava/lang/Object;)Z
      //   412: pop
      //   413: iload_2
      //   414: iconst_1
      //   415: iadd
      //   416: istore_2
      //   417: goto -40 -> 377
      //   420: aload_0
      //   421: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   424: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   427: invokevirtual 67	java/util/Vector:clear	()V
      //   430: aload_0
      //   431: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   434: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
      //   437: invokevirtual 36	java/util/Vector:size	()I
      //   440: ifne +59 -> 499
      //   443: aload_0
      //   444: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   447: invokestatic 71	com/amap/api/location/LocationManagerProxy:e	(Lcom/amap/api/location/LocationManagerProxy;)Landroid/location/LocationManager;
      //   450: ifnull +49 -> 499
      //   453: aload_0
      //   454: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   457: invokestatic 75	com/amap/api/location/LocationManagerProxy:f	(Lcom/amap/api/location/LocationManagerProxy;)Lcom/amap/api/location/LocationManagerProxy$a;
      //   460: ifnull +39 -> 499
      //   463: aload_0
      //   464: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   467: invokestatic 71	com/amap/api/location/LocationManagerProxy:e	(Lcom/amap/api/location/LocationManagerProxy;)Landroid/location/LocationManager;
      //   470: aload_0
      //   471: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
      //   474: invokestatic 75	com/amap/api/location/LocationManagerProxy:f	(Lcom/amap/api/location/LocationManagerProxy;)Lcom/amap/api/location/LocationManagerProxy$a;
      //   477: invokevirtual 81	android/location/LocationManager:removeUpdates	(Landroid/location/LocationListener;)V
      //   480: return
      //   481: astore_1
      //   482: aload_1
      //   483: invokevirtual 84	java/lang/Throwable:printStackTrace	()V
      //   486: return
      //   487: astore 5
      //   489: goto -408 -> 81
      //   492: iload_2
      //   493: iconst_1
      //   494: iadd
      //   495: istore_2
      //   496: goto -479 -> 17
      //   499: return
      //   500: iconst_0
      //   501: istore_2
      //   502: goto -239 -> 263
      //   505: iload_2
      //   506: iconst_1
      //   507: iadd
      //   508: istore_2
      //   509: goto -246 -> 263
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	512	0	this	a
      //   0	512	1	paramLocation	Location
      //   16	493	2	i	int
      //   1	157	3	j	int
      //   55	61	4	locali	i
      //   487	1	5	localThrowable	Throwable
      // Exception table:
      //   from	to	target	type
      //   6	15	481	java/lang/Throwable
      //   17	57	481	java/lang/Throwable
      //   86	121	481	java/lang/Throwable
      //   124	157	481	java/lang/Throwable
      //   159	195	481	java/lang/Throwable
      //   202	262	481	java/lang/Throwable
      //   263	302	481	java/lang/Throwable
      //   306	339	481	java/lang/Throwable
      //   342	375	481	java/lang/Throwable
      //   377	413	481	java/lang/Throwable
      //   420	480	481	java/lang/Throwable
      //   62	81	487	java/lang/Throwable
    }
    
    public void onLocationChanged(AMapLocation paramAMapLocation) {}
    
    public void onProviderDisabled(String paramString) {}
    
    public void onProviderEnabled(String paramString) {}
    
    public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {}
  }
  
  class b
    implements AMapLocationListener
  {
    b() {}
    
    public void onLocationChanged(Location paramLocation)
    {
      try
      {
        if ((LocationManagerProxy.a(LocationManagerProxy.this) != null) && (LocationManagerProxy.a(LocationManagerProxy.this).size() > 0))
        {
          Iterator localIterator = LocationManagerProxy.a(LocationManagerProxy.this).iterator();
          while (localIterator.hasNext())
          {
            PendingIntent localPendingIntent = (PendingIntent)localIterator.next();
            Intent localIntent = new Intent();
            Bundle localBundle = new Bundle();
            localBundle.putParcelable("location", paramLocation);
            localIntent.putExtras(localBundle);
            try
            {
              localPendingIntent.send(LocationManagerProxy.b(LocationManagerProxy.this), 0, localIntent);
            }
            catch (PendingIntent.CanceledException localCanceledException)
            {
              localCanceledException.printStackTrace();
            }
          }
        }
        return;
      }
      catch (Throwable paramLocation)
      {
        paramLocation.printStackTrace();
      }
    }
    
    public void onLocationChanged(AMapLocation paramAMapLocation)
    {
      try
      {
        if ((LocationManagerProxy.a(LocationManagerProxy.this) != null) && (LocationManagerProxy.a(LocationManagerProxy.this).size() > 0))
        {
          Iterator localIterator = LocationManagerProxy.a(LocationManagerProxy.this).iterator();
          while (localIterator.hasNext())
          {
            PendingIntent localPendingIntent = (PendingIntent)localIterator.next();
            Intent localIntent = new Intent();
            Bundle localBundle = new Bundle();
            localBundle.putParcelable("location", paramAMapLocation);
            localIntent.putExtras(localBundle);
            try
            {
              localPendingIntent.send(LocationManagerProxy.b(LocationManagerProxy.this), 0, localIntent);
            }
            catch (PendingIntent.CanceledException localCanceledException)
            {
              localCanceledException.printStackTrace();
            }
          }
        }
        return;
      }
      catch (Throwable paramAMapLocation)
      {
        paramAMapLocation.printStackTrace();
      }
    }
    
    public void onProviderDisabled(String paramString) {}
    
    public void onProviderEnabled(String paramString) {}
    
    public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {}
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.LocationManagerProxy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */