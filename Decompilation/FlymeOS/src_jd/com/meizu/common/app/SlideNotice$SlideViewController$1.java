package com.meizu.common.app;

import android.view.View;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.WindowManager.LayoutParams;
import java.lang.ref.WeakReference;

class SlideNotice$SlideViewController$1
  implements ViewTreeObserver.OnScrollChangedListener
{
  SlideNotice$SlideViewController$1(SlideNotice.SlideViewController paramSlideViewController) {}
  
  public void onScrollChanged()
  {
    if (SlideNotice.SlideViewController.access$100(this$1) != null) {}
    for (View localView = (View)SlideNotice.SlideViewController.access$100(this$1).get();; localView = null)
    {
      if ((localView != null) && (localView.getParent() != null) && (SlideNotice.SlideViewController.access$200(this$1) != null))
      {
        WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
        SlideNotice.SlideViewController.access$300(this$1, localView, localLayoutParams);
        SlideNotice.SlideViewController.access$400(this$1, x, y);
      }
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.app.SlideNotice.SlideViewController.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */