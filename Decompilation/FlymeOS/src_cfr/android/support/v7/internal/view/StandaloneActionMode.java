/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v7.internal.view;

import android.content.Context;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuPopupHelper;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.support.v7.internal.widget.ActionBarContextView;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.lang.ref.WeakReference;

public class StandaloneActionMode
extends ActionMode
implements MenuBuilder.Callback {
    private ActionMode.Callback mCallback;
    private Context mContext;
    private ActionBarContextView mContextView;
    private WeakReference<View> mCustomView;
    private boolean mFinished;
    private boolean mFocusable;
    private MenuBuilder mMenu;

    public StandaloneActionMode(Context context, ActionBarContextView actionBarContextView, ActionMode.Callback callback, boolean bl2) {
        this.mContext = context;
        this.mContextView = actionBarContextView;
        this.mCallback = callback;
        this.mMenu = new MenuBuilder(actionBarContextView.getContext()).setDefaultShowAsAction(1);
        this.mMenu.setCallback(this);
        this.mFocusable = bl2;
    }

    @Override
    public void finish() {
        if (this.mFinished) {
            return;
        }
        this.mFinished = true;
        this.mContextView.sendAccessibilityEvent(32);
        this.mCallback.onDestroyActionMode(this);
    }

    @Override
    public View getCustomView() {
        if (this.mCustomView != null) {
            return this.mCustomView.get();
        }
        return null;
    }

    @Override
    public Menu getMenu() {
        return this.mMenu;
    }

    @Override
    public MenuInflater getMenuInflater() {
        return new MenuInflater(this.mContextView.getContext());
    }

    @Override
    public CharSequence getSubtitle() {
        return this.mContextView.getSubtitle();
    }

    @Override
    public CharSequence getTitle() {
        return this.mContextView.getTitle();
    }

    @Override
    public void invalidate() {
        this.mCallback.onPrepareActionMode(this, this.mMenu);
    }

    @Override
    public boolean isTitleOptional() {
        return this.mContextView.isTitleOptional();
    }

    @Override
    public boolean isUiFocusable() {
        return this.mFocusable;
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean bl2) {
    }

    public void onCloseSubMenu(SubMenuBuilder subMenuBuilder) {
    }

    @Override
    public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        return this.mCallback.onActionItemClicked(this, menuItem);
    }

    @Override
    public void onMenuModeChange(MenuBuilder menuBuilder) {
        this.invalidate();
        this.mContextView.showOverflowMenu();
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        if (!subMenuBuilder.hasVisibleItems()) {
            return true;
        }
        new MenuPopupHelper(this.mContextView.getContext(), subMenuBuilder).show();
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setCustomView(View weakReference) {
        this.mContextView.setCustomView((View)weakReference);
        weakReference = weakReference != null ? new WeakReference<WeakReference<Object>>(weakReference) : null;
        this.mCustomView = weakReference;
    }

    @Override
    public void setSubtitle(int n2) {
        this.setSubtitle(this.mContext.getString(n2));
    }

    @Override
    public void setSubtitle(CharSequence charSequence) {
        this.mContextView.setSubtitle(charSequence);
    }

    @Override
    public void setTitle(int n2) {
        this.setTitle(this.mContext.getString(n2));
    }

    @Override
    public void setTitle(CharSequence charSequence) {
        this.mContextView.setTitle(charSequence);
    }

    @Override
    public void setTitleOptionalHint(boolean bl2) {
        super.setTitleOptionalHint(bl2);
        this.mContextView.setTitleOptional(bl2);
    }
}

