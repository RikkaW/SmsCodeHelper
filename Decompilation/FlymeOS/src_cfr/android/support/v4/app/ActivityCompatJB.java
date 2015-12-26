/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  java.lang.Object
 */
package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

class ActivityCompatJB {
    ActivityCompatJB() {
    }

    public static void finishAffinity(Activity activity) {
        activity.finishAffinity();
    }

    public static void startActivity(Context context, Intent intent, Bundle bundle) {
        context.startActivity(intent, bundle);
    }

    public static void startActivityForResult(Activity activity, Intent intent, int n2, Bundle bundle) {
        activity.startActivityForResult(intent, n2, bundle);
    }
}

