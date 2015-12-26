/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Rect
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.graphics.Rect;
import android.support.v7.internal.widget.ActionBarContainer;
import android.view.ViewTreeObserver;

class ActionBarContainer$2
implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ ActionBarContainer this$0;

    ActionBarContainer$2(ActionBarContainer actionBarContainer) {
        this.this$0 = actionBarContainer;
    }

    public void onGlobalLayout() {
        this.this$0.getGlobalVisibleRect(ActionBarContainer.access$200(this.this$0));
    }
}

