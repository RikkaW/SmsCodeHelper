package com.amap.api.maps2d.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class d
  implements Parcelable.Creator<GroundOverlayOptions>
{
  public GroundOverlayOptions a(Parcel paramParcel)
  {
    int i = paramParcel.readInt();
    BitmapDescriptor localBitmapDescriptor = (BitmapDescriptor)paramParcel.readParcelable(BitmapDescriptor.class.getClassLoader());
    LatLng localLatLng = (LatLng)paramParcel.readParcelable(LatLng.class.getClassLoader());
    float f1 = paramParcel.readFloat();
    float f2 = paramParcel.readFloat();
    LatLngBounds localLatLngBounds = (LatLngBounds)paramParcel.readParcelable(LatLngBounds.class.getClassLoader());
    float f3 = paramParcel.readFloat();
    float f4 = paramParcel.readFloat();
    if (paramParcel.readByte() != 0) {}
    for (boolean bool = true;; bool = false)
    {
      paramParcel = new GroundOverlayOptions(i, null, localLatLng, f1, f2, localLatLngBounds, f3, f4, bool, paramParcel.readFloat(), paramParcel.readFloat(), paramParcel.readFloat());
      paramParcel.image(localBitmapDescriptor);
      return paramParcel;
    }
  }
  
  public GroundOverlayOptions[] a(int paramInt)
  {
    return new GroundOverlayOptions[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.d
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */