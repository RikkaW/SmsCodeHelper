package com.meizu.common.widget;

import android.os.Handler;
import android.os.Message;
import android.widget.Scroller;
import java.lang.ref.WeakReference;

class ScrollTextView$AnimationHandler
  extends Handler
{
  private final WeakReference<ScrollTextView.ScrollTextViewScroller> mScrollTextViewScroller;
  
  public ScrollTextView$AnimationHandler(ScrollTextView.ScrollTextViewScroller paramScrollTextViewScroller)
  {
    mScrollTextViewScroller = new WeakReference(paramScrollTextViewScroller);
  }
  
  public void handleMessage(Message paramMessage)
  {
    ScrollTextView.ScrollTextViewScroller localScrollTextViewScroller = (ScrollTextView.ScrollTextViewScroller)mScrollTextViewScroller.get();
    if (localScrollTextViewScroller != null)
    {
      ScrollTextView.ScrollTextViewScroller.access$900(localScrollTextViewScroller).computeScrollOffset();
      int i = ScrollTextView.ScrollTextViewScroller.access$900(localScrollTextViewScroller).getCurrY();
      int j = ScrollTextView.ScrollTextViewScroller.access$600(localScrollTextViewScroller) - i;
      ScrollTextView.ScrollTextViewScroller.access$602(localScrollTextViewScroller, i);
      if (j != 0) {
        ScrollTextView.ScrollTextViewScroller.access$1100(localScrollTextViewScroller).onScroll(j);
      }
      if (Math.abs(i - ScrollTextView.ScrollTextViewScroller.access$900(localScrollTextViewScroller).getFinalY()) < 1)
      {
        ScrollTextView.ScrollTextViewScroller.access$900(localScrollTextViewScroller).getFinalY();
        ScrollTextView.ScrollTextViewScroller.access$900(localScrollTextViewScroller).forceFinished(true);
      }
      if (!ScrollTextView.ScrollTextViewScroller.access$900(localScrollTextViewScroller).isFinished()) {
        ScrollTextView.ScrollTextViewScroller.access$1200(localScrollTextViewScroller).sendEmptyMessage(what);
      }
    }
    else
    {
      return;
    }
    if (what == 0)
    {
      ScrollTextView.ScrollTextViewScroller.access$1300(localScrollTextViewScroller);
      return;
    }
    localScrollTextViewScroller.finishScrolling();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.ScrollTextView.AnimationHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */