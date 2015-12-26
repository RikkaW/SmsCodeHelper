package com.android.mms.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Map;

public class SlideListItemView
  extends LinearLayout
  implements SlideViewInterface
{
  private ImageView mAttachmentIcon;
  private TextView mAttachmentName;
  private ThumbnailView mImagePreview;
  private int mImagePreviewSize;
  private ImageView mPlayIcon;
  private TextView mTextPreview;
  
  public SlideListItemView(Context paramContext)
  {
    super(paramContext);
  }
  
  public SlideListItemView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  protected void onFinishInflate()
  {
    mTextPreview = ((TextView)findViewById(2131820884));
    mTextPreview.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    mImagePreview = ((ThumbnailView)findViewById(2131820881));
    mPlayIcon = ((ImageView)findViewById(2131820619));
    mAttachmentName = ((TextView)findViewById(2131820885));
    mAttachmentIcon = ((ImageView)findViewById(2131820886));
    mImagePreviewSize = getResources().getDimensionPixelSize(2131427437);
  }
  
  public void pauseAudio() {}
  
  public void pauseVideo() {}
  
  public void reset() {}
  
  public void seekAudio(int paramInt) {}
  
  public void seekVideo(int paramInt) {}
  
  public void setAudio(Uri paramUri, String paramString, Map<String, ?> paramMap)
  {
    if (paramString != null)
    {
      mAttachmentName.setText(paramString);
      mAttachmentName.setVisibility(0);
      mAttachmentIcon.setImageResource(2130837764);
      mAttachmentIcon.setVisibility(0);
      return;
    }
    mAttachmentName.setText("");
    mAttachmentName.setVisibility(8);
    mAttachmentIcon.setImageDrawable(null);
    mAttachmentIcon.setVisibility(8);
  }
  
  public void setAudioDuration(int paramInt) {}
  
  public void setImage(String paramString, Drawable paramDrawable)
  {
    paramString = paramDrawable;
    if (paramDrawable == null) {}
    try
    {
      paramString = getResources().getDrawable(2130837759);
      mImagePreview.setImageDrawable(getResources().getDrawable(2130837978), paramString, mImagePreviewSize, mImagePreviewSize);
      return;
    }
    catch (OutOfMemoryError paramString)
    {
      Log.e("SlideListItemView", "setImage: out of memory: ", paramString);
    }
  }
  
  public void setImageRegionFit(String paramString) {}
  
  public void setImageVisibility(boolean paramBoolean) {}
  
  public void setText(String paramString1, String paramString2)
  {
    mTextPreview.setText(paramString2);
    paramString1 = mTextPreview;
    if (TextUtils.isEmpty(paramString2)) {}
    for (int i = 8;; i = 0)
    {
      paramString1.setVisibility(i);
      return;
    }
  }
  
  public void setTextVisibility(boolean paramBoolean) {}
  
  /* Error */
  public void setVideo(String paramString, Uri paramUri)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +109 -> 110
    //   4: aload_0
    //   5: getfield 58	com/android/mms/ui/SlideListItemView:mAttachmentName	Landroid/widget/TextView;
    //   8: aload_1
    //   9: invokevirtual 86	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   12: aload_0
    //   13: getfield 58	com/android/mms/ui/SlideListItemView:mAttachmentName	Landroid/widget/TextView;
    //   16: iconst_0
    //   17: invokevirtual 89	android/widget/TextView:setVisibility	(I)V
    //   20: aload_0
    //   21: getfield 61	com/android/mms/ui/SlideListItemView:mAttachmentIcon	Landroid/widget/ImageView;
    //   24: ldc -113
    //   26: invokevirtual 93	android/widget/ImageView:setImageResource	(I)V
    //   29: aload_0
    //   30: getfield 61	com/android/mms/ui/SlideListItemView:mAttachmentIcon	Landroid/widget/ImageView;
    //   33: iconst_0
    //   34: invokevirtual 94	android/widget/ImageView:setVisibility	(I)V
    //   37: new 145	android/media/MediaMetadataRetriever
    //   40: dup
    //   41: invokespecial 147	android/media/MediaMetadataRetriever:<init>	()V
    //   44: astore_1
    //   45: aload_1
    //   46: aload_0
    //   47: invokevirtual 151	com/android/mms/ui/SlideListItemView:getContext	()Landroid/content/Context;
    //   50: aload_2
    //   51: invokevirtual 155	android/media/MediaMetadataRetriever:setDataSource	(Landroid/content/Context;Landroid/net/Uri;)V
    //   54: aload_0
    //   55: getfield 50	com/android/mms/ui/SlideListItemView:mImagePreview	Lcom/android/mms/ui/ThumbnailView;
    //   58: aload_0
    //   59: invokevirtual 65	com/android/mms/ui/SlideListItemView:getResources	()Landroid/content/res/Resources;
    //   62: ldc 113
    //   64: invokevirtual 112	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   67: new 157	android/graphics/drawable/BitmapDrawable
    //   70: dup
    //   71: aload_0
    //   72: invokevirtual 65	com/android/mms/ui/SlideListItemView:getResources	()Landroid/content/res/Resources;
    //   75: aload_1
    //   76: ldc2_w 158
    //   79: invokevirtual 163	android/media/MediaMetadataRetriever:getFrameAtTime	(J)Landroid/graphics/Bitmap;
    //   82: invokespecial 166	android/graphics/drawable/BitmapDrawable:<init>	(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V
    //   85: aload_0
    //   86: getfield 74	com/android/mms/ui/SlideListItemView:mImagePreviewSize	I
    //   89: aload_0
    //   90: getfield 74	com/android/mms/ui/SlideListItemView:mImagePreviewSize	I
    //   93: invokevirtual 116	com/android/mms/ui/ThumbnailView:setImageDrawable	(Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;II)Z
    //   96: pop
    //   97: aload_0
    //   98: getfield 55	com/android/mms/ui/SlideListItemView:mPlayIcon	Landroid/widget/ImageView;
    //   101: iconst_0
    //   102: invokevirtual 94	android/widget/ImageView:setVisibility	(I)V
    //   105: aload_1
    //   106: invokevirtual 169	android/media/MediaMetadataRetriever:release	()V
    //   109: return
    //   110: aload_0
    //   111: getfield 58	com/android/mms/ui/SlideListItemView:mAttachmentName	Landroid/widget/TextView;
    //   114: ldc 96
    //   116: invokevirtual 86	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   119: aload_0
    //   120: getfield 58	com/android/mms/ui/SlideListItemView:mAttachmentName	Landroid/widget/TextView;
    //   123: bipush 8
    //   125: invokevirtual 89	android/widget/TextView:setVisibility	(I)V
    //   128: aload_0
    //   129: getfield 61	com/android/mms/ui/SlideListItemView:mAttachmentIcon	Landroid/widget/ImageView;
    //   132: aconst_null
    //   133: invokevirtual 100	android/widget/ImageView:setImageDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   136: aload_0
    //   137: getfield 61	com/android/mms/ui/SlideListItemView:mAttachmentIcon	Landroid/widget/ImageView;
    //   140: bipush 8
    //   142: invokevirtual 94	android/widget/ImageView:setVisibility	(I)V
    //   145: goto -108 -> 37
    //   148: astore_2
    //   149: aload_1
    //   150: invokevirtual 169	android/media/MediaMetadataRetriever:release	()V
    //   153: return
    //   154: astore_1
    //   155: return
    //   156: astore_2
    //   157: aload_1
    //   158: invokevirtual 169	android/media/MediaMetadataRetriever:release	()V
    //   161: aload_2
    //   162: athrow
    //   163: astore_1
    //   164: return
    //   165: astore_1
    //   166: goto -5 -> 161
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	169	0	this	SlideListItemView
    //   0	169	1	paramString	String
    //   0	169	2	paramUri	Uri
    // Exception table:
    //   from	to	target	type
    //   45	105	148	java/lang/RuntimeException
    //   149	153	154	java/lang/RuntimeException
    //   45	105	156	finally
    //   105	109	163	java/lang/RuntimeException
    //   157	161	165	java/lang/RuntimeException
  }
  
  public void setVideoVisibility(boolean paramBoolean) {}
  
  public void startAudio() {}
  
  public void startVideo() {}
  
  public void stopAudio() {}
  
  public void stopVideo() {}
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideListItemView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */