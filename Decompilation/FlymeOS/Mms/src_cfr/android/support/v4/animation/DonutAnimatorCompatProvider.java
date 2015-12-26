/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  java.lang.Object
 *  java.util.ArrayList
 */
package android.support.v4.animation;

import android.support.v4.animation.AnimatorListenerCompat;
import android.support.v4.animation.AnimatorProvider;
import android.support.v4.animation.AnimatorUpdateListenerCompat;
import android.support.v4.animation.DonutAnimatorCompatProvider$DonutFloatValueAnimator$1;
import android.support.v4.animation.ValueAnimatorCompat;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

class DonutAnimatorCompatProvider
implements AnimatorProvider {
    DonutAnimatorCompatProvider() {
    }

    @Override
    public ValueAnimatorCompat emptyValueAnimator() {
        return new DonutFloatValueAnimator();
    }

    static class DonutFloatValueAnimator
    implements ValueAnimatorCompat {
        private long mDuration = 200;
        private boolean mEnded = false;
        private float mFraction = 0.0f;
        List<AnimatorListenerCompat> mListeners = new ArrayList();
        private Runnable mLoopRunnable;
        private long mStartTime;
        private boolean mStarted = false;
        View mTarget;
        List<AnimatorUpdateListenerCompat> mUpdateListeners = new ArrayList();

        public DonutFloatValueAnimator() {
            this.mLoopRunnable = new DonutAnimatorCompatProvider$DonutFloatValueAnimator$1(this);
        }

        static /* synthetic */ long access$000(DonutFloatValueAnimator donutFloatValueAnimator) {
            return donutFloatValueAnimator.getTime();
        }

        static /* synthetic */ long access$100(DonutFloatValueAnimator donutFloatValueAnimator) {
            return donutFloatValueAnimator.mStartTime;
        }

        static /* synthetic */ long access$200(DonutFloatValueAnimator donutFloatValueAnimator) {
            return donutFloatValueAnimator.mDuration;
        }

        static /* synthetic */ float access$300(DonutFloatValueAnimator donutFloatValueAnimator) {
            return donutFloatValueAnimator.mFraction;
        }

        static /* synthetic */ float access$302(DonutFloatValueAnimator donutFloatValueAnimator, float f2) {
            donutFloatValueAnimator.mFraction = f2;
            return f2;
        }

        static /* synthetic */ void access$400(DonutFloatValueAnimator donutFloatValueAnimator) {
            donutFloatValueAnimator.notifyUpdateListeners();
        }

        static /* synthetic */ void access$500(DonutFloatValueAnimator donutFloatValueAnimator) {
            donutFloatValueAnimator.dispatchEnd();
        }

        static /* synthetic */ Runnable access$600(DonutFloatValueAnimator donutFloatValueAnimator) {
            return donutFloatValueAnimator.mLoopRunnable;
        }

        private void dispatchCancel() {
            for (int i2 = this.mListeners.size() - 1; i2 >= 0; --i2) {
                this.mListeners.get(i2).onAnimationCancel(this);
            }
        }

        private void dispatchEnd() {
            for (int i2 = this.mListeners.size() - 1; i2 >= 0; --i2) {
                this.mListeners.get(i2).onAnimationEnd(this);
            }
        }

        private void dispatchStart() {
            for (int i2 = this.mListeners.size() - 1; i2 >= 0; --i2) {
                this.mListeners.get(i2).onAnimationStart(this);
            }
        }

        private long getTime() {
            return this.mTarget.getDrawingTime();
        }

        private void notifyUpdateListeners() {
            for (int i2 = this.mUpdateListeners.size() - 1; i2 >= 0; --i2) {
                this.mUpdateListeners.get(i2).onAnimationUpdate(this);
            }
        }

        @Override
        public void addListener(AnimatorListenerCompat animatorListenerCompat) {
            this.mListeners.add(animatorListenerCompat);
        }

        @Override
        public void addUpdateListener(AnimatorUpdateListenerCompat animatorUpdateListenerCompat) {
            this.mUpdateListeners.add(animatorUpdateListenerCompat);
        }

        @Override
        public void cancel() {
            if (this.mEnded) {
                return;
            }
            this.mEnded = true;
            if (this.mStarted) {
                this.dispatchCancel();
            }
            this.dispatchEnd();
        }

        @Override
        public float getAnimatedFraction() {
            return this.mFraction;
        }

        @Override
        public void setDuration(long l2) {
            if (!this.mStarted) {
                this.mDuration = l2;
            }
        }

        @Override
        public void setTarget(View view) {
            this.mTarget = view;
        }

        @Override
        public void start() {
            if (this.mStarted) {
                return;
            }
            this.mStarted = true;
            this.dispatchStart();
            this.mFraction = 0.0f;
            this.mStartTime = this.getTime();
            this.mTarget.postDelayed(this.mLoopRunnable, 16);
        }
    }

}

