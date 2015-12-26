package com.ted.android.contacts.netparser.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import aop;
import java.util.List;

public class CategoryDetailModel
  extends BaseModel
{
  public static final Parcelable.Creator<CategoryDetailModel> CREATOR = new aop();
  private String a;
  private String b;
  private List<CategoryDetailService> c;
  private List<CategoryDetailNumber> d;
  
  public CategoryDetailModel() {}
  
  public CategoryDetailModel(Parcel paramParcel)
  {
    a = paramParcel.readString();
    b = paramParcel.readString();
    c = paramParcel.createTypedArrayList(CategoryDetailService.CREATOR);
    d = paramParcel.createTypedArrayList(CategoryDetailNumber.CREATOR);
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
 * Qualified Name:     com.ted.android.contacts.netparser.model.CategoryDetailModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */