package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import com.android.mms.model.SlideModel;
import com.android.mms.model.SlideshowModel;

class SlideEditorActivity$10
  implements DialogInterface.OnClickListener
{
  SlideEditorActivity$10(SlideEditorActivity paramSlideEditorActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < 10)) {
      SlideEditorActivity.access$500(this$0).changeDuration(SlideEditorActivity.access$800(this$0), (paramInt + 1) * 1000);
    }
    for (;;)
    {
      paramDialogInterface.dismiss();
      return;
      Intent localIntent = new Intent(this$0, EditSlideDurationActivity.class);
      localIntent.putExtra("slide_index", SlideEditorActivity.access$800(this$0));
      localIntent.putExtra("slide_total", SlideEditorActivity.access$100(this$0).size());
      localIntent.putExtra("dur", SlideEditorActivity.access$100(this$0).get(SlideEditorActivity.access$800(this$0)).getDuration() / 1000);
      this$0.startActivityForResult(localIntent, 6);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideEditorActivity.10
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */