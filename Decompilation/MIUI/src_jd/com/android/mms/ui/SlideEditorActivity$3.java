package com.android.mms.ui;

import com.android.mms.model.IModelChangedObserver;
import com.android.mms.model.Model;

class SlideEditorActivity$3
  implements IModelChangedObserver
{
  SlideEditorActivity$3(SlideEditorActivity paramSlideEditorActivity) {}
  
  public void onModelChanged(Model arg1, boolean paramBoolean)
  {
    synchronized (this$0)
    {
      SlideEditorActivity.access$1202(this$0, true);
      this$0.setResult(-1);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideEditorActivity.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */