package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.packet.IQ;
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.Presence;
import java.util.Collection;
import java.util.Iterator;

public class ClientEventDispatcher
{
  private MIPushEventProcessor mPushEventProcessor = new MIPushEventProcessor();
  
  public static String getReceiverPermission(String paramString)
  {
    return paramString + ".permission.MIPUSH_RECEIVE";
  }
  
  private static void sendBroadcast(Context paramContext, Intent paramIntent, String paramString)
  {
    if ("com.xiaomi.xmsf".equals(paramContext.getPackageName()))
    {
      paramContext.sendBroadcast(paramIntent);
      return;
    }
    paramContext.sendBroadcast(paramIntent, getReceiverPermission(paramString));
  }
  
  PushClientsManager.ClientLoginInfo getClientLoginInfo(Packet paramPacket)
  {
    Object localObject = PushClientsManager.getInstance().getAllClientLoginInfoByChid(paramPacket.getChannelId());
    if (((Collection)localObject).isEmpty()) {}
    PushClientsManager.ClientLoginInfo localClientLoginInfo;
    do
    {
      Iterator localIterator;
      while (!localIterator.hasNext())
      {
        return null;
        localIterator = ((Collection)localObject).iterator();
        if (((Collection)localObject).size() == 1) {
          return (PushClientsManager.ClientLoginInfo)localIterator.next();
        }
        localObject = paramPacket.getFrom();
        paramPacket = paramPacket.getTo();
      }
      localClientLoginInfo = (PushClientsManager.ClientLoginInfo)localIterator.next();
    } while ((!TextUtils.equals((CharSequence)localObject, userId)) && (!TextUtils.equals(paramPacket, userId)));
    return localClientLoginInfo;
  }
  
  public void notifyChannelClosed(Context paramContext, PushClientsManager.ClientLoginInfo paramClientLoginInfo, int paramInt)
  {
    if ("5".equalsIgnoreCase(chid)) {
      return;
    }
    Intent localIntent = new Intent();
    localIntent.setAction("com.xiaomi.push.channel_closed");
    localIntent.setPackage(pkgName);
    localIntent.putExtra(PushConstants.EXTRA_CHANNEL_ID, chid);
    localIntent.putExtra("ext_reason", paramInt);
    localIntent.putExtra(PushConstants.EXTRA_USER_ID, userId);
    localIntent.putExtra(PushConstants.EXTRA_SESSION, session);
    sendBroadcast(paramContext, localIntent, pkgName);
  }
  
  public void notifyChannelOpenResult(Context paramContext, PushClientsManager.ClientLoginInfo paramClientLoginInfo, boolean paramBoolean, int paramInt, String paramString)
  {
    if ("5".equalsIgnoreCase(chid))
    {
      mPushEventProcessor.processChannelOpenResult(paramContext, paramClientLoginInfo, paramBoolean, paramInt, paramString);
      return;
    }
    Intent localIntent = new Intent();
    localIntent.setAction("com.xiaomi.push.channel_opened");
    localIntent.setPackage(pkgName);
    localIntent.putExtra("ext_succeeded", paramBoolean);
    if (!paramBoolean) {
      localIntent.putExtra("ext_reason", paramInt);
    }
    if (!TextUtils.isEmpty(paramString)) {
      localIntent.putExtra("ext_reason_msg", paramString);
    }
    localIntent.putExtra("ext_chid", chid);
    localIntent.putExtra(PushConstants.EXTRA_USER_ID, userId);
    localIntent.putExtra(PushConstants.EXTRA_SESSION, session);
    sendBroadcast(paramContext, localIntent, pkgName);
  }
  
  public void notifyKickedByServer(Context paramContext, PushClientsManager.ClientLoginInfo paramClientLoginInfo, String paramString1, String paramString2)
  {
    if ("5".equalsIgnoreCase(chid))
    {
      MyLog.e("mipush kicked by server");
      return;
    }
    Intent localIntent = new Intent();
    localIntent.setAction("com.xiaomi.push.kicked");
    localIntent.setPackage(pkgName);
    localIntent.putExtra("ext_kick_type", paramString1);
    localIntent.putExtra("ext_kick_reason", paramString2);
    localIntent.putExtra("ext_chid", chid);
    localIntent.putExtra(PushConstants.EXTRA_USER_ID, userId);
    localIntent.putExtra(PushConstants.EXTRA_SESSION, session);
    sendBroadcast(paramContext, localIntent, pkgName);
  }
  
  public void notifyPacketArrival(XMPushService paramXMPushService, String paramString, Packet paramPacket)
  {
    PushClientsManager.ClientLoginInfo localClientLoginInfo = getClientLoginInfo(paramPacket);
    if (localClientLoginInfo == null)
    {
      MyLog.e("error while notify channel closed! channel " + paramString + " not registered");
      return;
    }
    if ("5".equalsIgnoreCase(paramString))
    {
      mPushEventProcessor.processNewPacket(paramXMPushService, paramPacket, localClientLoginInfo);
      return;
    }
    String str2 = pkgName;
    String str1;
    if ((paramPacket instanceof Message)) {
      str1 = "com.xiaomi.push.new_msg";
    }
    for (;;)
    {
      Intent localIntent = new Intent();
      localIntent.setAction(str1);
      localIntent.setPackage(str2);
      localIntent.putExtra("ext_chid", paramString);
      localIntent.putExtra("ext_packet", paramPacket.toBundle());
      localIntent.putExtra(PushConstants.EXTRA_SESSION, session);
      localIntent.putExtra(PushConstants.EXTRA_SECURITY, security);
      sendBroadcast(paramXMPushService, localIntent, str2);
      return;
      if ((paramPacket instanceof IQ))
      {
        str1 = "com.xiaomi.push.new_iq";
      }
      else
      {
        if (!(paramPacket instanceof Presence)) {
          break;
        }
        str1 = "com.xiaomi.push.new_pres";
      }
    }
    MyLog.e("unknown packet type, drop it");
  }
  
  public void notifyServiceStarted(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("com.xiaomi.push.service_started");
    paramContext.sendBroadcast(localIntent);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.ClientEventDispatcher
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */