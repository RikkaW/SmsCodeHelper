package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public final class Hotel
  implements Parcelable
{
  public static final Parcelable.Creator<Hotel> CREATOR = new e();
  private String a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private String g;
  private String h;
  private String i;
  private String j;
  private String k;
  private List<Photo> l = new ArrayList();
  
  public Hotel() {}
  
  public Hotel(Parcel paramParcel)
  {
    a = paramParcel.readString();
    b = paramParcel.readString();
    c = paramParcel.readString();
    d = paramParcel.readString();
    e = paramParcel.readString();
    f = paramParcel.readString();
    g = paramParcel.readString();
    h = paramParcel.readString();
    i = paramParcel.readString();
    j = paramParcel.readString();
    k = paramParcel.readString();
    l = paramParcel.createTypedArrayList(Photo.CREATOR);
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
      do
      {
        return true;
        if (paramObject == null) {
          return false;
        }
        if (getClass() != paramObject.getClass()) {
          return false;
        }
        paramObject = (Hotel)paramObject;
        if (j == null)
        {
          if (j != null) {
            return false;
          }
        }
        else if (!j.equals(j)) {
          return false;
        }
        if (k == null)
        {
          if (k != null) {
            return false;
          }
        }
        else if (!k.equals(k)) {
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
        if (e == null)
        {
          if (e != null) {
            return false;
          }
        }
        else if (!e.equals(e)) {
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
        if (c == null)
        {
          if (c != null) {
            return false;
          }
        }
        else if (!c.equals(c)) {
          return false;
        }
        if (d == null)
        {
          if (d != null) {
            return false;
          }
        }
        else if (!d.equals(d)) {
          return false;
        }
        if (l == null)
        {
          if (l != null) {
            return false;
          }
        }
        else if (!l.equals(l)) {
          return false;
        }
        if (a == null)
        {
          if (a != null) {
            return false;
          }
        }
        else if (!a.equals(a)) {
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
        if (b == null)
        {
          if (b != null) {
            return false;
          }
        }
        else if (!b.equals(b)) {
          return false;
        }
        if (i != null) {
          break;
        }
      } while (i == null);
      return false;
    } while (i.equals(i));
    return false;
  }
  
  public String getAddition()
  {
    return j;
  }
  
  public String getDeepsrc()
  {
    return k;
  }
  
  public String getEnvironmentRating()
  {
    return g;
  }
  
  public String getFaciRating()
  {
    return e;
  }
  
  public String getHealthRating()
  {
    return f;
  }
  
  public String getIntro()
  {
    return c;
  }
  
  public String getLowestPrice()
  {
    return d;
  }
  
  public List<Photo> getPhotos()
  {
    return l;
  }
  
  public String getRating()
  {
    return a;
  }
  
  public String getServiceRating()
  {
    return h;
  }
  
  public String getStar()
  {
    return b;
  }
  
  public String getTraffic()
  {
    return i;
  }
  
  public int hashCode()
  {
    int i10 = 0;
    int m;
    int n;
    label21:
    int i1;
    label30:
    int i2;
    label40:
    int i3;
    label50:
    int i4;
    label60:
    int i5;
    label70:
    int i6;
    label80:
    int i7;
    label90:
    int i8;
    label100:
    int i9;
    if (j == null)
    {
      m = 0;
      if (k != null) {
        break label197;
      }
      n = 0;
      if (g != null) {
        break label208;
      }
      i1 = 0;
      if (e != null) {
        break label219;
      }
      i2 = 0;
      if (f != null) {
        break label231;
      }
      i3 = 0;
      if (c != null) {
        break label243;
      }
      i4 = 0;
      if (d != null) {
        break label255;
      }
      i5 = 0;
      if (l != null) {
        break label267;
      }
      i6 = 0;
      if (a != null) {
        break label281;
      }
      i7 = 0;
      if (h != null) {
        break label293;
      }
      i8 = 0;
      if (b != null) {
        break label305;
      }
      i9 = 0;
      label110:
      if (i != null) {
        break label317;
      }
    }
    for (;;)
    {
      return (i9 + (i8 + (i7 + (i6 + (i5 + (i4 + (i3 + (i2 + (i1 + (n + (m + 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31 + i10;
      m = j.hashCode();
      break;
      label197:
      n = k.hashCode();
      break label21;
      label208:
      i1 = g.hashCode();
      break label30;
      label219:
      i2 = e.hashCode();
      break label40;
      label231:
      i3 = f.hashCode();
      break label50;
      label243:
      i4 = c.hashCode();
      break label60;
      label255:
      i5 = d.hashCode();
      break label70;
      label267:
      i6 = l.hashCode();
      break label80;
      label281:
      i7 = a.hashCode();
      break label90;
      label293:
      i8 = h.hashCode();
      break label100;
      label305:
      i9 = b.hashCode();
      break label110;
      label317:
      i10 = i.hashCode();
    }
  }
  
  public void setAddition(String paramString)
  {
    j = paramString;
  }
  
  public void setDeepsrc(String paramString)
  {
    k = paramString;
  }
  
  public void setEnvironmentRating(String paramString)
  {
    g = paramString;
  }
  
  public void setFaciRating(String paramString)
  {
    e = paramString;
  }
  
  public void setHealthRating(String paramString)
  {
    f = paramString;
  }
  
  public void setIntro(String paramString)
  {
    c = paramString;
  }
  
  public void setLowestPrice(String paramString)
  {
    d = paramString;
  }
  
  public void setPhotos(List<Photo> paramList)
  {
    l = paramList;
  }
  
  public void setRating(String paramString)
  {
    a = paramString;
  }
  
  public void setServiceRating(String paramString)
  {
    h = paramString;
  }
  
  public void setStar(String paramString)
  {
    b = paramString;
  }
  
  public void setTraffic(String paramString)
  {
    i = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(a);
    paramParcel.writeString(b);
    paramParcel.writeString(c);
    paramParcel.writeString(d);
    paramParcel.writeString(e);
    paramParcel.writeString(f);
    paramParcel.writeString(g);
    paramParcel.writeString(h);
    paramParcel.writeString(i);
    paramParcel.writeString(j);
    paramParcel.writeString(k);
    paramParcel.writeTypedList(l);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.poisearch.Hotel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */