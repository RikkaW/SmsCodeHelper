package com.ted.android.contacts.netparser.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import aov;
import java.util.List;

public class CollectionDataModel
  extends BaseModel
{
  public static final Parcelable.Creator<CollectionDataModel> CREATOR = new aov();
  private List<CollectionItemModel> a;
  
  public CollectionDataModel() {}
  
  public CollectionDataModel(List<CollectionItemModel> paramList)
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
 * Qualified Name:     com.ted.android.contacts.netparser.model.CollectionDataModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */