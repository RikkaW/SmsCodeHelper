package com.xiaomi.mipush.sdk;

import android.content.Context;

final class MiPushClient$1
  implements Runnable
{
  MiPushClient$1(Context paramContext, String paramString1, String paramString2) {}
  
  public void run()
  {
    MiPushClient.initialize(val$context, val$appID, val$appToken, null);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mipush.sdk.MiPushClient.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */