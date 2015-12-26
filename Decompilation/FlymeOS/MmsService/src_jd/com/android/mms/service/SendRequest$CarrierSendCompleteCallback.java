package com.android.mms.service;

import android.content.Context;
import android.util.Log;

final class SendRequest$CarrierSendCompleteCallback
  extends MmsRequest.CarrierMmsActionCallback
{
  private final SendRequest.CarrierSendManager mCarrierSendManager;
  private final Context mContext;
  
  public SendRequest$CarrierSendCompleteCallback(SendRequest paramSendRequest, Context paramContext, SendRequest.CarrierSendManager paramCarrierSendManager)
  {
    super(paramSendRequest);
    mContext = paramContext;
    mCarrierSendManager = paramCarrierSendManager;
  }
  
  public void onDownloadMmsComplete(int paramInt)
  {
    Log.e("MmsService", "Unexpected onDownloadMmsComplete call with result: " + paramInt);
  }
  
  public void onSendMmsComplete(int paramInt, byte[] paramArrayOfByte)
  {
    Log.d("MmsService", "Carrier app result for send: " + paramInt);
    mCarrierSendManager.disposeConnection(mContext);
    if (!this$0.maybeFallbackToRegularDelivery(paramInt)) {
      this$0.processResult(mContext, MmsRequest.toSmsManagerResult(paramInt), paramArrayOfByte, 0);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.SendRequest.CarrierSendCompleteCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */