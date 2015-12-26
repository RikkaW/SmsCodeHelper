package com.amap.api.maps2d.model;

import com.amap.api.mapcore2d.cp;

public final class LatLngBounds$Builder
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

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.LatLngBounds.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */