package com.meizu.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.meizu.common.R.styleable;

public class RoundCornerImageView
  extends ImageView
{
  private Drawable mDstRoundCornerDrawable;
  private float mRadiusX;
  private float mRadiusY;
  private boolean mRecycle;
  
  public RoundCornerImageView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public RoundCornerImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public RoundCornerImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RoundCornerImageView, paramInt, 0);
    mRadiusX = paramContext.getFloat(R.styleable.RoundCornerImageView_mzCornerRadiusX, 0.0F);
    mRadiusY = paramContext.getFloat(R.styleable.RoundCornerImageView_mzCornerRadiusY, 0.0F);
    paramContext.recycle();
  }
  
  private void drawRoundCorner()
  {
    Drawable localDrawable;
    boolean bool;
    Bitmap localBitmap1;
    int i;
    int j;
    Object localObject;
    Bitmap localBitmap2;
    if (getDrawable() != null)
    {
      localDrawable = mDstRoundCornerDrawable;
      bool = mRecycle;
      mRecycle = false;
      if ((getDrawable() instanceof BitmapDrawable))
      {
        localBitmap1 = ((BitmapDrawable)getDrawable()).getBitmap();
        i = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        j = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        if ((localBitmap1.getWidth() >= i) || (localBitmap1.getHeight() >= j)) {
          break label353;
        }
        localObject = localBitmap1;
        if (localObject != null)
        {
          if ((mRadiusX == 0.0F) || (mRadiusY == 0.0F)) {
            break label465;
          }
          localBitmap2 = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
          Canvas localCanvas = new Canvas(localBitmap2);
          int k = i - ((Bitmap)localObject).getWidth();
          int m = j - ((Bitmap)localObject).getHeight();
          Rect localRect = new Rect(k / 2, m / 2, i - k / 2, j - m / 2);
          RectF localRectF = new RectF(new Rect(0, 0, i, j));
          Paint localPaint = new Paint();
          localPaint.setColor(Color.argb(255, 255, 255, 255));
          localPaint.setAntiAlias(true);
          localCanvas.drawRoundRect(localRectF, mRadiusX, mRadiusY, localPaint);
          localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
          localCanvas.drawBitmap((Bitmap)localObject, null, localRect, localPaint);
          if (localObject != localBitmap1) {
            ((Bitmap)localObject).recycle();
          }
          mDstRoundCornerDrawable = new BitmapDrawable(getContext().getResources(), localBitmap2);
          mRecycle = true;
        }
      }
    }
    for (;;)
    {
      super.setImageDrawable(mDstRoundCornerDrawable);
      if ((localDrawable != null) && (bool)) {
        ((BitmapDrawable)localDrawable).getBitmap().recycle();
      }
      return;
      label353:
      if (localBitmap1.getWidth() < i)
      {
        localObject = Bitmap.createBitmap(localBitmap1, 0, (localBitmap1.getHeight() - j) / 2, localBitmap1.getWidth(), j);
        mRecycle = true;
        break;
      }
      if (localBitmap1.getHeight() < j)
      {
        localObject = Bitmap.createBitmap(localBitmap1, (localBitmap1.getWidth() - i) / 2, 0, i, localBitmap1.getHeight());
        mRecycle = true;
        break;
      }
      localBitmap2 = ThumbnailUtils.extractThumbnail(localBitmap1, i, j);
      localObject = localBitmap2;
      if (localBitmap2 == localBitmap1) {
        break;
      }
      mRecycle = true;
      localObject = localBitmap2;
      break;
      label465:
      mDstRoundCornerDrawable = new BitmapDrawable(getContext().getResources(), (Bitmap)localObject);
    }
  }
  
  public float getRadiusX()
  {
    return mRadiusX;
  }
  
  public float getRadiusY()
  {
    return mRadiusY;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (mDstRoundCornerDrawable != getDrawable()) {
      drawRoundCorner();
    }
  }
  
  public void setImageBitmap(Bitmap paramBitmap)
  {
    super.setImageBitmap(paramBitmap);
    if ((mDstRoundCornerDrawable != null) && (mDstRoundCornerDrawable != getDrawable()) && (mRecycle))
    {
      ((BitmapDrawable)mDstRoundCornerDrawable).getBitmap().recycle();
      mDstRoundCornerDrawable = null;
      mRecycle = false;
    }
  }
  
  public void setImageDrawable(Drawable paramDrawable)
  {
    super.setImageDrawable(paramDrawable);
    if ((mDstRoundCornerDrawable != null) && (mDstRoundCornerDrawable != getDrawable()) && (mRecycle))
    {
      ((BitmapDrawable)mDstRoundCornerDrawable).getBitmap().recycle();
      mDstRoundCornerDrawable = null;
      mRecycle = false;
    }
  }
  
  public void setImageResource(int paramInt)
  {
    super.setImageResource(paramInt);
    if ((mDstRoundCornerDrawable != null) && (mDstRoundCornerDrawable != getDrawable()) && (mRecycle))
    {
      ((BitmapDrawable)mDstRoundCornerDrawable).getBitmap().recycle();
      mDstRoundCornerDrawable = null;
      mRecycle = false;
    }
  }
  
  public void setImageURI(Uri paramUri)
  {
    super.setImageURI(paramUri);
    if ((mDstRoundCornerDrawable != null) && (mDstRoundCornerDrawable != getDrawable()) && (mRecycle))
    {
      ((BitmapDrawable)mDstRoundCornerDrawable).getBitmap().recycle();
      mDstRoundCornerDrawable = null;
      mRecycle = false;
    }
  }
  
  public void setRadius(float paramFloat1, float paramFloat2)
  {
    if ((getDrawable() == null) || (getDrawable() != mDstRoundCornerDrawable))
    {
      mRadiusX = paramFloat1;
      mRadiusY = paramFloat2;
      invalidate();
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.RoundCornerImageView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */