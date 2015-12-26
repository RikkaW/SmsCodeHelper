package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public final class Scenic
  implements Parcelable
{
  public static final Parcelable.Creator<Scenic> CREATOR = new k();
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
  private String l;
  private List<Photo> m = new ArrayList();
  
  public Scenic() {}
  
  public Scenic(Parcel paramParcel)
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
    l = paramParcel.readString();
    m = paramParcel.createTypedArrayList(Photo.CREATOR);
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
        paramObject = (Scenic)paramObject;
        if (c == null)
        {
          if (c != null) {
            return false;
          }
        }
        else if (!c.equals(c)) {
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
        if (k == null)
        {
          if (k != null) {
            return false;
          }
        }
        else if (!k.equals(k)) {
          return false;
        }
        if (i == null)
        {
          if (i != null) {
            return false;
          }
        }
        else if (!i.equals(i)) {
          return false;
        }
        if (j == null)
        {
          if (j != null) {
            return false;
          }
        }
        else if (!j.equals(j)) {
          return false;
        }
        if (m == null)
        {
          if (m != null) {
            return false;
          }
        }
        else if (!m.equals(m)) {
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
        if (h != null) {
          break;
        }
      } while (h == null);
      return false;
    } while (h.equals(h));
    return false;
  }
  
  public String getDeepsrc()
  {
    return c;
  }
  
  public String getIntro()
  {
    return a;
  }
  
  public String getLevel()
  {
    return d;
  }
  
  public String getOpentime()
  {
    return l;
  }
  
  public String getOpentimeGDF()
  {
    return k;
  }
  
  public String getOrderWapUrl()
  {
    return i;
  }
  
  public String getOrderWebUrl()
  {
    return j;
  }
  
  public List<Photo> getPhotos()
  {
    return m;
  }
  
  public String getPrice()
  {
    return e;
  }
  
  public String getRating()
  {
    return b;
  }
  
  public String getRecommend()
  {
    return g;
  }
  
  public String getSeason()
  {
    return f;
  }
  
  public String getTheme()
  {
    return h;
  }
  
  public int hashCode()
  {
    int i12 = 0;
    int n;
    int i1;
    label21:
    int i2;
    label30:
    int i3;
    label40:
    int i4;
    label50:
    int i5;
    label60:
    int i6;
    label70:
    int i7;
    label80:
    int i8;
    label90:
    int i9;
    label100:
    int i10;
    label110:
    int i11;
    if (c == null)
    {
      n = 0;
      if (a != null) {
        break label213;
      }
      i1 = 0;
      if (d != null) {
        break label224;
      }
      i2 = 0;
      if (l != null) {
        break label235;
      }
      i3 = 0;
      if (k != null) {
        break label247;
      }
      i4 = 0;
      if (i != null) {
        break label259;
      }
      i5 = 0;
      if (j != null) {
        break label271;
      }
      i6 = 0;
      if (m != null) {
        break label283;
      }
      i7 = 0;
      if (e != null) {
        break label297;
      }
      i8 = 0;
      if (b != null) {
        break label309;
      }
      i9 = 0;
      if (g != null) {
        break label321;
      }
      i10 = 0;
      if (f != null) {
        break label333;
      }
      i11 = 0;
      label120:
      if (h != null) {
        break label345;
      }
    }
    for (;;)
    {
      return (i11 + (i10 + (i9 + (i8 + (i7 + (i6 + (i5 + (i4 + (i3 + (i2 + (i1 + (n + 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31 + i12;
      n = c.hashCode();
      break;
      label213:
      i1 = a.hashCode();
      break label21;
      label224:
      i2 = d.hashCode();
      break label30;
      label235:
      i3 = l.hashCode();
      break label40;
      label247:
      i4 = k.hashCode();
      break label50;
      label259:
      i5 = i.hashCode();
      break label60;
      label271:
      i6 = j.hashCode();
      break label70;
      label283:
      i7 = m.hashCode();
      break label80;
      label297:
      i8 = e.hashCode();
      break label90;
      label309:
      i9 = b.hashCode();
      break label100;
      label321:
      i10 = g.hashCode();
      break label110;
      label333:
      i11 = f.hashCode();
      break label120;
      label345:
      i12 = h.hashCode();
    }
  }
  
  public void setDeepsrc(String paramString)
  {
    c = paramString;
  }
  
  public void setIntro(String paramString)
  {
    a = paramString;
  }
  
  public void setLevel(String paramString)
  {
    d = paramString;
  }
  
  public void setOpentime(String paramString)
  {
    l = paramString;
  }
  
  public void setOpentimeGDF(String paramString)
  {
    k = paramString;
  }
  
  public void setOrderWapUrl(String paramString)
  {
    i = paramString;
  }
  
  public void setOrderWebUrl(String paramString)
  {
    j = paramString;
  }
  
  public void setPhotos(List<Photo> paramList)
  {
    m = paramList;
  }
  
  public void setPrice(String paramString)
  {
    e = paramString;
  }
  
  public void setRating(String paramString)
  {
    b = paramString;
  }
  
  public void setRecommend(String paramString)
  {
    g = paramString;
  }
  
  public void setSeason(String paramString)
  {
    f = paramString;
  }
  
  public void setTheme(String paramString)
  {
    h = paramString;
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
    paramParcel.writeString(l);
    paramParcel.writeTypedList(m);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.poisearch.Scenic
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */