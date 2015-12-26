/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.animation.Animation
 *  android.view.animation.Animation$AnimationListener
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.support.v7.internal.widget.MzSlidePopupWindow;
import android.view.animation.Animation;

class MzSlidePopupWindow$DismissAnimator$1
implements Animation.AnimationListener {
    final /* synthetic */ MzSlidePopupWindow.DismissAnimator this$1;

    MzSlidePopupWindow$DismissAnimator$1(MzSlidePopupWindow.DismissAnimator dismissAnimator) {
        this.this$1 = dismissAnimator;
    }

    public void onAnimationEnd(Animation animation) {
        this.this$1.this$0.supperDismiss();
        MzSlidePopupWindow.DismissAnimator.access$302(this.this$1, false);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }
}

