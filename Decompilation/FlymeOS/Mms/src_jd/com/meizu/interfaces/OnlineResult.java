package com.meizu.interfaces;

import aiw;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class OnlineResult
  implements Parcelable
{
  public static final Parcelable.Creator<OnlineResult> CREATOR = new aiw();
  private String a;
  private int b;
  private int c;
  private int d;
  
  private OnlineResult(Parcel paramParcel)
  {
    a(paramParcel);
  }
  
  private String a(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "unknow";
    case 1: 
      return "ONLINE";
    case 0: 
      return "OFFLINE";
    }
    return "NOTEXISTS";
  }
  
  public void a(Parcel paramParcel)
  {
    a = paramParcel.readString();
    b = paramParcel.readInt();
    c = paramParcel.readInt();
    d = paramParcel.readInt();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String toString()
  {
    return "OnlineResult [Number=" + a + ", Status=" + a(b) + ", Version=" + c + ", Ability=" + d + "]";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(a);
    paramParcel.writeInt(b);
    paramParcel.writeInt(c);
    paramParcel.writeInt(d);
  }
}

/* Location:
 * Qualified Name:     com.meizu.interfaces.OnlineResult
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */