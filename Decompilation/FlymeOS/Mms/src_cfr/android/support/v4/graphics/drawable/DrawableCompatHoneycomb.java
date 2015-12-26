/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.drawable.Drawable
 *  java.lang.Object
 */
package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableWrapperHoneycomb;

class DrawableCompatHoneycomb {
    DrawableCompatHoneycomb() {
    }

    public static void jumpToCurrentState(Drawable drawable2) {
        drawable2.jumpToCurrentState();
    }

    public static Drawable wrapForTinting(Drawable drawable2) {
        Drawable drawable3 = drawable2;
        if (!(drawable2 instanceof DrawableWrapperHoneycomb)) {
            drawable3 = new DrawableWrapperHoneycomb(drawable2);
        }
        return drawable3;
    }
}

