/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.res.Resources
 *  android.os.SystemClock
 *  android.util.DisplayMetrics
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnTouchListener
 *  android.view.ViewConfiguration
 *  android.view.animation.AccelerateInterpolator
 *  android.view.animation.AnimationUtils
 *  android.view.animation.Interpolator
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

public abstract class AutoScrollHelper
implements View.OnTouchListener {
    private static final int DEFAULT_ACTIVATION_DELAY = ViewConfiguration.getTapTimeout();
    private static final int DEFAULT_EDGE_TYPE = 1;
    private static final float DEFAULT_MAXIMUM_EDGE = Float.MAX_VALUE;
    private static final int DEFAULT_MAXIMUM_VELOCITY_DIPS = 1575;
    private static final int DEFAULT_MINIMUM_VELOCITY_DIPS = 315;
    private static final int DEFAULT_RAMP_DOWN_DURATION = 500;
    private static final int DEFAULT_RAMP_UP_DURATION = 500;
    private static final float DEFAULT_RELATIVE_EDGE = 0.2f;
    private static final float DEFAULT_RELATIVE_VELOCITY = 1.0f;
    public static final int EDGE_TYPE_INSIDE = 0;
    public static final int EDGE_TYPE_INSIDE_EXTEND = 1;
    public static final int EDGE_TYPE_OUTSIDE = 2;
    private static final int HORIZONTAL = 0;
    public static final float NO_MAX = Float.MAX_VALUE;
    public static final float NO_MIN = 0.0f;
    public static final float RELATIVE_UNSPECIFIED = 0.0f;
    private static final int VERTICAL = 1;
    private int mActivationDelay;
    private boolean mAlreadyDelayed;
    private boolean mAnimating;
    private final Interpolator mEdgeInterpolator = new AccelerateInterpolator();
    private int mEdgeType;
    private boolean mEnabled;
    private boolean mExclusive;
    private float[] mMaximumEdges = new float[]{Float.MAX_VALUE, Float.MAX_VALUE};
    private float[] mMaximumVelocity = new float[]{Float.MAX_VALUE, Float.MAX_VALUE};
    private float[] mMinimumVelocity = new float[]{0.0f, 0.0f};
    private boolean mNeedsCancel;
    private boolean mNeedsReset;
    private float[] mRelativeEdges = new float[]{0.0f, 0.0f};
    private float[] mRelativeVelocity = new float[]{0.0f, 0.0f};
    private Runnable mRunnable;
    private final ClampedScroller mScroller = new ClampedScroller();
    private final View mTarget;

    public AutoScrollHelper(View view) {
        this.mTarget = view;
        view = Resources.getSystem().getDisplayMetrics();
        int n2 = (int)(1575.0f * view.density + 0.5f);
        int n3 = (int)(view.density * 315.0f + 0.5f);
        this.setMaximumVelocity(n2, n2);
        this.setMinimumVelocity(n3, n3);
        this.setEdgeType(1);
        this.setMaximumEdges(Float.MAX_VALUE, Float.MAX_VALUE);
        this.setRelativeEdges(0.2f, 0.2f);
        this.setRelativeVelocity(1.0f, 1.0f);
        this.setActivationDelay(DEFAULT_ACTIVATION_DELAY);
        this.setRampUpDuration(500);
        this.setRampDownDuration(500);
    }

    private void cancelTargetTouch() {
        long l2 = SystemClock.uptimeMillis();
        MotionEvent motionEvent = MotionEvent.obtain((long)l2, (long)l2, (int)3, (float)0.0f, (float)0.0f, (int)0);
        this.mTarget.onTouchEvent(motionEvent);
        motionEvent.recycle();
    }

    private float computeTargetVelocity(int n2, float f2, float f3, float f4) {
        if ((f2 = this.getEdgeValue(this.mRelativeEdges[n2], f3, this.mMaximumEdges[n2], f2)) == 0.0f) {
            return 0.0f;
        }
        float f5 = this.mRelativeVelocity[n2];
        f3 = this.mMinimumVelocity[n2];
        float f6 = this.mMaximumVelocity[n2];
        f4 = f5 * f4;
        if (f2 > 0.0f) {
            return AutoScrollHelper.constrain(f2 * f4, f3, f6);
        }
        return - AutoScrollHelper.constrain((- f2) * f4, f3, f6);
    }

    private static float constrain(float f2, float f3, float f4) {
        if (f2 > f4) {
            return f4;
        }
        if (f2 < f3) {
            return f3;
        }
        return f2;
    }

    private static int constrain(int n2, int n3, int n4) {
        if (n2 > n4) {
            return n4;
        }
        if (n2 < n3) {
            return n3;
        }
        return n2;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private float constrainEdgeValue(float f2, float f3) {
        if (f3 == 0.0f) {
            return 0.0f;
        }
        switch (this.mEdgeType) {
            default: {
                return 0.0f;
            }
            case 0: 
            case 1: {
                if (f2 >= f3) return 0.0f;
                if (f2 >= 0.0f) {
                    return 1.0f - f2 / f3;
                }
                if (!this.mAnimating) return 0.0f;
                if (this.mEdgeType != 1) return 0.0f;
                return 1.0f;
            }
            case 2: 
        }
        if (f2 >= 0.0f) return 0.0f;
        return f2 / (- f3);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private float getEdgeValue(float f2, float f3, float f4, float f5) {
        float f6 = 0.0f;
        f2 = AutoScrollHelper.constrain(f2 * f3, 0.0f, f4);
        f4 = this.constrainEdgeValue(f5, f2);
        if ((f3 = this.constrainEdgeValue(f3 - f5, f2) - f4) < 0.0f) {
            f2 = - this.mEdgeInterpolator.getInterpolation(- f3);
            do {
                return AutoScrollHelper.constrain(f2, -1.0f, 1.0f);
                break;
            } while (true);
        }
        f2 = f6;
        if (f3 <= 0.0f) return f2;
        f2 = this.mEdgeInterpolator.getInterpolation(f3);
        return AutoScrollHelper.constrain(f2, -1.0f, 1.0f);
    }

    private void requestStop() {
        if (this.mNeedsReset) {
            this.mAnimating = false;
            return;
        }
        this.mScroller.requestStop();
    }

    private boolean shouldAnimate() {
        ClampedScroller clampedScroller = this.mScroller;
        int n2 = clampedScroller.getVerticalDirection();
        int n3 = clampedScroller.getHorizontalDirection();
        if (n2 != 0 && this.canTargetScrollVertically(n2) || n3 != 0 && this.canTargetScrollHorizontally(n3)) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void startAnimating() {
        if (this.mRunnable == null) {
            this.mRunnable = new ScrollAnimationRunnable();
        }
        this.mAnimating = true;
        this.mNeedsReset = true;
        if (!this.mAlreadyDelayed && this.mActivationDelay > 0) {
            ViewCompat.postOnAnimationDelayed(this.mTarget, this.mRunnable, this.mActivationDelay);
        } else {
            this.mRunnable.run();
        }
        this.mAlreadyDelayed = true;
    }

    public abstract boolean canTargetScrollHorizontally(int var1);

    public abstract boolean canTargetScrollVertically(int var1);

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public boolean isExclusive() {
        return this.mExclusive;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean bl2 = true;
        if (!this.mEnabled) {
            return false;
        }
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            case 0: {
                this.mNeedsCancel = true;
                this.mAlreadyDelayed = false;
            }
            case 2: {
                float f2 = this.computeTargetVelocity(0, motionEvent.getX(), view.getWidth(), this.mTarget.getWidth());
                float f3 = this.computeTargetVelocity(1, motionEvent.getY(), view.getHeight(), this.mTarget.getHeight());
                this.mScroller.setTargetVelocity(f2, f3);
                if (this.mAnimating || !this.shouldAnimate()) break;
                this.startAnimating();
                break;
            }
            case 1: 
            case 3: {
                this.requestStop();
            }
        }
        if (!this.mExclusive) return false;
        if (!this.mAnimating) return false;
        return bl2;
    }

    public abstract void scrollTargetBy(int var1, int var2);

    public AutoScrollHelper setActivationDelay(int n2) {
        this.mActivationDelay = n2;
        return this;
    }

    public AutoScrollHelper setEdgeType(int n2) {
        this.mEdgeType = n2;
        return this;
    }

    public AutoScrollHelper setEnabled(boolean bl2) {
        if (this.mEnabled && !bl2) {
            this.requestStop();
        }
        this.mEnabled = bl2;
        return this;
    }

    public AutoScrollHelper setExclusive(boolean bl2) {
        this.mExclusive = bl2;
        return this;
    }

    public AutoScrollHelper setMaximumEdges(float f2, float f3) {
        this.mMaximumEdges[0] = f2;
        this.mMaximumEdges[1] = f3;
        return this;
    }

    public AutoScrollHelper setMaximumVelocity(float f2, float f3) {
        this.mMaximumVelocity[0] = f2 / 1000.0f;
        this.mMaximumVelocity[1] = f3 / 1000.0f;
        return this;
    }

    public AutoScrollHelper setMinimumVelocity(float f2, float f3) {
        this.mMinimumVelocity[0] = f2 / 1000.0f;
        this.mMinimumVelocity[1] = f3 / 1000.0f;
        return this;
    }

    public AutoScrollHelper setRampDownDuration(int n2) {
        this.mScroller.setRampDownDuration(n2);
        return this;
    }

    public AutoScrollHelper setRampUpDuration(int n2) {
        this.mScroller.setRampUpDuration(n2);
        return this;
    }

    public AutoScrollHelper setRelativeEdges(float f2, float f3) {
        this.mRelativeEdges[0] = f2;
        this.mRelativeEdges[1] = f3;
        return this;
    }

    public AutoScrollHelper setRelativeVelocity(float f2, float f3) {
        this.mRelativeVelocity[0] = f2 / 1000.0f;
        this.mRelativeVelocity[1] = f3 / 1000.0f;
        return this;
    }

    static class ClampedScroller {
        private long mDeltaTime = 0;
        private int mDeltaX = 0;
        private int mDeltaY = 0;
        private int mEffectiveRampDown;
        private int mRampDownDuration;
        private int mRampUpDuration;
        private long mStartTime = Long.MIN_VALUE;
        private long mStopTime = -1;
        private float mStopValue;
        private float mTargetVelocityX;
        private float mTargetVelocityY;

        private float getValueAt(long l2) {
            if (l2 < this.mStartTime) {
                return 0.0f;
            }
            if (this.mStopTime < 0 || l2 < this.mStopTime) {
                return AutoScrollHelper.constrain((float)(l2 - this.mStartTime) / (float)this.mRampUpDuration, 0.0f, 1.0f) * 0.5f;
            }
            long l3 = this.mStopTime;
            float f2 = this.mStopValue;
            float f3 = this.mStopValue;
            return AutoScrollHelper.constrain((float)(l2 - l3) / (float)this.mEffectiveRampDown, 0.0f, 1.0f) * f3 + (1.0f - f2);
        }

        private float interpolateValue(float f2) {
            return -4.0f * f2 * f2 + 4.0f * f2;
        }

        public void computeScrollDelta() {
            if (this.mDeltaTime == 0) {
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            }
            long l2 = AnimationUtils.currentAnimationTimeMillis();
            float f2 = this.interpolateValue(this.getValueAt(l2));
            long l3 = l2 - this.mDeltaTime;
            this.mDeltaTime = l2;
            this.mDeltaX = (int)((float)l3 * f2 * this.mTargetVelocityX);
            this.mDeltaY = (int)((float)l3 * f2 * this.mTargetVelocityY);
        }

        public int getDeltaX() {
            return this.mDeltaX;
        }

        public int getDeltaY() {
            return this.mDeltaY;
        }

        public int getHorizontalDirection() {
            return (int)(this.mTargetVelocityX / Math.abs((float)this.mTargetVelocityX));
        }

        public int getVerticalDirection() {
            return (int)(this.mTargetVelocityY / Math.abs((float)this.mTargetVelocityY));
        }

        public boolean isFinished() {
            if (this.mStopTime > 0 && AnimationUtils.currentAnimationTimeMillis() > this.mStopTime + (long)this.mEffectiveRampDown) {
                return true;
            }
            return false;
        }

        public void requestStop() {
            long l2 = AnimationUtils.currentAnimationTimeMillis();
            this.mEffectiveRampDown = AutoScrollHelper.constrain((int)(l2 - this.mStartTime), 0, this.mRampDownDuration);
            this.mStopValue = this.getValueAt(l2);
            this.mStopTime = l2;
        }

        public void setRampDownDuration(int n2) {
            this.mRampDownDuration = n2;
        }

        public void setRampUpDuration(int n2) {
            this.mRampUpDuration = n2;
        }

        public void setTargetVelocity(float f2, float f3) {
            this.mTargetVelocityX = f2;
            this.mTargetVelocityY = f3;
        }

        public void start() {
            this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
            this.mStopTime = -1;
            this.mDeltaTime = this.mStartTime;
            this.mStopValue = 0.5f;
            this.mDeltaX = 0;
            this.mDeltaY = 0;
        }
    }

    class ScrollAnimationRunnable
    implements Runnable {
        private ScrollAnimationRunnable() {
        }

        @Override
        public void run() {
            ClampedScroller clampedScroller;
            if (!AutoScrollHelper.this.mAnimating) {
                return;
            }
            if (AutoScrollHelper.this.mNeedsReset) {
                AutoScrollHelper.this.mNeedsReset = false;
                AutoScrollHelper.this.mScroller.start();
            }
            if ((clampedScroller = AutoScrollHelper.this.mScroller).isFinished() || !AutoScrollHelper.this.shouldAnimate()) {
                AutoScrollHelper.this.mAnimating = false;
                return;
            }
            if (AutoScrollHelper.this.mNeedsCancel) {
                AutoScrollHelper.this.mNeedsCancel = false;
                AutoScrollHelper.this.cancelTargetTouch();
            }
            clampedScroller.computeScrollDelta();
            int n2 = clampedScroller.getDeltaX();
            int n3 = clampedScroller.getDeltaY();
            AutoScrollHelper.this.scrollTargetBy(n2, n3);
            ViewCompat.postOnAnimation(AutoScrollHelper.this.mTarget, this);
        }
    }

}

