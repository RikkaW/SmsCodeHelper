package android.support.v7.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

class ActionBarDrawerToggle$HoneycombDelegate
  implements ActionBarDrawerToggle.Delegate
{
  final Activity mActivity;
  ActionBarDrawerToggleHoneycomb.SetIndicatorInfo mSetIndicatorInfo;
  
  private ActionBarDrawerToggle$HoneycombDelegate(Activity paramActivity)
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
    return ActionBarDrawerToggleHoneycomb.getThemeUpIndicator(mActivity);
  }
  
  public boolean isNavigationVisible()
  {
    ActionBar localActionBar = mActivity.getActionBar();
    return (localActionBar != null) && ((localActionBar.getDisplayOptions() & 0x4) != 0);
  }
  
  public void setActionBarDescription(int paramInt)
  {
    mSetIndicatorInfo = ActionBarDrawerToggleHoneycomb.setActionBarDescription(mSetIndicatorInfo, mActivity, paramInt);
  }
  
  public void setActionBarUpIndicator(Drawable paramDrawable, int paramInt)
  {
    mActivity.getActionBar().setDisplayShowHomeEnabled(true);
    mSetIndicatorInfo = ActionBarDrawerToggleHoneycomb.setActionBarUpIndicator(mSetIndicatorInfo, mActivity, paramDrawable, paramInt);
    mActivity.getActionBar().setDisplayShowHomeEnabled(false);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.ActionBarDrawerToggle.HoneycombDelegate
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */