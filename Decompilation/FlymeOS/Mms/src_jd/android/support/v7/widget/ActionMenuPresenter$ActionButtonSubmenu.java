package android.support.v7.widget;

import android.content.Context;
import android.support.v7.internal.view.menu.MenuPopupHelper;
import android.support.v7.internal.view.menu.SubMenuBuilder;

class ActionMenuPresenter$ActionButtonSubmenu
  extends MenuPopupHelper
{
  private SubMenuBuilder mSubMenu;
  
  public ActionMenuPresenter$ActionButtonSubmenu(ActionMenuPresenter paramActionMenuPresenter, Context paramContext, SubMenuBuilder paramSubMenuBuilder) {}
  
  public void onDismiss()
  {
    super.onDismiss();
    ActionMenuPresenter.access$902(this$0, null);
    this$0.mOpenSubMenuId = 0;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ActionMenuPresenter.ActionButtonSubmenu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */