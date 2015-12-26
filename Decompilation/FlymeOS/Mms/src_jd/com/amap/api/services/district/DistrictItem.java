package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class DistrictItem
  implements Parcelable
{
  public static final Parcelable.Creator<DistrictItem> CREATOR = new a();
  private String a;
  private String b;
  private String c;
  private LatLonPoint d;
  private String e;
  private List<DistrictItem> f = new ArrayList();
  private String[] g = new String[0];
  
  public DistrictItem() {}
  
  public DistrictItem(Parcel paramParcel)
  {
    a = paramParcel.readString();
    b = paramParcel.readString();
    c = paramParcel.readString();
    d = ((LatLonPoint)paramParcel.readParcelable(LatLonPoint.class.getClassLoader()));
    e = paramParcel.readString();
    f = paramParcel.createTypedArrayList(CREATOR);
    g = new String[paramParcel.readInt()];
    paramParcel.readStringArray(g);
  }
  
  public DistrictItem(String paramString1, String paramString2, String paramString3, LatLonPoint paramLatLonPoint, String paramString4)
  {
    c = paramString1;
    a = paramString2;
    b = paramString3;
    d = paramLatLonPoint;
    e = paramString4;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String[] districtBoundary()
  {
    return g;
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
        paramObject = (DistrictItem)paramObject;
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
        if (a == null)
        {
          if (a != null) {
            return false;
          }
        }
        else if (!a.equals(a)) {
          return false;
        }
        if (!Arrays.equals(g, g)) {
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
        if (c != null) {
          break;
        }
      } while (c == null);
      return false;
    } while (c.equals(c));
    return false;
  }
  
  public String getAdcode()
  {
    return b;
  }
  
  public LatLonPoint getCenter()
  {
    return d;
  }
  
  public String getCitycode()
  {
    return a;
  }
  
  public String getLevel()
  {
    return e;
  }
  
  public String getName()
  {
    return c;
  }
  
  public List<DistrictItem> getSubDistrict()
  {
    return f;
  }
  
  public int hashCode()
  {
    int i1 = 0;
    int i;
    int j;
    label21:
    int k;
    label30:
    int i2;
    int m;
    label49:
    int n;
    if (b == null)
    {
      i = 0;
      if (d != null) {
        break label116;
      }
      j = 0;
      if (a != null) {
        break label127;
      }
      k = 0;
      i2 = Arrays.hashCode(g);
      if (f != null) {
        break label138;
      }
      m = 0;
      if (e != null) {
        break label152;
      }
      n = 0;
      label59:
      if (c != null) {
        break label164;
      }
    }
    for (;;)
    {
      return (n + (m + ((k + (j + (i + 31) * 31) * 31) * 31 + i2) * 31) * 31) * 31 + i1;
      i = b.hashCode();
      break;
      label116:
      j = d.hashCode();
      break label21;
      label127:
      k = a.hashCode();
      break label30;
      label138:
      m = f.hashCode();
      break label49;
      label152:
      n = e.hashCode();
      break label59;
      label164:
      i1 = c.hashCode();
    }
  }
  
  public void setAdcode(String paramString)
  {
    b = paramString;
  }
  
  public void setCenter(LatLonPoint paramLatLonPoint)
  {
    d = paramLatLonPoint;
  }
  
  public void setCitycode(String paramString)
  {
    a = paramString;
  }
  
  public void setDistrictBoundary(String[] paramArrayOfString)
  {
    g = paramArrayOfString;
  }
  
  public void setLevel(String paramString)
  {
    e = paramString;
  }
  
  public void setName(String paramString)
  {
    c = paramString;
  }
  
  public void setSubDistrict(ArrayList<DistrictItem> paramArrayList)
  {
    f = paramArrayList;
  }
  
  public String toString()
  {
    return "DistrictItem [mCitycode=" + a + ", mAdcode=" + b + ", mName=" + c + ", mCenter=" + d + ", mLevel=" + e + ", mDistricts=" + f + "]";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(a);
    paramParcel.writeString(b);
    paramParcel.writeString(c);
    paramParcel.writeParcelable(d, paramInt);
    paramParcel.writeString(e);
    paramParcel.writeTypedList(f);
    paramParcel.writeInt(g.length);
    paramParcel.writeStringArray(g);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.district.DistrictItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */