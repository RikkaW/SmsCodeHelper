package com.android.mms.transaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.mms.data.Conversation;

public class MessagingNotification$OnDeletedReceiver
  extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (Log.isLoggable("Mms:app", 2)) {
      Log.d("Mms:app", "[MessagingNotification] clear notification: mark all msgs seen");
    }
    Conversation.markAllConversationsAsSeen(paramContext);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessagingNotification.OnDeletedReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */