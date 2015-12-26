/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.Animator$AnimatorListener
 *  android.view.View
 *  android.view.ViewPropertyAnimator
 *  java.lang.Object
 */
package android.support.v4.view;

import android.animation.Animator;
import android.support.v4.view.ViewPropertyAnimatorCompatJB$1;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import android.view.ViewPropertyAnimator;

class ViewPropertyAnimatorCompatJB {
    ViewPropertyAnimatorCompatJB() {
    }

    public static void setListener(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        if (viewPropertyAnimatorListener != null) {
            view.animate().setListener((Animator.AnimatorListener)new ViewPropertyAnimatorCompatJB$1(viewPropertyAnimatorListener, view));
            return;
        }
        view.animate().setListener(null);
    }

    public static void withEndAction(View view, Runnable runnable) {
        view.animate().withEndAction(runnable);
    }

    public static void withLayer(View view) {
        view.animate().withLayer();
    }

    public static void withStartAction(View view, Runnable runnable) {
        view.animate().withStartAction(runnable);
    }
}

