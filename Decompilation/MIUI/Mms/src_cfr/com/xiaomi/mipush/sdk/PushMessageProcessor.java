/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.pm.PackageManager
 *  android.content.pm.ResolveInfo
 *  android.net.Uri
 *  android.text.TextUtils
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.net.URISyntaxException
 *  java.net.URL
 *  java.util.ArrayList
 *  java.util.LinkedList
 *  java.util.TimeZone
 */
package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import com.xiaomi.mipush.sdk.AppInfoHolder;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.PushContainerHelper;
import com.xiaomi.mipush.sdk.PushMessageHandler;
import com.xiaomi.mipush.sdk.PushMessageHelper;
import com.xiaomi.mipush.sdk.PushServiceClient;
import com.xiaomi.push.service.MIPushNotificationHelper;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.PushMessage;
import com.xiaomi.xmpush.thrift.PushMetaInfo;
import com.xiaomi.xmpush.thrift.XmPushActionAckMessage;
import com.xiaomi.xmpush.thrift.XmPushActionCommandResult;
import com.xiaomi.xmpush.thrift.XmPushActionContainer;
import com.xiaomi.xmpush.thrift.XmPushActionNotification;
import com.xiaomi.xmpush.thrift.XmPushActionRegistrationResult;
import com.xiaomi.xmpush.thrift.XmPushActionSendMessage;
import com.xiaomi.xmpush.thrift.XmPushActionSubscriptionResult;
import com.xiaomi.xmpush.thrift.XmPushActionUnRegistrationResult;
import com.xiaomi.xmpush.thrift.XmPushActionUnSubscriptionResult;
import com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TimeZone;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;

public class PushMessageProcessor {
    private static Object lock;
    private static Queue<String> mCachedMsgIds;
    private static PushMessageProcessor sInstance;
    private Context sAppContext;

    static {
        sInstance = null;
        lock = new Object();
    }

    private PushMessageProcessor(Context context) {
        this.sAppContext = context.getApplicationContext();
        if (this.sAppContext == null) {
            this.sAppContext = context;
        }
    }

    private void ackMessage(XmPushActionContainer xmPushActionContainer) {
        PushMetaInfo pushMetaInfo = xmPushActionContainer.getMetaInfo();
        XmPushActionAckMessage xmPushActionAckMessage = new XmPushActionAckMessage();
        xmPushActionAckMessage.setAppId(xmPushActionContainer.getAppid());
        xmPushActionAckMessage.setId(pushMetaInfo.getId());
        xmPushActionAckMessage.setMessageTs(pushMetaInfo.getMessageTs());
        if (!TextUtils.isEmpty((CharSequence)pushMetaInfo.getTopic())) {
            xmPushActionAckMessage.setTopic(pushMetaInfo.getTopic());
        }
        PushServiceClient.getInstance(this.sAppContext).sendMessage(xmPushActionAckMessage, ActionType.AckMessage, false, xmPushActionContainer.getMetaInfo());
    }

    private void ackMessage(XmPushActionSendMessage xmPushActionSendMessage, PushMetaInfo pushMetaInfo) {
        XmPushActionAckMessage xmPushActionAckMessage = new XmPushActionAckMessage();
        xmPushActionAckMessage.setAppId(xmPushActionSendMessage.getAppId());
        xmPushActionAckMessage.setId(xmPushActionSendMessage.getId());
        xmPushActionAckMessage.setMessageTs(xmPushActionSendMessage.getMessage().getCreateAt());
        if (!TextUtils.isEmpty((CharSequence)xmPushActionSendMessage.getTopic())) {
            xmPushActionAckMessage.setTopic(xmPushActionSendMessage.getTopic());
        }
        if (!TextUtils.isEmpty((CharSequence)xmPushActionSendMessage.getAliasName())) {
            xmPushActionAckMessage.setAliasName(xmPushActionSendMessage.getAliasName());
        }
        PushServiceClient.getInstance(this.sAppContext).sendMessage(xmPushActionAckMessage, ActionType.AckMessage, pushMetaInfo);
    }

    public static PushMessageProcessor getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PushMessageProcessor(context);
        }
        return sInstance;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static Intent getNotificationMessageIntent(Context var0, String var1_2, Map<String, String> var2_5) {
        if (var2_5 == null) return null;
        if (!var2_5.containsKey("notify_effect")) {
            return null;
        }
        var3_7 = (String)var2_5.get("notify_effect");
        if (PushConstants.NOTIFICATION_CLICK_DEFAULT.equals((Object)var3_7)) {
            try {
                var1_2 = var0.getPackageManager().getLaunchIntentForPackage((String)var1_2);
            }
            catch (Exception var1_3) {
                MyLog.e("Cause: " + var1_3.getMessage());
                return null;
            }
        }
        if (!PushConstants.NOTIFICATION_CLICK_INTENT.equals((Object)var3_7)) ** GOTO lbl36
        if (!var2_5.containsKey("intent_uri")) ** GOTO lbl24
        if ((var2_5 = (String)var2_5.get("intent_uri")) == null) return null;
        var2_5 = Intent.parseUri((String)var2_5, (int)1);
        try {
            var2_5.setPackage((String)var1_2);
            var1_2 = var2_5;
        }
        catch (URISyntaxException var3_8) {
            var1_2 = var2_5;
            ** GOTO lbl60
lbl24: // 1 sources:
            if (var2_5.containsKey("class_name") == false) return null;
            var4_12 = (String)var2_5.get("class_name");
            var3_7 = new Intent();
            var3_7.setComponent(new ComponentName((String)var1_2, var4_12));
            try {
                if (var2_5.containsKey("intent_flag")) {
                    var3_7.setFlags(Integer.parseInt((String)((String)var2_5.get("intent_flag"))));
                }
            }
            catch (NumberFormatException var1_4) {
                MyLog.e("Cause by intent_flag: " + var1_4.getMessage());
            }
            var1_2 = var3_7;
lbl36: // 1 sources:
            if (PushConstants.NOTIFICATION_CLICK_WEB_PAGE.equals((Object)var3_7) == false) return null;
            var1_2 = (String)var2_5.get("web_uri");
            if (var1_2 == null) return null;
            if (!(var1_2 = var1_2.trim()).startsWith("http://") && !var1_2.startsWith("https://")) {
                var1_2 = "http://" + (String)var1_2;
            }
            var2_5 = new URL((String)var1_2).getProtocol();
            if (!"http".equals(var2_5)) {
                if ("https".equals(var2_5) == false) return null;
            }
            var2_5 = new Intent("android.intent.action.VIEW");
            try {
                var2_5.setData(Uri.parse((String)var1_2));
                var1_2 = var2_5;
            }
            catch (MalformedURLException var3_10) {
                var1_2 = var2_5;
                var2_5 = var3_10;
                ** GOTO lbl56
                catch (MalformedURLException var2_6) {
                    var1_2 = null;
                }
lbl56: // 2 sources:
                MyLog.e("Cause: " + var2_5.getMessage());
            }
            catch (URISyntaxException var3_11) {
                var1_2 = null;
            }
lbl60: // 2 sources:
            MyLog.e("Cause: " + var3_9.getMessage());
        }
        if (var1_2 == null) return null;
        var1_2.addFlags(268435456);
        try {
            if ((var0 = var0.getPackageManager().resolveActivity((Intent)var1_2, 65536)) == null) return null;
            return var1_2;
        }
        catch (Exception var0_1) {
            MyLog.e("Cause: " + var0_1.getMessage());
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static boolean isDuplicateMessage(Context context, String string2) {
        Object object = lock;
        synchronized (object) {
            context = AppInfoHolder.getInstance(context).getSharedPreferences();
            if (mCachedMsgIds == null) {
                String[] arrstring = context.getString("pref_msg_ids", "").split(",");
                mCachedMsgIds = new LinkedList();
                for (String string3 : arrstring) {
                    mCachedMsgIds.add(string3);
                }
            }
            if (mCachedMsgIds.contains((Object)string2)) {
                return true;
            }
            mCachedMsgIds.add(string2);
            if (mCachedMsgIds.size() > 10) {
                mCachedMsgIds.poll();
            }
            string2 = XMStringUtils.join(mCachedMsgIds, ",");
            context = context.edit();
            context.putString("pref_msg_ids", string2);
            context.commit();
            return false;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    private PushMessageHandler.PushMessageInterface processMessage(XmPushActionContainer var1_1, boolean var2_3, byte[] var3_4) {
        var4_5 = PushContainerHelper.getResponseMessageBodyFromContainer(this.sAppContext, (XmPushActionContainer)var1_1);
        if (var4_5 == null) {
            MyLog.e("receiving an un-recognized message. " + (Object)var1_1.action);
            return null;
        }
        MyLog.v("receive a message." + var4_5);
        var5_6 = var1_1.getAction();
        MyLog.warn("processing a message, action=" + var5_6);
        switch (.$SwitchMap$com$xiaomi$xmpush$thrift$ActionType[var5_6.ordinal()]) lbl-1000: // 2 sources:
        {
            do {
                default: lbl-1000: // 4 sources:
                {
                    do {
                        return null;
                        break;
                    } while (true);
                }
                break;
            } while (true);
            catch (TException var1_2) {
                MyLog.e(var1_2);
                MyLog.e("receive a message which action string is not valid. is the reg expired?");
                return null;
            }
            case 2: {
                var3_4 = (XmPushActionRegistrationResult)var4_5;
                if (var3_4.errorCode == 0) {
                    AppInfoHolder.getInstance(this.sAppContext).putRegIDAndSecret(var3_4.regId, var3_4.regSecret);
                }
                var1_1 = null;
                if (!TextUtils.isEmpty((CharSequence)var3_4.regId)) {
                    var1_1 = new ArrayList();
                    var1_1.add(var3_4.regId);
                }
                var1_1 = PushMessageHelper.generateCommandMessage("register", var1_1, var3_4.errorCode, var3_4.reason, null);
                PushServiceClient.getInstance(this.sAppContext).processPendRequest();
                return var1_1;
            }
            case 3: {
                if (((XmPushActionUnRegistrationResult)var4_5).errorCode == 0) {
                    AppInfoHolder.getInstance(this.sAppContext).clear();
                    MiPushClient.clearExtras(this.sAppContext);
                }
                PushMessageHandler.removeAllPushCallbackClass();
                ** continue;
            }
            case 1: {
                if (AppInfoHolder.getInstance(this.sAppContext).isPaused() && !var2_3) {
                    MyLog.warn("receive a message in pause state. drop it");
                    return null;
                }
                var8_7 = (XmPushActionSendMessage)var4_5;
                var7_8 = var8_7.getMessage();
                if (var7_8 == null) {
                    MyLog.e("receive an empty message without push content, drop it");
                    return null;
                }
                if (!var2_3) ** GOTO lbl47
                if (!MIPushNotificationHelper.isBusinessMessage(var1_1)) ** GOTO lbl69
                MiPushClient.reportIgnoreRegMessageClicked(this.sAppContext, var7_8.getId(), var1_1.getMetaInfo(), var1_1.packageName, var7_8.getAppId());
lbl47: // 3 sources:
                do {
                    if (var2_3) ** GOTO lbl51
                    if (TextUtils.isEmpty((CharSequence)var8_7.getAliasName()) || MiPushClient.aliasSetTime(this.sAppContext, var8_7.getAliasName()) >= 0) ** GOTO lbl71
                    MiPushClient.addAlias(this.sAppContext, var8_7.getAliasName());
lbl51: // 4 sources:
                    do {
                        var6_9 = null;
                        var4_5 = var5_6 = null;
                        if (var1_1.metaInfo != null) {
                            var4_5 = var5_6;
                            if (var1_1.metaInfo.getExtra() != null) {
                                var4_5 = var1_1.metaInfo.extra.get("jobkey");
                            }
                        }
                        var5_6 = var4_5;
                        if (TextUtils.isEmpty((CharSequence)var4_5)) {
                            var5_6 = var7_8.getId();
                        }
                        if (!var2_3 && PushMessageProcessor.isDuplicateMessage(this.sAppContext, (String)var5_6)) {
                            MyLog.warn("drop a duplicate message, key=" + (String)var5_6);
                            var3_4 = var6_9;
lbl64: // 4 sources:
                            do {
                                if (var1_1.getMetaInfo() == null && !var2_3) {
                                    this.ackMessage(var8_7, var1_1.getMetaInfo());
                                }
                                return var3_4;
                                break;
                            } while (true);
                        }
                        ** GOTO lbl74
                        break;
                    } while (true);
                    break;
                } while (true);
lbl69: // 1 sources:
                MiPushClient.reportMessageClicked(this.sAppContext, var7_8.getId(), var1_1.getMetaInfo(), var7_8.getAppId());
                ** continue;
lbl71: // 1 sources:
                if (TextUtils.isEmpty((CharSequence)var8_7.getTopic()) || MiPushClient.topicSubscribedTime(this.sAppContext, var8_7.getTopic()) >= 0) ** GOTO lbl51
                MiPushClient.addTopic(this.sAppContext, var8_7.getTopic());
                ** continue;
lbl74: // 1 sources:
                var4_5 = PushMessageHelper.generateMessage(var8_7, var1_1.getMetaInfo(), var2_3);
                if (var4_5.getPassThrough() == 0 && !var2_3 && MIPushNotificationHelper.isNotifyForeground(var4_5.getExtra())) {
                    MIPushNotificationHelper.notifyPushMessage(this.sAppContext, (XmPushActionContainer)var1_1, (byte[])var3_4);
                    return null;
                }
                MyLog.warn("receive a message, msgid=" + var7_8.getId() + ", jobkey=" + (String)var5_6);
                var3_4 = var4_5;
                if (!var2_3) ** GOTO lbl64
                var3_4 = var4_5;
                if (var4_5.getExtra() == null) ** GOTO lbl64
                var3_4 = var4_5;
                if (!var4_5.getExtra().containsKey("notify_effect")) ** continue;
                var5_6 = var4_5.getExtra();
                var3_4 = var5_6.get("notify_effect");
                if (MIPushNotificationHelper.isBusinessMessage((XmPushActionContainer)var1_1)) {
                    var1_1 = PushMessageProcessor.getNotificationMessageIntent(this.sAppContext, var1_1.packageName, var5_6);
                    if (var1_1 == null) {
                        MyLog.warn("Getting Intent fail from ignore reg message. ");
                        return null;
                    }
                    var3_4 = var7_8.getPayload();
                    if (!TextUtils.isEmpty((CharSequence)var3_4)) {
                        var1_1.putExtra("payload", (String)var3_4);
                    }
                    this.sAppContext.startActivity((Intent)var1_1);
lbl96: // 3 sources:
                    do {
                        return null;
                        break;
                    } while (true);
                }
                var1_1 = PushMessageProcessor.getNotificationMessageIntent(this.sAppContext, this.sAppContext.getPackageName(), var5_6);
                if (var1_1 == null) ** GOTO lbl96
                if (!var3_4.equals((Object)PushConstants.NOTIFICATION_CLICK_WEB_PAGE)) {
                    var1_1.putExtra("key_message", (Serializable)var4_5);
                }
                this.sAppContext.startActivity((Intent)var1_1);
                ** continue;
            }
            case 4: {
                var3_4 = (XmPushActionSubscriptionResult)var4_5;
                if (var3_4.errorCode == 0) {
                    MiPushClient.addTopic(this.sAppContext, var3_4.getTopic());
                }
                var1_1 = null;
                if (!TextUtils.isEmpty((CharSequence)var3_4.getTopic())) {
                    var1_1 = new ArrayList();
                    var1_1.add(var3_4.getTopic());
                }
                return PushMessageHelper.generateCommandMessage("subscribe-topic", var1_1, var3_4.errorCode, var3_4.reason, var3_4.getCategory());
            }
            case 5: {
                var3_4 = (XmPushActionUnSubscriptionResult)var4_5;
                if (var3_4.errorCode == 0) {
                    MiPushClient.removeTopic(this.sAppContext, var3_4.getTopic());
                }
                var1_1 = null;
                if (!TextUtils.isEmpty((CharSequence)var3_4.getTopic())) {
                    var1_1 = new ArrayList();
                    var1_1.add(var3_4.getTopic());
                }
                return PushMessageHelper.generateCommandMessage("unsubscibe-topic", var1_1, var3_4.errorCode, var3_4.reason, var3_4.getCategory());
            }
            case 6: {
                var4_5 = (XmPushActionCommandResult)var4_5;
                var5_6 = var4_5.getCmdName();
                var1_1 = var3_4 = var4_5.getCmdArgs();
                if (var4_5.errorCode != 0) ** GOTO lbl133
                if (TextUtils.equals((CharSequence)var5_6, (CharSequence)"accept-time") && var3_4 != null && var3_4.size() > 1) {
                    MiPushClient.addAcceptTime(this.sAppContext, (String)var3_4.get(0), (String)var3_4.get(1));
                    if ("00:00".equals(var3_4.get(0)) && "00:00".equals(var3_4.get(1))) {
                        AppInfoHolder.getInstance(this.sAppContext).setPaused(true);
lbl131: // 2 sources:
                        do {
                            var1_1 = this.getTimeForTimeZone(TimeZone.getTimeZone((String)"GMT+08"), TimeZone.getDefault(), (List<String>)var3_4);
lbl133: // 9 sources:
                            do {
                                return PushMessageHelper.generateCommandMessage((String)var5_6, var1_1, var4_5.errorCode, var4_5.reason, var4_5.getCategory());
                                break;
                            } while (true);
                            break;
                        } while (true);
                    }
                    AppInfoHolder.getInstance(this.sAppContext).setPaused(false);
                    ** continue;
                }
                if (!TextUtils.equals((CharSequence)var5_6, (CharSequence)"set-alias") || var3_4 == null || var3_4.size() <= 0) ** GOTO lbl141
                MiPushClient.addAlias(this.sAppContext, (String)var3_4.get(0));
                var1_1 = var3_4;
                ** GOTO lbl133
lbl141: // 1 sources:
                if (!TextUtils.equals((CharSequence)var5_6, (CharSequence)"unset-alias") || var3_4 == null || var3_4.size() <= 0) ** GOTO lbl145
                MiPushClient.removeAlias(this.sAppContext, (String)var3_4.get(0));
                var1_1 = var3_4;
                ** GOTO lbl133
lbl145: // 1 sources:
                if (!TextUtils.equals((CharSequence)var5_6, (CharSequence)"set-account") || var3_4 == null || var3_4.size() <= 0) ** GOTO lbl149
                MiPushClient.addAccount(this.sAppContext, (String)var3_4.get(0));
                var1_1 = var3_4;
                ** GOTO lbl133
lbl149: // 1 sources:
                var1_1 = var3_4;
                if (!TextUtils.equals((CharSequence)var5_6, (CharSequence)"unset-account")) ** GOTO lbl133
                var1_1 = var3_4;
                if (var3_4 == null) ** GOTO lbl133
                var1_1 = var3_4;
                if (var3_4.size() <= 0) ** GOTO lbl133
                MiPushClient.removeAccount(this.sAppContext, (String)var3_4.get(0));
                var1_1 = var3_4;
                ** continue;
            }
            case 7: 
        }
        var1_1 = (XmPushActionNotification)var4_5;
        if (!"registration id expired".equalsIgnoreCase(var1_1.type)) ** GOTO lbl163
        MiPushClient.reInitialize(this.sAppContext);
        ** GOTO lbl-1000
lbl163: // 1 sources:
        if (!"client_info_update_ok".equalsIgnoreCase(var1_1.type) || var1_1.getExtra() == null || !var1_1.getExtra().containsKey("app_version")) ** GOTO lbl-1000
        var1_1 = var1_1.getExtra().get("app_version");
        AppInfoHolder.getInstance(this.sAppContext).updateVersionName((String)var1_1);
        ** while (true)
    }

    private PushMessageHandler.PushMessageInterface processMessage(XmPushActionContainer serializable, byte[] object) {
        block10 : {
            try {
                object = PushContainerHelper.getResponseMessageBodyFromContainer(this.sAppContext, serializable);
                if (object != null) break block10;
            }
            catch (TException var1_2) {
                MyLog.e(var1_2);
                MyLog.e("message arrived: receive a message which action string is not valid. is the reg expired?");
                return null;
            }
            MyLog.e("message arrived: receiving an un-recognized message. " + (Object)((Object)serializable.action));
            return null;
        }
        MyLog.v("message arrived: receive a message." + object);
        ActionType actionType = serializable.getAction();
        MyLog.warn("message arrived: processing an arrived message, action=" + (Object)((Object)actionType));
        switch (actionType) {
            default: {
                return null;
            }
            case SendMessage: 
        }
        XmPushActionSendMessage xmPushActionSendMessage = (XmPushActionSendMessage)object;
        PushMessage pushMessage = xmPushActionSendMessage.getMessage();
        if (pushMessage == null) {
            MyLog.e("message arrived: receive an empty message without push content, drop it");
            return null;
        }
        object = actionType = null;
        if (serializable.metaInfo != null) {
            object = actionType;
            if (serializable.metaInfo.getExtra() != null) {
                object = serializable.metaInfo.extra.get("jobkey");
            }
        }
        serializable = PushMessageHelper.generateMessage(xmPushActionSendMessage, serializable.getMetaInfo(), false);
        serializable.setArrivedMessage(true);
        MyLog.warn("message arrived: receive a message, msgid=" + pushMessage.getId() + ", jobkey=" + (String)object);
        return serializable;
    }

    public List<String> getTimeForTimeZone(TimeZone timeZone, TimeZone timeZone2, List<String> list) {
        if (timeZone.equals((Object)timeZone2)) {
            return list;
        }
        long l = (timeZone.getRawOffset() - timeZone2.getRawOffset()) / 1000 / 60;
        long l2 = Long.parseLong((String)list.get(0).split(":")[0]);
        long l3 = Long.parseLong((String)list.get(0).split(":")[1]);
        long l4 = Long.parseLong((String)list.get(1).split(":")[0]);
        long l5 = Long.parseLong((String)list.get(1).split(":")[1]);
        l2 = (60 * l2 + l3 - l + 1440) % 1440;
        l = (60 * l4 + l5 - l + 1440) % 1440;
        timeZone = new ArrayList();
        timeZone.add((Object)String.format((String)"%1$02d:%2$02d", (Object[])new Object[]{l2 / 60, l2 % 60}));
        timeZone.add((Object)String.format((String)"%1$02d:%2$02d", (Object[])new Object[]{l / 60, l % 60}));
        return timeZone;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public PushMessageHandler.PushMessageInterface proccessIntent(Intent var1_1) {
        block26 : {
            block27 : {
                var5_6 = var1_1.getAction();
                MyLog.warn("receive an intent from server, action=" + (String)var5_6);
                var3_9 = var4_8 = var1_1.getStringExtra("mrt");
                if (var4_8 == null) {
                    var3_9 = Long.toString((long)System.currentTimeMillis());
                }
                if (!"com.xiaomi.mipush.RECEIVE_MESSAGE".equals(var5_6)) ** GOTO lbl55
                var4_8 = var1_1.getByteArrayExtra("mipush_payload");
                var2_10 = var1_1.getBooleanExtra("mipush_notified", false);
                if (var4_8 == null) {
                    MyLog.e("receiving an empty message, drop");
                    return null;
                }
                var5_6 = new XmPushActionContainer();
                XmPushThriftSerializeUtils.convertByteArrayToThriftObject(var5_6, (byte[])var4_8);
                var6_11 = AppInfoHolder.getInstance(this.sAppContext);
                var1_1 = var5_6.getMetaInfo();
                if (var5_6.getAction() == ActionType.SendMessage && var1_1 != null && !var6_11.isPaused() && !var2_10) {
                    if (var1_1 != null) {
                        var5_6.getMetaInfo().putToExtra("mrt", (String)var3_9);
                        var5_6.getMetaInfo().putToExtra("mat", Long.toString((long)System.currentTimeMillis()));
                    }
                    this.ackMessage((XmPushActionContainer)var5_6);
                }
                if (var5_6.getAction() != ActionType.SendMessage || var5_6.isEncryptAction()) ** GOTO lbl-1000
                if (!MIPushNotificationHelper.isBusinessMessage((XmPushActionContainer)var5_6)) {
                    var3_9 = var5_6.getPackageName();
                    var1_1 = var1_1 != null ? var1_1.getId() : "";
                    MyLog.warn(String.format((String)"drop an un-encrypted messages. %1$s, %2$s", (Object[])new Object[]{var3_9, var1_1}));
                    return null;
                }
                if (var2_10 && var1_1.getExtra() != null && var1_1.getExtra().containsKey("notify_effect")) lbl-1000: // 2 sources:
                {
                    if (var6_11.appRegistered() || var5_6.action == ActionType.Registration) break block26;
                    if (MIPushNotificationHelper.isBusinessMessage((XmPushActionContainer)var5_6)) {
                        return this.processMessage((XmPushActionContainer)var5_6, var2_10, (byte[])var4_8);
                    }
                    break block27;
                }
                MyLog.warn(String.format((String)"drop an un-encrypted messages. %1$s, %2$s", (Object[])new Object[]{var5_6.getPackageName(), var1_1.getId()}));
                return null;
            }
            try {
                MyLog.e("receive message without registration. need unregister or re-register!");
                return null;
            }
            catch (TException var1_2) {
                MyLog.e(var1_2);
                return null;
            }
            catch (Exception var1_3) {
                MyLog.e(var1_3);
                return null;
            }
        }
        if (var6_11.appRegistered() == false) return this.processMessage((XmPushActionContainer)var5_6, var2_10, (byte[])var4_8);
        if (var6_11.invalidated() == false) return this.processMessage((XmPushActionContainer)var5_6, var2_10, (byte[])var4_8);
        if (var5_6.action == ActionType.UnRegistration) {
            var6_11.clear();
            MiPushClient.clearExtras(this.sAppContext);
            PushMessageHandler.removeAllPushCallbackClass();
            return null;
        }
        MiPushClient.unregisterPush(this.sAppContext);
        return null;
lbl55: // 1 sources:
        if ("com.xiaomi.mipush.ERROR".equals(var5_6)) {
            var3_9 = new MiPushCommandMessage();
            var4_8 = new XmPushActionContainer();
            var5_6 = var1_1.getByteArrayExtra("mipush_payload");
            if (var5_6 != null) {
                XmPushThriftSerializeUtils.convertByteArrayToThriftObject(var4_8, (byte[])var5_6);
            }
        } else {
            if ("com.xiaomi.mipush.MESSAGE_ARRIVED".equals(var5_6) == false) return null;
            if ((var1_1 = (Object)var1_1.getByteArrayExtra("mipush_payload")) == null) {
                MyLog.e("message arrived: receiving an empty message, drop");
                return null;
            }
            var3_9 = new XmPushActionContainer();
            try {
                XmPushThriftSerializeUtils.convertByteArrayToThriftObject(var3_9, (byte[])var1_1);
                var4_8 = AppInfoHolder.getInstance(this.sAppContext);
                var3_9.getMetaInfo();
                if (!var4_8.appRegistered()) {
                    if (MIPushNotificationHelper.isBusinessMessage((XmPushActionContainer)var3_9)) {
                        MyLog.e("message arrived: receive ignore reg message invalid!");
                        return null;
                    }
                    MyLog.e("message arrived: receive message without registration. need unregister or re-register!");
                    return null;
                }
                if (var4_8.appRegistered() == false) return this.processMessage((XmPushActionContainer)var3_9, (byte[])var1_1);
                if (var4_8.invalidated() == false) return this.processMessage((XmPushActionContainer)var3_9, (byte[])var1_1);
                MyLog.e("message arrived: app info is invalidated");
                return null;
            }
            catch (TException var1_4) {
                MyLog.e(var1_4);
                return null;
            }
            catch (Exception var1_5) {
                MyLog.e(var1_5);
                return null;
            }
            catch (TException var5_7) {}
        }
        var3_9.setCommand(String.valueOf((Object)var4_8.getAction()));
        var3_9.setResultCode(var1_1.getIntExtra("mipush_error_code", 0));
        var3_9.setReason(var1_1.getStringExtra("mipush_error_msg"));
        MyLog.e("receive a error message. code = " + var1_1.getIntExtra("mipush_error_code", 0) + ", msg= " + var1_1.getStringExtra("mipush_error_msg"));
        return var3_9;
    }

}

