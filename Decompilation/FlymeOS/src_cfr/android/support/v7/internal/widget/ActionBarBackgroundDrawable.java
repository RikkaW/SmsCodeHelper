/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Canvas
 *  android.graphics.ColorFilter
 *  android.graphics.drawable.Drawable
 */
package android.support.v7.internal.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v7.internal.widget.ActionBarContainer;

class ActionBarBackgroundDrawable
extends Drawable {
    final ActionBarContainer mContainer;

    public ActionBarBackgroundDrawable(ActionBarContainer actionBarContainer) {
        this.mContainer = actionBarContainer;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void draw(Canvas canvas) {
        if (this.mContainer.mIsSplit) {
            if (this.mContainer.mSplitBackground == null) return;
            {
                this.mContainer.mSplitBackground.draw(canvas);
                return;
            }
        } else {
            if (this.mContainer.mBackground != null) {
                this.mContainer.mBackground.draw(canvas);
            }
            if (this.mContainer.mStackedBackground == null || !this.mContainer.mIsStacked) return;
            {
                this.mContainer.mStackedBackground.draw(canvas);
                return;
            }
        }
    }

    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int n2) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }
}

