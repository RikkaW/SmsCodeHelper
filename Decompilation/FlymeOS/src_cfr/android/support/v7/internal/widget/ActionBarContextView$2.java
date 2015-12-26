/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnPreDrawListener
 *  android.view.animation.Interpolator
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.internal.widget.ActionBarContextView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Interpolator;

class ActionBarContextView$2
implements ViewTreeObserver.OnPreDrawListener {
    final /* synthetic */ ActionBarContextView this$0;
    final /* synthetic */ View val$viewToAnimate;

    ActionBarContextView$2(ActionBarContextView actionBarContextView, View view) {
        this.this$0 = actionBarContextView;
        this.val$viewToAnimate = view;
    }

    public boolean onPreDraw() {
        this.val$viewToAnimate.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver.OnPreDrawListener)this);
        float f2 = this.val$viewToAnimate.getHeight();
        ViewCompat.setTranslationY(this.val$viewToAnimate, f2);
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate(this.val$viewToAnimate).translationY(0.0f);
        viewPropertyAnimatorCompat.setDuration(260);
        viewPropertyAnimatorCompat.setInterpolator(ActionBarContextView.access$000());
        viewPropertyAnimatorCompat.start();
        return false;
    }
}

