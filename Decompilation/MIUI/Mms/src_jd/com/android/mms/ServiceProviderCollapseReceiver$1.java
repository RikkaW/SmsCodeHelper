package com.android.mms;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.util.Log;

class ServiceProviderCollapseReceiver$1
  extends PhoneStateListener
{
  ServiceProviderCollapseReceiver$1(ServiceProviderCollapseReceiver paramServiceProviderCollapseReceiver, Context paramContext, boolean paramBoolean) {}
  
  public void onServiceStateChanged(ServiceState paramServiceState)
  {
    Log.d("ServiceNumberCollapseReceiver", "onServiceStateChanged: " + paramServiceState);
    ServiceCategoryUpdateAsyncTask.startUpdateAsyncTask(val$context, val$isFirstUpdate, this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ServiceProviderCollapseReceiver.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */