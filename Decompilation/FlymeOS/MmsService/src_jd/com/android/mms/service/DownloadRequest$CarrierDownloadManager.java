package com.android.mms.service;

import android.content.Context;
import android.net.Uri;
import android.os.RemoteException;
import android.service.carrier.ICarrierMessagingService;
import android.telephony.CarrierMessagingServiceManager;
import android.util.Log;

final class DownloadRequest$CarrierDownloadManager
  extends CarrierMessagingServiceManager
{
  private volatile DownloadRequest.CarrierDownloadCompleteCallback mCarrierDownloadCallback;
  
  private DownloadRequest$CarrierDownloadManager(DownloadRequest paramDownloadRequest) {}
  
  void downloadMms(Context paramContext, String paramString, DownloadRequest.CarrierDownloadCompleteCallback paramCarrierDownloadCompleteCallback)
  {
    mCarrierDownloadCallback = paramCarrierDownloadCompleteCallback;
    if (bindToCarrierMessagingService(paramContext, paramString))
    {
      Log.v("MmsService", "bindService() for carrier messaging service succeeded");
      return;
    }
    Log.e("MmsService", "bindService() for carrier messaging service failed");
    paramCarrierDownloadCompleteCallback.onDownloadMmsComplete(1);
  }
  
  protected void onServiceReady(ICarrierMessagingService paramICarrierMessagingService)
  {
    try
    {
      paramICarrierMessagingService.downloadMms(DownloadRequest.access$100(this$0), this$0.mSubId, Uri.parse(DownloadRequest.access$200(this$0)), mCarrierDownloadCallback);
      return;
    }
    catch (RemoteException paramICarrierMessagingService)
    {
      Log.e("MmsService", "Exception downloading MMS using the carrier messaging service: " + paramICarrierMessagingService);
      mCarrierDownloadCallback.onDownloadMmsComplete(1);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.DownloadRequest.CarrierDownloadManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */