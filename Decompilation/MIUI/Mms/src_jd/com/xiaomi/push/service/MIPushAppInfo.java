package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import java.util.ArrayList;
import java.util.List;

public class MIPushAppInfo
{
  private static MIPushAppInfo sInstance = null;
  private Context appContext;
  private List<String> unRegisteredPkg = new ArrayList();
  
  private MIPushAppInfo(Context paramContext)
  {
    appContext = paramContext.getApplicationContext();
    if (appContext == null) {
      appContext = paramContext;
    }
    paramContext = appContext.getSharedPreferences("mipush_app_info", 0).getString("unregistered_pkg_names", "").split(",");
    int j = paramContext.length;
    int i = 0;
    while (i < j)
    {
      CharSequence localCharSequence = paramContext[i];
      if (TextUtils.isEmpty(localCharSequence)) {
        unRegisteredPkg.add(localCharSequence);
      }
      i += 1;
    }
  }
  
  public static MIPushAppInfo getInstance(Context paramContext)
  {
    if (sInstance == null) {
      sInstance = new MIPushAppInfo(paramContext);
    }
    return sInstance;
  }
  
  public void addUnRegisteredPkg(String paramString)
  {
    synchronized (unRegisteredPkg)
    {
      if (!unRegisteredPkg.contains(paramString))
      {
        unRegisteredPkg.add(paramString);
        paramString = XMStringUtils.join(unRegisteredPkg, ",");
        appContext.getSharedPreferences("mipush_app_info", 0).edit().putString("unregistered_pkg_names", paramString).commit();
      }
      return;
    }
  }
  
  public boolean isUnRegistered(String paramString)
  {
    synchronized (unRegisteredPkg)
    {
      boolean bool = unRegisteredPkg.contains(paramString);
      return bool;
    }
  }
  
  public void removeUnRegisteredPkg(String paramString)
  {
    synchronized (unRegisteredPkg)
    {
      if (unRegisteredPkg.contains(paramString))
      {
        unRegisteredPkg.remove(paramString);
        paramString = XMStringUtils.join(unRegisteredPkg, ",");
        appContext.getSharedPreferences("mipush_app_info", 0).edit().putString("unregistered_pkg_names", paramString).commit();
      }
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.MIPushAppInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */