package com.android.mms.transaction;

import android.content.Context;
import android.content.Intent;

public class HighPushReceiver
  extends PushReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    processReceive(paramContext, paramIntent);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.HighPushReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */