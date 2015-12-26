/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 */
package android.support.v7.internal.widget;

import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.internal.widget.ToolbarWidgetWrapper;
import android.view.View;

class ToolbarWidgetWrapper$2
extends ViewPropertyAnimatorListenerAdapter {
    private boolean mCanceled;
    final /* synthetic */ ToolbarWidgetWrapper this$0;

    ToolbarWidgetWrapper$2(ToolbarWidgetWrapper toolbarWidgetWrapper) {
        this.this$0 = toolbarWidgetWrapper;
        this.mCanceled = false;
    }

    @Override
    public void onAnimationCancel(View view) {
        this.mCanceled = true;
    }

    @Override
    public void onAnimationEnd(View view) {
        if (!this.mCanceled) {
            ToolbarWidgetWrapper.access$000(this.this$0).setVisibility(8);
            ToolbarWidgetWrapper.access$000(this.this$0).setMenuVisibility(8);
        }
    }
}

