package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;

public class PushProvision
{
  private static PushProvision sInstance;
  private Context mContext;
  private int mProvisioned = 0;
  
  private PushProvision(Context paramContext)
  {
    mContext = paramContext.getApplicationContext();
  }
  
  public static PushProvision getInstance(Context paramContext)
  {
    if (sInstance == null) {
      sInstance = new PushProvision(paramContext);
    }
    return sInstance;
  }
  
  public boolean checkProvisioned()
  {
    return ("@SHIP.TO.2A2FE0D7@".contains("xmsf")) || ("@SHIP.TO.2A2FE0D7@".contains("xiaomi")) || ("@SHIP.TO.2A2FE0D7@".contains("miui"));
  }
  
  @SuppressLint({"NewApi"})
  public int getProvisioned()
  {
    if (mProvisioned != 0) {
      return mProvisioned;
    }
    if (Build.VERSION.SDK_INT >= 17)
    {
      mProvisioned = Settings.Global.getInt(mContext.getContentResolver(), "device_provisioned", 0);
      return mProvisioned;
    }
    mProvisioned = Settings.Secure.getInt(mContext.getContentResolver(), "device_provisioned", 0);
    return mProvisioned;
  }
  
  @SuppressLint({"NewApi"})
  public Uri getProvisionedUri()
  {
    if (Build.VERSION.SDK_INT >= 17) {
      return Settings.Global.getUriFor("device_provisioned");
    }
    return Settings.Secure.getUriFor("device_provisioned");
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.PushProvision
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */