package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

class ActionBarDrawerToggle$DummyDelegate
  implements ActionBarDrawerToggle.Delegate
{
  final Activity mActivity;
  
  ActionBarDrawerToggle$DummyDelegate(Activity paramActivity)
  {
    mActivity = paramActivity;
  }
  
  public Context getActionBarThemedContext()
  {
    return mActivity;
  }
  
  public Drawable getThemeUpIndicator()
  {
    return null;
  }
  
  public boolean isNavigationVisible()
  {
    return true;
  }
  
  public void setActionBarDescription(int paramInt) {}
  
  public void setActionBarUpIndicator(Drawable paramDrawable, int paramInt) {}
}

/* Location:
 * Qualified Name:     android.support.v7.app.ActionBarDrawerToggle.DummyDelegate
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */