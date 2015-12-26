package com.android.mms.service;

import android.service.carrier.ICarrierMessagingCallback.Stub;
import android.util.Log;

public abstract class MmsRequest$CarrierMmsActionCallback
  extends ICarrierMessagingCallback.Stub
{
  protected MmsRequest$CarrierMmsActionCallback(MmsRequest paramMmsRequest) {}
  
  public void onFilterComplete(boolean paramBoolean)
  {
    Log.e("MmsService", "Unexpected onFilterComplete call with result: " + paramBoolean);
  }
  
  public void onSendMultipartSmsComplete(int paramInt, int[] paramArrayOfInt)
  {
    Log.e("MmsService", "Unexpected onSendMultipartSmsComplete call with result: " + paramInt);
  }
  
  public void onSendSmsComplete(int paramInt1, int paramInt2)
  {
    Log.e("MmsService", "Unexpected onSendSmsComplete call with result: " + paramInt1);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.MmsRequest.CarrierMmsActionCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */