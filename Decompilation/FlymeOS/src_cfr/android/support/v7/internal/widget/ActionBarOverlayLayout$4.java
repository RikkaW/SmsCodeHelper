/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.internal.widget.ActionBarOverlayLayout;
import android.view.View;

class ActionBarOverlayLayout$4
implements Runnable {
    final /* synthetic */ ActionBarOverlayLayout this$0;

    ActionBarOverlayLayout$4(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.this$0 = actionBarOverlayLayout;
    }

    @Override
    public void run() {
        ActionBarOverlayLayout.access$300(this.this$0);
        ActionBarOverlayLayout.access$002(this.this$0, ViewCompat.animate((View)ActionBarOverlayLayout.access$500(this.this$0)).translationY(- ActionBarOverlayLayout.access$500(this.this$0).getHeight()).setListener(ActionBarOverlayLayout.access$400(this.this$0)));
        if (ActionBarOverlayLayout.access$600(this.this$0) != null && ActionBarOverlayLayout.access$600(this.this$0).getVisibility() != 8) {
            ActionBarOverlayLayout.access$202(this.this$0, ViewCompat.animate((View)ActionBarOverlayLayout.access$600(this.this$0)).translationY(ActionBarOverlayLayout.access$600(this.this$0).getHeight()).setListener(ActionBarOverlayLayout.access$700(this.this$0)));
        }
    }
}

