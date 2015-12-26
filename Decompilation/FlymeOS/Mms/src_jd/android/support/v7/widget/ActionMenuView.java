package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v7.appcompat.R.dimen;
import android.support.v7.appcompat.R.drawable;
import android.support.v7.internal.view.ActionBarPolicy;
import android.support.v7.internal.view.menu.ActionMenuItemView;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuBuilder.Callback;
import android.support.v7.internal.view.menu.MenuBuilder.ItemInvoker;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.internal.widget.ViewUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;

public class ActionMenuView
  extends LinearLayoutCompat
  implements MenuBuilder.ItemInvoker, MenuView
{
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
  
  public ActionMenuView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ActionMenuView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    mContext = paramContext;
    setBaselineAligned(false);
    float f = getResourcesgetDisplayMetricsdensity;
    mMinCellSize = ((int)(56.0F * f));
    mGeneratedItemPadding = ((int)(f * 4.0F));
    mPopupContext = paramContext;
    mPopupTheme = 0;
    setMotionEventSplittingEnabled(false);
  }
  
  private void applyFlymeStyle(LayoutParams paramLayoutParams, int paramInt1, int paramInt2)
  {
    int i = getResources().getDimensionPixelSize(R.dimen.mz_action_overflow_btn_margin_right);
    int j = getResources().getDimensionPixelSize(R.dimen.mz_action_menu_item_last_margin_right);
    int k = getResources().getDimensionPixelSize(R.dimen.mz_action_menu_item_next_overflow_margin_right);
    int m = getResources().getDimensionPixelSize(R.dimen.mz_action_menu_item_margin_right);
    if ((Build.VERSION.SDK_INT < 21) && (paramInt1 == 0)) {
      leftMargin = getResources().getDimensionPixelSize(R.dimen.mz_action_menu_item_margin_left_lower_version);
    }
    if (isOverflowButton)
    {
      rightMargin = i;
      return;
    }
    if ((mHasOverflow) && (paramInt1 == paramInt2 - 2))
    {
      rightMargin = k;
      return;
    }
    if ((!mHasOverflow) && (paramInt1 == paramInt2 - 1))
    {
      rightMargin = j;
      return;
    }
    rightMargin = m;
  }
  
  static int measureChildForCells(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    boolean bool2 = false;
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    int i = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(paramInt3) - paramInt4, View.MeasureSpec.getMode(paramInt3));
    ActionMenuItemView localActionMenuItemView;
    if ((paramView instanceof ActionMenuItemView))
    {
      localActionMenuItemView = (ActionMenuItemView)paramView;
      if ((localActionMenuItemView == null) || (!localActionMenuItemView.hasText())) {
        break label184;
      }
      paramInt4 = 1;
      label57:
      if ((paramInt2 <= 0) || ((paramInt4 != 0) && (paramInt2 < 2))) {
        break label190;
      }
      paramView.measure(View.MeasureSpec.makeMeasureSpec(paramInt1 * paramInt2, Integer.MIN_VALUE), i);
      int j = paramView.getMeasuredWidth();
      paramInt3 = j / paramInt1;
      paramInt2 = paramInt3;
      if (j % paramInt1 != 0) {
        paramInt2 = paramInt3 + 1;
      }
      paramInt3 = paramInt2;
      if (paramInt4 != 0)
      {
        paramInt3 = paramInt2;
        if (paramInt2 >= 2) {}
      }
    }
    label184:
    label190:
    for (paramInt3 = 2;; paramInt3 = 0)
    {
      boolean bool1 = bool2;
      if (!isOverflowButton)
      {
        bool1 = bool2;
        if (paramInt4 != 0) {
          bool1 = true;
        }
      }
      expandable = bool1;
      cellsUsed = paramInt3;
      paramView.measure(View.MeasureSpec.makeMeasureSpec(paramInt3 * paramInt1, 1073741824), i);
      return paramInt3;
      localActionMenuItemView = null;
      break;
      paramInt4 = 0;
      break label57;
    }
  }
  
  static int measureChildForCellsInSplit(View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    paramInt2 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(paramInt2) - paramInt3, View.MeasureSpec.getMode(paramInt2));
    cellsUsed = 1;
    paramView.measure(View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824), paramInt2);
    return 1;
  }
  
  private void onMeasureExactFormat(int paramInt1, int paramInt2)
  {
    if (mPresenter.isSplit())
    {
      onMeasureExactFormatInSplit(paramInt1, paramInt2);
      return;
    }
    int i7 = View.MeasureSpec.getMode(paramInt2);
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    int i6 = View.MeasureSpec.getSize(paramInt2);
    int i = getPaddingLeft();
    int j = getPaddingRight();
    int i4 = getPaddingTop() + getPaddingBottom();
    int i8 = getChildMeasureSpec(paramInt2, i4, -2);
    int i9 = paramInt1 - (i + j);
    paramInt1 = i9 / mMinCellSize;
    paramInt2 = mMinCellSize;
    if (paramInt1 == 0)
    {
      setMeasuredDimension(i9, 0);
      return;
    }
    int i10 = mMinCellSize + i9 % paramInt2 / paramInt1;
    i = 0;
    int n = 0;
    int m = 0;
    int i1 = 0;
    int k = 0;
    long l1 = 0L;
    int i11 = getChildCount();
    int i2 = 0;
    Object localObject;
    boolean bool;
    LayoutParams localLayoutParams;
    label291:
    label308:
    int i5;
    for (;;)
    {
      if (i2 < i11)
      {
        localObject = getChildAt(i2);
        if (((View)localObject).getVisibility() == 8)
        {
          paramInt2 = n;
          j = paramInt1;
          paramInt1 = i1;
          i2 += 1;
          i1 = paramInt1;
          paramInt1 = j;
          n = paramInt2;
        }
        else
        {
          bool = localObject instanceof ActionMenuItemView;
          i1 += 1;
          if (bool) {
            ((View)localObject).setPadding(mGeneratedItemPadding, 0, mGeneratedItemPadding, 0);
          }
          localLayoutParams = (LayoutParams)((View)localObject).getLayoutParams();
          expanded = false;
          extraPixels = 0;
          cellsUsed = 0;
          expandable = false;
          leftMargin = 0;
          rightMargin = 0;
          if ((bool) && (((ActionMenuItemView)localObject).hasText()))
          {
            bool = true;
            preventEdgeOffset = bool;
            if (!isOverflowButton) {
              break label419;
            }
            paramInt2 = 1;
            i5 = measureChildForCells((View)localObject, i10, paramInt2, i8, i4);
            n = Math.max(n, i5);
            if (!expandable) {
              break label1253;
            }
          }
        }
      }
    }
    label419:
    label466:
    label503:
    label530:
    label589:
    label615:
    label748:
    label764:
    label797:
    label812:
    label955:
    label1092:
    label1096:
    label1195:
    label1201:
    label1204:
    label1215:
    label1253:
    for (paramInt2 = m + 1;; paramInt2 = m)
    {
      if (isOverflowButton) {}
      for (j = 1;; j = k)
      {
        int i3 = paramInt1 - i5;
        i = Math.max(i, ((View)localObject).getMeasuredHeight());
        if (i5 == 1)
        {
          long l2 = 1 << i2;
          m = paramInt2;
          l1 = l2 | l1;
          paramInt2 = n;
          paramInt1 = i1;
          k = j;
          j = i3;
          break;
          bool = false;
          break label291;
          paramInt2 = paramInt1;
          break label308;
          if ((k != 0) && (i1 == 2))
          {
            i3 = 1;
            j = 0;
            i2 = paramInt1;
            if ((m <= 0) || (i2 <= 0)) {
              break label1215;
            }
            paramInt1 = Integer.MAX_VALUE;
            l2 = 0L;
            paramInt2 = 0;
            i4 = 0;
            if (i4 >= i11) {
              break label589;
            }
            localObject = (LayoutParams)getChildAt(i4).getLayoutParams();
            if (expandable) {
              break label530;
            }
            i5 = paramInt2;
            paramInt2 = paramInt1;
            paramInt1 = i5;
          }
          for (;;)
          {
            i5 = i4 + 1;
            i4 = paramInt2;
            paramInt2 = paramInt1;
            paramInt1 = i4;
            i4 = i5;
            break label466;
            i3 = 0;
            break;
            if (cellsUsed < paramInt1)
            {
              paramInt2 = cellsUsed;
              l2 = 1 << i4;
              paramInt1 = 1;
            }
            else
            {
              if (cellsUsed != paramInt1) {
                break label1204;
              }
              l2 |= 1 << i4;
              i5 = paramInt2 + 1;
              paramInt2 = paramInt1;
              paramInt1 = i5;
            }
          }
          l1 |= l2;
          if (paramInt2 > i2) {}
          for (;;)
          {
            float f2;
            float f1;
            if ((k == 0) && (i1 == 1))
            {
              paramInt1 = 1;
              if ((i2 <= 0) || (l1 == 0L) || ((i2 >= i1 - 1) && (paramInt1 == 0) && (n <= 1))) {
                break label1092;
              }
              float f3 = Long.bitCount(l1);
              f2 = f3;
              if (paramInt1 != 0) {
                break label1195;
              }
              f1 = f3;
              if ((1L & l1) != 0L)
              {
                f1 = f3;
                if (!getChildAt0getLayoutParamspreventEdgeOffset) {
                  f1 = f3 - 0.5F;
                }
              }
              f2 = f1;
              if ((1 << i11 - 1 & l1) == 0L) {
                break label1195;
              }
              f2 = f1;
              if (getChildAt1getLayoutParamspreventEdgeOffset) {
                break label1195;
              }
              f1 -= 0.5F;
              if (f1 <= 0.0F) {
                break label955;
              }
              paramInt2 = (int)(i2 * i10 / f1);
              k = 0;
              paramInt1 = j;
              j = k;
              for (;;)
              {
                k = paramInt1;
                if (j >= i11) {
                  break label1096;
                }
                if ((1 << j & l1) != 0L) {
                  break;
                }
                j += 1;
              }
              j = 0;
              paramInt2 = i2;
              if (j < i11)
              {
                localObject = getChildAt(j);
                localLayoutParams = (LayoutParams)((View)localObject).getLayoutParams();
                if ((1 << j & l2) == 0L)
                {
                  if (cellsUsed != paramInt1 + 1) {
                    break label1201;
                  }
                  l1 |= 1 << j;
                }
              }
            }
            for (;;)
            {
              j += 1;
              break label812;
              if ((i3 != 0) && (preventEdgeOffset) && (paramInt2 == 1)) {
                ((View)localObject).setPadding(mGeneratedItemPadding + i10, 0, mGeneratedItemPadding, 0);
              }
              cellsUsed += 1;
              expanded = true;
              paramInt2 -= 1;
              continue;
              j = 1;
              i2 = paramInt2;
              break;
              paramInt1 = 0;
              break label615;
              paramInt2 = 0;
              break label764;
              localObject = getChildAt(j);
              localLayoutParams = (LayoutParams)((View)localObject).getLayoutParams();
              if ((localObject instanceof ActionMenuItemView))
              {
                extraPixels = paramInt2;
                expanded = true;
                if ((j == 0) && (!preventEdgeOffset)) {
                  leftMargin = (-paramInt2 / 2);
                }
                paramInt1 = 1;
                break label797;
              }
              if (isOverflowButton)
              {
                extraPixels = paramInt2;
                expanded = true;
                rightMargin = (-paramInt2 / 2);
                paramInt1 = 1;
                break label797;
              }
              if (j != 0) {
                leftMargin = (paramInt2 / 2);
              }
              if (j != i11 - 1) {
                rightMargin = (paramInt2 / 2);
              }
              break label797;
              k = j;
              if (k != 0)
              {
                paramInt1 = 0;
                if (paramInt1 < i11)
                {
                  localObject = getChildAt(paramInt1);
                  localLayoutParams = (LayoutParams)((View)localObject).getLayoutParams();
                  if (!expanded) {}
                  for (;;)
                  {
                    paramInt1 += 1;
                    break;
                    paramInt2 = cellsUsed;
                    ((View)localObject).measure(View.MeasureSpec.makeMeasureSpec(extraPixels + paramInt2 * i10, 1073741824), i8);
                  }
                }
              }
              if (i7 != 1073741824) {}
              for (;;)
              {
                setMeasuredDimension(i9, i);
                return;
                i = i6;
              }
              f1 = f2;
              break label748;
            }
            i5 = paramInt1;
            paramInt1 = paramInt2;
            paramInt2 = i5;
            break label503;
          }
        }
        paramInt1 = i1;
        k = n;
        n = i3;
        m = paramInt2;
        paramInt2 = k;
        k = j;
        j = n;
        break;
      }
    }
  }
  
  private void onMeasureExactFormatInSplit(int paramInt1, int paramInt2)
  {
    int k = ActionBarPolicy.get(getContext()).getSplitActionBarPadding();
    int i2 = View.MeasureSpec.getMode(paramInt2);
    int i = View.MeasureSpec.getSize(paramInt1);
    int n = View.MeasureSpec.getSize(paramInt2);
    int j = getPaddingLeft() + getPaddingRight() + k * 2;
    int i3 = getPaddingTop() + getPaddingBottom();
    Log.d("ActionMenuView", "onMeasureExactFormatInSplit:extraPadding=" + k + ";widthSize=" + i + ";widthPadding=" + j);
    int i4 = getChildMeasureSpec(paramInt2, i3, -2);
    paramInt2 = i - j;
    int i1 = getChildCount();
    if (i1 == 0)
    {
      setMeasuredDimension(paramInt2, 0);
      return;
    }
    int i5 = paramInt2 / i1;
    paramInt2 = 0;
    j = 0;
    k = 0;
    i = i1;
    while (k < i1)
    {
      View localView = getChildAt(k);
      int m;
      if (localView.getVisibility() == 8)
      {
        m = j;
        j = paramInt2;
        paramInt2 = m;
        m = k + 1;
        k = j;
        j = paramInt2;
        paramInt2 = k;
        k = m;
      }
      else
      {
        boolean bool = localView instanceof ActionMenuItemView;
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        expanded = false;
        extraPixels = 0;
        cellsUsed = 0;
        expandable = false;
        leftMargin = 0;
        rightMargin = 0;
        if ((bool) && (((ActionMenuItemView)localView).hasText())) {}
        for (bool = true;; bool = false)
        {
          preventEdgeOffset = bool;
          int i6 = measureChildForCellsInSplit(localView, i5, i4, i3);
          j = Math.max(j, i6);
          m = Math.max(paramInt2, localView.getMeasuredHeight());
          i -= i6;
          paramInt2 = j;
          j = m;
          break;
        }
      }
    }
    if (i2 != 1073741824) {}
    for (;;)
    {
      setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), paramInt2);
      return;
      paramInt2 = n;
    }
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return (paramLayoutParams != null) && ((paramLayoutParams instanceof LayoutParams));
  }
  
  public void dismissPopupMenus()
  {
    if (mPresenter != null) {
      mPresenter.dismissPopupMenus();
    }
  }
  
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    return false;
  }
  
  protected LayoutParams generateDefaultLayoutParams()
  {
    LayoutParams localLayoutParams = new LayoutParams(-2, -2);
    gravity = 16;
    return localLayoutParams;
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if (paramLayoutParams != null)
    {
      if ((paramLayoutParams instanceof LayoutParams)) {}
      for (paramLayoutParams = new LayoutParams((LayoutParams)paramLayoutParams);; paramLayoutParams = new LayoutParams(paramLayoutParams))
      {
        if (gravity <= 0) {
          gravity = 16;
        }
        return paramLayoutParams;
      }
    }
    return generateDefaultLayoutParams();
  }
  
  public LayoutParams generateOverflowButtonLayoutParams()
  {
    LayoutParams localLayoutParams = generateDefaultLayoutParams();
    isOverflowButton = true;
    return localLayoutParams;
  }
  
  public Menu getMenu()
  {
    ActionMenuPresenter localActionMenuPresenter;
    if (mMenu == null)
    {
      localObject = getContext();
      mMenu = new MenuBuilder((Context)localObject);
      mMenu.setCallback(new MenuBuilderCallback(null));
      mPresenter = new ActionMenuPresenter((Context)localObject);
      mPresenter.setReserveOverflow(true);
      localActionMenuPresenter = mPresenter;
      if (mActionMenuPresenterCallback == null) {
        break label110;
      }
    }
    label110:
    for (Object localObject = mActionMenuPresenterCallback;; localObject = new ActionMenuPresenterCallback(null))
    {
      localActionMenuPresenter.setCallback((MenuPresenter.Callback)localObject);
      mMenu.addMenuPresenter(mPresenter, mPopupContext);
      mPresenter.setMenuView(this);
      return mMenu;
    }
  }
  
  public int getPopupTheme()
  {
    return mPopupTheme;
  }
  
  public int getWindowAnimations()
  {
    return 0;
  }
  
  protected boolean hasSupportDividerBeforeChildAt(int paramInt)
  {
    boolean bool2 = false;
    if (paramInt == 0) {
      return false;
    }
    View localView1 = getChildAt(paramInt - 1);
    View localView2 = getChildAt(paramInt);
    boolean bool1 = bool2;
    if (paramInt < getChildCount())
    {
      bool1 = bool2;
      if ((localView1 instanceof ActionMenuChildView)) {
        bool1 = false | ((ActionMenuChildView)localView1).needsDividerAfter();
      }
    }
    if ((paramInt > 0) && ((localView2 instanceof ActionMenuChildView))) {
      return ((ActionMenuChildView)localView2).needsDividerBefore() | bool1;
    }
    return bool1;
  }
  
  public boolean hideOverflowMenu()
  {
    return (mPresenter != null) && (mPresenter.hideOverflowMenu());
  }
  
  public void initialize(MenuBuilder paramMenuBuilder)
  {
    mMenu = paramMenuBuilder;
  }
  
  public boolean invokeItem(MenuItemImpl paramMenuItemImpl)
  {
    return mMenu.performItemAction(paramMenuItemImpl, 0);
  }
  
  public boolean isOverflowMenuShowPending()
  {
    return (mPresenter != null) && (mPresenter.isOverflowMenuShowPending());
  }
  
  public boolean isOverflowMenuShowing()
  {
    return (mPresenter != null) && (mPresenter.isOverflowMenuShowing());
  }
  
  public boolean isOverflowReserved()
  {
    return mReserveOverflow;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (Build.VERSION.SDK_INT >= 8) {
      super.onConfigurationChanged(paramConfiguration);
    }
    if (mPresenter != null)
    {
      mPresenter.updateMenuView(false);
      if (mPresenter.isOverflowMenuShowing())
      {
        mPresenter.hideOverflowMenu();
        mPresenter.showOverflowMenu();
      }
    }
  }
  
  public void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    dismissPopupMenus();
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!mFormatItems)
    {
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    if (mPresenter.isSplit())
    {
      onLayoutInSplit(paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    int i2 = getChildCount();
    int i1 = (paramInt4 - paramInt2) / 2;
    int i3 = getDividerWidth();
    paramInt4 = 0;
    paramInt2 = 0;
    int j = paramInt3 - paramInt1 - getPaddingRight() - getPaddingLeft();
    int i = 0;
    paramBoolean = ViewUtils.isLayoutRtl(this);
    int k = 0;
    View localView;
    LayoutParams localLayoutParams;
    if (k < i2)
    {
      localView = getChildAt(k);
      int m;
      int n;
      if (localView.getVisibility() == 8)
      {
        m = i;
        n = j;
        j = paramInt4;
        i = paramInt2;
        paramInt4 = n;
        paramInt2 = m;
      }
      for (;;)
      {
        n = k + 1;
        k = j;
        m = i;
        i = paramInt2;
        j = paramInt4;
        paramInt2 = m;
        paramInt4 = k;
        k = n;
        break;
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (isOverflowButton)
        {
          m = localView.getMeasuredWidth();
          i = m;
          if (hasSupportDividerBeforeChildAt(k)) {
            i = m + i3;
          }
          int i4 = localView.getMeasuredHeight();
          if (paramBoolean)
          {
            m = getPaddingLeft();
            m = leftMargin + m;
            n = m + i;
          }
          for (;;)
          {
            int i5 = i1 - i4 / 2;
            localView.layout(m, i5, n, i4 + i5);
            m = j - i;
            n = 1;
            i = paramInt2;
            j = paramInt4;
            paramInt2 = n;
            paramInt4 = m;
            break;
            n = getWidth() - getPaddingRight() - rightMargin;
            m = n - i;
          }
        }
        n = localView.getMeasuredWidth() + leftMargin + rightMargin;
        m = paramInt4 + n;
        paramInt4 = m;
        if (hasSupportDividerBeforeChildAt(k)) {
          paramInt4 = m + i3;
        }
        m = j - n;
        n = paramInt2 + 1;
        j = paramInt4;
        paramInt2 = i;
        paramInt4 = m;
        i = n;
      }
    }
    if ((i2 == 1) && (i == 0))
    {
      localView = getChildAt(0);
      paramInt2 = localView.getMeasuredWidth();
      paramInt4 = localView.getMeasuredHeight();
      paramInt1 = (paramInt3 - paramInt1) / 2 - paramInt2 / 2;
      paramInt3 = i1 - paramInt4 / 2;
      localView.layout(paramInt1, paramInt3, paramInt2 + paramInt1, paramInt4 + paramInt3);
      return;
    }
    if (i != 0)
    {
      paramInt1 = 0;
      label483:
      paramInt1 = paramInt2 - paramInt1;
      if (paramInt1 <= 0) {
        break label572;
      }
      paramInt1 = j / paramInt1;
      label496:
      paramInt3 = Math.max(0, paramInt1);
      if (!paramBoolean) {
        break label642;
      }
      paramInt1 = getWidth() - getPaddingRight();
      paramInt2 = 0;
      label519:
      if (paramInt2 < i2)
      {
        localView = getChildAt(paramInt2);
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (localView.getVisibility() == 8) {
          break label765;
        }
        if (!isOverflowButton) {
          break label577;
        }
      }
    }
    label572:
    label577:
    label642:
    label649:
    label697:
    label762:
    label765:
    for (;;)
    {
      paramInt2 += 1;
      break label519;
      break;
      paramInt1 = 1;
      break label483;
      paramInt1 = 0;
      break label496;
      paramInt1 -= rightMargin;
      paramInt4 = localView.getMeasuredWidth();
      i = localView.getMeasuredHeight();
      j = i1 - i / 2;
      localView.layout(paramInt1 - paramInt4, j, paramInt1, i + j);
      paramInt1 -= leftMargin + paramInt4 + paramInt3;
      continue;
      paramInt1 = getPaddingLeft();
      paramInt2 = 0;
      if (paramInt2 < i2)
      {
        localView = getChildAt(paramInt2);
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (localView.getVisibility() == 8) {
          break label762;
        }
        if (!isOverflowButton) {
          break label697;
        }
      }
      for (;;)
      {
        paramInt2 += 1;
        break label649;
        break;
        paramInt1 += leftMargin;
        paramInt4 = localView.getMeasuredWidth();
        i = localView.getMeasuredHeight();
        j = i1 - i / 2;
        localView.layout(paramInt1, j, paramInt1 + paramInt4, i + j);
        paramInt1 = rightMargin + paramInt4 + paramInt3 + paramInt1;
      }
    }
  }
  
  protected void onLayoutInSplit(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i3 = ActionBarPolicy.get(getContext()).getSplitActionBarPadding();
    int i2 = getChildCount();
    int i1 = (paramInt4 - paramInt2) / 2;
    int i4 = getDividerWidth();
    paramInt4 = 0;
    paramInt2 = 0;
    int j = paramInt3 - paramInt1 - getPaddingRight() - getPaddingLeft() - i3 * 2;
    Log.d("ActionMenuView", "onLayoutInSplit:left = " + paramInt1 + "; right = " + paramInt3 + "; widthRemaining = " + j);
    int i = 0;
    boolean bool = ViewUtils.isLayoutRtl(this);
    int k = 0;
    View localView;
    LayoutParams localLayoutParams;
    if (k < i2)
    {
      localView = getChildAt(k);
      int m;
      int n;
      if (localView.getVisibility() == 8)
      {
        m = i;
        n = j;
        j = paramInt4;
        i = paramInt2;
        paramInt4 = n;
        paramInt2 = m;
      }
      for (;;)
      {
        n = k + 1;
        k = j;
        m = i;
        i = paramInt2;
        j = paramInt4;
        paramInt2 = m;
        paramInt4 = k;
        k = n;
        break;
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (isOverflowButton)
        {
          m = localView.getMeasuredWidth();
          i = m;
          if (hasSupportDividerBeforeChildAt(k)) {
            i = m + i4;
          }
          int i5 = localView.getMeasuredHeight();
          if (bool)
          {
            m = getPaddingLeft();
            m = leftMargin + (m + i3);
            n = m + i;
          }
          for (;;)
          {
            int i6 = i1 - i5 / 2;
            localView.layout(m, i6, n, i5 + i6);
            m = j - i;
            n = 1;
            i = paramInt2;
            j = paramInt4;
            paramInt2 = n;
            paramInt4 = m;
            break;
            n = getWidth() - i3 - getPaddingRight() - rightMargin;
            m = n - i;
          }
        }
        n = localView.getMeasuredWidth() + leftMargin + rightMargin;
        m = paramInt4 + n;
        paramInt4 = m;
        if (hasSupportDividerBeforeChildAt(k)) {
          paramInt4 = m + i4;
        }
        m = j - n;
        n = paramInt2 + 1;
        j = paramInt4;
        paramInt2 = i;
        paramInt4 = m;
        i = n;
      }
    }
    if ((i2 == 1) && (i == 0))
    {
      localView = getChildAt(0);
      paramInt2 = localView.getMeasuredWidth();
      paramInt4 = localView.getMeasuredHeight();
      paramInt1 = (paramInt3 - paramInt1) / 2 - paramInt2 / 2;
      paramInt3 = i1 - paramInt4 / 2;
      localView.layout(paramInt1, paramInt3, paramInt2 + paramInt1, paramInt4 + paramInt3);
      return;
    }
    if (i != 0)
    {
      paramInt1 = 0;
      label510:
      paramInt1 = paramInt2 - paramInt1;
      if (paramInt1 <= 0) {
        break label602;
      }
      paramInt1 = j / paramInt1;
      label523:
      paramInt3 = Math.max(0, paramInt1);
      if (!bool) {
        break label671;
      }
      paramInt1 = getWidth() - getPaddingRight() - i3;
      paramInt2 = 0;
      label549:
      if (paramInt2 < i2)
      {
        localView = getChildAt(paramInt2);
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (localView.getVisibility() == 8) {
          break label833;
        }
        if (!isOverflowButton) {
          break label607;
        }
      }
    }
    label602:
    label607:
    label671:
    label681:
    label729:
    label830:
    label833:
    for (;;)
    {
      paramInt2 += 1;
      break label549;
      break;
      paramInt1 = 1;
      break label510;
      paramInt1 = 0;
      break label523;
      paramInt1 -= rightMargin;
      paramInt4 = localView.getMeasuredWidth();
      i = localView.getMeasuredHeight();
      j = i1 - i / 2;
      localView.layout(paramInt1 - paramInt4, j, paramInt1, i + j);
      paramInt1 -= leftMargin + paramInt4 + paramInt3;
      continue;
      paramInt1 = getPaddingLeft() + i3;
      paramInt2 = 0;
      if (paramInt2 < i2)
      {
        localView = getChildAt(paramInt2);
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (localView.getVisibility() == 8) {
          break label830;
        }
        if (!isOverflowButton) {
          break label729;
        }
      }
      for (;;)
      {
        paramInt2 += 1;
        break label681;
        break;
        paramInt1 += leftMargin;
        paramInt4 = localView.getMeasuredWidth();
        i = localView.getMeasuredHeight();
        j = i1 - i / 2;
        Log.d("ActionMenuView", "layout child:left=" + paramInt1 + ";width=" + paramInt4);
        localView.layout(paramInt1, j, paramInt1 + paramInt4, i + j);
        paramInt1 = rightMargin + paramInt4 + paramInt3 + paramInt1;
      }
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    boolean bool2 = mFormatItems;
    boolean bool1;
    int i;
    int j;
    if (View.MeasureSpec.getMode(paramInt1) == 1073741824)
    {
      bool1 = true;
      mFormatItems = bool1;
      if (bool2 != mFormatItems) {
        mFormatItemsWidth = 0;
      }
      i = View.MeasureSpec.getSize(paramInt1);
      Log.d("ActionMenuView", "onMeasure:widthSize=" + i + ";mFormatItems=" + mFormatItems + ";mFormatItemsWidth=" + mFormatItemsWidth);
      if ((mFormatItems) && (mMenu != null) && (i != mFormatItemsWidth))
      {
        mFormatItemsWidth = i;
        mMenu.onItemsChanged(true);
      }
      j = getChildCount();
      if ((!mFormatItems) || (j <= 0)) {
        break label203;
      }
      onMeasureExactFormat(paramInt1, paramInt2);
    }
    for (;;)
    {
      Log.d("ActionMenuView", "onMeasure:getMeasuredWidth = " + getMeasuredWidth() + "; getMeasuredHeight = " + getMeasuredHeight());
      return;
      bool1 = false;
      break;
      label203:
      i = 0;
      while (i < j)
      {
        LayoutParams localLayoutParams = (LayoutParams)getChildAt(i).getLayoutParams();
        rightMargin = 0;
        leftMargin = 0;
        applyFlymeStyle(localLayoutParams, i, j);
        i += 1;
      }
      super.onMeasure(paramInt1, paramInt2);
    }
  }
  
  public MenuBuilder peekMenu()
  {
    return mMenu;
  }
  
  public void setBottonBarStyleDivider()
  {
    setButtonBarStyleDivider();
  }
  
  public void setButtonBarStyleDivider()
  {
    setShowDividers(2);
    setDividerDrawable(getResources().getDrawable(R.drawable.mz_button_bar_style_divider));
    setDividerPadding(getResources().getDimensionPixelSize(R.dimen.mz_button_bar_style_divider_padding));
  }
  
  public void setExpandedActionViewsExclusive(boolean paramBoolean)
  {
    mPresenter.setExpandedActionViewsExclusive(paramBoolean);
  }
  
  public void setHasOverflow(boolean paramBoolean)
  {
    mHasOverflow = paramBoolean;
  }
  
  public void setMenuCallbacks(MenuPresenter.Callback paramCallback, MenuBuilder.Callback paramCallback1)
  {
    mActionMenuPresenterCallback = paramCallback;
    mMenuBuilderCallback = paramCallback1;
  }
  
  public void setOnMenuItemClickListener(OnMenuItemClickListener paramOnMenuItemClickListener)
  {
    mOnMenuItemClickListener = paramOnMenuItemClickListener;
  }
  
  public void setOverflowDrawable(Drawable paramDrawable)
  {
    if (mPresenter != null) {
      mPresenter.setOverflowDrawable(paramDrawable);
    }
  }
  
  public void setOverflowReserved(boolean paramBoolean)
  {
    mReserveOverflow = paramBoolean;
  }
  
  public void setPopupTheme(int paramInt)
  {
    if (mPopupTheme != paramInt)
    {
      mPopupTheme = paramInt;
      if (paramInt == 0) {
        mPopupContext = mContext;
      }
    }
    else
    {
      return;
    }
    mPopupContext = new ContextThemeWrapper(mContext, paramInt);
  }
  
  public void setPresenter(ActionMenuPresenter paramActionMenuPresenter)
  {
    mPresenter = paramActionMenuPresenter;
    mPresenter.setMenuView(this);
  }
  
  public boolean showOverflowMenu()
  {
    return (mPresenter != null) && (mPresenter.showOverflowMenu());
  }
  
  public static abstract interface ActionMenuChildView
  {
    public abstract boolean needsDividerAfter();
    
    public abstract boolean needsDividerBefore();
  }
  
  class ActionMenuPresenterCallback
    implements MenuPresenter.Callback
  {
    private ActionMenuPresenterCallback() {}
    
    public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean) {}
    
    public boolean onOpenSubMenu(MenuBuilder paramMenuBuilder)
    {
      return false;
    }
  }
  
  public static class LayoutParams
    extends LinearLayoutCompat.LayoutParams
  {
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
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
      isOverflowButton = false;
    }
    
    LayoutParams(int paramInt1, int paramInt2, boolean paramBoolean)
    {
      super(paramInt2);
      isOverflowButton = paramBoolean;
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    public LayoutParams(LayoutParams paramLayoutParams)
    {
      super();
      isOverflowButton = isOverflowButton;
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
  }
  
  class MenuBuilderCallback
    implements MenuBuilder.Callback
  {
    private MenuBuilderCallback() {}
    
    public boolean onMenuItemSelected(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem)
    {
      return (mOnMenuItemClickListener != null) && (mOnMenuItemClickListener.onMenuItemClick(paramMenuItem));
    }
    
    public void onMenuModeChange(MenuBuilder paramMenuBuilder)
    {
      if (mMenuBuilderCallback != null) {
        mMenuBuilderCallback.onMenuModeChange(paramMenuBuilder);
      }
    }
  }
  
  public static abstract interface OnMenuItemClickListener
  {
    public abstract boolean onMenuItemClick(MenuItem paramMenuItem);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ActionMenuView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */