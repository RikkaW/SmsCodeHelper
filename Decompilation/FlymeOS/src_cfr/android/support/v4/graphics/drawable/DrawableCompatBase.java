/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.res.ColorStateList
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.drawable.Drawable
 *  java.lang.Object
 */
package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableWrapper;
import android.support.v4.graphics.drawable.DrawableWrapperDonut;

class DrawableCompatBase {
    DrawableCompatBase() {
    }

    public static void setTint(Drawable drawable2, int n2) {
        if (drawable2 instanceof DrawableWrapper) {
            ((DrawableWrapper)drawable2).setTint(n2);
        }
    }

    public static void setTintList(Drawable drawable2, ColorStateList colorStateList) {
        if (drawable2 instanceof DrawableWrapper) {
            ((DrawableWrapper)drawable2).setTintList(colorStateList);
        }
    }

    public static void setTintMode(Drawable drawable2, PorterDuff.Mode mode) {
        if (drawable2 instanceof DrawableWrapper) {
            ((DrawableWrapper)drawable2).setTintMode(mode);
        }
    }

    public static Drawable wrapForTinting(Drawable drawable2) {
        Drawable drawable3 = drawable2;
        if (!(drawable2 instanceof DrawableWrapperDonut)) {
            drawable3 = new DrawableWrapperDonut(drawable2);
        }
        return drawable3;
    }
}

