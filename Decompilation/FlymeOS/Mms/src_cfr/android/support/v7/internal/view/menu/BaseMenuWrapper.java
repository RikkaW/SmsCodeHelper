/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.MenuItem
 *  android.view.SubMenu
 *  java.lang.Object
 */
package android.support.v7.internal.view.menu;

import android.content.Context;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.internal.view.SupportSubMenu;
import android.support.v4.util.ArrayMap;
import android.support.v7.internal.view.menu.BaseWrapper;
import android.support.v7.internal.view.menu.MenuWrapperFactory;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

abstract class BaseMenuWrapper<T>
extends BaseWrapper<T> {
    final Context mContext;
    private Map<SupportMenuItem, MenuItem> mMenuItems;
    private Map<SupportSubMenu, SubMenu> mSubMenus;

    BaseMenuWrapper(Context context, T t2) {
        super(t2);
        this.mContext = context;
    }

    final MenuItem getMenuItemWrapper(MenuItem menuItem) {
        if (menuItem instanceof SupportMenuItem) {
            MenuItem menuItem2;
            SupportMenuItem supportMenuItem = (SupportMenuItem)menuItem;
            if (this.mMenuItems == null) {
                this.mMenuItems = new ArrayMap<SupportMenuItem, MenuItem>();
            }
            menuItem = menuItem2 = this.mMenuItems.get((Object)menuItem);
            if (menuItem2 == null) {
                menuItem = MenuWrapperFactory.wrapSupportMenuItem(this.mContext, supportMenuItem);
                this.mMenuItems.put(supportMenuItem, menuItem);
            }
            return menuItem;
        }
        return menuItem;
    }

    final SubMenu getSubMenuWrapper(SubMenu subMenu) {
        if (subMenu instanceof SupportSubMenu) {
            SubMenu subMenu2;
            SupportSubMenu supportSubMenu = (SupportSubMenu)subMenu;
            if (this.mSubMenus == null) {
                this.mSubMenus = new ArrayMap<SupportSubMenu, SubMenu>();
            }
            subMenu = subMenu2 = this.mSubMenus.get(supportSubMenu);
            if (subMenu2 == null) {
                subMenu = MenuWrapperFactory.wrapSupportSubMenu(this.mContext, supportSubMenu);
                this.mSubMenus.put(supportSubMenu, subMenu);
            }
            return subMenu;
        }
        return subMenu;
    }

    final void internalClear() {
        if (this.mMenuItems != null) {
            this.mMenuItems.clear();
        }
        if (this.mSubMenus != null) {
            this.mSubMenus.clear();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    final void internalRemoveGroup(int n2) {
        if (this.mMenuItems != null) {
            Iterator<SupportMenuItem> iterator = this.mMenuItems.keySet().iterator();
            while (iterator.hasNext()) {
                if (n2 != ((MenuItem)iterator.next()).getGroupId()) continue;
                iterator.remove();
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    final void internalRemoveItem(int n2) {
        if (this.mMenuItems == null) {
            return;
        }
        Iterator<SupportMenuItem> iterator = this.mMenuItems.keySet().iterator();
        do {
            if (!iterator.hasNext()) return;
        } while (n2 != ((MenuItem)iterator.next()).getItemId());
        iterator.remove();
    }
}

