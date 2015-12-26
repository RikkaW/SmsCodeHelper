/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.support.v7.internal.widget.AbsActionBarView;

class AbsActionBarView$1
implements Runnable {
    final /* synthetic */ AbsActionBarView this$0;

    AbsActionBarView$1(AbsActionBarView absActionBarView) {
        this.this$0 = absActionBarView;
    }

    @Override
    public void run() {
        this.this$0.showOverflowMenu();
    }
}

