package com.android.mms.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;

public class WidgetDrawableSpan
  extends DynamicDrawableSpan
{
  private static final int MESEASURE_SPEC = View.MeasureSpec.makeMeasureSpec(0, 0);
  private final Context mContext;
  private final FrameLayout mFrame;
  private final Resources mResource;
  
  public WidgetDrawableSpan(Context paramContext, View paramView)
  {
    mContext = paramContext;
    mResource = mContext.getResources();
    mFrame = new FrameLayout(paramContext);
    mFrame.addView(paramView);
    mFrame.setDrawingCacheEnabled(true);
  }
  
  public Drawable getDrawable()
  {
    mFrame.measure(MESEASURE_SPEC, MESEASURE_SPEC);
    mFrame.layout(0, 0, mFrame.getMeasuredWidth(), mFrame.getMeasuredHeight());
    Object localObject = mFrame.getDrawingCache();
    localObject = new BitmapDrawable(mResource, (Bitmap)localObject);
    ((Drawable)localObject).setBounds(0, 0, ((Drawable)localObject).getIntrinsicWidth(), ((Drawable)localObject).getIntrinsicHeight());
    return (Drawable)localObject;
  }
  
  public int getSize(Paint paramPaint, CharSequence paramCharSequence, int paramInt1, int paramInt2, Paint.FontMetricsInt paramFontMetricsInt)
  {
    paramInt1 = super.getSize(paramPaint, paramCharSequence, paramInt1, paramInt2, paramFontMetricsInt);
    if (paramFontMetricsInt != null)
    {
      paramInt2 = bottom - ascent;
      ascent = (-(int)(paramInt2 / 3.0D * 2.0D));
      descent = ((int)(paramInt2 / 3.0D * 1.0D));
      top = ascent;
      bottom = descent;
    }
    return paramInt1;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.WidgetDrawableSpan
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */