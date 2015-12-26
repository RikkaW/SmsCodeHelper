/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.Animator$AnimatorListener
 *  android.animation.ValueAnimator
 *  android.animation.ValueAnimator$AnimatorUpdateListener
 *  android.view.View
 *  java.lang.Object
 */
package android.support.v4.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.v4.animation.AnimatorListenerCompat;
import android.support.v4.animation.AnimatorProvider;
import android.support.v4.animation.AnimatorUpdateListenerCompat;
import android.support.v4.animation.HoneycombMr1AnimatorCompatProvider$HoneycombValueAnimatorCompat$1;
import android.support.v4.animation.ValueAnimatorCompat;
import android.view.View;

class HoneycombMr1AnimatorCompatProvider
implements AnimatorProvider {
    HoneycombMr1AnimatorCompatProvider() {
    }

    @Override
    public ValueAnimatorCompat emptyValueAnimator() {
        return new HoneycombValueAnimatorCompat((Animator)ValueAnimator.ofFloat((float[])new float[]{0.0f, 1.0f}));
    }

    static class AnimatorListenerCompatWrapper
    implements Animator.AnimatorListener {
        final ValueAnimatorCompat mValueAnimatorCompat;
        final AnimatorListenerCompat mWrapped;

        public AnimatorListenerCompatWrapper(AnimatorListenerCompat animatorListenerCompat, ValueAnimatorCompat valueAnimatorCompat) {
            this.mWrapped = animatorListenerCompat;
            this.mValueAnimatorCompat = valueAnimatorCompat;
        }

        public void onAnimationCancel(Animator animator2) {
            this.mWrapped.onAnimationCancel(this.mValueAnimatorCompat);
        }

        public void onAnimationEnd(Animator animator2) {
            this.mWrapped.onAnimationEnd(this.mValueAnimatorCompat);
        }

        public void onAnimationRepeat(Animator animator2) {
            this.mWrapped.onAnimationRepeat(this.mValueAnimatorCompat);
        }

        public void onAnimationStart(Animator animator2) {
            this.mWrapped.onAnimationStart(this.mValueAnimatorCompat);
        }
    }

    static class HoneycombValueAnimatorCompat
    implements ValueAnimatorCompat {
        final Animator mWrapped;

        public HoneycombValueAnimatorCompat(Animator animator2) {
            this.mWrapped = animator2;
        }

        @Override
        public void addListener(AnimatorListenerCompat animatorListenerCompat) {
            this.mWrapped.addListener((Animator.AnimatorListener)new AnimatorListenerCompatWrapper(animatorListenerCompat, this));
        }

        @Override
        public void addUpdateListener(AnimatorUpdateListenerCompat animatorUpdateListenerCompat) {
            if (this.mWrapped instanceof ValueAnimator) {
                ((ValueAnimator)this.mWrapped).addUpdateListener((ValueAnimator.AnimatorUpdateListener)new HoneycombMr1AnimatorCompatProvider$HoneycombValueAnimatorCompat$1(this, animatorUpdateListenerCompat));
            }
        }

        @Override
        public void cancel() {
            this.mWrapped.cancel();
        }

        @Override
        public float getAnimatedFraction() {
            return ((ValueAnimator)this.mWrapped).getAnimatedFraction();
        }

        @Override
        public void setDuration(long l2) {
            this.mWrapped.setDuration(l2);
        }

        @Override
        public void setTarget(View view) {
            this.mWrapped.setTarget((Object)view);
        }

        @Override
        public void start() {
            this.mWrapped.start();
        }
    }

}

