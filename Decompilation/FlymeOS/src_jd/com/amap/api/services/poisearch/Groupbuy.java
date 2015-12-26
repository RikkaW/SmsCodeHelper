package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public final class Groupbuy
  implements Parcelable
{
  public static final Parcelable.Creator<Groupbuy> CREATOR = new d();
  private String a;
  private String b;
  private String c;
  private Date d;
  private Date e;
  private int f;
  private int g;
  private float h;
  private float i;
  private float j;
  private String k;
  private String l;
  private List<Photo> m = new ArrayList();
  private String n;
  private String o;
  
  public Groupbuy() {}
  
  public Groupbuy(Parcel paramParcel)
  {
    a = paramParcel.readString();
    b = paramParcel.readString();
    c = paramParcel.readString();
    d = com.amap.api.services.core.d.e(paramParcel.readString());
    e = com.amap.api.services.core.d.e(paramParcel.readString());
    f = paramParcel.readInt();
    g = paramParcel.readInt();
    h = paramParcel.readFloat();
    i = paramParcel.readFloat();
    j = paramParcel.readFloat();
    k = paramParcel.readString();
    l = paramParcel.readString();
    m = paramParcel.createTypedArrayList(Photo.CREATOR);
    n = paramParcel.readString();
    o = paramParcel.readString();
  }
  
  public void addPhotos(Photo paramPhoto)
  {
    m.add(paramPhoto);
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
        paramObject = (Groupbuy)paramObject;
        if (f != f) {
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
        if (Float.floatToIntBits(j) != Float.floatToIntBits(j)) {
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
        if (Float.floatToIntBits(i) != Float.floatToIntBits(i)) {
          return false;
        }
        if (Float.floatToIntBits(h) != Float.floatToIntBits(h)) {
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
        if (o == null)
        {
          if (o != null) {
            return false;
          }
        }
        else if (!o.equals(o)) {
          return false;
        }
        if (g != g) {
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
        if (k == null)
        {
          if (k != null) {
            return false;
          }
        }
        else if (!k.equals(k)) {
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
        if (b == null)
        {
          if (b != null) {
            return false;
          }
        }
        else if (!b.equals(b)) {
          return false;
        }
        if (n != null) {
          break;
        }
      } while (n == null);
      return false;
    } while (n.equals(n));
    return false;
  }
  
  public int getCount()
  {
    return f;
  }
  
  public String getDetail()
  {
    return c;
  }
  
  public float getDiscount()
  {
    return j;
  }
  
  public Date getEndTime()
  {
    if (e == null) {
      return null;
    }
    return (Date)e.clone();
  }
  
  public float getGroupbuyPrice()
  {
    return i;
  }
  
  public float getOriginalPrice()
  {
    return h;
  }
  
  public List<Photo> getPhotos()
  {
    return m;
  }
  
  public String getProvider()
  {
    return o;
  }
  
  public int getSoldCount()
  {
    return g;
  }
  
  public Date getStartTime()
  {
    if (d == null) {
      return null;
    }
    return (Date)d.clone();
  }
  
  public String getTicketAddress()
  {
    return k;
  }
  
  public String getTicketTel()
  {
    return l;
  }
  
  public String getTypeCode()
  {
    return a;
  }
  
  public String getTypeDes()
  {
    return b;
  }
  
  public String getUrl()
  {
    return n;
  }
  
  public int hashCode()
  {
    int i10 = 0;
    int i11 = f;
    int i1;
    int i12;
    int i2;
    label36:
    int i13;
    int i14;
    int i3;
    label63:
    int i4;
    label73:
    int i15;
    int i5;
    label89:
    int i6;
    label99:
    int i7;
    label109:
    int i8;
    label119:
    int i9;
    if (c == null)
    {
      i1 = 0;
      i12 = Float.floatToIntBits(j);
      if (e != null) {
        break label234;
      }
      i2 = 0;
      i13 = Float.floatToIntBits(i);
      i14 = Float.floatToIntBits(h);
      if (m != null) {
        break label245;
      }
      i3 = 0;
      if (o != null) {
        break label258;
      }
      i4 = 0;
      i15 = g;
      if (d != null) {
        break label270;
      }
      i5 = 0;
      if (k != null) {
        break label282;
      }
      i6 = 0;
      if (l != null) {
        break label294;
      }
      i7 = 0;
      if (a != null) {
        break label306;
      }
      i8 = 0;
      if (b != null) {
        break label318;
      }
      i9 = 0;
      label129:
      if (n != null) {
        break label330;
      }
    }
    for (;;)
    {
      return (i9 + (i8 + (i7 + (i6 + (i5 + ((i4 + (i3 + (((i2 + ((i1 + (i11 + 31) * 31) * 31 + i12) * 31) * 31 + i13) * 31 + i14) * 31) * 31) * 31 + i15) * 31) * 31) * 31) * 31) * 31) * 31 + i10;
      i1 = c.hashCode();
      break;
      label234:
      i2 = e.hashCode();
      break label36;
      label245:
      i3 = m.hashCode();
      break label63;
      label258:
      i4 = o.hashCode();
      break label73;
      label270:
      i5 = d.hashCode();
      break label89;
      label282:
      i6 = k.hashCode();
      break label99;
      label294:
      i7 = l.hashCode();
      break label109;
      label306:
      i8 = a.hashCode();
      break label119;
      label318:
      i9 = b.hashCode();
      break label129;
      label330:
      i10 = n.hashCode();
    }
  }
  
  public void initPhotos(List<Photo> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0)) {}
    for (;;)
    {
      return;
      m.clear();
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Photo localPhoto = (Photo)paramList.next();
        m.add(localPhoto);
      }
    }
  }
  
  public void setCount(int paramInt)
  {
    f = paramInt;
  }
  
  public void setDetail(String paramString)
  {
    c = paramString;
  }
  
  public void setDiscount(float paramFloat)
  {
    j = paramFloat;
  }
  
  public void setEndTime(Date paramDate)
  {
    if (paramDate == null)
    {
      e = null;
      return;
    }
    e = ((Date)paramDate.clone());
  }
  
  public void setGroupbuyPrice(float paramFloat)
  {
    i = paramFloat;
  }
  
  public void setOriginalPrice(float paramFloat)
  {
    h = paramFloat;
  }
  
  public void setProvider(String paramString)
  {
    o = paramString;
  }
  
  public void setSoldCount(int paramInt)
  {
    g = paramInt;
  }
  
  public void setStartTime(Date paramDate)
  {
    if (paramDate == null)
    {
      d = null;
      return;
    }
    d = ((Date)paramDate.clone());
  }
  
  public void setTicketAddress(String paramString)
  {
    k = paramString;
  }
  
  public void setTicketTel(String paramString)
  {
    l = paramString;
  }
  
  public void setTypeCode(String paramString)
  {
    a = paramString;
  }
  
  public void setTypeDes(String paramString)
  {
    b = paramString;
  }
  
  public void setUrl(String paramString)
  {
    n = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(a);
    paramParcel.writeString(b);
    paramParcel.writeString(c);
    paramParcel.writeString(com.amap.api.services.core.d.a(d));
    paramParcel.writeString(com.amap.api.services.core.d.a(e));
    paramParcel.writeInt(f);
    paramParcel.writeInt(g);
    paramParcel.writeFloat(h);
    paramParcel.writeFloat(i);
    paramParcel.writeFloat(j);
    paramParcel.writeString(k);
    paramParcel.writeString(l);
    paramParcel.writeTypedList(m);
    paramParcel.writeString(n);
    paramParcel.writeString(o);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.poisearch.Groupbuy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */