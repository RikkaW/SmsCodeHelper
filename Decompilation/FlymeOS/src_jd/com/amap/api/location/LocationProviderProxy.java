package com.amap.api.location;

import android.location.Criteria;
import android.location.LocationManager;
import android.location.LocationProvider;

public class LocationProviderProxy
{
  public static final String AMapNetwork = "lbs";
  public static final int AVAILABLE = 2;
  public static final int OUT_OF_SERVICE = 0;
  public static final int TEMPORARILY_UNAVAILABLE = 1;
  private LocationManager a;
  private String b;
  
  protected LocationProviderProxy(LocationManager paramLocationManager, String paramString)
  {
    a = paramLocationManager;
    b = paramString;
  }
  
  private LocationProvider a()
  {
    try
    {
      if (a != null)
      {
        LocationProvider localLocationProvider = a.getProvider(b);
        return localLocationProvider;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  static LocationProviderProxy a(LocationManager paramLocationManager, String paramString)
  {
    return new LocationProviderProxy(paramLocationManager, paramString);
  }
  
  public int getAccuracy()
  {
    if ("lbs" != null) {}
    try
    {
      if ("lbs".equals(b)) {
        return 2;
      }
      if (a() != null)
      {
        int i = a().getAccuracy();
        return i;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return -1;
  }
  
  public String getName()
  {
    if ("lbs" != null) {}
    try
    {
      if ("lbs".equals(b)) {
        return "lbs";
      }
      if (a() != null)
      {
        String str = a().getName();
        return str;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return "null";
  }
  
  public int getPowerRequirement()
  {
    if ("lbs" != null) {}
    try
    {
      if ("lbs".equals(b)) {
        return 2;
      }
      if (a() != null)
      {
        int i = a().getPowerRequirement();
        return i;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return -1;
  }
  
  public boolean hasMonetaryCost()
  {
    if ("lbs" != null) {}
    try
    {
      if ("lbs".equals(b)) {
        return false;
      }
      if (a() != null)
      {
        boolean bool = a().hasMonetaryCost();
        return bool;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return false;
  }
  
  public boolean meetsCriteria(Criteria paramCriteria)
  {
    boolean bool = true;
    if ("lbs" != null) {}
    try
    {
      if ("lbs".equals(b))
      {
        if (paramCriteria == null) {
          return true;
        }
        if ((paramCriteria.isAltitudeRequired()) || (paramCriteria.isBearingRequired()) || (paramCriteria.isSpeedRequired())) {
          break label84;
        }
        if (paramCriteria.getAccuracy() == 1) {
          break label84;
        }
      }
      else if (a() != null)
      {
        bool = a().meetsCriteria(paramCriteria);
        return bool;
      }
    }
    catch (Throwable paramCriteria)
    {
      paramCriteria.printStackTrace();
      bool = false;
    }
    return bool;
    label84:
    return false;
  }
  
  public boolean requiresCell()
  {
    if ("lbs" != null) {}
    try
    {
      if ("lbs".equals(b)) {
        return true;
      }
      if (a() != null)
      {
        boolean bool = a().requiresCell();
        return bool;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return true;
  }
  
  public boolean requiresNetwork()
  {
    if ("lbs" != null) {}
    try
    {
      if ("lbs".equals(b)) {
        return true;
      }
      if (a() != null)
      {
        boolean bool = a().requiresNetwork();
        return bool;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return true;
  }
  
  public boolean requiresSatellite()
  {
    if ("lbs" != null) {}
    try
    {
      if ("lbs".equals(b)) {
        return false;
      }
      if (a() != null)
      {
        boolean bool = a().requiresNetwork();
        return bool;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return true;
  }
  
  public boolean supportsAltitude()
  {
    if ("lbs" != null) {}
    try
    {
      if ("lbs".equals(b)) {
        return false;
      }
      if (a() != null)
      {
        boolean bool = a().supportsAltitude();
        return bool;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return false;
  }
  
  public boolean supportsBearing()
  {
    if ("lbs" != null) {}
    try
    {
      if ("lbs".equals(b)) {
        return false;
      }
      if (a() != null)
      {
        boolean bool = a().supportsBearing();
        return bool;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return false;
  }
  
  public boolean supportsSpeed()
  {
    if ("lbs" != null) {}
    try
    {
      if ("lbs".equals(b)) {
        return false;
      }
      if (a() != null)
      {
        boolean bool = a().supportsSpeed();
        return bool;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.LocationProviderProxy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */