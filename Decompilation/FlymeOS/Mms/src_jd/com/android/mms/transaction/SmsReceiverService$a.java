package com.android.mms.transaction;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import ga;

final class SmsReceiverService$a
  extends Handler
{
  public SmsReceiverService$a(SmsReceiverService paramSmsReceiverService, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage)
  {
    int i = arg1;
    paramMessage = (Intent)obj;
    Log.v("SmsReceiverService", "handleMessage serviceId: " + i + " intent: " + paramMessage);
    String str;
    int j;
    if ((paramMessage != null) && (ga.C()))
    {
      str = paramMessage.getAction();
      j = paramMessage.getIntExtra("errorCode", 0);
      Log.v("SmsReceiverService", "handleMessage action: " + str + " error: " + j);
      if (!"com.android.mms.transaction.MESSAGE_SENT".equals(paramMessage.getAction())) {
        break label136;
      }
      SmsReceiverService.a(a, paramMessage, j);
    }
    for (;;)
    {
      SmsReceiver.a(a, i);
      return;
      label136:
      if ("android.provider.Telephony.SMS_DELIVER".equals(str)) {
        SmsReceiverService.b(a, paramMessage, j);
      } else if ("android.intent.action.DATA_SMS_RECEIVED".equals(str)) {
        SmsReceiverService.b(a, paramMessage, j);
      } else if ("android.intent.action.BOOT_COMPLETED".equals(str)) {
        SmsReceiverService.a(a);
      } else if ("android.intent.action.SERVICE_STATE".equals(str)) {
        SmsReceiverService.a(a, paramMessage);
      } else if ("com.android.mms.transaction.SEND_MESSAGE".endsWith(str)) {
        SmsReceiverService.b(a);
      } else if ("com.android.mms.transaction.SEND_INACTIVE_MESSAGE".equals(str)) {
        SmsReceiverService.c(a);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.SmsReceiverService.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */