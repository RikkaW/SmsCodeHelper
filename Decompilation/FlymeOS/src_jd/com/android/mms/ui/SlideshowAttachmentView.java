package com.android.mms.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.meizu.common.util.StrokeDrawableUtils;
import java.util.Map;

public class SlideshowAttachmentView
  extends AttachMentViewBase
{
  private ImageView a;
  private TextView b;
  private TextView c;
  private Context d;
  
  public SlideshowAttachmentView(Context paramContext)
  {
    super(paramContext);
    d = paramContext;
  }
  
  public SlideshowAttachmentView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    d = paramContext;
  }
  
  public void a(Uri paramUri, String paramString, Map<String, ?> paramMap)
  {
    a.setImageResource(2130838195);
  }
  
  public void a(String paramString, long paramLong)
  {
    b.setText(2131492892);
    c.setText("" + (1023L + paramLong) / 1024L + "KB");
  }
  
  public void a(String paramString, Bitmap paramBitmap)
  {
    if (paramBitmap == null)
    {
      a.setImageResource(2130837940);
      return;
    }
    a.setImageDrawable(StrokeDrawableUtils.createStrokeDrawable(new BitmapDrawable(d.getResources(), paramBitmap), d.getResources(), Boolean.valueOf(false)));
  }
  
  public void a(String paramString, Uri paramUri)
  {
    try
    {
      paramString = VideoAttachmentView.a(d, paramUri);
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
      Log.e("SlideshowAttachmentView", "setVideo: out of memory: ", paramString);
    }
  }
  
  public void a(String paramString1, String paramString2)
  {
    b.setText(paramString2);
    if ((paramString2 != null) && (paramString2.length() > 0)) {
      setTextVisibility(true);
    }
  }
  
  public void b(Uri paramUri, String paramString, Map<String, ?> paramMap)
  {
    a.setImageResource(2130838219);
  }
  
  public void b(String paramString, Bitmap paramBitmap)
  {
    if ((paramBitmap != null) && (!paramBitmap.isRecycled()))
    {
      a.setImageBitmap(paramBitmap);
      return;
    }
    a.setImageResource(2130837940);
  }
  
  public void h()
  {
    a.setImageDrawable(null);
    b.setText("");
    c.setText("");
  }
  
  protected void onFinishInflate()
  {
    a = ((ImageView)findViewById(2131886739));
    b = ((TextView)findViewById(2131886217));
    c = ((TextView)findViewById(2131886218));
    if (MmsApp.a)
    {
      b.setVisibility(0);
      c.setVisibility(0);
    }
  }
  
  public void setImageRegionFit(String paramString) {}
  
  public void setImageVisibility(boolean paramBoolean)
  {
    ImageView localImageView = a;
    if (paramBoolean) {}
    for (int i = 0;; i = 4)
    {
      localImageView.setVisibility(i);
      return;
    }
  }
  
  public void setTextVisibility(boolean paramBoolean)
  {
    TextView localTextView = b;
    if (paramBoolean) {}
    for (int i = 0;; i = 4)
    {
      localTextView.setVisibility(i);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowAttachmentView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */