/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.MenuItem
 *  java.lang.Object
 */
package android.support.v4.view;

import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MenuItemCompatIcs;
import android.view.MenuItem;

class MenuItemCompat$IcsMenuVersionImpl$1
implements MenuItemCompatIcs.SupportActionExpandProxy {
    final /* synthetic */ MenuItemCompat.IcsMenuVersionImpl this$0;
    final /* synthetic */ MenuItemCompat.OnActionExpandListener val$listener;

    MenuItemCompat$IcsMenuVersionImpl$1(MenuItemCompat.IcsMenuVersionImpl icsMenuVersionImpl, MenuItemCompat.OnActionExpandListener onActionExpandListener) {
        this.this$0 = icsMenuVersionImpl;
        this.val$listener = onActionExpandListener;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        return this.val$listener.onMenuItemActionCollapse(menuItem);
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        return this.val$listener.onMenuItemActionExpand(menuItem);
    }
}

