package com.ted.android.contacts.netparser.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import apf;
import java.util.List;

public class NearDataModel
  extends BaseModel
{
  public static final Parcelable.Creator<NearDataModel> CREATOR = new apf();
  private String a;
  private String b;
  private List<NearItemModel> c;
  private List<NearMoreModel> d;
  
  public NearDataModel() {}
  
  public NearDataModel(String paramString1, String paramString2, List<NearItemModel> paramList, List<NearMoreModel> paramList1)
  {
    a = paramString1;
    b = paramString2;
    c = paramList;
    d = paramList1;
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
    paramParcel.writeTypedList(d);
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.netparser.model.NearDataModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */