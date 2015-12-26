package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

class MultiWaveView$Tweener
{
  private static final boolean DEBUG = false;
  private static final String TAG = "Tweener";
  private static Animator.AnimatorListener mCleanupListener = new MultiWaveView.Tweener.1();
  private static HashMap<Object, Tweener> sTweens = new HashMap();
  ObjectAnimator animator;
  
  public MultiWaveView$Tweener(ObjectAnimator paramObjectAnimator)
  {
    animator = paramObjectAnimator;
  }
  
  private static void remove(Animator paramAnimator)
  {
    Iterator localIterator = sTweens.entrySet().iterator();
    while (localIterator.hasNext()) {
      if (nextgetValueanimator == paramAnimator) {
        localIterator.remove();
      }
    }
  }
  
  private static void replace(ArrayList<PropertyValuesHolder> paramArrayList, Object... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    if (i < j)
    {
      Object localObject = paramVarArgs[i];
      localObject = (Tweener)sTweens.get(localObject);
      if (localObject != null)
      {
        animator.cancel();
        if (paramArrayList == null) {
          break label74;
        }
        animator.setValues((PropertyValuesHolder[])paramArrayList.toArray(new PropertyValuesHolder[paramArrayList.size()]));
      }
      for (;;)
      {
        i += 1;
        break;
        label74:
        sTweens.remove(localObject);
      }
    }
  }
  
  public static void reset()
  {
    sTweens.clear();
  }
  
  public static Tweener to(Object paramObject, long paramLong, Object... paramVarArgs)
  {
    long l = 0L;
    Object localObject1 = null;
    Object localObject2 = null;
    Object localObject3 = null;
    ArrayList localArrayList = new ArrayList(paramVarArgs.length / 2);
    int i = 0;
    if (i < paramVarArgs.length)
    {
      if (!(paramVarArgs[i] instanceof String)) {
        throw new IllegalArgumentException("Key must be a string: " + paramVarArgs[i]);
      }
      localObject4 = (String)paramVarArgs[i];
      Object localObject5 = paramVarArgs[(i + 1)];
      if ("simultaneousTween".equals(localObject4))
      {
        localObject4 = localObject3;
        localObject3 = localObject1;
        localObject1 = localObject4;
      }
      for (;;)
      {
        i += 2;
        localObject4 = localObject3;
        localObject3 = localObject1;
        localObject1 = localObject4;
        break;
        if ("ease".equals(localObject4))
        {
          localObject4 = (TimeInterpolator)localObject5;
          localObject3 = localObject1;
          localObject1 = localObject4;
        }
        else if (("onUpdate".equals(localObject4)) || ("onUpdateListener".equals(localObject4)))
        {
          localObject4 = (ValueAnimator.AnimatorUpdateListener)localObject5;
          localObject1 = localObject3;
          localObject3 = localObject4;
        }
        else if (("onComplete".equals(localObject4)) || ("onCompleteListener".equals(localObject4)))
        {
          localObject4 = (Animator.AnimatorListener)localObject5;
          localObject2 = localObject3;
          localObject3 = localObject1;
          localObject1 = localObject2;
          localObject2 = localObject4;
        }
        else if ("delay".equals(localObject4))
        {
          l = ((Number)localObject5).longValue();
          localObject4 = localObject1;
          localObject1 = localObject3;
          localObject3 = localObject4;
        }
        else if ("syncWith".equals(localObject4))
        {
          localObject4 = localObject1;
          localObject1 = localObject3;
          localObject3 = localObject4;
        }
        else if ((localObject5 instanceof float[]))
        {
          localArrayList.add(PropertyValuesHolder.ofFloat((String)localObject4, new float[] { ((float[])(float[])localObject5)[0], ((float[])(float[])localObject5)[1] }));
          localObject4 = localObject1;
          localObject1 = localObject3;
          localObject3 = localObject4;
        }
        else if ((localObject5 instanceof int[]))
        {
          localArrayList.add(PropertyValuesHolder.ofInt((String)localObject4, new int[] { ((int[])(int[])localObject5)[0], ((int[])(int[])localObject5)[1] }));
          localObject4 = localObject1;
          localObject1 = localObject3;
          localObject3 = localObject4;
        }
        else
        {
          if (!(localObject5 instanceof Number)) {
            break label481;
          }
          localArrayList.add(PropertyValuesHolder.ofFloat((String)localObject4, new float[] { ((Number)localObject5).floatValue() }));
          localObject4 = localObject1;
          localObject1 = localObject3;
          localObject3 = localObject4;
        }
      }
      label481:
      throw new IllegalArgumentException("Bad argument for key \"" + (String)localObject4 + "\" with value " + localObject5.getClass());
    }
    Object localObject4 = (Tweener)sTweens.get(paramObject);
    if (localObject4 == null)
    {
      paramVarArgs = ObjectAnimator.ofPropertyValuesHolder(paramObject, (PropertyValuesHolder[])localArrayList.toArray(new PropertyValuesHolder[localArrayList.size()]));
      localObject4 = new Tweener(paramVarArgs);
      sTweens.put(paramObject, localObject4);
    }
    for (paramObject = localObject4;; paramObject = localObject4)
    {
      if (localObject3 != null) {
        paramVarArgs.setInterpolator((TimeInterpolator)localObject3);
      }
      paramVarArgs.setStartDelay(l);
      paramVarArgs.setDuration(paramLong);
      if (localObject1 != null)
      {
        paramVarArgs.removeAllUpdateListeners();
        paramVarArgs.addUpdateListener((ValueAnimator.AnimatorUpdateListener)localObject1);
      }
      if (localObject2 != null)
      {
        paramVarArgs.removeAllListeners();
        paramVarArgs.addListener((Animator.AnimatorListener)localObject2);
      }
      paramVarArgs.addListener(mCleanupListener);
      return (Tweener)paramObject;
      paramVarArgs = sTweensgetanimator;
      replace(localArrayList, new Object[] { paramObject });
    }
  }
  
  Tweener from(Object paramObject, long paramLong, Object... paramVarArgs)
  {
    return to(paramObject, paramLong, paramVarArgs);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MultiWaveView.Tweener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */