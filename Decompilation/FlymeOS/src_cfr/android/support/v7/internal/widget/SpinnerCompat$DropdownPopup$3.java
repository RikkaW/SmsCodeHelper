/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.widget.PopupWindow
 *  android.widget.PopupWindow$OnDismissListener
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.support.v7.internal.widget.SpinnerCompat;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow;

class SpinnerCompat$DropdownPopup$3
implements PopupWindow.OnDismissListener {
    final /* synthetic */ SpinnerCompat.DropdownPopup this$1;
    final /* synthetic */ ViewTreeObserver.OnGlobalLayoutListener val$layoutListener;

    SpinnerCompat$DropdownPopup$3(SpinnerCompat.DropdownPopup dropdownPopup, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        this.this$1 = dropdownPopup;
        this.val$layoutListener = onGlobalLayoutListener;
    }

    public void onDismiss() {
        ViewTreeObserver viewTreeObserver = this.this$1.this$0.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.val$layoutListener);
        }
    }
}

