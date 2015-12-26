/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.res.ColorStateList
 *  android.graphics.Canvas
 *  android.graphics.ColorFilter
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.Rect
 *  android.graphics.Region
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.Drawable$Callback
 *  java.lang.Object
 */
package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableWrapper;

class DrawableWrapperDonut
extends Drawable
implements Drawable.Callback,
DrawableWrapper {
    static final PorterDuff.Mode DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
    private boolean mColorFilterSet;
    private int mCurrentColor;
    private PorterDuff.Mode mCurrentMode;
    Drawable mDrawable;
    private ColorStateList mTintList;
    private PorterDuff.Mode mTintMode = DEFAULT_MODE;

    DrawableWrapperDonut(Drawable drawable2) {
        this.setWrappedDrawable(drawable2);
    }

    private boolean updateTint(int[] object) {
        if (this.mTintList != null && this.mTintMode != null) {
            int n2 = this.mTintList.getColorForState((int[])object, this.mTintList.getDefaultColor());
            object = (Object)this.mTintMode;
            if (!this.mColorFilterSet || n2 != this.mCurrentColor || object != this.mCurrentMode) {
                this.setColorFilter(n2, (PorterDuff.Mode)object);
                this.mCurrentColor = n2;
                this.mCurrentMode = object;
                this.mColorFilterSet = true;
                return true;
            }
        }
        return false;
    }

    public void draw(Canvas canvas) {
        this.mDrawable.draw(canvas);
    }

    public int getChangingConfigurations() {
        return this.mDrawable.getChangingConfigurations();
    }

    public Drawable getCurrent() {
        return this.mDrawable.getCurrent();
    }

    public int getIntrinsicHeight() {
        return this.mDrawable.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        return this.mDrawable.getIntrinsicWidth();
    }

    public int getMinimumHeight() {
        return this.mDrawable.getMinimumHeight();
    }

    public int getMinimumWidth() {
        return this.mDrawable.getMinimumWidth();
    }

    public int getOpacity() {
        return this.mDrawable.getOpacity();
    }

    public boolean getPadding(Rect rect) {
        return this.mDrawable.getPadding(rect);
    }

    public int[] getState() {
        return this.mDrawable.getState();
    }

    public Region getTransparentRegion() {
        return this.mDrawable.getTransparentRegion();
    }

    @Override
    public Drawable getWrappedDrawable() {
        return this.mDrawable;
    }

    public void invalidateDrawable(Drawable drawable2) {
        this.invalidateSelf();
    }

    public boolean isStateful() {
        if (this.mTintList != null && this.mTintList.isStateful() || this.mDrawable.isStateful()) {
            return true;
        }
        return false;
    }

    public Drawable mutate() {
        Drawable drawable2 = this.mDrawable;
        Drawable drawable3 = drawable2.mutate();
        if (drawable3 != drawable2) {
            this.setWrappedDrawable(drawable3);
        }
        return this;
    }

    protected void onBoundsChange(Rect rect) {
        this.mDrawable.setBounds(rect);
    }

    protected boolean onLevelChange(int n2) {
        return this.mDrawable.setLevel(n2);
    }

    public void scheduleDrawable(Drawable drawable2, Runnable runnable, long l2) {
        this.scheduleSelf(runnable, l2);
    }

    public void setAlpha(int n2) {
        this.mDrawable.setAlpha(n2);
    }

    public void setChangingConfigurations(int n2) {
        this.mDrawable.setChangingConfigurations(n2);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mDrawable.setColorFilter(colorFilter);
    }

    public void setDither(boolean bl2) {
        this.mDrawable.setDither(bl2);
    }

    public void setFilterBitmap(boolean bl2) {
        this.mDrawable.setFilterBitmap(bl2);
    }

    public boolean setState(int[] arrn) {
        boolean bl2 = this.mDrawable.setState(arrn);
        if (this.updateTint(arrn) || bl2) {
            return true;
        }
        return false;
    }

    @Override
    public void setTint(int n2) {
        this.setTintList(ColorStateList.valueOf((int)n2));
    }

    @Override
    public void setTintList(ColorStateList colorStateList) {
        this.mTintList = colorStateList;
        this.updateTint(this.getState());
    }

    @Override
    public void setTintMode(PorterDuff.Mode mode) {
        this.mTintMode = mode;
        this.updateTint(this.getState());
    }

    public boolean setVisible(boolean bl2, boolean bl3) {
        if (super.setVisible(bl2, bl3) || this.mDrawable.setVisible(bl2, bl3)) {
            return true;
        }
        return false;
    }

    @Override
    public void setWrappedDrawable(Drawable drawable2) {
        if (this.mDrawable != null) {
            this.mDrawable.setCallback(null);
        }
        this.mDrawable = drawable2;
        if (drawable2 != null) {
            drawable2.setCallback((Drawable.Callback)this);
        }
        this.invalidateSelf();
    }

    public void unscheduleDrawable(Drawable drawable2, Runnable runnable) {
        this.unscheduleSelf(runnable);
    }
}

