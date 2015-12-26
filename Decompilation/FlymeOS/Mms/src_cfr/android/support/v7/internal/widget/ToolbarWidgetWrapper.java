/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.os.Parcelable
 *  android.text.TextUtils
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.util.Log
 *  android.util.SparseArray
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewParent
 *  android.view.Window
 *  android.view.Window$Callback
 *  android.view.animation.Interpolator
 *  android.widget.SpinnerAdapter
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.ActionBarPolicy;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.support.v7.internal.widget.AbsActionBarView;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.internal.widget.DecorToolbar;
import android.support.v7.internal.widget.ScrollingTabContainerView;
import android.support.v7.internal.widget.SpinnerCompat;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.internal.widget.ToolbarWidgetWrapper$1;
import android.support.v7.internal.widget.ToolbarWidgetWrapper$2;
import android.support.v7.internal.widget.ToolbarWidgetWrapper$3;
import android.support.v7.widget.ActionMenuPresenter;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.animation.Interpolator;
import android.widget.SpinnerAdapter;

public class ToolbarWidgetWrapper
implements DecorToolbar {
    private static final int AFFECTS_LOGO_MASK = 3;
    private static final String TAG = "ToolbarWidgetWrapper";
    private ActionMenuPresenter mActionMenuPresenter;
    private View mCustomView;
    private int mDefaultNavigationContentDescription = 0;
    private Drawable mDefaultNavigationIcon;
    private int mDisplayOpts;
    private CharSequence mHomeDescription;
    private Drawable mIcon;
    private Drawable mLogo;
    private boolean mMenuPrepared;
    private Drawable mNavIcon;
    private int mNavigationMode = 0;
    private SpinnerCompat mSpinner;
    protected boolean mSplitActionBar;
    protected boolean mSplitWhenNarrow;
    private CharSequence mSubtitle;
    private View mTabView;
    private final TintManager mTintManager;
    private CharSequence mTitle;
    private boolean mTitleSet;
    private Toolbar mToolbar;
    private Window.Callback mWindowCallback;

    public ToolbarWidgetWrapper(Toolbar toolbar, boolean bl2) {
        this(toolbar, bl2, R.string.abc_action_bar_up_description, R.drawable.abc_ic_ab_back_mtrl_am_alpha);
    }

    /*
     * Enabled aggressive block sorting
     */
    public ToolbarWidgetWrapper(Toolbar object, boolean bl2, int n2, int n3) {
        this.mToolbar = object;
        this.mTitle = object.getTitle();
        this.mSubtitle = object.getSubtitle();
        boolean bl3 = this.mTitle != null;
        this.mTitleSet = bl3;
        this.mNavIcon = object.getNavigationIcon();
        if (bl2) {
            CharSequence charSequence = (object = TintTypedArray.obtainStyledAttributes(object.getContext(), null, R.styleable.ActionBar, R.attr.actionBarStyle, 0)).getText(R.styleable.ActionBar_title);
            if (!TextUtils.isEmpty((CharSequence)charSequence)) {
                this.setTitle(charSequence);
            }
            if (!TextUtils.isEmpty((CharSequence)(charSequence = object.getText(R.styleable.ActionBar_subtitle)))) {
                this.setSubtitle(charSequence);
            }
            if ((charSequence = object.getDrawable(R.styleable.ActionBar_logo)) != null) {
                this.setLogo((Drawable)charSequence);
            }
            charSequence = object.getDrawable(R.styleable.ActionBar_icon);
            if (this.mNavIcon == null && charSequence != null) {
                this.setIcon((Drawable)charSequence);
            }
            if ((charSequence = object.getDrawable(R.styleable.ActionBar_homeAsUpIndicator)) != null) {
                this.setNavigationIcon((Drawable)charSequence);
            }
            this.setDisplayOptions(object.getInt(R.styleable.ActionBar_displayOptions, 0));
            int n4 = object.getResourceId(R.styleable.ActionBar_customNavigationLayout, 0);
            if (n4 != 0) {
                this.setCustomView(LayoutInflater.from((Context)this.mToolbar.getContext()).inflate(n4, (ViewGroup)this.mToolbar, false));
                this.setDisplayOptions(this.mDisplayOpts | 16);
            }
            if ((n4 = object.getLayoutDimension(R.styleable.ActionBar_height, 0)) > 0) {
                charSequence = this.mToolbar.getLayoutParams();
                charSequence.height = n4;
                this.mToolbar.setLayoutParams((ViewGroup.LayoutParams)charSequence);
            }
            n4 = object.getDimensionPixelOffset(R.styleable.ActionBar_contentInsetStart, -1);
            int n5 = object.getDimensionPixelOffset(R.styleable.ActionBar_contentInsetEnd, -1);
            if (n4 >= 0 || n5 >= 0) {
                this.mToolbar.setContentInsetsRelative(Math.max((int)n4, (int)0), Math.max((int)n5, (int)0));
            }
            if ((n4 = object.getResourceId(R.styleable.ActionBar_titleTextStyle, 0)) != 0) {
                this.mToolbar.setTitleTextAppearance(this.mToolbar.getContext(), n4);
            }
            if ((n4 = object.getResourceId(R.styleable.ActionBar_subtitleTextStyle, 0)) != 0) {
                this.mToolbar.setSubtitleTextAppearance(this.mToolbar.getContext(), n4);
            }
            if ((n4 = object.getResourceId(R.styleable.ActionBar_popupTheme, 0)) != 0) {
                this.mToolbar.setPopupTheme(n4);
            }
            object.recycle();
            this.mTintManager = object.getTintManager();
        } else {
            this.mDisplayOpts = this.detectDisplayOptions();
            this.mTintManager = TintManager.get(object.getContext());
        }
        this.setDefaultNavigationContentDescription(n2);
        this.mHomeDescription = this.mToolbar.getNavigationContentDescription();
        this.setDefaultNavigationIcon(this.mTintManager.getDrawable(n3));
        this.mToolbar.setNavigationOnClickListener(new ToolbarWidgetWrapper$1(this));
    }

    static /* synthetic */ Toolbar access$000(ToolbarWidgetWrapper toolbarWidgetWrapper) {
        return toolbarWidgetWrapper.mToolbar;
    }

    static /* synthetic */ CharSequence access$100(ToolbarWidgetWrapper toolbarWidgetWrapper) {
        return toolbarWidgetWrapper.mTitle;
    }

    static /* synthetic */ Window.Callback access$200(ToolbarWidgetWrapper toolbarWidgetWrapper) {
        return toolbarWidgetWrapper.mWindowCallback;
    }

    static /* synthetic */ boolean access$300(ToolbarWidgetWrapper toolbarWidgetWrapper) {
        return toolbarWidgetWrapper.mMenuPrepared;
    }

    private int detectDisplayOptions() {
        int n2 = 11;
        if (this.mToolbar.getNavigationIcon() != null) {
            n2 = 15;
        }
        return n2;
    }

    private void ensureSpinner() {
        if (this.mSpinner == null) {
            this.mSpinner = new SpinnerCompat(this.getContext(), null, R.attr.actionDropDownStyle);
            Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(-2, -2, 8388627);
            this.mSpinner.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
        }
    }

    private void setTitleInt(CharSequence charSequence) {
        this.mTitle = charSequence;
        if ((this.mDisplayOpts & 8) != 0) {
            this.mToolbar.setTitle(charSequence);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void updateHomeAccessibility() {
        if ((this.mDisplayOpts & 4) == 0) return;
        if (TextUtils.isEmpty((CharSequence)this.mHomeDescription)) {
            this.mToolbar.setNavigationContentDescription(this.mDefaultNavigationContentDescription);
            return;
        }
        this.mToolbar.setNavigationContentDescription(this.mHomeDescription);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateNavigationIcon() {
        if ((this.mDisplayOpts & 4) != 0) {
            Toolbar toolbar = this.mToolbar;
            Drawable drawable2 = this.mNavIcon != null ? this.mNavIcon : this.mDefaultNavigationIcon;
            toolbar.setNavigationIcon(drawable2);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateToolbarLogo() {
        Drawable drawable2 = null;
        if ((this.mDisplayOpts & 2) != 0) {
            drawable2 = (this.mDisplayOpts & 1) != 0 ? (this.mLogo != null ? this.mLogo : this.mIcon) : this.mIcon;
        }
        this.mToolbar.setLogo(drawable2);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void animateToVisibility(int n2) {
        if (n2 == 8) {
            ViewCompat.animate((View)this.mToolbar).alpha(0.0f).setListener(new ToolbarWidgetWrapper$2(this)).setInterpolator(AbsActionBarView.sAlphaInterpolator).setDuration(160).start();
        } else if (n2 == 0) {
            ViewCompat.animate((View)this.mToolbar).alpha(1.0f).setListener(new ToolbarWidgetWrapper$3(this)).setInterpolator(AbsActionBarView.sAlphaInterpolator).setDuration(160).start();
        }
        if (this.mToolbar != null) {
            this.mToolbar.setMenuViewAnimateToVisibility(n2);
        }
    }

    @Override
    public boolean canShowOverflowMenu() {
        return this.mToolbar.canShowOverflowMenu();
    }

    @Override
    public boolean canSplit() {
        return true;
    }

    @Override
    public void collapseActionView() {
        this.mToolbar.collapseActionView();
    }

    @Override
    public void dismissPopupMenus() {
        this.mToolbar.dismissPopupMenus();
    }

    @Override
    public Context getContext() {
        return this.mToolbar.getContext();
    }

    @Override
    public View getCustomView() {
        return this.mCustomView;
    }

    @Override
    public int getDisplayOptions() {
        return this.mDisplayOpts;
    }

    @Override
    public int getDropdownItemCount() {
        if (this.mSpinner != null) {
            return this.mSpinner.getCount();
        }
        return 0;
    }

    @Override
    public int getDropdownSelectedPosition() {
        if (this.mSpinner != null) {
            return this.mSpinner.getSelectedItemPosition();
        }
        return 0;
    }

    @Override
    public int getHeight() {
        return this.mToolbar.getHeight();
    }

    @Override
    public Menu getMenu() {
        return this.mToolbar.getMenu();
    }

    @Override
    public int getNavigationMode() {
        return this.mNavigationMode;
    }

    @Override
    public int getPopupTheme() {
        return this.mToolbar.getPopupTheme();
    }

    @Override
    public CharSequence getSubtitle() {
        return this.mToolbar.getSubtitle();
    }

    @Override
    public CharSequence getTitle() {
        return this.mToolbar.getTitle();
    }

    @Override
    public ViewGroup getViewGroup() {
        return this.mToolbar;
    }

    @Override
    public int getVisibility() {
        return this.mToolbar.getVisibility();
    }

    @Override
    public boolean hasEmbeddedTabs() {
        if (this.mTabView != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasExpandedActionView() {
        return this.mToolbar.hasExpandedActionView();
    }

    @Override
    public boolean hasIcon() {
        if (this.mIcon != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasLogo() {
        if (this.mLogo != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hideOverflowMenu() {
        return this.mToolbar.hideOverflowMenu();
    }

    @Override
    public void initIndeterminateProgress() {
        Log.i((String)"ToolbarWidgetWrapper", (String)"Progress display unsupported");
    }

    @Override
    public void initProgress() {
        Log.i((String)"ToolbarWidgetWrapper", (String)"Progress display unsupported");
    }

    @Override
    public boolean isOverflowMenuShowPending() {
        return this.mToolbar.isOverflowMenuShowPending();
    }

    @Override
    public boolean isOverflowMenuShowing() {
        return this.mToolbar.isOverflowMenuShowing();
    }

    @Override
    public boolean isSplit() {
        return this.mSplitActionBar;
    }

    @Override
    public boolean isTitleTruncated() {
        return this.mToolbar.isTitleTruncated();
    }

    @Override
    public void restoreHierarchyState(SparseArray<Parcelable> sparseArray) {
        this.mToolbar.restoreHierarchyState(sparseArray);
    }

    @Override
    public void saveHierarchyState(SparseArray<Parcelable> sparseArray) {
        this.mToolbar.saveHierarchyState(sparseArray);
    }

    @Override
    public void setBackgroundDrawable(Drawable drawable2) {
        this.mToolbar.setBackgroundDrawable(drawable2);
    }

    @Override
    public void setCollapsible(boolean bl2) {
        this.mToolbar.setCollapsible(bl2);
    }

    @Override
    public void setCustomView(View view) {
        if (this.mCustomView != null && (this.mDisplayOpts & 16) != 0) {
            this.mToolbar.removeView(this.mCustomView);
        }
        this.mCustomView = view;
        if (view != null && (this.mDisplayOpts & 16) != 0) {
            this.mToolbar.addView(this.mCustomView);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void setDefaultNavigationContentDescription(int n2) {
        if (n2 == this.mDefaultNavigationContentDescription) {
            return;
        }
        this.mDefaultNavigationContentDescription = n2;
        if (!TextUtils.isEmpty((CharSequence)this.mToolbar.getNavigationContentDescription())) return;
        this.setNavigationContentDescription(this.mDefaultNavigationContentDescription);
    }

    @Override
    public void setDefaultNavigationIcon(Drawable drawable2) {
        if (this.mDefaultNavigationIcon != drawable2) {
            this.mDefaultNavigationIcon = drawable2;
            this.updateNavigationIcon();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setDisplayOptions(int n2) {
        int n3 = this.mDisplayOpts ^ n2;
        this.mDisplayOpts = n2;
        if (n3 == 0) return;
        {
            if ((n3 & 4) != 0) {
                if ((n2 & 4) != 0) {
                    this.updateNavigationIcon();
                    this.updateHomeAccessibility();
                } else {
                    this.mToolbar.setNavigationIcon(null);
                }
            }
            if ((n3 & 3) != 0) {
                this.updateToolbarLogo();
            }
            if ((n3 & 8) != 0) {
                if ((n2 & 8) != 0) {
                    this.mToolbar.setTitle(this.mTitle);
                    this.mToolbar.setSubtitle(this.mSubtitle);
                } else {
                    this.mToolbar.setTitle(null);
                    this.mToolbar.setSubtitle(null);
                }
            }
            if ((n3 & 16) != 0 && this.mCustomView != null) {
                if ((n2 & 16) != 0) {
                    this.mToolbar.addView(this.mCustomView);
                } else {
                    this.mToolbar.removeView(this.mCustomView);
                }
            }
            if ((n3 & 64) == 0 || this.mTabView == null) return;
            {
                if ((n2 & 64) != 0) {
                    if (this.mTabView == null || this.mNavigationMode != 2) return;
                    {
                        this.mToolbar.addView(this.mTabView, 0);
                        Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams)this.mTabView.getLayoutParams();
                        layoutParams.width = -2;
                        layoutParams.height = -2;
                        layoutParams.gravity = 8388691;
                        ((ScrollingTabContainerView)this.mTabView).setAllowCollapse(false);
                        ((ScrollingTabContainerView)this.mTabView).showAtToolbar(true);
                        return;
                    }
                } else {
                    if (this.mTabView == null || this.mTabView.getParent() != this.mToolbar) return;
                    {
                        this.mToolbar.removeView(this.mTabView);
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void setDropdownParams(SpinnerAdapter spinnerAdapter, AdapterViewCompat.OnItemSelectedListener onItemSelectedListener) {
        this.ensureSpinner();
        this.mSpinner.setAdapter(spinnerAdapter);
        this.mSpinner.setOnItemSelectedListener(onItemSelectedListener);
    }

    @Override
    public void setDropdownSelectedPosition(int n2) {
        if (this.mSpinner == null) {
            throw new IllegalStateException("Can't set dropdown selected position without an adapter");
        }
        this.mSpinner.setSelection(n2);
    }

    @Override
    public void setEmbeddedTabView(ScrollingTabContainerView scrollingTabContainerView) {
        if (this.mTabView != null && this.mTabView.getParent() == this.mToolbar) {
            this.mToolbar.removeView(this.mTabView);
        }
        this.mTabView = scrollingTabContainerView;
        if (scrollingTabContainerView != null && this.mNavigationMode == 2) {
            this.mToolbar.addView(this.mTabView, 0);
            Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams)this.mTabView.getLayoutParams();
            layoutParams.width = -2;
            layoutParams.height = -2;
            layoutParams.gravity = 8388691;
            scrollingTabContainerView.setAllowCollapse(false);
            scrollingTabContainerView.showAtToolbar(true);
        }
    }

    @Override
    public void setHomeButtonEnabled(boolean bl2) {
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setIcon(int n2) {
        Drawable drawable2 = n2 != 0 ? this.mTintManager.getDrawable(n2) : null;
        this.setIcon(drawable2);
    }

    @Override
    public void setIcon(Drawable drawable2) {
        this.mIcon = drawable2;
        this.updateToolbarLogo();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setLogo(int n2) {
        Drawable drawable2 = n2 != 0 ? this.mTintManager.getDrawable(n2) : null;
        this.setLogo(drawable2);
    }

    @Override
    public void setLogo(Drawable drawable2) {
        this.mLogo = drawable2;
        this.updateToolbarLogo();
    }

    @Override
    public void setMenu(Menu menu, MenuPresenter.Callback callback) {
        if (this.mActionMenuPresenter == null) {
            this.mActionMenuPresenter = new ActionMenuPresenter(this.mToolbar.getContext());
            this.mActionMenuPresenter.setId(R.id.action_menu_presenter);
            if (this.mSplitActionBar) {
                this.mActionMenuPresenter.setExpandedActionViewsExclusive(true);
                int n2 = ActionBarPolicy.get(this.getContext()).getSplitActionBarPadding();
                this.mActionMenuPresenter.setWidthLimit(this.getContext().getResources().getDisplayMetrics().widthPixels - n2 * 2, true);
                this.mActionMenuPresenter.setItemLimit(Integer.MAX_VALUE);
                this.mActionMenuPresenter.setIsSplit(true);
            }
        }
        this.mActionMenuPresenter.setCallback(callback);
        this.mToolbar.setMenu((MenuBuilder)menu, this.mActionMenuPresenter);
    }

    @Override
    public void setMenuCallbacks(MenuPresenter.Callback callback, MenuBuilder.Callback callback2) {
        this.mToolbar.setMenuCallbacks(callback, callback2);
    }

    @Override
    public void setMenuPrepared() {
        this.mMenuPrepared = true;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setNavigationContentDescription(int n2) {
        String string2 = n2 == 0 ? null : this.getContext().getString(n2);
        this.setNavigationContentDescription(string2);
    }

    @Override
    public void setNavigationContentDescription(CharSequence charSequence) {
        this.mHomeDescription = charSequence;
        this.updateHomeAccessibility();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setNavigationIcon(int n2) {
        Drawable drawable2 = n2 != 0 ? this.mTintManager.getDrawable(n2) : null;
        this.setNavigationIcon(drawable2);
    }

    @Override
    public void setNavigationIcon(Drawable drawable2) {
        this.mNavIcon = drawable2;
        this.updateNavigationIcon();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void setNavigationMode(int var1_1) {
        var2_2 = this.mNavigationMode;
        if (var1_1 == var2_2) return;
        switch (var2_2) {
            case 1: {
                if (this.mSpinner != null && this.mSpinner.getParent() == this.mToolbar) {
                    this.mToolbar.removeView((View)this.mSpinner);
                }
            }
            default: {
                ** GOTO lbl12
            }
            case 2: 
        }
        if (this.mTabView != null && this.mTabView.getParent() == this.mToolbar) {
            this.mToolbar.removeView(this.mTabView);
        }
lbl12: // 4 sources:
        this.mNavigationMode = var1_1;
        switch (var1_1) {
            default: {
                throw new IllegalArgumentException("Invalid navigation mode " + var1_1);
            }
            case 1: {
                this.ensureSpinner();
                this.mToolbar.addView((View)this.mSpinner, 0);
            }
            case 0: {
                return;
            }
            case 2: 
        }
        if (this.mTabView == null) return;
        this.mToolbar.addView(this.mTabView, 0);
        var3_3 = (Toolbar.LayoutParams)this.mTabView.getLayoutParams();
        var3_3.width = -2;
        var3_3.height = -2;
        var3_3.gravity = 8388691;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setSplitToolbar(boolean bl2) {
        if (this.mSplitActionBar != bl2) {
            this.mSplitActionBar = bl2;
            this.mToolbar.setSplitToolbar(bl2);
            if (this.mActionMenuPresenter != null) {
                if (!bl2) {
                    this.mActionMenuPresenter.setExpandedActionViewsExclusive(false);
                } else {
                    this.mActionMenuPresenter.setExpandedActionViewsExclusive(true);
                    int n2 = ActionBarPolicy.get(this.getContext()).getSplitActionBarPadding();
                    this.mActionMenuPresenter.setWidthLimit(this.getContext().getResources().getDisplayMetrics().widthPixels - n2 * 2, true);
                    this.mActionMenuPresenter.setItemLimit(Integer.MAX_VALUE);
                }
                this.mActionMenuPresenter.setIsSplit(bl2);
            }
        }
    }

    @Override
    public void setSplitView(ViewGroup viewGroup) {
        this.mToolbar.setSplitView(viewGroup);
    }

    @Override
    public void setSplitWhenNarrow(boolean bl2) {
        this.mSplitWhenNarrow = bl2;
    }

    @Override
    public void setSubtitle(CharSequence charSequence) {
        this.mSubtitle = charSequence;
        if ((this.mDisplayOpts & 8) != 0) {
            this.mToolbar.setSubtitle(charSequence);
        }
    }

    @Override
    public void setTitle(CharSequence charSequence) {
        this.mTitleSet = true;
        this.setTitleInt(charSequence);
    }

    @Override
    public void setVisibility(int n2) {
        this.mToolbar.setVisibility(n2);
    }

    @Override
    public void setWindowCallback(Window.Callback callback) {
        this.mWindowCallback = callback;
    }

    @Override
    public void setWindowTitle(CharSequence charSequence) {
        if (!this.mTitleSet) {
            this.setTitleInt(charSequence);
        }
    }

    @Override
    public boolean showOverflowMenu() {
        return this.mToolbar.showOverflowMenu();
    }
}

