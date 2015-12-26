package com.ted.android.contacts.netparser.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import aoo;
import java.util.List;

public class CategoryDetailData
  extends BaseModel
{
  public static final Parcelable.Creator<CategoryDetailData> CREATOR = new aoo();
  private List<CategoryDetailModel> a;
  
  public CategoryDetailData() {}
  
  public CategoryDetailData(Parcel paramParcel)
  {
    a = paramParcel.createTypedArrayList(CategoryDetailModel.CREATOR);
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
 * Qualified Name:     com.ted.android.contacts.netparser.model.CategoryDetailData
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */