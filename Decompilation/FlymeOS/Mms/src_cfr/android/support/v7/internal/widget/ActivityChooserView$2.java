/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.support.v4.view.ActionProvider;
import android.support.v7.internal.widget.ActivityChooserView;
import android.view.ViewTreeObserver;

class ActivityChooserView$2
implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ ActivityChooserView this$0;

    ActivityChooserView$2(ActivityChooserView activityChooserView) {
        this.this$0 = activityChooserView;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onGlobalLayout() {
        if (!this.this$0.isShowingPopup()) return;
        {
            if (!this.this$0.isShown()) {
                ActivityChooserView.access$100(this.this$0).dismiss();
                return;
            } else {
                ActivityChooserView.access$100(this.this$0).show();
                if (this.this$0.mProvider == null) return;
                {
                    this.this$0.mProvider.subUiVisibilityChanged(true);
                    return;
                }
            }
        }
    }
}

