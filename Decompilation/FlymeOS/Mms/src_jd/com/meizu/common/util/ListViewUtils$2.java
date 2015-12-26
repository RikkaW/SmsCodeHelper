package com.meizu.common.util;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;

class ListViewUtils$2
  implements ViewTreeObserver.OnPreDrawListener
{
  ListViewUtils$2(ListViewUtils paramListViewUtils, ViewTreeObserver paramViewTreeObserver, ListView paramListView, ArrayList paramArrayList, ListViewUtils.OnListViewFadeListener paramOnListViewFadeListener) {}
  
  public boolean onPreDraw()
  {
    val$observer.removeOnPreDrawListener(this);
    int k = val$listview.getFirstVisiblePosition();
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < val$listview.getChildCount())
    {
      localObject1 = val$listview.getChildAt(i);
      long l = ((Integer)val$adapterList.get(k + i)).intValue();
      Object localObject2 = (Integer)this$0.mItemIdTopMap.get(Long.valueOf(l));
      int m = ((View)localObject1).getTop();
      int j;
      Keyframe localKeyframe;
      if (localObject2 != null)
      {
        if (((Integer)localObject2).intValue() != m)
        {
          j = ((Integer)localObject2).intValue();
          if (localObject1 != null)
          {
            localObject2 = Keyframe.ofFloat(0.0F, j - m);
            localKeyframe = Keyframe.ofFloat(1.0F, 0.0F);
            localKeyframe.setInterpolator(ListViewUtils.access$100(this$0));
            localArrayList.add(ObjectAnimator.ofPropertyValuesHolder(localObject1, new PropertyValuesHolder[] { PropertyValuesHolder.ofKeyframe("translationY", new Keyframe[] { localObject2, localKeyframe }) }));
          }
        }
        i += 1;
      }
      else
      {
        j = ((View)localObject1).getHeight() + val$listview.getDividerHeight();
        if (i > 0) {}
        for (;;)
        {
          j = Integer.valueOf(j + m).intValue();
          if (localObject1 == null) {
            break;
          }
          localObject2 = Keyframe.ofFloat(0.0F, j - m);
          localKeyframe = Keyframe.ofFloat(1.0F, 0.0F);
          localKeyframe.setInterpolator(ListViewUtils.access$100(this$0));
          localArrayList.add(ObjectAnimator.ofPropertyValuesHolder(localObject1, new PropertyValuesHolder[] { PropertyValuesHolder.ofKeyframe("translationY", new Keyframe[] { localObject2, localKeyframe }) }));
          break;
          j = -j;
        }
      }
    }
    Object localObject1 = new AnimatorSet();
    ((AnimatorSet)localObject1).playTogether(localArrayList);
    ((AnimatorSet)localObject1).addListener(new ListViewUtils.2.1(this));
    ((AnimatorSet)localObject1).start();
    this$0.mItemIdTopMap.clear();
    return true;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.ListViewUtils.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */