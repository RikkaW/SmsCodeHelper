/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.animation.ValueAnimator
 *  android.animation.ValueAnimator$AnimatorUpdateListener
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.animation.ValueAnimator;
import android.support.v7.internal.widget.ScrollingTabContainerView;
import android.support.v7.widget.AnimationUtils;

class ScrollingTabContainerView$SlidingTabStrip$1
implements ValueAnimator.AnimatorUpdateListener {
    final /* synthetic */ ScrollingTabContainerView.SlidingTabStrip this$1;
    final /* synthetic */ int val$startLeft;
    final /* synthetic */ int val$startRight;
    final /* synthetic */ int val$targetLeft;
    final /* synthetic */ int val$targetRight;

    ScrollingTabContainerView$SlidingTabStrip$1(ScrollingTabContainerView.SlidingTabStrip slidingTabStrip, int n2, int n3, int n4, int n5) {
        this.this$1 = slidingTabStrip;
        this.val$startLeft = n2;
        this.val$targetLeft = n3;
        this.val$startRight = n4;
        this.val$targetRight = n5;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float f2 = valueAnimator.getAnimatedFraction();
        ScrollingTabContainerView.SlidingTabStrip.access$800(this.this$1, AnimationUtils.lerp(this.val$startLeft, this.val$targetLeft, f2), AnimationUtils.lerp(this.val$startRight, this.val$targetRight, f2));
    }
}

