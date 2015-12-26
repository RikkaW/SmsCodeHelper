package com.amap.api.maps2d.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.mapcore2d.cp;
import com.amap.api.mapcore2d.cy;

public final class CameraPosition
  implements Parcelable
{
  public static final b CREATOR = new b();
  public final float bearing;
  public final LatLng target;
  public final float tilt;
  public final float zoom;
  
  public CameraPosition(LatLng paramLatLng, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    cp.a(paramLatLng, "CameraPosition 位置不能为null");
    target = paramLatLng;
    zoom = cy.b(paramFloat1);
    tilt = cy.a(paramFloat2);
    paramFloat1 = paramFloat3;
    if (paramFloat3 <= 0.0D) {
      paramFloat1 = paramFloat3 % 360.0F + 360.0F;
    }
    bearing = (paramFloat1 % 360.0F);
  }
  
  public static Builder builder()
  {
    return new Builder();
  }
  
  public static Builder builder(CameraPosition paramCameraPosition)
  {
    return new Builder(paramCameraPosition);
  }
  
  public static final CameraPosition fromLatLngZoom(LatLng paramLatLng, float paramFloat)
  {
    return new CameraPosition(paramLatLng, paramFloat, 0.0F, 0.0F);
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
      if (!(paramObject instanceof CameraPosition)) {
        return false;
      }
      paramObject = (CameraPosition)paramObject;
    } while ((target.equals(target)) && (Float.floatToIntBits(zoom) == Float.floatToIntBits(zoom)) && (Float.floatToIntBits(tilt) == Float.floatToIntBits(tilt)) && (Float.floatToIntBits(bearing) == Float.floatToIntBits(bearing)));
    return false;
  }
  
  public int hashCode()
  {
    return super.hashCode();
  }
  
  public String toString()
  {
    return cy.a(new String[] { cy.a("target", target), cy.a("zoom", Float.valueOf(zoom)), cy.a("tilt", Float.valueOf(tilt)), cy.a("bearing", Float.valueOf(bearing)) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeFloat(bearing);
    paramParcel.writeFloat((float)target.latitude);
    paramParcel.writeFloat((float)target.longitude);
    paramParcel.writeFloat(tilt);
    paramParcel.writeFloat(zoom);
  }
  
  public static final class Builder
  {
    private LatLng a;
    private float b;
    private float c;
    private float d;
    
    public Builder() {}
    
    public Builder(CameraPosition paramCameraPosition)
    {
      target(target).bearing(bearing).tilt(tilt).zoom(zoom);
    }
    
    public Builder bearing(float paramFloat)
    {
      d = paramFloat;
      return this;
    }
    
    public CameraPosition build()
    {
      cp.a(a);
      return new CameraPosition(a, b, c, d);
    }
    
    public Builder target(LatLng paramLatLng)
    {
      a = paramLatLng;
      return this;
    }
    
    public Builder tilt(float paramFloat)
    {
      c = paramFloat;
      return this;
    }
    
    public Builder zoom(float paramFloat)
    {
      b = paramFloat;
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.CameraPosition
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */