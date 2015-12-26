package com.ted.android.contacts.netparser.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import aou;

public class CategoryServiceItem
  extends BaseModel
{
  public static final Parcelable.Creator<CategoryServiceItem> CREATOR = new aou();
  private String a;
  private String b;
  private String c;
  private String d;
  private String e;
  
  public CategoryServiceItem() {}
  
  public CategoryServiceItem(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    b = paramString1;
    a = paramString2;
    c = paramString3;
    d = paramString4;
    e = paramString5;
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
    paramParcel.writeString(d);
    paramParcel.writeString(e);
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.netparser.model.CategoryServiceItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */