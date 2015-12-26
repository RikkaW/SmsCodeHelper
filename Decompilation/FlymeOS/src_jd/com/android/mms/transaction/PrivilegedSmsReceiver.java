package com.android.mms.transaction;

import android.content.Context;
import android.content.Intent;

public class PrivilegedSmsReceiver
  extends SmsReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    a(paramContext, paramIntent, true);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.PrivilegedSmsReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */