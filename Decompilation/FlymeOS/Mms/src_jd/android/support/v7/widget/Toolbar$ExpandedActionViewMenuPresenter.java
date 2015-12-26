package android.support.v7.widget;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.support.v7.view.CollapsibleActionView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

class Toolbar$ExpandedActionViewMenuPresenter
  implements MenuPresenter
{
  MenuItemImpl mCurrentExpandedItem;
  MenuBuilder mMenu;
  
  private Toolbar$ExpandedActionViewMenuPresenter(Toolbar paramToolbar) {}
  
  public boolean collapseItemActionView(MenuBuilder paramMenuBuilder, MenuItemImpl paramMenuItemImpl)
  {
    if ((this$0.mExpandedActionView instanceof CollapsibleActionView)) {
      ((CollapsibleActionView)this$0.mExpandedActionView).onActionViewCollapsed();
    }
    this$0.removeView(this$0.mExpandedActionView);
    this$0.removeView(Toolbar.access$300(this$0));
    this$0.mExpandedActionView = null;
    Toolbar.access$500(this$0, false);
    mCurrentExpandedItem = null;
    this$0.requestLayout();
    paramMenuItemImpl.setActionViewExpanded(false);
    return true;
  }
  
  public boolean expandItemActionView(MenuBuilder paramMenuBuilder, MenuItemImpl paramMenuItemImpl)
  {
    Toolbar.access$200(this$0);
    if (Toolbar.access$300(this$0).getParent() != this$0) {
      this$0.addView(Toolbar.access$300(this$0));
    }
    this$0.mExpandedActionView = paramMenuItemImpl.getActionView();
    mCurrentExpandedItem = paramMenuItemImpl;
    if (this$0.mExpandedActionView.getParent() != this$0)
    {
      paramMenuBuilder = this$0.generateDefaultLayoutParams();
      gravity = (0x800003 | Toolbar.access$400(this$0) & 0x70);
      mViewType = 2;
      this$0.mExpandedActionView.setLayoutParams(paramMenuBuilder);
      this$0.addView(this$0.mExpandedActionView);
    }
    Toolbar.access$500(this$0, true);
    this$0.requestLayout();
    paramMenuItemImpl.setActionViewExpanded(true);
    if ((this$0.mExpandedActionView instanceof CollapsibleActionView)) {
      ((CollapsibleActionView)this$0.mExpandedActionView).onActionViewExpanded();
    }
    return true;
  }
  
  public boolean flagActionItems()
  {
    return false;
  }
  
  public int getId()
  {
    return 0;
  }
  
  public MenuView getMenuView(ViewGroup paramViewGroup)
  {
    return null;
  }
  
  public void initForMenu(Context paramContext, MenuBuilder paramMenuBuilder)
  {
    if ((mMenu != null) && (mCurrentExpandedItem != null)) {
      mMenu.collapseItemActionView(mCurrentExpandedItem);
    }
    mMenu = paramMenuBuilder;
  }
  
  public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean) {}
  
  public void onRestoreInstanceState(Parcelable paramParcelable) {}
  
  public Parcelable onSaveInstanceState()
  {
    return null;
  }
  
  public boolean onSubMenuSelected(SubMenuBuilder paramSubMenuBuilder)
  {
    return false;
  }
  
  public void setCallback(MenuPresenter.Callback paramCallback) {}
  
  public void updateMenuView(boolean paramBoolean)
  {
    int k = 0;
    int j;
    int m;
    int i;
    if (mCurrentExpandedItem != null)
    {
      j = k;
      if (mMenu != null)
      {
        m = mMenu.size();
        i = 0;
      }
    }
    for (;;)
    {
      j = k;
      if (i < m)
      {
        if (mMenu.getItem(i) == mCurrentExpandedItem) {
          j = 1;
        }
      }
      else
      {
        if (j == 0) {
          collapseItemActionView(mMenu, mCurrentExpandedItem);
        }
        return;
      }
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.Toolbar.ExpandedActionViewMenuPresenter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */