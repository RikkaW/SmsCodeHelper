package com.meizu.common.drawble;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import com.meizu.common.R.attr;
import com.meizu.common.R.color;
import com.meizu.common.R.dimen;
import com.meizu.common.R.styleable;
import java.io.InputStream;

public class TipDrawable
  extends BitmapDrawable
{
  Context mContext;
  int mRadius;
  Paint mTiPaint;
  int mTipColor;
  
  public TipDrawable(Context paramContext, Resources paramResources, Bitmap paramBitmap)
  {
    super(paramResources, paramBitmap);
    init(paramContext);
  }
  
  public TipDrawable(Context paramContext, Resources paramResources, InputStream paramInputStream)
  {
    super(paramResources, paramInputStream);
    init(paramContext);
  }
  
  public TipDrawable(Context paramContext, Resources paramResources, String paramString)
  {
    super(paramResources, paramString);
    init(paramContext);
  }
  
  private void init(Context paramContext)
  {
    mContext = paramContext;
    paramContext = paramContext.obtainStyledAttributes(null, R.styleable.TipDrawable, R.attr.MeizuCommon_TipDrawableStyle, 0);
    mTipColor = paramContext.getColor(R.styleable.TipDrawable_mcTipColor, mContext.getResources().getColor(R.color.mc_tip_color));
    mRadius = paramContext.getDimensionPixelSize(R.styleable.TipDrawable_mcTipRadius, mContext.getResources().getDimensionPixelSize(R.dimen.mc_drawable_tip_radius));
    paramContext.recycle();
    mTiPaint = new Paint();
    mTiPaint.setAntiAlias(true);
    mTiPaint.setColor(mTipColor);
  }
  
  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    paramCanvas.drawCircle(getBoundsright + mRadius * 3 / 5, getBoundstop + mRadius, mRadius, mTiPaint);
  }
  
  public void setTipColor(int paramInt)
  {
    if (mTipColor != paramInt)
    {
      mTipColor = paramInt;
      mTiPaint.setColor(mTipColor);
      invalidateSelf();
    }
  }
  
  public void setTipRadius(int paramInt)
  {
    if (mRadius != paramInt)
    {
      mRadius = paramInt;
      invalidateSelf();
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.drawble.TipDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */