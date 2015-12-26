package com.amap.api.maps2d.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.mapcore2d.cy;

public final class VisibleRegion
  implements Parcelable
{
  public static final l CREATOR = new l();
  private final int a;
  public final LatLng farLeft;
  public final LatLng farRight;
  public final LatLngBounds latLngBounds;
  public final LatLng nearLeft;
  public final LatLng nearRight;
  
  public VisibleRegion(int paramInt, LatLng paramLatLng1, LatLng paramLatLng2, LatLng paramLatLng3, LatLng paramLatLng4, LatLngBounds paramLatLngBounds)
  {
    a = paramInt;
    nearLeft = paramLatLng1;
    nearRight = paramLatLng2;
    farLeft = paramLatLng3;
    farRight = paramLatLng4;
    latLngBounds = paramLatLngBounds;
  }
  
  public VisibleRegion(LatLng paramLatLng1, LatLng paramLatLng2, LatLng paramLatLng3, LatLng paramLatLng4, LatLngBounds paramLatLngBounds)
  {
    this(1, paramLatLng1, paramLatLng2, paramLatLng3, paramLatLng4, paramLatLngBounds);
  }
  
  int a()
  {
    return a;
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
      if (!(paramObject instanceof VisibleRegion)) {
        return false;
      }
      paramObject = (VisibleRegion)paramObject;
    } while ((nearLeft.equals(nearLeft)) && (nearRight.equals(nearRight)) && (farLeft.equals(farLeft)) && (farRight.equals(farRight)) && (latLngBounds.equals(latLngBounds)));
    return false;
  }
  
  public int hashCode()
  {
    return cy.a(new Object[] { nearLeft, nearRight, farLeft, farRight, latLngBounds });
  }
  
  public String toString()
  {
    return cy.a(new String[] { cy.a("nearLeft", nearLeft), cy.a("nearRight", nearRight), cy.a("farLeft", farLeft), cy.a("farRight", farRight), cy.a("latLngBounds", latLngBounds) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    l.a(this, paramParcel, paramInt);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.VisibleRegion
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */