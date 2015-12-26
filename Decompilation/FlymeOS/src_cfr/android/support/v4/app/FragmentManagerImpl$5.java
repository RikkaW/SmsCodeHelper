/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.animation.Animation
 *  android.view.animation.Animation$AnimationListener
 *  java.lang.Object
 */
package android.support.v4.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManagerImpl;
import android.view.View;
import android.view.animation.Animation;

class FragmentManagerImpl$5
implements Animation.AnimationListener {
    final /* synthetic */ FragmentManagerImpl this$0;
    final /* synthetic */ Fragment val$fragment;

    FragmentManagerImpl$5(FragmentManagerImpl fragmentManagerImpl, Fragment fragment) {
        this.this$0 = fragmentManagerImpl;
        this.val$fragment = fragment;
    }

    public void onAnimationEnd(Animation animation) {
        if (this.val$fragment.mAnimatingAway != null) {
            this.val$fragment.mAnimatingAway = null;
            this.this$0.moveToState(this.val$fragment, this.val$fragment.mStateAfterAnimating, 0, 0, false);
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }
}

