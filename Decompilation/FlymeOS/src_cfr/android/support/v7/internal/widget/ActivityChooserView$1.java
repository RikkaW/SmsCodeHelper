/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.database.DataSetObserver
 */
package android.support.v7.internal.widget;

import android.database.DataSetObserver;
import android.support.v7.internal.widget.ActivityChooserView;

class ActivityChooserView$1
extends DataSetObserver {
    final /* synthetic */ ActivityChooserView this$0;

    ActivityChooserView$1(ActivityChooserView activityChooserView) {
        this.this$0 = activityChooserView;
    }

    public void onChanged() {
        super.onChanged();
        ActivityChooserView.access$000(this.this$0).notifyDataSetChanged();
    }

    public void onInvalidated() {
        super.onInvalidated();
        ActivityChooserView.access$000(this.this$0).notifyDataSetInvalidated();
    }
}

