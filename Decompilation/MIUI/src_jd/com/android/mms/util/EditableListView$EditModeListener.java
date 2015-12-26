package com.android.mms.util;

import android.view.ActionMode.Callback;
import android.view.View;

public abstract interface EditableListView$EditModeListener
  extends ActionMode.Callback
{
  public abstract void onCheckStateChanged(EditableListView.EditableListViewCheckable paramEditableListViewCheckable);
  
  public abstract void onVisibleViewCheckStateChanged(View paramView, boolean paramBoolean);
}

/* Location:
 * Qualified Name:     com.android.mms.util.EditableListView.EditModeListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */