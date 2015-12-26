package com.android.mms.ui;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import com.android.mms.MmsApp;

public class ThumbnailView
  extends ImageView
{
  private static Xfermode CLEAR_MODE;
  private static Xfermode SRC_IN_MODE = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
  private static String TAG;
  private static int sLargeBitmapLongEdgeSize;
  private static int sLargeBitmapShortEdgeSize;
  private static int sLargeBitmapSize;
  private static int sMaskPaddingSize;
  private static int sMinBitmapSize;
  private static int sNormalBitmapSize;
  private static Paint sPaint;
  private static int sSmallBitmapLongEdgeSize;
  private static int sSmallBitmapShortEdgeSize;
  private static int sSmallBitmapSize;
  private static int sZoomSize;
  private int mAlignType;
  private Drawable mBackgroundDrawable;
  private boolean mIsZoomAsSquare;
  private int mMaskSizeType = 2;
  private Drawable mNormalMaskDrawable;
  private Drawable mPressedMaskDrawable;
  private Rect mScaleRect = new Rect();
  private Rect mScaleTargetRect = new Rect();
  private Rect mSrcRect = new Rect();
  private int mUserDstHeight = 0;
  private int mUserDstWidth = 0;
  
  static
  {
    CLEAR_MODE = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
    TAG = "ThumbnailView";
    sPaint = new Paint();
    long l1 = System.currentTimeMillis();
    Resources localResources = MmsApp.getApp().getResources();
    sLargeBitmapSize = localResources.getDimensionPixelSize(2131427404);
    sSmallBitmapSize = localResources.getDimensionPixelSize(2131427406);
    sZoomSize = localResources.getDimensionPixelSize(2131427403);
    sMaskPaddingSize = localResources.getDimensionPixelSize(2131427402);
    sLargeBitmapLongEdgeSize = localResources.getDimensionPixelSize(2131427404);
    sLargeBitmapShortEdgeSize = localResources.getDimensionPixelSize(2131427405);
    sSmallBitmapLongEdgeSize = localResources.getDimensionPixelSize(2131427406);
    sSmallBitmapShortEdgeSize = localResources.getDimensionPixelSize(2131427407);
    sMinBitmapSize = localResources.getDimensionPixelSize(2131427401);
    sNormalBitmapSize = localResources.getDimensionPixelSize(2131427408);
    long l2 = System.currentTimeMillis();
    Log.d(TAG, "Initialize cached drawables of ThumbnailView: " + (l2 - l1) + "ms");
  }
  
  public ThumbnailView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setLayerType(1, null);
  }
  
  public static void initDummy() {}
  
  private void setZoomAsSquare(boolean paramBoolean)
  {
    mIsZoomAsSquare = paramBoolean;
  }
  
  public void draw(Canvas paramCanvas)
  {
    Object localObject = getDrawable();
    if ((mBackgroundDrawable == null) || (localObject == null)) {
      if (localObject != null) {
        ((Drawable)localObject).draw(paramCanvas);
      }
    }
    do
    {
      return;
      super.draw(paramCanvas);
      return;
      int i = ((Drawable)localObject).getIntrinsicWidth();
      int j = ((Drawable)localObject).getIntrinsicHeight();
      if ((i <= 0) || (j <= 0))
      {
        super.draw(paramCanvas);
        return;
      }
      mBackgroundDrawable.setBounds(mSrcRect);
      mBackgroundDrawable.draw(paramCanvas);
      if ((localObject instanceof BitmapDrawable))
      {
        localObject = (BitmapDrawable)localObject;
        Paint localPaint = ((BitmapDrawable)localObject).getPaint();
        Xfermode localXfermode = localPaint.getXfermode();
        localPaint.setXfermode(SRC_IN_MODE);
        paramCanvas.drawBitmap(((BitmapDrawable)localObject).getBitmap(), mScaleRect, mScaleTargetRect, localPaint);
        localPaint.setXfermode(localXfermode);
      }
      while ((isPressed()) && (mPressedMaskDrawable != null) && ((mAlignType == 1) || (mAlignType == 2)))
      {
        mPressedMaskDrawable.setBounds(mSrcRect);
        mPressedMaskDrawable.draw(paramCanvas);
        return;
        ((Drawable)localObject).draw(paramCanvas);
      }
    } while (mNormalMaskDrawable == null);
    mNormalMaskDrawable.setBounds(mSrcRect);
    mNormalMaskDrawable.draw(paramCanvas);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    Drawable localDrawable = getDrawable();
    if ((mBackgroundDrawable == null) || (localDrawable == null))
    {
      if (localDrawable != null)
      {
        setMeasuredDimension(localDrawable.getIntrinsicWidth(), localDrawable.getIntrinsicHeight());
        return;
      }
      super.onMeasure(paramInt1, paramInt2);
      return;
    }
    int j = localDrawable.getIntrinsicWidth();
    int k = localDrawable.getIntrinsicHeight();
    if ((j <= 0) || (k <= 0))
    {
      super.onMeasure(paramInt1, paramInt2);
      return;
    }
    int i;
    double d;
    if (mMaskSizeType == 0)
    {
      paramInt1 = mUserDstWidth;
      paramInt2 = mUserDstHeight;
      if (mAlignType != 1)
      {
        i = paramInt1;
        if (mAlignType != 2) {}
      }
      else
      {
        i = paramInt1 + sZoomSize;
      }
      d = Math.max(i / j, paramInt2 / k);
      if ((int)(j * d) <= i) {
        break label452;
      }
      mScaleRect.set((int)(j / 2.0D - i / (2.0D * d)), 0, (int)(j / 2.0D + i / (2.0D * d)), k);
      label193:
      if ((mAlignType != 1) && (mAlignType != 2)) {
        break label499;
      }
      i += sMaskPaddingSize;
      mSrcRect.set(0, 0, i - sZoomSize, paramInt2);
      label233:
      if (mAlignType != 1) {
        break label514;
      }
      mScaleTargetRect.set(sMaskPaddingSize, 0, i, paramInt2);
    }
    for (;;)
    {
      setMeasuredDimension(mSrcRect.width(), mSrcRect.height());
      return;
      mMaskSizeType = 2;
      if (mIsZoomAsSquare) {}
      for (paramInt1 = sLargeBitmapShortEdgeSize;; paramInt1 = sLargeBitmapLongEdgeSize)
      {
        paramInt2 = sLargeBitmapShortEdgeSize;
        if (mAlignType != 0) {
          break label347;
        }
        if ((j >= sMinBitmapSize) && (k >= sMinBitmapSize)) {
          break label338;
        }
        paramInt2 = sSmallBitmapShortEdgeSize;
        paramInt1 = paramInt2;
        mMaskSizeType = 1;
        break;
      }
      label338:
      paramInt2 = sNormalBitmapSize;
      paramInt1 = paramInt2;
      break;
      label347:
      if (j >= k)
      {
        if (k >= sMinBitmapSize) {
          break;
        }
        if (mIsZoomAsSquare) {}
        for (paramInt1 = sSmallBitmapShortEdgeSize;; paramInt1 = sSmallBitmapLongEdgeSize)
        {
          paramInt2 = sSmallBitmapShortEdgeSize;
          mMaskSizeType = 1;
          break;
        }
      }
      paramInt1 = sLargeBitmapShortEdgeSize;
      if (mIsZoomAsSquare)
      {
        paramInt2 = sLargeBitmapShortEdgeSize;
        label407:
        if (j >= sMinBitmapSize) {
          break label443;
        }
        paramInt1 = sSmallBitmapShortEdgeSize;
        if (!mIsZoomAsSquare) {
          break label445;
        }
      }
      label443:
      label445:
      for (paramInt2 = sSmallBitmapShortEdgeSize;; paramInt2 = sSmallBitmapLongEdgeSize)
      {
        mMaskSizeType = 1;
        break;
        paramInt2 = sLargeBitmapLongEdgeSize;
        break label407;
        break;
      }
      label452:
      mScaleRect.set(0, (int)(k / 2.0D - paramInt2 / (2.0D * d)), j, (int)(k / 2.0D + paramInt2 / (2.0D * d)));
      break label193;
      label499:
      mSrcRect.set(0, 0, i, paramInt2);
      break label233;
      label514:
      if (mAlignType == 2) {
        mScaleTargetRect.set(-sZoomSize, 0, i - sMaskPaddingSize - sZoomSize, paramInt2);
      } else {
        mScaleTargetRect.set(0, 0, i, paramInt2);
      }
    }
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable, int paramInt)
  {
    super.setBackground(paramDrawable);
    mBackgroundDrawable = paramDrawable;
    mAlignType = paramInt;
  }
  
  public void setImageBitmap(Bitmap paramBitmap)
  {
    setImageBitmap(paramBitmap, false);
    mMaskSizeType = 2;
  }
  
  public void setImageBitmap(Bitmap paramBitmap, boolean paramBoolean)
  {
    super.setImageBitmap(paramBitmap);
    setZoomAsSquare(paramBoolean);
    mMaskSizeType = 2;
  }
  
  public void setImageDrawable(Drawable paramDrawable)
  {
    setImageDrawable(paramDrawable, false);
    mMaskSizeType = 2;
  }
  
  public void setImageDrawable(Drawable paramDrawable, boolean paramBoolean)
  {
    super.setImageDrawable(paramDrawable);
    setZoomAsSquare(paramBoolean);
    mMaskSizeType = 2;
  }
  
  public boolean setImageDrawable(Drawable paramDrawable1, Drawable paramDrawable2, int paramInt1, int paramInt2)
  {
    if ((paramInt1 > sLargeBitmapSize) || (paramInt2 > sLargeBitmapSize)) {
      return false;
    }
    super.setImageDrawable(paramDrawable2);
    setZoomAsSquare(false);
    mBackgroundDrawable = paramDrawable1;
    mMaskSizeType = 0;
    mAlignType = 0;
    mUserDstWidth = paramInt1;
    mUserDstHeight = paramInt2;
    return true;
  }
  
  public void setImageResource(int paramInt)
  {
    setImageResource(paramInt, false);
    mMaskSizeType = 2;
  }
  
  public void setImageResource(int paramInt, boolean paramBoolean)
  {
    super.setImageResource(paramInt);
    setZoomAsSquare(paramBoolean);
    mMaskSizeType = 2;
    mUserDstWidth = 0;
    mUserDstHeight = 0;
  }
  
  public void setMaskDrawable(Drawable paramDrawable1, Drawable paramDrawable2)
  {
    mPressedMaskDrawable = paramDrawable1;
    mNormalMaskDrawable = paramDrawable2;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ThumbnailView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */