package com.meizu.common.widget;

class ScrollTextView$1
  implements ScrollTextView.ScrollingListener
{
  ScrollTextView$1(ScrollTextView paramScrollTextView) {}
  
  public void onFinished()
  {
    if (ScrollTextView.access$000(this$0))
    {
      this$0.notifyScrollingListenersAboutEnd();
      ScrollTextView.access$002(this$0, false);
    }
    ScrollTextView.access$202(this$0, 0);
    this$0.invalidate();
  }
  
  public void onJustify()
  {
    if ((!this$0.isCyclic) && (this$0.getCurrentItem() < ScrollTextView.access$400(this$0).getValidStart())) {
      this$0.scroll(ScrollTextView.access$400(this$0).getValidStart() - this$0.getCurrentItem(), 0);
    }
    do
    {
      return;
      if ((!this$0.isCyclic) && (this$0.getCurrentItem() > ScrollTextView.access$400(this$0).getValidEnd()))
      {
        this$0.scroll(ScrollTextView.access$400(this$0).getValidEnd() - this$0.getCurrentItem(), 0);
        return;
      }
    } while (Math.abs(ScrollTextView.access$200(this$0)) <= 1);
    ScrollTextView.access$300(this$0).scroll(ScrollTextView.access$200(this$0), 0);
  }
  
  public void onScroll(int paramInt)
  {
    ScrollTextView.access$100(this$0, paramInt);
    paramInt = this$0.getHeight();
    if (ScrollTextView.access$200(this$0) > paramInt)
    {
      ScrollTextView.access$202(this$0, paramInt);
      ScrollTextView.access$300(this$0).stopScrolling();
    }
    while (ScrollTextView.access$200(this$0) >= -paramInt) {
      return;
    }
    ScrollTextView.access$202(this$0, -paramInt);
    ScrollTextView.access$300(this$0).stopScrolling();
  }
  
  public void onStarted()
  {
    ScrollTextView.access$002(this$0, true);
    this$0.notifyScrollingListenersAboutStart();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.ScrollTextView.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */