package com.android.mms.transaction;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

final class SmsReceiverService$ServiceHandler
  extends Handler
{
  public SmsReceiverService$ServiceHandler(SmsReceiverService paramSmsReceiverService, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage)
  {
    int i = arg1;
    paramMessage = (Intent)obj;
    String str;
    int j;
    if (paramMessage != null)
    {
      str = paramMessage.getAction();
      j = paramMessage.getIntExtra("errorCode", 0);
      if (Log.isLoggable("Mms:transaction", 2)) {
        Log.v("SmsReceiverService", "handleMessage action: " + str + " error: " + j);
      }
      if (!"com.android.mms.transaction.MESSAGE_SENT".equals(paramMessage.getAction())) {
        break label105;
      }
      SmsReceiverService.access$000(this$0, paramMessage, j);
    }
    for (;;)
    {
      SmsReceiver.finishStartingService(this$0, i);
      return;
      label105:
      if ("android.provider.Telephony.SMS_DELIVER".equals(str)) {
        SmsReceiverService.access$100(this$0, paramMessage, j);
      } else if ("android.intent.action.BOOT_COMPLETED".equals(str)) {
        SmsReceiverService.access$200(this$0);
      } else if ("android.intent.action.SERVICE_STATE".equals(str)) {
        SmsReceiverService.access$300(this$0, paramMessage);
      } else if ("com.android.mms.transaction.SEND_MESSAGE".equals(str)) {
        SmsReceiverService.access$400(this$0, paramMessage);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.SmsReceiverService.ServiceHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */