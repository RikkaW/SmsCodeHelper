/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnPreDrawListener
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.internal.widget.MzSlidePopupWindow;
import android.view.ViewTreeObserver;

class MzSlidePopupWindow$1
implements ViewTreeObserver.OnPreDrawListener {
    final /* synthetic */ MzSlidePopupWindow this$0;

    MzSlidePopupWindow$1(MzSlidePopupWindow mzSlidePopupWindow) {
        this.this$0 = mzSlidePopupWindow;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onPreDraw() {
        MzSlidePopupWindow.access$100(this.this$0).getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver.OnPreDrawListener)this);
        int n2 = MzSlidePopupWindow.access$200(this.this$0) ? MzSlidePopupWindow.access$100(this.this$0).getHeight() : - MzSlidePopupWindow.access$100(this.this$0).getHeight();
        MzSlidePopupWindow.access$100(this.this$0).setTranslationY((float)n2);
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate(MzSlidePopupWindow.access$100(this.this$0)).translationY(0.0f);
        viewPropertyAnimatorCompat.setDuration(200);
        viewPropertyAnimatorCompat.start();
        return false;
    }
}

