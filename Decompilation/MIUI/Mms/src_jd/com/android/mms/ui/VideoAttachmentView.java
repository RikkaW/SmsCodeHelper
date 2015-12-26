package com.android.mms.ui;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.Map;

public class VideoAttachmentView
  extends LinearLayout
  implements SlideViewInterface
{
  private ImageView mThumbnailView;
  
  public VideoAttachmentView(Context paramContext)
  {
    super(paramContext);
  }
  
  public VideoAttachmentView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  /* Error */
  public static android.graphics.Bitmap createVideoThumbnail(Context paramContext, Uri paramUri)
  {
    // Byte code:
    //   0: new 22	android/media/MediaMetadataRetriever
    //   3: dup
    //   4: invokespecial 25	android/media/MediaMetadataRetriever:<init>	()V
    //   7: astore_2
    //   8: aload_2
    //   9: aload_0
    //   10: aload_1
    //   11: invokevirtual 29	android/media/MediaMetadataRetriever:setDataSource	(Landroid/content/Context;Landroid/net/Uri;)V
    //   14: aload_2
    //   15: ldc2_w 30
    //   18: invokevirtual 35	android/media/MediaMetadataRetriever:getFrameAtTime	(J)Landroid/graphics/Bitmap;
    //   21: astore_0
    //   22: aload_2
    //   23: invokevirtual 38	android/media/MediaMetadataRetriever:release	()V
    //   26: aload_0
    //   27: areturn
    //   28: astore_0
    //   29: aload_2
    //   30: invokevirtual 38	android/media/MediaMetadataRetriever:release	()V
    //   33: aconst_null
    //   34: areturn
    //   35: astore_0
    //   36: aconst_null
    //   37: areturn
    //   38: astore_0
    //   39: aload_2
    //   40: invokevirtual 38	android/media/MediaMetadataRetriever:release	()V
    //   43: aload_0
    //   44: athrow
    //   45: astore_1
    //   46: aload_0
    //   47: areturn
    //   48: astore_1
    //   49: goto -6 -> 43
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	52	0	paramContext	Context
    //   0	52	1	paramUri	Uri
    //   7	33	2	localMediaMetadataRetriever	android.media.MediaMetadataRetriever
    // Exception table:
    //   from	to	target	type
    //   8	22	28	java/lang/RuntimeException
    //   29	33	35	java/lang/RuntimeException
    //   8	22	38	finally
    //   22	26	45	java/lang/RuntimeException
    //   39	43	48	java/lang/RuntimeException
  }
  
  protected void onFinishInflate()
  {
    mThumbnailView = ((ImageView)findViewById(2131820900));
  }
  
  public void pauseAudio() {}
  
  public void pauseVideo() {}
  
  public void reset() {}
  
  public void seekAudio(int paramInt) {}
  
  public void seekVideo(int paramInt) {}
  
  public void setAudio(Uri paramUri, String paramString, Map<String, ?> paramMap) {}
  
  public void setAudioDuration(int paramInt) {}
  
  public void setImage(String paramString, Drawable paramDrawable) {}
  
  public void setImageRegionFit(String paramString) {}
  
  public void setImageVisibility(boolean paramBoolean) {}
  
  public void setText(String paramString1, String paramString2) {}
  
  public void setTextVisibility(boolean paramBoolean) {}
  
  public void setVideo(String paramString, Uri paramUri)
  {
    try
    {
      paramUri = createVideoThumbnail(mContext, paramUri);
      paramString = paramUri;
      if (paramUri == null) {
        paramString = BitmapFactory.decodeResource(getResources(), 2130837760);
      }
      mThumbnailView.setImageBitmap(paramString);
      return;
    }
    catch (OutOfMemoryError paramString)
    {
      Log.e("VideoAttachmentView", "setVideo: out of memory: ", paramString);
    }
  }
  
  public void setVideoVisibility(boolean paramBoolean) {}
  
  public void startAudio() {}
  
  public void startVideo() {}
  
  public void stopAudio() {}
  
  public void stopVideo() {}
}

/* Location:
 * Qualified Name:     com.android.mms.ui.VideoAttachmentView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */