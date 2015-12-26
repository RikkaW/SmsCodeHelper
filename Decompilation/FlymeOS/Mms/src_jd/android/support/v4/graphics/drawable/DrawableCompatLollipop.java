package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

class DrawableCompatLollipop
{
  public static void setHotspot(Drawable paramDrawable, float paramFloat1, float paramFloat2)
  {
    paramDrawable.setHotspot(paramFloat1, paramFloat2);
  }
  
  public static void setHotspotBounds(Drawable paramDrawable, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramDrawable.setHotspotBounds(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public static void setTint(Drawable paramDrawable, int paramInt)
  {
    if ((paramDrawable instanceof DrawableWrapperLollipop))
    {
      DrawableCompatBase.setTint(paramDrawable, paramInt);
      return;
    }
    paramDrawable.setTint(paramInt);
  }
  
  public static void setTintList(Drawable paramDrawable, ColorStateList paramColorStateList)
  {
    if ((paramDrawable instanceof DrawableWrapperLollipop))
    {
      DrawableCompatBase.setTintList(paramDrawable, paramColorStateList);
      return;
    }
    paramDrawable.setTintList(paramColorStateList);
  }
  
  public static void setTintMode(Drawable paramDrawable, PorterDuff.Mode paramMode)
  {
    if ((paramDrawable instanceof DrawableWrapperLollipop))
    {
      DrawableCompatBase.setTintMode(paramDrawable, paramMode);
      return;
    }
    paramDrawable.setTintMode(paramMode);
  }
  
  public static Drawable wrapForTinting(Drawable paramDrawable)
  {
    Object localObject = paramDrawable;
    if ((paramDrawable instanceof GradientDrawable)) {
      localObject = new DrawableWrapperLollipop(paramDrawable);
    }
    return (Drawable)localObject;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.graphics.drawable.DrawableCompatLollipop
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */