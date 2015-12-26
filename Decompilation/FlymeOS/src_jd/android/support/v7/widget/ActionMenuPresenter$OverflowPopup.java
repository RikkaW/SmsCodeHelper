package android.support.v7.widget;

import android.content.Context;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuPopupHelper;
import android.view.View;

class ActionMenuPresenter$OverflowPopup
  extends MenuPopupHelper
{
  public ActionMenuPresenter$OverflowPopup(ActionMenuPresenter paramActionMenuPresenter, Context paramContext, MenuBuilder paramMenuBuilder, View paramView, boolean paramBoolean) {}
  
  public void dismiss()
  {
    super.dismiss(true);
  }
  
  public void onDismiss()
  {
    super.onDismiss();
    ActionMenuPresenter.access$600(this$0).close();
    ActionMenuPresenter.access$302(this$0, null);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ActionMenuPresenter.OverflowPopup
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */