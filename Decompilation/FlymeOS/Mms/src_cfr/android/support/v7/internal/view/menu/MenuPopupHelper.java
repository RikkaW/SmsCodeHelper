/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.os.Parcelable
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.view.KeyEvent
 *  android.view.LayoutInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.View$OnKeyListener
 *  android.view.ViewGroup
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.widget.AbsListView
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.BaseAdapter
 *  android.widget.FrameLayout
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.PopupWindow
 *  android.widget.PopupWindow$OnDismissListener
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package android.support.v7.internal.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.menu.ListMenuItemView;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import java.util.ArrayList;

public class MenuPopupHelper
implements MenuPresenter,
View.OnKeyListener,
ViewTreeObserver.OnGlobalLayoutListener,
AdapterView.OnItemClickListener,
PopupWindow.OnDismissListener {
    static final int ITEM_LAYOUT = R.layout.mz_popup_menu_item_layout;
    private static final String TAG = "MenuPopupHelper";
    private final MenuAdapter mAdapter;
    private View mAnchorView;
    private int mContentWidth;
    private final Context mContext;
    private int mDropDownGravity = 0;
    boolean mForceShowIcon;
    private boolean mHasContentWidth;
    private final LayoutInflater mInflater;
    private ViewGroup mMeasureParent;
    private final MenuBuilder mMenu;
    private final boolean mOverflowOnly;
    private ListPopupWindow mPopup;
    private final int mPopupMaxWidth;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private MenuPresenter.Callback mPresenterCallback;
    private ViewTreeObserver mTreeObserver;

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder) {
        this(context, menuBuilder, null, false, R.attr.popupMenuStyle);
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view) {
        this(context, menuBuilder, view, false, R.attr.popupMenuStyle);
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view, boolean bl2, int n2) {
        this(context, menuBuilder, view, bl2, n2, 0);
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view, boolean bl2, int n2, int n3) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from((Context)context);
        this.mMenu = menuBuilder;
        this.mAdapter = new MenuAdapter(this.mMenu);
        this.mOverflowOnly = bl2;
        this.mPopupStyleAttr = n2;
        this.mPopupStyleRes = n3;
        Resources resources = context.getResources();
        this.mPopupMaxWidth = Math.max((int)(resources.getDisplayMetrics().widthPixels / 2), (int)resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.mAnchorView = view;
        menuBuilder.addMenuPresenter(this, context);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private int measureContentWidth() {
        MenuAdapter menuAdapter = this.mAdapter;
        int n2 = View.MeasureSpec.makeMeasureSpec((int)0, (int)0);
        int n3 = View.MeasureSpec.makeMeasureSpec((int)0, (int)0);
        int n4 = menuAdapter.getCount();
        int n5 = 0;
        int n6 = 0;
        View view = null;
        int n7 = 0;
        do {
            int n8 = n7;
            if (n5 >= n4) return n8;
            n8 = menuAdapter.getItemViewType(n5);
            if (n8 != n6) {
                n6 = n8;
                view = null;
            }
            if (this.mMeasureParent == null) {
                this.mMeasureParent = new FrameLayout(this.mContext);
            }
            view = menuAdapter.getView(n5, view, this.mMeasureParent);
            view.measure(n2, n3);
            n8 = view.getMeasuredWidth();
            if (n8 >= this.mPopupMaxWidth) {
                return this.mPopupMaxWidth;
            }
            if (n8 > n7) {
                n7 = n8;
            }
            ++n5;
        } while (true);
    }

    @Override
    public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public void dismiss() {
        this.dismiss(false);
    }

    public void dismiss(boolean bl2) {
        if (this.isShowing()) {
            this.mPopup.dismiss(bl2);
        }
    }

    @Override
    public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override
    public boolean flagActionItems() {
        return false;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public MenuView getMenuView(ViewGroup viewGroup) {
        throw new UnsupportedOperationException("MenuPopupHelpers manage their own views");
    }

    public ListPopupWindow getPopup() {
        return this.mPopup;
    }

    @Override
    public void initForMenu(Context context, MenuBuilder menuBuilder) {
    }

    public boolean isShowing() {
        if (this.mPopup != null && this.mPopup.isShowing()) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void onCloseMenu(MenuBuilder menuBuilder, boolean bl2) {
        if (menuBuilder != this.mMenu) {
            return;
        }
        this.dismiss();
        if (this.mPresenterCallback == null) return;
        this.mPresenterCallback.onCloseMenu(menuBuilder, bl2);
    }

    public void onDismiss() {
        this.mPopup = null;
        this.mMenu.close();
        if (this.mTreeObserver != null) {
            if (!this.mTreeObserver.isAlive()) {
                this.mTreeObserver = this.mAnchorView.getViewTreeObserver();
            }
            this.mTreeObserver.removeGlobalOnLayoutListener((ViewTreeObserver.OnGlobalLayoutListener)this);
            this.mTreeObserver = null;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onGlobalLayout() {
        if (!this.isShowing()) return;
        {
            View view = this.mAnchorView;
            if (view == null || !view.isShown()) {
                this.dismiss();
                return;
            } else {
                if (!this.isShowing()) return;
                {
                    this.mPopup.show();
                    return;
                }
            }
        }
    }

    public void onItemClick(AdapterView<?> object, View view, int n2, long l2) {
        object = this.mAdapter;
        ((MenuAdapter)((Object)object)).mAdapterMenu.performItemAction(object.getItem(n2), 0);
    }

    public boolean onKey(View view, int n2, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1 && n2 == 82) {
            this.dismiss();
            return true;
        }
        return false;
    }

    @Override
    public void onRestoreInstanceState(Parcelable parcelable) {
    }

    @Override
    public Parcelable onSaveInstanceState() {
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        if (subMenuBuilder.hasVisibleItems()) {
            boolean bl2;
            MenuPopupHelper menuPopupHelper;
            block4 : {
                menuPopupHelper = new MenuPopupHelper(this.mContext, subMenuBuilder, this.mAnchorView);
                menuPopupHelper.setCallback(this.mPresenterCallback);
                int n2 = subMenuBuilder.size();
                for (int i2 = 0; i2 < n2; ++i2) {
                    MenuItem menuItem = subMenuBuilder.getItem(i2);
                    if (!menuItem.isVisible() || menuItem.getIcon() == null) continue;
                    bl2 = true;
                    break block4;
                }
                bl2 = false;
            }
            menuPopupHelper.setForceShowIcon(bl2);
            if (menuPopupHelper.tryShow()) {
                if (this.mPresenterCallback != null) {
                    this.mPresenterCallback.onOpenSubMenu(subMenuBuilder);
                }
                return true;
            }
        }
        return false;
    }

    public void setAnchorView(View view) {
        this.mAnchorView = view;
    }

    @Override
    public void setCallback(MenuPresenter.Callback callback) {
        this.mPresenterCallback = callback;
    }

    public void setForceShowIcon(boolean bl2) {
        this.mForceShowIcon = bl2;
    }

    public void setGravity(int n2) {
        this.mDropDownGravity = n2;
    }

    public void show() {
        if (!this.tryShow()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public void showBySlide() {
        if (!this.tryShowBySlide()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean tryShow() {
        this.mPopup = new ListPopupWindow(this.mContext, null, this.mPopupStyleAttr, this.mPopupStyleRes);
        this.mPopup.setOnDismissListener(this);
        this.mPopup.setOnItemClickListener(this);
        this.mPopup.setAdapter((ListAdapter)this.mAdapter);
        this.mPopup.setModal(true);
        View view = this.mAnchorView;
        if (view == null) {
            return false;
        }
        boolean bl2 = this.mTreeObserver == null;
        this.mTreeObserver = view.getViewTreeObserver();
        if (bl2) {
            this.mTreeObserver.addOnGlobalLayoutListener((ViewTreeObserver.OnGlobalLayoutListener)this);
        }
        this.mPopup.setAnchorView(view);
        this.mPopup.setDropDownGravity(this.mDropDownGravity);
        if (!this.mHasContentWidth) {
            this.mContentWidth = this.measureContentWidth();
            this.mHasContentWidth = true;
        }
        this.mPopup.setContentWidth(this.mContentWidth);
        this.mPopup.setInputMethodMode(2);
        this.mPopup.setClippingEnabled(false);
        this.mPopup.show();
        this.mPopup.getListView().setOnKeyListener((View.OnKeyListener)this);
        new arb((AbsListView)this.mPopup.getListView(), this.mContext, this.mPopupStyleAttr).a();
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean tryShowBySlide() {
        this.mPopup = new ListPopupWindow(this.mContext, null, this.mPopupStyleAttr, this.mPopupStyleRes, true);
        this.mPopup.setOnDismissListener(this);
        this.mPopup.setOnItemClickListener(this);
        this.mPopup.setAdapter((ListAdapter)this.mAdapter);
        this.mPopup.setModal(true);
        View view = this.mAnchorView;
        if (view == null) {
            return false;
        }
        boolean bl2 = this.mTreeObserver == null;
        this.mTreeObserver = view.getViewTreeObserver();
        if (bl2) {
            this.mTreeObserver.addOnGlobalLayoutListener((ViewTreeObserver.OnGlobalLayoutListener)this);
        }
        this.mPopup.setAnchorView(view);
        this.mPopup.setDropDownGravity(this.mDropDownGravity);
        if (!this.mHasContentWidth) {
            this.mContentWidth = this.measureContentWidth();
            this.mHasContentWidth = true;
        }
        this.mPopup.setInputMethodMode(2);
        this.mPopup.show();
        this.mPopup.getListView().setOnKeyListener((View.OnKeyListener)this);
        new arb((AbsListView)this.mPopup.getListView(), this.mContext, this.mPopupStyleAttr).a();
        return true;
    }

    @Override
    public void updateMenuView(boolean bl2) {
        this.mHasContentWidth = false;
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    class MenuAdapter
    extends BaseAdapter {
        private MenuBuilder mAdapterMenu;
        private int mExpandedIndex;

        public MenuAdapter(MenuBuilder menuBuilder) {
            this.mExpandedIndex = -1;
            this.mAdapterMenu = menuBuilder;
            this.findExpandedIndex();
        }

        void findExpandedIndex() {
            MenuItemImpl menuItemImpl = MenuPopupHelper.this.mMenu.getExpandedItem();
            if (menuItemImpl != null) {
                ArrayList<MenuItemImpl> arrayList = MenuPopupHelper.this.mMenu.getNonActionItems();
                int n2 = arrayList.size();
                for (int i2 = 0; i2 < n2; ++i2) {
                    if ((MenuItemImpl)arrayList.get(i2) != menuItemImpl) continue;
                    this.mExpandedIndex = i2;
                    return;
                }
            }
            this.mExpandedIndex = -1;
        }

        /*
         * Enabled aggressive block sorting
         */
        public int getCount() {
            ArrayList<MenuItemImpl> arrayList = MenuPopupHelper.this.mOverflowOnly ? this.mAdapterMenu.getNonActionItems() : this.mAdapterMenu.getVisibleItems();
            if (this.mExpandedIndex < 0) {
                return arrayList.size();
            }
            return arrayList.size() - 1;
        }

        /*
         * Enabled aggressive block sorting
         */
        public MenuItemImpl getItem(int n2) {
            ArrayList<MenuItemImpl> arrayList = MenuPopupHelper.this.mOverflowOnly ? this.mAdapterMenu.getNonActionItems() : this.mAdapterMenu.getVisibleItems();
            int n3 = n2;
            if (this.mExpandedIndex >= 0) {
                n3 = n2;
                if (n2 >= this.mExpandedIndex) {
                    n3 = n2 + 1;
                }
            }
            return (MenuItemImpl)arrayList.get(n3);
        }

        public long getItemId(int n2) {
            return n2;
        }

        public View getView(int n2, View view, ViewGroup object) {
            if (view == null) {
                view = MenuPopupHelper.this.mInflater.inflate(MenuPopupHelper.ITEM_LAYOUT, (ViewGroup)object, false);
            }
            object = (MenuView.ItemView)view;
            if (MenuPopupHelper.this.mForceShowIcon) {
                ((ListMenuItemView)view).setForceShowIcon(true);
            }
            object.initialize(this.getItem(n2), 0);
            return view;
        }

        public boolean isEnabled(int n2) {
            MenuItemImpl menuItemImpl = this.getItem(n2);
            if (menuItemImpl == null) {
                return super.isEnabled(n2);
            }
            return menuItemImpl.isEnabled();
        }

        public void notifyDataSetChanged() {
            this.findExpandedIndex();
            super.notifyDataSetChanged();
        }
    }

}

