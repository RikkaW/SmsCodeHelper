/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.util.Log
 *  android.view.ContextThemeWrapper
 *  android.view.Menu
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.ViewDebug
 *  android.view.ViewDebug$ExportedProperty
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.accessibility.AccessibilityEvent
 *  java.lang.Long
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.ActionBarPolicy;
import android.support.v7.internal.view.menu.ActionMenuItemView;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.internal.widget.ViewUtils;
import android.support.v7.widget.ActionMenuPresenter;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

public class ActionMenuView
extends LinearLayoutCompat
implements MenuBuilder.ItemInvoker,
MenuView {
    static final int GENERATED_ITEM_PADDING = 4;
    static final int MIN_CELL_SIZE = 56;
    static final int MIN_CELL_SIZE_IN_SPLIT = 67;
    public static final int MIN_DELEGATE_WIDTH = 52;
    private static final String TAG = "ActionMenuView";
    private MenuPresenter.Callback mActionMenuPresenterCallback;
    private Context mContext;
    private boolean mFormatItems;
    private int mFormatItemsWidth;
    private int mGeneratedItemPadding;
    private boolean mHasOverflow;
    private MenuBuilder mMenu;
    private MenuBuilder.Callback mMenuBuilderCallback;
    private int mMinCellSize;
    private OnMenuItemClickListener mOnMenuItemClickListener;
    private Context mPopupContext;
    private int mPopupTheme;
    private ActionMenuPresenter mPresenter;
    private boolean mReserveOverflow;

    public ActionMenuView(Context context) {
        this(context, null);
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.setBaselineAligned(false);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.mMinCellSize = (int)(56.0f * f2);
        this.mGeneratedItemPadding = (int)(f2 * 4.0f);
        this.mPopupContext = context;
        this.mPopupTheme = 0;
        this.setMotionEventSplittingEnabled(false);
    }

    private void applyFlymeStyle(LayoutParams layoutParams, int n2, int n3) {
        int n4 = this.getResources().getDimensionPixelSize(R.dimen.mz_action_overflow_btn_margin_right);
        int n5 = this.getResources().getDimensionPixelSize(R.dimen.mz_action_menu_item_last_margin_right);
        int n6 = this.getResources().getDimensionPixelSize(R.dimen.mz_action_menu_item_next_overflow_margin_right);
        int n7 = this.getResources().getDimensionPixelSize(R.dimen.mz_action_menu_item_margin_right);
        if (Build.VERSION.SDK_INT < 21 && n2 == 0) {
            layoutParams.leftMargin = this.getResources().getDimensionPixelSize(R.dimen.mz_action_menu_item_margin_left_lower_version);
        }
        if (layoutParams.isOverflowButton) {
            layoutParams.rightMargin = n4;
            return;
        }
        if (this.mHasOverflow && n2 == n3 - 2) {
            layoutParams.rightMargin = n6;
            return;
        }
        if (!this.mHasOverflow && n2 == n3 - 1) {
            layoutParams.rightMargin = n5;
            return;
        }
        layoutParams.rightMargin = n7;
    }

    /*
     * Enabled aggressive block sorting
     */
    static int measureChildForCells(View view, int n2, int n3, int n4, int n5) {
        boolean bl2 = false;
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        int n6 = View.MeasureSpec.makeMeasureSpec((int)(View.MeasureSpec.getSize((int)n4) - n5), (int)View.MeasureSpec.getMode((int)n4));
        ActionMenuItemView actionMenuItemView = view instanceof ActionMenuItemView ? (ActionMenuItemView)view : null;
        n5 = actionMenuItemView != null && actionMenuItemView.hasText() ? 1 : 0;
        if (n3 > 0 && (n5 == 0 || n3 >= 2)) {
            view.measure(View.MeasureSpec.makeMeasureSpec((int)(n2 * n3), (int)Integer.MIN_VALUE), n6);
            int n7 = view.getMeasuredWidth();
            n3 = n4 = n7 / n2;
            if (n7 % n2 != 0) {
                n3 = n4 + 1;
            }
            n4 = n3;
            if (n5 != 0) {
                n4 = n3;
                if (n3 < 2) {
                    n4 = 2;
                }
            }
        } else {
            n4 = 0;
        }
        boolean bl3 = bl2;
        if (!layoutParams.isOverflowButton) {
            bl3 = bl2;
            if (n5 != 0) {
                bl3 = true;
            }
        }
        layoutParams.expandable = bl3;
        layoutParams.cellsUsed = n4;
        view.measure(View.MeasureSpec.makeMeasureSpec((int)(n4 * n2), (int)1073741824), n6);
        return n4;
    }

    static int measureChildForCellsInSplit(View view, int n2, int n3, int n4) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        n3 = View.MeasureSpec.makeMeasureSpec((int)(View.MeasureSpec.getSize((int)n3) - n4), (int)View.MeasureSpec.getMode((int)n3));
        layoutParams.cellsUsed = 1;
        view.measure(View.MeasureSpec.makeMeasureSpec((int)n2, (int)1073741824), n3);
        return 1;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void onMeasureExactFormat(int var1_1, int var2_2) {
        block30 : {
            if (this.mPresenter.isSplit()) {
                this.onMeasureExactFormatInSplit(var1_1, var2_2);
                return;
            }
            var17_3 = View.MeasureSpec.getMode((int)var2_2);
            var1_1 = View.MeasureSpec.getSize((int)var1_1);
            var16_4 = View.MeasureSpec.getSize((int)var2_2);
            var6_5 = this.getPaddingLeft();
            var7_6 = this.getPaddingRight();
            var14_7 = this.getPaddingTop() + this.getPaddingBottom();
            var18_8 = ActionMenuView.getChildMeasureSpec((int)var2_2, (int)var14_7, (int)-2);
            var19_9 = var1_1 - (var6_5 + var7_6);
            var1_1 = var19_9 / this.mMinCellSize;
            var2_2 = this.mMinCellSize;
            if (var1_1 == 0) {
                this.setMeasuredDimension(var19_9, 0);
                return;
            }
            var20_10 = this.mMinCellSize + var19_9 % var2_2 / var1_1;
            var6_5 = 0;
            var10_11 = 0;
            var9_12 = 0;
            var11_13 = 0;
            var8_14 = 0;
            var22_15 = 0;
            var21_16 = this.getChildCount();
            var12_17 = 0;
            do {
                if (var12_17 >= var21_16) break;
                var27_22 = this.getChildAt(var12_17);
                if (var27_22.getVisibility() == 8) {
                    var2_2 = var10_11;
                    var7_6 = var1_1;
                    var1_1 = var11_13;
                } else {
                    var26_21 = var27_22 instanceof ActionMenuItemView;
                    ++var11_13;
                    if (var26_21) {
                        var27_22.setPadding(this.mGeneratedItemPadding, 0, this.mGeneratedItemPadding, 0);
                    }
                    var28_23 = (LayoutParams)var27_22.getLayoutParams();
                    var28_23.expanded = false;
                    var28_23.extraPixels = 0;
                    var28_23.cellsUsed = 0;
                    var28_23.expandable = false;
                    var28_23.leftMargin = 0;
                    var28_23.rightMargin = 0;
                    var26_21 = var26_21 != false && ((ActionMenuItemView)var27_22).hasText() != false;
                    var28_23.preventEdgeOffset = var26_21;
                    var2_2 = var28_23.isOverflowButton != false ? 1 : var1_1;
                    var15_19 = ActionMenuView.measureChildForCells((View)var27_22, var20_10, var2_2, var18_8, var14_7);
                    var10_11 = Math.max((int)var10_11, (int)var15_19);
                    var2_2 = var28_23.expandable != false ? var9_12 + 1 : var9_12;
                    var7_6 = var28_23.isOverflowButton != false ? 1 : var8_14;
                    var13_18 = var1_1 - var15_19;
                    var6_5 = Math.max((int)var6_5, (int)var27_22.getMeasuredHeight());
                    if (var15_19 == 1) {
                        var24_20 = 1 << var12_17;
                        var9_12 = var2_2;
                        var22_15 = var24_20 | var22_15;
                        var2_2 = var10_11;
                        var1_1 = var11_13;
                        var8_14 = var7_6;
                        var7_6 = var13_18;
                    } else {
                        var1_1 = var11_13;
                        var8_14 = var10_11;
                        var10_11 = var13_18;
                        var9_12 = var2_2;
                        var2_2 = var8_14;
                        var8_14 = var7_6;
                        var7_6 = var10_11;
                    }
                }
                ++var12_17;
                var11_13 = var1_1;
                var1_1 = var7_6;
                var10_11 = var2_2;
            } while (true);
            var13_18 = var8_14 != 0 && var11_13 == 2 ? 1 : 0;
            var7_6 = 0;
            var12_17 = var1_1;
            do {
                block29 : {
                    if (var9_12 <= 0 || var12_17 <= 0) ** GOTO lbl116
                    var1_1 = Integer.MAX_VALUE;
                    var24_20 = 0;
                    var2_2 = 0;
                    var14_7 = 0;
                    do {
                        if (var14_7 >= var21_16) ** GOTO lbl103
                        var27_22 = (LayoutParams)this.getChildAt(var14_7).getLayoutParams();
                        if (var27_22.expandable) ** GOTO lbl92
                        var15_19 = var2_2;
                        var2_2 = var1_1;
                        var1_1 = var15_19;
                        ** GOTO lbl110
lbl92: // 1 sources:
                        if (var27_22.cellsUsed >= var1_1) ** GOTO lbl97
                        var2_2 = var27_22.cellsUsed;
                        var24_20 = 1 << var14_7;
                        var1_1 = 1;
                        ** GOTO lbl110
lbl97: // 1 sources:
                        if (var27_22.cellsUsed != var1_1) ** GOTO lbl107
                        var24_20 |= (long)(1 << var14_7);
                        var15_19 = var2_2 + 1;
                        var2_2 = var1_1;
                        var1_1 = var15_19;
                        ** GOTO lbl110
lbl103: // 1 sources:
                        var22_15 |= var24_20;
                        if (var2_2 > var12_17) break;
                        var2_2 = var12_17;
                        break block29;
lbl107: // 1 sources:
                        var15_19 = var1_1;
                        var1_1 = var2_2;
                        var2_2 = var15_19;
lbl110: // 4 sources:
                        var15_19 = var14_7 + 1;
                        var14_7 = var2_2;
                        var2_2 = var1_1;
                        var1_1 = var14_7;
                        var14_7 = var15_19;
                    } while (true);
lbl116: // 2 sources:
                    var1_1 = var8_14 == 0 && var11_13 == 1 ? 1 : 0;
                    if (var12_17 > 0 && var22_15 != 0 && (var12_17 < var11_13 - 1 || var1_1 != 0 || var10_11 > 1)) ** GOTO lbl120
                    var8_14 = var7_6;
                    ** GOTO lbl163
lbl120: // 1 sources:
                    var4_25 = var5_24 = (float)Long.bitCount((long)var22_15);
                    if (var1_1 != 0) ** GOTO lbl-1000
                    var3_26 = var5_24;
                    if ((1 & var22_15) != 0) {
                        var3_26 = var5_24;
                        if (!((LayoutParams)this.getChildAt((int)0).getLayoutParams()).preventEdgeOffset) {
                            var3_26 = var5_24 - 0.5f;
                        }
                    }
                    var4_25 = var3_26;
                    if (((long)(1 << var21_16 - 1) & var22_15) == 0) ** GOTO lbl-1000
                    var4_25 = var3_26;
                    if (!((LayoutParams)this.getChildAt((int)(var21_16 - 1)).getLayoutParams()).preventEdgeOffset) {
                        var3_26 -= 0.5f;
                    } else lbl-1000: // 3 sources:
                    {
                        var3_26 = var4_25;
                    }
                    var2_2 = var3_26 > 0.0f ? (int)((float)(var12_17 * var20_10) / var3_26) : 0;
                    var8_14 = 0;
                    var1_1 = var7_6;
                    var7_6 = var8_14;
                    do {
                        var8_14 = var1_1;
                        if (var7_6 >= var21_16) break;
                        if (((long)(1 << var7_6) & var22_15) != 0) {
                            var27_22 = this.getChildAt(var7_6);
                            var28_23 = (LayoutParams)var27_22.getLayoutParams();
                            if (var27_22 instanceof ActionMenuItemView) {
                                var28_23.extraPixels = var2_2;
                                var28_23.expanded = true;
                                if (var7_6 == 0 && !var28_23.preventEdgeOffset) {
                                    var28_23.leftMargin = (- var2_2) / 2;
                                }
                                var1_1 = 1;
                            } else if (var28_23.isOverflowButton) {
                                var28_23.extraPixels = var2_2;
                                var28_23.expanded = true;
                                var28_23.rightMargin = (- var2_2) / 2;
                                var1_1 = 1;
                            } else {
                                if (var7_6 != 0) {
                                    var28_23.leftMargin = var2_2 / 2;
                                }
                                if (var7_6 != var21_16 - 1) {
                                    var28_23.rightMargin = var2_2 / 2;
                                }
                            }
                        }
                        ++var7_6;
                    } while (true);
lbl163: // 2 sources:
                    if (var8_14 != 0) {
                        break;
                    }
                    break block30;
                }
                for (var7_6 = 0; var7_6 < var21_16; ++var7_6) {
                    var27_22 = this.getChildAt(var7_6);
                    var28_23 = (LayoutParams)var27_22.getLayoutParams();
                    if (((long)(1 << var7_6) & var24_20) == 0) {
                        if (var28_23.cellsUsed != var1_1 + 1) continue;
                        var22_15 |= (long)(1 << var7_6);
                        continue;
                    }
                    if (var13_18 != 0 && var28_23.preventEdgeOffset && var2_2 == 1) {
                        var27_22.setPadding(this.mGeneratedItemPadding + var20_10, 0, this.mGeneratedItemPadding, 0);
                    }
                    ++var28_23.cellsUsed;
                    var28_23.expanded = true;
                    --var2_2;
                }
                var7_6 = 1;
                var12_17 = var2_2;
            } while (true);
            for (var1_1 = 0; var1_1 < var21_16; ++var1_1) {
                var27_22 = this.getChildAt(var1_1);
                var28_23 = (LayoutParams)var27_22.getLayoutParams();
                if (!var28_23.expanded) continue;
                var2_2 = var28_23.cellsUsed;
                var27_22.measure(View.MeasureSpec.makeMeasureSpec((int)(var28_23.extraPixels + var2_2 * var20_10), (int)1073741824), var18_8);
            }
        }
        if (var17_3 == 1073741824) {
            var6_5 = var16_4;
        }
        this.setMeasuredDimension(var19_9, var6_5);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void onMeasureExactFormatInSplit(int n2, int n3) {
        int n4 = ActionBarPolicy.get(this.getContext()).getSplitActionBarPadding();
        int n5 = View.MeasureSpec.getMode((int)n3);
        int n6 = View.MeasureSpec.getSize((int)n2);
        int n7 = View.MeasureSpec.getSize((int)n3);
        int n8 = this.getPaddingLeft() + this.getPaddingRight() + n4 * 2;
        int n9 = this.getPaddingTop() + this.getPaddingBottom();
        Log.d((String)"ActionMenuView", (String)("onMeasureExactFormatInSplit:extraPadding=" + n4 + ";widthSize=" + n6 + ";widthPadding=" + n8));
        int n10 = ActionMenuView.getChildMeasureSpec((int)n3, (int)n9, (int)-2);
        n3 = n6 - n8;
        int n11 = this.getChildCount();
        if (n11 == 0) {
            this.setMeasuredDimension(n3, 0);
            return;
        }
        int n12 = n3 / n11;
        n3 = 0;
        n8 = 0;
        n4 = 0;
        n6 = n11;
        while (n4 < n11) {
            int n13;
            View view = this.getChildAt(n4);
            if (view.getVisibility() == 8) {
                n13 = n8;
                n8 = n3;
                n3 = n13;
            } else {
                boolean bl2 = view instanceof ActionMenuItemView;
                LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
                layoutParams.expanded = false;
                layoutParams.extraPixels = 0;
                layoutParams.cellsUsed = 0;
                layoutParams.expandable = false;
                layoutParams.leftMargin = 0;
                layoutParams.rightMargin = 0;
                bl2 = bl2 && ((ActionMenuItemView)view).hasText();
                layoutParams.preventEdgeOffset = bl2;
                int n14 = ActionMenuView.measureChildForCellsInSplit(view, n12, n10, n9);
                n8 = Math.max((int)n8, (int)n14);
                n13 = Math.max((int)n3, (int)view.getMeasuredHeight());
                n6 -= n14;
                n3 = n8;
                n8 = n13;
            }
            n13 = n4 + 1;
            n4 = n8;
            n8 = n3;
            n3 = n4;
            n4 = n13;
        }
        if (n5 == 1073741824) {
            n3 = n7;
        }
        this.setMeasuredDimension(View.MeasureSpec.getSize((int)n2), n3);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams != null && layoutParams instanceof LayoutParams) {
            return true;
        }
        return false;
    }

    public void dismissPopupMenus() {
        if (this.mPresenter != null) {
            this.mPresenter.dismissPopupMenus();
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 16;
        return layoutParams;
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(this.getContext(), attributeSet);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams object) {
        if (object == null) {
            return this.generateDefaultLayoutParams();
        }
        object = object instanceof LayoutParams ? new LayoutParams((LayoutParams)((Object)object)) : new LayoutParams((ViewGroup.LayoutParams)object);
        if (object.gravity <= 0) {
            object.gravity = 16;
        }
        return object;
    }

    public LayoutParams generateOverflowButtonLayoutParams() {
        LayoutParams layoutParams = this.generateDefaultLayoutParams();
        layoutParams.isOverflowButton = true;
        return layoutParams;
    }

    /*
     * Enabled aggressive block sorting
     */
    public Menu getMenu() {
        if (this.mMenu == null) {
            Object object = this.getContext();
            this.mMenu = new MenuBuilder((Context)object);
            this.mMenu.setCallback(new MenuBuilderCallback());
            this.mPresenter = new ActionMenuPresenter((Context)object);
            this.mPresenter.setReserveOverflow(true);
            ActionMenuPresenter actionMenuPresenter = this.mPresenter;
            object = this.mActionMenuPresenterCallback != null ? this.mActionMenuPresenterCallback : new ActionMenuPresenterCallback();
            actionMenuPresenter.setCallback((MenuPresenter.Callback)object);
            this.mMenu.addMenuPresenter(this.mPresenter, this.mPopupContext);
            this.mPresenter.setMenuView(this);
        }
        return this.mMenu;
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    @Override
    public int getWindowAnimations() {
        return 0;
    }

    protected boolean hasSupportDividerBeforeChildAt(int n2) {
        boolean bl2 = false;
        if (n2 == 0) {
            return false;
        }
        View view = this.getChildAt(n2 - 1);
        View view2 = this.getChildAt(n2);
        boolean bl3 = bl2;
        if (n2 < this.getChildCount()) {
            bl3 = bl2;
            if (view instanceof ActionMenuChildView) {
                bl3 = false | ((ActionMenuChildView)view).needsDividerAfter();
            }
        }
        if (n2 > 0 && view2 instanceof ActionMenuChildView) {
            return ((ActionMenuChildView)view2).needsDividerBefore() | bl3;
        }
        return bl3;
    }

    public boolean hideOverflowMenu() {
        if (this.mPresenter != null && this.mPresenter.hideOverflowMenu()) {
            return true;
        }
        return false;
    }

    @Override
    public void initialize(MenuBuilder menuBuilder) {
        this.mMenu = menuBuilder;
    }

    @Override
    public boolean invokeItem(MenuItemImpl menuItemImpl) {
        return this.mMenu.performItemAction(menuItemImpl, 0);
    }

    public boolean isOverflowMenuShowPending() {
        if (this.mPresenter != null && this.mPresenter.isOverflowMenuShowPending()) {
            return true;
        }
        return false;
    }

    public boolean isOverflowMenuShowing() {
        if (this.mPresenter != null && this.mPresenter.isOverflowMenuShowing()) {
            return true;
        }
        return false;
    }

    public boolean isOverflowReserved() {
        return this.mReserveOverflow;
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(configuration);
        }
        if (this.mPresenter != null) {
            this.mPresenter.updateMenuView(false);
            if (this.mPresenter.isOverflowMenuShowing()) {
                this.mPresenter.hideOverflowMenu();
                this.mPresenter.showOverflowMenu();
            }
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.dismissPopupMenus();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void onLayout(boolean bl2, int n2, int n3, int n4, int n5) {
        LayoutParams layoutParams;
        View view;
        if (!this.mFormatItems) {
            super.onLayout(bl2, n2, n3, n4, n5);
            return;
        }
        if (this.mPresenter.isSplit()) {
            this.onLayoutInSplit(n2, n3, n4, n5);
            return;
        }
        int n6 = this.getChildCount();
        int n7 = (n5 - n3) / 2;
        int n8 = this.getDividerWidth();
        n5 = 0;
        n3 = 0;
        int n9 = n4 - n2 - this.getPaddingRight() - this.getPaddingLeft();
        int n10 = 0;
        bl2 = ViewUtils.isLayoutRtl((View)this);
        int n11 = 0;
        while (n11 < n6) {
            int n12;
            int n13;
            view = this.getChildAt(n11);
            if (view.getVisibility() == 8) {
                n13 = n10;
                n12 = n9;
                n9 = n5;
                n10 = n3;
                n5 = n12;
                n3 = n13;
            } else {
                layoutParams = (LayoutParams)view.getLayoutParams();
                if (layoutParams.isOverflowButton) {
                    n10 = n13 = view.getMeasuredWidth();
                    if (this.hasSupportDividerBeforeChildAt(n11)) {
                        n10 = n13 + n8;
                    }
                    int n14 = view.getMeasuredHeight();
                    if (bl2) {
                        n13 = this.getPaddingLeft();
                        n13 = layoutParams.leftMargin + n13;
                        n12 = n13 + n10;
                    } else {
                        n12 = this.getWidth() - this.getPaddingRight() - layoutParams.rightMargin;
                        n13 = n12 - n10;
                    }
                    int n15 = n7 - n14 / 2;
                    view.layout(n13, n15, n12, n14 + n15);
                    n13 = n9 - n10;
                    n12 = 1;
                    n10 = n3;
                    n9 = n5;
                    n3 = n12;
                    n5 = n13;
                } else {
                    n12 = view.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                    n5 = n13 = n5 + n12;
                    if (this.hasSupportDividerBeforeChildAt(n11)) {
                        n5 = n13 + n8;
                    }
                    n13 = n9 - n12;
                    n12 = n3 + 1;
                    n9 = n5;
                    n3 = n10;
                    n5 = n13;
                    n10 = n12;
                }
            }
            n12 = n11 + 1;
            n11 = n9;
            n13 = n10;
            n10 = n3;
            n9 = n5;
            n3 = n13;
            n5 = n11;
            n11 = n12;
        }
        if (n6 == 1 && n10 == 0) {
            view = this.getChildAt(0);
            n3 = view.getMeasuredWidth();
            n5 = view.getMeasuredHeight();
            n2 = (n4 - n2) / 2 - n3 / 2;
            n4 = n7 - n5 / 2;
            view.layout(n2, n4, n3 + n2, n5 + n4);
            return;
        }
        n2 = n10 != 0 ? 0 : 1;
        n2 = (n2 = n3 - n2) > 0 ? n9 / n2 : 0;
        n4 = Math.max((int)0, (int)n2);
        if (bl2) {
            n2 = this.getWidth() - this.getPaddingRight();
            n3 = 0;
            while (n3 < n6) {
                view = this.getChildAt(n3);
                layoutParams = (LayoutParams)view.getLayoutParams();
                if (view.getVisibility() != 8 && !layoutParams.isOverflowButton) {
                    n5 = view.getMeasuredWidth();
                    n10 = view.getMeasuredHeight();
                    n9 = n7 - n10 / 2;
                    view.layout(n2 - n5, n9, n2 -= layoutParams.rightMargin, n10 + n9);
                    n2 -= layoutParams.leftMargin + n5 + n4;
                }
                ++n3;
            }
            return;
        }
        n2 = this.getPaddingLeft();
        n3 = 0;
        while (n3 < n6) {
            view = this.getChildAt(n3);
            layoutParams = (LayoutParams)view.getLayoutParams();
            if (view.getVisibility() != 8 && !layoutParams.isOverflowButton) {
                n5 = view.getMeasuredWidth();
                n10 = view.getMeasuredHeight();
                n9 = n7 - n10 / 2;
                view.layout(n2, n9, (n2 += layoutParams.leftMargin) + n5, n10 + n9);
                n2 = layoutParams.rightMargin + n5 + n4 + n2;
            }
            ++n3;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onLayoutInSplit(int n2, int n3, int n4, int n5) {
        LayoutParams layoutParams;
        View view;
        int n6 = ActionBarPolicy.get(this.getContext()).getSplitActionBarPadding();
        int n7 = this.getChildCount();
        int n8 = (n5 - n3) / 2;
        int n9 = this.getDividerWidth();
        n5 = 0;
        n3 = 0;
        int n10 = n4 - n2 - this.getPaddingRight() - this.getPaddingLeft() - n6 * 2;
        Log.d((String)"ActionMenuView", (String)("onLayoutInSplit:left = " + n2 + "; right = " + n4 + "; widthRemaining = " + n10));
        int n11 = 0;
        boolean bl2 = ViewUtils.isLayoutRtl((View)this);
        int n12 = 0;
        while (n12 < n7) {
            int n13;
            int n14;
            view = this.getChildAt(n12);
            if (view.getVisibility() == 8) {
                n14 = n11;
                n13 = n10;
                n10 = n5;
                n11 = n3;
                n5 = n13;
                n3 = n14;
            } else {
                layoutParams = (LayoutParams)view.getLayoutParams();
                if (layoutParams.isOverflowButton) {
                    n11 = n14 = view.getMeasuredWidth();
                    if (this.hasSupportDividerBeforeChildAt(n12)) {
                        n11 = n14 + n9;
                    }
                    int n15 = view.getMeasuredHeight();
                    if (bl2) {
                        n14 = this.getPaddingLeft();
                        n14 = layoutParams.leftMargin + (n14 + n6);
                        n13 = n14 + n11;
                    } else {
                        n13 = this.getWidth() - n6 - this.getPaddingRight() - layoutParams.rightMargin;
                        n14 = n13 - n11;
                    }
                    int n16 = n8 - n15 / 2;
                    view.layout(n14, n16, n13, n15 + n16);
                    n14 = n10 - n11;
                    n13 = 1;
                    n11 = n3;
                    n10 = n5;
                    n3 = n13;
                    n5 = n14;
                } else {
                    n13 = view.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                    n5 = n14 = n5 + n13;
                    if (this.hasSupportDividerBeforeChildAt(n12)) {
                        n5 = n14 + n9;
                    }
                    n14 = n10 - n13;
                    n13 = n3 + 1;
                    n10 = n5;
                    n3 = n11;
                    n5 = n14;
                    n11 = n13;
                }
            }
            n13 = n12 + 1;
            n12 = n10;
            n14 = n11;
            n11 = n3;
            n10 = n5;
            n3 = n14;
            n5 = n12;
            n12 = n13;
        }
        if (n7 == 1 && n11 == 0) {
            view = this.getChildAt(0);
            n3 = view.getMeasuredWidth();
            n5 = view.getMeasuredHeight();
            n2 = (n4 - n2) / 2 - n3 / 2;
            n4 = n8 - n5 / 2;
            view.layout(n2, n4, n3 + n2, n5 + n4);
            return;
        }
        n2 = n11 != 0 ? 0 : 1;
        n2 = (n2 = n3 - n2) > 0 ? n10 / n2 : 0;
        n4 = Math.max((int)0, (int)n2);
        if (bl2) {
            n2 = this.getWidth() - this.getPaddingRight() - n6;
            n3 = 0;
            while (n3 < n7) {
                view = this.getChildAt(n3);
                layoutParams = (LayoutParams)view.getLayoutParams();
                if (view.getVisibility() != 8 && !layoutParams.isOverflowButton) {
                    n5 = view.getMeasuredWidth();
                    n11 = view.getMeasuredHeight();
                    n10 = n8 - n11 / 2;
                    view.layout(n2 - n5, n10, n2 -= layoutParams.rightMargin, n11 + n10);
                    n2 -= layoutParams.leftMargin + n5 + n4;
                }
                ++n3;
            }
            return;
        }
        n2 = this.getPaddingLeft() + n6;
        n3 = 0;
        while (n3 < n7) {
            view = this.getChildAt(n3);
            layoutParams = (LayoutParams)view.getLayoutParams();
            if (view.getVisibility() != 8 && !layoutParams.isOverflowButton) {
                n5 = view.getMeasuredWidth();
                n11 = view.getMeasuredHeight();
                n10 = n8 - n11 / 2;
                Log.d((String)"ActionMenuView", (String)("layout child:left=" + (n2 += layoutParams.leftMargin) + ";width=" + n5));
                view.layout(n2, n10, n2 + n5, n11 + n10);
                n2 = layoutParams.rightMargin + n5 + n4 + n2;
            }
            ++n3;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void onMeasure(int n2, int n3) {
        boolean bl2 = this.mFormatItems;
        boolean bl3 = View.MeasureSpec.getMode((int)n2) == 1073741824;
        this.mFormatItems = bl3;
        if (bl2 != this.mFormatItems) {
            this.mFormatItemsWidth = 0;
        }
        int n4 = View.MeasureSpec.getSize((int)n2);
        Log.d((String)"ActionMenuView", (String)("onMeasure:widthSize=" + n4 + ";mFormatItems=" + this.mFormatItems + ";mFormatItemsWidth=" + this.mFormatItemsWidth));
        if (this.mFormatItems && this.mMenu != null && n4 != this.mFormatItemsWidth) {
            this.mFormatItemsWidth = n4;
            this.mMenu.onItemsChanged(true);
        }
        int n5 = this.getChildCount();
        if (this.mFormatItems && n5 > 0) {
            this.onMeasureExactFormat(n2, n3);
        } else {
            for (n4 = 0; n4 < n5; ++n4) {
                LayoutParams layoutParams = (LayoutParams)this.getChildAt(n4).getLayoutParams();
                layoutParams.rightMargin = 0;
                layoutParams.leftMargin = 0;
                this.applyFlymeStyle(layoutParams, n4, n5);
            }
            super.onMeasure(n2, n3);
        }
        Log.d((String)"ActionMenuView", (String)("onMeasure:getMeasuredWidth = " + this.getMeasuredWidth() + "; getMeasuredHeight = " + this.getMeasuredHeight()));
    }

    public MenuBuilder peekMenu() {
        return this.mMenu;
    }

    public void setBottonBarStyleDivider() {
        this.setButtonBarStyleDivider();
    }

    public void setButtonBarStyleDivider() {
        this.setShowDividers(2);
        this.setDividerDrawable(this.getResources().getDrawable(R.drawable.mz_button_bar_style_divider));
        this.setDividerPadding(this.getResources().getDimensionPixelSize(R.dimen.mz_button_bar_style_divider_padding));
    }

    public void setExpandedActionViewsExclusive(boolean bl2) {
        this.mPresenter.setExpandedActionViewsExclusive(bl2);
    }

    public void setHasOverflow(boolean bl2) {
        this.mHasOverflow = bl2;
    }

    public void setMenuCallbacks(MenuPresenter.Callback callback, MenuBuilder.Callback callback2) {
        this.mActionMenuPresenterCallback = callback;
        this.mMenuBuilderCallback = callback2;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.mOnMenuItemClickListener = onMenuItemClickListener;
    }

    public void setOverflowDrawable(Drawable drawable2) {
        if (this.mPresenter != null) {
            this.mPresenter.setOverflowDrawable(drawable2);
        }
    }

    public void setOverflowReserved(boolean bl2) {
        this.mReserveOverflow = bl2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void setPopupTheme(int n2) {
        if (this.mPopupTheme == n2) return;
        this.mPopupTheme = n2;
        if (n2 == 0) {
            this.mPopupContext = this.mContext;
            return;
        }
        this.mPopupContext = new ContextThemeWrapper(this.mContext, n2);
    }

    public void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.mPresenter = actionMenuPresenter;
        this.mPresenter.setMenuView(this);
    }

    public boolean showOverflowMenu() {
        if (this.mPresenter != null && this.mPresenter.showOverflowMenu()) {
            return true;
        }
        return false;
    }

    public static interface ActionMenuChildView {
        public boolean needsDividerAfter();

        public boolean needsDividerBefore();
    }

    class ActionMenuPresenterCallback
    implements MenuPresenter.Callback {
        private ActionMenuPresenterCallback() {
        }

        @Override
        public void onCloseMenu(MenuBuilder menuBuilder, boolean bl2) {
        }

        @Override
        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            return false;
        }
    }

    public static class LayoutParams
    extends LinearLayoutCompat.LayoutParams {
        @ViewDebug.ExportedProperty
        public int cellsUsed;
        @ViewDebug.ExportedProperty
        public boolean expandable;
        boolean expanded;
        @ViewDebug.ExportedProperty
        public int extraPixels;
        @ViewDebug.ExportedProperty
        public boolean isOverflowButton;
        @ViewDebug.ExportedProperty
        public boolean preventEdgeOffset;

        public LayoutParams(int n2, int n3) {
            super(n2, n3);
            this.isOverflowButton = false;
        }

        LayoutParams(int n2, int n3, boolean bl2) {
            super(n2, n3);
            this.isOverflowButton = bl2;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.LayoutParams)layoutParams);
            this.isOverflowButton = layoutParams.isOverflowButton;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    class MenuBuilderCallback
    implements MenuBuilder.Callback {
        private MenuBuilderCallback() {
        }

        @Override
        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            if (ActionMenuView.this.mOnMenuItemClickListener != null && ActionMenuView.this.mOnMenuItemClickListener.onMenuItemClick(menuItem)) {
                return true;
            }
            return false;
        }

        @Override
        public void onMenuModeChange(MenuBuilder menuBuilder) {
            if (ActionMenuView.this.mMenuBuilderCallback != null) {
                ActionMenuView.this.mMenuBuilderCallback.onMenuModeChange(menuBuilder);
            }
        }
    }

    public static interface OnMenuItemClickListener {
        public boolean onMenuItemClick(MenuItem var1);
    }

}

