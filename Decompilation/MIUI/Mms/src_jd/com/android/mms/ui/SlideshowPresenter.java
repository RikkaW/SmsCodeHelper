package com.android.mms.ui;

import android.content.Context;
import android.media.MediaDrmException;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import com.android.mms.model.AudioModel;
import com.android.mms.model.ImageModel;
import com.android.mms.model.LayoutModel;
import com.android.mms.model.MediaModel;
import com.android.mms.model.MediaModel.MediaAction;
import com.android.mms.model.Model;
import com.android.mms.model.RegionMediaModel;
import com.android.mms.model.RegionModel;
import com.android.mms.model.SlideModel;
import com.android.mms.model.SlideshowModel;
import com.android.mms.model.TextModel;
import com.android.mms.model.VideoModel;
import java.util.Iterator;

public class SlideshowPresenter
  extends Presenter
{
  protected final Handler mHandler = new Handler();
  protected float mHeightTransformRatio;
  protected int mLocation = 0;
  protected final int mSlideNumber = ((SlideshowModel)mModel).size();
  private final AdaptableSlideViewInterface.OnSizeChangedListener mViewSizeChangedListener = new AdaptableSlideViewInterface.OnSizeChangedListener()
  {
    public void onSizeChanged(int paramAnonymousInt1, int paramAnonymousInt2)
    {
      LayoutModel localLayoutModel = ((SlideshowModel)mModel).getLayout();
      mWidthTransformRatio = SlideshowPresenter.this.getWidthTransformRatio(paramAnonymousInt1, localLayoutModel.getLayoutWidth());
      mHeightTransformRatio = SlideshowPresenter.this.getHeightTransformRatio(paramAnonymousInt2, localLayoutModel.getLayoutHeight());
      if (mWidthTransformRatio > mHeightTransformRatio) {}
      for (float f = mWidthTransformRatio;; f = mHeightTransformRatio)
      {
        mWidthTransformRatio = f;
        mHeightTransformRatio = f;
        return;
      }
    }
  };
  protected float mWidthTransformRatio;
  
  public SlideshowPresenter(Context paramContext, ViewInterface paramViewInterface, Model paramModel)
  {
    super(paramContext, paramViewInterface, paramModel);
    if ((paramViewInterface instanceof AdaptableSlideViewInterface)) {
      ((AdaptableSlideViewInterface)paramViewInterface).setOnSizeChangedListener(mViewSizeChangedListener);
    }
  }
  
  private float getHeightTransformRatio(int paramInt1, int paramInt2)
  {
    if (paramInt1 > 0) {
      return paramInt2 / paramInt1;
    }
    return 1.0F;
  }
  
  private float getWidthTransformRatio(int paramInt1, int paramInt2)
  {
    if (paramInt1 > 0) {
      return paramInt2 / paramInt1;
    }
    return 1.0F;
  }
  
  private int transformHeight(int paramInt)
  {
    return (int)(paramInt / mHeightTransformRatio);
  }
  
  private int transformWidth(int paramInt)
  {
    return (int)(paramInt / mWidthTransformRatio);
  }
  
  public void goForward()
  {
    if (mLocation < mSlideNumber - 1) {
      mLocation += 1;
    }
  }
  
  public void onModelChanged(final Model paramModel, final boolean paramBoolean)
  {
    final SlideViewInterface localSlideViewInterface = (SlideViewInterface)mView;
    if ((paramModel instanceof SlideshowModel)) {}
    do
    {
      do
      {
        return;
        if ((paramModel instanceof SlideModel))
        {
          if (((SlideModel)paramModel).isVisible())
          {
            mHandler.post(new Runnable()
            {
              public void run()
              {
                presentSlide(localSlideViewInterface, (SlideModel)paramModel);
              }
            });
            return;
          }
          mHandler.post(new Runnable()
          {
            public void run()
            {
              goForward();
            }
          });
          return;
        }
        if (!(paramModel instanceof MediaModel)) {
          break;
        }
        if ((paramModel instanceof RegionMediaModel))
        {
          mHandler.post(new Runnable()
          {
            public void run()
            {
              try
              {
                presentRegionMedia(localSlideViewInterface, (RegionMediaModel)paramModel, paramBoolean);
                return;
              }
              catch (MediaDrmException localMediaDrmException)
              {
                Log.e("SlideshowPresenter", localMediaDrmException.getMessage(), localMediaDrmException);
                Toast.makeText(mContext, mContext.getString(2131361819), 0).show();
              }
            }
          });
          return;
        }
      } while (!((MediaModel)paramModel).isAudio());
      mHandler.post(new Runnable()
      {
        public void run()
        {
          try
          {
            presentAudio(localSlideViewInterface, (AudioModel)paramModel, paramBoolean);
            return;
          }
          catch (MediaDrmException localMediaDrmException)
          {
            Log.e("SlideshowPresenter", localMediaDrmException.getMessage(), localMediaDrmException);
            Toast.makeText(mContext, mContext.getString(2131361819), 0).show();
          }
        }
      });
      return;
    } while (!(paramModel instanceof RegionModel));
  }
  
  public void present()
  {
    presentSlide((SlideViewInterface)mView, ((SlideshowModel)mModel).get(mLocation));
  }
  
  protected void presentAudio(SlideViewInterface paramSlideViewInterface, AudioModel paramAudioModel, boolean paramBoolean)
    throws MediaDrmException
  {
    if (paramBoolean)
    {
      paramSlideViewInterface.setAudio(paramAudioModel.getUri(), paramAudioModel.getSrc(), paramAudioModel.getExtras());
      paramSlideViewInterface.setAudioDuration(paramAudioModel.getDuration() / 1000);
    }
    MediaModel.MediaAction localMediaAction = paramAudioModel.getCurrentAction();
    if (localMediaAction == MediaModel.MediaAction.START) {
      paramSlideViewInterface.startAudio();
    }
    do
    {
      return;
      if (localMediaAction == MediaModel.MediaAction.PAUSE)
      {
        paramSlideViewInterface.pauseAudio();
        return;
      }
      if (localMediaAction == MediaModel.MediaAction.STOP)
      {
        paramSlideViewInterface.stopAudio();
        return;
      }
    } while (localMediaAction != MediaModel.MediaAction.SEEK);
    paramSlideViewInterface.seekAudio(paramAudioModel.getSeekTo());
  }
  
  protected void presentImage(SlideViewInterface paramSlideViewInterface, ImageModel paramImageModel, RegionModel paramRegionModel, boolean paramBoolean)
    throws MediaDrmException
  {
    if (paramBoolean) {
      paramSlideViewInterface.setImage(paramImageModel.getSrc(), paramImageModel.getDrawable());
    }
    if ((paramSlideViewInterface instanceof AdaptableSlideViewInterface)) {
      ((AdaptableSlideViewInterface)paramSlideViewInterface).setImageRegion(transformWidth(paramRegionModel.getLeft()), transformHeight(paramRegionModel.getTop()), transformWidth(paramRegionModel.getWidth()), transformHeight(paramRegionModel.getHeight()));
    }
    paramSlideViewInterface.setImageRegionFit(paramRegionModel.getFit());
    paramSlideViewInterface.setImageVisibility(paramImageModel.isVisible());
  }
  
  protected void presentRegionMedia(SlideViewInterface paramSlideViewInterface, RegionMediaModel paramRegionMediaModel, boolean paramBoolean)
    throws MediaDrmException
  {
    RegionModel localRegionModel = paramRegionMediaModel.getRegion();
    if (paramRegionMediaModel.isText()) {
      presentText(paramSlideViewInterface, (TextModel)paramRegionMediaModel, localRegionModel, paramBoolean);
    }
    do
    {
      return;
      if (paramRegionMediaModel.isImage())
      {
        presentImage(paramSlideViewInterface, (ImageModel)paramRegionMediaModel, localRegionModel, paramBoolean);
        return;
      }
    } while (!paramRegionMediaModel.isVideo());
    presentVideo(paramSlideViewInterface, (VideoModel)paramRegionMediaModel, localRegionModel, paramBoolean);
  }
  
  protected void presentSlide(SlideViewInterface paramSlideViewInterface, SlideModel paramSlideModel)
  {
    paramSlideViewInterface.reset();
    for (;;)
    {
      MediaModel localMediaModel;
      try
      {
        paramSlideModel = paramSlideModel.iterator();
        if (paramSlideModel.hasNext())
        {
          localMediaModel = (MediaModel)paramSlideModel.next();
          if ((localMediaModel instanceof RegionMediaModel)) {
            presentRegionMedia(paramSlideViewInterface, (RegionMediaModel)localMediaModel, true);
          }
        }
        else
        {
          return;
        }
      }
      catch (MediaDrmException paramSlideViewInterface)
      {
        Log.e("SlideshowPresenter", paramSlideViewInterface.getMessage(), paramSlideViewInterface);
        Toast.makeText(mContext, mContext.getString(2131361819), 0).show();
      }
      if (localMediaModel.isAudio()) {
        presentAudio(paramSlideViewInterface, (AudioModel)localMediaModel, true);
      }
    }
  }
  
  protected void presentText(SlideViewInterface paramSlideViewInterface, TextModel paramTextModel, RegionModel paramRegionModel, boolean paramBoolean)
  {
    if (paramBoolean) {
      paramSlideViewInterface.setText(paramTextModel.getSrc(), paramTextModel.getText());
    }
    if ((paramSlideViewInterface instanceof AdaptableSlideViewInterface)) {
      ((AdaptableSlideViewInterface)paramSlideViewInterface).setTextRegion(transformWidth(paramRegionModel.getLeft()), transformHeight(paramRegionModel.getTop()), transformWidth(paramRegionModel.getWidth()), transformHeight(paramRegionModel.getHeight()));
    }
    paramSlideViewInterface.setTextVisibility(paramTextModel.isVisible());
  }
  
  protected void presentVideo(SlideViewInterface paramSlideViewInterface, VideoModel paramVideoModel, RegionModel paramRegionModel, boolean paramBoolean)
    throws MediaDrmException
  {
    if (paramBoolean) {
      paramSlideViewInterface.setVideo(paramVideoModel.getSrc(), paramVideoModel.getUri());
    }
    if ((paramSlideViewInterface instanceof AdaptableSlideViewInterface)) {
      ((AdaptableSlideViewInterface)paramSlideViewInterface).setVideoRegion(transformWidth(paramRegionModel.getLeft()), transformHeight(paramRegionModel.getTop()), transformWidth(paramRegionModel.getWidth()), transformHeight(paramRegionModel.getHeight()));
    }
    paramSlideViewInterface.setVideoVisibility(paramVideoModel.isVisible());
    paramRegionModel = paramVideoModel.getCurrentAction();
    if (paramRegionModel == MediaModel.MediaAction.START) {
      paramSlideViewInterface.startVideo();
    }
    do
    {
      return;
      if (paramRegionModel == MediaModel.MediaAction.PAUSE)
      {
        paramSlideViewInterface.pauseVideo();
        return;
      }
      if (paramRegionModel == MediaModel.MediaAction.STOP)
      {
        paramSlideViewInterface.stopVideo();
        return;
      }
    } while (paramRegionModel != MediaModel.MediaAction.SEEK);
    paramSlideViewInterface.seekVideo(paramVideoModel.getSeekTo());
  }
  
  public void setLocation(int paramInt)
  {
    mLocation = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowPresenter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */