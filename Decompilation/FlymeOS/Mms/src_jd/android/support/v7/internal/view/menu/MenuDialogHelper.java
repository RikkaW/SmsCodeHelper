package android.support.v7.internal.view.menu;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.appcompat.R.layout;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.ListAdapter;

public class MenuDialogHelper
  implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener, DialogInterface.OnKeyListener, MenuPresenter.Callback
{
  private AlertDialog mDialog;
  private MenuBuilder mMenu;
  ListMenuPresenter mPresenter;
  private MenuPresenter.Callback mPresenterCallback;
  
  public MenuDialogHelper(MenuBuilder paramMenuBuilder)
  {
    mMenu = paramMenuBuilder;
  }
  
  public void dismiss()
  {
    if (mDialog != null) {
      mDialog.dismiss();
    }
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    mMenu.performItemAction((MenuItemImpl)mPresenter.getAdapter().getItem(paramInt), 0);
  }
  
  public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
  {
    if ((paramBoolean) || (paramMenuBuilder == mMenu)) {
      dismiss();
    }
    if (mPresenterCallback != null) {
      mPresenterCallback.onCloseMenu(paramMenuBuilder, paramBoolean);
    }
  }
  
  public void onDismiss(DialogInterface paramDialogInterface)
  {
    mPresenter.onCloseMenu(mMenu, true);
  }
  
  public boolean onKey(DialogInterface paramDialogInterface, int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 82) || (paramInt == 4)) {
      if ((paramKeyEvent.getAction() == 0) && (paramKeyEvent.getRepeatCount() == 0))
      {
        paramDialogInterface = mDialog.getWindow();
        if (paramDialogInterface != null)
        {
          paramDialogInterface = paramDialogInterface.getDecorView();
          if (paramDialogInterface != null)
          {
            paramDialogInterface = paramDialogInterface.getKeyDispatcherState();
            if (paramDialogInterface != null)
            {
              paramDialogInterface.startTracking(paramKeyEvent, this);
              return true;
            }
          }
        }
      }
      else if ((paramKeyEvent.getAction() == 1) && (!paramKeyEvent.isCanceled()))
      {
        Object localObject = mDialog.getWindow();
        if (localObject != null)
        {
          localObject = ((Window)localObject).getDecorView();
          if (localObject != null)
          {
            localObject = ((View)localObject).getKeyDispatcherState();
            if ((localObject != null) && (((KeyEvent.DispatcherState)localObject).isTracking(paramKeyEvent)))
            {
              mMenu.close(true);
              paramDialogInterface.dismiss();
              return true;
            }
          }
        }
      }
    }
    return mMenu.performShortcut(paramInt, paramKeyEvent, 0);
  }
  
  public boolean onOpenSubMenu(MenuBuilder paramMenuBuilder)
  {
    if (mPresenterCallback != null) {
      return mPresenterCallback.onOpenSubMenu(paramMenuBuilder);
    }
    return false;
  }
  
  public void setPresenterCallback(MenuPresenter.Callback paramCallback)
  {
    mPresenterCallback = paramCallback;
  }
  
  public void show(IBinder paramIBinder)
  {
    Object localObject = mMenu;
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(((MenuBuilder)localObject).getContext());
    mPresenter = new ListMenuPresenter(localBuilder.getContext(), R.layout.abc_list_menu_item_layout);
    mPresenter.setCallback(this);
    mMenu.addMenuPresenter(mPresenter);
    localBuilder.setAdapter(mPresenter.getAdapter(), this);
    View localView = ((MenuBuilder)localObject).getHeaderView();
    if (localView != null) {
      localBuilder.setCustomTitle(localView);
    }
    for (;;)
    {
      localBuilder.setOnKeyListener(this);
      mDialog = localBuilder.create();
      mDialog.setOnDismissListener(this);
      localObject = mDialog.getWindow().getAttributes();
      type = 1003;
      if (paramIBinder != null) {
        token = paramIBinder;
      }
      flags |= 0x20000;
      mDialog.show();
      return;
      localBuilder.setIcon(((MenuBuilder)localObject).getHeaderIcon()).setTitle(((MenuBuilder)localObject).getHeaderTitle());
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.view.menu.MenuDialogHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */