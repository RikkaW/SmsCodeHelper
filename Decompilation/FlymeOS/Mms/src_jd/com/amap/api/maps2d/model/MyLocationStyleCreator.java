package com.amap.api.maps2d.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class MyLocationStyleCreator
  implements Parcelable.Creator<MyLocationStyle>
{
  public MyLocationStyle createFromParcel(Parcel paramParcel)
  {
    MyLocationStyle localMyLocationStyle = new MyLocationStyle();
    localMyLocationStyle.myLocationIcon((BitmapDescriptor)paramParcel.readParcelable(BitmapDescriptor.class.getClassLoader()));
    localMyLocationStyle.anchor(paramParcel.readFloat(), paramParcel.readFloat());
    localMyLocationStyle.radiusFillColor(paramParcel.readInt());
    localMyLocationStyle.strokeColor(paramParcel.readInt());
    localMyLocationStyle.strokeWidth(paramParcel.readFloat());
    return localMyLocationStyle;
  }
  
  public MyLocationStyle[] newArray(int paramInt)
  {
    return new MyLocationStyle[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.MyLocationStyleCreator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */