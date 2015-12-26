/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.res.Resources
 *  android.content.res.Resources$Theme
 *  android.graphics.drawable.Drawable
 *  java.lang.Object
 */
package android.support.v4.content.res;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

class ResourcesCompatApi21 {
    ResourcesCompatApi21() {
    }

    public static Drawable getDrawable(Resources resources, int n2, Resources.Theme theme) {
        return resources.getDrawable(n2, theme);
    }

    public static Drawable getDrawableForDensity(Resources resources, int n2, int n3, Resources.Theme theme) {
        return resources.getDrawableForDensity(n2, n3, theme);
    }
}

