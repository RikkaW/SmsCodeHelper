/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.util.ArrayList
 *  java.util.HashMap
 */
package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import com.xiaomi.mipush.sdk.AppInfoHolder;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageHandler;
import com.xiaomi.mipush.sdk.PushMessageHelper;
import com.xiaomi.mipush.sdk.PushServiceClient;
import com.xiaomi.push.service.DeviceInfo;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.PushMetaInfo;
import com.xiaomi.xmpush.thrift.XmPushActionCommand;
import com.xiaomi.xmpush.thrift.XmPushActionNotification;
import com.xiaomi.xmpush.thrift.XmPushActionRegistration;
import com.xiaomi.xmpush.thrift.XmPushActionSubscription;
import com.xiaomi.xmpush.thrift.XmPushActionUnRegistration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.thrift.TBase;

public abstract class MiPushClient {
    private static boolean awakeService = true;
    private static Context sContext;
    private static long sCurMsgId;

    static {
        sCurMsgId = System.currentTimeMillis();
    }

    public static long accountSetTime(Context context, String string2) {
        return context.getSharedPreferences("mipush_extra", 0).getLong("account_" + string2, -1);
    }

    static void addAcceptTime(Context context, String string2, String string3) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().putString("accept_time", string2 + "," + string3).commit();
            return;
        }
    }

    static void addAccount(Context context, String string2) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().putLong("account_" + string2, System.currentTimeMillis()).commit();
            return;
        }
    }

    static void addAlias(Context context, String string2) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().putLong("alias_" + string2, System.currentTimeMillis()).commit();
            return;
        }
    }

    private static void addPullNotificationTime(Context context) {
        context.getSharedPreferences("mipush_extra", 0).edit().putLong("last_pull_notification", System.currentTimeMillis()).commit();
    }

    private static void addRegRequestTime(Context context) {
        context.getSharedPreferences("mipush_extra", 0).edit().putLong("last_reg_request", System.currentTimeMillis()).commit();
    }

    static void addTopic(Context context, String string2) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().putLong("topic_" + string2, System.currentTimeMillis()).commit();
            return;
        }
    }

    public static long aliasSetTime(Context context, String string2) {
        return context.getSharedPreferences("mipush_extra", 0).getLong("alias_" + string2, -1);
    }

    private static void awakePushServices(final Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        if (System.currentTimeMillis() - 86400000 < sharedPreferences.getLong("wake_up", 0)) {
            return;
        }
        sharedPreferences.edit().putLong("wake_up", System.currentTimeMillis()).commit();
        new Thread(new Runnable(){

            /*
             * Exception decompiling
             */
            @Override
            public void run() {
                // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
                // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 5[DOLOOP]
                // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
                // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
                // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
                // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
                // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
                // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
                // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
                // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
                // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
                // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
                // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
                // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
                // org.benf.cfr.reader.Main.doJar(Main.java:128)
                // org.benf.cfr.reader.Main.main(Main.java:178)
                throw new IllegalStateException("Decompilation failed");
            }
        }).start();
    }

    private static void checkNotNull(Object object, String string2) {
        if (object == null) {
            throw new IllegalArgumentException("param " + string2 + " is not nullable");
        }
    }

    protected static void clearExtras(Context context) {
        context = context.getSharedPreferences("mipush_extra", 0);
        long l = context.getLong("wake_up", 0);
        context = context.edit();
        context.clear();
        if (l > 0) {
            context.putLong("wake_up", l);
        }
        context.commit();
    }

    public static void clearLocalNotificationType(Context context) {
        PushServiceClient.getInstance(context).clearLocalNotificationType();
    }

    public static void clearNotification(Context context) {
        PushServiceClient.getInstance(context).clearNotification(-1);
    }

    protected static String generatePacketID() {
        synchronized (MiPushClient.class) {
            String string2 = XMStringUtils.generateRandomString(4) + sCurMsgId;
            ++sCurMsgId;
            return string2;
        }
    }

    /*
     * Exception decompiling
     */
    @Deprecated
    public static void initialize(Context var0, String var1_2, String var2_3, MiPushClientCallback var3_4) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [] lbl54 : TryStatement: try { 1[TRYBLOCK]

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

    static void reInitialize(Context context) {
        if (!AppInfoHolder.getInstance(context).appRegistered()) {
            return;
        }
        String string2 = XMStringUtils.generateRandomString(6);
        String string3 = AppInfoHolder.getInstance(context).getAppID();
        String string4 = AppInfoHolder.getInstance(context).getAppToken();
        AppInfoHolder.getInstance(context).clear();
        AppInfoHolder.getInstance(context).putAppIDAndToken(string3, string4, string2);
        XmPushActionRegistration xmPushActionRegistration = new XmPushActionRegistration();
        xmPushActionRegistration.setId(MiPushClient.generatePacketID());
        xmPushActionRegistration.setAppId(string3);
        xmPushActionRegistration.setToken(string4);
        xmPushActionRegistration.setDeviceId(string2);
        xmPushActionRegistration.setPackageName(context.getPackageName());
        xmPushActionRegistration.setAppVersion(AppInfoHolder.getVersionName(context, context.getPackageName()));
        PushServiceClient.getInstance(context).register(xmPushActionRegistration, false);
    }

    public static void registerPush(final Context context, final String string2, final String string3) {
        new Thread(new Runnable(){

            @Override
            public void run() {
                MiPushClient.initialize(context, string2, string3, null);
            }
        }).start();
    }

    static void removeAccount(Context context, String string2) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().remove("account_" + string2).commit();
            return;
        }
    }

    static void removeAlias(Context context, String string2) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().remove("alias_" + string2).commit();
            return;
        }
    }

    static void removeTopic(Context context, String string2) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().remove("topic_" + string2).commit();
            return;
        }
    }

    static void reportIgnoreRegMessageClicked(Context context, String string2, PushMetaInfo pushMetaInfo, String string3, String string4) {
        XmPushActionNotification xmPushActionNotification = new XmPushActionNotification();
        if (TextUtils.isEmpty((CharSequence)string4)) {
            MyLog.e("do not report clicked message");
            return;
        }
        xmPushActionNotification.setAppId(string4);
        xmPushActionNotification.setType("bar:click");
        xmPushActionNotification.setId(string2);
        xmPushActionNotification.setRequireAck(false);
        PushServiceClient.getInstance(context).sendMessage(xmPushActionNotification, ActionType.Notification, false, true, pushMetaInfo, true, string3, string4);
    }

    /*
     * Enabled aggressive block sorting
     */
    static void reportMessageClicked(Context context, String string2, PushMetaInfo pushMetaInfo, String string3) {
        XmPushActionNotification xmPushActionNotification = new XmPushActionNotification();
        if (TextUtils.isEmpty((CharSequence)string3)) {
            if (!AppInfoHolder.getInstance(context).checkAppInfo()) {
                MyLog.e("do not report clicked message");
                return;
            }
            xmPushActionNotification.setAppId(AppInfoHolder.getInstance(context).getAppID());
        } else {
            xmPushActionNotification.setAppId(string3);
        }
        xmPushActionNotification.setType("bar:click");
        xmPushActionNotification.setId(string2);
        xmPushActionNotification.setRequireAck(false);
        PushServiceClient.getInstance(context).sendMessage(xmPushActionNotification, ActionType.Notification, false, pushMetaInfo);
    }

    public static void setAlias(Context context, String string2, String string3) {
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            MiPushClient.setCommand(context, "set-alias", string2, string3);
        }
    }

    protected static void setCommand(Context context, String string2, String string3, String string4) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty((CharSequence)string3)) {
            arrayList.add((Object)string3);
        }
        if ("set-alias".equalsIgnoreCase(string2) && System.currentTimeMillis() - MiPushClient.aliasSetTime(context, string3) < 3600000) {
            if (1 == PushMessageHelper.getPushMode(context)) {
                PushMessageHandler.onCommandResult(context, string4, string2, 0, null, arrayList);
                return;
            }
            PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage("set-alias", arrayList, 0, null, null));
            return;
        }
        if ("unset-alias".equalsIgnoreCase(string2) && MiPushClient.aliasSetTime(context, string3) < 0) {
            MyLog.warn("Don't cancel alias for " + (Object)arrayList + " is unseted");
            return;
        }
        if ("set-account".equalsIgnoreCase(string2) && System.currentTimeMillis() - MiPushClient.accountSetTime(context, string3) < 3600000) {
            if (1 == PushMessageHelper.getPushMode(context)) {
                PushMessageHandler.onCommandResult(context, string4, string2, 0, null, arrayList);
                return;
            }
            PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage("set-account", arrayList, 0, null, null));
            return;
        }
        if ("unset-account".equalsIgnoreCase(string2) && MiPushClient.accountSetTime(context, string3) < 0) {
            MyLog.warn("Don't cancel account for " + (Object)arrayList + " is unseted");
            return;
        }
        MiPushClient.setCommand(context, string2, arrayList, string4);
    }

    protected static void setCommand(Context context, String object, ArrayList<String> arrayList, String string2) {
        if (TextUtils.isEmpty((CharSequence)AppInfoHolder.getInstance(context).getAppID())) {
            return;
        }
        XmPushActionCommand xmPushActionCommand = new XmPushActionCommand();
        xmPushActionCommand.setId(MiPushClient.generatePacketID());
        xmPushActionCommand.setAppId(AppInfoHolder.getInstance(context).getAppID());
        xmPushActionCommand.setCmdName((String)object);
        object = arrayList.iterator();
        while (object.hasNext()) {
            xmPushActionCommand.addToCmdArgs((String)object.next());
        }
        xmPushActionCommand.setCategory(string2);
        xmPushActionCommand.setPackageName(context.getPackageName());
        PushServiceClient.getInstance(context).sendMessage(xmPushActionCommand, ActionType.Command, null);
    }

    private static boolean shouldPullNotification(Context context) {
        boolean bl = false;
        context = context.getSharedPreferences("mipush_extra", 0);
        if (System.currentTimeMillis() - context.getLong("last_pull_notification", -1) > 300000) {
            bl = true;
        }
        return bl;
    }

    private static boolean shouldSendRegRequest(Context context) {
        boolean bl = false;
        context = context.getSharedPreferences("mipush_extra", 0);
        if (System.currentTimeMillis() - context.getLong("last_reg_request", -1) > 5000) {
            bl = true;
        }
        return bl;
    }

    public static boolean shouldUseMIUIPush(Context context) {
        return PushServiceClient.getInstance(context).shouldUseMIUIPush();
    }

    public static void subscribe(Context context, String string2, String string3) {
        if (TextUtils.isEmpty((CharSequence)AppInfoHolder.getInstance(context).getAppID()) || TextUtils.isEmpty((CharSequence)string2)) {
            return;
        }
        if (System.currentTimeMillis() - MiPushClient.topicSubscribedTime(context, string2) > 86400000) {
            XmPushActionSubscription xmPushActionSubscription = new XmPushActionSubscription();
            xmPushActionSubscription.setId(MiPushClient.generatePacketID());
            xmPushActionSubscription.setAppId(AppInfoHolder.getInstance(context).getAppID());
            xmPushActionSubscription.setTopic(string2);
            xmPushActionSubscription.setPackageName(context.getPackageName());
            xmPushActionSubscription.setCategory(string3);
            PushServiceClient.getInstance(context).sendMessage(xmPushActionSubscription, ActionType.Subscription, null);
            return;
        }
        if (1 == PushMessageHelper.getPushMode(context)) {
            PushMessageHandler.onSubscribeResult(context, string3, 0, null, string2);
            return;
        }
        string3 = new ArrayList();
        string3.add(string2);
        PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage("subscribe-topic", string3, 0, null, null));
    }

    public static long topicSubscribedTime(Context context, String string2) {
        return context.getSharedPreferences("mipush_extra", 0).getLong("topic_" + string2, -1);
    }

    public static void unregisterPush(Context context) {
        if (!AppInfoHolder.getInstance(context).checkAppInfo()) {
            return;
        }
        XmPushActionUnRegistration xmPushActionUnRegistration = new XmPushActionUnRegistration();
        xmPushActionUnRegistration.setId(MiPushClient.generatePacketID());
        xmPushActionUnRegistration.setAppId(AppInfoHolder.getInstance(context).getAppID());
        xmPushActionUnRegistration.setRegId(AppInfoHolder.getInstance(context).getRegID());
        xmPushActionUnRegistration.setToken(AppInfoHolder.getInstance(context).getAppToken());
        xmPushActionUnRegistration.setPackageName(context.getPackageName());
        PushServiceClient.getInstance(context).unregister(xmPushActionUnRegistration);
        PushMessageHandler.removeAllPushCallbackClass();
        AppInfoHolder.getInstance(context).invalidate();
        MiPushClient.clearExtras(context);
        MiPushClient.clearLocalNotificationType(context);
        MiPushClient.clearNotification(context);
    }

    private static void updateIMEI() {
        new Thread(new Runnable(){

            @Override
            public void run() {
                if (DeviceInfo.blockingGetIMEI(sContext) != null) {
                    XmPushActionNotification xmPushActionNotification = new XmPushActionNotification();
                    xmPushActionNotification.setAppId(AppInfoHolder.getInstance(sContext).getAppID());
                    xmPushActionNotification.setType("client_info_update");
                    xmPushActionNotification.setId(MiPushClient.generatePacketID());
                    xmPushActionNotification.setExtra((Map<String, String>)new HashMap());
                    xmPushActionNotification.getExtra().put("imei_md5", XMStringUtils.getMd5Digest(DeviceInfo.blockingGetIMEI(sContext)));
                    PushServiceClient.getInstance(sContext).sendMessage(xmPushActionNotification, ActionType.Notification, false, null);
                }
            }
        }).start();
    }

    @Deprecated
    public static abstract class MiPushClientCallback {
        private String category;

        protected String getCategory() {
            return this.category;
        }

        public void onCommandResult(String string2, long l, String string3, List<String> list) {
        }

        public void onInitializeResult(long l, String string2, String string3) {
        }

        public void onReceiveMessage(MiPushMessage miPushMessage) {
        }

        public void onReceiveMessage(String string2, String string3, String string4, boolean bl) {
        }

        public void onSubscribeResult(long l, String string2, String string3) {
        }

        public void onUnsubscribeResult(long l, String string2, String string3) {
        }
    }

}

