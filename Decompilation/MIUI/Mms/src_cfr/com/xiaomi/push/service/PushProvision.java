/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.net.Uri
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.provider.Settings
 *  android.provider.Settings$Global
 *  android.provider.Settings$Secure
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

public class PushProvision {
    private static PushProvision sInstance;
    private Context mContext;
    private int mProvisioned = 0;

    private PushProvision(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static PushProvision getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PushProvision(context);
        }
        return sInstance;
    }

    public boolean checkProvisioned() {
        if ("@SHIP.TO.2A2FE0D7@".contains((CharSequence)"xmsf") || "@SHIP.TO.2A2FE0D7@".contains((CharSequence)"xiaomi") || "@SHIP.TO.2A2FE0D7@".contains((CharSequence)"miui")) {
            return true;
        }
        return false;
    }

    @SuppressLint(value={"NewApi"})
    public int getProvisioned() {
        if (this.mProvisioned != 0) {
            return this.mProvisioned;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            this.mProvisioned = Settings.Global.getInt((ContentResolver)this.mContext.getContentResolver(), (String)"device_provisioned", (int)0);
            return this.mProvisioned;
        }
        this.mProvisioned = Settings.Secure.getInt((ContentResolver)this.mContext.getContentResolver(), (String)"device_provisioned", (int)0);
        return this.mProvisioned;
    }

    @SuppressLint(value={"NewApi"})
    public Uri getProvisionedUri() {
        if (Build.VERSION.SDK_INT >= 17) {
            return Settings.Global.getUriFor((String)"device_provisioned");
        }
        return Settings.Secure.getUriFor((String)"device_provisioned");
    }
}

