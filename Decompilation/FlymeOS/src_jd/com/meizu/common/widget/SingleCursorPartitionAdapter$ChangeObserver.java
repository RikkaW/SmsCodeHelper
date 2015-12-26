package com.meizu.common.widget;

import android.database.ContentObserver;
import android.os.Handler;

class SingleCursorPartitionAdapter$ChangeObserver
  extends ContentObserver
{
  public SingleCursorPartitionAdapter$ChangeObserver(SingleCursorPartitionAdapter paramSingleCursorPartitionAdapter)
  {
    super(new Handler());
  }
  
  public boolean deliverSelfNotifications()
  {
    return true;
  }
  
  public void onChange(boolean paramBoolean)
  {
    this$0.onContentChanged();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.SingleCursorPartitionAdapter.ChangeObserver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */