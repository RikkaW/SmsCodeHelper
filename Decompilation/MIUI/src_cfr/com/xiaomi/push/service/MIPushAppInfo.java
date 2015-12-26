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
 *  java.util.ArrayList
 */
package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import java.util.ArrayList;
import java.util.List;

public class MIPushAppInfo {
    private static MIPushAppInfo sInstance = null;
    private Context appContext;
    private List<String> unRegisteredPkg = new ArrayList();

    private MIPushAppInfo(Context arrstring) {
        this.appContext = arrstring.getApplicationContext();
        if (this.appContext == null) {
            this.appContext = arrstring;
        }
        for (String string2 : this.appContext.getSharedPreferences("mipush_app_info", 0).getString("unregistered_pkg_names", "").split(",")) {
            if (!TextUtils.isEmpty((CharSequence)string2)) continue;
            this.unRegisteredPkg.add(string2);
        }
    }

    public static MIPushAppInfo getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MIPushAppInfo(context);
        }
        return sInstance;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void addUnRegisteredPkg(String string2) {
        List<String> list = this.unRegisteredPkg;
        synchronized (list) {
            if (!this.unRegisteredPkg.contains(string2)) {
                this.unRegisteredPkg.add(string2);
                string2 = XMStringUtils.join(this.unRegisteredPkg, ",");
                this.appContext.getSharedPreferences("mipush_app_info", 0).edit().putString("unregistered_pkg_names", string2).commit();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean isUnRegistered(String string2) {
        List<String> list = this.unRegisteredPkg;
        synchronized (list) {
            return this.unRegisteredPkg.contains(string2);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void removeUnRegisteredPkg(String string2) {
        List<String> list = this.unRegisteredPkg;
        synchronized (list) {
            if (this.unRegisteredPkg.contains(string2)) {
                this.unRegisteredPkg.remove(string2);
                string2 = XMStringUtils.join(this.unRegisteredPkg, ",");
                this.appContext.getSharedPreferences("mipush_app_info", 0).edit().putString("unregistered_pkg_names", string2).commit();
            }
            return;
        }
    }
}

