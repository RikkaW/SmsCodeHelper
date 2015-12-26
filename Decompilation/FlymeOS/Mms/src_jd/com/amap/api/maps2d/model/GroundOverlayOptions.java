package com.amap.api.maps2d.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.mapcore2d.cp;

public final class GroundOverlayOptions
  implements Parcelable
{
  public static final d CREATOR = new d();
  public static final float NO_DIMENSION = -1.0F;
  private final int a;
  private BitmapDescriptor b;
  private LatLng c;
  private float d;
  private float e;
  private LatLngBounds f;
  private float g;
  private float h;
  private boolean i = true;
  private float j = 0.0F;
  private float k = 0.5F;
  private float l = 0.5F;
  
  public GroundOverlayOptions()
  {
    a = 1;
  }
  
  GroundOverlayOptions(int paramInt, IBinder paramIBinder, LatLng paramLatLng, float paramFloat1, float paramFloat2, LatLngBounds paramLatLngBounds, float paramFloat3, float paramFloat4, boolean paramBoolean, float paramFloat5, float paramFloat6, float paramFloat7)
  {
    a = paramInt;
    b = BitmapDescriptorFactory.fromBitmap(null);
    c = paramLatLng;
    d = paramFloat1;
    e = paramFloat2;
    f = paramLatLngBounds;
    g = paramFloat3;
    h = paramFloat4;
    i = paramBoolean;
    j = paramFloat5;
    k = paramFloat6;
    l = paramFloat7;
  }
  
  private GroundOverlayOptions a(LatLng paramLatLng, float paramFloat1, float paramFloat2)
  {
    c = paramLatLng;
    d = paramFloat1;
    e = paramFloat2;
    return this;
  }
  
  public GroundOverlayOptions anchor(float paramFloat1, float paramFloat2)
  {
    k = paramFloat1;
    l = paramFloat2;
    return this;
  }
  
  public GroundOverlayOptions bearing(float paramFloat)
  {
    g = paramFloat;
    return this;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public float getAnchorU()
  {
    return k;
  }
  
  public float getAnchorV()
  {
    return l;
  }
  
  public float getBearing()
  {
    return g;
  }
  
  public LatLngBounds getBounds()
  {
    return f;
  }
  
  public float getHeight()
  {
    return e;
  }
  
  public BitmapDescriptor getImage()
  {
    return b;
  }
  
  public LatLng getLocation()
  {
    return c;
  }
  
  public float getTransparency()
  {
    return j;
  }
  
  public float getWidth()
  {
    return d;
  }
  
  public float getZIndex()
  {
    return h;
  }
  
  public GroundOverlayOptions image(BitmapDescriptor paramBitmapDescriptor)
  {
    b = paramBitmapDescriptor;
    return this;
  }
  
  public boolean isVisible()
  {
    return i;
  }
  
  public GroundOverlayOptions position(LatLng paramLatLng, float paramFloat)
  {
    boolean bool2 = true;
    if (f == null)
    {
      bool1 = true;
      cp.a(bool1, "Position has already been set using positionFromBounds");
      if (paramLatLng == null) {
        break label58;
      }
      bool1 = true;
      label24:
      cp.b(bool1, "Location must be specified");
      if (paramFloat < 0.0F) {
        break label63;
      }
    }
    label58:
    label63:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      cp.b(bool1, "Width must be non-negative");
      return a(paramLatLng, paramFloat, paramFloat);
      bool1 = false;
      break;
      bool1 = false;
      break label24;
    }
  }
  
  public GroundOverlayOptions position(LatLng paramLatLng, float paramFloat1, float paramFloat2)
  {
    boolean bool2 = true;
    if (f == null)
    {
      bool1 = true;
      cp.a(bool1, "Position has already been set using positionFromBounds");
      if (paramLatLng == null) {
        break label81;
      }
      bool1 = true;
      label27:
      cp.b(bool1, "Location must be specified");
      if (paramFloat1 < 0.0F) {
        break label87;
      }
      bool1 = true;
      label43:
      cp.b(bool1, "Width must be non-negative");
      if (paramFloat2 < 0.0F) {
        break label93;
      }
    }
    label81:
    label87:
    label93:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      cp.b(bool1, "Height must be non-negative");
      return a(paramLatLng, paramFloat1, paramFloat2);
      bool1 = false;
      break;
      bool1 = false;
      break label27;
      bool1 = false;
      break label43;
    }
  }
  
  public GroundOverlayOptions positionFromBounds(LatLngBounds paramLatLngBounds)
  {
    if (c == null) {}
    for (boolean bool = true;; bool = false)
    {
      cp.a(bool, "Position has already been set using position: " + c);
      f = paramLatLngBounds;
      return this;
    }
  }
  
  public GroundOverlayOptions transparency(float paramFloat)
  {
    if ((paramFloat >= 0.0F) && (paramFloat <= 1.0F)) {}
    for (boolean bool = true;; bool = false)
    {
      cp.b(bool, "Transparency must be in the range [0..1]");
      j = paramFloat;
      return this;
    }
  }
  
  public GroundOverlayOptions visible(boolean paramBoolean)
  {
    i = paramBoolean;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(a);
    paramParcel.writeParcelable(b, paramInt);
    paramParcel.writeParcelable(c, paramInt);
    paramParcel.writeFloat(d);
    paramParcel.writeFloat(e);
    paramParcel.writeParcelable(f, paramInt);
    paramParcel.writeFloat(g);
    paramParcel.writeFloat(h);
    if (i) {}
    for (paramInt = 1;; paramInt = 0)
    {
      paramParcel.writeByte((byte)paramInt);
      paramParcel.writeFloat(j);
      paramParcel.writeFloat(k);
      paramParcel.writeFloat(l);
      return;
    }
  }
  
  public GroundOverlayOptions zIndex(float paramFloat)
  {
    h = paramFloat;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.GroundOverlayOptions
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */