/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  java.io.PrintWriter
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.app;

import android.app.Activity;
import java.io.FileDescriptor;
import java.io.PrintWriter;

class ActivityCompatHoneycomb {
    ActivityCompatHoneycomb() {
    }

    static void dump(Activity activity, String string2, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] arrstring) {
        activity.dump(string2, fileDescriptor, printWriter, arrstring);
    }

    static void invalidateOptionsMenu(Activity activity) {
        activity.invalidateOptionsMenu();
    }
}

