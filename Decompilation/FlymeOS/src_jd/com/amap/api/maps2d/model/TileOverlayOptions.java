package com.amap.api.maps2d.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

public final class TileOverlayOptions
  implements Parcelable
{
  public static final k CREATOR = new k();
  private final int a;
  private TileProvider b;
  private boolean c = true;
  private float d;
  private int e = 5120;
  private int f = 20480;
  private String g = null;
  private boolean h = true;
  private boolean i = true;
  
  public TileOverlayOptions()
  {
    a = 1;
  }
  
  TileOverlayOptions(int paramInt, IBinder paramIBinder, boolean paramBoolean, float paramFloat)
  {
    a = paramInt;
    c = paramBoolean;
    d = paramFloat;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public TileOverlayOptions diskCacheDir(String paramString)
  {
    g = paramString;
    return this;
  }
  
  public TileOverlayOptions diskCacheEnabled(boolean paramBoolean)
  {
    i = paramBoolean;
    return this;
  }
  
  public TileOverlayOptions diskCacheSize(int paramInt)
  {
    f = (paramInt * 1024);
    return this;
  }
  
  public String getDiskCacheDir()
  {
    return g;
  }
  
  public boolean getDiskCacheEnabled()
  {
    return i;
  }
  
  public int getDiskCacheSize()
  {
    return f;
  }
  
  public int getMemCacheSize()
  {
    return e;
  }
  
  public boolean getMemoryCacheEnabled()
  {
    return h;
  }
  
  public TileProvider getTileProvider()
  {
    return b;
  }
  
  public float getZIndex()
  {
    return d;
  }
  
  public boolean isVisible()
  {
    return c;
  }
  
  public TileOverlayOptions memCacheSize(int paramInt)
  {
    e = paramInt;
    return this;
  }
  
  public TileOverlayOptions memoryCacheEnabled(boolean paramBoolean)
  {
    h = paramBoolean;
    return this;
  }
  
  public TileOverlayOptions tileProvider(TileProvider paramTileProvider)
  {
    b = paramTileProvider;
    return this;
  }
  
  public TileOverlayOptions visible(boolean paramBoolean)
  {
    c = paramBoolean;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int j = 1;
    paramParcel.writeInt(a);
    paramParcel.writeValue(b);
    if (c)
    {
      paramInt = 1;
      paramParcel.writeByte((byte)paramInt);
      paramParcel.writeFloat(d);
      paramParcel.writeInt(e);
      paramParcel.writeInt(f);
      paramParcel.writeString(g);
      if (!h) {
        break label101;
      }
      paramInt = 1;
      label74:
      paramParcel.writeByte((byte)paramInt);
      if (!i) {
        break label106;
      }
    }
    label101:
    label106:
    for (paramInt = j;; paramInt = 0)
    {
      paramParcel.writeByte((byte)paramInt);
      return;
      paramInt = 0;
      break;
      paramInt = 0;
      break label74;
    }
  }
  
  public TileOverlayOptions zIndex(float paramFloat)
  {
    d = paramFloat;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.TileOverlayOptions
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */