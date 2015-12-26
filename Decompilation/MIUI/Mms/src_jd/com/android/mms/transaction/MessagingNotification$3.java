package com.android.mms.transaction;

import android.content.Context;

final class MessagingNotification$3
  implements Runnable
{
  MessagingNotification$3(Context paramContext, boolean paramBoolean1, boolean paramBoolean2) {}
  
  public void run()
  {
    MessagingNotification.blockingUpdateNewMessageIndicator(val$context, val$isNew, val$isStatusMessage);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessagingNotification.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */