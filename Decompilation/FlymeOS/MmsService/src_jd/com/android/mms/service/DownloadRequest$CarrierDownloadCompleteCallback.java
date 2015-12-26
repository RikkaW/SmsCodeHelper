package com.android.mms.service;

import android.content.Context;
import android.util.Log;

final class DownloadRequest$CarrierDownloadCompleteCallback
  extends MmsRequest.CarrierMmsActionCallback
{
  private final DownloadRequest.CarrierDownloadManager mCarrierDownloadManager;
  private final Context mContext;
  
  public DownloadRequest$CarrierDownloadCompleteCallback(DownloadRequest paramDownloadRequest, Context paramContext, DownloadRequest.CarrierDownloadManager paramCarrierDownloadManager)
  {
    super(paramDownloadRequest);
    mContext = paramContext;
    mCarrierDownloadManager = paramCarrierDownloadManager;
  }
  
  public void onDownloadMmsComplete(int paramInt)
  {
    Log.d("MmsService", "Carrier app result for download: " + paramInt);
    mCarrierDownloadManager.disposeConnection(mContext);
    if (!this$0.maybeFallbackToRegularDelivery(paramInt)) {
      this$0.processResult(mContext, MmsRequest.toSmsManagerResult(paramInt), null, 0);
    }
  }
  
  public void onSendMmsComplete(int paramInt, byte[] paramArrayOfByte)
  {
    Log.e("MmsService", "Unexpected onSendMmsComplete call with result: " + paramInt);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.DownloadRequest.CarrierDownloadCompleteCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */