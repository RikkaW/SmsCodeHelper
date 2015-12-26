package com.android.mms.ui;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import com.android.mms.ExceedMessageSizeException;
import com.android.mms.model.AudioModel;
import com.android.mms.model.ImageModel;
import com.android.mms.model.LayoutModel;
import com.android.mms.model.SlideModel;
import com.android.mms.model.SlideshowModel;
import com.android.mms.model.TextModel;
import com.android.mms.model.VideoModel;
import com.google.android.mms.MmsException;
import miui.os.Build;

public class SlideshowEditor
{
  private final Context mContext;
  private final SlideshowModel mModel;
  
  public SlideshowEditor(Context paramContext, SlideshowModel paramSlideshowModel)
  {
    mContext = paramContext;
    mModel = paramSlideshowModel;
  }
  
  public boolean addNewSlide()
  {
    return addNewSlide(mModel.size());
  }
  
  public boolean addNewSlide(int paramInt)
  {
    int i = mModel.size();
    if (i < 20) {
      try
      {
        mModel.checkMessageSize(150);
        SlideModel localSlideModel = new SlideModel(mModel);
        localSlideModel.add(new TextModel(mContext, "text/plain", "text_" + i + ".txt", mModel.getLayout().getTextRegion()));
        mModel.add(paramInt, localSlideModel);
        return true;
      }
      catch (ExceedMessageSizeException localExceedMessageSizeException)
      {
        Toast.makeText(mContext, mContext.getResources().getString(2131361851), 0).show();
        return false;
      }
    }
    Log.w("Mms:slideshow", "The limitation of the number of slides is reached.");
    return false;
  }
  
  public void changeAudio(int paramInt, Uri paramUri)
    throws MmsException
  {
    if (paramUri == null) {
      throw new MmsException("new Audio url is null!");
    }
    paramUri = new AudioModel(mContext, paramUri);
    SlideModel localSlideModel = mModel.get(paramInt);
    localSlideModel.add(paramUri);
    localSlideModel.updateDuration(paramUri.getDuration());
  }
  
  public void changeDuration(int paramInt1, int paramInt2)
  {
    if (paramInt2 >= 0) {
      mModel.get(paramInt1).setDuration(paramInt2);
    }
  }
  
  public void changeImage(int paramInt, Uri paramUri)
    throws MmsException
  {
    if (paramUri == null) {
      throw new MmsException("new Image url is null!");
    }
    mModel.get(paramInt).add(new ImageModel(mContext, paramUri, mModel.getLayout().getImageRegion()));
  }
  
  public void changeLayout(int paramInt)
  {
    mModel.getLayout().changeTo(paramInt);
  }
  
  public void changeText(int paramInt, String paramString)
  {
    SlideModel localSlideModel;
    TextModel localTextModel;
    if (paramString != null)
    {
      localSlideModel = mModel.get(paramInt);
      localTextModel = localSlideModel.getText();
      if (!Build.IS_CM_CUSTOMIZATION_TEST) {
        break label101;
      }
      if ((localTextModel == null) || (!paramString.equals(localTextModel.getText()))) {
        localSlideModel.add(new TextModel(mContext, "text/plain", "text_" + paramInt + ".txt", 106, paramString.getBytes(), mModel.getLayout().getTextRegion()));
      }
    }
    label101:
    do
    {
      return;
      if (localTextModel == null)
      {
        localTextModel = new TextModel(mContext, "text/plain", "text_" + paramInt + ".txt", mModel.getLayout().getTextRegion());
        localTextModel.setText(paramString);
        localSlideModel.add(localTextModel);
        return;
      }
    } while (paramString.equals(localTextModel.getText()));
    localTextModel.setText(paramString);
  }
  
  public void changeVideo(int paramInt, Uri paramUri)
    throws MmsException
  {
    if (paramUri == null) {
      throw new MmsException("new Video url is null!");
    }
    paramUri = new VideoModel(mContext, paramUri, mModel.getLayout().getImageRegion());
    SlideModel localSlideModel = mModel.get(paramInt);
    localSlideModel.add(paramUri);
    localSlideModel.updateDuration(paramUri.getDuration());
  }
  
  public void checkMessageSize(int paramInt1, int paramInt2)
  {
    mModel.get(paramInt1).checkMessageSize(paramInt2);
  }
  
  public void moveSlideDown(int paramInt)
  {
    mModel.add(paramInt + 1, mModel.remove(paramInt));
  }
  
  public void moveSlideUp(int paramInt)
  {
    mModel.add(paramInt - 1, mModel.remove(paramInt));
  }
  
  public void removeAllSlides()
  {
    while (mModel.size() > 0) {
      removeSlide(0);
    }
  }
  
  public boolean removeAudio(int paramInt)
  {
    return mModel.get(paramInt).removeAudio();
  }
  
  public boolean removeImage(int paramInt)
  {
    return mModel.get(paramInt).removeImage();
  }
  
  public void removeSlide(int paramInt)
  {
    mModel.remove(paramInt);
  }
  
  public boolean removeVideo(int paramInt)
  {
    return mModel.get(paramInt).removeVideo();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowEditor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */