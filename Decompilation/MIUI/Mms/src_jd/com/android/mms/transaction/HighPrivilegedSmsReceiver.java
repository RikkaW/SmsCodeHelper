package com.android.mms.transaction;

import android.content.Context;
import android.content.Intent;

public class HighPrivilegedSmsReceiver
  extends SmsReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    onReceiveWithPrivilege(paramContext, paramIntent, true);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.HighPrivilegedSmsReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */