package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.d;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public final class Discount
  implements Parcelable
{
  public static final Parcelable.Creator<Discount> CREATOR = new c();
  private String a;
  private String b;
  private Date c;
  private Date d;
  private int e;
  private List<Photo> f = new ArrayList();
  private String g;
  private String h;
  
  public Discount() {}
  
  public Discount(Parcel paramParcel)
  {
    a = paramParcel.readString();
    b = paramParcel.readString();
    c = d.e(paramParcel.readString());
    d = d.e(paramParcel.readString());
    e = paramParcel.readInt();
    f = paramParcel.createTypedArrayList(Photo.CREATOR);
    g = paramParcel.readString();
    h = paramParcel.readString();
  }
  
  public void addPhotos(Photo paramPhoto)
  {
    f.add(paramPhoto);
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
        paramObject = (Discount)paramObject;
        if (b == null)
        {
          if (b != null) {
            return false;
          }
        }
        else if (!b.equals(b)) {
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
        if (f == null)
        {
          if (f != null) {
            return false;
          }
        }
        else if (!f.equals(f)) {
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
        if (e != e) {
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
        if (a == null)
        {
          if (a != null) {
            return false;
          }
        }
        else if (!a.equals(a)) {
          return false;
        }
        if (g != null) {
          break;
        }
      } while (g == null);
      return false;
    } while (g.equals(g));
    return false;
  }
  
  public String getDetail()
  {
    return b;
  }
  
  public Date getEndTime()
  {
    if (d == null) {
      return null;
    }
    return (Date)d.clone();
  }
  
  public List<Photo> getPhotos()
  {
    return f;
  }
  
  public String getProvider()
  {
    return h;
  }
  
  public int getSoldCount()
  {
    return e;
  }
  
  public Date getStartTime()
  {
    if (c == null) {
      return null;
    }
    return (Date)c.clone();
  }
  
  public String getTitle()
  {
    return a;
  }
  
  public String getUrl()
  {
    return g;
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
    int i3;
    int n;
    label56:
    int i1;
    if (b == null)
    {
      i = 0;
      if (d != null) {
        break label129;
      }
      j = 0;
      if (f != null) {
        break label140;
      }
      k = 0;
      if (h != null) {
        break label153;
      }
      m = 0;
      i3 = e;
      if (c != null) {
        break label165;
      }
      n = 0;
      if (a != null) {
        break label177;
      }
      i1 = 0;
      label66:
      if (g != null) {
        break label189;
      }
    }
    for (;;)
    {
      return (i1 + (n + ((m + (k + (j + (i + 31) * 31) * 31) * 31) * 31 + i3) * 31) * 31) * 31 + i2;
      i = b.hashCode();
      break;
      label129:
      j = d.hashCode();
      break label21;
      label140:
      k = f.hashCode();
      break label30;
      label153:
      m = h.hashCode();
      break label40;
      label165:
      n = c.hashCode();
      break label56;
      label177:
      i1 = a.hashCode();
      break label66;
      label189:
      i2 = g.hashCode();
    }
  }
  
  public void initPhotos(List<Photo> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0)) {}
    for (;;)
    {
      return;
      f.clear();
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Photo localPhoto = (Photo)paramList.next();
        f.add(localPhoto);
      }
    }
  }
  
  public void setDetail(String paramString)
  {
    b = paramString;
  }
  
  public void setEndTime(Date paramDate)
  {
    if (paramDate == null)
    {
      d = null;
      return;
    }
    d = ((Date)paramDate.clone());
  }
  
  public void setProvider(String paramString)
  {
    h = paramString;
  }
  
  public void setSoldCount(int paramInt)
  {
    e = paramInt;
  }
  
  public void setStartTime(Date paramDate)
  {
    if (paramDate == null)
    {
      c = null;
      return;
    }
    c = ((Date)paramDate.clone());
  }
  
  public void setTitle(String paramString)
  {
    a = paramString;
  }
  
  public void setUrl(String paramString)
  {
    g = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(a);
    paramParcel.writeString(b);
    paramParcel.writeString(d.a(c));
    paramParcel.writeString(d.a(d));
    paramParcel.writeInt(e);
    paramParcel.writeTypedList(f);
    paramParcel.writeString(g);
    paramParcel.writeString(h);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.poisearch.Discount
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */