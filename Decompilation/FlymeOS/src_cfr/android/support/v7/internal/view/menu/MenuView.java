/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.drawable.Drawable
 *  java.lang.Object
 */
package android.support.v7.internal.view.menu;

import android.graphics.drawable.Drawable;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuItemImpl;

public interface MenuView {
    public int getWindowAnimations();

    public void initialize(MenuBuilder var1);

    public static interface ItemView {
        public MenuItemImpl getItemData();

        public void initialize(MenuItemImpl var1, int var2);

        public boolean prefersCondensedTitle();

        public void setCheckable(boolean var1);

        public void setChecked(boolean var1);

        public void setEnabled(boolean var1);

        public void setIcon(Drawable var1);

        public void setShortcut(boolean var1, char var2);

        public void setTitle(CharSequence var1);

        public boolean showsIcon();
    }

}

