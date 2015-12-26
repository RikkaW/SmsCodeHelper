package com.android.mms.ui;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

class SlideEditorActivity$7
  implements View.OnClickListener
{
  SlideEditorActivity$7(SlideEditorActivity paramSlideEditorActivity) {}
  
  public void onClick(View paramView)
  {
    if (!SlideEditorActivity.access$000(this$0)) {
      Log.v("SlideEditorActivity", "mDataLoaded is false");
    }
    while (SlideEditorActivity.access$800(this$0) <= 0) {
      return;
    }
    SlideEditorActivity.access$810(this$0);
    SlideEditorActivity.access$900(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideEditorActivity.7
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */