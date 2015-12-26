package com.android.mms.util;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListAdapter;

class EditableListView$4
  implements AdapterView.OnItemLongClickListener
{
  EditableListView$4(EditableListView paramEditableListView) {}
  
  public boolean onItemLongClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (this$0.getAdapter().getItemViewType(paramInt) == -2) {
      return false;
    }
    this$0.enterEditMode(paramInt);
    return true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.EditableListView.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */