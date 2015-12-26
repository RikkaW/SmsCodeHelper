package com.meizu.common.app;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;

class SlideNotice$SlideViewController$5
  implements ViewTreeObserver.OnPreDrawListener
{
  SlideNotice$SlideViewController$5(SlideNotice.SlideViewController paramSlideViewController) {}
  
  public boolean onPreDraw()
  {
    SlideNotice.SlideViewController.access$200(this$1).getViewTreeObserver().removeOnPreDrawListener(this);
    SlideNotice.SlideViewController.access$702(this$1, SlideNotice.SlideViewController.access$800(this$1).getMeasuredHeight());
    SlideNotice.SlideViewController.access$900(this$1).start(0, SlideNotice.SlideViewController.access$700(this$1));
    SlideNotice.SlideViewController.access$1002(this$1, 0);
    return false;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.app.SlideNotice.SlideViewController.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */