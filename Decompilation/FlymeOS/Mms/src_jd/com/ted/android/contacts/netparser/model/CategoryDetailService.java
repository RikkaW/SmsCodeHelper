package com.ted.android.contacts.netparser.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import aor;
import java.util.List;

public class CategoryDetailService
  extends BaseModel
{
  public static final Parcelable.Creator<CategoryDetailService> CREATOR = new aor();
  private String a;
  private List<CategoryServiceItem> b;
  
  public CategoryDetailService() {}
  
  public CategoryDetailService(Parcel paramParcel)
  {
    a = paramParcel.readString();
    b = paramParcel.createTypedArrayList(CategoryServiceItem.CREATOR);
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
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.netparser.model.CategoryDetailService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */