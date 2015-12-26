package com.meizu.common.widget;

import java.util.ArrayList;

public class BasePartitionAdapter$Partition
{
  int mCount;
  ArrayList<BasePartitionAdapter.PartitionFixedViewInfo> mFooterViewInfos;
  int mFooterViewsCount;
  boolean mHasHeader;
  ArrayList<BasePartitionAdapter.PartitionFixedViewInfo> mHeaderViewInfos;
  int mHeaderViewsCount;
  int mItemCount;
  boolean mShowIfEmpty;
  int mSize;
  
  public BasePartitionAdapter$Partition(boolean paramBoolean1, boolean paramBoolean2, int paramInt)
  {
    mShowIfEmpty = paramBoolean1;
    mHasHeader = paramBoolean2;
    mItemCount = paramInt;
    mHeaderViewInfos = new ArrayList();
    mFooterViewInfos = new ArrayList();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.BasePartitionAdapter.Partition
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */