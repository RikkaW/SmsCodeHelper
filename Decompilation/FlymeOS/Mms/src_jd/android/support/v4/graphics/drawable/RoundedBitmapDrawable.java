package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

public abstract class RoundedBitmapDrawable
  extends Drawable
{
  private static final int DEFAULT_PAINT_FLAGS = 6;
  private boolean mApplyGravity = true;
  Bitmap mBitmap;
  private int mBitmapHeight;
  private BitmapShader mBitmapShader;
  private int mBitmapWidth;
  private float mCornerRadius;
  final Rect mDstRect = new Rect();
  final RectF mDstRectF = new RectF();
  private int mGravity = 119;
  private Paint mPaint = new Paint(6);
  private int mTargetDensity = 160;
  
  RoundedBitmapDrawable(Resources paramResources, Bitmap paramBitmap)
  {
    if (paramResources != null) {
      mTargetDensity = getDisplayMetricsdensityDpi;
    }
    mBitmap = paramBitmap;
    if (mBitmap != null)
    {
      computeBitmapSize();
      mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
      return;
    }
    mBitmapHeight = -1;
    mBitmapWidth = -1;
  }
  
  private void computeBitmapSize()
  {
    mBitmapWidth = mBitmap.getScaledWidth(mTargetDensity);
    mBitmapHeight = mBitmap.getScaledHeight(mTargetDensity);
  }
  
  private static boolean isGreaterThanZero(float paramFloat)
  {
    return Float.compare(paramFloat, 0.0F) > 0;
  }
  
  public void draw(Canvas paramCanvas)
  {
    Bitmap localBitmap = mBitmap;
    if (localBitmap == null) {
      return;
    }
    updateDstRect();
    Paint localPaint = mPaint;
    if (localPaint.getShader() == null)
    {
      paramCanvas.drawBitmap(localBitmap, null, mDstRect, localPaint);
      return;
    }
    paramCanvas.drawRoundRect(mDstRectF, mCornerRadius, mCornerRadius, localPaint);
  }
  
  public int getAlpha()
  {
    return mPaint.getAlpha();
  }
  
  public final Bitmap getBitmap()
  {
    return mBitmap;
  }
  
  public ColorFilter getColorFilter()
  {
    return mPaint.getColorFilter();
  }
  
  public float getCornerRadius()
  {
    return mCornerRadius;
  }
  
  public int getGravity()
  {
    return mGravity;
  }
  
  public int getIntrinsicHeight()
  {
    return mBitmapHeight;
  }
  
  public int getIntrinsicWidth()
  {
    return mBitmapWidth;
  }
  
  public int getOpacity()
  {
    if (mGravity != 119) {}
    Bitmap localBitmap;
    do
    {
      return -3;
      localBitmap = mBitmap;
    } while ((localBitmap == null) || (localBitmap.hasAlpha()) || (mPaint.getAlpha() < 255) || (isGreaterThanZero(mCornerRadius)));
    return -1;
  }
  
  public final Paint getPaint()
  {
    return mPaint;
  }
  
  void gravityCompatApply(int paramInt1, int paramInt2, int paramInt3, Rect paramRect1, Rect paramRect2)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean hasAntiAlias()
  {
    return mPaint.isAntiAlias();
  }
  
  public boolean hasMipMap()
  {
    throw new UnsupportedOperationException();
  }
  
  public void setAlpha(int paramInt)
  {
    if (paramInt != mPaint.getAlpha())
    {
      mPaint.setAlpha(paramInt);
      invalidateSelf();
    }
  }
  
  public void setAntiAlias(boolean paramBoolean)
  {
    mPaint.setAntiAlias(paramBoolean);
    invalidateSelf();
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    mPaint.setColorFilter(paramColorFilter);
    invalidateSelf();
  }
  
  public void setCornerRadius(float paramFloat)
  {
    if (isGreaterThanZero(paramFloat)) {
      mPaint.setShader(mBitmapShader);
    }
    for (;;)
    {
      mCornerRadius = paramFloat;
      return;
      mPaint.setShader(null);
    }
  }
  
  public void setDither(boolean paramBoolean)
  {
    mPaint.setDither(paramBoolean);
    invalidateSelf();
  }
  
  public void setFilterBitmap(boolean paramBoolean)
  {
    mPaint.setFilterBitmap(paramBoolean);
    invalidateSelf();
  }
  
  public void setGravity(int paramInt)
  {
    if (mGravity != paramInt)
    {
      mGravity = paramInt;
      mApplyGravity = true;
      invalidateSelf();
    }
  }
  
  public void setMipMap(boolean paramBoolean)
  {
    throw new UnsupportedOperationException();
  }
  
  public void setTargetDensity(int paramInt)
  {
    if (mTargetDensity != paramInt)
    {
      int i = paramInt;
      if (paramInt == 0) {
        i = 160;
      }
      mTargetDensity = i;
      if (mBitmap != null) {
        computeBitmapSize();
      }
      invalidateSelf();
    }
  }
  
  public void setTargetDensity(Canvas paramCanvas)
  {
    setTargetDensity(paramCanvas.getDensity());
  }
  
  public void setTargetDensity(DisplayMetrics paramDisplayMetrics)
  {
    setTargetDensity(densityDpi);
  }
  
  void updateDstRect()
  {
    if (mApplyGravity)
    {
      gravityCompatApply(mGravity, mBitmapWidth, mBitmapHeight, getBounds(), mDstRect);
      mDstRectF.set(mDstRect);
      mApplyGravity = false;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.graphics.drawable.RoundedBitmapDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */