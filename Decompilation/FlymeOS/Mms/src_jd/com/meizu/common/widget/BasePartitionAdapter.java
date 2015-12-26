package com.meizu.common.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.meizu.common.R.drawable;
import com.meizu.widget.ListSelectFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public abstract class BasePartitionAdapter
  extends AbsBasePartitionAdapter
  implements ListSelectFilter
{
  private static final int CAPACITY_INCREMENT = 10;
  protected static final int INITIAL_CAPACITY = 10;
  public static final int ITEM_VIEW_TYPE_PARTITION_HEADER = 0;
  public static final int PARTITION_FIRST_ITEM_BG_TYPE = 1;
  public static final int PARTITION_HEADER_ITEM_BG_TYPE = 0;
  public static final int PARTITION_LAST_ITEM_BG_TYPE = 3;
  public static final int PARTITION_MIDDLE_ITEM_BG_TYPE = 2;
  public static final int PARTITION_SINGLE_ITEM_BG_TYPE = 4;
  protected boolean mCacheValid;
  protected final Context mContext;
  protected int mCount;
  protected int mItemCounts;
  private boolean mNotificationNeeded;
  private boolean mNotificationsEnabled = true;
  protected int mPartitionCount;
  protected Partition[] mPartitions;
  
  public BasePartitionAdapter(Context paramContext)
  {
    this(paramContext, 10);
  }
  
  public BasePartitionAdapter(Context paramContext, int paramInt)
  {
    mContext = paramContext;
    mPartitions = new Partition[paramInt];
  }
  
  private boolean areAllPartitionFixedViewsSelectable(ArrayList<PartitionFixedViewInfo> paramArrayList)
  {
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext()) {
      if (!nextisSelectable) {
        return false;
      }
    }
    return true;
  }
  
  private boolean removeFixedViewInfo(View paramView, ArrayList<PartitionFixedViewInfo> paramArrayList)
  {
    int j = paramArrayList.size();
    int i = 0;
    while (i < j)
    {
      if (getview == paramView)
      {
        paramArrayList.remove(i);
        invalidate();
        notifyDataSetChanged();
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public void addFooterView(int paramInt, View paramView)
  {
    addFooterView(paramInt, paramView, null, true);
  }
  
  public void addFooterView(int paramInt, View paramView, Object paramObject, boolean paramBoolean)
  {
    if (paramInt >= mPartitionCount) {
      throw new ArrayIndexOutOfBoundsException(paramInt);
    }
    PartitionFixedViewInfo localPartitionFixedViewInfo = new PartitionFixedViewInfo();
    view = paramView;
    data = paramObject;
    isSelectable = paramBoolean;
    mPartitions[paramInt].mFooterViewInfos.add(localPartitionFixedViewInfo);
    invalidate();
    notifyDataSetChanged();
  }
  
  public void addHeaderView(int paramInt, View paramView)
  {
    addHeaderView(paramInt, paramView, null, true);
  }
  
  public void addHeaderView(int paramInt, View paramView, Object paramObject, boolean paramBoolean)
  {
    if (paramInt >= mPartitionCount) {
      throw new ArrayIndexOutOfBoundsException(paramInt);
    }
    PartitionFixedViewInfo localPartitionFixedViewInfo = new PartitionFixedViewInfo();
    view = paramView;
    data = paramObject;
    isSelectable = paramBoolean;
    mPartitions[paramInt].mHeaderViewInfos.add(localPartitionFixedViewInfo);
    invalidate();
    notifyDataSetChanged();
  }
  
  protected int addPartition(Partition paramPartition)
  {
    if (mPartitionCount >= mPartitions.length)
    {
      arrayOfPartition = new Partition[mPartitionCount + 10];
      System.arraycopy(mPartitions, 0, arrayOfPartition, 0, mPartitionCount);
      mPartitions = arrayOfPartition;
    }
    Partition[] arrayOfPartition = mPartitions;
    int i = mPartitionCount;
    mPartitionCount = (i + 1);
    arrayOfPartition[i] = paramPartition;
    invalidate();
    notifyDataSetChanged();
    return mPartitionCount - 1;
  }
  
  public boolean areAllItemsEnabled()
  {
    int i = 0;
    while (i < mPartitionCount)
    {
      if (mPartitions[i].mHasHeader) {}
      while ((!areAllPartitionFixedViewsSelectable(mPartitions[i].mHeaderViewInfos)) || (!areAllPartitionFixedViewsSelectable(mPartitions[i].mFooterViewInfos))) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  protected void bindHeaderView(View paramView, Context paramContext, int paramInt1, int paramInt2) {}
  
  protected boolean canSelect(int paramInt1, int paramInt2)
  {
    return true;
  }
  
  public void clearPartitions()
  {
    Arrays.fill(mPartitions, null);
    mPartitionCount = 0;
    invalidate();
    notifyDataSetChanged();
  }
  
  protected void ensureCacheValid()
  {
    int i = 0;
    if (mCacheValid) {
      return;
    }
    mCount = 0;
    mItemCounts = 0;
    while (i < mPartitionCount)
    {
      mPartitions[i].mHeaderViewsCount = mPartitions[i].mHeaderViewInfos.size();
      mPartitions[i].mFooterViewsCount = mPartitions[i].mFooterViewInfos.size();
      mPartitions[i].mCount = (mPartitions[i].mHeaderViewsCount + mPartitions[i].mItemCount + mPartitions[i].mFooterViewsCount);
      int k = mPartitions[i].mCount;
      int j = k;
      if (mPartitions[i].mHasHeader) {
        if (k == 0)
        {
          j = k;
          if (!mPartitions[i].mShowIfEmpty) {}
        }
        else
        {
          j = k + 1;
        }
      }
      mPartitions[i].mSize = j;
      mCount = (j + mCount);
      mItemCounts += mPartitions[i].mItemCount;
      i += 1;
    }
    mCacheValid = true;
  }
  
  protected int getBackgroundResource(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return 0;
    case 1: 
      return R.drawable.mz_card_top_shade_light;
    case 2: 
      return R.drawable.mz_card_middle_shade_light;
    case 3: 
      return R.drawable.mz_card_bottom_shade_light;
    }
    return R.drawable.mz_card_full_shade_light;
  }
  
  public Context getContext()
  {
    return mContext;
  }
  
  public int getCount()
  {
    ensureCacheValid();
    return mCount;
  }
  
  public int getCountForPartition(int paramInt)
  {
    if (paramInt >= mPartitionCount) {
      throw new ArrayIndexOutOfBoundsException(paramInt);
    }
    ensureCacheValid();
    return mPartitions[paramInt].mCount;
  }
  
  public int getFooterViewsCount(int paramInt)
  {
    if (paramInt >= mPartitionCount) {
      throw new ArrayIndexOutOfBoundsException(paramInt);
    }
    ensureCacheValid();
    return mPartitions[paramInt].mFooterViewsCount;
  }
  
  protected View getHeaderView(int paramInt1, int paramInt2, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView != null) {}
    for (;;)
    {
      bindHeaderView(paramView, mContext, paramInt1, paramInt2);
      return paramView;
      paramView = newHeaderView(mContext, paramInt1, paramInt2, paramViewGroup);
    }
  }
  
  public int getHeaderViewsCount(int paramInt)
  {
    if (paramInt >= mPartitionCount) {
      throw new ArrayIndexOutOfBoundsException(paramInt);
    }
    ensureCacheValid();
    return mPartitions[paramInt].mHeaderViewsCount;
  }
  
  public Object getItem(int paramInt)
  {
    int i = 0;
    ensureCacheValid();
    int k;
    for (int j = 0; i < mPartitionCount; j = k)
    {
      k = mPartitions[i].mSize + j;
      if ((paramInt >= j) && (paramInt < k))
      {
        j = paramInt - j;
        paramInt = j;
        if (mPartitions[i].mHasHeader) {
          paramInt = j - 1;
        }
        if (paramInt == -1) {
          return null;
        }
        if (isHeaderView(i, paramInt)) {
          return mPartitions[i].mHeaderViewInfos.get(paramInt)).data;
        }
        if (isFooterView(i, paramInt))
        {
          j = mPartitions[i].mCount;
          k = mPartitions[i].mFooterViewsCount;
          return mPartitions[i].mFooterViewInfos.get(paramInt - (j - k))).data;
        }
        return getItem(i, paramInt);
      }
      i += 1;
    }
    return null;
  }
  
  protected abstract Object getItem(int paramInt1, int paramInt2);
  
  protected int getItemBackgroundType(int paramInt1, int paramInt2)
  {
    int i = 1;
    if (paramInt2 == -1) {
      i = 0;
    }
    do
    {
      return i;
      if ((paramInt2 == 0) && (mPartitions[paramInt1].mCount == 1)) {
        return 4;
      }
    } while (paramInt2 == 0);
    if (paramInt2 == mPartitions[paramInt1].mCount - 1) {
      return 3;
    }
    return 2;
  }
  
  public int getItemCount()
  {
    ensureCacheValid();
    return mItemCounts;
  }
  
  public long getItemId(int paramInt)
  {
    int i = 0;
    ensureCacheValid();
    int k;
    for (int j = 0; i < mPartitionCount; j = k)
    {
      k = mPartitions[i].mSize + j;
      if ((paramInt >= j) && (paramInt < k))
      {
        j = paramInt - j;
        paramInt = j;
        if (mPartitions[i].mHasHeader) {
          paramInt = j - 1;
        }
        if (paramInt == -1) {
          return 0L;
        }
        if ((isHeaderView(i, paramInt)) || (isFooterView(i, paramInt))) {
          return -1L;
        }
        return getItemId(i, paramInt);
      }
      i += 1;
    }
    return 0L;
  }
  
  protected abstract long getItemId(int paramInt1, int paramInt2);
  
  public int getItemViewType(int paramInt)
  {
    ensureCacheValid();
    int i = 0;
    int k;
    for (int j = 0; i < mPartitionCount; j = k)
    {
      k = mPartitions[i].mSize + j;
      if ((paramInt >= j) && (paramInt < k))
      {
        k = paramInt - j;
        j = k;
        if (mPartitions[i].mHasHeader) {
          j = k - 1;
        }
        if (j == -1) {
          return 0;
        }
        if ((isHeaderView(i, j)) || (isFooterView(i, j))) {
          return -2;
        }
        return getItemViewType(i, paramInt);
      }
      i += 1;
    }
    throw new ArrayIndexOutOfBoundsException(paramInt);
  }
  
  protected int getItemViewType(int paramInt1, int paramInt2)
  {
    return 1;
  }
  
  public int getItemViewTypeCount()
  {
    return 1;
  }
  
  public int getOffsetInPartition(int paramInt)
  {
    int i = 0;
    ensureCacheValid();
    int k;
    for (int j = 0; i < mPartitionCount; j = k)
    {
      k = mPartitions[i].mSize + j;
      if ((paramInt >= j) && (paramInt < k))
      {
        paramInt -= j;
        if (!mPartitions[i].mHasHeader) {
          return paramInt;
        }
        return paramInt - 1;
      }
      i += 1;
    }
    throw new ArrayIndexOutOfBoundsException(paramInt);
    return paramInt;
  }
  
  public Partition getPartition(int paramInt)
  {
    if (paramInt >= mPartitionCount) {
      throw new ArrayIndexOutOfBoundsException(paramInt);
    }
    return mPartitions[paramInt];
  }
  
  public int getPartitionCount()
  {
    return mPartitionCount;
  }
  
  public int getPartitionForPosition(int paramInt)
  {
    int i = 0;
    ensureCacheValid();
    int k;
    for (int j = 0; i < mPartitionCount; j = k)
    {
      k = mPartitions[i].mSize + j;
      if ((paramInt >= j) && (paramInt < k)) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public int getPositionForPartition(int paramInt)
  {
    int i = 0;
    if (paramInt >= mPartitionCount) {
      throw new ArrayIndexOutOfBoundsException(paramInt);
    }
    ensureCacheValid();
    int j = 0;
    while (i < paramInt)
    {
      j += mPartitions[i].mSize;
      i += 1;
    }
    return j;
  }
  
  protected abstract View getView(int paramInt1, int paramInt2, int paramInt3, int paramInt4, View paramView, ViewGroup paramViewGroup);
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    int i = 0;
    ensureCacheValid();
    int k;
    for (int j = 0; i < mPartitionCount; j = k)
    {
      k = mPartitions[i].mSize + j;
      if ((paramInt >= j) && (paramInt < k))
      {
        k = paramInt - j;
        j = k;
        if (mPartitions[i].mHasHeader) {
          j = k - 1;
        }
        k = getItemBackgroundType(i, j);
        if (j == -1) {
          paramView = getHeaderView(paramInt, i, paramView, paramViewGroup);
        }
        while (paramView == null)
        {
          throw new NullPointerException("View should not be null, partition: " + i + " position: " + paramInt);
          if (isHeaderView(i, j))
          {
            paramView = mPartitions[i].mHeaderViewInfos.get(j)).view;
          }
          else if (isFooterView(i, j))
          {
            k = mPartitions[i].mCount;
            int m = mPartitions[i].mFooterViewsCount;
            paramView = mPartitions[i].mFooterViewInfos.get(j - (k - m))).view;
          }
          else
          {
            paramView = getView(paramInt, i, j, k, paramView, paramViewGroup);
          }
        }
      }
      i += 1;
    }
    throw new ArrayIndexOutOfBoundsException(paramInt);
    return paramView;
  }
  
  public int getViewTypeCount()
  {
    return getItemViewTypeCount() + 1;
  }
  
  public boolean hasHeader(int paramInt)
  {
    return mPartitions[paramInt].mHasHeader;
  }
  
  protected void invalidate()
  {
    mCacheValid = false;
  }
  
  public boolean isEnabled(int paramInt)
  {
    ensureCacheValid();
    int i = 0;
    int k;
    for (int j = 0;; j = k)
    {
      if (i < mPartitionCount)
      {
        k = mPartitions[i].mSize + j;
        if ((paramInt < j) || (paramInt >= k)) {
          break label160;
        }
        j = paramInt - j;
        paramInt = j;
        if (mPartitions[i].mHasHeader) {
          paramInt = j - 1;
        }
        if (paramInt != -1) {}
      }
      else
      {
        return false;
      }
      if (isHeaderView(i, paramInt)) {
        return mPartitions[i].mHeaderViewInfos.get(paramInt)).isSelectable;
      }
      if (isFooterView(i, paramInt))
      {
        j = mPartitions[i].mCount;
        k = mPartitions[i].mFooterViewsCount;
        return mPartitions[i].mFooterViewInfos.get(paramInt - (j - k))).isSelectable;
      }
      return isEnabled(i, paramInt);
      label160:
      i += 1;
    }
  }
  
  protected boolean isEnabled(int paramInt1, int paramInt2)
  {
    return true;
  }
  
  public boolean isFooterView(int paramInt)
  {
    int i = 0;
    ensureCacheValid();
    int k;
    for (int j = 0; i < mPartitionCount; j = k)
    {
      k = mPartitions[i].mSize + j;
      if ((paramInt >= j) && (paramInt < k))
      {
        j = paramInt - j;
        paramInt = j;
        if (mPartitions[i].mHasHeader) {
          paramInt = j - 1;
        }
        return isFooterView(i, paramInt);
      }
      i += 1;
    }
    throw new ArrayIndexOutOfBoundsException(paramInt);
  }
  
  protected boolean isFooterView(int paramInt1, int paramInt2)
  {
    return paramInt2 >= mPartitions[paramInt1].mCount - mPartitions[paramInt1].mFooterViewsCount;
  }
  
  public boolean isHeaderView(int paramInt)
  {
    int i = 0;
    ensureCacheValid();
    int k;
    for (int j = 0; i < mPartitionCount; j = k)
    {
      k = mPartitions[i].mSize + j;
      if ((paramInt >= j) && (paramInt < k))
      {
        j = paramInt - j;
        paramInt = j;
        if (mPartitions[i].mHasHeader) {
          paramInt = j - 1;
        }
        return isHeaderView(i, paramInt);
      }
      i += 1;
    }
    throw new ArrayIndexOutOfBoundsException(paramInt);
  }
  
  protected boolean isHeaderView(int paramInt1, int paramInt2)
  {
    return (paramInt2 >= 0) && (paramInt2 < mPartitions[paramInt1].mHeaderViewsCount);
  }
  
  public boolean isPartitionEmpty(int paramInt)
  {
    if (paramInt >= mPartitionCount) {
      throw new ArrayIndexOutOfBoundsException(paramInt);
    }
    ensureCacheValid();
    return mPartitions[paramInt].mCount == 0;
  }
  
  public boolean isSelectable(int paramInt)
  {
    boolean bool2 = false;
    ensureCacheValid();
    int i = 0;
    int k;
    for (int j = 0;; j = k)
    {
      boolean bool1 = bool2;
      if (i < mPartitionCount)
      {
        k = mPartitions[i].mSize + j;
        if ((paramInt < j) || (paramInt >= k)) {
          break label130;
        }
        j = paramInt - j;
        paramInt = j;
        if (mPartitions[i].mHasHeader) {
          paramInt = j - 1;
        }
        j = mPartitions[i].mCount;
        k = mPartitions[i].mFooterViewsCount;
        bool1 = bool2;
        if (paramInt >= mPartitions[i].mHeaderViewsCount)
        {
          bool1 = bool2;
          if (paramInt < j - k) {
            bool1 = canSelect(i, paramInt);
          }
        }
      }
      return bool1;
      label130:
      i += 1;
    }
  }
  
  public boolean isShowIfEmpty(int paramInt)
  {
    return mPartitions[paramInt].mShowIfEmpty;
  }
  
  public boolean isTopHeader()
  {
    int i = getPartitionForPosition(0);
    if (i < 0) {
      return false;
    }
    return mPartitions[i].mHasHeader;
  }
  
  protected View newHeaderView(Context paramContext, int paramInt1, int paramInt2, ViewGroup paramViewGroup)
  {
    return null;
  }
  
  public void notifyDataSetChanged()
  {
    if (mNotificationsEnabled)
    {
      mNotificationNeeded = false;
      super.notifyDataSetChanged();
      return;
    }
    mNotificationNeeded = true;
  }
  
  public boolean removeFooterView(int paramInt, View paramView)
  {
    if (paramInt >= mPartitionCount) {
      throw new ArrayIndexOutOfBoundsException(paramInt);
    }
    return removeFixedViewInfo(paramView, mPartitions[paramInt].mFooterViewInfos);
  }
  
  public boolean removeHeaderView(int paramInt, View paramView)
  {
    if (paramInt >= mPartitionCount) {
      throw new ArrayIndexOutOfBoundsException(paramInt);
    }
    return removeFixedViewInfo(paramView, mPartitions[paramInt].mHeaderViewInfos);
  }
  
  public void removePartition(int paramInt)
  {
    if (paramInt >= mPartitionCount) {
      throw new ArrayIndexOutOfBoundsException(paramInt);
    }
    System.arraycopy(mPartitions, paramInt + 1, mPartitions, paramInt, mPartitionCount - paramInt - 1);
    Partition[] arrayOfPartition = mPartitions;
    paramInt = mPartitionCount - 1;
    mPartitionCount = paramInt;
    arrayOfPartition[paramInt] = null;
    invalidate();
    notifyDataSetChanged();
  }
  
  public void setHasHeader(int paramInt, boolean paramBoolean)
  {
    mPartitions[paramInt].mHasHeader = paramBoolean;
    invalidate();
  }
  
  public void setNotificationsEnabled(boolean paramBoolean)
  {
    mNotificationsEnabled = paramBoolean;
    if ((paramBoolean) && (mNotificationNeeded)) {
      notifyDataSetChanged();
    }
  }
  
  public void setShowIfEmpty(int paramInt, boolean paramBoolean)
  {
    mPartitions[paramInt].mShowIfEmpty = paramBoolean;
    invalidate();
  }
  
  protected void setViewBackground(View paramView, int paramInt)
  {
    paramView.setBackgroundResource(getBackgroundResource(paramInt));
  }
  
  public static class Partition
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
    
    public Partition(boolean paramBoolean1, boolean paramBoolean2, int paramInt)
    {
      mShowIfEmpty = paramBoolean1;
      mHasHeader = paramBoolean2;
      mItemCount = paramInt;
      mHeaderViewInfos = new ArrayList();
      mFooterViewInfos = new ArrayList();
    }
  }
  
  public class PartitionFixedViewInfo
  {
    public Object data;
    public boolean isSelectable;
    public View view;
    
    public PartitionFixedViewInfo() {}
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.BasePartitionAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */