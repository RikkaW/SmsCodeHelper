package com.meizu.common.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.meizu.common.R.drawable;

public class CustomDatePickerDialog$FlipView
  extends View
{
  private static final int DEPTH_CONSTANT = 1500;
  private static final int POLY_POINTS = 8;
  private Bitmap mBitmapGregorian;
  private int mBitmapHeight;
  private Bitmap mBitmapLunar;
  private int mBitmapWidth;
  float[] mDstPoly = new float[8];
  private float mFoldFactor = 0.0F;
  private boolean mIslunar = false;
  private Matrix[] mMatrices;
  private Paint mPaintShadow;
  private Rect mRect;
  float[] mSrcPoly = new float[8];
  
  public CustomDatePickerDialog$FlipView(Context paramContext)
  {
    super(paramContext);
    init();
  }
  
  public CustomDatePickerDialog$FlipView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }
  
  private void calculateMatrix()
  {
    float f1 = Math.round((1.0F - mFoldFactor) * mBitmapWidth / 2.0F);
    f1 = ((float)Math.sqrt(mBitmapWidth / 2 * (mBitmapWidth / 2) - f1 * f1) + 1500.0F) / 1500.0F;
    float f2 = mBitmapHeight * f1;
    mSrcPoly[0] = 0.0F;
    mSrcPoly[1] = 0.0F;
    mSrcPoly[2] = 0.0F;
    mSrcPoly[3] = mBitmapHeight;
    mSrcPoly[4] = (mBitmapWidth / 2);
    mSrcPoly[5] = 0.0F;
    mSrcPoly[6] = (mBitmapWidth / 2);
    mSrcPoly[7] = mBitmapHeight;
    if (mFoldFactor < 0.5D) {}
    for (f1 = mFoldFactor;; f1 = 1.0F - mFoldFactor)
    {
      mMatrices[0].reset();
      mDstPoly[0] = (mBitmapWidth * f1);
      mDstPoly[1] = ((mBitmapHeight - f2) / 2.0F);
      mDstPoly[2] = mDstPoly[0];
      mDstPoly[3] = (mBitmapHeight + (f2 - mBitmapHeight) / 2.0F);
      mDstPoly[4] = (mBitmapWidth / 2);
      mDstPoly[5] = 0.0F;
      mDstPoly[6] = mDstPoly[4];
      mDstPoly[7] = mBitmapHeight;
      i = 0;
      while (i < 8)
      {
        mDstPoly[i] = Math.round(mDstPoly[i]);
        i += 1;
      }
    }
    mMatrices[0].setPolyToPoly(mSrcPoly, 0, mDstPoly, 0, 4);
    mMatrices[1].reset();
    mDstPoly[0] = (mBitmapWidth / 2);
    mDstPoly[1] = 0.0F;
    mDstPoly[2] = mDstPoly[0];
    mDstPoly[3] = mBitmapHeight;
    mDstPoly[4] = (mBitmapWidth - mBitmapWidth * f1);
    mDstPoly[5] = ((mBitmapHeight - f2) / 2.0F);
    mDstPoly[6] = mDstPoly[4];
    mDstPoly[7] = (mBitmapHeight + (f2 - mBitmapHeight) / 2.0F);
    int i = 0;
    while (i < 8)
    {
      mDstPoly[i] = Math.round(mDstPoly[i]);
      i += 1;
    }
    mMatrices[1].setPolyToPoly(mSrcPoly, 0, mDstPoly, 0, 4);
  }
  
  private void init()
  {
    int i = 0;
    mRect = new Rect(0, 0, mBitmapWidth, mBitmapHeight);
    mMatrices = new Matrix[2];
    mBitmapGregorian = BitmapFactory.decodeResource(getResources(), R.drawable.mc_ic_popup_calendar_gregorian);
    mBitmapLunar = BitmapFactory.decodeResource(getResources(), R.drawable.mc_ic_popup_calendar_lunar);
    mBitmapWidth = mBitmapLunar.getWidth();
    mBitmapHeight = mBitmapLunar.getHeight();
    while (i < 2)
    {
      mMatrices[i] = new Matrix();
      i += 1;
    }
    mPaintShadow = new Paint();
    Object localObject = new ColorMatrix();
    ((ColorMatrix)localObject).setSaturation(0.8F);
    localObject = new ColorMatrixColorFilter((ColorMatrix)localObject);
    mPaintShadow.setColorFilter((ColorFilter)localObject);
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    int i = 1;
    calculateMatrix();
    Bitmap localBitmap;
    if (mFoldFactor == 0.0F)
    {
      if (mIslunar) {}
      for (localBitmap = mBitmapLunar;; localBitmap = mBitmapGregorian)
      {
        paramCanvas.drawBitmap(localBitmap, 0.0F, 0.0F, null);
        return;
      }
    }
    if (mFoldFactor == 1.0F)
    {
      if (mIslunar) {}
      for (localBitmap = mBitmapGregorian;; localBitmap = mBitmapLunar)
      {
        paramCanvas.drawBitmap(localBitmap, 0.0F, 0.0F, null);
        return;
      }
    }
    mRect.set(0, 0, mBitmapWidth, mBitmapHeight);
    paramCanvas.drawBitmap(mBitmapLunar, mRect, mRect, mPaintShadow);
    mRect.set(0, 0, mBitmapWidth / 2, mBitmapHeight);
    paramCanvas.drawBitmap(mBitmapGregorian, mRect, mRect, null);
    paramCanvas.save();
    if (mIslunar) {
      if (mFoldFactor < 0.5F)
      {
        mRect.set(0, 0, mBitmapWidth / 2, mBitmapHeight);
        localBitmap = mBitmapLunar;
        i = 0;
      }
    }
    for (;;)
    {
      paramCanvas.concat(mMatrices[i]);
      paramCanvas.clipRect(0, 0, mRect.right - mRect.left, mRect.bottom - mRect.top);
      paramCanvas.translate(-mRect.left, 0.0F);
      paramCanvas.drawBitmap(localBitmap, 0.0F, 0.0F, null);
      paramCanvas.restore();
      return;
      mRect.set(mBitmapWidth / 2, 0, mBitmapWidth, mBitmapHeight);
      localBitmap = mBitmapGregorian;
      continue;
      if (mFoldFactor < 0.5F)
      {
        mRect.set(mBitmapWidth / 2, 0, mBitmapWidth, mBitmapHeight);
        localBitmap = mBitmapGregorian;
      }
      else
      {
        mRect.set(0, 0, mBitmapWidth / 2, mBitmapHeight);
        localBitmap = mBitmapLunar;
        i = 0;
      }
    }
  }
  
  public void setFilpViewPrefer(boolean paramBoolean)
  {
    mIslunar = paramBoolean;
  }
  
  public void setFoldFactor(float paramFloat)
  {
    mFoldFactor = paramFloat;
    postInvalidate();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CustomDatePickerDialog.FlipView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */