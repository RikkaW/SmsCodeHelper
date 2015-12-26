package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.layout;
import android.support.v7.appcompat.R.string;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.internal.widget.ViewUtils;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import aqt;

public class MultiChoiceView
  extends LinearLayoutCompat
{
  public static final int ITEM_CLOSE = 0;
  public static final int ITEM_SELECT_ALL = 1;
  private View mClose;
  private int mCloseItemLayout;
  private View mSelectAll;
  private int mSelectAllItemLayout;
  private CharSequence mSubTitle;
  private int mSubtitleStyleRes;
  private final TextView mSubtitleView;
  private CharSequence mTitle;
  private int mTitleGravity = 17;
  private View mTitleLayout;
  private int mTitleStyleRes;
  private final TextView mTitleView;
  
  public MultiChoiceView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public MultiChoiceView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.mzMultiChoiceViewStyle);
  }
  
  public MultiChoiceView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramAttributeSet = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.MzMultiChoiceView, paramInt, 0);
    mTitleStyleRes = paramAttributeSet.getResourceId(R.styleable.MzMultiChoiceView_titleTextStyle, 0);
    mSubtitleStyleRes = paramAttributeSet.getResourceId(R.styleable.MzMultiChoiceView_subtitleTextStyle, 0);
    paramAttributeSet.recycle();
    paramContext = LayoutInflater.from(paramContext);
    mCloseItemLayout = R.layout.mz_action_multi_choice_mode_close_item;
    mSelectAllItemLayout = R.layout.mz_action_multi_choice_mode_select_all_item;
    mClose = paramContext.inflate(mCloseItemLayout, this, false);
    addView(mClose);
    mSelectAll = paramContext.inflate(mSelectAllItemLayout, this, false);
    addView(mSelectAll);
    if (Build.VERSION.SDK_INT < 21)
    {
      paramAttributeSet = new ItemRippleDrawable(mClose);
      mClose.setBackgroundDrawable(paramAttributeSet);
      paramAttributeSet = new ItemRippleDrawable(mSelectAll);
      mSelectAll.setBackgroundDrawable(paramAttributeSet);
      setClipChildren(false);
    }
    mTitleLayout = paramContext.inflate(R.layout.mz_action_bar_title_item, this, false);
    addView(mTitleLayout);
    mTitleView = ((TextView)mTitleLayout.findViewById(R.id.action_bar_title));
    mSubtitleView = ((TextView)mTitleLayout.findViewById(R.id.action_bar_subtitle));
    if (mTitleStyleRes != 0) {
      mTitleView.setTextAppearance(getContext(), mTitleStyleRes);
    }
    if (mSubtitleStyleRes != 0) {
      mSubtitleView.setTextAppearance(getContext(), mSubtitleStyleRes);
    }
    setTitle(getResources().getString(R.string.mz_action_bar_multi_choice_title, new Object[] { Integer.valueOf(0) }));
  }
  
  private int computeAvailableWidth(int paramInt1, int paramInt2)
  {
    int i = 0;
    int j = paramInt2 - (getPaddingLeft() + getPaddingRight());
    ViewGroup.MarginLayoutParams localMarginLayoutParams;
    if ((mTitleGravity & 0x7) == 1)
    {
      if ((mClose == null) || (mClose.getParent() != this)) {
        break label159;
      }
      paramInt1 = mClose.getMeasuredWidth();
      localMarginLayoutParams = (ViewGroup.MarginLayoutParams)mClose.getLayoutParams();
      paramInt2 = leftMargin;
    }
    label159:
    for (paramInt1 = rightMargin + paramInt2 + paramInt1;; paramInt1 = 0)
    {
      paramInt2 = i;
      if (mSelectAll != null)
      {
        paramInt2 = i;
        if (mSelectAll.getParent() == this)
        {
          paramInt2 = mSelectAll.getMeasuredWidth();
          localMarginLayoutParams = (ViewGroup.MarginLayoutParams)mSelectAll.getLayoutParams();
          i = leftMargin;
          paramInt2 += rightMargin + i;
        }
      }
      if (paramInt1 >= paramInt2)
      {
        paramInt1 = j - paramInt1 * 2;
        return paramInt1;
      }
      return j - paramInt2 * 2;
    }
  }
  
  private void initTitle()
  {
    int m = 8;
    int j = 1;
    mTitleView.setText(mTitle);
    mSubtitleView.setText(mSubTitle);
    int i;
    label50:
    Object localObject;
    if (!TextUtils.isEmpty(mTitle))
    {
      i = 1;
      if (TextUtils.isEmpty(mSubTitle)) {
        break label99;
      }
      localObject = mSubtitleView;
      if (j == 0) {
        break label104;
      }
    }
    label99:
    label104:
    for (int k = 0;; k = 8)
    {
      ((TextView)localObject).setVisibility(k);
      localObject = mTitleLayout;
      if (i == 0)
      {
        i = m;
        if (j == 0) {}
      }
      else
      {
        i = 0;
      }
      ((View)localObject).setVisibility(i);
      return;
      i = 0;
      break;
      j = 0;
      break label50;
    }
  }
  
  protected static int next(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramBoolean) {
      return paramInt1 - paramInt2;
    }
    return paramInt1 + paramInt2;
  }
  
  private void setBackgroundHotspotBounds(View paramView)
  {
    Drawable localDrawable = paramView.getBackground();
    if (localDrawable != null)
    {
      int j = paramView.getPaddingLeft();
      int k = paramView.getPaddingRight();
      int m = paramView.getWidth();
      int i = (j - k + m) / 2;
      j = (m - j - k) / 2;
      DrawableCompat.setHotspotBounds(localDrawable, i - j, 0, j + i, paramView.getHeight());
    }
  }
  
  public View getCloseItemView()
  {
    return mClose;
  }
  
  public View getSelectAllView()
  {
    return mSelectAll;
  }
  
  public CharSequence getSubTitle()
  {
    return mSubTitle;
  }
  
  public CharSequence getTitle()
  {
    return mTitle;
  }
  
  protected int measureChildView(View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    paramView.measure(View.MeasureSpec.makeMeasureSpec(paramInt1, Integer.MIN_VALUE), paramInt2);
    return Math.max(0, paramInt1 - paramView.getMeasuredWidth() - paramInt3);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    boolean bool = ViewUtils.isLayoutRtl(this);
    int i;
    int j;
    int k;
    Object localObject;
    if (bool)
    {
      i = paramInt3 - paramInt1 - getPaddingRight();
      j = getPaddingTop();
      k = paramInt4 - paramInt2 - getPaddingTop() - getPaddingBottom();
      paramInt2 = i;
      if (mClose != null)
      {
        paramInt2 = i;
        if (mClose.getVisibility() != 8)
        {
          localObject = (ViewGroup.MarginLayoutParams)mClose.getLayoutParams();
          if (!bool) {
            break label317;
          }
          paramInt2 = rightMargin;
          label92:
          if (!bool) {
            break label326;
          }
          paramInt4 = leftMargin;
          label104:
          paramInt2 = next(i, paramInt2, bool);
          paramInt2 = next(positionChild(mClose, paramInt2, j, k, bool) + paramInt2, paramInt4, bool);
          setBackgroundHotspotBounds(mClose);
        }
      }
      if ((mTitleLayout != null) && (mTitleLayout.getVisibility() != 8))
      {
        if ((mTitleGravity & 0x7) != 1) {
          break label368;
        }
        paramInt2 = (paramInt3 - paramInt1 - mTitleLayout.getMeasuredWidth()) / 2;
      }
    }
    label217:
    label259:
    label266:
    label317:
    label326:
    label349:
    label358:
    label363:
    label368:
    for (;;)
    {
      positionChild(mTitleLayout, paramInt2, j, k, bool);
      if (bool)
      {
        paramInt1 = getPaddingLeft();
        if ((mSelectAll != null) && (mSelectAll.getVisibility() != 8))
        {
          localObject = (ViewGroup.MarginLayoutParams)mSelectAll.getLayoutParams();
          if (!bool) {
            break label349;
          }
          paramInt2 = leftMargin;
          if (bool) {
            break label358;
          }
          paramBoolean = true;
          paramInt1 = next(paramInt1, paramInt2, paramBoolean);
          localObject = mSelectAll;
          if (bool) {
            break label363;
          }
        }
      }
      for (paramBoolean = true;; paramBoolean = false)
      {
        positionChild((View)localObject, paramInt1, j, k, paramBoolean);
        setBackgroundHotspotBounds(mSelectAll);
        return;
        i = getPaddingLeft();
        break;
        paramInt2 = leftMargin;
        break label92;
        paramInt4 = rightMargin;
        break label104;
        paramInt1 = paramInt3 - paramInt1 - getPaddingRight();
        break label217;
        paramInt2 = rightMargin;
        break label259;
        paramBoolean = false;
        break label266;
      }
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = 0;
    int k = View.MeasureSpec.getSize(paramInt1);
    int m = getPaddingTop() + getPaddingBottom();
    int j = k - getPaddingLeft() - getPaddingRight();
    int n = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(paramInt2) - m, Integer.MIN_VALUE);
    paramInt1 = j;
    ViewGroup.MarginLayoutParams localMarginLayoutParams;
    if (mClose != null)
    {
      paramInt1 = measureChildView(mClose, j, n, 0);
      localMarginLayoutParams = (ViewGroup.MarginLayoutParams)mClose.getLayoutParams();
      paramInt2 = leftMargin;
      paramInt1 -= rightMargin + paramInt2;
    }
    paramInt2 = paramInt1;
    if (mSelectAll != null)
    {
      paramInt1 = measureChildView(mSelectAll, paramInt1, n, 0);
      localMarginLayoutParams = (ViewGroup.MarginLayoutParams)mSelectAll.getLayoutParams();
      paramInt2 = leftMargin;
      paramInt2 = paramInt1 - (rightMargin + paramInt2);
    }
    paramInt1 = computeAvailableWidth(paramInt2, k);
    if (mTitleLayout != null) {
      measureChildView(mTitleLayout, paramInt1, n, 0);
    }
    j = getChildCount();
    paramInt2 = 0;
    paramInt1 = i;
    if (paramInt2 < j)
    {
      i = getChildAt(paramInt2).getMeasuredHeight() + m;
      if (i <= paramInt1) {
        break label228;
      }
      paramInt1 = i;
    }
    label228:
    for (;;)
    {
      paramInt2 += 1;
      break;
      setMeasuredDimension(k, paramInt1);
      return;
    }
  }
  
  protected int positionChild(View paramView, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    int i = paramView.getMeasuredWidth();
    int j = paramView.getMeasuredHeight();
    paramInt2 = (paramInt3 - j) / 2 + paramInt2;
    if (paramBoolean) {
      paramView.layout(paramInt1 - i, paramInt2, paramInt1, j + paramInt2);
    }
    for (;;)
    {
      paramInt1 = i;
      if (paramBoolean) {
        paramInt1 = -i;
      }
      return paramInt1;
      paramView.layout(paramInt1, paramInt2, paramInt1 + i, j + paramInt2);
    }
  }
  
  public void setOnCloseItemClickListener(View.OnClickListener paramOnClickListener)
  {
    if (mClose != null) {
      mClose.setOnClickListener(paramOnClickListener);
    }
  }
  
  public void setOnItemClickListener(int paramInt, View.OnClickListener paramOnClickListener)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("Item does not exist");
    case 0: 
      if (mClose != null) {
        mClose.setOnClickListener(paramOnClickListener);
      }
      break;
    }
    do
    {
      return;
    } while (mSelectAll == null);
    mSelectAll.setOnClickListener(paramOnClickListener);
  }
  
  public void setOnSelectAllItemClickListener(View.OnClickListener paramOnClickListener)
  {
    if (mSelectAll != null) {
      mSelectAll.setOnClickListener(paramOnClickListener);
    }
  }
  
  public void setSubTitle(CharSequence paramCharSequence)
  {
    mSubTitle = paramCharSequence;
    initTitle();
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    mTitle = paramCharSequence;
    initTitle();
  }
  
  class ItemRippleDrawable
    extends aqt
  {
    public ItemRippleDrawable(View paramView)
    {
      super(R.attr.mzActionButtonRippleSplitStyle);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.MultiChoiceView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */