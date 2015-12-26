package com.ted.android.contacts.netparser.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import aoz;
import java.util.List;

public class HistoryDataModel
  extends BaseModel
{
  public static final Parcelable.Creator<HistoryDataModel> CREATOR = new aoz();
  private List<HistoryItemModel> a;
  
  public HistoryDataModel() {}
  
  public HistoryDataModel(List<HistoryItemModel> paramList)
  {
    a = paramList;
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
 * Qualified Name:     com.ted.android.contacts.netparser.model.HistoryDataModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */