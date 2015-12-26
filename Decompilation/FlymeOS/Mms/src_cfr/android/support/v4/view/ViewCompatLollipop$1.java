/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnApplyWindowInsetsListener
 *  android.view.WindowInsets
 *  java.lang.Object
 */
package android.support.v4.view;

import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.view.WindowInsetsCompatApi21;
import android.view.View;
import android.view.WindowInsets;

final class ViewCompatLollipop$1
implements View.OnApplyWindowInsetsListener {
    final /* synthetic */ OnApplyWindowInsetsListener val$listener;

    ViewCompatLollipop$1(OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        this.val$listener = onApplyWindowInsetsListener;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets object) {
        object = new WindowInsetsCompatApi21((WindowInsets)object);
        return ((WindowInsetsCompatApi21)this.val$listener.onApplyWindowInsets(view, (WindowInsetsCompat)object)).unwrap();
    }
}

