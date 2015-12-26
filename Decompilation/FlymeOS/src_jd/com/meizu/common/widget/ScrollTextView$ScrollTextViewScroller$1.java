package com.meizu.common.widget;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.Scroller;

class ScrollTextView$ScrollTextViewScroller$1
  extends GestureDetector.SimpleOnGestureListener
{
  ScrollTextView$ScrollTextViewScroller$1(ScrollTextView.ScrollTextViewScroller paramScrollTextViewScroller) {}
  
  public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    ScrollTextView.ScrollTextViewScroller.access$602(this$1, 0);
    int i = ScrollTextView.access$700(this$1.this$0);
    int j = ScrollTextView.access$800(this$1.this$0);
    ScrollTextView.ScrollTextViewScroller.access$900(this$1).fling(0, ScrollTextView.ScrollTextViewScroller.access$600(this$1), 0, (int)-paramFloat2, 0, 0, j, i);
    ScrollTextView.ScrollTextViewScroller.access$1000(this$1, 0);
    return true;
  }
  
  public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.ScrollTextView.ScrollTextViewScroller.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */