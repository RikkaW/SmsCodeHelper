package com.android.mms.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.meizu.common.util.StrokeDrawableUtils;
import com.meizu.common.widget.RoundCornerImageView;
import wd;

public class ImageAttachmentView
  extends AttachMentViewBase
{
  private RoundCornerImageView a;
  private TextView b;
  private TextView c;
  private Context d;
  
  public ImageAttachmentView(Context paramContext)
  {
    super(paramContext);
    d = paramContext;
  }
  
  public ImageAttachmentView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    d = paramContext;
  }
  
  public void a(long paramLong)
  {
    wd.a(c, paramLong, d);
  }
  
  public void a(String paramString, long paramLong)
  {
    if (MmsApp.a)
    {
      b.setText(wd.b(paramString, "---.jpg"));
      wd.a(c, paramLong, d);
    }
  }
  
  public void a(String paramString, Bitmap paramBitmap)
  {
    if (paramBitmap == null) {}
    try
    {
      a.setImageResource(2130837940);
      return;
    }
    catch (OutOfMemoryError paramString)
    {
      Log.e("ImageAttachmentView", "setImage: out of memory: ", paramString);
    }
    a.setImageDrawable(StrokeDrawableUtils.createStrokeDrawable(new BitmapDrawable(d.getResources(), paramBitmap), d.getResources(), Boolean.valueOf(false)));
    return;
  }
  
  public void h()
  {
    a.setImageDrawable(null);
  }
  
  protected void onFinishInflate()
  {
    a = ((RoundCornerImageView)findViewById(2131886479));
    b = ((TextView)findViewById(2131886217));
    c = ((TextView)findViewById(2131886218));
    if (MmsApp.a) {
      findViewById(2131886480).setVisibility(0);
    }
  }
  
  public void setVisibility(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 0;; i = 8)
    {
      setVisibility(i);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ImageAttachmentView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */