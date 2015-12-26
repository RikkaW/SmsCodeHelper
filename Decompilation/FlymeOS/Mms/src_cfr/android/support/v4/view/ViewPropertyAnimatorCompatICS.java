/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.Animator$AnimatorListener
 *  android.animation.TimeInterpolator
 *  android.view.View
 *  android.view.ViewPropertyAnimator
 *  android.view.animation.Interpolator
 *  java.lang.Object
 */
package android.support.v4.view;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.support.v4.view.ViewPropertyAnimatorCompatICS$1;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;

class ViewPropertyAnimatorCompatICS {
    ViewPropertyAnimatorCompatICS() {
    }

    public static void alpha(View view, float f2) {
        view.animate().alpha(f2);
    }

    public static void alphaBy(View view, float f2) {
        view.animate().alphaBy(f2);
    }

    public static void cancel(View view) {
        view.animate().cancel();
    }

    public static long getDuration(View view) {
        return view.animate().getDuration();
    }

    public static long getStartDelay(View view) {
        return view.animate().getStartDelay();
    }

    public static void rotation(View view, float f2) {
        view.animate().rotation(f2);
    }

    public static void rotationBy(View view, float f2) {
        view.animate().rotationBy(f2);
    }

    public static void rotationX(View view, float f2) {
        view.animate().rotationX(f2);
    }

    public static void rotationXBy(View view, float f2) {
        view.animate().rotationXBy(f2);
    }

    public static void rotationY(View view, float f2) {
        view.animate().rotationY(f2);
    }

    public static void rotationYBy(View view, float f2) {
        view.animate().rotationYBy(f2);
    }

    public static void scaleX(View view, float f2) {
        view.animate().scaleX(f2);
    }

    public static void scaleXBy(View view, float f2) {
        view.animate().scaleXBy(f2);
    }

    public static void scaleY(View view, float f2) {
        view.animate().scaleY(f2);
    }

    public static void scaleYBy(View view, float f2) {
        view.animate().scaleYBy(f2);
    }

    public static void setDuration(View view, long l2) {
        view.animate().setDuration(l2);
    }

    public static void setInterpolator(View view, Interpolator interpolator2) {
        view.animate().setInterpolator((TimeInterpolator)interpolator2);
    }

    public static void setListener(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        if (viewPropertyAnimatorListener != null) {
            view.animate().setListener((Animator.AnimatorListener)new ViewPropertyAnimatorCompatICS$1(viewPropertyAnimatorListener, view));
            return;
        }
        view.animate().setListener(null);
    }

    public static void setStartDelay(View view, long l2) {
        view.animate().setStartDelay(l2);
    }

    public static void start(View view) {
        view.animate().start();
    }

    public static void translationX(View view, float f2) {
        view.animate().translationX(f2);
    }

    public static void translationXBy(View view, float f2) {
        view.animate().translationXBy(f2);
    }

    public static void translationY(View view, float f2) {
        view.animate().translationY(f2);
    }

    public static void translationYBy(View view, float f2) {
        view.animate().translationYBy(f2);
    }

    public static void x(View view, float f2) {
        view.animate().x(f2);
    }

    public static void xBy(View view, float f2) {
        view.animate().xBy(f2);
    }

    public static void y(View view, float f2) {
        view.animate().y(f2);
    }

    public static void yBy(View view, float f2) {
        view.animate().yBy(f2);
    }
}

