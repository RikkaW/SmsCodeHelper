/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.support.v7.internal.widget.ScrollingTabContainerView;
import android.view.View;

class ScrollingTabContainerView$1
implements Runnable {
    final /* synthetic */ ScrollingTabContainerView this$0;
    final /* synthetic */ int val$position;
    final /* synthetic */ View val$tabView;

    ScrollingTabContainerView$1(ScrollingTabContainerView scrollingTabContainerView, View view, int n2) {
        this.this$0 = scrollingTabContainerView;
        this.val$tabView = view;
        this.val$position = n2;
    }

    @Override
    public void run() {
        int n2 = this.val$tabView.getLeft();
        int n3 = (this.this$0.getWidth() - this.val$tabView.getWidth()) / 2;
        this.this$0.smoothScrollTo(n2 - n3, 0);
        ScrollingTabContainerView.access$100(this.this$0).animateIndicatorToPosition(this.val$position, 300);
        this.this$0.mTabSelector = null;
    }
}

