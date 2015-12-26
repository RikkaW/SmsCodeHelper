package com.ted.android.contacts.netparser.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import apg;

public class NearItemModel
  extends BaseModel
{
  public static final Parcelable.Creator<NearItemModel> CREATOR = new apg();
  private String a;
  private String b;
  private String c;
  private String d;
  
  public NearItemModel() {}
  
  public NearItemModel(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    a = paramString1;
    b = paramString2;
    c = paramString3;
    d = paramString4;
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
    paramParcel.writeString(c);
    paramParcel.writeString(d);
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.netparser.model.NearItemModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */