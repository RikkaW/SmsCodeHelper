/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.Intent
 *  android.os.Build
 *  android.os.Build$VERSION
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.content;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.IntentCompatHoneycomb;
import android.support.v4.content.IntentCompatIcsMr1;

public class IntentCompat {
    public static final String ACTION_EXTERNAL_APPLICATIONS_AVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE";
    public static final String ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE";
    public static final String EXTRA_CHANGED_PACKAGE_LIST = "android.intent.extra.changed_package_list";
    public static final String EXTRA_CHANGED_UID_LIST = "android.intent.extra.changed_uid_list";
    public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";
    public static final int FLAG_ACTIVITY_CLEAR_TASK = 32768;
    public static final int FLAG_ACTIVITY_TASK_ON_HOME = 16384;
    private static final IntentCompatImpl IMPL;

    static {
        int n2 = Build.VERSION.SDK_INT;
        IMPL = n2 >= 15 ? new IntentCompatImplIcsMr1() : (n2 >= 11 ? new IntentCompatImplHC() : new IntentCompatImplBase());
    }

    private IntentCompat() {
    }

    public static Intent makeMainActivity(ComponentName componentName) {
        return IMPL.makeMainActivity(componentName);
    }

    public static Intent makeMainSelectorActivity(String string2, String string3) {
        return IMPL.makeMainSelectorActivity(string2, string3);
    }

    public static Intent makeRestartActivityTask(ComponentName componentName) {
        return IMPL.makeRestartActivityTask(componentName);
    }

    static interface IntentCompatImpl {
        public Intent makeMainActivity(ComponentName var1);

        public Intent makeMainSelectorActivity(String var1, String var2);

        public Intent makeRestartActivityTask(ComponentName var1);
    }

    static class IntentCompatImplBase
    implements IntentCompatImpl {
        IntentCompatImplBase() {
        }

        @Override
        public Intent makeMainActivity(ComponentName componentName) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setComponent(componentName);
            intent.addCategory("android.intent.category.LAUNCHER");
            return intent;
        }

        @Override
        public Intent makeMainSelectorActivity(String string2, String string3) {
            string2 = new Intent(string2);
            string2.addCategory(string3);
            return string2;
        }

        @Override
        public Intent makeRestartActivityTask(ComponentName componentName) {
            componentName = this.makeMainActivity(componentName);
            componentName.addFlags(268468224);
            return componentName;
        }
    }

    static class IntentCompatImplHC
    extends IntentCompatImplBase {
        IntentCompatImplHC() {
        }

        @Override
        public Intent makeMainActivity(ComponentName componentName) {
            return IntentCompatHoneycomb.makeMainActivity(componentName);
        }

        @Override
        public Intent makeRestartActivityTask(ComponentName componentName) {
            return IntentCompatHoneycomb.makeRestartActivityTask(componentName);
        }
    }

    static class IntentCompatImplIcsMr1
    extends IntentCompatImplHC {
        IntentCompatImplIcsMr1() {
        }

        @Override
        public Intent makeMainSelectorActivity(String string2, String string3) {
            return IntentCompatIcsMr1.makeMainSelectorActivity(string2, string3);
        }
    }

}

