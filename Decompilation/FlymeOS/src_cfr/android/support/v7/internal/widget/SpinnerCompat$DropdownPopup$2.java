/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.support.v7.internal.widget.SpinnerCompat;
import android.view.ViewTreeObserver;

class SpinnerCompat$DropdownPopup$2
implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ SpinnerCompat.DropdownPopup this$1;

    SpinnerCompat$DropdownPopup$2(SpinnerCompat.DropdownPopup dropdownPopup) {
        this.this$1 = dropdownPopup;
    }

    public void onGlobalLayout() {
        this.this$1.computeContentWidth();
        SpinnerCompat.DropdownPopup.access$501(this.this$1);
    }
}

