package android.support.v4.app;

import android.graphics.Rect;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class FragmentTransitionCompat21
{
  public static void addTargets(Object paramObject, ArrayList<View> paramArrayList)
  {
    int i = 0;
    paramObject = (Transition)paramObject;
    int j;
    if ((paramObject instanceof TransitionSet))
    {
      paramObject = (TransitionSet)paramObject;
      j = ((TransitionSet)paramObject).getTransitionCount();
      while (i < j)
      {
        addTargets(((TransitionSet)paramObject).getTransitionAt(i), paramArrayList);
        i += 1;
      }
    }
    if ((!hasSimpleTarget((Transition)paramObject)) && (isNullOrEmpty(((Transition)paramObject).getTargets())))
    {
      j = paramArrayList.size();
      i = 0;
      while (i < j)
      {
        ((Transition)paramObject).addTarget((View)paramArrayList.get(i));
        i += 1;
      }
    }
  }
  
  public static void addTransitionTargets(Object paramObject1, Object paramObject2, View paramView1, ViewRetriever paramViewRetriever, View paramView2, EpicenterView paramEpicenterView, Map<String, String> paramMap, ArrayList<View> paramArrayList1, Map<String, View> paramMap1, ArrayList<View> paramArrayList2)
  {
    if ((paramObject1 != null) || (paramObject2 != null))
    {
      paramObject1 = (Transition)paramObject1;
      if (paramObject1 != null) {
        ((Transition)paramObject1).addTarget(paramView2);
      }
      if (paramObject2 != null) {
        addTargets((Transition)paramObject2, paramArrayList2);
      }
      if (paramViewRetriever != null) {
        paramView1.getViewTreeObserver().addOnPreDrawListener(new FragmentTransitionCompat21.2(paramView1, paramViewRetriever, paramMap, paramMap1, (Transition)paramObject1, paramArrayList1, paramView2));
      }
      setSharedElementEpicenter((Transition)paramObject1, paramEpicenterView);
    }
  }
  
  public static void beginDelayedTransition(ViewGroup paramViewGroup, Object paramObject)
  {
    TransitionManager.beginDelayedTransition(paramViewGroup, (Transition)paramObject);
  }
  
  public static Object captureExitingViews(Object paramObject, View paramView1, ArrayList<View> paramArrayList, Map<String, View> paramMap, View paramView2)
  {
    Object localObject = paramObject;
    if (paramObject != null)
    {
      captureTransitioningViews(paramArrayList, paramView1);
      if (paramMap != null) {
        paramArrayList.removeAll(paramMap.values());
      }
      if (paramArrayList.isEmpty()) {
        localObject = null;
      }
    }
    else
    {
      return localObject;
    }
    paramArrayList.add(paramView2);
    addTargets((Transition)paramObject, paramArrayList);
    return paramObject;
  }
  
  private static void captureTransitioningViews(ArrayList<View> paramArrayList, View paramView)
  {
    if (paramView.getVisibility() == 0)
    {
      if (!(paramView instanceof ViewGroup)) {
        break label61;
      }
      paramView = (ViewGroup)paramView;
      if (!paramView.isTransitionGroup()) {
        break label33;
      }
      paramArrayList.add(paramView);
    }
    for (;;)
    {
      return;
      label33:
      int j = paramView.getChildCount();
      int i = 0;
      while (i < j)
      {
        captureTransitioningViews(paramArrayList, paramView.getChildAt(i));
        i += 1;
      }
    }
    label61:
    paramArrayList.add(paramView);
  }
  
  public static void cleanupTransitions(View paramView1, View paramView2, Object paramObject1, ArrayList<View> paramArrayList1, Object paramObject2, ArrayList<View> paramArrayList2, Object paramObject3, ArrayList<View> paramArrayList3, Object paramObject4, ArrayList<View> paramArrayList4, Map<String, View> paramMap)
  {
    paramObject1 = (Transition)paramObject1;
    paramObject2 = (Transition)paramObject2;
    paramObject3 = (Transition)paramObject3;
    paramObject4 = (Transition)paramObject4;
    if (paramObject4 != null) {
      paramView1.getViewTreeObserver().addOnPreDrawListener(new FragmentTransitionCompat21.4(paramView1, (Transition)paramObject1, paramView2, paramArrayList1, (Transition)paramObject2, paramArrayList2, (Transition)paramObject3, paramArrayList3, paramMap, paramArrayList4, (Transition)paramObject4));
    }
  }
  
  public static Object cloneTransition(Object paramObject)
  {
    Object localObject = paramObject;
    if (paramObject != null) {
      localObject = ((Transition)paramObject).clone();
    }
    return localObject;
  }
  
  public static void excludeTarget(Object paramObject, View paramView, boolean paramBoolean)
  {
    ((Transition)paramObject).excludeTarget(paramView, paramBoolean);
  }
  
  public static void findNamedViews(Map<String, View> paramMap, View paramView)
  {
    if (paramView.getVisibility() == 0)
    {
      String str = paramView.getTransitionName();
      if (str != null) {
        paramMap.put(str, paramView);
      }
      if ((paramView instanceof ViewGroup))
      {
        paramView = (ViewGroup)paramView;
        int j = paramView.getChildCount();
        int i = 0;
        while (i < j)
        {
          findNamedViews(paramMap, paramView.getChildAt(i));
          i += 1;
        }
      }
    }
  }
  
  private static Rect getBoundsOnScreen(View paramView)
  {
    Rect localRect = new Rect();
    int[] arrayOfInt = new int[2];
    paramView.getLocationOnScreen(arrayOfInt);
    localRect.set(arrayOfInt[0], arrayOfInt[1], arrayOfInt[0] + paramView.getWidth(), arrayOfInt[1] + paramView.getHeight());
    return localRect;
  }
  
  public static String getTransitionName(View paramView)
  {
    return paramView.getTransitionName();
  }
  
  private static boolean hasSimpleTarget(Transition paramTransition)
  {
    return (!isNullOrEmpty(paramTransition.getTargetIds())) || (!isNullOrEmpty(paramTransition.getTargetNames())) || (!isNullOrEmpty(paramTransition.getTargetTypes()));
  }
  
  private static boolean isNullOrEmpty(List paramList)
  {
    return (paramList == null) || (paramList.isEmpty());
  }
  
  public static Object mergeTransitions(Object paramObject1, Object paramObject2, Object paramObject3, boolean paramBoolean)
  {
    Transition localTransition = (Transition)paramObject1;
    paramObject1 = (Transition)paramObject2;
    paramObject3 = (Transition)paramObject3;
    if ((localTransition != null) && (paramObject1 != null)) {}
    for (;;)
    {
      if (paramBoolean)
      {
        paramObject2 = new TransitionSet();
        if (localTransition != null) {
          ((TransitionSet)paramObject2).addTransition(localTransition);
        }
        if (paramObject1 != null) {
          ((TransitionSet)paramObject2).addTransition((Transition)paramObject1);
        }
        if (paramObject3 != null) {
          ((TransitionSet)paramObject2).addTransition((Transition)paramObject3);
        }
        return paramObject2;
      }
      paramObject2 = null;
      if ((paramObject1 != null) && (localTransition != null)) {
        paramObject1 = new TransitionSet().addTransition((Transition)paramObject1).addTransition(localTransition).setOrdering(1);
      }
      while (paramObject3 != null)
      {
        paramObject2 = new TransitionSet();
        if (paramObject1 != null) {
          ((TransitionSet)paramObject2).addTransition((Transition)paramObject1);
        }
        ((TransitionSet)paramObject2).addTransition((Transition)paramObject3);
        return paramObject2;
        if (paramObject1 == null)
        {
          paramObject1 = paramObject2;
          if (localTransition != null) {
            paramObject1 = localTransition;
          }
        }
      }
      return paramObject1;
      paramBoolean = true;
    }
  }
  
  public static void removeTargets(Object paramObject, ArrayList<View> paramArrayList)
  {
    paramObject = (Transition)paramObject;
    int i;
    if ((paramObject instanceof TransitionSet))
    {
      paramObject = (TransitionSet)paramObject;
      int j = ((TransitionSet)paramObject).getTransitionCount();
      i = 0;
      while (i < j)
      {
        removeTargets(((TransitionSet)paramObject).getTransitionAt(i), paramArrayList);
        i += 1;
      }
    }
    if (!hasSimpleTarget((Transition)paramObject))
    {
      List localList = ((Transition)paramObject).getTargets();
      if ((localList != null) && (localList.size() == paramArrayList.size()) && (localList.containsAll(paramArrayList)))
      {
        i = paramArrayList.size() - 1;
        while (i >= 0)
        {
          ((Transition)paramObject).removeTarget((View)paramArrayList.get(i));
          i -= 1;
        }
      }
    }
  }
  
  public static void setEpicenter(Object paramObject, View paramView)
  {
    ((Transition)paramObject).setEpicenterCallback(new FragmentTransitionCompat21.1(getBoundsOnScreen(paramView)));
  }
  
  private static void setSharedElementEpicenter(Transition paramTransition, EpicenterView paramEpicenterView)
  {
    if (paramTransition != null) {
      paramTransition.setEpicenterCallback(new FragmentTransitionCompat21.3(paramEpicenterView));
    }
  }
  
  public static class EpicenterView
  {
    public View epicenter;
  }
  
  public static abstract interface ViewRetriever
  {
    public abstract View getView();
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.FragmentTransitionCompat21
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */