/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.res.ColorStateList
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.GradientDrawable
 *  java.lang.Object
 */
package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.graphics.drawable.DrawableCompatBase;
import android.support.v4.graphics.drawable.DrawableWrapperLollipop;

class DrawableCompatLollipop {
    DrawableCompatLollipop() {
    }

    public static void setHotspot(Drawable drawable2, float f2, float f3) {
        drawable2.setHotspot(f2, f3);
    }

    public static void setHotspotBounds(Drawable drawable2, int n2, int n3, int n4, int n5) {
        drawable2.setHotspotBounds(n2, n3, n4, n5);
    }

    public static void setTint(Drawable drawable2, int n2) {
        if (drawable2 instanceof DrawableWrapperLollipop) {
            DrawableCompatBase.setTint(drawable2, n2);
            return;
        }
        drawable2.setTint(n2);
    }

    public static void setTintList(Drawable drawable2, ColorStateList colorStateList) {
        if (drawable2 instanceof DrawableWrapperLollipop) {
            DrawableCompatBase.setTintList(drawable2, colorStateList);
            return;
        }
        drawable2.setTintList(colorStateList);
    }

    public static void setTintMode(Drawable drawable2, PorterDuff.Mode mode) {
        if (drawable2 instanceof DrawableWrapperLollipop) {
            DrawableCompatBase.setTintMode(drawable2, mode);
            return;
        }
        drawable2.setTintMode(mode);
    }

    public static Drawable wrapForTinting(Drawable drawable2) {
        Drawable drawable3 = drawable2;
        if (drawable2 instanceof GradientDrawable) {
            drawable3 = new DrawableWrapperLollipop(drawable2);
        }
        return drawable3;
    }
}

