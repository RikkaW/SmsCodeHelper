package com.amap.api.maps2d.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

class h
  implements Parcelable.Creator<PolygonOptions>
{
  public PolygonOptions a(Parcel paramParcel)
  {
    PolygonOptions localPolygonOptions = new PolygonOptions();
    ArrayList localArrayList = new ArrayList();
    paramParcel.readTypedList(localArrayList, LatLng.CREATOR);
    float f1 = paramParcel.readFloat();
    int i = paramParcel.readInt();
    int j = paramParcel.readInt();
    float f2 = paramParcel.readFloat();
    if (paramParcel.readByte() == 0) {}
    for (boolean bool = true;; bool = false)
    {
      localPolygonOptions.add((LatLng[])localArrayList.toArray(new LatLng[localArrayList.size()]));
      localPolygonOptions.strokeWidth(f1);
      localPolygonOptions.strokeColor(i);
      localPolygonOptions.fillColor(j);
      localPolygonOptions.zIndex(f2);
      localPolygonOptions.visible(bool);
      a = paramParcel.readString();
      return localPolygonOptions;
    }
  }
  
  public PolygonOptions[] a(int paramInt)
  {
    return new PolygonOptions[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.h
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */