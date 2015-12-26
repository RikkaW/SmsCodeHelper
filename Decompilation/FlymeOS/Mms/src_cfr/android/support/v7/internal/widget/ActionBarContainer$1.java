/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Rect
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnDrawListener
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.graphics.Rect;
import android.support.v7.internal.widget.ActionBarContainer;
import android.view.ViewTreeObserver;

class ActionBarContainer$1
implements ViewTreeObserver.OnDrawListener {
    final /* synthetic */ ActionBarContainer this$0;

    ActionBarContainer$1(ActionBarContainer actionBarContainer) {
        this.this$0 = actionBarContainer;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void onDraw() {
        Rect rect;
        try {
            if (ActionBarContainer.access$000() == null || ActionBarContainer.access$100() == null || (rect = (Rect)ActionBarContainer.access$000().get(ActionBarContainer.access$100().invoke((Object)this.this$0, new Object[0]))) == null) return;
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
            return;
        }
        if (!Rect.intersects((Rect)ActionBarContainer.access$200(this.this$0), (Rect)rect) || rect.contains(ActionBarContainer.access$200(this.this$0))) return;
        rect.union(ActionBarContainer.access$200(this.this$0));
    }
}

