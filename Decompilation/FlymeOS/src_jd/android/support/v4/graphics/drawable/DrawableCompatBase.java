package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;

class DrawableCompatBase
{
  public static void setTint(Drawable paramDrawable, int paramInt)
  {
    if ((paramDrawable instanceof DrawableWrapper)) {
      ((DrawableWrapper)paramDrawable).setTint(paramInt);
    }
  }
  
  public static void setTintList(Drawable paramDrawable, ColorStateList paramColorStateList)
  {
    if ((paramDrawable instanceof DrawableWrapper)) {
      ((DrawableWrapper)paramDrawable).setTintList(paramColorStateList);
    }
  }
  
  public static void setTintMode(Drawable paramDrawable, PorterDuff.Mode paramMode)
  {
    if ((paramDrawable instanceof DrawableWrapper)) {
      ((DrawableWrapper)paramDrawable).setTintMode(paramMode);
    }
  }
  
  public static Drawable wrapForTinting(Drawable paramDrawable)
  {
    Object localObject = paramDrawable;
    if (!(paramDrawable instanceof DrawableWrapperDonut)) {
      localObject = new DrawableWrapperDonut(paramDrawable);
    }
    return (Drawable)localObject;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.graphics.drawable.DrawableCompatBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */