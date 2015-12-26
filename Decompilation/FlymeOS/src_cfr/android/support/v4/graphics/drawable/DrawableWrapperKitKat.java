/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.drawable.Drawable
 */
package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableWrapperHoneycomb;

class DrawableWrapperKitKat
extends DrawableWrapperHoneycomb {
    DrawableWrapperKitKat(Drawable drawable2) {
        super(drawable2);
    }

    public boolean isAutoMirrored() {
        return this.mDrawable.isAutoMirrored();
    }

    public void setAutoMirrored(boolean bl2) {
        this.mDrawable.setAutoMirrored(bl2);
    }
}

