package com.android.mms.ui;

import android.content.Context;
import android.media.MediaDrmException;
import android.util.Log;
import android.widget.Toast;
import com.android.mms.model.Model;
import com.android.mms.model.RegionMediaModel;

class SlideshowPresenter$4
  implements Runnable
{
  SlideshowPresenter$4(SlideshowPresenter paramSlideshowPresenter, SlideViewInterface paramSlideViewInterface, Model paramModel, boolean paramBoolean) {}
  
  public void run()
  {
    try
    {
      this$0.presentRegionMedia(val$view, (RegionMediaModel)val$model, val$dataChanged);
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
 * Qualified Name:     com.android.mms.ui.SlideshowPresenter.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */