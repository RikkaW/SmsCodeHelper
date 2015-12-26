package com.amap.api.services.help;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Tip
  implements Parcelable
{
  public static final Parcelable.Creator<Tip> CREATOR = new a();
  private String a;
  private String b;
  private String c;
  
  public Tip() {}
  
  private Tip(Parcel paramParcel)
  {
    a = paramParcel.readString();
    c = paramParcel.readString();
    b = paramParcel.readString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getAdcode()
  {
    return c;
  }
  
  public String getDistrict()
  {
    return b;
  }
  
  public String getName()
  {
    return a;
  }
  
  public void setAdcode(String paramString)
  {
    c = paramString;
  }
  
  public void setDistrict(String paramString)
  {
    b = paramString;
  }
  
  public void setName(String paramString)
  {
    a = paramString;
  }
  
  public String toString()
  {
    return "name:" + a + " district:" + b + " adcode:" + c;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(a);
    paramParcel.writeString(c);
    paramParcel.writeString(b);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.help.Tip
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */