package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.road.Crossroad;
import com.amap.api.services.road.Road;
import java.util.ArrayList;
import java.util.List;

public final class RegeocodeAddress
  implements Parcelable
{
  public static final Parcelable.Creator<RegeocodeAddress> CREATOR = new c();
  private String a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private String g;
  private StreetNumber h;
  private String i;
  private String j;
  private List<RegeocodeRoad> k = new ArrayList();
  private List<Crossroad> l = new ArrayList();
  private List<PoiItem> m = new ArrayList();
  private List<BusinessArea> n = new ArrayList();
  
  public RegeocodeAddress() {}
  
  private RegeocodeAddress(Parcel paramParcel)
  {
    a = paramParcel.readString();
    b = paramParcel.readString();
    c = paramParcel.readString();
    d = paramParcel.readString();
    e = paramParcel.readString();
    f = paramParcel.readString();
    g = paramParcel.readString();
    h = ((StreetNumber)paramParcel.readValue(StreetNumber.class.getClassLoader()));
    k = paramParcel.readArrayList(Road.class.getClassLoader());
    l = paramParcel.readArrayList(Crossroad.class.getClassLoader());
    m = paramParcel.readArrayList(PoiItem.class.getClassLoader());
    i = paramParcel.readString();
    j = paramParcel.readString();
    n = paramParcel.readArrayList(BusinessArea.class.getClassLoader());
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getAdCode()
  {
    return j;
  }
  
  public String getBuilding()
  {
    return g;
  }
  
  public List<BusinessArea> getBusinessAreas()
  {
    return n;
  }
  
  public String getCity()
  {
    return c;
  }
  
  public String getCityCode()
  {
    return i;
  }
  
  public List<Crossroad> getCrossroads()
  {
    return l;
  }
  
  public String getDistrict()
  {
    return d;
  }
  
  public String getFormatAddress()
  {
    return a;
  }
  
  public String getNeighborhood()
  {
    return f;
  }
  
  public List<PoiItem> getPois()
  {
    return m;
  }
  
  public String getProvince()
  {
    return b;
  }
  
  public List<RegeocodeRoad> getRoads()
  {
    return k;
  }
  
  public StreetNumber getStreetNumber()
  {
    return h;
  }
  
  public String getTownship()
  {
    return e;
  }
  
  public void setAdCode(String paramString)
  {
    j = paramString;
  }
  
  public void setBuilding(String paramString)
  {
    g = paramString;
  }
  
  public void setBusinessAreas(List<BusinessArea> paramList)
  {
    n = paramList;
  }
  
  public void setCity(String paramString)
  {
    c = paramString;
  }
  
  public void setCityCode(String paramString)
  {
    i = paramString;
  }
  
  public void setCrossroads(List<Crossroad> paramList)
  {
    l = paramList;
  }
  
  public void setDistrict(String paramString)
  {
    d = paramString;
  }
  
  public void setFormatAddress(String paramString)
  {
    a = paramString;
  }
  
  public void setNeighborhood(String paramString)
  {
    f = paramString;
  }
  
  public void setPois(List<PoiItem> paramList)
  {
    m = paramList;
  }
  
  public void setProvince(String paramString)
  {
    b = paramString;
  }
  
  public void setRoads(List<RegeocodeRoad> paramList)
  {
    k = paramList;
  }
  
  public void setStreetNumber(StreetNumber paramStreetNumber)
  {
    h = paramStreetNumber;
  }
  
  public void setTownship(String paramString)
  {
    e = paramString;
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
    paramParcel.writeValue(h);
    paramParcel.writeList(k);
    paramParcel.writeList(l);
    paramParcel.writeList(m);
    paramParcel.writeString(i);
    paramParcel.writeString(j);
    paramParcel.writeList(n);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.geocoder.RegeocodeAddress
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */