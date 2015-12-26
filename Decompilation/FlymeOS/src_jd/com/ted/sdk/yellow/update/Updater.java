package com.ted.sdk.yellow.update;

import android.content.Context;
import apv;
import com.ted.android.contacts.updatesdk.IParsedDownload;

public class Updater
{
  public static final int ALLOW_ALL_NETWORK = 0;
  public static final int ALLOW_MOBILE_ONLY = 2;
  public static final int ALLOW_WIFI_ONLY = 1;
  
  public static final void enable(boolean paramBoolean)
  {
    apv.a(paramBoolean);
  }
  
  public static final void init(Context paramContext)
  {
    apv.a(paramContext);
  }
  
  public static final void init(Context paramContext, int paramInt)
  {
    apv.a(paramContext, paramInt);
  }
  
  public static final void setOnParsedCallback(IParsedDownload paramIParsedDownload)
  {
    apv.a(paramIParsedDownload);
  }
  
  public static final void terminate() {}
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.update.Updater
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */