/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  android.util.Base64
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.Date
 */
package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostManager;
import com.xiaomi.push.log.LogUploader;
import com.xiaomi.push.protobuf.ChannelMessage;
import com.xiaomi.push.service.ClientEventDispatcher;
import com.xiaomi.push.service.PushClientsManager;
import com.xiaomi.push.service.ServiceConfig;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.thrift.ChannelStatsType;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.ConnectionConfiguration;
import com.xiaomi.smack.XMBinder;
import com.xiaomi.smack.XMPPConnection;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.IQ;
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.XMPPError;
import com.xiaomi.smack.util.TrafficUtils;
import com.xiaomi.stats.StatsHelper;
import java.util.Date;

public class PacketSync {
    private XMPushService mService;

    PacketSync(XMPushService xMPushService) {
        this.mService = xMPushService;
    }

    private void dispatchNetFlow(Packet packet) {
        Object object = packet.getTo();
        String string2 = packet.getChannelId();
        if (!TextUtils.isEmpty((CharSequence)object) && !TextUtils.isEmpty((CharSequence)string2) && (object = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(string2, (String)object)) != null) {
            TrafficUtils.distributionTraffic(this.mService, object.pkgName, TrafficUtils.getTrafficFlow(packet.toXML()), true, System.currentTimeMillis());
        }
    }

    private void processRedirectMessage(CommonPacketExtension arrstring) {
        if (!TextUtils.isEmpty((CharSequence)(arrstring = arrstring.getText()))) {
            arrstring = arrstring.split(";");
            Fallback fallback = HostManager.getInstance().getFallbacksByHost(ConnectionConfiguration.getXmppServerHost(), false);
            if (fallback != null && arrstring.length > 0) {
                fallback.addPreferredHost(arrstring);
                this.mService.disconnect(20, null);
                this.mService.scheduleConnect(true);
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public void onPacketReceive(Packet object) {
        Object object2;
        if (!"5".equals((Object)object.getChannelId())) {
            this.dispatchNetFlow((Packet)object);
        }
        if (object instanceof XMBinder.BindResult) {
            Object object3 = (XMBinder.BindResult)object;
            XMBinder.BindResult.Type type = object3.getType();
            object = object3.getChannelId();
            String string2 = object3.getTo();
            if (TextUtils.isEmpty((CharSequence)object)) {
                return;
            }
            PushClientsManager.ClientLoginInfo clientLoginInfo = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId((String)object, string2);
            if (clientLoginInfo == null) return;
            if (type == XMBinder.BindResult.Type.RESULT) {
                clientLoginInfo.setStatus(PushClientsManager.ClientStatus.binded, 1, 0, null, null);
                MyLog.warn("SMACK: channel bind succeeded, chid=" + (String)object);
                return;
            }
            object3 = object3.getError();
            MyLog.warn("SMACK: channel bind failed, error=" + object3.toXML());
            if (object3 == null) return;
            if ("auth".equals((Object)object3.getType())) {
                if ("invalid-sig".equals((Object)object3.getReason())) {
                    MyLog.warn("SMACK: bind error invalid-sig token = " + clientLoginInfo.token + " sec = " + clientLoginInfo.security);
                    StatsHelper.stats(0, ChannelStatsType.BIND_INVALID_SIG.getValue(), 1, null);
                }
                clientLoginInfo.setStatus(PushClientsManager.ClientStatus.unbind, 1, 5, object3.getReason(), object3.getType());
                PushClientsManager.getInstance().deactivateClient((String)object, string2);
            } else if ("cancel".equals((Object)object3.getType())) {
                clientLoginInfo.setStatus(PushClientsManager.ClientStatus.unbind, 1, 7, object3.getReason(), object3.getType());
                PushClientsManager.getInstance().deactivateClient((String)object, string2);
            } else if ("wait".equals((Object)object3.getType())) {
                this.mService.scheduleRebindChannel(clientLoginInfo);
                clientLoginInfo.setStatus(PushClientsManager.ClientStatus.unbind, 1, 7, object3.getReason(), object3.getType());
            }
            MyLog.warn("SMACK: channel bind failed, chid=" + (String)object + " reason=" + object3.getReason());
            return;
        }
        Object object4 = object2 = object.getChannelId();
        if (TextUtils.isEmpty((CharSequence)object2)) {
            object4 = "1";
            object.setChannelId("1");
        }
        if (object4.equals((Object)"0")) {
            if (!(object instanceof IQ)) return;
            object4 = (IQ)object;
            if ("0".equals((Object)object.getPacketID()) && "result".equals((Object)object4.getType().toString())) {
                object = this.mService.getCurrentConnection();
                if (object instanceof XMPPConnection) {
                    ((XMPPConnection)object).updateLastReceived();
                }
                StatsHelper.pingEnded();
            } else if ("command".equals((Object)object4.getType().toString()) && (object2 = object.getExtension("u")) != null) {
                object = object2.getAttributeValue("url");
                String string3 = object2.getAttributeValue("startts");
                String string4 = object2.getAttributeValue("endts");
                try {
                    string3 = new Date(Long.parseLong((String)string3));
                    string4 = new Date(Long.parseLong((String)string4));
                    String string5 = object2.getAttributeValue("token");
                    boolean bl = "true".equals((Object)object2.getAttributeValue("force"));
                    object2 = object2.getAttributeValue("maxlen");
                    int n = 0;
                    if (!TextUtils.isEmpty((CharSequence)object2)) {
                        n = Integer.parseInt((String)object2) * 1024;
                    }
                    LogUploader.getInstance((Context)this.mService).upload((String)object, string5, (Date)string4, (Date)string3, n, bl);
                }
                catch (NumberFormatException var1_3) {
                    MyLog.warn("parseLong fail " + var1_3.getMessage());
                }
            }
            if (object4.getAttribute("ps") == null) return;
            try {
                object = ChannelMessage.PushServiceConfigMsg.parseFrom(Base64.decode((String)object4.getAttribute("ps"), (int)8));
                ServiceConfig.getInstance().handle((ChannelMessage.PushServiceConfigMsg)object);
                return;
            }
            catch (IllegalArgumentException var1_2) {
                MyLog.warn("invalid Base64 exception + " + var1_2.getMessage());
                return;
            }
            catch (InvalidProtocolBufferMicroException var1_4) {
                MyLog.warn("invalid pb exception + " + var1_4.getMessage());
                return;
            }
        }
        if (object instanceof IQ) {
            object2 = object.getExtension("kick");
            if (object2 != null) {
                String string6 = object.getTo();
                object = object2.getAttributeValue("type");
                object2 = object2.getAttributeValue("reason");
                MyLog.warn("kicked by server, chid=" + (String)object4 + " userid=" + string6 + " type=" + (String)object + " reason=" + (String)object2);
                if ("wait".equals(object)) {
                    object4 = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId((String)object4, string6);
                    if (object4 == null) return;
                    this.mService.scheduleRebindChannel((PushClientsManager.ClientLoginInfo)object4);
                    object4.setStatus(PushClientsManager.ClientStatus.unbind, 3, 0, (String)object2, (String)object);
                    return;
                }
                this.mService.closeChannel((String)object4, string6, 3, (String)object2, (String)object);
                PushClientsManager.getInstance().deactivateClient((String)object4, string6);
                return;
            }
        } else if (object instanceof Message && "redir".equals((Object)(object2 = (Message)object).getType())) {
            object = object2.getExtension("hosts");
            if (object == null) return;
            this.processRedirectMessage((CommonPacketExtension)object);
            return;
        }
        this.mService.getClientEventDispatcher().notifyPacketArrival(this.mService, (String)object4, (Packet)object);
    }
}

