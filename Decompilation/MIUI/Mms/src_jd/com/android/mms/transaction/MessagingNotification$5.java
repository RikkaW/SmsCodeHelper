package com.android.mms.transaction;

import android.content.Context;
import android.widget.Toast;

final class MessagingNotification$5
  implements Runnable
{
  MessagingNotification$5(Context paramContext, int paramInt, CharSequence paramCharSequence, long paramLong) {}
  
  public void run()
  {
    MessagingNotification.access$1300(val$context, 0, val$slotId);
    Toast.makeText(val$context, val$message, (int)val$timeMillis).show();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessagingNotification.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */