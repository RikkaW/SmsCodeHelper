package android.support.v7.internal.view.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

class MenuPopupHelper$MenuAdapter
  extends BaseAdapter
{
  private MenuBuilder mAdapterMenu;
  private int mExpandedIndex = -1;
  
  public MenuPopupHelper$MenuAdapter(MenuPopupHelper paramMenuPopupHelper, MenuBuilder paramMenuBuilder)
  {
    mAdapterMenu = paramMenuBuilder;
    findExpandedIndex();
  }
  
  void findExpandedIndex()
  {
    MenuItemImpl localMenuItemImpl = MenuPopupHelper.access$300(this$0).getExpandedItem();
    if (localMenuItemImpl != null)
    {
      ArrayList localArrayList = MenuPopupHelper.access$300(this$0).getNonActionItems();
      int j = localArrayList.size();
      int i = 0;
      while (i < j)
      {
        if ((MenuItemImpl)localArrayList.get(i) == localMenuItemImpl)
        {
          mExpandedIndex = i;
          return;
        }
        i += 1;
      }
    }
    mExpandedIndex = -1;
  }
  
  public int getCount()
  {
    if (MenuPopupHelper.access$100(this$0)) {}
    for (ArrayList localArrayList = mAdapterMenu.getNonActionItems(); mExpandedIndex < 0; localArrayList = mAdapterMenu.getVisibleItems()) {
      return localArrayList.size();
    }
    return localArrayList.size() - 1;
  }
  
  public MenuItemImpl getItem(int paramInt)
  {
    if (MenuPopupHelper.access$100(this$0)) {}
    for (ArrayList localArrayList = mAdapterMenu.getNonActionItems();; localArrayList = mAdapterMenu.getVisibleItems())
    {
      int i = paramInt;
      if (mExpandedIndex >= 0)
      {
        i = paramInt;
        if (paramInt >= mExpandedIndex) {
          i = paramInt + 1;
        }
      }
      return (MenuItemImpl)localArrayList.get(i);
    }
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {
      paramView = MenuPopupHelper.access$200(this$0).inflate(MenuPopupHelper.ITEM_LAYOUT, paramViewGroup, false);
    }
    for (;;)
    {
      paramViewGroup = (MenuView.ItemView)paramView;
      if (this$0.mForceShowIcon) {
        ((ListMenuItemView)paramView).setForceShowIcon(true);
      }
      paramViewGroup.initialize(getItem(paramInt), 0);
      return paramView;
    }
  }
  
  public boolean isEnabled(int paramInt)
  {
    MenuItemImpl localMenuItemImpl = getItem(paramInt);
    if (localMenuItemImpl == null) {
      return super.isEnabled(paramInt);
    }
    return localMenuItemImpl.isEnabled();
  }
  
  public void notifyDataSetChanged()
  {
    findExpandedIndex();
    super.notifyDataSetChanged();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.view.menu.MenuPopupHelper.MenuAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */