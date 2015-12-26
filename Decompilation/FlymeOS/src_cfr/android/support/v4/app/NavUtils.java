/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.ActivityInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtilsJB;
import android.support.v4.content.IntentCompat;
import android.util.Log;

public class NavUtils {
    private static final NavUtilsImpl IMPL = Build.VERSION.SDK_INT >= 16 ? new NavUtilsImplJB() : new NavUtilsImplBase();
    public static final String PARENT_ACTIVITY = "android.support.PARENT_ACTIVITY";
    private static final String TAG = "NavUtils";

    private NavUtils() {
    }

    public static Intent getParentActivityIntent(Activity activity) {
        return IMPL.getParentActivityIntent(activity);
    }

    public static Intent getParentActivityIntent(Context context, ComponentName componentName) {
        String string2 = NavUtils.getParentActivityName(context, componentName);
        if (string2 == null) {
            return null;
        }
        if (NavUtils.getParentActivityName(context, componentName = new ComponentName(componentName.getPackageName(), string2)) == null) {
            return IntentCompat.makeMainActivity(componentName);
        }
        return new Intent().setComponent(componentName);
    }

    public static Intent getParentActivityIntent(Context context, Class<?> object) {
        if ((object = NavUtils.getParentActivityName(context, new ComponentName(context, object))) == null) {
            return null;
        }
        if (NavUtils.getParentActivityName(context, (ComponentName)(object = new ComponentName(context, (String)object))) == null) {
            return IntentCompat.makeMainActivity((ComponentName)object);
        }
        return new Intent().setComponent((ComponentName)object);
    }

    @Nullable
    public static String getParentActivityName(Activity object) {
        try {
            object = NavUtils.getParentActivityName((Context)object, object.getComponentName());
            return object;
        }
        catch (PackageManager.NameNotFoundException var0_1) {
            throw new IllegalArgumentException((Throwable)var0_1);
        }
    }

    @Nullable
    public static String getParentActivityName(Context context, ComponentName componentName) {
        componentName = context.getPackageManager().getActivityInfo(componentName, 128);
        return IMPL.getParentActivityName(context, (ActivityInfo)componentName);
    }

    public static void navigateUpFromSameTask(Activity activity) {
        Intent intent = NavUtils.getParentActivityIntent(activity);
        if (intent == null) {
            throw new IllegalArgumentException("Activity " + activity.getClass().getSimpleName() + " does not have a parent activity name specified." + " (Did you forget to add the android.support.PARENT_ACTIVITY <meta-data> " + " element in your manifest?)");
        }
        NavUtils.navigateUpTo(activity, intent);
    }

    public static void navigateUpTo(Activity activity, Intent intent) {
        IMPL.navigateUpTo(activity, intent);
    }

    public static boolean shouldUpRecreateTask(Activity activity, Intent intent) {
        return IMPL.shouldUpRecreateTask(activity, intent);
    }

    static interface NavUtilsImpl {
        public Intent getParentActivityIntent(Activity var1);

        public String getParentActivityName(Context var1, ActivityInfo var2);

        public void navigateUpTo(Activity var1, Intent var2);

        public boolean shouldUpRecreateTask(Activity var1, Intent var2);
    }

    static class NavUtilsImplBase
    implements NavUtilsImpl {
        NavUtilsImplBase() {
        }

        @Override
        public Intent getParentActivityIntent(Activity activity) {
            String string2 = NavUtils.getParentActivityName(activity);
            if (string2 == null) {
                return null;
            }
            ComponentName componentName = new ComponentName((Context)activity, string2);
            try {
                if (NavUtils.getParentActivityName((Context)activity, componentName) == null) {
                    return IntentCompat.makeMainActivity(componentName);
                }
                activity = new Intent().setComponent(componentName);
                return activity;
            }
            catch (PackageManager.NameNotFoundException var1_2) {
                Log.e((String)"NavUtils", (String)("getParentActivityIntent: bad parentActivityName '" + string2 + "' in manifest"));
                return null;
            }
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public String getParentActivityName(Context context, ActivityInfo object) {
            if (object.metaData == null) {
                return null;
            }
            String string2 = object.metaData.getString("android.support.PARENT_ACTIVITY");
            if (string2 == null) {
                return null;
            }
            object = string2;
            if (string2.charAt(0) != '.') return object;
            return context.getPackageName() + string2;
        }

        @Override
        public void navigateUpTo(Activity activity, Intent intent) {
            intent.addFlags(67108864);
            activity.startActivity(intent);
            activity.finish();
        }

        @Override
        public boolean shouldUpRecreateTask(Activity object, Intent intent) {
            if ((object = object.getIntent().getAction()) != null && !object.equals((Object)"android.intent.action.MAIN")) {
                return true;
            }
            return false;
        }
    }

    static class NavUtilsImplJB
    extends NavUtilsImplBase {
        NavUtilsImplJB() {
        }

        @Override
        public Intent getParentActivityIntent(Activity activity) {
            Intent intent;
            Intent intent2 = intent = NavUtilsJB.getParentActivityIntent(activity);
            if (intent == null) {
                intent2 = this.superGetParentActivityIntent(activity);
            }
            return intent2;
        }

        @Override
        public String getParentActivityName(Context context, ActivityInfo activityInfo) {
            String string2;
            String string3 = string2 = NavUtilsJB.getParentActivityName(activityInfo);
            if (string2 == null) {
                string3 = super.getParentActivityName(context, activityInfo);
            }
            return string3;
        }

        @Override
        public void navigateUpTo(Activity activity, Intent intent) {
            NavUtilsJB.navigateUpTo(activity, intent);
        }

        @Override
        public boolean shouldUpRecreateTask(Activity activity, Intent intent) {
            return NavUtilsJB.shouldUpRecreateTask(activity, intent);
        }

        Intent superGetParentActivityIntent(Activity activity) {
            return super.getParentActivityIntent(activity);
        }
    }

}

