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

final class FragmentTransitionCompat21$2
  implements ViewTreeObserver.OnPreDrawListener
{
  FragmentTransitionCompat21$2(View paramView1, FragmentTransitionCompat21.ViewRetriever paramViewRetriever, Map paramMap1, Map paramMap2, Transition paramTransition, ArrayList paramArrayList, View paramView2) {}
  
  public boolean onPreDraw()
  {
    val$container.getViewTreeObserver().removeOnPreDrawListener(this);
    View localView = val$inFragment.getView();
    if (localView != null)
    {
      if (!val$nameOverrides.isEmpty())
      {
        FragmentTransitionCompat21.findNamedViews(val$renamedViews, localView);
        val$renamedViews.keySet().retainAll(val$nameOverrides.values());
        Iterator localIterator = val$nameOverrides.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          Object localObject = (String)localEntry.getValue();
          localObject = (View)val$renamedViews.get(localObject);
          if (localObject != null) {
            ((View)localObject).setTransitionName((String)localEntry.getKey());
          }
        }
      }
      if (val$enterTransition != null)
      {
        FragmentTransitionCompat21.access$000(val$enteringViews, localView);
        val$enteringViews.removeAll(val$renamedViews.values());
        val$enteringViews.add(val$nonExistentView);
        val$enterTransition.removeTarget(val$nonExistentView);
        FragmentTransitionCompat21.addTargets(val$enterTransition, val$enteringViews);
      }
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.FragmentTransitionCompat21.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */