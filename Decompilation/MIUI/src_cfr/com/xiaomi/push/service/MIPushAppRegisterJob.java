/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  org.json.JSONException
 */
package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.MIPushAccountUtils;
import com.xiaomi.push.service.MIPushClientManager;
import com.xiaomi.push.service.PushClientsManager;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.smack.XMPPException;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import org.json.JSONException;

public class MIPushAppRegisterJob
extends XMPushService.Job {
    private String appId;
    private String appToken;
    private String packageName;
    private byte[] payload;
    private XMPushService pushService;

    public MIPushAppRegisterJob(XMPushService xMPushService, String string2, String string3, String string4, byte[] arrby) {
        super(9);
        this.pushService = xMPushService;
        this.packageName = string2;
        this.payload = arrby;
        this.appId = string3;
        this.appToken = string4;
    }

    @Override
    public String getDesc() {
        return "register app";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void process() {
        Collection<PushClientsManager.ClientLoginInfo> collection;
        Object object = collection = MIPushAccountUtils.getMIPushAccount((Context)this.pushService);
        if (collection == null) {
            try {
                object = MIPushAccountUtils.register((Context)this.pushService, this.packageName, this.appId, this.appToken);
            }
            catch (IOException var1_3) {
                MyLog.e(var1_3);
                object = collection;
            }
            catch (JSONException var1_4) {
                MyLog.e((Throwable)var1_4);
                object = collection;
            }
        }
        if (object == null) {
            MyLog.e("no account for mipush");
            MIPushClientManager.notifyRegisterError((Context)this.pushService, 70000002, "no account.");
            return;
        }
        collection = PushClientsManager.getInstance().getAllClientLoginInfoByChid("5");
        if (collection.isEmpty()) {
            object = object.toClientLoginInfo(this.pushService);
            this.pushService.prepareMIPushClientLoginInfo((PushClientsManager.ClientLoginInfo)object);
            PushClientsManager.getInstance().addActiveClient((PushClientsManager.ClientLoginInfo)object);
        } else {
            object = (PushClientsManager.ClientLoginInfo)collection.iterator().next();
        }
        if (!this.pushService.isConnected()) {
            this.pushService.scheduleConnect(true);
            return;
        }
        try {
            if (object.status == PushClientsManager.ClientStatus.binded) {
                this.pushService.sendMIPushPacket(this.packageName, this.payload);
                return;
            }
            if (object.status != PushClientsManager.ClientStatus.unbind) return;
            collection = this.pushService;
            XMPushService xMPushService = this.pushService;
            xMPushService.getClass();
            collection.executeJob((XMPushService.Job)new XMPushService.BindJob(xMPushService, (PushClientsManager.ClientLoginInfo)object));
            return;
        }
        catch (XMPPException var1_5) {
            MyLog.e(var1_5);
            this.pushService.disconnect(10, var1_5);
            return;
        }
    }
}

