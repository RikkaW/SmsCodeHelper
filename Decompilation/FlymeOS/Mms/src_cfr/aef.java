/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.AnimatorListenerAdapter
 */
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class aef
extends AnimatorListenerAdapter {
    final /* synthetic */ aee a;

    aef(aee aee2) {
        this.a = aee2;
    }

    public void onAnimationEnd(Animator animator2) {
        aee.a(this.a).c();
    }
}

