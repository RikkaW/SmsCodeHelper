/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.Outline
 *  android.graphics.Rect
 *  android.view.Gravity
 */
package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.Rect;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.view.Gravity;

class RoundedBitmapDrawable21
extends RoundedBitmapDrawable {
    protected RoundedBitmapDrawable21(Resources resources, Bitmap bitmap) {
        super(resources, bitmap);
    }

    public void getOutline(Outline outline) {
        this.updateDstRect();
        outline.setRoundRect(this.mDstRect, this.getCornerRadius());
    }

    @Override
    void gravityCompatApply(int n2, int n3, int n4, Rect rect, Rect rect2) {
        Gravity.apply((int)n2, (int)n3, (int)n4, (Rect)rect, (Rect)rect2, (int)0);
    }

    @Override
    public boolean hasMipMap() {
        if (this.mBitmap != null && this.mBitmap.hasMipMap()) {
            return true;
        }
        return false;
    }

    @Override
    public void setMipMap(boolean bl2) {
        if (this.mBitmap != null) {
            this.mBitmap.setHasMipMap(bl2);
            this.invalidateSelf();
        }
    }
}

