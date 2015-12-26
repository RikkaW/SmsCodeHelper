/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 */
package android.support.v7.internal.widget;

import android.support.v7.internal.widget.ActivityChooserView;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;

class ActivityChooserView$3
extends ListPopupWindow.ForwardingListener {
    final /* synthetic */ ActivityChooserView this$0;

    ActivityChooserView$3(ActivityChooserView activityChooserView, View view) {
        this.this$0 = activityChooserView;
        super(view);
    }

    @Override
    public ListPopupWindow getPopup() {
        return ActivityChooserView.access$100(this.this$0);
    }

    @Override
    protected boolean onForwardingStarted() {
        this.this$0.showPopup();
        return true;
    }

    @Override
    protected boolean onForwardingStopped() {
        this.this$0.dismissPopup();
        return true;
    }
}

