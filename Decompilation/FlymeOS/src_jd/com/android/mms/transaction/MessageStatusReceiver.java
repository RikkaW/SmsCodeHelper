package com.android.mms.transaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class MessageStatusReceiver
  extends BroadcastReceiver
{
  private static final String[] a = { "_id" };
  private static final Uri b = Uri.parse("content://sms/status");
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (("com.android.mms.transaction.MessageStatusReceiver.MESSAGE_STATUS_RECEIVED_MZ".equals(paramIntent.getAction())) || ("com.android.mms.transaction.MessageStatusReceiver.MESSAGE_STATUS_RECEIVED".equals(paramIntent.getAction())))
    {
      paramIntent.setClass(paramContext, MessageStatusService.class);
      paramContext.startService(paramIntent);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessageStatusReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */