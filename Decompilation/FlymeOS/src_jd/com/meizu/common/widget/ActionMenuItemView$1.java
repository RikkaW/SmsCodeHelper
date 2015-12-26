package com.meizu.common.widget;

import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.PopupMenu;

class ActionMenuItemView$1
  implements View.OnClickListener
{
  ActionMenuItemView$1(ActionMenuItemView paramActionMenuItemView) {}
  
  public void onClick(View paramView)
  {
    if ((ActionMenuItemView.access$000(this$0) != null) && (ActionMenuItemView.access$000(this$0).getMenu().hasVisibleItems()) && (!ActionMenuItemView.access$100(this$0)))
    {
      ActionMenuItemView.access$102(this$0, true);
      ActionMenuItemView.access$000(this$0).show();
      ActionMenuItemView.access$200(this$0).setRotation(0.0F);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.ActionMenuItemView.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */