package com.meizu.common.widget;

import android.content.Context;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;

class ScrollTextView$ScrollTextViewScroller
{
  public static final int MIN_DELTA_FOR_SCROLLING = 1;
  private static final int SCROLLING_DURATION = 400;
  private final int MESSAGE_JUSTIFY = 1;
  private final int MESSAGE_SCROLL = 0;
  private Handler animationHandler = new ScrollTextView.AnimationHandler(this);
  private Context context;
  private GestureDetector gestureDetector = new GestureDetector(paramContext, gestureListener);
  private GestureDetector.SimpleOnGestureListener gestureListener = new ScrollTextView.ScrollTextViewScroller.1(this);
  private boolean isScrollingPerformed;
  private int lastScrollY;
  private float lastTouchedY;
  private ScrollTextView.ScrollingListener listener;
  private Scroller scroller;
  
  public ScrollTextView$ScrollTextViewScroller(ScrollTextView paramScrollTextView, Context paramContext, ScrollTextView.ScrollingListener paramScrollingListener)
  {
    gestureDetector.setIsLongpressEnabled(false);
    scroller = new Scroller(paramContext);
    listener = paramScrollingListener;
    context = paramContext;
  }
  
  private void clearMessages()
  {
    animationHandler.removeMessages(0);
    animationHandler.removeMessages(1);
  }
  
  private void justify()
  {
    listener.onJustify();
    setNextMessage(1);
  }
  
  private void setNextMessage(int paramInt)
  {
    clearMessages();
    animationHandler.sendEmptyMessage(paramInt);
  }
  
  private void startScrolling()
  {
    if (!isScrollingPerformed)
    {
      isScrollingPerformed = true;
      listener.onStarted();
    }
  }
  
  void finishScrolling()
  {
    if (isScrollingPerformed)
    {
      listener.onFinished();
      isScrollingPerformed = false;
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    }
    for (;;)
    {
      if ((!gestureDetector.onTouchEvent(paramMotionEvent)) && (paramMotionEvent.getAction() == 1)) {
        justify();
      }
      return true;
      lastTouchedY = paramMotionEvent.getY();
      scroller.forceFinished(true);
      clearMessages();
      continue;
      int i = (int)(paramMotionEvent.getY() - lastTouchedY);
      if (i != 0)
      {
        startScrolling();
        listener.onScroll(i);
        lastTouchedY = paramMotionEvent.getY();
      }
    }
  }
  
  public void scroll(int paramInt1, int paramInt2)
  {
    scroller.forceFinished(true);
    lastScrollY = 0;
    Scroller localScroller = scroller;
    if (paramInt2 != 0) {}
    for (;;)
    {
      localScroller.startScroll(0, 0, 0, paramInt1, paramInt2);
      setNextMessage(0);
      startScrolling();
      return;
      paramInt2 = 400;
    }
  }
  
  public void setInterpolator(Interpolator paramInterpolator)
  {
    scroller.forceFinished(true);
    scroller = new Scroller(context, paramInterpolator);
  }
  
  public void stopScrolling()
  {
    scroller.forceFinished(true);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.ScrollTextView.ScrollTextViewScroller
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */