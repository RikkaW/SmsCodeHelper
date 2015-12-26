/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.accounts.Account
 *  android.accounts.AccountManager
 *  android.accounts.AccountManagerCallback
 *  android.accounts.AccountManagerFuture
 *  android.content.Context
 *  android.os.Bundle
 *  android.os.Handler
 *  android.text.TextUtils
 *  com.xiaomi.accountsdk.account.data.ExtendedAuthToken
 *  java.lang.Object
 *  java.lang.String
 *  miui.yellowpage.MiPubUtils
 */
package com.xiaomi.mms.mx.fw.faccount;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.mms.mx.fw.HmsPersister;
import com.xiaomi.mms.mx.fw.faccount.JIDUtils;
import com.xiaomi.mms.mx.utils.Log;
import miui.yellowpage.MiPubUtils;

public class XiaoMiJIDUtils {
    public static ExtendedAuthToken getAuthtoken(Context context, String string2) {
        Object var2_2 = null;
        string2 = XiaoMiJIDUtils.getAuthtokenString(context, string2);
        context = var2_2;
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            context = ExtendedAuthToken.parse((String)string2);
        }
        return context;
    }

    public static String getAuthtokenString(Context object, String string2) {
        block4 : {
            Account account = XiaoMiJIDUtils.getXiaomiAccount((Context)object);
            Object var2_4 = null;
            try {
                string2 = AccountManager.get((Context)object).getAuthToken(account, string2, null, false, null, null);
                object = var2_4;
                if (string2 == null) break block4;
            }
            catch (Exception var0_1) {
                Log.d("XiaoMiJID", var0_1.toString());
                return null;
            }
            string2 = (Bundle)string2.getResult();
            object = var2_4;
            if (string2 == null) break block4;
            object = string2.getString("authtoken");
        }
        return object;
    }

    public static String getUserIDWithResId() {
        return XiaoMiJIDUtils.getUserIDWithResId(HmsPersister.getContext());
    }

    public static String getUserIDWithResId(Context context) {
        return XiaoMiJIDUtils.getUserIDWithResId(context, XiaoMiJIDUtils.tryGetFullSmtpName(context));
    }

    private static String getUserIDWithResId(Context object, String string2) {
        string2 = JIDUtils.getFullSmtpName(string2);
        object = MiPubUtils.getDeviceId((Context)object);
        return string2 + "/" + (String)object;
    }

    public static Account getXiaomiAccount(Context context) {
        Object var1_1 = null;
        Account[] arraccount = AccountManager.get((Context)context).getAccountsByType("com.xiaomi");
        context = var1_1;
        if (arraccount != null) {
            context = var1_1;
            if (arraccount.length > 0) {
                context = arraccount[0];
            }
        }
        return context;
    }

    public static boolean hasAccount(Context context) {
        if ((context = XiaoMiJIDUtils.getXiaomiAccount(context)) != null && !TextUtils.isEmpty((CharSequence)context.name)) {
            return true;
        }
        return false;
    }

    public static String tryGetFullSmtpName(Context object) {
        Object var1_1 = null;
        Account account = XiaoMiJIDUtils.getXiaomiAccount((Context)object);
        object = var1_1;
        if (account != null) {
            object = JIDUtils.getFullSmtpName(account.name);
        }
        return object;
    }

    public static String tryGetPSecurity(Context object, String string2) {
        Object var2_2 = null;
        string2 = XiaoMiJIDUtils.getAuthtoken((Context)object, string2);
        object = var2_2;
        if (string2 != null) {
            object = string2.security;
        }
        return object;
    }

    public static String tryGetServiceToken(Context object, String string2) {
        Object var2_2 = null;
        string2 = XiaoMiJIDUtils.getAuthtoken((Context)object, string2);
        object = var2_2;
        if (string2 != null) {
            object = string2.authToken;
        }
        return object;
    }
}

