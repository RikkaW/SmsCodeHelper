package com.amap.api.maps2d.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class k
  implements Parcelable.Creator<TileOverlayOptions>
{
  public TileOverlayOptions a(Parcel paramParcel)
  {
    boolean bool3 = true;
    int i = paramParcel.readInt();
    TileProvider localTileProvider = (TileProvider)paramParcel.readValue(TileProvider.class.getClassLoader());
    boolean bool1;
    float f;
    int j;
    int k;
    String str;
    boolean bool2;
    if (paramParcel.readByte() != 0)
    {
      bool1 = true;
      f = paramParcel.readFloat();
      j = paramParcel.readInt();
      k = paramParcel.readInt();
      str = paramParcel.readString();
      if (paramParcel.readByte() == 0) {
        break label140;
      }
      bool2 = true;
      label65:
      if (paramParcel.readByte() == 0) {
        break label146;
      }
    }
    for (;;)
    {
      paramParcel = new TileOverlayOptions(i, null, bool1, f);
      if (localTileProvider != null) {
        paramParcel.tileProvider(localTileProvider);
      }
      paramParcel.memCacheSize(j);
      paramParcel.diskCacheSize(k);
      paramParcel.diskCacheDir(str);
      paramParcel.memoryCacheEnabled(bool2);
      paramParcel.diskCacheEnabled(bool3);
      return paramParcel;
      bool1 = false;
      break;
      label140:
      bool2 = false;
      break label65;
      label146:
      bool3 = false;
    }
  }
  
  public TileOverlayOptions[] a(int paramInt)
  {
    return new TileOverlayOptions[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.k
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */