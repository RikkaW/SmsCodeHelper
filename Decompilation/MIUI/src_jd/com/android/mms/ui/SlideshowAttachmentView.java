package com.android.mms.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Map;

public class SlideshowAttachmentView
  extends LinearLayout
  implements SlideViewInterface
{
  private ImageView mImageView;
  private TextView mTextView;
  
  public SlideshowAttachmentView(Context paramContext)
  {
    super(paramContext);
  }
  
  public SlideshowAttachmentView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  protected void onFinishInflate()
  {
    mImageView = ((ImageView)findViewById(2131820878));
    mTextView = ((TextView)findViewById(2131820879));
  }
  
  public void pauseAudio() {}
  
  public void pauseVideo() {}
  
  public void reset()
  {
    mImageView.setImageURI(null);
    mTextView.setText("");
  }
  
  public void seekAudio(int paramInt) {}
  
  public void seekVideo(int paramInt) {}
  
  public void setAudio(Uri paramUri, String paramString, Map<String, ?> paramMap) {}
  
  public void setAudioDuration(int paramInt) {}
  
  public void setImage(String paramString, Drawable paramDrawable)
  {
    MessageUtils.setAttachmentImage(mImageView, paramDrawable, true);
  }
  
  public void setImageRegionFit(String paramString) {}
  
  public void setImageVisibility(boolean paramBoolean)
  {
    ImageView localImageView = mImageView;
    if (paramBoolean) {}
    for (int i = 0;; i = 4)
    {
      localImageView.setVisibility(i);
      return;
    }
  }
  
  public void setText(String paramString1, String paramString2)
  {
    mTextView.setText(paramString2);
  }
  
  public void setTextVisibility(boolean paramBoolean)
  {
    TextView localTextView = mTextView;
    if (paramBoolean) {}
    for (int i = 0;; i = 4)
    {
      localTextView.setVisibility(i);
      return;
    }
  }
  
  public void setVideo(String paramString, Uri paramUri)
  {
    paramString = new MediaMetadataRetriever();
    try
    {
      paramString.setDataSource(getContext(), paramUri);
      mImageView.setImageBitmap(paramString.getFrameAtTime(-1L));
      return;
    }
    catch (RuntimeException paramUri)
    {
      Log.e("SlideshowAttachmentView", "Unexpected RuntimeException.", paramUri);
      return;
    }
    finally
    {
      paramString.release();
    }
  }
  
  public void setVideoVisibility(boolean paramBoolean) {}
  
  public void startAudio() {}
  
  public void startVideo() {}
  
  public void stopAudio() {}
  
  public void stopVideo() {}
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowAttachmentView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */