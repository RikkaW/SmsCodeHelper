package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;

class DrawableCompat$BaseDrawableImpl
  implements DrawableCompat.DrawableImpl
{
  public boolean isAutoMirrored(Drawable paramDrawable)
  {
    return false;
  }
  
  public void jumpToCurrentState(Drawable paramDrawable) {}
  
  public void setAutoMirrored(Drawable paramDrawable, boolean paramBoolean) {}
  
  public void setHotspot(Drawable paramDrawable, float paramFloat1, float paramFloat2) {}
  
  public void setHotspotBounds(Drawable paramDrawable, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {}
  
  public void setTint(Drawable paramDrawable, int paramInt)
  {
    DrawableCompatBase.setTint(paramDrawable, paramInt);
  }
  
  public void setTintList(Drawable paramDrawable, ColorStateList paramColorStateList)
  {
    DrawableCompatBase.setTintList(paramDrawable, paramColorStateList);
  }
  
  public void setTintMode(Drawable paramDrawable, PorterDuff.Mode paramMode)
  {
    DrawableCompatBase.setTintMode(paramDrawable, paramMode);
  }
  
  public Drawable wrap(Drawable paramDrawable)
  {
    return DrawableCompatBase.wrapForTinting(paramDrawable);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.graphics.drawable.DrawableCompat.BaseDrawableImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */