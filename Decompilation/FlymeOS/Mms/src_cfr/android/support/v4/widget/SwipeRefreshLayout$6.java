/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.animation.Animation
 *  android.view.animation.Transformation
 *  java.lang.Math
 */
package android.support.v4.widget;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.animation.Animation;
import android.view.animation.Transformation;

class SwipeRefreshLayout$6
extends Animation {
    final /* synthetic */ SwipeRefreshLayout this$0;

    SwipeRefreshLayout$6(SwipeRefreshLayout swipeRefreshLayout) {
        this.this$0 = swipeRefreshLayout;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void applyTransformation(float f2, Transformation transformation) {
        int n2 = !SwipeRefreshLayout.access$1100(this.this$0) ? (int)(SwipeRefreshLayout.access$1200(this.this$0) - (float)Math.abs((int)this.this$0.mOriginalOffsetTop)) : (int)SwipeRefreshLayout.access$1200(this.this$0);
        int n3 = this.this$0.mFrom;
        n2 = (int)((float)(n2 - this.this$0.mFrom) * f2);
        int n4 = SwipeRefreshLayout.access$400(this.this$0).getTop();
        SwipeRefreshLayout.access$900(this.this$0, n2 + n3 - n4, false);
        SwipeRefreshLayout.access$100(this.this$0).setArrowScale(1.0f - f2);
    }
}

