package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class Photo
  implements Parcelable
{
  public static final Parcelable.Creator<Photo> CREATOR = new f();
  private String a;
  private String b;
  
  public Photo() {}
  
  public Photo(Parcel paramParcel)
  {
    a = paramParcel.readString();
    b = paramParcel.readString();
  }
  
  public Photo(String paramString1, String paramString2)
  {
    a = paramString1;
    b = paramString2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getTitle()
  {
    return a;
  }
  
  public String getUrl()
  {
    return b;
  }
  
  public void setTitle(String paramString)
  {
    a = paramString;
  }
  
  public void setUrl(String paramString)
  {
    b = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(a);
    paramParcel.writeString(b);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.poisearch.Photo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */