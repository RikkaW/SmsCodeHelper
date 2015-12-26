/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package com.android.common.userhappiness;

import android.content.Context;
import android.content.Intent;

public class UserHappinessSignals {
    private static boolean mHasVoiceLoggingInfo = false;

    public static void setHasVoiceLoggingInfo(boolean bl) {
        mHasVoiceLoggingInfo = bl;
    }

    public static void userAcceptedImeText(Context context) {
        if (mHasVoiceLoggingInfo) {
            Intent intent = new Intent("com.android.common.speech.LOG_EVENT");
            intent.putExtra("app_name", "voiceime");
            intent.putExtra("extra_event", 21);
            intent.putExtra("", context.getPackageName());
            intent.putExtra("timestamp", System.currentTimeMillis());
            context.sendBroadcast(intent);
            UserHappinessSignals.setHasVoiceLoggingInfo(false);
        }
    }
}

