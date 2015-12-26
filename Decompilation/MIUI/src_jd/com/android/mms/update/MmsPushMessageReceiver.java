package com.android.mms.update;

import android.content.Context;
import android.util.Log;
import com.android.mms.util.MiStatSdkHelper;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class MmsPushMessageReceiver
  extends PushMessageReceiver
{
  private static final String TAG = "MmsPushMessageReceiver";
  
  private long getRandomDelay(int paramInt)
  {
    return new Random().nextInt((int)TimeUnit.HOURS.toMillis(paramInt));
  }
  
  public void onCommandResult(Context paramContext, MiPushCommandMessage paramMiPushCommandMessage)
  {
    String str = paramMiPushCommandMessage.getCommand();
    Log.v("MmsPushMessageReceiver", "onCommandResult, command:" + paramMiPushCommandMessage);
    if ("register".equals(str)) {
      if (paramMiPushCommandMessage.getResultCode() == 0L) {
        Push.onRegisterSucceeded(paramContext);
      }
    }
    do
    {
      return;
      Log.e("MmsPushMessageReceiver", "failed to register");
      return;
      if ("set-alias".equals(str))
      {
        if (paramMiPushCommandMessage.getResultCode() == 0L)
        {
          Log.d("MmsPushMessageReceiver", "successfully set alias");
          return;
        }
        Log.d("MmsPushMessageReceiver", "failed to set alias");
        return;
      }
      if ("unset-alias".equals(str))
      {
        if (paramMiPushCommandMessage.getResultCode() == 0L)
        {
          Log.d("MmsPushMessageReceiver", "successfully unset alias");
          return;
        }
        Log.d("MmsPushMessageReceiver", "failed to unset alias");
        return;
      }
      if ("subscribe-topic".equals(str))
      {
        if (paramMiPushCommandMessage.getResultCode() == 0L)
        {
          Log.d("MmsPushMessageReceiver", "successfully subscribed topic: ");
          return;
        }
        Log.e("MmsPushMessageReceiver", "failed to subscribe topic:" + paramMiPushCommandMessage.getCommandArguments());
        return;
      }
    } while (!"unsubscibe-topic".equals(str));
    if (paramMiPushCommandMessage.getResultCode() == 0L)
    {
      paramContext = (String)paramMiPushCommandMessage.getCommandArguments().get(0);
      Log.d("MmsPushMessageReceiver", "successfully unsubscribed topic:" + paramContext);
      return;
    }
    Log.e("MmsPushMessageReceiver", "failed to unsubscribe topic:" + paramMiPushCommandMessage.getCommandArguments());
  }
  
  public void onReceiveMessage(Context paramContext, MiPushMessage paramMiPushMessage)
  {
    Log.d("MmsPushMessageReceiver", ":::onReceiveMessage: ");
  }
  
  public void onReceivePassThroughMessage(Context paramContext, MiPushMessage paramMiPushMessage)
  {
    Log.d("MmsPushMessageReceiver", ":::onReceivePassThroughMessage: ");
    paramContext = paramMiPushMessage.getContent();
    long l2 = 0L;
    boolean bool4 = true;
    boolean bool3 = false;
    boolean bool5 = false;
    boolean bool2 = bool3;
    long l1 = l2;
    boolean bool1 = bool4;
    try
    {
      paramMiPushMessage = new JSONObject(paramContext);
      bool2 = bool3;
      l1 = l2;
      bool1 = bool4;
      l2 = paramMiPushMessage.optLong("version", 0L);
      bool2 = bool3;
      l1 = l2;
      bool1 = bool4;
      bool4 = paramMiPushMessage.optBoolean("wifiOnly", true);
      bool2 = bool3;
      l1 = l2;
      bool1 = bool4;
      bool3 = paramMiPushMessage.optBoolean("forced", false);
      bool2 = bool3;
      l1 = l2;
      bool1 = bool4;
      boolean bool6 = paramMiPushMessage.optBoolean("guideUser", false);
      bool2 = bool6;
      bool1 = bool4;
      l1 = l2;
      bool4 = bool2;
      bool2 = bool3;
    }
    catch (JSONException paramMiPushMessage)
    {
      do
      {
        for (;;)
        {
          paramMiPushMessage.printStackTrace();
          bool4 = bool5;
        }
        Log.v("MmsPushMessageReceiver", " Not allowing update, wifiOnly is: " + bool1);
        MiStatSdkHelper.recordPushmessageStatus(2);
      } while (!bool4);
      Log.v("MmsPushMessageReceiver", " guide user to allow");
      return;
    }
    l2 = TemplateRequest.getLocalVersion();
    if (l1 > l2) {
      if (Network.allowUpdate(bool1, bool2))
      {
        l1 = getRandomDelay(2);
        Log.d("MmsPushMessageReceiver", "delay " + l1 + " milli secs");
        l2 = System.currentTimeMillis();
        MiStatSdkHelper.recordPushmessageStatus(0);
        UpdateManager.requestUpdateAt(l1 + l2, paramContext);
        return;
      }
    }
    MiStatSdkHelper.recordPushmessageStatus(1);
    Log.w("MmsPushMessageReceiver", " pushed version: " + l1 + " is not newer than local: " + l2);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.update.MmsPushMessageReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */