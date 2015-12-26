package android.support.v7.app;

import android.view.View;
import android.view.View.OnClickListener;

class ActionBarDrawerToggle$1
  implements View.OnClickListener
{
  ActionBarDrawerToggle$1(ActionBarDrawerToggle paramActionBarDrawerToggle) {}
  
  public void onClick(View paramView)
  {
    if (ActionBarDrawerToggle.access$000(this$0)) {
      ActionBarDrawerToggle.access$100(this$0);
    }
    while (ActionBarDrawerToggle.access$200(this$0) == null) {
      return;
    }
    ActionBarDrawerToggle.access$200(this$0).onClick(paramView);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.ActionBarDrawerToggle.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */