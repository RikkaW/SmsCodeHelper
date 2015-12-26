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

class SwipeRefreshLayout$4
extends Animation {
    final /* synthetic */ SwipeRefreshLayout this$0;
    final /* synthetic */ int val$endingAlpha;
    final /* synthetic */ int val$startingAlpha;

    SwipeRefreshLayout$4(SwipeRefreshLayout swipeRefreshLayout, int n2, int n3) {
        this.this$0 = swipeRefreshLayout;
        this.val$startingAlpha = n2;
        this.val$endingAlpha = n3;
    }

    public void applyTransformation(float f2, Transformation transformation) {
        SwipeRefreshLayout.access$100(this.this$0).setAlpha((int)((float)this.val$startingAlpha + (float)(this.val$endingAlpha - this.val$startingAlpha) * f2));
    }
}

