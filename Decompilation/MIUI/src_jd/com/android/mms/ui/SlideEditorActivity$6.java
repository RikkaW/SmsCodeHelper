package com.android.mms.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.android.mms.model.SlideModel;
import com.android.mms.model.SlideshowModel;

class SlideEditorActivity$6
  implements View.OnClickListener
{
  SlideEditorActivity$6(SlideEditorActivity paramSlideEditorActivity) {}
  
  public void onClick(View paramView)
  {
    if (!SlideEditorActivity.access$000(this$0)) {
      Log.v("SlideEditorActivity", "mDataLoaded is false");
    }
    paramView = SlideEditorActivity.access$100(this$0).get(SlideEditorActivity.access$800(this$0));
    if ((paramView != null) && (paramView.hasVideo()))
    {
      Toast.makeText(this$0, 2131361871, 0).show();
      return;
    }
    paramView = new Intent("android.intent.action.GET_CONTENT", null);
    paramView.setType("image/*");
    this$0.startActivityForResult(paramView, 1);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideEditorActivity.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */