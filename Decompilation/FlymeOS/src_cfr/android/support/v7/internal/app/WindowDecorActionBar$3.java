/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.ViewParent
 *  java.lang.Object
 */
package android.support.v7.internal.app;

import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.v7.internal.app.WindowDecorActionBar;
import android.view.View;
import android.view.ViewParent;

class WindowDecorActionBar$3
implements ViewPropertyAnimatorUpdateListener {
    final /* synthetic */ WindowDecorActionBar this$0;

    WindowDecorActionBar$3(WindowDecorActionBar windowDecorActionBar) {
        this.this$0 = windowDecorActionBar;
    }

    @Override
    public void onAnimationUpdate(View view) {
        ((View)WindowDecorActionBar.access$200(this.this$0).getParent()).invalidate();
    }
}

