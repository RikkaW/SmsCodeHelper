/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.animation.ValueAnimator
 *  android.animation.ValueAnimator$AnimatorUpdateListener
 *  android.view.View
 *  android.view.ViewPropertyAnimator
 *  java.lang.Object
 */
package android.support.v4.view;

import android.animation.ValueAnimator;
import android.support.v4.view.ViewPropertyAnimatorCompatKK$1;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.view.View;
import android.view.ViewPropertyAnimator;

class ViewPropertyAnimatorCompatKK {
    ViewPropertyAnimatorCompatKK() {
    }

    public static void setUpdateListener(View view, ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
        view.animate().setUpdateListener((ValueAnimator.AnimatorUpdateListener)new ViewPropertyAnimatorCompatKK$1(viewPropertyAnimatorUpdateListener, view));
    }
}

