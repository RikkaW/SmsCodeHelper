package com.xiaomi.mipush.sdk;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.push.service.PushProvision;

class PushServiceClient$1
  extends ContentObserver
{
  PushServiceClient$1(PushServiceClient paramPushServiceClient, Handler paramHandler)
  {
    super(paramHandler);
  }
  
  public void onChange(boolean paramBoolean)
  {
    PushServiceClient.access$002(this$0, Integer.valueOf(PushProvision.getInstance(PushServiceClient.access$100(this$0)).getProvisioned()));
    if (PushServiceClient.access$000(this$0).intValue() != 0)
    {
      PushServiceClient.access$100(this$0).getContentResolver().unregisterContentObserver(this);
      if (Network.hasNetwork(PushServiceClient.access$100(this$0))) {
        this$0.processRegisterTask();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mipush.sdk.PushServiceClient.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */