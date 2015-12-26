/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.res.Resources
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 *  android.util.DisplayMetrics
 *  android.view.TouchDelegate
 *  android.view.View
 *  android.view.View$OnLayoutChangeListener
 *  android.view.ViewParent
 *  java.lang.Object
 */
package android.support.v7.widget;

import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewParent;

class Toolbar$3
implements View.OnLayoutChangeListener {
    final /* synthetic */ Toolbar this$0;

    Toolbar$3(Toolbar toolbar) {
        this.this$0 = toolbar;
    }

    public void onLayoutChange(View view, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9) {
        View view2 = (View)view.getParent();
        n6 = (int)(this.this$0.getResources().getDisplayMetrics().density * 56.0f);
        if (n4 - n2 < n6) {
            n6 = (n6 - (n4 - n2)) / 2;
            view2.setTouchDelegate(new TouchDelegate(new Rect(n2 - n6, n3, n6 + n4, n5), view));
        }
        view2 = view.getBackground();
        n2 = n4 - n2;
        n5 -= n3;
        n7 = view.getPaddingLeft() - view.getPaddingRight();
        n6 = view.getPaddingTop() - view.getPaddingBottom();
        if (view2 != null && (n7 != 0 || n6 != 0)) {
            n3 = n2 / 2;
            n4 = n5 / 2;
            n2 = (n2 + n7) / 2;
            n5 = (n5 + n6) / 2;
            DrawableCompat.setHotspotBounds((Drawable)view2, n2 - n3, n5 - n4, n2 + n3, n5 + n4);
        }
    }
}

