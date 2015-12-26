package com.android.mms;

import android.location.Country;
import android.location.ICountryListener.Stub;

public class MmsApp$b
  extends ICountryListener.Stub
{
  public MmsApp$b(MmsApp paramMmsApp) {}
  
  public void onCountryDetected(Country paramCountry)
  {
    MmsApp.a(a, paramCountry.getCountryIso());
  }
}

/* Location:
 * Qualified Name:     com.android.mms.MmsApp.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */