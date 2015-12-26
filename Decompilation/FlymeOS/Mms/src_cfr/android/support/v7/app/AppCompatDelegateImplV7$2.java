/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  java.lang.Object
 */
package android.support.v7.app;

import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.app.AppCompatDelegateImplV7;
import android.view.View;

class AppCompatDelegateImplV7$2
implements OnApplyWindowInsetsListener {
    final /* synthetic */ AppCompatDelegateImplV7 this$0;

    AppCompatDelegateImplV7$2(AppCompatDelegateImplV7 appCompatDelegateImplV7) {
        this.this$0 = appCompatDelegateImplV7;
    }

    @Override
    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        int n2 = windowInsetsCompat.getSystemWindowInsetTop();
        int n3 = AppCompatDelegateImplV7.access$300(this.this$0, n2);
        WindowInsetsCompat windowInsetsCompat2 = windowInsetsCompat;
        if (n2 != n3) {
            windowInsetsCompat2 = windowInsetsCompat.replaceSystemWindowInsets(windowInsetsCompat.getSystemWindowInsetLeft(), n3, windowInsetsCompat.getSystemWindowInsetRight(), windowInsetsCompat.getSystemWindowInsetBottom());
        }
        return ViewCompat.onApplyWindowInsets(view, windowInsetsCompat2);
    }
}

