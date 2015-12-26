/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.String
 */
package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.smack.XMPPException;
import com.xiaomi.smack.packet.Packet;

public class SendMessageJob
extends XMPushService.Job {
    private Packet mPacket;
    private XMPushService pushService = null;

    public SendMessageJob(XMPushService xMPushService, Packet packet) {
        super(4);
        this.pushService = xMPushService;
        this.mPacket = packet;
    }

    @Override
    public String getDesc() {
        return "send a message.";
    }

    @Override
    public void process() {
        try {
            this.pushService.sendPacket(this.mPacket);
            return;
        }
        catch (XMPPException var1_1) {
            MyLog.e(var1_1);
            this.pushService.disconnect(10, var1_1);
            return;
        }
    }
}

