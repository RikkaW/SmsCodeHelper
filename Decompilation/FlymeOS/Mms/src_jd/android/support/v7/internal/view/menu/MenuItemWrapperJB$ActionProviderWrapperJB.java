package android.support.v7.internal.view.menu;

import android.content.Context;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;

class MenuItemWrapperJB$ActionProviderWrapperJB
  extends MenuItemWrapperICS.ActionProviderWrapper
  implements android.view.ActionProvider.VisibilityListener
{
  android.support.v4.view.ActionProvider.VisibilityListener mListener;
  
  public MenuItemWrapperJB$ActionProviderWrapperJB(MenuItemWrapperJB paramMenuItemWrapperJB, Context paramContext, ActionProvider paramActionProvider)
  {
    super(paramMenuItemWrapperJB, paramContext, paramActionProvider);
  }
  
  public boolean isVisible()
  {
    return mInner.isVisible();
  }
  
  public void onActionProviderVisibilityChanged(boolean paramBoolean)
  {
    if (mListener != null) {
      mListener.onActionProviderVisibilityChanged(paramBoolean);
    }
  }
  
  public View onCreateActionView(MenuItem paramMenuItem)
  {
    return mInner.onCreateActionView(paramMenuItem);
  }
  
  public boolean overridesItemVisibility()
  {
    return mInner.overridesItemVisibility();
  }
  
  public void refreshVisibility()
  {
    mInner.refreshVisibility();
  }
  
  public void setVisibilityListener(android.support.v4.view.ActionProvider.VisibilityListener paramVisibilityListener)
  {
    mListener = paramVisibilityListener;
    ActionProvider localActionProvider = mInner;
    if (paramVisibilityListener != null) {}
    for (paramVisibilityListener = this;; paramVisibilityListener = null)
    {
      localActionProvider.setVisibilityListener(paramVisibilityListener);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.view.menu.MenuItemWrapperJB.ActionProviderWrapperJB
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */