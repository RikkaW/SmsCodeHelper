/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 */
package android.support.v7.internal.widget;

import android.support.v7.internal.widget.SpinnerCompat;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;

class SpinnerCompat$1
extends ListPopupWindow.ForwardingListener {
    final /* synthetic */ SpinnerCompat this$0;
    final /* synthetic */ SpinnerCompat.DropdownPopup val$popup;

    SpinnerCompat$1(SpinnerCompat spinnerCompat, View view, SpinnerCompat.DropdownPopup dropdownPopup) {
        this.this$0 = spinnerCompat;
        this.val$popup = dropdownPopup;
        super(view);
    }

    @Override
    public ListPopupWindow getPopup() {
        return this.val$popup;
    }

    @Override
    public boolean onForwardingStarted() {
        if (!SpinnerCompat.access$100(this.this$0).isShowing()) {
            SpinnerCompat.access$100(this.this$0).show();
        }
        return true;
    }
}

