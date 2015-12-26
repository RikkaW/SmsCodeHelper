package android.support.v7.internal.app;

import android.support.v7.internal.view.WindowCallbackWrapper;
import android.support.v7.internal.widget.DecorToolbar;
import android.view.Menu;
import android.view.View;
import android.view.Window.Callback;

class ToolbarActionBar$ToolbarCallbackWrapper
  extends WindowCallbackWrapper
{
  public ToolbarActionBar$ToolbarCallbackWrapper(ToolbarActionBar paramToolbarActionBar, Window.Callback paramCallback)
  {
    super(paramCallback);
  }
  
  public View onCreatePanelView(int paramInt)
  {
    switch (paramInt)
    {
    }
    Menu localMenu;
    do
    {
      return super.onCreatePanelView(paramInt);
      localMenu = ToolbarActionBar.access$300(this$0).getMenu();
    } while ((!onPreparePanel(paramInt, null, localMenu)) || (!onMenuOpened(paramInt, localMenu)));
    return ToolbarActionBar.access$400(this$0, localMenu);
  }
  
  public boolean onPreparePanel(int paramInt, View paramView, Menu paramMenu)
  {
    boolean bool = super.onPreparePanel(paramInt, paramView, paramMenu);
    if ((bool) && (!ToolbarActionBar.access$200(this$0)))
    {
      ToolbarActionBar.access$300(this$0).setMenuPrepared();
      ToolbarActionBar.access$202(this$0, true);
    }
    return bool;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.app.ToolbarActionBar.ToolbarCallbackWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */