/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.IntentService
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Handler
 *  android.text.TextUtils
 *  android.util.Log
 *  android.util.Pair
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.HashSet
 *  miui.telephony.SubscriptionManager
 */
package com.android.mms.ui;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.android.mms.LogTag;
import com.android.mms.MmsApp;
import com.android.mms.data.Conversation;
import com.android.mms.transaction.SmsMessageSender;
import com.android.mms.ui.ComposeMessageRouterActivity;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.transaction.MxTaskService;
import java.util.Collection;
import java.util.HashSet;
import miui.telephony.SubscriptionManager;

public class NoConfirmationSendService
extends IntentService {
    public static MxIdCache.MxCacheStatusListener mListener;
    public static HashSet<Pair<String, Pair<String, Integer>>> mPendingMessages;
    private Handler mUiHandler = new Handler();

    static {
        mPendingMessages = new HashSet();
        mListener = new MxIdCache.MxCacheStatusListener(){

            @Override
            public void onMxIdAdded(String string2, String string3) {
            }

            @Override
            public void onMxIdOffline(String string2, String string3) {
                NoConfirmationSendService.startSendPendingMessage(string3, false, 0);
                NoConfirmationSendService.startSendPendingMessage(string3, false, 1);
            }

            @Override
            public void onMxIdOnline(String string2, String string3) {
                NoConfirmationSendService.startSendPendingMessage(string3, true, 0);
                NoConfirmationSendService.startSendPendingMessage(string3, true, 1);
            }
        };
    }

    public NoConfirmationSendService() {
        super(NoConfirmationSendService.class.getName());
        this.setIntentRedelivery(true);
    }

    public NoConfirmationSendService(String string2) {
        super(string2);
        this.setIntentRedelivery(true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void handleSend(String string2, int n, String string3) {
        boolean bl = true;
        MxIdCache.MxIdCacheItem mxIdCacheItem = MxIdCache.get(string2, true);
        if (mxIdCacheItem == null) {
            this.sendAfterQueryStatus(string2, n, string3);
            return;
        }
        if (mxIdCacheItem.isExpired()) {
            this.sendAfterQueryStatus(string2, n, string3);
            return;
        }
        if (!mxIdCacheItem.allowSms() || !MxActivateService.isMxEnabled((Context)this, n)) {
            bl = false;
        }
        this.send(string2, bl, n, string3);
    }

    public static void startSendPendingMessage(String string2, boolean bl, int n) {
        Intent intent = new Intent("android.intent.action.SEND_AND_REMOVE_PENNDING_MESSAGE");
        intent.putExtra("extra_address", string2);
        intent.putExtra("extra_usemx", bl);
        SubscriptionManager.putSlotIdExtra((Intent)intent, (int)n);
        intent.setPackage(MmsApp.getApp().getPackageName());
        MmsApp.getApp().startService(intent);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void onHandleIntent(Intent var1_1) {
        LogTag.debug("NoConfirmationSendService onHandleIntent", new Object[0]);
        if (var1_1 == null) {
            LogTag.warn("Null intent. Bail.", new Object[0]);
            return;
        }
        var6_2 = var1_1.getAction();
        if (!"com.android.mms.intent.action.SENDTO_NO_CONFIRMATION".equals((Object)var6_2) && !"android.intent.action.RESPOND_VIA_MESSAGE".equals((Object)var6_2)) ** GOTO lbl40
        var7_3 = var1_1.getExtras();
        if (var7_3 == null) {
            LogTag.warn("Called to send SMS but no extras", new Object[0]);
            return;
        }
        var6_2 = var7_3.getString("android.intent.extra.TEXT");
        var3_4 = MSimUtils.getSlotIdFromBundle(var7_3);
        LogTag.debug("onHandleIntent  slotId = " + var3_4, new Object[0]);
        if (var3_4 < 0) ** GOTO lbl-1000
        var2_5 = var3_4;
        if (!MSimUtils.isSimInserted(var3_4)) lbl-1000: // 2 sources:
        {
            var2_5 = MSimUtils.getInsertedSlotId();
            LogTag.debug("inserted slotId = " + var2_5, new Object[0]);
            if (var2_5 == -1) {
                LogTag.error("No usable sim card", new Object[0]);
                return;
            }
        }
        if (TextUtils.isEmpty((CharSequence)(var8_7 = Conversation.getRecipients(var1_1.getData())))) {
            LogTag.warn("Recipient(s) cannot be empty", new Object[0]);
            return;
        }
        if (var7_3.getBoolean("showUI", false)) {
            var1_1.addFlags(268435456);
            ComposeMessageRouterActivity.route((Context)this, (Intent)var1_1);
            return;
        }
        if (TextUtils.isEmpty((CharSequence)var6_2)) {
            LogTag.warn("Message cannot be empty", new Object[0]);
            return;
        }
        var1_1 = TextUtils.split((String)var8_7, (String)";");
        var4_8 = var1_1.length;
        var3_4 = 0;
        while (var3_4 < var4_8) {
            this.handleSend(var1_1[var3_4], var2_5, var6_2);
            ++var3_4;
        }
        return;
lbl40: // 1 sources:
        if ("android.intent.action.SEND_AND_REMOVE_PENNDING_MESSAGE".equals((Object)var6_2) == false) return;
        var6_2 = var1_1.getStringExtra("extra_address");
        var2_6 = MSimUtils.getSlotIdFromIntent((Intent)var1_1);
        var5_9 = var1_1.getBooleanExtra("extra_usemx", false) != false && MxActivateService.isMxEnabled((Context)this, var2_6) != false;
        if (TextUtils.isEmpty((CharSequence)var6_2) != false) return;
        if (NoConfirmationSendService.mPendingMessages.size() <= 0) return;
        this.sendAndRemovePendingMessage(var6_2, var5_9, var2_6);
    }

    protected void send(String object, boolean bl, int n, String string2) {
        object = new SmsMessageSender(this.getBaseContext(), new String[]{object}, string2, 0, 0, bl, n);
        try {
            object.sendMessage();
            return;
        }
        catch (Exception var1_2) {
            Log.e((String)"Mms/NoConfirmationSendService", (String)("Failed to send SMS message, threadId=" + 0), (Throwable)var1_2);
            return;
        }
    }

    protected void sendAfterQueryStatus(final String string2, final int n, String string3) {
        if (mPendingMessages.size() == 0) {
            MxIdCache.addStatusListener(mListener);
        }
        mPendingMessages.add((Object)Pair.create((Object)string2, (Object)Pair.create((Object)string3, (Object)n)));
        MxTaskService.queryStatus((Context)this, string2);
        this.mUiHandler.postDelayed(new Runnable(){

            @Override
            public void run() {
                NoConfirmationSendService.startSendPendingMessage(string2, false, n);
            }
        }, 10000);
    }

    protected void sendAndRemovePendingMessage(String string2, boolean bl, int n) {
        HashSet hashSet = new HashSet();
        for (Pair pair : mPendingMessages) {
            if (((String)pair.first).equals((Object)string2)) {
                if ((Integer)((Pair)pair.second).second == n) {
                    this.send((String)pair.first, bl, (Integer)((Pair)pair.second).second, (String)((Pair)pair.second).first);
                    continue;
                }
                hashSet.add((Object)pair);
                continue;
            }
            hashSet.add((Object)pair);
        }
        mPendingMessages.clear();
        if (!hashSet.isEmpty()) {
            mPendingMessages.addAll((Collection)hashSet);
            return;
        }
        MxIdCache.removeStatusListener(mListener);
    }

}

