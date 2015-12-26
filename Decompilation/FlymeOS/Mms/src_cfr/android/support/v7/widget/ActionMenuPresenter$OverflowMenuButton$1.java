/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 */
package android.support.v7.widget;

import android.support.v7.widget.ActionMenuPresenter;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;

class ActionMenuPresenter$OverflowMenuButton$1
extends ListPopupWindow.ForwardingListener {
    final /* synthetic */ ActionMenuPresenter.OverflowMenuButton this$1;
    final /* synthetic */ ActionMenuPresenter val$this$0;

    ActionMenuPresenter$OverflowMenuButton$1(ActionMenuPresenter.OverflowMenuButton overflowMenuButton, View view, ActionMenuPresenter actionMenuPresenter) {
        this.this$1 = overflowMenuButton;
        this.val$this$0 = actionMenuPresenter;
        super(view);
    }

    @Override
    public ListPopupWindow getPopup() {
        if (ActionMenuPresenter.access$300(this.this$1.this$0) == null) {
            return null;
        }
        return ActionMenuPresenter.access$300(this.this$1.this$0).getPopup();
    }

    @Override
    public boolean onForwardingStarted() {
        this.this$1.this$0.showOverflowMenu();
        return true;
    }

    @Override
    public boolean onForwardingStopped() {
        if (ActionMenuPresenter.access$400(this.this$1.this$0) != null) {
            return false;
        }
        this.this$1.this$0.hideOverflowMenu();
        return true;
    }
}

