package com.android.mms.ui;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import java.util.Map;

public abstract interface SlideViewInterface
  extends ViewInterface
{
  public abstract void pauseAudio();
  
  public abstract void pauseVideo();
  
  public abstract void seekAudio(int paramInt);
  
  public abstract void seekVideo(int paramInt);
  
  public abstract void setAudio(Uri paramUri, String paramString, Map<String, ?> paramMap);
  
  public abstract void setAudioDuration(int paramInt);
  
  public abstract void setImage(String paramString, Drawable paramDrawable);
  
  public abstract void setImageRegionFit(String paramString);
  
  public abstract void setImageVisibility(boolean paramBoolean);
  
  public abstract void setText(String paramString1, String paramString2);
  
  public abstract void setTextVisibility(boolean paramBoolean);
  
  public abstract void setVideo(String paramString, Uri paramUri);
  
  public abstract void setVideoVisibility(boolean paramBoolean);
  
  public abstract void startAudio();
  
  public abstract void startVideo();
  
  public abstract void stopAudio();
  
  public abstract void stopVideo();
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideViewInterface
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */