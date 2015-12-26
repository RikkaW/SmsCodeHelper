package com.android.mms.update;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.ThreadPool;
import com.android.mms.util.XiaomiAccount;
import com.xiaomi.mipush.sdk.MiPushClient;
import miui.os.Build;

public class Push
{
  public static String DEFAULT_TOPIC = "template-sms-dev";
  
  static
  {
    if (Build.IS_ALPHA_BUILD)
    {
      DEFAULT_TOPIC = "template-sms-alpha";
      return;
    }
  }
  
  public static void initiate(Context paramContext)
  {
    if (MessageUtils.allowPush())
    {
      Log.d("Mms_Push", "push initiate");
      MiPushClient.registerPush(paramContext.getApplicationContext(), "2882303761517322150", "5921732233150");
    }
  }
  
  public static void onRegisterSucceeded(Context paramContext)
  {
    ThreadPool.execute(new Runnable()
    {
      public void run()
      {
        Push.subscribeDefaultTopics(val$context);
        Push.setAliasByUserId(val$context, null);
      }
    });
  }
  
  public static void setAliasByUserId(Context paramContext, Account paramAccount)
  {
    Account localAccount = paramAccount;
    if (paramAccount == null) {
      localAccount = XiaomiAccount.getAccount(paramContext);
    }
    if ((localAccount != null) && (!TextUtils.isEmpty(name))) {}
    try
    {
      MiPushClient.setAlias(paramContext, name, null);
      return;
    }
    catch (NullPointerException paramContext)
    {
      Log.d("Mms_Push", "mipush sdk is throwing NPE!");
      paramContext.printStackTrace();
    }
  }
  
  public static void subscribeDefaultTopics(Context paramContext)
  {
    try
    {
      MiPushClient.subscribe(paramContext, DEFAULT_TOPIC, null);
      return;
    }
    catch (NullPointerException paramContext)
    {
      Log.e("Mms_Push", "Mipush sdk is throwing NPE! ");
      paramContext.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.update.Push
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */