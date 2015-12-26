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

class ToolbarWidgetWrapper$3
extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ ToolbarWidgetWrapper this$0;

    ToolbarWidgetWrapper$3(ToolbarWidgetWrapper toolbarWidgetWrapper) {
        this.this$0 = toolbarWidgetWrapper;
    }

    @Override
    public void onAnimationStart(View view) {
        ToolbarWidgetWrapper.access$000(this.this$0).setVisibility(0);
    }
}

