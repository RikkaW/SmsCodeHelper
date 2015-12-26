package android.support.v7.internal.widget;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;

class SpinnerCompat$DropDownAdapter
  implements ListAdapter, SpinnerAdapter
{
  private SpinnerAdapter mAdapter;
  private ListAdapter mListAdapter;
  
  public SpinnerCompat$DropDownAdapter(SpinnerAdapter paramSpinnerAdapter)
  {
    mAdapter = paramSpinnerAdapter;
    if ((paramSpinnerAdapter instanceof ListAdapter)) {
      mListAdapter = ((ListAdapter)paramSpinnerAdapter);
    }
  }
  
  public boolean areAllItemsEnabled()
  {
    ListAdapter localListAdapter = mListAdapter;
    if (localListAdapter != null) {
      return localListAdapter.areAllItemsEnabled();
    }
    return true;
  }
  
  public int getCount()
  {
    if (mAdapter == null) {
      return 0;
    }
    return mAdapter.getCount();
  }
  
  public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (mAdapter == null) {
      return null;
    }
    return mAdapter.getDropDownView(paramInt, paramView, paramViewGroup);
  }
  
  public Object getItem(int paramInt)
  {
    if (mAdapter == null) {
      return null;
    }
    return mAdapter.getItem(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    if (mAdapter == null) {
      return -1L;
    }
    return mAdapter.getItemId(paramInt);
  }
  
  public int getItemViewType(int paramInt)
  {
    return 0;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return getDropDownView(paramInt, paramView, paramViewGroup);
  }
  
  public int getViewTypeCount()
  {
    return 1;
  }
  
  public boolean hasStableIds()
  {
    return (mAdapter != null) && (mAdapter.hasStableIds());
  }
  
  public boolean isEmpty()
  {
    return getCount() == 0;
  }
  
  public boolean isEnabled(int paramInt)
  {
    ListAdapter localListAdapter = mListAdapter;
    if (localListAdapter != null) {
      return localListAdapter.isEnabled(paramInt);
    }
    return true;
  }
  
  public void registerDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    if (mAdapter != null) {
      mAdapter.registerDataSetObserver(paramDataSetObserver);
    }
  }
  
  public void unregisterDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    if (mAdapter != null) {
      mAdapter.unregisterDataSetObserver(paramDataSetObserver);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.SpinnerCompat.DropDownAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */