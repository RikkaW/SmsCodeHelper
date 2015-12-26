package android.support.v7.internal.view.menu;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.appcompat.R.layout;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import java.util.ArrayList;

public class ListMenuPresenter
  implements MenuPresenter, AdapterView.OnItemClickListener
{
  private static final String TAG = "ListMenuPresenter";
  public static final String VIEWS_TAG = "android:menu:list";
  MenuAdapter mAdapter;
  private MenuPresenter.Callback mCallback;
  Context mContext;
  private int mId;
  LayoutInflater mInflater;
  private int mItemIndexOffset;
  int mItemLayoutRes;
  MenuBuilder mMenu;
  ExpandedMenuView mMenuView;
  int mThemeRes;
  
  public ListMenuPresenter(int paramInt1, int paramInt2)
  {
    mItemLayoutRes = paramInt1;
    mThemeRes = paramInt2;
  }
  
  public ListMenuPresenter(Context paramContext, int paramInt)
  {
    this(paramInt, 0);
    mContext = paramContext;
    mInflater = LayoutInflater.from(mContext);
  }
  
  public boolean collapseItemActionView(MenuBuilder paramMenuBuilder, MenuItemImpl paramMenuItemImpl)
  {
    return false;
  }
  
  public boolean expandItemActionView(MenuBuilder paramMenuBuilder, MenuItemImpl paramMenuItemImpl)
  {
    return false;
  }
  
  public boolean flagActionItems()
  {
    return false;
  }
  
  public ListAdapter getAdapter()
  {
    if (mAdapter == null) {
      mAdapter = new MenuAdapter();
    }
    return mAdapter;
  }
  
  public int getId()
  {
    return mId;
  }
  
  int getItemIndexOffset()
  {
    return mItemIndexOffset;
  }
  
  public MenuView getMenuView(ViewGroup paramViewGroup)
  {
    if (mMenuView == null)
    {
      mMenuView = ((ExpandedMenuView)mInflater.inflate(R.layout.abc_expanded_menu_layout, paramViewGroup, false));
      if (mAdapter == null) {
        mAdapter = new MenuAdapter();
      }
      mMenuView.setAdapter(mAdapter);
      mMenuView.setOnItemClickListener(this);
    }
    return mMenuView;
  }
  
  public void initForMenu(Context paramContext, MenuBuilder paramMenuBuilder)
  {
    if (mThemeRes != 0)
    {
      mContext = new ContextThemeWrapper(paramContext, mThemeRes);
      mInflater = LayoutInflater.from(mContext);
    }
    for (;;)
    {
      mMenu = paramMenuBuilder;
      if (mAdapter != null) {
        mAdapter.notifyDataSetChanged();
      }
      return;
      if (mContext != null)
      {
        mContext = paramContext;
        if (mInflater == null) {
          mInflater = LayoutInflater.from(mContext);
        }
      }
    }
  }
  
  public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
  {
    if (mCallback != null) {
      mCallback.onCloseMenu(paramMenuBuilder, paramBoolean);
    }
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    mMenu.performItemAction(mAdapter.getItem(paramInt), this, 0);
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    restoreHierarchyState((Bundle)paramParcelable);
  }
  
  public Parcelable onSaveInstanceState()
  {
    if (mMenuView == null) {
      return null;
    }
    Bundle localBundle = new Bundle();
    saveHierarchyState(localBundle);
    return localBundle;
  }
  
  public boolean onSubMenuSelected(SubMenuBuilder paramSubMenuBuilder)
  {
    if (!paramSubMenuBuilder.hasVisibleItems()) {
      return false;
    }
    new MenuDialogHelper(paramSubMenuBuilder).show(null);
    if (mCallback != null) {
      mCallback.onOpenSubMenu(paramSubMenuBuilder);
    }
    return true;
  }
  
  public void restoreHierarchyState(Bundle paramBundle)
  {
    paramBundle = paramBundle.getSparseParcelableArray("android:menu:list");
    if (paramBundle != null) {
      mMenuView.restoreHierarchyState(paramBundle);
    }
  }
  
  public void saveHierarchyState(Bundle paramBundle)
  {
    SparseArray localSparseArray = new SparseArray();
    if (mMenuView != null) {
      mMenuView.saveHierarchyState(localSparseArray);
    }
    paramBundle.putSparseParcelableArray("android:menu:list", localSparseArray);
  }
  
  public void setCallback(MenuPresenter.Callback paramCallback)
  {
    mCallback = paramCallback;
  }
  
  public void setId(int paramInt)
  {
    mId = paramInt;
  }
  
  public void setItemIndexOffset(int paramInt)
  {
    mItemIndexOffset = paramInt;
    if (mMenuView != null) {
      updateMenuView(false);
    }
  }
  
  public void updateMenuView(boolean paramBoolean)
  {
    if (mAdapter != null) {
      mAdapter.notifyDataSetChanged();
    }
  }
  
  class MenuAdapter
    extends BaseAdapter
  {
    private int mExpandedIndex = -1;
    
    public MenuAdapter()
    {
      findExpandedIndex();
    }
    
    void findExpandedIndex()
    {
      MenuItemImpl localMenuItemImpl = mMenu.getExpandedItem();
      if (localMenuItemImpl != null)
      {
        ArrayList localArrayList = mMenu.getNonActionItems();
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
      int i = mMenu.getNonActionItems().size() - mItemIndexOffset;
      if (mExpandedIndex < 0) {
        return i;
      }
      return i - 1;
    }
    
    public MenuItemImpl getItem(int paramInt)
    {
      ArrayList localArrayList = mMenu.getNonActionItems();
      int i = mItemIndexOffset + paramInt;
      paramInt = i;
      if (mExpandedIndex >= 0)
      {
        paramInt = i;
        if (i >= mExpandedIndex) {
          paramInt = i + 1;
        }
      }
      return (MenuItemImpl)localArrayList.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null) {
        paramView = mInflater.inflate(mItemLayoutRes, paramViewGroup, false);
      }
      for (;;)
      {
        ((MenuView.ItemView)paramView).initialize(getItem(paramInt), 0);
        return paramView;
      }
    }
    
    public void notifyDataSetChanged()
    {
      findExpandedIndex();
      super.notifyDataSetChanged();
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.view.menu.ListMenuPresenter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */