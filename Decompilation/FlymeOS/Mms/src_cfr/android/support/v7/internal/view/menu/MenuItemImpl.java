/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ActivityNotFoundException
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.ColorStateList
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.util.Log
 *  android.view.ActionProvider
 *  android.view.ContextMenu
 *  android.view.ContextMenu$ContextMenuInfo
 *  android.view.LayoutInflater
 *  android.view.MenuItem
 *  android.view.MenuItem$OnActionExpandListener
 *  android.view.MenuItem$OnMenuItemClickListener
 *  android.view.SubMenu
 *  android.view.View
 *  android.view.ViewDebug
 *  android.view.ViewDebug$CapturedViewProperty
 *  android.view.ViewGroup
 *  android.widget.LinearLayout
 *  java.lang.Character
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package android.support.v7.internal.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuItemImpl$1;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.support.v7.internal.widget.TintManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public final class MenuItemImpl
implements SupportMenuItem {
    private static final int CHECKABLE = 1;
    private static final int CHECKED = 2;
    private static final int ENABLED = 16;
    private static final int EXCLUSIVE = 4;
    private static final int HIDDEN = 8;
    private static final int IS_ACTION = 32;
    static final int NO_ICON = 0;
    private static final int SHOW_AS_ACTION_MASK = 3;
    private static final String TAG = "MenuItemImpl";
    private static String sDeleteShortcutLabel;
    private static String sEnterShortcutLabel;
    private static String sPrependShortcutLabel;
    private static String sSpaceShortcutLabel;
    private ActionProvider mActionProvider;
    private View mActionView;
    private final int mCategoryOrder;
    private MenuItem.OnMenuItemClickListener mClickListener;
    private int mFlags = 16;
    private final int mGroup;
    private Drawable mIconDrawable;
    private int mIconResId = 0;
    private final int mId;
    private Intent mIntent;
    private boolean mIsActionViewExpanded = false;
    private boolean mIsOverflowActor;
    private Runnable mItemCallback;
    private boolean mLetMenuSlideOut = true;
    private MenuBuilder mMenu;
    private ContextMenu.ContextMenuInfo mMenuInfo;
    private MenuItemCompat.OnActionExpandListener mOnActionExpandListener;
    private final int mOrdering;
    private char mShortcutAlphabeticChar;
    private char mShortcutNumericChar;
    private int mShowAsAction = 0;
    private SubMenuBuilder mSubMenu;
    private CharSequence mTitle;
    private ColorStateList mTitleColor;
    private CharSequence mTitleCondensed;

    MenuItemImpl(MenuBuilder menuBuilder, int n2, int n3, int n4, int n5, CharSequence charSequence, int n6) {
        this.mMenu = menuBuilder;
        this.mId = n3;
        this.mGroup = n2;
        this.mCategoryOrder = n4;
        this.mOrdering = n5;
        this.mTitle = charSequence;
        this.mShowAsAction = n6;
    }

    static /* synthetic */ MenuBuilder access$000(MenuItemImpl menuItemImpl) {
        return menuItemImpl.mMenu;
    }

    public void actionFormatChanged() {
        this.mMenu.onItemActionRequestChanged(this);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public boolean collapseActionView() {
        if ((this.mShowAsAction & 8) == 0) {
            return false;
        }
        if (this.mActionView == null) {
            return true;
        }
        if (this.mOnActionExpandListener == null) return this.mMenu.collapseItemActionView(this);
        if (!this.mOnActionExpandListener.onMenuItemActionCollapse(this)) return false;
        return this.mMenu.collapseItemActionView(this);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public boolean expandActionView() {
        if (!this.hasCollapsibleActionView() || this.mOnActionExpandListener != null && !this.mOnActionExpandListener.onMenuItemActionExpand(this)) {
            return false;
        }
        return this.mMenu.expandItemActionView(this);
    }

    public android.view.ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    @Override
    public View getActionView() {
        if (this.mActionView != null) {
            return this.mActionView;
        }
        if (this.mActionProvider != null) {
            this.mActionView = this.mActionProvider.onCreateActionView(this);
            return this.mActionView;
        }
        return null;
    }

    public char getAlphabeticShortcut() {
        return this.mShortcutAlphabeticChar;
    }

    Runnable getCallback() {
        return this.mItemCallback;
    }

    public int getGroupId() {
        return this.mGroup;
    }

    public Drawable getIcon() {
        if (this.mIconDrawable != null) {
            return this.mIconDrawable;
        }
        if (this.mIconResId != 0) {
            Drawable drawable2 = TintManager.getDrawable(this.mMenu.getContext(), this.mIconResId);
            this.mIconResId = 0;
            this.mIconDrawable = drawable2;
            return drawable2;
        }
        return null;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    @ViewDebug.CapturedViewProperty
    public int getItemId() {
        return this.mId;
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.mMenuInfo;
    }

    public char getNumericShortcut() {
        return this.mShortcutNumericChar;
    }

    public int getOrder() {
        return this.mCategoryOrder;
    }

    public int getOrdering() {
        return this.mOrdering;
    }

    char getShortcut() {
        if (this.mMenu.isQwertyMode()) {
            return this.mShortcutAlphabeticChar;
        }
        return this.mShortcutNumericChar;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    String getShortcutLabel() {
        char c2 = this.getShortcut();
        if (c2 == '\u0000') {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(sPrependShortcutLabel);
        switch (c2) {
            default: {
                stringBuilder.append(c2);
                do {
                    return stringBuilder.toString();
                    break;
                } while (true);
            }
            case '\n': {
                stringBuilder.append(sEnterShortcutLabel);
                return stringBuilder.toString();
            }
            case '\b': {
                stringBuilder.append(sDeleteShortcutLabel);
                return stringBuilder.toString();
            }
            case ' ': 
        }
        stringBuilder.append(sSpaceShortcutLabel);
        return stringBuilder.toString();
    }

    public SubMenu getSubMenu() {
        return this.mSubMenu;
    }

    @Override
    public ActionProvider getSupportActionProvider() {
        return this.mActionProvider;
    }

    @ViewDebug.CapturedViewProperty
    public CharSequence getTitle() {
        return this.mTitle;
    }

    public ColorStateList getTitleColor() {
        return this.mTitleColor;
    }

    /*
     * Enabled aggressive block sorting
     */
    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.mTitleCondensed != null ? this.mTitleCondensed : this.mTitle;
        CharSequence charSequence2 = charSequence;
        if (Build.VERSION.SDK_INT >= 18) return charSequence2;
        charSequence2 = charSequence;
        if (charSequence == null) return charSequence2;
        charSequence2 = charSequence;
        if (charSequence instanceof String) return charSequence2;
        return charSequence.toString();
    }

    CharSequence getTitleForItemView(MenuView.ItemView itemView) {
        if (itemView != null && itemView.prefersCondensedTitle()) {
            return this.getTitleCondensed();
        }
        return this.getTitle();
    }

    public boolean hasCollapsibleActionView() {
        boolean bl2;
        boolean bl3 = bl2 = false;
        if ((this.mShowAsAction & 8) != 0) {
            if (this.mActionView == null && this.mActionProvider != null) {
                this.mActionView = this.mActionProvider.onCreateActionView(this);
            }
            bl3 = bl2;
            if (this.mActionView != null) {
                bl3 = true;
            }
        }
        return bl3;
    }

    public boolean hasSubMenu() {
        if (this.mSubMenu != null) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public boolean invoke() {
        if (this.mClickListener != null && this.mClickListener.onMenuItemClick((MenuItem)this)) {
            return true;
        }
        if (this.mMenu.dispatchMenuItemSelected(this.mMenu.getRootMenu(), this)) return true;
        if (this.mItemCallback != null) {
            this.mItemCallback.run();
            return true;
        }
        if (this.mIntent != null) {
            try {
                this.mMenu.getContext().startActivity(this.mIntent);
                return true;
            }
            catch (ActivityNotFoundException var1_1) {
                Log.e((String)"MenuItemImpl", (String)"Can't find activity to handle intent; ignoring", (Throwable)var1_1);
            }
        }
        if (this.mActionProvider == null) return false;
        if (this.mActionProvider.onPerformDefaultAction()) return true;
        return false;
    }

    public boolean isActionButton() {
        if ((this.mFlags & 32) == 32) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isActionViewExpanded() {
        return this.mIsActionViewExpanded;
    }

    public boolean isCheckable() {
        if ((this.mFlags & 1) == 1) {
            return true;
        }
        return false;
    }

    public boolean isChecked() {
        if ((this.mFlags & 2) == 2) {
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

    public boolean isExclusiveCheckable() {
        if ((this.mFlags & 4) != 0) {
            return true;
        }
        return false;
    }

    public boolean isLetMenuSlideOut() {
        return this.mLetMenuSlideOut;
    }

    public boolean isOverflowActor() {
        return this.mIsOverflowActor;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean isVisible() {
        if (this.mActionProvider != null && this.mActionProvider.overridesItemVisibility()) {
            if ((this.mFlags & 8) == 0 && this.mActionProvider.isVisible()) return true;
            return false;
        }
        if ((this.mFlags & 8) != 0) return false;
        return true;
    }

    public boolean requestsActionButton() {
        if ((this.mShowAsAction & 1) == 1) {
            return true;
        }
        return false;
    }

    public boolean requiresActionButton() {
        if ((this.mShowAsAction & 2) == 2) {
            return true;
        }
        return false;
    }

    public MenuItem setActionProvider(android.view.ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    @Override
    public SupportMenuItem setActionView(int n2) {
        Context context = this.mMenu.getContext();
        this.setActionView(LayoutInflater.from((Context)context).inflate(n2, (ViewGroup)new LinearLayout(context), false));
        return this;
    }

    @Override
    public SupportMenuItem setActionView(View view) {
        this.mActionView = view;
        this.mActionProvider = null;
        if (view != null && view.getId() == -1 && this.mId > 0) {
            view.setId(this.mId);
        }
        this.mMenu.onItemActionRequestChanged(this);
        return this;
    }

    public void setActionViewExpanded(boolean bl2) {
        this.mIsActionViewExpanded = bl2;
        this.mMenu.onItemsChanged(false);
    }

    public MenuItem setAlphabeticShortcut(char c2) {
        if (this.mShortcutAlphabeticChar == c2) {
            return this;
        }
        this.mShortcutAlphabeticChar = Character.toLowerCase((char)c2);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setCallback(Runnable runnable) {
        this.mItemCallback = runnable;
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public MenuItem setCheckable(boolean bl2) {
        int n2 = this.mFlags;
        int n3 = this.mFlags;
        int n4 = bl2 ? 1 : 0;
        this.mFlags = n4 | n3 & -2;
        if (n2 != this.mFlags) {
            this.mMenu.onItemsChanged(false);
        }
        return this;
    }

    public MenuItem setChecked(boolean bl2) {
        if ((this.mFlags & 4) != 0) {
            this.mMenu.setExclusiveItemChecked(this);
            return this;
        }
        this.setCheckedInt(bl2);
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    void setCheckedInt(boolean bl2) {
        int n2 = this.mFlags;
        int n3 = this.mFlags;
        int n4 = bl2 ? 2 : 0;
        this.mFlags = n4 | n3 & -3;
        if (n2 != this.mFlags) {
            this.mMenu.onItemsChanged(false);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public MenuItem setEnabled(boolean bl2) {
        this.mFlags = bl2 ? (this.mFlags |= 16) : (this.mFlags &= -17);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setExclusiveCheckable(boolean bl2) {
        int n2 = this.mFlags;
        int n3 = bl2 ? 4 : 0;
        this.mFlags = n3 | n2 & -5;
    }

    public MenuItem setIcon(int n2) {
        this.mIconDrawable = null;
        this.mIconResId = n2;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIcon(Drawable drawable2) {
        this.mIconResId = 0;
        this.mIconDrawable = drawable2;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIntent(Intent intent) {
        this.mIntent = intent;
        return this;
    }

    public void setIsActionButton(boolean bl2) {
        if (bl2) {
            this.mFlags |= 32;
            return;
        }
        this.mFlags &= -33;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public MenuItem setIsOverflowActor(boolean bl2) {
        if (this.mIsOverflowActor == bl2) return this;
        boolean bl3 = true;
        if (!bl3) return this;
        this.mIsOverflowActor = bl2;
        this.mMenu.onItemsChanged(true);
        return this;
    }

    public MenuItem setLetMenuSlideOut(boolean bl2) {
        this.mLetMenuSlideOut = bl2;
        return this;
    }

    void setMenuInfo(ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.mMenuInfo = contextMenuInfo;
    }

    public MenuItem setNumericShortcut(char c2) {
        if (this.mShortcutNumericChar == c2) {
            return this;
        }
        this.mShortcutNumericChar = c2;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setOnActionExpandListener()");
    }

    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.mClickListener = onMenuItemClickListener;
        return this;
    }

    public MenuItem setShortcut(char c2, char c3) {
        this.mShortcutNumericChar = c2;
        this.mShortcutAlphabeticChar = Character.toLowerCase((char)c3);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override
    public void setShowAsAction(int n2) {
        switch (n2 & 3) {
            default: {
                throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
            }
            case 0: 
            case 1: 
            case 2: 
        }
        this.mShowAsAction = n2;
        this.mMenu.onItemActionRequestChanged(this);
    }

    @Override
    public SupportMenuItem setShowAsActionFlags(int n2) {
        this.setShowAsAction(n2);
        return this;
    }

    void setSubMenu(SubMenuBuilder subMenuBuilder) {
        this.mSubMenu = subMenuBuilder;
        subMenuBuilder.setHeaderTitle(this.getTitle());
    }

    @Override
    public SupportMenuItem setSupportActionProvider(ActionProvider actionProvider) {
        if (this.mActionProvider != null) {
            this.mActionProvider.setVisibilityListener(null);
        }
        this.mActionView = null;
        this.mActionProvider = actionProvider;
        this.mMenu.onItemsChanged(true);
        if (this.mActionProvider != null) {
            this.mActionProvider.setVisibilityListener(new MenuItemImpl$1(this));
        }
        return this;
    }

    @Override
    public SupportMenuItem setSupportOnActionExpandListener(MenuItemCompat.OnActionExpandListener onActionExpandListener) {
        this.mOnActionExpandListener = onActionExpandListener;
        return this;
    }

    public MenuItem setTitle(int n2) {
        return this.setTitle(this.mMenu.getContext().getString(n2));
    }

    public MenuItem setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        this.mMenu.onItemsChanged(false);
        if (this.mSubMenu != null) {
            this.mSubMenu.setHeaderTitle(charSequence);
        }
        return this;
    }

    public MenuItem setTitleColor(ColorStateList colorStateList) {
        this.mTitleColor = colorStateList;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.mTitleCondensed = charSequence;
        if (charSequence == null) {
            charSequence = this.mTitle;
        }
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setVisible(boolean bl2) {
        if (this.setVisibleInt(bl2)) {
            this.mMenu.onItemVisibleChanged(this);
        }
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    boolean setVisibleInt(boolean bl2) {
        boolean bl3 = false;
        int n2 = this.mFlags;
        int n3 = this.mFlags;
        int n4 = bl2 ? 0 : 8;
        this.mFlags = n4 | n3 & -9;
        bl2 = bl3;
        if (n2 == this.mFlags) return bl2;
        return true;
    }

    public boolean shouldShowIcon() {
        return this.mMenu.getOptionalIconsVisible();
    }

    boolean shouldShowShortcut() {
        if (this.mMenu.isShortcutsVisible() && this.getShortcut() != '\u0000') {
            return true;
        }
        return false;
    }

    public boolean showsTextAsAction() {
        if ((this.mShowAsAction & 4) == 4) {
            return true;
        }
        return false;
    }

    public String toString() {
        return this.mTitle.toString();
    }
}

