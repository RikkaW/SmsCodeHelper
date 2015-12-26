package com.amap.api.maps2d.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;

class c
  implements Parcelable.Creator<CircleOptions>
{
  public CircleOptions a(Parcel paramParcel)
  {
    boolean bool = true;
    CircleOptions localCircleOptions = new CircleOptions();
    Bundle localBundle = paramParcel.readBundle();
    localCircleOptions.center(new LatLng(localBundle.getDouble("lat"), localBundle.getDouble("lng")));
    localCircleOptions.radius(paramParcel.readDouble());
    localCircleOptions.strokeWidth(paramParcel.readFloat());
    localCircleOptions.strokeColor(paramParcel.readInt());
    localCircleOptions.fillColor(paramParcel.readInt());
    localCircleOptions.zIndex(paramParcel.readInt());
    if (paramParcel.readByte() == 1) {}
    for (;;)
    {
      localCircleOptions.visible(bool);
      a = paramParcel.readString();
      return localCircleOptions;
      bool = false;
    }
  }
  
  public CircleOptions[] a(int paramInt)
  {
    return new CircleOptions[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */