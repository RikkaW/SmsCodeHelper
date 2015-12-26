package android.support.v7.app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R.attr;
import android.support.v7.internal.widget.TintTypedArray;

class AppCompatDelegateImplBase$ActionBarDrawableToggleImpl
  implements ActionBarDrawerToggle.Delegate
{
  private AppCompatDelegateImplBase$ActionBarDrawableToggleImpl(AppCompatDelegateImplBase paramAppCompatDelegateImplBase) {}
  
  public Context getActionBarThemedContext()
  {
    return this$0.getActionBarThemedContext();
  }
  
  public Drawable getThemeUpIndicator()
  {
    TintTypedArray localTintTypedArray = TintTypedArray.obtainStyledAttributes(getActionBarThemedContext(), null, new int[] { R.attr.homeAsUpIndicator });
    Drawable localDrawable = localTintTypedArray.getDrawable(0);
    localTintTypedArray.recycle();
    return localDrawable;
  }
  
  public boolean isNavigationVisible()
  {
    ActionBar localActionBar = this$0.getSupportActionBar();
    return (localActionBar != null) && ((localActionBar.getDisplayOptions() & 0x4) != 0);
  }
  
  public void setActionBarDescription(int paramInt)
  {
    ActionBar localActionBar = this$0.getSupportActionBar();
    if (localActionBar != null) {
      localActionBar.setHomeActionContentDescription(paramInt);
    }
  }
  
  public void setActionBarUpIndicator(Drawable paramDrawable, int paramInt)
  {
    ActionBar localActionBar = this$0.getSupportActionBar();
    if (localActionBar != null)
    {
      localActionBar.setHomeAsUpIndicator(paramDrawable);
      localActionBar.setHomeActionContentDescription(paramInt);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AppCompatDelegateImplBase.ActionBarDrawableToggleImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */