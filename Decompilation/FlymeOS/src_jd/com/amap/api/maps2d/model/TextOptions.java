package com.amap.api.maps2d.model;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class TextOptions
  implements Parcelable
{
  public static final TextOptionsCreator CREATOR = new TextOptionsCreator();
  private String a;
  private Typeface b = Typeface.DEFAULT;
  private boolean c = true;
  private float d;
  private LatLng e;
  private float f = 0.0F;
  private int g = 0;
  private Object h;
  private int i = -16777216;
  private int j = 20;
  private int k = 3;
  private int l = 6;
  
  public TextOptions align(int paramInt1, int paramInt2)
  {
    k = paramInt1;
    l = paramInt2;
    return this;
  }
  
  public TextOptions backgroundColor(int paramInt)
  {
    g = paramInt;
    return this;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public TextOptions fontColor(int paramInt)
  {
    i = paramInt;
    return this;
  }
  
  public TextOptions fontSize(int paramInt)
  {
    j = paramInt;
    return this;
  }
  
  public int getAlignX()
  {
    return k;
  }
  
  public int getAlignY()
  {
    return l;
  }
  
  public int getBackgroundColor()
  {
    return g;
  }
  
  public int getFontColor()
  {
    return i;
  }
  
  public int getFontSize()
  {
    return j;
  }
  
  public Object getObject()
  {
    return h;
  }
  
  public LatLng getPosition()
  {
    return e;
  }
  
  public float getRotate()
  {
    return f;
  }
  
  public String getText()
  {
    return a;
  }
  
  public Typeface getTypeface()
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
  
  public TextOptions position(LatLng paramLatLng)
  {
    e = paramLatLng;
    return this;
  }
  
  public TextOptions rotate(float paramFloat)
  {
    f = paramFloat;
    return this;
  }
  
  public TextOptions setObject(Object paramObject)
  {
    h = paramObject;
    return this;
  }
  
  public TextOptions text(String paramString)
  {
    a = paramString;
    return this;
  }
  
  public TextOptions typeface(Typeface paramTypeface)
  {
    b = paramTypeface;
    return this;
  }
  
  public TextOptions visible(boolean paramBoolean)
  {
    c = paramBoolean;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    Bundle localBundle = new Bundle();
    if (e != null)
    {
      localBundle.putDouble("lat", e.latitude);
      localBundle.putDouble("lng", e.longitude);
    }
    paramParcel.writeBundle(localBundle);
    paramParcel.writeString(a);
    paramParcel.writeInt(b.getStyle());
    paramParcel.writeFloat(f);
    paramParcel.writeInt(k);
    paramParcel.writeInt(l);
    paramParcel.writeInt(g);
    paramParcel.writeInt(i);
    paramParcel.writeInt(j);
    paramParcel.writeFloat(d);
    if (c) {}
    for (paramInt = 1;; paramInt = 0)
    {
      paramParcel.writeByte((byte)paramInt);
      if ((h instanceof Parcelable))
      {
        localBundle = new Bundle();
        localBundle.putParcelable("obj", (Parcelable)h);
        paramParcel.writeBundle(localBundle);
      }
      return;
    }
  }
  
  public TextOptions zIndex(float paramFloat)
  {
    d = paramFloat;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.TextOptions
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */