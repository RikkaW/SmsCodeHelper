/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.AnimatorListenerAdapter
 *  android.view.View
 */
package android.support.v4.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;

final class ViewPropertyAnimatorCompatJB$1
extends AnimatorListenerAdapter {
    final /* synthetic */ ViewPropertyAnimatorListener val$listener;
    final /* synthetic */ View val$view;

    ViewPropertyAnimatorCompatJB$1(ViewPropertyAnimatorListener viewPropertyAnimatorListener, View view) {
        this.val$listener = viewPropertyAnimatorListener;
        this.val$view = view;
    }

    public void onAnimationCancel(Animator animator2) {
        this.val$listener.onAnimationCancel(this.val$view);
    }

    public void onAnimationEnd(Animator animator2) {
        this.val$listener.onAnimationEnd(this.val$view);
    }

    public void onAnimationStart(Animator animator2) {
        this.val$listener.onAnimationStart(this.val$view);
    }
}

