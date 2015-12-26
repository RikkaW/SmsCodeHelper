package com.android.mms.transaction.sns;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.android.mms.transaction.SmsReceiver;

final class SnsSmsReceiverService$a
  extends Handler
{
  public SnsSmsReceiverService$a(SnsSmsReceiverService paramSnsSmsReceiverService, Looper paramLooper)
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
      Log.d("SnsMessageManager.SnsSmsReceiverService", "SnsSmsReceiverService-->handleMessage(), action is " + str + ", serviceId is " + i + ", error is " + j);
      if (!"com.android.mms.transaction.SEND_SNS_MESSAGE".endsWith(str)) {
        break label101;
      }
      SnsSmsReceiverService.a(a);
    }
    for (;;)
    {
      SmsReceiver.a(a, i);
      return;
      label101:
      if ("com.android.mms.transaction.SNS_MESSAGE_SENT".equals(paramMessage.getAction())) {
        SnsSmsReceiverService.a(a, paramMessage, j);
      } else if ("android.provider.Telephony.SNS_SMS_RECEIVED".equals(str)) {
        SnsSmsReceiverService.b(a, paramMessage, j);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.sns.SnsSmsReceiverService.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */