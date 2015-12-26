package com.android.mms.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import gb.a;

public class MessageRoundCornerImageView
  extends ImageView
{
  private Context a;
  private Resources b;
  private boolean c = false;
  private Bitmap d;
  private int e = 0;
  
  public MessageRoundCornerImageView(Context paramContext)
  {
    this(paramContext, null);
    a = paramContext;
    b = paramContext.getResources();
  }
  
  public MessageRoundCornerImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
    a = paramContext;
    b = paramContext.getResources();
  }
  
  public MessageRoundCornerImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    a = paramContext;
    b = paramContext.getResources();
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, gb.a.MessageRoundCornerImageView, paramInt, 0);
    e = paramContext.getDimensionPixelSize(0, 0);
    paramContext.recycle();
  }
  
  private void a(Drawable paramDrawable)
  {
    int i = Math.min(getMeasuredWidth(), getMeasuredHeight());
    Bitmap localBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    RectF localRectF = new RectF(0.0F, 0.0F, i, i);
    Paint localPaint = new Paint(1);
    localCanvas.drawRoundRect(localRectF, e, e, localPaint);
    localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    paramDrawable.setBounds(0, 0, i, i);
    localCanvas.saveLayer(localRectF, localPaint, 31);
    paramDrawable.draw(localCanvas);
    localCanvas.restore();
    d = localBitmap;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    if (c)
    {
      if (getDrawable() == null) {
        return;
      }
      if (d == null) {
        a(getDrawable());
      }
      paramCanvas.drawBitmap(d, 0.0F, 0.0F, null);
      return;
    }
    super.onDraw(paramCanvas);
  }
  
  public void setImageBitmap(Bitmap paramBitmap)
  {
    super.setImageBitmap(paramBitmap);
    if (d != null)
    {
      d.recycle();
      d = null;
    }
  }
  
  public void setImageDrawable(Drawable paramDrawable)
  {
    super.setImageDrawable(paramDrawable);
    if (d != null)
    {
      d.recycle();
      d = null;
    }
  }
  
  public void setImageResource(int paramInt)
  {
    super.setImageResource(paramInt);
    if (d != null)
    {
      d.recycle();
      d = null;
    }
  }
  
  public void setImageURI(Uri paramUri)
  {
    super.setImageURI(paramUri);
    if (d != null)
    {
      d.recycle();
      d = null;
    }
  }
  
  public void setIsNeedRedraw(boolean paramBoolean)
  {
    c = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MessageRoundCornerImageView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */