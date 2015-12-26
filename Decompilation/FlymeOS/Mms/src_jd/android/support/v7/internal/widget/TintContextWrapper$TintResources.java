package android.support.v7.internal.widget;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

class TintContextWrapper$TintResources
  extends ResourcesWrapper
{
  private final TintManager mTintManager;
  
  public TintContextWrapper$TintResources(Resources paramResources, TintManager paramTintManager)
  {
    super(paramResources);
    mTintManager = paramTintManager;
  }
  
  public Drawable getDrawable(int paramInt)
  {
    Drawable localDrawable = super.getDrawable(paramInt);
    if (localDrawable != null) {
      mTintManager.tintDrawableUsingColorFilter(paramInt, localDrawable);
    }
    return localDrawable;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.TintContextWrapper.TintResources
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */