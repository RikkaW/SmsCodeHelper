/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Vibrator
 *  java.lang.Object
 *  java.lang.String
 *  miui.util.AudioManagerHelper
 */
package com.android.mms.util;

import android.content.Context;
import android.os.Vibrator;
import miui.util.AudioManagerHelper;

public class VibratorManager {
    private static final long[] DEFAULT_VIBRATE_PATTERN = new long[]{0, 250, 250, 250};
    private static Vibrator sVibrator;

    public static void cancel() {
        if (sVibrator != null) {
            sVibrator.cancel();
        }
    }

    public static void vibrate(Context context) {
        VibratorManager.vibrate(context, 0);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void vibrate(Context context, int n) {
        if (sVibrator == null) {
            sVibrator = (Vibrator)context.getSystemService("vibrator");
        }
        if (n == 0 && AudioManagerHelper.isVibrateEnabled((Context)context)) {
            sVibrator.vibrate(DEFAULT_VIBRATE_PATTERN, -1);
            return;
        } else {
            if (n == 0) return;
            {
                sVibrator.vibrate((long)n);
                return;
            }
        }
    }
}

