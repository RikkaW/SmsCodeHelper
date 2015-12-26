package com.android.mms.ui;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.model.SlideshowModel;

class SlideEditorActivity$8
  implements View.OnClickListener
{
  SlideEditorActivity$8(SlideEditorActivity paramSlideEditorActivity) {}
  
  public void onClick(View paramView)
  {
    if (!SlideEditorActivity.access$000(this$0)) {
      Log.v("SlideEditorActivity", "mDataLoaded is false");
    }
    while (SlideEditorActivity.access$800(this$0) >= SlideEditorActivity.access$100(this$0).size() - 1) {
      return;
    }
    SlideEditorActivity.access$808(this$0);
    SlideEditorActivity.access$900(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideEditorActivity.8
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */