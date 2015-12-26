package com.ted.sdk.yellow.entry;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.NumItem.RelevantNumber;

public class ContactItem$RelevantNumber
  implements Parcelable
{
  public static final Parcelable.Creator<RelevantNumber> CREATOR = new ContactItem.RelevantNumber.1();
  private String desc;
  private String name;
  private String phone;
  
  private ContactItem$RelevantNumber(Parcel paramParcel)
  {
    phone = paramParcel.readString();
    name = paramParcel.readString();
    desc = paramParcel.readString();
  }
  
  ContactItem$RelevantNumber(NumItem.RelevantNumber paramRelevantNumber)
  {
    this(a, b, c);
  }
  
  ContactItem$RelevantNumber(String paramString1, String paramString2, String paramString3)
  {
    phone = paramString1;
    name = paramString2;
    desc = paramString3;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getDesciption()
  {
    return desc;
  }
  
  public String getName()
  {
    return name;
  }
  
  public String getPhoneNumber()
  {
    return phone;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(phone);
    paramParcel.writeString(name);
    paramParcel.writeString(desc);
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.entry.ContactItem.RelevantNumber
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */