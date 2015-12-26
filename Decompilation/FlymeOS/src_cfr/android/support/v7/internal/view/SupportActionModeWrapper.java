/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.annotation.TargetApi
 *  android.content.Context
 *  android.view.ActionMode
 *  android.view.ActionMode$Callback
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  java.lang.Object
 *  java.util.ArrayList
 */
package android.support.v7.internal.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.util.SimpleArrayMap;
import android.support.v7.internal.view.menu.MenuWrapperFactory;
import android.support.v7.view.ActionMode;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;

@TargetApi(value=11)
public class SupportActionModeWrapper
extends ActionMode {
    final Context mContext;
    final android.support.v7.view.ActionMode mWrappedObject;

    public SupportActionModeWrapper(Context context, android.support.v7.view.ActionMode actionMode) {
        this.mContext = context;
        this.mWrappedObject = actionMode;
    }

    public void finish() {
        this.mWrappedObject.finish();
    }

    public View getCustomView() {
        return this.mWrappedObject.getCustomView();
    }

    public Menu getMenu() {
        return MenuWrapperFactory.wrapSupportMenu(this.mContext, (SupportMenu)this.mWrappedObject.getMenu());
    }

    public MenuInflater getMenuInflater() {
        return this.mWrappedObject.getMenuInflater();
    }

    public CharSequence getSubtitle() {
        return this.mWrappedObject.getSubtitle();
    }

    public Object getTag() {
        return this.mWrappedObject.getTag();
    }

    public CharSequence getTitle() {
        return this.mWrappedObject.getTitle();
    }

    public boolean getTitleOptionalHint() {
        return this.mWrappedObject.getTitleOptionalHint();
    }

    public void invalidate() {
        this.mWrappedObject.invalidate();
    }

    public boolean isTitleOptional() {
        return this.mWrappedObject.isTitleOptional();
    }

    public void setAnimateToShowMenu(boolean bl2) {
        this.mWrappedObject.setAnimateToShowMenu(bl2);
    }

    public void setBackPressListener(ActionMode.BackPressedListener backPressedListener) {
        this.mWrappedObject.setBackPressListener(backPressedListener);
    }

    public void setCustomView(View view) {
        this.mWrappedObject.setCustomView(view);
    }

    public void setShowActionBar(boolean bl2) {
        this.mWrappedObject.setShowActionBar(bl2);
    }

    public void setSubtitle(int n2) {
        this.mWrappedObject.setSubtitle(n2);
    }

    public void setSubtitle(CharSequence charSequence) {
        this.mWrappedObject.setSubtitle(charSequence);
    }

    public void setTag(Object object) {
        this.mWrappedObject.setTag(object);
    }

    public void setTitle(int n2) {
        this.mWrappedObject.setTitle(n2);
    }

    public void setTitle(CharSequence charSequence) {
        this.mWrappedObject.setTitle(charSequence);
    }

    public void setTitleOptionalHint(boolean bl2) {
        this.mWrappedObject.setTitleOptionalHint(bl2);
    }

    public static class CallbackWrapper
    implements ActionMode.Callback {
        final ArrayList<SupportActionModeWrapper> mActionModes;
        final Context mContext;
        final SimpleArrayMap<Menu, Menu> mMenus;
        final ActionMode.Callback mWrappedCallback;

        public CallbackWrapper(Context context, ActionMode.Callback callback) {
            this.mContext = context;
            this.mWrappedCallback = callback;
            this.mActionModes = new ArrayList();
            this.mMenus = new SimpleArrayMap();
        }

        private ActionMode getActionModeWrapper(android.support.v7.view.ActionMode object) {
            int n2 = this.mActionModes.size();
            for (int i2 = 0; i2 < n2; ++i2) {
                SupportActionModeWrapper supportActionModeWrapper = (SupportActionModeWrapper)((Object)this.mActionModes.get(i2));
                if (supportActionModeWrapper == null || supportActionModeWrapper.mWrappedObject != object) continue;
                return supportActionModeWrapper;
            }
            object = new SupportActionModeWrapper(this.mContext, (android.support.v7.view.ActionMode)object);
            this.mActionModes.add(object);
            return object;
        }

        private Menu getMenuWrapper(Menu menu) {
            Menu menu2;
            Menu menu3 = menu2 = this.mMenus.get((Object)menu);
            if (menu2 == null) {
                menu3 = MenuWrapperFactory.wrapSupportMenu(this.mContext, (SupportMenu)menu);
                this.mMenus.put(menu, menu3);
            }
            return menu3;
        }

        public void addActionModeWrapper(SupportActionModeWrapper supportActionModeWrapper) {
            this.mActionModes.add((Object)supportActionModeWrapper);
        }

        @Override
        public boolean onActionItemClicked(android.support.v7.view.ActionMode actionMode, MenuItem menuItem) {
            return this.mWrappedCallback.onActionItemClicked(this.getActionModeWrapper(actionMode), MenuWrapperFactory.wrapSupportMenuItem(this.mContext, (SupportMenuItem)menuItem));
        }

        @Override
        public boolean onCreateActionMode(android.support.v7.view.ActionMode actionMode, Menu menu) {
            return this.mWrappedCallback.onCreateActionMode(this.getActionModeWrapper(actionMode), this.getMenuWrapper(menu));
        }

        @Override
        public void onDestroyActionMode(android.support.v7.view.ActionMode actionMode) {
            this.mWrappedCallback.onDestroyActionMode(this.getActionModeWrapper(actionMode));
        }

        @Override
        public boolean onPrepareActionMode(android.support.v7.view.ActionMode actionMode, Menu menu) {
            return this.mWrappedCallback.onPrepareActionMode(this.getActionModeWrapper(actionMode), this.getMenuWrapper(menu));
        }
    }

}

