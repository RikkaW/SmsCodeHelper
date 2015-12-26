/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.animation.Animation
 *  android.view.animation.Transformation
 */
package android.support.v4.widget;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.animation.Animation;
import android.view.animation.Transformation;

class SwipeRefreshLayout$8
extends Animation {
    final /* synthetic */ SwipeRefreshLayout this$0;

    SwipeRefreshLayout$8(SwipeRefreshLayout swipeRefreshLayout) {
        this.this$0 = swipeRefreshLayout;
    }

    public void applyTransformation(float f2, Transformation transformation) {
        float f3 = SwipeRefreshLayout.access$1400(this.this$0);
        float f4 = - SwipeRefreshLayout.access$1400(this.this$0);
        SwipeRefreshLayout.access$700(this.this$0, f3 + f4 * f2);
        SwipeRefreshLayout.access$1300(this.this$0, f2);
    }
}

