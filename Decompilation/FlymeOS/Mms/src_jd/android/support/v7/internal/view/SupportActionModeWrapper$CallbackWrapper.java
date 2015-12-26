package android.support.v7.internal.view;

import android.content.Context;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.util.SimpleArrayMap;
import android.support.v7.internal.view.menu.MenuWrapperFactory;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;

public class SupportActionModeWrapper$CallbackWrapper
  implements android.support.v7.view.ActionMode.Callback
{
  final ArrayList<SupportActionModeWrapper> mActionModes;
  final Context mContext;
  final SimpleArrayMap<Menu, Menu> mMenus;
  final android.view.ActionMode.Callback mWrappedCallback;
  
  public SupportActionModeWrapper$CallbackWrapper(Context paramContext, android.view.ActionMode.Callback paramCallback)
  {
    mContext = paramContext;
    mWrappedCallback = paramCallback;
    mActionModes = new ArrayList();
    mMenus = new SimpleArrayMap();
  }
  
  private android.view.ActionMode getActionModeWrapper(android.support.v7.view.ActionMode paramActionMode)
  {
    int j = mActionModes.size();
    int i = 0;
    while (i < j)
    {
      SupportActionModeWrapper localSupportActionModeWrapper = (SupportActionModeWrapper)mActionModes.get(i);
      if ((localSupportActionModeWrapper != null) && (mWrappedObject == paramActionMode)) {
        return localSupportActionModeWrapper;
      }
      i += 1;
    }
    paramActionMode = new SupportActionModeWrapper(mContext, paramActionMode);
    mActionModes.add(paramActionMode);
    return paramActionMode;
  }
  
  private Menu getMenuWrapper(Menu paramMenu)
  {
    Menu localMenu2 = (Menu)mMenus.get(paramMenu);
    Menu localMenu1 = localMenu2;
    if (localMenu2 == null)
    {
      localMenu1 = MenuWrapperFactory.wrapSupportMenu(mContext, (SupportMenu)paramMenu);
      mMenus.put(paramMenu, localMenu1);
    }
    return localMenu1;
  }
  
  public void addActionModeWrapper(SupportActionModeWrapper paramSupportActionModeWrapper)
  {
    mActionModes.add(paramSupportActionModeWrapper);
  }
  
  public boolean onActionItemClicked(android.support.v7.view.ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    return mWrappedCallback.onActionItemClicked(getActionModeWrapper(paramActionMode), MenuWrapperFactory.wrapSupportMenuItem(mContext, (SupportMenuItem)paramMenuItem));
  }
  
  public boolean onCreateActionMode(android.support.v7.view.ActionMode paramActionMode, Menu paramMenu)
  {
    return mWrappedCallback.onCreateActionMode(getActionModeWrapper(paramActionMode), getMenuWrapper(paramMenu));
  }
  
  public void onDestroyActionMode(android.support.v7.view.ActionMode paramActionMode)
  {
    mWrappedCallback.onDestroyActionMode(getActionModeWrapper(paramActionMode));
  }
  
  public boolean onPrepareActionMode(android.support.v7.view.ActionMode paramActionMode, Menu paramMenu)
  {
    return mWrappedCallback.onPrepareActionMode(getActionModeWrapper(paramActionMode), getMenuWrapper(paramMenu));
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.view.SupportActionModeWrapper.CallbackWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */