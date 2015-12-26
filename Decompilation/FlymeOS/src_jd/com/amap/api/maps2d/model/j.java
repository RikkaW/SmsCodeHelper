package com.amap.api.maps2d.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class j
  implements Parcelable.Creator<Tile>
{
  public Tile a(Parcel paramParcel)
  {
    return new Tile(paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.createByteArray());
  }
  
  public Tile[] a(int paramInt)
  {
    return new Tile[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.j
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */