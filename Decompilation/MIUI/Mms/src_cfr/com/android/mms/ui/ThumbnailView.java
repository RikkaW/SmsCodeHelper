/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.PorterDuffXfermode
 *  android.graphics.Rect
 *  android.graphics.Xfermode
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.Drawable
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.widget.ImageView
 *  java.lang.Math
 *  java.lang.String
 *  java.lang.System
 */
package com.android.mms.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import com.android.mms.MmsApp;

public class ThumbnailView
extends ImageView {
    private static Xfermode CLEAR_MODE;
    private static Xfermode SRC_IN_MODE;
    private static String TAG;
    private static int sLargeBitmapLongEdgeSize;
    private static int sLargeBitmapShortEdgeSize;
    private static int sLargeBitmapSize;
    private static int sMaskPaddingSize;
    private static int sMinBitmapSize;
    private static int sNormalBitmapSize;
    private static Paint sPaint;
    private static int sSmallBitmapLongEdgeSize;
    private static int sSmallBitmapShortEdgeSize;
    private static int sSmallBitmapSize;
    private static int sZoomSize;
    private int mAlignType;
    private Drawable mBackgroundDrawable;
    private boolean mIsZoomAsSquare;
    private int mMaskSizeType = 2;
    private Drawable mNormalMaskDrawable;
    private Drawable mPressedMaskDrawable;
    private Rect mScaleRect = new Rect();
    private Rect mScaleTargetRect = new Rect();
    private Rect mSrcRect = new Rect();
    private int mUserDstHeight = 0;
    private int mUserDstWidth = 0;

    static {
        SRC_IN_MODE = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        CLEAR_MODE = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        TAG = "ThumbnailView";
        sPaint = new Paint();
        long l = System.currentTimeMillis();
        Resources resources = MmsApp.getApp().getResources();
        sLargeBitmapSize = resources.getDimensionPixelSize(2131427404);
        sSmallBitmapSize = resources.getDimensionPixelSize(2131427406);
        sZoomSize = resources.getDimensionPixelSize(2131427403);
        sMaskPaddingSize = resources.getDimensionPixelSize(2131427402);
        sLargeBitmapLongEdgeSize = resources.getDimensionPixelSize(2131427404);
        sLargeBitmapShortEdgeSize = resources.getDimensionPixelSize(2131427405);
        sSmallBitmapLongEdgeSize = resources.getDimensionPixelSize(2131427406);
        sSmallBitmapShortEdgeSize = resources.getDimensionPixelSize(2131427407);
        sMinBitmapSize = resources.getDimensionPixelSize(2131427401);
        sNormalBitmapSize = resources.getDimensionPixelSize(2131427408);
        long l2 = System.currentTimeMillis();
        Log.d((String)TAG, (String)("Initialize cached drawables of ThumbnailView: " + (l2 - l) + "ms"));
    }

    public ThumbnailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.setLayerType(1, null);
    }

    public static void initDummy() {
    }

    private void setZoomAsSquare(boolean bl) {
        this.mIsZoomAsSquare = bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void draw(Canvas canvas) {
        Drawable drawable2 = this.getDrawable();
        if (this.mBackgroundDrawable == null || drawable2 == null) {
            if (drawable2 == null) {
                super.draw(canvas);
                return;
            }
            drawable2.draw(canvas);
            return;
        } else {
            int n = drawable2.getIntrinsicWidth();
            int n2 = drawable2.getIntrinsicHeight();
            if (n <= 0 || n2 <= 0) {
                super.draw(canvas);
                return;
            }
            this.mBackgroundDrawable.setBounds(this.mSrcRect);
            this.mBackgroundDrawable.draw(canvas);
            if (drawable2 instanceof BitmapDrawable) {
                drawable2 = (BitmapDrawable)drawable2;
                Paint paint = drawable2.getPaint();
                Xfermode xfermode = paint.getXfermode();
                paint.setXfermode(SRC_IN_MODE);
                canvas.drawBitmap(drawable2.getBitmap(), this.mScaleRect, this.mScaleTargetRect, paint);
                paint.setXfermode(xfermode);
            } else {
                drawable2.draw(canvas);
            }
            if (this.isPressed() && this.mPressedMaskDrawable != null && (this.mAlignType == 1 || this.mAlignType == 2)) {
                this.mPressedMaskDrawable.setBounds(this.mSrcRect);
                this.mPressedMaskDrawable.draw(canvas);
                return;
            }
            if (this.mNormalMaskDrawable == null) return;
            {
                this.mNormalMaskDrawable.setBounds(this.mSrcRect);
                this.mNormalMaskDrawable.draw(canvas);
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onMeasure(int n, int n2) {
        int n3;
        int n4;
        double d2;
        int n5;
        block23 : {
            Drawable drawable2 = this.getDrawable();
            if (this.mBackgroundDrawable == null || drawable2 == null) {
                if (drawable2 != null) {
                    this.setMeasuredDimension(drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight());
                    return;
                }
                super.onMeasure(n, n2);
                return;
            }
            n4 = drawable2.getIntrinsicWidth();
            n3 = drawable2.getIntrinsicHeight();
            if (n4 <= 0 || n3 <= 0) {
                super.onMeasure(n, n2);
                return;
            }
            if (this.mMaskSizeType == 0) {
                n = this.mUserDstWidth;
                n2 = this.mUserDstHeight;
            } else {
                this.mMaskSizeType = 2;
                n = this.mIsZoomAsSquare ? sLargeBitmapShortEdgeSize : sLargeBitmapLongEdgeSize;
                n2 = sLargeBitmapShortEdgeSize;
                if (this.mAlignType == 0) {
                    if (n4 < sMinBitmapSize || n3 < sMinBitmapSize) {
                        n = n2 = sSmallBitmapShortEdgeSize;
                        this.mMaskSizeType = 1;
                    } else {
                        n = n2 = sNormalBitmapSize;
                    }
                } else if (n4 >= n3) {
                    if (n3 < sMinBitmapSize) {
                        n = this.mIsZoomAsSquare ? sSmallBitmapShortEdgeSize : sSmallBitmapLongEdgeSize;
                        n2 = sSmallBitmapShortEdgeSize;
                        this.mMaskSizeType = 1;
                    }
                } else {
                    n = sLargeBitmapShortEdgeSize;
                    n2 = this.mIsZoomAsSquare ? sLargeBitmapShortEdgeSize : sLargeBitmapLongEdgeSize;
                    if (n4 < sMinBitmapSize) {
                        n = sSmallBitmapShortEdgeSize;
                        n2 = this.mIsZoomAsSquare ? sSmallBitmapShortEdgeSize : sSmallBitmapLongEdgeSize;
                        this.mMaskSizeType = 1;
                    }
                }
            }
            if (this.mAlignType != 1) {
                n5 = n;
                if (this.mAlignType != 2) break block23;
            }
            n5 = n + sZoomSize;
        }
        if ((int)((double)n4 * (d2 = Math.max((double)((double)n5 / (double)n4), (double)((double)n2 / (double)n3)))) > n5) {
            this.mScaleRect.set((int)((double)n4 / 2.0 - (double)n5 / (2.0 * d2)), 0, (int)((double)n4 / 2.0 + (double)n5 / (2.0 * d2)), n3);
        } else {
            this.mScaleRect.set(0, (int)((double)n3 / 2.0 - (double)n2 / (2.0 * d2)), n4, (int)((double)n3 / 2.0 + (double)n2 / (2.0 * d2)));
        }
        if (this.mAlignType == 1 || this.mAlignType == 2) {
            this.mSrcRect.set(0, 0, (n5 += sMaskPaddingSize) - sZoomSize, n2);
        } else {
            this.mSrcRect.set(0, 0, n5, n2);
        }
        if (this.mAlignType == 1) {
            this.mScaleTargetRect.set(sMaskPaddingSize, 0, n5, n2);
        } else if (this.mAlignType == 2) {
            this.mScaleTargetRect.set(- sZoomSize, 0, n5 - sMaskPaddingSize - sZoomSize, n2);
        } else {
            this.mScaleTargetRect.set(0, 0, n5, n2);
        }
        this.setMeasuredDimension(this.mSrcRect.width(), this.mSrcRect.height());
    }

    public void setBackgroundDrawable(Drawable drawable2, int n) {
        super.setBackground(drawable2);
        this.mBackgroundDrawable = drawable2;
        this.mAlignType = n;
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.setImageBitmap(bitmap, false);
        this.mMaskSizeType = 2;
    }

    public void setImageBitmap(Bitmap bitmap, boolean bl) {
        super.setImageBitmap(bitmap);
        this.setZoomAsSquare(bl);
        this.mMaskSizeType = 2;
    }

    public void setImageDrawable(Drawable drawable2) {
        this.setImageDrawable(drawable2, false);
        this.mMaskSizeType = 2;
    }

    public void setImageDrawable(Drawable drawable2, boolean bl) {
        super.setImageDrawable(drawable2);
        this.setZoomAsSquare(bl);
        this.mMaskSizeType = 2;
    }

    public boolean setImageDrawable(Drawable drawable2, Drawable drawable3, int n, int n2) {
        if (n > sLargeBitmapSize || n2 > sLargeBitmapSize) {
            return false;
        }
        super.setImageDrawable(drawable3);
        this.setZoomAsSquare(false);
        this.mBackgroundDrawable = drawable2;
        this.mMaskSizeType = 0;
        this.mAlignType = 0;
        this.mUserDstWidth = n;
        this.mUserDstHeight = n2;
        return true;
    }

    public void setImageResource(int n) {
        this.setImageResource(n, false);
        this.mMaskSizeType = 2;
    }

    public void setImageResource(int n, boolean bl) {
        super.setImageResource(n);
        this.setZoomAsSquare(bl);
        this.mMaskSizeType = 2;
        this.mUserDstWidth = 0;
        this.mUserDstHeight = 0;
    }

    public void setMaskDrawable(Drawable drawable2, Drawable drawable3) {
        this.mPressedMaskDrawable = drawable2;
        this.mNormalMaskDrawable = drawable3;
    }
}

