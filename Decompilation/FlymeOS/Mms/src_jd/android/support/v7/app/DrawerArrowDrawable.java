package android.support.v7.app;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.style;
import android.support.v7.appcompat.R.styleable;

abstract class DrawerArrowDrawable
  extends Drawable
{
  private static final float ARROW_HEAD_ANGLE = (float)Math.toRadians(45.0D);
  private final float mBarGap;
  private final float mBarSize;
  private final float mBarThickness;
  private float mCenterOffset;
  private float mMaxCutForBarSize;
  private final float mMiddleArrowSize;
  private final Paint mPaint = new Paint();
  private final Path mPath = new Path();
  private float mProgress;
  private final int mSize;
  private final boolean mSpin;
  private final float mTopBottomArrowSize;
  private boolean mVerticalMirror = false;
  
  DrawerArrowDrawable(Context paramContext)
  {
    paramContext = paramContext.getTheme().obtainStyledAttributes(null, R.styleable.DrawerArrowToggle, R.attr.drawerArrowStyle, R.style.Base_Widget_AppCompat_DrawerArrowToggle);
    mPaint.setAntiAlias(true);
    mPaint.setColor(paramContext.getColor(R.styleable.DrawerArrowToggle_color, 0));
    mSize = paramContext.getDimensionPixelSize(R.styleable.DrawerArrowToggle_drawableSize, 0);
    mBarSize = Math.round(paramContext.getDimension(R.styleable.DrawerArrowToggle_barSize, 0.0F));
    mTopBottomArrowSize = Math.round(paramContext.getDimension(R.styleable.DrawerArrowToggle_topBottomBarArrowSize, 0.0F));
    mBarThickness = paramContext.getDimension(R.styleable.DrawerArrowToggle_thickness, 0.0F);
    mBarGap = Math.round(paramContext.getDimension(R.styleable.DrawerArrowToggle_gapBetweenBars, 0.0F));
    mSpin = paramContext.getBoolean(R.styleable.DrawerArrowToggle_spinBars, true);
    mMiddleArrowSize = paramContext.getDimension(R.styleable.DrawerArrowToggle_middleBarArrowSize, 0.0F);
    mCenterOffset = ((int)(mSize - mBarThickness * 3.0F - mBarGap * 2.0F) / 4 * 2);
    mCenterOffset = ((float)(mCenterOffset + (mBarThickness * 1.5D + mBarGap)));
    paramContext.recycle();
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeJoin(Paint.Join.MITER);
    mPaint.setStrokeCap(Paint.Cap.BUTT);
    mPaint.setStrokeWidth(mBarThickness);
    mMaxCutForBarSize = ((float)(mBarThickness / 2.0F * Math.cos(ARROW_HEAD_ANGLE)));
  }
  
  private static float lerp(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return (paramFloat2 - paramFloat1) * paramFloat3 + paramFloat1;
  }
  
  public void draw(Canvas paramCanvas)
  {
    Rect localRect = getBounds();
    boolean bool = isLayoutRtl();
    float f5 = lerp(mBarSize, mTopBottomArrowSize, mProgress);
    float f3 = lerp(mBarSize, mMiddleArrowSize, mProgress);
    float f4 = Math.round(lerp(0.0F, mMaxCutForBarSize, mProgress));
    float f6 = lerp(0.0F, ARROW_HEAD_ANGLE, mProgress);
    float f1;
    float f2;
    label92:
    int i;
    if (bool)
    {
      f1 = 0.0F;
      if (!bool) {
        break label320;
      }
      f2 = 180.0F;
      f1 = lerp(f1, f2, mProgress);
      f2 = (float)Math.round(f5 * Math.cos(f6));
      f5 = (float)Math.round(f5 * Math.sin(f6));
      mPath.rewind();
      f6 = lerp(mBarGap + mBarThickness, -mMaxCutForBarSize, mProgress);
      float f7 = -f3 / 2.0F;
      mPath.moveTo(f7 + f4, 0.0F);
      mPath.rLineTo(f3 - f4 * 2.0F, 0.0F);
      mPath.moveTo(f7, f6);
      mPath.rLineTo(f2, f5);
      mPath.moveTo(f7, -f6);
      mPath.rLineTo(f2, -f5);
      mPath.close();
      paramCanvas.save();
      paramCanvas.translate(localRect.centerX(), mCenterOffset);
      if (!mSpin) {
        break label331;
      }
      if (!(mVerticalMirror ^ bool)) {
        break label325;
      }
      i = -1;
      label288:
      paramCanvas.rotate(i * f1);
    }
    for (;;)
    {
      paramCanvas.drawPath(mPath, mPaint);
      paramCanvas.restore();
      return;
      f1 = -180.0F;
      break;
      label320:
      f2 = 0.0F;
      break label92;
      label325:
      i = 1;
      break label288;
      label331:
      if (bool) {
        paramCanvas.rotate(180.0F);
      }
    }
  }
  
  public int getIntrinsicHeight()
  {
    return mSize;
  }
  
  public int getIntrinsicWidth()
  {
    return mSize;
  }
  
  public int getOpacity()
  {
    return -3;
  }
  
  public float getProgress()
  {
    return mProgress;
  }
  
  public boolean isAutoMirrored()
  {
    return true;
  }
  
  abstract boolean isLayoutRtl();
  
  public void setAlpha(int paramInt)
  {
    mPaint.setAlpha(paramInt);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    mPaint.setColorFilter(paramColorFilter);
  }
  
  public void setProgress(float paramFloat)
  {
    mProgress = paramFloat;
    invalidateSelf();
  }
  
  protected void setVerticalMirror(boolean paramBoolean)
  {
    mVerticalMirror = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.DrawerArrowDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */