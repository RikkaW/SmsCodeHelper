/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.Random
 *  java.util.concurrent.TimeUnit
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.android.mms.update;

import android.content.Context;
import android.util.Log;
import com.android.mms.update.Network;
import com.android.mms.update.Push;
import com.android.mms.update.TemplateRequest;
import com.android.mms.update.UpdateManager;
import com.android.mms.util.MiStatSdkHelper;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class MmsPushMessageReceiver
extends PushMessageReceiver {
    private static final String TAG = "MmsPushMessageReceiver";

    private long getRandomDelay(int n) {
        return new Random().nextInt((int)TimeUnit.HOURS.toMillis((long)n));
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onCommandResult(Context object, MiPushCommandMessage miPushCommandMessage) {
        String string2 = miPushCommandMessage.getCommand();
        Log.v((String)"MmsPushMessageReceiver", (String)("onCommandResult, command:" + miPushCommandMessage));
        if ("register".equals((Object)string2)) {
            if (miPushCommandMessage.getResultCode() != 0) {
                Log.e((String)"MmsPushMessageReceiver", (String)"failed to register");
                return;
            }
            Push.onRegisterSucceeded((Context)object);
            return;
        }
        if ("set-alias".equals((Object)string2)) {
            if (miPushCommandMessage.getResultCode() == 0) {
                Log.d((String)"MmsPushMessageReceiver", (String)"successfully set alias");
                return;
            }
            Log.d((String)"MmsPushMessageReceiver", (String)"failed to set alias");
            return;
        }
        if ("unset-alias".equals((Object)string2)) {
            if (miPushCommandMessage.getResultCode() == 0) {
                Log.d((String)"MmsPushMessageReceiver", (String)"successfully unset alias");
                return;
            }
            Log.d((String)"MmsPushMessageReceiver", (String)"failed to unset alias");
            return;
        }
        if ("subscribe-topic".equals((Object)string2)) {
            if (miPushCommandMessage.getResultCode() == 0) {
                Log.d((String)"MmsPushMessageReceiver", (String)"successfully subscribed topic: ");
                return;
            }
            Log.e((String)"MmsPushMessageReceiver", (String)("failed to subscribe topic:" + miPushCommandMessage.getCommandArguments()));
            return;
        }
        if (!"unsubscibe-topic".equals((Object)string2)) return;
        {
            if (miPushCommandMessage.getResultCode() == 0) {
                object = miPushCommandMessage.getCommandArguments().get(0);
                Log.d((String)"MmsPushMessageReceiver", (String)("successfully unsubscribed topic:" + (String)object));
                return;
            }
        }
        Log.e((String)"MmsPushMessageReceiver", (String)("failed to unsubscribe topic:" + miPushCommandMessage.getCommandArguments()));
    }

    @Override
    public void onReceiveMessage(Context context, MiPushMessage miPushMessage) {
        Log.d((String)"MmsPushMessageReceiver", (String)":::onReceiveMessage: ");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void onReceivePassThroughMessage(Context object, MiPushMessage miPushMessage) {
        Log.d((String)"MmsPushMessageReceiver", (String)":::onReceivePassThroughMessage: ");
        object = miPushMessage.getContent();
        long l = 0;
        boolean bl = true;
        boolean bl2 = false;
        boolean bl3 = false;
        boolean bl4 = bl2;
        long l2 = l;
        boolean bl5 = bl;
        try {
            boolean bl6;
            miPushMessage = new JSONObject((String)object);
            bl4 = bl2;
            l2 = l;
            bl5 = bl;
            l = miPushMessage.optLong("version", 0);
            bl4 = bl2;
            l2 = l;
            bl5 = bl;
            bl = miPushMessage.optBoolean("wifiOnly", true);
            bl4 = bl2;
            l2 = l;
            bl5 = bl;
            bl4 = bl2 = miPushMessage.optBoolean("forced", false);
            l2 = l;
            bl5 = bl;
            bl4 = bl6 = miPushMessage.optBoolean("guideUser", false);
            bl5 = bl;
            l2 = l;
            bl = bl4;
            bl4 = bl2;
        }
        catch (JSONException var2_3) {
            var2_3.printStackTrace();
            bl = bl3;
        }
        if (l2 <= (l = TemplateRequest.getLocalVersion())) {
            MiStatSdkHelper.recordPushmessageStatus(1);
            Log.w((String)"MmsPushMessageReceiver", (String)(" pushed version: " + l2 + " is not newer than local: " + l));
            return;
        }
        if (Network.allowUpdate(bl5, bl4)) {
            l2 = this.getRandomDelay(2);
            Log.d((String)"MmsPushMessageReceiver", (String)("delay " + l2 + " milli secs"));
            l = System.currentTimeMillis();
            MiStatSdkHelper.recordPushmessageStatus(0);
            UpdateManager.requestUpdateAt(l2 + l, (String)object);
            return;
        } else {
            Log.v((String)"MmsPushMessageReceiver", (String)(" Not allowing update, wifiOnly is: " + bl5));
            MiStatSdkHelper.recordPushmessageStatus(2);
            if (!bl) return;
            {
                Log.v((String)"MmsPushMessageReceiver", (String)" guide user to allow");
                return;
            }
        }
    }
}

