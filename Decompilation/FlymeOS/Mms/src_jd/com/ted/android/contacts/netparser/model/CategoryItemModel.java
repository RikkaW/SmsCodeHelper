package com.ted.android.contacts.netparser.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import aos;

public class CategoryItemModel
  extends BaseModel
{
  public static final Parcelable.Creator<CategoryItemModel> CREATOR = new aos();
  private String a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  
  public CategoryItemModel() {}
  
  public CategoryItemModel(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    a = paramString1;
    c = paramString2;
    b = paramString3;
    d = paramString4;
    e = paramString5;
    f = paramString6;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString(a);
    paramParcel.writeString(c);
    paramParcel.writeString(b);
    paramParcel.writeString(d);
    paramParcel.writeString(e);
    paramParcel.writeString(f);
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.netparser.model.CategoryItemModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */