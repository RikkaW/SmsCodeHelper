/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.PackageManager
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushServiceReceiver;
import com.xiaomi.xmpush.thrift.PushMessage;
import com.xiaomi.xmpush.thrift.PushMetaInfo;
import com.xiaomi.xmpush.thrift.XmPushActionSendMessage;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PushMessageHelper {
    private static int pushMode = 0;

    public static MiPushCommandMessage generateCommandMessage(String string2, List<String> list, long l, String string3, String string4) {
        MiPushCommandMessage miPushCommandMessage = new MiPushCommandMessage();
        miPushCommandMessage.setCommand(string2);
        miPushCommandMessage.setCommandArguments(list);
        miPushCommandMessage.setResultCode(l);
        miPushCommandMessage.setReason(string3);
        miPushCommandMessage.setCategory(string4);
        return miPushCommandMessage;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static MiPushMessage generateMessage(XmPushActionSendMessage xmPushActionSendMessage, PushMetaInfo pushMetaInfo, boolean bl) {
        MiPushMessage miPushMessage = new MiPushMessage();
        miPushMessage.setMessageId(xmPushActionSendMessage.getId());
        if (!TextUtils.isEmpty((CharSequence)xmPushActionSendMessage.getAliasName())) {
            miPushMessage.setMessageType(1);
            miPushMessage.setAlias(xmPushActionSendMessage.getAliasName());
        } else if (!TextUtils.isEmpty((CharSequence)xmPushActionSendMessage.getTopic())) {
            miPushMessage.setMessageType(2);
            miPushMessage.setTopic(xmPushActionSendMessage.getTopic());
        } else if (!TextUtils.isEmpty((CharSequence)xmPushActionSendMessage.getUserAccount())) {
            miPushMessage.setMessageType(3);
            miPushMessage.setUserAccount(xmPushActionSendMessage.getUserAccount());
        } else {
            miPushMessage.setMessageType(0);
        }
        miPushMessage.setCategory(xmPushActionSendMessage.getCategory());
        if (xmPushActionSendMessage.getMessage() != null) {
            miPushMessage.setContent(xmPushActionSendMessage.getMessage().getPayload());
        }
        if (pushMetaInfo != null) {
            if (TextUtils.isEmpty((CharSequence)miPushMessage.getMessageId())) {
                miPushMessage.setMessageId(pushMetaInfo.getId());
            }
            if (TextUtils.isEmpty((CharSequence)miPushMessage.getTopic())) {
                miPushMessage.setTopic(pushMetaInfo.getTopic());
            }
            miPushMessage.setDescription(pushMetaInfo.getDescription());
            miPushMessage.setTitle(pushMetaInfo.getTitle());
            miPushMessage.setNotifyType(pushMetaInfo.getNotifyType());
            miPushMessage.setNotifyId(pushMetaInfo.getNotifyId());
            miPushMessage.setPassThrough(pushMetaInfo.getPassThrough());
            miPushMessage.setExtra(pushMetaInfo.getExtra());
        }
        miPushMessage.setNotified(bl);
        return miPushMessage;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static int getPushMode(Context context) {
        if (pushMode != 0) return pushMode;
        if (PushMessageHelper.isUseCallbackPushMode(context)) {
            PushMessageHelper.setPushMode(1);
            return pushMode;
        }
        PushMessageHelper.setPushMode(2);
        return pushMode;
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

    public static boolean isUseCallbackPushMode(Context context) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setClassName(context.getPackageName(), "com.xiaomi.mipush.sdk.PushServiceReceiver");
        return PushMessageHelper.isIntentAvailable(context, intent);
    }

    public static void sendCommandMessageBroadcast(Context context, MiPushCommandMessage miPushCommandMessage) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setPackage(context.getPackageName());
        intent.putExtra("message_type", 3);
        intent.putExtra("key_command", (Serializable)miPushCommandMessage);
        new PushServiceReceiver().onReceive(context, intent);
    }

    private static void setPushMode(int n) {
        pushMode = n;
    }
}

