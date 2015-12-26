/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  java.io.File
 *  java.lang.Object
 */
package android.support.v4.content;

import android.content.Context;
import android.content.Intent;
import java.io.File;

class ContextCompatHoneycomb {
    ContextCompatHoneycomb() {
    }

    public static File getObbDir(Context context) {
        return context.getObbDir();
    }

    static void startActivities(Context context, Intent[] arrintent) {
        context.startActivities(arrintent);
    }
}

