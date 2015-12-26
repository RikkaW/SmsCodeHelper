/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.animation.TimeInterpolator
 *  android.animation.ValueAnimator
 *  android.animation.ValueAnimator$AnimatorUpdateListener
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.graphics.Canvas
 *  android.graphics.Color
 *  android.graphics.ColorFilter
 *  android.graphics.Paint
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnLayoutChangeListener
 *  android.view.animation.DecelerateInterpolator
 *  android.view.animation.Interpolator
 *  android.view.animation.PathInterpolator
 *  java.lang.Math
 *  java.lang.String
 */
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import java.lang.ref.WeakReference;

public class aqt
extends Drawable {
    private static final Interpolator RIPPLE_IN_INTERPOLATOR = aqt.createInInterpolator();
    private static final Interpolator RIPPLE_OUT_INTERPOLATOR = aqt.createOutInterpolator();
    private int mAlpha;
    private int mAlphaBg = 75;
    private ValueAnimator mAnimation;
    private int mColor = -16777216;
    private int mColorBg = -16777216;
    private Drawable mDrawable;
    private int mHeight;
    private int mInDuration = 0;
    private boolean mIsDown = false;
    private boolean mIsHaveBg;
    private boolean mIsRipple;
    private boolean mIsRippleFade;
    private boolean mIsShrink;
    private boolean mIsUseFadeOut;
    private int mMaxRadius = 0;
    private int mOutDuration = 0;
    private Paint mPaint;
    private Paint mPaintBg;
    private int mRadius;
    private int mStartRadius;
    private WeakReference<View> mView;
    private int mWidth;

    public aqt(View view) {
        this(view, R.attr.mzRippleDefaultStyle);
    }

    public aqt(View view, int n2) {
        if (view == null) {
            throw new IllegalArgumentException("you must use a view to create a RippleDrawableComp");
        }
        TypedArray typedArray = view.getContext().obtainStyledAttributes(null, R.styleable.MzRippleDrawableComp, n2, 0);
        this.mColor = typedArray.getColor(R.styleable.MzRippleDrawableComp_mzRippleColor, -16777216);
        this.mAlpha = Color.alpha((int)this.mColor);
        this.mStartRadius = typedArray.getDimensionPixelSize(R.styleable.MzRippleDrawableComp_mzStartRadius, -1);
        this.mMaxRadius = typedArray.getDimensionPixelSize(R.styleable.MzRippleDrawableComp_mzMaxRadius, 0);
        this.mIsUseFadeOut = typedArray.getBoolean(R.styleable.MzRippleDrawableComp_mzUseFadeOut, false);
        this.mIsHaveBg = typedArray.getBoolean(R.styleable.MzRippleDrawableComp_mzAutoLightBackground, true);
        this.mIsRippleFade = typedArray.getBoolean(R.styleable.MzRippleDrawableComp_mzRippleFade, true);
        this.mInDuration = typedArray.getInt(R.styleable.MzRippleDrawableComp_mzInDuration, 160);
        this.mOutDuration = typedArray.getInt(R.styleable.MzRippleDrawableComp_mzOutDuration, 320);
        this.mIsShrink = typedArray.getBoolean(R.styleable.MzRippleDrawableComp_mzShrink, true);
        typedArray.recycle();
        this.mPaint = new Paint();
        this.mPaint.setColor(this.mColor);
        this.mPaint.setAlpha(this.mAlpha);
        this.mPaint.setAntiAlias(true);
        this.mPaintBg = new Paint();
        this.mPaintBg.setColor(this.mColor);
        this.mPaintBg.setAlpha(this.mAlpha / 2);
        this.mPaintBg.setAntiAlias(true);
        this.mView = new WeakReference<View>(view);
        this.mRadius = this.mStartRadius;
        this.init();
    }

    static /* synthetic */ void access$000(aqt aqt2) {
        aqt2.initValue();
    }

    static /* synthetic */ Paint access$1000(aqt aqt2) {
        return aqt2.mPaint;
    }

    static /* synthetic */ int access$102(aqt aqt2, int n2) {
        aqt2.mRadius = n2;
        return n2;
    }

    static /* synthetic */ Paint access$1100(aqt aqt2) {
        return aqt2.mPaintBg;
    }

    static /* synthetic */ int access$200(aqt aqt2) {
        return aqt2.mMaxRadius;
    }

    static /* synthetic */ boolean access$302(aqt aqt2, boolean bl2) {
        aqt2.mIsRipple = bl2;
        return bl2;
    }

    static /* synthetic */ boolean access$400(aqt aqt2) {
        return aqt2.mIsDown;
    }

    static /* synthetic */ boolean access$500(aqt aqt2) {
        return aqt2.mIsRippleFade;
    }

    static /* synthetic */ boolean access$600(aqt aqt2) {
        return aqt2.mIsShrink;
    }

    static /* synthetic */ int access$700(aqt aqt2) {
        return aqt2.mStartRadius;
    }

    static /* synthetic */ boolean access$800(aqt aqt2) {
        return aqt2.mIsUseFadeOut;
    }

    static /* synthetic */ int access$900(aqt aqt2) {
        return aqt2.mAlpha;
    }

    private void cancelAnimation() {
        if (this.mAnimation != null && (this.mAnimation.isStarted() || this.mAnimation.isRunning())) {
            this.mAnimation.cancel();
        }
    }

    private static Interpolator createInInterpolator() {
        if (Build.VERSION.SDK_INT >= 21) {
            return new PathInterpolator(0.0f, 0.0f, 0.1f, 1.0f);
        }
        return new DecelerateInterpolator();
    }

    private static Interpolator createOutInterpolator() {
        if (Build.VERSION.SDK_INT >= 21) {
            return new PathInterpolator(0.66f, 0.0f, 0.67f, 1.0f);
        }
        return new DecelerateInterpolator();
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void initValue() {
        View view = this.mView.get();
        if (view == null) {
            return;
        }
        this.mWidth = view.getWidth();
        this.mHeight = view.getHeight();
        this.setBounds(new Rect(0, 0, this.mWidth, this.mHeight));
        if (this.mMaxRadius <= 0) {
            this.mMaxRadius = (int)Math.hypot((double)this.mWidth, (double)this.mHeight) / 2;
        }
        if (this.mStartRadius >= 0) return;
        this.mStartRadius = (int)((float)this.mMaxRadius * 0.825f);
    }

    public void draw(Canvas canvas) {
        if (this.mDrawable != null) {
            this.mDrawable.draw(canvas);
        }
        if (this.mIsRipple || this.mIsDown) {
            if (this.mIsHaveBg) {
                canvas.drawCircle((float)(this.mWidth / 2), (float)(this.mHeight / 2), (float)this.mMaxRadius, this.mPaintBg);
            }
            canvas.drawCircle((float)(this.mWidth / 2), (float)(this.mHeight / 2), (float)this.mRadius, this.mPaint);
        }
    }

    public int getOpacity() {
        return this.mAlpha;
    }

    public void init() {
        if (this.mView == null) {
            return;
        }
        this.mView.get().setClickable(true);
        this.mView.get().addOnLayoutChangeListener((View.OnLayoutChangeListener)new aqu(this));
        this.mView.get().post((Runnable)new aqv(this));
    }

    public boolean isStateful() {
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected boolean onStateChange(int[] arrn) {
        boolean bl2 = super.onStateChange(arrn);
        int n2 = arrn.length;
        int n3 = 0;
        boolean bl3 = false;
        boolean bl4 = false;
        do {
            if (n3 >= n2) {
                if (!this.mIsDown) return bl2;
                this.mIsDown = false;
                this.invalidateSelf();
                if (this.mIsRipple) return bl2;
                if (!this.mIsRippleFade) return bl2;
                this.rippleFade();
                return bl2;
            }
            int n4 = arrn[n3];
            if (n4 == 16842910) {
                bl4 = true;
            }
            if (n4 == 16842919) {
                bl3 = true;
            }
            ++n3;
        } while (true);
    }

    public void ripple() {
        this.cancelAnimation();
        this.mPaint.setAlpha(this.mAlpha);
        this.mPaintBg.setAlpha(this.mAlpha / 2);
        this.mIsRipple = true;
        this.mRadius = this.mStartRadius;
        this.mAnimation = ValueAnimator.ofInt((int[])new int[]{this.mRadius, this.mMaxRadius});
        this.mAnimation.addUpdateListener((ValueAnimator.AnimatorUpdateListener)new aqw(this));
        this.mAnimation.setDuration((long)this.mInDuration);
        this.mAnimation.setInterpolator((TimeInterpolator)RIPPLE_IN_INTERPOLATOR);
        this.mAnimation.start();
    }

    public void rippleFade() {
        this.cancelAnimation();
        this.mPaint.setAlpha(this.mAlpha);
        this.mIsRipple = true;
        this.mRadius = this.mMaxRadius;
        float f2 = (float)this.mStartRadius / (float)this.mMaxRadius;
        this.mAnimation = ValueAnimator.ofInt((int[])new int[]{this.mMaxRadius, this.mStartRadius});
        this.mAnimation.addUpdateListener((ValueAnimator.AnimatorUpdateListener)new aqx(this, f2));
        this.mAnimation.setDuration((long)this.mOutDuration);
        this.mAnimation.setInterpolator((TimeInterpolator)RIPPLE_OUT_INTERPOLATOR);
        this.mAnimation.start();
    }

    public void setAlpha(int n2) {
        this.mAlpha = n2;
        this.mPaint.setAlpha(this.mAlpha);
    }

    public void setAlphaBg(int n2) {
        this.mAlphaBg = n2;
        this.mPaintBg.setAlpha(this.mAlphaBg);
    }

    public void setBounds(Rect rect) {
        if (this.mDrawable != null) {
            this.mDrawable.setBounds(rect);
        }
    }

    public void setColor(int n2) {
        this.mColor = n2;
        this.mPaint.setColor(this.mColor);
    }

    public void setColorBg(int n2) {
        this.mColorBg = n2;
        this.mPaintBg.setColor(this.mColorBg);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    public void setDrawable(Drawable drawable2) {
        this.mDrawable = drawable2;
    }

    public void setInDuration(int n2) {
        this.mInDuration = n2;
    }

    public void setIsHaveBg(boolean bl2) {
        this.mIsHaveBg = bl2;
    }

    public void setIsRippleFade(boolean bl2) {
        this.mIsRippleFade = bl2;
    }

    public void setIsShrink(boolean bl2) {
        this.mIsShrink = bl2;
    }

    public void setIsUseFadeOut(boolean bl2) {
        this.mIsUseFadeOut = bl2;
    }

    public void setMaxRadius(int n2) {
        this.mMaxRadius = n2;
    }

    public void setOutDuration(int n2) {
        this.mOutDuration = n2;
    }

    public void setStartRadius(int n2) {
        this.mStartRadius = n2;
    }
}

