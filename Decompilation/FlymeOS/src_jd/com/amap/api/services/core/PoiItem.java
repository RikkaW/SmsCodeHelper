package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PoiItem
  implements Parcelable
{
  public static final Parcelable.Creator<PoiItem> CREATOR = new q();
  private String a;
  private String b;
  private String c;
  private String d;
  private String e = "";
  private int f = -1;
  private LatLonPoint g;
  private LatLonPoint h;
  private String i;
  private String j;
  private String k;
  private boolean l;
  private boolean m;
  protected final LatLonPoint mPoint;
  protected final String mSnippet;
  protected final String mTitle;
  private String n;
  private String o;
  private String p;
  private String q;
  private boolean r;
  private String s;
  
  public PoiItem(Parcel paramParcel)
  {
    a = paramParcel.readString();
    c = paramParcel.readString();
    b = paramParcel.readString();
    e = paramParcel.readString();
    f = paramParcel.readInt();
    mPoint = ((LatLonPoint)paramParcel.readValue(LatLonPoint.class.getClassLoader()));
    mTitle = paramParcel.readString();
    mSnippet = paramParcel.readString();
    d = paramParcel.readString();
    g = ((LatLonPoint)paramParcel.readValue(LatLonPoint.class.getClassLoader()));
    h = ((LatLonPoint)paramParcel.readValue(LatLonPoint.class.getClassLoader()));
    i = paramParcel.readString();
    j = paramParcel.readString();
    k = paramParcel.readString();
    boolean[] arrayOfBoolean = new boolean[3];
    paramParcel.readBooleanArray(arrayOfBoolean);
    l = arrayOfBoolean[0];
    m = arrayOfBoolean[1];
    r = arrayOfBoolean[2];
    n = paramParcel.readString();
    o = paramParcel.readString();
    p = paramParcel.readString();
    q = paramParcel.readString();
    s = paramParcel.readString();
  }
  
  public PoiItem(String paramString1, LatLonPoint paramLatLonPoint, String paramString2, String paramString3)
  {
    a = paramString1;
    mPoint = paramLatLonPoint;
    mTitle = paramString2;
    mSnippet = paramString3;
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
        paramObject = (PoiItem)paramObject;
        if (a != null) {
          break;
        }
      } while (a == null);
      return false;
    } while (a.equals(a));
    return false;
  }
  
  public String getAdCode()
  {
    return c;
  }
  
  public String getAdName()
  {
    return q;
  }
  
  public String getCityCode()
  {
    return d;
  }
  
  public String getCityName()
  {
    return p;
  }
  
  public String getDirection()
  {
    return n;
  }
  
  public int getDistance()
  {
    return f;
  }
  
  public String getEmail()
  {
    return k;
  }
  
  public LatLonPoint getEnter()
  {
    return g;
  }
  
  public LatLonPoint getExit()
  {
    return h;
  }
  
  public LatLonPoint getLatLonPoint()
  {
    return mPoint;
  }
  
  public String getPoiId()
  {
    return a;
  }
  
  public String getPostcode()
  {
    return j;
  }
  
  public String getProvinceCode()
  {
    return s;
  }
  
  public String getProvinceName()
  {
    return o;
  }
  
  public String getSnippet()
  {
    return mSnippet;
  }
  
  public String getTel()
  {
    return b;
  }
  
  public String getTitle()
  {
    return mTitle;
  }
  
  public String getTypeDes()
  {
    return e;
  }
  
  public String getWebsite()
  {
    return i;
  }
  
  public int hashCode()
  {
    if (a == null) {}
    for (int i1 = 0;; i1 = a.hashCode()) {
      return i1 + 31;
    }
  }
  
  public boolean isDiscountInfo()
  {
    return m;
  }
  
  public boolean isGroupbuyInfo()
  {
    return l;
  }
  
  public boolean isIndoorMap()
  {
    return r;
  }
  
  public void setAdCode(String paramString)
  {
    c = paramString;
  }
  
  public void setAdName(String paramString)
  {
    q = paramString;
  }
  
  public void setCityCode(String paramString)
  {
    d = paramString;
  }
  
  public void setCityName(String paramString)
  {
    p = paramString;
  }
  
  public void setDirection(String paramString)
  {
    n = paramString;
  }
  
  public void setDiscountInfo(boolean paramBoolean)
  {
    m = paramBoolean;
  }
  
  public void setDistance(int paramInt)
  {
    f = paramInt;
  }
  
  public void setEmail(String paramString)
  {
    k = paramString;
  }
  
  public void setEnter(LatLonPoint paramLatLonPoint)
  {
    g = paramLatLonPoint;
  }
  
  public void setExit(LatLonPoint paramLatLonPoint)
  {
    h = paramLatLonPoint;
  }
  
  public void setGroupbuyInfo(boolean paramBoolean)
  {
    l = paramBoolean;
  }
  
  public void setIndoorMap(boolean paramBoolean)
  {
    r = paramBoolean;
  }
  
  public void setPostcode(String paramString)
  {
    j = paramString;
  }
  
  public void setProvinceCode(String paramString)
  {
    s = paramString;
  }
  
  public void setProvinceName(String paramString)
  {
    o = paramString;
  }
  
  public void setTel(String paramString)
  {
    b = paramString;
  }
  
  public void setTypeDes(String paramString)
  {
    e = paramString;
  }
  
  public void setWebsite(String paramString)
  {
    i = paramString;
  }
  
  public String toString()
  {
    return mTitle;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(a);
    paramParcel.writeString(c);
    paramParcel.writeString(b);
    paramParcel.writeString(e);
    paramParcel.writeInt(f);
    paramParcel.writeValue(mPoint);
    paramParcel.writeString(mTitle);
    paramParcel.writeString(mSnippet);
    paramParcel.writeString(d);
    paramParcel.writeValue(g);
    paramParcel.writeValue(h);
    paramParcel.writeString(i);
    paramParcel.writeString(j);
    paramParcel.writeString(k);
    paramParcel.writeBooleanArray(new boolean[] { l, m, r });
    paramParcel.writeString(n);
    paramParcel.writeString(o);
    paramParcel.writeString(p);
    paramParcel.writeString(q);
    paramParcel.writeString(s);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.PoiItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */