package com.android.mms.util;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class EditableListView$EditModeWrapper
  implements EditableListView.EditModeListener
{
  private EditableListView.EditModeListener mWrapped;
  
  EditableListView$EditModeWrapper(EditableListView paramEditableListView) {}
  
  private int handleHeadFooterPosition(int paramInt)
  {
    if (paramInt < 0) {
      return paramInt;
    }
    int i = this$0.getHeaderViewsCount();
    if ((paramInt < i) || (paramInt >= this$0.getCount() - this$0.getFooterViewsCount())) {
      return -1;
    }
    return paramInt - i;
  }
  
  private void innerEnterEditMode(Integer paramInteger)
  {
    if (access$1900this$0).mEditMode) {
      return;
    }
    EditableListView.access$2002(this$0, this$0.isClickable());
    EditableListView.access$2102(this$0, this$0.isLongClickable());
    EditableListView.access$2202(this$0, this$0.getOnItemClickListener());
    this$0.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousInt = EditableListView.EditModeWrapper.this.handleHeadFooterPosition(paramAnonymousInt);
        if (paramAnonymousInt != -1) {
          EditableListView.EditableListData.access$2400(EditableListView.access$1900(this$0), paramAnonymousView, paramAnonymousInt);
        }
      }
    });
    this$0.setLongClickable(false);
    Integer localInteger = null;
    if (paramInteger != null)
    {
      paramInteger = Integer.valueOf(handleHeadFooterPosition(paramInteger.intValue()));
      localInteger = paramInteger;
      if (paramInteger.intValue() == -1) {
        localInteger = null;
      }
    }
    EditableListView.EditableListData.access$2500(EditableListView.access$1900(this$0), localInteger);
  }
  
  private void innerExitEditMode()
  {
    if (!access$1900this$0).mEditMode) {
      return;
    }
    this$0.setOnItemClickListener(EditableListView.access$2200(this$0));
    this$0.setClickable(EditableListView.access$2000(this$0));
    this$0.setLongClickable(EditableListView.access$2100(this$0));
    EditableListView.EditableListData.access$2600(EditableListView.access$1900(this$0));
  }
  
  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    return mWrapped.onActionItemClicked(paramActionMode, paramMenuItem);
  }
  
  public void onCheckStateChanged(EditableListView.EditableListViewCheckable paramEditableListViewCheckable)
  {
    mWrapped.onCheckStateChanged(paramEditableListViewCheckable);
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    if (mWrapped.onCreateActionMode(paramActionMode, paramMenu))
    {
      innerEnterEditMode(EditableListView.access$1700(this$0));
      return true;
    }
    return false;
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode)
  {
    mWrapped.onDestroyActionMode(paramActionMode);
    EditableListView.access$1802(this$0, null);
    innerExitEditMode();
  }
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    return mWrapped.onPrepareActionMode(paramActionMode, paramMenu);
  }
  
  public void onVisibleViewCheckStateChanged(View paramView, boolean paramBoolean)
  {
    mWrapped.onVisibleViewCheckStateChanged(paramView, paramBoolean);
  }
  
  public void setWrapped(EditableListView.EditModeListener paramEditModeListener)
  {
    mWrapped = paramEditModeListener;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.EditableListView.EditModeWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */