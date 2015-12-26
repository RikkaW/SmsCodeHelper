package com.android.mms.ui;

import android.os.AsyncTask;
import android.util.Log;
import com.android.mms.model.SlideshowModel;
import com.google.android.mms.MmsException;

class SlideEditorActivity$1
  extends AsyncTask<Void, Void, Void>
{
  SlideEditorActivity$1(SlideEditorActivity paramSlideEditorActivity) {}
  
  protected Void doInBackground(Void... paramVarArgs)
  {
    try
    {
      SlideEditorActivity.access$102(this$0, SlideshowModel.createFromMessageUri(SlideEditorActivity.access$200(this$0), SlideEditorActivity.access$300(this$0)));
      return null;
    }
    catch (MmsException paramVarArgs)
    {
      for (;;)
      {
        Log.e("SlideEditorActivity", "Create SlideshowModel failed!", paramVarArgs);
        this$0.finish();
      }
    }
  }
  
  protected void onPostExecute(Void paramVoid)
  {
    SlideEditorActivity.access$002(this$0, true);
    if ((SlideEditorActivity.access$100(this$0) == null) || (SlideEditorActivity.access$100(this$0).size() == 0))
    {
      Log.e("SlideEditorActivity", "Loaded slideshow is empty; can't edit nothingness, exiting.");
      this$0.finish();
      return;
    }
    SlideEditorActivity.access$100(this$0).registerModelChangedObserver(SlideEditorActivity.access$400(this$0));
    SlideEditorActivity.access$502(this$0, new SlideshowEditor(SlideEditorActivity.access$200(this$0), SlideEditorActivity.access$100(this$0)));
    SlideEditorActivity.access$602(this$0, new SlideshowPresenter(SlideEditorActivity.access$200(this$0), SlideEditorActivity.access$700(this$0), SlideEditorActivity.access$100(this$0)));
    if (SlideEditorActivity.access$800(this$0) >= SlideEditorActivity.access$100(this$0).size()) {
      SlideEditorActivity.access$802(this$0, Math.max(0, SlideEditorActivity.access$100(this$0).size() - 1));
    }
    for (;;)
    {
      SlideEditorActivity.access$900(this$0);
      return;
      if (SlideEditorActivity.access$800(this$0) < 0) {
        SlideEditorActivity.access$802(this$0, 0);
      }
    }
  }
  
  protected void onPreExecute()
  {
    SlideEditorActivity.access$002(this$0, false);
    SlideEditorActivity.access$102(this$0, null);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideEditorActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */