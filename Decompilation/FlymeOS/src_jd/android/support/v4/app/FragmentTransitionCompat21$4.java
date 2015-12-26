package android.support.v4.app;

import android.transition.Transition;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class FragmentTransitionCompat21$4
  implements ViewTreeObserver.OnPreDrawListener
{
  FragmentTransitionCompat21$4(View paramView1, Transition paramTransition1, View paramView2, ArrayList paramArrayList1, Transition paramTransition2, ArrayList paramArrayList2, Transition paramTransition3, ArrayList paramArrayList3, Map paramMap, ArrayList paramArrayList4, Transition paramTransition4) {}
  
  public boolean onPreDraw()
  {
    val$sceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
    if (val$enterTransition != null)
    {
      val$enterTransition.removeTarget(val$nonExistentView);
      FragmentTransitionCompat21.removeTargets(val$enterTransition, val$enteringViews);
    }
    if (val$exitTransition != null) {
      FragmentTransitionCompat21.removeTargets(val$exitTransition, val$exitingViews);
    }
    if (val$sharedElementTransition != null) {
      FragmentTransitionCompat21.removeTargets(val$sharedElementTransition, val$sharedElementTargets);
    }
    Iterator localIterator = val$renamedViews.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      ((View)localEntry.getValue()).setTransitionName((String)localEntry.getKey());
    }
    int j = val$hiddenViews.size();
    int i = 0;
    while (i < j)
    {
      val$overallTransition.excludeTarget((View)val$hiddenViews.get(i), false);
      i += 1;
    }
    val$overallTransition.excludeTarget(val$nonExistentView, false);
    return true;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.FragmentTransitionCompat21.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */