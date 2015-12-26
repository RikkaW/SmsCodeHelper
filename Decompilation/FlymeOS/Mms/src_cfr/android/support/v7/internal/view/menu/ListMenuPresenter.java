/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Bundle
 *  android.os.IBinder
 *  android.os.Parcelable
 *  android.util.SparseArray
 *  android.view.ContextThemeWrapper
 *  android.view.LayoutInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.BaseAdapter
 *  android.widget.ListAdapter
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package android.support.v7.internal.view.menu;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.menu.ExpandedMenuView;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuDialogHelper;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import java.util.ArrayList;

public class ListMenuPresenter
implements MenuPresenter,
AdapterView.OnItemClickListener {
    private static final String TAG = "ListMenuPresenter";
    public static final String VIEWS_TAG = "android:menu:list";
    MenuAdapter mAdapter;
    private MenuPresenter.Callback mCallback;
    Context mContext;
    private int mId;
    LayoutInflater mInflater;
    private int mItemIndexOffset;
    int mItemLayoutRes;
    MenuBuilder mMenu;
    ExpandedMenuView mMenuView;
    int mThemeRes;

    public ListMenuPresenter(int n2, int n3) {
        this.mItemLayoutRes = n2;
        this.mThemeRes = n3;
    }

    public ListMenuPresenter(Context context, int n2) {
        this(n2, 0);
        this.mContext = context;
        this.mInflater = LayoutInflater.from((Context)this.mContext);
    }

    @Override
    public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override
    public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override
    public boolean flagActionItems() {
        return false;
    }

    public ListAdapter getAdapter() {
        if (this.mAdapter == null) {
            this.mAdapter = new MenuAdapter();
        }
        return this.mAdapter;
    }

    @Override
    public int getId() {
        return this.mId;
    }

    int getItemIndexOffset() {
        return this.mItemIndexOffset;
    }

    @Override
    public MenuView getMenuView(ViewGroup viewGroup) {
        if (this.mMenuView == null) {
            this.mMenuView = (ExpandedMenuView)this.mInflater.inflate(R.layout.abc_expanded_menu_layout, viewGroup, false);
            if (this.mAdapter == null) {
                this.mAdapter = new MenuAdapter();
            }
            this.mMenuView.setAdapter((ListAdapter)this.mAdapter);
            this.mMenuView.setOnItemClickListener((AdapterView.OnItemClickListener)this);
        }
        return this.mMenuView;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void initForMenu(Context context, MenuBuilder menuBuilder) {
        if (this.mThemeRes != 0) {
            this.mContext = new ContextThemeWrapper(context, this.mThemeRes);
            this.mInflater = LayoutInflater.from((Context)this.mContext);
        } else if (this.mContext != null) {
            this.mContext = context;
            if (this.mInflater == null) {
                this.mInflater = LayoutInflater.from((Context)this.mContext);
            }
        }
        this.mMenu = menuBuilder;
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCloseMenu(MenuBuilder menuBuilder, boolean bl2) {
        if (this.mCallback != null) {
            this.mCallback.onCloseMenu(menuBuilder, bl2);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int n2, long l2) {
        this.mMenu.performItemAction(this.mAdapter.getItem(n2), this, 0);
    }

    @Override
    public void onRestoreInstanceState(Parcelable parcelable) {
        this.restoreHierarchyState((Bundle)parcelable);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        if (this.mMenuView == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        this.saveHierarchyState(bundle);
        return bundle;
    }

    @Override
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        new MenuDialogHelper(subMenuBuilder).show(null);
        if (this.mCallback != null) {
            this.mCallback.onOpenSubMenu(subMenuBuilder);
        }
        return true;
    }

    public void restoreHierarchyState(Bundle bundle) {
        if ((bundle = bundle.getSparseParcelableArray("android:menu:list")) != null) {
            this.mMenuView.restoreHierarchyState((SparseArray)bundle);
        }
    }

    public void saveHierarchyState(Bundle bundle) {
        SparseArray sparseArray = new SparseArray();
        if (this.mMenuView != null) {
            this.mMenuView.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray("android:menu:list", sparseArray);
    }

    @Override
    public void setCallback(MenuPresenter.Callback callback) {
        this.mCallback = callback;
    }

    public void setId(int n2) {
        this.mId = n2;
    }

    public void setItemIndexOffset(int n2) {
        this.mItemIndexOffset = n2;
        if (this.mMenuView != null) {
            this.updateMenuView(false);
        }
    }

    @Override
    public void updateMenuView(boolean bl2) {
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    class MenuAdapter
    extends BaseAdapter {
        private int mExpandedIndex;

        public MenuAdapter() {
            this.mExpandedIndex = -1;
            this.findExpandedIndex();
        }

        void findExpandedIndex() {
            MenuItemImpl menuItemImpl = ListMenuPresenter.this.mMenu.getExpandedItem();
            if (menuItemImpl != null) {
                ArrayList<MenuItemImpl> arrayList = ListMenuPresenter.this.mMenu.getNonActionItems();
                int n2 = arrayList.size();
                for (int i2 = 0; i2 < n2; ++i2) {
                    if ((MenuItemImpl)arrayList.get(i2) != menuItemImpl) continue;
                    this.mExpandedIndex = i2;
                    return;
                }
            }
            this.mExpandedIndex = -1;
        }

        public int getCount() {
            int n2 = ListMenuPresenter.this.mMenu.getNonActionItems().size() - ListMenuPresenter.this.mItemIndexOffset;
            if (this.mExpandedIndex < 0) {
                return n2;
            }
            return n2 - 1;
        }

        public MenuItemImpl getItem(int n2) {
            int n3;
            ArrayList<MenuItemImpl> arrayList = ListMenuPresenter.this.mMenu.getNonActionItems();
            n2 = n3 = ListMenuPresenter.this.mItemIndexOffset + n2;
            if (this.mExpandedIndex >= 0) {
                n2 = n3;
                if (n3 >= this.mExpandedIndex) {
                    n2 = n3 + 1;
                }
            }
            return (MenuItemImpl)arrayList.get(n2);
        }

        public long getItemId(int n2) {
            return n2;
        }

        public View getView(int n2, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = ListMenuPresenter.this.mInflater.inflate(ListMenuPresenter.this.mItemLayoutRes, viewGroup, false);
            }
            ((MenuView.ItemView)view).initialize(this.getItem(n2), 0);
            return view;
        }

        public void notifyDataSetChanged() {
            this.findExpandedIndex();
            super.notifyDataSetChanged();
        }
    }

}

