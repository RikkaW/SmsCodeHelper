package com.ted.android.contacts.netparser.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import aph;
import java.util.List;

public class NearMoreModel
  extends BaseModel
{
  public static final Parcelable.Creator<NearMoreModel> CREATOR = new aph();
  private String a;
  private int b;
  private List<ServiceItemModel> c;
  
  public NearMoreModel() {}
  
  public NearMoreModel(String paramString, int paramInt, List<ServiceItemModel> paramList)
  {
    a = paramString;
    b = paramInt;
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
    paramParcel.writeInt(b);
    paramParcel.writeTypedList(c);
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.netparser.model.NearMoreModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */