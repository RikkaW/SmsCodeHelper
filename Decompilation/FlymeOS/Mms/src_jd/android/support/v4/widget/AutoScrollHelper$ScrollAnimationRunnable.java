package android.support.v4.widget;

import android.support.v4.view.ViewCompat;

class AutoScrollHelper$ScrollAnimationRunnable
  implements Runnable
{
  private AutoScrollHelper$ScrollAnimationRunnable(AutoScrollHelper paramAutoScrollHelper) {}
  
  public void run()
  {
    if (!AutoScrollHelper.access$100(this$0)) {
      return;
    }
    if (AutoScrollHelper.access$200(this$0))
    {
      AutoScrollHelper.access$202(this$0, false);
      AutoScrollHelper.access$300(this$0).start();
    }
    AutoScrollHelper.ClampedScroller localClampedScroller = AutoScrollHelper.access$300(this$0);
    if ((localClampedScroller.isFinished()) || (!AutoScrollHelper.access$400(this$0)))
    {
      AutoScrollHelper.access$102(this$0, false);
      return;
    }
    if (AutoScrollHelper.access$500(this$0))
    {
      AutoScrollHelper.access$502(this$0, false);
      AutoScrollHelper.access$600(this$0);
    }
    localClampedScroller.computeScrollDelta();
    int i = localClampedScroller.getDeltaX();
    int j = localClampedScroller.getDeltaY();
    this$0.scrollTargetBy(i, j);
    ViewCompat.postOnAnimation(AutoScrollHelper.access$700(this$0), this);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.AutoScrollHelper.ScrollAnimationRunnable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */