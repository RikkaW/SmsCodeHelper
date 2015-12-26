/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.animation.ValueAnimator
 *  android.animation.ValueAnimator$AnimatorUpdateListener
 *  java.lang.Object
 */
package android.support.v4.animation;

import android.animation.ValueAnimator;
import android.support.v4.animation.AnimatorUpdateListenerCompat;
import android.support.v4.animation.HoneycombMr1AnimatorCompatProvider;
import android.support.v4.animation.ValueAnimatorCompat;

class HoneycombMr1AnimatorCompatProvider$HoneycombValueAnimatorCompat$1
implements ValueAnimator.AnimatorUpdateListener {
    final /* synthetic */ HoneycombMr1AnimatorCompatProvider.HoneycombValueAnimatorCompat this$0;
    final /* synthetic */ AnimatorUpdateListenerCompat val$animatorUpdateListener;

    HoneycombMr1AnimatorCompatProvider$HoneycombValueAnimatorCompat$1(HoneycombMr1AnimatorCompatProvider.HoneycombValueAnimatorCompat honeycombValueAnimatorCompat, AnimatorUpdateListenerCompat animatorUpdateListenerCompat) {
        this.this$0 = honeycombValueAnimatorCompat;
        this.val$animatorUpdateListener = animatorUpdateListenerCompat;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.val$animatorUpdateListener.onAnimationUpdate(this.this$0);
    }
}

