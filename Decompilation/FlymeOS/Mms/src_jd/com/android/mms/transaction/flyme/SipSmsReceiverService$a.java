package com.android.mms.transaction.flyme;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.android.mms.transaction.SmsReceiver;

final class SipSmsReceiverService$a
  extends Handler
{
  public SipSmsReceiverService$a(SipSmsReceiverService paramSipSmsReceiverService, Looper paramLooper)
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
      if (!"com.android.mms.transaction.SIP_MESSAGE_SENT".equals(paramMessage.getAction())) {
        break label61;
      }
      SipSmsReceiverService.a(a, paramMessage, j);
    }
    for (;;)
    {
      SmsReceiver.a(a, i);
      return;
      label61:
      if ("android.provider.Telephony.SIP_SMS_RECEIVED".equals(str)) {
        SipSmsReceiverService.b(a, paramMessage, j);
      } else if ("com.android.mms.transaction.SEND_SIP_MESSAGE".endsWith(str)) {
        SipSmsReceiverService.a(a);
      } else if ("android.provider.Telephony.SIP_SMS_STATUS_RECEIVED".equals(paramMessage.getAction())) {
        SipSmsReceiverService.a(a, paramMessage);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.flyme.SipSmsReceiverService.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */