package android.support.v7.widget;

import android.content.Context;
import android.support.v7.appcompat.R.attr;
import android.support.v7.internal.view.SupportMenuInflater;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuBuilder.Callback;
import android.support.v7.internal.view.menu.MenuPopupHelper;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnTouchListener;

public class PopupMenu
  implements MenuBuilder.Callback, MenuPresenter.Callback
{
  private View mAnchor;
  private Context mContext;
  private OnDismissListener mDismissListener;
  private View.OnTouchListener mDragListener;
  private MenuBuilder mMenu;
  private OnMenuItemClickListener mMenuItemClickListener;
  private MenuPopupHelper mPopup;
  
  public PopupMenu(Context paramContext, View paramView)
  {
    this(paramContext, paramView, 0);
  }
  
  public PopupMenu(Context paramContext, View paramView, int paramInt)
  {
    this(paramContext, paramView, paramInt, R.attr.popupMenuStyle, 0);
  }
  
  public PopupMenu(Context paramContext, View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    mContext = paramContext;
    mMenu = new MenuBuilder(paramContext);
    mMenu.setCallback(this);
    mAnchor = paramView;
    mPopup = new MenuPopupHelper(paramContext, mMenu, paramView, false, paramInt2, paramInt3);
    mPopup.setGravity(paramInt1);
    mPopup.setCallback(this);
  }
  
  public void dismiss()
  {
    mPopup.dismiss();
  }
  
  public View.OnTouchListener getDragToOpenListener()
  {
    if (mDragListener == null) {
      mDragListener = new PopupMenu.1(this, mAnchor);
    }
    return mDragListener;
  }
  
  public Menu getMenu()
  {
    return mMenu;
  }
  
  public MenuInflater getMenuInflater()
  {
    return new SupportMenuInflater(mContext);
  }
  
  public void inflate(int paramInt)
  {
    getMenuInflater().inflate(paramInt, mMenu);
  }
  
  public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
  {
    if (mDismissListener != null) {
      mDismissListener.onDismiss(this);
    }
  }
  
  public void onCloseSubMenu(SubMenuBuilder paramSubMenuBuilder) {}
  
  public boolean onMenuItemSelected(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem)
  {
    if (mMenuItemClickListener != null) {
      return mMenuItemClickListener.onMenuItemClick(paramMenuItem);
    }
    return false;
  }
  
  public void onMenuModeChange(MenuBuilder paramMenuBuilder) {}
  
  public boolean onOpenSubMenu(MenuBuilder paramMenuBuilder)
  {
    boolean bool = true;
    if (paramMenuBuilder == null) {
      bool = false;
    }
    while (!paramMenuBuilder.hasVisibleItems()) {
      return bool;
    }
    new MenuPopupHelper(mContext, paramMenuBuilder, mAnchor).show();
    return true;
  }
  
  public void setOnDismissListener(OnDismissListener paramOnDismissListener)
  {
    mDismissListener = paramOnDismissListener;
  }
  
  public void setOnMenuItemClickListener(OnMenuItemClickListener paramOnMenuItemClickListener)
  {
    mMenuItemClickListener = paramOnMenuItemClickListener;
  }
  
  public void show()
  {
    mPopup.show();
  }
  
  public static abstract interface OnDismissListener
  {
    public abstract void onDismiss(PopupMenu paramPopupMenu);
  }
  
  public static abstract interface OnMenuItemClickListener
  {
    public abstract boolean onMenuItemClick(MenuItem paramMenuItem);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.PopupMenu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */