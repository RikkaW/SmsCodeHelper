package android.support.v7.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

class ActionBarDrawerToggle$JellybeanMr2Delegate
  implements ActionBarDrawerToggle.Delegate
{
  final Activity mActivity;
  
  private ActionBarDrawerToggle$JellybeanMr2Delegate(Activity paramActivity)
  {
    mActivity = paramActivity;
  }
  
  public Context getActionBarThemedContext()
  {
    ActionBar localActionBar = mActivity.getActionBar();
    if (localActionBar != null) {
      return localActionBar.getThemedContext();
    }
    return mActivity;
  }
  
  public Drawable getThemeUpIndicator()
  {
    TypedArray localTypedArray = getActionBarThemedContext().obtainStyledAttributes(null, new int[] { 16843531 }, 16843470, 0);
    Drawable localDrawable = localTypedArray.getDrawable(0);
    localTypedArray.recycle();
    return localDrawable;
  }
  
  public boolean isNavigationVisible()
  {
    ActionBar localActionBar = mActivity.getActionBar();
    return (localActionBar != null) && ((localActionBar.getDisplayOptions() & 0x4) != 0);
  }
  
  public void setActionBarDescription(int paramInt)
  {
    ActionBar localActionBar = mActivity.getActionBar();
    if (localActionBar != null) {
      localActionBar.setHomeActionContentDescription(paramInt);
    }
  }
  
  public void setActionBarUpIndicator(Drawable paramDrawable, int paramInt)
  {
    ActionBar localActionBar = mActivity.getActionBar();
    if (localActionBar != null)
    {
      localActionBar.setHomeAsUpIndicator(paramDrawable);
      localActionBar.setHomeActionContentDescription(paramInt);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.ActionBarDrawerToggle.JellybeanMr2Delegate
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */