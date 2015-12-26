package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.IntDef;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.internal.widget.ViewUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LinearLayoutCompat
  extends ViewGroup
{
  public static final int HORIZONTAL = 0;
  private static final int INDEX_BOTTOM = 2;
  private static final int INDEX_CENTER_VERTICAL = 0;
  private static final int INDEX_FILL = 3;
  private static final int INDEX_TOP = 1;
  public static final int SHOW_DIVIDER_BEGINNING = 1;
  public static final int SHOW_DIVIDER_END = 4;
  public static final int SHOW_DIVIDER_MIDDLE = 2;
  public static final int SHOW_DIVIDER_NONE = 0;
  public static final int VERTICAL = 1;
  private static final int VERTICAL_GRAVITY_COUNT = 4;
  private boolean mBaselineAligned = true;
  private int mBaselineAlignedChildIndex = -1;
  private int mBaselineChildTop = 0;
  private Drawable mDivider;
  private int mDividerHeight;
  private int mDividerPadding;
  private int mDividerWidth;
  private int mGravity = 8388659;
  private int[] mMaxAscent;
  private int[] mMaxDescent;
  private int mOrientation;
  private int mShowDividers;
  private int mTotalLength;
  private boolean mUseLargestChild;
  private float mWeightSum;
  
  public LinearLayoutCompat(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public LinearLayoutCompat(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public LinearLayoutCompat(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.LinearLayoutCompat, paramInt, 0);
    paramInt = paramContext.getInt(R.styleable.LinearLayoutCompat_android_orientation, -1);
    if (paramInt >= 0) {
      setOrientation(paramInt);
    }
    paramInt = paramContext.getInt(R.styleable.LinearLayoutCompat_android_gravity, -1);
    if (paramInt >= 0) {
      setGravity(paramInt);
    }
    boolean bool = paramContext.getBoolean(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
    if (!bool) {
      setBaselineAligned(bool);
    }
    mWeightSum = paramContext.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0F);
    mBaselineAlignedChildIndex = paramContext.getInt(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
    mUseLargestChild = paramContext.getBoolean(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
    setDividerDrawable(paramContext.getDrawable(R.styleable.LinearLayoutCompat_divider));
    mShowDividers = paramContext.getInt(R.styleable.LinearLayoutCompat_showDividers, 0);
    mDividerPadding = paramContext.getDimensionPixelSize(R.styleable.LinearLayoutCompat_dividerPadding, 0);
    paramContext.recycle();
  }
  
  private void forceUniformHeight(int paramInt1, int paramInt2)
  {
    int j = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
    int i = 0;
    while (i < paramInt1)
    {
      View localView = getVirtualChildAt(i);
      if (localView.getVisibility() != 8)
      {
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (height == -1)
        {
          int k = width;
          width = localView.getMeasuredWidth();
          measureChildWithMargins(localView, paramInt2, 0, j, 0);
          width = k;
        }
      }
      i += 1;
    }
  }
  
  private void forceUniformWidth(int paramInt1, int paramInt2)
  {
    int j = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
    int i = 0;
    while (i < paramInt1)
    {
      View localView = getVirtualChildAt(i);
      if (localView.getVisibility() != 8)
      {
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (width == -1)
        {
          int k = height;
          height = localView.getMeasuredHeight();
          measureChildWithMargins(localView, j, 0, paramInt2, 0);
          height = k;
        }
      }
      i += 1;
    }
  }
  
  private void setChildFrame(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramView.layout(paramInt1, paramInt2, paramInt1 + paramInt3, paramInt2 + paramInt4);
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return paramLayoutParams instanceof LayoutParams;
  }
  
  void drawDividersHorizontal(Canvas paramCanvas)
  {
    int k = getVirtualChildCount();
    boolean bool = ViewUtils.isLayoutRtl(this);
    int i = 0;
    View localView;
    LayoutParams localLayoutParams;
    if (i < k)
    {
      localView = getVirtualChildAt(i);
      if ((localView != null) && (localView.getVisibility() != 8) && (hasDividerBeforeChildAt(i)))
      {
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (!bool) {
          break label92;
        }
        j = localView.getRight();
      }
      label92:
      for (int j = rightMargin + j;; j = localView.getLeft() - leftMargin - mDividerWidth)
      {
        drawVerticalDivider(paramCanvas, j);
        i += 1;
        break;
      }
    }
    if (hasDividerBeforeChildAt(k))
    {
      localView = getVirtualChildAt(k - 1);
      if (localView != null) {
        break label171;
      }
      if (!bool) {
        break label153;
      }
      i = getPaddingLeft();
    }
    for (;;)
    {
      drawVerticalDivider(paramCanvas, i);
      return;
      label153:
      i = getWidth() - getPaddingRight() - mDividerWidth;
      continue;
      label171:
      localLayoutParams = (LayoutParams)localView.getLayoutParams();
      if (bool)
      {
        i = localView.getLeft() - leftMargin - mDividerWidth;
      }
      else
      {
        i = localView.getRight();
        i = rightMargin + i;
      }
    }
  }
  
  void drawDividersVertical(Canvas paramCanvas)
  {
    int j = getVirtualChildCount();
    int i = 0;
    View localView;
    LayoutParams localLayoutParams;
    while (i < j)
    {
      localView = getVirtualChildAt(i);
      if ((localView != null) && (localView.getVisibility() != 8) && (hasDividerBeforeChildAt(i)))
      {
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        drawHorizontalDivider(paramCanvas, localView.getTop() - topMargin - mDividerHeight);
      }
      i += 1;
    }
    if (hasDividerBeforeChildAt(j))
    {
      localView = getVirtualChildAt(j - 1);
      if (localView != null) {
        break label124;
      }
    }
    for (i = getHeight() - getPaddingBottom() - mDividerHeight;; i = bottomMargin + i)
    {
      drawHorizontalDivider(paramCanvas, i);
      return;
      label124:
      localLayoutParams = (LayoutParams)localView.getLayoutParams();
      i = localView.getBottom();
    }
  }
  
  void drawHorizontalDivider(Canvas paramCanvas, int paramInt)
  {
    mDivider.setBounds(getPaddingLeft() + mDividerPadding, paramInt, getWidth() - getPaddingRight() - mDividerPadding, mDividerHeight + paramInt);
    mDivider.draw(paramCanvas);
  }
  
  void drawVerticalDivider(Canvas paramCanvas, int paramInt)
  {
    mDivider.setBounds(paramInt, getPaddingTop() + mDividerPadding, mDividerWidth + paramInt, getHeight() - getPaddingBottom() - mDividerPadding);
    mDivider.draw(paramCanvas);
  }
  
  protected LayoutParams generateDefaultLayoutParams()
  {
    if (mOrientation == 0) {
      return new LayoutParams(-2, -2);
    }
    if (mOrientation == 1) {
      return new LayoutParams(-1, -2);
    }
    return null;
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return new LayoutParams(paramLayoutParams);
  }
  
  public int getBaseline()
  {
    int i = -1;
    if (mBaselineAlignedChildIndex < 0) {
      i = super.getBaseline();
    }
    View localView;
    int j;
    do
    {
      return i;
      if (getChildCount() <= mBaselineAlignedChildIndex) {
        throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
      }
      localView = getChildAt(mBaselineAlignedChildIndex);
      j = localView.getBaseline();
      if (j != -1) {
        break;
      }
    } while (mBaselineAlignedChildIndex == 0);
    throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
    i = mBaselineChildTop;
    if (mOrientation == 1)
    {
      int k = mGravity & 0x70;
      if (k != 48) {
        switch (k)
        {
        }
      }
    }
    for (;;)
    {
      return getLayoutParamstopMargin + i + j;
      i = getBottom() - getTop() - getPaddingBottom() - mTotalLength;
      continue;
      i += (getBottom() - getTop() - getPaddingTop() - getPaddingBottom() - mTotalLength) / 2;
    }
  }
  
  public int getBaselineAlignedChildIndex()
  {
    return mBaselineAlignedChildIndex;
  }
  
  int getChildrenSkipCount(View paramView, int paramInt)
  {
    return 0;
  }
  
  public Drawable getDividerDrawable()
  {
    return mDivider;
  }
  
  public int getDividerPadding()
  {
    return mDividerPadding;
  }
  
  public int getDividerWidth()
  {
    return mDividerWidth;
  }
  
  int getLocationOffset(View paramView)
  {
    return 0;
  }
  
  int getNextLocationOffset(View paramView)
  {
    return 0;
  }
  
  public int getOrientation()
  {
    return mOrientation;
  }
  
  public int getShowDividers()
  {
    return mShowDividers;
  }
  
  View getVirtualChildAt(int paramInt)
  {
    return getChildAt(paramInt);
  }
  
  int getVirtualChildCount()
  {
    return getChildCount();
  }
  
  public float getWeightSum()
  {
    return mWeightSum;
  }
  
  protected boolean hasDividerBeforeChildAt(int paramInt)
  {
    if (paramInt == 0) {
      if ((mShowDividers & 0x1) == 0) {}
    }
    do
    {
      return true;
      return false;
      if (paramInt != getChildCount()) {
        break;
      }
    } while ((mShowDividers & 0x4) != 0);
    return false;
    if ((mShowDividers & 0x2) != 0)
    {
      paramInt -= 1;
      for (;;)
      {
        if (paramInt < 0) {
          break label75;
        }
        if (getChildAt(paramInt).getVisibility() != 8) {
          break;
        }
        paramInt -= 1;
      }
    }
    return false;
    label75:
    return false;
  }
  
  public boolean isBaselineAligned()
  {
    return mBaselineAligned;
  }
  
  public boolean isMeasureWithLargestChildEnabled()
  {
    return mUseLargestChild;
  }
  
  void layoutHorizontal(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    boolean bool1 = ViewUtils.isLayoutRtl(this);
    int k = getPaddingTop();
    int n = paramInt4 - paramInt2;
    int i1 = getPaddingBottom();
    int i2 = getPaddingBottom();
    int i3 = getVirtualChildCount();
    paramInt2 = mGravity;
    int i4 = mGravity;
    boolean bool2 = mBaselineAligned;
    int[] arrayOfInt1 = mMaxAscent;
    int[] arrayOfInt2 = mMaxDescent;
    switch (GravityCompat.getAbsoluteGravity(paramInt2 & 0x800007, ViewCompat.getLayoutDirection(this)))
    {
    default: 
      paramInt1 = getPaddingLeft();
      if (bool1) {
        paramInt4 = -1;
      }
      break;
    }
    for (int i = i3 - 1;; i = 0)
    {
      paramInt2 = 0;
      paramInt3 = paramInt1;
      label127:
      int i5;
      View localView;
      if (paramInt2 < i3)
      {
        i5 = i + paramInt4 * paramInt2;
        localView = getVirtualChildAt(i5);
        if (localView == null)
        {
          paramInt3 += measureNullChild(i5);
          paramInt1 = paramInt2;
        }
      }
      for (;;)
      {
        paramInt2 = paramInt1 + 1;
        break label127;
        paramInt1 = getPaddingLeft() + paramInt3 - paramInt1 - mTotalLength;
        break;
        paramInt1 = getPaddingLeft() + (paramInt3 - paramInt1 - mTotalLength) / 2;
        break;
        if (localView.getVisibility() != 8)
        {
          int i6 = localView.getMeasuredWidth();
          int i7 = localView.getMeasuredHeight();
          LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
          if ((bool2) && (height != -1)) {}
          for (int j = localView.getBaseline();; j = -1)
          {
            int m = gravity;
            paramInt1 = m;
            if (m < 0) {
              paramInt1 = i4 & 0x70;
            }
            switch (paramInt1 & 0x70)
            {
            default: 
              paramInt1 = k;
              label327:
              if (hasDividerBeforeChildAt(i5)) {
                paramInt3 = mDividerWidth + paramInt3;
              }
              break;
            }
            for (;;)
            {
              paramInt3 += leftMargin;
              setChildFrame(localView, paramInt3 + getLocationOffset(localView), paramInt1, i6, i7);
              paramInt3 += rightMargin + i6 + getNextLocationOffset(localView);
              paramInt1 = getChildrenSkipCount(localView, i5) + paramInt2;
              break;
              m = k + topMargin;
              paramInt1 = m;
              if (j == -1) {
                break label327;
              }
              paramInt1 = m + (arrayOfInt1[1] - j);
              break label327;
              paramInt1 = (n - k - i2 - i7) / 2 + k + topMargin - bottomMargin;
              break label327;
              m = n - i1 - i7 - bottomMargin;
              paramInt1 = m;
              if (j == -1) {
                break label327;
              }
              paramInt1 = localView.getMeasuredHeight();
              paramInt1 = m - (arrayOfInt2[2] - (paramInt1 - j));
              break label327;
              return;
            }
          }
        }
        paramInt1 = paramInt2;
      }
      paramInt4 = 1;
    }
  }
  
  void layoutVertical(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getPaddingLeft();
    int j = paramInt3 - paramInt1;
    int k = getPaddingRight();
    int m = getPaddingRight();
    int n = getVirtualChildCount();
    paramInt1 = mGravity;
    int i1 = mGravity;
    label83:
    View localView;
    switch (paramInt1 & 0x70)
    {
    default: 
      paramInt1 = getPaddingTop();
      paramInt3 = 0;
      paramInt2 = paramInt1;
      paramInt1 = paramInt3;
      if (paramInt1 < n)
      {
        localView = getVirtualChildAt(paramInt1);
        if (localView == null) {
          paramInt2 += measureNullChild(paramInt1);
        }
      }
      break;
    }
    for (;;)
    {
      paramInt1 += 1;
      break label83;
      paramInt1 = getPaddingTop() + paramInt4 - paramInt2 - mTotalLength;
      break;
      paramInt1 = getPaddingTop() + (paramInt4 - paramInt2 - mTotalLength) / 2;
      break;
      if (localView.getVisibility() != 8)
      {
        int i2 = localView.getMeasuredWidth();
        int i3 = localView.getMeasuredHeight();
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        paramInt4 = gravity;
        paramInt3 = paramInt4;
        if (paramInt4 < 0) {
          paramInt3 = i1 & 0x800007;
        }
        switch (GravityCompat.getAbsoluteGravity(paramInt3, ViewCompat.getLayoutDirection(this)) & 0x7)
        {
        default: 
          paramInt3 = i + leftMargin;
          label257:
          if (hasDividerBeforeChildAt(paramInt1)) {
            paramInt2 = mDividerHeight + paramInt2;
          }
          break;
        }
        for (;;)
        {
          paramInt2 += topMargin;
          setChildFrame(localView, paramInt3, paramInt2 + getLocationOffset(localView), i2, i3);
          paramInt2 += bottomMargin + i3 + getNextLocationOffset(localView);
          paramInt1 = getChildrenSkipCount(localView, paramInt1) + paramInt1;
          break;
          paramInt3 = (j - i - m - i2) / 2 + i + leftMargin - rightMargin;
          break label257;
          paramInt3 = j - k - i2 - rightMargin;
          break label257;
          return;
        }
      }
    }
  }
  
  void measureChildBeforeLayout(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    measureChildWithMargins(paramView, paramInt2, paramInt3, paramInt4, paramInt5);
  }
  
  void measureHorizontal(int paramInt1, int paramInt2)
  {
    mTotalLength = 0;
    int k = 0;
    int j = 0;
    int i1 = 0;
    int i3 = 0;
    int i = 1;
    float f1 = 0.0F;
    int i11 = getVirtualChildCount();
    int i13 = View.MeasureSpec.getMode(paramInt1);
    int i12 = View.MeasureSpec.getMode(paramInt2);
    int i2 = 0;
    int n = 0;
    if ((mMaxAscent == null) || (mMaxDescent == null))
    {
      mMaxAscent = new int[4];
      mMaxDescent = new int[4];
    }
    Object localObject = mMaxAscent;
    int[] arrayOfInt = mMaxDescent;
    localObject[3] = -1;
    localObject[2] = -1;
    localObject[1] = -1;
    localObject[0] = -1;
    arrayOfInt[3] = -1;
    arrayOfInt[2] = -1;
    arrayOfInt[1] = -1;
    arrayOfInt[0] = -1;
    boolean bool1 = mBaselineAligned;
    boolean bool2 = mUseLargestChild;
    int i7;
    int m;
    int i4;
    label156:
    View localView;
    int i5;
    int i6;
    if (i13 == 1073741824)
    {
      i7 = 1;
      m = Integer.MIN_VALUE;
      i4 = 0;
      if (i4 >= i11) {
        break label989;
      }
      localView = getVirtualChildAt(i4);
      if (localView != null) {
        break label262;
      }
      mTotalLength += measureNullChild(i4);
      i5 = m;
      i6 = n;
      n = k;
      m = j;
      k = i;
      j = i6;
      i = i5;
    }
    label262:
    int i8;
    for (;;)
    {
      i4 += 1;
      i5 = m;
      i6 = n;
      m = i;
      n = j;
      i = k;
      j = i5;
      k = i6;
      break label156;
      i7 = 0;
      break;
      if (localView.getVisibility() != 8) {
        break label332;
      }
      i8 = i4 + getChildrenSkipCount(localView, i4);
      i4 = m;
      i6 = n;
      m = i;
      n = j;
      i5 = k;
      i = i4;
      j = i6;
      i4 = i8;
      k = m;
      m = n;
      n = i5;
    }
    label332:
    if (hasDividerBeforeChildAt(i4)) {
      mTotalLength += mDividerWidth;
    }
    LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
    f1 += weight;
    if ((i13 == 1073741824) && (width == 0) && (weight > 0.0F)) {
      if (i7 != 0)
      {
        mTotalLength += leftMargin + rightMargin;
        label422:
        if (!bool1) {
          break label728;
        }
        i5 = View.MeasureSpec.makeMeasureSpec(0, 0);
        localView.measure(i5, i5);
        i6 = n;
        i5 = m;
        label451:
        n = 0;
        if ((i12 == 1073741824) || (height != -1)) {
          break label2370;
        }
        m = 1;
        n = 1;
      }
    }
    for (;;)
    {
      i2 = topMargin;
      i2 = bottomMargin + i2;
      i8 = localView.getMeasuredHeight() + i2;
      int i9 = ViewUtils.combineMeasuredStates(j, ViewCompat.getMeasuredState(localView));
      if (bool1)
      {
        i10 = localView.getBaseline();
        if (i10 != -1)
        {
          if (gravity >= 0) {
            break label938;
          }
          j = mGravity;
          label547:
          j = ((j & 0x70) >> 4 & 0xFFFFFFFE) >> 1;
          localObject[j] = Math.max(localObject[j], i10);
          arrayOfInt[j] = Math.max(arrayOfInt[j], i8 - i10);
        }
      }
      int i10 = Math.max(k, i8);
      if ((i != 0) && (height == -1))
      {
        k = 1;
        label620:
        if (weight <= 0.0F) {
          break label961;
        }
        if (n == 0) {
          break label954;
        }
      }
      for (;;)
      {
        j = Math.max(i3, i2);
        i = i1;
        i4 += getChildrenSkipCount(localView, i4);
        i3 = j;
        i1 = i;
        n = i10;
        i = i5;
        i5 = i9;
        i2 = m;
        j = i6;
        m = i5;
        break;
        i5 = mTotalLength;
        mTotalLength = Math.max(i5, leftMargin + i5 + rightMargin);
        break label422;
        label728:
        i6 = 1;
        i5 = m;
        break label451;
        i6 = Integer.MIN_VALUE;
        i5 = i6;
        if (width == 0)
        {
          i5 = i6;
          if (weight > 0.0F)
          {
            i5 = 0;
            width = -2;
          }
        }
        if (f1 == 0.0F)
        {
          i6 = mTotalLength;
          label791:
          measureChildBeforeLayout(localView, i4, paramInt1, i6, paramInt2, 0);
          if (i5 != Integer.MIN_VALUE) {
            width = i5;
          }
          i8 = localView.getMeasuredWidth();
          if (i7 == 0) {
            break label896;
          }
        }
        for (mTotalLength += leftMargin + i8 + rightMargin + getNextLocationOffset(localView);; mTotalLength = Math.max(i5, i5 + i8 + leftMargin + rightMargin + getNextLocationOffset(localView)))
        {
          i5 = m;
          i6 = n;
          if (!bool2) {
            break;
          }
          i5 = Math.max(i8, m);
          i6 = n;
          break;
          i6 = 0;
          break label791;
          label896:
          i5 = mTotalLength;
        }
        label938:
        j = gravity;
        break label547;
        k = 0;
        break label620;
        label954:
        i2 = i8;
      }
      label961:
      if (n != 0) {}
      for (;;)
      {
        i = Math.max(i1, i2);
        j = i3;
        break;
        i2 = i8;
      }
      label989:
      if ((mTotalLength > 0) && (hasDividerBeforeChildAt(i11))) {
        mTotalLength += mDividerWidth;
      }
      if ((localObject[1] != -1) || (localObject[0] != -1) || (localObject[2] != -1) || (localObject[3] != -1)) {}
      for (i4 = Math.max(k, Math.max(localObject[3], Math.max(localObject[0], Math.max(localObject[1], localObject[2]))) + Math.max(arrayOfInt[3], Math.max(arrayOfInt[0], Math.max(arrayOfInt[1], arrayOfInt[2]))));; i4 = k)
      {
        if ((bool2) && ((i13 == Integer.MIN_VALUE) || (i13 == 0)))
        {
          mTotalLength = 0;
          k = 0;
          if (k < i11)
          {
            localView = getVirtualChildAt(k);
            if (localView == null) {
              mTotalLength += measureNullChild(k);
            }
            for (;;)
            {
              k += 1;
              break;
              if (localView.getVisibility() == 8)
              {
                k = getChildrenSkipCount(localView, k) + k;
              }
              else
              {
                localLayoutParams = (LayoutParams)localView.getLayoutParams();
                if (i7 != 0)
                {
                  i5 = mTotalLength;
                  i6 = leftMargin;
                  mTotalLength = (rightMargin + (i6 + m) + getNextLocationOffset(localView) + i5);
                }
                else
                {
                  i5 = mTotalLength;
                  i6 = leftMargin;
                  mTotalLength = Math.max(i5, rightMargin + (i5 + m + i6) + getNextLocationOffset(localView));
                }
              }
            }
          }
        }
        mTotalLength += getPaddingLeft() + getPaddingRight();
        i8 = ViewCompat.resolveSizeAndState(Math.max(mTotalLength, getSuggestedMinimumWidth()), paramInt1, 0);
        k = (0xFFFFFF & i8) - mTotalLength;
        if ((n != 0) || ((k != 0) && (f1 > 0.0F)))
        {
          if (mWeightSum > 0.0F) {
            f1 = mWeightSum;
          }
          localObject[3] = -1;
          localObject[2] = -1;
          localObject[1] = -1;
          localObject[0] = -1;
          arrayOfInt[3] = -1;
          arrayOfInt[2] = -1;
          arrayOfInt[1] = -1;
          arrayOfInt[0] = -1;
          mTotalLength = 0;
          i4 = 0;
          i3 = i1;
          n = j;
          m = k;
          k = -1;
          i1 = i4;
          j = i3;
          if (i1 < i11)
          {
            localView = getVirtualChildAt(i1);
            if (localView == null) {
              break label2340;
            }
            if (localView.getVisibility() == 8)
            {
              i3 = m;
              m = k;
              k = j;
              j = i;
              i = i3;
            }
          }
        }
        for (;;)
        {
          i3 = i1 + 1;
          i1 = m;
          m = i;
          i = j;
          j = k;
          k = i1;
          i1 = i3;
          break;
          localLayoutParams = (LayoutParams)localView.getLayoutParams();
          float f2 = weight;
          if (f2 > 0.0F)
          {
            i4 = (int)(m * f2 / f1);
            i3 = m - i4;
            i5 = getChildMeasureSpec(paramInt2, getPaddingTop() + getPaddingBottom() + topMargin + bottomMargin, height);
            if ((width != 0) || (i13 != 1073741824))
            {
              i4 += localView.getMeasuredWidth();
              m = i4;
              if (i4 < 0) {
                m = 0;
              }
              localView.measure(View.MeasureSpec.makeMeasureSpec(m, 1073741824), i5);
              n = ViewUtils.combineMeasuredStates(n, ViewCompat.getMeasuredState(localView) & 0xFF000000);
              f1 -= f2;
              m = i3;
            }
          }
          for (;;)
          {
            if (i7 != 0)
            {
              mTotalLength += localView.getMeasuredWidth() + leftMargin + rightMargin + getNextLocationOffset(localView);
              label1727:
              if ((i12 == 1073741824) || (height != -1)) {
                break label1984;
              }
              i3 = 1;
              label1746:
              i6 = topMargin + bottomMargin;
              i5 = localView.getMeasuredHeight() + i6;
              i4 = Math.max(k, i5);
              if (i3 == 0) {
                break label1990;
              }
              k = i6;
              label1787:
              k = Math.max(j, k);
              if ((i == 0) || (height != -1)) {
                break label1997;
              }
              i = 1;
              label1813:
              if (bool1)
              {
                i3 = localView.getBaseline();
                if (i3 != -1) {
                  if (gravity >= 0) {
                    break label2003;
                  }
                }
              }
            }
            label1984:
            label1990:
            label1997:
            label2003:
            for (j = mGravity;; j = gravity)
            {
              j = ((j & 0x70) >> 4 & 0xFFFFFFFE) >> 1;
              localObject[j] = Math.max(localObject[j], i3);
              arrayOfInt[j] = Math.max(arrayOfInt[j], i5 - i3);
              j = i;
              i = m;
              m = i4;
              break;
              if (i4 > 0) {}
              for (m = i4;; m = 0)
              {
                localView.measure(View.MeasureSpec.makeMeasureSpec(m, 1073741824), i5);
                break;
              }
              i3 = mTotalLength;
              mTotalLength = Math.max(i3, localView.getMeasuredWidth() + i3 + leftMargin + rightMargin + getNextLocationOffset(localView));
              break label1727;
              i3 = 0;
              break label1746;
              k = i5;
              break label1787;
              i = 0;
              break label1813;
            }
            mTotalLength += getPaddingLeft() + getPaddingRight();
            if ((localObject[1] == -1) && (localObject[0] == -1) && (localObject[2] == -1))
            {
              m = k;
              if (localObject[3] == -1) {}
            }
            else
            {
              m = Math.max(k, Math.max(localObject[3], Math.max(localObject[0], Math.max(localObject[1], localObject[2]))) + Math.max(arrayOfInt[3], Math.max(arrayOfInt[0], Math.max(arrayOfInt[1], arrayOfInt[2]))));
            }
            k = i;
            i = n;
            n = k;
            k = j;
            for (;;)
            {
              if ((n == 0) && (i12 != 1073741824)) {}
              for (;;)
              {
                setMeasuredDimension(0xFF000000 & i | i8, ViewCompat.resolveSizeAndState(Math.max(k + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), paramInt2, i << 16));
                if (i2 != 0) {
                  forceUniformHeight(i11, paramInt1);
                }
                return;
                n = Math.max(i1, i3);
                if ((!bool2) || (i13 == 1073741824)) {
                  break;
                }
                k = 0;
                if (k >= i11) {
                  break;
                }
                localObject = getVirtualChildAt(k);
                if ((localObject == null) || (((View)localObject).getVisibility() == 8)) {}
                for (;;)
                {
                  k += 1;
                  break;
                  if (getLayoutParamsweight > 0.0F) {
                    ((View)localObject).measure(View.MeasureSpec.makeMeasureSpec(m, 1073741824), View.MeasureSpec.makeMeasureSpec(((View)localObject).getMeasuredHeight(), 1073741824));
                  }
                }
                k = m;
              }
              k = n;
              m = i4;
              n = i;
              i = j;
            }
          }
          label2340:
          i3 = m;
          m = k;
          k = j;
          j = i;
          i = i3;
        }
      }
      label2370:
      m = i2;
    }
  }
  
  int measureNullChild(int paramInt)
  {
    return 0;
  }
  
  void measureVertical(int paramInt1, int paramInt2)
  {
    mTotalLength = 0;
    int j = 0;
    int i = 0;
    int i1 = 0;
    int i4 = 0;
    int k = 1;
    float f1 = 0.0F;
    int i10 = getVirtualChildCount();
    int i11 = View.MeasureSpec.getMode(paramInt1);
    int i12 = View.MeasureSpec.getMode(paramInt2);
    int i3 = 0;
    int n = 0;
    int i13 = mBaselineAlignedChildIndex;
    boolean bool = mUseLargestChild;
    int m = Integer.MIN_VALUE;
    int i2 = 0;
    View localView;
    int i5;
    int i6;
    label212:
    LayoutParams localLayoutParams;
    int i7;
    if (i2 < i10)
    {
      localView = getVirtualChildAt(i2);
      if (localView == null)
      {
        mTotalLength += measureNullChild(i2);
        i5 = m;
        i6 = n;
        n = j;
        m = i;
        j = i6;
        i = i5;
      }
      for (;;)
      {
        i2 += 1;
        i5 = m;
        i6 = n;
        m = i;
        n = j;
        i = i5;
        j = i6;
        break;
        if (localView.getVisibility() != 8) {
          break label212;
        }
        i6 = i2 + getChildrenSkipCount(localView, i2);
        i2 = m;
        i5 = n;
        m = i;
        n = j;
        i = i2;
        j = i5;
        i2 = i6;
      }
      if (hasDividerBeforeChildAt(i2)) {
        mTotalLength += mDividerHeight;
      }
      localLayoutParams = (LayoutParams)localView.getLayoutParams();
      f1 += weight;
      if ((i12 == 1073741824) && (height == 0) && (weight > 0.0F))
      {
        n = mTotalLength;
        mTotalLength = Math.max(n, topMargin + n + bottomMargin);
        i6 = 1;
        i5 = m;
        if ((i13 >= 0) && (i13 == i2 + 1)) {
          mBaselineChildTop = mTotalLength;
        }
        if ((i2 < i13) && (weight > 0.0F)) {
          throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
        }
      }
      else
      {
        i6 = Integer.MIN_VALUE;
        i5 = i6;
        if (height == 0)
        {
          i5 = i6;
          if (weight > 0.0F)
          {
            i5 = 0;
            height = -2;
          }
        }
        if (f1 == 0.0F) {}
        for (i6 = mTotalLength;; i6 = 0)
        {
          measureChildBeforeLayout(localView, i2, paramInt1, 0, paramInt2, i6);
          if (i5 != Integer.MIN_VALUE) {
            height = i5;
          }
          i7 = localView.getMeasuredHeight();
          i5 = mTotalLength;
          mTotalLength = Math.max(i5, i5 + i7 + topMargin + bottomMargin + getNextLocationOffset(localView));
          i5 = m;
          i6 = n;
          if (!bool) {
            break;
          }
          i5 = Math.max(i7, m);
          i6 = n;
          break;
        }
      }
      n = 0;
      if ((i11 == 1073741824) || (width != -1)) {
        break label1677;
      }
      m = 1;
      n = 1;
    }
    for (;;)
    {
      i3 = leftMargin;
      i3 = rightMargin + i3;
      i7 = localView.getMeasuredWidth() + i3;
      int i9 = Math.max(j, i7);
      int i8 = ViewUtils.combineMeasuredStates(i, ViewCompat.getMeasuredState(localView));
      if ((k != 0) && (width == -1))
      {
        k = 1;
        label615:
        if (weight <= 0.0F) {
          break label704;
        }
        if (n == 0) {
          break label697;
        }
      }
      for (;;)
      {
        j = Math.max(i4, i3);
        i = i1;
        i2 += getChildrenSkipCount(localView, i2);
        i4 = j;
        i1 = i;
        n = i9;
        i = i5;
        i5 = i8;
        i3 = m;
        j = i6;
        m = i5;
        break;
        k = 0;
        break label615;
        label697:
        i3 = i7;
      }
      label704:
      if (n != 0) {}
      for (;;)
      {
        i = Math.max(i1, i3);
        j = i4;
        break;
        i3 = i7;
      }
      if ((mTotalLength > 0) && (hasDividerBeforeChildAt(i10))) {
        mTotalLength += mDividerHeight;
      }
      if ((bool) && ((i12 == Integer.MIN_VALUE) || (i12 == 0)))
      {
        mTotalLength = 0;
        i2 = 0;
        if (i2 < i10)
        {
          localView = getVirtualChildAt(i2);
          if (localView == null) {
            mTotalLength += measureNullChild(i2);
          }
          for (;;)
          {
            i2 += 1;
            break;
            if (localView.getVisibility() == 8)
            {
              i2 = getChildrenSkipCount(localView, i2) + i2;
            }
            else
            {
              localLayoutParams = (LayoutParams)localView.getLayoutParams();
              i5 = mTotalLength;
              i6 = topMargin;
              mTotalLength = Math.max(i5, bottomMargin + (i5 + m + i6) + getNextLocationOffset(localView));
            }
          }
        }
      }
      mTotalLength += getPaddingTop() + getPaddingBottom();
      i6 = ViewCompat.resolveSizeAndState(Math.max(mTotalLength, getSuggestedMinimumHeight()), paramInt2, 0);
      i2 = (0xFFFFFF & i6) - mTotalLength;
      if ((n != 0) || ((i2 != 0) && (f1 > 0.0F)))
      {
        if (mWeightSum > 0.0F) {
          f1 = mWeightSum;
        }
        mTotalLength = 0;
        m = 0;
        n = k;
        k = i1;
        i1 = i2;
        i2 = m;
        m = j;
        j = n;
        n = i1;
        for (;;)
        {
          if (i2 < i10)
          {
            localView = getVirtualChildAt(i2);
            if (localView.getVisibility() == 8)
            {
              i1 = k;
              k = m;
              m = i1;
              i2 += 1;
              i1 = k;
              k = m;
              m = i1;
            }
            else
            {
              localLayoutParams = (LayoutParams)localView.getLayoutParams();
              float f2 = weight;
              if (f2 <= 0.0F) {
                break label1662;
              }
              i4 = (int)(n * f2 / f1);
              i7 = getChildMeasureSpec(paramInt1, getPaddingLeft() + getPaddingRight() + leftMargin + rightMargin, width);
              if ((height != 0) || (i12 != 1073741824))
              {
                i5 = i4 + localView.getMeasuredHeight();
                i1 = i5;
                if (i5 < 0) {
                  i1 = 0;
                }
                localView.measure(i7, View.MeasureSpec.makeMeasureSpec(i1, 1073741824));
                i = ViewUtils.combineMeasuredStates(i, ViewCompat.getMeasuredState(localView) & 0xFF00);
                i1 = n - i4;
                n = i;
                f1 -= f2;
                i = i1;
              }
            }
          }
        }
      }
      for (;;)
      {
        i4 = leftMargin + rightMargin;
        i5 = localView.getMeasuredWidth() + i4;
        i1 = Math.max(m, i5);
        if ((i11 != 1073741824) && (width == -1))
        {
          m = 1;
          label1291:
          if (m == 0) {
            break label1433;
          }
          m = i4;
          label1300:
          m = Math.max(k, m);
          if ((j == 0) || (width != -1)) {
            break label1440;
          }
        }
        label1433:
        label1440:
        for (j = 1;; j = 0)
        {
          k = mTotalLength;
          i4 = localView.getMeasuredHeight();
          i5 = topMargin;
          mTotalLength = Math.max(k, bottomMargin + (i4 + k + i5) + getNextLocationOffset(localView));
          k = i1;
          i1 = i;
          i = n;
          n = i1;
          break;
          if (i4 > 0) {}
          for (i1 = i4;; i1 = 0)
          {
            localView.measure(i7, View.MeasureSpec.makeMeasureSpec(i1, 1073741824));
            break;
          }
          m = 0;
          break label1291;
          m = i5;
          break label1300;
        }
        mTotalLength += getPaddingTop() + getPaddingBottom();
        n = j;
        j = k;
        k = n;
        for (;;)
        {
          if ((k == 0) && (i11 != 1073741824)) {}
          for (;;)
          {
            setMeasuredDimension(ViewCompat.resolveSizeAndState(Math.max(j + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), paramInt1, i), i6);
            if (i3 != 0) {
              forceUniformWidth(i10, paramInt2);
            }
            return;
            i1 = Math.max(i1, i4);
            if ((!bool) || (i12 == 1073741824)) {
              break;
            }
            n = 0;
            if (n >= i10) {
              break;
            }
            localView = getVirtualChildAt(n);
            if ((localView == null) || (localView.getVisibility() == 8)) {}
            for (;;)
            {
              n += 1;
              break;
              if (getLayoutParamsweight > 0.0F) {
                localView.measure(View.MeasureSpec.makeMeasureSpec(localView.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(m, 1073741824));
              }
            }
            j = m;
          }
          m = i1;
          n = j;
          j = m;
          m = n;
        }
        label1662:
        i1 = i;
        i = n;
        n = i1;
      }
      label1677:
      m = i3;
    }
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    if (mDivider == null) {
      return;
    }
    if (mOrientation == 1)
    {
      drawDividersVertical(paramCanvas);
      return;
    }
    drawDividersHorizontal(paramCanvas);
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    if (Build.VERSION.SDK_INT >= 14)
    {
      super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
      paramAccessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
    }
  }
  
  public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    if (Build.VERSION.SDK_INT >= 14)
    {
      super.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfo);
      paramAccessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
    }
  }
  
  public void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (mOrientation == 1)
    {
      layoutVertical(paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    layoutHorizontal(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void onMeasure(int paramInt1, int paramInt2)
  {
    if (mOrientation == 1)
    {
      measureVertical(paramInt1, paramInt2);
      return;
    }
    measureHorizontal(paramInt1, paramInt2);
  }
  
  public void setBaselineAligned(boolean paramBoolean)
  {
    mBaselineAligned = paramBoolean;
  }
  
  public void setBaselineAlignedChildIndex(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getChildCount())) {
      throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
    }
    mBaselineAlignedChildIndex = paramInt;
  }
  
  public void setDividerDrawable(Drawable paramDrawable)
  {
    boolean bool = false;
    if (paramDrawable == mDivider) {
      return;
    }
    mDivider = paramDrawable;
    if (paramDrawable != null) {
      mDividerWidth = paramDrawable.getIntrinsicWidth();
    }
    for (mDividerHeight = paramDrawable.getIntrinsicHeight();; mDividerHeight = 0)
    {
      if (paramDrawable == null) {
        bool = true;
      }
      setWillNotDraw(bool);
      requestLayout();
      return;
      mDividerWidth = 0;
    }
  }
  
  public void setDividerPadding(int paramInt)
  {
    mDividerPadding = paramInt;
  }
  
  public void setGravity(int paramInt)
  {
    if (mGravity != paramInt)
    {
      if ((0x800007 & paramInt) != 0) {
        break label46;
      }
      paramInt = 0x800003 | paramInt;
    }
    label46:
    for (;;)
    {
      int i = paramInt;
      if ((paramInt & 0x70) == 0) {
        i = paramInt | 0x30;
      }
      mGravity = i;
      requestLayout();
      return;
    }
  }
  
  public void setHorizontalGravity(int paramInt)
  {
    paramInt &= 0x800007;
    if ((mGravity & 0x800007) != paramInt)
    {
      mGravity = (paramInt | mGravity & 0xFF7FFFF8);
      requestLayout();
    }
  }
  
  public void setMeasureWithLargestChildEnabled(boolean paramBoolean)
  {
    mUseLargestChild = paramBoolean;
  }
  
  public void setOrientation(int paramInt)
  {
    if (mOrientation != paramInt)
    {
      mOrientation = paramInt;
      requestLayout();
    }
  }
  
  public void setShowDividers(int paramInt)
  {
    if (paramInt != mShowDividers) {
      requestLayout();
    }
    mShowDividers = paramInt;
  }
  
  public void setVerticalGravity(int paramInt)
  {
    paramInt &= 0x70;
    if ((mGravity & 0x70) != paramInt)
    {
      mGravity = (paramInt | mGravity & 0xFFFFFF8F);
      requestLayout();
    }
  }
  
  public void setWeightSum(float paramFloat)
  {
    mWeightSum = Math.max(0.0F, paramFloat);
  }
  
  public boolean shouldDelayChildPressedState()
  {
    return false;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @IntDef(flag=true, value={0L, 1L, 2L, 4L})
  public static @interface DividerMode {}
  
  public static class LayoutParams
    extends ViewGroup.MarginLayoutParams
  {
    public int gravity = -1;
    public float weight;
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
      weight = 0.0F;
    }
    
    public LayoutParams(int paramInt1, int paramInt2, float paramFloat)
    {
      super(paramInt2);
      weight = paramFloat;
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.LinearLayoutCompat_Layout);
      weight = paramContext.getFloat(R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0F);
      gravity = paramContext.getInt(R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
      paramContext.recycle();
    }
    
    public LayoutParams(LayoutParams paramLayoutParams)
    {
      super();
      weight = weight;
      gravity = gravity;
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @IntDef({0L, 1L})
  public static @interface OrientationMode {}
}

/* Location:
 * Qualified Name:     android.support.v7.widget.LinearLayoutCompat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */