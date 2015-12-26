package com.ted.android.contacts.netparser.model;

import android.os.Parcel;
import android.os.Parcelable;

public class YellowPageData
  implements Parcelable
{
  private int a;
  private int b;
  private int c;
  private String d;
  private int e;
  private String f = "";
  
  public YellowPageData() {}
  
  public YellowPageData(int paramInt1, int paramInt2, int paramInt3, String paramString, int paramInt4)
  {
    a = paramInt1;
    b = paramInt2;
    c = paramInt3;
    d = paramString;
    e = paramInt4;
  }
  
  public int a()
  {
    return a;
  }
  
  public void a(String paramString)
  {
    f = paramString;
  }
  
  public int b()
  {
    return b;
  }
  
  public int c()
  {
    return c;
  }
  
  public String d()
  {
    return d;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int e()
  {
    return e;
  }
  
  public String f()
  {
    return f;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {}
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.netparser.model.YellowPageData
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */