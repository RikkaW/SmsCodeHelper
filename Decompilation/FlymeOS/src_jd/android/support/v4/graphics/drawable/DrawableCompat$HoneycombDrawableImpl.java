package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;

class DrawableCompat$HoneycombDrawableImpl
  extends DrawableCompat.BaseDrawableImpl
{
  public void jumpToCurrentState(Drawable paramDrawable)
  {
    DrawableCompatHoneycomb.jumpToCurrentState(paramDrawable);
  }
  
  public Drawable wrap(Drawable paramDrawable)
  {
    return DrawableCompatHoneycomb.wrapForTinting(paramDrawable);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.graphics.drawable.DrawableCompat.HoneycombDrawableImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */