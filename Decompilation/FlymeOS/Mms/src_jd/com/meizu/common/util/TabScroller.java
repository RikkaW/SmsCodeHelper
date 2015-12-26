package com.meizu.common.util;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import com.meizu.common.R.attr;
import com.meizu.common.R.drawable;
import com.meizu.common.R.styleable;
import java.util.ArrayList;

public class TabScroller
{
  private float mCurrentTabOffset;
  private int mCurrentTabPos = 0;
  private float mOldTabOffset = 0.0F;
  private Drawable mTabIndicator;
  private int mTabIndicatorHeight = 20;
  private ArrayList<Integer> mTabLength = new ArrayList();
  private ViewGroup mTabParentView;
  private ArrayList<View> mTabViews = new ArrayList();
  
  public TabScroller(Context paramContext, ViewGroup paramViewGroup)
  {
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(null, R.styleable.TabScroller, R.attr.MeizuCommon_TabScrollerStyle, 0);
    mTabIndicator = localTypedArray.getDrawable(R.styleable.TabScroller_mcTabIndicatorDrawable);
    if (mTabIndicator == null) {
      mTabIndicator = paramContext.getResources().getDrawable(R.drawable.mz_tab_selected);
    }
    localTypedArray.recycle();
    mTabIndicatorHeight = mTabIndicator.getIntrinsicHeight();
    mTabParentView = paramViewGroup;
  }
  
  public void addTabView(int paramInt, View paramView)
  {
    if ((paramView != null) && (!mTabViews.contains(paramView))) {
      mTabViews.add(paramInt, paramView);
    }
  }
  
  public void addTabView(View paramView)
  {
    if ((paramView != null) && (!mTabViews.contains(paramView))) {
      mTabViews.add(paramView);
    }
  }
  
  public void dispatchDraw(Canvas paramCanvas)
  {
    int n = 0;
    int i2 = mTabViews.size();
    if (i2 == 0) {
      return;
    }
    int j;
    label50:
    View localView3;
    int i;
    if (mCurrentTabPos >= i2)
    {
      mCurrentTabPos = (i2 - 1);
      if (mTabLength.size() != i2) {
        break label387;
      }
      j = 1;
      localView3 = (View)mTabViews.get(mCurrentTabPos);
      if (j == 0) {
        break label393;
      }
      i = ((Integer)mTabLength.get(mCurrentTabPos)).intValue();
      label90:
      if ((i >= 0) && (i <= localView3.getWidth())) {
        break label545;
      }
    }
    label201:
    label387:
    label393:
    label532:
    label535:
    label545:
    for (int k = localView3.getWidth();; k = i)
    {
      int i1 = localView3.getHeight();
      int m = localView3.getLeft() + localView3.getWidth() / 2;
      View localView2;
      View localView1;
      if ((mCurrentTabOffset > mOldTabOffset) && (mCurrentTabPos < i2 - 1))
      {
        localView2 = (View)mTabViews.get(mCurrentTabPos + 1);
        if (j != 0)
        {
          j = ((Integer)mTabLength.get(mCurrentTabPos + 1)).intValue();
          if (j >= 0)
          {
            localView1 = localView2;
            i = j;
            if (j <= localView2.getWidth()) {
              break label532;
            }
          }
          i = localView2.getWidth();
          localView1 = localView2;
        }
      }
      for (;;)
      {
        float f1;
        if (localView1 != null)
        {
          f1 = i - k;
          float f2 = mCurrentTabOffset;
          i = localView1.getLeft();
          j = localView1.getWidth() / 2;
          n = localView3.getLeft();
          i2 = localView3.getWidth() / 2;
          float f3 = m;
          i = (int)((j + i - (i2 + n)) * mCurrentTabOffset + f3);
          f1 *= f2;
        }
        for (;;)
        {
          j = (int)(f1 + k);
          mTabIndicator.setBounds(i - j / 2, i1 - mTabIndicatorHeight, j / 2 + i, i1);
          paramCanvas.save();
          mTabIndicator.draw(paramCanvas);
          paramCanvas.restore();
          return;
          if (mCurrentTabPos >= 0) {
            break;
          }
          mCurrentTabPos = 0;
          break;
          j = 0;
          break label50;
          i = localView3.getWidth();
          break label90;
          j = localView2.getWidth();
          break label201;
          if ((mCurrentTabOffset >= mOldTabOffset) || (mCurrentTabPos <= 0)) {
            break label535;
          }
          localView2 = (View)mTabViews.get(mCurrentTabPos - 1);
          if (j != 0) {}
          for (j = ((Integer)mTabLength.get(mCurrentTabPos - 1)).intValue();; j = localView2.getWidth())
          {
            if (j >= 0)
            {
              localView1 = localView2;
              i = j;
              if (j <= localView2.getWidth()) {
                break label532;
              }
            }
            i = localView2.getWidth();
            localView1 = localView2;
            break;
          }
          f1 = 0.0F;
          i = m;
        }
        continue;
        localView1 = null;
        i = n;
      }
    }
  }
  
  public void onTabScrolled(int paramInt, float paramFloat)
  {
    mCurrentTabPos = paramInt;
    mCurrentTabOffset = paramFloat;
    if (mTabParentView != null) {
      mTabParentView.invalidate();
    }
  }
  
  public boolean removeTabView(int paramInt)
  {
    return removeTabView((View)mTabViews.get(paramInt));
  }
  
  public boolean removeTabView(View paramView)
  {
    boolean bool = mTabViews.remove(paramView);
    if (mTabParentView != null) {
      mTabParentView.invalidate();
    }
    return bool;
  }
  
  public void setCurrentTab(int paramInt)
  {
    if (mTabParentView == null) {
      return;
    }
    mCurrentTabPos = paramInt;
    mCurrentTabOffset = 0.0F;
    mTabParentView.invalidate();
  }
  
  public void setTabIndicatorDrawable(Drawable paramDrawable)
  {
    if (paramDrawable != null)
    {
      mTabIndicator = paramDrawable;
      mTabIndicatorHeight = mTabIndicator.getIntrinsicHeight();
    }
  }
  
  public void setTabIndicatorHeight(int paramInt)
  {
    if (paramInt > 0) {
      mTabIndicatorHeight = paramInt;
    }
  }
  
  public void setTabLength(int[] paramArrayOfInt)
  {
    if (paramArrayOfInt != null)
    {
      int j = paramArrayOfInt.length;
      int i = 0;
      while (i < j)
      {
        int k = paramArrayOfInt[i];
        mTabLength.add(Integer.valueOf(k));
        i += 1;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.TabScroller
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */