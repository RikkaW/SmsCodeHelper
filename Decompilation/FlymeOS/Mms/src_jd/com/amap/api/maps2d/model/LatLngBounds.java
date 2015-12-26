package com.amap.api.maps2d.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.mapcore2d.cp;
import com.amap.api.mapcore2d.cy;

public final class LatLngBounds
  implements Parcelable
{
  public static final e CREATOR = new e();
  private final int a;
  public final LatLng northeast;
  public final LatLng southwest;
  
  LatLngBounds(int paramInt, LatLng paramLatLng1, LatLng paramLatLng2)
  {
    cp.a(paramLatLng1, "null southwest");
    cp.a(paramLatLng2, "null northeast");
    if (latitude >= latitude) {}
    for (boolean bool = true;; bool = false)
    {
      cp.a(bool, "southern latitude exceeds northern latitude (%s > %s)", new Object[] { Double.valueOf(latitude), Double.valueOf(latitude) });
      a = paramInt;
      southwest = paramLatLng1;
      northeast = paramLatLng2;
      return;
    }
  }
  
  public LatLngBounds(LatLng paramLatLng1, LatLng paramLatLng2)
  {
    this(1, paramLatLng1, paramLatLng2);
  }
  
  private boolean a(double paramDouble)
  {
    return (southwest.latitude <= paramDouble) && (paramDouble <= northeast.latitude);
  }
  
  private boolean a(LatLngBounds paramLatLngBounds)
  {
    if ((paramLatLngBounds == null) || (northeast == null) || (southwest == null) || (northeast == null) || (southwest == null)) {}
    double d1;
    double d2;
    double d3;
    double d4;
    double d5;
    double d6;
    double d7;
    double d8;
    double d9;
    double d10;
    double d11;
    double d12;
    double d13;
    double d14;
    double d15;
    double d16;
    do
    {
      return false;
      d1 = northeast.longitude;
      d2 = southwest.longitude;
      d3 = northeast.longitude;
      d4 = southwest.longitude;
      d5 = northeast.longitude;
      d6 = southwest.longitude;
      d7 = northeast.longitude;
      d8 = southwest.longitude;
      d9 = northeast.latitude;
      d10 = southwest.latitude;
      d11 = northeast.latitude;
      d12 = southwest.latitude;
      d13 = northeast.latitude;
      d14 = southwest.latitude;
      d15 = northeast.latitude;
      d16 = southwest.latitude;
    } while ((Math.abs(d1 + d2 - d3 - d4) >= d5 - d6 + d7 - d8) || (Math.abs(d9 + d10 - d11 - d12) >= d13 - d14 + d15 - d16));
    return true;
  }
  
  private boolean b(double paramDouble)
  {
    boolean bool = false;
    if (southwest.longitude <= northeast.longitude) {
      return (southwest.longitude <= paramDouble) && (paramDouble <= northeast.longitude);
    }
    if ((southwest.longitude <= paramDouble) || (paramDouble <= northeast.longitude)) {
      bool = true;
    }
    return bool;
  }
  
  public static Builder builder()
  {
    return new Builder();
  }
  
  private static double c(double paramDouble1, double paramDouble2)
  {
    return (paramDouble1 - paramDouble2 + 360.0D) % 360.0D;
  }
  
  private static double d(double paramDouble1, double paramDouble2)
  {
    return (paramDouble2 - paramDouble1 + 360.0D) % 360.0D;
  }
  
  int a()
  {
    return a;
  }
  
  public boolean contains(LatLng paramLatLng)
  {
    return (a(latitude)) && (b(longitude));
  }
  
  public boolean contains(LatLngBounds paramLatLngBounds)
  {
    if (paramLatLngBounds == null) {}
    while ((!contains(southwest)) || (!contains(northeast))) {
      return false;
    }
    return true;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof LatLngBounds)) {
        return false;
      }
      paramObject = (LatLngBounds)paramObject;
    } while ((southwest.equals(southwest)) && (northeast.equals(northeast)));
    return false;
  }
  
  public int hashCode()
  {
    return cy.a(new Object[] { southwest, northeast });
  }
  
  public LatLngBounds including(LatLng paramLatLng)
  {
    double d1 = Math.min(southwest.latitude, latitude);
    double d2 = Math.max(northeast.latitude, latitude);
    double d3 = northeast.longitude;
    double d4 = southwest.longitude;
    double d5 = longitude;
    if ((!b(d5)) && (c(d4, d5) < d(d3, d5))) {}
    return new LatLngBounds(new LatLng(d1, d5), new LatLng(d2, d5));
  }
  
  public boolean intersects(LatLngBounds paramLatLngBounds)
  {
    if (paramLatLngBounds == null) {}
    while ((!a(paramLatLngBounds)) && (!paramLatLngBounds.a(this))) {
      return false;
    }
    return true;
  }
  
  public String toString()
  {
    return cy.a(new String[] { cy.a("southwest", southwest), cy.a("northeast", northeast) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    e.a(this, paramParcel, paramInt);
  }
  
  public static final class Builder
  {
    private double a = Double.POSITIVE_INFINITY;
    private double b = Double.NEGATIVE_INFINITY;
    private double c = NaN.0D;
    private double d = NaN.0D;
    
    private boolean a(double paramDouble)
    {
      boolean bool = false;
      if (c <= d) {
        return (c <= paramDouble) && (paramDouble <= d);
      }
      if ((c <= paramDouble) || (paramDouble <= d)) {
        bool = true;
      }
      return bool;
    }
    
    public LatLngBounds build()
    {
      if (!Double.isNaN(c)) {}
      for (boolean bool = true;; bool = false)
      {
        cp.a(bool, "no included points");
        return new LatLngBounds(new LatLng(a, c), new LatLng(b, d));
      }
    }
    
    public Builder include(LatLng paramLatLng)
    {
      a = Math.min(a, latitude);
      b = Math.max(b, latitude);
      double d1 = longitude;
      if (Double.isNaN(c))
      {
        c = d1;
        d = d1;
      }
      while (a(d1)) {
        return this;
      }
      if (LatLngBounds.a(c, d1) < LatLngBounds.b(d, d1))
      {
        c = d1;
        return this;
      }
      d = d1;
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.LatLngBounds
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */