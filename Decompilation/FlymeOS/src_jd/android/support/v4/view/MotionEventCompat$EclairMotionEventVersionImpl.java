package android.support.v4.view;

import android.view.MotionEvent;

class MotionEventCompat$EclairMotionEventVersionImpl
  extends MotionEventCompat.BaseMotionEventVersionImpl
{
  public int findPointerIndex(MotionEvent paramMotionEvent, int paramInt)
  {
    return MotionEventCompatEclair.findPointerIndex(paramMotionEvent, paramInt);
  }
  
  public int getPointerCount(MotionEvent paramMotionEvent)
  {
    return MotionEventCompatEclair.getPointerCount(paramMotionEvent);
  }
  
  public int getPointerId(MotionEvent paramMotionEvent, int paramInt)
  {
    return MotionEventCompatEclair.getPointerId(paramMotionEvent, paramInt);
  }
  
  public float getX(MotionEvent paramMotionEvent, int paramInt)
  {
    return MotionEventCompatEclair.getX(paramMotionEvent, paramInt);
  }
  
  public float getY(MotionEvent paramMotionEvent, int paramInt)
  {
    return MotionEventCompatEclair.getY(paramMotionEvent, paramInt);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.MotionEventCompat.EclairMotionEventVersionImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */