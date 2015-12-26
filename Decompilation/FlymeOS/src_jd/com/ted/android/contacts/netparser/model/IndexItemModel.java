package com.ted.android.contacts.netparser.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import apb;

public class IndexItemModel
  extends BaseModel
{
  public static final Parcelable.Creator<IndexItemModel> CREATOR = new apb();
  private String a;
  private int b;
  private int c;
  private int d;
  
  public IndexItemModel() {}
  
  public IndexItemModel(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    a = paramString;
    b = paramInt1;
    c = paramInt2;
    d = paramInt3;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString(a);
    paramParcel.writeInt(b);
    paramParcel.writeInt(c);
    paramParcel.writeInt(d);
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.netparser.model.IndexItemModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */