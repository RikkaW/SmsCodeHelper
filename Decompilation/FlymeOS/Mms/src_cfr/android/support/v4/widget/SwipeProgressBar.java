/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Rect
 *  android.graphics.RectF
 *  android.view.View
 *  android.view.animation.AnimationUtils
 *  android.view.animation.Interpolator
 *  java.lang.Object
 */
package android.support.v4.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

final class SwipeProgressBar {
    private static final int ANIMATION_DURATION_MS = 2000;
    private static final int COLOR1 = -1291845632;
    private static final int COLOR2 = Integer.MIN_VALUE;
    private static final int COLOR3 = 1291845632;
    private static final int COLOR4 = 436207616;
    private static final int FINISH_ANIMATION_DURATION_MS = 1000;
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    private Rect mBounds = new Rect();
    private final RectF mClipRect = new RectF();
    private int mColor1;
    private int mColor2;
    private int mColor3;
    private int mColor4;
    private long mFinishTime;
    private final Paint mPaint = new Paint();
    private View mParent;
    private boolean mRunning;
    private long mStartTime;
    private float mTriggerPercentage;

    public SwipeProgressBar(View view) {
        this.mParent = view;
        this.mColor1 = -1291845632;
        this.mColor2 = Integer.MIN_VALUE;
        this.mColor3 = 1291845632;
        this.mColor4 = 436207616;
    }

    private void drawCircle(Canvas canvas, float f2, float f3, int n2, float f4) {
        this.mPaint.setColor(n2);
        canvas.save();
        canvas.translate(f2, f3);
        f3 = INTERPOLATOR.getInterpolation(f4);
        canvas.scale(f3, f3);
        canvas.drawCircle(0.0f, 0.0f, f2, this.mPaint);
        canvas.restore();
    }

    private void drawTrigger(Canvas canvas, int n2, int n3) {
        this.mPaint.setColor(this.mColor1);
        canvas.drawCircle((float)n2, (float)n3, (float)n2 * this.mTriggerPercentage, this.mPaint);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    void draw(Canvas var1_1) {
        var6_2 = this.mBounds.width();
        var9_3 = this.mBounds.height();
        var7_4 = var6_2 / 2;
        var8_5 = var9_3 / 2;
        var5_6 = var1_1.save();
        var1_1.clipRect(this.mBounds);
        if (!this.mRunning && this.mFinishTime <= 0) ** GOTO lbl23
        var10_7 = AnimationUtils.currentAnimationTimeMillis();
        var12_8 = this.mStartTime;
        var14_9 = (var10_7 - this.mStartTime) / 2000;
        var2_10 = (float)((var10_7 - var12_8) % 2000) / 20.0f;
        if (this.mRunning) ** GOTO lbl30
        if (var10_7 - this.mFinishTime >= 1000) {
            this.mFinishTime = 0;
            return;
        }
        var3_11 = (float)((var10_7 - this.mFinishTime) % 1000) / 10.0f / 100.0f;
        var4_12 = var6_2 / 2;
        var3_11 = SwipeProgressBar.INTERPOLATOR.getInterpolation(var3_11) * var4_12;
        this.mClipRect.set((float)var7_4 - var3_11, 0.0f, var3_11 + (float)var7_4, (float)var9_3);
        var1_1.saveLayerAlpha(this.mClipRect, 0, 0);
        var6_2 = 1;
        ** GOTO lbl31
lbl23: // 1 sources:
        var6_2 = var5_6;
        if (this.mTriggerPercentage > 0.0f) {
            var6_2 = var5_6;
            if ((double)this.mTriggerPercentage <= 1.0) {
                this.drawTrigger(var1_1, var7_4, var8_5);
                var6_2 = var5_6;
            }
        }
        ** GOTO lbl66
lbl30: // 1 sources:
        var6_2 = 0;
lbl31: // 2 sources:
        if (var14_9 == 0) {
            var1_1.drawColor(this.mColor1);
        } else if (var2_10 >= 0.0f && var2_10 < 25.0f) {
            var1_1.drawColor(this.mColor4);
        } else if (var2_10 >= 25.0f && var2_10 < 50.0f) {
            var1_1.drawColor(this.mColor1);
        } else if (var2_10 >= 50.0f && var2_10 < 75.0f) {
            var1_1.drawColor(this.mColor2);
        } else {
            var1_1.drawColor(this.mColor3);
        }
        if (var2_10 >= 0.0f && var2_10 <= 25.0f) {
            var3_11 = (25.0f + var2_10) * 2.0f / 100.0f;
            this.drawCircle(var1_1, var7_4, var8_5, this.mColor1, var3_11);
        }
        if (var2_10 >= 0.0f && var2_10 <= 50.0f) {
            var3_11 = 2.0f * var2_10 / 100.0f;
            this.drawCircle(var1_1, var7_4, var8_5, this.mColor2, var3_11);
        }
        if (var2_10 >= 25.0f && var2_10 <= 75.0f) {
            var3_11 = (var2_10 - 25.0f) * 2.0f / 100.0f;
            this.drawCircle(var1_1, var7_4, var8_5, this.mColor3, var3_11);
        }
        if (var2_10 >= 50.0f && var2_10 <= 100.0f) {
            var3_11 = (var2_10 - 50.0f) * 2.0f / 100.0f;
            this.drawCircle(var1_1, var7_4, var8_5, this.mColor4, var3_11);
        }
        if (var2_10 >= 75.0f && var2_10 <= 100.0f) {
            var2_10 = (var2_10 - 75.0f) * 2.0f / 100.0f;
            this.drawCircle(var1_1, var7_4, var8_5, this.mColor1, var2_10);
        }
        if (this.mTriggerPercentage > 0.0f && var6_2 != 0) {
            var1_1.restoreToCount(var5_6);
            var5_6 = var1_1.save();
            var1_1.clipRect(this.mBounds);
            this.drawTrigger(var1_1, var7_4, var8_5);
        }
        ViewCompat.postInvalidateOnAnimation(this.mParent, this.mBounds.left, this.mBounds.top, this.mBounds.right, this.mBounds.bottom);
        var6_2 = var5_6;
lbl66: // 2 sources:
        var1_1.restoreToCount(var6_2);
    }

    boolean isRunning() {
        if (this.mRunning || this.mFinishTime > 0) {
            return true;
        }
        return false;
    }

    void setBounds(int n2, int n3, int n4, int n5) {
        this.mBounds.left = n2;
        this.mBounds.top = n3;
        this.mBounds.right = n4;
        this.mBounds.bottom = n5;
    }

    void setColorScheme(int n2, int n3, int n4, int n5) {
        this.mColor1 = n2;
        this.mColor2 = n3;
        this.mColor3 = n4;
        this.mColor4 = n5;
    }

    void setTriggerPercentage(float f2) {
        this.mTriggerPercentage = f2;
        this.mStartTime = 0;
        ViewCompat.postInvalidateOnAnimation(this.mParent, this.mBounds.left, this.mBounds.top, this.mBounds.right, this.mBounds.bottom);
    }

    void start() {
        if (!this.mRunning) {
            this.mTriggerPercentage = 0.0f;
            this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
            this.mRunning = true;
            this.mParent.postInvalidate();
        }
    }

    void stop() {
        if (this.mRunning) {
            this.mTriggerPercentage = 0.0f;
            this.mFinishTime = AnimationUtils.currentAnimationTimeMillis();
            this.mRunning = false;
            this.mParent.postInvalidate();
        }
    }
}

