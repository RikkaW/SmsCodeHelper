package com.ted.android.contacts.netparser.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import api;

public class NumItem$RelevantNumber
  implements Parcelable
{
  public static final Parcelable.Creator<RelevantNumber> CREATOR = new api();
  public String a;
  public String b;
  public String c;
  
  private NumItem$RelevantNumber(Parcel paramParcel)
  {
    a = paramParcel.readString();
    b = paramParcel.readString();
    c = paramParcel.readString();
  }
  
  public NumItem$RelevantNumber(String paramString1, String paramString2, String paramString3)
  {
    a = paramString1;
    b = paramString2;
    c = paramString3;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(a);
    paramParcel.writeString(b);
    paramParcel.writeString(c);
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.netparser.model.NumItem.RelevantNumber
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */