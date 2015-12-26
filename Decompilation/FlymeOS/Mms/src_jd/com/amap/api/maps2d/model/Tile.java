package com.amap.api.maps2d.model;

import android.os.Parcel;
import android.os.Parcelable;

public final class Tile
  implements Parcelable
{
  public static final j CREATOR = new j();
  private final int a;
  private final int b;
  private final int c;
  public final byte[] data;
  
  Tile(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte)
  {
    a = paramInt1;
    b = paramInt2;
    c = paramInt3;
    data = paramArrayOfByte;
  }
  
  Tile(int paramInt1, int paramInt2, byte[] paramArrayOfByte)
  {
    this(1, paramInt1, paramInt2, paramArrayOfByte);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(a);
    paramParcel.writeInt(b);
    paramParcel.writeInt(c);
    paramParcel.writeByteArray(data);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.Tile
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */