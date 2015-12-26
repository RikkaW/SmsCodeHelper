package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public final class Cinema
  implements Parcelable
{
  public static final Parcelable.Creator<Cinema> CREATOR = new a();
  private boolean a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private String g;
  private List<Photo> h = new ArrayList();
  
  public Cinema() {}
  
  public Cinema(Parcel paramParcel)
  {
    boolean[] arrayOfBoolean = new boolean[1];
    paramParcel.readBooleanArray(arrayOfBoolean);
    a = arrayOfBoolean[0];
    b = paramParcel.readString();
    c = paramParcel.readString();
    d = paramParcel.readString();
    e = paramParcel.readString();
    f = paramParcel.readString();
    g = paramParcel.readString();
    h = paramParcel.createTypedArrayList(Photo.CREATOR);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (paramObject == null) {
        return false;
      }
      if (getClass() != paramObject.getClass()) {
        return false;
      }
      paramObject = (Cinema)paramObject;
      if (d == null)
      {
        if (d != null) {
          return false;
        }
      }
      else if (!d.equals(d)) {
        return false;
      }
      if (b == null)
      {
        if (b != null) {
          return false;
        }
      }
      else if (!b.equals(b)) {
        return false;
      }
      if (g == null)
      {
        if (g != null) {
          return false;
        }
      }
      else if (!g.equals(g)) {
        return false;
      }
      if (f == null)
      {
        if (f != null) {
          return false;
        }
      }
      else if (!f.equals(f)) {
        return false;
      }
      if (e == null)
      {
        if (e != null) {
          return false;
        }
      }
      else if (!e.equals(e)) {
        return false;
      }
      if (h == null)
      {
        if (h != null) {
          return false;
        }
      }
      else if (!h.equals(h)) {
        return false;
      }
      if (c == null)
      {
        if (c != null) {
          return false;
        }
      }
      else if (!c.equals(c)) {
        return false;
      }
    } while (a == a);
    return false;
  }
  
  public String getDeepsrc()
  {
    return d;
  }
  
  public String getIntro()
  {
    return b;
  }
  
  public String getOpentime()
  {
    return g;
  }
  
  public String getOpentimeGDF()
  {
    return f;
  }
  
  public String getParking()
  {
    return e;
  }
  
  public List<Photo> getPhotos()
  {
    return h;
  }
  
  public String getRating()
  {
    return c;
  }
  
  public int hashCode()
  {
    int i2 = 0;
    int i;
    int j;
    label21:
    int k;
    label30:
    int m;
    label40:
    int n;
    label50:
    int i1;
    if (d == null)
    {
      i = 0;
      if (b != null) {
        break label135;
      }
      j = 0;
      if (g != null) {
        break label146;
      }
      k = 0;
      if (f != null) {
        break label157;
      }
      m = 0;
      if (e != null) {
        break label169;
      }
      n = 0;
      if (h != null) {
        break label181;
      }
      i1 = 0;
      label60:
      if (c != null) {
        break label195;
      }
      label67:
      if (!a) {
        break label207;
      }
    }
    label135:
    label146:
    label157:
    label169:
    label181:
    label195:
    label207:
    for (int i3 = 1231;; i3 = 1237)
    {
      return i3 + ((i1 + (n + (m + (k + (j + (i + 31) * 31) * 31) * 31) * 31) * 31) * 31 + i2) * 31;
      i = d.hashCode();
      break;
      j = b.hashCode();
      break label21;
      k = g.hashCode();
      break label30;
      m = f.hashCode();
      break label40;
      n = e.hashCode();
      break label50;
      i1 = h.hashCode();
      break label60;
      i2 = c.hashCode();
      break label67;
    }
  }
  
  public boolean isSeatOrdering()
  {
    return a;
  }
  
  public void setDeepsrc(String paramString)
  {
    d = paramString;
  }
  
  public void setIntro(String paramString)
  {
    b = paramString;
  }
  
  public void setOpentime(String paramString)
  {
    g = paramString;
  }
  
  public void setOpentimeGDF(String paramString)
  {
    f = paramString;
  }
  
  public void setParking(String paramString)
  {
    e = paramString;
  }
  
  public void setPhotos(List<Photo> paramList)
  {
    h = paramList;
  }
  
  public void setRating(String paramString)
  {
    c = paramString;
  }
  
  public void setSeatOrdering(boolean paramBoolean)
  {
    a = paramBoolean;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeBooleanArray(new boolean[] { a });
    paramParcel.writeString(b);
    paramParcel.writeString(c);
    paramParcel.writeString(d);
    paramParcel.writeString(e);
    paramParcel.writeString(f);
    paramParcel.writeString(g);
    paramParcel.writeTypedList(h);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.poisearch.Cinema
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */