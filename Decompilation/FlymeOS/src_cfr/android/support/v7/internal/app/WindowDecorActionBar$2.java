/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 */
package android.support.v7.internal.app;

import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.internal.app.WindowDecorActionBar;
import android.view.View;

class WindowDecorActionBar$2
extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ WindowDecorActionBar this$0;

    WindowDecorActionBar$2(WindowDecorActionBar windowDecorActionBar) {
        this.this$0 = windowDecorActionBar;
    }

    @Override
    public void onAnimationEnd(View view) {
        WindowDecorActionBar.access$402(this.this$0, null);
        WindowDecorActionBar.access$200(this.this$0).requestLayout();
    }
}

