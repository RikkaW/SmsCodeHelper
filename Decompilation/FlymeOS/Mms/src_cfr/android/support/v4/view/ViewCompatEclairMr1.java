/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  android.view.View
 *  android.view.ViewGroup
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.lang.reflect.Method
 */
package android.support.v4.view;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ViewCompatEclairMr1 {
    public static final String TAG = "ViewCompat";
    private static Method sChildrenDrawingOrderMethod;

    ViewCompatEclairMr1() {
    }

    public static boolean isOpaque(View view) {
        return view.isOpaque();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void setChildrenDrawingOrderEnabled(ViewGroup viewGroup, boolean bl2) {
        if (sChildrenDrawingOrderMethod == null) {
            try {
                sChildrenDrawingOrderMethod = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", new Class[]{Boolean.TYPE});
            }
            catch (NoSuchMethodException var2_5) {
                Log.e((String)"ViewCompat", (String)"Unable to find childrenDrawingOrderEnabled", (Throwable)var2_5);
            }
            sChildrenDrawingOrderMethod.setAccessible(true);
        }
        try {
            sChildrenDrawingOrderMethod.invoke((Object)viewGroup, new Object[]{bl2});
            return;
        }
        catch (IllegalAccessException var0_1) {
            Log.e((String)"ViewCompat", (String)"Unable to invoke childrenDrawingOrderEnabled", (Throwable)var0_1);
            return;
        }
        catch (IllegalArgumentException var0_2) {
            Log.e((String)"ViewCompat", (String)"Unable to invoke childrenDrawingOrderEnabled", (Throwable)var0_2);
            return;
        }
        catch (InvocationTargetException var0_3) {
            Log.e((String)"ViewCompat", (String)"Unable to invoke childrenDrawingOrderEnabled", (Throwable)var0_3);
            return;
        }
    }
}

