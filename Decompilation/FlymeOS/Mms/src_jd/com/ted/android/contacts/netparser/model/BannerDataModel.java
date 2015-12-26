package com.ted.android.contacts.netparser.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import aol;
import java.util.List;

public class BannerDataModel
  extends BaseModel
{
  public static final Parcelable.Creator<BannerDataModel> CREATOR = new aol();
  private String a;
  private String b;
  private List<BannerItemModel> c;
  
  public BannerDataModel() {}
  
  public BannerDataModel(String paramString1, String paramString2, List<BannerItemModel> paramList)
  {
    a = paramString1;
    b = paramString2;
    c = paramList;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString(a);
    paramParcel.writeString(b);
    paramParcel.writeTypedList(c);
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.netparser.model.BannerDataModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */