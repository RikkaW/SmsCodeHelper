package com.xiaomi.push.service;

import android.text.TextUtils;
import android.util.Base64;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostManager;
import com.xiaomi.push.log.LogUploader;
import com.xiaomi.push.protobuf.ChannelMessage.PushServiceConfigMsg;
import com.xiaomi.push.thrift.ChannelStatsType;
import com.xiaomi.smack.ConnectionConfiguration;
import com.xiaomi.smack.XMBinder.BindResult;
import com.xiaomi.smack.XMBinder.BindResult.Type;
import com.xiaomi.smack.XMPPConnection;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.IQ;
import com.xiaomi.smack.packet.IQ.Type;
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.XMPPError;
import com.xiaomi.smack.util.TrafficUtils;
import com.xiaomi.stats.StatsHelper;
import java.util.Date;

public class PacketSync
{
  private XMPushService mService;
  
  PacketSync(XMPushService paramXMPushService)
  {
    mService = paramXMPushService;
  }
  
  private void dispatchNetFlow(Packet paramPacket)
  {
    Object localObject = paramPacket.getTo();
    String str = paramPacket.getChannelId();
    if ((!TextUtils.isEmpty((CharSequence)localObject)) && (!TextUtils.isEmpty(str)))
    {
      localObject = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(str, (String)localObject);
      if (localObject != null) {
        TrafficUtils.distributionTraffic(mService, pkgName, TrafficUtils.getTrafficFlow(paramPacket.toXML()), true, System.currentTimeMillis());
      }
    }
  }
  
  private void processRedirectMessage(CommonPacketExtension paramCommonPacketExtension)
  {
    paramCommonPacketExtension = paramCommonPacketExtension.getText();
    if (!TextUtils.isEmpty(paramCommonPacketExtension))
    {
      paramCommonPacketExtension = paramCommonPacketExtension.split(";");
      Fallback localFallback = HostManager.getInstance().getFallbacksByHost(ConnectionConfiguration.getXmppServerHost(), false);
      if ((localFallback != null) && (paramCommonPacketExtension.length > 0))
      {
        localFallback.addPreferredHost(paramCommonPacketExtension);
        mService.disconnect(20, null);
        mService.scheduleConnect(true);
      }
    }
  }
  
  public void onPacketReceive(Packet paramPacket)
  {
    if (!"5".equals(paramPacket.getChannelId())) {
      dispatchNetFlow(paramPacket);
    }
    Object localObject3;
    Object localObject4;
    Object localObject1;
    if ((paramPacket instanceof XMBinder.BindResult))
    {
      localObject3 = (XMBinder.BindResult)paramPacket;
      localObject4 = ((XMBinder.BindResult)localObject3).getType();
      paramPacket = ((XMBinder.BindResult)localObject3).getChannelId();
      localObject1 = ((XMBinder.BindResult)localObject3).getTo();
      if (!TextUtils.isEmpty(paramPacket)) {}
    }
    label956:
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return;
              localObject2 = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(paramPacket, (String)localObject1);
            } while (localObject2 == null);
            if (localObject4 == XMBinder.BindResult.Type.RESULT)
            {
              ((PushClientsManager.ClientLoginInfo)localObject2).setStatus(PushClientsManager.ClientStatus.binded, 1, 0, null, null);
              MyLog.warn("SMACK: channel bind succeeded, chid=" + paramPacket);
              return;
            }
            localObject3 = ((XMBinder.BindResult)localObject3).getError();
            MyLog.warn("SMACK: channel bind failed, error=" + ((XMPPError)localObject3).toXML());
          } while (localObject3 == null);
          if ("auth".equals(((XMPPError)localObject3).getType()))
          {
            if ("invalid-sig".equals(((XMPPError)localObject3).getReason()))
            {
              MyLog.warn("SMACK: bind error invalid-sig token = " + token + " sec = " + security);
              StatsHelper.stats(0, ChannelStatsType.BIND_INVALID_SIG.getValue(), 1, null);
            }
            ((PushClientsManager.ClientLoginInfo)localObject2).setStatus(PushClientsManager.ClientStatus.unbind, 1, 5, ((XMPPError)localObject3).getReason(), ((XMPPError)localObject3).getType());
            PushClientsManager.getInstance().deactivateClient(paramPacket, (String)localObject1);
          }
          for (;;)
          {
            MyLog.warn("SMACK: channel bind failed, chid=" + paramPacket + " reason=" + ((XMPPError)localObject3).getReason());
            return;
            if ("cancel".equals(((XMPPError)localObject3).getType()))
            {
              ((PushClientsManager.ClientLoginInfo)localObject2).setStatus(PushClientsManager.ClientStatus.unbind, 1, 7, ((XMPPError)localObject3).getReason(), ((XMPPError)localObject3).getType());
              PushClientsManager.getInstance().deactivateClient(paramPacket, (String)localObject1);
            }
            else if ("wait".equals(((XMPPError)localObject3).getType()))
            {
              mService.scheduleRebindChannel((PushClientsManager.ClientLoginInfo)localObject2);
              ((PushClientsManager.ClientLoginInfo)localObject2).setStatus(PushClientsManager.ClientStatus.unbind, 1, 7, ((XMPPError)localObject3).getReason(), ((XMPPError)localObject3).getType());
            }
          }
          localObject2 = paramPacket.getChannelId();
          localObject1 = localObject2;
          if (TextUtils.isEmpty((CharSequence)localObject2))
          {
            localObject1 = "1";
            paramPacket.setChannelId("1");
          }
          if (!((String)localObject1).equals("0")) {
            break;
          }
        } while (!(paramPacket instanceof IQ));
        localObject1 = (IQ)paramPacket;
        if (("0".equals(paramPacket.getPacketID())) && ("result".equals(((IQ)localObject1).getType().toString())))
        {
          paramPacket = mService.getCurrentConnection();
          if ((paramPacket instanceof XMPPConnection)) {
            ((XMPPConnection)paramPacket).updateLastReceived();
          }
          StatsHelper.pingEnded();
        }
        while (((IQ)localObject1).getAttribute("ps") != null) {
          try
          {
            paramPacket = ChannelMessage.PushServiceConfigMsg.parseFrom(Base64.decode(((IQ)localObject1).getAttribute("ps"), 8));
            ServiceConfig.getInstance().handle(paramPacket);
            return;
          }
          catch (IllegalArgumentException paramPacket)
          {
            MyLog.warn("invalid Base64 exception + " + paramPacket.getMessage());
            return;
            if ("command".equals(((IQ)localObject1).getType().toString()))
            {
              localObject2 = paramPacket.getExtension("u");
              if (localObject2 != null)
              {
                paramPacket = ((CommonPacketExtension)localObject2).getAttributeValue("url");
                localObject3 = ((CommonPacketExtension)localObject2).getAttributeValue("startts");
                localObject4 = ((CommonPacketExtension)localObject2).getAttributeValue("endts");
                try
                {
                  localObject3 = new Date(Long.parseLong((String)localObject3));
                  localObject4 = new Date(Long.parseLong((String)localObject4));
                  String str = ((CommonPacketExtension)localObject2).getAttributeValue("token");
                  boolean bool = "true".equals(((CommonPacketExtension)localObject2).getAttributeValue("force"));
                  localObject2 = ((CommonPacketExtension)localObject2).getAttributeValue("maxlen");
                  int i = 0;
                  if (!TextUtils.isEmpty((CharSequence)localObject2)) {
                    i = Integer.parseInt((String)localObject2) * 1024;
                  }
                  LogUploader.getInstance(mService).upload(paramPacket, str, (Date)localObject4, (Date)localObject3, i, bool);
                }
                catch (NumberFormatException paramPacket)
                {
                  MyLog.warn("parseLong fail " + paramPacket.getMessage());
                }
              }
            }
          }
          catch (InvalidProtocolBufferMicroException paramPacket)
          {
            MyLog.warn("invalid pb exception + " + paramPacket.getMessage());
            return;
          }
        }
        if (!(paramPacket instanceof IQ)) {
          break label956;
        }
        localObject2 = paramPacket.getExtension("kick");
        if (localObject2 == null) {
          break label1002;
        }
        localObject3 = paramPacket.getTo();
        paramPacket = ((CommonPacketExtension)localObject2).getAttributeValue("type");
        localObject2 = ((CommonPacketExtension)localObject2).getAttributeValue("reason");
        MyLog.warn("kicked by server, chid=" + (String)localObject1 + " userid=" + (String)localObject3 + " type=" + paramPacket + " reason=" + (String)localObject2);
        if (!"wait".equals(paramPacket)) {
          break;
        }
        localObject1 = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId((String)localObject1, (String)localObject3);
      } while (localObject1 == null);
      mService.scheduleRebindChannel((PushClientsManager.ClientLoginInfo)localObject1);
      ((PushClientsManager.ClientLoginInfo)localObject1).setStatus(PushClientsManager.ClientStatus.unbind, 3, 0, (String)localObject2, paramPacket);
      return;
      mService.closeChannel((String)localObject1, (String)localObject3, 3, (String)localObject2, paramPacket);
      PushClientsManager.getInstance().deactivateClient((String)localObject1, (String)localObject3);
      return;
      if (!(paramPacket instanceof Message)) {
        break;
      }
      Object localObject2 = (Message)paramPacket;
      if (!"redir".equals(((Message)localObject2).getType())) {
        break;
      }
      paramPacket = ((Message)localObject2).getExtension("hosts");
    } while (paramPacket == null);
    processRedirectMessage(paramPacket);
    return;
    label1002:
    mService.getClientEventDispatcher().notifyPacketArrival(mService, (String)localObject1, paramPacket);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.PacketSync
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */