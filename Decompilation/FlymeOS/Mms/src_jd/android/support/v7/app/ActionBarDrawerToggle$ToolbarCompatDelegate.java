package android.support.v7.app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;

class ActionBarDrawerToggle$ToolbarCompatDelegate
  implements ActionBarDrawerToggle.Delegate
{
  final CharSequence mDefaultContentDescription;
  final Drawable mDefaultUpIndicator;
  final Toolbar mToolbar;
  
  ActionBarDrawerToggle$ToolbarCompatDelegate(Toolbar paramToolbar)
  {
    mToolbar = paramToolbar;
    mDefaultUpIndicator = paramToolbar.getNavigationIcon();
    mDefaultContentDescription = paramToolbar.getNavigationContentDescription();
  }
  
  public Context getActionBarThemedContext()
  {
    return mToolbar.getContext();
  }
  
  public Drawable getThemeUpIndicator()
  {
    return mDefaultUpIndicator;
  }
  
  public boolean isNavigationVisible()
  {
    return true;
  }
  
  public void setActionBarDescription(int paramInt)
  {
    if (paramInt == 0)
    {
      mToolbar.setNavigationContentDescription(mDefaultContentDescription);
      return;
    }
    mToolbar.setNavigationContentDescription(paramInt);
  }
  
  public void setActionBarUpIndicator(Drawable paramDrawable, int paramInt)
  {
    mToolbar.setNavigationIcon(paramDrawable);
    setActionBarDescription(paramInt);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.ActionBarDrawerToggle.ToolbarCompatDelegate
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */