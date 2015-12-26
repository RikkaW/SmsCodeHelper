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
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Packet;

public class BatchSendMessageJob
extends XMPushService.Job {
    private Message[] mMessages;
    private XMPushService pushService = null;

    public BatchSendMessageJob(XMPushService xMPushService, Message[] arrmessage) {
        super(4);
        this.pushService = xMPushService;
        this.mMessages = arrmessage;
    }

    @Override
    public String getDesc() {
        return "batch send message.";
    }

    @Override
    public void process() {
        try {
            this.pushService.batchSendPacket(this.mMessages);
            return;
        }
        catch (XMPPException var1_1) {
            MyLog.e(var1_1);
            this.pushService.disconnect(10, var1_1);
            return;
        }
    }
}

