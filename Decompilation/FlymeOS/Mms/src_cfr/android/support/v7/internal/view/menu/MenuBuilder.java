/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.ActivityInfo
 *  android.content.pm.ApplicationInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.ResolveInfo
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.util.SparseArray
 *  android.view.ContextMenu
 *  android.view.ContextMenu$ContextMenuInfo
 *  android.view.KeyCharacterMap
 *  android.view.KeyCharacterMap$KeyData
 *  android.view.KeyEvent
 *  android.view.MenuItem
 *  android.view.SubMenu
 *  android.view.View
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.concurrent.CopyOnWriteArrayList
 */
package android.support.v7.internal.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.support.v7.widget.ActionMenuPresenter;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MenuBuilder
implements SupportMenu {
    private static final String ACTION_VIEW_STATES_KEY = "android:menu:actionviewstates";
    private static final String EXPANDED_ACTION_VIEW_ID = "android:menu:expandedactionview";
    private static final String PRESENTER_KEY = "android:menu:presenters";
    private static final String TAG = "MenuBuilder";
    private static final int[] sCategoryToOrder = new int[]{1, 4, 5, 3, 2, 0};
    private ArrayList<MenuItemImpl> mActionItems;
    private Callback mCallback;
    private final Context mContext;
    private ContextMenu.ContextMenuInfo mCurrentMenuInfo;
    private int mDefaultShowAsAction = 0;
    private MenuItemImpl mExpandedItem;
    private SparseArray<Parcelable> mFrozenViewStates;
    Drawable mHeaderIcon;
    CharSequence mHeaderTitle;
    View mHeaderView;
    private boolean mIsActionItemsStale;
    private boolean mIsClosing = false;
    private boolean mIsVisibleItemsStale;
    private ArrayList<MenuItemImpl> mItems;
    private boolean mItemsChangedWhileDispatchPrevented = false;
    private ArrayList<MenuItemImpl> mNonActionItems;
    private boolean mOptionalIconsVisible = false;
    private boolean mOverrideVisibleItems;
    private CopyOnWriteArrayList<WeakReference<MenuPresenter>> mPresenters = new CopyOnWriteArrayList();
    private boolean mPreventDispatchingItemsChanged = false;
    private boolean mQwertyMode;
    private final Resources mResources;
    private boolean mShortcutsVisible;
    private ArrayList<MenuItemImpl> mTempShortcutItemList = new ArrayList();
    private ArrayList<MenuItemImpl> mVisibleItems;

    public MenuBuilder(Context context) {
        this.mContext = context;
        this.mResources = context.getResources();
        this.mItems = new ArrayList();
        this.mVisibleItems = new ArrayList();
        this.mIsVisibleItemsStale = true;
        this.mActionItems = new ArrayList();
        this.mNonActionItems = new ArrayList();
        this.mIsActionItemsStale = true;
        this.setShortcutsVisibleInner(true);
    }

    private MenuItem addInternal(int n2, int n3, int n4, CharSequence object) {
        int n5 = MenuBuilder.getOrdering(n4);
        object = this.createNewMenuItem(n2, n3, n4, n5, (CharSequence)object, this.mDefaultShowAsAction);
        if (this.mCurrentMenuInfo != null) {
            object.setMenuInfo(this.mCurrentMenuInfo);
        }
        this.mItems.add(MenuBuilder.findInsertIndex(this.mItems, n5), object);
        this.onItemsChanged(true);
        return object;
    }

    private MenuItemImpl createNewMenuItem(int n2, int n3, int n4, int n5, CharSequence charSequence, int n6) {
        return new MenuItemImpl(this, n2, n3, n4, n5, charSequence, n6);
    }

    private void dispatchPresenterUpdate(boolean bl2) {
        if (this.mPresenters.isEmpty()) {
            return;
        }
        this.stopDispatchingItemsChanged();
        for (WeakReference weakReference : this.mPresenters) {
            MenuPresenter menuPresenter = (MenuPresenter)weakReference.get();
            if (menuPresenter == null) {
                this.mPresenters.remove((Object)weakReference);
                continue;
            }
            menuPresenter.updateMenuView(bl2);
        }
        this.startDispatchingItemsChanged();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void dispatchRestoreInstanceState(Bundle bundle) {
        if ((bundle = bundle.getSparseParcelableArray("android:menu:presenters")) != null && !this.mPresenters.isEmpty()) {
            for (WeakReference weakReference : this.mPresenters) {
                MenuPresenter menuPresenter = (MenuPresenter)weakReference.get();
                if (menuPresenter == null) {
                    this.mPresenters.remove((Object)weakReference);
                    continue;
                }
                int n2 = menuPresenter.getId();
                if (n2 <= 0 || (weakReference = (Parcelable)bundle.get(n2)) == null) continue;
                menuPresenter.onRestoreInstanceState((Parcelable)weakReference);
            }
        }
    }

    private void dispatchSaveInstanceState(Bundle bundle) {
        if (this.mPresenters.isEmpty()) {
            return;
        }
        SparseArray sparseArray = new SparseArray();
        for (WeakReference weakReference : this.mPresenters) {
            MenuPresenter menuPresenter = (MenuPresenter)weakReference.get();
            if (menuPresenter == null) {
                this.mPresenters.remove((Object)weakReference);
                continue;
            }
            int n2 = menuPresenter.getId();
            if (n2 <= 0 || (weakReference = menuPresenter.onSaveInstanceState()) == null) continue;
            sparseArray.put(n2, (Object)weakReference);
        }
        bundle.putSparseParcelableArray("android:menu:presenters", sparseArray);
    }

    private boolean dispatchSubMenuSelected(SubMenuBuilder subMenuBuilder, MenuPresenter object) {
        boolean bl2 = false;
        if (this.mPresenters.isEmpty()) {
            return false;
        }
        if (object != null) {
            bl2 = object.onSubMenuSelected(subMenuBuilder);
        }
        for (WeakReference weakReference : this.mPresenters) {
            MenuPresenter menuPresenter = (MenuPresenter)weakReference.get();
            if (menuPresenter == null) {
                this.mPresenters.remove((Object)weakReference);
                continue;
            }
            if (bl2) continue;
            bl2 = menuPresenter.onSubMenuSelected(subMenuBuilder);
        }
        return bl2;
    }

    private static int findInsertIndex(ArrayList<MenuItemImpl> arrayList, int n2) {
        for (int i2 = arrayList.size() - 1; i2 >= 0; --i2) {
            if (((MenuItemImpl)arrayList.get(i2)).getOrdering() > n2) continue;
            return i2 + 1;
        }
        return 0;
    }

    private static int getOrdering(int n2) {
        int n3 = (-65536 & n2) >> 16;
        if (n3 < 0 || n3 >= sCategoryToOrder.length) {
            throw new IllegalArgumentException("order does not contain a valid category.");
        }
        return sCategoryToOrder[n3] << 16 | 65535 & n2;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void removeItemAtInt(int n2, boolean bl2) {
        if (n2 < 0) return;
        if (n2 >= this.mItems.size()) {
            return;
        }
        this.mItems.remove(n2);
        if (!bl2) return;
        this.onItemsChanged(true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setHeaderInternal(int n2, CharSequence charSequence, int n3, Drawable drawable2, View view) {
        Resources resources = this.getResources();
        if (view != null) {
            this.mHeaderView = view;
            this.mHeaderTitle = null;
            this.mHeaderIcon = null;
        } else {
            if (n2 > 0) {
                this.mHeaderTitle = resources.getText(n2);
            } else if (charSequence != null) {
                this.mHeaderTitle = charSequence;
            }
            if (n3 > 0) {
                this.mHeaderIcon = ContextCompat.getDrawable(this.getContext(), n3);
            } else if (drawable2 != null) {
                this.mHeaderIcon = drawable2;
            }
            this.mHeaderView = null;
        }
        this.onItemsChanged(false);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setShortcutsVisibleInner(boolean bl2) {
        boolean bl3 = true;
        bl2 = bl2 && this.mResources.getConfiguration().keyboard != 1 && this.mResources.getBoolean(R.bool.abc_config_showMenuShortcutsWhenKeyboardPresent) ? bl3 : false;
        this.mShortcutsVisible = bl2;
    }

    public MenuItem add(int n2) {
        return this.addInternal(0, 0, 0, this.mResources.getString(n2));
    }

    public MenuItem add(int n2, int n3, int n4, int n5) {
        return this.addInternal(n2, n3, n4, this.mResources.getString(n5));
    }

    public MenuItem add(int n2, int n3, int n4, CharSequence charSequence) {
        return this.addInternal(n2, n3, n4, charSequence);
    }

    public MenuItem add(CharSequence charSequence) {
        return this.addInternal(0, 0, 0, charSequence);
    }

    /*
     * Enabled aggressive block sorting
     */
    public int addIntentOptions(int n2, int n3, int n4, ComponentName componentName, Intent[] arrintent, Intent intent, int n5, MenuItem[] arrmenuItem) {
        PackageManager packageManager = this.mContext.getPackageManager();
        List list = packageManager.queryIntentActivityOptions(componentName, arrintent, intent, 0);
        int n6 = list != null ? list.size() : 0;
        if ((n5 & 1) == 0) {
            this.removeGroup(n2);
        }
        n5 = 0;
        while (n5 < n6) {
            ResolveInfo resolveInfo = (ResolveInfo)list.get(n5);
            componentName = resolveInfo.specificIndex < 0 ? intent : arrintent[resolveInfo.specificIndex];
            componentName = new Intent((Intent)componentName);
            componentName.setComponent(new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name));
            componentName = this.add(n2, n3, n4, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent((Intent)componentName);
            if (arrmenuItem != null && resolveInfo.specificIndex >= 0) {
                arrmenuItem[resolveInfo.specificIndex] = componentName;
            }
            ++n5;
        }
        return n6;
    }

    public void addMenuPresenter(MenuPresenter menuPresenter) {
        this.addMenuPresenter(menuPresenter, this.mContext);
    }

    public void addMenuPresenter(MenuPresenter menuPresenter, Context context) {
        this.mPresenters.add(new WeakReference<MenuPresenter>(menuPresenter));
        menuPresenter.initForMenu(context, this);
        this.mIsActionItemsStale = true;
    }

    public SubMenu addSubMenu(int n2) {
        return this.addSubMenu(0, 0, 0, this.mResources.getString(n2));
    }

    public SubMenu addSubMenu(int n2, int n3, int n4, int n5) {
        return this.addSubMenu(n2, n3, n4, this.mResources.getString(n5));
    }

    public SubMenu addSubMenu(int n2, int n3, int n4, CharSequence object) {
        object = (MenuItemImpl)this.addInternal(n2, n3, n4, (CharSequence)object);
        SubMenuBuilder subMenuBuilder = new SubMenuBuilder(this.mContext, this, (MenuItemImpl)object);
        object.setSubMenu(subMenuBuilder);
        return subMenuBuilder;
    }

    public SubMenu addSubMenu(CharSequence charSequence) {
        return this.addSubMenu(0, 0, 0, charSequence);
    }

    public void changeMenuMode() {
        if (this.mCallback != null) {
            this.mCallback.onMenuModeChange(this);
        }
    }

    public void clear() {
        if (this.mExpandedItem != null) {
            this.collapseItemActionView(this.mExpandedItem);
        }
        this.mItems.clear();
        this.onItemsChanged(true);
    }

    public void clearAll() {
        this.mPreventDispatchingItemsChanged = true;
        this.clear();
        this.clearHeader();
        this.mPreventDispatchingItemsChanged = false;
        this.mItemsChangedWhileDispatchPrevented = false;
        this.onItemsChanged(true);
    }

    public void clearHeader() {
        this.mHeaderIcon = null;
        this.mHeaderTitle = null;
        this.mHeaderView = null;
        this.onItemsChanged(false);
    }

    public void close() {
        this.close(true);
    }

    public final void close(boolean bl2) {
        this.close(bl2, null);
    }

    public final void close(boolean bl2, MenuItemImpl menuItemImpl) {
        if (this.mIsClosing) {
            return;
        }
        this.mIsClosing = true;
        for (WeakReference weakReference : this.mPresenters) {
            MenuPresenter menuPresenter = (MenuPresenter)weakReference.get();
            if (menuPresenter == null) {
                this.mPresenters.remove((Object)weakReference);
                continue;
            }
            if (menuPresenter instanceof ActionMenuPresenter && menuItemImpl != null) {
                ((ActionMenuPresenter)menuPresenter).onCloseMenu(this, bl2, menuItemImpl);
                continue;
            }
            menuPresenter.onCloseMenu(this, bl2);
        }
        this.mIsClosing = false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean collapseItemActionView(MenuItemImpl menuItemImpl) {
        boolean bl2;
        boolean bl3 = bl2 = false;
        if (this.mPresenters.isEmpty()) return bl3;
        if (this.mExpandedItem != menuItemImpl) {
            return bl2;
        }
        this.stopDispatchingItemsChanged();
        Iterator iterator = this.mPresenters.iterator();
        bl2 = false;
        while (iterator.hasNext()) {
            WeakReference weakReference = (WeakReference)iterator.next();
            MenuPresenter menuPresenter = (MenuPresenter)weakReference.get();
            if (menuPresenter == null) {
                this.mPresenters.remove((Object)weakReference);
                continue;
            }
            bl2 = bl3 = menuPresenter.collapseItemActionView(this, menuItemImpl);
            if (!bl3) continue;
            bl2 = bl3;
            break;
        }
        this.startDispatchingItemsChanged();
        bl3 = bl2;
        if (!bl2) return bl3;
        this.mExpandedItem = null;
        return bl2;
    }

    boolean dispatchMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        if (this.mCallback != null && this.mCallback.onMenuItemSelected(menuBuilder, menuItem)) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean expandItemActionView(MenuItemImpl menuItemImpl) {
        boolean bl2 = false;
        if (this.mPresenters.isEmpty()) {
            return bl2;
        }
        this.stopDispatchingItemsChanged();
        Iterator iterator = this.mPresenters.iterator();
        boolean bl3 = false;
        while (iterator.hasNext()) {
            WeakReference weakReference = (WeakReference)iterator.next();
            MenuPresenter menuPresenter = (MenuPresenter)weakReference.get();
            if (menuPresenter == null) {
                this.mPresenters.remove((Object)weakReference);
                continue;
            }
            bl3 = bl2 = menuPresenter.expandItemActionView(this, menuItemImpl);
            if (!bl2) continue;
            bl3 = bl2;
            break;
        }
        this.startDispatchingItemsChanged();
        bl2 = bl3;
        if (!bl3) return bl2;
        this.mExpandedItem = menuItemImpl;
        return bl3;
    }

    public int findGroupIndex(int n2) {
        return this.findGroupIndex(n2, 0);
    }

    public int findGroupIndex(int n2, int n3) {
        int n4 = this.size();
        int n5 = n3;
        if (n3 < 0) {
            n5 = 0;
        }
        while (n5 < n4) {
            if (((MenuItemImpl)this.mItems.get(n5)).getGroupId() == n2) {
                return n5;
            }
            ++n5;
        }
        return -1;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public MenuItem findItem(int n2) {
        int n3 = this.size();
        int n4 = 0;
        while (n4 < n3) {
            MenuItemImpl menuItemImpl = (MenuItemImpl)this.mItems.get(n4);
            if (menuItemImpl.getItemId() == n2) {
                return menuItemImpl;
            }
            if (menuItemImpl.hasSubMenu()) {
                MenuItem menuItem;
                menuItemImpl = menuItem = menuItemImpl.getSubMenu().findItem(n2);
                if (menuItem != null) return menuItemImpl;
            }
            ++n4;
        }
        return null;
    }

    public int findItemIndex(int n2) {
        int n3 = this.size();
        for (int i2 = 0; i2 < n3; ++i2) {
            if (((MenuItemImpl)this.mItems.get(i2)).getItemId() != n2) continue;
            return i2;
        }
        return -1;
    }

    /*
     * Enabled aggressive block sorting
     */
    MenuItemImpl findItemWithShortcutForKey(int n2, KeyEvent object) {
        ArrayList<MenuItemImpl> arrayList = this.mTempShortcutItemList;
        arrayList.clear();
        this.findItemsWithShortcutForKey((List<MenuItemImpl>)arrayList, n2, (KeyEvent)object);
        if (arrayList.isEmpty()) {
            return null;
        }
        int n3 = object.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        object.getKeyData(keyData);
        int n4 = arrayList.size();
        if (n4 == 1) {
            return (MenuItemImpl)arrayList.get(0);
        }
        boolean bl2 = this.isQwertyMode();
        int n5 = 0;
        while (n5 < n4) {
            MenuItemImpl menuItemImpl = (MenuItemImpl)arrayList.get(n5);
            char c2 = bl2 ? menuItemImpl.getAlphabeticShortcut() : menuItemImpl.getNumericShortcut();
            if (c2 == keyData.meta[0]) {
                object = menuItemImpl;
                if ((n3 & 2) == 0) return object;
            }
            if (c2 == keyData.meta[2]) {
                object = menuItemImpl;
                if ((n3 & 2) != 0) return object;
            }
            if (bl2 && c2 == '\b') {
                object = menuItemImpl;
                if (n2 == 67) return object;
            }
            ++n5;
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    void findItemsWithShortcutForKey(List<MenuItemImpl> list, int n2, KeyEvent keyEvent) {
        boolean bl2 = this.isQwertyMode();
        int n3 = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (!keyEvent.getKeyData(keyData) && n2 != 67) {
            return;
        }
        int n4 = this.mItems.size();
        int n5 = 0;
        while (n5 < n4) {
            MenuItemImpl menuItemImpl = (MenuItemImpl)this.mItems.get(n5);
            if (menuItemImpl.hasSubMenu()) {
                ((MenuBuilder)menuItemImpl.getSubMenu()).findItemsWithShortcutForKey(list, n2, keyEvent);
            }
            char c2 = bl2 ? menuItemImpl.getAlphabeticShortcut() : menuItemImpl.getNumericShortcut();
            if ((n3 & 5) == 0 && c2 != '\u0000' && (c2 == keyData.meta[0] || c2 == keyData.meta[2] || bl2 && c2 == '\b' && n2 == 67) && menuItemImpl.isEnabled()) {
                list.add(menuItemImpl);
            }
            ++n5;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void flagActionItems() {
        ArrayList<MenuItemImpl> arrayList = this.getVisibleItems();
        if (!this.mIsActionItemsStale) {
            return;
        }
        Iterator iterator = this.mPresenters.iterator();
        int n2 = 0;
        while (iterator.hasNext()) {
            WeakReference weakReference = (WeakReference)iterator.next();
            MenuPresenter menuPresenter = (MenuPresenter)weakReference.get();
            if (menuPresenter == null) {
                this.mPresenters.remove((Object)weakReference);
                continue;
            }
            n2 = menuPresenter.flagActionItems() | n2;
        }
        this.mActionItems.clear();
        this.mNonActionItems.clear();
        this.mNonActionItems.addAll(this.getVisibleItems());
        this.mIsActionItemsStale = false;
    }

    public ArrayList<MenuItemImpl> getActionItems() {
        this.flagActionItems();
        return this.mActionItems;
    }

    protected String getActionViewStatesKey() {
        return "android:menu:actionviewstates";
    }

    public Context getContext() {
        return this.mContext;
    }

    public MenuItemImpl getExpandedItem() {
        return this.mExpandedItem;
    }

    public Drawable getHeaderIcon() {
        return this.mHeaderIcon;
    }

    public CharSequence getHeaderTitle() {
        return this.mHeaderTitle;
    }

    public View getHeaderView() {
        return this.mHeaderView;
    }

    public MenuItem getItem(int n2) {
        return (MenuItem)this.mItems.get(n2);
    }

    public ArrayList<MenuItemImpl> getNonActionItems() {
        this.flagActionItems();
        return this.mNonActionItems;
    }

    boolean getOptionalIconsVisible() {
        return this.mOptionalIconsVisible;
    }

    Resources getResources() {
        return this.mResources;
    }

    public MenuBuilder getRootMenu() {
        return this;
    }

    public ArrayList<MenuItemImpl> getVisibleItems() {
        if (!this.mIsVisibleItemsStale) {
            return this.mVisibleItems;
        }
        this.mVisibleItems.clear();
        int n2 = this.mItems.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            MenuItemImpl menuItemImpl = (MenuItemImpl)this.mItems.get(i2);
            if (!menuItemImpl.isVisible()) continue;
            this.mVisibleItems.add((Object)menuItemImpl);
        }
        this.mIsVisibleItemsStale = false;
        this.mIsActionItemsStale = true;
        return this.mVisibleItems;
    }

    public boolean hasVisibleItems() {
        if (this.mOverrideVisibleItems) {
            return true;
        }
        int n2 = this.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            if (!((MenuItemImpl)this.mItems.get(i2)).isVisible()) continue;
            return true;
        }
        return false;
    }

    boolean isQwertyMode() {
        return this.mQwertyMode;
    }

    public boolean isShortcutKey(int n2, KeyEvent keyEvent) {
        if (this.findItemWithShortcutForKey(n2, keyEvent) != null) {
            return true;
        }
        return false;
    }

    public boolean isShortcutsVisible() {
        return this.mShortcutsVisible;
    }

    void onItemActionRequestChanged(MenuItemImpl menuItemImpl) {
        this.mIsActionItemsStale = true;
        this.onItemsChanged(true);
    }

    void onItemVisibleChanged(MenuItemImpl menuItemImpl) {
        this.mIsVisibleItemsStale = true;
        this.onItemsChanged(true);
    }

    public void onItemsChanged(boolean bl2) {
        if (!this.mPreventDispatchingItemsChanged) {
            if (bl2) {
                this.mIsVisibleItemsStale = true;
                this.mIsActionItemsStale = true;
            }
            this.dispatchPresenterUpdate(bl2);
            return;
        }
        this.mItemsChangedWhileDispatchPrevented = true;
    }

    public boolean performIdentifierAction(int n2, int n3) {
        return this.performItemAction(this.findItem(n2), n3);
    }

    public boolean performItemAction(MenuItem menuItem, int n2) {
        return this.performItemAction(menuItem, null, n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean performItemAction(MenuItem object, MenuPresenter menuPresenter, int n2) {
        boolean bl2 = false;
        Object object2 = (MenuItemImpl)object;
        boolean bl3 = bl2;
        if (object2 == null) return bl3;
        if (!object2.isEnabled()) {
            return bl2;
        }
        bl3 = object2.invoke();
        object = object2.getSupportActionProvider();
        boolean bl4 = object != null && object.hasSubMenu();
        if (object2.hasCollapsibleActionView()) {
            bl3 = bl2 = object2.expandActionView() | bl3;
            if (!bl2) return bl3;
            this.close(true);
            return bl2;
        }
        if (object2.hasSubMenu() || bl4) {
            this.close(false);
            if (!object2.hasSubMenu()) {
                object2.setSubMenu(new SubMenuBuilder(this.getContext(), this, (MenuItemImpl)object2));
            }
            object2 = (SubMenuBuilder)object2.getSubMenu();
            if (bl4) {
                object.onPrepareSubMenu((SubMenu)object2);
            }
            bl3 = bl2 = this.dispatchSubMenuSelected((SubMenuBuilder)object2, menuPresenter) | bl3;
            if (bl2) return bl3;
            this.close(true);
            return bl2;
        }
        if ((n2 & 1) != 0) return bl3;
        this.close(true, (MenuItemImpl)object2);
        return bl3;
    }

    public boolean performShortcut(int n2, KeyEvent object, int n3) {
        object = this.findItemWithShortcutForKey(n2, (KeyEvent)object);
        boolean bl2 = false;
        if (object != null) {
            bl2 = this.performItemAction((MenuItem)object, n3);
        }
        if ((n3 & 2) != 0) {
            this.close(true);
        }
        return bl2;
    }

    public void removeGroup(int n2) {
        int n3 = this.findGroupIndex(n2);
        if (n3 >= 0) {
            int n4 = this.mItems.size();
            for (int i2 = 0; i2 < n4 - n3 && ((MenuItemImpl)this.mItems.get(n3)).getGroupId() == n2; ++i2) {
                this.removeItemAtInt(n3, false);
            }
            this.onItemsChanged(true);
        }
    }

    public void removeItem(int n2) {
        this.removeItemAtInt(this.findItemIndex(n2), true);
    }

    public void removeItemAt(int n2) {
        this.removeItemAtInt(n2, true);
    }

    public void removeMenuPresenter(MenuPresenter menuPresenter) {
        for (WeakReference weakReference : this.mPresenters) {
            MenuPresenter menuPresenter2 = (MenuPresenter)weakReference.get();
            if (menuPresenter2 != null && menuPresenter2 != menuPresenter) continue;
            this.mPresenters.remove((Object)weakReference);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void restoreActionViewStates(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        SparseArray sparseArray = bundle.getSparseParcelableArray(this.getActionViewStatesKey());
        int n2 = this.size();
        int n3 = 0;
        do {
            if (n3 >= n2) {
                n3 = bundle.getInt("android:menu:expandedactionview");
                if (n3 <= 0) return;
                bundle = this.findItem(n3);
                if (bundle == null) return;
                MenuItemCompat.expandActionView((MenuItem)bundle);
                return;
            }
            MenuItem menuItem = this.getItem(n3);
            View view = MenuItemCompat.getActionView(menuItem);
            if (view != null && view.getId() != -1) {
                view.restoreHierarchyState(sparseArray);
            }
            if (menuItem.hasSubMenu()) {
                ((SubMenuBuilder)menuItem.getSubMenu()).restoreActionViewStates(bundle);
            }
            ++n3;
        } while (true);
    }

    public void restorePresenterStates(Bundle bundle) {
        this.dispatchRestoreInstanceState(bundle);
    }

    public void saveActionViewStates(Bundle bundle) {
        int n2 = this.size();
        SparseArray sparseArray = null;
        for (int i2 = 0; i2 < n2; ++i2) {
            MenuItem menuItem = this.getItem(i2);
            View view = MenuItemCompat.getActionView(menuItem);
            SparseArray sparseArray2 = sparseArray;
            if (view != null) {
                sparseArray2 = sparseArray;
                if (view.getId() != -1) {
                    SparseArray sparseArray3 = sparseArray;
                    if (sparseArray == null) {
                        sparseArray3 = new SparseArray();
                    }
                    view.saveHierarchyState(sparseArray3);
                    sparseArray2 = sparseArray3;
                    if (MenuItemCompat.isActionViewExpanded(menuItem)) {
                        bundle.putInt("android:menu:expandedactionview", menuItem.getItemId());
                        sparseArray2 = sparseArray3;
                    }
                }
            }
            if (menuItem.hasSubMenu()) {
                ((SubMenuBuilder)menuItem.getSubMenu()).saveActionViewStates(bundle);
            }
            sparseArray = sparseArray2;
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(this.getActionViewStatesKey(), sparseArray);
        }
    }

    public void savePresenterStates(Bundle bundle) {
        this.dispatchSaveInstanceState(bundle);
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public void setCurrentMenuInfo(ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.mCurrentMenuInfo = contextMenuInfo;
    }

    public MenuBuilder setDefaultShowAsAction(int n2) {
        this.mDefaultShowAsAction = n2;
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    void setExclusiveItemChecked(MenuItem menuItem) {
        int n2 = menuItem.getGroupId();
        int n3 = this.mItems.size();
        int n4 = 0;
        while (n4 < n3) {
            MenuItemImpl menuItemImpl = (MenuItemImpl)this.mItems.get(n4);
            if (menuItemImpl.getGroupId() == n2 && menuItemImpl.isExclusiveCheckable() && menuItemImpl.isCheckable()) {
                boolean bl2 = menuItemImpl == menuItem;
                menuItemImpl.setCheckedInt(bl2);
            }
            ++n4;
        }
    }

    public void setGroupCheckable(int n2, boolean bl2, boolean bl3) {
        int n3 = this.mItems.size();
        for (int i2 = 0; i2 < n3; ++i2) {
            MenuItemImpl menuItemImpl = (MenuItemImpl)this.mItems.get(i2);
            if (menuItemImpl.getGroupId() != n2) continue;
            menuItemImpl.setExclusiveCheckable(bl3);
            menuItemImpl.setCheckable(bl2);
        }
    }

    public void setGroupEnabled(int n2, boolean bl2) {
        int n3 = this.mItems.size();
        for (int i2 = 0; i2 < n3; ++i2) {
            MenuItemImpl menuItemImpl = (MenuItemImpl)this.mItems.get(i2);
            if (menuItemImpl.getGroupId() != n2) continue;
            menuItemImpl.setEnabled(bl2);
        }
    }

    public void setGroupVisible(int n2, boolean bl2) {
        int n3 = this.mItems.size();
        boolean bl3 = false;
        for (int i2 = 0; i2 < n3; ++i2) {
            MenuItemImpl menuItemImpl = (MenuItemImpl)this.mItems.get(i2);
            if (menuItemImpl.getGroupId() != n2 || !menuItemImpl.setVisibleInt(bl2)) continue;
            bl3 = true;
        }
        if (bl3) {
            this.onItemsChanged(true);
        }
    }

    protected MenuBuilder setHeaderIconInt(int n2) {
        this.setHeaderInternal(0, null, n2, null, null);
        return this;
    }

    protected MenuBuilder setHeaderIconInt(Drawable drawable2) {
        this.setHeaderInternal(0, null, 0, drawable2, null);
        return this;
    }

    protected MenuBuilder setHeaderTitleInt(int n2) {
        this.setHeaderInternal(n2, null, 0, null, null);
        return this;
    }

    protected MenuBuilder setHeaderTitleInt(CharSequence charSequence) {
        this.setHeaderInternal(0, charSequence, 0, null, null);
        return this;
    }

    protected MenuBuilder setHeaderViewInt(View view) {
        this.setHeaderInternal(0, null, 0, null, view);
        return this;
    }

    void setOptionalIconsVisible(boolean bl2) {
        this.mOptionalIconsVisible = bl2;
    }

    public void setOverrideVisibleItems(boolean bl2) {
        this.mOverrideVisibleItems = bl2;
    }

    public void setQwertyMode(boolean bl2) {
        this.mQwertyMode = bl2;
        this.onItemsChanged(false);
    }

    public void setShortcutsVisible(boolean bl2) {
        if (this.mShortcutsVisible == bl2) {
            return;
        }
        this.setShortcutsVisibleInner(bl2);
        this.onItemsChanged(false);
    }

    public int size() {
        return this.mItems.size();
    }

    public void startDispatchingItemsChanged() {
        this.mPreventDispatchingItemsChanged = false;
        if (this.mItemsChangedWhileDispatchPrevented) {
            this.mItemsChangedWhileDispatchPrevented = false;
            this.onItemsChanged(true);
        }
    }

    public void stopDispatchingItemsChanged() {
        if (!this.mPreventDispatchingItemsChanged) {
            this.mPreventDispatchingItemsChanged = true;
            this.mItemsChangedWhileDispatchPrevented = false;
        }
    }

    public static interface Callback {
        public boolean onMenuItemSelected(MenuBuilder var1, MenuItem var2);

        public void onMenuModeChange(MenuBuilder var1);
    }

    public static interface ItemInvoker {
        public boolean invokeItem(MenuItemImpl var1);
    }

}

