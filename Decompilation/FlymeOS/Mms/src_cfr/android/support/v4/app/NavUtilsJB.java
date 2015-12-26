/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Intent
 *  android.content.pm.ActivityInfo
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;

class NavUtilsJB {
    NavUtilsJB() {
    }

    public static Intent getParentActivityIntent(Activity activity) {
        return activity.getParentActivityIntent();
    }

    public static String getParentActivityName(ActivityInfo activityInfo) {
        return activityInfo.parentActivityName;
    }

    public static void navigateUpTo(Activity activity, Intent intent) {
        activity.navigateUpTo(intent);
    }

    public static boolean shouldUpRecreateTask(Activity activity, Intent intent) {
        return activity.shouldUpRecreateTask(intent);
    }
}

