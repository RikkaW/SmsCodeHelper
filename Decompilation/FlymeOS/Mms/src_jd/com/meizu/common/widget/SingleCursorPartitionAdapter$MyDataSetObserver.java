package com.meizu.common.widget;

import android.database.DataSetObserver;

class SingleCursorPartitionAdapter$MyDataSetObserver
  extends DataSetObserver
{
  private SingleCursorPartitionAdapter$MyDataSetObserver(SingleCursorPartitionAdapter paramSingleCursorPartitionAdapter) {}
  
  public void onChanged()
  {
    this$0.notifyDataSetChanged();
  }
  
  public void onInvalidated()
  {
    this$0.notifyDataSetInvalidated();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.SingleCursorPartitionAdapter.MyDataSetObserver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */