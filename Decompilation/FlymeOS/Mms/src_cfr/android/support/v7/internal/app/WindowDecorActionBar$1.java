/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 */
package android.support.v7.internal.app;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.internal.app.WindowDecorActionBar;
import android.view.View;

class WindowDecorActionBar$1
extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ WindowDecorActionBar this$0;

    WindowDecorActionBar$1(WindowDecorActionBar windowDecorActionBar) {
        this.this$0 = windowDecorActionBar;
    }

    @Override
    public void onAnimationEnd(View view) {
        if (WindowDecorActionBar.access$000(this.this$0) && WindowDecorActionBar.access$100(this.this$0) != null) {
            ViewCompat.setTranslationY(WindowDecorActionBar.access$100(this.this$0), 0.0f);
            ViewCompat.setTranslationY((View)WindowDecorActionBar.access$200(this.this$0), 0.0f);
        }
        if (WindowDecorActionBar.access$300(this.this$0) != null) {
            WindowDecorActionBar.access$300(this.this$0).setVisibility(8);
        }
        WindowDecorActionBar.access$200(this.this$0).setVisibility(8);
        WindowDecorActionBar.access$200(this.this$0).setTransitioning(false);
        WindowDecorActionBar.access$402(this.this$0, null);
        this.this$0.completeDeferredDestroyActionMode();
        if (WindowDecorActionBar.access$500(this.this$0) != null) {
            ViewCompat.requestApplyInsets((View)WindowDecorActionBar.access$500(this.this$0));
        }
    }
}

