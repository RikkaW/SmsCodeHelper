package com.meizu.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.meizu.common.R.color;
import com.meizu.common.R.styleable;

public class ImageViewShadow
  extends ImageView
{
  private static final int DEFAULT_OFFSETY = -1;
  private static final int DEFAULT_RADIUS = 1;
  private BlurMaskFilter mBlurMaskFilter;
  private float mBlurRadius;
  private int mOffsetX;
  private int mOffsetY;
  private int[] offsetXY;
  
  public ImageViewShadow(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ImageViewShadow(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public ImageViewShadow(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ImageViewShadow, paramInt, 0);
    setBlurRadius(paramContext.getFloat(R.styleable.ImageViewShadow_mcBlurRadius, 1.0F));
    setOffsetX(paramContext.getInt(R.styleable.ImageViewShadow_mcOffsetX, -Math.round(mBlurRadius)));
    setOffsetY(paramContext.getInt(R.styleable.ImageViewShadow_mcOffsetY, -1));
    paramContext.recycle();
    mBlurMaskFilter = new BlurMaskFilter(mBlurRadius, BlurMaskFilter.Blur.NORMAL);
    setImageShadowDrawable(getDrawable());
  }
  
  private Drawable convertDrawableShadow(Resources paramResources, Drawable paramDrawable)
  {
    Object localObject = null;
    Bitmap localBitmap = drawable2Bitmap(paramDrawable);
    if (localBitmap != null)
    {
      localObject = new Paint();
      ((Paint)localObject).setColor(paramResources.getColor(R.color.mc_image_view_shadow));
      ((Paint)localObject).setMaskFilter(mBlurMaskFilter);
      offsetXY = new int[2];
      localObject = localBitmap.extractAlpha((Paint)localObject, offsetXY);
      paramDrawable = Bitmap.createBitmap(paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(paramDrawable);
      Paint localPaint = new Paint();
      localPaint.setAlpha(52);
      localCanvas.drawBitmap((Bitmap)localObject, mOffsetX, mOffsetY, localPaint);
      localCanvas.drawBitmap(localBitmap, 0.0F, 0.0F, null);
      localObject = new BitmapDrawable(paramResources, paramDrawable);
    }
    return (Drawable)localObject;
  }
  
  private Bitmap drawable2Bitmap(Drawable paramDrawable)
  {
    if ((paramDrawable instanceof BitmapDrawable)) {
      return ((BitmapDrawable)paramDrawable).getBitmap();
    }
    if ((paramDrawable instanceof NinePatchDrawable))
    {
      int i = paramDrawable.getIntrinsicWidth();
      int j = paramDrawable.getIntrinsicHeight();
      if (paramDrawable.getOpacity() != -1) {}
      for (Object localObject = Bitmap.Config.ARGB_8888;; localObject = Bitmap.Config.RGB_565)
      {
        localObject = Bitmap.createBitmap(i, j, (Bitmap.Config)localObject);
        Canvas localCanvas = new Canvas((Bitmap)localObject);
        paramDrawable.setBounds(0, 0, paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight());
        paramDrawable.draw(localCanvas);
        return (Bitmap)localObject;
      }
    }
    return null;
  }
  
  public float getBlurRadius()
  {
    return mBlurRadius;
  }
  
  public int getOffsetX()
  {
    return mOffsetX;
  }
  
  public int[] getOffsetXY()
  {
    return offsetXY;
  }
  
  public int getOffsetY()
  {
    return mOffsetY;
  }
  
  public void invalidateImageShadow()
  {
    setImageDrawable(convertDrawableShadow(getResources(), getDrawable()));
  }
  
  public void setBlurMaskFilter(BlurMaskFilter paramBlurMaskFilter)
  {
    mBlurMaskFilter = paramBlurMaskFilter;
    paramBlurMaskFilter = getDrawable();
    if (paramBlurMaskFilter != null) {
      setImageShadowDrawable(paramBlurMaskFilter);
    }
  }
  
  public void setBlurRadius(float paramFloat)
  {
    mBlurRadius = paramFloat;
  }
  
  public void setImageShadowDrawable(Drawable paramDrawable)
  {
    if (paramDrawable != null) {
      setImageDrawable(convertDrawableShadow(getResources(), paramDrawable));
    }
  }
  
  public void setImageShadowResource(int paramInt)
  {
    Resources localResources = getResources();
    Drawable localDrawable = localResources.getDrawable(paramInt);
    if (localDrawable != null) {
      setImageDrawable(convertDrawableShadow(localResources, localDrawable));
    }
  }
  
  public void setOffsetX(int paramInt)
  {
    mOffsetX = paramInt;
  }
  
  public void setOffsetY(int paramInt)
  {
    mOffsetY = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.ImageViewShadow
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */