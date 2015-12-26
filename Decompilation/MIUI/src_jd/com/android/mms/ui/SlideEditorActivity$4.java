package com.android.mms.ui;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.model.SlideshowModel;

class SlideEditorActivity$4
  implements View.OnClickListener
{
  SlideEditorActivity$4(SlideEditorActivity paramSlideEditorActivity) {}
  
  public void onClick(View paramView)
  {
    if (!SlideEditorActivity.access$000(this$0)) {
      Log.v("SlideEditorActivity", "mDataLoaded is false");
    }
    while ((SlideEditorActivity.access$800(this$0) < 0) || (SlideEditorActivity.access$800(this$0) >= SlideEditorActivity.access$100(this$0).size())) {
      return;
    }
    SlideEditorActivity.access$500(this$0).removeSlide(SlideEditorActivity.access$800(this$0));
    int i = SlideEditorActivity.access$100(this$0).size();
    if (i > 0)
    {
      if (SlideEditorActivity.access$800(this$0) >= i) {
        SlideEditorActivity.access$810(this$0);
      }
      SlideEditorActivity.access$900(this$0);
      return;
    }
    this$0.finish();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideEditorActivity.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */