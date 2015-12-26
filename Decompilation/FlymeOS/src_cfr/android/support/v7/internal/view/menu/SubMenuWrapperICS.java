/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.drawable.Drawable
 *  android.view.MenuItem
 *  android.view.SubMenu
 *  android.view.View
 *  java.lang.Object
 */
package android.support.v7.internal.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.internal.view.SupportSubMenu;
import android.support.v7.internal.view.menu.MenuWrapperICS;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

class SubMenuWrapperICS
extends MenuWrapperICS
implements SubMenu {
    SubMenuWrapperICS(Context context, SupportSubMenu supportSubMenu) {
        super(context, supportSubMenu);
    }

    public void clearHeader() {
        this.getWrappedObject().clearHeader();
    }

    public MenuItem getItem() {
        return this.getMenuItemWrapper(this.getWrappedObject().getItem());
    }

    @Override
    public SupportSubMenu getWrappedObject() {
        return (SupportSubMenu)this.mWrappedObject;
    }

    public SubMenu setHeaderIcon(int n2) {
        this.getWrappedObject().setHeaderIcon(n2);
        return this;
    }

    public SubMenu setHeaderIcon(Drawable drawable2) {
        this.getWrappedObject().setHeaderIcon(drawable2);
        return this;
    }

    public SubMenu setHeaderTitle(int n2) {
        this.getWrappedObject().setHeaderTitle(n2);
        return this;
    }

    public SubMenu setHeaderTitle(CharSequence charSequence) {
        this.getWrappedObject().setHeaderTitle(charSequence);
        return this;
    }

    public SubMenu setHeaderView(View view) {
        this.getWrappedObject().setHeaderView(view);
        return this;
    }

    public SubMenu setIcon(int n2) {
        this.getWrappedObject().setIcon(n2);
        return this;
    }

    public SubMenu setIcon(Drawable drawable2) {
        this.getWrappedObject().setIcon(drawable2);
        return this;
    }
}

