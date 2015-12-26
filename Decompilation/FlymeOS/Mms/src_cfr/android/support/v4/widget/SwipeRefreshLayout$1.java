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

class SwipeRefreshLayout$1
implements Animation.AnimationListener {
    final /* synthetic */ SwipeRefreshLayout this$0;

    SwipeRefreshLayout$1(SwipeRefreshLayout swipeRefreshLayout) {
        this.this$0 = swipeRefreshLayout;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onAnimationEnd(Animation animation) {
        if (SwipeRefreshLayout.access$000(this.this$0)) {
            SwipeRefreshLayout.access$100(this.this$0).setAlpha(255);
            SwipeRefreshLayout.access$100(this.this$0).start();
            if (SwipeRefreshLayout.access$200(this.this$0) && SwipeRefreshLayout.access$300(this.this$0) != null) {
                SwipeRefreshLayout.access$300(this.this$0).onRefresh();
            }
        } else {
            SwipeRefreshLayout.access$100(this.this$0).stop();
            SwipeRefreshLayout.access$400(this.this$0).setVisibility(8);
            SwipeRefreshLayout.access$500(this.this$0, 255);
            if (SwipeRefreshLayout.access$600(this.this$0)) {
                SwipeRefreshLayout.access$700(this.this$0, 0.0f);
            } else {
                SwipeRefreshLayout.access$900(this.this$0, this.this$0.mOriginalOffsetTop - SwipeRefreshLayout.access$800(this.this$0), true);
            }
        }
        SwipeRefreshLayout.access$802(this.this$0, SwipeRefreshLayout.access$400(this.this$0).getTop());
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }
}

