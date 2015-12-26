package com.android.mms.service;

import android.content.Context;
import android.net.Uri;
import android.os.RemoteException;
import android.service.carrier.ICarrierMessagingService;
import android.telephony.CarrierMessagingServiceManager;
import android.util.Log;

final class SendRequest$CarrierSendManager
  extends CarrierMessagingServiceManager
{
  private volatile SendRequest.CarrierSendCompleteCallback mCarrierSendCompleteCallback;
  
  private SendRequest$CarrierSendManager(SendRequest paramSendRequest) {}
  
  protected void onServiceReady(ICarrierMessagingService paramICarrierMessagingService)
  {
    Uri localUri = null;
    try
    {
      if (SendRequest.access$100(this$0) != null) {
        localUri = Uri.parse(SendRequest.access$100(this$0));
      }
      paramICarrierMessagingService.sendMms(SendRequest.access$200(this$0), this$0.mSubId, localUri, mCarrierSendCompleteCallback);
      return;
    }
    catch (RemoteException paramICarrierMessagingService)
    {
      Log.e("MmsService", "Exception sending MMS using the carrier messaging service: " + paramICarrierMessagingService);
      mCarrierSendCompleteCallback.onSendMmsComplete(1, null);
    }
  }
  
  void sendMms(Context paramContext, String paramString, SendRequest.CarrierSendCompleteCallback paramCarrierSendCompleteCallback)
  {
    mCarrierSendCompleteCallback = paramCarrierSendCompleteCallback;
    if (bindToCarrierMessagingService(paramContext, paramString))
    {
      Log.v("MmsService", "bindService() for carrier messaging service succeeded");
      return;
    }
    Log.e("MmsService", "bindService() for carrier messaging service failed");
    paramCarrierSendCompleteCallback.onSendMmsComplete(1, null);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.SendRequest.CarrierSendManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */