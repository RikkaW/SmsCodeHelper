/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.annotation.TargetApi
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.ColorStateList
 *  android.graphics.drawable.Drawable
 *  android.util.Log
 *  android.view.ActionProvider
 *  android.view.CollapsibleActionView
 *  android.view.ContextMenu
 *  android.view.ContextMenu$ContextMenuInfo
 *  android.view.MenuItem
 *  android.view.MenuItem$OnActionExpandListener
 *  android.view.MenuItem$OnMenuItemClickListener
 *  android.view.SubMenu
 *  android.view.View
 *  android.widget.FrameLayout
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.lang.reflect.Method
 */
package android.support.v7.internal.view.menu;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.internal.view.menu.BaseMenuWrapper;
import android.support.v7.internal.view.menu.BaseWrapper;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.util.Log;
import android.view.CollapsibleActionView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

@TargetApi(value=14)
public class MenuItemWrapperICS
extends BaseMenuWrapper<SupportMenuItem>
implements MenuItem {
    static final String LOG_TAG = "MenuItemWrapper";
    private Method mSetExclusiveCheckableMethod;

    MenuItemWrapperICS(Context context, SupportMenuItem supportMenuItem) {
        super(context, supportMenuItem);
    }

    public boolean collapseActionView() {
        return ((SupportMenuItem)this.mWrappedObject).collapseActionView();
    }

    ActionProviderWrapper createActionProviderWrapper(android.view.ActionProvider actionProvider) {
        return new ActionProviderWrapper(this.mContext, actionProvider);
    }

    public boolean expandActionView() {
        return ((SupportMenuItem)this.mWrappedObject).expandActionView();
    }

    public android.view.ActionProvider getActionProvider() {
        ActionProvider actionProvider = ((SupportMenuItem)this.mWrappedObject).getSupportActionProvider();
        if (actionProvider instanceof ActionProviderWrapper) {
            return ((ActionProviderWrapper)actionProvider).mInner;
        }
        return null;
    }

    public View getActionView() {
        View view;
        View view2 = view = ((SupportMenuItem)this.mWrappedObject).getActionView();
        if (view instanceof CollapsibleActionViewWrapper) {
            view2 = ((CollapsibleActionViewWrapper)view).getWrappedView();
        }
        return view2;
    }

    public char getAlphabeticShortcut() {
        return ((SupportMenuItem)this.mWrappedObject).getAlphabeticShortcut();
    }

    public int getGroupId() {
        return ((SupportMenuItem)this.mWrappedObject).getGroupId();
    }

    public Drawable getIcon() {
        return ((SupportMenuItem)this.mWrappedObject).getIcon();
    }

    public Intent getIntent() {
        return ((SupportMenuItem)this.mWrappedObject).getIntent();
    }

    public int getItemId() {
        return ((SupportMenuItem)this.mWrappedObject).getItemId();
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return ((SupportMenuItem)this.mWrappedObject).getMenuInfo();
    }

    public char getNumericShortcut() {
        return ((SupportMenuItem)this.mWrappedObject).getNumericShortcut();
    }

    public int getOrder() {
        return ((SupportMenuItem)this.mWrappedObject).getOrder();
    }

    public SubMenu getSubMenu() {
        return this.getSubMenuWrapper(((SupportMenuItem)this.mWrappedObject).getSubMenu());
    }

    public CharSequence getTitle() {
        return ((SupportMenuItem)this.mWrappedObject).getTitle();
    }

    public CharSequence getTitleCondensed() {
        return ((SupportMenuItem)this.mWrappedObject).getTitleCondensed();
    }

    public boolean hasSubMenu() {
        return ((SupportMenuItem)this.mWrappedObject).hasSubMenu();
    }

    public boolean isActionViewExpanded() {
        return ((SupportMenuItem)this.mWrappedObject).isActionViewExpanded();
    }

    public boolean isCheckable() {
        return ((SupportMenuItem)this.mWrappedObject).isCheckable();
    }

    public boolean isChecked() {
        return ((SupportMenuItem)this.mWrappedObject).isChecked();
    }

    public boolean isEnabled() {
        return ((SupportMenuItem)this.mWrappedObject).isEnabled();
    }

    public boolean isVisible() {
        return ((SupportMenuItem)this.mWrappedObject).isVisible();
    }

    /*
     * Enabled aggressive block sorting
     */
    public MenuItem setActionProvider(android.view.ActionProvider object) {
        SupportMenuItem supportMenuItem = (SupportMenuItem)this.mWrappedObject;
        object = object != null ? this.createActionProviderWrapper((android.view.ActionProvider)object) : null;
        supportMenuItem.setSupportActionProvider((ActionProvider)object);
        return this;
    }

    public MenuItem setActionView(int n2) {
        ((SupportMenuItem)this.mWrappedObject).setActionView(n2);
        View view = ((SupportMenuItem)this.mWrappedObject).getActionView();
        if (view instanceof CollapsibleActionView) {
            ((SupportMenuItem)this.mWrappedObject).setActionView((View)new CollapsibleActionViewWrapper(view));
        }
        return this;
    }

    public MenuItem setActionView(View view) {
        Object object = view;
        if (view instanceof CollapsibleActionView) {
            object = new CollapsibleActionViewWrapper(view);
        }
        ((SupportMenuItem)this.mWrappedObject).setActionView((View)object);
        return this;
    }

    public MenuItem setAlphabeticShortcut(char c2) {
        ((SupportMenuItem)this.mWrappedObject).setAlphabeticShortcut(c2);
        return this;
    }

    public MenuItem setCheckable(boolean bl2) {
        ((SupportMenuItem)this.mWrappedObject).setCheckable(bl2);
        return this;
    }

    public MenuItem setChecked(boolean bl2) {
        ((SupportMenuItem)this.mWrappedObject).setChecked(bl2);
        return this;
    }

    public MenuItem setEnabled(boolean bl2) {
        ((SupportMenuItem)this.mWrappedObject).setEnabled(bl2);
        return this;
    }

    public void setExclusiveCheckable(boolean bl2) {
        try {
            if (this.mSetExclusiveCheckableMethod == null) {
                this.mSetExclusiveCheckableMethod = ((SupportMenuItem)this.mWrappedObject).getClass().getDeclaredMethod("setExclusiveCheckable", new Class[]{Boolean.TYPE});
            }
            this.mSetExclusiveCheckableMethod.invoke(this.mWrappedObject, new Object[]{bl2});
            return;
        }
        catch (Exception var2_2) {
            Log.w((String)"MenuItemWrapper", (String)"Error while calling setExclusiveCheckable", (Throwable)var2_2);
            return;
        }
    }

    public MenuItem setIcon(int n2) {
        ((SupportMenuItem)this.mWrappedObject).setIcon(n2);
        return this;
    }

    public MenuItem setIcon(Drawable drawable2) {
        ((SupportMenuItem)this.mWrappedObject).setIcon(drawable2);
        return this;
    }

    public MenuItem setIntent(Intent intent) {
        ((SupportMenuItem)this.mWrappedObject).setIntent(intent);
        return this;
    }

    public MenuItem setNumericShortcut(char c2) {
        ((SupportMenuItem)this.mWrappedObject).setNumericShortcut(c2);
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener object) {
        SupportMenuItem supportMenuItem = (SupportMenuItem)this.mWrappedObject;
        object = object != null ? new OnActionExpandListenerWrapper((MenuItem.OnActionExpandListener)object) : null;
        supportMenuItem.setSupportOnActionExpandListener((MenuItemCompat.OnActionExpandListener)object);
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        SupportMenuItem supportMenuItem = (SupportMenuItem)this.mWrappedObject;
        onMenuItemClickListener = onMenuItemClickListener != null ? new OnMenuItemClickListenerWrapper(onMenuItemClickListener) : null;
        supportMenuItem.setOnMenuItemClickListener(onMenuItemClickListener);
        return this;
    }

    public MenuItem setShortcut(char c2, char c3) {
        ((SupportMenuItem)this.mWrappedObject).setShortcut(c2, c3);
        return this;
    }

    public void setShowAsAction(int n2) {
        ((SupportMenuItem)this.mWrappedObject).setShowAsAction(n2);
    }

    public MenuItem setShowAsActionFlags(int n2) {
        ((SupportMenuItem)this.mWrappedObject).setShowAsActionFlags(n2);
        return this;
    }

    public MenuItem setTitle(int n2) {
        ((SupportMenuItem)this.mWrappedObject).setTitle(n2);
        return this;
    }

    public MenuItem setTitle(CharSequence charSequence) {
        ((SupportMenuItem)this.mWrappedObject).setTitle(charSequence);
        return this;
    }

    public MenuItem setTitleColor(ColorStateList colorStateList) {
        if (this.mWrappedObject instanceof MenuItemImpl) {
            ((MenuItemImpl)this.mWrappedObject).setTitleColor(colorStateList);
        }
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        ((SupportMenuItem)this.mWrappedObject).setTitleCondensed(charSequence);
        return this;
    }

    public MenuItem setVisible(boolean bl2) {
        return ((SupportMenuItem)this.mWrappedObject).setVisible(bl2);
    }

    class ActionProviderWrapper
    extends ActionProvider {
        final android.view.ActionProvider mInner;

        public ActionProviderWrapper(Context context, android.view.ActionProvider actionProvider) {
            super(context);
            this.mInner = actionProvider;
        }

        @Override
        public boolean hasSubMenu() {
            return this.mInner.hasSubMenu();
        }

        @Override
        public View onCreateActionView() {
            return this.mInner.onCreateActionView();
        }

        @Override
        public boolean onPerformDefaultAction() {
            return this.mInner.onPerformDefaultAction();
        }

        @Override
        public void onPrepareSubMenu(SubMenu subMenu) {
            this.mInner.onPrepareSubMenu(MenuItemWrapperICS.this.getSubMenuWrapper(subMenu));
        }
    }

    static class CollapsibleActionViewWrapper
    extends FrameLayout
    implements android.support.v7.view.CollapsibleActionView {
        final CollapsibleActionView mWrappedView;

        CollapsibleActionViewWrapper(View view) {
            super(view.getContext());
            this.mWrappedView = (CollapsibleActionView)view;
            this.addView(view);
        }

        View getWrappedView() {
            return (View)this.mWrappedView;
        }

        @Override
        public void onActionViewCollapsed() {
            this.mWrappedView.onActionViewCollapsed();
        }

        @Override
        public void onActionViewExpanded() {
            this.mWrappedView.onActionViewExpanded();
        }
    }

    class OnActionExpandListenerWrapper
    extends BaseWrapper<MenuItem.OnActionExpandListener>
    implements MenuItemCompat.OnActionExpandListener {
        OnActionExpandListenerWrapper(MenuItem.OnActionExpandListener onActionExpandListener) {
            super(onActionExpandListener);
        }

        @Override
        public boolean onMenuItemActionCollapse(MenuItem menuItem) {
            return ((MenuItem.OnActionExpandListener)this.mWrappedObject).onMenuItemActionCollapse(MenuItemWrapperICS.this.getMenuItemWrapper(menuItem));
        }

        @Override
        public boolean onMenuItemActionExpand(MenuItem menuItem) {
            return ((MenuItem.OnActionExpandListener)this.mWrappedObject).onMenuItemActionExpand(MenuItemWrapperICS.this.getMenuItemWrapper(menuItem));
        }
    }

    class OnMenuItemClickListenerWrapper
    extends BaseWrapper<MenuItem.OnMenuItemClickListener>
    implements MenuItem.OnMenuItemClickListener {
        OnMenuItemClickListenerWrapper(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
            super(onMenuItemClickListener);
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            return ((MenuItem.OnMenuItemClickListener)this.mWrappedObject).onMenuItemClick(MenuItemWrapperICS.this.getMenuItemWrapper(menuItem));
        }
    }

}

