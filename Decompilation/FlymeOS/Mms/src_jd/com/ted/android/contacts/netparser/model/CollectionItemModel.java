package com.ted.android.contacts.netparser.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import aow;

public class CollectionItemModel
  extends BaseModel
{
  public static final Parcelable.Creator<CollectionItemModel> CREATOR = new aow();
  private String a;
  private String b;
  private String c;
  private String d;
  private String e;
  
  public CollectionItemModel() {}
  
  public CollectionItemModel(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    a = paramString1;
    b = paramString2;
    c = paramString3;
    d = paramString4;
    e = paramString5;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    do
    {
      do
      {
        return false;
      } while (getClass() != paramObject.getClass());
      paramObject = (CollectionItemModel)paramObject;
      if (c != null) {
        break;
      }
    } while (c != null);
    while (c.equals(c)) {
      return true;
    }
    return false;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString(a);
    paramParcel.writeString(b);
    paramParcel.writeString(c);
    paramParcel.writeString(d);
    paramParcel.writeString(e);
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.netparser.model.CollectionItemModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */