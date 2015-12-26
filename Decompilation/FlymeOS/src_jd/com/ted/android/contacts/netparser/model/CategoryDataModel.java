package com.ted.android.contacts.netparser.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import aon;
import java.util.List;

public class CategoryDataModel
  extends BaseModel
{
  public static final Parcelable.Creator<CategoryDataModel> CREATOR = new aon();
  private String a;
  private String b;
  private List<CategoryItemModel> c;
  
  public CategoryDataModel() {}
  
  public CategoryDataModel(Parcel paramParcel)
  {
    a = paramParcel.readString();
    b = paramParcel.readString();
    c = paramParcel.createTypedArrayList(CategoryItemModel.CREATOR);
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
 * Qualified Name:     com.ted.android.contacts.netparser.model.CategoryDataModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */