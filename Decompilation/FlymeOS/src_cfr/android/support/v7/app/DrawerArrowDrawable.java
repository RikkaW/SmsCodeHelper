/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.content.res.Resources$Theme
 *  android.content.res.TypedArray
 *  android.graphics.Canvas
 *  android.graphics.ColorFilter
 *  android.graphics.Paint
 *  android.graphics.Paint$Cap
 *  android.graphics.Paint$Join
 *  android.graphics.Paint$Style
 *  android.graphics.Path
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 *  android.util.AttributeSet
 *  java.lang.Math
 */
package android.support.v7.app;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;

abstract class DrawerArrowDrawable
extends Drawable {
    private static final float ARROW_HEAD_ANGLE = (float)Math.toRadians((double)45.0);
    private final float mBarGap;
    private final float mBarSize;
    private final float mBarThickness;
    private float mCenterOffset;
    private float mMaxCutForBarSize;
    private final float mMiddleArrowSize;
    private final Paint mPaint = new Paint();
    private final Path mPath = new Path();
    private float mProgress;
    private final int mSize;
    private final boolean mSpin;
    private final float mTopBottomArrowSize;
    private boolean mVerticalMirror = false;

    DrawerArrowDrawable(Context context) {
        context = context.getTheme().obtainStyledAttributes(null, R.styleable.DrawerArrowToggle, R.attr.drawerArrowStyle, R.style.Base_Widget_AppCompat_DrawerArrowToggle);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(context.getColor(R.styleable.DrawerArrowToggle_color, 0));
        this.mSize = context.getDimensionPixelSize(R.styleable.DrawerArrowToggle_drawableSize, 0);
        this.mBarSize = Math.round((float)context.getDimension(R.styleable.DrawerArrowToggle_barSize, 0.0f));
        this.mTopBottomArrowSize = Math.round((float)context.getDimension(R.styleable.DrawerArrowToggle_topBottomBarArrowSize, 0.0f));
        this.mBarThickness = context.getDimension(R.styleable.DrawerArrowToggle_thickness, 0.0f);
        this.mBarGap = Math.round((float)context.getDimension(R.styleable.DrawerArrowToggle_gapBetweenBars, 0.0f));
        this.mSpin = context.getBoolean(R.styleable.DrawerArrowToggle_spinBars, true);
        this.mMiddleArrowSize = context.getDimension(R.styleable.DrawerArrowToggle_middleBarArrowSize, 0.0f);
        this.mCenterOffset = (int)((float)this.mSize - this.mBarThickness * 3.0f - this.mBarGap * 2.0f) / 4 * 2;
        this.mCenterOffset = (float)((double)this.mCenterOffset + ((double)this.mBarThickness * 1.5 + (double)this.mBarGap));
        context.recycle();
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeJoin(Paint.Join.MITER);
        this.mPaint.setStrokeCap(Paint.Cap.BUTT);
        this.mPaint.setStrokeWidth(this.mBarThickness);
        this.mMaxCutForBarSize = (float)((double)(this.mBarThickness / 2.0f) * Math.cos((double)ARROW_HEAD_ANGLE));
    }

    private static float lerp(float f2, float f3, float f4) {
        return (f3 - f2) * f4 + f2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void draw(Canvas canvas) {
        Rect rect = this.getBounds();
        boolean bl2 = this.isLayoutRtl();
        float f2 = DrawerArrowDrawable.lerp(this.mBarSize, this.mTopBottomArrowSize, this.mProgress);
        float f3 = DrawerArrowDrawable.lerp(this.mBarSize, this.mMiddleArrowSize, this.mProgress);
        float f4 = Math.round((float)DrawerArrowDrawable.lerp(0.0f, this.mMaxCutForBarSize, this.mProgress));
        float f5 = DrawerArrowDrawable.lerp(0.0f, ARROW_HEAD_ANGLE, this.mProgress);
        float f6 = bl2 ? 0.0f : -180.0f;
        float f7 = bl2 ? 180.0f : 0.0f;
        f6 = DrawerArrowDrawable.lerp(f6, f7, this.mProgress);
        f7 = Math.round((double)((double)f2 * Math.cos((double)f5)));
        f2 = Math.round((double)((double)f2 * Math.sin((double)f5)));
        this.mPath.rewind();
        f5 = DrawerArrowDrawable.lerp(this.mBarGap + this.mBarThickness, - this.mMaxCutForBarSize, this.mProgress);
        float f8 = (- f3) / 2.0f;
        this.mPath.moveTo(f8 + f4, 0.0f);
        this.mPath.rLineTo(f3 - f4 * 2.0f, 0.0f);
        this.mPath.moveTo(f8, f5);
        this.mPath.rLineTo(f7, f2);
        this.mPath.moveTo(f8, - f5);
        this.mPath.rLineTo(f7, - f2);
        this.mPath.close();
        canvas.save();
        canvas.translate((float)rect.centerX(), this.mCenterOffset);
        if (this.mSpin) {
            int n2 = this.mVerticalMirror ^ bl2 ? -1 : 1;
            canvas.rotate((float)n2 * f6);
        } else if (bl2) {
            canvas.rotate(180.0f);
        }
        canvas.drawPath(this.mPath, this.mPaint);
        canvas.restore();
    }

    public int getIntrinsicHeight() {
        return this.mSize;
    }

    public int getIntrinsicWidth() {
        return this.mSize;
    }

    public int getOpacity() {
        return -3;
    }

    public float getProgress() {
        return this.mProgress;
    }

    public boolean isAutoMirrored() {
        return true;
    }

    abstract boolean isLayoutRtl();

    public void setAlpha(int n2) {
        this.mPaint.setAlpha(n2);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    public void setProgress(float f2) {
        this.mProgress = f2;
        this.invalidateSelf();
    }

    protected void setVerticalMirror(boolean bl2) {
        this.mVerticalMirror = bl2;
    }
}

