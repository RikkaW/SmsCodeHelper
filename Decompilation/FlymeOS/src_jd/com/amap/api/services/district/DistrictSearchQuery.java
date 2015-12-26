package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.d;

public class DistrictSearchQuery
  implements Parcelable, Cloneable
{
  public static final Parcelable.Creator<DistrictSearchQuery> CREATOR = new c();
  public static final String KEYWORDS_BUSINESS = "biz_area";
  public static final String KEYWORDS_CITY = "city";
  public static final String KEYWORDS_COUNTRY = "country";
  public static final String KEYWORDS_DISTRICT = "district";
  public static final String KEYWORDS_PROVINCE = "province";
  private int a = 0;
  private int b = 20;
  private String c;
  private String d;
  private boolean e = true;
  private boolean f = false;
  
  public DistrictSearchQuery() {}
  
  public DistrictSearchQuery(String paramString1, String paramString2, int paramInt)
  {
    c = paramString1;
    d = paramString2;
    a = paramInt;
  }
  
  public DistrictSearchQuery(String paramString1, String paramString2, int paramInt1, boolean paramBoolean, int paramInt2)
  {
    this(paramString1, paramString2, paramInt1);
    e = paramBoolean;
    b = paramInt2;
  }
  
  public boolean checkKeyWords()
  {
    if (c == null) {}
    while (c.trim().equalsIgnoreCase("")) {
      return false;
    }
    return true;
  }
  
  public boolean checkLevels()
  {
    if (d == null) {}
    while ((!d.trim().equals("country")) && (!d.trim().equals("province")) && (!d.trim().equals("city")) && (!d.trim().equals("district")) && (!d.trim().equals("biz_area"))) {
      return false;
    }
    return true;
  }
  
  public DistrictSearchQuery clone()
  {
    try
    {
      super.clone();
      DistrictSearchQuery localDistrictSearchQuery = new DistrictSearchQuery(c, d, a, e, b);
      localDistrictSearchQuery.setShowBoundary(f);
      return localDistrictSearchQuery;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      for (;;)
      {
        d.a(localCloneNotSupportedException, "DistrictSearchQuery", "clone");
      }
    }
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
      paramObject = (DistrictSearchQuery)paramObject;
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
      if (d == null)
      {
        if (d != null) {
          return false;
        }
      }
      else if (!d.equals(d)) {
        return false;
      }
      if (a != a) {
        return false;
      }
      if (b != b) {
        return false;
      }
    } while (e == e);
    return false;
  }
  
  public String getKeywords()
  {
    return c;
  }
  
  public String getKeywordsLevel()
  {
    return d;
  }
  
  public int getPageNum()
  {
    return a;
  }
  
  public int getPageSize()
  {
    return b;
  }
  
  public int hashCode()
  {
    int m = 1231;
    int k = 0;
    int i;
    int j;
    label27:
    label34:
    int n;
    int i1;
    if (f)
    {
      i = 1231;
      if (c != null) {
        break label93;
      }
      j = 0;
      if (d != null) {
        break label104;
      }
      n = a;
      i1 = b;
      if (!e) {
        break label115;
      }
    }
    for (;;)
    {
      return ((((j + (i + 31) * 31) * 31 + k) * 31 + n) * 31 + i1) * 31 + m;
      i = 1237;
      break;
      label93:
      j = c.hashCode();
      break label27;
      label104:
      k = d.hashCode();
      break label34;
      label115:
      m = 1237;
    }
  }
  
  public boolean isShowBoundary()
  {
    return f;
  }
  
  public boolean isShowChild()
  {
    return e;
  }
  
  public void setKeywords(String paramString)
  {
    c = paramString;
  }
  
  public void setKeywordsLevel(String paramString)
  {
    d = paramString;
  }
  
  public void setPageNum(int paramInt)
  {
    a = paramInt;
  }
  
  public void setPageSize(int paramInt)
  {
    b = paramInt;
  }
  
  public void setShowBoundary(boolean paramBoolean)
  {
    f = paramBoolean;
  }
  
  public void setShowChild(boolean paramBoolean)
  {
    e = paramBoolean;
  }
  
  protected boolean weakEquals(DistrictSearchQuery paramDistrictSearchQuery)
  {
    if (this == paramDistrictSearchQuery) {}
    do
    {
      return true;
      if (paramDistrictSearchQuery == null) {
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
      if (b != b) {
        return false;
      }
      if (e != e) {
        return false;
      }
    } while (f == f);
    return false;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = 1;
    paramParcel.writeString(c);
    paramParcel.writeString(d);
    paramParcel.writeInt(a);
    paramParcel.writeInt(b);
    if (e)
    {
      paramInt = 1;
      paramParcel.writeByte((byte)paramInt);
      if (!f) {
        break label70;
      }
    }
    label70:
    for (paramInt = i;; paramInt = 0)
    {
      paramParcel.writeByte((byte)paramInt);
      return;
      paramInt = 0;
      break;
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.district.DistrictSearchQuery
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */