package com.android.mms;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.util.Log;

public class ServiceProviderCollapseReceiver
  extends BroadcastReceiver
  implements ServiceCategoryUpdateAsyncTask.IServiceCategoryUpdateListener
{
  private static long UPDATE_INTERVAL = 604800000L;
  private static PhoneStateListener sPhoneListener;
  
  public void onReceive(final Context paramContext, Intent paramIntent)
  {
    if ("android.intent.action.BOOT_COMPLETED".equals(paramIntent.getAction()))
    {
      paramIntent = PreferenceManager.getDefaultSharedPreferences(paramContext);
      if (paramIntent.contains("pref_service_category_upadate_time")) {
        break label117;
      }
    }
    label117:
    for (final boolean bool = true;; bool = false)
    {
      long l1 = paramIntent.getLong("pref_service_category_upadate_time", 0L);
      int i = 0;
      long l2 = System.currentTimeMillis();
      if ((bool) || (l2 - l1 >= UPDATE_INTERVAL) || (l1 > l2)) {
        i = 1;
      }
      if ((i != 0) && (sPhoneListener == null))
      {
        sPhoneListener = new PhoneStateListener()
        {
          public void onServiceStateChanged(ServiceState paramAnonymousServiceState)
          {
            Log.d("ServiceNumberCollapseReceiver", "onServiceStateChanged: " + paramAnonymousServiceState);
            ServiceCategoryUpdateAsyncTask.startUpdateAsyncTask(paramContext, bool, ServiceProviderCollapseReceiver.this);
          }
        };
        ((TelephonyManager)paramContext.getSystemService("phone")).listen(sPhoneListener, 1);
      }
      return;
    }
  }
  
  public void onUpdateSuccess()
  {
    Application localApplication = MmsApp.getApp();
    if (sPhoneListener != null) {
      ((TelephonyManager)localApplication.getSystemService("phone")).listen(sPhoneListener, 0);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ServiceProviderCollapseReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */