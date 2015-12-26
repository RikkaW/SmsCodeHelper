package com.android.mms.util;

import android.database.DataSetObserver;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import junit.framework.Assert;

class EditableListView$EditableListData
  extends DataSetObserver
  implements EditableListView.EditableListViewCheckable
{
  private ListAdapter mAdapter;
  private HashSet<Long> mCheckedIds;
  public boolean mDataSetChanged;
  public boolean mEditMode;
  private HashMap<Long, Integer> mIdPositionMap;
  private boolean mInnerDateSetChange;
  private boolean mIsCheckDataDirty;
  private Integer mToggleIndex;
  
  protected EditableListView$EditableListData(EditableListView paramEditableListView)
  {
    clear();
  }
  
  private void cleanupCheckedData()
  {
    if (mIsCheckDataDirty)
    {
      HashSet localHashSet = new HashSet();
      mIdPositionMap.clear();
      int i = 0;
      while (i < mAdapter.getCount())
      {
        Long localLong = Long.valueOf(getIdFromPosition(i));
        if (mCheckedIds.contains(localLong)) {
          localHashSet.add(localLong);
        }
        mIdPositionMap.put(localLong, Integer.valueOf(i));
        i += 1;
      }
      mCheckedIds = localHashSet;
      mIsCheckDataDirty = false;
    }
  }
  
  private void enterEditMode(Integer paramInteger)
  {
    if ((!mEditMode) && (mAdapter != null) && (EditableListView.access$1600(this$0) != null))
    {
      mEditMode = true;
      mCheckedIds.clear();
      mToggleIndex = paramInteger;
      if (paramInteger != null)
      {
        innerToggleAt(paramInteger.intValue());
        EditableListView.access$1600(this$0).onCheckStateChanged(this);
      }
      if ((mAdapter instanceof BaseAdapter))
      {
        mInnerDateSetChange = true;
        mDataSetChanged = true;
        ((BaseAdapter)mAdapter).notifyDataSetChanged();
        mDataSetChanged = false;
      }
    }
  }
  
  private void exitEditMode()
  {
    if ((mEditMode) && (mAdapter != null) && (EditableListView.access$1600(this$0) != null))
    {
      mEditMode = false;
      mCheckedIds.clear();
      if ((mAdapter instanceof BaseAdapter))
      {
        mInnerDateSetChange = true;
        mDataSetChanged = true;
        ((BaseAdapter)mAdapter).notifyDataSetChanged();
        mDataSetChanged = false;
      }
    }
  }
  
  private long getIdFromPosition(int paramInt)
  {
    if ((mAdapter instanceof EditableListView.EditableListIdMapper)) {
      return ((EditableListView.EditableListIdMapper)mAdapter).mapPositionToId(paramInt);
    }
    return mAdapter.getItemId(paramInt);
  }
  
  private boolean hasAdapter()
  {
    return mAdapter != null;
  }
  
  private boolean innerToggleAt(int paramInt)
  {
    if (((mAdapter instanceof EditableListView.ICheckableAdapter)) && (!((EditableListView.ICheckableAdapter)mAdapter).allowChecked(paramInt))) {
      return false;
    }
    long l = getIdFromPosition(paramInt);
    if (mCheckedIds.contains(Long.valueOf(l)))
    {
      mCheckedIds.remove(Long.valueOf(l));
      return false;
    }
    mCheckedIds.add(Long.valueOf(l));
    return true;
  }
  
  private void notifyCheckStateChanged(Integer paramInteger)
  {
    mToggleIndex = paramInteger;
    if (EditableListView.access$1600(this$0) != null) {
      EditableListView.access$1600(this$0).onCheckStateChanged(this);
    }
  }
  
  private void setAdapter(ListAdapter paramListAdapter)
  {
    if (mAdapter != null) {
      mAdapter.unregisterDataSetObserver(this);
    }
    mAdapter = paramListAdapter;
    if (mAdapter != null)
    {
      Assert.assertEquals(true, mAdapter instanceof BaseAdapter);
      if (!mAdapter.hasStableIds()) {
        Assert.assertEquals(true, mAdapter instanceof EditableListView.EditableListIdMapper);
      }
      mAdapter.registerDataSetObserver(this);
    }
    mCheckedIds.clear();
    mIdPositionMap.clear();
    mIsCheckDataDirty = true;
    if (mEditMode) {
      notifyCheckStateChanged(null);
    }
  }
  
  private Boolean toggleAt(View paramView, int paramInt)
  {
    if ((mEditMode) && (mAdapter != null))
    {
      boolean bool = innerToggleAt(paramInt);
      CheckBox localCheckBox = (CheckBox)paramView.findViewById(16908289);
      if ((localCheckBox != null) && (localCheckBox.isEnabled())) {
        localCheckBox.setChecked(bool);
      }
      if (EditableListView.access$1600(this$0) != null) {
        EditableListView.access$1600(this$0).onVisibleViewCheckStateChanged(paramView, bool);
      }
      notifyCheckStateChanged(Integer.valueOf(paramInt));
      return Boolean.valueOf(bool);
    }
    return null;
  }
  
  public void checkAll()
  {
    if ((mEditMode) && (mAdapter != null))
    {
      Object localObject;
      long l;
      if ((mAdapter instanceof EditableListView.ICheckableAdapter))
      {
        localObject = (EditableListView.ICheckableAdapter)mAdapter;
        int k = ((EditableListView.ICheckableAdapter)localObject).getDisableCheckedCount();
        int j = 0;
        i = 0;
        if (i < mAdapter.getCount())
        {
          if (j == k)
          {
            l = getIdFromPosition(i);
            mCheckedIds.add(Long.valueOf(l));
          }
          for (;;)
          {
            i += 1;
            break;
            if (((EditableListView.ICheckableAdapter)localObject).allowChecked(i))
            {
              l = getIdFromPosition(i);
              mCheckedIds.add(Long.valueOf(l));
            }
            else
            {
              j += 1;
            }
          }
        }
      }
      else
      {
        i = 0;
        while (i < mAdapter.getCount())
        {
          l = getIdFromPosition(i);
          mCheckedIds.add(Long.valueOf(l));
          i += 1;
        }
      }
      int i = 0;
      while (i < this$0.getChildCount())
      {
        localObject = this$0.getChildAt(i);
        CheckBox localCheckBox = (CheckBox)((View)localObject).findViewById(16908289);
        if ((localCheckBox != null) && (localCheckBox.isEnabled())) {
          localCheckBox.setChecked(true);
        }
        if (EditableListView.access$1600(this$0) != null) {
          EditableListView.access$1600(this$0).onVisibleViewCheckStateChanged((View)localObject, true);
        }
        i += 1;
      }
      notifyCheckStateChanged(null);
    }
  }
  
  public void checkNothing()
  {
    if ((mEditMode) && (mAdapter != null))
    {
      mCheckedIds.clear();
      int i = 0;
      while (i < this$0.getChildCount())
      {
        View localView = this$0.getChildAt(i);
        CheckBox localCheckBox = (CheckBox)localView.findViewById(16908289);
        if ((localCheckBox != null) && (localCheckBox.isEnabled())) {
          localCheckBox.setChecked(false);
        }
        if (EditableListView.access$1600(this$0) != null) {
          EditableListView.access$1600(this$0).onVisibleViewCheckStateChanged(localView, false);
        }
        i += 1;
      }
      notifyCheckStateChanged(null);
    }
  }
  
  protected void clear()
  {
    mInnerDateSetChange = false;
    mEditMode = false;
    mIsCheckDataDirty = true;
    if (mCheckedIds == null) {
      mCheckedIds = new HashSet();
    }
    for (;;)
    {
      mToggleIndex = null;
      if (mIdPositionMap != null) {
        break;
      }
      mIdPositionMap = new HashMap();
      return;
      mCheckedIds.clear();
    }
    mIdPositionMap.clear();
  }
  
  public int count()
  {
    if ((mEditMode) && (mAdapter != null))
    {
      cleanupCheckedData();
      return mCheckedIds.size();
    }
    return 0;
  }
  
  public HashSet<Long> getCheckedItemInIds()
  {
    if ((mEditMode) && (mAdapter != null))
    {
      cleanupCheckedData();
      return new HashSet(mCheckedIds);
    }
    return new HashSet();
  }
  
  public HashSet<Integer> getCheckedItemInPositions()
  {
    HashSet localHashSet = new HashSet();
    if ((mEditMode) && (mAdapter != null))
    {
      cleanupCheckedData();
      Iterator localIterator = mCheckedIds.iterator();
      while (localIterator.hasNext())
      {
        Long localLong = (Long)localIterator.next();
        localHashSet.add(mIdPositionMap.get(localLong));
      }
    }
    return localHashSet;
  }
  
  public boolean isAllChecked()
  {
    boolean bool2 = true;
    boolean bool1 = true;
    EditableListView.ICheckableAdapter localICheckableAdapter;
    int j;
    int i;
    long l;
    if ((mEditMode) && (mAdapter != null))
    {
      cleanupCheckedData();
      if (!(mAdapter instanceof EditableListView.ICheckableAdapter)) {
        break label163;
      }
      localICheckableAdapter = (EditableListView.ICheckableAdapter)mAdapter;
      int k = localICheckableAdapter.getDisableCheckedCount();
      j = 0;
      i = 0;
      if (i >= mAdapter.getCount()) {
        break label144;
      }
      if (j != k) {
        break label97;
      }
      l = getIdFromPosition(i);
      if (mCheckedIds.contains(Long.valueOf(l))) {
        break label130;
      }
    }
    label97:
    do
    {
      return false;
      if (!localICheckableAdapter.allowChecked(i)) {
        break;
      }
      l = getIdFromPosition(i);
    } while (!mCheckedIds.contains(Long.valueOf(l)));
    for (;;)
    {
      label130:
      i += 1;
      break;
      j += 1;
    }
    label144:
    if (mCheckedIds.size() > 0) {}
    for (;;)
    {
      return bool1;
      bool1 = false;
    }
    label163:
    if (mAdapter.getCount() == mCheckedIds.size()) {}
    for (bool1 = bool2;; bool1 = false) {
      return bool1;
    }
  }
  
  public void onChanged()
  {
    if (!mInnerDateSetChange)
    {
      mIsCheckDataDirty = true;
      if (mEditMode) {
        notifyCheckStateChanged(null);
      }
    }
    mInnerDateSetChange = false;
  }
  
  public void onInvalidated()
  {
    mCheckedIds.clear();
    mIdPositionMap.clear();
    mIsCheckDataDirty = true;
    if (mEditMode) {
      notifyCheckStateChanged(null);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.EditableListView.EditableListData
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */