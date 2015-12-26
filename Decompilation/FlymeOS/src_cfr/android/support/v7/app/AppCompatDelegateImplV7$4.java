/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.widget.PopupWindow
 *  java.lang.Object
 */
package android.support.v7.app;

import android.support.v7.app.AppCompatDelegateImplV7;
import android.support.v7.internal.widget.ActionBarContextView;
import android.view.View;
import android.widget.PopupWindow;

class AppCompatDelegateImplV7$4
implements Runnable {
    final /* synthetic */ AppCompatDelegateImplV7 this$0;

    AppCompatDelegateImplV7$4(AppCompatDelegateImplV7 appCompatDelegateImplV7) {
        this.this$0 = appCompatDelegateImplV7;
    }

    @Override
    public void run() {
        this.this$0.mActionModePopup.showAtLocation((View)this.this$0.mActionModeView, 55, 0, 0);
    }
}

