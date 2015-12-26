/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.ViewPropertyAnimator
 *  java.lang.Object
 */
package android.support.v4.view;

import android.view.View;
import android.view.ViewPropertyAnimator;

class ViewPropertyAnimatorCompatLollipop {
    ViewPropertyAnimatorCompatLollipop() {
    }

    public static void translationZ(View view, float f2) {
        view.animate().translationZ(f2);
    }

    public static void translationZBy(View view, float f2) {
        view.animate().translationZBy(f2);
    }

    public static void z(View view, float f2) {
        view.animate().z(f2);
    }

    public static void zBy(View view, float f2) {
        view.animate().zBy(f2);
    }
}

