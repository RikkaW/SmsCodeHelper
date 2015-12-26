package com.ted.android.contacts.netparser.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import aox;
import java.util.List;

public class DottingEventDataModel
  extends BaseModel
{
  public static final Parcelable.Creator<DottingEventDataModel> CREATOR = new aox();
  private List<DottingEventItemModel> a;
  
  public DottingEventDataModel() {}
  
  public DottingEventDataModel(Parcel paramParcel)
  {
    a = paramParcel.createTypedArrayList(DottingEventItemModel.CREATOR);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeTypedList(a);
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.netparser.model.DottingEventDataModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */