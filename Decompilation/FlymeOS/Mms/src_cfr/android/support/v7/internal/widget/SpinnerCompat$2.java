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

class SpinnerCompat$2
implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ SpinnerCompat this$0;

    SpinnerCompat$2(SpinnerCompat spinnerCompat) {
        this.this$0 = spinnerCompat;
    }

    public void onGlobalLayout() {
        ViewTreeObserver viewTreeObserver;
        if (!SpinnerCompat.access$100(this.this$0).isShowing()) {
            SpinnerCompat.access$100(this.this$0).show();
        }
        if ((viewTreeObserver = this.this$0.getViewTreeObserver()) != null) {
            viewTreeObserver.removeGlobalOnLayoutListener((ViewTreeObserver.OnGlobalLayoutListener)this);
        }
    }
}

