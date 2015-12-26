package android.support.v7.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.ActionProvider.SubUiVisibilityListener;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.integer;
import android.support.v7.appcompat.R.layout;
import android.support.v7.internal.transition.ActionBarTransition;
import android.support.v7.internal.view.ActionBarPolicy;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.support.v7.internal.view.menu.ActionMenuItemView;
import android.support.v7.internal.view.menu.ActionMenuItemView.PopupCallback;
import android.support.v7.internal.view.menu.BaseMenuPresenter;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuPopupHelper;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.internal.view.menu.MenuView.ItemView;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.support.v7.internal.widget.TintImageView;
import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.TouchDelegate;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import aqt;
import java.util.ArrayList;
import java.util.Iterator;

public class ActionMenuPresenter
  extends BaseMenuPresenter
  implements ActionProvider.SubUiVisibilityListener
{
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
  final PopupPresenterCallback mPopupPresenterCallback = new PopupPresenterCallback(null);
  private OpenOverflowRunnable mPostedOpenRunnable;
  private boolean mReserveOverflow;
  private boolean mReserveOverflowSet;
  private View mScrapActionButtonView;
  private boolean mStrictWidthLimit;
  private int mWidthLimit;
  private boolean mWidthLimitSet;
  
  public ActionMenuPresenter(Context paramContext)
  {
    super(paramContext, R.layout.abc_action_menu_layout, R.layout.mz_action_menu_item_layout);
  }
  
  private View findAnchor(View paramView)
  {
    if (Build.VERSION.SDK_INT < 19) {
      return paramView;
    }
    for (;;)
    {
      try
      {
        if ((mContext instanceof ContextThemeWrapper))
        {
          localObject2 = (Activity)((ContextThemeWrapper)mContext).getBaseContext();
          localObject1 = paramView;
        }
      }
      catch (Exception localException)
      {
        Object localObject2;
        Object localObject1;
        View localView = paramView;
        continue;
        continue;
      }
      try
      {
        if (isSplit()) {
          continue;
        }
        localObject2 = ((Activity)localObject2).findViewById(R.id.action_bar);
        localObject1 = localObject2;
        if (localObject1 == null) {
          break;
        }
      }
      catch (Exception paramView)
      {
        continue;
        localObject2 = null;
        localView = paramView;
      }
      return (View)localObject1;
      if (!(mContext instanceof Activity)) {
        continue;
      }
      localObject2 = (Activity)mContext;
      localObject1 = ((Activity)mContext).findViewById(R.id.action_bar);
      continue;
      localObject2 = ((Activity)localObject2).findViewById(R.id.split_action_bar);
      localObject1 = localObject2;
    }
  }
  
  private View findViewForItem(MenuItem paramMenuItem)
  {
    ViewGroup localViewGroup = (ViewGroup)mMenuView;
    Object localObject;
    if (localViewGroup == null)
    {
      localObject = null;
      return (View)localObject;
    }
    int j = localViewGroup.getChildCount();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label74;
      }
      View localView = localViewGroup.getChildAt(i);
      if ((localView instanceof MenuView.ItemView))
      {
        localObject = localView;
        if (((MenuView.ItemView)localView).getItemData() == paramMenuItem) {
          break;
        }
      }
      i += 1;
    }
    label74:
    return null;
  }
  
  private boolean flagActionItemsInSplit()
  {
    ArrayList localArrayList = mMenu.getVisibleItems();
    int i2 = localArrayList.size();
    int i3 = mWidthLimit;
    int m = 0;
    int k = 0;
    int i = 0;
    int j = 0;
    Object localObject;
    int n;
    if (m < i2)
    {
      localObject = (MenuItemImpl)localArrayList.get(m);
      if (((MenuItemImpl)localObject).requiresActionButton())
      {
        j += 1;
        n = k;
        k = j;
        j = i;
        i = n;
      }
      for (;;)
      {
        n = m + 1;
        m = k;
        k = i;
        i = j;
        j = m;
        m = n;
        break;
        if (((MenuItemImpl)localObject).requestsActionButton())
        {
          n = j;
          j = i + 1;
          i = k;
          k = n;
        }
        else
        {
          int i1 = 1;
          k = i;
          n = j;
          i = i1;
          j = k;
          k = n;
        }
      }
    }
    if ((mReserveOverflow) && (k != 0)) {}
    for (m = 1;; m = 0)
    {
      localObject = mActionButtonGroups;
      ((SparseBooleanArray)localObject).clear();
      if (mStrictWidthLimit) {}
      for (k = i3 / mMinCellSizeInSplit;; k = 0)
      {
        label200:
        label219:
        MenuItemImpl localMenuItemImpl;
        if (j + i > k)
        {
          n = 1;
          if ((m | n) == 0) {
            break label296;
          }
          m = k - 1;
          k = m;
          if (j + i < m) {
            k = j + i;
          }
          i = 0;
          j = k;
          if ((i >= i2) || (j <= 0)) {
            break label311;
          }
          localMenuItemImpl = (MenuItemImpl)localArrayList.get(i);
          if ((!localMenuItemImpl.requiresActionButton()) && (!localMenuItemImpl.requestsActionButton())) {
            break label302;
          }
          localMenuItemImpl.setIsActionButton(true);
          k = localMenuItemImpl.getGroupId();
          if (k != 0) {
            ((SparseBooleanArray)localObject).put(k, true);
          }
          j -= 1;
        }
        for (;;)
        {
          i += 1;
          break label219;
          n = 0;
          break;
          label296:
          m = k;
          break label200;
          label302:
          localMenuItemImpl.setIsActionButton(false);
        }
        label311:
        j = i;
        while (j < i2)
        {
          ((MenuItemImpl)localArrayList.get(i)).setIsActionButton(false);
          j += 1;
        }
        return true;
      }
    }
  }
  
  protected void addItemView(View paramView, int paramInt)
  {
    super.addItemView(paramView, paramInt);
    if (((mMenuView instanceof ActionMenuView)) && ((paramView instanceof ActionMenuItemView))) {
      getLayoutParamsisOverflowButton = ((ActionMenuItemView)paramView).isOverflowActor();
    }
  }
  
  public void bindItemView(MenuItemImpl paramMenuItemImpl, MenuView.ItemView paramItemView)
  {
    ((ActionMenuItemView)paramItemView).setIsInSplit(mIsSplit);
    paramItemView.initialize(paramMenuItemImpl, 0);
    paramMenuItemImpl = (ActionMenuView)mMenuView;
    paramItemView = (ActionMenuItemView)paramItemView;
    paramItemView.setItemInvoker(paramMenuItemImpl);
    if (mPopupCallback == null) {
      mPopupCallback = new ActionMenuPopupCallback(null);
    }
    paramItemView.setPopupCallback(mPopupCallback);
  }
  
  public MenuView.ItemView createItemView(ViewGroup paramViewGroup)
  {
    paramViewGroup = super.createItemView(paramViewGroup);
    if (((isSplit()) || (Build.VERSION.SDK_INT < 21)) && ((paramViewGroup instanceof ActionMenuItemView)))
    {
      ((ActionMenuView)mMenuView).setClipChildren(false);
      ActionMenuItemView localActionMenuItemView = (ActionMenuItemView)paramViewGroup;
      localActionMenuItemView.setBackgroundDrawable(new ActionMenuRippleDrawable(localActionMenuItemView));
    }
    return paramViewGroup;
  }
  
  public boolean dismissPopupMenus()
  {
    return hideOverflowMenu() | hideSubMenus();
  }
  
  public boolean dismissPopupMenus(boolean paramBoolean)
  {
    return hideOverflowMenu(paramBoolean) | hideSubMenus();
  }
  
  public boolean filterLeftoverView(ViewGroup paramViewGroup, int paramInt)
  {
    if (paramViewGroup.getChildAt(paramInt) == mOverflowButton) {
      return false;
    }
    return super.filterLeftoverView(paramViewGroup, paramInt);
  }
  
  public boolean flagActionItems()
  {
    if (mIsSplit) {
      return flagActionItemsInSplit();
    }
    ArrayList localArrayList = mMenu.getVisibleItems();
    int i4 = localArrayList.size();
    int i = mMaxItems;
    int i3 = mActionItemWidthLimit;
    int i5 = View.MeasureSpec.makeMeasureSpec(0, 0);
    ViewGroup localViewGroup = (ViewGroup)mMenuView;
    int k = 0;
    int m = 0;
    int n = 0;
    int j = 0;
    Object localObject1;
    if (j < i4)
    {
      localObject1 = (MenuItemImpl)localArrayList.get(j);
      if (((MenuItemImpl)localObject1).requiresActionButton())
      {
        k += 1;
        label94:
        if ((!mExpandedActionViewsExclusive) || (!((MenuItemImpl)localObject1).isActionViewExpanded())) {
          break label867;
        }
        i = 0;
      }
    }
    label316:
    label457:
    label517:
    label522:
    label561:
    label644:
    label656:
    label661:
    label844:
    label847:
    label858:
    label867:
    for (;;)
    {
      j += 1;
      break;
      if (((MenuItemImpl)localObject1).requestsActionButton())
      {
        m += 1;
        break label94;
      }
      n = 1;
      break label94;
      j = i;
      if (mReserveOverflow) {
        if (n == 0)
        {
          j = i;
          if (k + m <= i) {}
        }
        else
        {
          j = i - 1;
        }
      }
      j -= k;
      localObject1 = mActionButtonGroups;
      ((SparseBooleanArray)localObject1).clear();
      i = 0;
      if (mStrictWidthLimit)
      {
        i = i3 / mMinCellSize;
        k = mMinCellSize;
        m = mMinCellSize;
      }
      for (int i1 = i3 % k / i + m;; i1 = 0)
      {
        int i2 = 0;
        n = 0;
        k = i;
        i = j;
        m = i3;
        j = n;
        MenuItemImpl localMenuItemImpl;
        Object localObject2;
        if (i2 < i4)
        {
          localMenuItemImpl = (MenuItemImpl)localArrayList.get(i2);
          if (localMenuItemImpl.requiresActionButton())
          {
            localObject2 = getItemView(localMenuItemImpl, mScrapActionButtonView, localViewGroup);
            if (mScrapActionButtonView == null) {
              mScrapActionButtonView = ((View)localObject2);
            }
            if (mStrictWidthLimit)
            {
              n = k - ActionMenuView.measureChildForCells((View)localObject2, i1, k, i5, 0);
              k = ((View)localObject2).getMeasuredWidth();
              if (j != 0) {
                break label858;
              }
              j = k;
            }
          }
        }
        for (;;)
        {
          i3 = localMenuItemImpl.getGroupId();
          if (i3 != 0) {
            ((SparseBooleanArray)localObject1).put(i3, true);
          }
          localMenuItemImpl.setIsActionButton(true);
          m -= k;
          k = i;
          i = m;
          i3 = i2 + 1;
          i2 = k;
          k = n;
          m = i;
          i = i2;
          i2 = i3;
          break;
          ((View)localObject2).measure(i5, i5);
          n = k;
          break label316;
          int i6;
          boolean bool;
          int i7;
          if (localMenuItemImpl.requestsActionButton())
          {
            i6 = localMenuItemImpl.getGroupId();
            bool = ((SparseBooleanArray)localObject1).get(i6);
            if (((i > 0) || (bool)) && (m > 0) && ((!mStrictWidthLimit) || (k > 0)))
            {
              i7 = 1;
              if (i7 == 0) {
                break label847;
              }
              localObject2 = getItemView(localMenuItemImpl, mScrapActionButtonView, localViewGroup);
              if (mScrapActionButtonView == null) {
                mScrapActionButtonView = ((View)localObject2);
              }
              if (!mStrictWidthLimit) {
                break label644;
              }
              n = ActionMenuView.measureChildForCells((View)localObject2, i1, k, i5, 0);
              if (n != 0) {
                break label844;
              }
              i7 = 0;
              k -= n;
              n = ((View)localObject2).getMeasuredWidth();
              i3 = m - n;
              m = j;
              if (j == 0) {
                m = n;
              }
              if (!mStrictWidthLimit) {
                break label661;
              }
              if (i3 < 0) {
                break label656;
              }
              j = 1;
              i7 &= j;
              j = m;
              n = k;
              m = i3;
              k = j;
              j = n;
            }
          }
          for (;;)
          {
            if ((i7 != 0) && (i6 != 0)) {
              ((SparseBooleanArray)localObject1).put(i6, true);
            }
            for (;;)
            {
              n = i;
              if (i7 != 0) {
                n = i - 1;
              }
              localMenuItemImpl.setIsActionButton(i7);
              i = m;
              m = n;
              n = j;
              j = k;
              k = m;
              break;
              int i8 = 0;
              break label457;
              ((View)localObject2).measure(i5, i5);
              break label522;
              j = 0;
              break label561;
              if (i2 == 0)
              {
                if (i3 + m > 0) {}
                for (j = 1;; j = 0)
                {
                  i8 &= j;
                  j = k;
                  k = m;
                  m = i3;
                  break;
                }
              }
              if (i3 >= 0) {}
              for (j = 1;; j = 0)
              {
                i8 &= j;
                j = k;
                k = m;
                m = i3;
                break;
              }
              if (bool)
              {
                ((SparseBooleanArray)localObject1).put(i6, false);
                i3 = 0;
                for (;;)
                {
                  if (i3 < i2)
                  {
                    localObject2 = (MenuItemImpl)localArrayList.get(i3);
                    n = i;
                    if (((MenuItemImpl)localObject2).getGroupId() == i6)
                    {
                      n = i;
                      if (((MenuItemImpl)localObject2).isActionButton()) {
                        n = i + 1;
                      }
                      ((MenuItemImpl)localObject2).setIsActionButton(false);
                    }
                    i3 += 1;
                    i = n;
                    continue;
                    localMenuItemImpl.setIsActionButton(false);
                    n = m;
                    m = i;
                    i = n;
                    n = k;
                    k = m;
                    break;
                    return true;
                  }
                }
              }
            }
            break label517;
            n = j;
            j = k;
            k = n;
          }
        }
      }
    }
  }
  
  public View getItemView(MenuItemImpl paramMenuItemImpl, View paramView, ViewGroup paramViewGroup)
  {
    View localView = paramMenuItemImpl.getActionView();
    if ((localView == null) || (paramMenuItemImpl.hasCollapsibleActionView())) {
      localView = super.getItemView(paramMenuItemImpl, paramView, paramViewGroup);
    }
    int i;
    if (paramMenuItemImpl.isActionViewExpanded())
    {
      i = 8;
      localView.setVisibility(i);
      paramMenuItemImpl = (ActionMenuView)paramViewGroup;
      paramView = localView.getLayoutParams();
      if (paramMenuItemImpl.checkLayoutParams(paramView)) {
        break label83;
      }
      localView.setLayoutParams(paramMenuItemImpl.generateLayoutParams(paramView));
    }
    label83:
    while ((!(paramView instanceof ActionMenuView.LayoutParams)) || (!(localView instanceof ActionMenuItemView)))
    {
      return localView;
      i = 0;
      break;
    }
    isOverflowButton = ((ActionMenuItemView)localView).isOverflowActor();
    return localView;
  }
  
  public MenuView getMenuView(ViewGroup paramViewGroup)
  {
    paramViewGroup = super.getMenuView(paramViewGroup);
    ((ActionMenuView)paramViewGroup).setPresenter(this);
    return paramViewGroup;
  }
  
  public boolean hideOverflowMenu()
  {
    return hideOverflowMenu(true);
  }
  
  public boolean hideOverflowMenu(boolean paramBoolean)
  {
    if ((mPostedOpenRunnable != null) && (mMenuView != null))
    {
      ((View)mMenuView).removeCallbacks(mPostedOpenRunnable);
      mPostedOpenRunnable = null;
      return true;
    }
    OverflowPopup localOverflowPopup = mOverflowPopup;
    if (localOverflowPopup != null)
    {
      localOverflowPopup.dismiss(paramBoolean);
      return true;
    }
    return false;
  }
  
  public boolean hideSubMenus()
  {
    if (mActionButtonPopup != null)
    {
      mActionButtonPopup.dismiss();
      return true;
    }
    return false;
  }
  
  public void initForMenu(Context paramContext, MenuBuilder paramMenuBuilder)
  {
    super.initForMenu(paramContext, paramMenuBuilder);
    paramMenuBuilder = paramContext.getResources();
    paramContext = ActionBarPolicy.get(paramContext);
    if (!mReserveOverflowSet) {
      mReserveOverflow = paramContext.showsOverflowMenuButton();
    }
    if (!mWidthLimitSet) {
      mWidthLimit = paramContext.getEmbeddedMenuWidthLimit();
    }
    if (!mMaxItemsSet) {
      mMaxItems = paramContext.getMaxActionButtons();
    }
    int i = mWidthLimit;
    if (mReserveOverflow)
    {
      if (mOverflowButton == null)
      {
        mOverflowButton = new OverflowMenuButton(mSystemContext);
        int j = View.MeasureSpec.makeMeasureSpec(0, 0);
        mOverflowButton.measure(j, j);
      }
      i -= mOverflowButton.getMeasuredWidth();
    }
    for (;;)
    {
      mActionItemWidthLimit = i;
      mMinCellSize = ((int)(56.0F * getDisplayMetricsdensity));
      mScrapActionButtonView = null;
      mMinCellSizeInSplit = ((int)(67.0F * getDisplayMetricsdensity));
      return;
      mOverflowButton = null;
    }
  }
  
  public boolean isOverflowMenuShowPending()
  {
    return (mPostedOpenRunnable != null) || (isOverflowMenuShowing());
  }
  
  public boolean isOverflowMenuShowing()
  {
    return (mOverflowPopup != null) && (mOverflowPopup.isShowing());
  }
  
  public boolean isOverflowReserved()
  {
    return mReserveOverflow;
  }
  
  public boolean isSplit()
  {
    return mIsSplit;
  }
  
  public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
  {
    dismissPopupMenus();
    super.onCloseMenu(paramMenuBuilder, paramBoolean);
  }
  
  public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean, MenuItemImpl paramMenuItemImpl)
  {
    dismissPopupMenus(paramMenuItemImpl.isLetMenuSlideOut());
    super.onCloseMenu(paramMenuBuilder, paramBoolean);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (!mMaxItemsSet) {
      mMaxItems = mContext.getResources().getInteger(R.integer.abc_max_action_buttons);
    }
    if (mMenu != null) {
      mMenu.onItemsChanged(true);
    }
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    paramParcelable = (SavedState)paramParcelable;
    if (openSubMenuId > 0)
    {
      paramParcelable = mMenu.findItem(openSubMenuId);
      if (paramParcelable != null) {
        onSubMenuSelected((SubMenuBuilder)paramParcelable.getSubMenu());
      }
    }
  }
  
  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState();
    openSubMenuId = mOpenSubMenuId;
    return localSavedState;
  }
  
  public boolean onSubMenuSelected(SubMenuBuilder paramSubMenuBuilder)
  {
    if (!paramSubMenuBuilder.hasVisibleItems()) {
      return false;
    }
    for (Object localObject = paramSubMenuBuilder; ((SubMenuBuilder)localObject).getParentMenu() != mMenu; localObject = (SubMenuBuilder)((SubMenuBuilder)localObject).getParentMenu()) {}
    View localView = findViewForItem(((SubMenuBuilder)localObject).getItem());
    localObject = localView;
    if (localView == null)
    {
      if (mOverflowButton == null) {
        return false;
      }
      localObject = mOverflowButton;
    }
    mOpenSubMenuId = paramSubMenuBuilder.getItem().getItemId();
    mActionButtonPopup = new ActionButtonSubmenu(mContext, paramSubMenuBuilder);
    localObject = findAnchor((View)localObject);
    mActionButtonPopup.setGravity(8388613);
    mActionButtonPopup.setAnchorView((View)localObject);
    mActionButtonPopup.show();
    super.onSubMenuSelected(paramSubMenuBuilder);
    return true;
  }
  
  public void onSubUiVisibilityChanged(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      super.onSubMenuSelected(null);
      return;
    }
    mMenu.close(false);
  }
  
  public void setExpandedActionViewsExclusive(boolean paramBoolean)
  {
    mExpandedActionViewsExclusive = paramBoolean;
  }
  
  public void setIsSplit(boolean paramBoolean)
  {
    if (mIsSplit != paramBoolean)
    {
      i = 1;
      if (i != 0)
      {
        mIsSplit = paramBoolean;
        if (!mIsSplit) {
          break label89;
        }
      }
    }
    label89:
    for (int i = R.layout.mz_action_menu_item_split_layout;; i = R.layout.mz_action_menu_item_layout)
    {
      setItemLayoutRes(i);
      if (mMenuView != null) {
        ((ViewGroup)mMenuView).removeAllViews();
      }
      if (!paramBoolean)
      {
        mReserveOverflowSet = false;
        mWidthLimitSet = false;
        mMaxItemsSet = false;
        initForMenu(mContext, mMenu);
      }
      return;
      i = 0;
      break;
    }
  }
  
  public void setItemLimit(int paramInt)
  {
    mMaxItems = paramInt;
    mMaxItemsSet = true;
  }
  
  public void setMenuView(ActionMenuView paramActionMenuView)
  {
    mMenuView = paramActionMenuView;
    paramActionMenuView.initialize(mMenu);
  }
  
  public void setOverflowDrawable(Drawable paramDrawable)
  {
    mOverflowDrawable = paramDrawable;
    if (mOverflowButton != null) {
      ((OverflowMenuButton)mOverflowButton).setImageDrawable(mOverflowDrawable);
    }
  }
  
  public void setReserveOverflow(boolean paramBoolean)
  {
    mReserveOverflow = paramBoolean;
    mReserveOverflowSet = true;
  }
  
  public void setWidthLimit(int paramInt, boolean paramBoolean)
  {
    mWidthLimit = paramInt;
    mStrictWidthLimit = paramBoolean;
    mWidthLimitSet = true;
  }
  
  public boolean shouldIncludeItem(int paramInt, MenuItemImpl paramMenuItemImpl)
  {
    return paramMenuItemImpl.isActionButton();
  }
  
  public boolean showOverflowMenu()
  {
    if ((mReserveOverflow) && (!isOverflowMenuShowing()) && (mMenu != null) && (mMenuView != null) && (mPostedOpenRunnable == null) && (!mMenu.getNonActionItems().isEmpty()))
    {
      View localView = findAnchor(mOverflowButton);
      mPostedOpenRunnable = new OpenOverflowRunnable(new OverflowPopup(mContext, mMenu, localView, true));
      ((View)mMenuView).post(mPostedOpenRunnable);
      super.onSubMenuSelected(null);
      return true;
    }
    return false;
  }
  
  public void updateMenuView(boolean paramBoolean)
  {
    Object localObject2 = null;
    boolean bool = true;
    Object localObject1 = (ViewGroup)((View)mMenuView).getParent();
    if (localObject1 != null) {
      ActionBarTransition.beginDelayedTransition((ViewGroup)localObject1);
    }
    super.updateMenuView(paramBoolean);
    ((View)mMenuView).requestLayout();
    int i;
    if (mMenu != null)
    {
      localObject1 = mMenu.getActionItems();
      int j = ((ArrayList)localObject1).size();
      i = 0;
      while (i < j)
      {
        ActionProvider localActionProvider = ((MenuItemImpl)((ArrayList)localObject1).get(i)).getSupportActionProvider();
        if (localActionProvider != null) {
          localActionProvider.setSubUiVisibilityListener(this);
        }
        i += 1;
      }
    }
    if (mMenu != null)
    {
      localObject1 = mMenu.getNonActionItems();
      if ((!mReserveOverflow) || (localObject1 == null)) {
        break label419;
      }
      i = ((ArrayList)localObject1).size();
      if (i != 1) {
        break label339;
      }
      if (((MenuItemImpl)((ArrayList)localObject1).get(0)).isActionViewExpanded()) {
        break label334;
      }
      paramBoolean = true;
    }
    for (;;)
    {
      label163:
      if (paramBoolean)
      {
        if (mOverflowButton == null) {
          mOverflowButton = new OverflowMenuButton(mSystemContext);
        }
        localObject1 = (ViewGroup)mOverflowButton.getParent();
        if (localObject1 != mMenuView)
        {
          if (localObject1 != null) {
            ((ViewGroup)localObject1).removeView(mOverflowButton);
          }
          localObject1 = (ActionMenuView)mMenuView;
          ((ActionMenuView)localObject1).addView(mOverflowButton, ((ActionMenuView)localObject1).generateOverflowButtonLayoutParams());
        }
      }
      for (;;)
      {
        bool = paramBoolean;
        if (paramBoolean) {
          break label392;
        }
        localObject1 = localObject2;
        if (mMenu != null) {
          localObject1 = mMenu.getVisibleItems();
        }
        bool = paramBoolean;
        if (localObject1 == null) {
          break label392;
        }
        bool = paramBoolean;
        if (((ArrayList)localObject1).size() <= 0) {
          break label392;
        }
        localObject1 = ((ArrayList)localObject1).iterator();
        while (((Iterator)localObject1).hasNext()) {
          paramBoolean = ((MenuItemImpl)((Iterator)localObject1).next()).isOverflowActor();
        }
        localObject1 = null;
        break;
        label334:
        paramBoolean = false;
        break label163;
        label339:
        paramBoolean = bool;
        if (i > 0) {
          break label163;
        }
        paramBoolean = false;
        break label163;
        if ((mOverflowButton != null) && (mOverflowButton.getParent() == mMenuView)) {
          ((ViewGroup)mMenuView).removeView(mOverflowButton);
        }
      }
      bool = paramBoolean;
      label392:
      ((ActionMenuView)mMenuView).setOverflowReserved(mReserveOverflow);
      ((ActionMenuView)mMenuView).setHasOverflow(bool);
      return;
      label419:
      paramBoolean = false;
    }
  }
  
  class ActionButtonSubmenu
    extends MenuPopupHelper
  {
    private SubMenuBuilder mSubMenu;
    
    public ActionButtonSubmenu(Context paramContext, SubMenuBuilder paramSubMenuBuilder) {}
    
    public void onDismiss()
    {
      super.onDismiss();
      ActionMenuPresenter.access$902(ActionMenuPresenter.this, null);
      mOpenSubMenuId = 0;
    }
  }
  
  class ActionMenuPopupCallback
    extends ActionMenuItemView.PopupCallback
  {
    private ActionMenuPopupCallback() {}
    
    public ListPopupWindow getPopup()
    {
      if (mActionButtonPopup != null) {
        return mActionButtonPopup.getPopup();
      }
      return null;
    }
  }
  
  class ActionMenuRippleDrawable
    extends aqt
  {
    public ActionMenuRippleDrawable(View paramView)
    {
      super(R.attr.mzActionButtonRippleSplitStyle);
    }
  }
  
  class OpenOverflowRunnable
    implements Runnable
  {
    private ActionMenuPresenter.OverflowPopup mPopup;
    
    public OpenOverflowRunnable(ActionMenuPresenter.OverflowPopup paramOverflowPopup)
    {
      mPopup = paramOverflowPopup;
    }
    
    public void run()
    {
      mMenu.changeMenuMode();
      View localView = (View)mMenuView;
      if ((localView != null) && (localView.getWindowToken() != null) && (mPopup.tryShow())) {
        ActionMenuPresenter.access$302(ActionMenuPresenter.this, mPopup);
      }
      ActionMenuPresenter.access$402(ActionMenuPresenter.this, null);
    }
  }
  
  class OverflowMenuButton
    extends TintImageView
    implements ActionMenuView.ActionMenuChildView
  {
    private final float[] mTempPts;
    
    public OverflowMenuButton(Context paramContext) {}
    
    public boolean needsDividerAfter()
    {
      return false;
    }
    
    public boolean needsDividerBefore()
    {
      return false;
    }
    
    protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      int i = (int)(getResourcesgetDisplayMetricsdensity * 52.0F);
      if (paramInt3 - paramInt1 < i)
      {
        i = (i - (paramInt3 - paramInt1)) / 2;
        Rect localRect = new Rect(paramInt1 - i, paramInt2, i + paramInt3, paramInt4);
        ((View)getParent()).setTouchDelegate(new TouchDelegate(localRect, this));
      }
    }
    
    public boolean performClick()
    {
      if (super.performClick()) {
        return true;
      }
      playSoundEffect(0);
      showOverflowMenu();
      return true;
    }
    
    protected boolean setFrame(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      boolean bool = super.setFrame(paramInt1, paramInt2, paramInt3, paramInt4);
      Drawable localDrawable1 = getDrawable();
      Drawable localDrawable2 = getBackground();
      int i = getPaddingLeft() - getPaddingRight();
      paramInt3 = getPaddingTop() - getPaddingBottom();
      if ((localDrawable1 != null) && (localDrawable2 != null) && ((i != 0) || (paramInt3 != 0)))
      {
        int j = getWidth();
        paramInt4 = getHeight();
        paramInt1 = j / 2;
        paramInt2 = paramInt4 / 2;
        i = (j + i) / 2;
        paramInt3 = (paramInt4 + paramInt3) / 2;
        DrawableCompat.setHotspotBounds(localDrawable2, i - paramInt1, paramInt3 - paramInt2, i + paramInt1, paramInt3 + paramInt2);
      }
      return bool;
    }
  }
  
  class OverflowPopup
    extends MenuPopupHelper
  {
    public OverflowPopup(Context paramContext, MenuBuilder paramMenuBuilder, View paramView, boolean paramBoolean) {}
    
    public void dismiss()
    {
      super.dismiss(true);
    }
    
    public void onDismiss()
    {
      super.onDismiss();
      mMenu.close();
      ActionMenuPresenter.access$302(ActionMenuPresenter.this, null);
    }
  }
  
  class PopupPresenterCallback
    implements MenuPresenter.Callback
  {
    private PopupPresenterCallback() {}
    
    public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
    {
      if ((paramMenuBuilder instanceof SubMenuBuilder)) {
        ((SubMenuBuilder)paramMenuBuilder).getRootMenu().close(false);
      }
      MenuPresenter.Callback localCallback = getCallback();
      if (localCallback != null) {
        localCallback.onCloseMenu(paramMenuBuilder, paramBoolean);
      }
    }
    
    public boolean onOpenSubMenu(MenuBuilder paramMenuBuilder)
    {
      if (paramMenuBuilder == null) {
        return false;
      }
      mOpenSubMenuId = ((SubMenuBuilder)paramMenuBuilder).getItem().getItemId();
      MenuPresenter.Callback localCallback = getCallback();
      if (localCallback != null) {}
      for (boolean bool = localCallback.onOpenSubMenu(paramMenuBuilder);; bool = false) {
        return bool;
      }
    }
  }
  
  static class SavedState
    implements Parcelable
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new ActionMenuPresenter.SavedState.1();
    public int openSubMenuId;
    
    SavedState() {}
    
    SavedState(Parcel paramParcel)
    {
      openSubMenuId = paramParcel.readInt();
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeInt(openSubMenuId);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ActionMenuPresenter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */