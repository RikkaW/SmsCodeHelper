/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnScrollChangedListener
 *  android.widget.PopupWindow
 *  java.lang.Object
 *  java.lang.reflect.Field
 */
package android.support.v7.internal.widget;

import android.view.ViewTreeObserver;
import android.widget.PopupWindow;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

final class AppCompatPopupWindow$1
implements ViewTreeObserver.OnScrollChangedListener {
    final /* synthetic */ Field val$fieldAnchor;
    final /* synthetic */ ViewTreeObserver.OnScrollChangedListener val$originalListener;
    final /* synthetic */ PopupWindow val$popup;

    AppCompatPopupWindow$1(Field field, PopupWindow popupWindow, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener) {
        this.val$fieldAnchor = field;
        this.val$popup = popupWindow;
        this.val$originalListener = onScrollChangedListener;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void onScrollChanged() {
        WeakReference weakReference = (WeakReference)this.val$fieldAnchor.get((Object)this.val$popup);
        if (weakReference == null) return;
        try {
            if (weakReference.get() == null) {
                return;
            }
            this.val$originalListener.onScrollChanged();
            return;
        }
        catch (IllegalAccessException var1_2) {
            // empty catch block
        }
    }
}

