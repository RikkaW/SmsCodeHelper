package android.support.v4.view;

import android.view.GestureDetector.OnDoubleTapListener;
import android.view.MotionEvent;

abstract interface GestureDetectorCompat$GestureDetectorCompatImpl
{
  public abstract boolean isLongpressEnabled();
  
  public abstract boolean onTouchEvent(MotionEvent paramMotionEvent);
  
  public abstract void setIsLongpressEnabled(boolean paramBoolean);
  
  public abstract void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener paramOnDoubleTapListener);
}

/* Location:
 * Qualified Name:     android.support.v4.view.GestureDetectorCompat.GestureDetectorCompatImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */