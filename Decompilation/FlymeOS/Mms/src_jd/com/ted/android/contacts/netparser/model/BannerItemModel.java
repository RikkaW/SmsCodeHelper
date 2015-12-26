package com.ted.android.contacts.netparser.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import aom;

public class BannerItemModel
  extends BaseModel
{
  public static final Parcelable.Creator<BannerItemModel> CREATOR = new aom();
  private String a;
  private String b;
  private String c;
  private String d;
  
  public BannerItemModel() {}
  
  public BannerItemModel(Parcel paramParcel)
  {
    b = paramParcel.readString();
    a = paramParcel.readString();
    c = paramParcel.readString();
    d = paramParcel.readString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString(b);
    paramParcel.writeString(a);
    paramParcel.writeString(c);
    paramParcel.writeString(d);
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.netparser.model.BannerItemModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */