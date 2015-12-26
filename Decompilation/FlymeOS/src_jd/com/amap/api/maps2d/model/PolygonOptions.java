package com.amap.api.maps2d.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class PolygonOptions
  implements Parcelable
{
  public static final h CREATOR = new h();
  String a;
  private final List<LatLng> b = new ArrayList();
  private float c = 10.0F;
  private int d = -16777216;
  private int e = -16777216;
  private float f = 0.0F;
  private boolean g = true;
  
  public PolygonOptions add(LatLng paramLatLng)
  {
    b.add(paramLatLng);
    return this;
  }
  
  public PolygonOptions add(LatLng... paramVarArgs)
  {
    b.addAll(Arrays.asList(paramVarArgs));
    return this;
  }
  
  public PolygonOptions addAll(Iterable<LatLng> paramIterable)
  {
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      LatLng localLatLng = (LatLng)paramIterable.next();
      b.add(localLatLng);
    }
    return this;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public PolygonOptions fillColor(int paramInt)
  {
    e = paramInt;
    return this;
  }
  
  public int getFillColor()
  {
    return e;
  }
  
  public List<LatLng> getPoints()
  {
    return b;
  }
  
  public int getStrokeColor()
  {
    return d;
  }
  
  public float getStrokeWidth()
  {
    return c;
  }
  
  public float getZIndex()
  {
    return f;
  }
  
  public boolean isVisible()
  {
    return g;
  }
  
  public PolygonOptions strokeColor(int paramInt)
  {
    d = paramInt;
    return this;
  }
  
  public PolygonOptions strokeWidth(float paramFloat)
  {
    c = paramFloat;
    return this;
  }
  
  public PolygonOptions visible(boolean paramBoolean)
  {
    g = paramBoolean;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeTypedList(b);
    paramParcel.writeFloat(c);
    paramParcel.writeInt(d);
    paramParcel.writeInt(e);
    paramParcel.writeFloat(f);
    if (g) {}
    for (paramInt = 0;; paramInt = 1)
    {
      paramParcel.writeByte((byte)paramInt);
      paramParcel.writeString(a);
      return;
    }
  }
  
  public PolygonOptions zIndex(float paramFloat)
  {
    f = paramFloat;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.PolygonOptions
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */