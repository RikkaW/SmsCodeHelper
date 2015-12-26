package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.AMapException;
import java.util.ArrayList;

public final class DistrictResult
  implements Parcelable
{
  public Parcelable.Creator<DistrictResult> CREATOR = new b(this);
  private DistrictSearchQuery a;
  private ArrayList<DistrictItem> b = new ArrayList();
  private int c;
  private AMapException d;
  
  public DistrictResult() {}
  
  protected DistrictResult(Parcel paramParcel)
  {
    a = ((DistrictSearchQuery)paramParcel.readParcelable(DistrictSearchQuery.class.getClassLoader()));
    b = paramParcel.createTypedArrayList(DistrictItem.CREATOR);
  }
  
  public DistrictResult(DistrictSearchQuery paramDistrictSearchQuery, ArrayList<DistrictItem> paramArrayList)
  {
    a = paramDistrictSearchQuery;
    b = paramArrayList;
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
        paramObject = (DistrictResult)paramObject;
        if (a == null)
        {
          if (a != null) {
            return false;
          }
        }
        else if (!a.equals(a)) {
          return false;
        }
        if (b != null) {
          break;
        }
      } while (b == null);
      return false;
    } while (b.equals(b));
    return false;
  }
  
  public AMapException getAMapException()
  {
    return d;
  }
  
  public ArrayList<DistrictItem> getDistrict()
  {
    return b;
  }
  
  public int getPageCount()
  {
    return c;
  }
  
  public DistrictSearchQuery getQuery()
  {
    return a;
  }
  
  public int hashCode()
  {
    int j = 0;
    int i;
    if (a == null)
    {
      i = 0;
      if (b != null) {
        break label39;
      }
    }
    for (;;)
    {
      return (i + 31) * 31 + j;
      i = a.hashCode();
      break;
      label39:
      j = b.hashCode();
    }
  }
  
  public void setAMapException(AMapException paramAMapException)
  {
    d = paramAMapException;
  }
  
  public void setDistrict(ArrayList<DistrictItem> paramArrayList)
  {
    b = paramArrayList;
  }
  
  public void setPageCount(int paramInt)
  {
    c = paramInt;
  }
  
  public void setQuery(DistrictSearchQuery paramDistrictSearchQuery)
  {
    a = paramDistrictSearchQuery;
  }
  
  public String toString()
  {
    return "DistrictResult [mDisQuery=" + a + ", mDistricts=" + b + "]";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeParcelable(a, paramInt);
    paramParcel.writeTypedList(b);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.district.DistrictResult
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */