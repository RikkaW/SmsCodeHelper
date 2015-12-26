/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 */
package android.support.v7.widget;

import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.PopupMenu;
import android.view.View;

class PopupMenu$1
extends ListPopupWindow.ForwardingListener {
    final /* synthetic */ PopupMenu this$0;

    PopupMenu$1(PopupMenu popupMenu, View view) {
        this.this$0 = popupMenu;
        super(view);
    }

    @Override
    public ListPopupWindow getPopup() {
        return PopupMenu.access$000(this.this$0).getPopup();
    }

    @Override
    protected boolean onForwardingStarted() {
        this.this$0.show();
        return true;
    }

    @Override
    protected boolean onForwardingStopped() {
        this.this$0.dismiss();
        return true;
    }
}

