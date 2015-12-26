/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.view.ActionProvider
 *  android.view.ContextMenu
 *  android.view.ContextMenu$ContextMenuInfo
 *  android.view.MenuItem
 *  android.view.MenuItem$OnActionExpandListener
 *  android.view.MenuItem$OnMenuItemClickListener
 *  android.view.SubMenu
 *  android.view.View
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v7.internal.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public class ActionMenuItem
implements SupportMenuItem {
    private static final int CHECKABLE = 1;
    private static final int CHECKED = 2;
    private static final int ENABLED = 16;
    private static final int EXCLUSIVE = 4;
    private static final int HIDDEN = 8;
    private static final int NO_ICON = 0;
    private final int mCategoryOrder;
    private MenuItem.OnMenuItemClickListener mClickListener;
    private Context mContext;
    private int mFlags = 16;
    private final int mGroup;
    private Drawable mIconDrawable;
    private int mIconResId = 0;
    private final int mId;
    private Intent mIntent;
    private final int mOrdering;
    private char mShortcutAlphabeticChar;
    private char mShortcutNumericChar;
    private CharSequence mTitle;
    private CharSequence mTitleCondensed;

    public ActionMenuItem(Context context, int n2, int n3, int n4, int n5, CharSequence charSequence) {
        this.mContext = context;
        this.mId = n3;
        this.mGroup = n2;
        this.mCategoryOrder = n4;
        this.mOrdering = n5;
        this.mTitle = charSequence;
    }

    @Override
    public boolean collapseActionView() {
        return false;
    }

    @Override
    public boolean expandActionView() {
        return false;
    }

    public android.view.ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override
    public View getActionView() {
        return null;
    }

    public char getAlphabeticShortcut() {
        return this.mShortcutAlphabeticChar;
    }

    public int getGroupId() {
        return this.mGroup;
    }

    public Drawable getIcon() {
        return this.mIconDrawable;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    public int getItemId() {
        return this.mId;
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    public char getNumericShortcut() {
        return this.mShortcutNumericChar;
    }

    public int getOrder() {
        return this.mOrdering;
    }

    public SubMenu getSubMenu() {
        return null;
    }

    @Override
    public ActionProvider getSupportActionProvider() {
        return null;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public CharSequence getTitleCondensed() {
        if (this.mTitleCondensed != null) {
            return this.mTitleCondensed;
        }
        return this.mTitle;
    }

    public boolean hasSubMenu() {
        return false;
    }

    public boolean invoke() {
        if (this.mClickListener != null && this.mClickListener.onMenuItemClick((MenuItem)this)) {
            return true;
        }
        if (this.mIntent != null) {
            this.mContext.startActivity(this.mIntent);
            return true;
        }
        return false;
    }

    @Override
    public boolean isActionViewExpanded() {
        return false;
    }

    public boolean isCheckable() {
        if ((this.mFlags & 1) != 0) {
            return true;
        }
        return false;
    }

    public boolean isChecked() {
        if ((this.mFlags & 2) != 0) {
            return true;
        }
        return false;
    }

    public boolean isEnabled() {
        if ((this.mFlags & 16) != 0) {
            return true;
        }
        return false;
    }

    public boolean isVisible() {
        if ((this.mFlags & 8) == 0) {
            return true;
        }
        return false;
    }

    public MenuItem setActionProvider(android.view.ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SupportMenuItem setActionView(int n2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SupportMenuItem setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setAlphabeticShortcut(char c2) {
        this.mShortcutAlphabeticChar = c2;
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public MenuItem setCheckable(boolean bl2) {
        int n2 = this.mFlags;
        int n3 = bl2 ? 1 : 0;
        this.mFlags = n3 | n2 & -2;
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public MenuItem setChecked(boolean bl2) {
        int n2 = this.mFlags;
        int n3 = bl2 ? 2 : 0;
        this.mFlags = n3 | n2 & -3;
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public MenuItem setEnabled(boolean bl2) {
        int n2 = this.mFlags;
        int n3 = bl2 ? 16 : 0;
        this.mFlags = n3 | n2 & -17;
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public ActionMenuItem setExclusiveCheckable(boolean bl2) {
        int n2 = this.mFlags;
        int n3 = bl2 ? 4 : 0;
        this.mFlags = n3 | n2 & -5;
        return this;
    }

    public MenuItem setIcon(int n2) {
        this.mIconResId = n2;
        this.mIconDrawable = ContextCompat.getDrawable(this.mContext, n2);
        return this;
    }

    public MenuItem setIcon(Drawable drawable2) {
        this.mIconDrawable = drawable2;
        this.mIconResId = 0;
        return this;
    }

    public MenuItem setIntent(Intent intent) {
        this.mIntent = intent;
        return this;
    }

    public MenuItem setNumericShortcut(char c2) {
        this.mShortcutNumericChar = c2;
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.mClickListener = onMenuItemClickListener;
        return this;
    }

    public MenuItem setShortcut(char c2, char c3) {
        this.mShortcutNumericChar = c2;
        this.mShortcutAlphabeticChar = c3;
        return this;
    }

    @Override
    public void setShowAsAction(int n2) {
    }

    @Override
    public SupportMenuItem setShowAsActionFlags(int n2) {
        this.setShowAsAction(n2);
        return this;
    }

    @Override
    public SupportMenuItem setSupportActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SupportMenuItem setSupportOnActionExpandListener(MenuItemCompat.OnActionExpandListener onActionExpandListener) {
        return this;
    }

    public MenuItem setTitle(int n2) {
        this.mTitle = this.mContext.getResources().getString(n2);
        return this;
    }

    public MenuItem setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.mTitleCondensed = charSequence;
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public MenuItem setVisible(boolean bl2) {
        int n2 = this.mFlags;
        int n3 = bl2 ? 0 : 8;
        this.mFlags = n3 | n2 & 8;
        return this;
    }
}

