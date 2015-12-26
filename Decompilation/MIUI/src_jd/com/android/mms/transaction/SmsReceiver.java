package com.android.mms.transaction;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import com.xiaomi.mms.utils.logger.MyLog;

public class SmsReceiver
  extends BroadcastReceiver
{
  static PowerManager.WakeLock mStartingService;
  static final Object mStartingServiceSync = new Object();
  private static SmsReceiver sInstance;
  
  public static void beginStartingService(Context paramContext, Intent paramIntent)
  {
    synchronized (mStartingServiceSync)
    {
      if (mStartingService == null)
      {
        mStartingService = ((PowerManager)paramContext.getSystemService("power")).newWakeLock(1, "StartingAlertService");
        mStartingService.setReferenceCounted(false);
      }
      mStartingService.acquire();
      paramContext.startService(paramIntent);
      return;
    }
  }
  
  public static void finishStartingService(Service paramService, int paramInt)
  {
    synchronized (mStartingServiceSync)
    {
      if ((mStartingService != null) && (paramService.stopSelfResult(paramInt))) {
        mStartingService.release();
      }
      return;
    }
  }
  
  public static SmsReceiver getInstance()
  {
    if (sInstance == null) {
      sInstance = new SmsReceiver();
    }
    return sInstance;
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    onReceiveWithPrivilege(paramContext, paramIntent, false);
  }
  
  protected void onReceiveWithPrivilege(Context paramContext, Intent paramIntent, boolean paramBoolean)
  {
    if ((!paramBoolean) && ("android.provider.Telephony.SMS_DELIVER".equals(paramIntent.getAction()))) {
      return;
    }
    Uri localUri = paramIntent.getData();
    if (localUri != null) {
      MyLog.d("SmsReceiver", "sms ack received, uri: " + localUri + ", result: " + getResultCode());
    }
    paramIntent.setClass(paramContext, SmsReceiverService.class);
    paramIntent.putExtra("result", getResultCode());
    beginStartingService(paramContext, paramIntent);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.SmsReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */