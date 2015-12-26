package com.amap.api.maps2d.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

class i
  implements Parcelable.Creator<PolylineOptions>
{
  public PolylineOptions a(Parcel paramParcel)
  {
    boolean bool2 = true;
    PolylineOptions localPolylineOptions = new PolylineOptions();
    ArrayList localArrayList = new ArrayList();
    paramParcel.readTypedList(localArrayList, LatLng.CREATOR);
    float f1 = paramParcel.readFloat();
    int i = paramParcel.readInt();
    float f2 = paramParcel.readFloat();
    boolean bool1;
    if (paramParcel.readByte() == 1)
    {
      bool1 = true;
      localPolylineOptions.addAll(localArrayList);
      localPolylineOptions.width(f1);
      localPolylineOptions.color(i);
      localPolylineOptions.zIndex(f2);
      localPolylineOptions.visible(bool1);
      a = paramParcel.readString();
      if (paramParcel.readByte() != 1) {
        break label148;
      }
      bool1 = true;
      label115:
      if (paramParcel.readByte() != 1) {
        break label154;
      }
    }
    for (;;)
    {
      localPolylineOptions.geodesic(bool1);
      localPolylineOptions.setDottedLine(bool2);
      return localPolylineOptions;
      bool1 = false;
      break;
      label148:
      bool1 = false;
      break label115;
      label154:
      bool2 = false;
    }
  }
  
  public PolylineOptions[] a(int paramInt)
  {
    return new PolylineOptions[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.i
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */