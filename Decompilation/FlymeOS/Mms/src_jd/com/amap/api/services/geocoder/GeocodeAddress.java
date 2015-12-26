package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public final class GeocodeAddress
  implements Parcelable
{
  public static final Parcelable.Creator<GeocodeAddress> CREATOR = new b();
  private String a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private String g;
  private String h;
  private LatLonPoint i;
  private String j;
  
  public GeocodeAddress() {}
  
  private GeocodeAddress(Parcel paramParcel)
  {
    a = paramParcel.readString();
    b = paramParcel.readString();
    c = paramParcel.readString();
    d = paramParcel.readString();
    e = paramParcel.readString();
    f = paramParcel.readString();
    g = paramParcel.readString();
    h = paramParcel.readString();
    i = ((LatLonPoint)paramParcel.readValue(LatLonPoint.class.getClassLoader()));
    j = paramParcel.readString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getAdcode()
  {
    return h;
  }
  
  public String getBuilding()
  {
    return g;
  }
  
  public String getCity()
  {
    return c;
  }
  
  public String getDistrict()
  {
    return d;
  }
  
  public String getFormatAddress()
  {
    return a;
  }
  
  public LatLonPoint getLatLonPoint()
  {
    return i;
  }
  
  public String getLevel()
  {
    return j;
  }
  
  public String getNeighborhood()
  {
    return f;
  }
  
  public String getProvince()
  {
    return b;
  }
  
  public String getTownship()
  {
    return e;
  }
  
  public void setAdcode(String paramString)
  {
    h = paramString;
  }
  
  public void setBuilding(String paramString)
  {
    g = paramString;
  }
  
  public void setCity(String paramString)
  {
    c = paramString;
  }
  
  public void setDistrict(String paramString)
  {
    d = paramString;
  }
  
  public void setFormatAddress(String paramString)
  {
    a = paramString;
  }
  
  public void setLatLonPoint(LatLonPoint paramLatLonPoint)
  {
    i = paramLatLonPoint;
  }
  
  public void setLevel(String paramString)
  {
    j = paramString;
  }
  
  public void setNeighborhood(String paramString)
  {
    f = paramString;
  }
  
  public void setProvince(String paramString)
  {
    b = paramString;
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
    paramParcel.writeString(h);
    paramParcel.writeValue(i);
    paramParcel.writeString(j);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.geocoder.GeocodeAddress
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */