package com.android.mms.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

class SlideEditorActivity$9
  implements View.OnClickListener
{
  SlideEditorActivity$9(SlideEditorActivity paramSlideEditorActivity) {}
  
  public void onClick(View paramView)
  {
    if (!SlideEditorActivity.access$000(this$0))
    {
      Log.v("SlideEditorActivity", "mDataLoaded is false");
      return;
    }
    paramView = new Intent();
    paramView.putExtra("done", true);
    this$0.setResult(-1, paramView);
    this$0.finish();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideEditorActivity.9
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */