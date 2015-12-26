package android.support.v4.widget;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;

class MaterialProgressDrawable$3
  implements Drawable.Callback
{
  MaterialProgressDrawable$3(MaterialProgressDrawable paramMaterialProgressDrawable) {}
  
  public void invalidateDrawable(Drawable paramDrawable)
  {
    this$0.invalidateSelf();
  }
  
  public void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong)
  {
    this$0.scheduleSelf(paramRunnable, paramLong);
  }
  
  public void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable)
  {
    this$0.unscheduleSelf(paramRunnable);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.MaterialProgressDrawable.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */