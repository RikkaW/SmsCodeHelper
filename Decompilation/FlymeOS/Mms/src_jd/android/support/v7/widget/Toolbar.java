package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.bool;
import android.support.v7.appcompat.R.dimen;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.internal.view.SupportMenuInflater;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuBuilder.Callback;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.support.v7.internal.widget.DecorToolbar;
import android.support.v7.internal.widget.RtlSpacingHelper;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.internal.widget.ToolbarWidgetWrapper;
import android.support.v7.internal.widget.ViewUtils;
import android.support.v7.view.CollapsibleActionView;
import android.text.Layout;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import aqt;
import java.util.ArrayList;
import java.util.List;

public class Toolbar
  extends ViewGroup
{
  private static final String TAG = "Toolbar";
  private MenuPresenter.Callback mActionMenuPresenterCallback;
  private int mButtonGravity;
  private ImageButton mCollapseButtonView;
  private CharSequence mCollapseDescription;
  private Drawable mCollapseIcon;
  private boolean mCollapsible;
  private final RtlSpacingHelper mContentInsets = new RtlSpacingHelper();
  private boolean mEatingHover;
  private boolean mEatingTouch;
  View mExpandedActionView;
  private ExpandedActionViewMenuPresenter mExpandedMenuPresenter;
  private int mGravity = 8388627;
  private ImageView mLogoView;
  private int mMaxButtonHeight;
  private MenuBuilder.Callback mMenuBuilderCallback;
  private ActionMenuView mMenuView;
  private final ActionMenuView.OnMenuItemClickListener mMenuViewItemClickListener = new Toolbar.1(this);
  protected ViewPropertyAnimatorCompat mMenuViewVisibilityAnim;
  private ImageButton mNavButtonView;
  private OnMenuItemClickListener mOnMenuItemClickListener;
  private ActionMenuPresenter mOuterActionMenuPresenter;
  private Context mPopupContext;
  private int mPopupTheme;
  private final Runnable mShowOverflowMenuRunnable = new Toolbar.2(this);
  protected boolean mSplitActionBar;
  protected ViewGroup mSplitView;
  protected boolean mSplitWhenNarrow;
  private CharSequence mSubtitleText;
  private int mSubtitleTextAppearance;
  private int mSubtitleTextColor;
  private TextView mSubtitleTextView;
  private final int[] mTempMargins = new int[2];
  private final ArrayList<View> mTempViews = new ArrayList();
  private final TintManager mTintManager;
  private int mTitleMarginBottom;
  private int mTitleMarginEnd;
  private int mTitleMarginStart;
  private int mTitleMarginTop;
  private CharSequence mTitleText;
  private int mTitleTextAppearance;
  private int mTitleTextColor;
  private TextView mTitleTextView;
  protected final VisibilityAnimListener mVisAnimListener = new VisibilityAnimListener();
  private ToolbarWidgetWrapper mWrapper;
  
  public Toolbar(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public Toolbar(Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.toolbarStyle);
  }
  
  public Toolbar(Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = TintTypedArray.obtainStyledAttributes(getContext(), paramAttributeSet, R.styleable.Toolbar, paramInt, 0);
    mTitleTextAppearance = paramContext.getResourceId(R.styleable.Toolbar_titleTextAppearance, 0);
    mSubtitleTextAppearance = paramContext.getResourceId(R.styleable.Toolbar_subtitleTextAppearance, 0);
    mGravity = paramContext.getInteger(R.styleable.Toolbar_android_gravity, mGravity);
    mButtonGravity = paramContext.getInteger(R.styleable.Toolbar_mzButtonGravity, 48);
    paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_titleMargins, 0);
    mTitleMarginBottom = paramInt;
    mTitleMarginTop = paramInt;
    mTitleMarginEnd = paramInt;
    mTitleMarginStart = paramInt;
    paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginStart, -1);
    if (paramInt >= 0) {
      mTitleMarginStart = paramInt;
    }
    paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginEnd, -1);
    if (paramInt >= 0) {
      mTitleMarginEnd = paramInt;
    }
    paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginTop, -1);
    if (paramInt >= 0) {
      mTitleMarginTop = paramInt;
    }
    paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginBottom, -1);
    if (paramInt >= 0) {
      mTitleMarginBottom = paramInt;
    }
    mMaxButtonHeight = paramContext.getDimensionPixelSize(R.styleable.Toolbar_maxButtonHeight, -1);
    paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_contentInsetStart, Integer.MIN_VALUE);
    int i = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
    int j = paramContext.getDimensionPixelSize(R.styleable.Toolbar_contentInsetLeft, 0);
    int k = paramContext.getDimensionPixelSize(R.styleable.Toolbar_contentInsetRight, 0);
    mContentInsets.setAbsolute(j, k);
    if ((paramInt != Integer.MIN_VALUE) || (i != Integer.MIN_VALUE)) {
      mContentInsets.setRelative(paramInt, i);
    }
    mCollapseIcon = paramContext.getDrawable(R.styleable.Toolbar_collapseIcon);
    mCollapseDescription = paramContext.getText(R.styleable.Toolbar_collapseContentDescription);
    paramAttributeSet = paramContext.getText(R.styleable.Toolbar_title);
    if (!TextUtils.isEmpty(paramAttributeSet)) {
      setTitle(paramAttributeSet);
    }
    paramAttributeSet = paramContext.getText(R.styleable.Toolbar_subtitle);
    if (!TextUtils.isEmpty(paramAttributeSet)) {
      setSubtitle(paramAttributeSet);
    }
    mPopupContext = getContext();
    setPopupTheme(paramContext.getResourceId(R.styleable.Toolbar_popupTheme, 0));
    paramAttributeSet = paramContext.getDrawable(R.styleable.Toolbar_navigationIcon);
    if (paramAttributeSet != null) {
      setNavigationIcon(paramAttributeSet);
    }
    paramAttributeSet = paramContext.getText(R.styleable.Toolbar_navigationContentDescription);
    if (!TextUtils.isEmpty(paramAttributeSet)) {
      setNavigationContentDescription(paramAttributeSet);
    }
    paramContext.recycle();
    mTintManager = paramContext.getTintManager();
  }
  
  private void addCustomViewsWithGravity(List<View> paramList, int paramInt)
  {
    int i = 1;
    int j = 0;
    if (ViewCompat.getLayoutDirection(this) == 1) {}
    int m;
    int k;
    View localView;
    LayoutParams localLayoutParams;
    for (;;)
    {
      m = getChildCount();
      k = GravityCompat.getAbsoluteGravity(paramInt, ViewCompat.getLayoutDirection(this));
      paramList.clear();
      paramInt = j;
      if (i == 0) {
        break;
      }
      paramInt = m - 1;
      while (paramInt >= 0)
      {
        localView = getChildAt(paramInt);
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if ((mViewType == 0) && (shouldLayout(localView)) && (getChildHorizontalGravity(gravity) == k)) {
          paramList.add(localView);
        }
        paramInt -= 1;
      }
      i = 0;
    }
    while (paramInt < m)
    {
      localView = getChildAt(paramInt);
      localLayoutParams = (LayoutParams)localView.getLayoutParams();
      if ((mViewType == 0) && (shouldLayout(localView)) && (getChildHorizontalGravity(gravity) == k)) {
        paramList.add(localView);
      }
      paramInt += 1;
    }
  }
  
  private void addSystemView(View paramView)
  {
    Object localObject = paramView.getLayoutParams();
    if (localObject == null) {
      localObject = generateDefaultLayoutParams();
    }
    for (;;)
    {
      mViewType = 1;
      addView(paramView, (ViewGroup.LayoutParams)localObject);
      return;
      if (!checkLayoutParams((ViewGroup.LayoutParams)localObject)) {
        localObject = generateLayoutParams((ViewGroup.LayoutParams)localObject);
      } else {
        localObject = (LayoutParams)localObject;
      }
    }
  }
  
  private void ensureCollapseButtonView()
  {
    if (mCollapseButtonView == null)
    {
      mCollapseButtonView = new ImageButton(getContext(), null, R.attr.toolbarNavigationButtonStyle);
      mCollapseButtonView.setImageDrawable(mCollapseIcon);
      mCollapseButtonView.setContentDescription(mCollapseDescription);
      LayoutParams localLayoutParams = generateDefaultLayoutParams();
      gravity = (0x800003 | mButtonGravity & 0x70);
      mViewType = 2;
      mCollapseButtonView.setLayoutParams(localLayoutParams);
      mCollapseButtonView.setOnClickListener(new Toolbar.4(this));
    }
  }
  
  private void ensureLogoView()
  {
    if (mLogoView == null) {
      mLogoView = new ImageView(getContext());
    }
  }
  
  private void ensureMenu()
  {
    ensureMenuView();
    if (mMenuView.peekMenu() == null)
    {
      MenuBuilder localMenuBuilder = (MenuBuilder)mMenuView.getMenu();
      if (mExpandedMenuPresenter == null) {
        mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter(null);
      }
      mMenuView.setExpandedActionViewsExclusive(true);
      localMenuBuilder.addMenuPresenter(mExpandedMenuPresenter, mPopupContext);
    }
  }
  
  private void ensureMenuView()
  {
    if (mMenuView == null)
    {
      mMenuView = new ActionMenuView(getContext());
      mMenuView.setPopupTheme(mPopupTheme);
      mMenuView.setOnMenuItemClickListener(mMenuViewItemClickListener);
      mMenuView.setMenuCallbacks(mActionMenuPresenterCallback, mMenuBuilderCallback);
      LayoutParams localLayoutParams = generateDefaultLayoutParams();
      gravity = (0x800005 | mButtonGravity & 0x70);
      mMenuView.setLayoutParams(localLayoutParams);
      mMenuView.setId(R.id.mz_action_menu_view);
      if (mSplitActionBar)
      {
        width = -1;
        mSplitView.addView(mMenuView, 0, localLayoutParams);
      }
    }
    else
    {
      return;
    }
    addSystemView(mMenuView);
  }
  
  private void ensureNavButtonView()
  {
    int i = 1;
    Object localObject;
    if (mNavButtonView == null)
    {
      mNavButtonView = new ImageButton(getContext(), null, R.attr.toolbarNavigationButtonStyle);
      localObject = generateDefaultLayoutParams();
      gravity = (0x800003 | mButtonGravity & 0x70);
      mNavButtonView.setLayoutParams((ViewGroup.LayoutParams)localObject);
      mNavButtonView.setId(R.id.mz_toolbar_nav_button);
      if (ViewCompat.getLayoutDirection(this) != 1) {
        break label144;
      }
      if (i == 0) {
        break label149;
      }
      rightMargin = getResources().getDimensionPixelOffset(R.dimen.mz_toolbar_nav_btn_margin_left);
    }
    for (;;)
    {
      mNavButtonView.addOnLayoutChangeListener(new Toolbar.3(this));
      if (Build.VERSION.SDK_INT < 21)
      {
        localObject = new aqt(mNavButtonView, R.attr.mzActionButtonRippleSplitStyle);
        mNavButtonView.setBackgroundDrawable((Drawable)localObject);
        setClipChildren(false);
      }
      return;
      label144:
      i = 0;
      break;
      label149:
      leftMargin = getResources().getDimensionPixelOffset(R.dimen.mz_toolbar_nav_btn_margin_left);
    }
  }
  
  private int getChildHorizontalGravity(int paramInt)
  {
    int j = ViewCompat.getLayoutDirection(this);
    int i = GravityCompat.getAbsoluteGravity(paramInt, j) & 0x7;
    paramInt = i;
    switch (i)
    {
    case 2: 
    case 4: 
    default: 
      if (j == 1) {
        paramInt = 5;
      }
      break;
    case 1: 
    case 3: 
    case 5: 
      return paramInt;
    }
    return 3;
  }
  
  private int getChildTop(View paramView, int paramInt)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    int j = paramView.getMeasuredHeight();
    if (paramInt > 0) {
      paramInt = (j - paramInt) / 2;
    }
    int i;
    int k;
    int m;
    switch (getChildVerticalGravity(gravity))
    {
    default: 
      i = getPaddingTop();
      k = getPaddingBottom();
      m = getHeight();
      paramInt = (m - i - k - j) / 2;
      if (paramInt < topMargin) {
        paramInt = topMargin;
      }
      break;
    }
    for (;;)
    {
      return paramInt + i;
      return getPaddingTop();
      return getHeight() - getPaddingBottom() - j - bottomMargin;
      j = m - k - j - paramInt - i;
      if (j < bottomMargin) {
        paramInt = Math.max(0, paramInt - (bottomMargin - j));
      }
    }
  }
  
  private int getChildVerticalGravity(int paramInt)
  {
    int i = paramInt & 0x70;
    paramInt = i;
    switch (i)
    {
    default: 
      paramInt = mGravity & 0x70;
    }
    return paramInt;
  }
  
  private int getHorizontalMargins(View paramView)
  {
    paramView = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    int i = MarginLayoutParamsCompat.getMarginStart(paramView);
    return MarginLayoutParamsCompat.getMarginEnd(paramView) + i;
  }
  
  private MenuInflater getMenuInflater()
  {
    return new SupportMenuInflater(getContext());
  }
  
  private int getVerticalMargins(View paramView)
  {
    paramView = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    int i = topMargin;
    return bottomMargin + i;
  }
  
  private int getViewListMeasuredWidth(List<View> paramList, int[] paramArrayOfInt)
  {
    int m = paramArrayOfInt[0];
    int k = paramArrayOfInt[1];
    int n = paramList.size();
    int j = 0;
    int i = 0;
    while (j < n)
    {
      paramArrayOfInt = (View)paramList.get(j);
      LayoutParams localLayoutParams = (LayoutParams)paramArrayOfInt.getLayoutParams();
      m = leftMargin - m;
      k = rightMargin - k;
      int i1 = Math.max(0, m);
      int i2 = Math.max(0, k);
      m = Math.max(0, -m);
      k = Math.max(0, -k);
      int i3 = paramArrayOfInt.getMeasuredWidth();
      j += 1;
      i += i3 + i1 + i2;
    }
    return i;
  }
  
  private static boolean isCustomView(View paramView)
  {
    return getLayoutParamsmViewType == 0;
  }
  
  private int layoutChildLeft(View paramView, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    int i = leftMargin - paramArrayOfInt[0];
    paramInt1 = Math.max(0, i) + paramInt1;
    paramArrayOfInt[0] = Math.max(0, -i);
    paramInt2 = getChildTop(paramView, paramInt2);
    i = paramView.getMeasuredWidth();
    paramView.layout(paramInt1, paramInt2, paramInt1 + i, paramView.getMeasuredHeight() + paramInt2);
    return rightMargin + i + paramInt1;
  }
  
  private int layoutChildRight(View paramView, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    int i = rightMargin - paramArrayOfInt[1];
    paramInt1 -= Math.max(0, i);
    paramArrayOfInt[1] = Math.max(0, -i);
    paramInt2 = getChildTop(paramView, paramInt2);
    i = paramView.getMeasuredWidth();
    paramView.layout(paramInt1 - i, paramInt2, paramInt1, paramView.getMeasuredHeight() + paramInt2);
    return paramInt1 - (leftMargin + i);
  }
  
  private int measureChildCollapseMargins(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt)
  {
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    int i = leftMargin - paramArrayOfInt[0];
    int j = rightMargin - paramArrayOfInt[1];
    int k = Math.max(0, i) + Math.max(0, j);
    paramArrayOfInt[0] = Math.max(0, -i);
    paramArrayOfInt[1] = Math.max(0, -j);
    paramView.measure(getChildMeasureSpec(paramInt1, getPaddingLeft() + getPaddingRight() + k + paramInt2, width), getChildMeasureSpec(paramInt3, getPaddingTop() + getPaddingBottom() + topMargin + bottomMargin + paramInt4, height));
    return paramView.getMeasuredWidth() + k;
  }
  
  private void measureChildConstrained(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    int i = getChildMeasureSpec(paramInt1, getPaddingLeft() + getPaddingRight() + leftMargin + rightMargin + paramInt2, width);
    paramInt2 = getChildMeasureSpec(paramInt3, getPaddingTop() + getPaddingBottom() + topMargin + bottomMargin + paramInt4, height);
    paramInt3 = View.MeasureSpec.getMode(paramInt2);
    paramInt1 = paramInt2;
    if (paramInt3 != 1073741824)
    {
      paramInt1 = paramInt2;
      if (paramInt5 >= 0)
      {
        paramInt1 = paramInt5;
        if (paramInt3 != 0) {
          paramInt1 = Math.min(View.MeasureSpec.getSize(paramInt2), paramInt5);
        }
        paramInt1 = View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824);
      }
    }
    paramView.measure(i, paramInt1);
  }
  
  private void onLayoutForFlyme(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i;
    int i2;
    int i4;
    int j;
    int i3;
    int n;
    int i5;
    int[] arrayOfInt;
    int i1;
    if (ViewCompat.getLayoutDirection(this) == 1)
    {
      i = 1;
      i2 = getWidth();
      i4 = getHeight();
      j = getPaddingLeft();
      i3 = getPaddingRight();
      n = getPaddingTop();
      i5 = getPaddingBottom();
      paramInt4 = i2 - i3;
      arrayOfInt = mTempMargins;
      arrayOfInt[1] = 0;
      arrayOfInt[0] = 0;
      i1 = ViewCompat.getMinimumHeight(this);
      if (!shouldLayout(mNavButtonView)) {
        break label1707;
      }
      if (i == 0) {
        break label888;
      }
      paramInt4 = layoutChildRight(mNavButtonView, paramInt4, arrayOfInt, i1);
      paramInt1 = j;
    }
    for (;;)
    {
      label111:
      paramInt2 = paramInt4;
      paramInt3 = paramInt1;
      if (shouldLayout(mCollapseButtonView))
      {
        if (i != 0)
        {
          paramInt2 = layoutChildRight(mCollapseButtonView, paramInt4, arrayOfInt, i1);
          paramInt3 = paramInt1;
        }
      }
      else
      {
        label151:
        paramInt1 = paramInt2;
        paramInt4 = paramInt3;
        if (shouldLayout(mMenuView))
        {
          if (i == 0) {
            break label927;
          }
          paramInt4 = layoutChildLeft(mMenuView, paramInt3, arrayOfInt, i1);
          paramInt1 = paramInt2;
        }
        label191:
        arrayOfInt[0] = Math.max(0, getContentInsetLeft() - paramInt4);
        arrayOfInt[1] = Math.max(0, getContentInsetRight() - (i2 - i3 - paramInt1));
        paramInt3 = Math.max(paramInt4, getContentInsetLeft());
        paramInt4 = Math.min(paramInt1, i2 - i3 - getContentInsetRight());
        paramInt2 = paramInt4;
        paramInt1 = paramInt3;
        if (shouldLayout(mExpandedActionView))
        {
          if (i == 0) {
            break label948;
          }
          paramInt2 = layoutChildRight(mExpandedActionView, paramInt4, arrayOfInt, i1);
          paramInt1 = paramInt3;
        }
        label293:
        if (!shouldLayout(mLogoView)) {
          break label1701;
        }
        if (i == 0) {
          break label969;
        }
        paramInt3 = layoutChildRight(mLogoView, paramInt2, arrayOfInt, i1);
      }
      for (;;)
      {
        label324:
        paramBoolean = shouldLayout(mTitleTextView);
        boolean bool = shouldLayout(mSubtitleTextView);
        paramInt2 = 0;
        Object localObject1;
        if (paramBoolean)
        {
          localObject1 = (LayoutParams)mTitleTextView.getLayoutParams();
          paramInt2 = topMargin;
          paramInt4 = mTitleTextView.getMeasuredHeight();
          paramInt2 = 0 + (bottomMargin + (paramInt2 + paramInt4));
        }
        int k;
        if (bool)
        {
          localObject1 = (LayoutParams)mSubtitleTextView.getLayoutParams();
          paramInt4 = topMargin;
          k = mSubtitleTextView.getMeasuredHeight();
        }
        for (int m = bottomMargin + (paramInt4 + k) + paramInt2;; m = paramInt2)
        {
          label462:
          Object localObject2;
          if (!paramBoolean)
          {
            paramInt4 = paramInt3;
            paramInt2 = paramInt1;
            if (!bool) {}
          }
          else
          {
            if (!paramBoolean) {
              break label989;
            }
            localObject1 = mTitleTextView;
            if (!bool) {
              break label998;
            }
            localObject2 = mSubtitleTextView;
            label473:
            localObject1 = (LayoutParams)((View)localObject1).getLayoutParams();
            localObject2 = (LayoutParams)((View)localObject2).getLayoutParams();
            if (((!paramBoolean) || (mTitleTextView.getMeasuredWidth() <= 0)) && ((!bool) || (mSubtitleTextView.getMeasuredWidth() <= 0))) {
              break label1007;
            }
            k = 1;
            label525:
            switch (mGravity & 0x70)
            {
            default: 
              paramInt2 = (i4 - n - i5 - m) / 2;
              if (paramInt2 < topMargin + mTitleMarginTop) {
                paramInt2 = topMargin + mTitleMarginTop;
              }
              break;
            }
          }
          label599:
          label604:
          label620:
          label888:
          label927:
          label948:
          label969:
          label989:
          label998:
          label1007:
          label1159:
          label1182:
          label1199:
          label1446:
          label1662:
          label1685:
          label1692:
          for (;;)
          {
            paramInt2 = n + paramInt2;
            if (i != 0) {
              if (k != 0)
              {
                paramInt4 = mTitleMarginStart;
                paramInt4 -= arrayOfInt[1];
                paramInt3 -= Math.max(0, paramInt4);
                arrayOfInt[1] = Math.max(0, -paramInt4);
                if (!paramBoolean) {
                  break label1685;
                }
                localObject1 = (LayoutParams)mTitleTextView.getLayoutParams();
                paramInt4 = paramInt3 - mTitleTextView.getMeasuredWidth();
                i = mTitleTextView.getMeasuredHeight() + paramInt2;
                mTitleTextView.layout(paramInt4, paramInt2, paramInt3, i);
                m = mTitleMarginEnd;
                paramInt2 = i + bottomMargin;
                paramInt4 -= m;
              }
            }
            for (;;)
            {
              if (bool)
              {
                localObject1 = (LayoutParams)mSubtitleTextView.getLayoutParams();
                paramInt2 = topMargin + paramInt2;
                i = mSubtitleTextView.getMeasuredWidth();
                m = mSubtitleTextView.getMeasuredHeight() + paramInt2;
                mSubtitleTextView.layout(paramInt3 - i, paramInt2, paramInt3, m);
                paramInt2 = mTitleMarginEnd;
                i = bottomMargin;
              }
              for (paramInt2 = paramInt3 - paramInt2;; paramInt2 = paramInt3)
              {
                if (k != 0) {}
                for (paramInt2 = Math.min(paramInt4, paramInt2);; paramInt2 = paramInt3)
                {
                  paramInt4 = paramInt2;
                  paramInt2 = paramInt1;
                  addCustomViewsWithGravity(mTempViews, 3);
                  i = mTempViews.size();
                  paramInt3 = 0;
                  paramInt1 = paramInt2;
                  paramInt2 = paramInt3;
                  for (;;)
                  {
                    if (paramInt2 < i)
                    {
                      paramInt1 = layoutChildLeft((View)mTempViews.get(paramInt2), paramInt1, arrayOfInt, i1);
                      paramInt2 += 1;
                      continue;
                      i = 0;
                      break;
                      paramInt1 = layoutChildLeft(mNavButtonView, j, arrayOfInt, i1);
                      break label111;
                      paramInt3 = layoutChildLeft(mCollapseButtonView, paramInt1, arrayOfInt, i1);
                      paramInt2 = paramInt4;
                      break label151;
                      paramInt1 = layoutChildRight(mMenuView, paramInt2, arrayOfInt, i1);
                      paramInt4 = paramInt3;
                      break label191;
                      paramInt1 = layoutChildLeft(mExpandedActionView, paramInt3, arrayOfInt, i1);
                      paramInt2 = paramInt4;
                      break label293;
                      paramInt1 = layoutChildLeft(mLogoView, paramInt1, arrayOfInt, i1);
                      paramInt3 = paramInt2;
                      break label324;
                      localObject1 = mSubtitleTextView;
                      break label462;
                      localObject2 = mTitleTextView;
                      break label473;
                      k = 0;
                      break label525;
                      paramInt2 = getPaddingTop();
                      paramInt2 = topMargin + paramInt2 + mTitleMarginTop;
                      break label604;
                      paramInt4 = i4 - i5 - m - paramInt2 - n;
                      if (paramInt4 >= bottomMargin + mTitleMarginBottom) {
                        break label1692;
                      }
                      paramInt2 = Math.max(0, paramInt2 - (bottomMargin + mTitleMarginBottom - paramInt4));
                      break label599;
                      paramInt2 = i4 - i5 - bottomMargin - mTitleMarginBottom - m;
                      break label604;
                      paramInt4 = 0;
                      break label620;
                      paramInt4 = 0;
                      m = 0;
                      switch (mGravity & 0x7)
                      {
                      default: 
                        if (k != 0)
                        {
                          paramInt4 = mTitleMarginStart;
                          paramInt4 -= arrayOfInt[0];
                          if (arrayOfInt[0] <= 0) {
                            break label1446;
                          }
                          paramInt1 = mTitleMarginStart + paramInt1;
                          arrayOfInt[0] = Math.max(0, -paramInt4);
                          paramInt4 = paramInt1;
                          i = paramInt1;
                        }
                        break;
                      }
                    }
                  }
                  for (;;)
                  {
                    m = i;
                    n = paramInt2;
                    if (paramBoolean)
                    {
                      localObject1 = (LayoutParams)mTitleTextView.getLayoutParams();
                      m = mTitleTextView.getMeasuredWidth() + i;
                      n = mTitleTextView.getMeasuredHeight() + paramInt2;
                      mTitleTextView.layout(i, paramInt2, m, n);
                      m = mTitleMarginEnd + m;
                      n = bottomMargin + n;
                    }
                    if (bool)
                    {
                      paramInt2 = mSubtitleTextView.getLayoutParams()).topMargin + n;
                      i = mSubtitleTextView.getMeasuredWidth() + paramInt4;
                      n = mSubtitleTextView.getMeasuredHeight();
                      mSubtitleTextView.layout(paramInt4, paramInt2, i, n + paramInt2);
                    }
                    for (paramInt2 = mTitleMarginEnd + i;; paramInt2 = paramInt4)
                    {
                      if (k != 0) {
                        paramInt1 = Math.max(m, paramInt2);
                      }
                      for (;;)
                      {
                        if ((mGravity & 0x70) == 17)
                        {
                          paramInt2 = paramInt1 + getContext().getResources().getDimensionPixelSize(R.dimen.mz_toolbar_title_margin_end);
                          paramInt4 = paramInt3;
                          break;
                          if (paramBoolean) {
                            paramInt4 = (i2 - mTitleTextView.getMeasuredWidth()) / 2;
                          }
                          if (!bool) {
                            break label1662;
                          }
                          m = (i2 - mSubtitleTextView.getMeasuredWidth()) / 2;
                          i = paramInt4;
                          paramInt4 = m;
                          break label1199;
                          paramInt4 = 0;
                          break label1159;
                          paramInt1 = Math.max(0, paramInt4) + paramInt1;
                          break label1182;
                          addCustomViewsWithGravity(mTempViews, 5);
                          paramInt3 = mTempViews.size();
                          paramInt2 = 0;
                          while (paramInt2 < paramInt3)
                          {
                            paramInt4 = layoutChildRight((View)mTempViews.get(paramInt2), paramInt4, arrayOfInt, i1);
                            paramInt2 += 1;
                          }
                          addCustomViewsWithGravity(mTempViews, 1);
                          paramInt3 = getViewListMeasuredWidth(mTempViews, arrayOfInt);
                          paramInt2 = (i2 - j - i3) / 2 + j - paramInt3 / 2;
                          paramInt3 += paramInt2;
                          if (paramInt2 < paramInt1) {}
                          for (;;)
                          {
                            paramInt4 = mTempViews.size();
                            paramInt3 = 0;
                            paramInt2 = paramInt1;
                            paramInt1 = paramInt3;
                            while (paramInt1 < paramInt4)
                            {
                              paramInt2 = layoutChildLeft((View)mTempViews.get(paramInt1), paramInt2, arrayOfInt, i1);
                              paramInt1 += 1;
                            }
                            paramInt1 = paramInt2;
                            if (paramInt3 > paramInt4) {
                              paramInt1 = paramInt2 - (paramInt3 - paramInt4);
                            }
                          }
                          mTempViews.clear();
                          return;
                        }
                        paramInt4 = paramInt3;
                        paramInt2 = paramInt1;
                        break;
                      }
                    }
                    i = paramInt4;
                    paramInt4 = m;
                  }
                }
              }
              paramInt4 = paramInt3;
            }
          }
        }
        label1701:
        paramInt3 = paramInt2;
      }
      label1707:
      paramInt1 = j;
    }
  }
  
  private void onMeasureForFlyme(int paramInt1, int paramInt2)
  {
    int m = 0;
    int k = 0;
    int[] arrayOfInt = mTempMargins;
    int n;
    int i1;
    int i;
    int j;
    if (ViewUtils.isLayoutRtl(this))
    {
      n = 0;
      i1 = 1;
      i = 0;
      if (shouldLayout(mNavButtonView))
      {
        measureChildConstrained(mNavButtonView, paramInt1, 0, paramInt2, 0, mMaxButtonHeight);
        i = mNavButtonView.getMeasuredWidth() + getHorizontalMargins(mNavButtonView);
        m = Math.max(0, mNavButtonView.getMeasuredHeight() + getVerticalMargins(mNavButtonView));
        k = ViewUtils.combineMeasuredStates(0, ViewCompat.getMeasuredState(mNavButtonView));
      }
      i2 = i;
      j = k;
      i = m;
      if (shouldLayout(mCollapseButtonView))
      {
        measureChildConstrained(mCollapseButtonView, paramInt1, 0, paramInt2, 0, mMaxButtonHeight);
        i2 = mCollapseButtonView.getMeasuredWidth() + getHorizontalMargins(mCollapseButtonView);
        i = Math.max(m, mCollapseButtonView.getMeasuredHeight() + getVerticalMargins(mCollapseButtonView));
        j = ViewUtils.combineMeasuredStates(k, ViewCompat.getMeasuredState(mCollapseButtonView));
      }
      m = getContentInsetStart();
      k = 0 + Math.max(m, i2);
      arrayOfInt[i1] = Math.max(0, m - i2);
      if (!shouldLayout(mMenuView)) {
        break label963;
      }
      measureChildConstrained(mMenuView, paramInt1, k, paramInt2, 0, mMaxButtonHeight);
      m = mMenuView.getMeasuredWidth();
      i1 = getHorizontalMargins(mMenuView);
      i = Math.max(i, mMenuView.getMeasuredHeight() + getVerticalMargins(mMenuView));
      j = ViewUtils.combineMeasuredStates(j, ViewCompat.getMeasuredState(mMenuView));
    }
    label514:
    label960:
    label963:
    for (int i2 = m + i1;; i2 = 0)
    {
      m = getContentInsetEnd();
      k += Math.max(m, i2);
      arrayOfInt[n] = Math.max(0, m - i2);
      i1 = k;
      m = j;
      n = i;
      if (shouldLayout(mExpandedActionView))
      {
        i1 = k + measureChildCollapseMargins(mExpandedActionView, paramInt1, k, paramInt2, 0, arrayOfInt);
        n = Math.max(i, mExpandedActionView.getMeasuredHeight() + getVerticalMargins(mExpandedActionView));
        m = ViewUtils.combineMeasuredStates(j, ViewCompat.getMeasuredState(mExpandedActionView));
      }
      k = i1;
      i = m;
      j = n;
      if (shouldLayout(mLogoView))
      {
        k = i1 + measureChildCollapseMargins(mLogoView, paramInt1, i1, paramInt2, 0, arrayOfInt);
        j = Math.max(n, mLogoView.getMeasuredHeight() + getVerticalMargins(mLogoView));
        i = ViewUtils.combineMeasuredStates(m, ViewCompat.getMeasuredState(mLogoView));
      }
      n = getChildCount();
      m = 0;
      View localView;
      if (m < n)
      {
        localView = getChildAt(m);
        if (getLayoutParamsmViewType != 0) {
          break label960;
        }
        if (shouldLayout(localView)) {}
      }
      for (;;)
      {
        m += 1;
        break label514;
        n = 1;
        i1 = 0;
        break;
        k += measureChildCollapseMargins(localView, paramInt1, k, paramInt2, 0, arrayOfInt);
        j = Math.max(j, localView.getMeasuredHeight() + getVerticalMargins(localView));
        i = ViewUtils.combineMeasuredStates(i, ViewCompat.getMeasuredState(localView));
        continue;
        i1 = 0;
        n = 0;
        int i5 = mTitleMarginTop + mTitleMarginBottom;
        int i6 = mTitleMarginStart + mTitleMarginEnd;
        switch (mGravity & 0x7)
        {
        default: 
          i2 = k;
        }
        for (;;)
        {
          m = i;
          if (shouldLayout(mTitleTextView))
          {
            measureChildCollapseMargins(mTitleTextView, paramInt1, i2 + i6, paramInt2, i5, arrayOfInt);
            m = mTitleTextView.getMeasuredWidth();
            i1 = getHorizontalMargins(mTitleTextView) + m;
            n = mTitleTextView.getMeasuredHeight() + getVerticalMargins(mTitleTextView);
            m = ViewUtils.combineMeasuredStates(i, ViewCompat.getMeasuredState(mTitleTextView));
          }
          int i4 = n;
          int i3 = i1;
          i = m;
          if (shouldLayout(mSubtitleTextView))
          {
            i3 = Math.max(i1, measureChildCollapseMargins(mSubtitleTextView, paramInt1, i2 + i6, paramInt2, i5 + n, arrayOfInt));
            i4 = n + (mSubtitleTextView.getMeasuredHeight() + getVerticalMargins(mSubtitleTextView));
            i = ViewUtils.combineMeasuredStates(m, ViewCompat.getMeasuredState(mSubtitleTextView));
          }
          j = Math.max(j, i4);
          i1 = getPaddingLeft();
          i2 = getPaddingRight();
          m = getPaddingTop();
          n = getPaddingBottom();
          k = ViewCompat.resolveSizeAndState(Math.max(k + i3 + (i1 + i2), getSuggestedMinimumWidth()), paramInt1, 0xFF000000 & i);
          paramInt1 = ViewCompat.resolveSizeAndState(Math.max(j + (m + n), getSuggestedMinimumHeight()), paramInt2, i << 16);
          if (shouldCollapse()) {
            paramInt1 = 0;
          }
          setMeasuredDimension(k, paramInt1);
          return;
          i2 *= 2;
        }
      }
    }
  }
  
  private void postShowOverflowMenu()
  {
    removeCallbacks(mShowOverflowMenuRunnable);
    post(mShowOverflowMenuRunnable);
  }
  
  private void setChildVisibilityForExpandedActionView(boolean paramBoolean)
  {
    int k = getChildCount();
    int i = 0;
    if (i < k)
    {
      View localView = getChildAt(i);
      if ((getLayoutParamsmViewType != 2) && (localView != mMenuView)) {
        if (!paramBoolean) {
          break label65;
        }
      }
      label65:
      for (int j = 8;; j = 0)
      {
        localView.setVisibility(j);
        i += 1;
        break;
      }
    }
  }
  
  private boolean shouldCollapse()
  {
    if (!mCollapsible) {
      return false;
    }
    int j = getChildCount();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label56;
      }
      View localView = getChildAt(i);
      if ((shouldLayout(localView)) && (localView.getMeasuredWidth() > 0) && (localView.getMeasuredHeight() > 0)) {
        break;
      }
      i += 1;
    }
    label56:
    return true;
  }
  
  private boolean shouldLayout(View paramView)
  {
    return (paramView != null) && (paramView.getParent() == this) && (paramView.getVisibility() != 8);
  }
  
  private void updateChildVisibilityForExpandedActionView(View paramView)
  {
    if ((getLayoutParamsmViewType != 2) && (paramView != mMenuView)) {
      if (mExpandedActionView == null) {
        break label38;
      }
    }
    label38:
    for (int i = 8;; i = 0)
    {
      paramView.setVisibility(i);
      return;
    }
  }
  
  public boolean canShowOverflowMenu()
  {
    return (getVisibility() == 0) && (mMenuView != null) && (mMenuView.isOverflowReserved());
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return (super.checkLayoutParams(paramLayoutParams)) && ((paramLayoutParams instanceof LayoutParams));
  }
  
  public void collapseActionView()
  {
    if (mExpandedMenuPresenter == null) {}
    for (MenuItemImpl localMenuItemImpl = null;; localMenuItemImpl = mExpandedMenuPresenter.mCurrentExpandedItem)
    {
      if (localMenuItemImpl != null) {
        localMenuItemImpl.collapseActionView();
      }
      return;
    }
  }
  
  public void dismissPopupMenus()
  {
    if (mMenuView != null) {
      mMenuView.dismissPopupMenus();
    }
  }
  
  protected LayoutParams generateDefaultLayoutParams()
  {
    return new LayoutParams(-2, -2);
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramLayoutParams instanceof LayoutParams)) {
      return new LayoutParams((LayoutParams)paramLayoutParams);
    }
    if ((paramLayoutParams instanceof ActionBar.LayoutParams)) {
      return new LayoutParams((ActionBar.LayoutParams)paramLayoutParams);
    }
    if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams)) {
      return new LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams);
    }
    return new LayoutParams(paramLayoutParams);
  }
  
  public int getContentInsetEnd()
  {
    return mContentInsets.getEnd();
  }
  
  public int getContentInsetLeft()
  {
    return mContentInsets.getLeft();
  }
  
  public int getContentInsetRight()
  {
    return mContentInsets.getRight();
  }
  
  public int getContentInsetStart()
  {
    return mContentInsets.getStart();
  }
  
  public Drawable getLogo()
  {
    if (mLogoView != null) {
      return mLogoView.getDrawable();
    }
    return null;
  }
  
  public CharSequence getLogoDescription()
  {
    if (mLogoView != null) {
      return mLogoView.getContentDescription();
    }
    return null;
  }
  
  public Menu getMenu()
  {
    ensureMenu();
    return mMenuView.getMenu();
  }
  
  @Nullable
  public CharSequence getNavigationContentDescription()
  {
    if (mNavButtonView != null) {
      return mNavButtonView.getContentDescription();
    }
    return null;
  }
  
  @Nullable
  public Drawable getNavigationIcon()
  {
    if (mNavButtonView != null) {
      return mNavButtonView.getDrawable();
    }
    return null;
  }
  
  public int getPopupTheme()
  {
    return mPopupTheme;
  }
  
  public CharSequence getSubtitle()
  {
    return mSubtitleText;
  }
  
  public CharSequence getTitle()
  {
    return mTitleText;
  }
  
  public DecorToolbar getWrapper()
  {
    if (mWrapper == null) {
      mWrapper = new ToolbarWidgetWrapper(this, true);
    }
    return mWrapper;
  }
  
  public boolean hasExpandedActionView()
  {
    return (mExpandedMenuPresenter != null) && (mExpandedMenuPresenter.mCurrentExpandedItem != null);
  }
  
  public boolean hideOverflowMenu()
  {
    return (mMenuView != null) && (mMenuView.hideOverflowMenu());
  }
  
  public void inflateMenu(int paramInt)
  {
    getMenuInflater().inflate(paramInt, getMenu());
  }
  
  public boolean isOverflowMenuShowPending()
  {
    return (mMenuView != null) && (mMenuView.isOverflowMenuShowPending());
  }
  
  public boolean isOverflowMenuShowing()
  {
    return (mMenuView != null) && (mMenuView.isOverflowMenuShowing());
  }
  
  public boolean isTitleTruncated()
  {
    if (mTitleTextView == null) {}
    for (;;)
    {
      return false;
      Layout localLayout = mTitleTextView.getLayout();
      if (localLayout != null)
      {
        int j = localLayout.getLineCount();
        int i = 0;
        while (i < j)
        {
          if (localLayout.getEllipsisCount(i) > 0) {
            return true;
          }
          i += 1;
        }
      }
    }
  }
  
  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (mSplitWhenNarrow) {
      setSplitToolbar(getContext().getResources().getBoolean(R.bool.mz_split_action_bar_is_narrow));
    }
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    removeCallbacks(mShowOverflowMenuRunnable);
  }
  
  public boolean onHoverEvent(MotionEvent paramMotionEvent)
  {
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    if (i == 9) {
      mEatingHover = false;
    }
    if (!mEatingHover)
    {
      boolean bool = super.onHoverEvent(paramMotionEvent);
      if ((i == 9) && (!bool)) {
        mEatingHover = true;
      }
    }
    if ((i == 10) || (i == 3)) {
      mEatingHover = false;
    }
    return true;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    onLayoutForFlyme(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    onMeasureForFlyme(paramInt1, paramInt2);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    SavedState localSavedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(localSavedState.getSuperState());
    if (mMenuView != null) {}
    for (paramParcelable = mMenuView.peekMenu();; paramParcelable = null)
    {
      if ((expandedMenuItemId != 0) && (mExpandedMenuPresenter != null) && (paramParcelable != null))
      {
        paramParcelable = paramParcelable.findItem(expandedMenuItemId);
        if (paramParcelable != null) {
          MenuItemCompat.expandActionView(paramParcelable);
        }
      }
      if (isOverflowOpen) {
        postShowOverflowMenu();
      }
      return;
    }
  }
  
  public void onRtlPropertiesChanged(int paramInt)
  {
    boolean bool = true;
    if (Build.VERSION.SDK_INT >= 17) {
      super.onRtlPropertiesChanged(paramInt);
    }
    RtlSpacingHelper localRtlSpacingHelper = mContentInsets;
    if (paramInt == 1) {}
    for (;;)
    {
      localRtlSpacingHelper.setDirection(bool);
      return;
      bool = false;
    }
  }
  
  protected Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    if ((mExpandedMenuPresenter != null) && (mExpandedMenuPresenter.mCurrentExpandedItem != null)) {
      expandedMenuItemId = mExpandedMenuPresenter.mCurrentExpandedItem.getItemId();
    }
    isOverflowOpen = isOverflowMenuShowing();
    return localSavedState;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    if (i == 0) {
      mEatingTouch = false;
    }
    if (!mEatingTouch)
    {
      boolean bool = super.onTouchEvent(paramMotionEvent);
      if ((i == 0) && (!bool)) {
        mEatingTouch = true;
      }
    }
    if ((i == 1) || (i == 3)) {
      mEatingTouch = false;
    }
    return true;
  }
  
  public void setCollapsible(boolean paramBoolean)
  {
    mCollapsible = paramBoolean;
    requestLayout();
  }
  
  public void setContentInsetsAbsolute(int paramInt1, int paramInt2)
  {
    mContentInsets.setAbsolute(paramInt1, paramInt2);
  }
  
  public void setContentInsetsRelative(int paramInt1, int paramInt2)
  {
    mContentInsets.setRelative(paramInt1, paramInt2);
  }
  
  public void setLogo(int paramInt)
  {
    setLogo(mTintManager.getDrawable(paramInt));
  }
  
  public void setLogo(Drawable paramDrawable)
  {
    if (paramDrawable != null)
    {
      ensureLogoView();
      if (mLogoView.getParent() == null)
      {
        addSystemView(mLogoView);
        updateChildVisibilityForExpandedActionView(mLogoView);
      }
    }
    for (;;)
    {
      if (mLogoView != null) {
        mLogoView.setImageDrawable(paramDrawable);
      }
      return;
      if ((mLogoView != null) && (mLogoView.getParent() != null)) {
        removeView(mLogoView);
      }
    }
  }
  
  public void setLogoDescription(int paramInt)
  {
    setLogoDescription(getContext().getText(paramInt));
  }
  
  public void setLogoDescription(CharSequence paramCharSequence)
  {
    if (!TextUtils.isEmpty(paramCharSequence)) {
      ensureLogoView();
    }
    if (mLogoView != null) {
      mLogoView.setContentDescription(paramCharSequence);
    }
  }
  
  public void setMenu(MenuBuilder paramMenuBuilder, ActionMenuPresenter paramActionMenuPresenter)
  {
    if ((paramMenuBuilder == null) && (mMenuView == null)) {}
    MenuBuilder localMenuBuilder;
    do
    {
      return;
      ensureMenuView();
      localMenuBuilder = mMenuView.peekMenu();
    } while (localMenuBuilder == paramMenuBuilder);
    if (localMenuBuilder != null)
    {
      localMenuBuilder.removeMenuPresenter(mOuterActionMenuPresenter);
      localMenuBuilder.removeMenuPresenter(mExpandedMenuPresenter);
    }
    if (mExpandedMenuPresenter == null) {
      mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter(null);
    }
    paramActionMenuPresenter.setExpandedActionViewsExclusive(true);
    if (paramMenuBuilder != null)
    {
      paramMenuBuilder.addMenuPresenter(paramActionMenuPresenter, mPopupContext);
      paramMenuBuilder.addMenuPresenter(mExpandedMenuPresenter, mPopupContext);
    }
    for (;;)
    {
      mMenuView.setPopupTheme(mPopupTheme);
      mMenuView.setPresenter(paramActionMenuPresenter);
      mOuterActionMenuPresenter = paramActionMenuPresenter;
      return;
      paramActionMenuPresenter.initForMenu(mPopupContext, null);
      mExpandedMenuPresenter.initForMenu(mPopupContext, null);
      paramActionMenuPresenter.updateMenuView(true);
      mExpandedMenuPresenter.updateMenuView(true);
    }
  }
  
  public void setMenuCallbacks(MenuPresenter.Callback paramCallback, MenuBuilder.Callback paramCallback1)
  {
    mActionMenuPresenterCallback = paramCallback;
    mMenuBuilderCallback = paramCallback1;
  }
  
  public void setMenuViewAnimateToVisibility(int paramInt)
  {
    if ((!mSplitActionBar) || (mMenuView == null)) {
      return;
    }
    if (paramInt == 0)
    {
      ViewCompat.setAlpha(mMenuView, 0.0F);
      localViewPropertyAnimatorCompat = ViewCompat.animate(mMenuView).alpha(1.0F);
      localViewPropertyAnimatorCompat.setDuration(160L);
      localViewPropertyAnimatorCompat.setListener(mVisAnimListener.withFinalVisibility(localViewPropertyAnimatorCompat, paramInt));
      localViewPropertyAnimatorCompat.start();
      return;
    }
    ViewPropertyAnimatorCompat localViewPropertyAnimatorCompat = ViewCompat.animate(mMenuView).alpha(0.0F);
    localViewPropertyAnimatorCompat.setDuration(160L);
    localViewPropertyAnimatorCompat.setListener(mVisAnimListener.withFinalVisibility(localViewPropertyAnimatorCompat, paramInt));
    localViewPropertyAnimatorCompat.start();
  }
  
  public void setMenuVisibility(int paramInt)
  {
    if ((mMenuView != null) && (mSplitActionBar)) {
      mMenuView.setVisibility(paramInt);
    }
  }
  
  public void setNavigationContentDescription(int paramInt)
  {
    if (paramInt != 0) {}
    for (CharSequence localCharSequence = getContext().getText(paramInt);; localCharSequence = null)
    {
      setNavigationContentDescription(localCharSequence);
      return;
    }
  }
  
  public void setNavigationContentDescription(@Nullable CharSequence paramCharSequence)
  {
    if (!TextUtils.isEmpty(paramCharSequence)) {
      ensureNavButtonView();
    }
    if (mNavButtonView != null) {
      mNavButtonView.setContentDescription(paramCharSequence);
    }
  }
  
  public void setNavigationIcon(int paramInt)
  {
    setNavigationIcon(mTintManager.getDrawable(paramInt));
  }
  
  public void setNavigationIcon(@Nullable Drawable paramDrawable)
  {
    if (paramDrawable != null)
    {
      ensureNavButtonView();
      if (mNavButtonView.getParent() == null)
      {
        addSystemView(mNavButtonView);
        updateChildVisibilityForExpandedActionView(mNavButtonView);
      }
    }
    for (;;)
    {
      if (mNavButtonView != null) {
        mNavButtonView.setImageDrawable(paramDrawable);
      }
      return;
      if ((mNavButtonView != null) && (mNavButtonView.getParent() != null))
      {
        removeView(mNavButtonView);
        setTouchDelegate(null);
      }
    }
  }
  
  public void setNavigationOnClickListener(View.OnClickListener paramOnClickListener)
  {
    ensureNavButtonView();
    mNavButtonView.setOnClickListener(paramOnClickListener);
  }
  
  public void setOnMenuItemClickListener(OnMenuItemClickListener paramOnMenuItemClickListener)
  {
    mOnMenuItemClickListener = paramOnMenuItemClickListener;
  }
  
  public void setPopupTheme(int paramInt)
  {
    if (mPopupTheme != paramInt)
    {
      mPopupTheme = paramInt;
      if (paramInt == 0) {
        mPopupContext = getContext();
      }
    }
    else
    {
      return;
    }
    mPopupContext = new ContextThemeWrapper(getContext(), paramInt);
  }
  
  public void setSplitToolbar(boolean paramBoolean)
  {
    ViewGroup localViewGroup;
    if (mSplitActionBar != paramBoolean)
    {
      mSplitActionBar = paramBoolean;
      if (mMenuView != null)
      {
        localViewGroup = (ViewGroup)mMenuView.getParent();
        if (localViewGroup != null) {
          localViewGroup.removeView(mMenuView);
        }
        if (!paramBoolean) {
          break label107;
        }
        if (mSplitView != null) {
          mSplitView.addView(mMenuView);
        }
        mMenuView.getLayoutParams().width = -1;
        mMenuView.requestLayout();
      }
      if (mSplitView != null)
      {
        localViewGroup = mSplitView;
        if (!paramBoolean) {
          break label130;
        }
      }
    }
    label107:
    label130:
    for (int i = 0;; i = 8)
    {
      localViewGroup.setVisibility(i);
      return;
      addSystemView(mMenuView);
      mMenuView.getLayoutParams().width = -2;
      break;
    }
  }
  
  public void setSplitView(ViewGroup paramViewGroup)
  {
    mSplitView = paramViewGroup;
  }
  
  public void setSubtitle(int paramInt)
  {
    setSubtitle(getContext().getText(paramInt));
  }
  
  public void setSubtitle(CharSequence paramCharSequence)
  {
    if (!TextUtils.isEmpty(paramCharSequence))
    {
      if (mSubtitleTextView == null)
      {
        Context localContext = getContext();
        mSubtitleTextView = new TextView(localContext);
        mSubtitleTextView.setSingleLine();
        mSubtitleTextView.setEllipsize(TextUtils.TruncateAt.END);
        if (mSubtitleTextAppearance != 0) {
          mSubtitleTextView.setTextAppearance(localContext, mSubtitleTextAppearance);
        }
        if (mSubtitleTextColor != 0) {
          mSubtitleTextView.setTextColor(mSubtitleTextColor);
        }
      }
      if (mSubtitleTextView.getParent() == null)
      {
        addSystemView(mSubtitleTextView);
        updateChildVisibilityForExpandedActionView(mSubtitleTextView);
      }
    }
    for (;;)
    {
      if (mSubtitleTextView != null) {
        mSubtitleTextView.setText(paramCharSequence);
      }
      mSubtitleText = paramCharSequence;
      return;
      if ((mSubtitleTextView != null) && (mSubtitleTextView.getParent() != null)) {
        removeView(mSubtitleTextView);
      }
    }
  }
  
  public void setSubtitleTextAppearance(Context paramContext, int paramInt)
  {
    mSubtitleTextAppearance = paramInt;
    if (mSubtitleTextView != null) {
      mSubtitleTextView.setTextAppearance(paramContext, paramInt);
    }
  }
  
  public void setSubtitleTextColor(int paramInt)
  {
    mSubtitleTextColor = paramInt;
    if (mSubtitleTextView != null) {
      mSubtitleTextView.setTextColor(paramInt);
    }
  }
  
  public void setTitle(int paramInt)
  {
    setTitle(getContext().getText(paramInt));
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    if (!TextUtils.isEmpty(paramCharSequence))
    {
      if (mTitleTextView == null)
      {
        Context localContext = getContext();
        mTitleTextView = new TextView(localContext);
        mTitleTextView.setSingleLine();
        mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
        mTitleTextView.setMaxWidth(localContext.getResources().getDimensionPixelSize(R.dimen.mz_toolbar_title_max_width));
        if (mTitleTextAppearance != 0) {
          mTitleTextView.setTextAppearance(localContext, mTitleTextAppearance);
        }
        if (mTitleTextColor != 0) {
          mTitleTextView.setTextColor(mTitleTextColor);
        }
      }
      if (mTitleTextView.getParent() == null)
      {
        addSystemView(mTitleTextView);
        updateChildVisibilityForExpandedActionView(mTitleTextView);
      }
    }
    for (;;)
    {
      if (mTitleTextView != null) {
        mTitleTextView.setText(paramCharSequence);
      }
      mTitleText = paramCharSequence;
      return;
      if ((mTitleTextView != null) && (mTitleTextView.getParent() != null)) {
        removeView(mTitleTextView);
      }
    }
  }
  
  public void setTitleTextAppearance(Context paramContext, int paramInt)
  {
    mTitleTextAppearance = paramInt;
    if (mTitleTextView != null) {
      mTitleTextView.setTextAppearance(paramContext, paramInt);
    }
  }
  
  public void setTitleTextColor(int paramInt)
  {
    mTitleTextColor = paramInt;
    if (mTitleTextView != null) {
      mTitleTextView.setTextColor(paramInt);
    }
  }
  
  public boolean showOverflowMenu()
  {
    return (mMenuView != null) && (mMenuView.showOverflowMenu());
  }
  
  class ExpandedActionViewMenuPresenter
    implements MenuPresenter
  {
    MenuItemImpl mCurrentExpandedItem;
    MenuBuilder mMenu;
    
    private ExpandedActionViewMenuPresenter() {}
    
    public boolean collapseItemActionView(MenuBuilder paramMenuBuilder, MenuItemImpl paramMenuItemImpl)
    {
      if ((mExpandedActionView instanceof CollapsibleActionView)) {
        ((CollapsibleActionView)mExpandedActionView).onActionViewCollapsed();
      }
      removeView(mExpandedActionView);
      removeView(mCollapseButtonView);
      mExpandedActionView = null;
      Toolbar.this.setChildVisibilityForExpandedActionView(false);
      mCurrentExpandedItem = null;
      requestLayout();
      paramMenuItemImpl.setActionViewExpanded(false);
      return true;
    }
    
    public boolean expandItemActionView(MenuBuilder paramMenuBuilder, MenuItemImpl paramMenuItemImpl)
    {
      Toolbar.this.ensureCollapseButtonView();
      if (mCollapseButtonView.getParent() != Toolbar.this) {
        addView(mCollapseButtonView);
      }
      mExpandedActionView = paramMenuItemImpl.getActionView();
      mCurrentExpandedItem = paramMenuItemImpl;
      if (mExpandedActionView.getParent() != Toolbar.this)
      {
        paramMenuBuilder = generateDefaultLayoutParams();
        gravity = (0x800003 | mButtonGravity & 0x70);
        mViewType = 2;
        mExpandedActionView.setLayoutParams(paramMenuBuilder);
        addView(mExpandedActionView);
      }
      Toolbar.this.setChildVisibilityForExpandedActionView(true);
      requestLayout();
      paramMenuItemImpl.setActionViewExpanded(true);
      if ((mExpandedActionView instanceof CollapsibleActionView)) {
        ((CollapsibleActionView)mExpandedActionView).onActionViewExpanded();
      }
      return true;
    }
    
    public boolean flagActionItems()
    {
      return false;
    }
    
    public int getId()
    {
      return 0;
    }
    
    public MenuView getMenuView(ViewGroup paramViewGroup)
    {
      return null;
    }
    
    public void initForMenu(Context paramContext, MenuBuilder paramMenuBuilder)
    {
      if ((mMenu != null) && (mCurrentExpandedItem != null)) {
        mMenu.collapseItemActionView(mCurrentExpandedItem);
      }
      mMenu = paramMenuBuilder;
    }
    
    public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean) {}
    
    public void onRestoreInstanceState(Parcelable paramParcelable) {}
    
    public Parcelable onSaveInstanceState()
    {
      return null;
    }
    
    public boolean onSubMenuSelected(SubMenuBuilder paramSubMenuBuilder)
    {
      return false;
    }
    
    public void setCallback(MenuPresenter.Callback paramCallback) {}
    
    public void updateMenuView(boolean paramBoolean)
    {
      int k = 0;
      int j;
      int m;
      int i;
      if (mCurrentExpandedItem != null)
      {
        j = k;
        if (mMenu != null)
        {
          m = mMenu.size();
          i = 0;
        }
      }
      for (;;)
      {
        j = k;
        if (i < m)
        {
          if (mMenu.getItem(i) == mCurrentExpandedItem) {
            j = 1;
          }
        }
        else
        {
          if (j == 0) {
            collapseItemActionView(mMenu, mCurrentExpandedItem);
          }
          return;
        }
        i += 1;
      }
    }
  }
  
  public static class LayoutParams
    extends ActionBar.LayoutParams
  {
    static final int CUSTOM = 0;
    static final int EXPANDED = 2;
    static final int SYSTEM = 1;
    int mViewType = 0;
    
    public LayoutParams(int paramInt)
    {
      this(-2, -1, paramInt);
    }
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
      gravity = 8388627;
    }
    
    public LayoutParams(int paramInt1, int paramInt2, int paramInt3)
    {
      super(paramInt2);
      gravity = paramInt3;
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    public LayoutParams(ActionBar.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(LayoutParams paramLayoutParams)
    {
      super();
      mViewType = mViewType;
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
      copyMarginsFromCompat(paramMarginLayoutParams);
    }
    
    void copyMarginsFromCompat(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      leftMargin = leftMargin;
      topMargin = topMargin;
      rightMargin = rightMargin;
      bottomMargin = bottomMargin;
    }
  }
  
  public static abstract interface OnMenuItemClickListener
  {
    public abstract boolean onMenuItemClick(MenuItem paramMenuItem);
  }
  
  static class SavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Toolbar.SavedState.1();
    public int expandedMenuItemId;
    public boolean isOverflowOpen;
    
    public SavedState(Parcel paramParcel)
    {
      super();
      expandedMenuItemId = paramParcel.readInt();
      if (paramParcel.readInt() != 0) {}
      for (boolean bool = true;; bool = false)
      {
        isOverflowOpen = bool;
        return;
      }
    }
    
    public SavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(expandedMenuItemId);
      if (isOverflowOpen) {}
      for (paramInt = 1;; paramInt = 0)
      {
        paramParcel.writeInt(paramInt);
        return;
      }
    }
  }
  
  public class VisibilityAnimListener
    implements ViewPropertyAnimatorListener
  {
    private boolean mCanceled = false;
    int mFinalVisibility;
    
    protected VisibilityAnimListener() {}
    
    public void onAnimationCancel(View paramView)
    {
      mCanceled = true;
    }
    
    public void onAnimationEnd(View paramView)
    {
      if (mCanceled) {}
      do
      {
        return;
        mMenuViewVisibilityAnim = null;
      } while (mMenuView == null);
      mMenuView.setVisibility(mFinalVisibility);
    }
    
    public void onAnimationStart(View paramView)
    {
      if (mMenuView != null) {
        mMenuView.setVisibility(0);
      }
      mCanceled = false;
    }
    
    public VisibilityAnimListener withFinalVisibility(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, int paramInt)
    {
      mMenuViewVisibilityAnim = paramViewPropertyAnimatorCompat;
      mFinalVisibility = paramInt;
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.Toolbar
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */