/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.animation.ValueAnimator
 *  android.animation.ValueAnimator$AnimatorUpdateListener
 *  android.view.View
 *  java.lang.Object
 */
package android.support.v4.view;

import android.animation.ValueAnimator;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.view.View;

final class ViewPropertyAnimatorCompatKK$1
implements ValueAnimator.AnimatorUpdateListener {
    final /* synthetic */ ViewPropertyAnimatorUpdateListener val$listener;
    final /* synthetic */ View val$view;

    ViewPropertyAnimatorCompatKK$1(ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener, View view) {
        this.val$listener = viewPropertyAnimatorUpdateListener;
        this.val$view = view;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.val$listener.onAnimationUpdate(this.val$view);
    }
}

