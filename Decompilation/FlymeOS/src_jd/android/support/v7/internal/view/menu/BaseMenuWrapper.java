package android.support.v7.internal.view.menu;

import android.content.Context;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.internal.view.SupportSubMenu;
import android.support.v4.util.ArrayMap;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

abstract class BaseMenuWrapper<T>
  extends BaseWrapper<T>
{
  final Context mContext;
  private Map<SupportMenuItem, MenuItem> mMenuItems;
  private Map<SupportSubMenu, SubMenu> mSubMenus;
  
  BaseMenuWrapper(Context paramContext, T paramT)
  {
    super(paramT);
    mContext = paramContext;
  }
  
  final MenuItem getMenuItemWrapper(MenuItem paramMenuItem)
  {
    if ((paramMenuItem instanceof SupportMenuItem))
    {
      SupportMenuItem localSupportMenuItem = (SupportMenuItem)paramMenuItem;
      if (mMenuItems == null) {
        mMenuItems = new ArrayMap();
      }
      MenuItem localMenuItem = (MenuItem)mMenuItems.get(paramMenuItem);
      paramMenuItem = localMenuItem;
      if (localMenuItem == null)
      {
        paramMenuItem = MenuWrapperFactory.wrapSupportMenuItem(mContext, localSupportMenuItem);
        mMenuItems.put(localSupportMenuItem, paramMenuItem);
      }
      return paramMenuItem;
    }
    return paramMenuItem;
  }
  
  final SubMenu getSubMenuWrapper(SubMenu paramSubMenu)
  {
    if ((paramSubMenu instanceof SupportSubMenu))
    {
      SupportSubMenu localSupportSubMenu = (SupportSubMenu)paramSubMenu;
      if (mSubMenus == null) {
        mSubMenus = new ArrayMap();
      }
      SubMenu localSubMenu = (SubMenu)mSubMenus.get(localSupportSubMenu);
      paramSubMenu = localSubMenu;
      if (localSubMenu == null)
      {
        paramSubMenu = MenuWrapperFactory.wrapSupportSubMenu(mContext, localSupportSubMenu);
        mSubMenus.put(localSupportSubMenu, paramSubMenu);
      }
      return paramSubMenu;
    }
    return paramSubMenu;
  }
  
  final void internalClear()
  {
    if (mMenuItems != null) {
      mMenuItems.clear();
    }
    if (mSubMenus != null) {
      mSubMenus.clear();
    }
  }
  
  final void internalRemoveGroup(int paramInt)
  {
    if (mMenuItems == null) {}
    for (;;)
    {
      return;
      Iterator localIterator = mMenuItems.keySet().iterator();
      while (localIterator.hasNext()) {
        if (paramInt == ((MenuItem)localIterator.next()).getGroupId()) {
          localIterator.remove();
        }
      }
    }
  }
  
  final void internalRemoveItem(int paramInt)
  {
    if (mMenuItems == null) {}
    Iterator localIterator;
    do
    {
      return;
      while (!localIterator.hasNext()) {
        localIterator = mMenuItems.keySet().iterator();
      }
    } while (paramInt != ((MenuItem)localIterator.next()).getItemId());
    localIterator.remove();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.view.menu.BaseMenuWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */