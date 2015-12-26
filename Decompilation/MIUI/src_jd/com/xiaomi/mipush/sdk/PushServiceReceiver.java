package com.xiaomi.mipush.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PushServiceReceiver
  extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Intent localIntent = new Intent(paramContext, PushMessageHandler.class);
    localIntent.putExtras(paramIntent);
    localIntent.setAction(paramIntent.getAction());
    try
    {
      paramContext.startService(localIntent);
      return;
    }
    catch (Exception paramContext) {}
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mipush.sdk.PushServiceReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */