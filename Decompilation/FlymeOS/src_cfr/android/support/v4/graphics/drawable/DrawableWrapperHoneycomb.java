/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.drawable.Drawable
 */
package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableWrapperDonut;

class DrawableWrapperHoneycomb
extends DrawableWrapperDonut {
    DrawableWrapperHoneycomb(Drawable drawable2) {
        super(drawable2);
    }

    public void jumpToCurrentState() {
        this.mDrawable.jumpToCurrentState();
    }
}

