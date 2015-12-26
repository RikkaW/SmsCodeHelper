package com.xiaomi.mipush.sdk;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageHandleService
  extends IntentService
{
  private static ConcurrentLinkedQueue<MessageHandleJob> jobQueue = new ConcurrentLinkedQueue();
  
  public MessageHandleService()
  {
    super("MessageHandleThread");
  }
  
  public static void addJob(MessageHandleJob paramMessageHandleJob)
  {
    if (paramMessageHandleJob != null) {
      jobQueue.add(paramMessageHandleJob);
    }
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    Object localObject;
    if (paramIntent != null)
    {
      localObject = (MessageHandleJob)jobQueue.poll();
      if (localObject != null) {
        break label19;
      }
    }
    label19:
    label162:
    label183:
    do
    {
      do
      {
        do
        {
          for (;;)
          {
            return;
            try
            {
              paramIntent = ((MessageHandleJob)localObject).getReceiver();
              localObject = ((MessageHandleJob)localObject).getIntent();
              switch (((Intent)localObject).getIntExtra("message_type", 1))
              {
              case 4: 
              case 1: 
                localObject = PushMessageProcessor.getInstance(this).proccessIntent((Intent)localObject);
                if (localObject != null)
                {
                  if (!(localObject instanceof MiPushMessage)) {
                    break label183;
                  }
                  localObject = (MiPushMessage)localObject;
                  if (!((MiPushMessage)localObject).isArrivedMessage()) {
                    paramIntent.onReceiveMessage(this, (MiPushMessage)localObject);
                  }
                  if (((MiPushMessage)localObject).getPassThrough() != 1) {
                    break label162;
                  }
                  paramIntent.onReceivePassThroughMessage(this, (MiPushMessage)localObject);
                  return;
                }
                break;
              }
            }
            catch (RuntimeException paramIntent)
            {
              MyLog.e(paramIntent);
              return;
            }
          }
          localObject = (MiPushCommandMessage)((Intent)localObject).getSerializableExtra("key_command");
          paramIntent.onCommandResult(this, (MiPushCommandMessage)localObject);
        } while (!TextUtils.equals(((MiPushCommandMessage)localObject).getCommand(), "register"));
        paramIntent.onReceiveRegisterResult(this, (MiPushCommandMessage)localObject);
        return;
        if (((MiPushMessage)localObject).isNotified())
        {
          paramIntent.onNotificationMessageClicked(this, (MiPushMessage)localObject);
          return;
        }
        paramIntent.onNotificationMessageArrived(this, (MiPushMessage)localObject);
        return;
      } while (!(localObject instanceof MiPushCommandMessage));
      localObject = (MiPushCommandMessage)localObject;
      paramIntent.onCommandResult(this, (MiPushCommandMessage)localObject);
    } while (!TextUtils.equals(((MiPushCommandMessage)localObject).getCommand(), "register"));
    paramIntent.onReceiveRegisterResult(this, (MiPushCommandMessage)localObject);
    return;
  }
  
  public static class MessageHandleJob
  {
    private Intent intent;
    private PushMessageReceiver receiver;
    
    public MessageHandleJob(Intent paramIntent, PushMessageReceiver paramPushMessageReceiver)
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
}

/* Location:
 * Qualified Name:     com.xiaomi.mipush.sdk.MessageHandleService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */