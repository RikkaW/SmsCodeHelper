package com.android.mms.transaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MmsReceiver
  extends BroadcastReceiver
{
  public static void a(Context paramContext, Intent paramIntent)
  {
    paramContext.startService(paramIntent);
  }
  
  protected void a(Context paramContext, Intent paramIntent, boolean paramBoolean)
  {
    Log.d("Mms", "MmsReceiver: onReceiveWithPrivilege(). sub Id = " + paramIntent.getLongExtra("subscription", -1L) + ", Action = " + paramIntent.getAction() + ", result = " + getResultCode() + ", uri = " + paramIntent.getStringExtra("bundle_uri"));
    paramIntent.setClass(paramContext, TransactionService.class);
    paramIntent.putExtra("result", getResultCode());
    a(paramContext, paramIntent);
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    a(paramContext, paramIntent, false);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MmsReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */