/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Canvas
 *  android.graphics.ColorFilter
 *  android.graphics.Paint
 *  android.graphics.Paint$Cap
 *  android.graphics.Paint$Style
 *  android.graphics.Path
 *  android.graphics.Path$FillType
 *  android.graphics.Rect
 *  android.graphics.RectF
 *  android.graphics.drawable.Animatable
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.Drawable$Callback
 *  android.util.DisplayMetrics
 *  android.view.View
 *  android.view.animation.Animation
 *  android.view.animation.Animation$AnimationListener
 *  android.view.animation.Interpolator
 *  android.view.animation.LinearInterpolator
 *  java.lang.Math
 *  java.lang.Object
 *  java.util.ArrayList
 */
package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.widget.MaterialProgressDrawable$1;
import android.support.v4.widget.MaterialProgressDrawable$2;
import android.support.v4.widget.MaterialProgressDrawable$3;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

class MaterialProgressDrawable
extends Drawable
implements Animatable {
    private static final int ANIMATION_DURATION = 1332;
    private static final int ARROW_HEIGHT = 5;
    private static final int ARROW_HEIGHT_LARGE = 6;
    private static final float ARROW_OFFSET_ANGLE = 5.0f;
    private static final int ARROW_WIDTH = 10;
    private static final int ARROW_WIDTH_LARGE = 12;
    private static final float CENTER_RADIUS = 8.75f;
    private static final float CENTER_RADIUS_LARGE = 12.5f;
    private static final int CIRCLE_DIAMETER = 40;
    private static final int CIRCLE_DIAMETER_LARGE = 56;
    private static final float COLOR_START_DELAY_OFFSET = 0.75f;
    static final int DEFAULT = 1;
    private static final float END_TRIM_START_DELAY_OFFSET = 0.5f;
    private static final float FULL_ROTATION = 1080.0f;
    static final int LARGE = 0;
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    private static final Interpolator MATERIAL_INTERPOLATOR = new FastOutSlowInInterpolator();
    private static final float MAX_PROGRESS_ARC = 0.8f;
    private static final float NUM_POINTS = 5.0f;
    private static final float START_TRIM_DURATION_OFFSET = 0.5f;
    private static final float STROKE_WIDTH = 2.5f;
    private static final float STROKE_WIDTH_LARGE = 3.0f;
    private final int[] COLORS = new int[]{-16777216};
    private Animation mAnimation;
    private final ArrayList<Animation> mAnimators = new ArrayList();
    private final Drawable.Callback mCallback;
    boolean mFinishing;
    private double mHeight;
    private View mParent;
    private Resources mResources;
    private final Ring mRing;
    private float mRotation;
    private float mRotationCount;
    private double mWidth;

    public MaterialProgressDrawable(Context context, View view) {
        this.mCallback = new MaterialProgressDrawable$3(this);
        this.mParent = view;
        this.mResources = context.getResources();
        this.mRing = new Ring(this.mCallback);
        this.mRing.setColors(this.COLORS);
        this.updateSizes(1);
        this.setupAnimators();
    }

    static /* synthetic */ void access$000(MaterialProgressDrawable materialProgressDrawable, float f2, Ring ring) {
        materialProgressDrawable.applyFinishTranslation(f2, ring);
    }

    static /* synthetic */ float access$100(MaterialProgressDrawable materialProgressDrawable, Ring ring) {
        return materialProgressDrawable.getMinProgressArc(ring);
    }

    static /* synthetic */ void access$200(MaterialProgressDrawable materialProgressDrawable, float f2, Ring ring) {
        materialProgressDrawable.updateRingColor(f2, ring);
    }

    static /* synthetic */ Interpolator access$300() {
        return MATERIAL_INTERPOLATOR;
    }

    static /* synthetic */ float access$400(MaterialProgressDrawable materialProgressDrawable) {
        return materialProgressDrawable.mRotationCount;
    }

    static /* synthetic */ float access$402(MaterialProgressDrawable materialProgressDrawable, float f2) {
        materialProgressDrawable.mRotationCount = f2;
        return f2;
    }

    private void applyFinishTranslation(float f2, Ring ring) {
        this.updateRingColor(f2, ring);
        float f3 = (float)(Math.floor((double)(ring.getStartingRotation() / 0.8f)) + 1.0);
        float f4 = this.getMinProgressArc(ring);
        float f5 = ring.getStartingStartTrim();
        ring.setStartTrim((ring.getStartingEndTrim() - f4 - ring.getStartingStartTrim()) * f2 + f5);
        ring.setEndTrim(ring.getStartingEndTrim());
        f4 = ring.getStartingRotation();
        ring.setRotation((f3 - ring.getStartingRotation()) * f2 + f4);
    }

    private int evaluateColorChange(float f2, int n2, int n3) {
        int n4 = n2;
        n2 = n4 >> 24 & 255;
        int n5 = n4 >> 16 & 255;
        int n6 = n4 >> 8 & 255;
        n3 = n3;
        int n7 = (int)((float)((n3 >> 24 & 255) - n2) * f2);
        int n8 = (int)((float)((n3 >> 16 & 255) - n5) * f2);
        int n9 = (int)((float)((n3 >> 8 & 255) - n6) * f2);
        return n4 + (int)((float)((n3 & 255) - (n4 &= 255)) * f2) | (n2 + n7 << 24 | n5 + n8 << 16 | n9 + n6 << 8);
    }

    private float getMinProgressArc(Ring ring) {
        return (float)Math.toRadians((double)((double)ring.getStrokeWidth() / (6.283185307179586 * ring.getCenterRadius())));
    }

    private float getRotation() {
        return this.mRotation;
    }

    private void setSizeParameters(double d2, double d3, double d4, double d5, float f2, float f3) {
        Ring ring = this.mRing;
        float f4 = this.mResources.getDisplayMetrics().density;
        this.mWidth = (double)f4 * d2;
        this.mHeight = (double)f4 * d3;
        ring.setStrokeWidth((float)d5 * f4);
        ring.setCenterRadius((double)f4 * d4);
        ring.setColorIndex(0);
        ring.setArrowDimensions(f2 * f4, f4 * f3);
        ring.setInsets((int)this.mWidth, (int)this.mHeight);
    }

    private void setupAnimators() {
        Ring ring = this.mRing;
        MaterialProgressDrawable$1 materialProgressDrawable$1 = new MaterialProgressDrawable$1(this, ring);
        materialProgressDrawable$1.setRepeatCount(-1);
        materialProgressDrawable$1.setRepeatMode(1);
        materialProgressDrawable$1.setInterpolator(LINEAR_INTERPOLATOR);
        materialProgressDrawable$1.setAnimationListener((Animation.AnimationListener)new MaterialProgressDrawable$2(this, ring));
        this.mAnimation = materialProgressDrawable$1;
    }

    private void updateRingColor(float f2, Ring ring) {
        if (f2 > 0.75f) {
            ring.setColor(this.evaluateColorChange((f2 - 0.75f) / 0.25f, ring.getStartingColor(), ring.getNextColor()));
        }
    }

    public void draw(Canvas canvas) {
        Rect rect = this.getBounds();
        int n2 = canvas.save();
        canvas.rotate(this.mRotation, rect.exactCenterX(), rect.exactCenterY());
        this.mRing.draw(canvas, rect);
        canvas.restoreToCount(n2);
    }

    public int getAlpha() {
        return this.mRing.getAlpha();
    }

    public int getIntrinsicHeight() {
        return (int)this.mHeight;
    }

    public int getIntrinsicWidth() {
        return (int)this.mWidth;
    }

    public int getOpacity() {
        return -3;
    }

    public boolean isRunning() {
        ArrayList<Animation> arrayList = this.mAnimators;
        int n2 = arrayList.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            Animation animation = (Animation)arrayList.get(i2);
            if (!animation.hasStarted() || animation.hasEnded()) continue;
            return true;
        }
        return false;
    }

    public void setAlpha(int n2) {
        this.mRing.setAlpha(n2);
    }

    public void setArrowScale(float f2) {
        this.mRing.setArrowScale(f2);
    }

    public void setBackgroundColor(int n2) {
        this.mRing.setBackgroundColor(n2);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mRing.setColorFilter(colorFilter);
    }

    public /* varargs */ void setColorSchemeColors(int ... arrn) {
        this.mRing.setColors(arrn);
        this.mRing.setColorIndex(0);
    }

    public void setProgressRotation(float f2) {
        this.mRing.setRotation(f2);
    }

    void setRotation(float f2) {
        this.mRotation = f2;
        this.invalidateSelf();
    }

    public void setStartEndTrim(float f2, float f3) {
        this.mRing.setStartTrim(f2);
        this.mRing.setEndTrim(f3);
    }

    public void showArrow(boolean bl2) {
        this.mRing.setShowArrow(bl2);
    }

    public void start() {
        this.mAnimation.reset();
        this.mRing.storeOriginals();
        if (this.mRing.getEndTrim() != this.mRing.getStartTrim()) {
            this.mFinishing = true;
            this.mAnimation.setDuration(666);
            this.mParent.startAnimation(this.mAnimation);
            return;
        }
        this.mRing.setColorIndex(0);
        this.mRing.resetOriginals();
        this.mAnimation.setDuration(1332);
        this.mParent.startAnimation(this.mAnimation);
    }

    public void stop() {
        this.mParent.clearAnimation();
        this.setRotation(0.0f);
        this.mRing.setShowArrow(false);
        this.mRing.setColorIndex(0);
        this.mRing.resetOriginals();
    }

    public void updateSizes(@ProgressDrawableSize int n2) {
        if (n2 == 0) {
            this.setSizeParameters(56.0, 56.0, 12.5, 3.0, 12.0f, 6.0f);
            return;
        }
        this.setSizeParameters(40.0, 40.0, 8.75, 2.5, 10.0f, 5.0f);
    }

    @Retention(value=RetentionPolicy.CLASS)
    @IntDef(value={0, 1})
    public static @interface ProgressDrawableSize {
    }

    static class Ring {
        private int mAlpha;
        private Path mArrow;
        private int mArrowHeight;
        private final Paint mArrowPaint = new Paint();
        private float mArrowScale;
        private int mArrowWidth;
        private int mBackgroundColor;
        private final Drawable.Callback mCallback;
        private final Paint mCirclePaint = new Paint(1);
        private int mColorIndex;
        private int[] mColors;
        private int mCurrentColor;
        private float mEndTrim = 0.0f;
        private final Paint mPaint = new Paint();
        private double mRingCenterRadius;
        private float mRotation = 0.0f;
        private boolean mShowArrow;
        private float mStartTrim = 0.0f;
        private float mStartingEndTrim;
        private float mStartingRotation;
        private float mStartingStartTrim;
        private float mStrokeInset = 2.5f;
        private float mStrokeWidth = 5.0f;
        private final RectF mTempBounds = new RectF();

        public Ring(Drawable.Callback callback) {
            this.mCallback = callback;
            this.mPaint.setStrokeCap(Paint.Cap.SQUARE);
            this.mPaint.setAntiAlias(true);
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mArrowPaint.setStyle(Paint.Style.FILL);
            this.mArrowPaint.setAntiAlias(true);
        }

        /*
         * Enabled aggressive block sorting
         */
        private void drawTriangle(Canvas canvas, float f2, float f3, Rect rect) {
            if (this.mShowArrow) {
                if (this.mArrow == null) {
                    this.mArrow = new Path();
                    this.mArrow.setFillType(Path.FillType.EVEN_ODD);
                } else {
                    this.mArrow.reset();
                }
                float f4 = (int)this.mStrokeInset / 2;
                float f5 = this.mArrowScale;
                float f6 = (float)(this.mRingCenterRadius * Math.cos((double)0.0) + (double)rect.exactCenterX());
                float f7 = (float)(this.mRingCenterRadius * Math.sin((double)0.0) + (double)rect.exactCenterY());
                this.mArrow.moveTo(0.0f, 0.0f);
                this.mArrow.lineTo((float)this.mArrowWidth * this.mArrowScale, 0.0f);
                this.mArrow.lineTo((float)this.mArrowWidth * this.mArrowScale / 2.0f, (float)this.mArrowHeight * this.mArrowScale);
                this.mArrow.offset(f6 - f4 * f5, f7);
                this.mArrow.close();
                this.mArrowPaint.setColor(this.mCurrentColor);
                canvas.rotate(f2 + f3 - 5.0f, rect.exactCenterX(), rect.exactCenterY());
                canvas.drawPath(this.mArrow, this.mArrowPaint);
            }
        }

        private int getNextColorIndex() {
            return (this.mColorIndex + 1) % this.mColors.length;
        }

        private void invalidateSelf() {
            this.mCallback.invalidateDrawable(null);
        }

        public void draw(Canvas canvas, Rect rect) {
            RectF rectF = this.mTempBounds;
            rectF.set(rect);
            rectF.inset(this.mStrokeInset, this.mStrokeInset);
            float f2 = (this.mStartTrim + this.mRotation) * 360.0f;
            float f3 = (this.mEndTrim + this.mRotation) * 360.0f - f2;
            this.mPaint.setColor(this.mCurrentColor);
            canvas.drawArc(rectF, f2, f3, false, this.mPaint);
            this.drawTriangle(canvas, f2, f3, rect);
            if (this.mAlpha < 255) {
                this.mCirclePaint.setColor(this.mBackgroundColor);
                this.mCirclePaint.setAlpha(255 - this.mAlpha);
                canvas.drawCircle(rect.exactCenterX(), rect.exactCenterY(), (float)(rect.width() / 2), this.mCirclePaint);
            }
        }

        public int getAlpha() {
            return this.mAlpha;
        }

        public double getCenterRadius() {
            return this.mRingCenterRadius;
        }

        public float getEndTrim() {
            return this.mEndTrim;
        }

        public float getInsets() {
            return this.mStrokeInset;
        }

        public int getNextColor() {
            return this.mColors[this.getNextColorIndex()];
        }

        public float getRotation() {
            return this.mRotation;
        }

        public float getStartTrim() {
            return this.mStartTrim;
        }

        public int getStartingColor() {
            return this.mColors[this.mColorIndex];
        }

        public float getStartingEndTrim() {
            return this.mStartingEndTrim;
        }

        public float getStartingRotation() {
            return this.mStartingRotation;
        }

        public float getStartingStartTrim() {
            return this.mStartingStartTrim;
        }

        public float getStrokeWidth() {
            return this.mStrokeWidth;
        }

        public void goToNextColor() {
            this.setColorIndex(this.getNextColorIndex());
        }

        public void resetOriginals() {
            this.mStartingStartTrim = 0.0f;
            this.mStartingEndTrim = 0.0f;
            this.mStartingRotation = 0.0f;
            this.setStartTrim(0.0f);
            this.setEndTrim(0.0f);
            this.setRotation(0.0f);
        }

        public void setAlpha(int n2) {
            this.mAlpha = n2;
        }

        public void setArrowDimensions(float f2, float f3) {
            this.mArrowWidth = (int)f2;
            this.mArrowHeight = (int)f3;
        }

        public void setArrowScale(float f2) {
            if (f2 != this.mArrowScale) {
                this.mArrowScale = f2;
                this.invalidateSelf();
            }
        }

        public void setBackgroundColor(int n2) {
            this.mBackgroundColor = n2;
        }

        public void setCenterRadius(double d2) {
            this.mRingCenterRadius = d2;
        }

        public void setColor(int n2) {
            this.mCurrentColor = n2;
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.mPaint.setColorFilter(colorFilter);
            this.invalidateSelf();
        }

        public void setColorIndex(int n2) {
            this.mColorIndex = n2;
            this.mCurrentColor = this.mColors[this.mColorIndex];
        }

        public void setColors(@NonNull int[] arrn) {
            this.mColors = arrn;
            this.setColorIndex(0);
        }

        public void setEndTrim(float f2) {
            this.mEndTrim = f2;
            this.invalidateSelf();
        }

        /*
         * Enabled aggressive block sorting
         */
        public void setInsets(int n2, int n3) {
            float f2 = Math.min((int)n2, (int)n3);
            f2 = this.mRingCenterRadius <= 0.0 || f2 < 0.0f ? (float)Math.ceil((double)(this.mStrokeWidth / 2.0f)) : (float)((double)(f2 / 2.0f) - this.mRingCenterRadius);
            this.mStrokeInset = f2;
        }

        public void setRotation(float f2) {
            this.mRotation = f2;
            this.invalidateSelf();
        }

        public void setShowArrow(boolean bl2) {
            if (this.mShowArrow != bl2) {
                this.mShowArrow = bl2;
                this.invalidateSelf();
            }
        }

        public void setStartTrim(float f2) {
            this.mStartTrim = f2;
            this.invalidateSelf();
        }

        public void setStrokeWidth(float f2) {
            this.mStrokeWidth = f2;
            this.mPaint.setStrokeWidth(f2);
            this.invalidateSelf();
        }

        public void storeOriginals() {
            this.mStartingStartTrim = this.mStartTrim;
            this.mStartingEndTrim = this.mEndTrim;
            this.mStartingRotation = this.mRotation;
        }
    }

}

