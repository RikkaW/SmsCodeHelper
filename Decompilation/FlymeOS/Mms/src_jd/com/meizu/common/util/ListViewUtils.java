package com.meizu.common.util;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.BaseAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;

public class ListViewUtils
{
  private static final int DEFAULT_DURATION = 300;
  private static final int DELAY_ANIMATION = 33;
  private Interpolator mAlInterpolator;
  HashMap<Long, Integer> mItemIdTopMap = new HashMap();
  private Interpolator mTrInterpolator;
  private Interpolator mUpInterpolator;
  
  public ListViewUtils()
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      mTrInterpolator = new PathInterpolator(0.5F, 0.0F, 0.5F, 1.0F);
      mAlInterpolator = new PathInterpolator(0.33F, 0.0F, 0.67F, 1.0F);
      mUpInterpolator = new PathInterpolator(0.33F, 0.0F, 0.4F, 1.0F);
      return;
    }
    mTrInterpolator = new LinearInterpolator();
    mAlInterpolator = new LinearInterpolator();
    mUpInterpolator = new LinearInterpolator();
  }
  
  private void animateRemoval(ListView paramListView, View paramView, OnListViewFadeListener paramOnListViewFadeListener)
  {
    int j = 0;
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramListView.getCount())
    {
      localArrayList.add(Integer.valueOf(i));
      i += 1;
    }
    int k = paramListView.getFirstVisiblePosition();
    i = j;
    while (i < paramListView.getChildCount())
    {
      View localView = paramListView.getChildAt(i);
      if (localView != paramView)
      {
        long l = k + i;
        mItemIdTopMap.put(Long.valueOf(l), Integer.valueOf(localView.getTop()));
      }
      i += 1;
    }
    if (paramOnListViewFadeListener != null)
    {
      paramOnListViewFadeListener.onEndListViewFadedOut();
      localArrayList.remove(paramListView.getPositionForView(paramView));
    }
    paramView = paramListView.getViewTreeObserver();
    paramView.addOnPreDrawListener(new ListViewUtils.2(this, paramView, paramListView, localArrayList, paramOnListViewFadeListener));
  }
  
  public void fadeOutItemView(ListView paramListView, int paramInt1, int paramInt2, OnListViewFadeListener paramOnListViewFadeListener, BaseAdapter paramBaseAdapter)
  {
    int j = 0;
    int m = paramListView.getFirstVisiblePosition();
    paramBaseAdapter = new ArrayList();
    int i = paramInt1;
    while (i <= paramInt2)
    {
      localObject = paramListView.getChildAt(i - m);
      int k = j;
      if (localObject != null)
      {
        Keyframe localKeyframe1 = Keyframe.ofFloat(0.0F, 1.0F);
        Keyframe localKeyframe2 = Keyframe.ofFloat(1.0F, 0.0F);
        localKeyframe2.setInterpolator(mAlInterpolator);
        Keyframe localKeyframe3 = Keyframe.ofFloat(0.0F, 0.0F);
        Keyframe localKeyframe4 = Keyframe.ofFloat(1.0F, -((View)localObject).getWidth());
        localKeyframe4.setInterpolator(mTrInterpolator);
        localObject = ObjectAnimator.ofPropertyValuesHolder(localObject, new PropertyValuesHolder[] { PropertyValuesHolder.ofKeyframe("alpha", new Keyframe[] { localKeyframe1, localKeyframe2 }), PropertyValuesHolder.ofKeyframe("translationX", new Keyframe[] { localKeyframe3, localKeyframe4 }) });
        ((ObjectAnimator)localObject).setDuration(300L);
        ((ObjectAnimator)localObject).setStartDelay(j * 33);
        paramBaseAdapter.add(localObject);
        k = j + 1;
      }
      i += 1;
      j = k;
    }
    Object localObject = new AnimatorSet();
    ((AnimatorSet)localObject).playTogether(paramBaseAdapter);
    ((AnimatorSet)localObject).addListener(new ListViewUtils.1(this, paramOnListViewFadeListener, paramInt1, paramInt2, paramListView, m));
    ((AnimatorSet)localObject).start();
  }
  
  public static abstract interface OnListViewFadeListener
  {
    public abstract void onEndListViewFadedOut();
    
    public abstract void onEndResetListView();
    
    public abstract void onStartListViewFadeOut();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.ListViewUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */