package com.android.mms.ui;

import com.android.mms.model.IModelChangedObserver;
import com.android.mms.model.Model;

class SlideshowEditActivity$1
  implements IModelChangedObserver
{
  SlideshowEditActivity$1(SlideshowEditActivity paramSlideshowEditActivity) {}
  
  public void onModelChanged(Model arg1, boolean paramBoolean)
  {
    synchronized (this$0)
    {
      SlideshowEditActivity.access$002(this$0, true);
      this$0.setResult(-1, SlideshowEditActivity.access$100(this$0));
      SlideshowEditActivity.access$200(this$0);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowEditActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */