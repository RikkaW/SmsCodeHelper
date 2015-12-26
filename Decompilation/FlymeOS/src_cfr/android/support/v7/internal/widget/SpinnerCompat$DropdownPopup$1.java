/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.internal.widget.SpinnerCompat;
import android.view.View;
import android.widget.AdapterView;

class SpinnerCompat$DropdownPopup$1
implements AdapterView.OnItemClickListener {
    final /* synthetic */ SpinnerCompat.DropdownPopup this$1;
    final /* synthetic */ SpinnerCompat val$this$0;

    SpinnerCompat$DropdownPopup$1(SpinnerCompat.DropdownPopup dropdownPopup, SpinnerCompat spinnerCompat) {
        this.this$1 = dropdownPopup;
        this.val$this$0 = spinnerCompat;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int n2, long l2) {
        this.this$1.this$0.setSelection(n2);
        if (this.this$1.this$0.mOnItemClickListener != null) {
            this.this$1.this$0.performItemClick(view, n2, SpinnerCompat.DropdownPopup.access$300(this.this$1).getItemId(n2));
        }
        this.this$1.dismiss();
    }
}

