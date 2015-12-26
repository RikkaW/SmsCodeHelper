package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.drawable;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.string;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.internal.view.ActionBarPolicy;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuBuilder.Callback;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.support.v7.widget.ActionMenuPresenter;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.LayoutParams;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window.Callback;
import android.widget.SpinnerAdapter;

public class ToolbarWidgetWrapper
  implements DecorToolbar
{
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
  
  public ToolbarWidgetWrapper(Toolbar paramToolbar, boolean paramBoolean)
  {
    this(paramToolbar, paramBoolean, R.string.abc_action_bar_up_description, R.drawable.abc_ic_ab_back_mtrl_am_alpha);
  }
  
  public ToolbarWidgetWrapper(Toolbar paramToolbar, boolean paramBoolean, int paramInt1, int paramInt2)
  {
    mToolbar = paramToolbar;
    mTitle = paramToolbar.getTitle();
    mSubtitle = paramToolbar.getSubtitle();
    boolean bool;
    if (mTitle != null)
    {
      bool = true;
      mTitleSet = bool;
      mNavIcon = paramToolbar.getNavigationIcon();
      if (!paramBoolean) {
        break label492;
      }
      paramToolbar = TintTypedArray.obtainStyledAttributes(paramToolbar.getContext(), null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
      Object localObject = paramToolbar.getText(R.styleable.ActionBar_title);
      if (!TextUtils.isEmpty((CharSequence)localObject)) {
        setTitle((CharSequence)localObject);
      }
      localObject = paramToolbar.getText(R.styleable.ActionBar_subtitle);
      if (!TextUtils.isEmpty((CharSequence)localObject)) {
        setSubtitle((CharSequence)localObject);
      }
      localObject = paramToolbar.getDrawable(R.styleable.ActionBar_logo);
      if (localObject != null) {
        setLogo((Drawable)localObject);
      }
      localObject = paramToolbar.getDrawable(R.styleable.ActionBar_icon);
      if ((mNavIcon == null) && (localObject != null)) {
        setIcon((Drawable)localObject);
      }
      localObject = paramToolbar.getDrawable(R.styleable.ActionBar_homeAsUpIndicator);
      if (localObject != null) {
        setNavigationIcon((Drawable)localObject);
      }
      setDisplayOptions(paramToolbar.getInt(R.styleable.ActionBar_displayOptions, 0));
      int i = paramToolbar.getResourceId(R.styleable.ActionBar_customNavigationLayout, 0);
      if (i != 0)
      {
        setCustomView(LayoutInflater.from(mToolbar.getContext()).inflate(i, mToolbar, false));
        setDisplayOptions(mDisplayOpts | 0x10);
      }
      i = paramToolbar.getLayoutDimension(R.styleable.ActionBar_height, 0);
      if (i > 0)
      {
        localObject = mToolbar.getLayoutParams();
        height = i;
        mToolbar.setLayoutParams((ViewGroup.LayoutParams)localObject);
      }
      i = paramToolbar.getDimensionPixelOffset(R.styleable.ActionBar_contentInsetStart, -1);
      int j = paramToolbar.getDimensionPixelOffset(R.styleable.ActionBar_contentInsetEnd, -1);
      if ((i >= 0) || (j >= 0)) {
        mToolbar.setContentInsetsRelative(Math.max(i, 0), Math.max(j, 0));
      }
      i = paramToolbar.getResourceId(R.styleable.ActionBar_titleTextStyle, 0);
      if (i != 0) {
        mToolbar.setTitleTextAppearance(mToolbar.getContext(), i);
      }
      i = paramToolbar.getResourceId(R.styleable.ActionBar_subtitleTextStyle, 0);
      if (i != 0) {
        mToolbar.setSubtitleTextAppearance(mToolbar.getContext(), i);
      }
      i = paramToolbar.getResourceId(R.styleable.ActionBar_popupTheme, 0);
      if (i != 0) {
        mToolbar.setPopupTheme(i);
      }
      paramToolbar.recycle();
    }
    for (mTintManager = paramToolbar.getTintManager();; mTintManager = TintManager.get(paramToolbar.getContext()))
    {
      setDefaultNavigationContentDescription(paramInt1);
      mHomeDescription = mToolbar.getNavigationContentDescription();
      setDefaultNavigationIcon(mTintManager.getDrawable(paramInt2));
      mToolbar.setNavigationOnClickListener(new ToolbarWidgetWrapper.1(this));
      return;
      bool = false;
      break;
      label492:
      mDisplayOpts = detectDisplayOptions();
    }
  }
  
  private int detectDisplayOptions()
  {
    int i = 11;
    if (mToolbar.getNavigationIcon() != null) {
      i = 15;
    }
    return i;
  }
  
  private void ensureSpinner()
  {
    if (mSpinner == null)
    {
      mSpinner = new SpinnerCompat(getContext(), null, R.attr.actionDropDownStyle);
      Toolbar.LayoutParams localLayoutParams = new Toolbar.LayoutParams(-2, -2, 8388627);
      mSpinner.setLayoutParams(localLayoutParams);
    }
  }
  
  private void setTitleInt(CharSequence paramCharSequence)
  {
    mTitle = paramCharSequence;
    if ((mDisplayOpts & 0x8) != 0) {
      mToolbar.setTitle(paramCharSequence);
    }
  }
  
  private void updateHomeAccessibility()
  {
    if ((mDisplayOpts & 0x4) != 0)
    {
      if (TextUtils.isEmpty(mHomeDescription)) {
        mToolbar.setNavigationContentDescription(mDefaultNavigationContentDescription);
      }
    }
    else {
      return;
    }
    mToolbar.setNavigationContentDescription(mHomeDescription);
  }
  
  private void updateNavigationIcon()
  {
    Toolbar localToolbar;
    if ((mDisplayOpts & 0x4) != 0)
    {
      localToolbar = mToolbar;
      if (mNavIcon == null) {
        break label32;
      }
    }
    label32:
    for (Drawable localDrawable = mNavIcon;; localDrawable = mDefaultNavigationIcon)
    {
      localToolbar.setNavigationIcon(localDrawable);
      return;
    }
  }
  
  private void updateToolbarLogo()
  {
    Drawable localDrawable = null;
    if ((mDisplayOpts & 0x2) != 0)
    {
      if ((mDisplayOpts & 0x1) == 0) {
        break label49;
      }
      if (mLogo == null) {
        break label41;
      }
      localDrawable = mLogo;
    }
    for (;;)
    {
      mToolbar.setLogo(localDrawable);
      return;
      label41:
      localDrawable = mIcon;
      continue;
      label49:
      localDrawable = mIcon;
    }
  }
  
  public void animateToVisibility(int paramInt)
  {
    if (paramInt == 8) {
      ViewCompat.animate(mToolbar).alpha(0.0F).setListener(new ToolbarWidgetWrapper.2(this)).setInterpolator(AbsActionBarView.sAlphaInterpolator).setDuration(160L).start();
    }
    for (;;)
    {
      if (mToolbar != null) {
        mToolbar.setMenuViewAnimateToVisibility(paramInt);
      }
      return;
      if (paramInt == 0) {
        ViewCompat.animate(mToolbar).alpha(1.0F).setListener(new ToolbarWidgetWrapper.3(this)).setInterpolator(AbsActionBarView.sAlphaInterpolator).setDuration(160L).start();
      }
    }
  }
  
  public boolean canShowOverflowMenu()
  {
    return mToolbar.canShowOverflowMenu();
  }
  
  public boolean canSplit()
  {
    return true;
  }
  
  public void collapseActionView()
  {
    mToolbar.collapseActionView();
  }
  
  public void dismissPopupMenus()
  {
    mToolbar.dismissPopupMenus();
  }
  
  public Context getContext()
  {
    return mToolbar.getContext();
  }
  
  public View getCustomView()
  {
    return mCustomView;
  }
  
  public int getDisplayOptions()
  {
    return mDisplayOpts;
  }
  
  public int getDropdownItemCount()
  {
    if (mSpinner != null) {
      return mSpinner.getCount();
    }
    return 0;
  }
  
  public int getDropdownSelectedPosition()
  {
    if (mSpinner != null) {
      return mSpinner.getSelectedItemPosition();
    }
    return 0;
  }
  
  public int getHeight()
  {
    return mToolbar.getHeight();
  }
  
  public Menu getMenu()
  {
    return mToolbar.getMenu();
  }
  
  public int getNavigationMode()
  {
    return mNavigationMode;
  }
  
  public int getPopupTheme()
  {
    return mToolbar.getPopupTheme();
  }
  
  public CharSequence getSubtitle()
  {
    return mToolbar.getSubtitle();
  }
  
  public CharSequence getTitle()
  {
    return mToolbar.getTitle();
  }
  
  public ViewGroup getViewGroup()
  {
    return mToolbar;
  }
  
  public int getVisibility()
  {
    return mToolbar.getVisibility();
  }
  
  public boolean hasEmbeddedTabs()
  {
    return mTabView != null;
  }
  
  public boolean hasExpandedActionView()
  {
    return mToolbar.hasExpandedActionView();
  }
  
  public boolean hasIcon()
  {
    return mIcon != null;
  }
  
  public boolean hasLogo()
  {
    return mLogo != null;
  }
  
  public boolean hideOverflowMenu()
  {
    return mToolbar.hideOverflowMenu();
  }
  
  public void initIndeterminateProgress()
  {
    Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
  }
  
  public void initProgress()
  {
    Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
  }
  
  public boolean isOverflowMenuShowPending()
  {
    return mToolbar.isOverflowMenuShowPending();
  }
  
  public boolean isOverflowMenuShowing()
  {
    return mToolbar.isOverflowMenuShowing();
  }
  
  public boolean isSplit()
  {
    return mSplitActionBar;
  }
  
  public boolean isTitleTruncated()
  {
    return mToolbar.isTitleTruncated();
  }
  
  public void restoreHierarchyState(SparseArray<Parcelable> paramSparseArray)
  {
    mToolbar.restoreHierarchyState(paramSparseArray);
  }
  
  public void saveHierarchyState(SparseArray<Parcelable> paramSparseArray)
  {
    mToolbar.saveHierarchyState(paramSparseArray);
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    mToolbar.setBackgroundDrawable(paramDrawable);
  }
  
  public void setCollapsible(boolean paramBoolean)
  {
    mToolbar.setCollapsible(paramBoolean);
  }
  
  public void setCustomView(View paramView)
  {
    if ((mCustomView != null) && ((mDisplayOpts & 0x10) != 0)) {
      mToolbar.removeView(mCustomView);
    }
    mCustomView = paramView;
    if ((paramView != null) && ((mDisplayOpts & 0x10) != 0)) {
      mToolbar.addView(mCustomView);
    }
  }
  
  public void setDefaultNavigationContentDescription(int paramInt)
  {
    if (paramInt == mDefaultNavigationContentDescription) {}
    do
    {
      return;
      mDefaultNavigationContentDescription = paramInt;
    } while (!TextUtils.isEmpty(mToolbar.getNavigationContentDescription()));
    setNavigationContentDescription(mDefaultNavigationContentDescription);
  }
  
  public void setDefaultNavigationIcon(Drawable paramDrawable)
  {
    if (mDefaultNavigationIcon != paramDrawable)
    {
      mDefaultNavigationIcon = paramDrawable;
      updateNavigationIcon();
    }
  }
  
  public void setDisplayOptions(int paramInt)
  {
    int i = mDisplayOpts ^ paramInt;
    mDisplayOpts = paramInt;
    if (i != 0)
    {
      if ((i & 0x4) != 0)
      {
        if ((paramInt & 0x4) == 0) {
          break label215;
        }
        updateNavigationIcon();
        updateHomeAccessibility();
      }
      if ((i & 0x3) != 0) {
        updateToolbarLogo();
      }
      if ((i & 0x8) != 0)
      {
        if ((paramInt & 0x8) == 0) {
          break label226;
        }
        mToolbar.setTitle(mTitle);
        mToolbar.setSubtitle(mSubtitle);
      }
      label82:
      if (((i & 0x10) != 0) && (mCustomView != null))
      {
        if ((paramInt & 0x10) == 0) {
          break label245;
        }
        mToolbar.addView(mCustomView);
      }
      label114:
      if (((i & 0x40) != 0) && (mTabView != null))
      {
        if ((paramInt & 0x40) == 0) {
          break label259;
        }
        if ((mTabView != null) && (mNavigationMode == 2))
        {
          mToolbar.addView(mTabView, 0);
          localLayoutParams = (Toolbar.LayoutParams)mTabView.getLayoutParams();
          width = -2;
          height = -2;
          gravity = 8388691;
          ((ScrollingTabContainerView)mTabView).setAllowCollapse(false);
          ((ScrollingTabContainerView)mTabView).showAtToolbar(true);
        }
      }
    }
    label215:
    label226:
    label245:
    label259:
    while ((mTabView == null) || (mTabView.getParent() != mToolbar))
    {
      Toolbar.LayoutParams localLayoutParams;
      return;
      mToolbar.setNavigationIcon(null);
      break;
      mToolbar.setTitle(null);
      mToolbar.setSubtitle(null);
      break label82;
      mToolbar.removeView(mCustomView);
      break label114;
    }
    mToolbar.removeView(mTabView);
  }
  
  public void setDropdownParams(SpinnerAdapter paramSpinnerAdapter, AdapterViewCompat.OnItemSelectedListener paramOnItemSelectedListener)
  {
    ensureSpinner();
    mSpinner.setAdapter(paramSpinnerAdapter);
    mSpinner.setOnItemSelectedListener(paramOnItemSelectedListener);
  }
  
  public void setDropdownSelectedPosition(int paramInt)
  {
    if (mSpinner == null) {
      throw new IllegalStateException("Can't set dropdown selected position without an adapter");
    }
    mSpinner.setSelection(paramInt);
  }
  
  public void setEmbeddedTabView(ScrollingTabContainerView paramScrollingTabContainerView)
  {
    if ((mTabView != null) && (mTabView.getParent() == mToolbar)) {
      mToolbar.removeView(mTabView);
    }
    mTabView = paramScrollingTabContainerView;
    if ((paramScrollingTabContainerView != null) && (mNavigationMode == 2))
    {
      mToolbar.addView(mTabView, 0);
      Toolbar.LayoutParams localLayoutParams = (Toolbar.LayoutParams)mTabView.getLayoutParams();
      width = -2;
      height = -2;
      gravity = 8388691;
      paramScrollingTabContainerView.setAllowCollapse(false);
      paramScrollingTabContainerView.showAtToolbar(true);
    }
  }
  
  public void setHomeButtonEnabled(boolean paramBoolean) {}
  
  public void setIcon(int paramInt)
  {
    if (paramInt != 0) {}
    for (Drawable localDrawable = mTintManager.getDrawable(paramInt);; localDrawable = null)
    {
      setIcon(localDrawable);
      return;
    }
  }
  
  public void setIcon(Drawable paramDrawable)
  {
    mIcon = paramDrawable;
    updateToolbarLogo();
  }
  
  public void setLogo(int paramInt)
  {
    if (paramInt != 0) {}
    for (Drawable localDrawable = mTintManager.getDrawable(paramInt);; localDrawable = null)
    {
      setLogo(localDrawable);
      return;
    }
  }
  
  public void setLogo(Drawable paramDrawable)
  {
    mLogo = paramDrawable;
    updateToolbarLogo();
  }
  
  public void setMenu(Menu paramMenu, MenuPresenter.Callback paramCallback)
  {
    if (mActionMenuPresenter == null)
    {
      mActionMenuPresenter = new ActionMenuPresenter(mToolbar.getContext());
      mActionMenuPresenter.setId(R.id.action_menu_presenter);
      if (mSplitActionBar)
      {
        mActionMenuPresenter.setExpandedActionViewsExclusive(true);
        int i = ActionBarPolicy.get(getContext()).getSplitActionBarPadding();
        mActionMenuPresenter.setWidthLimit(getContextgetResourcesgetDisplayMetricswidthPixels - i * 2, true);
        mActionMenuPresenter.setItemLimit(Integer.MAX_VALUE);
        mActionMenuPresenter.setIsSplit(true);
      }
    }
    mActionMenuPresenter.setCallback(paramCallback);
    mToolbar.setMenu((MenuBuilder)paramMenu, mActionMenuPresenter);
  }
  
  public void setMenuCallbacks(MenuPresenter.Callback paramCallback, MenuBuilder.Callback paramCallback1)
  {
    mToolbar.setMenuCallbacks(paramCallback, paramCallback1);
  }
  
  public void setMenuPrepared()
  {
    mMenuPrepared = true;
  }
  
  public void setNavigationContentDescription(int paramInt)
  {
    if (paramInt == 0) {}
    for (Object localObject = null;; localObject = getContext().getString(paramInt))
    {
      setNavigationContentDescription((CharSequence)localObject);
      return;
    }
  }
  
  public void setNavigationContentDescription(CharSequence paramCharSequence)
  {
    mHomeDescription = paramCharSequence;
    updateHomeAccessibility();
  }
  
  public void setNavigationIcon(int paramInt)
  {
    if (paramInt != 0) {}
    for (Drawable localDrawable = mTintManager.getDrawable(paramInt);; localDrawable = null)
    {
      setNavigationIcon(localDrawable);
      return;
    }
  }
  
  public void setNavigationIcon(Drawable paramDrawable)
  {
    mNavIcon = paramDrawable;
    updateNavigationIcon();
  }
  
  public void setNavigationMode(int paramInt)
  {
    int i = mNavigationMode;
    if (paramInt != i)
    {
      switch (i)
      {
      }
      for (;;)
      {
        mNavigationMode = paramInt;
        switch (paramInt)
        {
        default: 
          throw new IllegalArgumentException("Invalid navigation mode " + paramInt);
          if ((mSpinner != null) && (mSpinner.getParent() == mToolbar))
          {
            mToolbar.removeView(mSpinner);
            continue;
            if ((mTabView != null) && (mTabView.getParent() == mToolbar)) {
              mToolbar.removeView(mTabView);
            }
          }
          break;
        }
      }
      ensureSpinner();
      mToolbar.addView(mSpinner, 0);
    }
    do
    {
      return;
    } while (mTabView == null);
    mToolbar.addView(mTabView, 0);
    Toolbar.LayoutParams localLayoutParams = (Toolbar.LayoutParams)mTabView.getLayoutParams();
    width = -2;
    height = -2;
    gravity = 8388691;
  }
  
  public void setSplitToolbar(boolean paramBoolean)
  {
    if (mSplitActionBar != paramBoolean)
    {
      mSplitActionBar = paramBoolean;
      mToolbar.setSplitToolbar(paramBoolean);
      if (mActionMenuPresenter != null)
      {
        if (paramBoolean) {
          break label49;
        }
        mActionMenuPresenter.setExpandedActionViewsExclusive(false);
      }
    }
    for (;;)
    {
      mActionMenuPresenter.setIsSplit(paramBoolean);
      return;
      label49:
      mActionMenuPresenter.setExpandedActionViewsExclusive(true);
      int i = ActionBarPolicy.get(getContext()).getSplitActionBarPadding();
      mActionMenuPresenter.setWidthLimit(getContextgetResourcesgetDisplayMetricswidthPixels - i * 2, true);
      mActionMenuPresenter.setItemLimit(Integer.MAX_VALUE);
    }
  }
  
  public void setSplitView(ViewGroup paramViewGroup)
  {
    mToolbar.setSplitView(paramViewGroup);
  }
  
  public void setSplitWhenNarrow(boolean paramBoolean)
  {
    mSplitWhenNarrow = paramBoolean;
  }
  
  public void setSubtitle(CharSequence paramCharSequence)
  {
    mSubtitle = paramCharSequence;
    if ((mDisplayOpts & 0x8) != 0) {
      mToolbar.setSubtitle(paramCharSequence);
    }
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    mTitleSet = true;
    setTitleInt(paramCharSequence);
  }
  
  public void setVisibility(int paramInt)
  {
    mToolbar.setVisibility(paramInt);
  }
  
  public void setWindowCallback(Window.Callback paramCallback)
  {
    mWindowCallback = paramCallback;
  }
  
  public void setWindowTitle(CharSequence paramCharSequence)
  {
    if (!mTitleSet) {
      setTitleInt(paramCharSequence);
    }
  }
  
  public boolean showOverflowMenu()
  {
    return mToolbar.showOverflowMenu();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ToolbarWidgetWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */