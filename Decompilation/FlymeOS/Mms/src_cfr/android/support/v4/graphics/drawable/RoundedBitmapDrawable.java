/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.BitmapShader
 *  android.graphics.Canvas
 *  android.graphics.ColorFilter
 *  android.graphics.Paint
 *  android.graphics.Rect
 *  android.graphics.RectF
 *  android.graphics.Shader
 *  android.graphics.Shader$TileMode
 *  android.graphics.drawable.Drawable
 *  android.util.DisplayMetrics
 *  java.lang.Float
 */
package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

public abstract class RoundedBitmapDrawable
extends Drawable {
    private static final int DEFAULT_PAINT_FLAGS = 6;
    private boolean mApplyGravity = true;
    Bitmap mBitmap;
    private int mBitmapHeight;
    private BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private float mCornerRadius;
    final Rect mDstRect = new Rect();
    final RectF mDstRectF = new RectF();
    private int mGravity = 119;
    private Paint mPaint = new Paint(6);
    private int mTargetDensity = 160;

    RoundedBitmapDrawable(Resources resources, Bitmap bitmap) {
        if (resources != null) {
            this.mTargetDensity = resources.getDisplayMetrics().densityDpi;
        }
        this.mBitmap = bitmap;
        if (this.mBitmap != null) {
            this.computeBitmapSize();
            this.mBitmapShader = new BitmapShader(this.mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            return;
        }
        this.mBitmapHeight = -1;
        this.mBitmapWidth = -1;
    }

    private void computeBitmapSize() {
        this.mBitmapWidth = this.mBitmap.getScaledWidth(this.mTargetDensity);
        this.mBitmapHeight = this.mBitmap.getScaledHeight(this.mTargetDensity);
    }

    private static boolean isGreaterThanZero(float f2) {
        if (Float.compare((float)f2, (float)0.0f) > 0) {
            return true;
        }
        return false;
    }

    public void draw(Canvas canvas) {
        Bitmap bitmap = this.mBitmap;
        if (bitmap == null) {
            return;
        }
        this.updateDstRect();
        Paint paint = this.mPaint;
        if (paint.getShader() == null) {
            canvas.drawBitmap(bitmap, null, this.mDstRect, paint);
            return;
        }
        canvas.drawRoundRect(this.mDstRectF, this.mCornerRadius, this.mCornerRadius, paint);
    }

    public int getAlpha() {
        return this.mPaint.getAlpha();
    }

    public final Bitmap getBitmap() {
        return this.mBitmap;
    }

    public ColorFilter getColorFilter() {
        return this.mPaint.getColorFilter();
    }

    public float getCornerRadius() {
        return this.mCornerRadius;
    }

    public int getGravity() {
        return this.mGravity;
    }

    public int getIntrinsicHeight() {
        return this.mBitmapHeight;
    }

    public int getIntrinsicWidth() {
        return this.mBitmapWidth;
    }

    /*
     * Enabled aggressive block sorting
     */
    public int getOpacity() {
        Bitmap bitmap;
        if (this.mGravity != 119 || (bitmap = this.mBitmap) == null || bitmap.hasAlpha() || this.mPaint.getAlpha() < 255 || RoundedBitmapDrawable.isGreaterThanZero(this.mCornerRadius)) {
            return -3;
        }
        return -1;
    }

    public final Paint getPaint() {
        return this.mPaint;
    }

    void gravityCompatApply(int n2, int n3, int n4, Rect rect, Rect rect2) {
        throw new UnsupportedOperationException();
    }

    public boolean hasAntiAlias() {
        return this.mPaint.isAntiAlias();
    }

    public boolean hasMipMap() {
        throw new UnsupportedOperationException();
    }

    public void setAlpha(int n2) {
        if (n2 != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(n2);
            this.invalidateSelf();
        }
    }

    public void setAntiAlias(boolean bl2) {
        this.mPaint.setAntiAlias(bl2);
        this.invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
        this.invalidateSelf();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setCornerRadius(float f2) {
        if (RoundedBitmapDrawable.isGreaterThanZero(f2)) {
            this.mPaint.setShader((Shader)this.mBitmapShader);
        } else {
            this.mPaint.setShader(null);
        }
        this.mCornerRadius = f2;
    }

    public void setDither(boolean bl2) {
        this.mPaint.setDither(bl2);
        this.invalidateSelf();
    }

    public void setFilterBitmap(boolean bl2) {
        this.mPaint.setFilterBitmap(bl2);
        this.invalidateSelf();
    }

    public void setGravity(int n2) {
        if (this.mGravity != n2) {
            this.mGravity = n2;
            this.mApplyGravity = true;
            this.invalidateSelf();
        }
    }

    public void setMipMap(boolean bl2) {
        throw new UnsupportedOperationException();
    }

    public void setTargetDensity(int n2) {
        if (this.mTargetDensity != n2) {
            int n3 = n2;
            if (n2 == 0) {
                n3 = 160;
            }
            this.mTargetDensity = n3;
            if (this.mBitmap != null) {
                this.computeBitmapSize();
            }
            this.invalidateSelf();
        }
    }

    public void setTargetDensity(Canvas canvas) {
        this.setTargetDensity(canvas.getDensity());
    }

    public void setTargetDensity(DisplayMetrics displayMetrics) {
        this.setTargetDensity(displayMetrics.densityDpi);
    }

    void updateDstRect() {
        if (this.mApplyGravity) {
            this.gravityCompatApply(this.mGravity, this.mBitmapWidth, this.mBitmapHeight, this.getBounds(), this.mDstRect);
            this.mDstRectF.set(this.mDstRect);
            this.mApplyGravity = false;
        }
    }
}

