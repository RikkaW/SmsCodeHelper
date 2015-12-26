/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 */
package android.support.v7.widget;

import android.support.v7.widget.ListPopupWindow;
import android.view.View;

class ListPopupWindow$1
extends ListPopupWindow.ForwardingListener {
    final /* synthetic */ ListPopupWindow this$0;

    ListPopupWindow$1(ListPopupWindow listPopupWindow, View view) {
        this.this$0 = listPopupWindow;
        super(view);
    }

    @Override
    public ListPopupWindow getPopup() {
        return this.this$0;
    }
}

