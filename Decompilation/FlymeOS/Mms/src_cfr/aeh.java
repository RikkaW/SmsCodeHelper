/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.AnimatorListenerAdapter
 */
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.android.mms.view.MessagePopupGroupView;

class aeh
extends AnimatorListenerAdapter {
    final /* synthetic */ MessagePopupGroupView.a a;
    final /* synthetic */ aee b;

    aeh(aee aee2, MessagePopupGroupView.a a2) {
        this.b = aee2;
        this.a = a2;
    }

    public void onAnimationEnd(Animator animator2) {
        this.a.c(1.0f);
    }
}

