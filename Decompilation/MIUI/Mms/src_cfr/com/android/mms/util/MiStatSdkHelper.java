/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  com.xiaomi.mistatistic.sdk.MiStatInterface
 *  java.lang.Object
 *  java.lang.String
 *  miui.os.Build
 */
package com.android.mms.util;

import android.app.Activity;
import android.content.Context;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import miui.os.Build;

public class MiStatSdkHelper {
    private static final boolean sEnable;

    /*
     * Enabled aggressive block sorting
     */
    static {
        boolean bl = !Build.IS_INTERNATIONAL_BUILD && !Build.IS_CTA_BUILD;
        sEnable = bl;
    }

    public static void recordBottomMenuClick(String string2, String string3) {
        if (!sEnable) {
            return;
        }
        try {
            StringBuilder stringBuilder = new StringBuilder("menu_click");
            stringBuilder.append("_");
            stringBuilder.append(string2);
            stringBuilder.append("_");
            stringBuilder.append(string3);
            MiStatInterface.recordCountEvent((String)"sp_menu_category", (String)stringBuilder.toString());
            return;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void recordBottomMenuShown(String string2) {
        if (!sEnable) {
            return;
        }
        try {
            StringBuilder stringBuilder = new StringBuilder("menu_shown");
            stringBuilder.append("_");
            stringBuilder.append(string2);
            MiStatInterface.recordCountEvent((String)"sp_menu_category", (String)stringBuilder.toString());
            return;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void recordNetworkAllowRecommend(String string2) {
        if (!sEnable) {
            return;
        }
        try {
            MiStatInterface.recordCountEvent((String)"network_recommend", (String)string2);
            return;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void recordPageEnd() {
        if (!sEnable) {
            return;
        }
        try {
            MiStatInterface.recordPageEnd();
            return;
        }
        catch (Exception var0) {
            var0.printStackTrace();
            return;
        }
    }

    public static void recordPageStart(Activity activity, String string2) {
        if (!sEnable) {
            return;
        }
        try {
            MiStatInterface.recordPageStart((Activity)activity, (String)string2);
            return;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void recordPushmessageStatus(int n) {
        if (!sEnable) {
            return;
        }
        try {
            StringBuilder stringBuilder = new StringBuilder("pushmessage_status");
            stringBuilder.append(n);
            MiStatInterface.recordCountEvent((String)"understand_category", (String)stringBuilder.toString());
            return;
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    public static void recordSearch(String string2) {
        if (!sEnable) {
            return;
        }
        try {
            MiStatInterface.recordCountEvent((String)"search", (String)string2);
            return;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void recordStringPropertyEvent(String string2, String string3, String string4) {
        if (!sEnable) {
            return;
        }
        try {
            MiStatInterface.recordStringPropertyEvent((String)string2, (String)string3, (String)string4);
            return;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void recordUnderstandButtonClick(int n, int n2) {
        if (!sEnable) {
            return;
        }
        try {
            StringBuilder stringBuilder = new StringBuilder("button_click");
            stringBuilder.append(n);
            stringBuilder.append("_");
            stringBuilder.append(n2);
            MiStatInterface.recordCountEvent((String)"understand_category", (String)stringBuilder.toString());
            return;
        }
        catch (Exception var2_3) {
            var2_3.printStackTrace();
            return;
        }
    }

    public static void recordUnderstandButtonShown(int n) {
        if (!sEnable) {
            return;
        }
        try {
            StringBuilder stringBuilder = new StringBuilder("button_shown");
            stringBuilder.append(n);
            MiStatInterface.recordCountEvent((String)"understand_category", (String)stringBuilder.toString());
            return;
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    public static void recourdUpdateEvent(String string2) {
        if (!sEnable) {
            return;
        }
        try {
            StringBuilder stringBuilder = new StringBuilder("update_from_");
            stringBuilder.append(string2);
            MiStatInterface.recordCountEvent((String)"understand_category", (String)stringBuilder.toString());
            return;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void start(Context context) {
        if (!sEnable) {
            return;
        }
        try {
            MiStatInterface.initialize((Context)context, (String)"2882303761517322150", (String)"5921732233150", (String)"com.android.mms");
            MiStatInterface.setUploadPolicy((int)1, (long)0);
            return;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }
}

