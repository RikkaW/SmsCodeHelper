/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.res.Resources
 *  android.content.res.Resources$Theme
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  java.lang.Object
 */
package android.support.v4.content.res;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.res.ResourcesCompatApi21;
import android.support.v4.content.res.ResourcesCompatIcsMr1;

public class ResourcesCompat {
    public static Drawable getDrawable(Resources resources, int n2, Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= 21) {
            return ResourcesCompatApi21.getDrawable(resources, n2, theme);
        }
        return resources.getDrawable(n2);
    }

    public static Drawable getDrawableForDensity(Resources resources, int n2, int n3, Resources.Theme theme) {
        int n4 = Build.VERSION.SDK_INT;
        if (n4 >= 21) {
            return ResourcesCompatApi21.getDrawableForDensity(resources, n2, n3, theme);
        }
        if (n4 >= 15) {
            return ResourcesCompatIcsMr1.getDrawableForDensity(resources, n2, n3);
        }
        return resources.getDrawable(n2);
    }
}

