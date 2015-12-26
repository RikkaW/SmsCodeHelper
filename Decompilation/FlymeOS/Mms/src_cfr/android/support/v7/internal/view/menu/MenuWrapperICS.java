/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.view.KeyEvent
 *  android.view.Menu
 *  android.view.MenuItem
 *  android.view.SubMenu
 *  java.lang.Object
 */
package android.support.v7.internal.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.internal.view.SupportMenu;
import android.support.v7.internal.view.menu.BaseMenuWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

class MenuWrapperICS
extends BaseMenuWrapper<SupportMenu>
implements Menu {
    MenuWrapperICS(Context context, SupportMenu supportMenu) {
        super(context, supportMenu);
    }

    public MenuItem add(int n2) {
        return this.getMenuItemWrapper(((SupportMenu)this.mWrappedObject).add(n2));
    }

    public MenuItem add(int n2, int n3, int n4, int n5) {
        return this.getMenuItemWrapper(((SupportMenu)this.mWrappedObject).add(n2, n3, n4, n5));
    }

    public MenuItem add(int n2, int n3, int n4, CharSequence charSequence) {
        return this.getMenuItemWrapper(((SupportMenu)this.mWrappedObject).add(n2, n3, n4, charSequence));
    }

    public MenuItem add(CharSequence charSequence) {
        return this.getMenuItemWrapper(((SupportMenu)this.mWrappedObject).add(charSequence));
    }

    public int addIntentOptions(int n2, int n3, int n4, ComponentName componentName, Intent[] arrintent, Intent intent, int n5, MenuItem[] arrmenuItem) {
        MenuItem[] arrmenuItem2 = null;
        if (arrmenuItem != null) {
            arrmenuItem2 = new MenuItem[arrmenuItem.length];
        }
        n3 = ((SupportMenu)this.mWrappedObject).addIntentOptions(n2, n3, n4, componentName, arrintent, intent, n5, arrmenuItem2);
        if (arrmenuItem2 != null) {
            n4 = arrmenuItem2.length;
            for (n2 = 0; n2 < n4; ++n2) {
                arrmenuItem[n2] = this.getMenuItemWrapper(arrmenuItem2[n2]);
            }
        }
        return n3;
    }

    public SubMenu addSubMenu(int n2) {
        return this.getSubMenuWrapper(((SupportMenu)this.mWrappedObject).addSubMenu(n2));
    }

    public SubMenu addSubMenu(int n2, int n3, int n4, int n5) {
        return this.getSubMenuWrapper(((SupportMenu)this.mWrappedObject).addSubMenu(n2, n3, n4, n5));
    }

    public SubMenu addSubMenu(int n2, int n3, int n4, CharSequence charSequence) {
        return this.getSubMenuWrapper(((SupportMenu)this.mWrappedObject).addSubMenu(n2, n3, n4, charSequence));
    }

    public SubMenu addSubMenu(CharSequence charSequence) {
        return this.getSubMenuWrapper(((SupportMenu)this.mWrappedObject).addSubMenu(charSequence));
    }

    public void clear() {
        this.internalClear();
        ((SupportMenu)this.mWrappedObject).clear();
    }

    public void close() {
        ((SupportMenu)this.mWrappedObject).close();
    }

    public MenuItem findItem(int n2) {
        return this.getMenuItemWrapper(((SupportMenu)this.mWrappedObject).findItem(n2));
    }

    public MenuItem getItem(int n2) {
        return this.getMenuItemWrapper(((SupportMenu)this.mWrappedObject).getItem(n2));
    }

    public boolean hasVisibleItems() {
        return ((SupportMenu)this.mWrappedObject).hasVisibleItems();
    }

    public boolean isShortcutKey(int n2, KeyEvent keyEvent) {
        return ((SupportMenu)this.mWrappedObject).isShortcutKey(n2, keyEvent);
    }

    public boolean performIdentifierAction(int n2, int n3) {
        return ((SupportMenu)this.mWrappedObject).performIdentifierAction(n2, n3);
    }

    public boolean performShortcut(int n2, KeyEvent keyEvent, int n3) {
        return ((SupportMenu)this.mWrappedObject).performShortcut(n2, keyEvent, n3);
    }

    public void removeGroup(int n2) {
        this.internalRemoveGroup(n2);
        ((SupportMenu)this.mWrappedObject).removeGroup(n2);
    }

    public void removeItem(int n2) {
        this.internalRemoveItem(n2);
        ((SupportMenu)this.mWrappedObject).removeItem(n2);
    }

    public void setGroupCheckable(int n2, boolean bl2, boolean bl3) {
        ((SupportMenu)this.mWrappedObject).setGroupCheckable(n2, bl2, bl3);
    }

    public void setGroupEnabled(int n2, boolean bl2) {
        ((SupportMenu)this.mWrappedObject).setGroupEnabled(n2, bl2);
    }

    public void setGroupVisible(int n2, boolean bl2) {
        ((SupportMenu)this.mWrappedObject).setGroupVisible(n2, bl2);
    }

    public void setQwertyMode(boolean bl2) {
        ((SupportMenu)this.mWrappedObject).setQwertyMode(bl2);
    }

    public int size() {
        return ((SupportMenu)this.mWrappedObject).size();
    }
}

