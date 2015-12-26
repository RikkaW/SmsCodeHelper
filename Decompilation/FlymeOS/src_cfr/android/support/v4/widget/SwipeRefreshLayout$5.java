/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.animation.Animation
 *  android.view.animation.Animation$AnimationListener
 *  java.lang.Object
 */
package android.support.v4.widget;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.animation.Animation;

class SwipeRefreshLayout$5
implements Animation.AnimationListener {
    final /* synthetic */ SwipeRefreshLayout this$0;

    SwipeRefreshLayout$5(SwipeRefreshLayout swipeRefreshLayout) {
        this.this$0 = swipeRefreshLayout;
    }

    public void onAnimationEnd(Animation animation) {
        if (!SwipeRefreshLayout.access$600(this.this$0)) {
            SwipeRefreshLayout.access$1000(this.this$0, null);
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }
}

