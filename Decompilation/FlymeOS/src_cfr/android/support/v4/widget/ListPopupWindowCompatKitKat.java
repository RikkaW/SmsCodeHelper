/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnTouchListener
 *  android.widget.ListPopupWindow
 *  java.lang.Object
 */
package android.support.v4.widget;

import android.view.View;
import android.widget.ListPopupWindow;

class ListPopupWindowCompatKitKat {
    ListPopupWindowCompatKitKat() {
    }

    public static View.OnTouchListener createDragToOpenListener(Object object, View view) {
        return ((ListPopupWindow)object).createDragToOpenListener(view);
    }
}

