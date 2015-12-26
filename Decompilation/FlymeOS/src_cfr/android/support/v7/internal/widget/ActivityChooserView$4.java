/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.database.DataSetObserver
 */
package android.support.v7.internal.widget;

import android.database.DataSetObserver;
import android.support.v7.internal.widget.ActivityChooserView;

class ActivityChooserView$4
extends DataSetObserver {
    final /* synthetic */ ActivityChooserView this$0;

    ActivityChooserView$4(ActivityChooserView activityChooserView) {
        this.this$0 = activityChooserView;
    }

    public void onChanged() {
        super.onChanged();
        ActivityChooserView.access$400(this.this$0);
    }
}

