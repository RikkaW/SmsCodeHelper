/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.Intent
 *  java.lang.Object
 */
package android.support.v4.content;

import android.content.ComponentName;
import android.content.Intent;

class IntentCompatHoneycomb {
    IntentCompatHoneycomb() {
    }

    public static Intent makeMainActivity(ComponentName componentName) {
        return Intent.makeMainActivity((ComponentName)componentName);
    }

    public static Intent makeRestartActivityTask(ComponentName componentName) {
        return Intent.makeRestartActivityTask((ComponentName)componentName);
    }
}

