package com.ted.android.contacts.netparser.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import aoq;
import java.util.List;

public class CategoryDetailNumber
  extends BaseModel
{
  public static final Parcelable.Creator<CategoryDetailNumber> CREATOR = new aoq();
  private String a;
  private List<CategoryNumberItem> b;
  private List<CategoryNumberItem> c;
  
  public CategoryDetailNumber() {}
  
  public CategoryDetailNumber(Parcel paramParcel)
  {
    a = paramParcel.readString();
    b = paramParcel.createTypedArrayList(CategoryNumberItem.CREATOR);
    c = paramParcel.createTypedArrayList(CategoryNumberItem.CREATOR);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString(a);
    paramParcel.writeTypedList(b);
    paramParcel.writeTypedList(c);
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.netparser.model.CategoryDetailNumber
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */