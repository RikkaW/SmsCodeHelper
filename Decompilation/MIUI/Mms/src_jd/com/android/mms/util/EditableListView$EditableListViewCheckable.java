package com.android.mms.util;

import java.util.HashSet;

public abstract interface EditableListView$EditableListViewCheckable
{
  public abstract void checkAll();
  
  public abstract void checkNothing();
  
  public abstract int count();
  
  public abstract HashSet<Long> getCheckedItemInIds();
  
  public abstract HashSet<Integer> getCheckedItemInPositions();
  
  public abstract boolean isAllChecked();
}

/* Location:
 * Qualified Name:     com.android.mms.util.EditableListView.EditableListViewCheckable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */