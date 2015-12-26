/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.res.Resources
 *  android.content.res.Resources$Theme
 *  android.graphics.Outline
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 */
package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableWrapperKitKat;

class DrawableWrapperLollipop
extends DrawableWrapperKitKat {
    DrawableWrapperLollipop(Drawable drawable2) {
        super(drawable2);
    }

    public void applyTheme(Resources.Theme theme) {
        this.mDrawable.applyTheme(theme);
    }

    public boolean canApplyTheme() {
        return this.mDrawable.canApplyTheme();
    }

    public Rect getDirtyBounds() {
        return this.mDrawable.getDirtyBounds();
    }

    public void getOutline(Outline outline) {
        this.mDrawable.getOutline(outline);
    }

    public void setHotspot(float f2, float f3) {
        this.mDrawable.setHotspot(f2, f3);
    }

    public void setHotspotBounds(int n2, int n3, int n4, int n5) {
        this.mDrawable.setHotspotBounds(n2, n3, n4, n5);
    }
}

