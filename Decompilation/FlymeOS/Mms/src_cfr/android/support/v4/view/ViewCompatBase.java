/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.res.ColorStateList
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.os.IBinder
 *  android.view.View
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Field
 */
package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.IBinder;
import android.support.v4.view.TintableBackgroundView;
import android.view.View;
import java.lang.reflect.Field;

class ViewCompatBase {
    private static final String TAG = "ViewCompatBase";
    private static Field sMinHeightField;
    private static boolean sMinHeightFieldFetched;
    private static Field sMinWidthField;
    private static boolean sMinWidthFieldFetched;

    ViewCompatBase() {
    }

    static ColorStateList getBackgroundTintList(View view) {
        if (view instanceof TintableBackgroundView) {
            return ((TintableBackgroundView)view).getSupportBackgroundTintList();
        }
        return null;
    }

    static PorterDuff.Mode getBackgroundTintMode(View view) {
        if (view instanceof TintableBackgroundView) {
            return ((TintableBackgroundView)view).getSupportBackgroundTintMode();
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static int getMinimumHeight(View view) {
        if (!sMinHeightFieldFetched) {
            try {
                sMinHeightField = View.class.getDeclaredField("mMinHeight");
                sMinHeightField.setAccessible(true);
            }
            catch (NoSuchFieldException var2_3) {}
            sMinHeightFieldFetched = true;
        }
        if (sMinHeightField == null) return 0;
        try {
            return (Integer)sMinHeightField.get((Object)view);
        }
        catch (Exception var0_1) {
            // empty catch block
        }
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static int getMinimumWidth(View view) {
        if (!sMinWidthFieldFetched) {
            try {
                sMinWidthField = View.class.getDeclaredField("mMinWidth");
                sMinWidthField.setAccessible(true);
            }
            catch (NoSuchFieldException var2_3) {}
            sMinWidthFieldFetched = true;
        }
        if (sMinWidthField == null) return 0;
        try {
            return (Integer)sMinWidthField.get((Object)view);
        }
        catch (Exception var0_1) {
            // empty catch block
        }
        return 0;
    }

    static boolean isAttachedToWindow(View view) {
        if (view.getWindowToken() != null) {
            return true;
        }
        return false;
    }

    static boolean isLaidOut(View view) {
        if (view.getWidth() > 0 && view.getHeight() > 0) {
            return true;
        }
        return false;
    }

    static void setBackgroundTintList(View view, ColorStateList colorStateList) {
        if (view instanceof TintableBackgroundView) {
            ((TintableBackgroundView)view).setSupportBackgroundTintList(colorStateList);
        }
    }

    static void setBackgroundTintMode(View view, PorterDuff.Mode mode) {
        if (view instanceof TintableBackgroundView) {
            ((TintableBackgroundView)view).setSupportBackgroundTintMode(mode);
        }
    }
}

