package com.android.mms.transaction;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import com.android.mms.transaction.flyme.SipSmsReceiverService;
import com.android.mms.transaction.sns.SnsSmsReceiverService;

public class SmsReceiver
  extends BroadcastReceiver
{
  static final Object a = new Object();
  static PowerManager.WakeLock b;
  private static SmsReceiver c;
  
  public static SmsReceiver a()
  {
    if (c == null) {
      c = new SmsReceiver();
    }
    return c;
  }
  
  public static void a(Service paramService, int paramInt)
  {
    synchronized (a)
    {
      if ((b != null) && (paramService.stopSelfResult(paramInt))) {
        b.release();
      }
      return;
    }
  }
  
  public static void a(Context paramContext, Intent paramIntent)
  {
    synchronized (a)
    {
      if (b == null)
      {
        b = ((PowerManager)paramContext.getSystemService("power")).newWakeLock(1, "StartingAlertService");
        b.setReferenceCounted(false);
      }
      b.acquire();
      paramContext.startService(paramIntent);
      return;
    }
  }
  
  protected void a(Context paramContext, Intent paramIntent, boolean paramBoolean)
  {
    int j = 0;
    boolean bool = paramIntent.getAction().equals("android.provider.Telephony.SIP_SMS_RECEIVED");
    if ((!paramBoolean) && ((paramIntent.getAction().equals("android.provider.Telephony.SMS_DELIVER")) || (paramIntent.getAction().equals("android.intent.action.DATA_SMS_RECEIVED")))) {
      Log.d("SmsReceiver", "onReceiveWithPrivilege: ignored privileged = " + paramBoolean + ", intent " + paramIntent);
    }
    int i;
    int k;
    do
    {
      return;
      if ((!bool) && (!paramIntent.getAction().equals("android.provider.Telephony.SIP_SMS_STATUS_RECEIVED")) && (!paramIntent.getAction().equals("com.android.mms.transaction.SIP_MESSAGE_SENT")) && (!paramIntent.getAction().equals("com.android.mms.transaction.SEND_SIP_MESSAGE"))) {
        break label272;
      }
      i = 1;
      if ((paramIntent.getAction().equals("android.provider.Telephony.SNS_SMS_RECEIVED")) || (paramIntent.getAction().equals("com.android.mms.transaction.SNS_MESSAGE_SENT")) || (paramIntent.getAction().equals("com.android.mms.transaction.SEND_SNS_MESSAGE"))) {
        j = 1;
      }
      if ((i != 0) || (j != 0) || (!paramIntent.getAction().equals("android.intent.action.DATA_SMS_RECEIVED"))) {
        break;
      }
      k = paramIntent.getData().getPort();
      paramIntent.putExtra("port", k);
      Log.d("SmsReceiver", "procReceivedPortDataSms: received data sms from port " + k);
    } while (k != 8700);
    if (j != 0)
    {
      Log.d("MMS_APP", "procReceivedPortDataSms: received a sns type msg");
      paramIntent.setClass(paramContext, SnsSmsReceiverService.class);
    }
    for (;;)
    {
      paramIntent.putExtra("result", getResultCode());
      a(paramContext, paramIntent);
      return;
      label272:
      i = 0;
      break;
      if (i != 0)
      {
        Log.d("MMS_APP", "procReceivedPortDataSms: received a sip type msg");
        paramIntent.setClass(paramContext, SipSmsReceiverService.class);
      }
      else
      {
        Log.d("MMS_APP", "procReceivedPortDataSms: received a gsm msg");
        paramIntent.setClass(paramContext, SmsReceiverService.class);
      }
    }
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    a(paramContext, paramIntent, false);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.SmsReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */