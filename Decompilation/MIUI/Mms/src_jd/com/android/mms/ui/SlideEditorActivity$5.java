package com.android.mms.ui;

import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import com.android.mms.ExceedMessageSizeException;
import miui.os.Build;

class SlideEditorActivity$5
  implements BasicSlideEditorView.OnTextChangedListener
{
  SlideEditorActivity$5(SlideEditorActivity paramSlideEditorActivity) {}
  
  public void onTextChanged(String paramString)
  {
    if (!SlideEditorActivity.access$000(this$0)) {}
    do
    {
      Log.v("SlideEditorActivity", "mDataLoaded is false");
      for (;;)
      {
        return;
        if (!this$0.isFinishing()) {
          try
          {
            SlideEditorActivity.access$500(this$0).changeText(SlideEditorActivity.access$800(this$0), paramString);
            if (Build.IS_CM_CUSTOMIZATION_TEST)
            {
              SlideEditorActivity.access$1302(this$0, paramString);
              return;
            }
          }
          catch (ExceedMessageSizeException paramString) {}
        }
      }
    } while (!Build.IS_CM_CUSTOMIZATION_TEST);
    Toast.makeText(this$0, 2131361878, 0).show();
    SlideEditorActivity.access$1000(this$0).setText(SlideEditorActivity.access$1300(this$0));
    SlideEditorActivity.access$1000(this$0).setSelection(SlideEditorActivity.access$1300(this$0).length());
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideEditorActivity.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */