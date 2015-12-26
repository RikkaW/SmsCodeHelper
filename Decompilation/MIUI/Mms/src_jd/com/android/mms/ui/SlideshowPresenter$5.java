package com.android.mms.ui;

import android.content.Context;
import android.media.MediaDrmException;
import android.util.Log;
import android.widget.Toast;
import com.android.mms.model.AudioModel;
import com.android.mms.model.Model;

class SlideshowPresenter$5
  implements Runnable
{
  SlideshowPresenter$5(SlideshowPresenter paramSlideshowPresenter, SlideViewInterface paramSlideViewInterface, Model paramModel, boolean paramBoolean) {}
  
  public void run()
  {
    try
    {
      this$0.presentAudio(val$view, (AudioModel)val$model, val$dataChanged);
      return;
    }
    catch (MediaDrmException localMediaDrmException)
    {
      Log.e("SlideshowPresenter", localMediaDrmException.getMessage(), localMediaDrmException);
      Toast.makeText(this$0.mContext, this$0.mContext.getString(2131361819), 0).show();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowPresenter.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */