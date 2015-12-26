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
 */
package android.support.v7.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;

public class DrawableWrapper
extends Drawable
implements Drawable.Callback {
    private Drawable mDrawable;

    public DrawableWrapper(Drawable drawable2) {
        this.setWrappedDrawable(drawable2);
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

    public Drawable getWrappedDrawable() {
        return this.mDrawable;
    }

    public void invalidateDrawable(Drawable drawable2) {
        this.invalidateSelf();
    }

    public boolean isAutoMirrored() {
        return DrawableCompat.isAutoMirrored(this.mDrawable);
    }

    public boolean isStateful() {
        return this.mDrawable.isStateful();
    }

    public void jumpToCurrentState() {
        DrawableCompat.jumpToCurrentState(this.mDrawable);
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

    public void setAutoMirrored(boolean bl2) {
        DrawableCompat.setAutoMirrored(this.mDrawable, bl2);
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

    public void setHotspot(float f2, float f3) {
        DrawableCompat.setHotspot(this.mDrawable, f2, f3);
    }

    public void setHotspotBounds(int n2, int n3, int n4, int n5) {
        DrawableCompat.setHotspotBounds(this.mDrawable, n2, n3, n4, n5);
    }

    public boolean setState(int[] arrn) {
        return this.mDrawable.setState(arrn);
    }

    public void setTint(int n2) {
        DrawableCompat.setTint(this.mDrawable, n2);
    }

    public void setTintList(ColorStateList colorStateList) {
        DrawableCompat.setTintList(this.mDrawable, colorStateList);
    }

    public void setTintMode(PorterDuff.Mode mode) {
        DrawableCompat.setTintMode(this.mDrawable, mode);
    }

    public boolean setVisible(boolean bl2, boolean bl3) {
        if (super.setVisible(bl2, bl3) || this.mDrawable.setVisible(bl2, bl3)) {
            return true;
        }
        return false;
    }

    public void setWrappedDrawable(Drawable drawable2) {
        if (this.mDrawable != null) {
            this.mDrawable.setCallback(null);
        }
        this.mDrawable = drawable2;
        if (drawable2 != null) {
            drawable2.setCallback((Drawable.Callback)this);
        }
    }

    public void unscheduleDrawable(Drawable drawable2, Runnable runnable) {
        this.unscheduleSelf(runnable);
    }
}

