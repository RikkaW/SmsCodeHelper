package com.meizu.common.widget;

import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnDismissListener;

class ActionMenuItemView$MenuDismissListener
  implements PopupMenu.OnDismissListener
{
  ActionMenuItemView$MenuDismissListener(ActionMenuItemView paramActionMenuItemView) {}
  
  public void onDismiss(PopupMenu paramPopupMenu)
  {
    ActionMenuItemView.access$102(this$0, false);
    ActionMenuItemView.access$200(this$0).setRotation(180.0F);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.ActionMenuItemView.MenuDismissListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */