/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.IBinder
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.util.SparseBooleanArray
 *  android.view.Menu
 *  android.view.MenuItem
 *  android.view.SubMenu
 *  android.view.TouchDelegate
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewParent
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package android.support.v7.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ActionProvider;
import android.support.v7.appcompat.R;
import android.support.v7.internal.transition.ActionBarTransition;
import android.support.v7.internal.view.ActionBarPolicy;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.support.v7.internal.view.menu.ActionMenuItemView;
import android.support.v7.internal.view.menu.BaseMenuPresenter;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuPopupHelper;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.support.v7.internal.widget.TintImageView;
import android.support.v7.widget.ActionMenuPresenter$OverflowMenuButton$1;
import android.support.v7.widget.ActionMenuPresenter$SavedState$1;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.ArrayList;
import java.util.Iterator;

public class ActionMenuPresenter
extends BaseMenuPresenter
implements ActionProvider.SubUiVisibilityListener {
    private static final String TAG = "ActionMenuPresenter";
    private final SparseBooleanArray mActionButtonGroups = new SparseBooleanArray();
    private ActionButtonSubmenu mActionButtonPopup;
    private int mActionItemWidthLimit;
    private boolean mExpandedActionViewsExclusive;
    private boolean mIsSplit;
    private int mMaxItems;
    private boolean mMaxItemsSet;
    private int mMinCellSize;
    private int mMinCellSizeInSplit;
    int mOpenSubMenuId;
    private View mOverflowButton;
    private Drawable mOverflowDrawable;
    private OverflowPopup mOverflowPopup;
    private ActionMenuPopupCallback mPopupCallback;
    final PopupPresenterCallback mPopupPresenterCallback;
    private OpenOverflowRunnable mPostedOpenRunnable;
    private boolean mReserveOverflow;
    private boolean mReserveOverflowSet;
    private View mScrapActionButtonView;
    private boolean mStrictWidthLimit;
    private int mWidthLimit;
    private boolean mWidthLimitSet;

    public ActionMenuPresenter(Context context) {
        super(context, R.layout.abc_action_menu_layout, R.layout.mz_action_menu_item_layout);
        this.mPopupPresenterCallback = new PopupPresenterCallback();
    }

    static /* synthetic */ OverflowPopup access$300(ActionMenuPresenter actionMenuPresenter) {
        return actionMenuPresenter.mOverflowPopup;
    }

    static /* synthetic */ OpenOverflowRunnable access$400(ActionMenuPresenter actionMenuPresenter) {
        return actionMenuPresenter.mPostedOpenRunnable;
    }

    static /* synthetic */ View access$700(ActionMenuPresenter actionMenuPresenter) {
        return actionMenuPresenter.mOverflowButton;
    }

    static /* synthetic */ MenuView access$800(ActionMenuPresenter actionMenuPresenter) {
        return actionMenuPresenter.mMenuView;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private View findAnchor(View view) {
        Activity activity;
        View view2;
        block8 : {
            block9 : {
                if (Build.VERSION.SDK_INT < 19) {
                    return view;
                }
                try {
                    if (this.mContext instanceof ContextThemeWrapper) {
                        activity = (Activity)((ContextThemeWrapper)this.mContext).getBaseContext();
                        view2 = view;
                        break block8;
                    }
                    if (!(this.mContext instanceof Activity)) break block9;
                    activity = (Activity)this.mContext;
                    view2 = ((Activity)this.mContext).findViewById(R.id.action_bar);
                    break block8;
                }
                catch (Exception var2_5) {
                    return view;
                }
            }
            activity = null;
            view2 = view;
        }
        try {
            view2 = !this.isSplit() ? (activity = activity.findViewById(R.id.action_bar)) : (activity = activity.findViewById(R.id.split_action_bar));
            if (view2 == null) return view;
            return view2;
        }
        catch (Exception var1_2) {
            return view2;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private View findViewForItem(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup)this.mMenuView;
        if (viewGroup == null) {
            return null;
        }
        int n2 = viewGroup.getChildCount();
        int n3 = 0;
        while (n3 < n2) {
            View view = viewGroup.getChildAt(n3);
            if (view instanceof MenuView.ItemView) {
                View view2 = view;
                if (((MenuView.ItemView)view).getItemData() == menuItem) return view2;
            }
            ++n3;
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean flagActionItemsInSplit() {
        int n2;
        MenuItemImpl menuItemImpl;
        ArrayList<MenuItemImpl> arrayList = this.mMenu.getVisibleItems();
        int n3 = arrayList.size();
        int n4 = this.mWidthLimit;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        while (n5 < n3) {
            menuItemImpl = (MenuItemImpl)arrayList.get(n5);
            if (menuItemImpl.requiresActionButton()) {
                n2 = n6;
                n6 = ++n8;
                n8 = n7;
                n7 = n2;
            } else if (menuItemImpl.requestsActionButton()) {
                n2 = n8;
                n8 = n7 + 1;
                n7 = n6;
                n6 = n2;
            } else {
                int n9 = 1;
                n6 = n7;
                n2 = n8;
                n7 = n9;
                n8 = n6;
                n6 = n2;
            }
            n2 = n5 + 1;
            n5 = n6;
            n6 = n7;
            n7 = n8;
            n8 = n5;
            n5 = n2;
        }
        n5 = this.mReserveOverflow && n6 != 0 ? 1 : 0;
        menuItemImpl = this.mActionButtonGroups;
        menuItemImpl.clear();
        n6 = this.mStrictWidthLimit ? n4 / this.mMinCellSizeInSplit : 0;
        n2 = n8 + n7 > n6 ? 1 : 0;
        n5 = (n5 | n2) != 0 ? n6 - 1 : n6;
        n6 = n5;
        if (n8 + n7 < n5) {
            n6 = n8 + n7;
        }
        n8 = n6;
        for (n7 = 0; n7 < n3 && n8 > 0; ++n7) {
            MenuItemImpl menuItemImpl2 = (MenuItemImpl)arrayList.get(n7);
            if (menuItemImpl2.requiresActionButton() || menuItemImpl2.requestsActionButton()) {
                menuItemImpl2.setIsActionButton(true);
                n6 = menuItemImpl2.getGroupId();
                if (n6 != 0) {
                    menuItemImpl.put(n6, true);
                }
                --n8;
                continue;
            }
            menuItemImpl2.setIsActionButton(false);
        }
        n8 = n7;
        while (n8 < n3) {
            ((MenuItemImpl)arrayList.get(n7)).setIsActionButton(false);
            ++n8;
        }
        return true;
    }

    @Override
    protected void addItemView(View view, int n2) {
        super.addItemView(view, n2);
        if (this.mMenuView instanceof ActionMenuView && view instanceof ActionMenuItemView) {
            ((ActionMenuView.LayoutParams)view.getLayoutParams()).isOverflowButton = ((ActionMenuItemView)view).isOverflowActor();
        }
    }

    @Override
    public void bindItemView(MenuItemImpl object, MenuView.ItemView itemView) {
        ((ActionMenuItemView)itemView).setIsInSplit(this.mIsSplit);
        itemView.initialize((MenuItemImpl)object, 0);
        object = (ActionMenuView)this.mMenuView;
        itemView = (ActionMenuItemView)itemView;
        itemView.setItemInvoker((MenuBuilder.ItemInvoker)object);
        if (this.mPopupCallback == null) {
            this.mPopupCallback = new ActionMenuPopupCallback();
        }
        itemView.setPopupCallback(this.mPopupCallback);
    }

    @Override
    public MenuView.ItemView createItemView(ViewGroup object) {
        object = super.createItemView((ViewGroup)object);
        if ((this.isSplit() || Build.VERSION.SDK_INT < 21) && object instanceof ActionMenuItemView) {
            ((ActionMenuView)this.mMenuView).setClipChildren(false);
            ActionMenuItemView actionMenuItemView = (ActionMenuItemView)object;
            actionMenuItemView.setBackgroundDrawable((Drawable)new ActionMenuRippleDrawable((View)actionMenuItemView));
        }
        return object;
    }

    public boolean dismissPopupMenus() {
        return this.hideOverflowMenu() | this.hideSubMenus();
    }

    public boolean dismissPopupMenus(boolean bl2) {
        return this.hideOverflowMenu(bl2) | this.hideSubMenus();
    }

    @Override
    public boolean filterLeftoverView(ViewGroup viewGroup, int n2) {
        if (viewGroup.getChildAt(n2) == this.mOverflowButton) {
            return false;
        }
        return super.filterLeftoverView(viewGroup, n2);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public boolean flagActionItems() {
        if (this.mIsSplit) {
            return this.flagActionItemsInSplit();
        }
        var14_1 = this.mMenu.getVisibleItems();
        var9_2 = var14_1.size();
        var1_3 = this.mMaxItems;
        var8_4 = this.mActionItemWidthLimit;
        var10_5 = View.MeasureSpec.makeMeasureSpec((int)0, (int)0);
        var15_6 = (ViewGroup)this.mMenuView;
        var3_7 = 0;
        var4_8 = 0;
        var5_9 = 0;
        for (var2_10 = 0; var2_10 < var9_2; ++var2_10) {
            var16_11 = (MenuItemImpl)var14_1.get(var2_10);
            if (var16_11.requiresActionButton()) {
                ++var3_7;
            } else if (var16_11.requestsActionButton()) {
                ++var4_8;
            } else {
                var5_9 = 1;
            }
            if (!this.mExpandedActionViewsExclusive || !var16_11.isActionViewExpanded()) continue;
            var1_3 = 0;
        }
        var2_10 = var1_3;
        if (!this.mReserveOverflow) ** GOTO lbl30
        if (var5_9 != 0) ** GOTO lbl-1000
        var2_10 = var1_3;
        if (var3_7 + var4_8 > var1_3) lbl-1000: // 2 sources:
        {
            var2_10 = var1_3 - 1;
        }
lbl30: // 4 sources:
        var2_10 -= var3_7;
        var16_11 = this.mActionButtonGroups;
        var16_11.clear();
        var1_3 = 0;
        if (this.mStrictWidthLimit) {
            var1_3 = var8_4 / this.mMinCellSize;
            var3_7 = this.mMinCellSize;
            var4_8 = this.mMinCellSize;
            var6_12 = var8_4 % var3_7 / var1_3 + var4_8;
        } else {
            var6_12 = 0;
        }
        var7_13 = 0;
        var5_9 = 0;
        var3_7 = var1_3;
        var1_3 = var2_10;
        var4_8 = var8_4;
        var2_10 = var5_9;
        while (var7_13 < var9_2) {
            var17_17 = (MenuItemImpl)var14_1.get(var7_13);
            if (!var17_17.requiresActionButton()) ** GOTO lbl67
            var18_18 = this.getItemView(var17_17, this.mScrapActionButtonView, var15_6);
            if (this.mScrapActionButtonView == null) {
                this.mScrapActionButtonView = var18_18;
            }
            if (this.mStrictWidthLimit) {
                var5_9 = var3_7 - ActionMenuView.measureChildForCells((View)var18_18, var6_12, var3_7, var10_5, 0);
            } else {
                var18_18.measure(var10_5, var10_5);
                var5_9 = var3_7;
            }
            var3_7 = var18_18.getMeasuredWidth();
            if (var2_10 == 0) {
                var2_10 = var3_7;
            }
            if ((var8_4 = var17_17.getGroupId()) != 0) {
                var16_11.put(var8_4, true);
            }
            var17_17.setIsActionButton(true);
            var3_7 = var1_3;
            var1_3 = var4_8 -= var3_7;
            ** GOTO lbl143
lbl67: // 1 sources:
            if (!var17_17.requestsActionButton()) ** GOTO lbl109
            var11_14 = var17_17.getGroupId();
            var13_16 = var16_11.get(var11_14);
            var12_15 = !(var1_3 <= 0 && var13_16 == false || var4_8 <= 0 || this.mStrictWidthLimit != false && var3_7 <= 0) ? 1 : 0;
            if (var12_15 == 0) ** GOTO lbl116
            var18_18 = this.getItemView(var17_17, this.mScrapActionButtonView, var15_6);
            if (this.mScrapActionButtonView == null) {
                this.mScrapActionButtonView = var18_18;
            }
            if (this.mStrictWidthLimit) {
                var5_9 = ActionMenuView.measureChildForCells((View)var18_18, var6_12, var3_7, var10_5, 0);
                if (var5_9 == 0) {
                    var12_15 = 0;
                }
                var3_7 -= var5_9;
            } else {
                var18_18.measure(var10_5, var10_5);
            }
            var5_9 = var18_18.getMeasuredWidth();
            var8_4 = var4_8 - var5_9;
            var4_8 = var2_10;
            if (var2_10 == 0) {
                var4_8 = var5_9;
            }
            if (this.mStrictWidthLimit) {
                var2_10 = var8_4 >= 0 ? 1 : 0;
                var12_15 &= var2_10;
                var2_10 = var4_8;
                var5_9 = var3_7;
                var4_8 = var8_4;
                var3_7 = var2_10;
                var2_10 = var5_9;
            } else if (var7_13 == 0) {
                var2_10 = var8_4 + var4_8 > 0 ? 1 : 0;
                var12_15 &= var2_10;
                var2_10 = var3_7;
                var3_7 = var4_8;
                var4_8 = var8_4;
            } else {
                var2_10 = var8_4 >= 0 ? 1 : 0;
                var12_15 &= var2_10;
                var2_10 = var3_7;
                var3_7 = var4_8;
                var4_8 = var8_4;
            }
            ** GOTO lbl119
lbl109: // 1 sources:
            var17_17.setIsActionButton(false);
            var5_9 = var4_8;
            var4_8 = var1_3;
            var1_3 = var5_9;
            var5_9 = var3_7;
            var3_7 = var4_8;
            ** GOTO lbl143
lbl116: // 1 sources:
            var5_9 = var2_10;
            var2_10 = var3_7;
            var3_7 = var5_9;
lbl119: // 4 sources:
            if (var12_15 != 0 && var11_14 != 0) {
                var16_11.put(var11_14, true);
            } else if (var13_16) {
                var16_11.put(var11_14, false);
                for (var8_4 = 0; var8_4 < var7_13; ++var8_4) {
                    var18_18 = (MenuItemImpl)var14_1.get(var8_4);
                    var5_9 = var1_3;
                    if (var18_18.getGroupId() == var11_14) {
                        var5_9 = var1_3;
                        if (var18_18.isActionButton()) {
                            var5_9 = var1_3 + 1;
                        }
                        var18_18.setIsActionButton(false);
                    }
                    var1_3 = var5_9;
                }
            }
            var5_9 = var1_3;
            if (var12_15 != 0) {
                var5_9 = var1_3 - 1;
            }
            var17_17.setIsActionButton((boolean)var12_15);
            var1_3 = var4_8;
            var4_8 = var5_9;
            var5_9 = var2_10;
            var2_10 = var3_7;
            var3_7 = var4_8;
lbl143: // 3 sources:
            var8_4 = var7_13 + 1;
            var7_13 = var3_7;
            var3_7 = var5_9;
            var4_8 = var1_3;
            var1_3 = var7_13;
            var7_13 = var8_4;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public View getItemView(MenuItemImpl object, View view, ViewGroup viewGroup) {
        View view2 = object.getActionView();
        if (view2 == null || object.hasCollapsibleActionView()) {
            view2 = super.getItemView((MenuItemImpl)object, view, viewGroup);
        }
        int n2 = object.isActionViewExpanded() ? 8 : 0;
        view2.setVisibility(n2);
        object = (ActionMenuView)viewGroup;
        view = view2.getLayoutParams();
        if (!object.checkLayoutParams((ViewGroup.LayoutParams)view)) {
            view2.setLayoutParams((ViewGroup.LayoutParams)object.generateLayoutParams((ViewGroup.LayoutParams)view));
            return view2;
        } else {
            if (!(view instanceof ActionMenuView.LayoutParams) || !(view2 instanceof ActionMenuItemView)) return view2;
            {
                ((ActionMenuView.LayoutParams)view).isOverflowButton = ((ActionMenuItemView)view2).isOverflowActor();
                return view2;
            }
        }
    }

    @Override
    public MenuView getMenuView(ViewGroup object) {
        object = super.getMenuView((ViewGroup)object);
        ((ActionMenuView)object).setPresenter(this);
        return object;
    }

    public boolean hideOverflowMenu() {
        return this.hideOverflowMenu(true);
    }

    public boolean hideOverflowMenu(boolean bl2) {
        if (this.mPostedOpenRunnable != null && this.mMenuView != null) {
            ((View)this.mMenuView).removeCallbacks((Runnable)this.mPostedOpenRunnable);
            this.mPostedOpenRunnable = null;
            return true;
        }
        OverflowPopup overflowPopup = this.mOverflowPopup;
        if (overflowPopup != null) {
            overflowPopup.dismiss(bl2);
            return true;
        }
        return false;
    }

    public boolean hideSubMenus() {
        if (this.mActionButtonPopup != null) {
            this.mActionButtonPopup.dismiss();
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void initForMenu(Context object, MenuBuilder menuBuilder) {
        super.initForMenu((Context)object, menuBuilder);
        menuBuilder = object.getResources();
        object = ActionBarPolicy.get((Context)object);
        if (!this.mReserveOverflowSet) {
            this.mReserveOverflow = object.showsOverflowMenuButton();
        }
        if (!this.mWidthLimitSet) {
            this.mWidthLimit = object.getEmbeddedMenuWidthLimit();
        }
        if (!this.mMaxItemsSet) {
            this.mMaxItems = object.getMaxActionButtons();
        }
        int n2 = this.mWidthLimit;
        if (this.mReserveOverflow) {
            if (this.mOverflowButton == null) {
                this.mOverflowButton = new OverflowMenuButton(this.mSystemContext);
                int n3 = View.MeasureSpec.makeMeasureSpec((int)0, (int)0);
                this.mOverflowButton.measure(n3, n3);
            }
            n2 -= this.mOverflowButton.getMeasuredWidth();
        } else {
            this.mOverflowButton = null;
        }
        this.mActionItemWidthLimit = n2;
        this.mMinCellSize = (int)(56.0f * menuBuilder.getDisplayMetrics().density);
        this.mScrapActionButtonView = null;
        this.mMinCellSizeInSplit = (int)(67.0f * menuBuilder.getDisplayMetrics().density);
    }

    public boolean isOverflowMenuShowPending() {
        if (this.mPostedOpenRunnable != null || this.isOverflowMenuShowing()) {
            return true;
        }
        return false;
    }

    public boolean isOverflowMenuShowing() {
        if (this.mOverflowPopup != null && this.mOverflowPopup.isShowing()) {
            return true;
        }
        return false;
    }

    public boolean isOverflowReserved() {
        return this.mReserveOverflow;
    }

    public boolean isSplit() {
        return this.mIsSplit;
    }

    @Override
    public void onCloseMenu(MenuBuilder menuBuilder, boolean bl2) {
        this.dismissPopupMenus();
        super.onCloseMenu(menuBuilder, bl2);
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean bl2, MenuItemImpl menuItemImpl) {
        this.dismissPopupMenus(menuItemImpl.isLetMenuSlideOut());
        super.onCloseMenu(menuBuilder, bl2);
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (!this.mMaxItemsSet) {
            this.mMaxItems = this.mContext.getResources().getInteger(R.integer.abc_max_action_buttons);
        }
        if (this.mMenu != null) {
            this.mMenu.onItemsChanged(true);
        }
    }

    @Override
    public void onRestoreInstanceState(Parcelable parcelable) {
        parcelable = (SavedState)parcelable;
        if (parcelable.openSubMenuId > 0 && (parcelable = this.mMenu.findItem(parcelable.openSubMenuId)) != null) {
            this.onSubMenuSelected((SubMenuBuilder)parcelable.getSubMenu());
        }
    }

    @Override
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState();
        savedState.openSubMenuId = this.mOpenSubMenuId;
        return savedState;
    }

    @Override
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        View view;
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        SubMenuBuilder subMenuBuilder2 = subMenuBuilder;
        while (subMenuBuilder2.getParentMenu() != this.mMenu) {
            subMenuBuilder2 = (SubMenuBuilder)subMenuBuilder2.getParentMenu();
        }
        subMenuBuilder2 = view = this.findViewForItem(subMenuBuilder2.getItem());
        if (view == null) {
            if (this.mOverflowButton == null) {
                return false;
            }
            subMenuBuilder2 = this.mOverflowButton;
        }
        this.mOpenSubMenuId = subMenuBuilder.getItem().getItemId();
        this.mActionButtonPopup = new ActionButtonSubmenu(this, this.mContext, subMenuBuilder);
        subMenuBuilder2 = this.findAnchor((View)subMenuBuilder2);
        this.mActionButtonPopup.setGravity(8388613);
        this.mActionButtonPopup.setAnchorView((View)subMenuBuilder2);
        this.mActionButtonPopup.show();
        super.onSubMenuSelected(subMenuBuilder);
        return true;
    }

    @Override
    public void onSubUiVisibilityChanged(boolean bl2) {
        if (bl2) {
            super.onSubMenuSelected(null);
            return;
        }
        this.mMenu.close(false);
    }

    public void setExpandedActionViewsExclusive(boolean bl2) {
        this.mExpandedActionViewsExclusive = bl2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setIsSplit(boolean bl2) {
        if (this.mIsSplit == bl2) {
            return;
        }
        int n2 = 1;
        if (n2 != 0) {
            this.mIsSplit = bl2;
            n2 = this.mIsSplit ? R.layout.mz_action_menu_item_split_layout : R.layout.mz_action_menu_item_layout;
            this.setItemLayoutRes(n2);
            if (this.mMenuView != null) {
                ((ViewGroup)this.mMenuView).removeAllViews();
            }
            if (!bl2) {
                this.mReserveOverflowSet = false;
                this.mWidthLimitSet = false;
                this.mMaxItemsSet = false;
                this.initForMenu(this.mContext, this.mMenu);
            }
        }
    }

    public void setItemLimit(int n2) {
        this.mMaxItems = n2;
        this.mMaxItemsSet = true;
    }

    public void setMenuView(ActionMenuView actionMenuView) {
        this.mMenuView = actionMenuView;
        actionMenuView.initialize(this.mMenu);
    }

    public void setOverflowDrawable(Drawable drawable2) {
        this.mOverflowDrawable = drawable2;
        if (this.mOverflowButton != null) {
            ((OverflowMenuButton)this.mOverflowButton).setImageDrawable(this.mOverflowDrawable);
        }
    }

    public void setReserveOverflow(boolean bl2) {
        this.mReserveOverflow = bl2;
        this.mReserveOverflowSet = true;
    }

    public void setWidthLimit(int n2, boolean bl2) {
        this.mWidthLimit = n2;
        this.mStrictWidthLimit = bl2;
        this.mWidthLimitSet = true;
    }

    @Override
    public boolean shouldIncludeItem(int n2, MenuItemImpl menuItemImpl) {
        return menuItemImpl.isActionButton();
    }

    public boolean showOverflowMenu() {
        if (this.mReserveOverflow && !this.isOverflowMenuShowing() && this.mMenu != null && this.mMenuView != null && this.mPostedOpenRunnable == null && !this.mMenu.getNonActionItems().isEmpty()) {
            View view = this.findAnchor(this.mOverflowButton);
            this.mPostedOpenRunnable = new OpenOverflowRunnable(new OverflowPopup(this.mContext, this.mMenu, view, true));
            ((View)this.mMenuView).post((Runnable)this.mPostedOpenRunnable);
            super.onSubMenuSelected(null);
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void updateMenuView(boolean bl2) {
        int n2;
        Object var6_2 = null;
        boolean bl3 = true;
        ArrayList<MenuItemImpl> arrayList = (ArrayList<MenuItemImpl>)((View)this.mMenuView).getParent();
        if (arrayList != null) {
            ActionBarTransition.beginDelayedTransition((ViewGroup)arrayList);
        }
        super.updateMenuView(bl2);
        ((View)this.mMenuView).requestLayout();
        if (this.mMenu != null) {
            arrayList = this.mMenu.getActionItems();
            int n3 = arrayList.size();
            for (n2 = 0; n2 < n3; ++n2) {
                ActionProvider actionProvider = ((MenuItemImpl)arrayList.get(n2)).getSupportActionProvider();
                if (actionProvider == null) continue;
                actionProvider.setSubUiVisibilityListener(this);
            }
        }
        arrayList = this.mMenu != null ? this.mMenu.getNonActionItems() : null;
        if (this.mReserveOverflow && arrayList != null) {
            n2 = arrayList.size();
            if (n2 == 1) {
                bl2 = !((MenuItemImpl)arrayList.get(0)).isActionViewExpanded();
            } else {
                bl2 = bl3;
                if (n2 <= 0) {
                    bl2 = false;
                }
            }
        } else {
            bl2 = false;
        }
        if (bl2) {
            if (this.mOverflowButton == null) {
                this.mOverflowButton = new OverflowMenuButton(this.mSystemContext);
            }
            if ((arrayList = (ArrayList<MenuItemImpl>)this.mOverflowButton.getParent()) != this.mMenuView) {
                if (arrayList != null) {
                    arrayList.removeView(this.mOverflowButton);
                }
                arrayList = (ActionMenuView)this.mMenuView;
                arrayList.addView(this.mOverflowButton, (ViewGroup.LayoutParams)arrayList.generateOverflowButtonLayoutParams());
            }
        } else if (this.mOverflowButton != null && this.mOverflowButton.getParent() == this.mMenuView) {
            ((ViewGroup)this.mMenuView).removeView(this.mOverflowButton);
        }
        bl3 = bl2;
        if (!bl2) {
            arrayList = var6_2;
            if (this.mMenu != null) {
                arrayList = this.mMenu.getVisibleItems();
            }
            bl3 = bl2;
            if (arrayList != null) {
                bl3 = bl2;
                if (arrayList.size() > 0) {
                    arrayList = arrayList.iterator();
                    while (arrayList.hasNext()) {
                        bl2 = ((MenuItemImpl)arrayList.next()).isOverflowActor();
                    }
                    bl3 = bl2;
                }
            }
        }
        ((ActionMenuView)this.mMenuView).setOverflowReserved(this.mReserveOverflow);
        ((ActionMenuView)this.mMenuView).setHasOverflow(bl3);
    }

    /*
     * Exception performing whole class analysis.
     */
    class ActionButtonSubmenu
    extends MenuPopupHelper {
        private SubMenuBuilder mSubMenu;
        final /* synthetic */ ActionMenuPresenter this$0;

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public ActionButtonSubmenu(ActionMenuPresenter var1_1, Context var2_2, SubMenuBuilder var3_3) {
            var7_4 = false;
            this.this$0 = var1_1;
            var4_5 = var1_1.isSplit() != false ? R.attr.mzActionOverflowMenuSplitStyle : R.attr.actionOverflowMenuStyle;
            super(var2_2, var3_3, null, false, var4_5);
            this.mSubMenu = var3_3;
            if (!((MenuItemImpl)var3_3.getItem()).isActionButton()) {
                var2_2 = ActionMenuPresenter.access$700(var1_1) == null ? (View)ActionMenuPresenter.access$800(var1_1) : ActionMenuPresenter.access$700(var1_1);
                this.setAnchorView((View)var2_2);
            }
            this.setCallback(var1_1.mPopupPresenterCallback);
            var5_6 = var3_3.size();
            var4_5 = 0;
            do {
                var6_7 = var7_4;
                if (var4_5 >= var5_6) ** GOTO lbl18
                var1_1 = var3_3.getItem(var4_5);
                if (var1_1.isVisible() && var1_1.getIcon() != null) {
                    var6_7 = true;
lbl18: // 2 sources:
                    this.setForceShowIcon(var6_7);
                    return;
                }
                ++var4_5;
            } while (true);
        }

        @Override
        public void onDismiss() {
            super.onDismiss();
            this.this$0.mActionButtonPopup = null;
            this.this$0.mOpenSubMenuId = 0;
        }
    }

    class ActionMenuPopupCallback
    extends ActionMenuItemView.PopupCallback {
        private ActionMenuPopupCallback() {
        }

        @Override
        public ListPopupWindow getPopup() {
            if (ActionMenuPresenter.this.mActionButtonPopup != null) {
                return ActionMenuPresenter.this.mActionButtonPopup.getPopup();
            }
            return null;
        }
    }

    class ActionMenuRippleDrawable
    extends aqt {
        public ActionMenuRippleDrawable(View view) {
            super(view, R.attr.mzActionButtonRippleSplitStyle);
        }
    }

    class OpenOverflowRunnable
    implements Runnable {
        private OverflowPopup mPopup;

        public OpenOverflowRunnable(OverflowPopup overflowPopup) {
            this.mPopup = overflowPopup;
        }

        @Override
        public void run() {
            ActionMenuPresenter.this.mMenu.changeMenuMode();
            View view = (View)ActionMenuPresenter.this.mMenuView;
            if (view != null && view.getWindowToken() != null && this.mPopup.tryShow()) {
                ActionMenuPresenter.this.mOverflowPopup = this.mPopup;
            }
            ActionMenuPresenter.this.mPostedOpenRunnable = null;
        }
    }

    class OverflowMenuButton
    extends TintImageView
    implements ActionMenuView.ActionMenuChildView {
        private final float[] mTempPts;

        /*
         * Enabled aggressive block sorting
         */
        public OverflowMenuButton(Context context) {
            int n2 = ActionMenuPresenter.this.mIsSplit ? R.attr.mzActionOverflowButtonSplitStyle : R.attr.actionOverflowButtonStyle;
            super(context, null, n2);
            this.mTempPts = new float[2];
            this.setId(R.id.mz_action_overflow_button);
            this.setClickable(true);
            this.setFocusable(true);
            this.setVisibility(0);
            this.setEnabled(true);
            this.setOnTouchListener((View.OnTouchListener)new ActionMenuPresenter$OverflowMenuButton$1(this, (View)this, ActionMenuPresenter.this));
            this.setOnTouchListener(null);
            if (ActionMenuPresenter.this.isSplit() || Build.VERSION.SDK_INT < 21) {
                this.setBackgroundDrawable((Drawable)new ActionMenuRippleDrawable((View)this));
            }
            if (ActionMenuPresenter.this.mOverflowDrawable != null) {
                this.setImageDrawable(ActionMenuPresenter.this.mOverflowDrawable);
            }
            this.setContentDescription((CharSequence)this.getResources().getString(R.string.abc_action_menu_overflow_description));
        }

        @Override
        public boolean needsDividerAfter() {
            return false;
        }

        @Override
        public boolean needsDividerBefore() {
            return false;
        }

        protected void onLayout(boolean bl2, int n2, int n3, int n4, int n5) {
            super.onLayout(bl2, n2, n3, n4, n5);
            int n6 = (int)(this.getResources().getDisplayMetrics().density * 52.0f);
            if (n4 - n2 < n6) {
                n6 = (n6 - (n4 - n2)) / 2;
                Rect rect = new Rect(n2 - n6, n3, n6 + n4, n5);
                ((View)this.getParent()).setTouchDelegate(new TouchDelegate(rect, (View)this));
            }
        }

        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            this.playSoundEffect(0);
            ActionMenuPresenter.this.showOverflowMenu();
            return true;
        }

        protected boolean setFrame(int n2, int n3, int n4, int n5) {
            boolean bl2 = super.setFrame(n2, n3, n4, n5);
            Drawable drawable2 = this.getDrawable();
            Drawable drawable3 = this.getBackground();
            int n6 = this.getPaddingLeft() - this.getPaddingRight();
            n4 = this.getPaddingTop() - this.getPaddingBottom();
            if (drawable2 != null && drawable3 != null && (n6 != 0 || n4 != 0)) {
                int n7 = this.getWidth();
                n5 = this.getHeight();
                n2 = n7 / 2;
                n3 = n5 / 2;
                n6 = (n7 + n6) / 2;
                n4 = (n5 + n4) / 2;
                DrawableCompat.setHotspotBounds(drawable3, n6 - n2, n4 - n3, n6 + n2, n4 + n3);
            }
            return bl2;
        }
    }

    class OverflowPopup
    extends MenuPopupHelper {
        /*
         * Enabled aggressive block sorting
         */
        public OverflowPopup(Context context, MenuBuilder menuBuilder, View view, boolean bl2) {
            int n2 = ActionMenuPresenter.this.isSplit() ? R.attr.mzActionOverflowMenuSplitStyle : R.attr.actionOverflowMenuStyle;
            super(context, menuBuilder, view, bl2, n2);
            this.setGravity(8388613);
            this.setCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
        }

        @Override
        public void dismiss() {
            super.dismiss(true);
        }

        @Override
        public void onDismiss() {
            super.onDismiss();
            ActionMenuPresenter.this.mMenu.close();
            ActionMenuPresenter.this.mOverflowPopup = null;
        }
    }

    class PopupPresenterCallback
    implements MenuPresenter.Callback {
        private PopupPresenterCallback() {
        }

        @Override
        public void onCloseMenu(MenuBuilder menuBuilder, boolean bl2) {
            MenuPresenter.Callback callback;
            if (menuBuilder instanceof SubMenuBuilder) {
                ((SubMenuBuilder)menuBuilder).getRootMenu().close(false);
            }
            if ((callback = ActionMenuPresenter.this.getCallback()) != null) {
                callback.onCloseMenu(menuBuilder, bl2);
            }
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            if (menuBuilder == null) {
                return false;
            }
            ActionMenuPresenter.this.mOpenSubMenuId = ((SubMenuBuilder)menuBuilder).getItem().getItemId();
            MenuPresenter.Callback callback = ActionMenuPresenter.this.getCallback();
            if (callback == null) return false;
            return callback.onOpenSubMenu(menuBuilder);
        }
    }

    static class SavedState
    implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new ActionMenuPresenter$SavedState$1();
        public int openSubMenuId;

        SavedState() {
        }

        SavedState(Parcel parcel) {
            this.openSubMenuId = parcel.readInt();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int n2) {
            parcel.writeInt(this.openSubMenuId);
        }
    }

}

