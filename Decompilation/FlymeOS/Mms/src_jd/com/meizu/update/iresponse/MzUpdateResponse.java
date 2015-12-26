package com.meizu.update.iresponse;

import amt;
import amt.a;
import amu;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public class MzUpdateResponse
  implements Parcelable
{
  public static final Parcelable.Creator<MzUpdateResponse> CREATOR = new amu();
  private amt a;
  
  public MzUpdateResponse(amt paramamt)
  {
    a = paramamt;
  }
  
  public MzUpdateResponse(Parcel paramParcel)
  {
    a = amt.a.a(paramParcel.readStrongBinder());
  }
  
  private void a(int paramInt)
  {
    try
    {
      a.a(paramInt, null);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
  }
  
  private void a(int paramInt, Bundle paramBundle)
  {
    try
    {
      a.b(paramInt, paramBundle);
      return;
    }
    catch (RemoteException paramBundle)
    {
      paramBundle.printStackTrace();
    }
  }
  
  public void a()
  {
    a(2, null);
  }
  
  public void a(String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("apk_path", paramString);
    a(0, localBundle);
  }
  
  public void b()
  {
    a(1, null);
  }
  
  public void c()
  {
    a(0);
  }
  
  public void d()
  {
    a(2);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void e()
  {
    a(3);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeStrongBinder(a.asBinder());
  }
}

/* Location:
 * Qualified Name:     com.meizu.update.iresponse.MzUpdateResponse
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */