/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.AnimatorListenerAdapter
 */
package android.support.v7.internal.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.internal.widget.ScrollingTabContainerView;

class ScrollingTabContainerView$SlidingTabStrip$2
extends AnimatorListenerAdapter {
    final /* synthetic */ ScrollingTabContainerView.SlidingTabStrip this$1;
    final /* synthetic */ int val$position;

    ScrollingTabContainerView$SlidingTabStrip$2(ScrollingTabContainerView.SlidingTabStrip slidingTabStrip, int n2) {
        this.this$1 = slidingTabStrip;
        this.val$position = n2;
    }

    public void onAnimationCancel(Animator animator2) {
        ScrollingTabContainerView.SlidingTabStrip.access$902(this.this$1, this.val$position);
        ScrollingTabContainerView.SlidingTabStrip.access$1002(this.this$1, 0.0f);
    }

    public void onAnimationEnd(Animator animator2) {
        ScrollingTabContainerView.SlidingTabStrip.access$902(this.this$1, this.val$position);
        ScrollingTabContainerView.SlidingTabStrip.access$1002(this.this$1, 0.0f);
    }
}

