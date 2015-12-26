/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package android.support.v4.widget;

import android.support.v4.widget.ViewDragHelper;

class ViewDragHelper$2
implements Runnable {
    final /* synthetic */ ViewDragHelper this$0;

    ViewDragHelper$2(ViewDragHelper viewDragHelper) {
        this.this$0 = viewDragHelper;
    }

    @Override
    public void run() {
        this.this$0.setDragState(0);
    }
}

