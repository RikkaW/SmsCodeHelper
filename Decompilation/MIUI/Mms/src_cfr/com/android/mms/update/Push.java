/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.accounts.Account
 *  android.content.Context
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  miui.os.Build
 */
package com.android.mms.update;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.ThreadPool;
import com.android.mms.util.XiaomiAccount;
import com.xiaomi.mipush.sdk.MiPushClient;
import miui.os.Build;

public class Push {
    public static String DEFAULT_TOPIC = Build.IS_ALPHA_BUILD ? "template-sms-alpha" : "template-sms-dev";

    public static void initiate(Context context) {
        if (MessageUtils.allowPush()) {
            Log.d((String)"Mms_Push", (String)"push initiate");
            MiPushClient.registerPush(context.getApplicationContext(), "2882303761517322150", "5921732233150");
        }
    }

    public static void onRegisterSucceeded(final Context context) {
        ThreadPool.execute(new Runnable(){

            @Override
            public void run() {
                Push.subscribeDefaultTopics(context);
                Push.setAliasByUserId(context, null);
            }
        });
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void setAliasByUserId(Context context, Account account) {
        Account account2 = account;
        if (account == null) {
            account2 = XiaomiAccount.getAccount(context);
        }
        if (account2 == null || TextUtils.isEmpty((CharSequence)account2.name)) return;
        try {
            MiPushClient.setAlias(context, account2.name, null);
            return;
        }
        catch (NullPointerException var0_1) {
            Log.d((String)"Mms_Push", (String)"mipush sdk is throwing NPE!");
            var0_1.printStackTrace();
            return;
        }
    }

    public static void subscribeDefaultTopics(Context context) {
        try {
            MiPushClient.subscribe(context, DEFAULT_TOPIC, null);
            return;
        }
        catch (NullPointerException var0_1) {
            Log.e((String)"Mms_Push", (String)"Mipush sdk is throwing NPE! ");
            var0_1.printStackTrace();
            return;
        }
    }

}

