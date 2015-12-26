/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Locale
 */
package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.push.service.ClientEventDispatcher;
import com.xiaomi.push.service.PushClientsManager;
import com.xiaomi.push.service.XMPushService;
import java.util.Locale;

public class MIPushAccount {
    public final String account;
    protected final String appId;
    protected final String appToken;
    protected final int envType;
    protected final String packageName;
    protected final String security;
    protected final String token;

    public MIPushAccount(String string2, String string3, String string4, String string5, String string6, String string7, int n) {
        this.account = string2;
        this.token = string3;
        this.security = string4;
        this.appId = string5;
        this.appToken = string6;
        this.packageName = string7;
        this.envType = n;
    }

    private static boolean isMIUIPush(Context context) {
        return context.getPackageName().equals((Object)"com.xiaomi.xmsf");
    }

    /*
     * Enabled aggressive block sorting
     */
    public PushClientsManager.ClientLoginInfo toClientLoginInfo(XMPushService xMPushService) {
        PushClientsManager.ClientLoginInfo clientLoginInfo = new PushClientsManager.ClientLoginInfo(xMPushService);
        clientLoginInfo.pkgName = xMPushService.getPackageName();
        clientLoginInfo.userId = this.account;
        clientLoginInfo.security = this.security;
        clientLoginInfo.token = this.token;
        clientLoginInfo.chid = "5";
        clientLoginInfo.authMethod = "XMPUSH-PASS";
        clientLoginInfo.kick = false;
        clientLoginInfo.clientExtra = "sdk_ver:7";
        String string2 = MIPushAccount.isMIUIPush((Context)xMPushService) ? "1000271" : this.appId;
        clientLoginInfo.cloudExtra = String.format((String)"%1$s:%2$s,%3$s:%4$s", (Object[])new Object[]{"appid", string2, "locale", Locale.getDefault().toString()});
        clientLoginInfo.mClientEventDispatcher = xMPushService.getClientEventDispatcher();
        return clientLoginInfo;
    }
}

