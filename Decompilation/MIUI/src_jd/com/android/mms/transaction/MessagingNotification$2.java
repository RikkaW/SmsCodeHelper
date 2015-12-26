package com.android.mms.transaction;

import com.android.mms.MmsApp;

final class MessagingNotification$2
  implements Runnable
{
  public void run()
  {
    MessagingNotification.cancelFloatNotification(MmsApp.getApp());
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessagingNotification.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */