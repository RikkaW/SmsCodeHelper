/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.view.Menu
 *  android.view.MenuItem
 *  android.view.SubMenu
 *  android.view.View
 *  java.lang.String
 */
package android.support.v7.internal.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public class SubMenuBuilder
extends MenuBuilder
implements SubMenu {
    private MenuItemImpl mItem;
    private MenuBuilder mParentMenu;

    public SubMenuBuilder(Context context, MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        super(context);
        this.mParentMenu = menuBuilder;
        this.mItem = menuItemImpl;
    }

    @Override
    public boolean collapseItemActionView(MenuItemImpl menuItemImpl) {
        return this.mParentMenu.collapseItemActionView(menuItemImpl);
    }

    @Override
    boolean dispatchMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        if (super.dispatchMenuItemSelected(menuBuilder, menuItem) || this.mParentMenu.dispatchMenuItemSelected(menuBuilder, menuItem)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean expandItemActionView(MenuItemImpl menuItemImpl) {
        return this.mParentMenu.expandItemActionView(menuItemImpl);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public String getActionViewStatesKey() {
        if (this.mItem == null) return null;
        int n2 = this.mItem.getItemId();
        if (n2 != 0) return super.getActionViewStatesKey() + ":" + n2;
        return null;
    }

    public MenuItem getItem() {
        return this.mItem;
    }

    public Menu getParentMenu() {
        return this.mParentMenu;
    }

    @Override
    public MenuBuilder getRootMenu() {
        return this.mParentMenu;
    }

    @Override
    public boolean isQwertyMode() {
        return this.mParentMenu.isQwertyMode();
    }

    @Override
    public boolean isShortcutsVisible() {
        return this.mParentMenu.isShortcutsVisible();
    }

    @Override
    public void setCallback(MenuBuilder.Callback callback) {
        this.mParentMenu.setCallback(callback);
    }

    public SubMenu setHeaderIcon(int n2) {
        super.setHeaderIconInt(ContextCompat.getDrawable(this.getContext(), n2));
        return this;
    }

    public SubMenu setHeaderIcon(Drawable drawable2) {
        super.setHeaderIconInt(drawable2);
        return this;
    }

    public SubMenu setHeaderTitle(int n2) {
        super.setHeaderTitleInt(this.getContext().getResources().getString(n2));
        return this;
    }

    public SubMenu setHeaderTitle(CharSequence charSequence) {
        super.setHeaderTitleInt(charSequence);
        return this;
    }

    public SubMenu setHeaderView(View view) {
        super.setHeaderViewInt(view);
        return this;
    }

    public SubMenu setIcon(int n2) {
        this.mItem.setIcon(n2);
        return this;
    }

    public SubMenu setIcon(Drawable drawable2) {
        this.mItem.setIcon(drawable2);
        return this;
    }

    @Override
    public void setQwertyMode(boolean bl2) {
        this.mParentMenu.setQwertyMode(bl2);
    }

    @Override
    public void setShortcutsVisible(boolean bl2) {
        this.mParentMenu.setShortcutsVisible(bl2);
    }
}

