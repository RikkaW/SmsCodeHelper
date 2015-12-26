package com.ted.android.contacts.netparser.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import apa;

public class HistoryItemModel
  extends BaseModel
{
  public static final Parcelable.Creator<HistoryItemModel> CREATOR = new apa();
  private String a;
  private String b;
  private String c;
  private String d;
  private int e;
  
  public HistoryItemModel() {}
  
  public HistoryItemModel(Parcel paramParcel)
  {
    a = paramParcel.readString();
    b = paramParcel.readString();
    c = paramParcel.readString();
    d = paramParcel.readString();
    e = paramParcel.readInt();
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
    paramParcel.writeString(c);
    paramParcel.writeString(d);
    paramParcel.writeInt(e);
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.netparser.model.HistoryItemModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */