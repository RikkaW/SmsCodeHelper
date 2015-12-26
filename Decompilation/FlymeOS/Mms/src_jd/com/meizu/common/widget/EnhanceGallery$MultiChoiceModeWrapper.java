package com.meizu.common.widget;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

class EnhanceGallery$MultiChoiceModeWrapper
  implements EnhanceGallery.MultiChoiceModeListener
{
  private EnhanceGallery.MultiChoiceModeListener mWrapped;
  
  EnhanceGallery$MultiChoiceModeWrapper(EnhanceGallery paramEnhanceGallery) {}
  
  public boolean hasWrappedCallback()
  {
    return mWrapped != null;
  }
  
  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    return mWrapped.onActionItemClicked(paramActionMode, paramMenuItem);
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    if (mWrapped.onCreateActionMode(paramActionMode, paramMenu))
    {
      if (EnhanceGallery.access$1200(this$0) == 1)
      {
        this$0.setLongClickable(true);
        return true;
      }
      this$0.setLongClickable(false);
      return true;
    }
    return false;
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode)
  {
    mWrapped.onDestroyActionMode(paramActionMode);
    EnhanceGallery.access$602(this$0, null);
    EnhanceGallery.access$1300(this$0);
    this$0.invalidateViews();
    this$0.setLongClickable(true);
  }
  
  public void onItemCheckedStateChanged(ActionMode paramActionMode, int paramInt, long paramLong, boolean paramBoolean)
  {
    mWrapped.onItemCheckedStateChanged(paramActionMode, paramInt, paramLong, paramBoolean);
    if (this$0.getCheckedItemCount() == 0) {
      paramActionMode.finish();
    }
  }
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    return mWrapped.onPrepareActionMode(paramActionMode, paramMenu);
  }
  
  public void setWrapped(EnhanceGallery.MultiChoiceModeListener paramMultiChoiceModeListener)
  {
    mWrapped = paramMultiChoiceModeListener;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.EnhanceGallery.MultiChoiceModeWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */