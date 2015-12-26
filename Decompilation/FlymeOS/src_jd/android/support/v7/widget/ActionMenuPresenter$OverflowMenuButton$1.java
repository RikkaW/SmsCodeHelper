package android.support.v7.widget;

import android.view.View;

class ActionMenuPresenter$OverflowMenuButton$1
  extends ListPopupWindow.ForwardingListener
{
  ActionMenuPresenter$OverflowMenuButton$1(ActionMenuPresenter.OverflowMenuButton paramOverflowMenuButton, View paramView, ActionMenuPresenter paramActionMenuPresenter)
  {
    super(paramView);
  }
  
  public ListPopupWindow getPopup()
  {
    if (ActionMenuPresenter.access$300(this$1.this$0) == null) {
      return null;
    }
    return ActionMenuPresenter.access$300(this$1.this$0).getPopup();
  }
  
  public boolean onForwardingStarted()
  {
    this$1.this$0.showOverflowMenu();
    return true;
  }
  
  public boolean onForwardingStopped()
  {
    if (ActionMenuPresenter.access$400(this$1.this$0) != null) {
      return false;
    }
    this$1.this$0.hideOverflowMenu();
    return true;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ActionMenuPresenter.OverflowMenuButton.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */