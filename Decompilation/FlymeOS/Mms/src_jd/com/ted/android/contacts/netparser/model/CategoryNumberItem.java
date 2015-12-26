package com.ted.android.contacts.netparser.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import aot;

public class CategoryNumberItem
  extends BaseModel
{
  public static final Parcelable.Creator<CategoryNumberItem> CREATOR = new aot();
  private String a;
  private String b;
  private String c;
  
  public CategoryNumberItem() {}
  
  public CategoryNumberItem(String paramString1, String paramString2, String paramString3)
  {
    b = paramString1;
    a = paramString2;
    c = paramString3;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString(b);
    paramParcel.writeString(a);
    paramParcel.writeString(c);
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.netparser.model.CategoryNumberItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */