package com.xiaomi.mipush.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public abstract class PushMessageReceiver
  extends BroadcastReceiver
{
  public void onCommandResult(Context paramContext, MiPushCommandMessage paramMiPushCommandMessage) {}
  
  public void onNotificationMessageArrived(Context paramContext, MiPushMessage paramMiPushMessage) {}
  
  public void onNotificationMessageClicked(Context paramContext, MiPushMessage paramMiPushMessage) {}
  
  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    MessageHandleService.addJob(new MessageHandleService.MessageHandleJob(paramIntent, this));
    paramIntent = new Intent(paramContext, MessageHandleService.class);
    try
    {
      paramContext.startService(paramIntent);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  @Deprecated
  public void onReceiveMessage(Context paramContext, MiPushMessage paramMiPushMessage) {}
  
  public void onReceivePassThroughMessage(Context paramContext, MiPushMessage paramMiPushMessage) {}
  
  public void onReceiveRegisterResult(Context paramContext, MiPushCommandMessage paramMiPushCommandMessage) {}
}

/* Location:
 * Qualified Name:     com.xiaomi.mipush.sdk.PushMessageReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */