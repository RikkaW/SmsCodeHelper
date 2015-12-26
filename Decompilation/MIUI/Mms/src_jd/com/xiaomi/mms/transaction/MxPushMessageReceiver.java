package com.xiaomi.mms.transaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.mms.transaction.MxStatusService;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.utils.MxMessageLogicHelper;
import com.xiaomi.mms.utils.logger.MyLog;
import miui.push.CommonPacketExtension;
import miui.push.Message;
import miui.push.Presence;
import miui.push.PushConstants;

public class MxPushMessageReceiver
  extends BroadcastReceiver
{
  private static long mKickCountStartTime = 0L;
  private static int mKickedCount;
  private static int mTokenInvalidCount = 0;
  
  static
  {
    mKickedCount = 0;
  }
  
  private void updateKickedCount()
  {
    if (System.currentTimeMillis() - mKickCountStartTime > 600000L)
    {
      mKickedCount = 0;
      mKickCountStartTime = System.currentTimeMillis();
    }
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Object localObject1 = null;
    Object localObject2 = paramIntent.getAction();
    if (!TextUtils.equals(paramIntent.getStringExtra("ext_chid"), "3")) {}
    int i;
    label1044:
    do
    {
      do
      {
        do
        {
          do
          {
            for (;;)
            {
              return;
              updateKickedCount();
              if (!"com.xiaomi.push.new_msg".equals(localObject2)) {
                break;
              }
              localObject2 = new Message(paramIntent.getBundleExtra("ext_packet"));
              paramIntent = ((Message)localObject2).getBody();
              Object localObject3 = ((Message)localObject2).getBodyEncoding();
              Object localObject5 = ((Message)localObject2).getExtension("sent", null);
              Object localObject4 = ((Message)localObject2).getExtension("received", null);
              if (!TextUtils.isEmpty(paramIntent))
              {
                localObject1 = ((Message)localObject2).getExtension("xmthread", null);
                if (localObject1 != null)
                {
                  localObject4 = ((CommonPacketExtension)localObject1).getAttributeValue("type");
                  localObject5 = ((CommonPacketExtension)localObject1).getText();
                  if ((!TextUtils.isEmpty((CharSequence)localObject4)) && (!TextUtils.isEmpty((CharSequence)localObject5)))
                  {
                    CommonPacketExtension localCommonPacketExtension = ((Message)localObject2).getExtension("attachment");
                    localObject1 = new Intent("com.xiaomi.mms.mx.ACTION_HANDLE_MX_RECEIVED");
                    ((Intent)localObject1).putExtra("fromType", (String)localObject4);
                    ((Intent)localObject1).putExtra("address", (String)localObject5);
                    ((Intent)localObject1).putExtra("body", paramIntent);
                    ((Intent)localObject1).putExtra("encoding", (String)localObject3);
                    if (localCommonPacketExtension != null) {
                      ((Intent)localObject1).putExtra("attachment", localCommonPacketExtension.toBundle().getBundle("attributes"));
                    }
                    localObject3 = ((Message)localObject2).getExtension("multi-from", null);
                    paramIntent = (Intent)localObject1;
                    if (localObject3 != null)
                    {
                      paramIntent = (Intent)localObject1;
                      if (!TextUtils.isEmpty(((CommonPacketExtension)localObject3).getText()))
                      {
                        ((Intent)localObject1).putExtra("b2c_number", ((CommonPacketExtension)localObject3).getText());
                        paramIntent = ((Message)localObject2).getExtension("unicast-expire", null);
                        localObject3 = ((Message)localObject2).getExtension("unicast-display", null);
                        if (paramIntent != null) {
                          ((Intent)localObject1).putExtra("b2c_ttl", paramIntent.getText());
                        }
                        paramIntent = (Intent)localObject1;
                        if (localObject3 != null)
                        {
                          ((Intent)localObject1).putExtra("b2c_display_name", ((CommonPacketExtension)localObject3).getText());
                          paramIntent = (Intent)localObject1;
                        }
                      }
                    }
                  }
                }
              }
              while (paramIntent != null)
              {
                paramIntent.putExtra("from", ((Message)localObject2).getFrom());
                paramIntent.putExtra("to", ((Message)localObject2).getTo());
                paramIntent.putExtra("packetId", ((Message)localObject2).getPacketID());
                paramIntent.setPackage(paramContext.getPackageName());
                MxMessageService.beginStartingService(paramContext, paramIntent);
                return;
                MyLog.w("MxPushMessageReceiver", "fromType or address is empty");
                for (;;)
                {
                  paramIntent = null;
                  break;
                  MyLog.w("MxPushMessageReceiver", "receive msg without thread ext");
                }
                if (localObject5 != null)
                {
                  localObject3 = ((CommonPacketExtension)localObject5).getAttributeValue("id");
                  paramIntent = (Intent)localObject1;
                  if (!TextUtils.isEmpty((CharSequence)localObject3))
                  {
                    paramIntent = (Intent)localObject1;
                    if (TextUtils.isDigitsOnly((CharSequence)localObject3))
                    {
                      paramIntent = new Intent("com.xiaomi.mms.mx.ACTION_HANDLE_MX_SENT");
                      paramIntent.putExtra("msgId", Long.parseLong((String)localObject3));
                    }
                  }
                }
                else
                {
                  paramIntent = (Intent)localObject1;
                  if (localObject4 != null)
                  {
                    localObject3 = ((CommonPacketExtension)localObject4).getAttributeValue("id");
                    if ((TextUtils.isEmpty((CharSequence)localObject3)) || (!TextUtils.isDigitsOnly((CharSequence)localObject3)))
                    {
                      MyLog.w("MxPushMessageReceiver", "receive delivered ack with illegal id: " + (String)localObject3);
                      paramIntent = (Intent)localObject1;
                    }
                    else
                    {
                      paramIntent = new Intent("com.xiaomi.mms.mx.ACTION_HANDLE_MX_DELIVERED");
                      paramIntent.putExtra("msgId", Long.parseLong((String)localObject3));
                      if (((Message)localObject2).getError() != null) {
                        paramIntent.putExtra("error", true);
                      } else {
                        mKickedCount = 0;
                      }
                    }
                  }
                }
              }
            }
            if ("com.xiaomi.push.new_pres".equals(localObject2))
            {
              localObject1 = new Presence(paramIntent.getBundleExtra("ext_packet"));
              boolean bool = ((Presence)localObject1).isAvailable();
              localObject2 = MxMessageLogicHelper.getSimpleMid(((Presence)localObject1).getFrom());
              paramIntent = new Intent("com.xiaomi.mms.mx.ACTION_HANDLE_PRESENCE");
              paramIntent.putExtra("from", ((Presence)localObject1).getFrom());
              paramIntent.putExtra("to", ((Presence)localObject1).getTo());
              paramIntent.putExtra("packetId", ((Presence)localObject1).getPacketID());
              paramIntent.putExtra("mid", (String)localObject2);
              paramIntent.putExtra("available", bool);
              localObject1 = ((Presence)localObject1).getExtension("client_attrs");
              if (localObject1 != null) {
                paramIntent.putExtra("client_attrs", ((CommonPacketExtension)localObject1).getText());
              }
              paramIntent.setPackage(paramContext.getPackageName());
              MxMessageService.beginStartingService(paramContext, paramIntent);
              return;
            }
            if ("com.xiaomi.push.channel_closed".equals(localObject2))
            {
              paramIntent = paramIntent.getStringExtra(PushConstants.EXTRA_USER_ID);
              if (paramIntent == null)
              {
                MyLog.e("MxPushMessageReceiver", "receive channel close packet without toId");
                return;
              }
              localObject1 = PushSession.getInstance(paramContext);
              i = ((PushSession)localObject1).getSimIdByMid(MxMessageLogicHelper.getSimpleMid(paramIntent));
              if (i < 0)
              {
                ((PushSession)localObject1).resetAllStatus(paramContext, PushSession.Status.DISCONNECTED);
                MyLog.w("MxPushMessageReceiver", "simIndex not ready for channel close event");
                return;
              }
              ((PushSession)localObject1).setStatus(paramContext, i, PushSession.Status.DISCONNECTED);
              return;
            }
            if (!"com.xiaomi.push.channel_opened".equals(localObject2)) {
              break label1044;
            }
            localObject1 = paramIntent.getStringExtra(PushConstants.EXTRA_USER_ID);
            if (localObject1 == null)
            {
              MyLog.e("MxPushMessageReceiver", "receive channel open without toId");
              return;
            }
            localObject2 = PushSession.getInstance(paramContext);
            i = ((PushSession)localObject2).getSimIdByMid(MxMessageLogicHelper.getSimpleMid((String)localObject1));
            if (i < 0)
            {
              MyLog.w("MxPushMessageReceiver", "simIndex not ready for channel open event");
              ((PushSession)localObject2).resetAllStatus(paramContext, PushSession.Status.DISCONNECTED);
              return;
            }
            if (!paramIntent.getBooleanExtra("ext_succeeded", false)) {
              break;
            }
          } while (((PushSession)localObject2).getStatus(i) == PushSession.Status.CONNECTED);
          ((PushSession)localObject2).setStatus(paramContext, i, PushSession.Status.CONNECTED);
          mTokenInvalidCount = 0;
          MxTaskService.queryPendingAddresses(paramContext);
          if (mKickedCount < 3)
          {
            paramIntent = new Intent(paramContext, MxResendService.class);
            paramIntent.putExtra(MSimUtils.SLOT_ID, i);
            paramContext.startService(paramIntent);
          }
          MxStatusService.queryPendingPresence(paramContext);
          return;
          ((PushSession)localObject2).setStatus(paramContext, i, PushSession.Status.DISCONNECTED);
          paramIntent = paramIntent.getStringExtra("ext_reason_msg");
        } while ((!"invalid-sig".equals(paramIntent)) && (!"invalid-token".equals(paramIntent)) && (!"token-expired".equals(paramIntent)));
        mTokenInvalidCount += 1;
        MxActivateService.invalidMxToken(paramContext, i);
        if (mTokenInvalidCount < 3)
        {
          MxActivateService.enableMx(paramContext, i, true, false);
          return;
        }
        MyLog.w("MxPushMessageReceiver", "max token try time reaches, abort try");
        return;
        if ("com.xiaomi.push.service_started".equals(localObject2))
        {
          MxActivateService.enableAll(paramContext, false);
          return;
        }
      } while (!"com.xiaomi.push.kicked".equals(localObject2));
      mKickedCount += 1;
      localObject2 = paramIntent.getStringExtra(PushConstants.EXTRA_USER_ID);
      if (localObject2 == null)
      {
        MyLog.e("MxPushMessageReceiver", "kicked by server without toId");
        return;
      }
      localObject1 = PushSession.getInstance(paramContext);
      i = ((PushSession)localObject1).getSimIdByMid(MxMessageLogicHelper.getSimpleMid((String)localObject2));
      localObject2 = paramIntent.getStringExtra("ext_kick_type");
      paramIntent = paramIntent.getStringExtra("ext_kick_reason");
      MyLog.d("MxPushMessageReceiver", "kicked by server: " + (String)localObject2 + ", " + "reason: " + paramIntent);
      if (("auth".equals(localObject2)) || (("modify".equals(localObject2)) && ("invalid-pid".equals(paramIntent))))
      {
        if (i < 0)
        {
          ((PushSession)localObject1).resetAllStatus(paramContext, PushSession.Status.DISCONNECTED);
          MxActivateService.enableAll(paramContext, false);
          return;
        }
        ((PushSession)localObject1).setStatus(paramContext, i, PushSession.Status.DISCONNECTED);
        MxActivateService.enableMx(paramContext, i, true, false);
        return;
      }
    } while ((!"wait".equals(localObject2)) && (!"cancel".equals(localObject2)));
    if (i < 0)
    {
      ((PushSession)localObject1).resetAllStatus(paramContext, PushSession.Status.DISCONNECTED);
      return;
    }
    ((PushSession)localObject1).setStatus(paramContext, i, PushSession.Status.DISCONNECTED);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.MxPushMessageReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */