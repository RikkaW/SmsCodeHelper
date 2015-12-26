package android.support.v4.view;

import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

class ViewPropertyAnimatorCompat$BaseViewPropertyAnimatorCompatImpl
  implements ViewPropertyAnimatorCompat.ViewPropertyAnimatorCompatImpl
{
  WeakHashMap<View, Runnable> mStarterMap = null;
  
  private void postStartMessage(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
  {
    if (mStarterMap != null) {}
    for (Runnable localRunnable = (Runnable)mStarterMap.get(paramView);; localRunnable = null)
    {
      Object localObject = localRunnable;
      if (localRunnable == null)
      {
        localObject = new Starter(paramViewPropertyAnimatorCompat, paramView, null);
        if (mStarterMap == null) {
          mStarterMap = new WeakHashMap();
        }
        mStarterMap.put(paramView, localObject);
      }
      paramView.removeCallbacks((Runnable)localObject);
      paramView.post((Runnable)localObject);
      return;
    }
  }
  
  private void removeStartMessage(View paramView)
  {
    if (mStarterMap != null)
    {
      Runnable localRunnable = (Runnable)mStarterMap.get(paramView);
      if (localRunnable != null) {
        paramView.removeCallbacks(localRunnable);
      }
    }
  }
  
  private void startAnimation(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
  {
    Object localObject = paramView.getTag(2113929216);
    if ((localObject instanceof ViewPropertyAnimatorListener)) {}
    for (localObject = (ViewPropertyAnimatorListener)localObject;; localObject = null)
    {
      Runnable localRunnable = ViewPropertyAnimatorCompat.access$100(paramViewPropertyAnimatorCompat);
      paramViewPropertyAnimatorCompat = ViewPropertyAnimatorCompat.access$000(paramViewPropertyAnimatorCompat);
      if (localRunnable != null) {
        localRunnable.run();
      }
      if (localObject != null)
      {
        ((ViewPropertyAnimatorListener)localObject).onAnimationStart(paramView);
        ((ViewPropertyAnimatorListener)localObject).onAnimationEnd(paramView);
      }
      if (paramViewPropertyAnimatorCompat != null) {
        paramViewPropertyAnimatorCompat.run();
      }
      if (mStarterMap != null) {
        mStarterMap.remove(paramView);
      }
      return;
    }
  }
  
  public void alpha(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    postStartMessage(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public void alphaBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    postStartMessage(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public void cancel(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
  {
    postStartMessage(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public long getDuration(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
  {
    return 0L;
  }
  
  public Interpolator getInterpolator(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
  {
    return null;
  }
  
  public long getStartDelay(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
  {
    return 0L;
  }
  
  public void rotation(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    postStartMessage(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public void rotationBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    postStartMessage(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public void rotationX(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    postStartMessage(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public void rotationXBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    postStartMessage(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public void rotationY(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    postStartMessage(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public void rotationYBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    postStartMessage(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public void scaleX(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    postStartMessage(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public void scaleXBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    postStartMessage(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public void scaleY(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    postStartMessage(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public void scaleYBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    postStartMessage(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public void setDuration(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, long paramLong) {}
  
  public void setInterpolator(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, Interpolator paramInterpolator) {}
  
  public void setListener(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, ViewPropertyAnimatorListener paramViewPropertyAnimatorListener)
  {
    paramView.setTag(2113929216, paramViewPropertyAnimatorListener);
  }
  
  public void setStartDelay(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, long paramLong) {}
  
  public void setUpdateListener(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, ViewPropertyAnimatorUpdateListener paramViewPropertyAnimatorUpdateListener) {}
  
  public void start(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
  {
    removeStartMessage(paramView);
    startAnimation(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public void translationX(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    postStartMessage(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public void translationXBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    postStartMessage(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public void translationY(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    postStartMessage(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public void translationYBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    postStartMessage(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public void translationZ(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat) {}
  
  public void translationZBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat) {}
  
  public void withEndAction(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, Runnable paramRunnable)
  {
    ViewPropertyAnimatorCompat.access$002(paramViewPropertyAnimatorCompat, paramRunnable);
    postStartMessage(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public void withLayer(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView) {}
  
  public void withStartAction(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, Runnable paramRunnable)
  {
    ViewPropertyAnimatorCompat.access$102(paramViewPropertyAnimatorCompat, paramRunnable);
    postStartMessage(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public void x(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    postStartMessage(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public void xBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    postStartMessage(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public void y(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    postStartMessage(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public void yBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    postStartMessage(paramViewPropertyAnimatorCompat, paramView);
  }
  
  public void z(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat) {}
  
  public void zBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat) {}
  
  class Starter
    implements Runnable
  {
    WeakReference<View> mViewRef;
    ViewPropertyAnimatorCompat mVpa;
    
    private Starter(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
    {
      mViewRef = new WeakReference(paramView);
      mVpa = paramViewPropertyAnimatorCompat;
    }
    
    public void run()
    {
      View localView = (View)mViewRef.get();
      if (localView != null) {
        ViewPropertyAnimatorCompat.BaseViewPropertyAnimatorCompatImpl.this.startAnimation(mVpa, localView);
      }
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.ViewPropertyAnimatorCompat.BaseViewPropertyAnimatorCompatImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */