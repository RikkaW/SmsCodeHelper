package com.android.mms.data;

import android.content.Context;
import android.util.Log;
import com.android.mms.transaction.MessagingNotification;

final class Conversation$3
  implements Runnable
{
  Conversation$3(Context paramContext) {}
  
  public void run()
  {
    long l1 = System.currentTimeMillis();
    if (Thread.currentThread().isInterrupted()) {}
    do
    {
      do
      {
        do
        {
          return;
          Conversation.access$400(val$context);
        } while (Thread.currentThread().isInterrupted());
        Conversation.access$500(val$context);
      } while (Thread.currentThread().isInterrupted());
      Conversation.access$600(val$context);
      long l2 = System.currentTimeMillis();
      Log.d("Mms/conv", "update all seen: " + (l2 - l1) + "ms");
    } while (Thread.currentThread().isInterrupted());
    MessagingNotification.blockingUpdateAllNotifications(val$context);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.data.Conversation.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */