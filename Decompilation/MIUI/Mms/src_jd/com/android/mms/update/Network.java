package com.android.mms.update;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.android.mms.MmsApp;
import com.android.mms.ui.MessageUtils;

public class Network
{
  public static boolean allowUpdate(boolean paramBoolean1, boolean paramBoolean2)
  {
    Application localApplication = MmsApp.getApp();
    if ((paramBoolean2) || (MessageUtils.isAllowNetworkAccess(localApplication))) {}
    for (int i = 1; i == 0; i = 0) {
      return false;
    }
    if (paramBoolean1) {
      return isWifiConnected(localApplication);
    }
    return isNetWorkConnected(localApplication);
  }
  
  public static boolean isNetWorkConnected(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnectedOrConnecting());
  }
  
  public static boolean isWifiConnected(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    NetworkInfo localNetworkInfo = paramContext.getActiveNetworkInfo();
    return (!paramContext.isActiveNetworkMetered()) && (localNetworkInfo != null) && (localNetworkInfo.isConnected());
  }
}

/* Location:
 * Qualified Name:     com.android.mms.update.Network
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */