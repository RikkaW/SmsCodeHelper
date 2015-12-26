package com.android.mms.transaction;

import android.content.Context;
import com.android.mms.util.VibratorManager;

class MessagingNotification$MmsSmsNotificationInfo$1
  implements Runnable
{
  MessagingNotification$MmsSmsNotificationInfo$1(MessagingNotification.MmsSmsNotificationInfo paramMmsSmsNotificationInfo, Context paramContext) {}
  
  public void run()
  {
    MessagingNotification.access$800(val$context, this$0.mBlockType, this$0.mSlotId);
    if (this$0.mBlockType != 1) {
      VibratorManager.vibrate(val$context);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessagingNotification.MmsSmsNotificationInfo.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */