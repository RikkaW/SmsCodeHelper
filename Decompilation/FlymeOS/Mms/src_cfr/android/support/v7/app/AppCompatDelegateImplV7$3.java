/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Rect
 *  java.lang.Object
 */
package android.support.v7.app;

import android.graphics.Rect;
import android.support.v7.app.AppCompatDelegateImplV7;
import android.support.v7.internal.widget.FitWindowsViewGroup;

class AppCompatDelegateImplV7$3
implements FitWindowsViewGroup.OnFitSystemWindowsListener {
    final /* synthetic */ AppCompatDelegateImplV7 this$0;

    AppCompatDelegateImplV7$3(AppCompatDelegateImplV7 appCompatDelegateImplV7) {
        this.this$0 = appCompatDelegateImplV7;
    }

    @Override
    public void onFitSystemWindows(Rect rect) {
        rect.top = AppCompatDelegateImplV7.access$300(this.this$0, rect.top);
    }
}

