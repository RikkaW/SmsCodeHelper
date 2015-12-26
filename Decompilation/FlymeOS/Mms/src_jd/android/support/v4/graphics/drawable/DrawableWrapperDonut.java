package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;

class DrawableWrapperDonut
  extends Drawable
  implements Drawable.Callback, DrawableWrapper
{
  static final PorterDuff.Mode DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
  private boolean mColorFilterSet;
  private int mCurrentColor;
  private PorterDuff.Mode mCurrentMode;
  Drawable mDrawable;
  private ColorStateList mTintList;
  private PorterDuff.Mode mTintMode = DEFAULT_MODE;
  
  DrawableWrapperDonut(Drawable paramDrawable)
  {
    setWrappedDrawable(paramDrawable);
  }
  
  private boolean updateTint(int[] paramArrayOfInt)
  {
    if ((mTintList != null) && (mTintMode != null))
    {
      int i = mTintList.getColorForState(paramArrayOfInt, mTintList.getDefaultColor());
      paramArrayOfInt = mTintMode;
      if ((!mColorFilterSet) || (i != mCurrentColor) || (paramArrayOfInt != mCurrentMode))
      {
        setColorFilter(i, paramArrayOfInt);
        mCurrentColor = i;
        mCurrentMode = paramArrayOfInt;
        mColorFilterSet = true;
        return true;
      }
    }
    return false;
  }
  
  public void draw(Canvas paramCanvas)
  {
    mDrawable.draw(paramCanvas);
  }
  
  public int getChangingConfigurations()
  {
    return mDrawable.getChangingConfigurations();
  }
  
  public Drawable getCurrent()
  {
    return mDrawable.getCurrent();
  }
  
  public int getIntrinsicHeight()
  {
    return mDrawable.getIntrinsicHeight();
  }
  
  public int getIntrinsicWidth()
  {
    return mDrawable.getIntrinsicWidth();
  }
  
  public int getMinimumHeight()
  {
    return mDrawable.getMinimumHeight();
  }
  
  public int getMinimumWidth()
  {
    return mDrawable.getMinimumWidth();
  }
  
  public int getOpacity()
  {
    return mDrawable.getOpacity();
  }
  
  public boolean getPadding(Rect paramRect)
  {
    return mDrawable.getPadding(paramRect);
  }
  
  public int[] getState()
  {
    return mDrawable.getState();
  }
  
  public Region getTransparentRegion()
  {
    return mDrawable.getTransparentRegion();
  }
  
  public Drawable getWrappedDrawable()
  {
    return mDrawable;
  }
  
  public void invalidateDrawable(Drawable paramDrawable)
  {
    invalidateSelf();
  }
  
  public boolean isStateful()
  {
    return ((mTintList != null) && (mTintList.isStateful())) || (mDrawable.isStateful());
  }
  
  public Drawable mutate()
  {
    Drawable localDrawable1 = mDrawable;
    Drawable localDrawable2 = localDrawable1.mutate();
    if (localDrawable2 != localDrawable1) {
      setWrappedDrawable(localDrawable2);
    }
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    mDrawable.setBounds(paramRect);
  }
  
  protected boolean onLevelChange(int paramInt)
  {
    return mDrawable.setLevel(paramInt);
  }
  
  public void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong)
  {
    scheduleSelf(paramRunnable, paramLong);
  }
  
  public void setAlpha(int paramInt)
  {
    mDrawable.setAlpha(paramInt);
  }
  
  public void setChangingConfigurations(int paramInt)
  {
    mDrawable.setChangingConfigurations(paramInt);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    mDrawable.setColorFilter(paramColorFilter);
  }
  
  public void setDither(boolean paramBoolean)
  {
    mDrawable.setDither(paramBoolean);
  }
  
  public void setFilterBitmap(boolean paramBoolean)
  {
    mDrawable.setFilterBitmap(paramBoolean);
  }
  
  public boolean setState(int[] paramArrayOfInt)
  {
    boolean bool = mDrawable.setState(paramArrayOfInt);
    return (updateTint(paramArrayOfInt)) || (bool);
  }
  
  public void setTint(int paramInt)
  {
    setTintList(ColorStateList.valueOf(paramInt));
  }
  
  public void setTintList(ColorStateList paramColorStateList)
  {
    mTintList = paramColorStateList;
    updateTint(getState());
  }
  
  public void setTintMode(PorterDuff.Mode paramMode)
  {
    mTintMode = paramMode;
    updateTint(getState());
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2)
  {
    return (super.setVisible(paramBoolean1, paramBoolean2)) || (mDrawable.setVisible(paramBoolean1, paramBoolean2));
  }
  
  public void setWrappedDrawable(Drawable paramDrawable)
  {
    if (mDrawable != null) {
      mDrawable.setCallback(null);
    }
    mDrawable = paramDrawable;
    if (paramDrawable != null) {
      paramDrawable.setCallback(this);
    }
    invalidateSelf();
  }
  
  public void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable)
  {
    unscheduleSelf(paramRunnable);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.graphics.drawable.DrawableWrapperDonut
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */