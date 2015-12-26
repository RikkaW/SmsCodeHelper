/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.util.Log
 *  android.view.MenuItem
 *  android.view.View
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.view;

import android.os.Build;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat$IcsMenuVersionImpl$1;
import android.support.v4.view.MenuItemCompatHoneycomb;
import android.support.v4.view.MenuItemCompatIcs;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

public class MenuItemCompat {
    static final MenuVersionImpl IMPL;
    public static final int SHOW_AS_ACTION_ALWAYS = 2;
    public static final int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;
    public static final int SHOW_AS_ACTION_IF_ROOM = 1;
    public static final int SHOW_AS_ACTION_NEVER = 0;
    public static final int SHOW_AS_ACTION_WITH_TEXT = 4;
    private static final String TAG = "MenuItemCompat";

    static {
        int n2 = Build.VERSION.SDK_INT;
        IMPL = n2 >= 14 ? new IcsMenuVersionImpl() : (n2 >= 11 ? new HoneycombMenuVersionImpl() : new BaseMenuVersionImpl());
    }

    public static boolean collapseActionView(MenuItem menuItem) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).collapseActionView();
        }
        return IMPL.collapseActionView(menuItem);
    }

    public static boolean expandActionView(MenuItem menuItem) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).expandActionView();
        }
        return IMPL.expandActionView(menuItem);
    }

    public static ActionProvider getActionProvider(MenuItem menuItem) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).getSupportActionProvider();
        }
        Log.w((String)"MenuItemCompat", (String)"getActionProvider: item does not implement SupportMenuItem; returning null");
        return null;
    }

    public static View getActionView(MenuItem menuItem) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).getActionView();
        }
        return IMPL.getActionView(menuItem);
    }

    public static boolean isActionViewExpanded(MenuItem menuItem) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).isActionViewExpanded();
        }
        return IMPL.isActionViewExpanded(menuItem);
    }

    public static MenuItem setActionProvider(MenuItem menuItem, ActionProvider actionProvider) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).setSupportActionProvider(actionProvider);
        }
        Log.w((String)"MenuItemCompat", (String)"setActionProvider: item does not implement SupportMenuItem; ignoring");
        return menuItem;
    }

    public static MenuItem setActionView(MenuItem menuItem, int n2) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).setActionView(n2);
        }
        return IMPL.setActionView(menuItem, n2);
    }

    public static MenuItem setActionView(MenuItem menuItem, View view) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).setActionView(view);
        }
        return IMPL.setActionView(menuItem, view);
    }

    public static MenuItem setOnActionExpandListener(MenuItem menuItem, OnActionExpandListener onActionExpandListener) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).setSupportOnActionExpandListener(onActionExpandListener);
        }
        return IMPL.setOnActionExpandListener(menuItem, onActionExpandListener);
    }

    public static void setShowAsAction(MenuItem menuItem, int n2) {
        if (menuItem instanceof SupportMenuItem) {
            ((SupportMenuItem)menuItem).setShowAsAction(n2);
            return;
        }
        IMPL.setShowAsAction(menuItem, n2);
    }

    static class BaseMenuVersionImpl
    implements MenuVersionImpl {
        BaseMenuVersionImpl() {
        }

        @Override
        public boolean collapseActionView(MenuItem menuItem) {
            return false;
        }

        @Override
        public boolean expandActionView(MenuItem menuItem) {
            return false;
        }

        @Override
        public View getActionView(MenuItem menuItem) {
            return null;
        }

        @Override
        public boolean isActionViewExpanded(MenuItem menuItem) {
            return false;
        }

        @Override
        public MenuItem setActionView(MenuItem menuItem, int n2) {
            return menuItem;
        }

        @Override
        public MenuItem setActionView(MenuItem menuItem, View view) {
            return menuItem;
        }

        @Override
        public MenuItem setOnActionExpandListener(MenuItem menuItem, OnActionExpandListener onActionExpandListener) {
            return menuItem;
        }

        @Override
        public void setShowAsAction(MenuItem menuItem, int n2) {
        }
    }

    static class HoneycombMenuVersionImpl
    implements MenuVersionImpl {
        HoneycombMenuVersionImpl() {
        }

        @Override
        public boolean collapseActionView(MenuItem menuItem) {
            return false;
        }

        @Override
        public boolean expandActionView(MenuItem menuItem) {
            return false;
        }

        @Override
        public View getActionView(MenuItem menuItem) {
            return MenuItemCompatHoneycomb.getActionView(menuItem);
        }

        @Override
        public boolean isActionViewExpanded(MenuItem menuItem) {
            return false;
        }

        @Override
        public MenuItem setActionView(MenuItem menuItem, int n2) {
            return MenuItemCompatHoneycomb.setActionView(menuItem, n2);
        }

        @Override
        public MenuItem setActionView(MenuItem menuItem, View view) {
            return MenuItemCompatHoneycomb.setActionView(menuItem, view);
        }

        @Override
        public MenuItem setOnActionExpandListener(MenuItem menuItem, OnActionExpandListener onActionExpandListener) {
            return menuItem;
        }

        @Override
        public void setShowAsAction(MenuItem menuItem, int n2) {
            MenuItemCompatHoneycomb.setShowAsAction(menuItem, n2);
        }
    }

    static class IcsMenuVersionImpl
    extends HoneycombMenuVersionImpl {
        IcsMenuVersionImpl() {
        }

        @Override
        public boolean collapseActionView(MenuItem menuItem) {
            return MenuItemCompatIcs.collapseActionView(menuItem);
        }

        @Override
        public boolean expandActionView(MenuItem menuItem) {
            return MenuItemCompatIcs.expandActionView(menuItem);
        }

        @Override
        public boolean isActionViewExpanded(MenuItem menuItem) {
            return MenuItemCompatIcs.isActionViewExpanded(menuItem);
        }

        @Override
        public MenuItem setOnActionExpandListener(MenuItem menuItem, OnActionExpandListener onActionExpandListener) {
            if (onActionExpandListener == null) {
                return MenuItemCompatIcs.setOnActionExpandListener(menuItem, null);
            }
            return MenuItemCompatIcs.setOnActionExpandListener(menuItem, new MenuItemCompat$IcsMenuVersionImpl$1(this, onActionExpandListener));
        }
    }

    static interface MenuVersionImpl {
        public boolean collapseActionView(MenuItem var1);

        public boolean expandActionView(MenuItem var1);

        public View getActionView(MenuItem var1);

        public boolean isActionViewExpanded(MenuItem var1);

        public MenuItem setActionView(MenuItem var1, int var2);

        public MenuItem setActionView(MenuItem var1, View var2);

        public MenuItem setOnActionExpandListener(MenuItem var1, OnActionExpandListener var2);

        public void setShowAsAction(MenuItem var1, int var2);
    }

    public static interface OnActionExpandListener {
        public boolean onMenuItemActionCollapse(MenuItem var1);

        public boolean onMenuItemActionExpand(MenuItem var1);
    }

}

