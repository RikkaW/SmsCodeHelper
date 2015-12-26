package com.xiaomi.mipush.sdk;

import android.content.Intent;

public class MessageHandleService$MessageHandleJob
{
  private Intent intent;
  private PushMessageReceiver receiver;
  
  public MessageHandleService$MessageHandleJob(Intent paramIntent, PushMessageReceiver paramPushMessageReceiver)
  {
    receiver = paramPushMessageReceiver;
    intent = paramIntent;
  }
  
  public Intent getIntent()
  {
    return intent;
  }
  
  public PushMessageReceiver getReceiver()
  {
    return receiver;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mipush.sdk.MessageHandleService.MessageHandleJob
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */