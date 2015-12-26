package com.android.mms.transaction;

import android.content.Context;
import android.net.Uri;

final class MessagingNotification$4
  implements Runnable
{
  MessagingNotification$4(Context paramContext, Uri paramUri) {}
  
  public void run()
  {
    MessagingNotification.MmsSmsDeliveryInfo localMmsSmsDeliveryInfo = MessagingNotification.access$500(val$context, val$messageUri);
    if (localMmsSmsDeliveryInfo != null) {
      localMmsSmsDeliveryInfo.deliver(val$context, true);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessagingNotification.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */