package com.android.mms.util;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class EditableListView$EditModeWrapper$1
  implements AdapterView.OnItemClickListener
{
  EditableListView$EditModeWrapper$1(EditableListView.EditModeWrapper paramEditModeWrapper) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramInt = EditableListView.EditModeWrapper.access$2300(this$1, paramInt);
    if (paramInt != -1) {
      EditableListView.EditableListData.access$2400(EditableListView.access$1900(this$1.this$0), paramView, paramInt);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.EditableListView.EditModeWrapper.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */