package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;

class DrawableCompat$KitKatDrawableImpl
  extends DrawableCompat.HoneycombDrawableImpl
{
  public boolean isAutoMirrored(Drawable paramDrawable)
  {
    return DrawableCompatKitKat.isAutoMirrored(paramDrawable);
  }
  
  public void setAutoMirrored(Drawable paramDrawable, boolean paramBoolean)
  {
    DrawableCompatKitKat.setAutoMirrored(paramDrawable, paramBoolean);
  }
  
  public Drawable wrap(Drawable paramDrawable)
  {
    return DrawableCompatKitKat.wrapForTinting(paramDrawable);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.graphics.drawable.DrawableCompat.KitKatDrawableImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */