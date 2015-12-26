/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.text.TextUtils
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  org.json.JSONException
 */
package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.MIPushAccount;
import com.xiaomi.push.service.MIPushAccountUtils;
import com.xiaomi.push.service.PushClientsManager;
import com.xiaomi.push.service.RC4Cryption;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.smack.XMPPException;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.util.TrafficUtils;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.PushMetaInfo;
import com.xiaomi.xmpush.thrift.XmPushActionAckMessage;
import com.xiaomi.xmpush.thrift.XmPushActionContainer;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.thrift.TBase;
import org.json.JSONException;

public class MIPushEventProcessor {
    private static XmPushActionContainer constructAckMessage(XMPushService object, XmPushActionContainer cloneable) {
        XmPushActionAckMessage xmPushActionAckMessage = new XmPushActionAckMessage();
        xmPushActionAckMessage.setAppId(cloneable.getAppid());
        PushMetaInfo pushMetaInfo = cloneable.getMetaInfo();
        if (pushMetaInfo != null) {
            xmPushActionAckMessage.setId(pushMetaInfo.getId());
            xmPushActionAckMessage.setMessageTs(pushMetaInfo.getMessageTs());
            if (!TextUtils.isEmpty((CharSequence)pushMetaInfo.getTopic())) {
                xmPushActionAckMessage.setTopic(pushMetaInfo.getTopic());
            }
        }
        object = object.generateRequestContainer(cloneable.getPackageName(), cloneable.getAppid(), xmPushActionAckMessage, ActionType.AckMessage);
        cloneable = cloneable.getMetaInfo().deepCopy();
        cloneable.putToExtra("mat", Long.toString((long)System.currentTimeMillis()));
        object.setMetaInfo((PushMetaInfo)cloneable);
        return object;
    }

    private static boolean isIntentAvailable(Context object, Intent intent) {
        block3 : {
            object = object.getPackageManager();
            try {
                object = object.queryBroadcastReceivers(intent, 32);
                if (object == null) break block3;
            }
            catch (Exception var0_1) {
                return true;
            }
            boolean bl = object.isEmpty();
            if (bl) break block3;
            return true;
        }
        return false;
    }

    private static boolean isMIUIOldAdsSDKMessage(XmPushActionContainer xmPushActionContainer) {
        if (xmPushActionContainer.getMetaInfo() == null || xmPushActionContainer.getMetaInfo().getExtra() == null) {
            return false;
        }
        return "1".equals((Object)xmPushActionContainer.getMetaInfo().getExtra().get("obslete_ads_message"));
    }

    private static boolean isMIUIPushMessage(XmPushActionContainer xmPushActionContainer) {
        if ("com.xiaomi.xmsf".equals((Object)xmPushActionContainer.packageName) && xmPushActionContainer.getMetaInfo() != null && xmPushActionContainer.getMetaInfo().getExtra() != null && xmPushActionContainer.getMetaInfo().getExtra().containsKey("miui_package_name")) {
            return true;
        }
        return false;
    }

    private static boolean isMIUIPushSupported(Context object, String object2) {
        boolean bl = false;
        Intent intent = new Intent("com.xiaomi.mipush.miui.CLICK_MESSAGE");
        intent.setPackage((String)object2);
        Intent intent2 = new Intent("com.xiaomi.mipush.miui.RECEIVE_MESSAGE");
        intent2.setPackage((String)object2);
        object = object.getPackageManager();
        try {
            boolean bl2;
            object2 = object.queryBroadcastReceivers(intent2, 32);
            object = object.queryIntentServices(intent, 32);
            if (!object2.isEmpty() || !(bl2 = object.isEmpty())) {
                bl = true;
            }
            return bl;
        }
        catch (Exception var0_1) {
            MyLog.e(var0_1);
            return false;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean isPkgInstalled(Context context, String string2) {
        try {
            context = context.getPackageManager().getPackageInfo(string2, 0);
            if (context != null) return true;
            return false;
        }
        catch (PackageManager.NameNotFoundException var0_1) {
            return false;
        }
    }

    private static boolean predefinedNotification(XmPushActionContainer xmPushActionContainer) {
        return xmPushActionContainer.getMetaInfo().getExtra().containsKey("notify_effect");
    }

    /*
     * Exception decompiling
     */
    private static void processMIPushMessage(XMPushService var0, byte[] var1_2, long var2_4) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [] lbl34 : TryStatement: try { 1[TRYBLOCK]

        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:44)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:22)
        // org.benf.cfr.reader.util.graph.GraphVisitorDFS.process(GraphVisitorDFS.java:68)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner.removeUnreachableCode(Cleaner.java:54)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.RemoveDeterministicJumps.apply(RemoveDeterministicJumps.java:35)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:507)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    private static void sendAckMessage(final XMPushService xMPushService, final XmPushActionContainer xmPushActionContainer) {
        xMPushService.executeJob(new XMPushService.Job(4){

            @Override
            public String getDesc() {
                return "send ack message for message.";
            }

            @Override
            public void process() {
                try {
                    XmPushActionContainer xmPushActionContainer2 = MIPushEventProcessor.constructAckMessage(xMPushService, xmPushActionContainer);
                    xMPushService.sendMIPushPacket(xmPushActionContainer2);
                    return;
                }
                catch (XMPPException var1_2) {
                    MyLog.e(var1_2);
                    xMPushService.disconnect(10, var1_2);
                    return;
                }
            }
        });
    }

    private static void sendAppAbsentAck(final XMPushService xMPushService, final XmPushActionContainer xmPushActionContainer, final String string2) {
        xMPushService.executeJob(new XMPushService.Job(4){

            @Override
            public String getDesc() {
                return "send app absent ack message for message.";
            }

            @Override
            public void process() {
                try {
                    XmPushActionContainer xmPushActionContainer2 = MIPushEventProcessor.constructAckMessage(xMPushService, xmPushActionContainer);
                    xmPushActionContainer2.getMetaInfo().putToExtra("absent_target_package", string2);
                    xMPushService.sendMIPushPacket(xmPushActionContainer2);
                    return;
                }
                catch (XMPPException var1_2) {
                    MyLog.e(var1_2);
                    xMPushService.disconnect(10, var1_2);
                    return;
                }
            }
        });
    }

    private static void sendErrorAck(final XMPushService xMPushService, final XmPushActionContainer xmPushActionContainer, final String string2, final String string3) {
        xMPushService.executeJob(new XMPushService.Job(4){

            @Override
            public String getDesc() {
                return "send wrong message ack for message.";
            }

            @Override
            public void process() {
                try {
                    XmPushActionContainer xmPushActionContainer2 = MIPushEventProcessor.constructAckMessage(xMPushService, xmPushActionContainer);
                    xmPushActionContainer2.metaInfo.putToExtra("error", string2);
                    xmPushActionContainer2.metaInfo.putToExtra("reason", string3);
                    xMPushService.sendMIPushPacket(xmPushActionContainer2);
                    return;
                }
                catch (XMPPException var1_2) {
                    MyLog.e(var1_2);
                    xMPushService.disconnect(10, var1_2);
                    return;
                }
            }
        });
    }

    private static void sendMIUINewAdsAckMessage(final XMPushService xMPushService, final XmPushActionContainer xmPushActionContainer) {
        xMPushService.executeJob(new XMPushService.Job(4){

            @Override
            public String getDesc() {
                return "send ack message for unrecognized new miui message.";
            }

            @Override
            public void process() {
                try {
                    XmPushActionContainer xmPushActionContainer2 = MIPushEventProcessor.constructAckMessage(xMPushService, xmPushActionContainer);
                    xmPushActionContainer2.getMetaInfo().putToExtra("miui_message_unrecognized", "1");
                    xMPushService.sendMIPushPacket(xmPushActionContainer2);
                    return;
                }
                catch (XMPPException var1_2) {
                    MyLog.e(var1_2);
                    xMPushService.disconnect(10, var1_2);
                    return;
                }
            }
        });
    }

    private static void sendMIUIOldAdsAckMessage(final XMPushService xMPushService, final XmPushActionContainer xmPushActionContainer) {
        xMPushService.executeJob(new XMPushService.Job(4){

            @Override
            public String getDesc() {
                return "send ack message for obsleted message.";
            }

            @Override
            public void process() {
                try {
                    XmPushActionContainer xmPushActionContainer2 = MIPushEventProcessor.constructAckMessage(xMPushService, xmPushActionContainer);
                    xmPushActionContainer2.getMetaInfo().putToExtra("message_obsleted", "1");
                    xMPushService.sendMIPushPacket(xmPushActionContainer2);
                    return;
                }
                catch (XMPPException var1_2) {
                    MyLog.e(var1_2);
                    xMPushService.disconnect(10, var1_2);
                    return;
                }
            }
        });
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void processChannelOpenResult(Context context, PushClientsManager.ClientLoginInfo object, boolean bl, int n, String string2) {
        if (bl || (object = MIPushAccountUtils.getMIPushAccount(context)) == null || !"token-expired".equals((Object)string2)) return;
        try {
            MIPushAccountUtils.register(context, object.appId, object.appToken, object.packageName);
            return;
        }
        catch (IOException var1_2) {
            MyLog.e(var1_2);
            return;
        }
        catch (JSONException var1_3) {
            MyLog.e((Throwable)var1_3);
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void processNewPacket(XMPushService xMPushService, Packet packet, PushClientsManager.ClientLoginInfo clientLoginInfo) {
        if (packet instanceof Message) {
            Message message = (Message)packet;
            CommonPacketExtension commonPacketExtension = message.getExtension("s");
            if (commonPacketExtension == null) return;
            try {
                MIPushEventProcessor.processMIPushMessage(xMPushService, RC4Cryption.decrypt(RC4Cryption.generateKeyForRC4(clientLoginInfo.security, message.getPacketID()), commonPacketExtension.getText()), TrafficUtils.getTrafficFlow(packet.toXML()));
                return;
            }
            catch (IllegalArgumentException var1_2) {
                MyLog.e(var1_2);
                return;
            }
        }
        MyLog.warn("not a mipush message");
    }

}

