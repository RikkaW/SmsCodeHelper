/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Application
 *  android.app.Instrumentation
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.ActivityInfo
 *  android.os.IBinder
 *  java.lang.Class
 *  java.lang.ClassLoader
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Field
 *  java.lang.reflect.Method
 */
package miui.external;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.IBinder;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import miui.external.SdkConstants;
import miui.external.SdkErrorActivity;

final class e
extends Instrumentation
implements SdkConstants {
    private SdkConstants.SdkError m;

    private e(SdkConstants.SdkError sdkError) {
        this.m = sdkError;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static Field a(Class<?> var0, Object var1_3, Object var2_4, String var3_5, Class<?> var4_6) throws NoSuchFieldException {
        var7_17 = var0.getDeclaredFields();
        if (var1_13 != null && var2_14 != null) {
            for (Field var0_2 : var7_17) {
                var0_2.setAccessible(true);
                try {
                    var8_20 = var0_2.get((Object)var1_13);
                    if (var8_20 != var2_14) continue;
                    return var0_3;
                }
                catch (IllegalArgumentException var0_4) {
                    var0_4.printStackTrace();
                    continue;
                }
                catch (IllegalAccessException var0_6) {
                    var0_6.printStackTrace();
                }
            }
        }
        if (var3_15 != null) {
            for (Field var0_8 : var7_17) {
                if (!var0_8.getName().equals((Object)var3_15)) continue;
                var0_8.setAccessible(true);
                return var0_8;
            }
        }
        var1_13 = null;
        var0_9 = null;
        if (var4_16 != null) return var0_3;
        var6_18 = var7_17.length;
        var0_10 = var1_13;
        for (var5_19 = 0; var5_19 < var6_18; ++var5_19) {
            var3_15 = var7_17[var5_19];
            if (var3_15.getType() == var4_16) ** GOTO lbl-1000
            var1_13 = var0_11;
            if (var3_15.getType().isInstance((Object)var4_16)) lbl-1000: // 2 sources:
            {
                if (var0_11 != null) throw new NoSuchFieldException("More than one matched field found: " + var0_11.getName() + " and " + var3_15.getName());
                var1_13 = var3_15;
            }
            var0_12 = var1_13;
        }
        if (var0_11 == null) {
            throw new NoSuchFieldException("No such field found of value " + var2_14);
        }
        var0_11.setAccessible(true);
        return var0_11;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static void a(SdkConstants.SdkError var0) {
        var4_2 = Class.forName((String)"android.app.ActivityThread");
        var3_3 = var4_2.getMethod("currentActivityThread", new Class[0]).invoke((Object)null, new Object[0]);
        var4_2 = e.a(var4_2, var3_3, (Object)((Instrumentation)var4_2.getMethod("getInstrumentation", new Class[0]).invoke(var3_3, new Object[0])), null, null);
        var5_4 = (Instrumentation)var4_2.get(var3_3);
        var6_5 = new e((SdkConstants.SdkError)var0);
        ** for (var0 = Instrumentation.class;
        ; var0 != null; var0 = var0.getSuperclass())
lbl-1000: // 2 sources:
        {
            for (Field var8_9 : var0.getDeclaredFields()) {
                var8_9.setAccessible(true);
                var8_9.set((Object)var6_5, var8_9.get((Object)var5_4));
                continue;
            }
            try {
                continue;
            }
            catch (Exception var0_1) {
                var0_1.printStackTrace();
                return;
            }
        }
lbl18: // 1 sources:
        var4_2.set(var3_3, (Object)var6_5);
    }

    public Activity newActivity(Class<?> var1_1, Context context, IBinder iBinder, Application application, Intent var5_5, ActivityInfo activityInfo, CharSequence charSequence, Activity activity, String string2, Object object) throws InstantiationException, IllegalAccessException {
        void var7_14;
        void var3_8;
        void var1_6;
        void var6_13;
        void var10_17;
        void var9_16;
        void var5_12;
        void var4_9;
        void var2_7;
        void var8_15;
        if (!var1_1.getSimpleName().startsWith("SdkError")) {
            void var1_4;
            void var5_10;
            reference var11_18 = SdkErrorActivity.class;
            void var1_2 = var5_10;
            if (var5_10 == null) {
                Intent intent = new Intent();
            }
            var1_4.putExtra("com.miui.sdk.error", (Serializable)((Object)this.m));
            void var5_11 = var1_4;
            reference var1_5 = var11_18;
        }
        return super.newActivity((Class)var1_6, (Context)var2_7, (IBinder)var3_8, (Application)var4_9, (Intent)var5_12, (ActivityInfo)var6_13, (CharSequence)var7_14, (Activity)var8_15, (String)var9_16, (Object)var10_17);
    }

    public Activity newActivity(ClassLoader classLoader, String string2, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        String string3 = string2;
        Object object = intent;
        if (!string2.startsWith("SdkError")) {
            string3 = SdkErrorActivity.class.getName();
            string2 = intent;
            if (intent == null) {
                string2 = new Intent();
            }
            string2.putExtra("com.miui.sdk.error", (Serializable)((Object)this.m));
            object = string2;
        }
        return super.newActivity(classLoader, string3, (Intent)object);
    }
}

