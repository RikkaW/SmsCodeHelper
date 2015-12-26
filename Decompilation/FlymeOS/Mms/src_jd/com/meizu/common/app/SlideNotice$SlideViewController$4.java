package com.meizu.common.app;

import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;

class SlideNotice$SlideViewController$4
  implements View.OnClickListener
{
  SlideNotice$SlideViewController$4(SlideNotice.SlideViewController paramSlideViewController) {}
  
  public void onClick(View paramView)
  {
    if (SlideNotice.SlideViewController.access$500(this$1) != null) {}
    for (paramView = Message.obtain(SlideNotice.SlideViewController.access$500(this$1));; paramView = null)
    {
      if (paramView != null) {
        paramView.sendToTarget();
      }
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.app.SlideNotice.SlideViewController.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */