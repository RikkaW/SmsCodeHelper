package com.meizu.common.widget;

import android.animation.Animator;
import android.graphics.RectF;
import android.os.Handler;
import android.view.View;

class PullRefreshAnimationView$1
  implements Runnable
{
  PullRefreshAnimationView$1(PullRefreshAnimationView paramPullRefreshAnimationView) {}
  
  public void run()
  {
    if ((PullRefreshAnimationView.access$000(this$0)) || (PullRefreshAnimationView.access$100(this$0)))
    {
      if (!PullRefreshAnimationView.access$100(this$0)) {
        break label142;
      }
      PullRefreshAnimationView.access$300(this$0).postInvalidate(0, PullRefreshAnimationView.access$200(this$0), PullRefreshAnimationView.access$300(this$0).getWidth(), (int)PullRefreshAnimationView.access$400(this$0) + PullRefreshAnimationView.access$200(this$0));
      PullRefreshAnimationView.access$700(this$0).postDelayed(this, PullRefreshAnimationView.access$500(this$0) / PullRefreshAnimationView.access$600(this$0));
    }
    for (;;)
    {
      if ((!PullRefreshAnimationView.access$000(this$0)) && (PullRefreshAnimationView.access$1300(this$0) != null))
      {
        PullRefreshAnimationView.access$1300(this$0).cancel();
        PullRefreshAnimationView.access$1302(this$0, null);
      }
      return;
      label142:
      if (PullRefreshAnimationView.access$000(this$0))
      {
        PullRefreshAnimationView.access$300(this$0).postInvalidate((int)(PullRefreshAnimationView.access$300(this$0).getWidth() / 2 - PullRefreshAnimationView.access$800(this$0) - PullRefreshAnimationView.access$900(this$0) - 10.0F), (int)(access$1000this$0).top - 5.0F + PullRefreshAnimationView.access$300(this$0).getScrollY() + PullRefreshAnimationView.access$1100(this$0)), (int)(PullRefreshAnimationView.access$300(this$0).getWidth() / 2 + PullRefreshAnimationView.access$800(this$0) + PullRefreshAnimationView.access$900(this$0) + 10.0F), (int)(access$1000this$0).bottom + 5.0F + PullRefreshAnimationView.access$300(this$0).getScrollY() + PullRefreshAnimationView.access$1100(this$0)));
        PullRefreshAnimationView.access$700(this$0).postDelayed(this, PullRefreshAnimationView.access$1200(this$0) / PullRefreshAnimationView.access$600(this$0));
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.PullRefreshAnimationView.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */