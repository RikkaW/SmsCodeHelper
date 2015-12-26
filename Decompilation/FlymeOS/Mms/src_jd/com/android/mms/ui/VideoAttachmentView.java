package com.android.mms.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.meizu.common.util.StrokeDrawableUtils;
import com.meizu.common.widget.RoundCornerImageView;
import wd;

public class VideoAttachmentView
  extends AttachMentViewBase
{
  private RoundCornerImageView a;
  private TextView b;
  private TextView c;
  private Context d;
  
  public VideoAttachmentView(Context paramContext)
  {
    super(paramContext);
    d = paramContext;
  }
  
  public VideoAttachmentView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    d = paramContext;
  }
  
  /* Error */
  public static Bitmap a(Context paramContext, Uri paramUri)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 26	android/media/MediaMetadataRetriever
    //   5: dup
    //   6: invokespecial 29	android/media/MediaMetadataRetriever:<init>	()V
    //   9: astore 4
    //   11: aload_3
    //   12: astore_2
    //   13: aload 4
    //   15: aload_0
    //   16: aload_1
    //   17: invokevirtual 33	android/media/MediaMetadataRetriever:setDataSource	(Landroid/content/Context;Landroid/net/Uri;)V
    //   20: aload_3
    //   21: astore_2
    //   22: aload 4
    //   24: ldc2_w 34
    //   27: invokevirtual 39	android/media/MediaMetadataRetriever:getFrameAtTime	(J)Landroid/graphics/Bitmap;
    //   30: astore_0
    //   31: aload_0
    //   32: astore_1
    //   33: aload_0
    //   34: ifnonnull +12 -> 46
    //   37: aload_0
    //   38: astore_2
    //   39: aload 4
    //   41: lconst_0
    //   42: invokevirtual 39	android/media/MediaMetadataRetriever:getFrameAtTime	(J)Landroid/graphics/Bitmap;
    //   45: astore_1
    //   46: aload 4
    //   48: invokevirtual 42	android/media/MediaMetadataRetriever:release	()V
    //   51: aload_1
    //   52: areturn
    //   53: astore_0
    //   54: aload 4
    //   56: invokevirtual 42	android/media/MediaMetadataRetriever:release	()V
    //   59: aload_2
    //   60: areturn
    //   61: astore_0
    //   62: aload_2
    //   63: areturn
    //   64: astore_0
    //   65: aload 4
    //   67: invokevirtual 42	android/media/MediaMetadataRetriever:release	()V
    //   70: aload_0
    //   71: athrow
    //   72: astore_0
    //   73: aload_1
    //   74: areturn
    //   75: astore_1
    //   76: goto -6 -> 70
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	79	0	paramContext	Context
    //   0	79	1	paramUri	Uri
    //   12	51	2	localObject1	Object
    //   1	20	3	localObject2	Object
    //   9	57	4	localMediaMetadataRetriever	android.media.MediaMetadataRetriever
    // Exception table:
    //   from	to	target	type
    //   13	20	53	java/lang/RuntimeException
    //   22	31	53	java/lang/RuntimeException
    //   39	46	53	java/lang/RuntimeException
    //   54	59	61	java/lang/RuntimeException
    //   13	20	64	finally
    //   22	31	64	finally
    //   39	46	64	finally
    //   46	51	72	java/lang/RuntimeException
    //   65	70	75	java/lang/RuntimeException
  }
  
  public void a(long paramLong)
  {
    wd.a(c, paramLong, d);
  }
  
  public void a(String paramString, long paramLong)
  {
    if (MmsApp.a)
    {
      b.setText(wd.b(paramString, "---.mpeg"));
      wd.a(c, paramLong, d);
    }
  }
  
  public void a(String paramString, Uri paramUri)
  {
    try
    {
      paramString = a(d, paramUri);
      if (paramString == null)
      {
        a.setImageResource(2130837940);
        return;
      }
      a.setImageDrawable(StrokeDrawableUtils.createStrokeDrawable(new BitmapDrawable(d.getResources(), paramString), d.getResources(), Boolean.valueOf(false)));
      return;
    }
    catch (OutOfMemoryError paramString)
    {
      Log.e("VideoAttachmentView", "setVideo: out of memory: ", paramString);
    }
  }
  
  public void b(String paramString, Bitmap paramBitmap)
  {
    if (paramBitmap == null)
    {
      a.setImageResource(2130837940);
      return;
    }
    a.setImageBitmap(paramBitmap);
  }
  
  protected void onFinishInflate()
  {
    a = ((RoundCornerImageView)findViewById(2131886774));
    b = ((TextView)findViewById(2131886217));
    c = ((TextView)findViewById(2131886218));
    if (MmsApp.a) {
      findViewById(2131886480).setVisibility(0);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.VideoAttachmentView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */