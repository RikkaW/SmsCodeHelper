package android.support.v7.internal.view.menu;

import android.view.View;
import android.widget.FrameLayout;

class MenuItemWrapperICS$CollapsibleActionViewWrapper
  extends FrameLayout
  implements android.support.v7.view.CollapsibleActionView
{
  final android.view.CollapsibleActionView mWrappedView;
  
  MenuItemWrapperICS$CollapsibleActionViewWrapper(View paramView)
  {
    super(paramView.getContext());
    mWrappedView = ((android.view.CollapsibleActionView)paramView);
    addView(paramView);
  }
  
  View getWrappedView()
  {
    return (View)mWrappedView;
  }
  
  public void onActionViewCollapsed()
  {
    mWrappedView.onActionViewCollapsed();
  }
  
  public void onActionViewExpanded()
  {
    mWrappedView.onActionViewExpanded();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.view.menu.MenuItemWrapperICS.CollapsibleActionViewWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */