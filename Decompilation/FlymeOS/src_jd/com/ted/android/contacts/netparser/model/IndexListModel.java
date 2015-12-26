package com.ted.android.contacts.netparser.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import apd;
import java.util.List;

public class IndexListModel
  extends BaseModel
{
  public static final Parcelable.Creator<IndexListModel> CREATOR = new apd();
  private int a;
  private String b;
  private List<IndexItemModel> c;
  
  public IndexListModel() {}
  
  public IndexListModel(int paramInt, String paramString, List<IndexItemModel> paramList)
  {
    a = paramInt;
    b = paramString;
    c = paramList;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(a);
    paramParcel.writeString(b);
    paramParcel.writeTypedList(c);
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.netparser.model.IndexListModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */