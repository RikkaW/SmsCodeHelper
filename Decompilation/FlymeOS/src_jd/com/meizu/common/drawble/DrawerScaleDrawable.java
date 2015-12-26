package com.meizu.common.drawble;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import com.meizu.common.R.color;
import com.meizu.common.R.dimen;
import com.meizu.common.R.styleable;
import com.meizu.common.util.ResourceUtils;

public class DrawerScaleDrawable
  extends Drawable
{
  private int mDefaultColor;
  private int mHeight;
  private final Paint mPaint = new Paint();
  private final Paint mPaintFill = new Paint(1);
  private final Path mPath = new Path();
  private int mPathDefaultLength;
  private int mPathDistance;
  private int mPathMinLength;
  private int mPathThickness;
  private float mProgress;
  private int mThemeColor;
  private int mWidth;
  
  public DrawerScaleDrawable(Context paramContext)
  {
    Resources localResources = paramContext.getResources();
    paramContext = paramContext.obtainStyledAttributes(R.styleable.MZTheme);
    mThemeColor = paramContext.getColor(R.styleable.MZTheme_mzThemeColor, localResources.getColor(R.color.mc_default_theme_color));
    paramContext.recycle();
    mDefaultColor = localResources.getColor(R.color.mc_drawerscaledrawable_default_color);
    mPathThickness = localResources.getDimensionPixelSize(R.dimen.mc_drawerscaledrawable_path_thickness);
    mPathDistance = localResources.getDimensionPixelSize(R.dimen.mc_drawerscaledrawable_path_distance);
    mPathDefaultLength = localResources.getDimensionPixelSize(R.dimen.mc_drawerscaledrawable_path_length);
    mPathMinLength = localResources.getDimensionPixelSize(R.dimen.mc_drawerscaledrawable_path_min_length);
    if (mPathThickness % 2 != 0) {
      mPathThickness += 1;
    }
    mHeight = (mPathThickness * 3 + mPathDistance * 2);
    mWidth = mPathDefaultLength;
    mPaint.setAntiAlias(true);
    mPaint.setColor(mDefaultColor);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeJoin(Paint.Join.ROUND);
    mPaint.setStrokeCap(Paint.Cap.SQUARE);
    mPaint.setStrokeWidth(mPathThickness);
    mPaintFill.setStyle(Paint.Style.FILL);
    mPaintFill.setColor(mDefaultColor);
  }
  
  private static float lerp(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return (paramFloat2 - paramFloat1) * paramFloat3 + paramFloat1;
  }
  
  public void draw(Canvas paramCanvas)
  {
    mPath.rewind();
    int k = mPathThickness / 2;
    float f = lerp(mPathDefaultLength, mPathMinLength, mProgress) - mPathThickness;
    int m = ResourceUtils.getGradualColor(mDefaultColor, mThemeColor, mProgress, 1);
    mPaintFill.setColor(m);
    int i = 0;
    int j = k;
    while (i < 3)
    {
      paramCanvas.drawCircle(0, j, k, mPaintFill);
      paramCanvas.drawCircle((int)(0 + (mPathThickness + f)), j, k, mPaintFill);
      j += mPathDistance + mPathThickness;
      i += 1;
    }
    mPaint.setColor(m);
    mPath.moveTo(k, k);
    mPath.rLineTo(f, 0.0F);
    i = mPathDistance + mPathThickness + k;
    mPath.moveTo(k, i);
    mPath.rLineTo(f, 0.0F);
    j = mPathDistance;
    m = mPathThickness;
    mPath.moveTo(k, i + (j + m));
    mPath.rLineTo(f, 0.0F);
    mPath.moveTo(0.0F, 0.0F);
    mPath.close();
    paramCanvas.drawPath(mPath, mPaint);
  }
  
  public int getIntrinsicHeight()
  {
    return mHeight;
  }
  
  public int getIntrinsicWidth()
  {
    return mWidth;
  }
  
  public int getOpacity()
  {
    return -3;
  }
  
  public void setAlpha(int paramInt)
  {
    mPaint.setAlpha(paramInt);
    mPaintFill.setAlpha(paramInt);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    mPaint.setColorFilter(paramColorFilter);
    mPaintFill.setColorFilter(paramColorFilter);
  }
  
  public void setProgress(float paramFloat)
  {
    mProgress = paramFloat;
    invalidateSelf();
  }
  
  public void setProgress(int paramInt1, int paramInt2, int paramInt3)
  {
    mProgress = (paramInt1 / (paramInt3 - paramInt2));
    invalidateSelf();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.drawble.DrawerScaleDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */