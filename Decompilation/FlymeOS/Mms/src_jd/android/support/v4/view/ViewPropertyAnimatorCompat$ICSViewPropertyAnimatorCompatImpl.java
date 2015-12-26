package android.support.v4.view;

import android.view.View;
import android.view.animation.Interpolator;
import java.util.WeakHashMap;

class ViewPropertyAnimatorCompat$ICSViewPropertyAnimatorCompatImpl
  extends ViewPropertyAnimatorCompat.BaseViewPropertyAnimatorCompatImpl
{
  WeakHashMap<View, Integer> mLayerMap = null;
  
  public void alpha(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    ViewPropertyAnimatorCompatICS.alpha(paramView, paramFloat);
  }
  
  public void alphaBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    ViewPropertyAnimatorCompatICS.alphaBy(paramView, paramFloat);
  }
  
  public void cancel(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
  {
    ViewPropertyAnimatorCompatICS.cancel(paramView);
  }
  
  public long getDuration(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
  {
    return ViewPropertyAnimatorCompatICS.getDuration(paramView);
  }
  
  public long getStartDelay(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
  {
    return ViewPropertyAnimatorCompatICS.getStartDelay(paramView);
  }
  
  public void rotation(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    ViewPropertyAnimatorCompatICS.rotation(paramView, paramFloat);
  }
  
  public void rotationBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    ViewPropertyAnimatorCompatICS.rotationBy(paramView, paramFloat);
  }
  
  public void rotationX(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    ViewPropertyAnimatorCompatICS.rotationX(paramView, paramFloat);
  }
  
  public void rotationXBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    ViewPropertyAnimatorCompatICS.rotationXBy(paramView, paramFloat);
  }
  
  public void rotationY(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    ViewPropertyAnimatorCompatICS.rotationY(paramView, paramFloat);
  }
  
  public void rotationYBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    ViewPropertyAnimatorCompatICS.rotationYBy(paramView, paramFloat);
  }
  
  public void scaleX(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    ViewPropertyAnimatorCompatICS.scaleX(paramView, paramFloat);
  }
  
  public void scaleXBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    ViewPropertyAnimatorCompatICS.scaleXBy(paramView, paramFloat);
  }
  
  public void scaleY(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    ViewPropertyAnimatorCompatICS.scaleY(paramView, paramFloat);
  }
  
  public void scaleYBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    ViewPropertyAnimatorCompatICS.scaleYBy(paramView, paramFloat);
  }
  
  public void setDuration(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, long paramLong)
  {
    ViewPropertyAnimatorCompatICS.setDuration(paramView, paramLong);
  }
  
  public void setInterpolator(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, Interpolator paramInterpolator)
  {
    ViewPropertyAnimatorCompatICS.setInterpolator(paramView, paramInterpolator);
  }
  
  public void setListener(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, ViewPropertyAnimatorListener paramViewPropertyAnimatorListener)
  {
    paramView.setTag(2113929216, paramViewPropertyAnimatorListener);
    ViewPropertyAnimatorCompatICS.setListener(paramView, new MyVpaListener(paramViewPropertyAnimatorCompat));
  }
  
  public void setStartDelay(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, long paramLong)
  {
    ViewPropertyAnimatorCompatICS.setStartDelay(paramView, paramLong);
  }
  
  public void start(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
  {
    ViewPropertyAnimatorCompatICS.start(paramView);
  }
  
  public void translationX(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    ViewPropertyAnimatorCompatICS.translationX(paramView, paramFloat);
  }
  
  public void translationXBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    ViewPropertyAnimatorCompatICS.translationXBy(paramView, paramFloat);
  }
  
  public void translationY(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    ViewPropertyAnimatorCompatICS.translationY(paramView, paramFloat);
  }
  
  public void translationYBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    ViewPropertyAnimatorCompatICS.translationYBy(paramView, paramFloat);
  }
  
  public void withEndAction(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, Runnable paramRunnable)
  {
    ViewPropertyAnimatorCompatICS.setListener(paramView, new MyVpaListener(paramViewPropertyAnimatorCompat));
    ViewPropertyAnimatorCompat.access$002(paramViewPropertyAnimatorCompat, paramRunnable);
  }
  
  public void withLayer(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
  {
    ViewPropertyAnimatorCompat.access$402(paramViewPropertyAnimatorCompat, ViewCompat.getLayerType(paramView));
    ViewPropertyAnimatorCompatICS.setListener(paramView, new MyVpaListener(paramViewPropertyAnimatorCompat));
  }
  
  public void withStartAction(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, Runnable paramRunnable)
  {
    ViewPropertyAnimatorCompatICS.setListener(paramView, new MyVpaListener(paramViewPropertyAnimatorCompat));
    ViewPropertyAnimatorCompat.access$102(paramViewPropertyAnimatorCompat, paramRunnable);
  }
  
  public void x(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    ViewPropertyAnimatorCompatICS.x(paramView, paramFloat);
  }
  
  public void xBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    ViewPropertyAnimatorCompatICS.xBy(paramView, paramFloat);
  }
  
  public void y(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    ViewPropertyAnimatorCompatICS.y(paramView, paramFloat);
  }
  
  public void yBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
  {
    ViewPropertyAnimatorCompatICS.yBy(paramView, paramFloat);
  }
  
  static class MyVpaListener
    implements ViewPropertyAnimatorListener
  {
    ViewPropertyAnimatorCompat mVpa;
    
    MyVpaListener(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat)
    {
      mVpa = paramViewPropertyAnimatorCompat;
    }
    
    public void onAnimationCancel(View paramView)
    {
      Object localObject = paramView.getTag(2113929216);
      if ((localObject instanceof ViewPropertyAnimatorListener)) {}
      for (localObject = (ViewPropertyAnimatorListener)localObject;; localObject = null)
      {
        if (localObject != null) {
          ((ViewPropertyAnimatorListener)localObject).onAnimationCancel(paramView);
        }
        return;
      }
    }
    
    public void onAnimationEnd(View paramView)
    {
      if (ViewPropertyAnimatorCompat.access$400(mVpa) >= 0)
      {
        ViewCompat.setLayerType(paramView, ViewPropertyAnimatorCompat.access$400(mVpa), null);
        ViewPropertyAnimatorCompat.access$402(mVpa, -1);
      }
      if (ViewPropertyAnimatorCompat.access$000(mVpa) != null) {
        ViewPropertyAnimatorCompat.access$000(mVpa).run();
      }
      Object localObject = paramView.getTag(2113929216);
      if ((localObject instanceof ViewPropertyAnimatorListener)) {}
      for (localObject = (ViewPropertyAnimatorListener)localObject;; localObject = null)
      {
        if (localObject != null) {
          ((ViewPropertyAnimatorListener)localObject).onAnimationEnd(paramView);
        }
        return;
      }
    }
    
    public void onAnimationStart(View paramView)
    {
      if (ViewPropertyAnimatorCompat.access$400(mVpa) >= 0) {
        ViewCompat.setLayerType(paramView, 2, null);
      }
      if (ViewPropertyAnimatorCompat.access$100(mVpa) != null) {
        ViewPropertyAnimatorCompat.access$100(mVpa).run();
      }
      Object localObject = paramView.getTag(2113929216);
      if ((localObject instanceof ViewPropertyAnimatorListener)) {}
      for (localObject = (ViewPropertyAnimatorListener)localObject;; localObject = null)
      {
        if (localObject != null) {
          ((ViewPropertyAnimatorListener)localObject).onAnimationStart(paramView);
        }
        return;
      }
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.ViewPropertyAnimatorCompat.ICSViewPropertyAnimatorCompatImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */