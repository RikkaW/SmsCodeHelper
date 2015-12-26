/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.MIPushEventProcessor;
import com.xiaomi.push.service.PushClientsManager;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.smack.packet.IQ;
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.Presence;
import java.util.Collection;
import java.util.Iterator;

public class ClientEventDispatcher {
    private MIPushEventProcessor mPushEventProcessor = new MIPushEventProcessor();

    public static String getReceiverPermission(String string2) {
        return string2 + ".permission.MIPUSH_RECEIVE";
    }

    private static void sendBroadcast(Context context, Intent intent, String string2) {
        if ("com.xiaomi.xmsf".equals((Object)context.getPackageName())) {
            context.sendBroadcast(intent);
            return;
        }
        context.sendBroadcast(intent, ClientEventDispatcher.getReceiverPermission(string2));
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    PushClientsManager.ClientLoginInfo getClientLoginInfo(Packet object) {
        PushClientsManager.ClientLoginInfo clientLoginInfo;
        Collection<PushClientsManager.ClientLoginInfo> collection = PushClientsManager.getInstance().getAllClientLoginInfoByChid(object.getChannelId());
        if (collection.isEmpty()) {
            return null;
        }
        Iterator<PushClientsManager.ClientLoginInfo> iterator = collection.iterator();
        if (collection.size() == 1) {
            return iterator.next();
        }
        collection = object.getFrom();
        object = object.getTo();
        do {
            if (!iterator.hasNext()) return null;
            clientLoginInfo = iterator.next();
            if (TextUtils.equals((CharSequence)((Object)collection), (CharSequence)clientLoginInfo.userId)) return clientLoginInfo;
        } while (!TextUtils.equals((CharSequence)object, (CharSequence)clientLoginInfo.userId));
        return clientLoginInfo;
    }

    public void notifyChannelClosed(Context context, PushClientsManager.ClientLoginInfo clientLoginInfo, int n) {
        if ("5".equalsIgnoreCase(clientLoginInfo.chid)) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.channel_closed");
        intent.setPackage(clientLoginInfo.pkgName);
        intent.putExtra(PushConstants.EXTRA_CHANNEL_ID, clientLoginInfo.chid);
        intent.putExtra("ext_reason", n);
        intent.putExtra(PushConstants.EXTRA_USER_ID, clientLoginInfo.userId);
        intent.putExtra(PushConstants.EXTRA_SESSION, clientLoginInfo.session);
        ClientEventDispatcher.sendBroadcast(context, intent, clientLoginInfo.pkgName);
    }

    public void notifyChannelOpenResult(Context context, PushClientsManager.ClientLoginInfo clientLoginInfo, boolean bl, int n, String string2) {
        if ("5".equalsIgnoreCase(clientLoginInfo.chid)) {
            this.mPushEventProcessor.processChannelOpenResult(context, clientLoginInfo, bl, n, string2);
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.channel_opened");
        intent.setPackage(clientLoginInfo.pkgName);
        intent.putExtra("ext_succeeded", bl);
        if (!bl) {
            intent.putExtra("ext_reason", n);
        }
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            intent.putExtra("ext_reason_msg", string2);
        }
        intent.putExtra("ext_chid", clientLoginInfo.chid);
        intent.putExtra(PushConstants.EXTRA_USER_ID, clientLoginInfo.userId);
        intent.putExtra(PushConstants.EXTRA_SESSION, clientLoginInfo.session);
        ClientEventDispatcher.sendBroadcast(context, intent, clientLoginInfo.pkgName);
    }

    public void notifyKickedByServer(Context context, PushClientsManager.ClientLoginInfo clientLoginInfo, String string2, String string3) {
        if ("5".equalsIgnoreCase(clientLoginInfo.chid)) {
            MyLog.e("mipush kicked by server");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.kicked");
        intent.setPackage(clientLoginInfo.pkgName);
        intent.putExtra("ext_kick_type", string2);
        intent.putExtra("ext_kick_reason", string3);
        intent.putExtra("ext_chid", clientLoginInfo.chid);
        intent.putExtra(PushConstants.EXTRA_USER_ID, clientLoginInfo.userId);
        intent.putExtra(PushConstants.EXTRA_SESSION, clientLoginInfo.session);
        ClientEventDispatcher.sendBroadcast(context, intent, clientLoginInfo.pkgName);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void notifyPacketArrival(XMPushService xMPushService, String string2, Packet packet) {
        String string3;
        PushClientsManager.ClientLoginInfo clientLoginInfo = this.getClientLoginInfo(packet);
        if (clientLoginInfo == null) {
            MyLog.e("error while notify channel closed! channel " + string2 + " not registered");
            return;
        }
        if ("5".equalsIgnoreCase(string2)) {
            this.mPushEventProcessor.processNewPacket(xMPushService, packet, clientLoginInfo);
            return;
        }
        String string4 = clientLoginInfo.pkgName;
        if (packet instanceof Message) {
            string3 = "com.xiaomi.push.new_msg";
        } else if (packet instanceof IQ) {
            string3 = "com.xiaomi.push.new_iq";
        } else {
            if (!(packet instanceof Presence)) {
                MyLog.e("unknown packet type, drop it");
                return;
            }
            string3 = "com.xiaomi.push.new_pres";
        }
        Intent intent = new Intent();
        intent.setAction(string3);
        intent.setPackage(string4);
        intent.putExtra("ext_chid", string2);
        intent.putExtra("ext_packet", packet.toBundle());
        intent.putExtra(PushConstants.EXTRA_SESSION, clientLoginInfo.session);
        intent.putExtra(PushConstants.EXTRA_SECURITY, clientLoginInfo.security);
        ClientEventDispatcher.sendBroadcast((Context)xMPushService, intent, string4);
    }

    public void notifyServiceStarted(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.service_started");
        context.sendBroadcast(intent);
    }
}

