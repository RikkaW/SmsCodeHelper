package com.android.mms.transaction;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import com.android.mms.util.MSimUtils;

class SmsReceiverService$3
  implements Runnable
{
  SmsReceiverService$3(SmsReceiverService paramSmsReceiverService, int paramInt) {}
  
  public void run()
  {
    SmsReceiverService localSmsReceiverService = this$0;
    Object localObject2 = "";
    Object localObject1 = localObject2;
    if (MSimUtils.isMSimInserted())
    {
      if (val$slotId != 0) {
        break label91;
      }
      localObject1 = localSmsReceiverService.getString(2131362235);
    }
    for (;;)
    {
      localObject2 = localObject1;
      if (!TextUtils.isEmpty((CharSequence)localObject1)) {
        localObject2 = (String)localObject1 + " ";
      }
      Toast.makeText(localSmsReceiverService, (String)localObject2 + localSmsReceiverService.getString(2131362000), 0).show();
      return;
      label91:
      localObject1 = localObject2;
      if (val$slotId == 1) {
        localObject1 = localSmsReceiverService.getString(2131362236);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.SmsReceiverService.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */