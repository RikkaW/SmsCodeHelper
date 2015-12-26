/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package android.support.v7.internal.view.menu;

import android.support.v4.view.ActionProvider;
import android.support.v7.internal.view.menu.MenuItemImpl;

class MenuItemImpl$1
implements ActionProvider.VisibilityListener {
    final /* synthetic */ MenuItemImpl this$0;

    MenuItemImpl$1(MenuItemImpl menuItemImpl) {
        this.this$0 = menuItemImpl;
    }

    @Override
    public void onActionProviderVisibilityChanged(boolean bl2) {
        MenuItemImpl.access$000(this.this$0).onItemVisibleChanged(this.this$0);
    }
}

