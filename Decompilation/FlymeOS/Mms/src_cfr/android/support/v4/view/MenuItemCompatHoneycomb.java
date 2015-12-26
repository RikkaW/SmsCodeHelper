/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.MenuItem
 *  android.view.View
 *  java.lang.Object
 */
package android.support.v4.view;

import android.view.MenuItem;
import android.view.View;

class MenuItemCompatHoneycomb {
    MenuItemCompatHoneycomb() {
    }

    public static View getActionView(MenuItem menuItem) {
        return menuItem.getActionView();
    }

    public static MenuItem setActionView(MenuItem menuItem, int n2) {
        return menuItem.setActionView(n2);
    }

    public static MenuItem setActionView(MenuItem menuItem, View view) {
        return menuItem.setActionView(view);
    }

    public static void setShowAsAction(MenuItem menuItem, int n2) {
        menuItem.setShowAsAction(n2);
    }
}

