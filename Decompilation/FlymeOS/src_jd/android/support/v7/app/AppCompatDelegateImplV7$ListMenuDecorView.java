package android.support.v7.app;

import android.content.Context;
import android.support.v7.internal.widget.TintManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.FrameLayout;

class AppCompatDelegateImplV7$ListMenuDecorView
  extends FrameLayout
{
  public AppCompatDelegateImplV7$ListMenuDecorView(AppCompatDelegateImplV7 paramAppCompatDelegateImplV7, Context paramContext)
  {
    super(paramContext);
  }
  
  private boolean isOutOfBounds(int paramInt1, int paramInt2)
  {
    return (paramInt1 < -5) || (paramInt2 < -5) || (paramInt1 > getWidth() + 5) || (paramInt2 > getHeight() + 5);
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    return this$0.dispatchKeyEvent(paramKeyEvent);
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 0) && (isOutOfBounds((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY())))
    {
      AppCompatDelegateImplV7.access$1200(this$0, 0);
      return true;
    }
    return super.onInterceptTouchEvent(paramMotionEvent);
  }
  
  public void setBackgroundResource(int paramInt)
  {
    setBackgroundDrawable(TintManager.getDrawable(getContext(), paramInt));
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AppCompatDelegateImplV7.ListMenuDecorView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */