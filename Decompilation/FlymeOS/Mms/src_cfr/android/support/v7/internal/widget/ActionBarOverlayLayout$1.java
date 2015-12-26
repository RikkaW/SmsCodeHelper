/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 */
package android.support.v7.internal.widget;

import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.internal.widget.ActionBarOverlayLayout;
import android.view.View;

class ActionBarOverlayLayout$1
extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ ActionBarOverlayLayout this$0;

    ActionBarOverlayLayout$1(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.this$0 = actionBarOverlayLayout;
    }

    @Override
    public void onAnimationCancel(View view) {
        ActionBarOverlayLayout.access$002(this.this$0, null);
        ActionBarOverlayLayout.access$102(this.this$0, false);
    }

    @Override
    public void onAnimationEnd(View view) {
        ActionBarOverlayLayout.access$002(this.this$0, null);
        ActionBarOverlayLayout.access$102(this.this$0, false);
    }
}

