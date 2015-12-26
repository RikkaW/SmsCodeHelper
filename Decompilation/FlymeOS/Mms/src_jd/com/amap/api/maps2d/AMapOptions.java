package com.amap.api.maps2d;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.maps2d.model.CameraPosition;

public class AMapOptions
  implements Parcelable
{
  public static final a CREATOR = new a();
  public static final int LOGO_POSITION_BOTTOM_CENTER = 1;
  public static final int LOGO_POSITION_BOTTOM_LEFT = 0;
  public static final int LOGO_POSITION_BOTTOM_RIGHT = 2;
  public static final int ZOOM_POSITION_RIGHT_BUTTOM = 0;
  public static final int ZOOM_POSITION_RIGHT_CENTER = 1;
  private int a = 1;
  private boolean b = true;
  private boolean c = true;
  private boolean d = true;
  private boolean e = false;
  private CameraPosition f;
  private boolean g = false;
  private boolean h = false;
  private int i = 0;
  
  public AMapOptions camera(CameraPosition paramCameraPosition)
  {
    f = paramCameraPosition;
    return this;
  }
  
  public AMapOptions compassEnabled(boolean paramBoolean)
  {
    g = paramBoolean;
    return this;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public CameraPosition getCamera()
  {
    return f;
  }
  
  public Boolean getCompassEnabled()
  {
    return Boolean.valueOf(g);
  }
  
  public int getLogoPosition()
  {
    return i;
  }
  
  public int getMapType()
  {
    return a;
  }
  
  public Boolean getScaleControlsEnabled()
  {
    return Boolean.valueOf(h);
  }
  
  public Boolean getScrollGesturesEnabled()
  {
    return Boolean.valueOf(b);
  }
  
  public Boolean getZOrderOnTop()
  {
    return Boolean.valueOf(e);
  }
  
  public Boolean getZoomControlsEnabled()
  {
    return Boolean.valueOf(d);
  }
  
  public Boolean getZoomGesturesEnabled()
  {
    return Boolean.valueOf(c);
  }
  
  public AMapOptions logoPosition(int paramInt)
  {
    i = paramInt;
    return this;
  }
  
  public AMapOptions mapType(int paramInt)
  {
    a = paramInt;
    return this;
  }
  
  public AMapOptions scaleControlsEnabled(boolean paramBoolean)
  {
    h = paramBoolean;
    return this;
  }
  
  public AMapOptions scrollGesturesEnabled(boolean paramBoolean)
  {
    b = paramBoolean;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeParcelable(f, paramInt);
    paramParcel.writeInt(a);
    paramParcel.writeBooleanArray(new boolean[] { b, c, d, e, g, h });
  }
  
  public AMapOptions zOrderOnTop(boolean paramBoolean)
  {
    e = paramBoolean;
    return this;
  }
  
  public AMapOptions zoomControlsEnabled(boolean paramBoolean)
  {
    d = paramBoolean;
    return this;
  }
  
  public AMapOptions zoomGesturesEnabled(boolean paramBoolean)
  {
    c = paramBoolean;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.AMapOptions
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */