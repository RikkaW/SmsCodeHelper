package com.android.mms.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

public class PrivateEntryView
  extends View
{
  private Bitmap mBitmap;
  private int mBlueColor;
  private OnPrivateColorChangeListener mColorChangeListener;
  private int mCurrPosition;
  private int mGreenColor;
  private int mRedColor;
  
  public PrivateEntryView(Context paramContext)
  {
    super(paramContext);
  }
  
  public PrivateEntryView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public PrivateEntryView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    paramCanvas.save();
    if (mCurrPosition > 100)
    {
      int i = (int)((mCurrPosition - 100) / (getHeight() - 100) * 90.0F);
      paramCanvas.drawARGB(i, mRedColor, mGreenColor, mBlueColor);
      if (mColorChangeListener != null) {
        mColorChangeListener.onColorChange(Color.argb(i, mRedColor, mGreenColor, mBlueColor));
      }
    }
    if (mBitmap != null)
    {
      float f1 = (getHeight() - mBitmap.getHeight()) * 0.32F;
      if (mCurrPosition > f1)
      {
        float f2 = getHeight();
        float f3 = mCurrPosition;
        paramCanvas.drawBitmap(mBitmap, (getWidth() - mBitmap.getWidth()) / 2, f2 - (f3 - f1), null);
      }
    }
    paramCanvas.restore();
  }
  
  public void onScroll(int paramInt)
  {
    if (mCurrPosition != paramInt)
    {
      mCurrPosition = paramInt;
      invalidate();
      if ((paramInt == 0) && (mColorChangeListener != null)) {
        mColorChangeListener.onColorChange(0);
      }
    }
  }
  
  public void setBackgroundColor(int paramInt)
  {
    mRedColor = Color.red(paramInt);
    mGreenColor = Color.green(paramInt);
    mBlueColor = Color.blue(paramInt);
  }
  
  public void setBackgroundResId(int paramInt)
  {
    mBitmap = BitmapFactory.decodeResource(getResources(), paramInt);
  }
  
  public void setOnColorChangeListener(OnPrivateColorChangeListener paramOnPrivateColorChangeListener)
  {
    mColorChangeListener = paramOnPrivateColorChangeListener;
  }
  
  static abstract interface OnPrivateColorChangeListener
  {
    public abstract void onColorChange(int paramInt);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PrivateEntryView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */