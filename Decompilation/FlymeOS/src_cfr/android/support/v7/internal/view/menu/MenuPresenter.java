/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Parcelable
 *  android.view.ViewGroup
 *  java.lang.Object
 */
package android.support.v7.internal.view.menu;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.view.ViewGroup;

public interface MenuPresenter {
    public boolean collapseItemActionView(MenuBuilder var1, MenuItemImpl var2);

    public boolean expandItemActionView(MenuBuilder var1, MenuItemImpl var2);

    public boolean flagActionItems();

    public int getId();

    public MenuView getMenuView(ViewGroup var1);

    public void initForMenu(Context var1, MenuBuilder var2);

    public void onCloseMenu(MenuBuilder var1, boolean var2);

    public void onRestoreInstanceState(Parcelable var1);

    public Parcelable onSaveInstanceState();

    public boolean onSubMenuSelected(SubMenuBuilder var1);

    public void setCallback(Callback var1);

    public void updateMenuView(boolean var1);

    public static interface Callback {
        public void onCloseMenu(MenuBuilder var1, boolean var2);

        public boolean onOpenSubMenu(MenuBuilder var1);
    }

}

